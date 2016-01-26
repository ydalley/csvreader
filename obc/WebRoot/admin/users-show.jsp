<%@ taglib prefix="s" uri="/struts-tags"%>
<s:url action="edit" includeContext="true" var="editurl" />
<s:form role="form" action="%{editurl}" id="form-edit-user">
	<div class="panel-body">
		<fieldset disabled="disabled">
			<div class="form-group col-lg-6">
				<label>First Name</label> <input class="form-control" value="${user.firstName}" disabled="disabled">
			</div>
			<div class="form-group col-lg-6">
				<label>Last Name</label> <input class="form-control" value="${user.lastName}" disabled="disabled">
			</div>
			<div class="form-group col-lg-6">
				<label>Login ID</label> <input class="form-control" placeholder="${user.loginId}" disabled="disabled">
			</div>
			<div class="form-group col-lg-6">
				<label>E-mail</label> <input class="form-control" placeholder="${user.email}" disabled="disabled">
			</div>
			<div class="form-group col-lg-6">
				<label>Phone</label> <input class="form-control" placeholder="${user.phone}" disabled="disabled">
			</div>
			<div class="form-group col-lg-6">
				<label>Profile</label> 
				<select class="form-control" >
					<s:iterator value="profiles" var="p">
						<option value="${p.id}" ${p.id == user.profile.id ? 'selected' : ''}>${p.name}</option>
					</s:iterator>
				</select>
			</div>
			 <input type="hidden" name="user.id" value="${user.id}">
		</fieldset>
	</div>
</s:form>
