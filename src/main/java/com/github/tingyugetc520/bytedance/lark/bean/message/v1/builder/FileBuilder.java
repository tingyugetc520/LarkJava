package com.github.tingyugetc520.bytedance.lark.bean.message.v1.builder;

import com.github.tingyugetc520.bytedance.lark.bean.message.v1.FsMessage;
import com.github.tingyugetc520.bytedance.lark.constant.FsConstant;

/**
 *
 */
public final class FileBuilder extends BaseBuilder<FileBuilder> {
  private String fileKey;

  public FileBuilder() {
    this.msgType = FsConstant.AppMsgType.FILE;
  }

  public FileBuilder fileKey(String fileKey) {
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
