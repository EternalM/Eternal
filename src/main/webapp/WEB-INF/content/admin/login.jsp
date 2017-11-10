<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/content/common/import.jsp" %>
<html>
<head>
<title>Login</title>
<devsun:link src="/css/admin/login.css"/>
<style type="text/css">
	label.error {
	    position: absolute;
		right: 18px;
		top: 13px;
		color: #ef392b;
		font-size: 13px;
		background-color: beige;
	}
	.formControls {
		position: relative;
	}
	#uservcode {
		background-position: 10px 10px !important
	}

	#vcode {
	    height: 46px;
	    width: 116px;
	}
	.vocde_img{
		float: left;
		padding: 0px;
	}
	#vcode_div label.error{
		position: absolute;
		right: 145px;
		top: 13px;
		color: #ef392b;
		font-size: 13px;
		background-color: beige;
	}
</style>
</head>
<body>
	<section id="content">
		<form method="post" id="frm_admin_login">
			<h1><devsun:i18n i18nName="admin_index" key="title.login"/></h1>
			<div class="formControls">
				<div  >
					<input type="text" placeholder="<devsun:i18n i18nName="admin_index" key="text.username"/>" name="username" id="username">
				</div>
			</div>
			<div class="formControls">
				<div >
					<input type="password" autocomplete="off" placeholder="<devsun:i18n i18nName="admin_index" key="text.password"/>" name="password" id="password">
				</div>
			</div>
			<div class="formControls" id="vcode_div" style="margin-left: 10px;">
				<div class="vocde_img">
					<input type="text" placeholder="<devsun:i18n i18nName="admin_index" key="text.vcode"/>" name="vcode" id="uservcode" style="width: 165px;">&nbsp;&nbsp;
				</div>
				<div class="vocde_img">
					<img src="<%=basePath%>/images/vcode.jpg" class="vcode" id="vcode" onclick="refreshVcode()"/>
				</div>
			</div>
			<div>
				<input type="submit" value="<devsun:i18n i18nName="admin_index" key="title.login"/>">
			</div>
		</form>
	</section>
</body>

<devsun:script src="/plugin/jquery/jquery-1.12.3.min.js" cache="true"/>
<devsun:script src="/plugin/jquery-validation/jquery.validate.min.js" cache="true"/>
<devsun:script src="/plugin/layer/layer.js" cache="true"/>
<devsun:script src="/js/utils/util.js" cache="true"/>
<devsun:script src="/plugin/jquery-i18n/jquery.i18n.properties-1.0.9_update.js" cache="true"/>

<script type="text/javascript">
	if (self == top) {
		document.documentElement.style.display = 'block'
	} else {
		top.location = self.location
	}
	
	jQuery(document).ready(function(){
		initI18nProperties("admin_index");
		$('#username').focus();
		admin_login();
	});
	//新增
	function admin_login(){
		//添加验证
		$("#frm_admin_login").validate({
			rules:{
				username:{
					required: true,
					checkName: true
				},
				password:{
					required: true,
					minlength : 6,
					maxlength : 18
				},
				vcode:{
					required:true
				}
			},
			messages:{
				username:{
					required: $.i18n.prop("msg.please_input_name"),
					checkName: $.i18n.prop("msg.username_length")
				},
				password:{
					required: $.i18n.prop("msg.please_input_password"),
					minlength :$.i18n.prop("msg.password_min_length"),
					maxlength :$.i18n.prop("msg.password_max_length")
				},
				vcode:{
					required: $.i18n.prop("msg.please_input_vcode")
				}
			},
			submitHandler:function(form){
				var data = {'username':$("#username").val(),'password':$("#password").val(),'vcode':$("#uservcode").val()};
				ajaxPost('<%=basePath%>/admin/login.json',data,function(data){
			    	if(data=='0'){
			    		window.location.href='<%=basePath%>/admin';
			    	}
			    	else if(data=='1'){
			    		layer.msg($.i18n.prop("msg.param_error"));
			    	}
			    	else if(data=='2'){
			    		layer.msg($.i18n.prop("msg.user_error"));
			    	}
			    	else if(data=='3'){
			    		layer.msg($.i18n.prop("msg.user_pwd_error"));
			    	}
			    	else if(data=='4'){
			    		layer.msg($.i18n.prop("msg.vcode_error"));
			    	}
			    	refreshVcode();
				 });
			},
			invalidHandler: function(form, validator) {return false;}
		});
	}
	
	//自定义姓名验证
	$.validator.addMethod("checkName",function(value,element,params){
	    var checkName = /^\w{4,16}$/g;  
	    return this.optional(element)||(checkName.test(value));  
	});
	
	//验证码
	function refreshVcode(){
		document.getElementById("vcode").src="<%=basePath %>/images/vcode.jpg?r="+Math.random();
	}
</script>
</html>
