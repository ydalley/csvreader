<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="./headert.jsp" />
<!-- Page Content -->
<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">User</h1>
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
				<s:form role="form" action="update" method="post" id="form-save-code">
					<div class="panel panel-default">
						<div class="panel-heading">Edit User</div>

						<div class="panel-body">

							<div class="form-group col-lg-6">
								<label>First Name</label> <input class="form-control" name="user.firstName" value="${user.firstName}" />
							</div>
							<div class="form-group col-lg-6">
								<label>Last Name</label> <input class="form-control" name="user.lastName" value="${user.lastName}" />
							</div>
							<div class="form-group col-lg-6">
								<label>Login ID</label> <input class="form-control" name="user.loginId" value="${user.loginId}" />
							</div>
							<div class="form-group col-lg-6">
								<label>E-mail</label> <input class="form-control" name="user.email" value="${user.email}" />
							</div>
							<div class="form-group col-lg-6">
								<label>Phone</label> <input class="form-control" name="user.phone" value="${user.phone}" />
							</div>
							<div class="form-group col-lg-6">
								<label>Profile</label> <select class="form-control" name="user.profile.id">
									<s:iterator value="profiles" var="p">
										<option value="${p.id}" ${p.id == user.profile.id ? 'selected' : ''}>${p.name}</option>
									</s:iterator>
								</select>
							</div>
							<input type="hidden" name="user.id" value="${user.id}">
							<input type="hidden" name="user.version" value="${user.version}">
						</div>
						<div class="panel-footer text-right">
							<button class="btn btn-primary" id="btn-save-user" type="submit">Save</button>
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
	$("#btn-save-user").on("click", function(e) {
		$("#form-save-user").submit();
	});
</script>
