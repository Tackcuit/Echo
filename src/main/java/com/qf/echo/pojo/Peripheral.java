package com.qf.echo.pojo;

import javax.persistence.*;

/**
 * Created by Administrator on 2018/6/21.
 */
@Entity
@Table(name = "t_product")
public class Peripheral {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "p_id")
	private Integer id;
	@Column(name = "p_name")
	private String name;
	@Column(name = "p_price")
	private Double price;
	@Column(name = "p_membershipprice")
	private Double membershipPrice;
	@Column(name = "p_salenum")
	private Integer sellingNum;
	@Column(name = "p_img")
	private String goodImg;
	@Column(name = "p_intro")
	private String introduction;

	public Peripheral() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getMembershipPrice() {
		return membershipPrice;
	}

	public void setMembershipPrice(Double membershipPrice) {
		this.membershipPrice = membershipPrice;
	}

	public Integer getSellingNum() {
		return sellingNum;
	}

	public void setSellingNum(Integer sellingNum) {
		this.sellingNum = sellingNum;
	}

	public String getGoodImg() {
		return goodImg;
	}

	public void setGoodImg(String goodImg) {
		this.goodImg = goodImg;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	@Override
	public String toString() {
		return "Peripheral{" +
				"id=" + id +
				", name='" + name + '\'' +
				", price=" + price +
				", membershipPrice=" + membershipPrice +
				", sellingNum=" + sellingNum +
				", goodImg='" + goodImg + '\'' +
				", introduction='" + introduction + '\'' +
				'}';
	}
}
