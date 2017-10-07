<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>客户端下载</title>
	<link rel="stylesheet" href="https://source.magicmanufactory.com/project/c2mwebservice/css/common.css">
	<link rel="stylesheet" href="https://source.magicmanufactory.com/js/jqueryMobile/jquery.mobile-1.4.5.min.css">
	<script src="https://source.magicmanufactory.com/js/jquery/jquery.min_2.1.4.js"></script>
	<script src="https://7xkybm.com2.z0.glb.qiniucdn.com/@/resources/jquery/mobile/jquery.mobile-1.4.5.min.js"></script>
<style>
/*
   对话气泡
   用法：使用.speech-bubble和.speech-bubble-DIRECTION类
   <div class="speech-bubble speech-bubble-top">Hi there</div>
*/

.speech-bubble {
  position: fixed;
  right:4px;top:15px;
  background-color: #292929;
  width: 200px;
  color: white;
  text-align: center;
  border-radius: 10px;
  font-family: sans-serif;
}

.speech-bubble:after {
  content: '';
  position: absolute;

  width: 0;
  height: 0;

  border: 15px solid;
}

/* 箭头的位置 */
.speech-bubble-top:after {
  border-bottom-color: #292929;
  left: 50%;
  bottom: 100%;
  margin-left: -15px;
}
.speech-bubble-right:after {
  border-left-color: #292929;

  left: 100%;
  top: 50%;
  margin-top: -15px;
}

.speech-bubble-bottom:after {
  border-top-color: #292929;
  top: 100%;
  left: 50%;
  margin-left: -15px;
}

.speech-bubble-left:after {
  border-right-color: #292929;
  top: 50%;
  right: 100%;
  margin-top: -15px;
}
</style>
</head>

<body>
<div data-role="page" data-theme="d" id="pageone" class="ui-page">

	<div  >
	      <div style="width:100%"  align="center">
	          <img alt="" src="https://source.magicmanufactory.com/project/c2mwebservice/public/images/magic-factory.png">
	      </div>
	       <div align="center"  class='level'>
             <h1>Magic Manufactory</h1>
          </div>
          <div style="width:100%" class='level abstract-text'>

          </div>
          <div style="margin-top:30px;text-align:center;">
<!--           <a href='http://7xjold.com2.z0.glb.qiniucdn.com/app/official/app-release.apk' class="inputs-code bg-black code-wd button-text" data-role="none" data-inline="true" >Android234下载</a> -->
			<input onclick="locationToDownload(1)" type="button" class="inputs-code bg-black code-wd button-text" value="Android下载" data-role="none" data-inline="true" >
<!-- 			<input onclick="location.href='http://mp.weixin.qq.com/mp/redirect?url=https://itunes.apple.com/cn/app/mo-huan-gong-chang/id1004834346?mt=8'" type="button" class="inputs-code bg-black code-wd button-text" value="ios下载" data-role="none" data-inline="true" > -->
			<input onclick="locationToDownload(2)" type="button" class="inputs-code bg-black code-wd button-text" value="ios下载" data-role="none" data-inline="true" >
		  </div>
	</div><!-- /content -->
</div>

 <div class="speech-bubble speech-bubble-top" id='tips'>
  亲爱的iphone用户,请点击右上角在Safari中打开，然后点击安装
</div>

</body>
<script type="text/javascript">
	function locationToDownload(platform) {
		if (platform == 1) {
			window.open ('http://a.app.qq.com/o/simple.jsp?pkgname=com.yuandian.wanna','客户端下载','');
		} else {
			window.open ('https://itunes.apple.com/cn/app/mo-huan-gong-chang/id1004834346?mt=8','客户端下载','');
		}
	}
function is_weixin(){
    var ua = navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i)=="micromessenger") {
        return true;
    } else {
        return false;
    }
}

var browser={
    versions:function(){
      var u = navigator.userAgent, app = navigator.appVersion;
        return {
          trident: u.indexOf('Trident') > -1,
          presto: u.indexOf('Presto') > -1,
          webKit: u.indexOf('AppleWebKit') > -1,
          gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1,
          mobile: !!u.match(/AppleWebKit.*Mobile.*/),
          ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/),
          android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1,
          iPhone: u.indexOf('iPhone') > -1 ,
          iPad: u.indexOf('iPad') > -1,
          webApp: u.indexOf('Safari') == -1
       }
    }(),
    language:(navigator.browserLanguage || navigator.language).toLowerCase()
};

function init(){
    if(is_weixin()){
        //weixin为提示使用浏览器打开的div
	$("#tips").css("display","block");
    }else{
        //下载页div
	$("#tips").css("display","none");
    }
}
init();

</script>

</html>

