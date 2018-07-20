package com.qf.echo.dao;

import com.qf.echo.pojo.User;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/17.
 */
public interface UserDao {
	void add(User user);
	List<User> findall();
	//这个是判断账号密码对不对的
	Boolean login(String email,String password);
	//检查用户名，邮箱，和手机号
	Boolean checkEmail(String email);
	Boolean checkUserName(String username);
	Boolean checkPhone(String phone);
}
