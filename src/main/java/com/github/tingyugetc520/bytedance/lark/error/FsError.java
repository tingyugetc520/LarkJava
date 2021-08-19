package com.github.tingyugetc520.bytedance.lark.error;

import com.github.tingyugetc520.bytedance.lark.util.json.FsGsonBuilder;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class FsError implements Serializable {
    private static final long serialVersionUID = 7869786563361406291L;

    /**
     * 错误代码.
     */
    private int errorCode;

    /**
     * 错误信息.
     */
    private String errorMsg;

    private String json;

    public static FsError fromJson(String json) {
        return FsGsonBuilder.create().fromJson(json, FsError.class);
    }

    @Override
    public String toString() {
        if (this.json == null) {
            return "错误代码：" + this.errorCode + ", 错误信息：" + this.errorMsg;
        }

        return "错误代码：" + this.errorCode + ", 错误信息：" + this.errorMsg + "，飞书原始报文：" + this.json;
    }

}
