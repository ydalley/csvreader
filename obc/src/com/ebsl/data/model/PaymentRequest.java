package com.ebsl.data.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Entity
@Table(name = "ov_payment_request")
@Indexed
public class PaymentRequest implements Serializable {

	private static final long serialVersionUID = -6440162866811683360L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "stan", length = 25)
	private String STAN;

	@Column(name = "action_code", length = 10)
	private String actionCode;

	// @Column(name="receiving_bank_code", length=15)
	// private String receivingBankCode;

	// @Column(name="receiving_bank_short_name", length=10)
	// private String receivingBankShortName;

	@Column(name = "empire_income_account", length = 15)
	private String empireIncomeAccount;

	@Column(name = "receiving_bank_income_account", length = 15)
	private String receivingBankIncomeAccount;

	@Column(name = "oneview_settlement_account", length = 15)
	private String oneViewSettlementAccount;

	// @Column(name="merchant_full_name", length=65)
	// private String merchantFullName;

	@Column(name = "merchant_id", length = 15)
	private String merchantId;
	
	@Field(index=Index.YES, store=Store.NO)
	@Column(name = "merchant_account_name", length = 55)
	private String merchantAccountName;

	@Column(name = "merchant_account_number", length = 15)
	private String merchantAccountNo;

	// @Column(name="funds_reservation_no", length=25)
	// private String fundsReservationNo;

	// @Column(name="payment_reference", length=25)
	// private String paymentReference;

	@Column(name = "receipt_number", length = 25)
	private String receiptNo;

	// @Column(name="payment_description", length=100)
	// private String paymentDescription;

	@Column(name = "payment_date")
	private Date paymentDate;

	@Column(name = "payment_amount")
	private Double paymentAmount;

	@Column(name = "amount_due_merchant")
	private Double amountDueMerchant;

	@Column(name = "receiving_bank_income_amount")
	private Double receivingBankIncomeAmount;

	@Column(name = "empire_income_amount")
	private Double empireIncomeAmount;

	@Column(name = "invoice_number", length = 25)
	private String invoiceNo;

	@Column(name = "invoice_date")
	private Date invoiceDate;

	@Column(name = "invoice_item_reference", length = 25)
	private String invoiceItemReference;

	@Column(name = "status", length = 25)
	private String status;


	@JSON(name = "DT_RowId")
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

	/*
	 * public String getReceivingBankCode() { return receivingBankCode; }
	 * 
	 * public void setReceivingBankCode(String receivingBankCode) {
	 * this.receivingBankCode = receivingBankCode; }
	 * 
	 * public String getReceivingBankShortName() { return
	 * receivingBankShortName; }
	 * 
	 * public void setReceivingBankShortName(String receivingBankShortName) {
	 * this.receivingBankShortName = receivingBankShortName; }
	 */
	public String getEmpireIncomeAccount() {
		return empireIncomeAccount;
	}

	public void setEmpireIncomeAccount(String empireIncomeAccount) {
		this.empireIncomeAccount = empireIncomeAccount;
	}

	public String getReceivingBankIncomeAccount() {
		return receivingBankIncomeAccount;
	}

	public void setReceivingBankIncomeAccount(String receivingBankIncomeAccount) {
		this.receivingBankIncomeAccount = receivingBankIncomeAccount;
	}

	public String getOneViewSettlementAccount() {
		return oneViewSettlementAccount;
	}

	public void setOneViewSettlementAccount(String oneViewSettlementAccount) {
		this.oneViewSettlementAccount = oneViewSettlementAccount;
	}

	/*
	 * public String getMerchantFullName() { return merchantFullName; }
	 * 
	 * public void setMerchantFullName(String merchantFullName) {
	 * this.merchantFullName = merchantFullName; }
	 */

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantAccountName() {
		return merchantAccountName;
	}

	public void setMerchantAccountName(String merchantAccountName) {
		this.merchantAccountName = merchantAccountName;
	}

	public String getMerchantAccountNo() {
		return merchantAccountNo;
	}

	public void setMerchantAccountNo(String merchantAccountNo) {
		this.merchantAccountNo = merchantAccountNo;
	}

	/*
	 * public String getFundsReservationNo() { return fundsReservationNo; }
	 * 
	 * public void setFundsReservationNo(String fundsReservationNo) {
	 * this.fundsReservationNo = fundsReservationNo; }
	 * 
	 * public String getPaymentReference() { return paymentReference; }
	 * 
	 * public void setPaymentReference(String paymentReference) {
	 * this.paymentReference = paymentReference; }
	 */

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	/*
	 * public String getPaymentDescription() { return paymentDescription; }
	 * 
	 * public void setPaymentDescription(String paymentDescription) {
	 * this.paymentDescription = paymentDescription; }
	 */


	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public Double getAmountDueMerchant() {
		return amountDueMerchant;
	}

	public void setAmountDueMerchant(Double amountDueMerchant) {
		this.amountDueMerchant = amountDueMerchant;
	}

	public Double getReceivingBankIncomeAmount() {
		return receivingBankIncomeAmount;
	}

	public void setReceivingBankIncomeAmount(Double receivingBankIncomeAmount) {
		this.receivingBankIncomeAmount = receivingBankIncomeAmount;
	}

	public Double getEmpireIncomeAmount() {
		return empireIncomeAmount;
	}

	public void setEmpireIncomeAmount(Double empireIncomeAmount) {
		this.empireIncomeAmount = empireIncomeAmount;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}


	public String getInvoiceItemReference() {
		return invoiceItemReference;
	}

	public void setInvoiceItemReference(String invoiceItemReference) {
		this.invoiceItemReference = invoiceItemReference;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
