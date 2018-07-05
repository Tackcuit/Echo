package com.qf.echo.service.impl;

import com.qf.echo.dao.TableDao;
import com.qf.echo.pojo.T_table;
import com.qf.echo.service.TableService;
import javafx.scene.control.Tab;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import java.util.List;

/**
 * Created by Administrator on 2018/7/3.
 */
@Service
@DynamicUpdate
public class TableServiceImpl implements TableService {
	@Autowired
	private TableDao tableDao;
	@Override
	public List<T_table> selectAll() {
		List<T_table> tables = tableDao.selectAll();
		return tables;
	}

	@Override
	public void changeFlag(Integer tableId) {
		T_table table = tableDao.selectTable(tableId);
		Boolean flag = table.getFlag();
		if (!flag){
			table.setFlag(true);
			tableDao.changeFlag(table);


		}else {
			table.setFlag(false);
			tableDao.changeFlag(table);
		}
	}

	@Override
	public void changeConsumption(Integer tableId, Double consumpation) {
		T_table table = new T_table();
		table.setId(tableId);
		table.setConsumption(consumpation);
		tableDao.changeConsumption(table);
	}

	@Override
	public void changeOrderId(Integer tableId, String orderId) {
		T_table table =new T_table();
		table.setId(tableId);
		table.setOrderid(orderId);
		tableDao.changeOrderId(table);
	}

	@Override
	public T_table selectTable(Integer tableId) {
		T_table table = tableDao.selectTable(tableId);
		return table;
	}

	@Override
	public void resetTable(Integer tableId) {
		T_table table = new T_table();
		table.setId(tableId);
		table.setOrderid("");
		table.setFlag(false);
		table.setConsumption(0.0);
		tableDao.resetTable(table);
	}


}
