package net.cafe.service;


import net.cafe.data.model.User;
import net.cafe.utils.PageBean;


public interface UserService {
	User getUser(long lcID);
	
	PageBean getAllUsers(int max, int record);
	PageBean findUser(String pattern,int max, int record);
//	User  getCurrentUser();
	User getUserForLoginByName(String userId);
	String modify(User user, User editor) throws CafeException;
	String add(User user, User editor) throws CafeException;
	String authorize(User user) throws CafeException;
	void setUserPassword(User user, String oldPassword, String newPassword) throws CafeException;
	void setUserPassword(User user, String newPassword) throws CafeException;
	void genearteUserPassword(User user)
			throws CafeException;
}
