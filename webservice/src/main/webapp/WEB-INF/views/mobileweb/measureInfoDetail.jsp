<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
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
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
<title>查看专属数据</title>
<link href="//dn-c2m-resources.qbox.me/@/resources/jquery/mobile/styleKute.css" rel="stylesheet">
<link rel="stylesheet" href="//cdn.bootcss.com/jquery-mobile/1.4.5/jquery.mobile.min.css">
<script src="//cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
<style type="text/css">
	#specialState select {
		width: 120px;
		text-align: right;
		direction: rtl;
	}
</style>
</head>

<body>
<div class="v_container">
	<div class="v_toptitle">
    	<h1 id = "member_name">量体人：</h1>
        <h2 id = "measure_date">量体时间&nbsp;:&nbsp;</h2>
        <div class="v_data">
        	<div class="v_bodyheight">
            	<h4 id="height"><span>&nbsp;cm</span></h4>
            </div>
            <div class="v_bodyweight">
            	<h4 id="weight"><span >&nbsp;kg</span></h4>
            </div>
        </div>
    </div>
    
    <div class="v_tailor">
        <div class="v_tailor_title">量体净尺寸:</div>
        <div >
            <table width="" cellpadding="0" cellspacing="0" style="">
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
                    <td class="td_left">脚腕围</td>
                    <td><span id="kk"></span>cm</td>
                </tr>
                <tr class="tr_hide">
                    <td class="td_left td_left_f">膝围</td>
                    <td><span id="xiw"></span>cm</td>
                    <td class="td_left">小腿围</td>
                    <td><span id="xtw"></span>cm</td>
                </tr>
                <tr class="tr_hide">
                    <td class="td_left td_left_f">腹围(凸)</td>
                    <td><span id="fwt"></span>cm</td>
                    <td class="td_left">脚口(成衣)</td>
                    <td><span id="cyjk"></span>cm</td>
                </tr>
                
            </table>
            
        </div>
        
        <div class="v_tailor_open">展开更多</div>
    </div>  
    <div id="specialState" class="tlt_special" style="padding:10px">  
 		
	    <div style="width:100%;font-size:13px;clear:both;height:45px;line-height:45px;">
	         <div style="float:left;margin:0px 2%;height:45px;line-height:45px;">左肩型</div>
	         <div style="float:right;margin:0px 2%;height:45px;line-height:45px;">
	             <select id="zjx" name="zjx" data-role=none disabled>
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
	             <select id="yjx" name="yjx" data-role=none disabled>
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
	            <div style="float:left;margin:0px 2%;height:45px;line-height:45px;">肚型</div>
	            <div style="float:right;margin:0px 2%;height:45px;line-height:45px;">
	                <select id="dx" name="dx" data-role=none disabled>
					    <option value="15" selected="">正常体</option>
					    <option value="16" >凸肚体</option>
					</select>
				</div>
	    </div>
	    <div class="c_boder" style="width:100%;font-size:13px;clear:both">
	            <div style="float:left;margin:0px 2%;height:45px;line-height:45px;">臂型</div>
	            <div style="float:right;margin:0px 2%;height:45px;line-height:45px;">
	                <select id="sx" name="sx" data-role=none disabled>
					    <option value="17">手臂靠前</option>
					    <option value="18">轻微手臂靠后</option>
					    <option value="19">手臂正常</option>
					    <option value="84">严重手臂靠后</option>
				    </select>
				</div>
	    </div>
	    <div class="c_boder" style="width:100%;font-size:13px;clear:both">
	            <div style="float:left;margin:0px 2%;height:45px;line-height:45px;">臀型</div>
	            <div style="float:right;margin:0px 2%;height:45px;line-height:45px;">
	            	<select id="tx" name="tx" data-role=none disabled>
						<option value="20">正常臀</option>
						<option value="21">平臀</option>
						<option value="23">凸臀</option>
						<option value="25">坠臀</option>
					</select>
				</div>
	    </div>
   	</div>
	<div class="v_pinpai" style="margin-top:50px;border-top:1px solid #E4E4E4">
    	<h1>风格习惯：</h1>
        <ul class="v_ul1">
        	<li class="v_fdot" id = "s_xf">
            	<span class="v_span1 v_yijispan_hide">西装</span>
            	<ul class="v_ul3">
                	<li>
                    </li>
                    <li>
                    </li>
                    <li>
                    </li>
                </ul>
            </li>
            <li id = "s_xk">
            	<span class="v_span1 v_span1 v_yijispan_hide">西裤</span>
            	<ul class="v_ul2">
                	<li>
                    </li>
                    <li>
                    </li>
                    <li>
                    </li>
                </ul>
            </li>
            <li id = "s_dy">
            	<span class="v_span1 v_yijispan_hide">大衣</span>
            	<ul class="v_ul2">
                	<li>
                    </li>
                    <li>
                    </li>
                    <li>
                    </li>
                </ul>
            </li>
            <li id = "s_mj">
            	<span class="v_span1 v_yijispan_hide">马甲</span>
            	<ul class="v_ul2">
                	<li>
                    </li>
                    <li>
                    </li>
                    <li>
                    </li>
                </ul>
            </li>
            <li id = "s_cy">
            	<span class="v_span1 v_yijispan_hide">衬衣</span>
            	<ul class="v_ul2">
                	<li>
                    </li>
                    <li>
                    </li>
                    <li>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
   
</div>

<script>
$(function(){
	
		$('.v_tailor_open').bind('click',function(){
			var that = this;
			$('.v_tailor table tr').each(function(index, element) {
                $(element).show();
				if(index>2 && $(that).attr('class')=='v_tailor_open_hide'){
					$(element).hide()
				}
            });
			
			if($(this).attr('class')=='v_tailor_open'){
				$(this).removeClass('v_tailor_open')
				$(this).addClass('v_tailor_open_hide');
				$(this).html("收起");
			}else{
				$(this).removeClass('v_tailor_open_hide')
				$(this).addClass('v_tailor_open');
				$(this).html("展开更多");
			}
		});
		
		$('.v_span1,.v_span2').bind('click',function(){
			if($(this).next().css('display')=='block'){
				$(this).next().hide();
			}else{
				$(this).next().show();
			}
		});
		$('.v_span1').bind('click',function(){
			if($(this).next().css('display')=='block'){
				$(this).removeClass("v_yijispan_hide")
				$(this).addClass("v_yijispan_show")
			}else{
				$(this).removeClass("v_yijispan_show")
				$(this).addClass("v_yijispan_hide")
			}
		})
		
	 $.ajax({
		type:'GET',
		url:'<%=path%>/api/v1/public/members/exclusiveInfo/<%=request.getAttribute("id")%>',
		success:function(data){
			$("#member_name").append(data.returnData.measurerName);
			var d = data.returnData;
			if (!!d.measureDate) {
				$("#measure_date").append(d.measureDate.substr(0,10));
			}
			$("#height").prepend(d.height);
			$("#weight").prepend(d.weight || '---');
			for(var p in data.returnData){
				if($("#"+p).length == 0 || p == "height" || p == "weight"){
					continue;
				}
				$("#"+p).append(d[p]);
			}
			/*
			$("#s_xf_szz ul li:nth-child(1)").append("<span>测试版</span>")*/
			var ms = d.ms;
			for(var j in ms){
				$("#"+j).val(ms[j]);
			}
			var malist = d.malist;
			for(var m in malist){
				var id_3 = "#"+getCategory(malist[m].category)+" ul li:nth-child(3)";
				if(malist[m].lengthStyleName != ""){
					$(id_3).append("<span class=\"v_ul3_span\">衣长调整</span><br>");
					$(id_3).append("<span>"+malist[m].lengthStyleName+"</span>");
				}
			}
			var list = d.dslist;
			for(var p in list){
				var id_1 = "#"+getCategory(list[p].dsCategory)+" ul li:nth-child(1)";
				var id_2 = "#"+getCategory(list[p].dsCategory)+" ul li:nth-child(2)";
				if(list[p].styleValues != ""){
				    var styles = list[p].styleValues.split(",");
					$(id_1).append("<span class=\"v_ul3_span\">着装风格</span><br>");
					for(var i = 0; i<styles.length; i++){
						var test = styles[i];
						$(id_1).append("<span>"+getStyleName(styles[i])+"</span>");
					}
				}
				if(list[p].habitValues != ""){
				    var habits = list[p].habitValues.split(",");
					$(id_2).append("<span class=\"v_ul3_span\">着装习惯</span><br>");
					for(var i = 0; i<habits.length; i++){
						$(id_2).append("<span>"+getHabitName(habits[i])+"</span><br>");						
					}
				}
			}
		}
	})
	function getCategory(category){
		switch(category){
			case "MXF":
			case "WXF":
			case "TXF": return "s_xf";
			case "MMJ":
			case "WMJ": return "s_mj";
			case "MXK":
			case "WXK":
			case "TXK": return "s_xk";
			case "MDY":
			case "WDY": return "s_dy";
			case "MCY":
			case "WCY":
			case "TCY": return "s_cy";
		}
	}
	function getStyleName(id){
		switch(parseInt(id)){
			case 1: return "修身";
			case 2: return "正常版";
			case 3: return "宽松";
			case 10: return "非常修身";
			case 11: return "很修身";
			case 12: return "正常偏瘦";
			case 13: return "正常偏肥";
			case 14: return "很宽松";
			case 15: return "非常宽松";
		}
	}
	function getHabitName(id){
		switch(id){
			case "001": return "+衬衣";
			case "010": return "＋保暖内衣";
			case "0A02": return "穿大衣套西服";
			case "0A01": return "穿大衣不套西服";
			case "012": return "＋薄羽绒服";
			case "013": return "＋内衣＋薄羽绒内胆";
			case "002": return "+毛衣＋衬衣";
			case "003": return "+薄毛衣＋厚毛衣＋衬衣";
			case "004": return "＋保暖内衣＋衬衣";
			case "005": return "＋保暖内衣＋衬衣＋毛衣";
			case "006": return "＋马甲＋衬衣";
			case "007": return "＋马甲＋衬衣＋保暖内衣";
			case "008": return "＋贴身穿";
			case "009": return "＋内衣";
			case "3004": return "小脚裤";
		}
	}
		
		
		
		
})
</script> 

</body>
</html>
