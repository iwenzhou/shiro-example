package cn.zhengwenzhou.shiro.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.realm.AuthenticatingRealm;

/**
 * @author 郑文洲
 * @version 1.0
 * @date 2017/09/17 22:35
 * @description 自定义Shiro Realm
 */

public class ShiroRealm extends AuthenticatingRealm
{
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException
	{
		System.out.println("doGetAuthenticationInfo ===> " + authenticationToken.hashCode());
		
		return null;
	}
	
	
}
