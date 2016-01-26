package com.ebsl.service;


import com.ebsl.data.model.User;
import com.ebsl.utils.PageBean;


public interface UserService {
	User getUser(long lcID);
	User authenticate(String user, String password) throws OBCException;
	
	PageBean getAllUsers(int max, int record);
	PageBean findUser(String pattern,int max, int record);
	User  getCurrentUser();
	User getUserForLoginByName(String userId);
	String modify(User user, User editor) throws OBCException;
	String add(User user, User editor) throws OBCException;
	String authorize(User user) throws OBCException;
	void setUserPassword(User user, String oldPassword, String newPassword) throws OBCException;
	void setUserPassword(User user, String newPassword) throws OBCException;
	void genearteUserPassword(User user)
			throws OBCException;
}
