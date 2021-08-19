package com.github.tingyugetc520.bytedance.lark.api;

import com.github.tingyugetc520.bytedance.lark.bean.message.v1.FsMessage;
import com.github.tingyugetc520.bytedance.lark.bean.message.v1.FsMessageSendResult;
import com.github.tingyugetc520.bytedance.lark.error.FsErrorException;

/**
 *
 */
public interface FsMessageService {

  FsMessageSendResult send(FsMessage message) throws FsErrorException;

}
