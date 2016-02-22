$(function(){
});

function buildMenu(menus)
{
	var $menu = $("#sidemenu");
	
	$.each(menus,function(index,item){
		var url = item.resourceUrl ? item.resourceUrl : '';
		var menuItem = '<li class="" id="'+item.resourceCode+'">';
		menuItem += '<a href="#'+url+'" data-url="'+url+'">';
		menuItem += '<i class="'+item.resourceIcon+'"></i>';
		menuItem += '<span class="menu-text">'+item.resourceName+'</span>';
		menuItem += '</a>';
		menuItem += '</li>';
		
		var pid = item.parentResourceCode;
		var parent = $("#"+pid);
		if(parent.length>0)
		{
			var atag = parent.children("a");
			if(!atag.hasClass("dropdown-toggle"))
			{
				atag.addClass("dropdown-toggle");
				atag.append('<b class="arrow fa fa-angle-down"></b>');
			}
			var submenu = parent.children(".submenu");
			if(!submenu.length>0)
			{
				submenu = $('<ul class="submenu"></ul>');
				parent.append(submenu);
			}
			
				submenu.append(menuItem);
			
		}
		else
		{
			
			$menu.append(menuItem);
		}
		
		
	});
	
}