<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="./headert.jsp" />
<!-- Page Content -->
<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Preferences</h1>
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
				<s:form role="form" action="password-reset" method="post" id="form-save">
					<div class="panel panel-default">
						<div class="panel-heading">Change Password</div>

						<div class="panel-body">


							<div class="form-group col-lg-6">
								<label>Old Password</label> <input class="form-control" placeholder="Password" name="model.password" type="password" value="${model.password}">
							</div>
							<div class="form-group col-lg-6">
								<label>New Password</label> <input class="form-control" placeholder="Password" name="model.newPassword" type="password" value="${model.newPassword}"></div>
							<div class="form-group col-lg-6">
								<label>Confirm New Password</label>
								<input class="form-control" placeholder="Password" name="model.newPassword2" type="password" value="${model.newPassword2}">
							</div>
						</div>
						<div class="panel-footer text-right">
							<button class="btn btn-primary" id="btn-change" type="submit">Change</button>
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
	
	$("#btn-change").on("click", function(e) {
		$("#form-change").submit();
	});
	
	$(document).ready(function() {
		$("#fielderrors li").each(function (index, value) { 
			var tt = $(this).text();
			$("[name='"+ tt +"']").parent().addClass("has-error");
		});
	});
	
</script>
