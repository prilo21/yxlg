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
<meta name="keywords" content="场景应用">
<meta name="description" content="场景应用">
<meta name="apple-touch-fullscreen" content="yes">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="pragram" content="no-cache">
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
<title>量体数据添加</title>
<link href="<%=path%>/resources/public/css/style-sh.css" rel="stylesheet">
<script src="https://source.magicmanufactory.com/js/jquery/jquery.min_2.1.4.js"></script>
<script src="https://source.magicmanufactory.com/underscore.js"></script>
<style>

.tlts_design h1{ width:94%; margin-left:3%;text-align:center; padding:20px 0px;}
.tlts_design table{ width:94%; margin-left:3%; border-top:1px solid #e4e4e4;}
.tlts_design table td{ border-bottom:1px solid #e8e8e8; line-height:50px; padding-left:7px;padding-right:7px;}
.pre{float:right;z-index:1;position:absolute;right:10px}
</style>
</head>

<body>
	<div class="top" style="display:flex;flex-flow:row;">
	    
		<div class="back"><img src="<%=path%>/resources/public/images/back.jpg"
			width="100%"></div>

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
		<div class="pre">上一步</div>
	</div>

	<div id="s_xf" class="tlts_container">
		<div class="tlts_select tlts_select_view">
			<div class="tlts_style" id="s_xf_sartorial_style">
				<h1>
					SARTORIAL STYLE<br>穿衣风格
				</h1>
				<table cellpadding="0" cellspacing="0">
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
			
			<div class="tlts_design" id="s_xf_design_style">
				<h1>DRESSING HABITS<br>款式风格</h1>
				<table cellpadding="0" cellspacing="0" >
					<tr><td  width="90%">正常</td><td class="td_tright"><div class="tlts_unactive"  value="-2"></div></td></tr>
					<tr><td  width="90%">长款</td><td class="td_tright"><div class="tlts_unactive"  value="0"></div></td></tr>
					<tr><td  width="90%">短款</td><td class="td_tright"><div class="tlts_unactive"  value="-4"></div></td></tr>
				</table>
	       	</div>
			
		</div>
		<div class="tlts_btn" >下一步</div>
	</div>
	<div id="s_dy" class="tlts_container">
		<div class="tlts_select tlts_select_view">
			<div class="tlts_style" id="s_dy_sartorial_style">
				<h1>
					SARTORIAL STYLE<br>穿衣风格
				</h1>
				<table cellpadding="0" cellspacing="0">
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
			<div class="tlts_design" id="s_dy_design_style">
					<h1>DRESSING HABITS<br>款式风格</h1>
					<table cellpadding="0" cellspacing="0" >
		       		  	<tr><td  width="90%">正常</td><td class="td_tright"><div class="tlts_unactive"  value="17"></div></td></tr>
	      		  		<tr><td  width="90%">长款</td><td class="td_tright"><div class="tlts_unactive"  value="37"></div></td></tr>
	      		  	</table>
	       	</div>
			<div class="tlts_habits" id="s_dy_dressing_habits">
	           	 	<h1>DRESSING HABITS<br>穿衣习惯</h1>
	           	 	<table cellpadding="0" cellspacing="0">
	           	 		<tr><td  width="90%">穿大衣套西服</td><td class="td_tright"><div class="tlts_unactive"  value="0A02"></div></td></tr>
	           	 		<tr><td  width="90%">穿大衣不套西服</td><td class="td_tright"><div class="tlts_unactive"  value="0A01"></div></td></tr>
	           	 	</table>
	       	</div>
		</div>
		<div class="tlts_btn" >下一步</div>
	</div>
	<div id="s_cy" class="tlts_container">
		<div class="tlts_select tlts_select_view">
			<div class="tlts_style" id="s_cy_sartorial_style">
				<h1>
					SARTORIAL STYLE<br>穿衣风格
				</h1>
				<table cellpadding="0" cellspacing="0">
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
			<div class="tlts_design" id="s_cy_design_style">
					<h1>DRESSING HABITS<br>款式风格</h1>
					<table cellpadding="0" cellspacing="0">
		      		  	<tr><td  width="90%">正常</td><td class="td_tright"><div class="tlts_unactive"  value="0"></div></td></tr>
		      		  	<tr><td  width="90%">长款</td><td class="td_tright"><div class="tlts_unactive"  value="2"></div></td></tr>
		      		 	<tr><td  width="90%">短款</td><td class="td_tright"><div class="tlts_unactive"  value="-2"></div></td></tr>
	      		  	</table>
	       	</div>			
		</div>
		<div class="tlts_btn" >下一步</div>
	</div>
	<div id="s_mj" class="tlts_container">
		<div class="tlts_select tlts_select_view">
			<div class="tlts_style" id="s_mj_sartorial_style">
				<h1>
					SARTORIAL STYLE<br>穿衣风格
				</h1>
				<table cellpadding="0" cellspacing="0">
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
		</div>
		<div class="tlts_btn" >下一步</div>
	</div>
		<div id="s_kz" class="tlts_container">
		<div class="tlts_select tlts_select_view">
			<div class="tlts_style" id="s_kz_sartorial_style">
				<h1>
					SARTORIAL STYLE<br>穿衣风格
				</h1>
				<table cellpadding="0" cellspacing="0">
					<tr>
						<td width="90%">A正常版</td>
						<td width="10%" class="td_tright"><div class="tlts_unactive"
								value="2"></div></td>
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
				</table>
			</div>
			<div  class="tlts_habits" id="s_kz_dressing_habits">
	           	 	<h1>DRESSING HABITS<br>穿衣习惯</h1>
	           	 	<table cellpadding="0" cellspacing="0">
	           	 		<tr><td  width="90%">小脚裤</td><td class="td_tright"><div class="tlts_unactive"  value="3004"></div></td></tr>
	           	 	</table>
	       	</div>
		</div>
		<div class="tlts_btn" >提交</div>
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
		<div class="tlts_tck" id="msgPop" style="display: none; text-align: center">
			<div class="tlts_tcmid">
				<h1></h1>
				<div class="tlts_tcbtn">
					<span class="tlts_btnactive" style="margin-left: 65px"
						onclick="closePop('msgPop')">确定</span>
				</div>
			</div>
		</div>
		<script>

$(function(){
   var i = 0;//计数
   var input = new Array();

   $('.tlts_container').css('display', 'none');
   $('#s_xf').css('display', 'block');
   
   //上一步，返回上一个品类并赋值
	 $('.pre').click(function(event){
		 if(i != 0){
			 $('.option').find('a').eq(i-1).trigger('click');
		 }
	 });
     //下一步点击事件，品类切换数据保存
     $('.tlts_btn').bind('click',function(event){	    	 
    	var category_check = $('.option').find('a').eq(i).attr('check-id');
    	var child = {};
    	child.category = convertCategoryId(category_check);
    	var dressStyleCode = "",//着装风格
   		lengthStyleName = "",//款式风格名称
   		lengthStyleCode = "",//款式风格值
   		wearSuit = "";//着装习惯
    	
    	//获得选中的风格的值
    	$('#'+$('.option').find('a').eq(i).attr('check-id')+'_sartorial_style .tlts_active').each(function(index, element) {
    		dressStyleCode = $(element).attr("value");
    	});
    	//获得选中的款式的值
    	$('#'+$('.option').find('a').eq(i).attr('check-id')+'_design_style .tlts_active').each(function(index, element) {
    		lengthStyleCode = $(element).attr("value");
    		lengthStyleName = $(element).parent().prev().text();
    	});
    	//获得选中的习惯的值
    	$('#'+$('.option').find('a').eq(i).attr('check-id')+'_dressing_habits .tlts_active').each(function(index, element) {
    		wearSuit = $(element).attr('value');
    	});
    	
    	child.dressStyleCode = dressStyleCode;
   		child.lengthStyleCode = lengthStyleCode;
   		child.lengthStyleName = lengthStyleName;
   		child.wearSuit = wearSuit;
    	
    	if(categoryDataCheck(category_check,child)){ //检查信息是否填写完整
    		input[i] = child;
    	    //品类为西裤时做数据提交
    		if(child.category == 'MXK'){
    			  
    				$.ajax({
	    				type : 'POST',
	    				url : '<%=path%>/api/v2/public/measures/additionSH/<%=request.getAttribute("measureId")%>',
	    				contentType : 'application/json',
	    				dataType: 'json', 
	    				data : JSON.stringify(input),
	    				success : function(data) {
	    					var message = data.returnMsg;
	    					$('#submitPop').css('display', 'block');
	    					$('#submitPop').height(document.body.scrollHeight);
	    					$('#submitPop h1').html(message);
	    					$('html,body').animate({
	    						scrollTop : $('#submitPop').offset().top
	    					}, 100);
	    				},
	    				error:function(data){
	    					$('#submitPop').height(document.body.scrollHeight);
	    					$('#submitPop h1').html('访问服务器失败');
	    					$('#submitPop').css('display', 'block');
	    				}
	    			
	    			});
    			
    			
    		}else{
    			//触发下一个品类
    			$('.option').find('a').eq(i+1).trigger('click');
    		}
    	}else{
    		alert('填写完整信息');
    	}
    	
    });

    $('.select_box').click(function(event){   
        event.stopPropagation();
    });
      //选择品类更改dom和点击事件
    $('.option a').click(function(){
        i = $(this).index();
   	      
	    //显示或隐藏各个品类的dom	
	    $('.tlts_container').css('display','none');
	    $('#'+$(this).attr('check-id')).css('display','block');
	    var value=$(this).text();
	    var currentOptionName = $(this).parent().siblings('.select_txt').text();
		if($(this).text() != currentOptionName){
		    $(this).parent().siblings('.select_txt').text(value);
		}
    });
		
	//设置每个品类下各项单选控制
	$('.option a').each(function() {
		initializeSelect('#'+$(this).attr('check-id')+'_sartorial_style div');
		initializeSelect('#'+$(this).attr('check-id')+'_design_style div');
		initializeSelect('#'+$(this).attr('check-id')+'_dressing_habits div');
	});

})

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
	$('#' + pop).css('display', 'none');
	window.location.href = '<%=path%>/api/v1/public/measurers/measureDetailView/<%=request.getAttribute("measureId")%>';
}
function initializeSelect(selector){
	$(selector).bind('click', function() {
		if ($(this).attr('class') == 'tlts_unactive') {
			$(selector).each(function() {
				$(this).attr('class', 'tlts_unactive');
			});
			$(this).removeClass('tlts_unactive');
			$(this).addClass('tlts_active');
		} else {
			$(this).removeClass('tlts_active');
			$(this).addClass('tlts_unactive');
		}
	});
}
//arg:品类选择器代码;obj:品类下风格、着装习惯、款式值存放对象
function categoryDataCheck(arg,obj){
	if(arg == 's_xf') {
		if(!!obj.dressStyleCode && !! obj.lengthStyleCode && !! obj.lengthStyleName){
			return true;
		}
	}
	if(arg == 's_dy') {
		if(!!obj.dressStyleCode && !!obj.lengthStyleCode && !!obj.lengthStyleName && !!obj.wearSuit){
			return true;
		}
	}
	if(arg == 's_cy') {
		if(!!obj.dressStyleCode && !!obj.lengthStyleCode && !!obj.lengthStyleName ){
			return true;
		}
	}
	if(arg == 's_mj') {
		if(!!obj.dressStyleCode){
			return true;
		}
	}
	if(arg == 's_kz') {
		if(!!obj.dressStyleCode){
			return true;
		}
	}
	return false;
}
</script>
</body>
</html>
