<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="./headert.jsp" />
<!-- Page Content -->
<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row">
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
						<a href="#" class="alert-link">Alert Link</a>.
					</div>
				</s:if>
				<s:form role="form" action="update" method="post" id="form-save">
					<div class="panel panel-default">
						<div class="panel-heading">Edit Permission</div>
						<div class="panel-body">


							<div class="form-group col-lg-6">
								<label>Name</label> <input class="form-control" name="option.name" value="${option.name}" />
							</div>
							<div class="form-group col-lg-6">
								<label>Description</label> <input class="form-control" name="option.description" value="${option.description}" />
							</div>
						</div>
						<div class="panel-footer text-right">
							<button class="btn btn-primary" id="btn-save" type="submit">Save</button>
						</div>
					<input type="hidden" name="option.id" value="${option.id}">
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
	
	$("#btn-save").on("click", function(e) {
		$("#form-save").submit();
	});
</script>
