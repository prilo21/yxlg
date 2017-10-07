<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//String basePath = "//"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
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
<title>大礼包详情</title>
<link href="https://dn-c2m-resources.qbox.me/@/resources/jquery/mobile/style.css" rel="stylesheet">
<link href="resources/public/css/dalibao.css" rel="stylesheet">
<script src="https://source.magicmanufactory.com/js/jquery/jquery.min_2.1.4.js"></script>
<script src="https://7xkybm.com2.z0.glb.qiniucdn.com/@/resources/jquery/mobile/jquery.mobile-1.4.5.min.js"></script>
</head>
<style>

</style>
<body class="bg2">
    <div class="gongxi"><img src="resources/public/images/gongxi.png" width="100%"></div>
    <c:forEach items="${giftCard}" var="tpermiss">
    <div class="lipinka">
		<img src="https://source.magicmanufactory.com/image%2Ffuxing%2Fsalespromotion%2F1%2Flipinka.png" width="100%">
		<div class="lpk_t">${tpermiss.type}</div>
	</div>
	</c:forEach>
	<c:forEach items="${discountCard}" var="tpermiss">
    <div class="dazheka">
		<img src="https://source.magicmanufactory.com/image%2Ffuxing%2Fsalespromotion%2F1%2Fdazheka.png" width="100%">
		<div class="dzk_t">
			<div class="dzk_zhekou"><span>${tpermiss.type*10}</span>折</div>
			<div class="dzk_num">x${tpermiss.count}</div>
		</div>
	</div>
	</c:forEach>
	<c:forEach items="${redPackage}" var="tpermiss">
    <div class="hongbao">
		<img src="https://source.magicmanufactory.com/image%2Ffuxing%2Fsalespromotion%2F1%2Fhongbao.png" width="100%">
		<div class="hb_t">
			<div class="hb_zhekou"><span>${tpermiss.type}</span>元<br>${tpermiss.categoryName}</div>
			<div class="hb_num">x${tpermiss.count}</div>
		</div>
	</div>
	</c:forEach>
	
    <div class="libao_t1">红包已放至账户<span>${phone}</span>，<br>请使用同一手机号注册酷特，即可使用</div>
    <div class="download"><a onclick="startApp()"><img src="resources/public/images/download.png" width="100%"></a></div>
    <div class="hujiao"> <img src="https://source.magicmanufactory.com/image%2Ffuxing%2Fsalespromotion%2F1%2Fhujiao.png" width="100%"></div>
    <div class="rule"><img src="https://source.magicmanufactory.com/image%2Ffuxing%2Fsalespromotion%2F1%2Frule.png" width="100%"></div>
    <div class="magic"><img src="https://source.magicmanufactory.com/image%2Ffuxing%2Fsalespromotion%2F1%2Fmagic.png" width="100%"></div>

<script>
   function startApp(){
	window.location = 'wanna:';
	setTimeout(function(){
		window.location = 'http://a.app.qq.com/o/simple.jsp?pkgname=com.yuandian.wanna&g_f=991653';
	}, 500)
}
</script> 
</body>


</html>
