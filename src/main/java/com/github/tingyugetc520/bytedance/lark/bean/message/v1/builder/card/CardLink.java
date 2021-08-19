package com.github.tingyugetc520.bytedance.lark.bean.message.v1.builder.card;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CardLink implements Serializable {
    private static final long serialVersionUID = -5908377852202905231L;

    private String url;
    private String androidUrl;
    private String iosUrl;
    private String pcUrl;
}
