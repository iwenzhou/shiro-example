package cn.zhengwenzhou.shiro.chapter2.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * @author wen
 * @version 1.0
 * @date 2017/06/21 00:03
 * @description 自定义Realm类,需重写如下三个方法,自定义完了之后,可配置到配置文件中调用
 */

public class MyRealm implements Realm
{
    public String getName()
    {
        return "MyRealm Name";
    }

    public boolean supports(AuthenticationToken authenticationToken)
    {
        //仅支持UsernamePasswordToken类型的Token
        return authenticationToken instanceof UsernamePasswordToken;
    }

    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException
    {
        //获取用户名
        String username = (String) authenticationToken.getPrincipal();
        //获取密码
        String password = (String) authenticationToken.getCredentials();

        if(!"admin".equals(username))
        {
            //若用户名不是admin,抛出未知用户错误
            throw new UnknownAccountException();
        }

        if(!"123456".equals(password))
        {
            //若密码不是123456,抛出密码错误
            throw new IncorrectCredentialsException();
        }

        //如果身份认证验证成功，返回一个AuthenticationInfo实现
        return new SimpleAuthenticationInfo(username,password,getName());

    }
}
