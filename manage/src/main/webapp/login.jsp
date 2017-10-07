<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>红领C2M定制管理平台</title>
  	<link rel="shortcut icon" href="favicon.ico" />
	<script type="text/javascript" src="<%=basePath%>public/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>public/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>public/easyui/datagrid-detailview.js"></script>
	<script type="text/javascript" src="<%=basePath%>public/easyui/jquery.easyui.plus.js"></script>
	<script type="text/javascript" src="<%=basePath%>public/easyui/locale/easyui-lang-zh_CN.js"></script>
	
  	<link href="public/login/css/zice.style.css" rel="stylesheet" type="text/css" />
  	<link href="public/login/css/buttons.css" rel="stylesheet" type="text/css" />
  	<link href="public/login/css/icon.css" rel="stylesheet" type="text/css" />
  	<link rel="stylesheet" type="text/css" href="public/login/css/tipsy.css" media="all" />
  	<style type="text/css">
		html {
			background-image: none;
		}

		label.iPhoneCheckLabelOn span {
			padding-left: 0px
		}

		#versionBar {
			background-color: #212121;
			position: fixed;
			width: 100%;
			height: 35px;
			bottom: 0;
			left: 0;
			text-align: center;
			line-height: 35px;
			z-index: 11;
			-webk	it-box-shadow: black 0px 10px 10px -10px inset;
			-moz-box-shadow: black 0px 10px 10px -10px inset;
			box-shadow: black 0px 10px 10px -10px inset;
		}
		
		.copyright {
			text-align: center;
			font-size: 10px;
			color: #CCC;
		}
		
		.copyright a {
			color: #A31F1A;
			text-decoration: none
		}
		
		#login .logo {
			width: 500px;
			height: 51px;
		}
		</style>
  </head>
  
<body>
	<div id="alertMessage"></div>
	  <div id="successLogin"></div>
	  <div class="text_success">
	   <img src="public/login/images/loader_green.gif" alt="Please wait" />
	   <span>登陆成功!请稍后....</span>
	  </div>
	  <div id="login">
	   <div class="ribbon" style="background-image:url(public/login/images/typelogin.png);"></div>
	   <div class="inner">
	    <div class="logo">
	     <img src="public/login/images/toplogo-jeecg.png"/>
	    </div>
	    <div class="formLogin">
	     <form name="formLogin" id="formLogin" action="security/login" method="post">
	      <div class="tip">
	       <input class="userName" autofocus name='userName' type="text" id="account" title="用户名" iscookie="true" nullmsg="请输入账号!"/>
	      </div>
	      <div class="tip">
	       <input class="password" type="password" name='password' id="password" title="密码" nullmsg="请输入密码!"/>
	      </div>
	      <div class="loginButton">
	       <div style="float: right; padding: 3px 0; margin-right: -12px;">
	        <div>
	         <ul class="uibutton-group">
	          <li>
	          	<a class="uibutton normal" id="btn_login" name="submit" type="submit">登录</a>
	           <!-- <a class="uibutton normal" href="#" id="but_login">登陆</a> -->
	          </li>
	          <!-- <li>
	           <a class="uibutton normal" href="#" id="forgetpass">重置</a>
	          </li> -->
	         </ul>
	        </div>
	       </div>
	      <div class="errdiv" align="center" ><div id="promptMsgDiv" class="prompt" style="display:none;">用户名或密码错误</div></div>
	      </div>
	     </form>
	    </div>
	   </div>
	   <div class="shadow"></div>
	  </div>
	  <!--Login div-->
	  <div id="versionBar">
	   <div class="copyright">
	    &copy; 版权所有
	    <span class="tip"><a href="#" title="青岛源点信息科技有限公司">源点</a> (推荐使用IE8+,谷歌浏览器可以获得更快,更安全的页面响应速度)技术支持:<a href="#" title="青岛源点信息科技有限公司">源点</a></span>
	   </div>
	  </div>
	    <!-- Link JScript-->
	  <script type="text/javascript" src="public/cookie/jquery.cookie.js"></script>
	  <script type="text/javascript" src="public/login/js/jquery-jrumble.js"></script>
	  <script type="text/javascript" src="public/login/js/jquery.tipsy.js"></script>
	  <script type="text/javascript" src="public/login/js/iphone.check.js"></script>
	  <script type="text/javascript" src="public/login/js/login.js"></script>
</body>
</html>

<script type="text/javascript">
		<%
		String er = request.getParameter("error");
		if(!org.apache.commons.lang.StringUtils.isNotEmpty(er)){
			er = "999";
			}
		%>
		
	if (window != top)
		top.location.href = location.href;
	
		$(function(){
			var error = parseInt("<%=er%>", 10);
			if (error==1){
				$("#promptMsgDiv").html('用户名或密码不能为空!');
				$("#promptMsgDiv").show();
			} else if (error==0){
				$("#promptMsgDiv").html('');
				$("#promptMsgDiv").show();
			}
			else if (error==2){
				$("#promptMsgDiv").html('网页已过期,请重登陆');
				$("#promptMsgDiv").show();
			}
			else if (error==3){
				$("#promptMsgDiv").html('用户名不存在');
				$("#promptMsgDiv").show();
			}
				else if (error==4){
					$("#promptMsgDiv").html('用户名或密码不正确!!');
					$("#promptMsgDiv").show();
			} else {
				$("#promptMsgDiv").hide();
			}

			$("#account").focus();
		});

		
		</script>