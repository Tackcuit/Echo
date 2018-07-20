package com.qf.echo.dao.impl;

import com.qf.echo.dao.PeripheralDao;
import com.qf.echo.pojo.Drink;
import com.qf.echo.pojo.Peripheral;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2018/6/21.
 */
@Repository
public class PeripheralDaoImpl implements PeripheralDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<Peripheral> peripheralList(Integer type, Integer page) {
		NativeQuery sqlQuery = currentSession().createSQLQuery("SELECT * FROM t_product WHERE c_id = (SELECT c_id FROM t_category WHERE c_parent_id = 3 LIMIT ? , 1) LIMIT ? , 8;");
		sqlQuery.setParameter(0,type);
		sqlQuery.setParameter(1,(page - 1) * 8);
		NativeQuery nativeQuery = sqlQuery.addEntity(Peripheral.class);
		List<Peripheral> list = nativeQuery.list();
		return list;
	}

	@Override
	public List<Peripheral> newestPeripheral() {
		NativeQuery sqlQuery = currentSession().createSQLQuery("SELECT * FROM t_product WHERE c_id in (SELECT c_id FROM t_category WHERE c_parent_id = 3) AND p_size = 1 AND p_isnew = 'y' LIMIT 0 , 8");
		NativeQuery nativeQuery = sqlQuery.addEntity(Peripheral.class);
		List<Peripheral> list = nativeQuery.list();
		return list;
	}

	@Override
	public List<Peripheral> hottest() {
		NativeQuery sqlQuery = currentSession().createSQLQuery("SELECT * FROM t_product WHERE c_id IN (SELECT c_id FROM t_category WHERE c_parent_id = 3) ORDER BY p_salenum DESC LIMIT 0,4");
		NativeQuery nativeQuery = sqlQuery.addEntity(Peripheral.class);
		List<Peripheral> list = nativeQuery.list();
		return null;
	}

	@Override
	public Peripheral selectById(Integer type, Integer id) {
		NativeQuery sqlQuery = currentSession().createSQLQuery("SELECT * FROM t_product WHERE c_id = (SELECT c_id FROM t_category WHERE c_parent_id = 3 LIMIT ? , 1) LIMIT ? , 1");
		sqlQuery.setParameter(0,type);
		sqlQuery.setParameter(1,id);
		NativeQuery nativeQuery = sqlQuery.addEntity(Peripheral.class);
		List<Peripheral> list = nativeQuery.list();
		Peripheral peripheral = list.get(0);
		return peripheral;
	}

	@Override
	public void addSellingNum0Id(Integer type, Integer id,Integer num) {
		//TODO 再改一下数据过来告诉我加了几个
		Peripheral peripheral = selectById(type, id);
		peripheral.setSellingNum(peripheral.getSellingNum() + num);
		currentSession().update(peripheral);
	}

	@Override
	public void addSellingNum(Integer id, Integer num) {
		Peripheral peripheral = selectByRealId(id);
		peripheral.setSellingNum(peripheral.getSellingNum() + num);
		currentSession().update(peripheral);
	}

	@Override
	public Peripheral selectByRealId(Integer id) {
		Peripheral peripheral = currentSession().get(Peripheral.class, id);
		return peripheral;
	}


	private Session currentSession(){
		return sessionFactory.getCurrentSession();
	}
}
