package com.qf.echo.dao;

import com.qf.echo.pojo.BuyItem;
import com.qf.echo.pojo.Drink;
import com.qf.echo.pojo.GoodDetail;

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

//	List<Drink> selectById(Integer goodid);//分辨是哪一个饮品

	//分辨是属于什么的
	Integer findPidByGoodid(Integer id);

	//List<BuyItem> listBuyItems(Integer tid);

	List<GoodDetail> details(Integer goodid);


	//Integer selectMainC(Integer goodid);
}
