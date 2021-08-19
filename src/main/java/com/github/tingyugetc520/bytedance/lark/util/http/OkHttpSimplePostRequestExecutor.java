package com.github.tingyugetc520.bytedance.lark.util.http;

import com.github.tingyugetc520.bytedance.lark.error.FsError;
import com.github.tingyugetc520.bytedance.lark.error.FsErrorException;
import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

/**
 * 请求的参数是String, 返回的结果也是String
 */
public class OkHttpSimplePostRequestExecutor implements RequestExecutor<String, String> {
    protected OkHttpClient httpClient;

    private String accessToken;

    public OkHttpSimplePostRequestExecutor(OkHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String execute(String uri, String postEntity) throws FsErrorException, IOException {
        RequestBody body = RequestBody.Companion.create(postEntity, MediaType.parse("text/plain; charset=utf-8"));
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer " + this.accessToken)
                .url(uri).post(body).build();
        Response response = httpClient.newCall(request).execute();
        return this.handleResponse(Objects.requireNonNull(response.body()).string());
    }

    public String handleResponse(String responseContent) throws FsErrorException {
        if (responseContent.isEmpty()) {
            throw new FsErrorException("无响应内容");
        }

        if (responseContent.startsWith("<xml>")) {
            //xml格式输出直接返回
            return responseContent;
        }

        FsError error = FsError.fromJson(responseContent);
        if (error.getErrorCode() != 0) {
            throw new FsErrorException(error);
        }
        return responseContent;
    }

    public static RequestExecutor<String, String> create(OkHttpClient httpClient) {
        return new OkHttpSimplePostRequestExecutor(httpClient);
    }
}
