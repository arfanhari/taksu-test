package com.taksu.evouchermanagementapi.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="customer")
public class Customer implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="phone_number", length=20, nullable=false, unique=true)
	private String phone_number;
	
	@Column(name="gift_limit")
	private int gift_limit;
	
	@Column(name="purchase_limit")
	private int voucher_limit;
	
	@Column(name="amount_debit")
	private Double amount_debit;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public int getGift_limit() {
		return gift_limit;
	}
	public void setGift_limit(int gift_limit) {
		this.gift_limit = gift_limit;
	}
	public int getVoucher_limit() {
		return voucher_limit;
	}
	public void setVoucher_limit(int voucher_limit) {
		this.voucher_limit = voucher_limit;
	}
	public Double getAmount_debit() {
		return amount_debit;
	}
	public void setAmount_debit(Double amount_debit) {
		this.amount_debit = amount_debit;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
