<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<title>成衣尺寸录入</title>
<script src="//cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
<link href="<%=path%>/resources/public/css/style-garment.css" rel="stylesheet">
<script src="//res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
<style>
.circle_btn_sub,.circle_btn_add {
	width:24px;height:24px;border:1px solid #eee;border-radius: 12px;margin:0px 10px;
}
.cy_row{
	height:24px;
	margin-top:10px;
	font-size:12px;
	line-height:24px;text-align:center;
}
.cy_data_box{
	width:45%;
	color:#999;
}
</style>
</head>
<body>
<%@ include file="../tailor/wxConfig.jsp"%>
<div class="user_msg">
	<div class="user_name"><span>姓名：</span><input type="text" id="user_name"></div>
    <div class="user_phone"><span>联系方式：</span><input id="user_phone" type="tel" name=""  maxlength="13"></div>
</div>
<ul class="se_nav invoiceBgColor">
    <li class="se_nav_ml se_nav_selected" check-id="se_cy">成衣数据</li>
    <li class="se_nav_ml " check-id="se_lt">量体基础信息</li>
</ul>
<div class="se_select_view se_select" id="se_cy">
    <div class="se_selectBox">
    	<div class="se_b_left">
        	<div class="se_select_row">
            	<span class="se_select_title">西服品类</span>
            	<form> 
                    <a class="btn-select"> 
                        <span class="cur-select" id="s_c_xf">请扫描二维码</span> 
                    </a> 
                </form>
            </div>
            <div class="se_select_row">
            	<span class="se_select_title">选尺码</span>
            	<form> 
                    <a class="btn-select"> 
                        <span class="cur-select" id="s_xf_selected">请选择</span> 
                        <select id="size-xf-select" > 
                        	<option value="">请选择</option> 
                     
                        </select> 
                    </a> 
                </form>
            </div>
            <div class="se_select_row">
            	<span class="se_select_title">选体型</span>
            	<form> 
                    <a class="btn-select"> 
                        <span class="cur-select" id="t_xf_selected">请选择</span> 
                        <select id="type-xf-select"> 
                        	<option>请选择</option> 
                           
                        </select> 
                    </a> 
                </form>
            </div>
        </div>
        <div class="col_line"></div>
        <div class="se_b_right">
        	<span>扫描二维码（西服）</span>
            <div class="erwm" data-id="xf"><img src="<%=path%>/resources/public/images/garment/erwm.png" width="100%"></div>
        </div>
    </div>
    <div class="se_selectBox">
    	<div class="se_b_left">
        	<div class="se_select_row">
            	<span class="se_select_title">西裤品类</span>
            	<form> 
                    <a class="btn-select"> 
                        <span class="cur-select" id="s_c_xk">请扫描二维码</span> 
                    </a> 
                </form>
            </div>
            <div class="se_select_row">
            	<span class="se_select_title">选尺码</span>
            	<form> 
                    <a class="btn-select"> 
                        <span class="cur-select" id="s_xk_selected">请选择</span> 
                        <select id="size-xk-select" > 
                        	<option value="">请选择</option> 
                     
                        </select> 
                    </a> 
                </form>
            </div>
            <div class="se_select_row">
            	<span class="se_select_title">选体型</span>
            	<form> 
                    <a class="btn-select"> 
                        <span class="cur-select" id="t_xk_selected">请选择</span> 
                        <select id="type-xk-select"> 
                        	<option>请选择</option> 
                           
                        </select> 
                    </a> 
                </form>
            </div>
        </div>
        <div class="col_line"></div>
        <div class="se_b_right">
        	<span>扫描二维码（西裤）</span>
            <div class="erwm" data-id="xk"><img src="<%=path%>/resources/public/images/garment/erwm.png" width="100%"></div>
        </div>
    </div>
    <div class="cy_entry">
    	<div class="cy_row">
			<div>肩宽</div>
			<div class="circle_btn_sub">－</div>
			<div class="cy_data_box" id="jk" max="3" min="3"></div>
			<div class="circle_btn_add">＋</div>
			<div>cm</div>
		</div>
		<div class="cy_row">
			<div>胸围</div>
			<div class="circle_btn_sub">－</div>
			<div class="cy_data_box" id="xw" max="2" min="2"></div>
			<div class="circle_btn_add">＋</div>
			<div>cm</div>
		</div>
		<div class="cy_row">
			<div>腹围</div>
			<div class="circle_btn_sub">－</div>
			<div class="cy_data_box" id="fw" max="4" min="4"></div>
			<div class="circle_btn_add">＋</div>
			<div>cm</div>
		</div>
		<div class="cy_row">
			<div>袖肥</div>
			<div class="circle_btn_sub">－</div>
			<div class="cy_data_box" id="sbw" max="3" min="2"></div>
			<div class="circle_btn_add">＋</div>
			<div>cm</div>
		</div>
		<div class="cy_row">
			<div>后衣长</div>
			<div class="circle_btn_sub">－</div>
			<div class="cy_data_box" id="hyc" max="4" min="4"></div>
			<div class="circle_btn_add">＋</div>
			<div>cm</div>
		</div>
		<div class="cy_row">
			<div>左袖长</div>
			<div class="circle_btn_sub">－</div>
			<div class="cy_data_box" id="zxc" max="4" min="4"></div>
			<div class="circle_btn_add">＋</div>
			<div>cm</div>
		</div>
		<div class="cy_row">
			<div>右袖长</div>
			<div class="circle_btn_sub">－</div>
			<div class="cy_data_box" id="yxc" max="4" min="4"></div>
			<div class="circle_btn_add">＋</div>
			<div>cm</div>
		</div>
		<div class="cy_row">
			<div>腰围</div>
			<div class="circle_btn_sub">－</div>
			<div class="cy_data_box" id="kyw" max="4" min="4"></div>
			<div class="circle_btn_add">＋</div>
			<div>cm</div>
		</div>
		<div class="cy_row">
			<div>臀围</div>
			<div class="circle_btn_sub">－</div>
			<div class="cy_data_box" id="tw" max="2" min="2"></div>
			<div class="circle_btn_add">＋</div>
			<div>cm</div>
		</div>
		<div class="cy_row">
			<div>横档</div>
			<div class="circle_btn_sub">－</div>
			<div class="cy_data_box" id="hd" max="3" min="3"></div>
			<div class="circle_btn_add">＋</div>
			<div>cm</div>
		</div>
		<div class="cy_row">
			<div>脚口</div>
			<div class="circle_btn_sub">－</div>
			<div class="cy_data_box" id="jiaok" max="5" min="5"></div>
			<div class="circle_btn_add">＋</div>
			<div>cm</div>
		</div>
		<div class="cy_row">
			<div>左裤长</div>
			<div class="circle_btn_sub">－</div>
			<div class="cy_data_box" id="zkc" max="10" min="10"></div>
			<div class="circle_btn_add">＋</div>
			<div>cm</div>
		</div>
		<div class="cy_row">
			<div>右裤长</div>
			<div class="circle_btn_sub">－</div>
			<div class="cy_data_box" id="ykc" max="10" min="10"></div>
			<div class="circle_btn_add">＋</div>
			<div>cm</div>
		</div>
    </div>
    <div id="confirm-btn" class="confirm-btn">下一步</div>
</div>
<div class="se_select" id="se_lt">
    <div class="lt_entry">
        <div class="lt_row">
        	<span>&nbsp;身高</span>
            <div class="lt_data_box">
                <input name="sg" type="number">
            </div>
            <span>cm</span>
        </div>
        
        <div class="lt_row">
        	<span>&nbsp;胸围</span>
            <div class="lt_data_box">
                <input type="number" name="xw">
            </div>
            <span>cm</span>
        </div>
        <div class="lt_row">
        	<span>中腰围</span>
            <div class="lt_data_box">
                <input type="number" name="zyw">
            </div>
            <span>cm</span>
        </div>
        <div class="lt_row">
        	<span>后衣长</span>
            <div class="lt_data_box" >
                <input type="number" name="hyc">
            </div>
            <span>cm</span>
        </div>
        <div class="lt_row">
        	<span>&nbsp;臀围</span>
            <div class="lt_data_box">
                <input type="number" name="tw">
            </div>
            <span>cm</span>
        </div>
        <div class="lt_row">
        	<span>左裤长</span>
            <div class="lt_data_box">
                <input type="number" name="zkc">
            </div>
            <span>cm</span>
        </div>
        <div class="lt_row">
        	<span>右裤长</span>
            <div class="lt_data_box">
                <input type="number" name="ykc">
            </div>
            <span>cm</span>
        </div>
        <div class="lt_row">
        	<span>左肩型</span>
            <div class="lt_data_box">
                    <a class="no_border btn-select"> 
                        <span class="cur-select no-pin">正常肩</span> 
                        <select name="zjx"> 
                           	<option value="1" selected="">正常肩</option>
						    <option value="51">轻微溜肩</option>
						    <option value="2">中度溜肩</option>
						    <option value="31">严重溜肩</option>
						    <option value="45">溜肩1.5</option>
						    <option value="46">溜肩2.0</option>
						    <option value="54">轻微耸肩</option>
						    <option value="4">中度耸肩</option>
						    <option value="34">严重耸肩</option>
                        </select> 
                    </a> 
            </div>
            <span class="pin" >cm</span>
        </div>
        <div class="lt_row">
        	<span>右肩型</span>
            <div class="lt_data_box">
                    <a class="no_border btn-select"> 
                        <span class="cur-select no-pin">正常肩</span> 
                        <select name="yjx"> 
                           	<option value="6" selected="">正常肩</option>
						    <option value="52">轻微溜肩</option>
						    <option value="7">中度溜肩</option>
						    <option value="32">严重溜肩</option>
						    <option value="47">溜肩1.5</option>
						    <option value="48">溜肩2.0</option>
						    <option value="53">轻微耸肩</option>
						    <option value="9">中度耸肩</option>
						    <option value="33">严重耸肩</option>
                        </select> 
                    </a> 
            </div>
            <span class="pin" >cm</span>
        </div>
        <div class="lt_row">
        	<span>&nbsp;肚型</span>
            <div class="lt_data_box">
                    <a class="no_border btn-select"> 
                        <span class="cur-select no-pin">正常体</span> 
                        <select name="dx"> 
                            <option value="15" selected="">正常体</option>
				    		<option value="16" >凸肚体</option>
                        </select> 
                    </a> 
            </div>
            <span class="pin" >cm</span>
        </div>
        <div class="lt_row">
        	<span>&nbsp;臂型</span>
            <div class="lt_data_box">
                    <a class="no_border btn-select"> 
                        <span class="cur-select no-pin">手臂正常</span> 
                        <select name="sx"> 
                           	<option value="17">手臂靠前</option>
						    <option value="18">轻微手臂靠后</option>
						    <option value="19" selected="">手臂正常</option>
						    <option value="84">严重手臂靠后</option>
                        </select> 
                    </a> 
            </div>
            <span class="pin" >cm</span>
        </div>
        <div class="lt_row">
        	<span>&nbsp;臀型</span>
            <div class="lt_data_box">
                    <a class="no_border btn-select"> 
                        <span class="cur-select no-pin">正常臀</span> 
                        <select name="tx"> 
                           	<option value="20" selected="">正常臀</option>
						    <option value="21">平臀</option>
						    <option value="23">凸臀</option>
						    <option value="25">坠臀</option>
                        </select> 
                    </a> 
            </div>
            <span class="pin" >cm</span>
        </div>
    </div>
    <div id="submit-btn" class="confirm-btn">提交</div>
</div>

<script>
var data = {};
var s_data = [];//带尺码的成衣数据
var t_data = {};//成衣数据
$(function(){
	$("#size-xf-select").change(function(){
		if (!!data["xf"]) {
			$("#t_xf_selected").html("请选择");
			var t_html = "<option value=\"\">请选择</option>";
			for(var i=0;i<data["xf"].length;i++){
				if(data["xf"][i].eurCode == $("#size-xf-select").val()){
					s_data = data["xf"][i].data;
					for(var j=0;j<s_data.length;j++){
						t_html = t_html + "<option  value='"+s_data[j].bodyType+"'>"+s_data[j].bodyType+"</option>";
					}
				}
			}
			$("#type-xf-select").html(t_html);
		}
	});
	$("#size-xk-select").change(function(){
		if (!!data["xk"]) {
			$("#t_xk_selected").html("请选择");
			var t_html = "<option value=\"\">请选择</option>";
			for(var i=0;i<data["xk"].length;i++){
				if(data["xk"][i].eurCode == $("#size-xk-select").val()){
					s_data = data["xk"][i].data;
					for(var j=0;j<s_data.length;j++){
						t_html = t_html + "<option  value='"+s_data[j].bodyType+"'>"+s_data[j].bodyType+"</option>";
					}
				}
			}
			$("#type-xk-select").html(t_html);
		}
	});
	$("#type-xf-select").change(function(){
		if (!!s_data) {
			for(var i=0;i<s_data.length;i++){
				if(s_data[i].bodyType == $("#type-xf-select").val()){
					t_data=s_data[i];
					for(var key in t_data){
						if(key != "bodyType" && key != "tw"){
							$("#"+key).text(t_data[key]);
						}
					}
				}
			}
		}
	});
	$("#type-xk-select").change(function(){
		if (!!s_data) {
			for(var i=0;i<s_data.length;i++){
				if(s_data[i].bodyType == $("#type-xk-select").val()){
					t_data=s_data[i];
					for(var key in t_data){
						if(key != "bodyType"){
							$("#"+key).text(t_data[key]);
						}
					}
				}
			}
		}
	});
	wx.ready(function(){
	});
	wx.error(function (res) {
		if(res.errMsg=="config:invalid signature"){
			window.location.href = '<%=path%>/tailor/measurers/garmentSize';
		}
			alert(res.errMsg);
	});
	//扫描二维码
	$(".erwm").click(function(){
		var c = $(this).data('id');
		wx.scanQRCode({
			desc: 'scanQRCode desc',
			needResult: 1, 
			scanType: ["qrCode","barCode"], 
			success: function (res) {
				callback(res);
			}
		});
		//callback({
		//	"resultStr" : '/8a248693529d22dc0152ee20e88504a4-'
		//});
		function callback(res){
			var result = res.resultStr;
			var id = result.substring(result.lastIndexOf('/')+1,result.indexOf('-'));
			var url = '<%=path%>/api/v1/public/standardinfo/'+id+'/'+convertCategoryId(c);
			data[c] = (function(){
				var result;
				$.ajax({
					async:false, 
					type:'GET',
					url:url,
					dataType: 'json', 
					success:function(data){
					//	alert(JSON.stringify(data));
							$("#s_c_"+c).html(data.returnData.sizeName);
							var s_html = "<option value=\"\">请选择</option>";
							
							result = data.returnData.sizeDetail;
							for(var i=0;i<result.length;i++){
								s_html = s_html + "<option  value='"+result[i].eurCode+"'>"+result[i].eurCode+"</option>"
							}
							$("#s_"+c+"_selected").html("请选择");
							$("#t_"+c+"_selected").html("请选择");
							$("#type-"+c+"-select").html("<option value=\"\">请选择</option>");
							$("#size-"+c+"-select").html(s_html);
							
					},
					error:function(){
						alert("请扫描正确品类下的商品信息二维码");
					}
				})
				return result;
			})();
		}
	});
	$("#confirm-btn").click(function(){
		$('.se_nav li').removeClass('se_nav_selected');
		$('[check-id=se_lt]').addClass('se_nav_selected');
		$('.se_select').removeClass('se_select_view');
		$('#se_lt').addClass('se_select_view');
	});
    $("#submit-btn").bind('click',function(){
        var input = {};
        input.bodyMeasurementPersonId = '${measureId}';

        input.measurerName = $("#user_name").val();
        input.phoneNo = $("#user_phone").val().split('-').join('');
        input.categoryId = "";
        input.bodySize = $("#size-xf-select").val();
        input.bodyType = $("#type-select").val();
        var cybw = {};//特体数据
        $(".cy_data_box").each(function(){
        	var key = $(this).attr("id");
        	cybw[key] = $(this).text();
        });
        input.cybw = cybw; 
        var jtbw = {};//净体数据
        $(".lt_entry input[type='number']").each(function(){
			var attrName= $(this).attr('name');
			var value = $(this).val();
			jtbw[attrName] = value;
		});      
        input.jtbw = jtbw;
        var ttxx = {};//特体数据
        $(".lt_entry select").each(function(){
			var attrName= $(this).attr('name');
			var value = $(this).val();
			ttxx[attrName] = value;
		});    
        input.ttxx = ttxx;
        if(checkSubmit(input)){
        	$(this).unbind('click');//只允许提交一次
	        $.ajax({
	        	type:'POST',
	        	url:'<%=path%>/api/v2/public/measures/standardSize/rev',
	        	contentType:'application/json',
	        	dataType:'json',
	        	data:JSON.stringify(input),
	        	success:function(data){
	        		var infoId = data.returnData.measureId;
	        		window.location.href = "<%=path%>/api/v1/public/measurers/measureDetailView/"+infoId;
	        	}
	        });
        }
    });

	//tab 标签切换样式
	$('.se_nav li').bind('click', function(){
		$('.se_nav li').removeClass('se_nav_selected');
		$(this).addClass('se_nav_selected');
		$('.se_select').removeClass('se_select_view');
		$('#'+$(this).attr('check-id')).addClass('se_select_view');
	});
	//选品类
	$('.btn-select').bind('click',function(){
		var oselect = $(this).find('select').eq(0);
		var ospan = $(this).find('span').eq(0);
		oselect.change(function(){
			ospan.html(oselect.find("option:selected").text());
		});	
	});
	//手机号样式
	var pureTel = "";
	$("#user_phone").on('input', function(e){
		pureTel = this.value.split('-').join('');
		if (pureTel.length > 7) {
			var s1 = pureTel.substr(0, 3);
			var s2 = pureTel.substr(3, 4);
			var s3 = pureTel.substr(7, pureTel.length - 7);
			var formattedTel = s1 + '-' + s2 + '-' + s3;
			this.value = formattedTel;
		} else {
			if (pureTel.length > 3) {
			var s1 = pureTel.substr(0, 3);
			var s2 = pureTel.substr(3, pureTel.length - 3);
			var formattedTel = s1 + '-' + s2;
			this.value = formattedTel;
			} else {
				//do nothing
			}
		}
	});
	//向下修改标准码
	$(".circle_btn_sub").click(function(){
		var id = $(this).parent().find(".cy_data_box").attr("id");
		var init = t_data[id];//初始值
		var value = $(this).parent().find(".cy_data_box").text();
		if(!!value){
			$(this).parent().find(".cy_data_box").css("color","#000");
			var min = Number.parseFloat(init) - Number.parseFloat($(this).parent().find(".cy_data_box").attr("min"));//区域最小值
			value = Number.parseFloat(value) - Number.parseFloat(0.5);
			if(value <= min){
				value = min;
			}
			$(this).parent().find(".cy_data_box").text(value);
		}
	});
	//向上修改标准码
	$(".circle_btn_add").click(function(){
		var id = $(this).parent().find(".cy_data_box").attr("id");
		var	init = t_data[id];
		var value = $(this).parent().find(".cy_data_box").text();
		if(!!value){
			$(this).parent().find(".cy_data_box").css("color","#000");
			var max = Number.parseFloat(init) + Number.parseFloat($(this).parent().find(".cy_data_box").attr("max"));//区域最大值
			value = Number.parseFloat(value) + Number.parseFloat(0.5);
			if(value >= max){
				value = max;
			}
			$(this).parent().find(".cy_data_box").text(value);
		}
	});
	
});
function checkSubmit(input){
	if(input.measurerName == "" || input.phoneNo == ""){
		return false;
	}
	if(input.bodySize == "" || input.bodyType == ""){
		return false;
	}
	for(var key in input.jtbw){
		if(input.jtbw[key] == ""){
			return false;
		}
	}
	return true;
}
function eNameToCName(ename){
	switch(ename){
	case "xw":return "　　胸围";
	case "fw":return "　　腹围";
	case "tw":return "　　臀围";
	case "jk":return "　　肩宽";
	case "zxc":return "　左袖长";
	case "yxc":return "　右袖长";
	case "hyc":return "　后衣长";
	case "sbw":return "　　袖肥";
	case "zsww":return "左手腕围";
	case "ysww":return "右手腕围";
	case "ql":return "　　前浪";
	case "hl":return "　　后浪";
	case "kyw":return "　　腰围";
	case "hd":return "　　横裆";
	case "td":return "　　通裆";
	case "jiaok":return "　　脚口";
	case "zkc":return "　左裤长";
	case "ykc":return "　右裤长";

	}
}
//转换品类Id
function convertCategoryId(category){
	if(category == 'xf') return 'MXF';
	if(category == 'xk') return 'MXK';
};
</script>
</body>
</html>
