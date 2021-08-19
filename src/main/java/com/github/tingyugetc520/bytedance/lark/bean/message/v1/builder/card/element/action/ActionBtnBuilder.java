package com.github.tingyugetc520.bytedance.lark.bean.message.v1.builder.card.element.action;

import com.github.tingyugetc520.bytedance.lark.bean.message.v1.builder.card.Text;

public class ActionBtnBuilder extends Action {
    private static final long serialVersionUID = -4830299710272841847L;

    private Text text;
    private String url;
    private String type;

    public ActionBtnBuilder() {
        this.tag = "button";
    }

    public ActionBtnBuilder text(Text text) {
        this.text = text;
        return this;
    }

    public ActionBtnBuilder url(String url) {
        this.url = url;
        return this;
    }

    public ActionBtnBuilder type(String type) {
        this.type = type;
        return this;
    }

    public ActionBtnBuilder build() {
        return this;
    }
}
