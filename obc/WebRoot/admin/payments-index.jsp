<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="./headert.jsp" />
<!-- Page Content -->
<div id="page-wrapper">
	<div class="container-fluid">
		
		<!-- .panel-heading -->

		<!-- .panel-body -->
		<!-- my add -->
		<div class="col-lg-12">
			<h1 class="page-header">Payments</h1>
			<s:if test="hasActionErrors()">
			<div class="alert alert-danger alert-dismissable">
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
				<s:actionerror />
				<a href="#" class="alert-link">Alert Link</a>.
			</div>
		</s:if>
		<s:if test="hasActionMessages()">
			<div class="alert alert-success alert-dismissable">
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
				<s:actionmessage />
				
			</div>
		</s:if>
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="panel-group" id="accordion">
					
						<div class="panel panel-default">
							<div class="panel-heading">
							<s:url action="show" includeContext="true"  var="showurl"/>
							<s:url action="new" includeContext="true" var="newurl"/>
								<div class="btn-toolbar" role="toolbar" aria-label="payment toolbar">
									<button formaction="${showurl}" data-toggle="modal" data-target="#myModal" class="btn btn-xs btn-primary"
										id='btn-show-payment' disabled>
										<span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Open
									</button>
									<button formaction="${newurl}" class="btn  btn-primary btn-xs" id="btn-new-payment">
										<span class="glyphicon glyphicon-new-window" aria-hidden="true"></span> New
									</button>
									<button class="btn  btn-primary btn-xs accordion-toggle collapsed" id="btn-new-payment" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
										<span class="glyphicon glyphicon-search" aria-hidden="true"></span> search
									</button>
								</div>
							</div>

						</div>
						<div class="panel panel-default">
                                    <div id="collapseOne" class="accordion-body collapse">
								<s:form role="form" id="form-find">
									<div class="panel-body">

										<div class="form-group col-lg-4">
											<label>Merchant ID</label> <input class="form-control" name="merchantId"
												value="${merchantId}" />
										</div>
										<div class="form-group col-lg-4">
											<label>Payment Amount</label> <input class="form-control" name="paymentAmount"
												value="${paymentAmount}" />
										</div>
										<div class="form-group col-lg-4">
											<label>Payment Amount(max)</label> <input class="form-control" name="paymentAmountl"
												value="${paymentAmountl}" />
										</div>
										<div class="form-group col-lg-4">
											<label>Invoice Number</label> <input class="form-control" name="invoiceNumber"
												value="${invoiceNumber}" />
										</div>
										<div class="form-group col-lg-4">
										<s:date name="dispute.transactionDate" var="fdate" format="yyyy-MM-dd" />
											<label>From Date</label> <input type="date" class="form-control" name="fromDate"
												value="${fdate}" />
										</div>

										<div class="form-group col-lg-4">
										<s:date name="dispute.transactionDate" var="tdate" format="yyyy-MM-dd" />
											<label>To Date</label> <input type="date" class="form-control" name="toDate"
												value="${tdate}"/>
										</div>
										<div class="text-right">
										<button class="btn  btn-primary  btn-outline btn-xs" id="btn-find">
												<span class="glyphicon glyphicon-search" aria-hidden="true"></span> Go
											</button>
										</div>
									</div>
								</s:form>
							</div>
                        </div>
						<div class="panel panel-default">
							<!-- /.panel-heading -->
							<div class="panel-body">
								<div class="dataTable_wrapper">
									<table class="table responsive table-striped table-bordered table-hover" id="paymenttable">
										<thead>
											<tr>
												<th>Merchant ID</th>
												<th>Amount</th>
												<th>Status</th>
											</tr>
										</thead>


									</table>
								</div>
								<!-- /.table-responsive -->
								<!-- Modal -->
								<s:url action="edit" includeContext="true" var="disputeurl"/>
								<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
									aria-hidden="true">
									<div class="modal-dialog modal-lg">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
												<h4 class="modal-title" id="myModalLabel">View payment</h4>
											</div>
											<div class="modal-body"></div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
												<button type="button" formaction="${disputeurl}" class="btn btn-primary " id="btn-edit-code">Dispute</button>
											</div>
										</div>
									</div>
									<!-- /.modal-dialog -->
								</div>
								<!-- /.modal -->
							</div>
						</div>
						<!-- /.col-lg-12 -->
					</div>
					<!-- /.row -->
				</div>

				<!-- /.container-fluid -->
			</div>
			<!-- /#page-wrapper -->
		</div>


		<!-- /#wrapper -->
	</div>
</div>
	<script>
		$(document).on('hidden.bs.modal', function(e) {
			$(e.target).removeData('bs.modal');
		});

		$("#myModal").on(
				"show.bs.modal",
				function(e) {
					var link = $(e.relatedTarget);
					var dt = $('#paymenttable').DataTable();
					var id = $(dt.row({
						selected : true
					}).node()).attr("id");
					$(this).find(".modal-body").load(link.attr("formaction"),
							'id=' + id);
				});

		$("#btn-edit-payment").on("click", function(e) {
			var link = $(e.target);
			var dt = $('#paymenttable').DataTable();
			var id = $(dt.row({
				selected : true
			}).node()).attr("id");
			window.location.href = link.attr("formaction") + '?id=' + id;
			//goto url
		});
		
		$("#btn-new-payment").on("click", function(e) {
			var link = $(e.target);
			window.location.href = link.attr("formaction") ;
			//goto url
		});
		
		$("#btn-find").on("click", function(e) {
			e.preventDefault();
			var link = $(e.target);
			var table = $('#paymenttable').DataTable();
			table.ajax.reload( null, false ); 
			
		});

		$(document).ready(function() {
			var table = $('#paymenttable').DataTable({
				"bFilter" : false,
				select : {
					style : 'single'
				},
				"processing" : true,
				"deferLoading": 0,
				"serverSide" : true,
				"ajax" : {
					url :"paymentsj", 
					"type": "POST",
					"data": function ( d ) {
		                var x = $("#form-find").serializeArray();
					    $.each(x, function(i, field){
					       d[field.name] = field.value ;
					    });
	            	}
				},
				"columns" : [ {
					"data" : "merchantId"
				}, {
					"data" : "paymentAmount"
				}, {
					"data" : "status"
				} ]
			});
			table.on('select', function() {
				if (table.rows({
					selected : true
				}).indexes().length === 0) {
					$('#btn-show-payment').attr("disabled", true);
				} else {
					$('#btn-show-payment').removeAttr("disabled");
				}

			});

			table.on('deselect', function() {
				if (table.rows({
					selected : true
				}).indexes().length === 0) {
					$('#btn-show-payment').attr("disabled", true);
				} else {
					$('#btn-show-payment').removeAttr("disabled");
				}
			});
			//$('#collapseOne').collapse("hide");
		});
	</script>
	<s:include value="./footert.jsp" />