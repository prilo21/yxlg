<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>  
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, user-scalable=no" />
	<title>分渠道量体订单查询</title>
	<link rel="stylesheet" href="https://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script> 
</head>
<body>
	<h3 style="text-align:center">Cotte酷特个性化定制平台量体预约广告页面用户成功下单预约信息</h2>
    <table id="contentTable" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
		<thead>
			<tr>
				<th>序号</th>
				<th class="sort-column orderNo">订单编号</th>
				<th class="sort-column channel">渠道</th>
				<th class="sort-column adsense">广告位</th>
				<th class="sort-column orderTime">订单时间</th>
				<th class="sort-column orderName">量体人姓名</th>
				<th class="sort-column phoneNo">手机号</th>
				<th class="sort-column cityXd">城市</th>
				<th class="sort-column addressDetailed">地址</th>
				<th class="sort-column orderState">订单状态</th>
				<th class="sort-column orderCancelTime">取消时间</th>
			</tr>
		</thead>
	<c:choose>
		<c:when test="${list.size() == 0}">
			<tbody>
				<tr>
	                <td height="28" colspan="11">
	                   <div align="center">未能找到符合条件的数据!</div>
					</td>
	            </tr>
	       </tbody>
		</c:when>
		<c:otherwise>
			<tbody>
			<c:forEach items="${list}" var="order" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${order.orderNo}</td>
					<td>${order.channel}</td>
					<td>${order.adsense}</td>
					<td>${order.orderTime}</td>
					<td>${order.orderName}</td>
					<td>${order.phoneNo}</td>
					<td>${order.cityXd}</td>
					<td>${order.addressDetailed}</td>
					<td>${order.orderState}</td>
					<td>${order.orderCancelTime}</td>
				</tr>
			</c:forEach>
			</tbody>
		</c:otherwise>
	</c:choose>
</body>
</html>