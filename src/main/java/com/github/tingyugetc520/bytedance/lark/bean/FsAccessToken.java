package com.github.tingyugetc520.bytedance.lark.bean;

import com.github.tingyugetc520.bytedance.lark.util.json.FsGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * access token
 */
@Data
@Accessors(chain = true)
public class FsAccessToken implements Serializable {
    private static final long serialVersionUID = -944200774978928518L;

    @SerializedName("tenant_access_token")
    private String accessToken;

    @SerializedName("expire")
    private int expiresIn = -1;

    public static FsAccessToken fromJson(String json) {
        return FsGsonBuilder.create().fromJson(json, FsAccessToken.class);
    }
}
