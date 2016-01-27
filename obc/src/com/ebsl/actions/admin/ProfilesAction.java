package com.ebsl.actions.admin;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;

import com.ebsl.data.model.Code;
import com.ebsl.data.model.Option;
import com.ebsl.data.model.Profile;
import com.ebsl.service.CodeService;
import com.ebsl.service.OBCException;
import com.ebsl.service.SecurityService;
import com.ebsl.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@Controller
@Scope(value="prototype",proxyMode=ScopedProxyMode.TARGET_CLASS)
@Namespace("/admin/security")
@ParentPackage("obc")
@Results({ @Result(name = "error", location = "/admin/profiles-index.jsp") })
public class ProfilesAction extends ActionSupport implements Preparable,
		ParameterAware {

	private static final Logger log = LogManager
			.getLogger(ProfilesAction.class);

	private Profile profile;
	private Long id;
	private Collection<Profile> profileList;
	private PageBean pagebean;

	@Autowired
	private SecurityService securityservice;
	private Map<String, String[]> params;
	private List<Option> optionsList;

	// GET /codes/edit?1d=1
	@Action(value = "/admin/security/show", results = { @Result(name = "show", location = "/admin/profiles-show.jsp") })
	public String show() {
		return "show";
	}

	@Action(value = "/admin/security/index", results = { @Result(name = "index", location = "/admin/profiles-index.jsp") })
	public String execute() throws Exception {

		return "index";
	}

	@Actions({
			@Action(value = "/admin/profilesj", results = { @Result(name = "index", type = "json", params = {
					"root", "pagebean" ,"excludeProperties",
					"data\\[\\d+\\]\\.options"}) }, interceptorRefs = { @InterceptorRef(value = "json") }),
			@Action(value = "/profilesj", results = { @Result(name = "index", type = "json", params = {
					"root", "pagebean", "excludeProperties",
					"data\\[\\d+\\]\\.options" }) }, interceptorRefs = { @InterceptorRef(value = "json") }) })
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

		pagebean = securityservice.getAllProfiles(length, start);
		pagebean.setDraw(draw);

		return "index";
	}

	// GET /codes/edit?1d=1
	@Action(value = "/admin/security/edit", results = { @Result(name = "edit", location = "/admin/profiles-edit.jsp") })
	public String edit() {
		return "edit";
	}

	// GET /codes/new
	@Action(value = "/admin/security/new", results = { @Result(name = "input", location = "/admin/profiles-editNew.jsp") })
	public String editNew() {
		profile = new Profile();
		return "input";
	}

	

	@Action(value = "/admin/security/create", results = {
			@Result(name = "error", location = "/admin/profiles-editNew.jsp"),
			@Result(name = "success", location = "/admin/profiles-index.jsp") })
	public String create() {
		log.debug("Create new profile {}", profile);
		try {
			securityservice.add(profile, null);
			addActionMessage("New Profile created successfully");
			return "success";
		} catch (OBCException e) {
			e.printStackTrace();
			addActionError("Error creating profile:" + e.getMessage());
		}

		return "error";

	}

	// POST /codes/update
	@Action(value = "/admin/security/update", results = {
			@Result(name = "error", location = "/admin/profiles-edit.jsp"),
			@Result(name = "input", location = "/admin/profiles-edit.jsp"),
			@Result(name = "success", location = "/admin/profiles-index.jsp") })
	public String update() {
		try {
			securityservice.modify(profile, null);
			addActionMessage("Profile updated successfully");
		} catch (OBCException e) {
			// TODO Auto-generated catch block
			addActionError("Failed to update Profile");
			e.printStackTrace();
			return "error";
		}

		return "success";
	}

	@Override
	public void prepare() throws Exception {
		// code = new Code();
		String[] strings = params.get("id");
		String[] altstrings = params.get("profile.id");
		//fix for input 
		if(strings == null || strings.length == 0){
			strings = altstrings;
			loadOptions = true;
		}
		if (strings != null && strings.length > 0) {
			id = NumberUtils.createLong(strings[0]);
			if(loadOptions){
				profile = securityservice.loadProfile(id);
				optionsList = securityservice.getAllOtherOptions(profile);
			}else{
				profile = securityservice.getProfile(id);
			}
		}
		 
	}

	private boolean loadOptions = false;
	public void prepareEdit() throws Exception {
		loadOptions = true;
	}
	
	public void prepareEditNew() throws Exception {
		optionsList = securityservice.getAllOptions();
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Collection<Profile> getProfileList() {
		return profileList;
	}

	@Override
	public void setParameters(Map<String, String[]> parameters) {
		params = parameters;
	}

	public PageBean getPagebean() {
		return pagebean;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Option> getOptionsList() {
		return optionsList;
	}

}
