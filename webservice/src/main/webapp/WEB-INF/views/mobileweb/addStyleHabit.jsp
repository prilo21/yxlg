<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>
<html>
<head>
<base href="<%=path%>">
<meta charset="utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
<meta http-equiv="expires" content="0">
<meta name="apple-touch-fullscreen" content="yes">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
<title>量体数据添加</title>
<link href="<%=path%>/resources/public/css/style-sh.css" rel="stylesheet">
<script src="https://source.magicmanufactory.com/js/jquery/jquery.min_2.1.4.js"></script>
<style>

.tlts_design h1{ width:94%; margin-left:3%;text-align:center; padding:20px 0px;}
.tlts_design table{ width:94%; margin-left:3%; border-top:1px solid #e4e4e4;}
.tlts_design table td{ border-bottom:1px solid #e8e8e8; line-height:50px; padding-left:7px;padding-right:7px;}

</style>
</head>

<body>
	<div class="top">
		<span class="back"><img src="<%=path%>/resources/public/images/back.jpg"
			width="100%"></span>

		<div class="select_box">
			<span class="select_txt">西服</span>
			<div class="option">
				<a check-id="s_xf">西服</a> 
				<a check-id="s_dy">大衣</a> 
				<a check-id="s_cy">衬衣</a> 
				<a check-id="s_mj">马甲</a> 
				<a check-id="s_kz">西裤</a>
			</div>
		</div>
	</div>

	<div id="s_xf" class="tlts_container">
		<div class="tlts_select tlts_select_view" id="s_xf_tlts_xx">
			<div class="tlts_style">
				<h1>
					SARTORIAL STYLE<br>穿衣风格
				</h1>
				<table ce llpadding="0" cellspacing="0" id="sartorial_style" class="s_xf_sartorial_style">
					<tr>
						<td width="90%">A正常版</td>
						<td width="10%" class="td_tright"><div class="tlts_unactive"
								value="2"></div></td>
					</tr>
					<tr>
						<td>B正常偏瘦</td>
						<td class="td_tright"><div class="tlts_unactive" value="12"></div></td>
					</tr>
					<tr>
						<td>C修身</td>
						<td class="td_tright"><div class="tlts_unactive" value="1"></div></td>
					</tr>
					<tr>
						<td>D很修身</td>
						<td class="td_tright"><div class="tlts_unactive" value="11"></div></td>
					</tr>
					<tr>
						<td>E非常修身</td>
						<td class="td_tright"><div class="tlts_unactive" value="10"></div></td>
					</tr>
					<tr>
						<td>F正常偏肥</td>
						<td class="td_tright"><div class="tlts_unactive" value="13"></div></td>
					</tr>
					<tr>
						<td>G宽松</td>
						<td class="td_tright"><div class="tlts_unactive" value="3"></div></td>
					</tr>
					<tr>
						<td>H很宽松</td>
						<td class="td_tright"><div class="tlts_unactive" value="14"></div></td>
					</tr>
					<tr>
						<td>I非常宽松</td>
						<td class="td_tright"><div class="tlts_unactive" value="15"></div></td>
					</tr>
				</table>
			</div>
			<div class="tlts_design">
				<h1>DRESSING HABITS<br>款式风格</h1>
				<table cellpadding="0" cellspacing="0" id="s_xf_design_style">
	       		  	<tr><td  width="90%">正常</td><td class="td_tright"><div class="tlts_unactive"  value="-2"></div></td></tr>
	       		  	<tr><td  width="90%">长款</td><td class="td_tright"><div class="tlts_unactive"  value="0"></div></td></tr>
	       		 	<tr><td  width="90%">短款</td><td class="td_tright"><div class="tlts_unactive"  value="-4"></div></td></tr>
	       		</table>
			</div>
			<div  class="tlts_habits">
	            
	        </div>
			<div class="tlts_btn" parent-id="s_xf_tlts_xx"
				onclick="submitSH(this)">保存</div>
		</div>


		
		</div>
	<div class="tlts_tck" id="submitPop" style="display: none;">
			<div class="tlts_tcmid">
				<h1></h1>
				<div class="tlts_tcbtn">
					<span class="tlts_btnactive" style="margin-left: 65px"
						onclick="closePop('submitPop')">确定</span>
				</div>
			</div>
		</div>
		<div class="tlts_tck" id="msgPop"
			style="display: none; text-align: center">
			<div class="tlts_tcmid">
				<h1></h1>
				<div class="tlts_tcbtn">
					<span class="tlts_btnactive" style="margin-left: 65px"
						onclick="closePop('msgPop')">确定</span>
				</div>
			</div>
		<script>
	
$(function(){
	
         $(".select_box").click(function(event){   
              event.stopPropagation();
              $(this).find(".option").toggle();
         });
         $(document).click(function(event){
              var eo=$(event.target);
              if($(".select_box").is(":visible") && eo.attr("class")!="option" && !eo.parent(".option").length)
              $('.option').hide();           
         });
         //选择品类更改dom和点击事件
         $(".option a").click(function(){
           
      	   $("#sartorial_style").attr("class",$(this).attr('check-id')+"_sartorial_style");
      	   var sytleHeaderHtml = "<h1>DRESSING HABITS<br>款式风格</h1>";
      	   	//修改dom	
      	   if($(this).attr('check-id') == "s_dy"){
      		   var styleHtml = "<table cellpadding=\"0\" cellspacing=\"0\" id=\"s_dy_design_style\">"+
      		  	"<tr><td  width=\"90%\">正常</td><td class=\"td_tright\"><div class=\"tlts_unactive\"  value=\"17\"></div></td></tr>"+
      		  	"<tr><td  width=\"90%\">长款</td><td class=\"td_tright\"><div class=\"tlts_unactive\"  value=\"37\"></div></td></tr>"+
      		  	"</table>";
      		   $(".tlts_design").html(sytleHeaderHtml+styleHtml);
      		   $(".tlts_habits").html("<h1>DRESSING HABITS<br>穿衣习惯</h1><table cellpadding=\"0\" cellspacing=\"0\" id=\"s_dy_dressing_habits\"><tr><td  width=\"90%\">穿大衣套西服</td><td class=\"td_tright\"><div class=\"tlts_unactive\"  value=\"011\"></div></td></tr></table>");
      	   }
      	   if($(this).attr('check-id') == "s_mj"){
      		   $(".tlts_design").html("");
      		   $(".tlts_habits").html("");
      	   }       	
      	   if($(this).attr('check-id') == "s_xf"){
      		   var styleHtml = "<table cellpadding=\"0\" cellspacing=\"0\" id=\"s_xf_design_style\">"+
      		  	"<tr><td  width=\"90%\">正常</td><td class=\"td_tright\"><div class=\"tlts_unactive\"  value=\"-2\"></div></td></tr>"+
      		  	"<tr><td  width=\"90%\">长款</td><td class=\"td_tright\"><div class=\"tlts_unactive\"  value=\"0\"></div></td></tr>"+
      		 	"<tr><td  width=\"90%\">短款</td><td class=\"td_tright\"><div class=\"tlts_unactive\"  value=\"-4\"></div></td></tr>"+
      		  	"</table>";
      		   $(".tlts_design").html(sytleHeaderHtml+styleHtml);
      		   $(".tlts_habits").html("");
      	   }
      	   if($(this).attr('check-id') == "s_cy"){
      		   var styleHtml = "<table cellpadding=\"0\" cellspacing=\"0\" id=\"s_cy_design_style\">"+
      		  	"<tr><td  width=\"90%\">正常</td><td class=\"td_tright\"><div class=\"tlts_unactive\"  value=\"0\"></div></td></tr>"+
      		  	"<tr><td  width=\"90%\">长款</td><td class=\"td_tright\"><div class=\"tlts_unactive\"  value=\"2\"></div></td></tr>"+
      		 	"<tr><td  width=\"90%\">短款</td><td class=\"td_tright\"><div class=\"tlts_unactive\"  value=\"-2\"></div></td></tr>"+
      		  	"</table>";
      		   $(".tlts_design").html(sytleHeaderHtml+styleHtml);
      		   $(".tlts_habits").html("");
      	   }
      	   if($(this).attr('check-id') == "s_kz"){
      		   $(".tlts_design").html("");
      		   $(".tlts_habits").html("<h1>DRESSING HABITS<br>穿衣习惯</h1><table cellpadding=\"0\" cellspacing=\"0\" id=\"s_kz_dressing_habits\"><tr><td  width=\"90%\">小脚裤</td><td class=\"td_tright\"><div class=\"tlts_unactive\"  value=\"3004\"></div></td></tr></table>");
      	   }
      	   //每个品类的下的点击事件
      	   var id = $(this).attr('check-id');
      		$('.'+id+'sartorial_style div').bind('click', function() {
      			if ($(this).attr('class') == 'tlts_unactive') {
      				$('.'+id+'sartorial_style div').each(function() {
      					$(this).attr('class', 'tlts_unactive');
      				});
      				$(this).removeClass('tlts_unactive');
      				$(this).addClass('tlts_active');
      				} else {
      					$(this).removeClass('tlts_active');
      					$(this).addClass('tlts_unactive');
      				}
      		});
      		$('#'+id+'_design_style div').bind('click', function() {
      			if ($(this).attr('class') == 'tlts_unactive') {
      				$('#'+id+'_design_style div').each(function() {
      					$(this).attr('class', 'tlts_unactive');
      				});
      				$(this).removeClass('tlts_unactive');
      				$(this).addClass('tlts_active');
      				} else {
      					$(this).removeClass('tlts_active');
      					$(this).addClass('tlts_unactive');
      				}
      		});
      		$('#'+id+'_dressing_habits div').bind('click', function() {
      			if ($(this).attr('class') == 'tlts_unactive') {
      				$('#'+id+'_dressing_habits div').each(function() {
      					$(this).attr('class', 'tlts_unactive');
      				});
      				$(this).removeClass('tlts_unactive');
      				$(this).addClass('tlts_active');
      				} else {
      					$(this).removeClass('tlts_active');
      					$(this).addClass('tlts_unactive');
      				}
      		});
      	    var value=$(this).text();
   		    var currentOptionName = $(this).parent().siblings(".select_txt").text();
   			if($(this).text() != currentOptionName){
			$(this).parent().siblings(".select_txt").text(value);
        			
			//$("#sartorial_style div").each(function(){
        				//$(this).attr('class', 'tlts_unactive');
        			//});
			
    			}
        	closeUnselectCategoryDiv($(this).attr('check-id'));
	});
			

	//复制不同品类的输入框
	var start = 1;
	$(".option a").each(function() {
			if (start++ == 1) {
				return true;
			}
			var id = $(this).attr('check-id');
			$(".tlts_container").eq(start - 3).after($(".tlts_container").eq(start - 3).clone(true));
			$(".tlts_container").eq(start - 2).attr('id', id);
			//var test = "tlts_container:eq("+(start-2)+")"+" div";
			//alert(test);
			var styleId = $(".tlts_container:eq(" + (start - 2) + ")" + " div").attr('id');
			$(".tlts_container:eq(" + (start - 2) + ")"+ " div:eq(0)").attr('id',id + styleId.substring(4, styleId.length));
			$(".tlts_container:eq(" + (start - 2) + ")"+ " .tlts_btn").attr('parent-id',id + styleId.substring(4, styleId.length));
			$(".tlts_container:eq(" + (start - 2) + ")"+ " table").attr('id',$(this).attr('check-id')+ '_sartorial_style');
	});
	//初始展示（西服）设置单选
	$('.option a').each(function() {
		$('.s_xf_sartorial_style div').bind('click', function() {
	     			if ($(this).attr('class') == 'tlts_unactive') {
	     				$('.s_xf_sartorial_style div').each(function() {
	     					$(this).attr('class', 'tlts_unactive');
	     				});
	     				$(this).removeClass('tlts_unactive');
	     				$(this).addClass('tlts_active');
	     				} else {
	     					$(this).removeClass('tlts_active');
	     					$(this).addClass('tlts_unactive');
	     				}
	     		});
		$('#s_xf_design_style div').bind('click', function() {
			if ($(this).attr('class') == 'tlts_unactive') {
				$('#s_xf_design_style div').each(function() {
					$(this).attr('class', 'tlts_unactive');
				});
				$(this).removeClass('tlts_unactive');
				$(this).addClass('tlts_active');
				} else {
					$(this).removeClass('tlts_active');
					$(this).addClass('tlts_unactive');
				}
		});
	});
	closeUnselectCategoryDiv('s_xf');
})

function closeUnselectCategoryDiv(category) {
	$(".option a").each(function() {
		$("#" + $(this).attr('check-id')).css('display', 'none');
	});
	$("#" + category).css('display', 'block');
}
//提交数据
function submitSH(obj) {
	var measureId = '<%=request.getAttribute("measureId")%>';
	var category = "",//品类
		dressStyleCode = "",//着装风格
		lengthStyleName = "",//款式风格名称
		lengthStyleCode = "",//款式风格值
		wearSuit = "";//着装习惯
	var parentId = obj.getAttribute("parent-id");
	
	//获得选中的风格的值
	$(".tlts_style .tlts_active").each(function(index, element) {
		dressStyleCode = $(element).attr("value");
	});
	//获得选中的款式的值
	$(".tlts_design .tlts_active").each(function(index, element) {
		lengthStyleCode = $(element).attr("value");
		lengthStyleName = $(element).parent().prev().text();
	});
	//获得选中的习惯的值
	$(".tlts_habits .tlts_active").each(function(index, element) {
		wearSuit = $(element).attr("value");
	});
	
	//获得选中的品类
	$(".option a").each(
			function() {
				if ($(this).text() == $(".select_txt").text()) {
					category = convertCategoryId($(this).attr('check-id'));
				}
	});	

	obj.style.background = "#eee";
	obj.style.color = "#000";
	obj.setAttribute("onclick", "");
	$.ajax({
		type : 'POST',
		url : '<%=path%>/api/v2/public/measures/additionSH/'+measureId,
		contentType : "application/json",
		dataType: 'json', 
		data : JSON.stringify({'category':category,'dressStyleCode':dressStyleCode,'lengthStyleName':lengthStyleName,'lengthStyleCode':lengthStyleCode,'wearSuit':wearSuit}),
		success : function(data) {
			var message = data.returnMsg;
			$("#submitPop").css('display', 'block');
			$("#submitPop").height(document.body.scrollHeight);
			$("#submitPop h1").html(message);
			$("html,body").animate({
				scrollTop : $("#submitPop").offset().top
			}, 100);
		},
		error:function(data){
			var msg = data.returnMsg;
			obj.style.background = "#000";
			obj.style.color = "#fff";
			obj.setAttribute("onclick", "submitSH(this)");
			$("#submitPop").height(document.body.scrollHeight);
			$("#submitPop h1").html("添加失败，请重新选择");
			$("#submitPop").css('display', 'block');
		}
	
	});
}
//获取选中的风格值
function getData(measureId){
	var list = new Array(5);
	var index = 0;
	//构建五个品类穿衣风格
	$('.option a').each(function(){
		var category = $(this).attr('check-id');
		list[index++] = eval({
			"lengthStyleCode": "",
	        "dressStyleCode": getDressStyleCode(category),
	        "categoryId": convertCategoryId(category),
	        "dressStyleName": getDressStyleName(category),
	        "lengthStyleName": ""
		});
	})
	index = 0;
	var data = JSON.stringify({
		'xf':list[index++],
		'dy':list[index++],
		'cy':list[index++],
		'mj':list[index++],
		'kz':list[index],
		'measureId':measureId,
		'wearSuit':''
	});
	return data;
}
//获取选中的穿衣风格
function getDressStyleCode(category){
	var code = '';
	$('#'+category+' table div').each(function(){
		if($(this).attr('class') == 'tlts_active'){
			code = $(this).attr('value');
			return true;
		}
	});
	return code;
}
//获取选中的风格名称
function getDressStyleName(category){
	var name = '';
	$('#'+category+' table div').each(function(){
		if($(this).attr('class') == 'tlts_active'){
			name = $(this).parent().prev().text();
			return true;
		}
	});
	return name;
}
//转换品类Id
function convertCategoryId(category){
	if(category == 's_xf') return 'MXF';
	if(category == 's_dy') return 'MDY';
	if(category == 's_cy') return 'MCY';
	if(category == 's_kz') return 'MXK';
	if(category == 's_mj') return 'MMJ';
};
//关闭弹出层
function closePop(pop) {
	$("#" + pop).css("display", "none");
	if (pop == 'submitPop') {
		history.go(-2);
	}
}
</script>
</body>
</html>
