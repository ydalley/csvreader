package net.cafe.service.implementation;

import java.util.List;

import net.cafe.data.dao.OptionDao;
import net.cafe.data.dao.ProfileDao;
import net.cafe.data.model.Option;
import net.cafe.data.model.Profile;
import net.cafe.data.model.User;
import net.cafe.service.CafeException;
import net.cafe.service.SecurityService;
import net.cafe.utils.PageBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
	public String modify(Profile profile, User editor) throws CafeException {
		profileDao.update(profile);
		return "";
	}

	@Override
	public String add(Profile profile, User editor) throws CafeException {
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
	public String modify(Option option, User editor) throws CafeException {
		optionDao.update(option);
		return "";
	}

	@Override
	public String add(Option option, User editor) throws CafeException {
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

	@Override
	public PageBean findOption(String pattern, int max, int record) {
		return null;
	}
}
