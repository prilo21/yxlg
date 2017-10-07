<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <meta http-equiv="refresh" content="0; url=https://dn-c2m-resources.qbox.me/html/redcollar/aboutus/magic_intro/magicmanufactory.html"> -->
<title>酷特介绍</title>
</script>
</head>
<body>
<%
   // 重定向到新地址
   String site = new String("https://dn-c2m-resources.qbox.me/html/redcollar/aboutus/magic_intro/magicmanufactory.html");
   response.setStatus(response.SC_MOVED_TEMPORARILY);
   response.setHeader("Location", site); 
%>
</body>
</html>