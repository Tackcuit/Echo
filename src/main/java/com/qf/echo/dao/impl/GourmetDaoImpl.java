package com.qf.echo.dao.impl;

import com.qf.echo.dao.GourmetDao;
import com.qf.echo.pojo.Drink;
import com.qf.echo.pojo.Gourmet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2018/6/20.
 */
@Repository
public class GourmetDaoImpl implements GourmetDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Session currentSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<Gourmet> gourmetList(Integer type,Integer page) {
		NativeQuery sqlQuery = currentSession().createSQLQuery("SELECT * FROM t_product WHERE c_id = (SELECT c_id FROM t_category WHERE c_parent_id = 2 LIMIT ? , 1 ) LIMIT ? , 8");
		sqlQuery.setParameter(0,type);
		sqlQuery.setParameter(1,(page - 1) * 8);
		NativeQuery nativeQuery = sqlQuery.addEntity(Gourmet.class);
		List<Gourmet> list = nativeQuery.list();
		return list;
	}

	@Override
	public List<Gourmet> newestGourmet() {
		NativeQuery sqlQuery = currentSession().createSQLQuery("SELECT * FROM t_product WHERE c_id in (SELECT c_id FROM t_category WHERE c_parent_id = 2) AND p_size = 1 AND p_isnew = 'y' LIMIT 0 , 8");
		NativeQuery nativeQuery = sqlQuery.addEntity(Gourmet.class);
		List<Gourmet> list = nativeQuery.list();
		return list;
	}

	@Override
	public List<Gourmet> hottest() {
		NativeQuery sqlQuery = currentSession().createSQLQuery("SELECT * FROM t_product WHERE c_id IN (SELECT c_id FROM t_category WHERE c_parent_id = 2) ORDER BY p_salenum DESC LIMIT 0,3");
		NativeQuery nativeQuery = sqlQuery.addEntity(Gourmet.class);
		List<Gourmet> list = nativeQuery.list();
		return list;
	}

	@Override
	public Gourmet selectById(Integer type, Integer id) {

		NativeQuery sqlQuery = currentSession().createSQLQuery("SELECT * FROM t_product WHERE c_id = (SELECT c_id FROM t_category WHERE c_parent_id = 2 LIMIT ? , 1) LIMIT ? , 1");
		sqlQuery.setParameter(0,type);
		sqlQuery.setParameter(1,id);
		NativeQuery nativeQuery = sqlQuery.addEntity(Gourmet.class);
		List<Gourmet> list = nativeQuery.list();
		Gourmet gourmet = list.get(0);

		return gourmet;
	}

	@Override
	public Gourmet selectByRealId(Integer goodId) {
		Gourmet gourmet = currentSession().get(Gourmet.class, goodId);
		return gourmet;
	}

	@Override
	public void addSellingNum0Id(Integer type, Integer id, Integer num) {
		Gourmet gourmet = selectById(type, id);
		gourmet.setSellingNum(gourmet.getSellingNum() + num);
		currentSession().update(gourmet);
	}

	@Override
	public void addSellingNum(Integer id, Integer num) {
		Gourmet gourmet = selectByRealId(id);
		gourmet.setSellingNum(gourmet.getSellingNum() + num);
		currentSession().update(gourmet);
	}
}
