package com.github.tingyugetc520.bytedance.lark.api.impl;

import com.github.tingyugetc520.bytedance.lark.api.FsMessageService;
import com.github.tingyugetc520.bytedance.lark.api.FsService;
import com.github.tingyugetc520.bytedance.lark.bean.message.v1.FsMessage;
import com.github.tingyugetc520.bytedance.lark.bean.message.v1.FsMessageSendResult;
import com.github.tingyugetc520.bytedance.lark.constant.FsApiPathConstant;
import com.github.tingyugetc520.bytedance.lark.error.FsErrorException;
import com.github.tingyugetc520.bytedance.lark.util.json.GsonParser;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * message相关
 */
@RequiredArgsConstructor
public class FsMessageServiceImpl implements FsMessageService {
    private final FsService mainService;

    @Override
    public FsMessageSendResult send(FsMessage message) throws FsErrorException {
        String url = this.mainService.getFsConfigStorage().getApiUrl(FsApiPathConstant.Message.MESSAGE_SEND);
        url = String.format(url, StringUtils.isBlank(message.getReceiveIdType()) ? "open_id" : message.getReceiveIdType());

        String responseContent = this.mainService.post(url, message.toJson());
        JsonObject jsonObject = GsonParser.parse(responseContent);
        return FsMessageSendResult.fromJson(jsonObject.get("data").toString());
    }

}
