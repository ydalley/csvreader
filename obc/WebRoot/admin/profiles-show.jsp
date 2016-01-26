<%@ taglib prefix="s" uri="/struts-tags"%>
<s:url action="edit" includeContext="true"  var="editurl"/>
<s:form role="form" action="%{editurl}" id="form-edit-profile">
	<div class="panel-body">
	<fieldset disabled="disabled">
		<div class="form-group col-lg-6">
			<label>Name</label> <input class="form-control" name="profile.name" value="${profile.name}" />
		</div>
		<div class="form-group col-lg-6">
			<label>Email</label> <input class="form-control" name="profile.name" value="${profile.email}">
		</div>
		<div class="form-group col-lg-6">
			<label>Role</label> <input class="form-control" name="code.description" value="${profile.role}">
		</div>
		<input type="hidden" name="profile.id" value="${profile.id}">
	</fieldset>
	</div>
</s:form>
