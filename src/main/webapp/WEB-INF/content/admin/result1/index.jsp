<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/content/common/import.jsp" %>
<html>
<head>
<%@ include file="/WEB-INF/content/common/global_head.jsp" %>
<title>Result1</title>
<style type="text/css">
	.list_item {
		float:left;
		margin: 5px;
	}
	.list_item img {
		width:244px;
		height:304px;
	}
</style>
</head>
<body>
<section class="Hui-article-box">
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i>Result1</nav>
	<div class="Hui-article">
		<article class="cl pd-20">
			<div class="text-c">
				<input type="text" class="input-text" style="width:250px" placeholder="name" id="name" name="name" />
				<input type="text" class="input-text" style="width:250px" placeholder="image" id="image" name="image" />
				<input type="text" class="input-text" style="width:250px" placeholder="pUrl" id="pUrl" name="pUrl" />
				<button type="button" class="btn btn-success radius" onclick="toPage(1)"><i class="Hui-iconfont">&#xe665;</i>搜索</button>
			</div>
			<div class="mt-20">
				<div class="dataTables_wrapper no-footer">
					<div id="uiList"></div>
					<div id="uiPage"></div>
				</div>	 
			</div>
		</article>
	</div>
</section>
</body>
<%@ include file="/WEB-INF/content/common/scripts.jsp" %>
<devsun:script src="/js/utils/page_utils.js" cache="true"/>
<script>
	var pageIndex = 1;
	
	$(document).ready(function () {
		load();
	});
	
	//查询点击事件
	function toPage(i){
		pageIndex = i;
		load();
	}
	
	function load(){
		var data = {
				'pageIndex':pageIndex,
				'name':$("#name").val(),
				'image':$("#image").val(),
				'pUrl':$("#pUrl").val()};
		ajaxPost(basePath + '/admin/result1/list.json',data,function(data){
			var obj = data;
			var pageResult = obj.list;
			var list = pageResult.result;
			var imageHome = obj.imageHome;
			listFun(list,imageHome);
			var paging = buildPageHtml(pageResult.pageNum,pageResult.pageSize,pageResult.total,"toPage");
			$('#uiPage').html(paging);
		});
	}
	
	//列表
	function listFun(list,imageHome){
		var html = '';
		var i = 1;
		$.each(list,function(){
			html +='<div class="list_item">';
			html +='<a target="_blank" href="http://'+imageHome+this.pUrl+'"><img alt="" src="'+basePath+'/upload'+this.image+'" /></a>';
			html +='<br/>';
			var printName = this.name;
			if(this.name.length>14){
				printName = this.name.substring(0,14)+"...";
			}
			html +='<div title="'+this.name+'">'+printName+'</div>';
			html +='</div>';
			i++;
		});
		$("#uiList").html(html);
	}
</script>
</html>
