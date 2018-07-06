package com.qf.echo.service.impl;

import com.qf.echo.dao.PeripheralDao;
import com.qf.echo.pojo.Peripheral;
import com.qf.echo.service.PeripheralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/6/21.
 */
@Service
public class PeripheralServiceImpl implements PeripheralService {
	@Autowired
	private PeripheralDao peripheralDao;
	@Override
	public List<Peripheral> peripheralList(Integer type, Integer page) {
		List<Peripheral> list = peripheralDao.peripheralList(type, page);
		return list;
	}

	@Override
	public List<Peripheral> newestPeripheral() {
		List<Peripheral> list = peripheralDao.newestPeripheral();
		return list;
	}

	@Override
	public List<Peripheral> hottest() {
		List<Peripheral> hottestPeripheral = peripheralDao.hottest();
		return hottestPeripheral;
	}

	@Override
	public Peripheral selectById(Integer type, Integer id) {
		Peripheral peripheral = peripheralDao.selectById(type, id);
		return peripheral;
	}

	@Override
	public void addSellingNum0Id(Integer type, Integer id, Integer num) {
		peripheralDao.addSellingNum0Id(type,id,num);
	}

	@Override
	public void addSellingNum(Integer id, Integer num) {
		peripheralDao.addSellingNum(id,num);
	}

	@Override
	public Peripheral selectByRealId(Integer id) {
		return null;
	}
}
