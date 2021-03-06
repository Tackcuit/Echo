package com.qf.echo.service.impl;

import com.qf.echo.dao.OrderDao;
import com.qf.echo.dao.UserDao;
import com.qf.echo.pojo.BuyItem;
import com.qf.echo.pojo.Order;
import com.qf.echo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/26.
 */
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;
	@Override
	public Serializable save(Order order) {
		Serializable save = orderDao.save(order);
		return save;
	}

	@Override
	public Serializable save1(BuyItem buyItem) {
		orderDao.save1(buyItem);
		return null;
	}

	@Override
	public List<BuyItem> selectOrderByTableId(Integer tableId) {
		List<BuyItem> list = orderDao.selectOrderByTableId(tableId);
		return list;
	}

	@Override
	public Integer selectPidById(Integer goodid) {
		Integer integer = orderDao.selectPidById(goodid);
		return integer;
	}
}
