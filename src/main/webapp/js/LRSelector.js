
	$.fn.extend({
		jeesel:function(options){
			console.log(options);
			
			var defaults = {
					leftTitle:"已选项目",
					leftId:id+"_leftId",
					rightTitle:"待选项目",
					rightId:id+"_rightId"
			};
			var options = $.extend(defaults,options);
			var $this = $(this);
			var id = $this.attr("id")
			
			var template = '';
			template += '<div class="col-sm-5">';
			template += '	<fieldset>';
			template += '		<legend>${title}</legend>';
			template += '		<select id="${id}">';
			template += '		</select>';
			template += '	</fieldset>';
			template += '</div>';
			
			var left = template.replace('${title}',options.leftTitle).replace('${id}',options.leftId);
			var right = template.replace('${title}',options.rightTitle).replace('${id}',options.rightId);
			$this.append(left);
			
			$this.append(right);
			
		}
	});
	
	
