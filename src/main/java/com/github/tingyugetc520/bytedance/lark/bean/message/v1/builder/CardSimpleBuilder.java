package com.github.tingyugetc520.bytedance.lark.bean.message.v1.builder;


import com.github.tingyugetc520.bytedance.lark.bean.message.v1.FsMessage;
import com.github.tingyugetc520.bytedance.lark.constant.FsConstant;

/**
 * 消息卡片
 */
public class CardSimpleBuilder extends BaseBuilder<CardSimpleBuilder> {
  private String card;

  public CardSimpleBuilder() {
    this.msgType = FsConstant.AppMsgType.CARD;
  }

  public CardSimpleBuilder card(String card) {
    this.card = card;
    return this;
  }

  @Override
  public FsMessage build() {
    FsMessage m = super.build();
    m.setCardSimple(this.card);
    return m;
  }

}
