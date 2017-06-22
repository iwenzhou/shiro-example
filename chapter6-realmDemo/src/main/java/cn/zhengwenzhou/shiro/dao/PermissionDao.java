package cn.zhengwenzhou.shiro.dao;

import cn.zhengwenzhou.shiro.entity.Permission;


/**
 * @author wen
 * @version 1.0
 * @date 2017/06/22 23:02
 * @description 权限操作
 */

public interface PermissionDao
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
     * @param permissionId
     */
    public void deletePermission(Long permissionId);

}
