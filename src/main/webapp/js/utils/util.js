/**
 * 时间格式转换
 */
var dateFormat = function(time, format) {
	if (time != null) {
		var t = new Date(time);
		var tf = function(i) {
			return (i < 10 ? '0' : '') + i
		};
		return format.replace(/yyyy|MM|dd|hh|mm|ss/g, function(a) {
			switch (a) {
			case 'yyyy':
				return tf(t.getFullYear());
				break;
			case 'MM':
				return tf(t.getMonth() + 1);
				break;
			case 'dd':
				return tf(t.getDate());
				break;
			case 'hh':
				return tf(t.getHours());
				break;
			case 'mm':
				return tf(t.getMinutes());
				break;
			case 'ss':
				return tf(t.getSeconds());
				break;
			}
		});
	}
	return "0000-00-00 00:00:00";
}

/**
 * 日期文字描述
 * @param dateTimeStamp
 * @returns
 */
function getDateDiff(dateTimeStamp){
	if(dateTimeStamp==null){
		return "--"
	}
	var minute = 1000 * 60;
	var hour = minute * 60;
	var day = hour * 24;
	var halfamonth = day * 15;
	var month = day * 30;
	var year = 365 * day;
	var now = new Date().getTime();
	var diffValue = now - dateTimeStamp;
	if(diffValue < 0){
		return "刚刚";
	}
	var yearC = diffValue/year;
	var monthC = diffValue/month;
	var weekC = diffValue/(7*day);
	var dayC = diffValue/day;
	var hourC = diffValue/hour;
	var minC = diffValue/minute;
	if(yearC>=1){
		result="" + parseInt(yearC) + "年前";
	}
	else if(monthC>=1){
		result="" + parseInt(monthC) + "月前";
	}
	else if(weekC>=1){
		result="" + parseInt(weekC) + "周前";
	}
	else if(dayC>=1){
		result=""+ parseInt(dayC) +"天前";
	}
	else if(hourC>=1){
		result=""+ parseInt(hourC) +"小时前";
	}
	else if(minC>=1){
		result=""+ parseInt(minC) +"分钟前";
	}else{
		result="刚刚";
	}
	return result;
}

function ajaxPost(url,data,successFun){
	ajaxFun(url,data,"post",successFun,true);
}

function ajaxGet(url,data,successFun){
	ajaxFun(url,data,"get",successFun,true);
}

function ajaxPostSync(url,data,successFun){
	ajaxFun(url,data,"post",successFun,false);
}

function ajaxGetSync(url,data,successFun){
	ajaxFun(url,data,"get",successFun,false);
}

function ajaxFun(url,data,type,successFun,async){
	$.ajax({
		type:type,
		url:url,
		dataType:'json',
		data:data,
		async:async,
		success:function(data){
			successFun(data);
		}
	});
}

/**
 * form表单数据转json
 */
(function ($){
	$.fn.serializeJson= function (){
		var serializeObj={};
		$( this .serializeArray()).each( function (){
			serializeObj[ this .name]= this .value;
		});
		return serializeObj;
	};
})(jQuery);

/**
 * 国际化初始化
 * @param name
 * @returns
 */
function initI18nProperties(name){
	jQuery.i18n.properties({
	    name:name, //使用资源文件名称
	    path:basePath+"/get_i18n.json", //资源文件所在路径
	    mode:'map',    //调用方式，可用值‘vars’ (default) 变量方式, ‘map’map方式 or ‘both’两者均支持
	    callback: function() {
	    	
	    }
	});
}
