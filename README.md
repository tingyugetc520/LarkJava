# LarkJava

## 介绍
LarkJava(Lark Java SDK 飞书SDK) 封装了飞书凭证、通讯录管理、消息通知等服务端接口，让开发者可以使用简单的配置，提供简洁的 API 以供方便快速地调用飞书接口。

注意：目前SDK主要是以企业内建应用为主，ISV应用后面会陆续支持。

## 安装
* 通过Maven方式安装使用
尚未托管再Maven上

* 直接下载源码使用
```git
git clone https://github.com/tingyugetc520/LarkJava.git
```
源码下载完成后，放置在您的项目中使用，同时请注意相关依赖的问题。

## 使用
```java
// 应用配置
FsDefaultConfigImpl config = new FsDefaultConfigImpl();
config.setAppId("corpId");
config.setAppSecret("appSecret");

// FsService为SDK使用入口，后续接口使用均需要FsService
FsServiceImpl fsService = new FsServiceImpl();
fsService.setFsConfigStorage(config);

// 查询用户
fsService.getUserService().list();
```

## 封装进度(企业内建应用)
- [x] HTTP代理，支持正向代理与反向代理
- [x] 获取凭证
- [x] 身份验证
- [x] 通讯录管理（只读）
- [x] 机器人消息
- [] HTTP事件回调
