<%@ page language="java" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
            String basePath = request.getScheme() + "://"
                    + request.getServerName() + ":" + request.getServerPort()
                    + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>最新资讯</title>
	<link rel="stylesheet" href="../resources/public/jquery.mobile-1.4.5/jquery.mobile-1.4.5.css">
	<link rel="stylesheet" href="../resources/public/css/gh-buttons.css">
	<script src="https://source.magicmanufactory.com/js/jquery/jquery.min_2.1.4.js"></script>
	<script src="https://7xkybm.com2.z0.glb.qiniucdn.com/@/resources/jquery/mobile/jquery.mobile-1.4.5.min.js"></script>
<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
	font-family: "微软雅黑", "宋体";
	font-size: 15px;
	background-color: #fff;
}
#infoDetail {
	margin-left: auto;
	margin-right: auto;
	text-align: center;
}
img {
	width: 90%;
}
</style>
</head>
<body>
	<div id="infoDetail" style="margin-top: 30px;">
		<div id="title">
			 ${info.title }
		</div>
		<div id="content">
			 ${info.content }
		</div>
	</div>
</body>
</html>
