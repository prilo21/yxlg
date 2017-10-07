<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
// String basePath = "//"+request.getServerName()+":"+request.getServerPort()+path+"/";
String basePath = "//"+request.getServerName()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<title>欢迎加入酷特</title>
	<link rel="stylesheet" href="https://cdn.bootcss.com/jquery-mobile/1.4.5/jquery.mobile.min.css">
	<script src="https://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
	<script src="https://cdn.bootcss.com/jquery-mobile/1.4.5/jquery.mobile.min.js"></script>
<style>
input,img{ margin:0px; padding:0px 3px; }
#header{ margin:20px 0px 10px 0px; text-align:center;}
.level{ word-wrap:break-word; word-break:break-all;display:block; }
.level h1{
		color: rgb(0,0,0);
		font-family: Georgia;
		font-size: 1.0em;
		letter-spacing: 0.1em;
		text-shadow: 1px 1px 1px rgba(255,255,255,0.6);
	}
.abstract-text{ text-indent: 30pt; font-size:14px; }
.mig-space { margin:10px; }
#inputs input
{   width:215px;
    background: #f1f1f1 url("public/images/icon-phone.png ") no-repeat;
    padding: 10px 10px 10px 25px;
    border: 1px solid #ccc;
    -moz-border-radius: 5px;
    -webkit-border-radius: 5px;
    border-radius: 5px;
    -moz-box-shadow: 0 1px 1px #ccc inset, 0 1px 0 #fff;
    -webkit-box-shadow: 0 1px 1px #ccc inset, 0 1px 0 #fff;
    box-shadow: 0 1px 1px #ccc inset, 0 1px 0 #fff;
    font-size:14px;
}
.inputs-code 
{   
    height:35px;
    border:0px;
    font-size:16px;
    float: left;
}
.code-wd{
    width:33%;
}
.gcode{
    font-family:"Microsoft YaHei";
    text-decoration:underline;
}
.msfont{
font-family:"Microsoft YaHei";
}
#phoneNo, #ecode{
    width:84%;
}
#imageCode{
	width:45%;
}
#ecode, #imageCode{ border-bottom:1px solid #d0d0d0;outline：none; }
#gecode{ position:relative;top: -38px;float:right;}

.main-button{ width:115px; }
#ecode-button{ margin-left:10px; }
#mainContent{ margin:30px 0px 30px 0px;text-align:center; }
.main-input{ width:88%; margin:0 auto; margin-top:3px; height:42px; line-height:38px; }
.button-text{ color:#ffffff }
.input-icon-bg{
    height:42px;
    line-height:55px;
    margin:0 1%;
    float:left;
}
.bg-black{
    background:#000000;
}
.bg-hui{
    background:#d0d0d0;
}
.imageCode{
	height:36px;
	float:right;
	border:1px solid #d0d0d0;
	margin-right:5%;
}
</style>
</head>

<body>
<!-- pageone -->
<div data-role="page" data-theme="d" id="pageone">
    <div id="header">
        <div>
            <img style="width:50%" src="resources/public/images/banner.png">
        </div>
    </div>
    <div style="height:20px;margin:0 auto;"></div>
    <div id="mainContent">
		<div class="main-input level" >
		    <div class="input-icon-bg">
		        <img style="width:25px;height:25px" src="resources/public/images/username.png">
		    </div>
		    <input class="inputs-code" style="border-bottom:1px solid #d0d0d0" type="text" name="phoneNo" id="phoneNo" data-role="none" placeholder="请输入您的手机号">
		</div>
		<div class="main-input level" >
		    <div class="input-icon-bg">
		        <img style="width:25px;height:25px" src="resources/public/images/password.png">
		    </div>
		    <input class="inputs-code" type="text" name="imageCode" id="imageCode" data-role="none" placeholder="输入图片验证码">
		    <img id="codeImage"class="inputs-code code-wd gcode imageCode" src="api/v2/public/captcha/image-stream/reg" alt="" onclick="refreshImage(this)">
		</div>
		<div class="main-input level" >
		    <div class="input-icon-bg">
		        <img style="width:25px;height:25px" src="resources/public/images/password.png">
		    </div>
		    <input class="inputs-code" type="text" name="ecode" id="ecode" data-role="none" placeholder="输入验证码">
		    <input class="inputs-code code-wd gcode" style="background:none;" type="button" id="gecode"  value="获取验证码"  onclick="getCode(this)" data-role="none">
		</div>
		<div class=" level" style="margin:20px 0px;">
		    <div class="bg-black button-text" style="-moz-border-radius: 20px; -webkit-border-radius: 20px; border-radius:20px;width:80%;  margin:0; padding:0;display: inline-block;line-height:28px;text-align:center;"   onclick="checkCode()" data-role="none">下一步</div>
		</div>
<%-- 		<div class=" level msfont" style="margin:20px 0px;">新用户注册即可领取<span id="registerCoin"><%=request.getAttribute("coins")%></span>酷特币</div> --%>
		<div class=" level msfont" style="margin:20px 0px;">新用户注册即可领取<span id="registerCoin"></span>酷特币</div>
	</div>
    <div id="msgAlertDiv" style="display:none;width:100%;height:100%;background-color:rgba(0,0,0,0.5);position:fixed;top:0px;left:0px;padding:0px 0px;">
    	<div data-role="content" style="padding:20px 20px;position:fixed;width:60%;height:40%;left:15%;right:auto;margin-top:30%;background-color:white;">
            <h3 style="text-align:center;color:#000;padding:0px 0px;"></h3>
            <div style="margin-top:30%;"><button type="submit" onclick="closeMsgPop('msgAlertDiv')" class="ui-btn ui-corner-all ui-shadow ui-btn-b ui-btn-icon-left ui-icon-check" data-transition="flow">确定</button></div>
       </div>
    </div>
    
    <div id="msgSuccessDiv" style="display:none;width:100%;height:100%;background-color:rgba(0,0,0,0.5);position:fixed;top:0px;left:0px;padding:0px 0px;">
    	<div data-role="content" style="padding:20px 20px;position:fixed;width:60%;height:40%;left:15%;right:auto;margin-top:30%;background-color:white;">
            <h3 style="text-align:center;color:#000;padding:0px 0px;"></h3>
            <div style="margin-top:30%;"><button type="submit" onclick="closePop('msgSuccessDiv')" class="ui-btn ui-corner-all ui-shadow ui-btn-b ui-btn-icon-left ui-icon-check" data-transition="flow">确定</button></div>
       </div>
    </div>
</div>
    
    
    
<!-- /pageone -->

</body>
<script>
	//var winHeight = $(window).height();
	//var winWidth = $(window).width();
$(document).ready(function(){
	$.getJSON(window.location.protocol + "<%=basePath%>api/v1/public/registerTactic",function(data){
		//alert("JSON Data: " + data.returnData.coinReturnRatio);
		if(data.returnData == null || data.returnData.coinReturnRatio == null){
			$("#registerCoin").text("20"); 
		}else{
			$("#registerCoin").text(data.returnData.coinReturnRatio);
		}
	}); 
	//$("#msgAlertDiv div").css("margin-top", winHeight*0.30);
<%-- 	var flag = <%=request.getAttribute("flag")%>;
	if (flag == 1) {
	    $("#popupWin").popup();
		$("#popupWin div h3").html("晚了一步，推荐名额已满");
	    $("#popupWin").popup('open');
	    $("#popupWin").popup({closeEvents:true});
	} --%>
});
var pageUrl = window.location.pathname;
var memberId = pageUrl.substr(pageUrl.length - 32, pageUrl.length);
var code = "";
var phoneNo = "";
function getCode(tag){
	phoneNo = $("#phoneNo").val();
	if(phoneNo == ""){
		//alert("手机号不合法！","提示");
		$("#msgAlertDiv").css("display","block");
		$("#msgAlertDiv div h3").html("请输入手机号");
		//$("#msgWin").popup('open');****************
		return false;
	}	
	var imageCode = $("#imageCode").val();
	if(imageCode == "" || imageCode.length != 4){
		$("#msgAlertDiv div h3").html("请输入正确的图片验证码");
		$("#msgAlertDiv").css('display','block');
		return false;
	}
	var getCodeData = {"emailPhone":phoneNo,"imageCode":imageCode,"codePurpose":"reg"};
	$.ajax({
		type:'POST',
		url:window.location.protocol + '<%=basePath%>api/v1/public/registration',
		data:JSON.stringify(getCodeData),
		headers : {"C2M-Identify": "Platform:WX,APP Version:1.6,OS Version:5.0.1,Device Type:MX4 Pro", "Content-Type":"application/json"},
		success:function(data){
			// code = data.returnData.verifyCode;
			time(tag);
		},
		error:function(data){
			var json = JSON.parse(data.responseText); 
			// var json = eval("("+data.responseText+")");
			$("#msgAlertDiv div h3").html(json.returnMsg);
			$("#msgAlertDiv").css('display','block');
		}
	});
}
function checkCode(){
	var ecode = $("#ecode").val();
	phoneNo = $("#phoneNo").val();
	if(phoneNo == ""){
		$("#msgAlertDiv div h3").html("请输入手机号");
		$("#msgAlertDiv").css('display','block');
		return false;
	}
	var imageCode = $("#imageCode").val();
	if(imageCode == "" || imageCode.length != 4){
		$("#msgAlertDiv div h3").html("请输入正确的图片验证码");
		$("#msgAlertDiv").css('display','block');
		return false;
	}
	if(ecode == ""){
		$("#msgAlertDiv div h3").html("请输入验证码");
		$("#msgAlertDiv").css('display','block');
		return false;
	}
	$.ajax({
		type:'POST',
		// url:'api/v1/public/memberRelation/<%=request.getAttribute("mid")%>',
		url:window.location.protocol + '<%=basePath%>api/v1/public/memberRelation/' + memberId,
		contentType: "application/json",
		dataType: 'json', 
		data:JSON.stringify({'phoneNo':phoneNo, 'phoneCode':ecode}),
		success:function(data){
			if(data.returnMsg == "恭喜您加入酷特,赶快下载app体验个性化定制吧"){
				clearTimeout(t);
				code = "";
				$("#gecode").removeAttr("disabled"); 
				$("#gecode").val("获取验证码"); 
				$("#msgSuccessDiv div h3").html(data.returnMsg);
				$("#msgSuccessDiv").css('display','block');
			}else{
				$("#msgAlertDiv div h3").html(data.returnMsg);
				$("#msgAlertDiv").css('display','block');
			}
		},
		error:function(data){
			var json = JSON.parse(data.responseText); 
			// var json = eval("("+data.responseText+")");
			$("#msgAlertDiv div h3").html(json.returnMsg);
			$("#msgAlertDiv").css('display','block');
			refreshImage(document.getElementById('codeImage'));
		}
	})
}
function closePop(win){
	//$("#"+win).popup('close'); //Jarvis close之后导致页面不跳转
	//$("#"+win+" div h3").empty();
	$("#"+win).css("display","none");
	window.location.href = "https://source.magicmanufactory.com/html/redcollar/aboutus/magic_intro/magicmanufactory.html";
}
function closeMsgPop(win){
	//$("#"+win).popup('close');
	$("#"+win).css("display","none");
	var alertMsg = $("#msgAlertDiv div h3").html();
	if(alertMsg != "请输入手机号" && alertMsg != "请输入验证码" && alertMsg != "验证码错误" && alertMsg != "请输入正确的图片验证码" && alertMsg != "图片验证码错误或已过期"){
		window.location.href = "https://source.magicmanufactory.com/html/redcollar/aboutus/magic_intro/magicmanufactory.html";
	}
}

function request(paras){
    var url = location.href;
    var paraString = url.substring(url.indexOf("?")+1,url.length).split("&");
    var paraObj = {}
    for (i=0; j=paraString[i]; i++){
        paraObj[j.substring(0,j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=")+1,j.length);
    }

    var returnValue = paraObj[paras.toLowerCase()];
    if(typeof(returnValue)=="undefined"){
        return "";
    }else{
        return returnValue;
    }
}
var wait=60; 
var t;
function time(o) { 
    if (wait == 0) { 
    	code = "";
        o.removeAttribute("disabled"); 
        o.value="获取验证码"; 
        wait = 60; 
    } else { 
        o.setAttribute("disabled", true); 
        o.value="重新发送(" + wait + ")"; 
        wait--; 
        t = setTimeout(function() { 
            time(o) 
        }, 1000) 
    } 
} 
function refreshImage(obj){
	$(obj).attr("src", "api/v2/public/captcha/image-stream/reg?v="+new Date().getTime());
}
</script>
</html>
