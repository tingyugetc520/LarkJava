package com.github.tingyugetc520.bytedance.lark.util.http;

import com.github.tingyugetc520.bytedance.lark.error.FsError;
import com.github.tingyugetc520.bytedance.lark.error.FsErrorException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

/**
 * 请求的参数是String, 返回的结果也是String
 */
public class OkHttpSimpleGetRequestExecutor implements RequestExecutor<String, String> {
    protected OkHttpClient httpClient;

    private String accessToken;

    public OkHttpSimpleGetRequestExecutor(OkHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String execute(String uri, String queryParam) throws FsErrorException, IOException {
        if (queryParam != null) {
            if (uri.indexOf('?') == -1) {
                uri += '?';
            }
            uri += uri.endsWith("?") ? queryParam : '&' + queryParam;
        }

        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer " + this.accessToken)
                .url(uri).build();
        Response response = httpClient.newCall(request).execute();
        return handleResponse(Objects.requireNonNull(response.body()).string());
    }

    protected String handleResponse(String responseContent) throws FsErrorException {
        FsError error = FsError.fromJson(responseContent);
        if (error.getErrorCode() != 0) {
            throw new FsErrorException(error);
        }

        return responseContent;
    }

    public static RequestExecutor<String, String> create(OkHttpClient httpClient) {
        return new OkHttpSimpleGetRequestExecutor(httpClient);
    }
}
