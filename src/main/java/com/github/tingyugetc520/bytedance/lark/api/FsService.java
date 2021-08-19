package com.github.tingyugetc520.bytedance.lark.api;


import com.github.tingyugetc520.bytedance.lark.config.FsConfigStorage;
import com.github.tingyugetc520.bytedance.lark.error.FsErrorException;
import com.github.tingyugetc520.bytedance.lark.util.http.RequestExecutor;

public interface FsService extends BaseService {

    /**
     * 获取access_token, 不强制刷新access_token
     *
     * @return the access token
     * @throws FsErrorException the error exception
     * @see #getAccessToken(boolean)
     */
    String getAccessToken() throws FsErrorException;

    /**
     * 获取access_token，本方法线程安全
     * 且在多线程同时刷新时只刷新一次，避免触发调用次数频繁限制
     * 非必要情况下尽量不要主动调用此方法
     *
     * @param forceRefresh 强制刷新
     * @return the access token
     * @throws FsErrorException the error exception
     */
    String getAccessToken(boolean forceRefresh) throws FsErrorException;

    /**
     * Service没有实现某个API的时候，可以用这个，
     * 比{@link #get}和{@link #post}方法更灵活，可以自己构造RequestExecutor用来处理不同的参数和不同的返回类型。
     *
     * @param <T>      请求值类型
     * @param <E>      返回值类型
     * @param executor 执行器
     * @param uri      请求地址
     * @param data     参数
     * @return the t
     * @throws FsErrorException the error exception
     */
    <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data) throws FsErrorException;

    /**
     * 初始化http请求对象
     */
    void initHttp();

    /**
     * 获取configStorage 对象
     */
    FsConfigStorage getFsConfigStorage();

    /**
     * 注入 {@link FsConfigStorage} 的实现
     *
     * @param configProvider 配置对象
     */
    void setFsConfigStorage(FsConfigStorage configProvider);

    /**
     * 获取部门相关接口的服务类对象
     * @return department service
     */
    FsDepartmentService getDepartmentService();

    /**
     * 获取用户相关接口的服务类对象
     *
     * @return the user service
     */
    FsUserService getUserService();

    /**
     * 获取Oauth2相关接口的服务类对象
     *
     * @return the oauth 2 service
     */
    FsOAuth2Service getOauth2Service();

    /**
     * message service
     * @return
     */
    FsMessageService getMessageService();

    FsAgentService getAgentService();

}
