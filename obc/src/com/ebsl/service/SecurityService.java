package com.ebsl.service;

import java.util.List;

import com.ebsl.data.model.Option;
import com.ebsl.data.model.Profile;
import com.ebsl.data.model.User;
import com.ebsl.utils.PageBean;

public interface SecurityService {
	Profile getProfile(long lcID);
	List<Profile> getAllProfiles();
	String modify(Profile profile, User editor) throws OBCException;
	String add(Profile profile, User editor) throws OBCException;
	PageBean getAllProfiles(int max, int record);
	Option getOption(long lcID);
	PageBean getAllOptions(int max, int record);
	String modify(Option option, User editor) throws OBCException;
	String add(Option option, User editor) throws OBCException;
	List<Option> getAllOptions();
	Profile loadProfile(Long id);
	List<Option> getAllOtherOptions(Profile profile);
	List getOptionDesc(String[] options);
}
