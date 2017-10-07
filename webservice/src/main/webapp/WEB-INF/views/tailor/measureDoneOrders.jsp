<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
<script src="https://source.magicmanufactory.com/underscore.js"></script>
<style>
body,img{margin:0;padding:0}
.order-container {
	display:-webkit-flex;
	display: flex;
	flex-direction: column;
	-webkit-flex-direction:column;
}
.order {
	display: flex;
	display:-webkit-flex;
}
.order-row {
	display: flex;
	display:-webkit-flex;
}
.order-column {
	display: flex;
	display:-webkit-flex;
	flex-direction: column;
	-webkit-flex-direction:column;
}
.order-column > div {
	height: 1rem;
	line-height: 1rem;
	font-size: 1rem;
	margin: 0.3rem 0;
}
.flex-grow {
	flex-grow: 1;
	-webkit-flex-grow: 1;
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
.top-search {
	text-align:center;
	border:1px solid #000;
}
.s-button{
	background:#000;color:#fff;line-height:40px;width:20%;display:inline-block;
}
.top-search input{
	border:0px;width:75%;padding:0px;margin:0px;margin-left:5%;
}
.add-detail{
	display:inline-table;
}
</style>
</head>
<body>
<div>
<div  class="top-search">
	<span style="display:block;height:40px;line-height:40px;"><input id="i-search" type="text" placeholder="姓名或手机号"/><span class="s-button">搜索</span></span>
</div>
<div style="font-size:14px;padding:5px 20px" id="order_l">
<c:forEach var="order" items="${orders}">   
	<div class="order_item">
		<div class="order" data-order-no="${order.orderNo}">
			<div class="order-column">
				<div class="rtl">订单号：</div>
				<div class="rtl">姓名：</div>
				<div class="rtl">电话：</div>
				<div class="rtl">地址：</div>
				<div class="rtl">时间：</div>
				<div class="rtl">备注：</div>
			</div>
			<div class="order-column gap"></div>
			<div class="order-column flex-grow">
				<div class="orderNo">${order.orderNo}</div>
				<div class="orderName">${order.orderName}</div>
				<div class="phoneNo">${order.phoneNo}</div>
				<div class="add-detail">${order.addressDetailed}</div>
				<div>${order.appointTime}</div>
				<div></div>
			</div>
		</div>
		<div class="separtor"></div>
	</div>
</c:forEach>
</div>
</div>
<script>

$(function(){
	$('.order').click(function(){
		window.location.href = "<%=path%>/tailor/members/exclusiveInfo/orderNo/"+$(this).data('order-no');
	})
	$('.s-button').click(function(){
		var n_p = $('#i-search').val();
		if(n_p != ""){
			$('.order_item').css('display','none');
			$('.order_item').each(function(){
				if(isContains($(this).find(".orderName").text(),n_p) || isContains($(this).find(".phoneNo").text(),n_p)){
					$(this).css('display','block');
				}
			});
		}else{
			$('.order_item').css('display','block');
		}
	})
});
//判断字符串的包含关系
function isContains(str, substr) {
    return str.indexOf(substr) >= 0;
}

</script>
</body>
</html>