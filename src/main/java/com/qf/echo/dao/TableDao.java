package com.qf.echo.dao;

import com.qf.echo.pojo.T_table;

import java.net.Inet4Address;
import java.util.List;

/**
 * Created by Administrator on 2018/6/29.
 */
public interface TableDao {
	List<T_table> selectAll();
	void changeFlag(T_table table);
	void changeConsumption(T_table table);
	void changeOrderId(T_table table);
	void resetTable(T_table table);
	T_table selectTable(Integer tableId);
	void updateChangeAll(T_table table);
}
