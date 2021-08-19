package com.github.tingyugetc520.bytedance.lark.bean.agent.v2;

import com.github.tingyugetc520.bytedance.lark.util.json.FsGsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 应用通讯录权限信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FsAgentAuthScope implements Serializable {
  private static final long serialVersionUID = -900857349581940407L;

  private List<AuthDepart> departments;

  private Integer isVisibleToAll;


  public static FsAgentAuthScope fromJson(String json) {
    return FsGsonBuilder.create().fromJson(json, FsAgentAuthScope.class);
  }

  public String toJson() {
    return FsGsonBuilder.create().toJson(this);
  }

  @Data
  public static class AuthDepart implements Serializable {
    private static final long serialVersionUID = -1618704334282970678L;

    private String id;
  }
}
