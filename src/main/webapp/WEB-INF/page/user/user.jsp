<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<title>用户管理 - Ace Admin</title>
<link rel="stylesheet" href="${basePath}/assets/css/jquery-ui.css" />
<link rel="stylesheet" href="${basePath}/assets/css/datepicker.css" />
<link rel="stylesheet" href="${basePath}/assets/css/ui.jqgrid.css" />
<!-- ajax layout which only needs content area -->
<div class="page-header">
	<h1>
		用户管理
		<small>
			<i class="ace-icon fa fa-angle-double-right"></i>
			用户信息维护
		</small>
	</h1>
</div><!-- /.page-header -->

<div class="row">
	<div class="col-sm-12">
	<table id="table-grid"></table>
	<div id="grid-pager"></div>
	 <div class="modal fade" id="detail-modal" data-show="false" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="detail-modal-label" aria-hidden="true">
	   <div class="modal-dialog">
	      <div class="modal-content" id="detail-content">
	      </div><!-- /.modal-content -->
	   </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<div class="modal fade" id="search-modal" data-show="false" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="search-modal-label" aria-hidden="true">
	   <div class="modal-dialog">
	      <div class="modal-content">
	      	<div class="modal-header">
			   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			   <h4 class="modal-title" id="modal-label">查询</h4>
			</div>
			<div class="modal-body">
	      		<form action="#" id="query-form" class="form-horizontal">
					<div class="form-group">
						<label for="userLogin" class="col-sm-2 control-label">登录名</label>
						<div class="col-sm-10">
							<input type="text" name="userLogin" class="form-control input-sm" size="10"/>
						</div>
					</div>
					<div class="form-group">
						<label for="userName" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-10">
							<input type="text" name="userName" class="form-control input-sm" size="10"/>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
			   <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			   <button type="button" class="btn btn-primary" id="search-btn">查询</button>
			</div>
	      </div><!-- /.modal-content -->
	   </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
</div>

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
  <script src="${basePath}/assets/js/excanvas.js"></script>
<![endif]-->
<script type="text/javascript">
	var scripts = [
	               null,
	               "${basePath}/assets/js/jquery-ui.custom.js",
	               "${basePath}/assets/js/jquery.ui.touch-punch.js", 
	               "${basePath}/assets/js/jqGrid/jquery.jqGrid.src.js",
	       		   "${basePath}/assets/js/jqGrid/i18n/grid.locale-cn.js",
	               null]
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
		$(function(){
			
			var parent_column = $("#table-grid").closest('[class*="col-"]');
			$(document).on('settings.ace.jqGrid' , function(ev, event_name, collapsed) {
				console.log(event_name);
				if( event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed' ) {
					setTimeout(function() {
						$("#table-grid").jqGrid( 'setGridWidth', parent_column.width() );
					}, 0);
				}
		    });
			
			$("#table-grid").jqGrid({
				url:"${basePath}/user/search.do",
				datatype: "json",
                mytype: 'GET',
                colNames:["ID","登录名","用户名","最后登录时间","角色设置"],
                colModel:[
                	{name:"userId",hidden:true},
                	{name:"userLogin"},
                	{name:"userName"},
                	{name:"latestLoginTime"},
                	{
                		name:"",
                		formatter:function(val,opts,row){
                			return '<a href="#" onclick="openRoleSelector(\''+row.userId+'\')"><span class="fa fa-users"></span></a>';
                		}
                	}
                 ],
                 rowNum: 10,
                 page: 1,
                 rowList: [10, 20, 30],
                 pager: "#grid-pager",
                 viewrecords: true,
                 height: "auto",
                 autowidth: true,
                 gridview:true,
                 autoencode: true,
                 multiselect: true
                 
			});
			$(window).triggerHandler('resize.jqGrid');
			$("#table-grid")
			.jqGrid(
					'navGrid',
					'#grid-pager',
					{
						edit: true,
						editicon : 'ace-icon fa fa-pencil blue',
						editfunc:function(){
							edit();
						},
						
						add: true,
						addicon : 'ace-icon fa fa-plus-circle purple',
						addfunc:function(){
							add();
						},
						
						del: true,
						delicon : 'ace-icon fa fa-trash-o red',
						delfunc:function(){
							log('del');
						},
						
						search: true,
						searchicon : 'ace-icon fa fa-search orange',
						searchfunc:function(){
							$("#search-modal").modal('show');
						},
						
						refresh: true,
						refreshicon : 'ace-icon fa fa-refresh green'
					});
			
			$("#search-btn")
			.click(function(){
				$("#search-modal").modal('hide');
				search();
			});
			
			function search()
			{
				var data = $("#query-form").serialize();
				$("#table-grid").jqGrid('setGridParam',{
                    page:1,
                    postData:data
                }).trigger("reloadGrid");
			}
			function add(){
				var url = "${basePath}/user/add.do";
				var data = {_ts_:new Date().getTime()};
				$("#detail-content").ajaxLoad(
					url,
					data,
					function(){
						$("#detail-modal").modal('show');
					},
					true
				);
			}
			function edit()
			{
				var rowData = jQuery('#table-grid').jqGrid('getGridParam','selarrrow');
	                if(rowData.length != 1)
	    		    {
	    		    	alert("请选择一条记录");
	    				return;
	    		    }
	            var userId = $('#table-grid').jqGrid('getCell',rowData[0],'userId');
				var url = "${basePath}/user/edit.do";
				var data = {userId:userId,_ts_:new Date().getTime()};
				$("#detail-content").ajaxLoad(
					url,
					data,
					function(){
						$("#detail-modal").modal('show');
					},
					true
				);
			}
		});
	
	});
	function openRoleSelector(userId)
	{
		var url = "${basePath}/user/showRoles.do";
		var data = {userId:userId,_ts_:new Date().getTime()};
		$("#detail-content").ajaxLoad(
			url,
			data,
			function(){
				$("#detail-modal").modal('show');
			},
			true
		);
	}
</script>
