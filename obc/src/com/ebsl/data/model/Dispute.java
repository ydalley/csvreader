package com.ebsl.data.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.struts2.json.annotations.JSON;
@Entity
@Table(name="dispute")
public class Dispute {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected long id;
	
	@Column(length=1024)
	private String reason;
	private String status;
	private Date transactionDate;
	private Date disputeDate;
	private String invoiceNumber;
	private String receiptNumber;
	private BigDecimal amount;
	private String fundingBankAccount;
	private String merchantId;
	
	private String loggedBy;
	private String approvedBy;
	
	
	private PaymentRequest payment;
	
	
	@Version
    protected int version;
	@JSON(name="DT_RowId")
	public long getId() {
		return id;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public Date getDisputeDate() {
		return disputeDate;
	}
	public void setDisputeDate(Date disputeDate) {
		this.disputeDate = disputeDate;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getFundingBankAccount() {
		return fundingBankAccount;
	}
	public void setFundingBankAccount(String fundingBankAccount) {
		this.fundingBankAccount = fundingBankAccount;
	}
	public String getLoggedBy() {
		return loggedBy;
	}
	public void setLoggedBy(String loggedBy) {
		this.loggedBy = loggedBy;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public PaymentRequest getPayment() {
		return payment;
	}
	public void setPayment(PaymentRequest payment) {
		this.payment = payment;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getReceiptNumber() {
		return receiptNumber;
	}
	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}
	
	
}
