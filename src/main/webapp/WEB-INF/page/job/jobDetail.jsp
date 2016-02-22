<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="modal-header">
   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
   <h4 class="modal-title" id="modal-label"> 任务详细</h4>
</div>
<div class="modal-body">
	<form:form class="form-horizontal" id="command-form">
		<form:hidden path="jobId"/>
		<fieldset>
			<legend>任务信息</legend>
		
		<div class="form-group">
			<label for="jobName" class="col-sm-2 control-label">任务名称</label>
			<div class="col-sm-10">
			<form:input path="jobName" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<label for="jobClass" class="col-sm-2 control-label">类型</label>
			<div class="col-sm-10">
			<form:select path="jobClass">
				<form:options items="${jobs }" itemValue="codeKey" itemLabel="codeValue"/>
			</form:select>
			</div>
		</div>
		<div class="form-group">
			<label for="jobTriggerType" class="col-sm-2 control-label">触发类型</label>
			<div class="col-sm-10">
			<c:forEach var="item" items="${jobTriggerTypes }">
				<div class="radio">
				<form:radiobutton path="jobTriggerType" value="${item.codeKey }" />
				<label>${item.codeValue }</label>
				</div>
			</c:forEach>
			</div>
		</div>
		<div class="form-group">
			<label for="jobTriggerValue" class="col-sm-2 control-label">触发条件</label>
			<div class="col-sm-10">
			<form:input path="jobTriggerValue"/>
			</div>
		</div>
		</fieldset>
		<fieldset>
			<legend>执行参数</legend>
		<div id="params"></div>
		</fieldset>
	</form:form>
</div>
<div class="modal-footer">
   <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
   <button type="button" class="btn btn-primary" id="save-btn">保存</button>
</div>
<script>
var jobParams = {};

$(function(){	
	<c:if test="${!empty command.jobParam }">
	<c:forEach var="item" items="${command.jobParam }">
	jobParams['${item.key}']='${item.value}';
	</c:forEach>
	</c:if>
	var jobClass = $("#jobClass").val();
	getConfigParams(jobClass);
	$("#jobClass").change(function(){
		var jobClass = $(this).val();
		getConfigParams(jobClass);
	});
	$("input[name='jobTriggerType']").each(function(intex,item){
		$(this).click(function(){
			console.log($(this).val());
			if($(this).val()==1)
			{
				$("#jobTriggerValue").datetimepicker({format:'YYYY-MM-DD HH:mm:ss',useSeconds:true});
			}
			else
			{
				var picker = $("#jobTriggerValue").data('DateTimePicker');
				if(picker)
				{
					picker.destroy();
					$("input[name='jobTriggerValue']").val('');
				}
			}
		});
	});
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
			var url = "${basePath}/job/save.do";
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

function getConfigParams(jobClass)
{
	var $paramsDiv = $("#params");
	$paramsDiv.empty();
	if(jobClass == null || jobClass=="")
	{
		return;
	}
	var url = "${basePath}/job/getConfigParams.do";
	var data = {jobClass:jobClass,_ts_:new Date().getTime()};
	$.getJSON(url,data,function(data){
		if('SUCCESS'==data.status)
		{
			var params = data.userData.configParams;
			$.each(params,function(index,param){
				var val = param.paramName in jobParams  ? jobParams[param.paramName] : '' ;
				$paramsDiv.append(
					'<div class="form-group">' +
						'<label for="jobParam.'+param.paramName+'" class="col-sm-2 control-label">'+param.paramLabel+'</label>'+
						'<div class="col-sm-10">'+
						'<input type="text" id="jobParam.'+param.paramName+'" name="jobParam['+param.paramName+']" class="form-control" value="'+val+'"/>'+
						'</div>'+
					'</div>'		
				);
			});
		}
		else
		{
			alert(data.message);
		}
	});
}
</script>