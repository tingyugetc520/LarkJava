package com.github.tingyugetc520.bytedance.lark.api;

import com.github.tingyugetc520.bytedance.lark.bean.contact.v3.FsUserListQuery;
import com.github.tingyugetc520.bytedance.lark.bean.contact.v3.FsUserListResult;
import com.github.tingyugetc520.bytedance.lark.error.FsErrorException;

/**
 * 用户管理接口
 */
public interface FsUserService {

  /**
   * 获取部门成员详情
   * @param query query
   * @return the list
   * @throws FsErrorException the wx error exception
   */
  FsUserListResult listByDepart(FsUserListQuery query) throws FsErrorException;

}
