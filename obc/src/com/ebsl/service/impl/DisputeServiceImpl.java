package com.ebsl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebsl.data.dao.DisputeDao;
import com.ebsl.data.model.Dispute;
import com.ebsl.data.model.User;
import com.ebsl.service.DisputeService;
import com.ebsl.service.OBCException;
import com.ebsl.utils.PageBean;

@Service
@Transactional
public class DisputeServiceImpl implements DisputeService {

	@Autowired
	DisputeDao disputedao;
	
	@Override
	public Dispute getDispute(long id) {
		// TODO Auto-generated method stub
		return disputedao.findById(id);
	}

	@Override
	public List<Dispute> getAllDispute() {
		// TODO Auto-generated method stub
		return disputedao.findAll();
	}

	@Override
	public List<Dispute> getDisputeByMerchant(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dispute> getDisputeByTransaction(String tranId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dispute> findDispute(Dispute c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageBean getAllDispute(int max, int record) {
		// TODO Auto-generated method stub
		return disputedao.findAll(max, record);
	}

	@Override
	public PageBean findDisputeByMerchant(String id, int max, int record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageBean findDispute(Dispute c, int max, int record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageBean findDispute(String pattern, int max, int record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String modify(Dispute dispute, User editor) throws OBCException {
		// TODO Auto-generated method stub
		disputedao.update(dispute);
		return "";
	}

	@Override
	public String add(Dispute dispute, User editor) throws OBCException {
		// TODO Auto-generated method stub
		disputedao.persist(dispute);
		return "";
	}

	@Override
	public String approve(Dispute dispute, User editor) throws OBCException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String resolve(Dispute dispute, User editor) throws OBCException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String remove(Dispute code, User editor) throws OBCException {
		// TODO Auto-generated method stub
		return null;
	}

}
