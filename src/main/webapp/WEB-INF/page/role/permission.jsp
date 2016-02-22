<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="modal-header">
   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
   <h4 class="modal-title" id="modal-label"> 权限设置</h4>
</div>
<div class="modal-body">
	<ul id="res-tree" class="ztree"></ul>
</div>
<div class="modal-footer">
   <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
   <button type="button" class="btn btn-primary" id="save-btn">保存</button>
</div>
<script>
$(function(){	

	var setting = {
			check: {
				enable: true,
				chkboxType: { "Y": "p"}
			},
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
			}
		};
	zNodes = $.parseJSON('${resTree }');
	zTree = $.fn.zTree.init($("#res-tree"), setting, zNodes);
	
	var permissions = $.parseJSON('${permissions }');
	$.each(permissions, function(index,permission){
		var nodes = zTree.getNodesByParam("resourceId",permission.resourceId);
		if(nodes && nodes[0])
		{
			zTree.checkNode(nodes[0], true, true);
		}
	});
	zTree.expandAll(true);
	
	$("#save-btn").click(function(){
		var resourceIds = new Array();
		var nodes = zTree.getCheckedNodes(true);
		var len = nodes.length;
		for(var i=0;i<len;i++)
		{
			var resourceId=nodes[i].resourceId;
			resourceIds.push(resourceId);
		}
		var _url = "${basePath}/role/savePermission.do";
		var _param = {
				resourceIds:resourceIds,
				roleId:"${roleId}",
				_ts_:new Date().getTime()
				};
		$.post(_url,_param,function(data){
			data = $.parseJSON(data);
			if(data.status!="SUCCESS")
			{
				alert(data.message);
			}
			else
			{
				alert("设置成功");
				$("#detail-modal").modal('hide');
			}
		});
	});
});
</script>