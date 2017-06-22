package cn.zhengwenzhou.shiro.service.impl;

import cn.zhengwenzhou.shiro.dao.RoleDao;
import cn.zhengwenzhou.shiro.dao.impl.RoleDaoImpl;
import cn.zhengwenzhou.shiro.entity.Role;
import cn.zhengwenzhou.shiro.service.RoleService;

/**
 * @author wen
 * @version 1.0
 * @date 2017/06/22 23:02
 * @description 角色服务实现类
 */

public class RoleServiceImpl implements RoleService
{

    private RoleDao roleDao = new RoleDaoImpl();


    public Role createRole(Role role)
    {
        return roleDao.createRole(role);
    }

    public void deleteRole(Long roleId)
    {
        roleDao.deleteRole(roleId);
    }

    /**
     * 添加角色-权限之间关系
     *
     * @param roleId
     * @param permissionIds
     */
    public void correlationPermissions(Long roleId, Long... permissionIds)
    {
        roleDao.correlationPermissions(roleId, permissionIds);
    }

    /**
     * 移除角色-权限之间关系
     *
     * @param roleId
     * @param permissionIds
     */
    public void uncorrelationPermissions(Long roleId, Long... permissionIds)
    {
        roleDao.uncorrelationPermissions(roleId, permissionIds);
    }

}
