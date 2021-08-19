package com.github.tingyugetc520.bytedance.lark.bean.message.v1.builder.card;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Field implements Serializable {
    private static final long serialVersionUID = -5908377852202905231L;

    private Boolean isShort;
    private Text text;
}
