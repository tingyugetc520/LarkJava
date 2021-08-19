package com.github.tingyugetc520.bytedance.lark.bean.message.v1.builder;

import com.github.tingyugetc520.bytedance.lark.bean.message.v1.FsMessage;
import com.github.tingyugetc520.bytedance.lark.constant.FsConstant;

/**
 *
 */
public final class TextBuilder extends BaseBuilder<TextBuilder> {
  private String text;

  public TextBuilder() {
    this.msgType = FsConstant.AppMsgType.TEXT;
  }

  public TextBuilder text(String text) {
    this.text = text;
    return this;
  }

  @Override
  public FsMessage build() {
    FsMessage m = super.build();
    m.setText(this.text);
    return m;
  }
}
