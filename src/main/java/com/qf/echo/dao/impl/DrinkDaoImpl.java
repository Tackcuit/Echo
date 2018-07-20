package com.qf.echo.dao.impl;

import com.qf.echo.dao.DrinkDao;
import com.qf.echo.pojo.BuyItem;
import com.qf.echo.pojo.Drink;
import com.qf.echo.pojo.GoodDetail;
import com.qf.echo.pojo.Gourmet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2018/6/19.
 */
@Repository
public class DrinkDaoImpl implements DrinkDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<Drink> drinklist(Integer type,Integer page) {
		NativeQuery sqlQuery = currentSession().createSQLQuery("SELECT * FROM t_product WHERE c_id = (SELECT c_id FROM t_category WHERE c_parent_id = 1 LIMIT ? , 1 ) AND p_size = 1  LIMIT ? , 8");
		sqlQuery.setParameter(0,type);
		sqlQuery.setParameter(1,(page - 1) * 8);
		NativeQuery nativeQuery = sqlQuery.addEntity(Drink.class);
		List<Drink> list = nativeQuery.list();
		//这个地方怎么返回map集合或者是properties集合？
		System.out.println(list);
		return list;
	}

	@Override
	public List<Drink> newestDrink() {
		NativeQuery sqlQuery = currentSession().createSQLQuery("SELECT * FROM t_product WHERE c_id in (SELECT c_id FROM t_category WHERE c_parent_id = 1) AND p_size = 1 AND p_isnew = 'y' LIMIT 0 , 8");
		NativeQuery nativeQuery = sqlQuery.addEntity(Drink.class);
		List<Drink> list = nativeQuery.list();
		return list;
	}

	@Override
	public List<Drink> hottest() {
		NativeQuery sqlQuery = currentSession().createSQLQuery("SELECT * FROM t_product WHERE c_id IN (SELECT c_id FROM t_category WHERE c_parent_id = 1) ORDER BY p_salenum DESC LIMIT 0 , 4");
		NativeQuery nativeQuery = sqlQuery.addEntity(Drink.class);
		List<Drink> list = nativeQuery.list();
		return list;
	}

	@Override
	public Drink selectById(Integer type, Integer id,Integer size) {
		NativeQuery sqlQuery = currentSession().createSQLQuery("select * from t_product where c_id = (select c_id from t_category where c_parent_id = 1 limit ? , 1) and p_size = ? limit ? , 1");/*第一个是type，第二个就是商品种类*/
		sqlQuery.setParameter(0,type);
		sqlQuery.setParameter(1,size);
		sqlQuery.setParameter(2,id);
		NativeQuery nativeQuery = sqlQuery.addEntity(Drink.class);
		List<Drink> list = nativeQuery.list();
		Drink drink = list.get(0);//注意从0开始！
		return drink;
	}

	@Override
	public void addSellingNum(Integer goodId, Integer num) {
		//假定传过来的ID是数据库中存在的ID
		Drink drink = selectByRealKey(goodId);
		drink.setSellingNum(drink.getSellingNum() + num);
		currentSession().update(drink);
	}




	@Override
	public Drink selectByRealKey(Integer goodId) {
		Drink drink = currentSession().get(Drink.class, goodId);
		return drink;
	}

	@Override
	public void addSellingNum0Id(Integer type, Integer id, Integer size, Integer num) {
		Drink drink = selectById(type, id, size);
		drink.setSellingNum(drink.getSellingNum() + num);
		currentSession().update(drink);
	}

	@Override
	public List<GoodDetail> details(Integer goodid) {
//		NativeQuery sqlQuery = currentSession().createSQLQuery("SELECT p_img,p_name,p_price,p_membershipprice FROM t_product WHERE p_id = ?");
//		sqlQuery.setParameter(0,goodid);
//		NativeQuery nativeQuery = sqlQuery.addEntity(GoodDetail.class);
//		List<GoodDetail> list = nativeQuery.list();
		Query<GoodDetail> query = currentSession().createQuery("from GoodDetail where id = ?", GoodDetail.class);
		query.setParameter(0, goodid);
		List<GoodDetail> list = query.list();
		return list;
	}

//	@Override
//	public Integer selectMainC(Integer goodid) {
//		currentSession().createSQLQuery("")
//		return null;
//	}

//	@Override
//	public List<BuyItem> listBuyItems(Integer tid) {
//		NativeQuery sqlQuery = currentSession().createSQLQuery("SELECT * FROM t_odetail WHERE oid = (SELECT oid FROM t_order WHERE tid = ? AND status = 0)");
//		sqlQuery.setParameter(0, tid);
//		NativeQuery nativeQuery = sqlQuery.addEntity(BuyItem.class);
//		List<BuyItem> list = nativeQuery.list();
//		return list;
//	}

//	@Override
//	public List<Drink> selectById(Integer goodid) {
//
//		return null;
//	}

	@Override
	public Integer findPidByGoodid(Integer id) {
		NativeQuery sqlQuery = currentSession().createSQLQuery("SELECT c_parent_id FROM t_category WHERE c_id = (SELECT c_id FROM t_product WHERE p_id = ?)");
		NativeQuery nativeQuery = sqlQuery.setParameter(0, id);
		List list = nativeQuery.list();
		Integer o = (Integer)list.get(0);
		return o;
	}


	private Session currentSession(){
		return sessionFactory.getCurrentSession();
	}
}
