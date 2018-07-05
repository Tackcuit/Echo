package com.qf.echo.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.Date;

/**
 * Created by Administrator on 2018/6/22.
 */
@Entity
@Table(name = "t_order")
public class Order implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "oid")
	private String oid;
	@Column(name = "tid")
	private Integer tid;
	@Column(name = "money")
	private Double money;
	@Column(name = "status")
	private Integer status;
	@Column(name = "time")
	private Date time;

	//最最重要的，订单里一定要有购物项！
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
	private Set<BuyItem> buyItemList = new HashSet<BuyItem>();

	public Order() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Set<BuyItem> getBuyItemList() {
		return buyItemList;
	}

	public void setBuyItemList(Set<BuyItem> buyItemList) {
		this.buyItemList = buyItemList;
	}

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", oid='" + oid + '\'' +
				", tid=" + tid +
				", money=" + money +
				", status=" + status +
				", time=" + time +
//				", buyItemList=" + buyItemList +
				'}';
	}
}
