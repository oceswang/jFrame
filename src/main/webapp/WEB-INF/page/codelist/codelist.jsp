<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<title>权限管理 - Ace Admin</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
	<h1>
		数据字典
		<small>
			<i class="ace-icon fa fa-angle-double-right"></i>
			管理系统字典
		</small>
	</h1>
</div><!-- /.page-header -->

<div class="row">
	<div class="col-sm-12">
		<div class="widget-box widget-color-blue2">
			<div class="widget-header">
				<h4 class="widget-title lighter smaller">数据字典</h4>
			</div>

			<div class="widget-body">
				<div class="widget-main padding-8">
					<ul id="codelist-tree" class="ztree"></ul>
				</div>
			</div>
		</div>
	</div>
	 <div class="modal fade" id="codelist-modal" data-show="false" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="codelist-modal-label" aria-hidden="true">
	   <div class="modal-dialog">
	      <div class="modal-content" id="codelist-content">
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
								var name = node['codeValue']+"("+node['codeKey']+")";
								return name;
							},
							title: "codeValue"
						},
						simpleData: {
							enable: true,
							idKey: "codeListId",
							pIdKey: "parentId",
							rootPId: -1
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
								var url = "${basePath}/codelist/add.do";
								var data = {parentId:node.codeListId,_ts_:new Date().getTime()};
								$("#codelist-content").load(url,data,function(){
									$("#codelist-modal").modal('show');
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
							var url = "${basePath}/codelist/edit.do";
							var data = {codeListId:node.codeListId,_ts_:new Date().getTime()};
							$("#codelist-content").ajaxLoad(
								url,
								data,
								function(){
									$("#codelist-modal").modal('show');
								},
								true
							);
							return false;
						},
						beforeRemove:function(treeId,node){
							if(confirm("sure remove?"))
							{
							
							}
							return false;
						}
					}
				};
			zNodes = $.parseJSON('${codedata }');
			$.fn.zTree.init($("#codelist-tree"), setting, zNodes);
			
			
		});
	
	});
</script>
