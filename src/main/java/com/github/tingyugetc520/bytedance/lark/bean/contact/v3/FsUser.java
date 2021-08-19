package com.github.tingyugetc520.bytedance.lark.bean.contact.v3;

import com.github.tingyugetc520.bytedance.lark.util.json.FsGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FsUser implements Serializable {
  private static final long serialVersionUID = -5028321625140879571L;

  private String unionId;
  /**
   * 不是唯一，不推荐使用
   */
  @Deprecated
  private String userId;
  private String openId;

  private String name;
  private String email;
  private String mobile;
  private FsUserStatus status;
  @SerializedName("department_ids")
  private List<String> departIds;
  @SerializedName("leader_user_id")
  private String leaderUserId;
  @SerializedName("enterprise_email")
  private String enterpriseEmail;

  public static FsUser fromJson(String json) {
    return FsGsonBuilder.create().fromJson(json, FsUser.class);
  }

  public String toJson() {
    return FsGsonBuilder.create().toJson(this);
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class FsUserStatus {
    @SerializedName("is_frozen")
    private Boolean frozen;
    @SerializedName("is_resigned")
    private Boolean resigned;
    @SerializedName("is_activated")
    private Boolean activated;
  }
}
