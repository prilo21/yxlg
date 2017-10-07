<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=path%>">
    <meta charset="utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
	<meta name="apple-touch-fullscreen" content="yes">
	<meta name="format-detection" content="telephone=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	<title>量体数据添加</title>
	<link rel="stylesheet" href="https://source.magicmanufactory.com/@/resources/jquery/mobile/jquery.mobile-1.4.5.min.css">
	<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<!-- <script src="https://source.magicmanufactory.com/@/resources/jquery/mobile/jquery.mobile-1.4.5.min.js"></script> -->
	<script src="https://source.magicmanufactory.com/jQuery/jquery.mobile-1.4.5.min.js"></script>
	
	<script src="//res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
<style type="text/css">
table,  td, tr, th {
	margin:0;
	padding:0;
	font-weight:normal;
	}

table {
	border-collapse:collapse;
	margin-bottom:15px;
	width:100%;
	}
	
	table td,
	table th {
		padding:10px 2%;
		border:1px solid #eee;
		border-width:0 1px 1px 0;
		text-align:left
		}
		
	thead th,tbody tr {
		font-family:Microsoft YaHei;
		height:30px;
		line-height:30px;
		width:50%
		}
	thead th{
	   
		font-family: Georgia;
		
	}
	table input{
	    width:70%;
	    border:0
	}
	@media screen and ( min-width: 319px){/*320px显示屏样式 苹果4/4S/5C/5S黑莓Z30 */
	    table input{
	    width:60%;
	    border:0
	}
	}
		
	tbody th,
	tbody td,
	tbody tr.odd td { 
		background:#fff;
		font-size:13px;
		}
	thead th div,
	tbody td div{
	    float:left
	}
	.co-style{
	    font-family:Microsoft YaHei;
        background:#f3f3f3;
        height:30px;
        line-height:30px;
        text-align:left;
        font-size:16px;
        padding:10px;
	}

	 .co-style input{
	 border:none;
	    border-bottom:1px solid #000;
	    height:25px;
	    background:#f3f3f3;
	}

	 #gender-div input{
	    border:none;
	    background:#f3f3f3;
	}
	.mainpage{
	    font-family:Microsoft YaHei
	}	
.tlt_special select{ border:none; color:#949494;appearance:none;-moz-appearance:none;-webkit-appearance:none;background:url(resources/public/images/xia.jpg) no-repeat scroll right center transparent; background-size:12px;padding-right: 14px;}
.tlt_special option{ background-color:#fff;}
.tlt_special option:hover{ background-color:#eee;}
.c_boder{
    border:1px 0 0 0;
    border:solid;
    border:#000;
}
.ewm {
background:url(<%=path%>/resources/public/images/garment/erwm.png) center no-repeat;
background-size:35px;
}
</style>
</head>

<body>
<%@ include file="../tailor/wxConfig.jsp"%>
<script>
	var standardData = {"lw":[30,62],"xw":[72,175],"fw":[55,185],"tw":[70,200],"sbw":[22,68],"jk":[31,72],"qjk":[29,68],"zxc":[30,85],"yxc":[30,85],"tgw":[40,113],"td":[50,120],"hyg":[0,21],"qyg":[0,25],"zkc":[60,135],"ykc":[60,135],"qyjc":[33,60],"hyjc":[31,60],"hyc":[47,105],"zsww":[13,33],"ysww":[13,33],"kyw":[60,190],"kk":[28,80],"xiw":[30,103],"xtw":[25,70]};
	
		wx.ready(function(){
			});
		wx.error(function (res) {
			if(res.errMsg=="config:invalid signature"){
				
			}
  			alert(res.errMsg);
		});
function fillData(keyArray,result) {
	for (var i = 0; i < keyArray.length; i++) {
		$("input[name=" +keyArray[i] + "]").val(result[i]);
		if (Number(result[i])<(standardData[keyArray[i]])[0] || Number(result[i])>(standardData[keyArray[i]])[1]) {
			$("input[name=" +keyArray[i] + "]").css({'color':'#ff0000'});
		}
	}
	$("#memberPhone").val(result[result.length - 1]);
}
	$(document).ready(function(){
			$("#scanQRCode4MeasureInfo").click(function(){
				wx.scanQRCode({
      				desc: 'scanQRCode desc',
    				needResult: 1, 
    				scanType: ["qrCode","barCode"], 
   					 success: function (res) {
    					var result = res.resultStr.split(","); 
    					var tempKeyArray = ['lw','xw','kyw','fw','tw','tgw','sbw','zsww','ysww','zxc','yxc','jk','qjk','hyjc','td','zkc','ykc','hyc','hyg','qyg','qyjc','kk','xiw','xtw'];
    					fillData(tempKeyArray,result);
    					
      				},
					
    			});
    		});
	});
	</script>
<div data-role="page" class="mainpage">
	<div  style="background:#fff;">
		
		<div class="co-style" style=" background:#f3f3f3;">
		    <span id="name-div" style="float:left;">顾客姓名：</span>
		    <span style="width:50%;float:left" class="m-name"><input name="measurerName" type="text" data-role="none" /></span>
		   	<span id="scanQRCode4MeasureInfo" style="width:40px;height:40px;float:right" class="ewm"></span>
	   	</div>
	   <c:choose>
		<c:when test="${flag == '0' }">
			<div align="center" class="co-style" style="display:flex"><span>顾客手机号：</span><input id="memberPhone" type="tel" data-role="none" /></div>
		</c:when>
		</c:choose>
	    <div align="center" id = "gender-div" class="co-style">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：
	    	<select name="gender" data-role=none>
			    <option value="男">男</option>
			    <option value="女">女</option>
			</select>
		</div>
	    <table id="valueTable" data-role="none">
		    <thead>    
		        <tr>
		            <th><div style="width:40%;font-size:14px;font-family:Microsoft YaHei;">身高</div><div style="width:60%;"><input type="number" name="height"  data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></th>
		            <th><div style="width:40%;font-size:14px;font-family:Microsoft YaHei;">体重</div><div style="width:60%;"><input type="number" name="weight"  data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>kg</div></th>
		        </tr>        
		    </thead>
		    <tbody>
		    	<tr>
		            <td><div style="width:40%;">领围</div><div style="width:60%;"><input name="lw" type="number" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		            <td><div style="width:40%;">胸围</div><div style="width:60%;"><input name="xw" type="number" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		        </tr>
		        <tr>
		        	<td><div style="width:40%;">腹围</div><div style="width:60%;"><input name="fw" type="number" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		            <td><div style="width:40%;">裤腰围</div><div style="width:60%;"><input name="kyw" type="number" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		        </tr>
		        <tr>
		         	<td><div style="width:40%;">臀围</div><div style="width:60%;"><input name="tw" type="number" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		           	<td><div style="width:40%;">腿根围</div><div style="width:60%;"><input name="tgw" type="number" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		        </tr>
		        <tr>
		            <td><div style="width:40%;">通档</div><div style="width:60%;"><input name="td" type="number" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		        	<td><div style="width:40%;">上臂围</div><div style="width:60%;"><input name="sbw" type="number" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		        </tr>
		        <tr>
		           	<td><div style="width:40%;">左手腕围</div><div style="width:60%;"><input name="zsww" type="number" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		        	<td><div style="width:40%;">右手腕围</div><div style="width:60%;"><input name="ysww" type="number" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		        </tr>
		        <tr>    
		            <td><div style="width:40%;">肩宽</div><div style="width:60%;"><input name="jk" type="number" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		        	<td><div style="width:40%;">左袖长</div><div style="width:60%;"><input name="zxc" type="number" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		        </tr>
		       		<td><div style="width:40%;">右袖长</div><div style="width:60%;"><input name="yxc" type="number" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		            <td><div style="width:40%;">前肩宽</div><div style="width:60%;"><input name="qjk" type="number" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		        </tr>
		        <tr>
		        	<td><div style="width:40%;">后腰节长</div><div style="width:60%;"><input name="hyjc" type="number" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		           	<td><div style="width:40%;">后衣长</div><div style="width:60%;"><input name="hyc" type="number" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		        </tr>    
		        <tr>
		            <td><div style="width:40%;">后腰高</div><div style="width:60%;"><input name="hyg" type="number" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		            <td><div style="width:40%;">前腰节长</div><div style="width:60%;"><input name="qyjc" type="number" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		        </tr>
		        <tr>
		         	<td><div style="width:40%;">前腰高</div><div style="width:60%;"><input name="qyg" type="number" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		            <td><div style="width:40%;">左裤长</div><div style="width:60%;"><input name="zkc" type="number" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		        </tr>
		         <tr>
		            <td><div style="width:40%;">右裤长</div><div style="width:60%;"><input name="ykc" type="number" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		            <td><div style="width:40%;">脚腕围</div><div style="width:60%;"><input name="kk" type="number" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		        </tr>
		        <tr>
		            <td><div style="width:40%;">膝围</div><div style="width:60%;"><input name="xiw" type="number" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		            <td><div style="width:40%;">小腿围</div><div style="width:60%;"><input name="xtw" type="number" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		        </tr>
		    </tbody>
        </table>
	</div>
	 <div style="text-align:center;font-weight:bold;margin-top:-5px;height:40px;line-height:40px;font-size:15px;">特殊备注</div>
 
 <div id="specialState" class="tlt_special">  

    <div style="width:100%;font-size:13px;clear:both;height:45px;line-height:45px;">
         <div style="float:left;margin:0px 2%;height:45px;line-height:45px;">左肩型</div>
         <div style="float:right;margin:0px 2%;height:45px;line-height:45px;">
             <select  name="zjx" data-role=none > 
				    <option value="1">正常肩</option>
				    <option value="51">轻微溜肩</option>
				    <option value="2">中度溜肩</option>
				    <option value="31">严重溜肩</option>
				    <option value="45">溜肩1.5</option>
				    <option value="46">溜肩2.0</option>
				    <option value="54">轻微耸肩</option>
				    <option value="4">中度耸肩</option>
				    <option value="34">严重耸肩</option>
			 </select>
		</div>
    </div>
    <div class="c_boder" style="width:100%;font-size:13px;clear:both">
         <div style="float:left;margin:0px 2%;height:45px;line-height:45px;">右肩型</div>
         <div class="" style="float:right;margin:0px 2%;height:45px;line-height:45px;">
             <select  name="yjx" data-role=none>
				    <option value="6">正常肩</option>
				    <option value="52">轻微溜肩</option>
				    <option value="7">中度溜肩</option>
				    <option value="32">严重溜肩</option>
				    <option value="47">溜肩1.5</option>
				    <option value="48">溜肩2.0</option>
				    <option value="53">轻微耸肩</option>
				    <option value="9">中度耸肩</option>
				    <option value="33">严重耸肩</option>
			    </select>
		</div>
    </div>
    <div class="c_boder" style="width:100%;font-size:13px;clear:both">
            <div style="float:left;margin:0px 2%;height:45px;line-height:45px;">肚型？</div>
            <div style="float:right;margin:0px 2%;height:45px;line-height:45px;">
                <select name="dx" data-role=none>
				    <option value="15" selected="">正常体</option>
				    <option value="16" >凸肚体</option>
				</select>
			</div>
    </div>
    <!-- 
    <div class="c_boder" style="width:100%;font-size:13px;clear:both">
            <div style="float:left;margin:0px 2%;height:45px;line-height:45px;">背型？</div>
            <div style="float:right;margin:0px 2%;height:45px;line-height:45px;">
                <select  name="bx" data-role=none>
				    <option value="11">正常体</option>
				    <option value="12">轻微驼背</option>
				    <option value="13">驼背</option>
				    <option value="35">中度驼背</option>
				    <option value="36">严重驼背</option>
			    </select>
			</div>
    </div>
     -->
    <div class="c_boder" style="width:100%;font-size:13px;clear:both">
            <div style="float:left;margin:0px 2%;height:45px;line-height:45px;">臂型？</div>
            <div style="float:right;margin:0px 2%;height:45px;line-height:45px;">
                <select  name="sx" data-role=none>
				    <option value="17">手臂靠前</option>
				    <option value="18">轻微手臂靠后</option>
				    <option value="19">手臂正常</option>
				    <option value="84">严重手臂靠后</option>
			    </select>
			</div>
    </div>
    <div class="c_boder" style="width:100%;font-size:13px;clear:both">
            <div style="float:left;margin:0px 2%;height:45px;line-height:45px;">臀型？</div>
            <div style="float:right;margin:0px 2%;height:45px;line-height:45px;">
               <select name="tx" data-role=none>
				    <option value="20">正常臀</option>
				    <option value="21">平臀</option>
				    <option value="23">凸臀</option>
				    <option value="25">坠臀</option>
				</select>
			</div>
    </div>
   	
  </div>
    <button class="ui-btn ui-btn-b" onclick="submitMeasure()" id="submitMeasure">提交</button>
    
    <div data-role="popup" id="msgWin" data-theme="a" class="ui-corner-all" data-dismissible="false" >
       <div style="padding:0px 30px;">
            <h3></h3>
            <button type="submit" onclick="closePop('msgWin')" class="ui-btn ui-corner-all ui-shadow ui-btn-b ui-btn-icon-left ui-icon-check" data-transition="flow">确定</button>
       </div>
    </div>
</div>

</body>
<script>
var url = '';
$(document).ready(function() { 
	closePop('msgWin')
})
function openMeasureMsg(msg){
	$("#msgWin div h3").empty();
	$("#msgWin div h3").append(msg);
	$("#msgWin").popup('open');
}
function submitMeasure(){
	$("#submitMeasure").attr('onclick','');
	var flag = ${flag};
	if(flag == "1"){
		url = '<%=path%>/api/v1/public/measures/<%=request.getAttribute("mid")%>/addition/<%=request.getAttribute("measureId")%>/<%=request.getAttribute("orderId")%>';
	}
	if(flag == "0"){
		var phone = $("#memberPhone").val();
		if(phone == ""){
			openMeasureMsg('手机号不能为空');
		} else {
			url = '<%=path%>/api/v1/public/measures/'+phone+'/addition/<%=request.getAttribute("measureId")%>';		
		}

	}	
	var measureName = $("input[name='measurerName']").val();
	var jsonObject = {};
	jsonObject.measurerName = measureName;

	$("#valueTable input[type='number']").each(function(){
		var attrName= $(this).attr('name');
		var value = $(this).val();
		jsonObject[attrName] = value;
	});
	$("#specialState select").each(function(){
		var attrName= $(this).attr('name');
		var value = $(this).val();
		jsonObject[attrName] = value;
	});
	$("#gender-div select").each(function(){
		var attrName= $(this).attr('name');
		var value = $(this).val();
		jsonObject[attrName] = value;
	});
	addMeasure(jsonObject);
}
function addMeasure(stringJSON){

	$.ajax({
		type:'POST',
		url:url,
		contentType: 'application/json',
		dataType: 'json', 
		data:JSON.stringify(stringJSON),
		success:function(data){
			if(data.returnCode == 200){
				window.location.href = '<%=path%>/api/v1/public/measure/measureInfo/'+data.returnData.measureId;
				
			}else{
				openMeasureMsg(data.returnMsg);
				$("#submitMeasure").attr('onclick','submitMeasure()');
			}
		},
		error:function(data){
			openMeasureMsg('网络错误');
		}
	})
}
function reLocation(measureId){
	window.location.href = '<%=path%>/api/v1/public/measure/measureInfo/'+measureId;
}
function isNumber(o){
	if(isNaN(o.value))document.execCommand('undo');
}
function closePop(win){
	$("#"+win).popup('close');
}
</script>
</html>
