<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String basePath2 = "//"+request.getServerName() + ":" + request.getServerPort() + path+"/";
%>
<!doctype html>
<html>
<head>
<base href="<%=basePath2%>">
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
<meta http-equiv="expires" content="0">
<meta name="keywords" content="场景应用">
<meta name="description" content="场景应用">
<meta name="apple-touch-fullscreen" content="yes">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="pragram" content="no-cache">
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
<title>领取大礼包-失败</title>
<link href="/c2mwebservice/resources/public/css/dalibao.css" rel="stylesheet">
<script src="https://dn-c2m-resources.qbox.me/html/resources/js/jquery.min.js"></script>
</head>

<body>
<div class="bg">
	<div class="logo"><img src="https://source.magicmanufactory.com/image%2Ffuxing%2Fsalespromotion%2F1%2Flogo1.png" width="100%"></div>
    <div class="middle_text1"><img src="https://source.magicmanufactory.com/image%2Ffuxing%2Fsalespromotion%2F1%2Fmiddle_text1.png" width="100%"></div>
    <div class="middle_text2"><img src="https://source.magicmanufactory.com/image%2Ffuxing%2Fsalespromotion%2F1%2Fmiddle_text2.png" width="100%"></div>
    <div class="shangou"><img src="https://source.magicmanufactory.com/image%2Ffuxing%2Fsalespromotion%2F1%2Fshangou.png" width="100%"></div>
    <div class="button_box">
    	<div class="button_left">
        	<input type="text" placeholder="输入手机号" disabled>
            <div class="yzm_box">
            	<div class="yzm"><img src="/c2mwebservice/resources/public/images/yanzheng.png" width="80%"></div>
                <input class="yzm_i" type="text" placeholder="输入验证码" disabled>
            </div>
        </div>
        <div class="button_right"><img src="/c2mwebservice/resources/public/images/lingqu.png" width="60%"></div>
    </div>
    <div class="bottom_text">感谢您购买酷特闪购大礼包</div>
	<div class="zhezhao">
		<div class="tanchu">
			<span>${msg}<br>请您确认输入是否正确<br>如有问题请联系客服</span>
		</div>
	</div>
		

</div>
<script>
$(function(){
	$('.bg,.zhezhao').css('height',window.innerHeight);
	$('.tanchu').css('height',($('.tanchu').width())*0.77);
	$('.zhezhao').bind('click',function(){
		$(this).css('display','none');
	})
	$('.button_left').css('backgroundColor','#8b8b8b');
	$('.button_left').attr("disabled","true");
	$('.yzm_i').css('backgroundColor','#8b8b8b');
	$('.yzm_i').attr("disabled","true");
	
})
</script>
</body>
</html>
