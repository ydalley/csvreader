package com.ebsl.actions.admin;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ebsl.actions.UserAware;
import com.ebsl.data.model.User;
import com.ebsl.service.OBCException;
import com.ebsl.service.SecurityService;
import com.ebsl.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.ExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespace("/admin/users")
@ParentPackage("obc")
@Results({ @Result(name = "error", location = "/admin/users-index.jsp") })
public class PreferencesAction extends ActionSupport implements UserAware {

	private static final Logger log = LogManager
			.getLogger(PreferencesAction.class);

	private User user;
	private Long id;

	@Autowired
	private UserService userservice;
	@Autowired
	private SecurityService securityservice;

	private Map<String, String[]> params;

	@Override
	@Action(value = "/admin/preferences/password", results = { @Result(name = "input", location = "/admin/preferences-password.jsp") })
	public String input() throws Exception {
		return "input";
	}

	@Action(value = "/admin/preferences/password-reset", results = {
			@Result(name = "input", location = "/admin/preferences-password.jsp"),
			@Result(name = "success", location = "/admin/users-index.jsp") })
	public String password() {
		try {
			userservice.setUserPassword(user, model.password, model.newPassword);
			addActionMessage("User password reset successfully");
		} catch (OBCException e) {
			// TODO Auto-generated catch block
			addActionError("Failed to reset User password");
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	private Model model;
	
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	@Override
	public void setCurrentUser(User user) {
		setUser(user);
	}

	public class Model{
		private String password;
		private String newPassword;
		private String newPassword2;
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getNewPassword() {
			return newPassword;
		}
		public void setNewPassword(String newPassword) {
			this.newPassword = newPassword;
		}
		public String getNewPassword2() {
			return newPassword2;
		}
		public void setNewPassword2(String newPassword2) {
			this.newPassword2 = newPassword2;
		}
		
	}
}
