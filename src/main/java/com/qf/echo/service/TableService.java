package com.qf.echo.service;

import com.qf.echo.pojo.T_table;

import java.util.List;

/**
 * Created by Administrator on 2018/7/3.
 */
public interface TableService {
	List<T_table> selectAll();
	void changeFlag(Integer tableId);
	void changeConsumption(Integer tableId,Double Consumpation);
	void changeOrderId(Integer tableId, String orderId);
	T_table selectTable(Integer tableId);
	void resetTable(Integer tableId);
	void updateChangeAll(T_table table);
}
