package com.qf.echo.controller;

import com.qf.echo.dto.R;
import com.qf.echo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/19.
 */
@Controller
@RequestMapping("/api/menu")
public class CategoryController {
	@Autowired
	private MenuService menuService;
	@RequestMapping("/firstlevel")
	@ResponseBody
	public R firstLevel(){
		List<Object> objects = menuService.firstMenu();
		return R.ojbk().put("res",objects);
	}
	@RequestMapping("/secondlevel")
	@ResponseBody
	public R secondlevel(@RequestBody Map.Entry<String,Integer> entry){
		System.out.println(entry);
		if(entry != null){
			Integer i = entry.getValue();
			List<Object> secondMenu = menuService.secondMenu(i);
			return R.ojbk().put("res",secondMenu);
		}
//		if(type != null){
//			Integer i = Integer.parseInt(type);
//			List<Object> secondMenu = menuService.secondMenu(i);
//			return R.ojbk().put("res",secondMenu);
//		}
		return R.error("错误的类型");
	}
}
