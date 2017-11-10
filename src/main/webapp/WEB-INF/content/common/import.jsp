<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String basePath;
	if(com.devsun.eternal.common.Memory.basePath!=null){
		basePath = com.devsun.eternal.common.Memory.basePath;
	}
	else{
		String path = request.getContextPath();
		//basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		basePath = path;
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="devsun" uri="devsun-tags"%>
<script type="text/javascript">
var basePath = '<%=basePath %>';
</script>
