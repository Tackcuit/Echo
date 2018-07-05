package com.qf.echo.dao.impl;

import com.qf.echo.dao.MenuDao;
import com.qf.echo.pojo.Category;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2018/6/18.
 */
@Repository
public class MenuDaoImpl implements MenuDao {
	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public List<Object> firstMenu() {
		NativeQuery sqlQuery = currentSession().createSQLQuery("select c_name from t_category where c_level = 1");
		//创建实体类，来接收查出来的东西
//		sqlQuery.addEntity(Category.class);
		List list = sqlQuery.list();
//		Object[] objects = list.toArray();
		for (Object o : list) {
			System.out.println(o);
		}
		return list;
	}

	@Override
	public List<Object> secondMenu(Integer type) {
		NativeQuery sqlQuery = currentSession().createSQLQuery("select c_name from t_category where c_parent_id = ? and c_level = 2");
		sqlQuery.setParameter(0,type);
		List list = sqlQuery.list();
		for (Object o : list) {
			System.out.println(o);
		}
		return list;
	}


	private Session currentSession(){
		return sessionFactory.getCurrentSession();
	}
}
