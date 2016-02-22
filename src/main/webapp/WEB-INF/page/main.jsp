<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${basePath }/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${basePath }/css/bootstrap-treeview.min.css" >
<link rel="stylesheet" type="text/css" href="${basePath }/css/bootstrap-table.min.css">

<link rel="stylesheet" type="text/css" href="${basePath }/css/style.css">

<script type="text/javascript" src="${basePath }/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${basePath }/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath }/js/bootstrap-treeview.min.js"></script>
<script type="text/javascript" src="${basePath }/js/bootstrap-table.min.js"></script>
<script type="text/javascript" src="${basePath }/js/bootstrap-table-en-US.min.js"></script>
<script type="text/javascript" src="${basePath }/js/core.js"></script>
<script type="text/javascript">


</script>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar" aria-expanded="false">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<div class="navbar-header pull-left">
				<a href="#" class="navbar-brand">
					<span class="glyphicon glyphicon-apple"></span>
					Brand
				</a>
			</div>
			<div class="navbar-header pull-right" role="navigation">
          		<ul class="nav navbar-nav navbar-right">
					<li>
						<a data-toggle="dropdown" class="dropdown-toggle" href="#">
							<span class="glyphicon glyphicon-tasks"></span>
							<span class="badge">4</span>
						</a>
						<ul class="dropdown-menu">
							<li class="dropdown-header">
								<span class="glyphicon glyphicon-ok"></span>
								4 task to complete
							</li>
							<li><a href="#">Task 1</a></li>
							<li><a href="#">Task 1</a></li>
							<li><a href="#">Task 1</a></li>
							<li><a href="#">Task 1</a></li>
						</ul>
					</li>
					<li>
						<a data-toggle="dropdown" class="dropdown-toggle" href="#">
							<span class="glyphicon glyphicon-bell"></span>
							<span class="badge">8</span>
						</a>
					</li>
					<li>
						<a data-toggle="dropdown" class="dropdown-toggle" href="#">
							<span class="glyphicon glyphicon-user"></span>
							Welecome,Admin
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
			                <li><a href="#">资料修改</a></li>
			                <li class="divider" role="separator"></li>
			                <li><a href="#">退出</a></li>
			        	</ul>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<div id="main-container" class="main-container">
		<div id="sidebar" class="sidebar">
			<ul class="nav nav-list">
				<li>
					<a href="#">
						<span class="glyphicon glyphicon-blackboard"></span>
						我的桌面
					</a>
				</li>
				<li>
					<a href="#" class="dropdown-toggle">
						<span class="glyphicon glyphicon-blackboard"></span>
						菜单2
						<span class="glyphicon glyphicon-chevron-down pull-right"></span>
					</a>
					<ul class="submenu">
						<li>
							<a href="#">Simple & Dynamic</a>
						</li>
						<li>
							<a href="#">jgGrid</a>
						</li>
					</ul>
					
				</li>
			</ul>
		</div>
	</div>
</body>
</html>
