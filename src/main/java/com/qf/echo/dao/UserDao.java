package com.qf.echo.dao;

import com.qf.echo.pojo.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/17.
 */
public interface UserDao {
	void add(User user);
	List<User> findall();
	Boolean login(String email,String password);
}
