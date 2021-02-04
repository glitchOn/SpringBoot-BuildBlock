package com.ck.restservices.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="orders")
public class Order extends RepresentationModel<Order>{
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(length=50,nullable=false,unique=false)
	private String orderDesc;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;

	public Order() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderDesc=" + orderDesc + ", user=" + user + "]";
	}
	
	
}
