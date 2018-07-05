package com.qf.echo.controller;

import com.qf.echo.dto.R;
import com.qf.echo.pojo.*;
import com.qf.echo.service.*;
import com.qf.echo.utils.OrderIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	/*如果接收的数据是json字符串那么就要用RequestBody，如果是get形式传参就用Requestparam，要是路径传参，那么就用PathVirable*/
	public R addCustom(@RequestBody Map<String,Object> map){
		//这个地方要get一下session，然后获取登录信息，把消费存到用户表中
		Order trans = trans(map);

		System.out.println("尝试级联保存");
		Serializable save = orderService.save(trans);
		System.out.println("级联保存完成");
		System.out.println(save);

		return R.ojbk();
	}

	//这个问题既然不能通过转换器解决那么就手动解决吧
	private Order trans(Map map){
		Double money = 0.0;
		//上来就直接创建订单号
		String orderId = OrderIdGenerator.createOrderId();
		//在这个时候就把桌子占下，不能丢了
		Integer tableId = (Integer) map.get("tableId");
		System.out.println("tableId = " + tableId);
		tableService.changeFlag(tableId);
		tableService.changeOrderId(tableId,orderId);
		//创建order需要的list
		Set<BuyItem> set = new HashSet<BuyItem>();
		//由接收到数据创建订单
		Integer userLogined = (Integer) map.get("userLogined");
		System.out.println("--------1----------");
		System.out.println(userLogined);
		List<Map> goods = (List<Map>) map.get("goods");
		//接收到数据的时候直接创建订单
//		List<?> buyItemList = new LinkedList<>();
		Order order = new Order();
//		BuyItem buyItem = new BuyItem();
		order.setStatus(0);//这个是支付状态
		order.setTid(tableId);
		order.setOid(orderId);
		//这个时候的good是个LinkedHashMap
		if(userLogined.equals(1)){
			//这个是非会员的价格
			for (Map good : goods) {
				Integer mainCategory = (Integer) good.get("mainCategory");
				System.out.println("-----------2------------");
				System.out.println(mainCategory);
				//这个是用来判断到底应该调用那个方法的
				if (mainCategory == 0){//默认等于0就是饮品了
					BuyItem buyItem = new BuyItem();/*TODO 2*/
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
					order.getBuyItemList().add(buyItem);/*TODO 测试1*/
					//进行销售量数据的计算
					drinkService.addSellingNum0Id(type,id,size);
				}else if(mainCategory == 1){
					//这个说明是美食，这个的处理方法与另一个几乎一致
					BuyItem buyItem = new BuyItem();/*TODO 2*/
					Integer id = (Integer) good.get("goodId");
					Integer type = (Integer) good.get("smallCategory");
					Integer num = (Integer) good.get("buyingNum");
					Gourmet gourmet = gourmetService.selectById(type, id);
					Double price = gourmet.getMembershipprice();
					money = money + price * num;
					buyItem.setGoodid(gourmet.getId());
					buyItem.setMoney(price);
					buyItem.setNum(num);
					set.add(buyItem);
					//双向关联关系
					buyItem.setOrder(order);
					//双向关联关系
				//	order.setBuyItemList(list);
					order.getBuyItemList().add(buyItem);/*TODO 测试1*/
					//进行销售量的计算，假定是假数据
					gourmetService.addSellingNum0Id(type,id);
				}else if(mainCategory == 2){
					//这个说明是周边商品，这个的处理方法与另一个几乎一致
					BuyItem buyItem = new BuyItem();/*TODO 2*/
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
					order.getBuyItemList().add(buyItem);/*TODO 测试1*/
					//进行销售量加1的操作
					//TODO
					peripheralService.addSellingNum0Id(type,id);
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
					BuyItem buyItem = new BuyItem();/*TODO 2*/
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
					order.getBuyItemList().add(buyItem);/*TODO 测试1*/
					//进行销售量数据的计算
					//TODO 这个地方只进行了加一的操作
					drinkService.addSellingNum0Id(type,id,size);
				}else if(mainCategory == 1){
					//这个说明是美食，这个的处理方法与另一个几乎一致
					BuyItem buyItem = new BuyItem();/*TODO 2*/
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
					order.getBuyItemList().add(buyItem);/*TODO 测试1*/
					//进行销售量的计算，假定是假数据
					//TODO 这个地方只进行了加一的操作
					gourmetService.addSellingNum0Id(type,id);
				}else if(mainCategory == 2){
					//这个说明是美食，这个的处理方法与另一个几乎一致
					BuyItem buyItem = new BuyItem();/*TODO 2*/
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
					order.getBuyItemList().add(buyItem);/*TODO 测试1*/
					//进行销售量加1的操作
					//TODO
					peripheralService.addSellingNum0Id(type,id);
				}
				//order.setBuyItemList(set);
			}
		}
		order.setMoney(money);
		tableService.changeConsumption(tableId,money);
		System.out.println("/**************/");
		System.out.println(money);
		return order;
	}

	@RequestMapping("/falg")
	@ResponseBody
	public R cah(){
		tableService.changeFlag(1);
		return R.ojbk();
	}

	@RequestMapping("/changeTable")
	@ResponseBody
	public R changeTable(@RequestBody Map<String,Integer> map){
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
		return R.ojbk();
	}
}
