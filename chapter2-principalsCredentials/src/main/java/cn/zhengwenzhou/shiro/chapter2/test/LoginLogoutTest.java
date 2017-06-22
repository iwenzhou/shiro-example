package cn.zhengwenzhou.shiro.chapter2.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author wen
 * @version 1.0
 * @date 2017/06/20 23:02
 * @description 登录/登出测试类
 */

public class LoginLogoutTest
{
    @Test
    /**
     * 测试Users配置文件身份验证
     */
    public void testLoginLogout()
    {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-users.ini");

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");

        try
        {
            //4、登录，即身份验证
            subject.login(token);
        } catch(AuthenticationException e)
        {
            //5、身份验证失败
            System.out.println("登录失败：" + e.getMessage());
        }

        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录

        System.out.println("登录成功："+token.getUsername());

        //6、退出登录
        subject.logout();
    }

    @Test
    /**
     * 测试单个自定义Realm验证
     */
    public void testCustomRealm()
    {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");

        try
        {
            //4、登录，即身份验证
            subject.login(token);
        } catch(AuthenticationException e)
        {
            //5、身份验证失败
            //e.printStackTrace();
            System.out.println("登录失败：" + e.getMessage());
        }

        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录

        System.out.println("登录成功："+token.getUsername());

        //6、退出登录
        subject.logout();

    }

    @Test
    /**
     * 测试多个自定义Realm的身份验证
     */
    public void testCustomMultiRealm()
    {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-multi-realm.ini");

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("wen", "123456");

        try
        {
            //4、登录，即身份验证
            subject.login(token);
        } catch(AuthenticationException e)
        {
            //5、身份验证失败
            //e.printStackTrace();
            System.out.println("登录失败：" + e.getMessage());
        }

        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录

        System.out.println("登录成功："+token.getUsername());

        //6、退出登录
        subject.logout();

    }

    @Test
    /**
     * 测试JDBC的Realm身份验证
     */
    public void testJDBCRealm() {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5、身份验证失败
            //e.printStackTrace();
            System.out.println("登录失败：" + e.getMessage());
        }

        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
        System.out.println("登录成功：" +token.getUsername() );

        //6、退出
        subject.logout();
    }

}
