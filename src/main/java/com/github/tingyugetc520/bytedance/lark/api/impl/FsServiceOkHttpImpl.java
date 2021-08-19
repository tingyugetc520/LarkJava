package com.github.tingyugetc520.bytedance.lark.api.impl;

import com.github.tingyugetc520.bytedance.lark.bean.FsAccessToken;
import com.github.tingyugetc520.bytedance.lark.config.FsConfigStorage;
import com.github.tingyugetc520.bytedance.lark.config.impl.FsDefaultConfigImpl;
import com.github.tingyugetc520.bytedance.lark.constant.FsApiPathConstant;
import com.github.tingyugetc520.bytedance.lark.error.FsError;
import com.github.tingyugetc520.bytedance.lark.error.FsErrorException;
import com.github.tingyugetc520.bytedance.lark.util.http.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Objects;

@Slf4j
public class FsServiceOkHttpImpl extends BaseFsServiceImpl {
    private OkHttpClient httpClient;

    public OkHttpClient getRequestHttpClient() {
        return httpClient;
    }

    @Override
    public String getAccessToken(boolean forceRefresh) throws FsErrorException {
        if (!configStorage.isAccessTokenExpired() && !forceRefresh) {
            return configStorage.getAccessToken();
        }

        synchronized (this.globalAccessTokenRefreshLock) {
            //得到httpClient
            OkHttpClient client = getRequestHttpClient();

            String json = String.format("{\"app_id\":\"%s\",\"app_secret\":\"%s\"}", configStorage.getAppId(), configStorage.getAppSecret());
            RequestBody body = RequestBody.Companion.create(json, MediaType.parse("application/json; charset=utf-8"));
            //请求的request
            Request request = new Request.Builder()
                    .url(configStorage.getApiUrl(FsApiPathConstant.GET_TOKEN))
                    .post(body)
                    .build();
            String resultContent = null;
            try {
                Response response = client.newCall(request).execute();
                resultContent = Objects.requireNonNull(response.body()).string();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }

            FsError error = FsError.fromJson(resultContent);
            if (error.getErrorCode() != 0) {
                throw new FsErrorException(error);
            }
            FsAccessToken accessToken = FsAccessToken.fromJson(resultContent);
            configStorage.updateAccessToken(accessToken.getAccessToken(),
                    accessToken.getExpiresIn());
        }
        return configStorage.getAccessToken();
    }

    @Override
    public void initHttp() {
        log.debug("FsServiceOkHttpImpl initHttp");

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        // 设置代理
        HttpProxyType httpProxyType = HttpProxyType.findByCode(configStorage.getHttpProxyType());
        if (HttpProxyType.FORWARD.equals(httpProxyType)) {
            // 正向代理
            initForwardProxy(clientBuilder);
        }
        httpClient = clientBuilder.addInterceptor(new ResponseHttpStatusInterceptor()).build();
    }

    private void initForwardProxy(OkHttpClient.Builder clientBuilder) {
        if (StringUtils.isBlank(configStorage.getHttpProxyHost()) || configStorage.getHttpProxyPort() <= 0) {
            return;
        }
        OkHttpProxyInfo httpProxy = OkHttpProxyInfo.httpProxy(
                configStorage.getHttpProxyHost(),
                configStorage.getHttpProxyPort(),
                configStorage.getHttpProxyUsername(),
                configStorage.getHttpProxyPassword()
        );
        clientBuilder.proxy(httpProxy.getProxy());

        //设置授权
        clientBuilder.authenticator((route, response) -> {
            String credential = Credentials.basic(httpProxy.getProxyUsername(), httpProxy.getProxyPassword());
            return response.request().newBuilder()
                    .header("Authorization", credential)
                    .build();
        });
    }

    @Override
    public FsConfigStorage getFsConfigStorage() {
        return this.configStorage;
    }

    @Override
    protected RequestExecutor<String, String> getOkHttpSimpleGetRequestExecutor() {
        return OkHttpSimpleGetRequestExecutor.create(httpClient);
    }

    @Override
    protected RequestExecutor<String, String> getOkHttpSimplePostRequestExecutor() {
        return OkHttpSimplePostRequestExecutor.create(httpClient);
    }


}
