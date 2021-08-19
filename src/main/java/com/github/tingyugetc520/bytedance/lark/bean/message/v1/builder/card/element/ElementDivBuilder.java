package com.github.tingyugetc520.bytedance.lark.bean.message.v1.builder.card.element;

import com.github.tingyugetc520.bytedance.lark.bean.message.v1.builder.card.Field;
import com.github.tingyugetc520.bytedance.lark.bean.message.v1.builder.card.Text;

import java.util.List;

public class ElementDivBuilder extends Element {
    private static final long serialVersionUID = -4830299710272841847L;

    private Text text;
    private List<Field> fields;

    public ElementDivBuilder() {
        this.tag = "div";
    }

    public ElementDivBuilder text(Text text) {
        this.text = text;
        return this;
    }

    public ElementDivBuilder fields(List<Field> fields) {
        this.fields = fields;
        return this;
    }

    public ElementDivBuilder build() {
        return this;
    }
}
