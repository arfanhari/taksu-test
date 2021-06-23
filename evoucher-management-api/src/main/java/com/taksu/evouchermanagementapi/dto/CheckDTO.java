package com.taksu.evouchermanagementapi.dto;

public class CheckDTO {
	private String voucher_status;
	private int customer_id;
	
	public String getVoucher_status() {
		return voucher_status;
	}
	public void setVoucher_status(String voucher_status) {
		this.voucher_status = voucher_status;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
}
