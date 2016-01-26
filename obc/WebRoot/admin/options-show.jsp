<%@ taglib prefix="s" uri="/struts-tags"%>
<s:url action="edit" includeContext="true"  var="editurl"/>
<s:form role="form" action="%{editurl}" id="form-edit">
	<div class="panel-body">
	<fieldset disabled="disabled">
		<div class="form-group col-lg-6">
			<label>Name</label> <input class="form-control" name="option.code" value="${option.name}" />
		</div>
		<div class="form-group col-lg-6">
			<label>Description</label> <input class="form-control" name="option.Description" value="${option.description}" />
		</div>
		<input type="hidden" name="option.id" value="${option.id}">
	</fieldset>
	</div>
</s:form>
