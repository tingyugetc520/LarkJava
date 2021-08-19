package com.github.tingyugetc520.bytedance.lark.api;

import com.github.tingyugetc520.bytedance.lark.bean.agent.v2.FsAgentAuthScope;
import com.github.tingyugetc520.bytedance.lark.error.FsErrorException;

public interface FsAgentService {

  FsAgentAuthScope getAuthScope() throws FsErrorException;

}
