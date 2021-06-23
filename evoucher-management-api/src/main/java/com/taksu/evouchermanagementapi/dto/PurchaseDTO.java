package com.taksu.evouchermanagementapi.dto;

public class PurchaseDTO {
	private int customerId;
	private int eVoucherId;
	private String purchaseType;
	private String selectedPaymentMethod;
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int geteVoucherId() {
		return eVoucherId;
	}
	public void seteVoucherId(int eVoucherId) {
		this.eVoucherId = eVoucherId;
	}
	public String getPurchaseType() {
		return purchaseType;
	}
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	public String getSelectedPaymentMethod() {
		return selectedPaymentMethod;
	}
	public void setSelectedPaymentMethod(String selectedPaymentMethod) {
		this.selectedPaymentMethod = selectedPaymentMethod;
	}
}
