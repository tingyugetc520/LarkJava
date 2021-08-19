package com.github.tingyugetc520.bytedance.lark.error;

public class FsErrorException extends Exception {
    private static final long serialVersionUID = 676033489287280611L;

    private final FsError error;

    public FsErrorException(String message) {
        this(FsError.builder().errorCode(-1).errorMsg(message).build());
    }

    public FsErrorException(FsError error) {
        super(error.toString());
        this.error = error;
    }

    public FsErrorException(FsError error, Throwable cause) {
        super(error.toString(), cause);
        this.error = error;
    }

    public FsErrorException(Throwable cause) {
        super(cause.getMessage(), cause);
        this.error = FsError.builder().errorCode(-1).errorMsg(cause.getMessage()).build();
    }

    public FsError getError() {
        return this.error;
    }

}
