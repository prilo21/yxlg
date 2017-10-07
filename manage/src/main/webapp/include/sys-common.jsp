<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>红领C2M定制管理平台</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>public/style/css.admin.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>public/easyui/themes/default/easyui.admin.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>public/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>public/easyui/jquery-ui.css">

	<script type="text/javascript" src="<%=basePath%>public/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>public/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>public/easyui/datagrid-detailview.js"></script>
	<script type="text/javascript" src="<%=basePath%>public/easyui/jquery.easyui.plus.js"></script>
	<script type="text/javascript" src="<%=basePath%>public/easyui/jquery.easyui.patch.js"></script>
	<script type="text/javascript" src="<%=basePath%>public/easyui/locale/easyui-lang-zh_CN.js"></script>
	<style type="text/css">
        #ff{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        
        #eff{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            width:100px;
        }
        .fitem input{
            width:180px;
        }
         .combogrid-input span{
            height:23px !important;
        }
        
        .combogrid-input .easyui-fluid {
        	height:23px !important;
        }
        .combogrid-input .textbox-icon, .combo-arrow{
        	height:23px !important;
        }
        
        /* .combogrid-input .textbox-text, .validatebox-text{
        	width: 400px !important;
        }*/
        
        .fitem .combo{
        	width: 300px !important;
        } 
        
    </style>
	<script type="text/javascript">
	//ajax请求过期处理
	$.ajaxSetup({ 
		contentType:"application/x-www-form-urlencoded;charset=utf-8", 
		cache:false ,
		global:true, 
		complete:function(XMLHttpRequest,textStatus){
			var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus"); ; 
			if(sessionstatus == "timeout"){ 
				 alert('未登录或网页已过期，请重新登陆！');
				 window.top.location.href ='<%=basePath%>'; 		               
			}
			var responseJSON = XMLHttpRequest.responseJSON;
			if(responseJSON && responseJSON.returnCode && responseJSON.returnCode != 200 && responseJSON.returnMsg){
				$.messager.alert('错误提示:', responseJSON.returnMsg + '请联系系統管理員');
				return;
			}
		}  
	}); 
	 //js方法：序列化表单 	
	function serializeForm(form) {
		var obj = {};
		$.each(form.serializeArray(), function(index) {
			if (obj[this['name']]) {
				obj[this['name']] = obj[this['name']] + ',' + this['value'];
			} else {
				obj[this['name']] = this['value'];
			}
		});
		return obj;
	}
	 
	/**
	 * 给时间框控件扩展一个清除的按钮
	 */
	$.fn.datebox.defaults.cleanText = '清空';

	(function ($) {
	    var buttons = $.extend([], $.fn.datebox.defaults.buttons);
	    buttons.splice(1, 0, {
	        text: function (target) {
	            return $(target).datebox("options").cleanText
	        },
	        handler: function (target) {
	            $(target).datebox("setValue", "");
	            $(target).datebox("hidePanel");
	        }
	    });
	    $.extend($.fn.datebox.defaults, {
	        buttons: buttons
	    });

	})(jQuery)
	/**
	 * 给时间框控件扩展一个清除的按钮
	 */
	$.fn.datetimebox.defaults.cleanText = '清空';

	(function ($) {
	    var buttons = $.extend([], $.fn.datetimebox.defaults.buttons);
	    buttons.splice(1, 0, {
	        text: function (target) {
	            return $(target).datetimebox("options").cleanText
	        },
	        handler: function (target) {
	            $(target).datetimebox("setValue", "");
	            $(target).datetimebox("hidePanel");
	        }
	    });
	    $.extend($.fn.datetimebox.defaults, {
	        buttons: buttons
	    });

	})(jQuery)
	
	//form禁用
	function disableForm(formId,isDisabled) {  
      
    var attr="disable";  
    if(!isDisabled){  
       attr="enable";  
    }  
    $("form[id='"+formId+"'] :text").attr("readonly",isDisabled);  
    $("form[id='"+formId+"'] textarea").attr("readonly",isDisabled);  
    $("form[id='"+formId+"'] select").attr("readonly",isDisabled);  
    $("form[id='"+formId+"'] :radio").attr("readonly",isDisabled);  
    $("form[id='"+formId+"'] :checkbox").attr("readonly",isDisabled);  
    
  //禁用jquery easyui中的文本框（使用input生成的textbox）  
    
    $("#" + formId + " input[class='textbox-f']").each(function () {  
        if (this.id) { 
            $("#" + this.id).textbox("readonly",isDisabled);  
        }  
    });  
      
    
    //禁用jquery easyui中的下拉选（使用input生成的combox）  
  
    $("#" + formId + " input[class='combobox-f combo-f']").each(function () {  
        if (this.id) {  
            $("#" + this.id).combobox("readonly",isDisabled);  
        }  
    });  
      
    //禁用jquery easyui中的下拉选（使用select生成的combox）  
    $("#" + formId + " select[class='combobox-f combo-f']").each(function () {  
        if (this.id) {  
            $("#" + this.id).combobox("readonly",isDisabled);  
        }  
    });  
      
    //禁用jquery easyui中的日期组件dataBox  
    $("#" + formId + " input[class='datebox-f combo-f']").each(function () {  
        if (this.id) {  
            $("#" + this.id).datebox(attr);  
        }  
    }); 
    
    //禁用jquery easyui中的日期组件datetimebox  
    $("#" + formId + " input[class='datetimebox-f combo-f']").each(function () {  
        if (this.id) {  
            $("#" + this.id).datetimebox(attr);  
        }  
    }); 
    //隐藏button div或toolbar
    $("#" + formId + " div").each(function () {  
        if (this.id&&(this.id.indexOf("buttons")!=-1||this.id.indexOf("toolbar")!=-1)) { 
        	if(isDisabled)
           		$("#" + this.id).hide();  
        	else
        		$("#" + this.id).show();  
        }  
    }); 
}  
	function load() {  
	    $("<div class=\"datagrid-mask\"></div>").css({ display: "block", width: "100%", height: $(window).height() }).appendTo("body");  
	    $("<div class=\"datagrid-mask-msg\"></div>").html("正在进行操作，请稍候。。。").appendTo("body").css({ display: "block", left: ($(document.body).outerWidth(true) - 190) / 2, top: ($(window).height() - 45) / 2 });
	}  

	//取消加载层  
	function disLoad() {  
	    $(".datagrid-mask").remove();  
	    $(".datagrid-mask-msg").remove();  
	}
//
	</script>
	<link rel="icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="<%=basePath%>favicon.ico" type="image/x-icon" />
  </head>
</html>
