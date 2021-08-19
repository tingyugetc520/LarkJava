package com.github.tingyugetc520.bytedance.lark.api;

import com.github.tingyugetc520.bytedance.lark.bean.authentication.v1.FsOauth2UserInfo;
import com.github.tingyugetc520.bytedance.lark.error.FsErrorException;

/**
 * OAuth2相关管理接口.
 */
public interface FsOAuth2Service {

    String buildAuthorizationUrl(String redirectUri, String state);

    FsOauth2UserInfo getUserInfo(String code) throws FsErrorException;

}
