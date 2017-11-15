<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/content/common/import.jsp" %>
<html>
<head>
<title>Index</title>
</head>
<body>
	<a href="<%=basePath %>/api/mxd2/macro/all.json" target="_blank">键盘宏</a>
	<br/>
	<a href="<%=basePath %>/api/mxd2/oxanswer/all.json" target="_blank">OX答题</a>
</body>
<devsun:script src="/plugin/jquery/jquery-1.12.3.min.js" cache="true"/>
<script>
	$(document).ready(function () {
		
	});
</script>
</html>
