package com.github.tingyugetc520.bytedance.lark.bean;

import com.github.tingyugetc520.bytedance.lark.bean.contact.v3.FsUser;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@Test
@Slf4j
public class FsUserTest {

    public void testFromJson() {
        String json = "";
        FsUser user = FsUser.fromJson(json);
        log.info("user: {}", user);
    }

    public void testToJson() {
        FsUser user = FsUser.builder().build();
        log.info("json: {}", user.toJson());
    }

}
