package com.ebsl.service.impl;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.apache.catalina.realm.RealmBase;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import com.ebsl.data.dao.UserDao;
import com.ebsl.data.model.User;
import com.ebsl.service.OBCException;
import com.ebsl.service.UserService;
import com.ebsl.utils.BeanUtilsBean;
import com.ebsl.utils.PageBean;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	private static final Logger log = LogManager
			.getLogger(UserServiceImpl.class);
	@Autowired
	private UserDao userDao;

	@Override
	public User getUser(long id) {
		return userDao.findById(id);
	}

	@Override
	public PageBean findUser(String pattern, int max, int record) {
		return userDao.findByFullText(pattern, max, record);
	}

	@Override
	public User authenticate(String user, String password) throws OBCException {
		// TODO Auto-generated method stub
		List<User> list = userDao.findByProperty("loginId", user.trim()
				.toUpperCase());
		if (!list.isEmpty()) {
			User user2 = list.get(0);
			String encrypt = apacheEncrypt(password, user);
			if (encrypt.equals(user2.getPassword())) {
				return user2;
			}
		}
		return null;
	}

	public String generateSHAdigest(String plaintext) throws OBCException {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new OBCException(e);
		}
		byte[] md_password = plaintext.getBytes();
		byte[] md_hash = md.digest(md_password);
		String bytes = "";
		for (byte by : md_hash) {
			bytes += String.format("%02x", by);
		}
		return bytes;
	}

	@Override
	public PageBean getAllUsers(int max, int record) {
		return userDao.findAll(max, record);
	}

	@Override
	public User getUserForLoginByName(String userId) {
		return userDao.getActiveUserByName(userId);
	}

	@Override
	public String modify(User user, User editor) throws OBCException {
		User user2 = userDao.findById(user.getId());
		try {
			BeanUtilsBean.getInstance().copyProperties(user2, user);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userDao.update(user2);
		return "";
	}


	@Override
	@Transactional
	public String add(User user, User editor) throws OBCException {
		userDao.persist(user);
		return "";
	}

	@Override
	public String authorize(User user) throws OBCException {
		// TODO Auto-generated method stub
		return null;
	}

	private synchronized String apacheEncrypt(String plaintext, String seed)
			throws OBCException {
		String digest = RealmBase.Digest(plaintext, "SHA", "UTF-8");
		return digest;
	}

	private synchronized String encrypt(String plaintext, String seed)
			throws OBCException {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
			throw new OBCException(e.getMessage());
		}
		try {
			md.update(plaintext.getBytes("UTF-8"));
			// md.update(seed.toLowerCase().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new OBCException(e.getMessage());
		}
		byte raw[] = md.digest();
		// String hash = (new BASE64Encoder()).encode(raw);
		String hash = "";
		try {
			hash = new String(Base64Utils.encode(raw), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hash;
	}

	public static void main(String[] args) throws OBCException {
		UserServiceImpl u = new UserServiceImpl();
		String encrypt = u.encrypt("yemi", "YDALLEY");
		//String encrypt2 = u.apacheEncrypt("yemi", "YDALLEY");
		String encrypt3 = u.generateSHAdigest("yemi");
		System.out.println(encrypt);
		//System.out.println(encrypt2);
		System.out.println(encrypt3);
		
		//5e77a9dc45f2cf7b9807ff2b927e0041c9b4644ed6f31169d6602b0c6a8606f0

	}

	@Override
	public void setUserPassword(User user, String oldPassword,
			String newPassword) throws OBCException {
		if (validatePassword(user, oldPassword, newPassword)) {
			String passwd = generateSHAdigest(newPassword);
			user = userDao.findById(user.getId());
			user.setPassword(passwd);
			user.setExpiryDate(passwordExpiryDate());
			userDao.update(user);
		}

	}

	@Override
	public void setUserPassword(User user, String newPassword)
			throws OBCException {
		String passwd = generateSHAdigest(newPassword);
		user.setPassword(passwd);
		user.setExpiryDate(new Date());
		userDao.update(user);
	}

	@Override
	public void genearteUserPassword(User user) throws OBCException {
		String randomPassword = generateRandomPassword();
		log.debug("setting password for " + user + " to [" + randomPassword
				+ "]");
		setUserPassword(user, randomPassword);
	}

	private String generateRandomPassword() {
		String alphanumeric = RandomStringUtils.randomAlphanumeric(8);
		return alphanumeric;
	}

	private boolean validatePassword(User user, String oldPassword,
			String newPassword) throws OBCException {
		return true;
	}

	private Date passwordExpiryDate() {
		return DateUtils.addDays(new Date(), 30);
	}
}
