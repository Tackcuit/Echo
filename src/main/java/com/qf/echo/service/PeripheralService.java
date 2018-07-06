package com.qf.echo.service;

import com.qf.echo.pojo.Peripheral;

import java.util.List;

/**
 * Created by Administrator on 2018/6/21.
 */
public interface PeripheralService {
	List<Peripheral> peripheralList(Integer type, Integer page);
	List<Peripheral> newestPeripheral();
	List<Peripheral> hottest();
	Peripheral selectById(Integer type , Integer id);
	//根据假数据进行加销售量的操作
	void addSellingNum0Id(Integer type , Integer id, Integer num);
	//根据数据库真实的id加销售量的操作
	void addSellingNum(Integer id, Integer num);
	//根据真实数据来查数据库
	Peripheral selectByRealId(Integer id);
}
