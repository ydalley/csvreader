<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="./headert.jsp" />
<!-- Page Content -->
<div id="page-wrapper">
	<div class="container-fluid">
		
		<!-- .panel-heading -->

		<!-- .panel-body -->
		<!-- my add -->
		<div class="col-lg-12">
			<h1 class="page-header">Permissions</h1>
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
								<div class="btn-toolbar" role="toolbar" aria-label="permission toolbar">
									<button formaction="${showurl}" data-toggle="modal" data-target="#myModal" class="btn  btn-primary"
										id='btn-show' disabled>
										<span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Open
									</button>
									<button formaction="${newurl}" class="btn  btn-primary" id="btn-new">
										<span class="glyphicon glyphicon-new-window" aria-hidden="true"></span> New
									</button>
								</div>
							</div>

						</div>
						<div class="panel panel-default">
							<!-- /.panel-heading -->
							<div class="panel-body">
								<div class="dataTable_wrapper">
									<table class="table responsive table-striped table-bordered table-hover" id="obctable">
										<thead>
											<tr>
												<th>Name</th>
												<th>Description</th>
											</tr>
										</thead>


									</table>
								</div>
								<!-- /.table-responsive -->
								<!-- Modal -->
								<s:url action="edit" includeContext="true" var="editurl"/>
								<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
									aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
												<h4 class="modal-title" id="myModalLabel">Edit Permission</h4>
											</div>
											<div class="modal-body"></div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
												<button type="button" formaction="${editurl}" class="btn btn-primary" id="btn-edit">Edit</button>
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
					var dt = $('#obctable').DataTable();
					var id = $(dt.row({
						selected : true
					}).node()).attr("id");
					$(this).find(".modal-body").load(link.attr("formaction"),
							'id=' + id);
				});

		$("#btn-edit").on("click", function(e) {
			var link = $(e.target);
			var dt = $('#obctable').DataTable();
			var id = $(dt.row({
				selected : true
			}).node()).attr("id");
			window.location.href = link.attr("formaction") + '?id=' + id;
			//goto url
		});
		
		$("#btn-new").on("click", function(e) {
			var link = $(e.target);
			window.location.href = link.attr("formaction") ;
			//goto url
		});

		$(document).ready(function() {
			var table = $('#obctable').DataTable({

				select : {
					style : 'single'
				},
				"processing" : true,
				"serverSide" : true,
				"ajax" : "optionsj",
				"columns" : [ {"data" : "name"},{"data": "description"}]
			});

			table.on('select', function() {
				if (table.rows({
					selected : true
				}).indexes().length === 0) {
					$('#btn-show').attr("disabled", true);
				} else {
					$('#btn-show').removeAttr("disabled");
				}

			});

			table.on('deselect', function() {
				if (table.rows({
					selected : true
				}).indexes().length === 0) {
					$('#btn-show').attr("disabled", true);
				} else {
					$('#btn-show').removeAttr("disabled");
				}
			});
		});
	</script>
	<s:include value="./footert.jsp" />