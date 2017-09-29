var addTabs = function(options) {
	$("#menu li").removeClass("active");
	$("#menu_li_" + options.id).addClass("active");
	var id = "tab_" + options.id;
	$(".nav-tabs .active").removeClass("active");
	$(".tab-content .active").removeClass("active");
	//如果TAB不存在，创建一个新的TAB
	if(!$("#" + id)[0]) {
		//固定TAB中IFRAME高度
		mainHeight = $(document.body).height() - 90;
		//创建新TAB的title
		title = '<li role="presentation" id="tab_' + id + '"><a href="#' + id + '" aria-controls="' + id + '" role="tab" data-toggle="tab">' 
				+ '<span class="glyphicon glyphicon'+options.icon.substr(4)+'" aria-hidden="true"></span>&nbsp;' + options.title;
		//是否允许关闭
		if(options.close) {
			title += ' <i class="glyphicon glyphicon-remove" tabclose="' + id + '"></i>';
		}
		title += '</a></li>';
		//是否指定TAB内容
		if(options.content) {
			content = '<div role="tabpanel" class="tab-pane" id="' + id + '">' + options.content + '</div>';
		} else { //没有内容，使用IFRAME打开链接
			content = '<div role="tabpanel" class="tab-pane" id="' + id + '"><iframe src="' + options.url + '" width="100%" height="' + mainHeight +
				'" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe></div>';
		}
		//加入TABS
		$(".nav-tabs").append(title);
		//加入内容页面
		$(".tab-content").append(content);
		/*******************  增加绑定右键菜单的事件  added by 童晟 2017-4-21 ***********************/
		if(undefined != options.contextMenu && "function" == typeof(options.contextMenu))
			options.contextMenu(id, !options.close);
	}
	//激活TAB
	$("#tab_" + id).addClass('active');
	$("#" + id).addClass("active");
};
var closeTab = function(id) {
	//如果关闭的是当前激活的TAB，激活他的前一个TAB
	if($(".nav-tabs li.active").attr('id') == "tab_" + id) {
		$("#tab_" + id).prev().addClass('active');
		$("#" + id).prev().addClass('active');
	}
	//关闭TAB
	$("#tab_" + id).remove();
	$("#" + id).remove();
};
$(function() {
	mainHeight = $(document.body).height() - 45;
	$('.main-left,.main-right').height(mainHeight);
	$("[addtabs]").click(function() {
		//console.log("添加tab");
		addTabs({ id: $(this).attr("id"), title: $(this).attr('title'), close: true });
	});

	$(".nav-tabs").on("click", "[tabclose]", function(e) {
		id = $(this).attr("tabclose");
		closeTab(id);
	});
});