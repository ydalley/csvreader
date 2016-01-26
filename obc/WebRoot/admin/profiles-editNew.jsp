<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="./headert.jsp" />
<!-- Page Content -->
<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Profiles</h1>
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
						<div class="panel-heading">Create Profile</div>

						<div class="panel-body">
						<fieldset>
							<div class="form-group col-lg-6">
								<label>Name</label> <input class="form-control" name="profile.name" value="${profile.name}" />
							</div>
							<div class="form-group col-lg-6">
								<label>Email</label> <input class="form-control" name="profile.email" value="${profile.email}">
							</div>
							<div class="form-group col-lg-6">
								<label>Role</label> <input class="form-control" name="code.description" value="${profile.role}">
							</div>
							</fieldset>
							<fieldset>
							<legend>Permissions</legend>
							<div class="row">
							
							 <div class="form-group text-left col-lg-5">
									<select multiple class="form-control left-selector">
										<s:iterator value="optionsList" var="p">
											<option value="${p.id}"}>${p.description}</option>
										</s:iterator>
									</select>
							</div>
                              <div class="btn-group-vertical col-xs-2 ">
                               <button type="button" class="btn btn-default text- btn-circle btn-default right-btn"><i class="fa fa-long-arrow-right"></i>
                            </button>
                              	 <button type="button" class="btn btn-default text-center btn-circle left-btn" ><i class="fa fa-long-arrow-left"></i>
                            </button>
                            
                              </div>
                               <div class="form-group text-right col-lg-5">
                              	<select multiple class="form-control right-selector" name="profile.options.id">
                              	 <s:iterator value="profile.options" var="op">
											<option value="${op.id}"}>${op.description}</option>
								</s:iterator>
                                 </select>
                              </div>
                              </div>
							</fieldset>
							
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

	$().ready(function() {
		$('.left-btn').click(
				function() {
					return !$('.right-selector option:selected').remove()
							.appendTo('.left-selector');
				});
		$('.right-btn').click(
				function() {
					return !$('.left-selector option:selected').remove()
							.appendTo('.right-selector');
				});
	});
	$("#btn-create").on("click", function(e) {
		$('.right-selector option').prop("selected", true);
		$("#form-save").submit();
	});
</script>
