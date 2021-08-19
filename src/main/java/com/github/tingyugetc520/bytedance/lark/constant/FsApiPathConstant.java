package com.github.tingyugetc520.bytedance.lark.constant;

import lombok.experimental.UtilityClass;

/**
 * api地址常量类
 */
public final class FsApiPathConstant {
    public static final String DEFAULT_DT_BASE_URL = "https://open.feishu.cn";

    public static final String GET_TOKEN = "/open-apis/auth/v3/tenant_access_token/internal";

    /**
     * 消息通知相关接口
     */
    @UtilityClass
    public static class Message {
        public static final String MESSAGE_SEND = "/open-apis/im/v1/messages?receive_id_type=%s";
    }

    /**
     * 免登
     */
    @UtilityClass
    public static class OAuth2 {
        public static final String GET_USER_INFO = "/open-apis/authen/v1/access_token";
        public static final String URL_OAUTH2_AUTHORIZE = "https://open.feishu.cn/open-apis/authen/v1/index";
    }

    /**
     * 部门管理
     */
    @UtilityClass
    public static class Department {
        public static final String DEPARTMENT_LIST = "/open-apis/contact/v3/departments";
    }

    /**
     * 用户管理
     */
    @UtilityClass
    public static class User {
        public static final String USER_LIST = "/open-apis/contact/v3/users";
    }

    @UtilityClass
    public static class Agent {
        public static final String AGENT_AUTH_SCOPE = "/open-apis/application/v2/app/visibility";
    }

}
