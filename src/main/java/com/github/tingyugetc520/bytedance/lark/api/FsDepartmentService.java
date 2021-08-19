package com.github.tingyugetc520.bytedance.lark.api;

import com.github.tingyugetc520.bytedance.lark.bean.contact.v3.FsDepartListQuery;
import com.github.tingyugetc520.bytedance.lark.bean.contact.v3.FsDepartListResult;
import com.github.tingyugetc520.bytedance.lark.error.FsErrorException;

/**
 * 部门管理接口
 */
public interface FsDepartmentService {

    /**
     * 部门管理接口 - 获取部门列表
     *
     * @param query 参数
     * @return 获取的部门列表
     * @throws FsErrorException 异常
     */
    FsDepartListResult list(FsDepartListQuery query) throws FsErrorException;

}
