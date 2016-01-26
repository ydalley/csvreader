package com.ebsl.service;


import com.ebsl.data.model.PaymentRequest;
import com.ebsl.utils.PageBean;


public interface PaymentRequestService {
	PaymentRequest getPaymentRequest(long lcID);
	
	PageBean getAllPaymentRequests(int max, int record);
	PageBean findPaymentRequests(String pattern,int max, int record);
	
}
