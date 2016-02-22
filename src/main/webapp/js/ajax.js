(function($){
	/***
	 * Ajax
	 * */
	$.fn.extend({
		ajaxLoad:function(url,data,callback,showLoading){
			if(showLoading)
			{
				$('.page-content-area').aceAjax('startLoading');
			}
			$(this).load(url,data,function(responseText,textStatus,jqXHR ){
				var success = true;
				var data = null;
				try
				{
					data = $.parseJSON(responseText);
				}
				catch(error)
				{
					data =null;
					log(error);
				}
				if(data != null && (data.status == "TIMEOUT" || data.status == "ERROR"))
				{
					success = false;
				}
				if(success==true && (typeof callback) == 'function')
				{
					callback.apply(this,[responseText,textStatus,jqXHR]);
				}
				if(showLoading)
				{
					$('.page-content-area').aceAjax('stopLoading',true);
				}
			});
		}
	});
	
	
})(jQuery || $); 