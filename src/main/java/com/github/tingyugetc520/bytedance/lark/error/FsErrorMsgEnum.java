package com.github.tingyugetc520.bytedance.lark.error;

import lombok.Getter;

@Getter
public enum FsErrorMsgEnum {
    /**
     *
     */
    CODE_1500(1500, "内部错误，请稍后重试"),

    CODE_4001(4001, "查看token是否填写正确，是否过期"),

    CODE_10012(10012, "获取App Token失败"),
    CODE_10013(10013, "获取Tenant Token失败"),

    CODE_20005(20005, "无效的 access_token，请确保header里的 access_token 是正确、有效的"),
    CODE_99991663(99991663, "tenant_access_token 无效，更新token"),
    CODE_99991665(99991665, "tenant_access_token 非法"),
    CODE_99991677(99991677, "token已过期，请更新token"),
    ;

    private int code;
    private String msg;

    FsErrorMsgEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 通过错误代码查找其中文含义..
     */
    public static String findMsgByCode(int code) {
        for (FsErrorMsgEnum value : FsErrorMsgEnum.values()) {
            if (value.code == code) {
                return value.msg;
            }
        }

        return null;
    }
}
