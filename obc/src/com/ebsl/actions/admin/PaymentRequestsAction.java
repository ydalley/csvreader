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

import com.ebsl.data.model.PaymentRequest;
import com.ebsl.service.PaymentRequestService;
import com.ebsl.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@Controller
@Scope("prototype")
@Namespace("/admin/payments")
@ParentPackage("obc")
@Results({ @Result(name = "error", location = "/admin/payments-index.jsp") })
public class PaymentRequestsAction extends ActionSupport implements Preparable,
		ParameterAware {

	private static final Logger log = LogManager.getLogger(PaymentRequestsAction.class);

	private PaymentRequest payment;
	private Long id;
	private Collection<PaymentRequest> paymentRequestList;
	private PageBean pagebean;

	@Autowired
	private PaymentRequestService paymentRequestService;
	private Map<String, String[]> params;

		@Action(value = "/admin/payments/show",results = {@Result(name="show",location="/admin/payments-show.jsp")})
		public String show() {
			return "show";
		}

	@Action(value = "/admin/payments/index",results = {@Result(name="index",location="/admin/payments-index.jsp")})	
	public String execute() throws Exception {

		return "index";
	}

	@Actions({
		@Action(value = "/admin/paymentsj", results = { @Result(name = "index", type = "json", params = {
				"root", "pagebean" }) }, interceptorRefs = { @InterceptorRef(value = "json") }),
		@Action(value = "/paymentsj", results = { @Result(name = "index", type = "json", params = {
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
		String search = ServletActionContext.getRequest()
				.getParameter("search[value]");

		if (StringUtils.isEmpty(search)) {
			pagebean = paymentRequestService.getAllPaymentRequests(length, start);
		} else {
			pagebean = paymentRequestService.findPaymentRequests(search, length, start);
		}
		pagebean.setDraw(draw);

		return "index";
	}



	public PaymentRequest getPayment() {
		return payment;
	}

	public void setPayment(PaymentRequest payment) {
		this.payment = payment;
	}

	public Collection<PaymentRequest> getPaymentRequestList() {
		return paymentRequestList;
	}

	@Override
	public void prepare() throws Exception {
		// code = new Code();
		String[] strings = params.get("id");
		if (strings != null && strings.length > 0) {
			id = NumberUtils.createLong(strings[0]);
			payment = paymentRequestService.getPaymentRequest(id);
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

}
