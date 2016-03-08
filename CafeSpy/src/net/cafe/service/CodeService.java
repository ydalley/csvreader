package net.cafe.service;


import java.util.List;

import net.cafe.data.model.Code;
import net.cafe.data.model.User;
import net.cafe.utils.PageBean;



public interface CodeService {
	Code getCode(long id);
	
	List<Code> getAllCodes();
	List<Code> getCodeByType(String type);
	List<Code> getCodeByName(String name);
	List<Code> findCode(String code,String type, String desc);
	public List<Code> findCode(Code c) ;
	
	PageBean getAllCodes(int max, int record);
	PageBean getCodeByType(String type,int max, int record);
	PageBean findCode(Code c,int max, int record) ;
	PageBean findCode(String pattern,int max, int record);
	
	
	String modify(Code code, User editor) throws CafeException;
	String add(Code code, User editor) throws CafeException;
	String remove(Code code, User editor) throws CafeException;

	

	
}
