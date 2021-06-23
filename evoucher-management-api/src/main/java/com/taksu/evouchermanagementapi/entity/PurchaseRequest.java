package com.taksu.evouchermanagementapi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="purchase_request")
public class PurchaseRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="purchase_status")
	private String purchase_status;
	
	@Column(name="purchase_type")
	private String purchase_type;
	
	@Column(name="picked_payment_method")
	private String picked_payment_method;
	
	@Column(name="amount")
	private Double amount;
	
	@Column(name="voucher_code", unique=true)
	private String voucher_code;
	
	@Column(name="voucher_qrcode_path")
	private String voucher_qrcode_path;
	
	@Column(name="voucher_status")
	private String voucher_status;
	
	@ManyToOne
	private EVoucher eVoucher;
	
	@ManyToOne
	private Customer customer;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPurchase_status() {
		return purchase_status;
	}
	public void setPurchase_status(String purchase_status) {
		this.purchase_status = purchase_status;
	}
	public String getPurchase_type() {
		return purchase_type;
	}
	public void setPurchase_type(String purchase_type) {
		this.purchase_type = purchase_type;
	}
	public String getPicked_payment_method() {
		return picked_payment_method;
	}
	public void setPicked_payment_method(String picked_payment_method) {
		this.picked_payment_method = picked_payment_method;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getVoucher_code() {
		return voucher_code;
	}
	public void setVoucher_code(String voucher_code) {
		this.voucher_code = voucher_code;
	}
	public String getVoucher_qrcode_path() {
		return voucher_qrcode_path;
	}
	public void setVoucher_qrcode_path(String voucher_qrcode_path) {
		this.voucher_qrcode_path = voucher_qrcode_path;
	}
	public String getVoucher_status() {
		return voucher_status;
	}
	public void setVoucher_status(String voucher_status) {
		this.voucher_status = voucher_status;
	}
	public EVoucher geteVoucher() {
		return eVoucher;
	}
	public void seteVoucher(EVoucher eVoucher) {
		this.eVoucher = eVoucher;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
