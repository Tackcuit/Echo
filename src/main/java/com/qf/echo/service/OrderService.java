package com.qf.echo.service;

import com.qf.echo.pojo.BuyItem;
import com.qf.echo.pojo.Order;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/26.
 */
public interface OrderService {
	Serializable save(Order order);
	Serializable save1(BuyItem buyItem);

	List<BuyItem> selectOrderByTableId(Integer tableId);

	Integer selectPidById(Integer goodid);
}
