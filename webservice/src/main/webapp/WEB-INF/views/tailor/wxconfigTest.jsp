<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="//res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
</head>
<body>
	
	<%@ include file="wxConfig.jsp"%>
	
	<script>
		wx.ready(function(){
  			alert("ready")
			});
		wx.error(function (res) {
  			alert(res.errMsg);
		});
	$(document).ready(function(){
			$("#scanQRCode1").click(function(){
				$("#output").val('button clicked.');
				wx.scanQRCode({
      				desc: 'scanQRCode desc',
    				needResult: 1, 
    				scanType: ["qrCode","barCode"], 
   					 success: function (res) {
    					var result = res.resultStr; 
      				}
    			});
    		});
	});
	</script>
	
	<button id="scanQRCode1">scan</button>
</body>
</html>