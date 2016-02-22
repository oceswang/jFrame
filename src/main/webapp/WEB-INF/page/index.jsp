<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${basePath }/plugins/jquery-ui/jquery-ui.min.css">
<link rel="stylesheet" type="text/css" href="${basePath }/css/style2.css">
<link rel="stylesheet" type="text/css" href="${basePath }/css/jquery-accordion-menu.css">
<link rel="stylesheet" type="text/css" href="${basePath}/plugins/font-awesome/css/font-awesome.min.css" />

<script type="text/javascript" src="${basePath }/plugins/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${basePath }/plugins/jquery-ui/jquery-ui.min.js"></script>
<script type="text/javascript" src="${basePath }/js/jquery-accordion-menu.js"></script>

<style>
 #tabs li .ui-icon-close { float: left; margin: 0.4em 0.2em 0 0; cursor: pointer; }
 #tabs .scroller {overflow:hidden;} 
</style>

<script type="text/javascript">
var tabs;
$(function(){
	var nodes = $.parseJSON('${resTree }');
	$("#sidebar").jqueryAccordionMenu({source:nodes});
	tabs = $(".main-content #tabs").tabs({
		heightStyle: "fill"
	});
	
	for(var i =1;i<10;i++)
	{
		//addTab('tabs-'+i,'Home Tab '+i,'This is home tab '+i);
	}
});
var tabTemp = "<li><a href='{href}'>{label}</a> <span class='ui-icon ui-icon-close' role='presentation'>关闭</span></li>";
function addTab(href,label,content)
{
	li = $("<li><a href='#"+href+"'>"+label+"</a> <span class='ui-icon ui-icon-close' role='presentation'>关闭</span></li>"),
	tabs.find( ".ui-tabs-nav" ).append( li );
	tabs.append( "<div id='" + href + "'>" + content + "</div>" );
	tabs.tabs( "refresh" );
}
</script>
</head>
<body>

	<div class="bannar">
	JFrame Admin
	</div>
	<div id="sidebar" class="jquery-accordion-menu white sidebar">
		<ul id="sidemenu">
			<li class="active">
				<a href="#"><i class="fa fa-home"></i>Home </a>
			</li>
		</ul>
	</div>	
	<div class="main-content">
		<div id="tabs">
			<ul>
				<li><a href="#home">Home Tab</a><span class='ui-icon ui-icon-close' role='presentation'>关闭</span></li>
			</ul>
			<div id="home">home content</div>
		</div>
	</div>
</body>
</html>
