<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="./headert.jsp" />
<!-- Page Content -->
<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Users</h1>
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
				<s:form role="form" action="create" method="post" id="form-create-user">
					<div class="panel panel-default">
						<div class="panel-heading">Create User</div>

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
										<option disabled selected> -- select a profile -- </option>
									<s:iterator value="profiles" var="p">
										<option value="${p.id}" ${p.id == user.profile.id ? 'selected' : ''}>${p.name}</option>
									</s:iterator>

								</select>
							</div>

						</div>
						<div class="panel-footer text-right">
							<button class="btn btn-primary" id="btn-create-user" type="submit">Create</button>
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
	$("#btn-create-user").on("click", function(e) {
		$("#form-create-user").submit();
	});
	$(document).ready(function() {
		$("#fielderrors li").each(function (index, value) { 
			var tt = $(this).text();
				$("[name='"+ tt +"']").parent().addClass("has-error");
		});
	});
</script>
