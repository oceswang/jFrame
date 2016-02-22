<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <body>
   	<div class="panel panel-default">
    	<div class="btn-group" role="group" aria-label="btns">
    		<button class="btn btn-default" type="button">
				<span class="glyphicon glyphicon-file"></span>
				<span class="glyphicon-class">添加</span>
			</button>
			<button class="btn btn-default" type="button">
				<span class="glyphicon glyphicon-edit"></span>
				<span class="glyphicon-class">修改</span>
			</button>
			<button class="btn btn-default" type="button">
				<span class="glyphicon glyphicon-trash"></span>
				<span class="glyphicon-class">删除</span>
			</button>
    	</div>
    	<table id="jobList"></table>
   	</div>
    <script type="text/javascript">
    $(function(){
    	$('#jobList').bootstrapTable({
    		method:'get',
    		url:'${pageContext.request.contextPath}/schedule/getSchedJobList.do',
    		sidePagination:'server',
    		pagination:true,
    		//height:400,
    		clickToSelect:true,
    		columns:[
    			{
    				radio:true,
    				field:'schedJobId',
    				title:'ID'
    				
    			},
    			{
    				field:'jobName',
    				title:'Job Name'
    			}
    			,
    			{
    				field:'jobDesc',
    				title:'Job Description'
    			},
    			{
    				field:'jobClass',
    				title:'Job Class'
    			},
    			{
    				field:'nextRunTime',
    				title:'Next Run time'
    			}
    		],
    		queryParamsType:'server',
    		queryParams:function(params)
    		{
    			params.rows = params.pageSize;
    			params.page = params.pageNumber;
    			params.sidx = params.sortName;
    			params.sord = params.sortOrder;
    			return params;
    		},
    		responseHandler:function(data)
    		{
    			data.total = data.records;
    			return data;
    		}
    	});
    	
    });
    </script>
    
  </body>
</html>