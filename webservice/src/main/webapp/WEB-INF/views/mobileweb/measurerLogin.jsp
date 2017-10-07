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
	<title>量体师登录</title>
	<link rel="shortcut icon" href="favicon.ico" />
	<link href="resources/public/login/css/style.css" rel="stylesheet">
	<script src="https://source.magicmanufactory.com/js/jquery/jquery.min_2.1.4.js"></script>

	
</head>
<body>
<div class="tl_container">
	<div class="tl_pic"><img src="resources/public/login/images/login_p1.jpg"></div>
    <div class="tl_inputbox">
        <table cellspacing="0" cellpadding="0">
            <tr>
                <td><img src="resources/public/login/images/login_user.jpg"></td>
                <td>PHONENO<br><input type="text" name="phoneNo" id="phoneNo" onkeydown="if(event.keyCode==13){passwordFocus()}" placeholder="Enter phoneNo"></td>
            </tr>
            <tr>
                <td><img src="resources/public/login/images/login_pw.jpg"></td>
                <td>PASSWORD<br><input class="password" type="password" name="userPassword" id="password" onkeydown="if(event.keyCode==13){measurerLogin()}" autocomplete="off" placeholder="Enter password"></td>
            </tr>
        </table>
    </div>
    <button class="tl_btn" onclick="measurerLogin()">登录</button>
</div>
</body>

<script type="text/javascript">
$(document).ready(function() { 
	
})
function passwordFocus(){
	$("#password").focus();
}
function measurerLogin(){
	var phoneNo = $("#phoneNo").val();
	var userPassword = $("#password").val();
	$.ajax({
		type:'POST',
		url:'<%=basePath%>api/v1/public/measurers/login',
		contentType: 'application/json',
		dataType: 'json', 
		data:JSON.stringify({'phoneNo':phoneNo,'userPassword':userPassword}),
		success:function(data){
			if(data.returnData != null){
				location.href='api/v1/public/measurers/'+data.returnData.bodyMeasurementPersonId+'/members';
			}else{
				alert(data.returnMsg);
			}
		},
		error: function(data){
			alert(data.responseJSON.returnMsg);
		}
	});
}

</script>
</html>
