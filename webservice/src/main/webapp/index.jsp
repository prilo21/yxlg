<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>客户端下载</title>
	<link rel="stylesheet" href="https://source.magicmanufactory.com/project/c2mwebservice/js/jquery/jquery.mobile-1.4.5.css">
	<link rel="stylesheet" href="https://source.magicmanufactory.com/project/c2mwebservice/css/common.css">
	<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script src="https://source.magicmanufactory.com/@/resources/jquery/mobile/jquery.mobile-1.4.5.min.js"></script>
	<link rel="shortcut icon" href="favicon.ico" />
<style>

</style>
</head>

<body>
<div data-role="page" data-theme="d" id="pageone" class="ui-page">

	<div  >
	      <div style="width:100%"  align="center">
	          <img alt="" src="https://source.magicmanufactory.com/project/c2mwebservice/public/images/magic-factory.png"> 
	      </div>
	       <div align="center"  class='level'>
             <h1>Magic Manufactory</h1>
          </div>
          <div style="width:100%" class='level abstract-text'>
          </div>
          <div style="margin-top:30px;text-align:center;">
			<input onclick="location.href='http://a.app.qq.com/o/simple.jsp?pkgname=com.yuandian.wanna'" type="button" class="inputs-code bg-black code-wd button-text" value="Android下载" data-role="none" data-inline="true" >
<!-- 			<a href='http://7xjold.com2.z0.glb.qiniucdn.com/app/official/app-release.apk' class="inputs-code bg-black code-wd button-text" data-role="none" data-inline="true" >Android下载</a> -->
			<input onclick="location.href='http://mp.weixin.qq.com/mp/redirect?url=https://itunes.apple.com/cn/app/mo-huan-gong-chang/id1004834346?mt=8'" type="button" class="inputs-code bg-black code-wd button-text" value="ios下载" data-role="none" data-inline="true" >
		  </div>
	</div><!-- /content -->
</div>
</body>

</html>
