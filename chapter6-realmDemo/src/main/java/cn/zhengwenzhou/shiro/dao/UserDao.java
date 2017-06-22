package cn.zhengwenzhou.shiro.dao;


import cn.zhengwenzhou.shiro.entity.User;

import java.util.Set;


/**
 * @author wen
 * @version 1.0
 * @date 2017/06/22 23:02
 * @description 用户操作
 */

public interface UserDao
{

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    public User createUser(User user);

    /**
     * 更新用户
     *
     * @param user
     */
    public void updateUser(User user);

    /**
     * 通过用户ID删除用户
     *
     * @param userId
     */
    public void deleteUser(Long userId);

    /**
     * 添加用户-角色之间关系
     *
     * @param userId
     * @param roleIds
     */
    public void correlationRoles(Long userId, Long... roleIds);

    /**
     * 移除用户-角色之间关系
     *
     * @param userId
     * @param roleIds
     */
    public void uncorrelationRoles(Long userId, Long... roleIds);

    /**
     * 通过用户ID查找用户
     *
     * @param userId
     * @return
     */
    public User findOne(Long userId);

    /**
     * 通过用户名查找用户
     *
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 通过用户名获取用户所有角色
     *
     * @param username
     * @return
     */
    public Set<String> findRoles(String username);

    /**
     * 通过用户名获取用户所有权限
     *
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username);
}
