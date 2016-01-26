package com.ebsl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebsl.data.dao.OptionDao;
import com.ebsl.data.dao.ProfileDao;
import com.ebsl.data.model.Option;
import com.ebsl.data.model.Profile;
import com.ebsl.data.model.User;
import com.ebsl.service.OBCException;
import com.ebsl.service.SecurityService;
import com.ebsl.utils.PageBean;

@Service
@Transactional
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	private ProfileDao profileDao;
	@Autowired
	private OptionDao optionDao;
	
	@Override
	public Profile getProfile(long lcID) {
		return profileDao.findById(lcID);
	}

	@Override
	public PageBean getAllProfiles(int max, int record) {
		return profileDao.findAll(max, record);
	}


	@Override
	public String modify(Profile profile, User editor) throws OBCException {
		profileDao.update(profile);
		return "";
	}

	@Override
	public String add(Profile profile, User editor) throws OBCException {
		profileDao.persist(profile);
		return null;
	}

	@Override
	public List<Profile> getAllProfiles() {
		return profileDao.findAll();
	}
///options
	
	@Override
	public Option getOption(long lcID) {
		return optionDao.findById(lcID);
	}

	@Override
	public PageBean getAllOptions(int max, int record) {
		return optionDao.findAll(max, record);
	}


	@Override
	public String modify(Option option, User editor) throws OBCException {
		optionDao.update(option);
		return "";
	}

	@Override
	public String add(Option option, User editor) throws OBCException {
		optionDao.persist(option);
		return null;
	}

	@Override
	public List<Option> getAllOptions() {
		return optionDao.findAll();
	}
	
	@Override
	public List<Option> getAllOtherOptions(Profile profile) {
		return optionDao.findOtherOptions(profile);
	}
	
	@Override
	public Profile loadProfile(Long id) {
		 return profileDao.loadProfile(id);
	}

	@Override
	public List getOptionDesc(String[] options) {
		// TODO Auto-generated method stub
		return optionDao.getOptionDesc(options);
	}
}
