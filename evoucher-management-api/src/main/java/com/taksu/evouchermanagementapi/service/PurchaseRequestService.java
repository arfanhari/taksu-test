package com.taksu.evouchermanagementapi.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.taksu.evouchermanagementapi.dto.CheckDTO;
import com.taksu.evouchermanagementapi.dto.PurchaseDTO;
import com.taksu.evouchermanagementapi.entity.Customer;
import com.taksu.evouchermanagementapi.entity.EVoucher;
import com.taksu.evouchermanagementapi.entity.PurchaseRequest;
import com.taksu.evouchermanagementapi.repository.CustomerRepo;
import com.taksu.evouchermanagementapi.repository.EVoucherRepo;
import com.taksu.evouchermanagementapi.repository.PurchaseRequestRepo;

@Transactional
@Service
public class PurchaseRequestService {
	@Autowired
	private CustomerRepo custRepo;
	
	@Autowired
	private EVoucherRepo eVoucherRepo;
	
	@Autowired
	private PurchaseRequestRepo purchaseRequestRepo;
	
	public PurchaseRequest addPurchase(PurchaseDTO req) {
		Customer customer = custRepo.getDetailById(req.getCustomerId());
		
		EVoucher eVoucher = eVoucherRepo.getEVoucherDetailById(req.geteVoucherId());
		
		PurchaseRequest purchaseReq = new PurchaseRequest();
		purchaseReq.setPurchase_status("unpaid");
		purchaseReq.setPicked_payment_method(req.getSelectedPaymentMethod());
		
		if (req.getSelectedPaymentMethod().equalsIgnoreCase("VISA")) {
			purchaseReq.setAmount(eVoucher.getAmount() - (eVoucher.getAmount() * 10 / 100));
		} else {
			purchaseReq.setAmount(eVoucher.getAmount());
		}
		purchaseReq.setPurchase_type(req.getPurchaseType());
		purchaseReq.seteVoucher(eVoucher);
		purchaseReq.setCustomer(customer);
		
		purchaseRequestRepo.save(purchaseReq);
		
		return purchaseReq;
	}
	

	public PurchaseRequest getDetailById(int purchaseId) {
		return purchaseRequestRepo.getDetailById(purchaseId);
	}
	
	public PurchaseRequest getPurchaseDetailByVoucherCode(String voucher_code) {
		return purchaseRequestRepo.getPurchaseDetailByVoucherCode(voucher_code);
	}
	
	public PurchaseRequest addPayment(int purId) {
		PurchaseRequest purchaseRequest = purchaseRequestRepo.getDetailById(purId);
		Customer customer = purchaseRequest.getCustomer();
		EVoucher eVoucher = purchaseRequest.geteVoucher();
		
		PurchaseRequest payReq = new PurchaseRequest();
		payReq.seteVoucher(eVoucher);
		payReq.setCustomer(customer);
		if(eVoucher.getQuantity() > 0) {
			
			if(purchaseRequest.getPicked_payment_method().equalsIgnoreCase("DEBIT")){
				if(customer.getAmount_debit() > purchaseRequest.getAmount()) {
					//update quantity -1
					eVoucherRepo.updateQuantity(eVoucher.getId());
					
					//update debit
					Double sum  = customer.getAmount_debit() - purchaseRequest.getAmount();
					custRepo.updateAmountDebit(sum, customer.getId());
					
					customer.setAmount_debit(sum);
					payReq.setPurchase_status("paid");
					payReq.setVoucher_code(generateEVoucherCodeAndQRAndUpdate(
												Integer.valueOf(
														customer.getPhone_number().substring((customer.getPhone_number().length()-2),customer.getPhone_number().length())),
												purId));
					
				}
				System.out.println("your debit amount is not enough");
			} else {
				eVoucherRepo.updateQuantity(eVoucher.getId());
				
				//connect to payment visa with this amount
				Double amount = purchaseRequest.getAmount();
				payReq.setPurchase_status("paid");
				payReq.setVoucher_code(generateEVoucherCodeAndQRAndUpdate(
						Integer.valueOf(
								customer.getPhone_number().substring((customer.getPhone_number().length()-2),customer.getPhone_number().length())),
						purId));
			}
		}
		

		return payReq;
	}


	private String generateEVoucherCodeAndQRAndUpdate(int phoneTwoLastNum, int purchaseId) {
		String strPurId = "";
		if(purchaseId < 99) {
			strPurId = String.format("%04d", purchaseId);
		}
		String rndStr = getAlphaNumericString(5);
		
		//6Num 5Ch
		String voucherCode = rndStr + String.valueOf(phoneTwoLastNum)+strPurId;

		String path = "C:\\Users\\Arfan\\Desktop\\QR IMAGE\\"+voucherCode+".jpg";
		
		try {
			BitMatrix matrix = null;
			try {
				matrix = new MultiFormatWriter().encode(voucherCode, BarcodeFormat.QR_CODE, 250, 250);
			} catch (WriterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String voucherQRCode = path;
		
		purchaseRequestRepo.updateEVoucherDetails(voucherCode, voucherQRCode, purchaseId);
		
		return voucherCode;
	}

	public String getAlphaNumericString(int n) {

		// length is bounded by 256 Character
		byte[] array = new byte[256];
		new Random().nextBytes(array);

		String randomString= new String(array, Charset.forName("UTF-8"));

		// Create a StringBuffer to store the result
		StringBuffer r = new StringBuffer();

		// remove all spacial char
		String AlphaString = randomString.replaceAll("[^A-Za-z]", "");

		// Append first 20 alphanumeric characters
		// from the generated random String into the result
		for (int k = 0; k < AlphaString.length(); k++) {

			if (Character.isLetter(AlphaString.charAt(k))
					&& (n > 0)
				|| Character.isDigit(AlphaString.charAt(k))
					&& (n > 0)) {

				r.append(AlphaString.charAt(k));
				n--;
			}
		}

		// return the resultant string
		return r.toString();
	}

	public List<PurchaseRequest> getPurchaseUsedStatusByCheckDTO(CheckDTO checkDTO) {
		int customer_id = checkDTO.getCustomer_id();
		String voucher_status = checkDTO.getVoucher_status();
		return purchaseRequestRepo.getPurchaseUsedStatusByCheckDTO(customer_id, voucher_status);
	}


}
