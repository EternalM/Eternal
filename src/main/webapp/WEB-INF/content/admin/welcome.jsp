<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/content/common/import.jsp" %>
<html>
<head>
<%@ include file="/WEB-INF/content/common/global_head.jsp" %>
<devsun:link src="/plugin/Hui-page/skin/default/skin.css" cache="true"/>
<style>
	#welcome_footer{
		margin-left:380px;
	}
</style>
</head>
<body>
<section class="Hui-article-box">
	<nav class="breadcrumb"><i class="Hui-iconfont"></i> <a href="/" class="maincolor"><devsun:i18n i18nName="admin_index" key="title.home_page"/></a> 
		<span class="c-999 en">&gt;</span>
		<span class="c-666"><devsun:i18n i18nName="admin_index" key="title.welcome"/></span> 
	</nav>
	<div class="Hui-article">
		<article class="cl pd-20">
			<p class="f-20 text-success">Welcome 
				<devsun:i18n i18nName="admin_index" key="title.welcome"/>
				<span class="f-14">v1.0</span>
			</p>
			<p>
				<img src="<%=basePath%>/images/welcome.jpg" style="width:100%;height:100%;"/>
			</p>
		</article>
		<footer >
			<div id="welcome_footer">
				<nav> <a href="#" target="_blank"><devsun:i18n i18nName="admin_index" key="title.about_our"/></a> <span class="pipe">|</span> <a href="#" target="_blank"><devsun:i18n i18nName="admin_index" key="title.call_our"/></a> <span class="pipe">|</span> <a href="#" target="_blank"><devsun:i18n i18nName="admin_index" key="title.low_about"/></a> </nav>
				<p>
					Copyright &copy;2017 DEVSUN.COM
				</p>
			</div>
		</footer>
	</div>
</section>
</body>
</html>
