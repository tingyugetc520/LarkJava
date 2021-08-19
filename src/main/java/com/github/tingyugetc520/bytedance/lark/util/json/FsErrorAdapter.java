package com.github.tingyugetc520.bytedance.lark.util.json;

import com.github.tingyugetc520.bytedance.lark.error.FsError;
import com.google.gson.*;

import java.lang.reflect.Type;

public class FsErrorAdapter implements JsonDeserializer<FsError> {

  @Override
  public FsError deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    throws JsonParseException {
    FsError.FsErrorBuilder errorBuilder = FsError.builder();
    JsonObject errorJsonObj = json.getAsJsonObject();

    if (errorJsonObj.get("code") != null && !errorJsonObj.get("code").isJsonNull()) {
      errorBuilder.errorCode(GsonHelper.getAsPrimitiveInt(errorJsonObj.get("code")));
    }
    if (errorJsonObj.get("msg") != null && !errorJsonObj.get("msg").isJsonNull()) {
      errorBuilder.errorMsg(GsonHelper.getAsString(errorJsonObj.get("msg")));
    }

    errorBuilder.json(json.toString());

    return errorBuilder.build();
  }

}
