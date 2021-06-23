package com.taksu.evouchermanagementapi.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taksu.evouchermanagementapi.entity.Customer;
import com.taksu.evouchermanagementapi.repository.CustomerRepo;

@Transactional
@Service
public class CustomerService {
	@Autowired
	private CustomerRepo customerRepo;

	public Customer getCustById(int custId) {
		return customerRepo.findById(custId).orElse(null);
	}

	public int getGiftLimitById(int customerId) {
		return customerRepo.getGiftLimitById(customerId);
	}

	public int getPurchaseLimitById(int customerId) {
		return customerRepo.getPurchaseLimitById(customerId);
	}
}
