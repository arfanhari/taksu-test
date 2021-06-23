package com.taksu.evouchermanagementapi.service;

import java.util.ArrayList;
import java.util.Date;
import java.time.Instant;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taksu.evouchermanagementapi.entity.EVoucher;
import com.taksu.evouchermanagementapi.repository.EVoucherRepo;

@Transactional
@Service
public class EVoucherService {
	@Autowired
	private EVoucherRepo eVoucherRepo;
	
	public EVoucher addEVoucher(EVoucher eVoucher) {
		return eVoucherRepo.save(eVoucher);
	}
	
	public EVoucher getProductById(int id) {
        return eVoucherRepo.findById(id).orElse(null);
    }
	
	public EVoucher updateEVoucherById(EVoucher eVoucher) {
		EVoucher productUpdate = eVoucherRepo.findById(eVoucher.getId()).orElse(null);
		productUpdate.setId(eVoucher.getId());
		productUpdate.setTitle(eVoucher.getTitle());
		productUpdate.setDescription(eVoucher.getDescription());
		productUpdate.setExpired_date(eVoucher.getExpired_date());
		productUpdate.setAmount(eVoucher.getAmount());
		productUpdate.setDiscounted_payment_method(eVoucher.getDiscounted_payment_method());
		productUpdate.setQuantity(eVoucher.getQuantity());
        return eVoucherRepo.save(productUpdate);
    }

	public EVoucher updateEVoucherExpDateById(EVoucher eVoucher) {
		EVoucher existingProduct = eVoucherRepo.findById(eVoucher.getId()).orElse(null);
		Instant yesterday = Instant.now();
		Date newDate =  Date.from(yesterday);
		
        existingProduct.setExpired_date(newDate);
        return eVoucherRepo.save(existingProduct);
    }

	public List<EVoucher> findAll() {
		return eVoucherRepo.findAll();
	}

	public EVoucher getEVoucherDetailById(int id) {
		return eVoucherRepo.getEVoucherDetailById(id);
	}

	public List<String> getEVoucherList() {
		List<EVoucher> eVoucherList = eVoucherRepo.findAll();
		List<String> titles = new ArrayList<String>();
		for (int i = 0; i < eVoucherList.size(); i++) {
			titles.add(eVoucherList.get(i).getTitle());
		}
		return titles;
	}

}
