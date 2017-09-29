(function($) {
	$.fn.sidebarMenu = function(options) {
		options = $.extend({}, $.fn.sidebarMenu.defaults, options || {});
		var target = $(this);
		target.addClass('nav');
		target.addClass('nav-list');
		if(options.data) {
			init(target, options.data);
		} else {
			if(!options.url) return;
			$.getJSON(options.url, options.param, function(data) {
				init(target, data);
				/**  增加构造完菜单后触发的事件 added by ts 2017-3-29 */
				if (null != options.loadSuccess)  options.loadSuccess();
			});
		}
		var url = window.location.pathname;
		//menu = target.find("[href='" + url + "']");
		//menu.parent().addClass('active');
		//menu.parent().parentsUntil('.nav-list', 'li').addClass('active').addClass('open');
		function init(target, data) {
			$.each(data, function(i, item) {
				var li = $('<li id=\'menu_li_' + item.id + '\'></li>');
				var a = $('<a></a>');
				var icon = $('<i></i>');
				icon.addClass(item.icon);
				var text = $('<span></span>');
				text.addClass('menu-text').text(item.text);
				a.append(icon);
				a.append(text);
				if(item.menus && item.menus.length > 0) {
					a.attr('href', '#');
					a.addClass('dropdown-toggle');
					var arrow = $('<b></b>');
					arrow.addClass('arrow').addClass('icon-angle-down');
					a.append(arrow);
					li.append(a);
					var menus = $('<ul></ul>');
					menus.addClass('submenu');
					init(menus, item.menus);
					li.append(menus);
				} else {
					/** close属性已经设置默认值，不需要再判断 added by ts 2017-3-29
					 *  if(item.close == undefined) item.close = true;
					 */
					var href = 'javascript:addTabs({id:\'' + item.id + '\',title: \'' + item.text + '\',icon: \''+item.icon+'\',close: ' + item.close 
						+ ',url: \'' + item.url + '\',contextMenu: bindContextMenu  });';
					a.attr('href', href);
					//if (item.istab)
					// a.attr('href', href);
					//else {
					// a.attr('href', item.url);
					// a.attr('title', item.text);
					// a.attr('target', '_blank')
					//}
					li.append(a);
				}
				target.append(li);
			});
		}
	}

	$.fn.sidebarMenu.defaults = {
		url: null,
		param: null,
		data: null,
		loadSuccess: null
	};
})(jQuery);