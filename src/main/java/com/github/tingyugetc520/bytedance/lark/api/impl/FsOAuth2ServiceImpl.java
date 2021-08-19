package com.github.tingyugetc520.bytedance.lark.api.impl;

import com.github.tingyugetc520.bytedance.lark.api.FsOAuth2Service;
import com.github.tingyugetc520.bytedance.lark.api.FsService;
import com.github.tingyugetc520.bytedance.lark.bean.authentication.v1.FsOauth2UserInfo;
import com.github.tingyugetc520.bytedance.lark.error.FsErrorException;
import com.github.tingyugetc520.bytedance.lark.util.json.GsonParser;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;

import java.net.URLEncoder;

import static com.github.tingyugetc520.bytedance.lark.constant.FsApiPathConstant.OAuth2.GET_USER_INFO;
import static com.github.tingyugetc520.bytedance.lark.constant.FsApiPathConstant.OAuth2.URL_OAUTH2_AUTHORIZE;

/**
 * oauth2相关接口实现类
 */
@RequiredArgsConstructor
public class FsOAuth2ServiceImpl implements FsOAuth2Service {
    private final FsService mainService;

    @Override
    public String buildAuthorizationUrl(String redirectUri, String state) {
        StringBuilder url = new StringBuilder(URL_OAUTH2_AUTHORIZE);
        url.append("?app_id=").append(this.mainService.getFsConfigStorage().getAppId());
        url.append("&redirect_uri=").append(URLEncoder.encode(redirectUri));

        if (state != null) {
            url.append("&state=").append(state);
        }
        return url.toString();
    }

    @Override
    public FsOauth2UserInfo getUserInfo(String code) throws FsErrorException {
        String json = String.format("{\"grant_type\":\"authorization_code\",\"code\":\"%s\"}", code);
        String responseText = this.mainService.post(this.mainService.getFsConfigStorage().getApiUrl(GET_USER_INFO), json);

        JsonObject jsonObject = GsonParser.parse(responseText);
        return FsOauth2UserInfo.fromJson(jsonObject.get("data").toString());
    }

}
