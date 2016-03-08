<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="./headert.jsp" />
<!-- Page Content -->
<div id="page-wrapper">
	<div class="container-fluid">
		
		<!-- .panel-heading -->

		<!-- .panel-body -->
		<!-- my add -->
		<div class="col-lg-12">
			<h1 class="page-header">Users</h1>
			<s:if test="hasActionErrors()">
			<div class="alert alert-danger alert-dismissable">
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
				<s:actionerror />
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
							<s:url action="show" var="showurl"/>
							<s:url action="new"  var="newurl"/>
							<s:url action="enable" var="enableurl"/>
							<s:url action="password"  var="passurl"/>
								<div class="btn-toolbar" role="toolbar" aria-label="user toolbar">
									<button formaction="${showurl}" data-toggle="modal" data-target="#myModal" class="btn  btn-primary btn-xs onselect"
										id='btn-show-user' disabled>
										<span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Open
									</button>
									<button formaction="${enableurl}" class="btn  btn-primary  btn-xs onselect" id="btn-enable-user" disabled >
										<span class="fa fa-unlock" aria-hidden="true"></span>/<span class="fa fa-lock" aria-hidden="true"></span> Enable/Disable
									</button>
									<button formaction="${passurl}" class="btn  btn-primary  btn-xs onselect"  id="btn-password-user" disabled>
										<span class="fa fa-key" aria-hidden="true"></span> Password
									</button>
									<button formaction="${newurl}" class="btn  btn-primary btn-xs" id="btn-new-user">
										<span class="glyphicon glyphicon-new-window" aria-hidden="true"></span> New
									</button>
								</div>
							</div>

						</div>
						<div class="panel panel-default">
							<!-- /.panel-heading -->
							<div class="panel-body">
								<div class="dataTable_wrapper">
									<table class="table responsive table-striped table-bordered table-hover" id="usertable">
										<thead>
											<tr>
												<th>First Name</th>
												<th>Last Name</th>
												<th>Email</th>
												<th>Status</th>
											</tr>
										</thead>


									</table>
								</div>
								<!-- /.table-responsive -->
								<!-- Modal -->
								<s:url action="edit"  var="editurl"/>
								<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
									aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
												<h4 class="modal-title" id="myModalLabel">Edit Code</h4>
											</div>
											<div class="modal-body"></div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
												<button type="button" formaction="${editurl}" class="btn btn-primary" id="btn-edit-user">Edit</button>
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
					var dt = $('#usertable').DataTable();
					var id = $(dt.row({
						selected : true
					}).node()).attr("id");
					$(this).find(".modal-body").load(link.attr("formaction"),
							'id=' + id);
				});

		$("#btn-edit-user").on("click", function(e) {
			var link = $(e.target);
			var dt = $('#usertable').DataTable();
			var id = $(dt.row({
				selected : true
			}).node()).attr("id");
			window.location.href = link.attr("formaction") + '?id=' + id;
			//goto url
		});
		
		$("#btn-new-user").on("click", function(e) {
			var link = $(e.target);
			window.location.href = link.attr("formaction") ;
			//goto url
		});
		
		$("#btn-enable-user").on("click", function(e) {
			var link = $(e.target);
			var dt = $('#usertable').DataTable();
			var id = $(dt.row({
				selected : true
			}).node()).attr("id");
			
		window.location.href = link.attr("formaction") + '?id=' + id;	
		
		});
		
		$("#btn-password-user").on("click", function(e) {
			var link = $(e.target);
			var dt = $('#usertable').DataTable();
			var id = $(dt.row({
				selected : true
			}).node()).attr("id");
			
		window.location.href = link.attr("formaction") + '?id=' + id;	
		
		});

		$(document).ready(function() {
			var table = $('#usertable').DataTable({

				select : {
					style : 'single'
				},
				"processing" : true,
				"serverSide" : true,
				"ordering": false,
				"ajax" : "usersj",
				"columns" : [ {
					"data" : "firstName"
				}, {
					"data" : "lastName"
				}, {
					"data" : "email"
				}, {
					"data" : "status"
				} ],
				"columnDefs" : [ {
				"targets" : 3,
				"className": "dt-center",
				"data" : "Status",
				"render" : function(data, type, full, meta) {
					if (type === 'display') {
						if (data == 'E') {
							return "<span class='fa fa-check-circle dt-body-center' style='color: green;'></span>";
						} else if (data == 'D') {
							return "<span class='fa fa-times-circle' style='color: red;'></span>";
						}else{
							return "<span class='fa fa-question-circlen' style='color: red;'></span>";
						}
					}
					return data;
				}
			} ]
			});

			table.on('select', function() {
				if (table.rows({
					selected : true
				}).indexes().length === 0) {
					$('.onselect').attr("disabled", true);
				} else {
					$('.onselect').removeAttr("disabled");
				}

			});

			table.on('deselect', function() {
				if (table.rows({
					selected : true
				}).indexes().length === 0) {
					$('.onselect').attr("disabled", true);
				} else {
					$('.onselect').removeAttr("disabled");
				}
			});
		});
	</script>
	<s:include value="./footert.jsp" />