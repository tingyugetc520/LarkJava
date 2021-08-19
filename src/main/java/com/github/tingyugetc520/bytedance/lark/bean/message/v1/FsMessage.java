package com.github.tingyugetc520.bytedance.lark.bean.message.v1;

import com.github.tingyugetc520.bytedance.lark.bean.message.v1.builder.*;
import com.github.tingyugetc520.bytedance.lark.constant.FsConstant;
import com.github.tingyugetc520.bytedance.lark.util.json.FsGsonBuilder;
import com.google.gson.JsonObject;
import lombok.Data;

import java.io.Serializable;

import static com.github.tingyugetc520.bytedance.lark.constant.FsConstant.AppMsgType.*;

/**
 * 消息.
 */
@Data
public class FsMessage implements Serializable {
    private static final long serialVersionUID = -2082278303476631708L;

    private String receiveIdType;
    private String receiveId;

    private String msgType;

    /**
     * text
     */
    private String text;

    /**
     * image media
     */
    private String imageKey;

    /**
     * audio media file sticker
     */
    private String fileKey;

    /**
     * card
     */
    private String cardSimple;


    /**
     * 获得文本消息builder.
     */
    public static TextBuilder TEXT() {
        return new TextBuilder();
    }

    /**
     * 获得图片消息builder.
     */
    public static ImageBuilder IMAGE() {
        return new ImageBuilder();
    }

    /**
     * 获得语音消息builder.
     */
    public static AudioBuilder VOICE() {
        return new AudioBuilder();
    }

    /**
     * 视频消息builder
     */
    public static MediaBuilder MEDIA() {
        return new MediaBuilder();
    }

    /**
     * 获得文件消息builder.
     */
    public static FileBuilder FILE() {
        return new FileBuilder();
    }

    /**
     * 表情包builder
     */
    public static StickerBuilder STICKER() {
        return new StickerBuilder();
    }

    /**
     * 卡片消息builder.
     */
    public static CardSimpleBuilder CARD_SIMPLE() {
        return new CardSimpleBuilder();
    }

    public static CardBuilder CARD() {
        return new CardBuilder();
    }

    /**
     * 请使用.
     * {@link FsConstant.AppMsgType}
     *
     * @param msgType 消息类型
     */
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public JsonObject toJsonObject() {
        JsonObject messageJson = new JsonObject();
        messageJson.addProperty("receive_id", this.getReceiveId());
        messageJson.addProperty("msg_type", this.getMsgType());
        messageJson.addProperty("content", this.handleMsgType());
        return messageJson;
    }

    public String toJson() {
        return toJsonObject().toString();
    }

    private String handleMsgType() {
        switch (this.getMsgType()) {
            case TEXT: {
                JsonObject text = new JsonObject();
                text.addProperty("text", this.getText());
                return FsGsonBuilder.create().toJson(text);
            }
            case IMAGE: {
                JsonObject image = new JsonObject();
                image.addProperty("image_key", this.getImageKey());
                return FsGsonBuilder.create().toJson(image);
            }
            case AUDIO:
            case FILE:
            case STICKER: {
                JsonObject audio = new JsonObject();
                audio.addProperty("file_key", this.getFileKey());
                return FsGsonBuilder.create().toJson(audio);
            }
            case MEDIA: {
                JsonObject media = new JsonObject();
                media.addProperty("file_key", this.getFileKey());
                media.addProperty("image_key", this.getImageKey());
                return FsGsonBuilder.create().toJson(media);
            }
            case CARD_SIMPLE:
            case CARD: {
                return this.getCardSimple();
            }
            default: {
                // do nothing
            }
        }
        return "";
    }

}
