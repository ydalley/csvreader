<%@ taglib prefix="s" uri="/struts-tags"%>
<s:url action="edit"  var="editurl"/>
<s:form role="form" action="%{editurl}" id="form-edit">
	<div class="panel-body">
	<fieldset disabled="disabled">
		<div class="form-group col-lg-6">
			<label>Code</label> <input class="form-control" name="code.code" value="${code.code}" />
		</div>
		<div class="form-group col-lg-6">
			<label>type</label> <input class="form-control" name="code.type" value="${code.type}">
		</div>
		<div class="form-group col-lg-6">
			<label>description</label> <input class="form-control" name="code.description" value="${code.description}">
		</div>
		<input type="hidden" name="code.id" value="${code.id}">
	</fieldset>
	</div>
</s:form>
