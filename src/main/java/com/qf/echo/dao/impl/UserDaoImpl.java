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
		NativeQuery nativeQuery = sqlQuery.addEntity(User.class);
		List<User> list = nativeQuery.list();
//		for (Object o : list) {
//			if(o != null){
//				return true;
//			}else{
//				return false;
//			}
//		}
		if (list.size() > 0){
			return true;
		}
		return false;
	}

	@Override
	public Boolean checkEmail(String email) {
		NativeQuery sqlQuery = currentSession().createSQLQuery("SELECT * FROM t_user WHERE email = ?");
		sqlQuery.addEntity(User.class);
		NativeQuery nativeQuery = sqlQuery.setParameter(0, email);
		List<User> list = nativeQuery.list();
		for (User user : list) {
			if (user != null){
				return true;
			}else {
				return false;
			}
		}
		return false;
	}

	@Override
	public Boolean checkUserName(String username) {
		NativeQuery sqlQuery = currentSession().createSQLQuery("SELECT * FROM t_user WHERE username = ?");
		sqlQuery.addEntity(User.class);
		NativeQuery nativeQuery = sqlQuery.setParameter(0, username);
		List<User> list = nativeQuery.list();
		for (User user : list) {
			if (user != null){
				return true;
			}else {
				return false;
			}
		}
		return false;
	}

	@Override
	public Boolean checkPhone(String phone) {
		NativeQuery sqlQuery = currentSession().createSQLQuery("SELECT * FROM t_user WHERE phone = ?");
		sqlQuery.addEntity(User.class);
		NativeQuery nativeQuery = sqlQuery.setParameter(0, phone);
		List<User> list = nativeQuery.list();
		for (User user : list) {
			if (user != null){
				return true;
			}else {
				return false;
			}
		}
		return false;
	}

}
