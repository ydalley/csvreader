package com.ebsl.actions.admin;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.ebsl.actions.UserAware;
import com.ebsl.data.model.User;
import com.ebsl.service.ConfigurationService;
import com.ebsl.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@Component
@Scope(value = "prototype")
@Namespace("/admin/configurations")
@ParentPackage("obc")
@Results({ @Result(name = "error", location = "/admin/index.jsp") })
public class ConfigAction extends ActionSupport implements Preparable,
		UserAware, ParameterAware {

	@Autowired
	private ConfigurationService configService;
	private User currentUser;
	private Map<String, String[]> params;
	private PageBean pagebean;

	@Action(value = "/admin/configurations/index", results = { @Result(name = "index", location = "/admin/configurations-index.jsp") })
	public String execute() throws Exception {

		return "index";
	}

	@Actions({
		@Action(value = "/admin/configsj", results = { @Result(name = "index", type = "json", params = {
				"root", "pagebean"}) }, interceptorRefs = { @InterceptorRef(value = "json") }),
		@Action(value = "/configsj", results = { @Result(name = "index", type = "json", params = {
				"root", "pagebean" }) }, interceptorRefs = { @InterceptorRef(value = "json") }) })
	
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

			pagebean = configService.getAllConfigs(length, start);

		pagebean.setDraw(draw);

		return "index";
	}

	@Override
	public void setCurrentUser(User user) {
		currentUser = user;
	}

	@Override
	public void prepare() throws Exception {

	}

	@Override
	public void setParameters(Map<String, String[]> parameters) {
		params = parameters;
	}

	public PageBean getPagebean() {
		return pagebean;
	}

}
