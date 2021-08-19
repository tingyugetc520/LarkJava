package com.github.tingyugetc520.bytedance.lark.constant;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;

import static com.github.tingyugetc520.bytedance.lark.error.FsErrorMsgEnum.*;

public final class FsConstant {
    /**
     * access_token 过期 需要尝试刷新access_token
     */
    public static final List<Integer> ACCESS_TOKEN_ERROR_CODES = Arrays.asList(
            CODE_4001.getCode(), CODE_20005.getCode(),
            CODE_99991663.getCode(), CODE_99991665.getCode(), CODE_99991677.getCode()
    );

    /**
     * 应用推送消息的消息类型.
     */
    @UtilityClass
    public static class AppMsgType {
        /**
         * 文本消息.
         */
        public static final String TEXT = "text";
        /**
         * 图片消息.
         */
        public static final String IMAGE = "image";
        /**
         * 语音消息.
         */
        public static final String AUDIO = "audio";
        /**
         * 视频
         */
        public static final String MEDIA = "media";
        /**
         * 发送文件
         */
        public static final String FILE = "file";
        /**
         * 表情包
         */
        public static final String STICKER = "sticker";
        /**
         * 卡片消息
         */
        public static final String CARD_SIMPLE = "interactive_simple";
        /**
         * 卡片消息
         */
        public static final String CARD = "interactive";
    }

}
