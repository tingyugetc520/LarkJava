package com.github.tingyugetc520.bytedance.lark.bean.message.v1;

import com.github.tingyugetc520.bytedance.lark.util.json.FsGsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FsMessageSendResult implements Serializable {
  private static final long serialVersionUID = 916455987193190004L;

  private String messageId;
  private String rootId;
  private String parentId;
  private String msgType;

  private String createTime;
  private String updateTime;
  private Boolean deleted;
  private Boolean updated;
  private String chatId;
  private String upperMessageId;

  public static FsMessageSendResult fromJson(String json) {
    return FsGsonBuilder.create().fromJson(json, FsMessageSendResult.class);
  }

}
