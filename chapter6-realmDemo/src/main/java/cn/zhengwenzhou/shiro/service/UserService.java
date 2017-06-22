package cn.zhengwenzhou.shiro.service;

import cn.zhengwenzhou.shiro.entity.User;

import java.util.Set;

/**
 * @author wen
 * @version 1.0
 * @date 2017/06/22 23:02
 * @description 用户服务
 */

public interface UserService
{

    /**
     * 添加用户
     *
     * @param user
     */
    public User createUser(User user);

    /**
     * 修改密码
     *
     * @param userId
     * @param newPassword
     */
    public void changePassword(Long userId, String newPassword);

    /**
     * 添加用户-角色关系
     *
     * @param userId
     * @param roleIds
     */
    public void correlationRoles(Long userId, Long... roleIds);


    /**
     * 移除用户-角色关系
     *
     * @param userId
     * @param roleIds
     */
    public void uncorrelationRoles(Long userId, Long... roleIds);

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 根据用户名查找其角色
     *
     * @param username
     * @return
     */
    public Set<String> findRoles(String username);

    /**
     * 根据用户名查找其权限
     *
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username);

}
