package com.ebsl.service;

import java.util.List;

import com.ebsl.data.model.Dispute;
import com.ebsl.data.model.User;
import com.ebsl.utils.PageBean;


public interface DisputeService {
	Dispute getDispute(long id);
	
	List<Dispute> getAllDispute();
	List<Dispute> getDisputeByMerchant(String id);
	List<Dispute> getDisputeByTransaction(String tranId);
	public List<Dispute> findDispute(Dispute c) ;
	
	PageBean getAllDispute(int max, int record);
	PageBean findDisputeByMerchant(String id,int max, int record);
	PageBean findDispute(Dispute c,int max, int record) ;
	PageBean findDispute(String pattern,int max, int record);
	
	
	String modify(Dispute code, User editor) throws OBCException;
	String add(Dispute dispute, User editor) throws OBCException;
	String approve(Dispute dispute, User editor) throws OBCException;
	String resolve(Dispute dispute, User editor) throws OBCException;
	String remove(Dispute code, User editor) throws OBCException;

	

	
}
