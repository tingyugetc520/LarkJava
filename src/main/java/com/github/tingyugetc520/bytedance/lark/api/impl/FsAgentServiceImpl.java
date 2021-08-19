package com.github.tingyugetc520.bytedance.lark.api.impl;

import com.github.tingyugetc520.bytedance.lark.api.FsAgentService;
import com.github.tingyugetc520.bytedance.lark.api.FsService;
import com.github.tingyugetc520.bytedance.lark.bean.agent.v2.FsAgentAuthScope;
import com.github.tingyugetc520.bytedance.lark.error.FsErrorException;
import com.github.tingyugetc520.bytedance.lark.util.json.GsonParser;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;

import static com.github.tingyugetc520.bytedance.lark.constant.FsApiPathConstant.Agent.AGENT_AUTH_SCOPE;

/**
 * 应用相关
 */
@RequiredArgsConstructor
public class FsAgentServiceImpl implements FsAgentService {
  private final FsService mainService;

  @Override
  public FsAgentAuthScope getAuthScope() throws FsErrorException {
    String url = this.mainService.getFsConfigStorage().getApiUrl(AGENT_AUTH_SCOPE);
    url = String.format(url, this.mainService.getFsConfigStorage().getAppId());

    String responseContent = this.mainService.get(url, null);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    return FsAgentAuthScope.fromJson(jsonObject.get("data").toString());
  }

}
