package com.taksu.evouchermanagementapi.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "e_voucher")
public class EVoucher implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="title", length=50, nullable=false, unique=true)
	private String title;
	
	@Column(name="description", length=300)
	private String description;
	
	@Column(name="expired_date")
	private Date expired_date;
	
	@Column(name="amount")
	private Double amount;
	
	@Column(name="discounted_payment_method", length=50)
	private String discounted_payment_method;
	
	@Column(name="quantity")
	private int quantity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getExpired_date() {
		return expired_date;
	}

	public void setExpired_date(Date expired_date) {
		this.expired_date = expired_date;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDiscounted_payment_method() {
		return discounted_payment_method;
	}

	public void setDiscounted_payment_method(String discounted_payment_method) {
		this.discounted_payment_method = discounted_payment_method;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}