package com.qf.echo.controller;

import com.qf.echo.dto.R;
import com.qf.echo.pojo.Drink;
import com.qf.echo.pojo.Gourmet;
import com.qf.echo.pojo.Peripheral;
import com.qf.echo.service.DrinkService;
import com.qf.echo.service.GourmetService;
import com.qf.echo.service.PeripheralService;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/19.
 */
@Controller
@RequestMapping("/api/prod")
public class DrinkController {
	@Autowired
	private DrinkService drinkService;
	@RequestMapping("/drinks")
	@ResponseBody
	public R drinks(@RequestBody Map<String,Integer> map){
		System.out.println(map);
		Integer type = map.get("type");
		Integer page = map.get("page");
//		System.out.println(entry);
//		System.out.println(type);
//		System.out.println(page);
		List<Drink> drinklist = drinkService.drinklist(type, page);
		return R.ojbk().put("data",drinklist);
	}


	@Autowired
	private GourmetService gourmetService;
	@RequestMapping("/gourmet")
	@ResponseBody
	public R gourmets(@RequestBody Map<String,Integer> map){
		System.out.println(map);
		Integer type = map.get("type");
		Integer page = map.get("page");
//		System.out.println(entry);
//		System.out.println(type);
//		System.out.println(page);
		List<Gourmet> gourmets = gourmetService.gourmetList(type, page);
		return R.ojbk().put("data",gourmets);
	}
	@Autowired
	private PeripheralService peripheralService;
	@RequestMapping("/newest")
	@ResponseBody
	public R newest(){
		List<Drink> drinks = drinkService.newestDrink();
		List<Gourmet> gourmets = gourmetService.newestGourmet();
		List<Peripheral> peripherals = peripheralService.newestPeripheral();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("drinks",drinks);
		map.put("gourmet",gourmets);
		map.put("peripheral",peripherals);
		return R.ojbk().put("data",map);

	}

	@RequestMapping("/hottest")
	@ResponseBody
	public R hottest(@RequestBody Map.Entry<String,Integer> entry){
		Integer value = entry.getValue();
		if(value == 0){
			return R.ojbk().put("data",drinkService.hottest());
		}
		if(value == 1){
			return R.ojbk().put("data",gourmetService.hottest());
		}
		if(value == 2){
			return R.ojbk().put("data",peripheralService.hottest());
		}
		return R.error("想要查找的类型不存在了");
	}
}

