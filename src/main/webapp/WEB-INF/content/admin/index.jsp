<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/content/common/import.jsp" %>
<html>
<head>
<%@ include file="/WEB-INF/content/common/global_head.jsp" %>
<devsun:link src="/plugin/Hui-page/skin/default/skin.css" cache="true"/>
<title><devsun:i18n i18nName="admin_index" key="title.index"/></title>
<style type="text/css">
	.selecthover{background-color:#FAFAFA}
</style>
</head>
<body>
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl">
			<a class="logo navbar-logo f-l mr-10 hidden-xs" href="javascript:void(0);">
				<devsun:i18n i18nName="admin_index" key="title.message"/>
			</a>
			<span class="logo navbar-slogan f-l mr-10 hidden-xs">v1.0</span> 
			<!-- 
			<a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
			<nav class="nav navbar-nav">
				<ul class="cl">
					<li class="dropDown dropDown_hover"><a href="javascript:;" class="dropDown_A"><i class="Hui-iconfont">&#xe600;</i> 新增 <i class="Hui-iconfont">&#xe6d5;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
							<li><a href="javascript:;" onclick="article_add('添加资讯','article-add.html')"><i class="Hui-iconfont">&#xe616;</i> 资讯</a></li>
							<li><a href="javascript:;" onclick="picture_add('添加资讯','picture-add.html')"><i class="Hui-iconfont">&#xe613;</i> 图片</a></li>
							<li><a href="javascript:;" onclick="product_add('添加资讯','product-add.html')"><i class="Hui-iconfont">&#xe620;</i> 产品</a></li>
							<li><a href="javascript:;" onclick="member_add('添加用户','member-add.html','','510')"><i class="Hui-iconfont">&#xe60d;</i> 用户</a></li>
						</ul>
					</li>
				</ul>
			</nav>
			-->
			<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
				<ul class="cl">
					<li>${login.realname}</li>
					<li class="dropDown dropDown_hover"> <a href="#" class="dropDown_A">${login.username} <i class="Hui-iconfont">&#xe6d5;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
							<!-- 
							<li><a href="javascript:void(0);">个人信息</a></li>
							<li><a href="javascript:void(0);">切换账户</a></li>
							 -->
							<li><a href="javascript:loginoutClick()"><devsun:i18n i18nName="admin_index" key="title.exit"/></a></li>
						</ul>
					</li>
					<!-- <li id="Hui-msg"> <a href="#" title="消息"><span class="badge badge-danger">1</span><i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a> </li> -->
				</ul>
			</nav>
		</div>
	</div>
</header>
<aside class="Hui-aside">
	<div class="menu_dropdown bk_2">	
		<dl>
			<dt><i class="Hui-iconfont">&#xe60d;</i> <devsun:i18n i18nName="admin_index" key="title.account_index"/><i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul class="category" >
					<li><a target="mainframe" href="<%=basePath %>/admin/user/index" title="<devsun:i18n i18nName="admin_index" key="title.user_index"/>"><devsun:i18n i18nName="admin_index" key="title.user_index"/></a></li>
				</ul>
			</dd>
		</dl>
		<dl>
			<dt><i class="Hui-iconfont">&#xe60d;</i> 资料<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul class="category" >
					<li><a target="mainframe" href="<%=basePath %>/admin/result1/index" title="Result1">Result1</a></li>
				</ul>
			</dd>
		</dl>
	</div>
</aside>
<div id="iframe_box" class="Hui-article">
	<div class="show_iframe">
		<iframe name="mainframe" scrolling="yes" frameborder="0" src="<%=basePath %>/admin/welcome"></iframe>
	</div>
</div>
</body>
<%@ include file="/WEB-INF/content/common/scripts.jsp" %>
<script type="text/javascript">
	if (self == top) {
		document.documentElement.style.display = 'block'
	} else {
		top.location = self.location
	}
	
	$(function () {
		initI18nProperties("admin_index");
		$(".category li").click(function(){
			$("li[class='selecthover']").removeAttr("class");
			$(this).addClass("selecthover");
		});
	});
	
	//登出
	function loginoutClick(){
		//询问框
		layer.confirm($.i18n.prop("msg.please_sure_exit"), function(){
			jQuery.ajax({
				url: "<%=basePath %>/admin/logout.json",
				type: "post",
				success:function(){
					location.href="<%=basePath %>/admin/login";
				}
			});
		}, function(){
			
		});
	}
</script>
</html>
