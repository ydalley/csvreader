package com.ebsl.actions.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
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
import org.springframework.stereotype.Component;

import com.ebsl.actions.UserAware;
import com.ebsl.data.model.Dispute;
import com.ebsl.data.model.User;
import com.ebsl.service.DisputeService;
import com.ebsl.service.OBCException;
import com.ebsl.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@Component
@Scope(value = "prototype")
@Namespace("/admin/disputes")
@ParentPackage("obc")
@Results({ @Result(name = "error", location = "/admin/disputes/index.jsp") })
public class DisputesAction extends ActionSupport implements Preparable,
		UserAware, ParameterAware {

	@Autowired
	private DisputeService disputeservice;
	private User currentUser;
	private Map<String, String[]> params;
	private PageBean pagebean;
	private Dispute dispute;
	private Long id;
	private static final Logger log = LogManager
			.getLogger(DisputesAction.class);

	@Override
	@Action(value = "/admin/disputes/index", results = { @Result(name = "index", location = "/admin/disputes-index.jsp") })
	public String execute() throws Exception {
		DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
		SimpleDateFormat sdf = new SimpleDateFormat();
		DateFormat f = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ENGLISH);
		
		sdf.toPattern();
		return "index";
	}

	@Action(value = "/admin/disputes/show", results = { @Result(name = "show", location = "/admin/disputes-show.jsp") })
	public String show() {
		return "show";
	}

	
	@Actions({
		@Action(value = "/admin/disputesj", results = { @Result(name = "index", type = "json", params = {
				"root", "pagebean" ,"excludeProperties",
				"data\\[\\d+\\]\\.payment"}) }, interceptorRefs = { @InterceptorRef(value = "json") }),
		@Action(value = "/disputesj", results = { @Result(name = "index", type = "json", params = {
				"root", "pagebean", "excludeProperties",
				"data\\[\\d+\\]\\.payment" }) }, interceptorRefs = { @InterceptorRef(value = "json") }) })
	
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

			pagebean = disputeservice.getAllDispute(length, start);

		pagebean.setDraw(draw);

		return "index";
	}

	@RequiresPermissions(value="update:dispute")
	@Action(value = "/admin/disputes/edit", results = { @Result(name = "edit", location = "/admin/disputes-edit.jsp") })
	public String edit() {
		return "edit";
	}

	@RequiresPermissions(value="create:dispute")
	@Action(value = "/admin/disputes/new", results = { @Result(name = "input", location = "/admin/disputes-editNew.jsp") })
	public String editNew() {
		dispute = new Dispute();
		return "input";
	}

	
	@RequiresPermissions(value="create:dispute")
	@Action(value = "/admin/disputes/create", results = {
			@Result(name = "input", location = "/admin/disputes-editNew.jsp"),
			@Result(name = "success", location = "/admin/disputes-index.jsp") })
	public String create() {
		log.debug("Create new dispute {}", dispute);
		try {
			disputeservice.add(dispute, currentUser);
			addActionMessage("New Dispute Claim created successfully");
			return "success";
		} catch (OBCException e) {
			e.printStackTrace();
			addActionError("Error creating Dispute claim:" + e.getMessage());
		}

		return "error";

	}

	// POST /codes/update
	@RequiresPermissions(value="update:dispute")
	@Action(value = "/admin/disputes/update", results = {
			@Result(name = "input", location = "/admin/disputes-edit.jsp"),
			@Result(name = "success", location = "/admin/disputes-index.jsp") })
	public String update() {
		try {
			disputeservice.modify(dispute, currentUser);
			addActionMessage("Dispute claim updated successfully");
		} catch (OBCException e) {
			// TODO Auto-generated catch block
			addActionError("Failed to update dispute claim");
			e.printStackTrace();
			return "error";
		}

		return "success";
	}

	@Override
	public void prepare() throws Exception {
		String[] strings = params.get("id");
		if (strings != null && strings.length > 0) {
			id = NumberUtils.createLong(strings[0]);
			dispute = disputeservice.getDispute(id);
		}
	}

	
	@Override
	public void setCurrentUser(User user) {
		currentUser = user;
	}


	@Override
	public void setParameters(Map<String, String[]> parameters) {
		params = parameters;
	}

	public PageBean getPagebean() {
		return pagebean;
	}

	public Dispute getDispute() {
		return dispute;
	}

	public void setDispute(Dispute dispute) {
		this.dispute = dispute;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
