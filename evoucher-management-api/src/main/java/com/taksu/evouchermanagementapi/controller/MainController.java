package com.taksu.evouchermanagementapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taksu.evouchermanagementapi.entity.EVoucher;
import com.taksu.evouchermanagementapi.service.EVoucherService;

@RestController
public class MainController {
	@Autowired
	private EVoucherService eVoucherService;
	
	@PostMapping("/evoucher/add")
	public EVoucher addEVoucher(@RequestBody EVoucher eVoucher) {
		return eVoucherService.addEVoucher(eVoucher);
	}
	
	@PutMapping("/evoucher/edit")
	public EVoucher editEVoucher(@RequestBody EVoucher eVoucher) {
		return eVoucherService.updateEVoucherById(eVoucher);
	}
	
	@PutMapping("/evoucher/setInActive")
	public EVoucher setEVoucherInActive(@RequestBody EVoucher eVoucher) {
		return eVoucherService.updateEVoucherExpDateById(eVoucher);
	}
	
}
