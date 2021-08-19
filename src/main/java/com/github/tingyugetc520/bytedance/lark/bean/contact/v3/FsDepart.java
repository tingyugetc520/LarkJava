package com.github.tingyugetc520.bytedance.lark.bean.contact.v3;

import com.github.tingyugetc520.bytedance.lark.util.json.FsGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class FsDepart implements Serializable {
  private static final long serialVersionUID = -5028321625140879571L;

  @SerializedName("department_id")
  private String id;
  @SerializedName("open_department_id")
  private String openId;
  private String name;
  @SerializedName("parent_department_id")
  private String parentId;
  @SerializedName("leader_user_id")
  private String leaderUserId;
  private FsDepartStatus status;

  public static FsDepart fromJson(String json) {
    return FsGsonBuilder.create().fromJson(json, FsDepart.class);
  }

  public String toJson() {
    return FsGsonBuilder.create().toJson(this);
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class FsDepartStatus {
    @SerializedName("is_deleted")
    private Boolean deleted;
  }
}
