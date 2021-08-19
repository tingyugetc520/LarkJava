package com.github.tingyugetc520.bytedance.lark.error;

import org.jetbrains.annotations.NotNull;

public class FsRuntimeException extends RuntimeException {
    private static final long serialVersionUID = -8901740493005859719L;

    private long code;

    public FsRuntimeException(Throwable e) {
        super(e);
    }

    public FsRuntimeException(String msg) {
        super(msg);
    }

    public FsRuntimeException(@NotNull FsRuntimeErrorEnum errorEnum) {
        super(errorEnum.getMsg());
        this.code = errorEnum.getCode();
    }

    public FsRuntimeException(String msg, Throwable e) {
        super(msg, e);
    }
}
