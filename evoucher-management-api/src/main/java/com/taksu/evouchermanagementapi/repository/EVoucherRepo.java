package com.taksu.evouchermanagementapi.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.taksu.evouchermanagementapi.entity.EVoucher;

public interface EVoucherRepo extends JpaRepository<EVoucher, Integer>{

	EVoucher getEVoucherDetailById(int id);

	@Transactional
	@Modifying
	@Query(value = "UPDATE taksu_db.e_voucher SET quantity = quantity - 1 WHERE id=?1 ", nativeQuery = true)
	void updateQuantity(int id);
}
