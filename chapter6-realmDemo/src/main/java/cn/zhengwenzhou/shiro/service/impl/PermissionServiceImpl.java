package cn.zhengwenzhou.shiro.service.impl;

import cn.zhengwenzhou.shiro.dao.PermissionDao;
import cn.zhengwenzhou.shiro.dao.impl.PermissionDaoImpl;
import cn.zhengwenzhou.shiro.entity.Permission;
import cn.zhengwenzhou.shiro.service.PermissionService;

/**
 * @author wen
 * @version 1.0
 * @date 2017/06/22 23:02
 * @description 权限服务实现类
 */

public class PermissionServiceImpl implements PermissionService
{

    private PermissionDao permissionDao = new PermissionDaoImpl();

    public Permission createPermission(Permission permission)
    {
        return permissionDao.createPermission(permission);
    }

    public void deletePermission(Long permissionId)
    {
        permissionDao.deletePermission(permissionId);
    }
}
