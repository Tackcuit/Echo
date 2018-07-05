package com.qf.echo.controller;

import com.qf.echo.dto.R;
import com.qf.echo.pojo.Gourmet;
import com.qf.echo.service.GourmetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/20.
 */
@Controller
@RequestMapping("/api/prod")
public class GourmetController {
	@Autowired
	private GourmetService gourmetService;
	@RequestMapping("/gourmet1")
	@ResponseBody
	public R gourmetlist(@RequestBody Map<String,Integer> map){
		Integer type = map.get("type");
		Integer page = map.get("page");
		List<Gourmet> gourmets = gourmetService.gourmetList(type, page);
		return R.ojbk().put("data",gourmets);
	}
}
