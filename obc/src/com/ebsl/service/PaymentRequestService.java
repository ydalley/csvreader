package com.ebsl.service;

import java.util.Date;

import com.ebsl.data.model.PaymentRequest;
import com.ebsl.utils.PageBean;

public interface PaymentRequestService {
	PaymentRequest getPaymentRequest(long lcID);

	PageBean getAllPaymentRequests(int max, int record);

	PageBean findPaymentRequests(String pattern, int max, int record);

	public PageBean findByExample(PaymentRequest entity, Double amountMax, Date start, Date end,
			int max, int record);

}
