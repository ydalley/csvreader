package com.ebsl.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
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
		return paymentRequestDao.findAll(max, record);
	}

	@Override
	public PageBean findPaymentRequests(String pattern, int max, int record) {
		return paymentRequestDao.findByFullText(pattern, max, record);
	}

	@Override
	public PageBean findByExample(PaymentRequest entity, Double paymentUpper, Date start, Date end,
			 int record,int max) {
		Date datel, dateh;
		if (start == null && end == null) {
			return paymentRequestDao.findByExample(entity,paymentUpper ,record,max);
		} else if (start == null) {
			datel = DateUtils.ceiling(end, Calendar.HOUR);
			dateh = DateUtils.ceiling(DateUtils.addDays(end, 1), Calendar.HOUR);
		} else if (end == null) {
			datel = DateUtils.ceiling(start, Calendar.HOUR);
			dateh = DateUtils.ceiling(DateUtils.addDays(start, 1),
					Calendar.HOUR);
		} else {
			datel = DateUtils.ceiling(start, Calendar.HOUR);
			dateh = DateUtils.ceiling(DateUtils.addDays(end, 1), Calendar.HOUR);
		}
		return paymentRequestDao.findByExample(entity,paymentUpper ,datel, dateh, record, max );
	}
	
}
