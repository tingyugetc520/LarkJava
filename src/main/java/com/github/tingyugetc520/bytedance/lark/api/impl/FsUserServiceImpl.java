package com.github.tingyugetc520.bytedance.lark.api.impl;

import com.github.tingyugetc520.bytedance.lark.api.FsService;
import com.github.tingyugetc520.bytedance.lark.api.FsUserService;
import com.github.tingyugetc520.bytedance.lark.bean.contact.v3.FsUserListQuery;
import com.github.tingyugetc520.bytedance.lark.bean.contact.v3.FsUserListResult;
import com.github.tingyugetc520.bytedance.lark.error.FsErrorException;
import com.github.tingyugetc520.bytedance.lark.util.DataUtils;
import com.github.tingyugetc520.bytedance.lark.util.json.GsonParser;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;

import static com.github.tingyugetc520.bytedance.lark.constant.FsApiPathConstant.User.USER_LIST;


@RequiredArgsConstructor
public class FsUserServiceImpl implements FsUserService {
    private final FsService mainService;

    @Override
    public FsUserListResult listByDepart(FsUserListQuery query) throws FsErrorException {
        String params = DataUtils.parseJsonToUrlParams(query.toJson());

        String url = this.mainService.getFsConfigStorage().getApiUrl(USER_LIST);
        String responseContent = this.mainService.get(url, params);
        JsonObject jsonObject = GsonParser.parse(responseContent);
        return FsUserListResult.fromJson(jsonObject.get("data").toString());
    }

}
