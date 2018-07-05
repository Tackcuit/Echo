package com.qf.echo.pojo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2018/6/19.
 */
@Entity
@Table(name = "t_category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "c_id")
	private Integer id;
	@Column(name = "c_name")
	private String name;
	@Column(name = "c_level")
	private Integer level;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,targetEntity = Category.class)
	@JoinColumn(name = "c_parent_id")
	private Set<Category> Categories = new HashSet<Category>();

	public Category() {
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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Set<Category> getCategories() {
		return Categories;
	}

	public void setCategories(Set<Category> categories) {
		Categories = categories;
	}
}
