<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="modal-header">
   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
   <h4 class="modal-title" id="res-modal-label"> 资源详细</h4>
</div>
<div class="modal-body">
	<form:form commandName="resource" class="form-horizontal" id="resource-form">
		<form:hidden path="resourceId"/>
		<form:hidden path="parentResourceCode"/>
		<form:hidden path="depth"/>
		<div class="form-group">
			<label for="resourceCode" class="col-sm-2 control-label">编号</label>
			<div class="col-sm-10">
			<c:if test="${not empty resource.resourceId }">
			<form:input path="resourceCode" class="form-control" readonly="true"/>
			</c:if>
			<c:if test="${empty resource.resourceId }">
			<form:input path="resourceCode" class="form-control"/>
			</c:if>
			
			</div>
		</div>
		<div class="form-group">
			<label for="resourceName" class="col-sm-2 control-label">名称</label>
			<div class="col-sm-10">
			<form:input path="resourceName" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<label for="projectCode" class="col-sm-2 control-label">系统</label>
			<div class="col-sm-10">
			<form:select path="projectCode" 
						items="${projectList }" 
						itemValue="codeKey" 
						itemLabel="codeValue">
			</form:select>
			</div>
		</div>
		<div class="form-group">
			<label for="resourceTypeCode" class="col-sm-2 control-label">类型</label>
			<div class="col-sm-10">
			<form:select path="resourceTypeCode" 
						items="${resourceTypeList }" 
						itemValue="codeKey" 
						itemLabel="codeValue">
			</form:select>
			</div>
		</div>
		<div class="form-group">
			<label for="resourceUrl" class="col-sm-2 control-label">地址</label>
			<div class="col-sm-10">
			<form:input path="resourceUrl" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<label for="resourceIcon" class="col-sm-2 control-label">图标</label>
			<div class="col-sm-10">
				<div class="input-group">
					<form:input path="resourceIcon" class="form-control"/>
					<span class="input-group-addon ${resource.resourceIcon }"></span>
				</div>
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
   <button type="button" class="btn btn-primary" id="save-resource-btn">保存</button>
</div>
<script>
$(function(){
	$("#resourceIcon").change(function(){
		var self = $(this); 
		console.log(self.val());
		self.next().removeClass().addClass("input-group-addon " + self.val());
	});
	
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
	$("#resource-form").validate(validateOpts);
	$("#save-resource-btn").click(function(){
		if($("#resource-form").validate().form())
		{
			var url = "${basePath}/resource/save.do";
			var data = $("#resource-form").serialize();
			$.getJSON(url,data,function(data){
				if('SUCCESS'==data.status)
				{
					alert("保存成功。");
					$("#res-modal").modal('hide');
					var treeObj = $.fn.zTree.getZTreeObj("res-tree");
					var node = data.userData.res;
					if($("#resourceId").val().length>0)
					{
						console.log("update");
						//update
						var old = treeObj.getNodeByParam("resourceId", node.resourceId, null);
						var newNode = $.extend({},old,node);
						console.log(newNode)
						treeObj.updateNode(newNode);
					}
					else
					{
						console.log("insert");
						var parent = treeObj.getNodeByParam("resourceCode", node.parentResourceCode, null);
						//add
						 treeObj.addNodes(parent, node);
					}
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