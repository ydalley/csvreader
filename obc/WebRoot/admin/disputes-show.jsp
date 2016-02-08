<%@ taglib prefix="s" uri="/struts-tags"%>
<s:url action="edit"  var="editurl"/>
<s:form role="form" action="%{editurl}" id="form-edit">
	<div class="panel-body">
	<fieldset disabled="disabled">
			<div class="form-group col-lg-6">
								<label>Merchant ID</label> <input class="form-control" name="dispute.merchantId" value="${dispute.merchantId}" />
							</div>
							<div class="form-group col-lg-6">
								<label>Invoice Number</label> <input class="form-control" name="dispute.invoiceNumber"
									value="${dispute.invoiceNumber}" />
							</div>
							<div class="form-group col-lg-6">
								<label>Receipt Number</label> <input class="form-control" name="dispute.receiptNumber"
									value="${dispute.receiptNumber}" />
							</div>

							<div class="form-group col-lg-6">
								<label>Funding Bank Account</label> <input class="form-control" name="dispute.fundingBankAccount"
									value="${dispute.fundingBankAccount}" />
							</div>
							<div class="form-group col-lg-6">
								<label>Amount</label> <input class="form-control" type="number" name="dispute.amount" value="${dispute.amount}" />
							</div>
							<div class="form-group col-lg-6">
							<s:date name="dispute.transactionDate" var="mdate" format="yyyy-MM-dd"/>
								<label>Transaction Date</label> <input class="form-control" type="date" name="dispute.transactionDate"
									value="${mdate}" />
									
							</div>

							<div class="form-group col-lg-6">
								<label>Dispute Reason</label>
								<textarea class="form-control" name="dispute.reason" rows="3">${dispute.reason}</textarea>
							</div>
		<input type="hidden" name="dispute.id" value="${dispute.id}">
	</fieldset>
	</div>
</s:form>
