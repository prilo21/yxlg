<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
	<title>登录输手机号</title>
	<link rel="shortcut icon" href="favicon.ico" />
	<link href="resources/public/login/css/style.css" rel="stylesheet">
	<script src="https://source.magicmanufactory.com/js/jquery/jquery.min_2.1.4.js"></script>

</head>

<body>
<div class="tp_container">
    <div class="tp_inputbox">
    	
        <table cellspacing="0" cellpadding="0">
        	<c:if test="${map.ordersNum ne 0}">
	        	<tr><!--  onclick="showOrHideOrdersDiv('newOrderDiv')" -->
	        		<td><img src="resources/public/login/images/xia.jpg"></td>
	            	<td>新建订单</td>
	            </tr>
        	</c:if>
        	
            <tr id="newOrderDiv">
                <td><img src="resources/public/login/images/login_phone.jpg"></td>
                <td>PHONE<br><input type="text" name="phone" id="phone" onkeydown="if(event.keyCode==13){verifyMember()}" placeholder="Enter phone number"></td>
            </tr>
        </table>
        
        <c:if test="${map.ordersNum ne 0}">
	        <table cellspacing="0" cellpadding="0">
	        	<tr id="ordersSlideBtn" onclick="showOrHideOrdersDiv('ordersDiv')">
		        	<td><img src="resources/public/login/images/xia.jpg"></td>
		        	<td>已预约订单</td>
		        </tr>
	        </table>
	        
	        <table id="ordersDiv" cellspacing="0" cellpadding="0">
	        	<c:forEach var="order" items="${map.orders}">
	        		<tr ondblclick="goToMeasureInfo('${order.orderId}')">
	        			<td><img src="resources/public/images/dot3.png"></td>
	        			<td>${order.orderNo} ${order.orderDevice}<br>${order.orderName} - ${order.phoneNo}</td>
	        		</tr>
	        	</tr>
	        	</c:forEach>
        	</table>
        </c:if>
        
    </div>
    
    <button class="tp_btn" onclick="verifyMember()">确定</button>
</div>
</body>
<script>

$(function(){
	$("#ordersDiv").slideUp("fast");
});

function verifyMember(){
	var phone = $("#phone").val();
	if(phone){
		$.ajax({
			type:'GET',
			url:'api/v1/public/members/existence/'+phone,
			dataType: 'json', 
			success:function(data){
				if(data.returnMsg == "N"){
					alert("不存在此会员");
				}else{
					location.href = 'api/v1/public/measurers/${map.mmid}/measureInfo/'+data.returnData.memberId+'/null';
				}
			}
		});
	}else{
		alert("请输入手机号！");
	}
	
}

function showOrHideOrdersDiv(div){
	$("#"+div).slideToggle("fast");
}

function goToMeasureInfo(orderId){
	location.href = 'api/v1/public/measurers/${map.mmid}/measureInfo/null/'+orderId;
}
</script>
</html>
