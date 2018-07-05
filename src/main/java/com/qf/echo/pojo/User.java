package com.qf.echo.pojo;

import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by Administrator on 2018/6/17.
 */
@Entity
@Table(name = "t_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "u_id")
	private Integer id;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "currentconsumption")
	private Double currentConsumption;
	@Column(name = "sumconsumption")
	private Double sumConsumption;
	@Column(name = "email")
	private String email;
	@Column(name = "gender")
	private String gender;
	@Column(name = "phone")
	private String phone;

	public User() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Double getCurrentConsumption() {
		return currentConsumption;
	}

	public void setCurrentConsumption(Double currentConsumption) {
		this.currentConsumption = currentConsumption;
	}

	public Double getSumConsumption() {
		return sumConsumption;
	}

	public void setSumConsumption(Double sunConsumption) {
		this.sumConsumption = sunConsumption;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", currentConsumption=" + currentConsumption +
				", sunConsumption=" + sumConsumption +
				", email='" + email + '\'' +
				", gender='" + gender + '\'' +
				", phone='" + phone + '\'' +
				'}';
	}
}
