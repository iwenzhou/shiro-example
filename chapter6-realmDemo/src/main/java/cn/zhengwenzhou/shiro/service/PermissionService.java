package cn.zhengwenzhou.shiro.service;

import cn.zhengwenzhou.shiro.entity.Permission;

/**
 * @author wen
 * @version 1.0
 * @date 2017/06/22 23:02
 * @description 权限服务
 */

public interface PermissionService
{
    /**
     * 添加权限
     *
     * @param permission
     * @return
     */
    public Permission createPermission(Permission permission);

    /**
     * 通过权限ID删除权限
     *
     * @param permissionId 权限ID
     */
    public void deletePermission(Long permissionId);
}
