package com.ebsl.actions;

import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.ebsl.data.model.User;
import com.ebsl.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class Login extends ActionSupport implements SessionAware,ParameterAware {

	
	private Map<String, Object> session;
	@Autowired
	private UserService userservice;
	private Map<String, String[]> params;
	private String loginid;
	private String password;

	@Override
	public void setSession(Map<String, Object> arg0) {
		session = arg0;
	}

	@Override
	public void setParameters(Map<String, String[]> arg0) {
		params = arg0;
	}
	
	@Override
	public String execute() throws Exception {
		User user = userservice.authenticate(loginid, password);
		if(user == null){
			addActionError("Invalid User id");
			return LOGIN;
		}else{
			session.put(User.USER, user);
		}
		return SUCCESS;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	 
	
}
