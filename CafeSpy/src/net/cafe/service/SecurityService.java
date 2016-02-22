package net.cafe.service;

import java.util.List;

import net.cafe.data.model.Option;
import net.cafe.data.model.Profile;
import net.cafe.data.model.User;
import net.cafe.utils.PageBean;

public interface SecurityService {
	Profile getProfile(long lcID);
	List<Profile> getAllProfiles();
	String modify(Profile profile, User editor) throws CafeException;
	String add(Profile profile, User editor) throws CafeException;
	PageBean getAllProfiles(int max, int record);
	Option getOption(long lcID);
	PageBean getAllOptions(int max, int record);
	String modify(Option option, User editor) throws CafeException;
	String add(Option option, User editor) throws CafeException;
	List<Option> getAllOptions();
	Profile loadProfile(Long id);
	List<Option> getAllOtherOptions(Profile profile);
	List getOptionDesc(String[] options);
	
	PageBean findOption(String pattern,int max, int record);
}
