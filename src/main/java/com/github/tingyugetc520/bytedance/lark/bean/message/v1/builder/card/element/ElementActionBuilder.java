package com.github.tingyugetc520.bytedance.lark.bean.message.v1.builder.card.element;

import com.github.tingyugetc520.bytedance.lark.bean.message.v1.builder.card.element.action.Action;

import java.util.List;

public class ElementActionBuilder extends Element {
    private static final long serialVersionUID = -2763525945365737899L;

    private List<Action> actions;

    public ElementActionBuilder() {
        this.tag = "action";
    }

    public ElementActionBuilder actions(List<Action> actions) {
        this.actions = actions;
        return this;
    }

    public ElementActionBuilder build() {
        return this;
    }
}
