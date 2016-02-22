/**
 * Top message
 */
$.extend({
	topMessage:function(msg){
		var $body = $("body");
		//set message container
		var $message_container = $body.find(".message-container");
		if($message_container.length == 0)
		{
			var msgContainerDiv = '';
			msgContainerDiv += '<div class="message-container">';
			msgContainerDiv += '</div>';
			$body.prepend(msgContainerDiv);
		}
		else
		{
			$message_container.slideUp(300);
		}
		//set alert div
		$message_container = $(".message-container");
		if($message_container.find(".alert").length == 0)
		{
			var alertDiv = '';
			alertDiv += '<div class="alert alert-danger alert-dismissible" role="alert">';
			alertDiv +=		'<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
			alertDiv +=		'<span class="glyphicon glyphicon-alert"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="message-span"></span>';
			alertDiv += '</div>';
			$message_container.prepend(alertDiv);
		}
		
		//show message
		var $tmc_span = $(".message-span");
		$tmc_span.html(msg);
		$message_container.slideDown(300);
		
		//auto hide
		clearTimeout(window.timer);
		window.timer = setTimeout(function(){
			$message_container.slideUp(300);
		},3000);
		
		$message_container.on('mouseover',function(){
			clearTimeout(window.timer);
		});
		$message_container.on('mouseout',function(){
			window.timer = setTimeout(function(){
				$message_container.slideUp(300);
			},3000);
		});
		
		//close
		$colse = $tmc_span.find(".close");
		$colse.on("click",function() {
			$tip_container.slideUp(300);
		});
	}
	
	
	
});


function log(msg)
{
	if(typeof(console) != 'undefined' && console.log)
	{
		console.log(msg);
	}
}