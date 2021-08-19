package com.github.tingyugetc520.bytedance.lark.error;

import lombok.Getter;

/**
 * 异常枚举
 */
@Getter
public enum FsRuntimeErrorEnum {
    /**
     *
     */
    FS_HTTP_CALL_FAILED(400, "请求飞书接口异常,请检查地址是否正确或代理服务是否正常"),
    FS_JSON_HTTP_PARAM_FAILED(401, "构建请求参数异常"),
    ;

    private int code;
    private String msg;

    FsRuntimeErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 通过错误代码查找其中文含义..
     */
    public static String findMsgByCode(int code) {
        for (FsRuntimeErrorEnum value : FsRuntimeErrorEnum.values()) {
            if (value.code == code) {
                return value.msg;
            }
        }

        return null;
    }
}
