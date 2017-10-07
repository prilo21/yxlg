<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width:device-width, initial-scale=1, user-scalable=no">
<title>Henry's home</title>
	<script src="//code.jquery.com/jquery-2.1.1.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#btnPostMessage").click(function(){
				var msg = $("#inputMessage").val();
				$.get("<%=request.getContextPath()%>/tailor/wechat/postMessage/" + "1" + "/" + msg)
				.done(function(data){
					$("#display").val(data);
				}).always(function(){
					$("#display").val($("#display").val() + "\n123\n");
				});
			});
		});
	</script>
</head>
	<body>
		<input type="text" id="inputMessage" /><button id="btnPostMessage">发条消息</button>
		<div>
			<textarea id="display" rows="25" cols="80"></textarea>
		</div>
	</body>
</html>