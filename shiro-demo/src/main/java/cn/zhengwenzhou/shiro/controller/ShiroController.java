package cn.zhengwenzhou.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 郑文洲
 * @version 1.0
 * @date 2017/09/19 14:09
 * @description
 */

@Controller
@RequestMapping("/shiro")
public class ShiroController
{
	
	@RequestMapping("/login.do")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password)
	{
		// 获得一个主体对象
		Subject currentUser = SecurityUtils.getSubject();
		
		if (!currentUser.isAuthenticated()) {
			// 登录用户
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			token.setRememberMe(true);
			
			System.out.println("执行登录：" + token.hashCode());
			
			try {
				currentUser.login(token);
			} catch (AuthenticationException e) {
				e.printStackTrace();
				System.out.println("登录失败："+e.getMessage());
			}
		}
		
		return "redirect:/list.jsp";
	}
	
}
