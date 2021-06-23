package com.taksu.evouchermanagementapi.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.taksu.evouchermanagementapi.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	@Query(value = "SELECT gift_limit FROM taksu_db.customer WHERE id=?", nativeQuery = true)
	int getGiftLimitById(int customerId);
	
	@Query(value = "SELECT purchase_limit FROM taksu_db.customer WHERE id=?", nativeQuery = true)
	int getPurchaseLimitById(int customerId);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE taksu_db.customer SET gift_limit = gift_limit - 1, voucher_limit = voucher_limit - 1 WHERE id=?1 AND gift_limit > 0 AND voucher_limit > 0", nativeQuery = true)
	void updateGiftAndPurchaseLimitById(int customerId);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE taksu_db.customer SET voucher_limit = voucher_limit - 1 WHERE id=?1 AND gift_limit > 0 AND voucher_limit > 0", nativeQuery = true)
	void updatePurchaseLimitById(int customerId);

	Customer getDetailById(int customerId);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE taksu_db.customer SET amount_debit = ?1 WHERE id=?2 ", nativeQuery = true)
	void updateAmountDebit(Double sum, int id);

}
