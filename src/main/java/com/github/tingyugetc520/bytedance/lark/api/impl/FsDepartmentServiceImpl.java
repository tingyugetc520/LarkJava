package com.github.tingyugetc520.bytedance.lark.api.impl;

import com.github.tingyugetc520.bytedance.lark.api.FsDepartmentService;
import com.github.tingyugetc520.bytedance.lark.api.FsService;
import com.github.tingyugetc520.bytedance.lark.bean.contact.v3.FsDepartListQuery;
import com.github.tingyugetc520.bytedance.lark.bean.contact.v3.FsDepartListResult;
import com.github.tingyugetc520.bytedance.lark.error.FsErrorException;
import com.github.tingyugetc520.bytedance.lark.util.DataUtils;
import com.github.tingyugetc520.bytedance.lark.util.json.GsonParser;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;

import static com.github.tingyugetc520.bytedance.lark.constant.FsApiPathConstant.Department.DEPARTMENT_LIST;


/**
 * 部门管理接口
 */
@RequiredArgsConstructor
public class FsDepartmentServiceImpl implements FsDepartmentService {
    private final FsService mainService;

    @Override
    public FsDepartListResult list(FsDepartListQuery query) throws FsErrorException {
        String json = query.toJson();
        String params = DataUtils.parseJsonToUrlParams(json);

        String url = this.mainService.getFsConfigStorage().getApiUrl(DEPARTMENT_LIST);
        String responseContent = this.mainService.get(url, params);
        JsonObject tmpJsonObject = GsonParser.parse(responseContent);
        return FsDepartListResult.fromJson(tmpJsonObject.get("data").toString());
    }
}
