<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=path%>">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
<meta http-equiv="expires" content="0">
<meta name="apple-touch-fullscreen" content="yes">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title>量体订单列表</title>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<style>
body,img{margin:0;padding:0}
.order-container {
	display:-webkit-flex;
	display: flex;
	flex-direction: column;
	-webkit-flex-direction:column;
}
.order {
	display:-webkit-flex;
	display: flex;
}
.order-row {
	display: flex;
	display:-webkit-flex;
}
.order-column {
	display:-webkit-flex;
	display: flex;
	flex-direction: column;
	-webkit-flex-direction:column;
}
.order-column > div {
	height: 1rem;
	line-height: 1rem;
	font-size: 1rem;
	margin: 0.3rem 0;
	overflow: scroll;
}
.flex-grow {
	flex-grow: 1;
	-webkit-flex-grow: 1;
}
.no-shrink {
	flex-shrink: 0;
	-webkit-flex-shrink: 0;
}
.rtl {
	text-align: right;
}
.gap {
	width: 0.8rem;
}
.separtor{
	height: 1px;
	background-color: #F0F0F0;
	margin: 0.1rem 0 0.5rem 0;
}

</style>
</head>
<body>
<div>
	<div style="margin:auto;width:80%;padding:10px 0px;overflow:hidden">
		<img style="width:30%;float:left" src="https://source.magicmanufactory.com/wx%2Ftailor.png" onclick="openAddMeasureInfoView()"/>
		<img style="width:30%;margin-left:5%;float:left" src="https://source.magicmanufactory.com/wx%2Fgarment.png"  onclick="openGarmentSizeView()"/>
		<img style="width:30%;margin-left:5%;float:left" src="https://source.magicmanufactory.com/wx%2Fcompleted.png"  onclick="openDoneList()"/>
	</div>
	<div style="background:#F0F0F0;height:30px;line-height:30px;padding:10px 20px">未完成的量体订单</div>
	<div style="font-size:14px;padding:5px 20px" id="order_l" class="order-container">
	<c:forEach var="order" items="${orders}">
		<div class="order" 
			data-member-id="${order.member.memberId}" 
			data-tailor-id="${order.bodyMeasurementPerson.bodyMeasurementPersonId}"
			data-order-id="${order.orderId}"
		>
			<div class="order-column no-shrink">
				<div class="rtl">订单号：</div>
				<div class="rtl">姓名：</div>
				<div class="rtl">电话：</div>
				<div class="rtl">地址：</div>
				<div class="rtl">时间：</div>
				<div class="rtl">备注：</div>
			</div>
			<div class="order-column gap"></div>
			<div class="order-column flex-grow">
				<div>${order.orderNo}</div>
				<div>${order.orderName}</div>
				<div>${order.phoneNo}</div>
				<div>${order.addressDetailed}</div>
				<div>${order.appointTime}</div>
				<div></div>
			</div>
		</div>
		<div class="separtor"></div>
	</c:forEach>
	</div>
</div>
<script>
$(function(){
	$('[name="tel"]').click(function(e){
		e.stopPropagation();
	});
	$('.order').click(function(){
		window.location.href = "<%=path%>/tailor/measurers/"
								+ $(this).data('tailor-id')
								+ '/measureInfo/'
								+ $(this).data('member-id')
								+ '/'
								+ $(this).data('order-id');
	});
})
function openAddMeasureInfoView(){
	window.location.href = "<%=path%>/tailor/measurers/measureInfo";
}
function openDoneList(){
	window.location.href = "<%=path%>/tailor/measure/orders/done";
}
function openGarmentSizeView(){
	window.location.href = "<%=path%>/tailor/measurers/garmentSize";
}
</script>
</body>
</html>