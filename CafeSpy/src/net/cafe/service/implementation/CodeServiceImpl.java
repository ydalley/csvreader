package net.cafe.service.implementation;


import java.util.List;

import net.cafe.data.dao.CodeDao;
import net.cafe.data.model.Code;
import net.cafe.data.model.User;
import net.cafe.service.CafeException;
import net.cafe.service.CodeService;
import net.cafe.utils.PageBean;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CodeServiceImpl implements CodeService {

	@Autowired
	private CodeDao codeDao;
	
	@Override
	public Code getCode(long id) {
		Code code = codeDao.findById(id);
		return code;
	}

	@Override
	public List<Code> getAllCodes() {
		return codeDao.findAll();
	}

	@Override
	public List<Code> getCodeByType(String type) {
		
		return codeDao.findByProperty("type", type);
	}

	@Override
	public List<Code> getCodeByName(String name) {
		return  codeDao.findByProperty("code", name);
	}

	@Override
	@Transactional
	public String modify(Code code, User editor) throws CafeException {
		// TODO Auto-generated method stub
		codeDao.update(code);
		return "";
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public String add(Code code, User editor) throws CafeException {
		try {
			codeDao.persist(code);
		} catch (Exception e) {
			throw new CafeException(e);
		}
		return "";
	}

	@Override
	public String remove(Code code, User editor) throws CafeException {
		codeDao.remove(code);
		return "";
	}

	@Override
	public List<Code> findCode(String code, String type, String desc) {
		Code c = new Code();
		if(StringUtils.isNotEmpty(code)){
			c.setCode(code);
		}
		
		if(StringUtils.isNotEmpty(type)){
			c.setType(type);
		}
		if(StringUtils.isNotEmpty(desc)){
			c.setDescription(desc);
		}
		return codeDao.findByExample(c);
	}

	@Override
	public List<Code> findCode(Code c) {
		return codeDao.findByExample(c);
	}

	@Override
	public PageBean getAllCodes(int max, int record) {
		return codeDao.findAll(max,record);
	}

	@Override
	public PageBean findCode(String pattern,int max, int record) {
		return null;
	}
	
	@Override
	public PageBean getCodeByType(String type, int max, int record) {
		Code c = new Code();
		c.setType(type);
		return codeDao.findByExample(c,max,record);
	}

	@Override
	public PageBean findCode(Code c, int max, int record) {
		return codeDao.findByExample(c,max,record);
	}
}
