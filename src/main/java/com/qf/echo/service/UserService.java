package com.qf.echo.service;

import com.qf.echo.pojo.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/17.
 */
public interface UserService {
	void add(User user);
	List<User> findall();
	Boolean login(User user);
	Boolean login(String email,String password);
}
