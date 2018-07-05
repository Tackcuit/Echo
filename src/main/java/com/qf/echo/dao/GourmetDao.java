package com.qf.echo.dao;

import com.qf.echo.pojo.Gourmet;

import java.util.List;

/**
 * Created by Administrator on 2018/6/20.
 */
public interface GourmetDao {
	List<Gourmet> gourmetList(Integer type,Integer page);
	List<Gourmet> newestGourmet();
	List<Gourmet> hottest();
	Gourmet selectById(Integer type , Integer id);
	//假定前端传过来的数据是数据库中的ID
	Gourmet selectByRealId(Integer goodId);
	//根据假数据进行加销售量的操作
	void addSellingNum0Id(Integer type , Integer id);
	//根据数据库真实的id加销售量的操作
	void addSellingNum(Integer id);
}
