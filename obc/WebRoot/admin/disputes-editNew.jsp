<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="./headert.jsp" />
<!-- Page Content -->
<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Disputes</h1>
				<s:if test="hasActionErrors()">
					<div class="alert alert-danger alert-dismissable">
					<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
						<s:actionerror />
					</div>
				</s:if>
				<s:if test="hasFieldErrors()">
						<div hidden="hidden">
							<ul id="fielderrors">
								<s:iterator value="fieldErrors.keySet()" var="field">
								<li>${field}</li>
								</s:iterator>
							</ul>
						</div>
					<div class="alert alert-danger alert-dismissable">
						<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
						<s:fielderror />
						
					</div>
				</s:if>
				<s:if test="hasActionMessages()">
					<div class="alert alert-success alert-dismissable">
						<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
						<s:actionmessage />
					</div>
				</s:if>
				<s:form role="form" action="create" method="post" id="form-create">
					<div class="panel panel-default">
						<div class="panel-heading">Create Dispute Claim</div>

						<div class="panel-body">


							<div class="form-group col-lg-6">
								<label>Merchant ID</label> <input class="form-control" name="dispute.merchantId" value="${dispute.merchantId}" />
							</div>
							<div class="form-group col-lg-6">
								<label>Invoice Number</label> <input class="form-control" name="dispute.invoiceNumber" value="${dispute.invoiceNumber}" />
							</div>
							<div class="form-group col-lg-6">
								<label>Receipt Number</label> <input class="form-control" name="dispute.receiptNumber" value="${dispute.receiptNumber}" />
							</div>
							
							<div class="form-group col-lg-6">
								<label>Funding Bank Account</label> <input class="form-control" name="dispute.fundingBankAccount" value="${dispute.fundingBankAccount}" />
							</div>
							<div class="form-group col-lg-6">
								<label>Amount</label> <input class="form-control" type="number" name="dispute.amount" value="${dispute.amount}" />
							</div>
							<div class="form-group col-lg-6">
								<label>Transaction Date</label> <input class="form-control" type="date" name="dispute.transactionDate" value="${dispute.transactionDate}" />
							</div>
							
							<div class="form-group col-lg-6">
								<label>Dispute Reason</label> 
								<textarea class="form-control" name="dispute.reason" rows="3" >${dispute.reason}</textarea>
							</div>
						</div>
						<div class="panel-footer text-right">
							<button class="btn btn-primary" id="btn-create" type="submit">Create</button>
						</div>

					</div>
				</s:form>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.container-fluid -->
	</div>
	<!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->
<s:include value="./footert.jsp" />
<script type="text/javascript">
	
	$("#btn-create").on("click", function(e) {
		$("#form-create").submit();
	});
	$(document).ready(function() {
		$("#fielderrors li").each(function (index, value) { 
			var tt = $(this).text();
				$("[name='"+ tt +"']").parent().addClass("has-error");
		});
	});
</script>
