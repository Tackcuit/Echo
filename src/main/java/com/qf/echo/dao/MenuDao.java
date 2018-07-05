package com.qf.echo.dao;

import java.util.List;

/**
 * Created by Administrator on 2018/6/18.
 */
public interface MenuDao {
	List<Object> firstMenu();
	List<Object> secondMenu(Integer type);
}
