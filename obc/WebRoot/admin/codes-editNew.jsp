<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="./headert.jsp" />
<!-- Page Content -->
<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Codes</h1>
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
						<div class="panel-heading">Create Code</div>

						<div class="panel-body">


							<div class="form-group col-lg-6">
								<label>Code</label> <input class="form-control" name="code.code" value="${code.code}" />
							</div>
							<div class="form-group col-lg-6">
								<label>Type</label> <input class="form-control" name="code.type" value="${code.type}" />
							</div>
							<div class="form-group col-lg-6">
								<label>Description</label> <input class="form-control" name="code.description" value="${code.description}" />
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
