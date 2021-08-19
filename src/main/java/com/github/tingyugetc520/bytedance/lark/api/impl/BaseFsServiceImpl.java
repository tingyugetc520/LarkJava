package com.github.tingyugetc520.bytedance.lark.api.impl;

import com.github.tingyugetc520.bytedance.lark.api.*;
import com.github.tingyugetc520.bytedance.lark.config.FsConfigStorage;
import com.github.tingyugetc520.bytedance.lark.constant.FsConstant;
import com.github.tingyugetc520.bytedance.lark.error.FsError;
import com.github.tingyugetc520.bytedance.lark.error.FsErrorException;
import com.github.tingyugetc520.bytedance.lark.error.FsRuntimeException;
import com.github.tingyugetc520.bytedance.lark.util.DataUtils;
import com.github.tingyugetc520.bytedance.lark.util.http.RequestExecutor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static com.github.tingyugetc520.bytedance.lark.error.FsErrorMsgEnum.CODE_1500;


@Slf4j
public abstract class BaseFsServiceImpl implements FsService {
    private final FsUserService userService = new FsUserServiceImpl(this);
    private final FsDepartmentService departmentService = new FsDepartmentServiceImpl(this);
    private final FsOAuth2Service oauth2Service = new FsOAuth2ServiceImpl(this);
    private final FsMessageService messageService = new FsMessageServiceImpl(this);
    private final FsAgentService agentService = new FsAgentServiceImpl(this);

    /**
     * 全局的是否正在刷新access token的锁.
     */
    protected final Object globalAccessTokenRefreshLock = new Object();

    protected FsConfigStorage configStorage;

    private final int retrySleepMillis = 1000;
    private final int maxRetryTimes = 5;

    @Override
    public String getAccessToken() throws FsErrorException {
        return getAccessToken(false);
    }

    @Override
    public String get(String url, String queryParam) throws FsErrorException {
        RequestExecutor<String, String> executor = getOkHttpSimpleGetRequestExecutor();
        return execute(executor, url, queryParam);
    }

    @Override
    public String post(String url, String postData) throws FsErrorException {
        RequestExecutor<String, String> executor = getOkHttpSimplePostRequestExecutor();
        return execute(executor, url, postData);
    }

    /**
     * okHttp get请求
     * @return executor
     */
    protected abstract RequestExecutor<String, String> getOkHttpSimpleGetRequestExecutor();

    /**
     * okHttp post请求
     * @return executor
     */
    protected abstract RequestExecutor<String, String> getOkHttpSimplePostRequestExecutor();

    /**
     * 发送请求，在这里执行的策略是当发生access_token过期时才去刷新，然后重新执行请求，而不是全局定时请求.
     */
    @Override
    public <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data) throws FsErrorException {
        int retryTimes = 0;
        do {
            try {
                return executeInternal(executor, uri, data);
            } catch (FsErrorException e) {
                if (retryTimes + 1 > maxRetryTimes) {
                    log.warn("重试达到最大次数【{}】", maxRetryTimes);
                    //最后一次重试失败后，直接抛出异常，不再等待
                    throw new FsRuntimeException("飞书服务端异常，超出重试次数");
                }

                FsError error = e.getError();
                // 系统繁忙, 1000ms后重试
                if (error.getErrorCode() == CODE_1500.getCode()) {
                    int sleepMillis = retrySleepMillis * (1 << retryTimes);
                    try {
                        log.debug("飞书系统繁忙，{} ms 后重试(第{}次)", sleepMillis, retryTimes + 1);
                        Thread.sleep(sleepMillis);
                    } catch (InterruptedException e1) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    throw e;
                }
            }
        } while (retryTimes++ < maxRetryTimes);

        log.warn("重试达到最大次数【{}】", maxRetryTimes);
        throw new FsRuntimeException("飞书服务端异常，超出重试次数");
    }

    protected <T, E> T executeInternal(RequestExecutor<T, E> executor, String uri, E data) throws FsErrorException {
        E dataForLog = DataUtils.handleDataWithSecret(data);

        if (uri.contains("access_token=")) {
            throw new IllegalArgumentException("uri参数中不允许有access_token: " + uri);
        }

        try {
            String accessToken = getAccessToken(false);
            executor.setAccessToken(accessToken);

            T result = executor.execute(uri, data);
            log.debug("\n【请求地址】: {}\n【请求参数】：{}\n【响应数据】：{}", uri, dataForLog, result);
            return result;
        } catch (FsErrorException e) {
            FsError error = e.getError();

            if (FsConstant.ACCESS_TOKEN_ERROR_CODES.contains(error.getErrorCode())) {
                // 强制设置FsConfigStorage它的access token过期了，这样在下一次请求里就会刷新access token
                this.configStorage.expireAccessToken();
                if (this.getFsConfigStorage().autoRefreshToken()) {
                    log.warn("即将重新获取新的access_token，错误代码：{}，错误信息：{}", error.getErrorCode(), error.getErrorMsg());
                    return this.execute(executor, uri, data);
                }
            }

            if (error.getErrorCode() != 0) {
                log.error("\n【请求地址】: {}\n【请求参数】：{}\n【错误信息】：{}", uri, dataForLog, error);
                throw new FsErrorException(error, e);
            }
            return null;
        } catch (IOException e) {
            log.error("\n【请求地址】: {}\n【请求参数】：{}\n【异常信息】：{}", uri, dataForLog, e.getMessage());
            throw new FsRuntimeException(e);
        }
    }

    @Override
    public void setFsConfigStorage(FsConfigStorage wxConfigProvider) {
        this.configStorage = wxConfigProvider;
        this.initHttp();
    }

    @Override
    public FsDepartmentService getDepartmentService() {
        return departmentService;
    }

    @Override
    public FsOAuth2Service getOauth2Service() {
        return oauth2Service;
    }

    @Override
    public FsUserService getUserService() {
        return userService;
    }

    @Override
    public FsMessageService getMessageService() {
        return messageService;
    }

    @Override
    public FsAgentService getAgentService() {
        return agentService;
    }
}
