package com.qf.echo.pojo;

import javax.persistence.*;

/**
 * Created by Administrator on 2018/6/22.
 */
@Entity
@Table(name = "t_odetail")
public class BuyItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "gid")
	private Integer goodid;
	@Column(name = "money")
	private Double money;
	@Column(name = "num")
	private Integer num;

	@ManyToOne
	@JoinColumn(name = "oid",referencedColumnName = "oid")
	private Order order;

	public BuyItem() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Integer getGoodid() {
		return goodid;
	}

	public void setGoodid(Integer goodid) {
		this.goodid = goodid;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "BuyItem{" +
				"id=" + id +
//				", order=" + order +
				", goodid=" + goodid +
				", money=" + money +
				", num=" + num +
				'}';
	}
}
