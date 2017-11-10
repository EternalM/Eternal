<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/content/common/import.jsp" %>
<html>
<head>
<%@ include file="/WEB-INF/content/common/global_head.jsp" %>
<title><devsun:i18n i18nName="admin_user" key="title.user_index"/></title>
</head>
<body>
<section class="Hui-article-box">
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i><devsun:i18n i18nName="admin_user" key="title.account_index"/> <span class="c-gray en">&gt;</span> ><devsun:i18n i18nName="admin_user" key="title.user_index"/></nav>
	<div class="Hui-article">
		<article class="cl pd-20">
			<div class="text-c">
				<!-- 日期范围: <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})" id="datemin" class="input-text Wdate" style="width:120px;">
				-
				<input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d'})" id="datemax" class="input-text Wdate" style="width:120px;"> -->
				<input type="text" class="input-text" style="width:250px" placeholder="<devsun:i18n i18nName="admin_user" key="msg.please_input_name"/>" id="check_user_name" name="check_user_name">
				<button type="button" class="btn btn-success radius" onclick="toPage(1)"><i class="Hui-iconfont">&#xe665;</i><devsun:i18n i18nName="admin_user" key="title.search_user"/></button>
			</div>
			<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="user_delete()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i><devsun:i18n i18nName="admin_user" key="title.batch_delete"/></a> <a href="javascript:;" onclick="user_add()" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> <devsun:i18n i18nName="admin_user" key="title.add_user"/></a></span></div>
			<div class="mt-20">
				<div class="dataTables_wrapper no-footer">
					<table class="table table-border table-bordered table-hover table-bg table-sort">
						<thead>
							<tr class="text-c">
								<th width="25"><input type="checkbox" id="select_all_delete" name="user_check_id" value=""></th>
								<th width="60"><devsun:i18n i18nName="admin_user" key="title.id"/></th>
								<th width="100"><devsun:i18n i18nName="admin_user" key="title.username"/></th>
								<th width="100"><devsun:i18n i18nName="admin_user" key="title.realname"/></th>
								<th width="120"><devsun:i18n i18nName="admin_user" key="title.email"/></th>
								<th width="70"><devsun:i18n i18nName="admin_user" key="title.type"/></th>
								<th width="70"><devsun:i18n i18nName="admin_user" key="title.status"/></th>
								<th width="70"><devsun:i18n i18nName="admin_user" key="title.operate"/></th>
							</tr>
						</thead>
						<tbody id="user_list">
						</tbody>
					</table>
					<div id="uiPage"></div>
				</div>	 
			</div>
		</article>
	</div>
</section>
<div id="user_add_dialogue" style="display:none">
	<form id="frm_user_add" action="" method="post" class="form form-horizontal" >
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><devsun:i18n i18nName="admin_user" key="text.username"/>:</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" placeholder="<devsun:i18n i18nName="admin_user" key="msg.username_length"/>" name="username" id="user_name">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><devsun:i18n i18nName="admin_user" key="text.realname"/>:</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" placeholder="<devsun:i18n i18nName="admin_user" key="msg.realname_length"/>" name="realname" id="real_name">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><devsun:i18n i18nName="admin_user" key="text.password"/>:</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="password" class="input-text" autocomplete="off" placeholder="<devsun:i18n i18nName="admin_user" key="text.password"/>" name="password" id="password">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><devsun:i18n i18nName="admin_user" key="text.re_password"/>:</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="password" class="input-text" autocomplete="off" placeholder="<devsun:i18n i18nName="admin_user" key="text.re_password"/>" name="repwd" id="repwd" recheck="password" datatype="*6-18" nullmsg="<devsun:i18n i18nName="admin_user" key="msg.repwd"/>" errormsg="<devsun:i18n i18nName="admin_user" key="msg.repwd_error"/>" >
			</div>
			<div class="col-xs-8 col-sm-6 col-xs-offset-4 col-sm-offset-3"> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><devsun:i18n i18nName="admin_user" key="text.email"/>:</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" placeholder="<devsun:i18n i18nName="admin_user" key="msg.email"/>" name="email" id="email">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span><devsun:i18n i18nName="admin_user" key="text.type"/>:</label>
			<div class="formControls col-xs-8 col-sm-9 skin-minimal">
				<div class="radio-box">
					<input name="type" type="radio" id="type_one" value="0" checked>
					<label for="type_one"><devsun:i18n i18nName="admin_user" key="title.common_user"/></label>
				</div>
				<div class="radio-box">
					<input type="radio" id="type_two" value="1" name="type">
					<label for="type_two"><devsun:i18n i18nName="admin_user" key="title.admin_user"/></label>
				</div>
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-6 col-xs-offset-4 col-sm-offset-3" style="margin:0 210px;">
				<input class="btn btn-primary" type="submit" value="&nbsp;&nbsp;<devsun:i18n i18nName="admin_user" key="title.submit"/>&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</div>
	
<div id="user_update_dialogue" style="display:none">
	<form id="frm_user_update" action="" method="post" class="form form-horizontal" >
		<div class="row cl">
			<input type="hidden" id="update_user_id">
			<label class="form-label col-xs-4 col-sm-3"><devsun:i18n i18nName="admin_user" key="text.username"/>:</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" placeholder="<devsun:i18n i18nName="admin_user" key="msg.username_length"/>" name="username" id="update_user_name" readonly="true" style="background-color:#CCC" >
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><devsun:i18n i18nName="admin_user" key="text.realname"/>:</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" placeholder="<devsun:i18n i18nName="admin_user" key="msg.realname_length"/>" name="realname" id="update_real_name">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><devsun:i18n i18nName="admin_user" key="text.password"/>:</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="password" class="input-text" autocomplete="off" placeholder="<devsun:i18n i18nName="admin_user" key="text.password"/>" name="password" id="update_password">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><devsun:i18n i18nName="admin_user" key="text.email"/>:</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" placeholder="<devsun:i18n i18nName="admin_user" key="msg.email"/>" name="email" id="update_email">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span><devsun:i18n i18nName="admin_user" key="text.type"/>:</label>
			<div class="formControls col-xs-8 col-sm-9 skin-minimal">
				<div class="radio-box">
					<input name="update_type" type="radio" id="update_type_one" value="0" checked>
					<label for="type_one"><devsun:i18n i18nName="admin_user" key="title.common_user"/></label>
				</div>
				<div class="radio-box">
					<input type="radio" id="update_type_two" value="1" name="update_type">
					<label for="type_two"><devsun:i18n i18nName="admin_user" key="title.admin_user"/></label>
				</div>
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-6 col-xs-offset-4 col-sm-offset-3" style="margin:0 210px;">
				<input class="btn btn-primary" type="submit" value="&nbsp;&nbsp;<devsun:i18n i18nName="admin_user" key="title.submit"/>&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</div>
	
<div id="user_view_dialogue" style="display:none">
	<div class="cl pd-20" style=" background-color:#5bacb6">
	  <dl style="margin-left:140px; color:#fff">
	    <dt><span class="f-18" id="view_username"></span></dt>
	    <dd class="pt-10 f-12" style="margin-left:0" id="view_bankmoney"></dd>
	  </dl>
	</div>
	<div class="cl pd-20">
	  <table class="table">
	    <tbody>
	      <tr>
	        <th class="text-r"><devsun:i18n i18nName="admin_user" key="text.realname"/>:</th>
	        <td id="view_realname"></td>
	      </tr>
	      <tr>
	        <th class="text-r"><devsun:i18n i18nName="admin_user" key="text.email"/>:</th>
	        <td id="view_email"></td>
	      </tr>
	      <tr>
	        <th class="text-r"><devsun:i18n i18nName="admin_user" key="text.type"/>:</th>
	        <td id="view_type"></td>
	      </tr>
	    </tbody>
	  </table>
	</div>
</div>
</body>
<%@ include file="/WEB-INF/content/common/scripts.jsp" %>
<devsun:script src="/js/utils/page_utils.js" cache="true"/>
<devsun:script src="/plugin/jquery-validation/jquery.validate.min.js" cache="true"/>
<devsun:script src="/plugin/jquery-validation/validate-methods.js"/>
<script>
	var pageIndex = 1;
	var index_add;
	var index_update;
	var index_del;
	var index_del_more;
	var index_status;
	
	$(document).ready(function () {
		initI18nProperties("admin_user");
		load();
		add_info();
		update_info();
	});
	
	//查询点击事件
	function toPage(i){
		pageIndex = i;
		load();
	}
	
	function load(){
		var data = {'pageIndex':pageIndex,'like_username':$("#check_user_name").val()};
		ajaxGet(basePath + '/admin/user/index.json',data,function(data){
			var obj = data;
			var pageResult = obj.userList;
			var list = pageResult.result;
			userList(list);
			var paging = buildPageHtml(pageResult.pageNum,pageResult.pageSize,pageResult.total,"toPage");
			$('#uiPage').html(paging);
		});
	}
	
	//列表
	function userList(list){
		var html = '';
		var i = 1;
		$.each(list,function(){
			html +='<tr class="text-c">';
			html +='<td><input type="checkbox" class="user_check_id_class" name="user_check_id" value="'+ this.id +'"></td>';
			html +='<td>'+i+'</td>';
			html +='<td><u style="cursor:pointer" onclick="user_view('+this.id+')" class="text-primary" >'+this.username+'</u></td>';
			if(this.realname!=null && this.realname !=''){
				html +='<td>'+this.realname+'</td>';
			}else {
				html +='<td> -- </td>';
			}
			
			html +='<td>'+this.email+'</td>';
			if(this.type==0){
				html +='<td>'+$.i18n.prop("title.common_user")+'</td>';
			}else if(this.type==1){
				html +='<td>'+$.i18n.prop("title.admin_user")+'</td>';
			}
			if(this.status==0){
				html +='<td class="td-status"><span class="label label-success radius" style="background-color:#CCC;">'+$.i18n.prop("title.forbidden")+'</span></td>';
			}else if(this.status==1){
				html +='<td class="td-status"><span class="label label-success radius">'+$.i18n.prop("title.start_using")+'</span></td>';
			}
			html +='<td class="td-manage">';
			if(this.status==0){
				html +='<a style="text-decoration:none; font-size:20px;"  onclick="revise_status('+this.id+','+this.status+')" title="'+$.i18n.prop("title.start_using")+'"><i class="Hui-iconfont">&#xe6e1;</i></a>'; 
			}else if(this.status==1){
				html +='<a style="text-decoration:none; font-size:20px;"  onclick="revise_status('+this.id+','+this.status+')" title="'+$.i18n.prop("title.forbidden")+'"><i class="Hui-iconfont">&#xe631;</i></a>'; 
			}
			
			html +='<a title="'+$.i18n.prop("title.edit")+'" href="javascript:;" onclick="user_update('+this.id+')" class="ml-5" style="text-decoration:none; font-size:20px;"><i class="Hui-iconfont">&#xe6df;</i></a>';
			html +='<a title="'+$.i18n.prop("title.delete")+'" href="javascript:;" onclick="member_del('+this.id+')" class="ml-5" style="text-decoration:none; font-size:20px;"><i class="Hui-iconfont">&#xe6e2;</i></a>';
			html +='</td>';
			html +='</tr>';
			i++;
		});
		$("#user_list").html(html);
	}
	
	//新增
	function user_add(){
		$("#user_name").val('');
    	$("#real_name").val('');
    	$("#password").val('');
    	$("#repwd").val('');
    	$("#email").val('');
    	$(".error").html('');
		$(".error").removeClass("error");
		index_add = layer.open({
			type: 1,
			area: ['500px','390px'],
			fix: false,
			maxmin: false,
			shade:0.4,
			title: $.i18n.prop("title.add_user"),
			content: jQuery('#user_add_dialogue')
		});
	}
	
	function add_info(){
		//添加验证(新增)
		$("#frm_user_add").validate({
			rules:{
				username:{
					required: true,
					checkName: true,
					remote : { // 验证用户名是否存在
						type : "POST",
						url : basePath + "/username_is_exist.json",
						data : {
							username : function(){
								return $("#user_name").val()
							} 
						}
					}
				},
				realname:{
					required: true,
					minlength : 2,
					maxlength : 100
				},
				password:{
					required: true,
					minlength : 6,
					maxlength : 18
				},
				repwd:{
					required: true,
					equalTo: "#password"
				},
				email:{
					required: true,
					maxlength : 50,
					checkEmail: true,
					remote : { 			// 验证邮箱是否重复
						type : "POST",
						url : basePath + "/email_is_exist.json",
						data : {
							username : function(){
								return $("#email").val()
							} 
						}
					}
				}
			},
			messages:{
				username:{
					required: $.i18n.prop("msg.please_input_name"),
					checkName: $.i18n.prop("msg.username_length"),
					remote: $.i18n.prop("msg.name_same")
				},
				realname:{
					required: $.i18n.prop("msg.please_input_name"),
					minlength :$.i18n.prop("msg.name_min_length"),
					maxlength :$.i18n.prop("msg.name_max_length")
				},
				password:{
					required: $.i18n.prop("msg.please_input_password"),
					minlength :$.i18n.prop("msg.password_min_length"),
					maxlength :$.i18n.prop("msg.password_max_length")
				},
				repwd:{
					required: $.i18n.prop("msg.repwd"),
					equalTo: $.i18n.prop("msg.repwd_error")
				},
				email: {
					required: $.i18n.prop("msg.please_input_email"),
					maxlength :$.i18n.prop("msg.email_max_length"),
					checkEmail: $.i18n.prop("msg.email"),
					remote: $.i18n.prop("msg.email_same")
				}
			},
			submitHandler:function(form){
				var data = {'userName':$("#user_name").val(),
				    	'realName':$("#real_name").val(),'password':$("#password").val(),
				    	'email':$("#email").val(),'type':$("input[name='type']:checked").val()
				    	};
				ajaxPost(basePath + '/admin/user/add.json',data,function(data){
			    	if(data.result){
			    		layer.close(index_add);
				    	layer.msg($.i18n.prop("msg.add_success"));
				    	load();
			    	}
				 });
			},
			invalidHandler: function(form, validator) {return false;}
		});
	}
	//修改
	function user_update(id){
		var data ={'userId':id};
		ajaxGet(basePath + '/admin/user/view.json',data,function(data){
			$("#update_user_id").val(data.user.id);
	    	$("#update_user_name").val(data.user.username);
	    	$("#update_real_name").val(data.user.realname);
	    	$("#update_email").val(data.user.email);
	    	if(data.user.type=='0'){
				$("input[name=update_type][value='0']").attr("checked",true);
			}else if(data.user.type=='1'){
				$("input[name=update_type][value='1']").attr("checked",true);
			}
	    	
	    	$(".error").html('');
			$(".error").removeClass("error");
			index_update = layer.open({
				type: 1,
				area: ['500px','330px'],
				fix: false,
				maxmin: false,
				shade:0.4,
				title: $.i18n.prop("title.update_user"),
				content: jQuery('#user_update_dialogue')
			});
		 });
	}
	
	function update_info(){
		//添加验证（修改）
		$("#frm_user_update").validate({
			rules:{
				realname:{
					required: true,
					minlength: 2,
					maxlength: 100
				},
				email:{
					required: true,
					maxlength : 50,
					checkEmail: true,
					remote : { 			// 验证邮箱是否重复
						type : "POST",
						url : basePath + "/email_is_exist.json",
						data : {
							email : function(){
								return $("#update_email").val()
							},
							id: function(){
								return $("#update_user_id").val()
							}
						}
					}
				}
			},
			messages:{
				realname:{
					required: $.i18n.prop("msg.please_input_name"),
					minlength :$.i18n.prop("msg.name_min_length"),
					maxlength :$.i18n.prop("msg.name_max_length")
				},
				email: {
					required: $.i18n.prop("msg.please_input_email"),
					maxlength :$.i18n.prop("msg.email_max_length"),
					checkEmail: $.i18n.prop("msg.email"),
					remote: $.i18n.prop("msg.email_same")
				}
			},
			submitHandler:function(form){
				var data ={'userName':$("#update_user_name").val(),'userId':$("#update_user_id").val(),'realName':$("#update_real_name").val(),'password':$("#update_password").val(),'email':$("#update_email").val(),'type':$("input[name='update_type']:checked").val()};
				ajaxPost(basePath + '/admin/user/update.json',data,function(data){
					if(data.result){
			    		layer.close(index_update);
				    	layer.msg($.i18n.prop("msg.update_success"));
				    	load();
			    	}
				 });
				
			},
			invalidHandler: function(form, validator) {return false;}
		});
	}
	
  	//修改状态
	function revise_status(id,status){
		index_status = layer.confirm($.i18n.prop("msg.please_sure_status"),function(index){
			var data = {'userId':id,'userStatus':status};
			ajaxPost(basePath + '/admin/user/revise_status.json',data,function(data){
		    	if(data.result){
		    		layer.close(index_status);
		    		layer.msg($.i18n.prop("msg.update_success"));
			    	load();
		    	}
			});
		});
	}
	//加载详情
	function user_view(id){
		var data= {'userId':id};
		ajaxGet(basePaht + '/admin/user/view.json',data,function(data){
	    	$("#view_username").html(data.user.username);
	    	$("#view_realname").html(data.user.realname);
	    	$("#view_bankmoney").html($.i18n.prop("text.paymoney")+':'+data.user.bankMoney);
	    	$("#view_email").html(data.user.email);
	    	if(data.user.type==0){
	    		$("#view_type").html($.i18n.prop("title.common_user"));
			}else if(data.user.type==1){
				$("#view_type").html($.i18n.prop("title.admin_user"));
			}
	    	
	    	layer.open({
				type: 1,
				area: ['500px','320px'],
				fix: false,
				maxmin: false,
				shade:0.4,
				title: $.i18n.prop("title.view"),
				content: jQuery('#user_view_dialogue')
			});
		});
	}
	
	//删除
    function member_del(id){
		index_del = layer.confirm($.i18n.prop("msg.please_sure_del"), function(index_del){
			var data = {'delete_id':id};
			ajaxPost(basePath + '/admin/user/delete_by_id.json',data,function(data){
		    	if(data.result='true'){
		    		layer.close(index_del);
		    		layer.msg($.i18n.prop("msg.delete_success"));
			    	load();
		    	}
			 });
		});       
	}
	
	//批量删除
	function user_delete(){
		var value_del = '';
		$('input[name="user_check_id"]:checked').each(function(){
			if(value_del!=''){
				value_del += ',';
			}
			value_del += $(this).val();
		});
		
  		if(value_del!='' && value_del!=null){
  			index_del_more = layer.confirm($.i18n.prop("msg.please_sure_del"), function(index_del_more){
  				var data = {'delete_id':value_del};
  				ajaxPost(basePath + '/admin/user/delete.json',data,function(data){
					if(data.result){
						layer.close(index_del_more);
						toPage(1);
						layer.msg($.i18n.prop("msg.delete_success"));
					}
				});
  			});
		}else{
			layer.msg($.i18n.prop("msg.error_msg"));
		}
	}
</script>
</html>
