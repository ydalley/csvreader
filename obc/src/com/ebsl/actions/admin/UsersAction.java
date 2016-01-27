package com.ebsl.actions.admin;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ParameterAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ebsl.actions.UserAware;
import com.ebsl.data.model.Profile;
import com.ebsl.data.model.User;
import com.ebsl.service.OBCException;
import com.ebsl.service.SecurityService;
import com.ebsl.service.UserService;
import com.ebsl.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Controller
@Scope("prototype")
@Namespace("/admin/users")
@ParentPackage("obc")
@Results({ @Result(name = "error", location = "/admin/users-index.jsp") })
public class UsersAction extends ActionSupport implements Preparable,
		ParameterAware , UserAware{

	private static final Logger log = LogManager.getLogger(UsersAction.class);

	private User user;
	private Long id;
	private boolean setPasswordAndSend;
	private Collection<User> users;
	private List<Profile> profiles;
	private PageBean pagebean;

	@Autowired
	private UserService userservice;
	@Autowired
	private SecurityService securityservice;

	private Map<String, String[]> params;

	private User currentUser;

	// GET /users/1
	@Action(value = "/admin/users/show",results = {@Result(name="show",location="/admin/users-show.jsp")})
	public String show() {
		return "show";
	}

	// GET /users/
	@Action(value = "/admin/users/index",results = {@Result(name="index",location="/admin/users-index.jsp")})	
	public String execute() throws Exception {
		return "index";
	}

	@Actions({
			@Action(value = "/admin/usersj", results = { @Result(name = "index", type = "json", params = {
					"root", "pagebean", "excludeProperties",
					"data\\[\\d+\\]\\.(password|version|profile)" }) }, interceptorRefs = { @InterceptorRef(value = "json") }),
			@Action(value = "/usersj", results = { @Result(name = "index", type = "json", params = {
					"root", "pagebean", "excludeProperties",
					"data\\[\\d+\\]\\.(password|version|profile)" }) }, interceptorRefs = { @InterceptorRef(value = "json") }) })
	public String index() throws Exception {
		int draw = 1;
		int start = 0;
		int length = -1;

		draw = NumberUtils.createInteger(ServletActionContext.getRequest()
				.getParameter("draw"));
		length = NumberUtils.createInteger(ServletActionContext.getRequest()
				.getParameter("length"));
		start = NumberUtils.createInteger(ServletActionContext.getRequest()
				.getParameter("start"));
		String search = ServletActionContext.getRequest()
				.getParameter("search[value]");

		if (StringUtils.isEmpty(search)) {
			pagebean = userservice.getAllUsers(length, start);
		} else {
			pagebean = userservice.findUser(search, length, start);
		}
		pagebean.setDraw(draw);
		return "index";
	}

	// GET /users/1/edit
	@RequiresPermissions(value="update:user")
	@Action(value = "/admin/users/edit",results = {@Result(name="edit",location="/admin/users-edit.jsp")})
	public String edit() {
		return "edit";
	}

	// GET /users/new
	@RequiresPermissions(value="create:user")
	@Action(value = "/admin/users/new",results = {@Result(name="input",location="/admin/users-editNew.jsp")})
	public String editNew() {
		user = new User();
		return "input";
	}

	// GET /users/1/deleteConfirm
	public String deleteConfirm() {
		return "deleteConfirm";
	}

	// DELETE /users/1
	public String destroy() {
		// log.debug("Delete order with id: {}", id);
		// ordersService.remove(id);
		// addActionMessage("Order removed successfully");
		return "success";
	}

	@Action(value = "/admin/users/create",results = {@Result(name="input",location="/admin/users-editNew.jsp"),
			@Result(name="success",location="/admin/users-index.jsp")})
	@RequiresPermissions(value="create:user")
	@Validations(
            requiredStrings ={
            		@RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "user.firstName", message = "You must enter a value for First Name."),
            		@RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "user.lastName", message = "You must enter a value for Last Name."),
            		@RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "user.loginId", message = "You must enter a value for Login ID.")},
            emails =
                    { @EmailValidator(type = ValidatorType.SIMPLE, fieldName = "user.email", message = "You must enter a value for email.")}
           
    )
	public String create() {
		log.debug("Create new user {}", user);
		try {
			userservice.add(user, currentUser);
			addActionMessage("New User created successfully");
			return "success";
		} catch (OBCException e) {
			e.printStackTrace();
			addActionError("Error creating User:" + e.getMessage());
		}
		
		return "input";

	}

	@Validations(
            requiredStrings ={
            		@RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "user.firstName", message = "You must enter a value for First Name."),
            		@RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "user.lastName", message = "You must enter a value for Last Name."),
            		@RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "user.loginId", message = "You must enter a value for Login ID.")},
            emails =
                    { @EmailValidator(type = ValidatorType.SIMPLE, fieldName = "user.email", message = "You must enter a value for email.")}
           
    )
	@Action(value = "/admin/users/update",results = {@Result(name="input",location="/admin/users-edit.jsp"),
			@Result(name="success",location="/admin/users-index.jsp")})
	@RequiresPermissions(value="update:user")
	public String update() {
		try {
			userservice.modify(user, currentUser);
			addActionMessage("User updated successfully");
		} catch (OBCException e) {
			// TODO Auto-generated catch block
			addActionError("Failed to update User");
			e.printStackTrace();
			return "input";
		}
		return "success";
	}
	

	// PUT /orders/1
	@Action(value = "/admin/users/enable",results = {@Result(name="error",location="/admin/users-index.jsp"),
			@Result(name="success",location="/admin/users-index.jsp")})
	@RequiresPermissions(value="enable:user")
	
	public String enable() {
		try {
			String status = user.getStatus();
			if(StringUtils.isEmpty(status)){
				status = "D";
			}
			else if(status.equals("E")){
				status = "D";
			}else if(status.equals("D")){
				status = "E";
			}
			user.setStatus(status);
			userservice.modify(user, currentUser);
			addActionMessage("User enabled successfully");
		} catch (OBCException e) {
			// TODO Auto-generated catch block
			addActionError("Failed to enable User");
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	// PUT /orders/1
		@Action(value = "/admin/users/password",results = {@Result(name="error",location="/admin/users-index.jsp"),
				@Result(name="success",location="/admin/users-index.jsp")})
		@RequiresPermissions(value="password:user")
		public String password() {
			try {
				
				userservice.genearteUserPassword(user);
				addActionMessage("User password set successfully");
			} catch (OBCException e) {
				// TODO Auto-generated catch block
				addActionError("Failed to set User password");
				e.printStackTrace();
				return "error";
			}
			return "success";
		}
		
	@Override
	public void prepare() throws Exception {
		profiles = securityservice.getAllProfiles();
		String[] strings = params.get("id");
		if (strings != null && strings.length > 0) {
			id = NumberUtils.createLong(strings[0]);
			user = userservice.getUser(id);
		}

	}

	public void preparePassword() throws Exception {
		String[] strings = params.get("send");
		if (strings != null && strings.length > 0) {
			setPasswordAndSend = BooleanUtils.toBoolean(strings[0]);
		}
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public List<Profile> getProfiles() {
		return profiles;
	}

	@Override
	public void setParameters(Map<String, String[]> parameters) {
		params = parameters;
	}

	public PageBean getPagebean() {
		return pagebean;
	}

	@Override
	public void setCurrentUser(User user) {
		currentUser = user;
	}

}
