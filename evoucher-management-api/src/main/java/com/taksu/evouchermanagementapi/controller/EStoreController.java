package com.taksu.evouchermanagementapi.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taksu.evouchermanagementapi.dto.CheckDTO;
import com.taksu.evouchermanagementapi.dto.PurchaseDTO;
import com.taksu.evouchermanagementapi.entity.EVoucher;
import com.taksu.evouchermanagementapi.entity.PurchaseRequest;
import com.taksu.evouchermanagementapi.service.CustomerService;
import com.taksu.evouchermanagementapi.service.EVoucherService;
import com.taksu.evouchermanagementapi.service.PurchaseRequestService;

@RestController
public class EStoreController {
	@Autowired
	private EVoucherService eVoucherService;

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private PurchaseRequestService purchaseRequestService;

	@GetMapping("/estore/list")
	public List<String> getEVoucherList() {
		return eVoucherService.getEVoucherList();
	}

	@GetMapping("/estore/detail/{id}")
	public EVoucher getEVoucherDetail(@PathVariable int id) {
		return eVoucherService.getEVoucherDetailById(id);
	}

	@GetMapping("/estore/listdetail")
	public List<EVoucher> getAllEVouchersDetail() {
		return eVoucherService.findAll();
	}

	@PostMapping("/estore/checkout")
	public PurchaseRequest placeOrder(@RequestBody PurchaseDTO req) {
		PurchaseRequest purReq = new PurchaseRequest();
		//check if this user avaiable to buy
		if(customerService.getPurchaseLimitById(req.getCustomerId()) != 0) {
			//check purchase type (gift/consume)
			if(req.getPurchaseType().equalsIgnoreCase("GIFT") && customerService.getGiftLimitById(req.getCustomerId()) != 0) {
				purReq = purchaseRequestService.addPurchase(req);
			} else if (req.getPurchaseType().equalsIgnoreCase("CONSUME")) {
				purReq = purchaseRequestService.addPurchase(req);
			}
		}	
		return purReq;
	}
	
	@PostMapping("/estore/payment")
	public PurchaseRequest payOrder(@RequestBody int purchaseId) {
		PurchaseRequest purReq = purchaseRequestService.getDetailById(purchaseId);
		
		if(customerService.getPurchaseLimitById(purchaseId) != 0) {
			if(purReq.getPurchase_type().equalsIgnoreCase("GIFT") && customerService.getGiftLimitById(purchaseId) != 0) {
				purReq = purchaseRequestService.addPayment(purchaseId);
			} else if (purReq.getPurchase_type().equalsIgnoreCase("CONSUME")) {
				purReq = purchaseRequestService.addPayment(purchaseId);
			}
		}
		return purReq;
	}
	
	@GetMapping("/estore/purchase-detail/{voucher_code}")
	public PurchaseRequest getPurchaseDetailStatusByCode(@PathVariable String voucher_code) {
		return purchaseRequestService.getPurchaseDetailByVoucherCode(voucher_code);
	}
	
	@GetMapping("/estore/purchase-status/{voucher_code}")
	public HashMap<String,String> getPurchaseStatusStatusByCode(@PathVariable String voucher_code) {
		HashMap<String,String> result = new HashMap<String,String>();
		
		PurchaseRequest purReq = purchaseRequestService.getPurchaseDetailByVoucherCode(voucher_code);
		result.put("voucher_status", purReq.getVoucher_status());
		
		return result;
	}
	
	@GetMapping("/estore/purchase-history")
	public List<PurchaseRequest> getPurchaseUsedStatusByCustId(@RequestBody CheckDTO checkDTO) {
		List<PurchaseRequest> result = purchaseRequestService.getPurchaseUsedStatusByCheckDTO(checkDTO);
		
		return result;
	}
	
}
