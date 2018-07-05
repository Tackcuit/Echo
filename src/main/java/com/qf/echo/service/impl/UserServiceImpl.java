package com.qf.echo.service.impl;

import com.qf.echo.dao.UserDao;
import com.qf.echo.pojo.User;
import com.qf.echo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/17.
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Override
	public void add(User user) {
		userDao.add(user);

	}

	@Override
	public List<User> findall() {
		List<User> findall = userDao.findall();
		return findall;
	}

	@Override
	public Boolean login(User user) {
		Boolean res = userDao.login(user.getEmail(), user.getPassword());
		return res;
	}

	@Override
	public Boolean login(String email, String password) {
		Boolean res = userDao.login(email, password);
		return res;
	}
}
