package com.qf.echo.controller;

import com.qf.echo.dto.R;
import com.qf.echo.pojo.*;
import com.qf.echo.service.*;
import com.qf.echo.utils.OrderIdGenerator;
import com.sun.jdi.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.transform.Source;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Administrator on 2018/6/23.
 */
@Controller
@RequestMapping("/api/service")
public class CartController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private DrinkService drinkService;
	@Autowired
	private GourmetService gourmetService;
	@Autowired
	private PeripheralService peripheralService;
	@Autowired
	private TableService tableService;

	@RequestMapping("/addToCart")
	@ResponseBody
	/*处理订单业务*/
	/*如果接收的数据是json字符串那么就要用RequestBody，如果是get形式传参就用Requestparam，要是路径传参，那么就用PathVirable*/
	public R addCustom(@RequestBody Map<String, Object> map) {
		//这个地方要get一下session，然后获取登录信息，把消费存到用户表中
		//不要往session中放置任何数据
		Order trans = trans(map);

		System.out.println("尝试级联保存");
		Serializable save = orderService.save(trans);
		System.out.println("级联保存完成");
		System.out.println(save);

		return R.ojbk("入座成功");
	}

	//这个问题既然不能通过转换器解决那么就手动解决吧
	private Order trans(Map map) {
		Double money = 0.0;
		//上来就直接创建订单号
		String orderId = OrderIdGenerator.createOrderId();
		//在这个时候就把桌子占下，不能丢了
		Integer tableId = (Integer) map.get("tableId");
		System.out.println("tableId = " + tableId);
		tableService.changeFlag(tableId);
		tableService.changeOrderId(tableId, orderId);
		//创建order需要的list
		Set<BuyItem> set = new HashSet<BuyItem>();
		//由接收到数据创建订单
		Integer userLogined = (Integer) map.get("userLogined");
		System.out.println("--------1----------");
		System.out.println(userLogined);
		List<Map> goods = (List<Map>) map.get("cartList");
		//接收到数据的时候直接创建订单
//		List<?> buyItemList = new LinkedList<>();
		Order order = new Order();
//		BuyItem buyItem = new BuyItem();
		order.setStatus(0);//这个是支付状态
		order.setTid(tableId);
		order.setOid(orderId);
		//这个时候的good是个LinkedHashMap
		if (userLogined.equals(1)) {
			//这个是非会员的价格
			for (Map good : goods) {

				Integer goodId = Integer.parseInt((String) good.get("goodId"));
				Integer pid = drinkService.findPidByGoodid(goodId);
				System.out.println("啦啦啦");
				System.out.println(pid);
				System.out.println("啦啦啦");
				if (pid == 1) {
					//说明是饮品
					BuyItem buyItem = new BuyItem();
					Drink drink = drinkService.selectByRealKey(goodId);
					Integer num = (Integer) good.get("goodNum");
					Double price = drink.getMembershipPrice();//获取会员价
					System.out.println("----------------------------------");
					System.out.println(price);
					money = money + price * num;
					buyItem.setGoodid(drink.getId());
					buyItem.setMoney(price);
					buyItem.setNum(num);
					set.add(buyItem);
					//双向关联关系
					buyItem.setOrder(order);
					//双向关联关系
					//order.setBuyItemList(list);
					order.getBuyItemList().add(buyItem);//*TODO 测试1*//*
					//进行销售量数据的计算
					drinkService.addSellingNum(goodId,num);

				} else if (pid == 2) {
					//说明是美食
					BuyItem buyItem = new BuyItem();
					Gourmet gourmet = gourmetService.selectByRealId(goodId);
					Integer num = (Integer) good.get("goodNum");
					Double price = gourmet.getMembershipPrice();//获取会员价
					System.out.println("----------------------------------");
					System.out.println(price);
					money = money + price * num;
					buyItem.setGoodid(gourmet.getId());
					buyItem.setMoney(price);
					buyItem.setNum(num);
					set.add(buyItem);
					//双向关联关系
					buyItem.setOrder(order);
					//双向关联关系
					//order.setBuyItemList(list);
					order.getBuyItemList().add(buyItem);//*TODO 测试1*//*
					//进行销售量数据的计算

					drinkService.addSellingNum(goodId,num);
				}else if (pid == 3){
					BuyItem buyItem = new BuyItem();
					Peripheral peripheral = peripheralService.selectByRealId(goodId);
					Integer num = (Integer) good.get("goodNum");
					Double price = peripheral.getMembershipPrice();//获取会员价
					System.out.println("----------------------------------");
					System.out.println(price);
					money = money + price * num;
					buyItem.setGoodid(peripheral.getId());
					buyItem.setMoney(price);
					buyItem.setNum(num);
					set.add(buyItem);
					//双向关联关系
					buyItem.setOrder(order);
					//双向关联关系
					//order.setBuyItemList(list);
					order.getBuyItemList().add(buyItem);//*TODO 测试1*//*
					//进行销售量数据的计算
				}





				/*Integer mainCategory = (Integer) good.get("mainCategory");
				System.out.println("-----------2------------");
				System.out.println(mainCategory);
				//这个是用来判断到底应该调用那个方法的
				if (mainCategory == 0){//默认等于0就是饮品了
					BuyItem buyItem = new BuyItem();*//*TODO 2*//*
					Integer id = (Integer) good.get("goodId");
					Integer type = (Integer) good.get("smallCategory");
					Integer size = (Integer) good.get("size");
					Integer num = (Integer) good.get("buyingNum");
					Drink drink = drinkService.selectById(type, id, size);
					Double price = drink.getMembershipPrice();//获取会员价
					System.out.println("----------------------------------");
					System.out.println(price);
					money = money + price * num;
					buyItem.setGoodid(drink.getId());
					buyItem.setMoney(price);
					buyItem.setNum(num);
					set.add(buyItem);
					//双向关联关系
					buyItem.setOrder(order);
					//双向关联关系
					//order.setBuyItemList(list);
					order.getBuyItemList().add(buyItem);*//*TODO 测试1*//*
					//进行销售量数据的计算
					drinkService.addSellingNum0Id(type,id,size,num);
				}else if(mainCategory == 1){
					//这个说明是美食，这个的处理方法与另一个几乎一致
					BuyItem buyItem = new BuyItem();*//*TODO 2*//*
					Integer id = (Integer) good.get("goodId");
					Integer type = (Integer) good.get("smallCategory");
					Integer num = (Integer) good.get("buyingNum");
					Gourmet gourmet = gourmetService.selectById(type, id);
					Double price = gourmet.getMembershipPrice();
					money = money + price * num;
					buyItem.setGoodid(gourmet.getId());
					buyItem.setMoney(price);
					buyItem.setNum(num);
					set.add(buyItem);
					//双向关联关系
					buyItem.setOrder(order);
					//双向关联关系
				//	order.setBuyItemList(list);
					order.getBuyItemList().add(buyItem);*//*TODO 测试1*//*
					//进行销售量的计算，假定是假数据
					gourmetService.addSellingNum0Id(type,id,num);
				}else if(mainCategory == 2){
					//这个说明是周边商品，这个的处理方法与另一个几乎一致
					BuyItem buyItem = new BuyItem();*//*TODO 2*//*
					Integer id = (Integer) good.get("goodId");
					Integer type = (Integer) good.get("smallCategory");
					Integer num = (Integer) good.get("buyingNum");
					Peripheral peripheral = peripheralService.selectById(type, id);
					Double price = peripheral.getMembershipPrice();
					money = money + price * num;
					buyItem.setGoodid(peripheral.getId());
					buyItem.setMoney(price);
					buyItem.setNum(num);
					set.add(buyItem);
					//双向关联关系
					buyItem.setOrder(order);
					//双向关联关系
				//	order.setBuyItemList(list);
					order.getBuyItemList().add(buyItem);*//*TODO 测试1*//*
					//进行销售量加1的操作
					//TODO
					peripheralService.addSellingNum0Id(type,id,num);
				}
				order.setMoney(money);
			}
			order.setBuyItemList(set);
		}else if (userLogined != 1){
			//没有登录，实行非会员价格
			for (Map good : goods) {
				Integer mainCategory = (Integer) good.get("mainCategory");
				System.out.println("-----------2------------");
				System.out.println(mainCategory);
				//这个是用来判断到底应该调用那个方法的
				if (mainCategory == 0){//默认等于0就是饮品了
					BuyItem buyItem = new BuyItem();*//*TODO 2*//*
					Integer id = (Integer) good.get("goodId");
					Integer type = (Integer) good.get("smallCategory");
					Integer size = (Integer) good.get("size");
					Integer num = (Integer) good.get("buyingNum");
					Drink drink = drinkService.selectById(type, id, size);
					Double price = drink.getPrice();//获取会员价
					System.out.println("----------------------------------");
					System.out.println(price);
					money = money + price * num;
					buyItem.setGoodid(drink.getId());
					buyItem.setMoney(price);
					buyItem.setNum(num);
					set.add(buyItem);
					//双向关联关系
					buyItem.setOrder(order);
					//双向关联关系
				//	order.setBuyItemList(list);
					order.getBuyItemList().add(buyItem);*//*TODO 测试1*//*
					//进行销售量数据的计算
					//TODO 这个地方只进行了加一的操作
					drinkService.addSellingNum0Id(type,id,size,num);
				}else if(mainCategory == 1){
					//这个说明是美食，这个的处理方法与另一个几乎一致
					BuyItem buyItem = new BuyItem();*//*TODO 2*//*
					Integer id = (Integer) good.get("goodId");
					Integer type = (Integer) good.get("smallCategory");
					Integer num = (Integer) good.get("buyingNum");
					Gourmet gourmet = gourmetService.selectById(type, id);
					Double price = gourmet.getPrice();
					money = money + price * num;
					buyItem.setGoodid(gourmet.getId());
					buyItem.setMoney(price);
					buyItem.setNum(num);
					set.add(buyItem);
					//双向关联关系
					buyItem.setOrder(order);
					//双向关联关系
				//	order.setBuyItemList(list);
					order.getBuyItemList().add(buyItem);*//*TODO 测试1*//*
					//进行销售量的计算，假定是假数据
					//TODO 这个地方只进行了加一的操作
					gourmetService.addSellingNum0Id(type,id,num);
				}else if(mainCategory == 2){
					//这个说明是美食，这个的处理方法与另一个几乎一致
					BuyItem buyItem = new BuyItem();*//*TODO 2*//*
					Integer id = (Integer) good.get("goodId");
					Integer type = (Integer) good.get("smallCategory");
					Integer num = (Integer) good.get("buyingNum");
					Peripheral peripheral = peripheralService.selectById(type, id);
					Double price = peripheral.getPrice();
					money = money + price * num;
					buyItem.setGoodid(peripheral.getId());
					buyItem.setMoney(price);
					buyItem.setNum(num);
					set.add(buyItem);
					//双向关联关系
					buyItem.setOrder(order);
					//双向关联关系
				//	order.setBuyItemList(list);
					order.getBuyItemList().add(buyItem);*//*TODO 测试1*//*
					//进行销售量加1的操作
					//TODO
					peripheralService.addSellingNum0Id(type,id,num);
				}
				//order.setBuyItemList(set);*/
			}

		}else {
			for (Map good : goods) {

				Integer goodId = Integer.parseInt((String) good.get("goodId"));
				Integer pid = drinkService.findPidByGoodid(goodId);
				System.out.println("啦啦啦");
				System.out.println(pid);
				System.out.println("啦啦啦");
				if (pid == 1) {
					//说明是饮品
					BuyItem buyItem = new BuyItem();
					Drink drink = drinkService.selectByRealKey(goodId);
					Integer num = (Integer) good.get("goodNum");
					Double price = drink.getPrice();//获取会员价
					System.out.println("----------------------------------");
					System.out.println(price);
					money = money + price * num;
					buyItem.setGoodid(drink.getId());
					buyItem.setMoney(price);
					buyItem.setNum(num);
					set.add(buyItem);
					//双向关联关系
					buyItem.setOrder(order);
					//双向关联关系
					//order.setBuyItemList(list);
					order.getBuyItemList().add(buyItem);//*TODO 测试1*//*
					//进行销售量数据的计算
					drinkService.addSellingNum(goodId,num);

				} else if (pid == 2) {
					//说明是美食
					BuyItem buyItem = new BuyItem();
					Gourmet gourmet = gourmetService.selectByRealId(goodId);
					Integer num = (Integer) good.get("goodNum");
					Double price = gourmet.getPrice();//获取会员价
					System.out.println("----------------------------------");
					System.out.println(price);
					money = money + price * num;
					buyItem.setGoodid(gourmet.getId());
					buyItem.setMoney(price);
					buyItem.setNum(num);
					set.add(buyItem);
					//双向关联关系
					buyItem.setOrder(order);
					//双向关联关系
					//order.setBuyItemList(list);
					order.getBuyItemList().add(buyItem);//*TODO 测试1*//*
					//进行销售量数据的计算

					drinkService.addSellingNum(goodId,num);
				}else if (pid == 3){
					BuyItem buyItem = new BuyItem();
					Peripheral peripheral = peripheralService.selectByRealId(goodId);
					Integer num = (Integer) good.get("goodNum");
					Double price = peripheral.getPrice();//获取会员价
					System.out.println("----------------------------------");
					System.out.println(price);
					money = money + price * num;
					buyItem.setGoodid(peripheral.getId());
					buyItem.setMoney(price);
					buyItem.setNum(num);
					set.add(buyItem);
					//双向关联关系
					buyItem.setOrder(order);
					//双向关联关系
					//order.setBuyItemList(list);
					order.getBuyItemList().add(buyItem);//*TODO 测试1*//*
					//进行销售量数据的计算
				}





				/*Integer mainCategory = (Integer) good.get("mainCategory");
				System.out.println("-----------2------------");
				System.out.println(mainCategory);
				//这个是用来判断到底应该调用那个方法的
				if (mainCategory == 0){//默认等于0就是饮品了
					BuyItem buyItem = new BuyItem();*//*TODO 2*//*
					Integer id = (Integer) good.get("goodId");
					Integer type = (Integer) good.get("smallCategory");
					Integer size = (Integer) good.get("size");
					Integer num = (Integer) good.get("buyingNum");
					Drink drink = drinkService.selectById(type, id, size);
					Double price = drink.getMembershipPrice();//获取会员价
					System.out.println("----------------------------------");
					System.out.println(price);
					money = money + price * num;
					buyItem.setGoodid(drink.getId());
					buyItem.setMoney(price);
					buyItem.setNum(num);
					set.add(buyItem);
					//双向关联关系
					buyItem.setOrder(order);
					//双向关联关系
					//order.setBuyItemList(list);
					order.getBuyItemList().add(buyItem);*//*TODO 测试1*//*
					//进行销售量数据的计算
					drinkService.addSellingNum0Id(type,id,size,num);
				}else if(mainCategory == 1){
					//这个说明是美食，这个的处理方法与另一个几乎一致
					BuyItem buyItem = new BuyItem();*//*TODO 2*//*
					Integer id = (Integer) good.get("goodId");
					Integer type = (Integer) good.get("smallCategory");
					Integer num = (Integer) good.get("buyingNum");
					Gourmet gourmet = gourmetService.selectById(type, id);
					Double price = gourmet.getMembershipPrice();
					money = money + price * num;
					buyItem.setGoodid(gourmet.getId());
					buyItem.setMoney(price);
					buyItem.setNum(num);
					set.add(buyItem);
					//双向关联关系
					buyItem.setOrder(order);
					//双向关联关系
				//	order.setBuyItemList(list);
					order.getBuyItemList().add(buyItem);*//*TODO 测试1*//*
					//进行销售量的计算，假定是假数据
					gourmetService.addSellingNum0Id(type,id,num);
				}else if(mainCategory == 2){
					//这个说明是周边商品，这个的处理方法与另一个几乎一致
					BuyItem buyItem = new BuyItem();*//*TODO 2*//*
					Integer id = (Integer) good.get("goodId");
					Integer type = (Integer) good.get("smallCategory");
					Integer num = (Integer) good.get("buyingNum");
					Peripheral peripheral = peripheralService.selectById(type, id);
					Double price = peripheral.getMembershipPrice();
					money = money + price * num;
					buyItem.setGoodid(peripheral.getId());
					buyItem.setMoney(price);
					buyItem.setNum(num);
					set.add(buyItem);
					//双向关联关系
					buyItem.setOrder(order);
					//双向关联关系
				//	order.setBuyItemList(list);
					order.getBuyItemList().add(buyItem);*//*TODO 测试1*//*
					//进行销售量加1的操作
					//TODO
					peripheralService.addSellingNum0Id(type,id,num);
				}
				order.setMoney(money);
			}
			order.setBuyItemList(set);
		}else if (userLogined != 1){
			//没有登录，实行非会员价格
			for (Map good : goods) {
				Integer mainCategory = (Integer) good.get("mainCategory");
				System.out.println("-----------2------------");
				System.out.println(mainCategory);
				//这个是用来判断到底应该调用那个方法的
				if (mainCategory == 0){//默认等于0就是饮品了
					BuyItem buyItem = new BuyItem();*//*TODO 2*//*
					Integer id = (Integer) good.get("goodId");
					Integer type = (Integer) good.get("smallCategory");
					Integer size = (Integer) good.get("size");
					Integer num = (Integer) good.get("buyingNum");
					Drink drink = drinkService.selectById(type, id, size);
					Double price = drink.getPrice();//获取会员价
					System.out.println("----------------------------------");
					System.out.println(price);
					money = money + price * num;
					buyItem.setGoodid(drink.getId());
					buyItem.setMoney(price);
					buyItem.setNum(num);
					set.add(buyItem);
					//双向关联关系
					buyItem.setOrder(order);
					//双向关联关系
				//	order.setBuyItemList(list);
					order.getBuyItemList().add(buyItem);*//*TODO 测试1*//*
					//进行销售量数据的计算
					//TODO 这个地方只进行了加一的操作
					drinkService.addSellingNum0Id(type,id,size,num);
				}else if(mainCategory == 1){
					//这个说明是美食，这个的处理方法与另一个几乎一致
					BuyItem buyItem = new BuyItem();*//*TODO 2*//*
					Integer id = (Integer) good.get("goodId");
					Integer type = (Integer) good.get("smallCategory");
					Integer num = (Integer) good.get("buyingNum");
					Gourmet gourmet = gourmetService.selectById(type, id);
					Double price = gourmet.getPrice();
					money = money + price * num;
					buyItem.setGoodid(gourmet.getId());
					buyItem.setMoney(price);
					buyItem.setNum(num);
					set.add(buyItem);
					//双向关联关系
					buyItem.setOrder(order);
					//双向关联关系
				//	order.setBuyItemList(list);
					order.getBuyItemList().add(buyItem);*//*TODO 测试1*//*
					//进行销售量的计算，假定是假数据
					//TODO 这个地方只进行了加一的操作
					gourmetService.addSellingNum0Id(type,id,num);
				}else if(mainCategory == 2){
					//这个说明是美食，这个的处理方法与另一个几乎一致
					BuyItem buyItem = new BuyItem();*//*TODO 2*//*
					Integer id = (Integer) good.get("goodId");
					Integer type = (Integer) good.get("smallCategory");
					Integer num = (Integer) good.get("buyingNum");
					Peripheral peripheral = peripheralService.selectById(type, id);
					Double price = peripheral.getPrice();
					money = money + price * num;
					buyItem.setGoodid(peripheral.getId());
					buyItem.setMoney(price);
					buyItem.setNum(num);
					set.add(buyItem);
					//双向关联关系
					buyItem.setOrder(order);
					//双向关联关系
				//	order.setBuyItemList(list);
					order.getBuyItemList().add(buyItem);*//*TODO 测试1*//*
					//进行销售量加1的操作
					//TODO
					peripheralService.addSellingNum0Id(type,id,num);
				}
				//order.setBuyItemList(set);*/
			}
		}
		order.setMoney(money);
		tableService.changeConsumption(tableId, money);
		System.out.println("/**************/");
		System.out.println(money);
		return order;
	}

	@RequestMapping("/flag")
	@ResponseBody
	/*测试方法*/
	public R cah() {
		tableService.changeFlag(1);
		return R.ojbk();
	}


	@RequestMapping("/changeTable")
	@ResponseBody
	public R changeTable(@RequestBody Map<String, Integer> map) {
		Integer curTableId = map.get("curTableId");
		Integer changTo = map.get("changTo");
		/*开始进行桌子的筛选，筛选当前的桌子然后将坐姿的所有数据都转移到另一个桌子上，然后进行桌子复位的操作*/
		T_table table = tableService.selectTable(curTableId);
		T_table table1 = tableService.selectTable(changTo);
		table1.setFlag(true);
		table1.setOrderid(table.getOrderid());
		table1.setConsumption(table.getConsumption());
		tableService.resetTable(table.getId());
		/*需要一个总体更新的功能*/
		//TODO 后续还需要判断能不能换成功,要判断桌子的座位数是不是少于原本的座位数
		tableService.updateChangeAll(table1);
		return R.ojbk();
	}

	@RequestMapping("/reset/{id}")
	@ResponseBody
	public R reset(@PathVariable Integer id) {
		tableService.resetTable(id);
		return R.ojbk("桌子状态重置成功");
	}


	@RequestMapping("/orderDetail")
	@ResponseBody
	public R orderdetail(@RequestBody Map.Entry<String, String> entry) {
		String tableId = entry.getValue();
		if (tableId == null) {
			return R.error("桌号不能为空");
		}
		int i = Integer.parseInt(tableId);
		List<BuyItem> list = orderService.selectOrderByTableId(i);
		List data = new ArrayList<>();
		for (BuyItem buyItem : list) {
			Integer goodid = buyItem.getGoodid();
			//TODO 根据商品id去查找商品到底属于什么，
			//TODO 接口上并不需要是什么分类，那么把名字什么的筛选过去就行了
			Integer integer = orderService.selectPidById(goodid);

			if (integer == 1) {
				//这个时候说明这个商品是饮品所以筛选出个饮品来
				Drink drink = drinkService.selectByRealKey(goodid);
				data.add(drink);
			}
			if (integer == 2) {
				//说明现在是美食
				Gourmet gourmet = gourmetService.selectByRealId(goodid);
				data.add(gourmet);
			}
			if (integer == 3) {
				//说明现在是咖啡产品
				Peripheral peripheral = peripheralService.selectByRealId(goodid);
				data.add(peripheral);
			}
		}
		Double sum = tableService.selectTable(i).getConsumption();
		return R.ojbk().put("data", data).put("sum", sum);
	}

	@RequestMapping("/checkout")
	@ResponseBody
	public R checkout() {

		return R.ojbk();
	}

	@RequestMapping("/goodDetail")
	@ResponseBody
	public R goodDetail(@RequestBody Map<String, List<String>> goodmap) {
		List<String> goodslist = goodmap.get("goods");
		List<Integer> list = new ArrayList<Integer>();
		for (String s : goodslist) {
			list.add(Integer.parseInt(s));
		}
		List<GoodDetail> details = new ArrayList<GoodDetail>();
		for (Integer integer : list) {
			List<GoodDetail> details1 = drinkService.details(integer);
			for (GoodDetail goodDetail : details1) {
				details.add(goodDetail);
			}
		}
		return R.ojbk().put("details", details);
	}
}
