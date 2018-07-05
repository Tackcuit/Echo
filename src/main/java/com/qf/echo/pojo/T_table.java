package com.qf.echo.pojo;

import org.springframework.context.annotation.Scope;

import javax.persistence.*;


/**
 * Created by Administrator on 2018/6/22.
 */
@Entity
@Scope("prototype")
@Table(name = "t_table")
public class T_table {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "t_id")
	private Integer id;
	@Column(name = "size")
	private Integer size;
	@Column(name = "flag")
	private Boolean flag;
	@Column(name = "consumption")
	private Double consumption;
	@Column(name = "orderid")
	private String orderid;

	public T_table() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public Double getConsumption() {
		return consumption;
	}

	public void setConsumption(Double consumption) {
		this.consumption = consumption;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	@Override
	public String toString() {
		return "T_table{" +
				"id=" + id +
				", size=" + size +
				", flag=" + flag +
				", consumption=" + consumption +
				", orderid='" + orderid + '\'' +
				'}';
	}
}
