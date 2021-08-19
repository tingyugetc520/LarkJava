package com.github.tingyugetc520.bytedance.lark.bean.message.v1.builder;

import com.github.tingyugetc520.bytedance.lark.bean.message.v1.FsMessage;

public class BaseBuilder<T> {
  protected String receiveIdType;
  protected String receiveId;
  protected String msgType;

  public T receiveIdType(String receiveIdType) {
    this.receiveIdType = receiveIdType;
    return (T) this;
  }

  public T receiveId(String receiveId) {
    this.receiveId = receiveId;
    return (T) this;
  }

  public FsMessage build() {
    FsMessage m = new FsMessage();
    m.setReceiveIdType(this.receiveIdType);
    m.setReceiveId(this.receiveId);
    m.setMsgType(this.msgType);
    return m;
  }
}
