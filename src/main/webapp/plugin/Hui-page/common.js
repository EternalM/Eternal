/*菜单处于当前状态*/
function leftDropdown(obj){
	var host = window.location.host,
		url = location.href,
		domain = "http://"+host+"/",
		menu = url.replace(domain,"");
	$(obj).each(function(){
		var current = $(this).find("a");
		$(this).removeClass("current");
		if(menu == $(current[0]).attr("href")){
			$(this).addClass("current");
		}
	});
}
$(function(){
	/*新窗口查看代码*/
	$('.codeView').Huihover();
	
	/*返回顶部调用*/
	$.Huitotop();
	
	leftDropdown(".menu_dropdown ul li");
});

function layer_show(title,url,w,h){
	if (title == null || title == '') {
		title=false;
	};
	if (url == null || url == '') {
		url="404.html";
	};
	if (w == null || w == '') {
		w=800;
	};
	if (h == null || h == '') {
		h=($(window).height() - 50);
	};
	layer.open({
		type: 2,
		area: [w+'px', h +'px'],
		fix: false,
		maxmin: true,
		shade:0.4,
		title: title,
		content: url
	});
}
