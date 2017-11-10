
var page=1;

$(function(){
	user_document_list(1);
	$("#tab_demo").Huitab();
});

//用户修改按钮相似与隐藏
$("#user_info").hover(
	function(){
		$("#user_up").show();
	},
   	function(){
		$("#user_up").hide();
	}
);

//绑定用户信息
function find_user_info(data){
	$(".error").html('');
	$(".error").removeClass("error");
	var user=data.user;
	if(user!=null){
		$("#email").val(user.email);
		if(user.emailStatus==1){
			$("#email").attr('disabled','true');
		}
		$("#realname").val(user.realname);
		$("input:radio[name='sex'][value='" + user.sex + "']").click();
		$("#brithday").val(dateFormat(user.brithday, 'yyyy-MM-dd'));
		$("#position").val(user.position);
	}
}
//根据uuid查询用户信息
function get_user_info(){
	var data={'uuid':$("#uuid").val()};
	ajaxPost(basePath+'/user/get_info.json',data,function(data){
		//调用绑定方法
		find_user_info(data);
	 });
}
//修改用户按钮点击事件
function up_user_click(){
	get_user_info();
	var update_users_info=layer.open({
		type : 1,
		area : [ '590px', '400px' ],
		fix : false,
		maxmin : false,
		shade : 0.4,
		title : '编辑简介',
		content : jQuery('#up_user'),
		end: function(){
			$("#realname").val('');
			$("input:radio[name='sex']").removeAttr('checked');
			$("#brithday").val('');
			$("#position").val('');
			$("#email").attr('');
			$(".error").html('');
			$(".error").removeClass("error");
		}
	});
	$("#frmUpUser").validate({
		rules : {
			email : {
				required : true,
				maxlength : 50,
				checkEmail : true,
				remote : { // 验证邮箱是否存在
					type : "POST",
					url : basePath+"/email_is_exist.json", // servlet
					data : {
						email : function() {
							return $("#email").val();
						},
						id:function(){
							return $("#id").val();
						}
					}
				}
			},
			realname : {
				required : true,
				minlength: 2,
				maxlength: 100
			},
			position : {
				minlength: 2,
				maxlength: 90
			}
		},
		messages : {
			email : {
				required: "请输入邮箱",
				maxlength: "邮箱长度不能超过50位",
				checkEmail: "请输入正确的邮箱格式",
				remote : "邮箱已经被注册"
			},
			realname : {
				required : "请填写实名",
				minlength: "名称最少2位",
				maxlength: "名称最多100位"
			},
			position : {
				minlength: "职位最少2位",
				maxlength: "职位最多90位"
			}
		},
		onkeyup : false,
		focusCleanup : false,
		success : "valid",
		submitHandler : function(form) {
			$("#modal-shenqing-success").modal("show");
			$(form).ajaxSubmit({
				type : 'post', // 提交方式 get/post
				url : 'up_user_info.json', // 需要提交的 url
				success : function(data) {
					if (data!=null) {
						layer.closeAll();
						layer.msg('保存成功');
						$('#user_realname').html(data.realname);
						//职位
						if(data.position==null || data.position==''){
							$('#user_position').html('职位未填写');
							$('#user_position').addClass('c-red');
						}
						else{
							$('#user_position').html(data.position);
							$('#user_position').removeClass('c-red');
						}
						//性别
						if(data.sex=="0"){
							$('#user_sex').html('男');
							$('#user_sex').removeClass('c-red');
						}
						else if(data.sex=="1"){
							$('#user_sex').html('女');
							$('#user_sex').removeClass('c-red');
						}
						else if(data.sex=="2"){
							$('#user_sex').html('性别保密');
							$('#user_sex').removeClass('c-red');
						}
						else{
							$('#user_sex').html('性别未填写');
							$('#user_sex').addClass('c-red');
						}
						//生日
						if(data.brithday!=null){
							$('#user_brithday').html(dateFormat(data.brithday,"yyyy-MM-dd"));
							$('#user_brithday').removeClass('c-red');
						}
						else{
							$('#user_brithday').html('生日未填写');
							$('#user_brithday').addClass('c-red');
						}
						//邮箱
						$('#user_email').html(data.email);
					}
					else{
						layer.msg('保存失败，请稍后重试');
					}
				},
				error : function(XMLHttpRequest) {
					alert(XMLHttpRequest.status);
				},
			})
		}
	});
}

//用户修改按钮相似与隐藏
$("#user_img_dis").hover(
	function(){
		$("#user_img").show();
	},
  	function(){
  		$("#user_img").hide();
  	}
);

 //修改头像按钮点击事件
 function up_img_click(){
	var update_users_img=layer.open({
		type : 1,
		area : [ '690px', '520px' ],
		fix : false,
		maxmin : false,
		shade : 0.4,
		title : '上传头像',
		content : jQuery('#up_img'),
		end: function(){
			$('.imgareaselect-selection').parent().hide();
			$('.imgareaselect-outer').hide();
		}
	});
	//重置属性
	$('#x1').val('0');
	$('#y1').val('0');
	$('#width').val('300');
	$('#height').val('300');
	
	$('img#select').imgAreaSelect({
		selectionColor:'blue',
		maxWidth:400,
		maxHeitht:400,
		minWidth:50,
		minHeight:50,
		selectionOpacity:0.1,
		handles: true,
		aspectRatio:'1:1',
		onSelectEnd:preview
	});
	
//绑定上传头像
$("#imgFile").change(function(){
	isuploadimg = true;
	var objUrl = getObjectURL(this.files[0]) ;
	console.log("objUrl = "+objUrl) ;
	if (objUrl) {
		$("#select").attr("src", objUrl) ;
		$('#preview100').attr("src",objUrl);
		$('#preview200').attr("src",objUrl);
	}
});
//建立一個可存取到該file的url
function getObjectURL(file) {
	var url = null ; 
	if (window.createObjectURL!=undefined) { // basic
		url = window.createObjectURL(file) ;
	} else if (window.URL!=undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file) ;
	} else if (window.webkitURL!=undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file) ;
	}
	return url ;
}

	function getValue(selection){
		$('input[name="x1"]').val(selection.x1);
		$('input[name="y1"]').val(selection.y1);
		$('input[name="width"]').val(selection.width);
		$('input[name="height"]').val(selection.height);      
	}

function preview(img, selection) {
    if(selection.width>49){
        getValue(selection);
        var scaleX = 200 / (selection.width || 1);
        var scaleY = 200 / (selection.height || 1);
        
        var scaleX1 = 100 / (selection.width || 1);
        var scaleY1 = 100 / (selection.height || 1);
        $('#preview200').css({
        width: Math.round(scaleX * 300) + 'px',
        height: Math.round(scaleY * 300) + 'px',
        marginLeft: '-' + Math.round(scaleX * selection.x1) + 'px',
        marginTop: '-' + Math.round(scaleY * selection.y1) + 'px'
        
    });
    $('#preview100').css({
        width: Math.round(scaleX1 * 300) + 'px',
        height: Math.round(scaleY1 * 300) + 'px',
        marginLeft: '-' + Math.round(scaleX1* selection.x1) + 'px',
        marginTop: '-' + Math.round(scaleY1 * selection.y1) + 'px'
            
        });
    }
}
	
	//var data={'photo':$("#imgFile").val(),'x1':$('#x1').val(),'y1':$('#y1').val(),'width':$("#width").val(),'heigth':$("#heigth").val()}
	$("#button1").click(function(){
		if(!isuploadimg){
			layer.msg('请先上传图片');
			return false;
		}
		$("#frmUpload").ajaxSubmit({
			type: 'post', // 提交方式 get/post
			url: basePath+'/user/upload_face.json', // 需要提交的 url
			data: $("#frmUpload").serialize(),
			dataType:'json',
			success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
				if(data.result){
					layer.msg('保存成功');
					layer.close(update_users_img);
					$('#user_face').attr('src',basePath+data.photo);
				}
				else{
					layer.msg('头像保存失败，请稍后重试');
				}
			},
			error : function(XMLHttpRequest) {
				alert(XMLHttpRequest.status);
			},
		});
	});
}

//分页查询
function toPage(i){
	page=i
	user_document_list(page);
}
 //查询用户文档列表
function user_document_list(page){
	ajaxPost(basePath+'/user/user_document.json',{'page':page,'rows':10},function(data){
		var pageResult = data.userDocumentList;
		successList(data);
		var paging = buildPageHtml(pageResult.pageNum,pageResult.pageSize,pageResult.total,"toPage");
		$('#uiPage').html(paging);
	 });
}
//绑定数据
function successList(data){
	var page=data.userDocumentList;
	var str_html='';
	if(page!=null && page.result.length>0){
		for(var i=0;i<page.result.length;i++){	
			str_html+='<tr>';
			str_html+='<td style="text-align: left;"><a class="f-14 c-blue doc_title" href="'+basePath+'/user/detail?uuid='+page.result[i].uuid+'">'+page.result[i].title+'</a><span>（'+dateFormat(page.result[i].createDate, 'yyyy-MM-dd hh:mm:ss')+'）</span></td>';
			if(page.result[i].status==0){
				str_html+='<td>待发布</td>';
			}
			else if(page.result[i].status==1){
				str_html+='<td>待审核</td>';
			}
			else if(page.result[i].status==2){
				str_html+='<td>审核通过</td>';
			}
			else if(page.result[i].status==3){
				str_html+='<td>审核未通过</td>';
			}
			else{
				str_html+='<td>----</td>';
			}
			
			str_html+='<td>'+page.result[i].number+'次</td>';
			str_html+='<td>';
			if(page.result[i].status==0){
				str_html+='<a class="f-20" href="javascript:void(0)" onclick="publish_doc('+page.result[i].id+')"><i class="Hui-iconfont Hui-iconfont-upload" title="发布"></i></a>';
				str_html+='<span class="pipe">|</span>';
				str_html+='<a class="f-16" href="'+basePath+'/document/update?uuid='+page.result[i].uuid+'" target="mainframe"><i class="iconfont icon-unif012" title="修改"></i></a>';
				str_html+='<span class="pipe">|</span>';
				str_html+='<a class="f-16" onclick="document_del('+page.result[i].id+')"><i class="Hui-iconfont Hui-iconfont-del3" title="删除"></i></a>';
			}
			else if(page.result[i].status==1){
				str_html+='<a class="f-16" onclick="document_del('+page.result[i].id+')"><i class="Hui-iconfont Hui-iconfont-del3" title="删除"></i></a>';
			}
			else if(page.result[i].status==2 || page.result[i].status==3){
				str_html+='<a class="f-16" href="'+basePath+'/document/update?uuid='+page.result[i].uuid+'" target="mainframe"><i class="iconfont icon-unif012" title="修改"></i></a>';
				str_html+='<span class="pipe">|</span>';
				str_html+='<a class="f-16" onclick="document_del('+page.result[i].id+')"><i class="Hui-iconfont Hui-iconfont-del3" title="删除"></i></a>';
			}
			str_html+='</td></tr>';
		}
	}
	else{
		str_html+='<center><p class="f-18 mt-20">你还没有原创文档，<a class="c-blue" href="'+basePath+'/document/add" target="mainframe">立即去创建吧</a></p></center>';
		str_html+='<br/><br/><br/><br/>';
	}
	$("#pageInfo").html(str_html);
	
}

//发布文档
function publish_doc(id){
	//询问框
	var index_publish = layer.confirm('确定要发布文档吗？',function(index_publish){
		var data='id='+id;
		ajaxPost(basePath+'/document/publish.json',data,function(data){
			if(data=='success'){
				layer.close(index_publish);
				layer.msg('发布成功');
				user_document_list(1);
			}
		 });
	});
}

//删除
function document_del(id){
	var index_del = layer.confirm('确认要删除？', function(index_del){
		var data = {'deleteId':id};
		ajaxPost(basePath+'/user/delete_document.json',data,function(data){
			if(data.result){
				layer.close(index_del);
				layer.msg('删除成功');
				user_document_list(1);
			}
		 });
	});
}

//添加按钮点击事件
function add_doc_click(){
	location.href=basePath+'/document/add';
}

var cook_time =10;
var countdown=cook_time;
var check_cookie = true;
var isuploadimg = false; 
 
var isAjax=false;
//验证邮箱点击事件
function audit_email(uuid){
	if(isAjax){
		return;
	}else{
		isAjax = true;
		var data = {'uuid':uuid};
		ajaxPost('audit_email.json',data,function(data){
			if(data == '1'){
				layer.msg('请求过于频繁');
	    	}
			else if(data == '2'){
				layer.msg('验证已发送到邮箱');
	    		messageBack();
	    	}
	    	else if(data == '3'){
	    		layer.msg('已经验证或发送错误');
	    		$("#audit_email").hide();
	    	}
			isAjax = false;
		});
	}
};
	
function messageBack(){
	settime(document.getElementById("audit_email"));
}

//判断cookie
var times = $.cookie('times');
if(times!=null && times !=''){
	var dt =(new Date().getTime()-times)/1000;
	var md =Math.round(dt)-cook_time;	//大于0说明已经超时
	if(md<0){
		check_cookie =false;
		countdown = -md;
		settime(document.getElementById("audit_email"));
	}
}

function settime(val){
	if(check_cookie){
		$.cookie('times', new Date().getTime());
		check_cookie =false;
	}
	if (countdown <= 0) {
		$("#audit_email").css("background-color","rgb(10, 105, 153)");
		val.removeAttribute("disabled");
		val.value="邮箱验证";
		$.cookie('times', null);
		countdown = cook_time;
		check_cookie =true;
		return;
	}
	else {
		$("#audit_email").css("background","#CCC");
		val.setAttribute("disabled", true);
		val.value=countdown + "s后再次发送";
		countdown--;
	}
	setTimeout(function() {
		settime(val)
	},1000)
}
