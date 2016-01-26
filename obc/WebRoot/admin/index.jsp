<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="./headert.jsp"/>
      <!-- Page Content -->
      <div  id="page-wrapper">
        <div  class="container-fluid">
		<s:if test="hasActionErrors()">
			<div class="alert alert-danger alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                <s:actionerror/>
           </div>
		</s:if>
		<s:if test="hasActionMessages()">
			<div class="alert alert-success alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                <s:actionmessage/> 
           </div>
		</s:if>
          <div  class="row">
            <div  class="col-lg-12">
              <h1  class="page-header">My Dashboard</h1>
			     </div>
          <!-- /.row --> </div>
        <!-- /.container-fluid --> </div>
      <!-- /#page-wrapper --> </div>
    <!-- /#wrapper -->
    <script>
    $(document).ready(function(){
        $('#codetable').DataTable();
    });
    </script>
 <s:include value="./footert.jsp"/>