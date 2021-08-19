package com.github.tingyugetc520.bytedance.lark.bean.contact.v3;

import com.github.tingyugetc520.bytedance.lark.util.json.FsGsonBuilder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FsUserListResult implements Serializable {
  private static final long serialVersionUID = -4552693690813504019L;

  private Boolean hasMore;
  private String pageToken;
  private List<FsUser> items;

  public static FsUserListResult fromJson(String json) {
    return FsGsonBuilder.create().fromJson(json, FsUserListResult.class);
  }

  public String toJson() {
    return FsGsonBuilder.create().toJson(this);
  }

}
