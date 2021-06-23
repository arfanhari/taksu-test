package com.taksu.evouchermanagementapi.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.taksu.evouchermanagementapi.entity.PurchaseRequest;

public interface PurchaseRequestRepo extends JpaRepository<PurchaseRequest, Integer>{

	PurchaseRequest getDetailById(int purchaseId);
	
	@Query(value = "SELECT * FROM taksu_db.purchase_request WHERE voucher_code=?", nativeQuery = true)
	PurchaseRequest getPurchaseDetailByVoucherCode(String voucher_code);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE taksu_db.purchase_request SET purchase_status = 'paid', voucher_status='unused', voucher_code=?1, voucher_qrcode_path=?2 WHERE id=?3 ", nativeQuery = true)
	void updateEVoucherDetails(String voucherCode, String voucherQRCode, int purchaseId);
	
	@Query(value = "SELECT * FROM taksu_db.purchase_request WHERE customer_id=?1 AND voucher_status=?2", nativeQuery = true)
	List<PurchaseRequest> getPurchaseUsedStatusByCheckDTO(int customer_id, String voucher_status);
	
}
