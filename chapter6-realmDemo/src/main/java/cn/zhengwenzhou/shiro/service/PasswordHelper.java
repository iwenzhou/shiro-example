package cn.zhengwenzhou.shiro.service;

import cn.zhengwenzhou.shiro.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author wen
 * @version 1.0
 * @date 2017/06/22 23:02
 * @description 密码工具类
 */

public class PasswordHelper
{

    /**
     * 生成随机数
     */
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    private String algorithmName = "md5";//加密方式
    private final int hashIterations = 2;//加密次数

    /**
     * 加密用户密码
     *
     * @param user 用户对象
     */
    public void encryptPassword(User user)
    {
        //设置盐
        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(algorithmName, user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()), hashIterations).toHex();

        user.setPassword(newPassword);
    }
}
