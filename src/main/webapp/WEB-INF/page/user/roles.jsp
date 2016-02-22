<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="modal-header">
   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
   <h4 class="modal-title" id="modal-label"> 设置角色</h4>
</div>
<div class="modal-body">
	<table id="user-role-table-grid"></table>
	<div id="user-role-grid-pager"></div>
	<table id="user-role-seld-table-grid"></table>
</div>
<div class="modal-footer">
   <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
   <button type="button" class="btn btn-primary" id="save-btn">保存</button>
</div>
<script>
$(function(){	
	var parent_column_all = $("#user-role-table-grid").closest('[class*="col-"]');
	var parent_column_seld = $("#user-role-seld-table-grid").closest('[class*="col-"]');
	$(document).on('settings.ace.jqGrid' , function(ev, event_name, collapsed) {
		console.log(event_name);
		if( event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed' ) {
			setTimeout(function() {
				$("#user-role-table-grid").jqGrid( 'setGridWidth', parent_column_all.width() );
				$("#user-role-seld-table-grid").jqGrid( 'setGridWidth', parent_column_seld.width() );
			}, 0);
		}
    });
	$("#user-role-table-grid").jqGrid({
		url:"${basePath}/role/search.do",
		datatype: "json",
        mytype: 'GET',
        caption:"所有角色",
        colNames:["ID","编号","名称","描述","操作"],
        colModel:[
        	{name:"roleId",hidden:true},
        	{name:"roleCode"},
        	{name:"roleName"},
        	{name:"roleDesc"},
        	{
        		name:"opt",
        		formatter:function(val,opt,row){
        			return '<a href="#" onclick="addRole(\''+opt.rowId+'\')"><span class="glyphicon glyphicon-plus"></span></a>';
        		}
        	}
         ],
         rowNum: 10,
         page: 1,
         rowList: [10, 20, 30],
         pager: "#user-role-grid-pager",
         viewrecords: true,
         height: "auto",
         width:"550",
         gridview:true,
         autoencode: true
	}).navGrid("#user-role-grid-pager", {
        refresh: false,
        edit: false,
        add: false,
        del: false,
        search: false
    });
	
	$("#user-role-seld-table-grid").jqGrid({
		url:"${basePath}/role/search.do?userId=${userId}",
		datatype: "json",
        mytype: 'GET',
        caption:"所有角色",
        colNames:["ID","编号","名称","描述","操作"],
        colModel:[
        	{name:"roleId",hidden:true},
        	{name:"roleCode"},
        	{name:"roleName"},
        	{name:"roleDesc"},
        	{
        		name:"opt",
        		formatter:function(val,opt,row){
        			return '<a href="#" onclick="delRole(\''+opt.rowId+'\')"><span class="glyphicon glyphicon-minus"></span></a>';
        		}
        	}
         ],
         viewrecords: true,
         height: "auto",
         //autowidth: true,
         width:"550",
         gridview:true,
         autoencode: true
	});
	$(window).triggerHandler('resize.jqGrid');
	
	
});
function addRole(currRowId)
{
	console.log(currRowId);
	var $allGrid = $("#user-role-table-grid");
	var $selGrid = $("#user-role-seld-table-grid");
	var currRow = $allGrid.getRowData(currRowId);
	var selIds = $selGrid.getDataIDs();
	if(selIds.length>0)
	{
		var exists = false;
		$.each(selIds,function(){
			var rowId = this;
			var selRow = $selGrid.getRowData(rowId);
			if(currRow.roleId == selRow.roleId)
			{
				exists = true;
				return false;
			}
		});
		if(exists)
		{
			alert("不能重复添加。");
			return;
		}
		$selGrid.addRowData(currRow.roleId,currRow,"last");
	}
}

function delRole(rowid)
{
	$("#user-role-seld-table-grid").delRowData(rowid);
}
</script>