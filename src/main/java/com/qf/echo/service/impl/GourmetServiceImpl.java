package com.qf.echo.service.impl;

import com.qf.echo.dao.GourmetDao;
import com.qf.echo.pojo.Gourmet;
import com.qf.echo.service.GourmetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/6/20.
 */
@Service
public class GourmetServiceImpl implements GourmetService {
	@Autowired
	private GourmetDao gourmetDao;
	@Override
	public List<Gourmet> gourmetList(Integer type, Integer page) {
		List<Gourmet> gourmets = gourmetDao.gourmetList(type, page);
		return gourmets;
	}

	@Override
	public List<Gourmet> newestGourmet() {
		List<Gourmet> gourmets = gourmetDao.newestGourmet();
		return gourmets;
	}

	@Override
	public List<Gourmet> hottest() {
		List<Gourmet> hottestGourmet = gourmetDao.hottest();
		return hottestGourmet;
	}

	@Override
	public Gourmet selectById(Integer type, Integer id) {
		Gourmet gourmet = gourmetDao.selectById(type, id);
		return gourmet;
	}

	@Override
	public Gourmet selectByRealId(Integer goodId) {
		Gourmet gourmet = gourmetDao.selectByRealId(goodId);
		return gourmet;
	}

	@Override
	public void addSellingNum0Id(Integer type, Integer id) {
		gourmetDao.addSellingNum0Id(type,id);
	}

	@Override
	public void addSellingNum(Integer id) {
		gourmetDao.addSellingNum(id);
	}
}
