<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="modal-header">
   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
   <h4 class="modal-title" id="modal-label"> 字典详细</h4>
</div>
<div class="modal-body">
	<form:form commandName="codeList" class="form-horizontal" id="codelist-form">
		<form:hidden path="codeListId"/>
		<form:hidden path="parentId"/>
		<form:hidden path="depth"/>
		<div class="form-group">
			<label for="codeKey" class="col-sm-2 control-label">编号</label>
			<div class="col-sm-10">
			<c:if test="${not empty codeList.codeListId }">
			<form:input path="codeKey" class="form-control" disabled="true"/>
			</c:if>
			<c:if test="${empty codeList.codeListId  }">
			<form:input path="codeKey" class="form-control"/>
			</c:if>
			
			</div>
		</div>
		<div class="form-group">
			<label for="codeValue" class="col-sm-2 control-label">名称</label>
			<div class="col-sm-10">
			<form:input path="codeValue" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<label for="groupName" class="col-sm-2 control-label">分组</label>
			<div class="col-sm-10">
			<form:input path="groupName" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<label for="sortOrder" class="col-sm-2 control-label">序号</label>
			<div class="col-sm-10">
				<form:input path="sortOrder" class="form-control"/>
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
   <button type="button" class="btn btn-primary" id="save-codelist-btn">保存</button>
</div>
<script>
$(function(){
	
	var validateOpts = {
		errorElement : 'span',  
        errorClass : 'help-block',  
        focusInvalid : false, 
		rules:{
			codeKey:{
				required:true
			},
			codeValue:{
				required:true
			},
			groupName:{
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
	$("#codelist-form").validate(validateOpts);
	$("#save-codelist-btn").click(function(){
		if($("#codelist-form").validate().form())
		{
			var url = "${basePath}/codelist/save.do";
			var data = $("#codelist-form").serialize();
			$.getJSON(url,data,function(data){
				if('SUCCESS'==data.status)
				{
					alert("保存成功。");
					$("#codelist-modal").modal('hide');
					var treeObj = $.fn.zTree.getZTreeObj("codelist-tree");
					var node = data.userData.codeList;
					if($("#codeListId").val().length>0)
					{
						console.log("update");
						//update
						var old = treeObj.getNodeByParam("codeListId", node.codeListId, null);
						var newNode = $.extend({},old,node);
						console.log(newNode)
						treeObj.updateNode(newNode);
					}
					else
					{
						console.log("insert");
						var parent = treeObj.getNodeByParam("codeListId", node.parentId, null);
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