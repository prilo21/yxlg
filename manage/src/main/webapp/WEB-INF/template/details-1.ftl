<!doctype html>
<html>
<head>
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
<title>详情页</title>
<link href="https://source.magicmanufactory.com/@/resources/template/details-1.css" rel="stylesheet">
<script src="https://source.magicmanufactory.com//resources/template/jquery/2.1.4/jquery.min.js"></script>
<script src="https://source.magicmanufactory.com/@/resources/template/jquery.lazyload.js" language="javascript"></script>
<script src="https://source.magicmanufactory.com/@/resources/jquery/mobile/jquery.mobile-1.4.5.min.js"></script>
<script type="text/javascript" src="https://source.magicmanufactory.com/@/resources/template/underscore.js"></script>
<script type="text/javascript" src="https://source.magicmanufactory.com/@/resources/template/pinchzoom.js"></script>
<script type="text/javascript" src="https://source.magicmanufactory.com/@/template/common.js"></script>

</head>

<body>
<div class="d_contain">
    <div class="clothes">
    	<div class="clo_lb">
        <!--轮播图后台动态加载-->
            <ul id="ulId1">
              <#list goods.showImgList as showImg>
                <li><img src="${showImg}"></li>
              </#list>
            </ul>
            <p id="showImg" id="exp_emo0_page" class="pNumCon">
              <#list goods.showImgList as a>  
                  <a class="pNum" href="javascript:;" data-index='${a_index}'></a> 
              </#list> 
            </p>
        </div>
        <!--产品名称-->
    	<div class="clo_name">
        	<h1>${goods.goodsNameEn}</h1>
            <h2>${goods.goodsName}</h2>
        </div>
        <!--指数圆圈后台上传-->
        <div class="zhishu" >
          <#--
        	<div class="zhishu1 zs_chunxia"></div>
            <div class="zhishu2 zs_weitan"></div>
            <div class="zhishu3 zs_tingkuo"></div>
            <div class="zhishu4 zs_ketieshen"></div>
            <div class="zhishu5 zs_hshizhong"></div>
          -->
          <#list goods.applySeasonImgList as season>
              <div style=" float:left; width:12%; height:70px; margin-left:8%;  font-size:0.2rem;background:url(${season}) no-repeat;background-size:contain;"></div>
          </#list>
        </div>
        <!--热销推荐产品后台动态加载-->
        <div class="rexiao">
        	<h2>热销推荐</h2>
            <div width="100%">
                <#list goods.recommendGoods as recommend>
                	<div>
                    	<img class="lazy" src="images/loading.gif" data-original="${recommend.goodsImg}" width="100%" data-goodsid="${recommend.goodsUid}">
                        <p>${recommend.goodsNameEn}<br>${recommend.goodsName}<br>￥${recommend.goodsPrice}</p>
                    </div>
                </#list>
            </div>
        </div>
       <!--模特展示和细节展示图片是后台动态加载，模特展示与上面轮播图对应相同-->
        <div class="model_dis">
        	<h1>Model display</h1>
            <h2>模特展示</h2>
        </div>
        <#list goods.positionImgList as positionImg>
            <div class="pinch-zoom"><img class="lazy" src="images/loading.gif" data-original="${positionImg}"></div>
        </#list>
        <div class="detail_dis">
        	<h1>Detail display</h1>
            <h2>细节展示</h2>
        </div>
        <#list goods.detailImgList as detail>
            <div class="pinch-zoom"><img class="lazy" src="images/loading.gif" data-original="${detail}"></div>
        </#list>
        <!--面料的轮播，图片是动态加载的-->
        <div class="ml_lb">
        	<ul id="ulId2">
        	    <#list goods.materialImgList as material>
                    <li><img src="${material}"></li>
                </#list>
            </ul>
            <p id="materailImg" id="exp_emo0_page" class="pNumCon">
                <#list goods.materialImgList as b>
                    <a class="pNum" href="javascript:;" data-index='${b_index}'></a>
                </#list>
            </p>
        </div>
        <div class="design_inter">
        	<h1>Design interpretation</h1>
            <h2>设计解读</h2>
        </div>
        <div class="introduce">${goods.goodsDescription}</div>
        <img class="lazy" src="images/loading.gif" data-original="${goods.semiFullImg}" width="100%">
        <div class="size_table">
        	<h1>Size table</h1>
            <h2>尺码表</h2>
        </div>
        <!--尺码表和版式图片后台上传-->
        <img class="lazy chimabiao" src="images/loading.gif" data-original="${goods.sizeImg}" width="100%">
        <img class="lazy banshi" src="images/loading.gif" data-original="${goods.measureImg}" width="100%">
    </div>
    <div class="footer">
        <!--以下图片固定不变-->
        <img class="lazy" src="images/loading.gif" data-original="https://source.magicmanufactory.com/@/resources/template/del_bot04.jpg" width="100%">
        <img class="lazy" src="images/loading.gif" data-original="https://source.magicmanufactory.com/@/resources/template/del_bot05.jpg" width="100%">
        <img class="lazy" src="images/loading.gif" data-original="https://source.magicmanufactory.com/@/resources/template/del_bot06.jpg" width="100%">
        <img class="lazy" src="images/loading.gif" data-original="https://source.magicmanufactory.com/@/resources/template/del_bot07.jpg" width="100%">
        <img class="lazy" src="images/loading.gif" data-original="https://source.magicmanufactory.com/@/resources/template/del_bot08.jpg" width="100%">
    </div>
    <a onclick="pageScroll()" class="d_up"><img src="https://source.magicmanufactory.com/@/resources/template/up.png" width="100%"></a>

</div>
<script>
function submitJSON(messageToPost){
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

$(function(){

     $("#showImg").find("a").eq(0).addClass("on");
     $("#materailImg").find("a").eq(0).addClass("on");
     
		$('#ulId1 li').on("swipeleft",function(){
			var a = $('.pNum.ui-link.on').attr('data-index'); 
			if(a!=3){  
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
		$('#ulId2 li').on("swipeleft",function(){
			var a = $('.pNum.ui-link.on').attr('data-index'); 
			if(a!=6){ 
				var nextLi=$('.pNum.ui-link.on').next(); 
				$('.pNum.ui-link.on').removeClass('on');  
				nextLi.addClass('on');  
				a=a*1+1; 
				var b = (a-1)*100; 
				var left ='-'+b+'%';
				$('#ulId2').animate({marginLeft:left});
			}
		});
		$('#ulId2 li').on("swiperight",function(){
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
				$('#ulId2').animate({marginLeft:left});
			}
		});
		
	});
	function pageScroll(){ 
	window.scrollBy(0,-100); 
	scrolldelay = setTimeout('pageScroll()',10); 
	var sTop=document.documentElement.scrollTop+document.body.scrollTop; 
	if(sTop==0) clearTimeout(scrolldelay); 
	} 
</script>
</body>
</html>
