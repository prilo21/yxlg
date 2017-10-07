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
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
<title>数据确认</title>
<link href="https://dn-c2m-resources.qbox.me/@/resources/jquery/mobile/style.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.bootcss.com/jquery-mobile/1.4.5/jquery.mobile.min.css">
<script src="https://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/jquery-mobile/1.4.5/jquery.mobile.min.js"></script>
</head>
<style>

</style>
<body>
<div class="dc_container">
	<div class="dc_toptitle">
        <div class="dc_data">
        	<div class="dc_bodyheight">
            	<h3 class="lheight"><span>&nbsp;cm</span></h3>身高
            </div>
            <div class="dc_bodyweight">
            	<h3 class="lweight"><span>&nbsp;kg</span></h3>体重
            </div>
        </div>
    </div>
    <div class="dc_tailor">
        <div class="dc_tailor_title">量体净尺寸:</div>
        <div >
            <table width="" cellpadding="0" cellspacing="0" style="" id="pureMeasure">
                <tr >
                    <td class="td_left td_left_f">领围</td>
                    <td><span id="lw"></span>cm</td>
                    <td class="td_left">肩宽</td>
                    <td><span id="jk"></span>cm</td>
                </tr>
                <tr>
                    <td class="td_left td_left_f">前肩宽</td>
                    <td><span id="qjk"></span>cm</td>
                    <td class="td_left">胸围</td>
                    <td><span id="xw"></span>cm</td>
                </tr>
                <tr>
                    <td class="td_left td_left_f">腹围</td>
                    <td><span id="fw"></span>cm</td>
                    <td class="td_left">前腰高</td>
                    <td><span id="qyg"></span>cm</td>
                </tr>
                <tr class="tr_hide">
                    <td class="td_left td_left_f">后腰高</td>
                    <td><span id="hyg"></span>cm</td>
                    <td class="td_left">前腰节长</td>
                    <td><span id="qyjc"></span>cm</td>
                </tr>
                <tr class="tr_hide">
                    <td class="td_left td_left_f">后腰节长</td>
                    <td><span id="hyjc"></span>cm</td>
                    <td class="td_left">左手腕围</td>
                    <td><span id="zsww"></span>cm</td>
                </tr>
                <tr class="tr_hide">
                    <td class="td_left td_left_f">右手腕围</td>
                    <td><span id="ysww"></span>cm</td>
                    <td class="td_left">左袖长</td>
                    <td><span id="zxc"></span>cm</td>
                </tr>
                 <tr class="tr_hide">
                    <td class="td_left td_left_f">右袖长</td>
                    <td><span id="yxc"></span>cm</td>
                    <td class="td_left">上臂围</td>
                    <td><span id="sbw"></span>cm</td>
                </tr>
                 <tr class="tr_hide">
                    <td class="td_left td_left_f">后衣长</td>
                    <td><span id="hyc"></span>cm</td>
                    <td class="td_left">裤腰围</td>
                    <td><span id="kyw"></span>cm</td>
                </tr>
                 <tr class="tr_hide">
                    <td class="td_left td_left_f">臀围</td>
                    <td><span id="tw"></span>cm</td>
                    <td class="td_left">通档</td>
                    <td><span id="td"></span>cm</td>
                </tr>
                 <tr class="tr_hide">
                    <td class="td_left td_left_f">腿根围</td>
                    <td><span id="tgw"></span>cm</td>
                    <td class="td_left">左裤长</td>
                    <td><span id="zkc"></span>cm</td>
                </tr>
                 <tr class="tr_hide">
                    <td class="td_left td_left_f">右裤长</td>
                    <td><span id="ykc"></span>cm</td>
                    <td class="td_left">裤口</td>
                    <td><span id="kk"></span>cm</td>
                </tr>
                <tr class="tr_hide">
                    <td class="td_left td_left_f">膝围</td>
                    <td><span id="xiw"></span>cm</td>
                    <td class="td_left">小腿围</td>
                    <td><span id="xtw"></span>cm</td>
                </tr>
            </table>
        </div>
        <div class="dc_tailor_open">展开更多</div>
    </div>
    
<!-- 	<ul class="dc_nav"> -->
<!-- 		<li class="dc_nav_ml dc_nav_selected" id="XF" style="display:none" check-id="s_xf">西服</li> -->
<!-- 		<li class="dc_nav_ml " id="DY" style="display:none" check-id="s_dy">大衣</li> -->
<!-- 		<li class="dc_nav_ml " id="CY" style="display:none" check-id="s_cy">衬衣</li> -->
<!-- 		<li class="dc_nav_ml " id="MJ" style="display:none" check-id="s_mj">马甲</li> -->
<!-- 		<li class="dc_nav_ml " id="XK" style="display:none" check-id="s_xk">西裤</li> -->
		
<!-- 	</ul> -->
<!--     <div class="dc_select_view dc_select" id="s_xf"> -->
<!--     	<ul class="dc_ul"> -->
<!--         	<li class="dc_selected1" check-class="s_xf_c" check-id="s_xfsxx">休闲</li> -->
<!--             <li class="dc_ml dc_unselected1" check-class="s_xf_c" check-id="s_xfszz">正装</li> -->
<!--             <li class="dc_ml dc_unselected1" check-class="s_xf_c" check-id="s_xfslf">礼服</li> -->
<!--         </ul> -->
<!--         <div class="s_xf_c" id="s_xfsxx"> -->
<!-- 	        <div class="dc_stybox1"  id="s_xfsxx_sv"> -->
<!-- 	        </div> -->
<!-- 	        <h1 class="dc_selzzxg">选择着装习惯：</h1> -->
<!-- 	        <ul class="dc_ul2" id="s_xfsxx_hv"> -->
<!-- 	        </ul> -->
<!--         </div> -->
<!--         <div class="s_xf_c" style="display:none;" id="s_xfszz"> -->
<!-- 	        <div class="dc_stybox1"  id="s_xfszz_sv"> -->
<!-- 	        </div> -->
<!-- 	        <h1 class="dc_selzzxg">选择着装习惯：</h1> -->
<!-- 	        <ul class="dc_ul2" id="s_xfszz_hv"> -->
<!-- 	        </ul> -->
<!--         </div> -->
<!--         <div class="s_xf_c" style="display:none;" id="s_xfslf"> -->
<!-- 	        <div class="dc_stybox1"  id="s_xfslf_sv"> -->
<!-- 	        </div> -->
<!-- 	        <h1 class="dc_selzzxg">选择着装习惯：</h1> -->
<!-- 	        <ul class="dc_ul2" id="s_xfslf_hv"> -->
<!-- 	        </ul> -->
<!--         </div> -->
<!--     </div> -->
<!-- 	<div class="dc_select" id="s_dy"> -->
<!-- 	    <ul class="dc_ul"> -->
<!--         	<li class="dc_selected1" check-class="s_dy_c" check-id="s_dysxx">休闲</li> -->
<!--             <li class="dc_ml dc_unselected1" check-class="s_dy_c" check-id="s_dyszz">正装</li> -->
<!--         </ul> -->
<!--         <div class="s_dy_c" id="s_dysxx"> -->
<!-- 	        <div class="dc_stybox1"  id="s_dysxx_sv"> -->
<!-- 	        </div> -->
<!-- 	        <h1 class="dc_selzzxg">选择着装习惯：</h1> -->
<!-- 	        <ul class="dc_ul2" id="s_dysxx_hv"> -->
<!-- 	        </ul> -->
<!--         </div> -->
<!--         <div class="s_dy_c" style="display:none;" id="s_dyszz"> -->
<!-- 	        <div class="dc_stybox1"  id="s_dyszz_sv"> -->
<!-- 	        </div> -->
<!-- 	        <h1 class="dc_selzzxg">选择着装习惯：</h1> -->
<!-- 	        <ul class="dc_ul2" id="s_dyszz_hv"> -->
<!-- 	        </ul> -->
<!--         </div> -->
<!-- 	</div> -->
<!-- 	<div class="dc_select" id="s_cy"> -->
<!-- 	    <ul class="dc_ul"> -->
<!--         	<li class="dc_selected1" check-class="s_cy_c" check-id="s_cysxx">休闲</li> -->
<!--             <li class="dc_ml dc_unselected1" check-class="s_cy_c" check-id="s_cyszz">正装</li> -->
<!--         </ul> -->
<!--         <div class="s_cy_c" id="s_cysxx"> -->
<!-- 	        <div class="dc_stybox1"  id="s_cysxx_sv"> -->
<!-- 	        </div> -->
<!-- 	        <h1 class="dc_selzzxg">选择着装习惯：</h1> -->
<!-- 	        <ul class="dc_ul2" id="s_cysxx_hv"> -->
<!-- 	        </ul> -->
<!--         </div> -->
<!--         <div class="s_cy_c" style="display:none;" id="s_cyszz"> -->
<!-- 	        <div class="dc_stybox1"  id="s_cyszz_sv"> -->
<!-- 	        </div> -->
<!-- 	        <h1 class="dc_selzzxg">选择着装习惯：</h1> -->
<!-- 	        <ul class="dc_ul2" id="s_cyszz_hv"> -->
<!-- 	        </ul> -->
<!--         </div> -->
<!-- 	</div> -->
<!-- 	<div class="dc_select" id="s_mj"> -->
<!-- 	    <ul class="dc_ul"> -->
<!--         	<li class="dc_selected1" check-class="s_mj_c" check-id="s_mjsxx">休闲</li> -->
<!--             <li class="dc_ml dc_unselected1" check-class="s_mj_c" check-id="s_mjszz">正装</li> -->
<!--         </ul> -->
<!--         <div class="s_mj_c" id="s_mjsxx"> -->
<!-- 	        <div class="dc_stybox1"  id="s_mjsxx_sv"> -->
<!-- 	        </div> -->
<!-- 	        <h1 class="dc_selzzxg">选择着装习惯：</h1> -->
<!-- 	        <ul class="dc_ul2" id="s_mjsxx_hv"> -->
<!-- 	        </ul> -->
<!--         </div> -->
<!--         <div class="s_mj_c" style="display:none;" id="s_mjszz"> -->
<!-- 	        <div class="dc_stybox1"  id="s_mjszz_sv"> -->
<!-- 	        </div> -->
<!-- 	        <h1 class="dc_selzzxg">选择着装习惯：</h1> -->
<!-- 	        <ul class="dc_ul2" id="s_mjszz_hv"> -->
<!-- 	        </ul> -->
<!--         </div> -->
<!-- 	</div> -->

<!-- 	<div class="dc_select" id="s_xk"> -->
<!-- 	    <ul class="dc_ul"> -->
<!--         	<li class="dc_selected1" check-class="s_xk_c" check-id="s_xksxx">休闲</li> -->
<!--             <li class="dc_ml dc_unselected1" check-class="s_xk_c" check-id="s_xkszz">正装</li> -->
<!--             <li class="dc_ml dc_unselected1" check-class="s_xk_c" check-id="s_xkslf">礼服</li> -->
<!--         </ul> -->
<!--         <div class="s_xk_c" id="s_xksxx"> -->
<!-- 	        <div class="dc_stybox1"  id="s_xksxx_sv"> -->
<!-- 	        </div> -->
<!-- 	        <h1 class="dc_selzzxg">选择着装习惯：</h1> -->
<!-- 	        <ul class="dc_ul2" id="s_xksxx_hv"> -->
<!-- 	        </ul> -->
<!--         </div> -->
<!--         <div class="s_xk_c" style="display:none;" id="s_xkszz"> -->
<!-- 	        <div class="dc_stybox1"  id="s_xkszz_sv"> -->
<!-- 	        </div> -->
<!-- 	        <h1 class="dc_selzzxg">选择着装习惯：</h1> -->
<!-- 	        <ul class="dc_ul2" id="s_xkszz_hv"> -->
<!-- 	        </ul> -->
<!--         </div> -->
<!--         <div class="s_xk_c" style="display:none;" id="s_xkslf"> -->
<!-- 	        <div class="dc_stybox1"  id="s_xkslf_sv"> -->
<!-- 	        </div> -->
<!-- 	        <h1 class="dc_selzzxg">选择着装习惯：</h1> -->
<!-- 	        <ul class="dc_ul2" id="s_xkslf_hv"> -->
<!-- 	        </ul> -->
<!--         </div> -->
<!-- 	</div> -->

	
</div>
<div  class="dc_btn" onclick="submitJSON();" >确定</div>
<script>
$(function(){
})
var dslength = 0;
function submitJSON(){
	var jsonString = '{"measureId":"<%=request.getAttribute("id")%>","category":[';
	var messageToPost = "";
	var flag = 0;
	if(dslength != 0){
		var shString = "";
		var s_obj = $(".dc_btnactive");
		var h_obj = $(".dc_selected2");
		for(var i=0;i<s_obj.length;i++){
			var s_value = s_obj[i].getAttribute("value");
			var s_c = s_obj[i].getAttribute("checked-s");
			var s_cd = s_obj[i].getAttribute("checked-d");
			for(var j=0;j<h_obj.length;j++){
				var h_value = h_obj[i].getAttribute("value");
				var h_c = h_obj[i].getAttribute("checked-s");
				var h_cd = h_obj[i].getAttribute("checked-d");
				if(s_c==h_c ){
					if(s_cd == h_cd){
						var s_code = getStyleCode(s_c);
						shString = shString + '{"ds_category":"'+s_code+'","ds_category_detail":"'+s_cd+'","style_value":"'+s_value+'","habit_value":"'+h_value+'"},';
					}else{
						flag = 1;
						alert("请选择相同小类下的着装风格和习惯");
					}
					break;
				}else{
					continue;
				}
			}
		}
		if(shString != ""){
		    messageToPost = jsonString + shString.substring(0,shString.length-1) + ']}';
	    }else{
	    	 messageToPost = jsonString + ']}';
	    }
	}else{
		messageToPost = jsonString + ']}';
	}
	if(flag == 1){
		return false;
	}
	console.log(messageToPost);
	var ua = navigator.userAgent.toLowerCase();	
	if (/iphone|ipad|ipod/.test(ua)) {
		window.webkit.messageHandlers.buttonClicked.postMessage(messageToPost);	
	} 
	else if (/android/.test(ua)) {
		window.jsObj.sendMessage(messageToPost);
	} else {
		alert("请在移动设备上使用");
	}
	
}
$(document).ready(function() {
	$.ajax({
		type:'GET',
		url:window.location.protocol + '<%=basePath%>api/v1/public/members/exclusiveInfo/<%=request.getAttribute("id")%>',
		success:function(data){
	    	$(".lheight").prepend(data.returnData.height);
			$(".lweight").prepend(data.returnData.weight);
			var measure = data.returnData;
			//净体数据展示
			for(var key in measure) {
				$("#"+key).append(measure[key]);
			}
			var dslist = data.returnData.dslist;
			dslength = dslist.length;
			if(dslength != 0){
				for(var n=0;n<dslist.length;n++){
					var one = dslist[n];
					if(one.dsCategoryDetail == "sxx"){//如果是西服的休闲，默认选中第一个，其他的类型不选中
						var styles = one.styleValues.split(",");
						var html = "";
						for(var i=0;i<styles.length;i++){
							if(i==0){//第一个风格选中
								html = html + getSelectStyleHtml(one.dsCategory,one.dsCategoryDetail,styles[i]);
							}else{//其他风格不选中
								html = html + getStyleHtml(one.dsCategory,one.dsCategoryDetail,styles[i]);
							}
						}
						
						$("#"+one.dsCategory+one.dsCategoryDetail+"_sv").append(html);
						var habits = one.habitValues.split(",");
						var hvhtml = "";
						for(var j=0;j<habits.length;j++){
							if(j == 0){ 
								hvhtml = hvhtml + getSelectHabitHtml(one.dsCategory,one.dsCategoryDetail,habits[j]);
							}else{getSelectHabitHtml
								hvhtml = hvhtml + getHabitHtml(one.dsCategory,one.dsCategoryDetail,habits[j]);
							}
						}
						$("#"+one.dsCategory+one.dsCategoryDetail+"_hv").append(hvhtml);
					}else{//非西服和休闲的风格和习惯默认不选中
		//				if(one.dsCategory == "s_tz"){//套装
		//					var styles = one.styleValues.split(",");
		//					var html = "";
		//					for(var i=0;i<styles.length;i++){
		//						html = html + getStyleHtml(one.dsCategory+"_c",styles[i]);
		//					}
		//					$("#"+one.dsCategory+"_"+one.dsCategoryTz+one.dsCategoryDetail+"_sv").append(html);
		//					var habits = one.habitValues.split(",");
		//					var hvhtml = "";
		//					for(var j=0;j<habits.length;j++){
		//						hvhtml = hvhtml + getHabitHtml(one.dsCategory+"_c",habits[j]);
		//					}
		//					$("#"+one.dsCategory+"_"+one.dsCategoryTz+one.dsCategoryDetail+"_hv").append(hvhtml);
		//				}else{
							var styles = one.styleValues.split(",");
							var html = "";
							for(var i=0;i<styles.length;i++){
								html = html + getStyleHtml(one.dsCategory,one.dsCategoryDetail,styles[i]);
							}
							$("#"+one.dsCategory+one.dsCategoryDetail+"_sv").append(html);
							var habits = one.habitValues.split(",");
							var hvhtml = "";
							for(var j=0;j<habits.length;j++){
								hvhtml = hvhtml + getHabitHtml(one.dsCategory,one.dsCategoryDetail,habits[j]);
							}
							$("#"+one.dsCategory+one.dsCategoryDetail+"_hv").append(hvhtml);
		//				}
					}
			   }	
			}
		}
	});
	
	$('.dc_nav li').bind('click',function(){
		$('.dc_nav li').removeClass('dc_nav_selected');
		$(this).addClass('dc_nav_selected');
		$('.dc_select').removeClass('dc_select_view');
		$('#'+$(this).attr('check-id')).addClass('dc_select_view');
	})
	var category = '<%=request.getAttribute("category")%>';
	if(category == '6' || category == '7'||category == '8'||category == '9'||category == '10'||category == '0'){
		if(category == '6'){
			$('#XF').css("display","block");
			$('.dc_nav li').removeClass('dc_nav_selected');
			$('#XF').addClass('dc_nav_selected');
			$('.dc_select').removeClass('dc_select_view');
			$('#'+$('#XF').attr('check-id')).addClass('dc_select_view');
		}
		if(category == '7'){
			$('#MJ').css("display","block");
			$('.dc_nav li').removeClass('dc_nav_selected');
			$('#MJ').addClass('dc_nav_selected');
			$('.dc_select').removeClass('dc_select_view');
			$('#'+$('#MJ').attr('check-id')).addClass('dc_select_view');
		}
		if(category == '8'){
			$('#XK').css("display","block");
			$('.dc_nav li').removeClass('dc_nav_selected');
			$('#XK').addClass('dc_nav_selected');
			$('.dc_select').removeClass('dc_select_view');
			$('#'+$('#XK').attr('check-id')).addClass('dc_select_view');
		}
		if(category == '9'){
			$('#DY').css("display","block");
			$('.dc_nav li').removeClass('dc_nav_selected');
			$('#DY').addClass('dc_nav_selected');
			$('.dc_select').removeClass('dc_select_view');
			$('#'+$('#DY').attr('check-id')).addClass('dc_select_view');
		}
		if(category == '10'){
			$('#CY').css("display","block");
			$('.dc_nav li').removeClass('dc_nav_selected');
			$('#CY').addClass('dc_nav_selected');
			$('.dc_select').removeClass('dc_select_view');
			$('#'+$('#CY').attr('check-id')).addClass('dc_select_view');
		}
	    if(category == '0'){
	    	$('#XF').css("display","block");
	    	$('#XK').css("display","block");
	    	$('.dc_nav li').removeClass('dc_nav_selected');
			$('#XF').addClass('dc_nav_selected');
			$('.dc_select').removeClass('dc_select_view');
			$('#'+$('#XF').attr('check-id')).addClass('dc_select_view');
	    }
	} else {
		$("body").html("不存在的品类");
	}
	$('.dc_nav_two li').bind('click',function(){
		$('.dc_nav_two li').removeClass('dc_nav_two_selected');
		$(this).addClass('dc_nav_two_selected');
		$('.dc_two_select').removeClass('dc_two_select_view');
		$('#'+$(this).attr('check-id')).addClass('dc_two_select_view');
	})
	$('#ulId1 li').on("swipeleft",function(){
		var a = $('.pNum.ui-link.on').attr('data-index'); 
		if(a!=2){  
			var nextLi=$('.pNum.ui-link.on').next();
			$('.pNum.ui-link.on').removeClass('on'); 
			nextLi.addClass('on');  
			a=a*1+1;  
			var b = (a-1)*100;  
			var left ='-'+b+'%';
			$('#ulId1').animate({marginLeft:left});
		}
	});
	$('#ulId1 li').on("swiperight",function(){
		var a = $('.pNum.ui-link.on').attr('data-index');
		if(a!=1){ 
			var prevLi=$('.pNum.ui-link.on').prev(); 
			$('.pNum.ui-link.on').removeClass('on');
			prevLi.addClass('on');
			a=a*1-1;
			var b = (a-1)*100;
			var left = '0px'
			if(b>0){  
				left ='-'+b+'%';
			}
			$('#ulId1').animate({marginLeft:left});
		}
	});
	//正装、休闲、礼服切换
	$('#s_xf  .dc_ul li').bind('click',function(){
		$('#s_xf .dc_ul li').each(function(index, element) {
            $(element).removeClass('dc_selected1');
			$(element).addClass('dc_unselected1');
        });
		$(this).addClass('dc_selected1');
		var check_class = $(this).attr("check-class");
		var check_id = $(this).attr("check-id");
		$('.'+check_class).each(function(index, element){
			$(element).css("display","none");
		});
		$('#'+check_id).css("display","block");
	});
	
	$('#s_dy .dc_ul li').bind('click',function(){
		$('#s_dy .dc_ul li').each(function(index, element) {
            $(element).removeClass('dc_selected1');
			$(element).addClass('dc_unselected1');
        });
		$(this).addClass('dc_selected1');
		var check_class = $(this).attr("check-class");
		var check_id = $(this).attr("check-id");
		$('.'+check_class).each(function(index, element){
			$(element).css("display","none");
		});
		$('#'+check_id).css("display","block");
	});
	$('#s_cy .dc_ul li').bind('click',function(){
		$('#s_cy .dc_ul li').each(function(index, element) {
            $(element).removeClass('dc_selected1');
			$(element).addClass('dc_unselected1');
        });
		$(this).addClass('dc_selected1');
		var check_class = $(this).attr("check-class");
		var check_id = $(this).attr("check-id");
		$('.'+check_class).each(function(index, element){
			$(element).css("display","none");
		});
		$('#'+check_id).css("display","block");
	});
	$('#s_mj .dc_ul li').bind('click',function(){
		$('#s_mj .dc_ul li').each(function(index, element) {
            $(element).removeClass('dc_selected1');
			$(element).addClass('dc_unselected1');
        });
		$(this).addClass('dc_selected1');
		var check_class = $(this).attr("check-class");
		var check_id = $(this).attr("check-id");
		$('.'+check_class).each(function(index, element){
			$(element).css("display","none");
		});
		$('#'+check_id).css("display","block");
	});
	$('#s_xk .dc_ul li').bind('click',function(){
		$('#s_xk .dc_ul li').each(function(index, element) {
            $(element).removeClass('dc_selected1');
			$(element).addClass('dc_unselected1');
        });
		$(this).addClass('dc_selected1');
		var check_class = $(this).attr("check-class");
		var check_id = $(this).attr("check-id");
		$('.'+check_class).each(function(index, element){
			$(element).css("display","none");
		});
		$('#'+check_id).css("display","block");
	});

	$('.dc_sty1 .dc_stybtn').bind('click',function(){
		$('.dc_sty1 .dc_stybtn').each(function(index, element) {
            $(element).removeClass('dc_btnactive');
        });
		$(this).addClass('dc_btnactive');
	});
	
	$('.dc_ul2 li').bind('click',function(){
		
		$('.dc_ul2 li').each(function(index, element) {
             $(element).removeClass('dc_selected2');
			$(element).addClass('dc_unselected2');
        });
		$(this).addClass('dc_selected2');
	});
	$('.dc_tailor_open').bind('click',function(){
		var that = this;
		$('.dc_tailor table tr').each(function(index, element) {
            $(element).show();
			if(index>2 && $(that).attr('class')=='dc_tailor_open_hide'){
				$(element).hide()
			}
        });
		
		if($(this).attr('class')=='dc_tailor_open'){
			$(this).removeClass('dc_tailor_open')
			$(this).addClass('dc_tailor_open_hide');
			$(this).html('收起');
		}else{
			$(this).removeClass('dc_tailor_open_hide')
			$(this).addClass('dc_tailor_open');
			$(this).html('展开更多');
		}
	});
	
	
})
/**
 * 风格选中变换
 */
 var pubc;
 var pubcd;
function displayStyle(obj){
	var checked = obj.getAttribute("checked-s");
		$('#'+checked+' .dc_sty1 .dc_stybtn').each(function(index, element) {
	        $(element).removeClass('dc_btnactive');
	    });
	   $(obj).addClass('dc_btnactive');
	
}
/**
 * 习惯选中变换
 */
function displayHabit(obj){
	var checked = obj.getAttribute("checked-s");
	$('#'+checked+' .dc_ul2 li').each(function(index, element) {
        $(element).removeClass('dc_selected2');
		$(element).addClass('dc_unselected2');
   });
	$(obj).addClass('dc_selected2');
}
/**
 * 获得选中的风格html
 */
function getSelectStyleHtml(cs,csd,sv){
	var svhtml = "";
	switch (sv){
	    case "2":
		    svhtml = "<div class=\"dc_sty1\"><div class=\"dc_stybtn dc_btnactive\" value=\"2\" checked-s=\""+cs+"\" checked-d=\""+csd+"\" onclick=\"displayStyle(this)\">正常版</div><p></p></div>";
	        break;
	    case "12":
		    svhtml = "<div class=\"dc_sty1\"><div class=\"dc_stybtn dc_btnactive\" value=\"12\" checked-s=\""+cs+"\" checked-d=\""+csd+"\" onclick=\"displayStyle(this)\">正常偏瘦</div><p></p></div>";
	        break;
	    case "1":
	    	svhtml = "<div class=\"dc_sty1\"><div class=\"dc_stybtn dc_btnactive\" value=\"1\" checked-s=\""+cs+"\" checked-d=\""+csd+"\" onclick=\"displayStyle(this)\">修身</div><p></p></div>";
	        break;
	    case "11":
		    svhtml = "<div class=\"dc_sty1\"><div class=\"dc_stybtn dc_btnactive\" value=\"11\" checked-s=\""+cs+"\" checked-d=\""+csd+"\" onclick=\"displayStyle(this)\">很修身</div><p></p></div>";
	        break;
	    case "10":
	    	svhtml = "<div class=\"dc_sty1\"><div class=\"dc_stybtn dc_btnactive\" value=\"10\" checked-s=\""+cs+"\" checked-d=\""+csd+"\" onclick=\"displayStyle(this)\">非常修身</div><p></p></div>";
	        break;
	    case "13":
	    	svhtml = "<div class=\"dc_sty1\"><div class=\"dc_stybtn dc_btnactive\"  value=\"13\" checked-s=\""+cs+"\" checked-d=\""+csd+"\" onclick=\"displayStyle(this)\">正常偏肥</div><p></p></div>";
	        break;
	    case "3":
	    	svhtml = "<div class=\"dc_sty1\"><div class=\"dc_stybtn dc_btnactive\" value=\"3\" checked-s=\""+cs+"\" checked-d=\""+csd+"\" onclick=\"displayStyle(this)\">宽松</div><p></p></div>";
	        break;
	    case "14":
	    	svhtml = "<div class=\"dc_sty1\"><div class=\"dc_stybtn dc_btnactive\" value=\"14\" checked-s=\""+cs+"\" checked-d=\""+csd+"\" onclick=\"displayStyle(this)\">很宽松</div><p></p></div>";
	        break;
	    case "15":
	    	svhtml = "<div class=\"dc_sty1\"><div class=\"dc_stybtn dc_btnactive\" value=\"15\" checked-s=\""+cs+"\" checked-d=\""+csd+"\" onclick=\"displayStyle(this)\">非常宽松</div><p></p></div>";
	        break;
	    default:
	    	break;
	}
	return svhtml;
}
/**
 * 未选中的风格html
 */
function getStyleHtml(cs,csd,sv){
	var svhtml = "";
	switch (sv){
	    case "2":
		    svhtml = "<div class=\"dc_sty1\"><div class=\"dc_stybtn\" value=\"2\"  checked-s=\""+cs+"\" checked-d=\""+csd+"\" onclick=\"displayStyle(this)\">正常版</div><p></p></div>";
	        break;
	    case "12":
		    svhtml = "<div class=\"dc_sty1\"><div class=\"dc_stybtn\" value=\"12\"  checked-s=\""+cs+"\" checked-d=\""+csd+"\" onclick=\"displayStyle(this)\">正常偏瘦</div><p></p></div>";
	        break;
	    case "1":
	    	svhtml = "<div class=\"dc_sty1\"><div class=\"dc_stybtn\" value=\"1\"  checked-s=\""+cs+"\" checked-d=\""+csd+"\" onclick=\"displayStyle(this)\">修身</div><p></p></div>";
	        break;
	    case "11":
		    svhtml = "<div class=\"dc_sty1\"><div class=\"dc_stybtn\" value=\"11\"  checked-s=\""+cs+"\" checked-d=\""+csd+"\" onclick=\"displayStyle(this)\">很修身</div><p></p></div>";
	        break;
	    case "10":
	    	svhtml = "<div class=\"dc_sty1\"><div class=\"dc_stybtn\" value=\"10\"  checked-s=\""+cs+"\" checked-d=\""+csd+"\" onclick=\"displayStyle(this)\">非常修身</div><p></p></div>";
	        break;
	    case "13":
	    	svhtml = "<div class=\"dc_sty1\"><div class=\"dc_stybtn\" value=\"13\"  checked-s=\""+cs+"\" checked-d=\""+csd+"\" onclick=\"displayStyle(this)\">正常偏肥</div><p></p></div>";
	        break;
	    case "3":
	    	svhtml = "<div class=\"dc_sty1\"><div class=\"dc_stybtn\" value=\"3\"  checked-s=\""+cs+"\" checked-d=\""+csd+"\" onclick=\"displayStyle(this)\">宽松</div><p></p></div>";
	        break;
	    case "14":
	    	svhtml = "<div class=\"dc_sty1\"><div class=\"dc_stybtn\" value=\"14\"  checked-s=\""+cs+"\" checked-d=\""+csd+"\" onclick=\"displayStyle(this)\">很宽松</div><p></p></div>";
	        break;
	    case "15":
	    	svhtml = "<div class=\"dc_sty1\"><div class=\"dc_stybtn\" value=\"15\"  checked-s=\""+cs+"\" checked-d=\""+csd+"\" onclick=\"displayStyle(this)\">非常宽松</div><p></p></div>";
	        break;
	    default:
	    	break;
	}
	return svhtml;
}

function getSelectHabitHtml(cs,csd,hv){
	var hvhtml = "";
	switch(hv){
		case "001":
			hvhtml = "<li class=\"dc_selected2\" checked-s=\""+cs+"\"  value=\"001\"  checked-d=\""+csd+"\" onclick=\"displayHabit(this)\">＋衬衣</li>";
			break;
		case "002":
			hvhtml = "<li class=\"dc_selected2\" checked-s=\""+cs+"\"  value=\"002\"  checked-d=\""+csd+"\" onclick=\"displayHabit(this)\">+毛衣＋衬衣</li>";
			break;
		case "003":
			hvhtml = "<li class=\"dc_selected2\" checked-s=\""+cs+"\"  value=\"003\"  checked-d=\""+csd+"\" onclick=\"displayHabit(this)\">+薄毛衣＋厚毛衣＋衬衣</li>";
			break;
		case "004":
			hvhtml = "<li class=\"dc_selected2\" checked-s=\""+cs+"\"  value=\"004\"  checked-d=\""+csd+"\" onclick=\"displayHabit(this)\">＋保暖内衣＋衬衣</li>";
			break;
		case "005":
			hvhtml = "<li class=\"dc_selected2\" checked-s=\""+cs+"\"  value=\"005\" checked-d=\""+csd+"\"  onclick=\"displayHabit(this)\">＋保暖内衣＋衬衣＋毛衣</li>";
			break;
		case "006":
			hvhtml = "<li class=\"dc_selected2\" checked-s=\""+cs+"\"  value=\"006\" checked-d=\""+csd+"\"  onclick=\"displayHabit(this)\">＋马甲＋衬衣</li>";
			break;
		case "007":
			hvhtml = "<li class=\"dc_selected2\" checked-s=\""+cs+"\"  value=\"007\" checked-d=\""+csd+"\"  onclick=\"displayHabit(this)\">＋马甲＋衬衣＋保暖内衣</li>";
			break;
		case "008": 
			hvhtml = "<li class=\"dc_selected2\" checked-s=\""+cs+"\"  value=\"008\" checked-d=\""+csd+"\"  onclick=\"displayHabit(this)\">＋贴身穿</li>";
		    break;
		case "009":
			hvhtml = "<li class=\"dc_selected2\" checked-s=\""+cs+"\"  value=\"009\" checked-d=\""+csd+"\"  onclick=\"displayHabit(this)\">＋内衣</li>";
			break;
		case "010":
			hvhtml = "<li class=\"dc_selected2\" checked-s=\""+cs+"\"  value=\"010\" checked-d=\""+csd+"\"  onclick=\"displayHabit(this)\">＋保暖内衣</li>";
			break;
		case "011":
			hvhtml = "<li class=\"dc_selected2\" checked-s=\""+cs+"\" value=\"011\"  checked-d=\""+csd+"\"  onclick=\"displayHabit(this)\">＋西装</li>";
			break;
		case "012":
			hvhtml = "<li class=\"dc_selected2\" checked-s=\""+cs+"\" value=\"012\"  checked-d=\""+csd+"\"  onclick=\"displayHabit(this)\">＋薄羽绒服</li>";
			break;
		case "013":
			hvhtml = "<li class=\"dc_selected2\" checked-s=\""+cs+"\" value=\"013\"  checked-d=\""+csd+"\"  onclick=\"displayHabit(this)\">＋内衣＋薄羽绒内胆</li>";
			break;
	    default:
	    	break;
	}
	return hvhtml;
}

function getHabitHtml(cs,csd,hv){
	var hvhtml = "";
	switch(hv){
		case "001":
			hvhtml = "<li class=\"dc_ml dc_unselected2\" checked-s=\""+cs+"\" value=\"001\"  checked-d=\""+csd+"\"  onclick=\"displayHabit(this)\">＋衬衣</li>";
			break;
		case "002":
			hvhtml = "<li class=\"dc_ml dc_unselected2\" checked-s=\""+cs+"\" value=\"002\"  checked-d=\""+csd+"\"  onclick=\"displayHabit(this)\">+毛衣＋衬衣</li>";
			break;
		case "003":
			hvhtml = "<li class=\"dc_ml dc_unselected2\" checked-s=\""+cs+"\" value=\"003\"  checked-d=\""+csd+"\"  onclick=\"displayHabit(this)\">+薄毛衣＋厚毛衣＋衬衣</li>";
			break;
		case "004":
			hvhtml = "<li class=\"dc_ml dc_unselected2\" checked-s=\""+cs+"\" value=\"004\"  checked-d=\""+csd+"\"  checked=\""+cs+"\"  onclick=\"displayHabit(this)\">＋保暖内衣＋衬衣</li>";
			break;
		case "005":
			hvhtml = "<li class=\"dc_ml dc_unselected2\" checked-s=\""+cs+"\" value=\"005\"  checked-d=\""+csd+"\"  onclick=\"displayHabit(this)\">＋保暖内衣＋衬衣＋毛衣</li>";
			break;
		case "006":
			hvhtml = "<li class=\"dc_ml dc_unselected2\" checked-s=\""+cs+"\" value=\"006\"  checked-d=\""+csd+"\"  onclick=\"displayHabit(this)\">＋马甲＋衬衣</li>";
			break;
		case "007":
			hvhtml = "<li class=\"dc_ml dc_unselected2\" checked-s=\""+cs+"\" value=\"007\"  checked-d=\""+csd+"\"  onclick=\"displayHabit(this)\">＋马甲＋衬衣＋保暖内衣</li>";
			break;
		case "008": 
			hvhtml = "<li class=\"dc_ml dc_unselected2\" checked-s=\""+cs+"\" value=\"008\"  checked-d=\""+csd+"\"  onclick=\"displayHabit(this)\">＋贴身穿</li>";
		    break;
		case "009":
			hvhtml = "<li class=\"dc_ml dc_unselected2\" checked-s=\""+cs+"\" value=\"009\"  checked-d=\""+csd+"\"  onclick=\"displayHabit(this)\">＋内衣</li>";
			break;
		case "010":
			hvhtml = "<li class=\"dc_ml dc_unselected2\" checked-s=\""+cs+"\"  value=\"010\" checked-d=\""+csd+"\"  onclick=\"displayHabit(this)\">＋保暖内衣</li>";
			break;
		case "011":
			hvhtml = "<li class=\"dc_ml dc_unselected2\" checked-s=\""+cs+"\" value=\"011\"  checked-d=\""+csd+"\"  onclick=\"displayHabit(this)\">＋西装</li>";
			break;
		case "012":
			hvhtml = "<li class=\"dc_ml dc_unselected2\" checked-s=\""+cs+"\"  value=\"012\"  checked-d=\""+csd+"\" onclick=\"displayHabit(this)\">＋薄羽绒服</li>";
			break;
		case "013":
			hvhtml = "<li class=\"dc_ml dc_unselected2\" checked-s=\""+cs+"\" value=\"013\"  checked-d=\""+csd+"\"  onclick=\"displayHabit(this)\">＋内衣＋薄羽绒内胆</li>";
			break;
	    default:
	    	break;
	}
	return hvhtml;
}
function getStyleCode(sc){
	var code = "";
	switch(sc){
	case "s_xf":
		code = "MXF";
		break;
	case "s_dy":
		code = "MDY";
		break;
	case "s_mj":
		code = "MMJ";
		break;
	case "s_cy":
		code = "MCY";
		break;
	case "s_xk":
		code = "MXK";
		break;
	default:
		break;
	}
	return code;
}
</script> 

</body>
</html>
