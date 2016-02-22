<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<title>权限管理 - Ace Admin</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
	<h1>
		权限管理
		<small>
			<i class="ace-icon fa fa-angle-double-right"></i>
			管理系统权限
		</small>
	</h1>
</div><!-- /.page-header -->

<div class="row">
	<div class="col-sm-12">
		<div class="widget-box widget-color-blue2">
			<div class="widget-header widget-header-small">
				<h4 class="widget-title lighter smaller">资源列表</h4>
			</div>

			<div class="widget-body">
				<div class="widget-main padding-8">
					<ul id="res-tree" class="ztree"></ul>
				</div>
			</div>
		</div>
	</div>
	 <div class="modal fade" id="res-modal" data-show="false" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="res-modal-label" aria-hidden="true">
	   <div class="modal-dialog">
	      <div class="modal-content" id="res-content">
	      </div><!-- /.modal-content -->
	   </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
</div>

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
  <script src="${basePath}/assets/js/excanvas.js"></script>
<![endif]-->
<script type="text/javascript">
	var scripts = [null,"${basePath}/assets/js/jquery-ui.custom.js","${basePath}/assets/js/jquery.ui.touch-punch.js", null]
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
	  //inline scripts related to this page
		var clickAddBtn = false;
		$(function(){
			var setting = {
					data: {
						key : {
							name: function(treeId,node){
								var name = node['resourceName']+"("+node['resourceCode']+")";
								return name;
							},
							title: "resourceCode"
						},
						simpleData: {
							enable: true,
							idKey: "resourceCode",
							pIdKey: "parentResourceCode",
							rootPId: 1
						}
					},
					view:{
						nameIsHTML:true,
						addHoverDom:function(treeId,node){
							//添加按钮
							if (node.editNameFlag || $("#addBtn_"+node.tId).length > 0) {
								return;
							}
							var aObj = $("#"+node.tId+"_a");
							var addStr = '<span class="button add" id="addBtn_'+node.tId+'" " ></span>';
							aObj.append(addStr);
							$("#addBtn_"+node.tId).click(function(){
								clickAddBtn = true;
								var url = "${basePath}/resource/add.do";
								var data = {parentResourceCode:node.resourceCode,_ts_:new Date().getTime()};
								$("#res-content").load(url,data,function(){
									$("#res-modal").modal('show');
								});
							});
						},
						removeHoverDom:function(treeId,node){
							$("#addBtn_"+node.tId).unbind().remove();
						}
					},
					edit:{
						enable:true
					},
					callback:{
						beforeClick:function(treeId,node,flag){
							console.log('before');
							if(clickAddBtn == true)
							{
								clickAddBtn = false;
								return false;
							}
							return true;
						},
						beforeEditName:function(treeId,node){
							var url = "${basePath}/resource/edit.do";
							var data = {resourceCode:node.resourceCode,_ts_:new Date().getTime()};
							$("#res-content").ajaxLoad(
								url,
								data,
								function(){
									$("#res-modal").modal('show');
								},
								true
							);
							return false;
						},
						beforeRemove:function(treeId,node){
							if(confirm("确定删除?"))
							{
								var url = "${basePath}/resource/delete.do";
								var data = {resourceId:node.resourceId,_ts_:new Date().getTime()};
								$.getJSON(url,data,function(data){
									if('SUCCESS'==data.status)
									{
										var treeObj = $.fn.zTree.getZTreeObj("res-tree");
										treeObj.removeNode(node);
									}
									else
									{
										alert(data.message);
									}
								});
							}
							return false;
						}
					}
				};
			zNodes = $.parseJSON('${resTree }');
			zTree = $.fn.zTree.init($("#res-tree"), setting, zNodes);
			zTree.expandAll(true);
			
		});
	
	});
</script>
