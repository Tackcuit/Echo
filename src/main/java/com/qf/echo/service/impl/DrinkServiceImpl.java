package com.qf.echo.service.impl;

import com.qf.echo.dao.DrinkDao;
import com.qf.echo.pojo.Drink;
import com.qf.echo.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/6/19.
 */
@Service
public class DrinkServiceImpl implements DrinkService {
	@Autowired
	private DrinkDao drinkDao;
	@Override
	public List<Drink> drinklist(Integer type, Integer page) {
		List<Drink> drinklist = drinkDao.drinklist(type, page);
		return drinklist;
	}

	@Override
	public List<Drink> newestDrink() {
		List<Drink> drinks = drinkDao.newestDrink();
		return drinks;
	}

	@Override
	public List<Drink> hottest() {
		List<Drink> hottestDrink = drinkDao.hottest();
		return hottestDrink;
	}

	@Override
	public Drink selectById(Integer type, Integer id,Integer size) {
		Drink drink = drinkDao.selectById(type, id,size);
		return drink;
	}

	@Override
	public void addSellingNum(Integer goodId,Integer num) {
		drinkDao.addSellingNum(goodId,num);
	}

	@Override
	public Drink selectByRealKey(Integer goodId) {
		Drink drink = drinkDao.selectByRealKey(goodId);
		return drink;
	}

	@Override
	public void addSellingNum0Id(Integer type, Integer id, Integer size, Integer num) {
		drinkDao.addSellingNum0Id(type,id,size, num);
	}
}
