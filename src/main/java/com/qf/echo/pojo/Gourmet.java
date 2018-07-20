package com.qf.echo.pojo;

import javax.persistence.*;

/**
 * Created by Administrator on 2018/6/19.
 */
@Entity
@Table(name = "t_product")
public class Gourmet {
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
	@Column(name = "p_intro")
	private String intro;
	@Column(name = "p_img")
	private String goodImg;
	@Column(name = "p_isnew")
	private String isNew;

	public Gourmet() {
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

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getGoodImg() {
		return goodImg;
	}

	public void setGoodImg(String goodImg) {
		this.goodImg = goodImg;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
}
