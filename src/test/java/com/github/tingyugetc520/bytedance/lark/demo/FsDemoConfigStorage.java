package com.github.tingyugetc520.bytedance.lark.demo;

import com.github.tingyugetc520.bytedance.lark.config.impl.FsDefaultConfigImpl;
import com.github.tingyugetc520.bytedance.lark.util.json.FsGsonBuilder;
import com.google.gson.stream.JsonReader;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class FsDemoConfigStorage extends FsDefaultConfigImpl {
    private static final long serialVersionUID = 4554681793802089123L;
    private String userId;

    public static FsDemoConfigStorage fromJson(InputStream is) {
        JsonReader reader = new JsonReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        return FsGsonBuilder.create().fromJson(reader, FsDemoConfigStorage.class);
    }

}
