package com.qf.echo.pojo;

import com.fasterxml.jackson.databind.node.DoubleNode;

import javax.persistence.*;

//                            _ooOoo_
//                           o8888888o
//                           88" . "88
//                           (| -_- |)
//                            O\ = /O
//                        ____/`---'\____
//                      .   ' \\| |// `.
//                       / \\||| : |||// \
//                     / _||||| -:- |||||- \
//                       | | \\\ - /// | |
//                     | \_| ''\---/'' | |
//                      \ .-\__ `-` ___/-. /
//                   ___`. .' /--.--\ `. . __
//                ."" '< `.___\_<|>_/___.' >'"".
//               | | : `- \`.;`\ _ /`;.`/ - ` : | |
//                 \ \ `-. \_ __\ /__ _/ .-` / /
//         ======`-.____`-.___\_____/___.-`____.-'======
//                            `=---='
//
//         .............................................
//                  佛祖镇楼           BUG辟易
//
//                             佛曰:
//
//                  写字楼里写字间，写字间里程序员；
//                  程序人员写程序，又拿程序换酒钱。
//                  酒醒只在网上坐，酒醉还来网下眠；
//                  酒醉酒醒日复日，网上网下年复年。
//                  但愿老死电脑间，不愿鞠躬老板前；
//                  奔驰宝马贵者趣，公交自行程序员。
//                  别人笑我忒疯癫，我笑自己命太贱；
//                  不见满街漂亮妹，哪个归得程序员？
@Entity
@Table(name = "t_product")
public class GoodDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "p_id")
	private Integer id;
	@Column(name = "p_img")
	private String goodImg;
	@Column(name = "p_name")
	private String goodName;
	@Column(name = "p_price")
	private Double price;
	@Column(name = "p_membershipprice")
	private Double membershipPrice;

	public GoodDetail() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGoodImg() {
		return goodImg;
	}

	public void setGoodImg(String goodImg) {
		this.goodImg = goodImg;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
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

	@Override
	public String toString() {
		return "GoodDetail{" +
				"id=" + id +
				", goodImg='" + goodImg + '\'' +
				", goodName='" + goodName + '\'' +
				", price=" + price +
				", membershipPrice=" + membershipPrice +
				'}';
	}
}
