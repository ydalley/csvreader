package com.ebsl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebsl.data.dao.ConfigDao;
import com.ebsl.data.model.Config;
import com.ebsl.data.model.User;
import com.ebsl.service.ConfigurationService;
import com.ebsl.service.OBCException;
import com.ebsl.utils.PageBean;

@Service
@Transactional
public class ConfigurationServiceImpl implements ConfigurationService {

	@Autowired
	private ConfigDao configDao;

	@Override
	public Config getConfig(long id) {
		return configDao.findById(id);
	}

	@Override
	public List<Config> getConfigs() {
		return configDao.findAll();
	}

	@Override
	public List<Config> getConfigByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageBean getAllConfigs(int max, int record) {
		return configDao.findAll(max, record);
	}

	@Override
	public String modify(Config config, User editor) throws OBCException {
		configDao.update(config);
		return "";
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public String add(Config config, User editor) throws OBCException {
		try {
			configDao.persist(config);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new OBCException(e);
		}
		return "";
	}

	@Override
	public String disable(Config config, User editor) throws OBCException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
