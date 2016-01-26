<%@ taglib prefix="s" uri="/struts-tags"%>
<s:url action="edit" includeContext="true"  var="editurl"/>
<s:form role="form" action="%{editurl}" id="form-edit-code">
	<div class="panel-body">
	<fieldset disabled="disabled">
		<div class="form-group col-lg-4">
			<label>Action Code</label> <input class="form-control" name="payment.actionCode" value="${payment.actionCode}" />
</div>
<div class="form-group col-lg-4">
			<label>Amount Due Merchant</label> <input class="form-control" name="payment.amountDueMerchant" value="${payment.amountDueMerchant}" />
</div>
<div class="form-group col-lg-4">
			<label>Empire Income Account</label> <input class="form-control" name="payment.empireIncomeAccount" value="${payment.empireIncomeAccount}" />
</div>
<div class="form-group col-lg-4">
			<label>Empire Income Amount</label> <input class="form-control" name="payment.empireIncomeAmount" value="${payment.empireIncomeAmount}" />
</div>

<div class="form-group col-lg-4">
			<label>Invoice Date</label> <input class="form-control" name="payment.invoiceDate" value="${payment.invoiceDate}" />
</div>
<div class="form-group col-lg-4">
			<label>Invoice Item Reference</label> <input class="form-control" name="payment.invoiceItemReference" value="${payment.invoiceItemReference}" />
</div>
<div class="form-group col-lg-4">
			<label>Invoice No</label> <input class="form-control" name="payment.invoiceNo" value="${payment.invoiceNo}" />
</div>
<div class="form-group col-lg-4">
			<label>Merchant AccountName</label> <input class="form-control" name="payment.merchantAccountName" value="${payment.merchantAccountName}" />
</div>
<div class="form-group col-lg-4">
			<label>Merchant AccountNo</label> <input class="form-control" name="payment.merchantAccountNo" value="${payment.merchantAccountNo}" />
</div>
<div class="form-group col-lg-4">
			<label>Merchant Id</label> <input class="form-control" name="payment.merchantId" value="${payment.merchantId}" />
</div>
<div class="form-group col-lg-4">
			<label>OneView Settlement Account</label> <input class="form-control" name="payment.oneViewSettlementAccount" value="${payment.oneViewSettlementAccount}" />
</div>
<div class="form-group col-lg-4">
			<label>Payment Amount</label> <input class="form-control" name="payment.paymentAmount" value="${payment.paymentAmount}" />
</div>
<div class="form-group col-lg-4">
			<label>Payment Date</label> <input class="form-control" name="payment.paymentDate" value="${payment.paymentDate}" />
</div>
<div class="form-group col-lg-4">
			<label>Receipt No</label> <input class="form-control" name="payment.receiptNo" value="${payment.receiptNo}" />
</div>
<div class="form-group col-lg-4">
			<label>Receiving Bank Income Account</label> <input class="form-control" name="payment.receivingBankIncomeAccount" value="${payment.receivingBankIncomeAccount}" />
</div>
<div class="form-group col-lg-4">
			<label>Receiving Bank Income Amount</label> <input class="form-control" name="payment.receivingBankIncomeAmount" value="${payment.receivingBankIncomeAmount}" />
</div>
<div class="form-group col-lg-4">
			<label>STAN</label> <input class="form-control" name="payment.STAN" value="${payment.STAN}" />
</div>
<div class="form-group col-lg-4">
			<label>Status</label> <input class="form-control" name="payment.status" value="${payment.status}" />
</div>
<input type="hidden" name="payment.id" value="${payment.id}">
	</fieldset>
	</div>
</s:form>
