package com.ebsl.service;

import java.util.List;

import com.ebsl.data.model.Config;
import com.ebsl.data.model.User;
import com.ebsl.utils.PageBean;


public interface ConfigurationService {
	Config getConfig(long id);
	
	List<Config> getConfigs();
	List<Config> getConfigByName(String name);
	
	PageBean getAllConfigs(int max, int record);
	
	String modify(Config config, User editor) throws OBCException;
	String add(Config config, User editor) throws OBCException;
	String disable(Config config, User editor) throws OBCException;

	

	
}
