package com.ebsl.actions.admin;

import java.util.Map;

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
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.ebsl.data.model.Option;
import com.ebsl.service.CodeService;
import com.ebsl.service.OBCException;
import com.ebsl.service.SecurityService;
import com.ebsl.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@Component
@Scope(value="prototype")
@Namespace("/admin/options")
@ParentPackage("obc")
@Results({ @Result(name = "error", location = "/admin/options-index.jsp") })
public class OptionsAction extends ActionSupport implements Preparable,
		ParameterAware {

	private static final Logger log = LogManager
			.getLogger(OptionsAction.class);

	private Option option;
	private Long id;
	private PageBean pagebean;

	@Autowired
	private SecurityService securityservice;
	@Autowired
	private CodeService codeservice;
	private Map<String, String[]> params;

	@Action(value = "/admin/options/show", results = { @Result(name = "show", location = "/admin/options-show.jsp") })
	public String show() {
		return "show";
	}

	@Action(value = "/admin/options/index", results = { @Result(name = "index", location = "/admin/options-index.jsp") })
	public String execute() throws Exception {

		return "index";
	}

	@Actions({
			@Action(value = "/admin/optionsj", results = { @Result(name = "index", type = "json", params = {
					"root", "pagebean" ,"excludeProperties",
					"data\\[\\d+\\]\\.profiles"}) }, interceptorRefs = { @InterceptorRef(value = "json") }),
			@Action(value = "/optionsj", results = { @Result(name = "index", type = "json", params = {
					"root", "pagebean", "excludeProperties",
					"data\\[\\d+\\]\\.profiles" }) }, interceptorRefs = { @InterceptorRef(value = "json") }) })
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

		pagebean = securityservice.getAllOptions(length, start);
		pagebean.setDraw(draw);

		return "index";
	}

	@RequiresPermissions(value="update:option")
	@Action(value = "/admin/options/edit", results = { @Result(name = "edit", location = "/admin/options-edit.jsp") })
	public String edit() {
		return "edit";
	}

	@RequiresPermissions(value="create:option")
	@Action(value = "/admin/options/new", results = { @Result(name = "input", location = "/admin/options-editNew.jsp") })
	public String editNew() {
		option = new Option();
		return "input";
	}

	
	@RequiresPermissions(value="create:option")
	@Action(value = "/admin/options/create", results = {
			@Result(name = "error", location = "/admin/options-editNew.jsp"),
			@Result(name = "success", location = "/admin/options-index.jsp") })
	public String create() {
		log.debug("Create new permission {}", option);
		try {
			securityservice.add(option, null);
			addActionMessage("New Permission created successfully");
			return "success";
		} catch (OBCException e) {
			e.printStackTrace();
			addActionError("Error creating permission:" + e.getMessage());
		}

		return "error";

	}

	// POST /codes/update
	@RequiresPermissions(value="update:option")
	@Action(value = "/admin/options/update", results = {
			@Result(name = "error", location = "/admin/options-edit.jsp"),
			@Result(name = "success", location = "/admin/options-index.jsp") })
	public String update() {
		try {
			securityservice.modify(option, null);
			addActionMessage("permission updated successfully");
		} catch (OBCException e) {
			// TODO Auto-generated catch block
			addActionError("Failed to update permission");
			e.printStackTrace();
			return "error";
		}

		return "success";
	}

	@Override
	public void prepare() throws Exception {
		// code = new Code();
		String[] strings = params.get("id");
		if (strings != null && strings.length > 0) {
			id = NumberUtils.createLong(strings[0]);
			option = securityservice.getOption(id);
		}
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


	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
	}

}
