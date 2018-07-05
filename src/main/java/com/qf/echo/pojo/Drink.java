package com.qf.echo.pojo;

import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;

/**
 * Created by Administrator on 2018/6/19.
 */
@Entity
@Table(name = "t_product")
public class Drink {
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
	@Column(name = "p_size")
	private Integer size;
	@Column(name = "p_salenum")
	private Integer sellingNum;
	@Column(name = "p_isnew")
	private String isNew;
	@Column(name = "p_img")
	private String imgPath;
	@Column(name = "p_intro")
	private String intro;
	@Column(name = "c_id")
	private Integer cid;

	public Drink() {
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

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getSellingNum() {
		return sellingNum;
	}

	public void setSellingNum(Integer sellingNum) {
		this.sellingNum = sellingNum;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	@Override
	public String toString() {
		return "Drink{" +
				"id=" + id +
				", name='" + name + '\'' +
				", price=" + price +
				", membershipPrice=" + membershipPrice +
				", size=" + size +
				", sellingNum=" + sellingNum +
				", isNew='" + isNew + '\'' +
				", imgPath='" + imgPath + '\'' +
				", intro='" + intro + '\'' +
				", cid=" + cid +
				'}';
	}
}
