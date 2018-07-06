package com.qf.echo.dao;

import com.qf.echo.pojo.Drink;

import java.util.List;

/**
 * Created by Administrator on 2018/6/19.
 */
public interface DrinkDao {
	List<Drink> drinklist(Integer type,Integer page);
	//最新商品
	List<Drink> newestDrink();
	//热卖商品
	List<Drink> hottest();

	//根据所存在的数据进行检索
	Drink selectById(Integer type , Integer id,Integer size);
	//假定前端传输过来的数据是数据库中的ID，进行直接检索
	void addSellingNum(Integer goodId, Integer num);
	//根据数据库ID进行检索
	Drink selectByRealKey(Integer goodId);
	//假定前端传过来的数据不是数据库中的ID，所以说要进行普通检索
	void addSellingNum0Id(Integer type , Integer id,Integer size, Integer num);
}
