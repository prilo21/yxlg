<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
<title>退回重做</title>
<link href="https://source.magicmanufactory.com/resources/jquery/mobile/reDo.css" rel="stylesheet">
<script src="https://source.magicmanufactory.com/resources/jquery/mobile/jquery-2.0.3.min.js" language="javascript"></script>
<link
	href="https://img.magicmanufactory.com/@/jquery/mobile/jquery.mobile-1.4.5.css"
	rel="stylesheet">
	<link
	href="https://img.magicmanufactory.com/@/jquery/mobile/jquery.mobile-1.4.5.min.css"
	rel="stylesheet">
	<script src="https://source.magicmanufactory.com/js/jquery/jquery.min_2.1.4.js"></script>
<script src="https://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
</head>

<body>
 	<div data-role="popup" id="popupDialog" data-overlay-theme="b" data-theme="b" data-dismissible="false" style="max-width:400px;">
    <div data-role="header" data-theme="a">
    <h3>支付确认结果页</h3>
    </div>
    <div role="main" class="ui-content">
        <h3 class="ui-title" id="waitingTag">支付等待中...</h3>
    <p>请支付或确认动作完成后选择此操作</p>
	<fieldset  class="ui-grid-a" > 
		<div class="ui-block-a">
       	 <a href="#" id = "finishId" class="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-btn-b" data-rel="back"  onclick='payToFinish()'>支付完成</a>
		</div>
		<div class="ui-block-b">
       	 <a href="#" id ="problemId" class="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-btn-b" data-rel="back" data-transition="flow" onclick ='payToProblem()'>遇到问题</a>
		</div>
    </fieldset>
	</div>
	</div>
<div class="fx_container">
	<table cellpadding="0" cellspacing="0">
    	<tr>
        	<td width="35%">原订单号：</td>
            <td width="65%" class="hui">${reDo.tradeOrderNo}</td>
        </tr>
        <tr>
        	<td>重做订单号:</td>
            <td class="hui">${reDo.newOrderNo}</td>
        </tr>
        <tr>
        	<td>产品名称:</td>
            <td class="hui">${reDo.goodsName}</td>
        </tr>
          <tr>
        	<td>金额:</td>
            <td class="hui" id="feiyong">${reDo.price}<!--  ￥ --></td>
        </tr>
        
        <tr>
        	<td>重做原因:</td>
            <td class="hui">${reDo.reason}</td>
        </tr>
        <tr>
        	<td>重做工期:</td>
            <td class="hui">7天</td>
        </tr>
      <%--   <tr>
        	<td>面料编码:</td>
            <td class="hui">${reDo.fabricCode}</td>
        </tr>
        <tr>
        	<td>主要工艺内容：</td>
            <td class="hui">主题要工艺内容</td>
        </tr> --%>
        <!-- <tr>
        	<td>标准尺码或量体尺寸：</td>
            <td class="hui">xxxxx码 或 XXX先生/小姐的量体尺寸</td>
        </tr> -->
        <tr>
        	<td>客户信息:</td>
            <td class="hui">${reDo.receiver}<br>
		${reDo.phoneNo}<br>
		${reDo.province}${reDo.city}${reDo.suburb}${reDo.addressDetail}</td>
        </tr>
        
        	<tr id="payId">
				<td>支付方式:</td>
				<td><div class="hui" id="payChanel">
				    <fieldset data-role="controlgroup" data-type="horizontal" data-mini="true">
					<input type="radio" name="payMethod" id="zhifubaoId" value="02" checked="checked" />
         			<label for="zhifubaoId">&nbsp;支付宝&nbsp;</label>

         			<input type="radio" name="payMethod" id="radio-choice-22" value="01"  />
         			<label for="radio-choice-22">&nbsp;银联&nbsp;</label>
         			  </fieldset>
					</div></td>
			</tr>
       <!--  <tr>
        	<td>退货物流信息:</td>
            <td class="hui"><input class="hui" placeholder="手动输入"><span>确认</span></td>
        </tr> -->
    </table>
      <div style="background-color:#e0e0e0; line-height:30px; margin:10px auto; text-align:center;" id="processBtn">
			<a id ="operateBtn" onclick="gotoPay()" href="#popupDialog" data-rel="popup" data-position-to="window" data-transition="pop" data-role="button" data-inline="true" data-theme="b">&nbsp;支&nbsp;付&nbsp;&nbsp;</a>
		</div>
	<div class="fx_jindu">
    	<h1>重做进度：</h1>
       <ul class="fx_ul1">
			<c:if test="${reDo.newOrderStatus eq 100}">
				<li class="fx_fdot"><span>用户未发货</span> <span class="fx_sj"><script type="text/javascript">
					var d = new Date();
					var str = d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
					document.write(str);
				</script></span></li>
			</c:if>
			<c:if test="${reDo.newOrderStatus eq 199  }">
				<li class="fx_fdot"><span>用户已发货</span> <span class="fx_sj"><spring:eval expression="repair.repairOrder.customerDeliverDate" /></span></li>
			</c:if>
			<c:if test="${reDo.newOrderStatus eq 200}">
				<li class="fx_fdot"><span>用户已发货</span> <span class="fx_sj"><spring:eval expression="repair.repairOrder.customerDeliverDate" /></span></li>
				<li class="fx_fdot"><span>重做中</span> <span class="fx_sj"><spring:eval expression="repair.repairOrder.repairTime" /></span></li>
			</c:if>
			<c:if test="${reDo.newOrderStatus eq 400}">
				<li class="fx_fdot"><span>用户已发货</span> <span class="fx_sj"><spring:eval expression="repair.repairOrder.customerDeliverDate" /></span></li>
				<li class="fx_fdot"><span>重做中</span> <span class="fx_sj"><spring:eval expression="repair.repairOrder.repairTime" /></span></li>
				<li class="fx_fdot"><span>工厂已发货</span> <span class="fx_sj"><spring:eval expression="repair.repairOrder.deliveryTime" /></span></li>
				<li class="fx_fdot"><span>完成</span> <span class="fx_sj"><spring:eval expression="repair.repairOrder.estimatedDate" /></span></li>
				
			</c:if>
			
			</ul>
    </div>
   <div class="fx_btn">确认收货</div>
</div>
<script type="text/javascript">
	
		$(document).ready(function (){
			
			var price = "${reDo.price}";		//价格	 
			if(parseInt(price)==0){
				$('#operateBtn').text("确定");
				$('#payId').hide();
			}
			
		});
		
	
		function gotoPay(){
		 var reDoOrderId= "${reDo.newOrderId}";
			var money = $("#feiyong").text();
			//价格为0时提交后台的json,带着默认的发票信息
			var jsonValue = {
					"reDoOrderId":reDoOrderId,
			};
			//1.判断价钱
			if (parseInt(money) == 0) {
				$.ajax({
					type : "POST",
					contentType: "application/json",
					url : "${ctx}/api/v1/public/trade/reDo/unpayReDoFinished",
					data :  JSON.stringify(jsonValue),
					success : function(data) {
						$('#operateBtn').text("完成");
					   	var timestamp = (new Date()).valueOf();  
					   	var str ="${reDo.tradeOrderNo}";
						str = str + "/t/" + timestamp;  
						window.location.href= "${ctx}/api/v1/public/trade/reDo/"+str;
					}
				}) 
			} else {//表示需要支付		
				var payMethod = $('input:radio[name="payMethod"]:checked').val();
				 if (payMethod == null) {
					alert("您好,请选择支付方式!");
					return false;
				} 
			
				var messageToPost = {
					"reDoOrderId" : reDoOrderId,
					"payChanel" : payMethod  
				};
				
				var ua = navigator.userAgent.toLowerCase();
			 	console.info(JSON.stringify(messageToPost));
			 	console.info(JSON.stringify(messageToPost));
				if (/iphone|ipad|ipod/.test(ua)) { //表示的是ios
					window.webkit.messageHandlers.payRedoClick
							.postMessage(JSON.stringify(messageToPost));
				} else if (/android/.test(ua)) { //表示的是android
					window.jsObj.postPayRedoMessage(JSON.stringify(messageToPost));
				} else {
					alert("请在移动设备上使用");
				} 
			}
	}
	

		function payToProblem(){
			var money = $("#feiyong").text();
			if(parseInt(money) != 0 ){
				alert("请重新支付");
			}
		   	var timestamp = (new Date()).valueOf();  
		   	var str ="${reDo.tradeOrderNo}";
			str = str + "/t/" + timestamp;  
			window.location.href= "${ctx}/api/v1/public/trade/reDo/"+str;
		}
		
		function payToFinish(){
		   	var timestamp = (new Date()).valueOf();  
		   	var str ="${reDo.tradeOrderNo}";
			str = str + "/t/" + timestamp;  
			window.location.href= "${ctx}/api/v1/public/trade/reDo/"+str;
	}

</script>

</body>
</html>
