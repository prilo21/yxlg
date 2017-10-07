<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8" />
    <title>门店指南</title>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0" />
    <link type="text/css" rel="stylesheet" href="resources/public/css/storelist.css" />
</head>
<body>

<div class="s_head prel">
	<a href="index.html" class="s_gps pabs"><img src="images/gps_icon.png" width="16px" height="19px"></a>
	<img class="s_logo" src="resources/public/images/logo.png" width="128px" height="47px">
	<a href="#Search" class="s_zoom pabs"><img src="images/zoom_icon.png" width="19px" height="19px"></a>
	<script src="https://source.magicmanufactory.com/js/jquery/jquery.min_2.1.4.js"></script>
</div>
<div id="address_content" class="s_list">
	
</div>

</body>
<script>

$(function(){
	var html = "";
	var prehtml = "<a href=\"api/v1/public/storeList\"><div class=\"s_col prel\"><strong>";
	var chtml = "<label></label></strong><p>";
	var afthtml = "</p><div class=\"s_tag pabs\"></div></div></a>";
	$.ajax({
		type:'GET',
		url:'<%=basePath%>api/v1/public/stores',
		dataType: 'json', 
		success:function(data){
			var returnData = data.returnData;
			for (var i=0; i<returnData.length;i++) {
				html = html + prehtml + returnData[i].storeName + chtml + returnData[i].storeAddress + afthtml;
			}
			$("#address_content").html(html);
		}
	});
});
</script>
</html>
