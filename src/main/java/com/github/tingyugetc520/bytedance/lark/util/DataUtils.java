package com.github.tingyugetc520.bytedance.lark.util;

import com.github.tingyugetc520.bytedance.lark.error.FsRuntimeErrorEnum;
import com.github.tingyugetc520.bytedance.lark.error.FsRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

@Slf4j
public class DataUtils {
  /**
   * 将数据中包含的secret字符使用星号替换，防止日志打印时被输出
   */
  public static <E> E handleDataWithSecret(E data) {
    E dataForLog = data;
    if(data instanceof String && StringUtils.contains((String)data, "&secret=")){
      dataForLog = (E) StringUtils.replaceAll((String)data,"&secret=\\w+&","&secret=******&");
    }
    return dataForLog;
  }

  public static String parseJsonToUrlParams(String json) {
    if (StringUtils.isBlank(json)) {
      return "";
    }

    try {
      JSONObject jsonObject = new JSONObject(json);
      Iterator<String> it = jsonObject.keys();

      StringBuilder paramStr = new StringBuilder();
      while (it.hasNext()) {
        String key = it.next();
        String value = jsonObject.get(key).toString();
        paramStr.append("&").append(key).append("=").append(value);
      }
      if (StringUtils.startsWith(paramStr, "&")) {
        paramStr.replace(0, 1, "");
      }
      return paramStr.toString();
    } catch (JSONException e) {
      log.error("构建请求参数异常", e);
      throw new FsRuntimeException(FsRuntimeErrorEnum.FS_JSON_HTTP_PARAM_FAILED);
    }
  }

  public static String parseUrlParamsToJson(String param) {
    param = param.replaceAll("=", "\":\"");
    param = param.replaceAll("&", "\",\"");
    return"{\"" + param + "\"}";
  }
}
