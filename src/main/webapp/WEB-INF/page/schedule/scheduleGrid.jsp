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
    	<div id="pager"></div>
   	</div>
    <script type="text/javascript">
    $(function(){
    	$('#jobList').jqGrid({
    		method:'get',
    		url:'${pageContext.request.contextPath}/schedule/getSchedJobList.do',
    		datatype:"json",
    		colNames:["ID","任务名称","任务说明","类型","下次执行时间"],
    		colModel:[
    	    			{
    	    				name:'schedJobId'
    	    			},
    	    			{
    	    				name:'jobName'
    	    			}
    	    			,
    	    			{
    	    				name:'jobDesc'
    	    			},
    	    			{
    	    				name:'jobClass'
    	    			},
    	    			{
    	    				name:'nextRunTime'
    	    			}
    	    		],
 	    	rowNum: 10,
            page: 1,
            total: 15,
            rowList: [10, 20, 30],
            pager: "#pager",
            viewrecords: true,
            height: "auto",
            //width: "auto",
            autowidth: true,
            gridview:true,
            autoencode: true,
            multiselect: true
    	}).navGrid("#pager", {
            refresh: false,
            edit: false,
            add: false,
            del: false,
            search: false
        });
    	
    });
    </script>
    
  </body>
</html>