<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		<c:if test="${discountCategory==30}">
			<title>打折卡</title>
		</c:if>
		<c:if test="${discountCategory==20}">
			<title>红包</title>
		</c:if>
		<link href="/c2mwebservice/resources/public/css/style-coupon.css" rel="stylesheet">
		<link href="https://img.magicmanufactory.com/@/jquery/mobile/jquery.mobile-1.4.5.min.css" rel="stylesheet">
		<link href="https://source.magicmanufactory.com/@/resources/goods-template/xiazai_css.css" rel="stylesheet">
		
		<style type="text/css">
			body {text-align: center;}
		</style>
		
		<script src="https://source.magicmanufactory.com/js/jquery/jquery.min_2.1.4.js"></script>
		<script src="https://7xkybm.com2.z0.glb.qiniucdn.com/@/resources/jquery/mobile/jquery.mobile-1.4.5.min.js"></script>
	</head>
		
	<body>
		<div class="xiazai" id="downloadDiv">
			<div class="xz_logo"><img src="https://source.magicmanufactory.com/@/resources/goods-template/images/xz_logo.png" width="100%"></div>
			<div class="xz_btn" onclick="startApp()" style="cursor: pointer;text-shadow: none;">立即下载</div>
		</div>
		
		<form id="couponForm" action="/c2mwebservice/api/v1/public/receiveCompanyShare" method="post" data-ajax=“false”>
			<input type="hidden" name="memberId" id="memberId" value="${memberId}">
			<div class="bg">
				<div class="toplogo"><img src="/c2mwebservice/resources/public/images/toplogo.png" width="100%"></div>
			    <c:if test="${discountCategory==30}">
				    <div class="dzk_bot">
				    	<img class="dzk_mid" src="/c2mwebservice/resources/public/images/dzk_mid.png">
				    	<div class="sjh_box">
			            	<select id="sjh_value" class="sjh_value" name="IDCode">
			                    <option value="+86" selected>中国  China</option>
			                    <option value="+852">香港  Hongkong</option>
			                    <option value="+81">日本  Japan</option>
			                    <option value="+1">美国  U.S.A</option>
			                </select>
			                <input type="text" class="midbtn" name="phoneNo" id="phoneNo" placeholder="输入您的手机号">
			            </div>
				        <div class="btn2" style="cursor: pointer; text-shadow: none;" onclick="getCoupon()">领取打折卡</div>
				    </div>
			    </c:if>
			    <c:if test="${discountCategory==20}">
				    <div class="hb_bot">
				    	<img class="hb_mid" src="/c2mwebservice/resources/public/images/hb_mid.png">
				    	<div style="width: 80%;text-align: center;margin-left: auto;margin-right: auto;">
				        	<input class="midbtn" type="text" name="phoneNo" id="phoneNo" placeholder="输入您的手机号">
				        </div>
				        <div class="midbtn btn2" style="cursor: pointer; text-shadow: none;" onclick="getCoupon()">领取红包</div>
				    </div>
			    </c:if>
			</div>
		</form>
		
		<script>
			$(function(){
				$('.bg').css('height',window.innerHeight);
				$('.btn2').css('backgroundColor','#8b8b8b');
				$('.btn2').attr("disabled","disabled");
				var discountCategory = ${discountCategory};
				if (discountCategory == 30) {
					var h = $('.dzk_bot').css('width').split('px')[0];
					$('.dzk_bot').css('height',h*1.26+'px');
				}
				if (discountCategory == 20) {
					var h = $('.hb_bot').css('width').split('px')[0];
					$('.hb_bot').css('height',h*1.26+'px');
				}
				
				$(".midbtn").keyup(function(){
					var length=this.value.length;
					var phoneNo = $("#phoneNo").val();
					var IDCode = $("#sjh_value").val();
					var reg = new RegExp("^[0-9]*$");  
				    if(reg.test(phoneNo) && phoneNo != "") {
				    	if ("+86" == IDCode) {
							if(length==11){
								$('.btn2').css('backgroundColor','#ffa200');
								$('.btn2').removeAttr("disabled");
							}else{
								$('.btn2').css('backgroundColor','#8b8b8b');
								$('.btn2').attr("disabled","disabled");
							}
				    	} else {
					    	$('.btn2').css('backgroundColor','#ffa200');
							$('.btn2').removeAttr("disabled");
				    	}
				    } else {
						$('.btn2').css('backgroundColor','#8b8b8b');
						$('.btn2').attr("disabled","disabled");
				    }
				});
			});

			
			function getCoupon() {
				var phoneNo = $("#phoneNo").val();
				var IDCode = $("#sjh_value").val();
				var reg = new RegExp("^[0-9]*$");  
			    if(reg.test(phoneNo)) {
			    	if ("+86" == IDCode) {
						if (phoneNo.length!=11) {
							alert("请输入正确的手机号");
							$("#phoneNo").val("");
							return false;
						}
						if(isNaN(phoneNo)){
							alert("请输入正确的手机号");
							$("#phoneNo").val("");
							return false;
						}
						$("#couponForm").submit();
						$("#phoneNo").val("");
			    	}
			    } else {
			    	alert("请输入正确的手机号码");
					$("#phoneNo").val("");
					return false;
			    }
			}
			var flag="${complete}";
			if(flag=="10"){
				alert("对不起，您已领取过此款大礼包，快去您的酷特app上查看吧！");
			}
			if(flag=="20"){
				alert("很遗憾，您的手机号已经被其他人推荐过了，换个手机号试试手气吧！");
			}
			
			function startApp(){
				window.location = 'wanna:';
				setTimeout(function(){
					window.location = 'http://a.app.qq.com/o/simple.jsp?pkgname=com.yuandian.wanna&g_f=991653';
				}, 500)
			}
		</script>
	</body>
</html>
