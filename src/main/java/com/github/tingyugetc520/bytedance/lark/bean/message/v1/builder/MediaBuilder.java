package com.github.tingyugetc520.bytedance.lark.bean.message.v1.builder;

import com.github.tingyugetc520.bytedance.lark.bean.message.v1.FsMessage;
import com.github.tingyugetc520.bytedance.lark.constant.FsConstant;

/**
 *
 */
public final class MediaBuilder extends BaseBuilder<MediaBuilder> {
  private String fileKey;
  private String imageKey;

  public MediaBuilder() {
    this.msgType = FsConstant.AppMsgType.MEDIA;
  }

  public MediaBuilder fileKey(String fileKey) {
    this.fileKey = fileKey;
    return this;
  }

  public MediaBuilder imageKey(String imageKey) {
    this.imageKey = imageKey;
    return this;
  }

  @Override
  public FsMessage build() {
    FsMessage m = super.build();
    m.setFileKey(this.fileKey);
    m.setImageKey(this.imageKey);
    return m;
  }
}
