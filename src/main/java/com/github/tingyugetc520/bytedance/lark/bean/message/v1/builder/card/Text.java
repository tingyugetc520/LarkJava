package com.github.tingyugetc520.bytedance.lark.bean.message.v1.builder.card;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Text implements Serializable {
    private static final long serialVersionUID = 8333571829601834863L;

    /**
     * plain_text lark_md
     */
    private String tag;
    private String content;
}
