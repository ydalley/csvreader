package com.ebsl.actions.admin;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
import org.springframework.stereotype.Controller;

import com.ebsl.data.model.Code;
import com.ebsl.service.CodeService;
import com.ebsl.service.OBCException;
import com.ebsl.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@Controller
@Scope("prototype")
@Namespace("/admin/codes")
@ParentPackage("obc")
@Results({ @Result(name = "error", location = "/admin/options-index.jsp") })
public class CodesAction extends ActionSupport implements Preparable,
		ParameterAware {

	private static final Logger log = LogManager.getLogger(CodesAction.class);

	private Code code;
	private Long id;
	private Collection<Code> codeList;
	private PageBean pagebean;

	@Autowired
	private CodeService codeservice;
	private Map<String, String[]> params;

	// GET /codes/edit?1d=1
		@Action(value = "/admin/codes/show",results = {@Result(name="show",location="/admin/codes-show.jsp")})
		public String show() {
			return "show";
		}

	@Action(value = "/admin/codes/index",results = {@Result(name="index",location="/admin/codes-index.jsp")})	
	public String execute() throws Exception {

		return "index";
	}

	@Actions({
			@Action(value = "/admin/codesj", results = { @Result(name = "index", type = "json", params = {
					"root", "pagebean" }) }, interceptorRefs = { @InterceptorRef(value = "json") }),
			@Action(value = "/codesj", results = { @Result(name = "index", type = "json", params = {
					"root", "pagebean", "excludeProperties",
					"data\\[\\d+\\]\\.modification" }) }, interceptorRefs = { @InterceptorRef(value = "json") }) })
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
			pagebean = codeservice.getAllCodes(length, start);
		} else {
			pagebean = codeservice.findCode(search, length, start);
		}
		pagebean.setDraw(draw);

		return "index";
	}

	// GET /codes/edit?1d=1
	@Action(value = "/admin/codes/edit",results = {@Result(name="edit",location="/admin/codes-edit.jsp")})
	public String edit() {
		return "edit";
	}
	
	// GET /codes/new
	@Action(value = "/admin/codes/new",results = {@Result(name="input",location="/admin/codes-editNew.jsp")})
	public String editNew() {
		code = new Code();
		return "input";
	}

	// GET /codes/1/deleteConfirm
	public String deleteConfirm() {
		return "deleteConfirm";
	}

	// DELETE /codes?id=1
	public String destroy() {
		// log.debug("Delete order with id: {}", id);
		// ordersService.remove(id);
		// addActionMessage("Order removed successfully");
		return "success";
	}

	@Action(value = "/admin/codes/create",results = {@Result(name="error",location="/admin/codes-editNew.jsp"),
			@Result(name="success",location="/admin/codes-index.jsp")})
	public String create() {
		log.debug("Create new code {}", code);
		try {
			codeservice.add(code, null);
			addActionMessage("New Code created successfully");
			return "success";
		} catch (OBCException e) {
			e.printStackTrace();
			addActionError("Error creating code:"+e.getMessage());
		}
		
		return "error";

	}

	// POST /codes/update
	@Action(value = "/admin/codes/update",results = {@Result(name="error",location="/admin/codes-edit.jsp"),
			@Result(name="success",location="/admin/codes-index.jsp")})
	
	public String update() {
		try {
			codeservice.modify(code, null);
			addActionMessage("Code updated successfully");
		} catch (OBCException e) {
			// TODO Auto-generated catch block
			addActionError("Failed to update Code");
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
			code = codeservice.getCode(id);
		}
	}

	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
	}

	public Collection<Code> getCodeList() {
		return codeList;
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

}
