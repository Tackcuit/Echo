package com.qf.echo.dao.impl;

import com.qf.echo.dao.TableDao;
import com.qf.echo.pojo.T_table;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2018/6/29.
 */
@Repository
@DynamicUpdate
public class TableDaoImpl implements TableDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<T_table> selectAll() {
		Query<T_table> query = currentSession().createQuery("from T_table", T_table.class);
		List<T_table> list = query.list();
		return list;
	}

	@Override
	//这几个方法应该能够合成一个update的方法，时候用hibernate封装的update方法
	public void changeFlag(T_table table) {
		T_table table1 = selectTable(table.getId());
		System.out.println("筛选桌子并试图改变状态");
		table1.setFlag(table.getFlag());
		System.out.println("尝试改变桌子状态" + table.getFlag());
		//需要在服务层判断是入座还是买单  1，入座 0，买单
		currentSession().saveOrUpdate(table1);
	}

	@Override
	public void changeConsumption(T_table table) {
		T_table table1 = selectTable(table.getId());
		table1.setConsumption(table.getConsumption());
		currentSession().saveOrUpdate(table1);
	}

	@Override
	public void changeOrderId(T_table table) {
		T_table table1 = selectTable(table.getId());
		table1.setOrderid(table.getOrderid());
		System.out.println("/////////////////");
		currentSession().update(table1);
	}

	@Override
	public T_table selectTable(Integer tableId) {
			T_table table = currentSession().get(T_table.class, tableId);
			return table;
	}

	@Override
	public void resetTable(T_table table) {
		currentSession().update(table);
	}


	private Session currentSession(){
		return sessionFactory.getCurrentSession();
	}

}
