<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache, must-revalidate">
	<meta http-equiv="expires" content="0">
	<meta name="keywords" content="场景应用">
	<meta name="description" content="场景应用">
	<meta name="apple-touch-fullscreen" content="yes">
	<meta name="format-detection" content="telephone=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	<title>返修</title>
	<link href="https://source.magicmanufactory.com/resources/jquery/mobile/style-repair.css" rel="stylesheet">
	<link href="https://img.magicmanufactory.com/@/jquery/mobile/jquery.mobile-1.4.5.css" rel="stylesheet">
	
	<style type="text/css">
		.ui-loader-default {
			display: none
		}
		
		.ui-mobile-viewport {
			border: none;
		}
		
		.ui-page {
			padding: 0;
			margin: 0;
			outline: 0
		}
		
		label.error {
			color: red;
			font-size: 16px;
			font-weight: normal;
			line-height: 1.4;
			margin-top: 0.5em;
			width: 100%;
			float: none;
		}
		
		@media screen and (orientation: portrait) {
			label.error {
				margin-left: 0;
				display: block;
			}
		}
		
		@media screen and (orientation: landscape) {
			label.error {
				display: inline-block;
				margin-left: 22%;
			}
		}
		
		em {
			color: red;
			font-weight: bold;
			padding-right: .25em;
		}
		
		form {
			width: 100%;
			margin-left: 0%;
			padding-top: 20px;
		}
	</style>
	<script src="https://dn-c2m-resources.qbox.me//resources/jquery/mobile/jquery.min.js"></script>
	<script src="https://dn-c2mplatform.qbox.me/@/jquery/mobile/images/jquery.validate.js"></script>
	<script src="https://dn-c2m-resources.qbox.me/resources/jquery/mobilejquery.mobile-1.4.5.min.js"></script>

</head>
<body>
 	<div data-role="popup" id="popupDialog" data-overlay-theme="b" data-theme="b" data-dismissible="false" style="max-width:400px;">
	    <div data-role="header" data-theme="a"><h3>支付确认结果页</h3></div>
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
		<form action="" method="post" id="form1Id">
			<input type="hidden" name="repairOrderId" value="${repair.repairOrder.repairOrderId}"/>
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td width="35%">原订单号：</td>
					<td width="65%" class="hui">${repair.repairOrder.tradeOrderNo}</td>
				</tr>
				<tr>
					<td>返修订单号:</td>
					<td class="hui" id="repairOrderNoId">${repair.repairOrder.repairOrderNo}</td>
				</tr>
				<tr>
					<td>产品名称:</td>
					<td class="hui"><%-- ${repair.goodsName} --%>
						<%-- <c:forEach var="goods" items="${repairList}" > </c:forEach> --%>
						<ul><li> <c:out value="${repair.goodsName}" /></li></ul>           			    		             	
	             	</td>
				</tr>
				<tr>
					<td>返修内容:</td>
					<td class="hui">
						<%-- <c:forEach var="processAndPart" items="${repairList}" > </c:forEach> --%>
							<ul>
								<li><c:out value="${repair.repairProcessName}"/></li>
								<li><c:out value="${repair.repairPartName}"/></li>
							</ul>						
					</td>
				</tr>
				<tr>
					<td>返修原因:</td>
					<td class="hui">
						<%-- <c:forEach var="reason" items="${repairList}" > </c:forEach> --%>
							<ul>
								<li><c:out value="一级原因——${repair.responsibilityInfoA}"/></li>
								<li><c:out value="二级原因——${repair.responsibilityInfoB}"/></li>
							</ul>					
				    </td>								
				</tr>
				<%--   <tr>
	        	<td>返修措施:</td>
	            <td class="hui">${repair.repairOrderNo}</td>
	        </tr> --%>
				<tr>
					<td>返修费用(￥):</td>
					<td class="hui" id="feiyong">${repair.repairOrder.price}</td>
				</tr>
	<%-- 			<tr>
					<td>返修工期:</td>
					<td class="hui">
						${repair.repairOrderNo}
					</td>
				</tr> --%>
				<tr>
					<td>客户信息:</td>
					<td class="hui">
					    ${repair.repairOrder.receiver}<br>
						${repair.repairOrder.phoneNo}<br>
						${repair.repairOrder.province}${repair.repairOrder.city}
						${repair.repairOrder.suburb}${repair.repairOrder.addressDetail}
					</td>
				</tr>
				<tr id="fp_info">
					<td>发票信息:</td>
					<td class="hui">
						<div id ="fp_pt_show">
							<ul>
								<li>${invoiceInfo.orderTypeTag}</li>
								<li id="fp_pt_show_name">${invoiceInfo.taxerName}</li>
								<li id="fp_pt_show_depart">${invoiceInfo.companyName}</li>
							</ul>
						</div>
					
						<div id ="fp_zz_show">
							<ul>
								<li>${invoiceInfo.orderTypeTag}</li>
								<li id="fp_zz_show_company">${invoiceInfo.companyName}</li>
								<li id="fp_zz_show_taxno">${invoiceInfo.taxerIdNo}</li>
								<li id="fp_zz_show_address">${invoiceInfo.address}</li>
								<li id="fp_zz_show_phone">${invoiceInfo.phoneNo}</li>
								<li id="fp_zz_show_bank">${invoiceInfo.accountBank}</li>
								<li id="fp_zz_show_account">${invoiceInfo.accountNo}</li>
							</ul>
						</div>
						<div class="fp_btn_close" id="fp_btn"></div>
					</td>
				</tr>
				<tr id="payId">
					<td>支付方式:</td>
					<td>
						<div class="hui" id="payChanel">
							<fieldset data-role="controlgroup" data-type="horizontal" data-mini="true">
								<input type="radio" name="payMethod" id="zhifubaoId" value="02" checked="checked" />
			         			<label for="zhifubaoId">&nbsp;支付宝&nbsp;</label>
			
			         			<input type="radio" name="payMethod" id="radio-choice-22" value="01"  />
			         			<label for="radio-choice-22">&nbsp;银联&nbsp;</label>
	         			  	</fieldset>
						</div>
					</td>
				</tr>
			</table>
		</form>
		<div class="fp_main fp_select">
			<ul class="fp_nav">
				<li class="fp_nav_ml fp_nav_selected" check-id="fp_pt">普通发票</li>
				<li class="fp_nav_ml " check-id="fp_zz">增值税专用发票</li>
			</ul>
			<div class="fp_select_view fp_select" id="fp_pt">
				<form action="" id="form1" method="post">
					<input type="hidden" name="orderType" value="2"/>
					<input type="hidden" name="repairOrderId" value="${repair.repairOrder.repairOrderId}"/>
					<input type="hidden" name="orderNo" value="${repair.repairOrder.tradeOrderNo}"/>
					
					<table cellpadding="0" cellspacing="0" >
						<tr>
							<td width="10%"><input type="radio" id="fp_pt_radio1" name="fp" checked="checked" value="1"></td>
							<td width="20%"><span
								style="display: block; text-align: center;">抬头</span></td>
							<td width="70%"><input id="fp_pt_input1" name="taxerName" type="text"></td>
						</tr>
					</table>
					<input class="submit"  type="button" value="确认发票信息" onclick="fapiaosubmit()" />
				</form>
			</div>
			
			<div class="fp_select" id="fp_zz">
				<form action="" id="form2" method="post">
					<input type="hidden" name="orderType" value="0"/>
					<input type="hidden" name="repairOrderId" value="${repair.repairOrder.repairOrderId}"/>
					<input type="hidden" name="orderNo" value="${repair.repairOrder.tradeOrderNo}"/>
					<fieldset>
						<table >
							<tr>
								<td width="30%"><label for="companyName">单&nbsp;位&nbsp;名&nbsp;称</label></td>
								<td width="70%"><input type="text" name="companyName" id="companyName" ></td>
							</tr>
							<tr>
								<td> <label for="taxerIdNo">纳税人识别号</label></td>
								<td><input type="text" name="taxerIdNo" id="taxerIdNo" ></td>
							</tr>
							<tr>
								<td><label for="address">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址</label></td>
								<td><input type="text" name="address" id="address" ></td>
							</tr>
							<tr>
								<td>电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话</td>
								<td><input type="text" id="phone" name="phoneNo" ></td>
							</tr>
							<tr>
								<td>开&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;&nbsp;行</td>
								<td><input type="text" id="bank" name="accountBank" ></td>
							</tr>
							<tr>
								<td>账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</td>
								<td><input type="text" id="account" name="accountNo" ></td>
							</tr>
						</table>
						<input id="confirmBtn" class="submit" type="submit" value="确认发票信息" />
					</fieldset>
				</form>
			</div>
		</div>
		<div style="background-color:#e0e0e0; line-height:30px; margin:10px auto; text-align:center;" id="processBtn">
			<a id ="operateBtn" onclick="gotoPay()" href="#popupDialog" data-rel="popup" data-position-to="window" data-transition="pop" data-role="button" data-inline="true" data-theme="b">&nbsp;支&nbsp;付&nbsp;&nbsp;</a>
		</div>
		<!--    <table cellpadding="0" cellspacing="0">
        <tr>
        	<td width="36%">退货物流信息:</td>
            <td class="hui" width="64%"><input class="hui que_inp" placeholder="手动输入"><span class="queren">确认</span></td>
        </tr>
    </table> -->
		<div class="fx_jindu">
			<h1>返修进度：</h1>
			<ul class="fx_ul1">

			<c:if test="${repair.repairOrder.repairStatus eq 000}">
				<li class="fx_fdot">
					<span>用户未发货</span>
					<span class="fx_sj">
						<script type="text/javascript">
							var d = new Date();
							var str = d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
							document.write(str);
						</script>
					</span>
				</li>
			</c:if>

			<c:if test="${repair.repairOrder.repairStatus eq 002}">
				<li class="fx_fdot"><span>用户已发货</span> <span class="fx_sj"><spring:eval expression="repair.repairOrder.customerDeliverDate" /></span></li>
			</c:if>

			<c:if test="${repair.repairOrder.repairStatus eq 200}">
				<li class="fx_fdot"><span>用户已发货</span> <span class="fx_sj"><spring:eval expression="repair.repairOrder.customerDeliverDate" /></span></li>
			</c:if>

			<c:if test="${repair.repairOrder.repairStatus eq 300}">
				<li class="fx_fdot"><span>用户已发货</span> <span class="fx_sj"><spring:eval expression="repair.repairOrder.customerDeliverDate" /></span></li>
				<li class="fx_fdot"><span>返修中</span> <span class="fx_sj"><spring:eval expression="repair.repairOrder.repairTime" /></span></li>
			</c:if>

			<c:if test="${repair.repairOrder.repairStatus eq 400}">
				<li class="fx_fdot"><span>用户已发货</span> <span class="fx_sj"><spring:eval expression="repair.repairOrder.customerDeliverDate" /></span></li>
				<li class="fx_fdot"><span>返修中</span> <span class="fx_sj"><spring:eval expression="repair.repairOrder.repairTime" /></span></li>
				<li class="fx_fdot"><span>工厂已发货</span> <span class="fx_sj"><spring:eval expression="repair.repairOrder.deliveryTime" /></span></li>

			</c:if>
			<c:if test="${repair.repairOrder.repairStatus eq 500}">
				<li class="fx_fdot"><span>用户已发货</span> <span class="fx_sj"><spring:eval expression="repair.repairOrder.customerDeliverDate" /></span></li>
				<li class="fx_fdot"><span>返修中</span> <span class="fx_sj"><spring:eval expression="repair.repairOrder.repairTime" /></span></li>
				<li class="fx_fdot"><span>工厂已发货</span> <span class="fx_sj"><spring:eval expression="repair.repairOrder.deliveryTime" /></span></li>
				<li class="fx_fdot"><span>完成</span> <span class="fx_sj"><spring:eval expression="repair.repairOrder.estimatedDate" /></span></li>
			</c:if>
			</ul>
		</div>
		<div class="fx_btn" id="querenBtn">
			<a onclick="confirmReceived()" href="#" id="confirmTag"><font color="white">确认收货</font></a>
		</div>
	</div>
	
	<script>	
		jQuery.extend(jQuery.validator.messages, {
		    required: "这个内容必须输入.",
		    remote: "Please fix this field.",
		    email: "Please enter a valid email address.",
		    url: "Please enter a valid URL.",
		    date: "Please enter a valid date.",
		    dateISO: "Please enter a valid date (ISO).",
		    number: "Please enter a valid number.",
		    digits: "Please enter only digits.",
		    creditcard: "Please enter a valid credit card number.",
		    equalTo: "Please enter the same value again.",
		    accept: "Please enter a value with a valid extension.",
		    maxlength: jQuery.validator.format("Please enter no more than {0} characters."),
		    minlength: jQuery.validator.format("Please enter at least {0} characters."),
		    rangelength: jQuery.validator.format("Please enter a value between {0} and {1} characters long."),
		    range: jQuery.validator.format("Please enter a value between {0} and {1}."),
		    max: jQuery.validator.format("Please enter a value less than or equal to {0}."),
		    min: jQuery.validator.format("Please enter a value greater than or equal to {0}.")
		});
	
		// 发票类型，默认为普通发票
		var fp_type = "fp_pt";
	
		$(function() {
		    // 获取发票类型
		    var flag = "${invoiceInfo.orderType}";
	
		    // 普通发票,增值税发票信息隐藏，普通发票信息展示
		    if (2 == flag) {
		        $('#fp_zz_show').hide();
		        $('#fp_pt_show').show();
		        // 增值税发票，普通发票信息隐藏，增值税发票信息展示
		    } else {
		        $('#fp_pt_show').hide();
		        $('#fp_zz_show').show();
		    }
	
		    $('.fp_nav li').bind('click',
		    function() {
		        $('.fp_nav li').removeClass('fp_nav_selected');
		        $(this).addClass('fp_nav_selected');
		        $('.fp_select').removeClass('fp_select_view');
		        $('#' + $(this).attr('check-id')).addClass('fp_select_view');
		        fp_type = $(this).attr('check-id');
		        if (fp_type == "fp_pt") {
		            $('#form2 :input').val("");
		        } else if (fp_type == "fp_zz") {
		            $('#fp_pt_input1').val("");
		            $('#fp_pt_input2').val("");
		        }
		    });
	
		    $('#fp_btn').bind('click',
		    function() {
		        if ($(this).attr('class') == 'fp_btn_close') {
		            $(this).removeClass('fp_btn_close');
		            $(this).addClass('fp_btn_open');
		            $('.fp_main').removeClass('fp_select');
		            $('.fp_main').addClass('fp_select_view');
		        } else {
		            $(this).removeClass('fp_btn_open');
		            $(this).addClass('fp_btn_close');
		            $('.fp_main').removeClass('fp_select_view');
		            $('.fp_main').addClass('fp_select');
		        }
		    });
	
		    $("#fp_pt_input2").attr("disabled", "disabled");
		    $("#fp_pt_radio1").click(function() {
		        $("#fp_pt_input1").removeAttr("disabled");
		        $("#fp_pt_input2").val("");
		        $("#fp_pt_input2").attr("disabled", "disabled");
		    });
	
		    $("#fp_pt_radio2").click(function() {
		        $("#fp_pt_input2").removeAttr("disabled");
		        $("#fp_pt_input1").val("");
		        $("#fp_pt_input1").attr("disabled", "disabled");
		    });
	
		    // 返修单状态
		    var orderSatus = "${repair.repairOrder.repairStatus}";
	
		    // 返修费用
		    var money = $("#feiyong").text();
	
		    if (parseInt(orderSatus) >= 400) {
		        if (parseInt(orderSatus) == 400) {
		            $('#querenBtn').show();
		        } else {
		            $('#querenBtn').show();
		            $('#confirmTag').removeAttr('onclick');
		        }
		    } else {
		        $('#querenBtn').hide();
		    }
		    // 判断收货状态
		    // 如果不是等待用户确认或付款
		    if (parseInt(orderSatus) != 200) {
		        $('#processBtn').hide();
		        $('#payId').hide();
		        if (parseInt(orderSatus) >= 300) {
		            $('#fp_btn').hide();
		            if (parseInt(money) == 0) $('#fp_info').hide();
		            if (parseInt(orderSatus) == 500) {
		                $('#confirmTag').text("已收货");
		            }
		        }
		    // 等待用户确认或付款
		    } else {
		        //判断支付状态			
		        if (parseFloat(money) <= 0) {
		            $('#fp_info').hide();
		            $('#operateBtn').text("确认");
		            $('#payId').hide();
		            $('#waitingTag').text("确认等待中...");
		            $('#finishId').text("确认完成");
		            $('#problemId').hide();
		        } else {
		            $('#operateBtn').text("支付");
		        }
		    }
			
		    // 支付方式默认选中支付宝
		    $('#zhifubaoId').attr("checked", true);
	
		    //validation rules
		    $("#form2").validate({
		        rules: {
		            "taxerIdNo": {
		                maxlength: 30
		            },
		            "companyName": {
		                required: true,
		                maxlength: 30
		            }
		        },
		        errorPlacement: function(error, element) {
		            error.insertAfter(element.parent());
		        },
		        //perform an AJAX post to ajax.php
		        submitHandler: function() {
		            $.post('${ctx}/api/v1/public/saveInvoiceInfo', $('form#form2').serialize(),
		            function(data) {
		                //  alert(data.msg);
		                var timestamp = (new Date()).valueOf();
		                var str = "${repair.repairOrder.tradeOrderNo}";
		                str = str + "/t/" + timestamp;
		                window.location.href = "${ctx}/api/v1/public/trade/repairInfo/" + str;
		            },
		            "json");
		        }
		    });
		})
	
		function gotoPay() {
		    var repairOrderId = "${repair.repairOrder.repairOrderId}";
		    var orderType = "1";
		    var money = $("#feiyong").text();
		    //价格为0时提交后台的json,带着默认的发票信息
		    var jsonValue = {
		        "repairOrderId": repairOrderId,
		    };
		    // 无需支付
		    if (parseFloat(money) <= 0) {
		        $.ajax({
		            type: "POST",
		            contentType: "application/json",
		            url: "${ctx}/api/v1/public/trade/unpayFinished",
		            data: JSON.stringify(jsonValue),
		            success: function(data) {
		                $('#operateBtn').text("完成");
		            }
		        })
		    // 需要支付	
		    } else {	
		        var payMethod = $('input:radio[name="payMethod"]:checked').val();
		        if (payMethod == null) {
		            alert("您好,请选择支付方式!");
		            return false;
		        }
	
		        var messageToPost = {
		            "repairOrderId": repairOrderId,
		            "payChanel": payMethod
		        };
		        
		        var ua = navigator.userAgent.toLowerCase();
		        if (/iphone|ipad|ipod/.test(ua)) { //表示的是ios
		            window.webkit.messageHandlers.payClick.postMessage(JSON.stringify(messageToPost));
		        } else if (/android/.test(ua)) { //表示的是android
		            window.jsObj.postPayMessage(JSON.stringify(messageToPost));
		        } else {
		            alert("请在移动设备上使用");
		        }
		    }
		}
	
		//确认收货
		function confirmReceived() {
		    var repairOrderId = "${repair.repairOrder.repairOrderId}";
		    var messageToPost = {
		        "repairOrderId": repairOrderId
		    };
	
		    $.ajax({
		        type: "POST",
		        contentType: "application/json",
		        url: "${ctx}/api/v1/public/trade/confirmRepairOrder",
		        data: JSON.stringify(messageToPost),
		        success: function(data) {
		            if (data.returnMsg == "操作成功") {
		                alert(data.returnMsg);
		                $('#confirmTag').text("已收货");
		            } else {
		                alert(data.returnMsg);
		            }
		        }
		    })
		}
	
		function payToProblem() {
		    var money = $("#feiyong").text();
		    if (parseFloat(money) > 0) {
		        alert("请重新支付");
		    }
		    var timestamp = (new Date()).valueOf();
		    var str = "${repair.repairOrder.tradeOrderNo}";
		    str = str + "/t/" + timestamp;
		    window.location.href = "${ctx}/api/v1/public/trade/repairInfo/" + str;
		}
	
		function fapiaosubmit() {
		    $.post('${ctx}/api/v1/public/saveInvoiceInfo', $('form#form1').serialize(),
		    function(data) {
		        window.location.reload();
		    },
		    "json");
		}
	
		function payToFinish() {
		    var timestamp = (new Date()).valueOf();
		    var str = "${repair.repairOrder.tradeOrderNo}";
		    str = str + "/t/" + timestamp;
		    setTimeout(window.location.href = "${ctx}/api/v1/public/trade/repairInfo/" + str, 3000);
		}
	</script>
</body>
</html>