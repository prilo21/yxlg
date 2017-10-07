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
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title>量体订单列表</title>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<style>
body,img{margin:0;padding:0}
#order_l talbe{
	border-bottom:1px solid #F0F0F0;
}
</style>
</head>
<body>
<div>
<div style="margin:auto;width:80%;padding:10px 0px;overflow:hidden">
<img style="width:30%;float:left" src="https://source.magicmanufactory.com/wx%2Ftailor.png" onclick="openAddMeasureInfoView()"/>
<img style="width:30%;margin-left:5%;float:left" src="https://source.magicmanufactory.com/wx%2Fgarment.png"  onclick="alert('b')"/>
<img style="width:30%;margin-left:5%;float:left" src="https://source.magicmanufactory.com/wx%2Fcompleted.png"  onclick="openDoneList()"/>

</div>
<div style="background:#F0F0F0;height:30px;line-height:30px;padding:10px 20px">未完成的量体订单</div>
<div style="font-size:14px;padding:5px 20px" id="order_l">
<c:forEach var="order" items="${orders}">   
   <table style="border-bottom:1px solid #F0F0F0;width:100%;margin-top:10px;"  class="c_m_info" onclick="toMeasueAdd('${order.member.memberId}','${order.bodyMeasurementPerson.bodyMeasurementPersonId}','${order.orderId}')">
	   <tr>
		   <td valign="top" width="50px">订单号：</td>
		   <td align="left">${order.orderNo }</td>
	   </tr>
	   <tr>
		   <td valign="top">姓&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
		   <td align="left">${order.orderName }</td>
	   </tr>
	   <tr>
		   <td valign="top">电&nbsp;&nbsp;&nbsp;&nbsp;话：</td>
		   <td align="left">${order.phoneNo }</td>
	   </tr>
	   <tr>
		   <td valign="top">地&nbsp;&nbsp;&nbsp;&nbsp;址：</td>
		   <td align="left">${order.addressDetailed }</td>
	   </tr>
	   <tr>
		   <td valign="top">时间：</td>
		   <td align="left">${order.appointTime }</td>
	   </tr>
	   <tr>
		   <td valign="top">备注：</td>
		   <td align="left"></td>
	   </tr>
   </table>
</c:forEach>
</div>
</div>
<script>
$(function(){

})
function toMeasueAdd(mId,pmId,orderId){
	window.location.href = "<%=path%>/tailor/measurers/"+pmId+"/measureInfo/"+mId+"/"+orderId;
}
function openAddMeasureInfoView(){
	window.location.href = "<%=path%>/tailor/measurers/measureInfo";
}
function openDoneList(){
	window.location.href = "<%=path%>/tailor/measure/orders/done";
}
</script>
</body>
</html>