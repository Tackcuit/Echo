package com.qf.echo.controller;

import com.qf.echo.dto.R;
import com.qf.echo.pojo.User;
import com.qf.echo.service.UserService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/17.
 */
@Controller
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping("/list")
	@ResponseBody
	public R list(){
		List<User> findall = userService.findall();
		return R.ojbk(1,"成功").put("User",findall);
	}

	@ResponseBody
	@RequestMapping("/register")
	public R register(@RequestBody User user){
		//这个时候接收到json数据然后转换成user对象，只需要把缺少的几项补充完整就能正常的运作了
		//有问题。这个时候为什么电话号码设置不上？
		//密码最好要在数据库中加密
		user.setSumConsumption(0.0);
		user.setCurrentConsumption(0.0);
		user.setGender("男");
		userService.add(user);
		return R.ojbk();
	}


	@ResponseBody
	@RequestMapping("/login")
	public R login(@RequestBody User user, HttpServletResponse response, HttpServletRequest request){
		//总是感觉很冗余，到时还还需要优化
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for (Cookie cookie : cookies) {
				if(cookie != null){
					if (cookie.equals("userinfo")){
						String[] infos = cookie.getValue().split("#");
						Boolean res = userService.login(infos[0], infos[1]);
						if(res){
							return R.ojbk("登陆成功");
						}
					}
				}
			}
		}

		boolean login = userService.login(user);
		if(login){
			String userinfo = user.getEmail()+"#"+user.getPassword();
			Cookie cookie = new Cookie("userinfo",userinfo);
			cookie.setMaxAge(60*60*2);
			//设置cookie两个小时失效
			cookie = new Cookie("userinfo",userinfo);
			response.addCookie(cookie);
			return R.ojbk("登录成功");
		}
		else{
			return R.error("用户不存在");
		}
	}
}
