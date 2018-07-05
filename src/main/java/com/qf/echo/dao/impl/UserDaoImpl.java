package com.qf.echo.dao.impl;

import com.qf.echo.dao.UserDao;
import com.qf.echo.pojo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/17.
 */

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Session currentSession(){
		return sessionFactory.getCurrentSession();
	}
	@Override
	public void add(User user) {
		Serializable save = currentSession().save(user);
	}

	@Override
	public List<User> findall() {
		Query<User> query = currentSession().createQuery("from User", User.class);
		List<User> list = query.list();
		return list;
	}

	@Override
	public Boolean login(String email, String password) {
		NativeQuery sqlQuery = currentSession().createSQLQuery("select * from t_user where email = ? and password = ?");
		sqlQuery.setParameter(0,email);
		sqlQuery.setParameter(1,password);
		sqlQuery.addEntity(User.class);
		List list = sqlQuery.list();
		for (Object o : list) {
			if(o != null){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
}
