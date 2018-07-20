package com.qf.echo.dao.impl;

import com.qf.echo.dao.OrderDao;
import com.qf.echo.pojo.BuyItem;
import com.qf.echo.pojo.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

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

	@Override
	public List<BuyItem> selectOrderByTableId(Integer tableId) {
		NativeQuery sqlQuery = currentSession().createSQLQuery("SELECT * FROM t_odetail WHERE oid = (SELECT oid FROM t_order WHERE tid = ? AND status = 0)");
		sqlQuery.setParameter(0,tableId);
		NativeQuery nativeQuery = sqlQuery.addEntity(BuyItem.class);
		List<BuyItem> list = nativeQuery.list();
		return list;
	}

	@Override
	public Integer selectPidById(Integer goodid) {
		NativeQuery sqlQuery = currentSession().createSQLQuery("SELECT c_parent_id FROM t_category WHERE c_id = (SELECT c_id FROM t_product WHERE p_id = ?)");
		sqlQuery.setParameter(0, goodid);
		List<Integer> list = sqlQuery.list();
		Integer integer = list.get(0);
		return integer;
	}

//	@Override
//	public void setorderCheckout() {
//		currentSession().
//	}
	//TODO 设置订单状态

	private Session currentSession(){
		return sessionFactory.getCurrentSession();
	}
}
