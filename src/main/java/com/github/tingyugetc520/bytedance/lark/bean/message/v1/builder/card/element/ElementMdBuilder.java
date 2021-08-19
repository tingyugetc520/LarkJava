package com.github.tingyugetc520.bytedance.lark.bean.message.v1.builder.card.element;

public class ElementMdBuilder extends Element {
    private static final long serialVersionUID = -2763525945365737899L;

    private String content;

    public ElementMdBuilder() {
        this.tag = "markdown";
    }

    public ElementMdBuilder content(String content) {
        this.content = content;
        return this;
    }

    public ElementMdBuilder build() {
        return this;
    }
}
