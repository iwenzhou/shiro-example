package cn.zhengwenzhou.shiro.dao;


import cn.zhengwenzhou.shiro.entity.Role;

/**
 * @author wen
 * @version 1.0
 * @date 2017/06/22 23:02
 * @description 角色操作
 */

public interface RoleDao
{

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    public Role createRole(Role role);

    /**
     * 通过角色ID删除角色
     *
     * @param roleId
     */
    public void deleteRole(Long roleId);

    /**
     * 添加角色-权限之间关系
     *
     * @param roleId
     * @param permissionIds
     */
    public void correlationPermissions(Long roleId, Long... permissionIds);

    /**
     * 移除角色-权限之间关系
     *
     * @param roleId
     * @param permissionIds
     */
    public void uncorrelationPermissions(Long roleId, Long... permissionIds);

}
