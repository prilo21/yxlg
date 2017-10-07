<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
	<head>
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
		<title>抢光</title>
		<link href="/c2mwebservice/resources/public/css/style-coupon.css" rel="stylesheet">
		<link href="https://img.magicmanufactory.com/@/jquery/mobile/jquery.mobile-1.4.5.min.css" rel="stylesheet">
		<link href="https://source.magicmanufactory.com/@/resources/goods-template/xiazai_css.css" rel="stylesheet">
		
		<script src="https://source.magicmanufactory.com/js/jquery/jquery.min_2.1.4.js"></script>
		<script src="https://7xkybm.com2.z0.glb.qiniucdn.com/@/resources/jquery/mobile/jquery.mobile-1.4.5.min.js"></script>
	</head>
	
	<body>
		<div class="xiazai" id="downloadDiv">
			<div class="xz_logo"><img src="https://source.magicmanufactory.com/@/resources/goods-template/images/xz_logo.png" width="100%"></div>
			<div class="xz_btn" onclick="startApp()" style="cursor: pointer;text-shadow: none;">立即下载</div>
		</div>
		
		<div class="bg">
			<div class="toplogo"><img src="/c2mwebservice/resources/public/images/toplogo.png" width="100%"></div>
		    <div class="hbqg_mid">
		    	<c:if test="${discountCategory==20}">
		    		<img src="/c2mwebservice/resources/public/images/qiangg.png" width="100%">
		    	</c:if>
		    	<c:if test="${discountCategory==30}">
		    		<img src="/c2mwebservice/resources/public/images/qiangg_dzk.png" width="100%">
		    	</c:if>
		    </div>
		</div>
		<script>
			$(function(){
				$('.bg').css('height',window.innerHeight);
				
			})
			function startApp(){
				window.location = 'wanna:';
				setTimeout(function(){
					window.location = 'http://a.app.qq.com/o/simple.jsp?pkgname=com.yuandian.wanna&g_f=991653';
				}, 500)
			}
		</script>
	</body>
</html>