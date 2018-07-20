package com.qf.echo.controller;

import com.qf.echo.dto.R;
import com.qf.echo.pojo.Peripheral;
import com.qf.echo.service.PeripheralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2018/6/21.
 */
@Controller
@RequestMapping("/api/prod")
public class PeripheralController {
	@Autowired
	private PeripheralService peripheralService;
	@RequestMapping("/peripheral")
	@ResponseBody
	public R peripheralList(@RequestBody Map<String,Integer> map){
		Integer type = map.get("type");
//		Integer page = map.get("page");
		List<Peripheral> list = peripheralService.peripheralList(type, 1);
		return R.ojbk().put("data",list);
	}


}
