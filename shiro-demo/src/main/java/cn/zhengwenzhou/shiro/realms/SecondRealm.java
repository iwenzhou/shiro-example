package cn.zhengwenzhou.shiro.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

/**
 * @author 郑文洲
 * @version 1.0
 * @date 2017/09/17 22:35
 * @description 自定义Shiro Realm
 */

public class SecondRealm extends AuthenticatingRealm
{
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException
	{
		System.out.println("SecondRealm doGetAuthenticationInfo");
		
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
			credentials = "14c33c244faa5ad403e38be03037060e97bc7aea";
		}
		if("user".equals(username))
		{
			credentials = "9b51165daa869d7d7162480119108f3119e00249";
		}
		
		//普通的AuthenticationInfo对象，会根据配置的密码对比策略进行比较
		//SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal, credentials, realmName);
		
		//加盐的AuthenticationInfo对象，会根据盐值进行比较
		//4.) credentialsSalt：盐值,尽量每个用户的盐值都是唯一的，这样加密后的密文也是唯一的
		ByteSource credentialsSalt = ByteSource.Util.bytes(username);
		principal = "SecondRealm";
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
		
		return authenticationInfo;
	}
	
	
	public static void main(String[] args)
	{
		String hashAlgorithmName = "SHA1";
		Object credentials = "123456";
		Object salt = ByteSource.Util.bytes("admin");
		int hashIterations = 2;
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		
		System.out.println(result);
		
	}
	
}
