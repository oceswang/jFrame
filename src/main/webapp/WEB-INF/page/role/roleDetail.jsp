<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="modal-header">
   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
   <h4 class="modal-title" id="modal-label"> 用户详细</h4>
</div>
<div class="modal-body">
	<form:form class="form-horizontal" id="command-form">
		<form:hidden path="roleId"/>
		<div class="form-group">
			<label for="userLogin" class="col-sm-2 control-label">编号</label>
			<div class="col-sm-10">
			<c:if test="${not empty command.roleId }">
			<form:input path="roleCode" class="form-control" readonly="true"/>
			</c:if>
			<c:if test="${empty command.roleId }">
			<form:input path="roleCode" class="form-control"/>
			</c:if>
			
			</div>
		</div>
		<div class="form-group">
			<label for="roleName" class="col-sm-2 control-label">名称</label>
			<div class="col-sm-10">
			<form:input path="roleName" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<label for=roleDesc class="col-sm-2 control-label">描述</label>
			<div class="col-sm-10">
			<form:textarea path="roleDesc" cols="50" rows="3"/>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<div class="checkbox">
					<label>
					<form:checkbox path="active" value="1"/>是否可用
					</label>
				</div>
			</div>
		</div>
	</form:form>
</div>
<div class="modal-footer">
   <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
   <button type="button" class="btn btn-primary" id="save-btn">保存</button>
</div>
<script>
$(function(){	
	var validateOpts = {
		errorElement : 'span',  
        errorClass : 'help-block',  
        focusInvalid : false, 
		rules:{
			resourceCode:{
				required:true
			},
			resourceName:{
				required:true
			}
		},

		highlight : function(element) {  
            $(element).closest('.form-group').addClass('has-error');  
        },  

        success : function(label) {  
            label.closest('.form-group').removeClass('has-error');  
            label.remove();  
        },  

        errorPlacement : function(error, element) {  
            element.parent('div').append(error);  
        }
	};
	$("#command-form").validate(validateOpts);
	$("#save-btn").click(function(){
		if($("#command-form").validate().form())
		{
			var url = "${basePath}/role/save.do";
			var data = $("#command-form").serialize();
			$.getJSON(url,data,function(data){
				if('SUCCESS'==data.status)
				{
					alert("保存成功。");
					$("#table-grid").jqGrid().trigger("reloadGrid");
					$("#detail-modal").modal('hide');
				}
				else
				{
					alert(data.message);
				}
			});
		}
	});
});
</script>