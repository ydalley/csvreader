package com.empire.obc.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ov_payment_response")
public class NewPaymentReciptResponse implements Serializable {
	
	/*Response:
	{
	"actionCode":"BR01",
	"responseCode":"00",
	"responseMessage":"Update Successful",
	"paymentReference":"2234",
	"fundsReservationNo":"1122388998",
	"merchantFullName":"Easiways Logistics",
	"merchantId":"717dcace9a",
	"paymentAmount":"5700.00",
	"paymentStatus":"SETTLEMENT PENDING"
	}*/
	
	
	//STAN, the merchantID, the receiptNo, paymentDate, responseCode, responseMessage.

	private static final long serialVersionUID = -4421182189490048209L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="stan", length=25)
	private String STAN;
	
	@Column(name="action_code", length=10)
	private String actionCode;
	
	@Column(name="response_code", length=10)
	private String responseCode;
	
	@Column(name="response_message", length=75)
	private String responseMessage;
	
	@Column(name="receipt_number", length=25)
	private String receiptNo;
	
	@Column(name="payment_date", length=25)
	private String paymentDate;
	
	//@Column(name="payment_reference", length=15)
	//private String paymentReference;
	
	//@Column(name="funds_reservation_number", length=15)
	//private String fundsReservationNo;
	
	//@Column(name="merchant_full_name", length=65)
	//private String merchantFullName;
	
	@Column(name="merchant_id", length=15)
	private String merchantId;
	
	//@Column(name="payment_amount")
	//private double paymentAmount;
	
	@Column(name="payment_status", length=25)
	private String paymentStatus;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSTAN() {
		return STAN;
	}

	public void setSTAN(String sTAN) {
		STAN = sTAN;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	/*public String getPaymentReference() {
		return paymentReference;
	}

	public void setPaymentReference(String paymentReference) {
		this.paymentReference = paymentReference;
	}

	public String getFundsReservationNo() {
		return fundsReservationNo;
	}

	public void setFundsReservationNo(String fundsReservationNo) {
		this.fundsReservationNo = fundsReservationNo;
	}

	public String getMerchantFullName() {
		return merchantFullName;
	}

	public void setMerchantFullName(String merchantFullName) {
		this.merchantFullName = merchantFullName;
	}*/

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	/*public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}*/

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	
	
}
