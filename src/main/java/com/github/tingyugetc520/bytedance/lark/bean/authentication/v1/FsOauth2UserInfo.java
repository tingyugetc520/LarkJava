package com.github.tingyugetc520.bytedance.lark.bean.authentication.v1;

import com.github.tingyugetc520.bytedance.lark.util.json.FsGsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用oauth2获取用户信息的结果类
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FsOauth2UserInfo implements Serializable {
  private static final long serialVersionUID = 3786309563951035110L;

  private String name;
  private String openId;
  private String unionId;
  private String email;
  private String userId;
  private String mobile;

  private String accessToken;
  private Integer expiresIn;

  private String refreshToken;
  private Integer refreshExpiresId;
  private String tokenType;
  private String tenantKey;

  public static FsOauth2UserInfo fromJson(String json) {
    return FsGsonBuilder.create().fromJson(json, FsOauth2UserInfo.class);
  }

  public String toJson() {
    return FsGsonBuilder.create().toJson(this);
  }

}
