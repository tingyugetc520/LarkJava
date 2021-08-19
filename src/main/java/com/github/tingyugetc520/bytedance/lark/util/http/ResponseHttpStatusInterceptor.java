package com.github.tingyugetc520.bytedance.lark.util.http;

import com.github.tingyugetc520.bytedance.lark.error.FsRuntimeErrorEnum;
import com.github.tingyugetc520.bytedance.lark.error.FsRuntimeException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.HttpStatus;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@Slf4j
public class ResponseHttpStatusInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();

        Response response = chain.proceed(request);
//        if (response.code() != HttpStatus.SC_OK) {
//            throw new FsRuntimeException(FsRuntimeErrorEnum.FS_HTTP_CALL_FAILED);
//        }

        return response;
    }
}
