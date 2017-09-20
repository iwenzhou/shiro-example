package cn.zhengwenzhou.shiro.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 郑文洲
 * @version 1.0
 * @date 2017/09/17 22:35
 * @description 自定义Shiro Realm
 * 1. 授权需要继承 AuthorizingRealm 类, 并实现其 doGetAuthorizationInfo 方法
 * 2. AuthorizingRealm 类继承自 AuthenticatingRealm, 但没有实现 AuthenticatingRealm 中的
 *    doGetAuthenticationInfo, 所以认证和授权只需要继承 AuthorizingRealm 就可以了. 同时实现他的两个抽象方法.
 */

public class ShiroRealm extends AuthorizingRealm
{
	//用于认证的方法
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException
	{
		System.out.println("FirstRealm doGetAuthenticationInfo");
		
		//1.把AuthenticationToken转换为UsernamePasswordToken
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
		
		//2.从UsernamePasswordToken中获取username
		String username = usernamePasswordToken.getUsername();
		
		//3.调用数据库的方法，从数据库中查询username对应的数据
		System.out.println("从数据库查询用户信息 [ " + username + " ]");
		
		//4.若用户不存在，抛出UnknownAccountException错误
		//模拟用户不存在
		if("unknown".equals(username))
		{
			throw new UnknownAccountException("用户不存在");
		}
		
		//5.根据用户情况抛出AuthenticationException其他异常
		//模拟用户被锁定
		if("monster".equals(username))
		{
			throw new LockedAccountException("用户被锁定");
		}
		
		//6.根据用户情况，构建AuthenticationInfo对象并返回
		//以下信息模拟从数据中获取的用户信息
		//1). principal：认证的实体信息，可以是username，也可以是数据表对应的用户的实体对象
		Object principal = username;
		//2). credentials：密码
		String credentials = "4280d89a5a03f812751f504cc10ee8a5";
		//3). realmName：当前realm对象的name，调用父类的getName()即可
		String realmName = getName();
		
		if("admin".equals(username))
		{
			credentials = "928bfd2577490322a6e19b793691467e";
		}
		if("user".equals(username))
		{
			credentials = "b8c2d5b0a37cc51f91d5e8970347a3a3";
		}
		
		//普通的AuthenticationInfo对象，会根据配置的密码对比策略进行比较
		//SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal, credentials, realmName);
		
		//加盐的AuthenticationInfo对象，会根据盐值进行比较
		//4.) credentialsSalt：盐值,尽量每个用户的盐值都是唯一的，这样加密后的密文也是唯一的
		ByteSource credentialsSalt = ByteSource.Util.bytes(username);
		
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
		
		return authenticationInfo;
	}
	
	//用于授权的方法
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection)
	{
		System.out.println("doGetAuthorizationInfo ....");
		
		//1.从 PrincipalCollection 中获取登录用户的信息(当设置多Realms时，获取也是有先后顺序的)
		Object principal = principalCollection.getPrimaryPrincipal();
		
		//2.利用登录的用户信息来查询当前用户的角色或权限（可能需要查询数据库）
		Set<String> roles = new HashSet<String>();
		roles.add("user");
		if("admin".equals(principal))
		{
			roles.add("admin");
		}
		
		//3.创建 SimpleAuthorizationInfo 对象，并设置其rele属性
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		
		//4.返回 SimpleAuthorizationInfo 对象
		
		return info;
	}
	
	public static void main(String[] args)
	{
		String hashAlgorithmName = "MD5";
		Object credentials = "123456";
		Object salt = ByteSource.Util.bytes("user");
		int hashIterations = 2;
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		
		System.out.println(result);
		
	}
}
