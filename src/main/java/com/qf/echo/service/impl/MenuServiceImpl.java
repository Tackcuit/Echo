package com.qf.echo.service.impl;

import com.qf.echo.dao.MenuDao;
import com.qf.echo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/6/19.
 */
@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuDao menuDao;

	@Override
	public List<Object> firstMenu() {
		return menuDao.firstMenu();
	}

	@Override
	public List<Object> secondMenu(Integer type) {
		List<Object> list = menuDao.secondMenu(type);
		return list;
	}
}
