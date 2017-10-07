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
	<title>测试发邮件</title>
	<link rel="shortcut icon" href="favicon.ico" />
	<link href="resources/public/login/css/style.css" rel="stylesheet">
	<script src="https://source.magicmanufactory.com/js/jquery/jquery.min_2.1.4.js"></script>

<script>
function saveForm(){
	//$("#saveFormDiv").submit();
	//$('#saveFormDiv').serialize(),// 你的formid
	
	/*
	var data1 = {
			name: $('#name1').val(),
        	email: $('#email1').val(),
        	telephone: $('#telephone1').val(),
        	position: $('#position1').val(),
        	content: $('#content1').val(),
	};
	$.ajax({
        type: "POST",
        url: 'api/v1/public/offical/website/email/recruit',
        //contentType: "application/json",
		//dataType: 'json',
		data: data1,//JSON.stringify(data1),
        error: function(request) {
            alert("error");
        },
        success: function(data) {
            alert("提交成功！");
        }
    });
	*/
	
	var data2 = {
			name: $('#name').val(),
        	email: $('#email').val(),
        	content: $('#content').val(),
	}
	
	$.ajax({
        type: "POST",
        url: 'api/v1/public/offical/website/email/franchisee',
        //contentType: "application/json",
		//dataType: 'json',
		data: data2,//JSON.stringify(data2),
        error: function(request) {
            alert("error");
        },
        success: function(data) {
            alert("提交成功！");
        }
    });
	
	
}

function submitForm(){
	
}
</script>
</head>


<body>
<br/><br/><br/><br/>
<div>
	 表单1，有附件：<br/><br/>
	<!--  -->
    <form target="_blank" id="saveFormDiv" enctype="multipart/form-data" method="post" action="/c2mwebservice/api/v1/public/offical/website/email/recruit" onsubmit="return submitForm();">
   	
    		姓名：<input type="text" name="name" id="name1" /><br/><br/>
    		邮箱：<input type="text" name="email" id="email1" /><br/><br/>
    		电话：<input type="text" name="telephone" id="telephone1" /><br/><br/>
    		职位：<input type="text" name="position" id="position1" /><br/><br/>
    		内容：<input type="text" name="content" id="content1" /><br/><br/>
    		文件：<input id="file1" name='file' type='file' /><br/><br/>
    		<input type="submit" value="提交"/>
    </form>
    
    <br/><br/>表单2，无附件：<br/><br/>
    <form target="_blank" method="post" action="/c2mwebservice/api/v1/public/offical/website/email/franchisee">
    		姓名：<input type="text" name="name" id="name"/><br/><br/>
    		邮箱：<input type="text" name="email" id="email"/><br/><br/>
    		内容：<input type="text" name="content" id="content"/><br/><br/>
    		<input type="submit" value="提交"/>
    </form><br/>
    <button onclick="saveForm()">ajax提交</button>
    
</div>
</body>
	
</html>
