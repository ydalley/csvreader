<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="./headert2.jsp" />
<!-- Page Content -->
<div id="page-wrapper">
	<div class="container-fluid">
		
		<!-- .panel-heading -->

		<!-- .panel-body -->
		<!-- my add -->
		<div class="col-lg-12">
			<h1 class="page-header">Codes</h1>
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
							<s:url action="show"   var="showurl"/>
							<s:url action="new"  var="newurl"/>
								<div class="btn-toolbar" role="toolbar" aria-label="code toolbar">
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
								<div id="CodeTableContainer"></div>
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
	<script type="text/javascript">
	
		$(document).ready(function() {
			$('#CodeTableContainer').jtable({
				title : 'Codes',
				actions : {
					listAction : '/GettingStarted/PersonList',
					createAction : '/GettingStarted/CreatePerson',
					updateAction : '/GettingStarted/UpdatePerson',
					deleteAction : '/GettingStarted/DeletePerson'
				},
				fields : {
					id : {
						key : true,
						list : false
					},
					code : {
						title : 'Code',
						width : '30%'
					},
					type : {
						title : 'Type',
						width : '30%'
					},
					description : {
						title : 'Description',
						width : '40%'
					}
				}
			});
		});
	</script>
	<s:include value="./footert.jsp" />