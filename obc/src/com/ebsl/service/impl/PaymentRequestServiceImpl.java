package com.ebsl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebsl.data.dao.PaymentRequestDao;
import com.ebsl.data.model.PaymentRequest;
import com.ebsl.service.PaymentRequestService;
import com.ebsl.utils.PageBean;

@Service
@Transactional
public class PaymentRequestServiceImpl implements PaymentRequestService {

	@Autowired
	private PaymentRequestDao paymentRequestDao;
	
	@Override
	public PaymentRequest getPaymentRequest(long lcID) {
		
		return paymentRequestDao.findById(lcID);
	}

	@Override
	public PageBean getAllPaymentRequests(int max, int record) {
		return paymentRequestDao.findAll(max,record);
	}

	@Override
	public PageBean findPaymentRequests(String pattern, int max, int record) {
		return paymentRequestDao.findByFullText(pattern,max,record);
	}

}
