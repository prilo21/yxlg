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
<title>领取大礼包</title>
<link href="/c2mwebservice/resources/public/css/dalibao.css" rel="stylesheet">
<script src="https://source.magicmanufactory.com/html/resources/js/jquery.min.js"></script>
</head>

<body class="bg">

<div>
	<div class="logo"><img src="https://source.magicmanufactory.com/image%2Ffuxing%2Fsalespromotion%2F1%2Flogo1.png" width="100%"></div>
    <div class="middle_text1"><img src="https://source.magicmanufactory.com/image%2Ffuxing%2Fsalespromotion%2F1%2Fmiddle_text1.png" width="100%"></div>
    <div class="middle_text2"><img src="https://source.magicmanufactory.com/image%2Ffuxing%2Fsalespromotion%2F1%2Fmiddle_text2.png" width="100%"></div>
    <div class="shangou"><img src="https://source.magicmanufactory.com/image%2Ffuxing%2Fsalespromotion%2F1%2Fshangou.png" width="100%"></div>
    <div class="button_box">
    	<div class="button_left" >
        	<div class="sjh_box">
            	<select id = "sjh_value">
                    <option value="+86" selected>中国  China</option>
                    <option value="+852">香港  Hongkong</option>
                    <option value="+81">日本  Japan</option>
                    <option value="+1">美国  U.S.A</option>
                </select>
                <input type="text" name="phoneNo" id="phoneNo" placeholder="输入手机号" style="width:108px; height:20px;">
            </div>
            <div class="yzm_box" >
            	<div class="yzm" id="yzmBtn" onclick="getIdentifyCode()"><img src="/c2mwebservice/resources/public/images/yanzheng.png" width="80%"></div>
                <input class="yzm_i" type="tel" name="identifyCode" id="identifyCode"
                		onclick = "yzmFocus()"
                		onblur = "yzmBlur()"
                		placeholder="输入验证码">
            </div>
        </div>
        <div class="button_right btnr" id="getCouponBtn" onclick="beforeGetCoupon()"><img src="/c2mwebservice/resources/public/images/lingqu.png" width="60%"></div>
    </div>
    <div class="bottom_text">感谢您购买酷特闪购大礼包</div>
	<div class="zhezhao" id="zhezhao" hidden="ture">
		<div class="tanchu" id="tanchu">
			<span id="errorMsg"></span>
		</div>
	</div>
</div>
<script>
	var verifyCode = 0;
	var InterValObj;
	var count = 60;//验证码倒计时预制为60秒
	var curCount;
	var placeholderFlag = 1;//yzm_i被聚焦为0; yzm_i失焦为1;
	
	$(function(){
		$('.bg').css('height',window.innerHeight);
	})
	
	function yzmFocus(){
		placeholderFlag = 0;
		$('#identifyCode').val("");
	}
	function yzmBlur(){
		placeholderFlag = 1;
	}
	
	function beforeGetCoupon(){
		$("#errorMsg").text("请输入正确手机号和验证码");
		$('.bg,.zhezhao').css('height',window.innerHeight);
		$('.tanchu').css('width',window.innerWidth * 0.7);
		$('.tanchu').css('height',($('.tanchu').width())*0.77);
		$('.zhezhao').bind('click',function(){
			$(this).css('display','none');
		})
		$('#zhezhao').show("true");
	}
	
	function getCoupon() {
		if($("#identifyCode").val() != verifyCode){
			$("#errorMsg").text("验证码错误啦！");
			$('.bg,.zhezhao').css('height',window.innerHeight);
			$('.tanchu').css('width',window.innerWidth * 0.7);
			$('.tanchu').css('height',($('.tanchu').width())*0.77);
			$('.zhezhao').bind('click',function(){
				$(this).css('display','none');
			})
			$('#zhezhao').show("true");
			return false;
		}
		var jumpUrl = "<%=basePath2%>api/v1/public/salesPromotionPackage/" 
							+ "${salesPromotionPackageId}"
							+ "/"
							+ "${qrCode}"
							+ "/"
      						+ $("#phoneNo").val();

		window.location = window.location.protocol + jumpUrl;
	}
	
	function isNaN(phoneNo){
		var reg = /^1[3|5|7|8][0-9]\d{4,8}$/;
		var rtn = false;
		if(!reg.test(phoneNo)){
			rtn = true;
		}; 
		return rtn;
	}
	
	function setRemainTime(){
		if(curCount == 0){
			window.clearInterval(InterValObj);
			$('#identifyCode').val("请重新获取验证码");
			$('#yzmBtn').attr("onclick","getIdentifyCode()");
			$('.btnr').attr("onclick","beforeGetCoupon()");
			$('.yzm').css('backgroundColor','#ffeaac');
			verifyCode = 0;
		}else{
			curCount --;
			if(placeholderFlag == 1){
				var code = $('#identifyCode').val();
				if(code.length >= 8 || code.length == 0){
					$('#identifyCode').val(curCount + "秒内输入验证码");
				}
			}
		}
	}
	function doNothing(){}
	
	function getIdentifyCode(){
		var phoneNo = $("#phoneNo").val();
		var IDCode = $("#sjh_value").val();
		var reg = new RegExp("^[0-9]*$");  
	    if(reg.test(phoneNo)) {
	    	if ("+86" == IDCode) {
				if (phoneNo.length!=11) {
					$("#errorMsg").text("手机号输入不正确!");
					$('.bg,.zhezhao').css('height',window.innerHeight);
					$('.tanchu').css('width',window.innerWidth * 0.7);
					$('.tanchu').css('height',($('.tanchu').width())*0.77);
					$('.zhezhao').bind('click',function(){
						$(this).css('display','none');
					})
					$('#zhezhao').show("true");
					return false;
				}
				if(isNaN(phoneNo)){
					$("#errorMsg").text("手机号输入不正确!");
					$('.bg,.zhezhao').css('height',window.innerHeight);
					$('.tanchu').css('width',window.innerWidth * 0.7);
					$('.tanchu').css('height',($('.tanchu').width())*0.77);
					$('.zhezhao').bind('click',function(){
						$(this).css('display','none');
					})
					$('#zhezhao').show("true");
					return false;
				}
	    	}
	    } else {
	    	$("#errorMsg").text("手机号码输入不正确!");
			$('.bg,.zhezhao').css('height',window.innerHeight);
			$('.tanchu').css('width',window.innerWidth * 0.7);
			$('.tanchu').css('height',($('.tanchu').width())*0.77);
			$('.zhezhao').bind('click',function(){
				$(this).css('display','none');
			})
			$('#zhezhao').show("true");
			return false;
	    }
		curCount = count;
		$('#identifyCode').attr("value",curCount + "秒内输入验证码");
		$('.yzm').css('backgroundColor','#8b8b8b');
		$('#yzmBtn').attr("onclick","doNothing()");
		
		
		InterValObj = window.setInterval(setRemainTime, 1000);
		
		$.ajax({
			url : "<%=basePath%>api/v1/public/verification",
			method : "POST",
			contentType: 'application/json',
			dataType: 'json',
			data :JSON.stringify({'emailPhone':phoneNo,'IDCode':IDCode}),
			success:function(msg){
				verifyCode = msg.returnData.verifyCode;
				$('.btnr').attr("onclick","getCoupon()");
			},
			error:function(){
				verifyCode = 0;
				$("#errorMsg").text("服务器异常,请稍后再试!");
				$('.bg,.zhezhao').css('height',window.innerHeight);
				$('.tanchu').css('width',window.innerWidth * 0.7);
				$('.tanchu').css('height',($('.tanchu').width())*0.77);
				$('.zhezhao').bind('click',function(){
					$(this).css('display','none');
				})
				$('#zhezhao').show("true");
			}
		});
	}

</script>
</body>
</html>
