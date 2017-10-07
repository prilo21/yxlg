<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib uri="http://com.yuandian.util.datejstl/tags" prefix="date" %>
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
	    <div class="dzk_di">
	    
			<!-- 优惠券 -->
	        <c:if test="${discountCategory==20}">
	        	<img class="hb_fxt" src="/c2mwebservice/resources/public/images/hb_fxt.png" width="100%" style="margin-left: 20%">
		        <c:forEach items="${result}" var="coupon">
			        <table class="hb" cellpadding="0" cellspacing="0">
			        	<tr>
			            	<td width="24%"></td>
			            	
			            	<td width="32%" class="hb_yqq">满<c:out value="${coupon.amountRange}"></c:out>元使用<br><span>有效期至<date:date pattern="yyyy-MM-dd" value="${coupon.ruleEnd}" /></span></td>
			                <td class="hb_zhec">
								<%-- <c:set var="amountLength" value="${fn:length(coupon.amount)}" />
								<c:set var="amountPointIndex" value="${fn:indexOf(coupon.amount,'.')}" />
								<c:if test="${amountLength >= 1}">
									<c:forEach begin="0" end="${amountLength-1}" varStatus="amountIndex">
										<c:set var="imgIndexStr" value="${fn:substring(coupon.amount,amountIndex.index,amountIndex.index+1)}" />
										<c:choose>
											<c:when test="${amountPointIndex!=-1 && amountPointIndex <= amountIndex.index}">
												<!-- 输出点符号的图片，比如7.5的"." -->
												<!-- <img src="/c2mwebservice/resources/public/images/hb/point.png" width="3%"/> -->
											</c:when>
											<c:otherwise>
												<img src="/c2mwebservice/resources/public/images/hb/${imgIndexStr}.png"  width="20%"/>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:if> --%>
								
								<c:set var="strAmount" value="K${coupon.amount}" />
								<c:set var="strAmountReal" value="${fn:substringAfter(strAmount, 'K')}" />
								<c:set var="amountLength" value="${fn:length(strAmountReal)}" />
								<c:set var="amountPointIndex" value="${fn:indexOf(strAmountReal,'.')}" />
								<c:if test="${amountLength >= 1}">
									<c:forEach begin="0" end="${amountLength-1}" varStatus="amountIndex">
										<c:set var="imgIndexStr" value="${fn:substring(strAmountReal,amountIndex.index,amountIndex.index+1)}" />
										<c:choose>
											<c:when test="${amountPointIndex!=-1 && amountPointIndex <= amountIndex.index}">
												<!-- 输出点符号的图片，比如7.5的"." -->
												<!-- <img src="/c2mwebservice/resources/public/images/hb/point.png" width="3%"/> -->
											</c:when>
											<c:otherwise>
												<img src="/c2mwebservice/resources/public/images/hb/${imgIndexStr}.png"  width="15%"/>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:if>
								
								<img src="/c2mwebservice/resources/public/images/yuan.png" width="10%"></td>
			                <td width="11%"></td>
			            </tr>
			        </table>
				</c:forEach>
				<p class="dzk_fxt2">红包已放入账户<br><span><c:out value="${phoneNo}"></c:out></span></p>
	        </c:if>
			
			<!-- 打折卡  discountCategory==30-->
			<c:if test="${discountCategory==30}">
	        	<img class="dzk_fxt" src="/c2mwebservice/resources/public/images/dzk_fxt.png" width="100%" style="margin-left: 20%">
		        <c:forEach items="${result}" var="coupon">
					<table class="dzk" cellpadding="0" cellspacing="0">
			        	<tr>
			            	<td width="28%"></td>
			            	
			            	<td width="34%" class="dzk_yqq">有效期至<br>
			            		<fmt:formatDate value="${coupon.validTo}" pattern="yyyy-MM-dd" />
			            	</td>
			                <td class="dzk_zhec">
			                	<!-- 将double类型转为String类型，jstl没有把数字转换为string的标签，比如<ftm:parseString />之类 -->
			                	<c:set var="couponCardPercent">
			                		<c:out value="${coupon.discountCardPercent * 10}"></c:out>
			                	</c:set>
								<c:set var="amountLength" value="${fn:length(couponCardPercent)}" />
								<c:set var="amountPointIndex" value="${fn:indexOf(couponCardPercent,'.')}" />
								<c:if test="${amountLength >= 1}">
									<c:forEach begin="0" end="${amountLength-1}" varStatus="amountIndex">
										<c:set var="imgIndexStr" value="${fn:substring(couponCardPercent,amountIndex.index,amountIndex.index+1)}" />
										<c:choose>
											<c:when test="${amountPointIndex == amountIndex.index}">
												<!-- 输出点符号的图片，比如7.5的"." -->
												<img src="/c2mwebservice/resources/public/images/dzk/point.png" width="3%"/>
											</c:when>
											<c:otherwise>
												<img src="/c2mwebservice/resources/public/images/dzk/${imgIndexStr}.png"  width="25%"/>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:if>
								<img src="/c2mwebservice/resources/public/images/zhe.png" width="20%">
							</td>
			                <td width="14%"></td>
			            </tr>
					</table>
				</c:forEach>
				<p class="dzk_fxt2">打折卡已放入账户<br><span><c:out value="${phoneNo}"></c:out></span></p>
			</c:if>
	    </div>
	</div>
	<script>
		$(function(){
			$('.bg').css('height',window.innerHeight);
			var h = window.innerWidth;
			$('.dzk_di').css('height',h*1.35+'px');

			var discountCategory = <%=request.getAttribute("discountCategory")%>;
			if(discountCategory=="20"){
				$('.hb').css('height',h*0.3+'px');	
			}else if(discountCategory=="30"){
				$('.dzk').css('height',h*0.3+'px');	
			}
			
			$(".midbtn").keyup(function(){
				var length=this.value.length;
				if(length==11){
					$('.btn2').css('backgroundColor','#ffa200');
				}else{
					$('.btn2').css('backgroundColor','#8b8b8b');
				}
			});
			
		});
		
		function startApp(){
			window.location = 'wanna:';
			setTimeout(function(){
				window.location = 'http://a.app.qq.com/o/simple.jsp?pkgname=com.yuandian.wanna&g_f=991653';
			}, 500)
		}
	</script>
	</body>
</html>
