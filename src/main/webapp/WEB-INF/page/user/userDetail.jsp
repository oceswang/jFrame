<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="modal-header">
   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
   <h4 class="modal-title" id="modal-label"> 用户详细</h4>
</div>
<div class="modal-body">
	<form:form class="form-horizontal" id="command-form">
		<form:hidden path="userId"/>
		<div class="form-group">
			<label for="userLogin" class="col-sm-2 control-label">登录名</label>
			<div class="col-sm-10">
			<c:if test="${not empty command.userId }">
			<form:input path="userLogin" class="form-control" readonly="true"/>
			</c:if>
			<c:if test="${empty command.userId }">
			<form:input path="userLogin" class="form-control"/>
			</c:if>
			
			</div>
		</div>
		<c:if test="${empty command.userId }">
		<div class="form-group">
			<label for="userName" class="col-sm-2 control-label">用户名</label>
			<div class="col-sm-10">
			<form:input path="userName" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<label for="password" class="col-sm-2 control-label">密码</label>
			<div class="col-sm-10">
			<form:password path="password" class="form-control"/>
			</div>
		</div>
		</c:if>
		<div class="form-group">
			<label for="latestLoginTime" class="col-sm-2 control-label">最后登录时间</label>
			<div class="col-sm-10">
			<form:input path="latestLoginTime" class="form-control" readonly="true"/>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<div class="checkbox">
					<label>
					<form:checkbox path="userStatus" value="1"/>是否可用
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<div class="col-sm-4">
					<fieldset>
						<legend><h6 class="blue">已选角色</h6></legend>
						<select multiple class="form-control" id="selectedRoles" name="selectedRoles">
						</select>
					</fieldset>
				</div>
				<div class="col-sm-2 btn-group-sm btn-group-vertical">
					<div class="space-20"></div>
					<a class="btn btn-primary"><i class="ace-icon fa fa-angle-double-left"></i></a>
					<a class="btn btn-primary"><i class="ace-icon fa fa-angle-left"></i></a>
				  	<a class="btn btn-primary"><span class="ace-icon fa fa-angle-right"></span></a>
				  	<a class="btn btn-primary"><span class="ace-icon fa fa-angle-double-right"></span></a>
				</div>
				<div class="col-sm-4">
					<fieldset>
						<legend><h6>可选角色</h6></legend>
						<select multiple class="form-control" id="availableRoles" name="availableRoles">
						</select>
					</fieldset>
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
			userLogin:{
				required:true
			},
			password:{
				required:true
			}
			,
			userName:{
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
			var url = "${basePath}/user/save.do";
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