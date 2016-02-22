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
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<div class="tabbable">
			<ul class="nav nav-tabs padding-12 tab-color-blue background-blue" id="myTab4">
				<li class="active">
					<a data-toggle="tab" href="#user-tab"><i class="ace-icon glyphicon glyphicon-user"></i>用户管理</a>
				</li>

				<li>
					<a data-toggle="tab" href="#resource-tab"><i class="ace-icon glyphicon glyphicon-list"></i>资源管理</a>
				</li>

				<li>
					<a data-toggle="tab" href="#role-tab"><i class="ace-icon fa fa-users"></i>角色管理</a>
				</li>
			</ul>

			<div class="tab-content">
				<div id="user-tab" class="tab-pane in active">
					
				</div>

				<div id="resource-tab" class="tab-pane">
					<jsp:include page="resourceTree.jsp"></jsp:include>
				</div>

				<div id="role-tab" class="tab-pane">
					<p>Etsy mixtape wayfarers, ethical wes anderson tofu before they sold out mcsweeney's organic lomo retro fanny pack lo-fi farm-to-table readymade.</p>
				</div>
			</div>
		</div>
		<!-- PAGE CONTENT ENDS -->
	</div><!-- /.col -->
</div><!-- /.row -->

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
  <script src="${basePath}/assets/js/excanvas.js"></script>
<![endif]-->
<script type="text/javascript">
	var scripts = [null,"${basePath}/assets/js/jquery-ui.custom.js","${basePath}/assets/js/jquery.ui.touch-punch.js", null]
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
	  //inline scripts related to this page
		 jQuery(function($) {
	
		
		});
	
	});
</script>
