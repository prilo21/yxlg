<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String basePath = "//"+request.getServerName()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>量体数据详情</title>
	<link rel="stylesheet" href="https://cdn.bootcss.com/jquery-mobile/1.4.5/jquery.mobile.min.css">
	<script src="https://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
	<script src="https://cdn.bootcss.com/jquery-mobile/1.4.5/jquery.mobile.min.js"></script>
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
	    background:#000;
		color:#fff;
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
	thead th input{
	    background:#000;
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
    #name-div{
        font-family:Microsoft YaHei;padding:10px;
    }
	 #name-div input{
	border:none;border-bottom:1px solid #000
	}
	.mainpage{
	    font-family:Microsoft YaHei
	}	
</style>
</head>

<body>
<div data-role="page" class="mainpage">
	<div data-role="header"><h3>量体数据</h3></div>
	<div  style="background:#fff;">
	    <div align="center" id="name-div">顾客姓名：<input name="measurerName" type="text" data-role="none" /></div>
	    <div align="center" id="name-div">量体师编号：<input name="measureNo" type ="text" data-role="none" /></div>
	    <table id="valueTable" data-role="none">
		    <thead>    
		        <tr>
		            <th><div style="width:40%;">身高</div><div style="width:60%;"><input type="text" name="height" style="color:#fff" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></th>
		            <th><div style="width:40%;">体重</div><div style="width:60%;"><input type="text" name="weight" style="color:#fff" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>kg</div></th>
		        </tr>        
		    </thead>
		    <tbody>
		    	<tr>
		            <td><div style="width:40%;">领围</div><div style="width:60%;"><input id="lw" type="text" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		            <td><div style="width:40%;">肩宽</div><div style="width:60%;"><input id="jk" type="text" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		        </tr>
		        <tr>
		            <td><div style="width:40%;">前肩宽</div><div style="width:60%;"><input id="qjk" type="text" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		            <td><div style="width:40%;">胸围</div><div style="width:60%;"><input id="xw" type="text" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		        </tr>
		        <tr>
		            <td><div style="width:40%;">腹围</div><div style="width:60%;"><input id="fw" type="text" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		            <td><div style="width:40%;">前腰高</div><div style="width:60%;"><input id="qyg" type="text" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		        </tr>
		        <tr>
		            <td><div style="width:40%;">后腰高</div><div style="width:60%;"><input id="hyg" type="text" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		            <td><div style="width:40%;">前腰节长</div><div style="width:60%;"><input id="qyjc" type="text" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		        </tr>
		        
		        <tr>
		           <td><div style="width:40%;">后腰节长</div><div style="width:60%;"><input id="hyjc" type="text" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		            <td><div style="width:40%;">左手腕围</div><div style="width:60%;"><input id="zsww" type="text" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		        </tr>
		        
		        <tr>
		            <td><div style="width:40%;">右手腕围</div><div style="width:60%;"><input id="ysww" type="text" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		            <td><div style="width:40%;">左袖长</div><div style="width:60%;"><input id="zxc" type="text" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		        </tr>
		        
		        <tr>
		            <td><div style="width:40%;">右袖长</div><div style="width:60%;"><input id="yxc" type="text" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		            <td><div style="width:40%;">上臂围</div><div style="width:60%;"><input id="sbw" type="text" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		        </tr>
		        <tr>
		            <td><div style="width:40%;">后衣长</div><div style="width:60%;"><input id="hyc" type="text" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		            <td><div style="width:40%;">裤腰围</div><div style="width:60%;"><input id="kyw" type="text" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		        </tr>
		        <tr>
		            <td><div style="width:40%;">臀围</div><div style="width:60%;"><input id="tw" type="text" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		            <td><div style="width:40%;">通档</div><div style="width:60%;"><input id="td" type="text" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		        </tr>
		         <tr>
		            <td><div style="width:40%;">腿根围</div><div style="width:60%;"><input id="tgw" type="text" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		            <td><div style="width:40%;">左裤长</div><div style="width:60%;"><input id="zkc" type="text" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		        </tr>
		         <tr>
		            <td><div style="width:40%;">右裤长</div><div style="width:60%;"><input id="ykc" type="text" data-role="none" onkeyup="isNumber(this)" onafterpaste="isNumber(this)"/>cm</div></td>
		        </tr>
		    </tbody>
        </table>
	</div>
 <div style="text-align:center;margin:15px;font-weight:bold;font-size:15px">特殊备注</div>
 <div id="specialState">  

    <div style="width:100%;font-size:13px;clear:both">
         <div style="float:left;margin:0px 2%;height:45px;line-height:45px;">左肩型</div>
         <div style="float:right;margin:0px 2%;height:45px;line-height:45px;">
             <select data-role="none" id="zjx" >
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
    <div style="width:100%;font-size:13px;clear:both">
         <div style="float:left;margin:0px 2%;height:45px;line-height:45px;">右肩型</div>
         <div style="float:right;margin:0px 2%;height:45px;line-height:45px;">
             <select  id="yjx" data-role="none">
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
    <div style="width:100%;font-size:13px;clear:both">
            <div style="float:left;margin:0px 2%;height:45px;line-height:45px;">肚型？</div>
            <div style="float:right;margin:0px 2%;height:45px;line-height:45px;">
                <select id="dx" data-role="none">
				     <option value="15" selected="">正常体</option>
				    <option value="16" >凸肚体</option>
					<option value="136" >正常肚</option>
				</select>
			</div>
    </div>
    <div style="width:100%;font-size:13px;clear:both">
            <div style="float:left;margin:0px 2%;height:45px;line-height:45px;">背型？</div>
            <div style="float:right;margin:0px 2%;height:45px;line-height:45px;">
                <select id="bx" data-role="none">
				  <option value="11">正常体</option>
				    <option value="12">轻微驼背</option>
				    <option value="13">驼背</option>
				    <option value="35">中度驼背</option>
				    <option value="36">严重驼背</option>
				</select>
			</div>
    </div>
    <div style="width:100%;font-size:13px;clear:both">
            <div style="float:left;margin:0px 2%;height:45px;line-height:45px;">臂型？</div>
            <div style="float:right;margin:0px 2%;height:45px;line-height:45px;">
                <select id="sx" data-role="none">
				     <option value="17">手臂靠前</option>
				    <option value="18">轻微手臂靠后</option>
				    <option value="19">手臂正常</option>
				    <option value="84">严重手臂靠后</option>
				</select>
			</div>
    </div>
    <div style="width:100%;font-size:13px;clear:both">
            <div style="float:left;margin:0px 2%;height:45px;line-height:45px;">臀型？</div>
            <div style="float:right;margin:0px 2%;height:45px;line-height:45px;">
               <select id="tx" data-role="none">
				    <option value="20">正常臀</option>
				    <option value="21">平臀</option>
				    <option value="23">凸臀</option>
				    <option value="25">坠臀</option>
				</select>
			</div>
    </div>
   	
  </div>
    <button class="ui-btn ui-btn-b" onclick="javascript:history.back()"id="submitMeasure">继续添加</button>
    <div data-role="popup" id="popupWin" data-theme="a" class="ui-corner-all" data-dismissible="false" >
       <div style="padding:0px 30px;">
            <h3></h3>
            <button type="submit" onclick="closePop('popupWin')" class="ui-btn ui-corner-all ui-shadow ui-btn-b ui-btn-icon-left ui-icon-check" data-transition="flow">确定</button>
       </div>
    </div>
</div>

</body>
<script>
$(document).ready(function() {
	$.ajax({
		type:'GET',
		url:window.location.protocol + '<%=basePath%>api/v1/public/members/<%=request.getAttribute("mid")%>/exclusiveInfo/<%=request.getAttribute("id")%>',
		success:function(data){
			$("input[name='measurerName']").val(data.returnData.measurer.measurerName);
			$("input[name='measureNo']").val(data.returnData.measurer.measurePerson.personNo);
			$("input[name='height']").val(data.returnData.measurer.height);
			$("input[name='weight']").val(data.returnData.measurer.weight);
			var measures = data.returnData.measures;
			for(var i = 0;i < measures.length;i++) {
				$("#"+measures[i].propertyCode).val(measures[i].values.value);
			}
		}
	})
})

</script>
</html>
