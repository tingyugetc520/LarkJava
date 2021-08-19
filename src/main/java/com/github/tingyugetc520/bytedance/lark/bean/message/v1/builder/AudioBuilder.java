package com.github.tingyugetc520.bytedance.lark.bean.message.v1.builder;

import com.github.tingyugetc520.bytedance.lark.bean.message.v1.FsMessage;
import com.github.tingyugetc520.bytedance.lark.constant.FsConstant;

/**
 *
 */
public final class AudioBuilder extends BaseBuilder<AudioBuilder> {
  private String fileKey;

  public AudioBuilder() {
    this.msgType = FsConstant.AppMsgType.AUDIO;
  }

  public AudioBuilder fileKey(String fileKey) {
    this.fileKey = fileKey;
    return this;
  }

  @Override
  public FsMessage build() {
    FsMessage m = super.build();
    m.setFileKey(this.fileKey);
    return m;
  }
}
