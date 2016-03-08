package net.cafe.actions;


import java.util.Map;

import net.cafe.data.model.Profile;
import net.cafe.data.model.User;
import net.cafe.service.SecurityService;
import net.cafe.service.UserService;
import net.cafe.utils.PageBean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
@ParentPackage("cafe")
@Results({ @Result(name = "error", location = "/users-index.jsp") })
public class UsersAction extends ActionSupport {

	private static final Logger log = LogManager.getLogger(UsersAction.class);


	@Autowired
	private UserService userservice;
	@Autowired
	private SecurityService securityservice;

	private Map<String, String[]> params;

	private User currentUser;


	// GET /users/
	@Override
	@Action(value = "/users-index",results = {@Result(name="index",location="/users-index.jsp")})	
	public String execute() throws Exception {
		return "index";
	}

}
