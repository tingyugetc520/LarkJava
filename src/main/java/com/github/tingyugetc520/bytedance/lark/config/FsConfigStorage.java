package com.github.tingyugetc520.bytedance.lark.config;

import com.github.tingyugetc520.bytedance.lark.bean.FsAccessToken;

import java.util.concurrent.locks.Lock;

public interface FsConfigStorage {

    void setBaseApiUrl(String baseUrl);

    String getApiUrl(String path);

    String getAccessToken();

    Lock getAccessTokenLock();

    boolean isAccessTokenExpired();

    void expireAccessToken();

    void updateAccessToken(FsAccessToken accessToken);

    void updateAccessToken(String accessToken, int expiresIn);

    String getAppId();

    String getAppSecret();

    long getExpiresTime();

    int getHttpProxyType();

    String getHttpProxyServer();

    String getHttpProxyHost();

    int getHttpProxyPort();

    String getHttpProxyUsername();

    String getHttpProxyPassword();

    boolean autoRefreshToken();

}
