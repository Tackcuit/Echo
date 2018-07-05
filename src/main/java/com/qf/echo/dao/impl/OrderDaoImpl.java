package com.qf.echo.dao.impl;

import com.qf.echo.dao.OrderDao;
import com.qf.echo.pojo.BuyItem;
import com.qf.echo.pojo.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/6/26.
 */
@Repository
public class OrderDaoImpl implements OrderDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public Serializable save(Order order) {
		Serializable save = currentSession().save(order);
		return save;
	}

	@Override
	public Serializable save1(BuyItem buyItem) {
		Serializable save = currentSession().save(buyItem);

		return save;
	}

	private Session currentSession(){
		return sessionFactory.getCurrentSession();
	}
}
