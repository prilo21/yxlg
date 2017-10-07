/**
//jquery ajax 默认选项设置
(function($){
	$._ajax_ = $.ajax;
	$.ajax = function(url,options){
		if(typeof url==='object'){
			options = url;
			url = undefined;
		}
		var setting = {
			type: "POST",
			dataType:'json',
			complete:function(){
				alert(1);
			}
		};
		$.extend(setting,options||{});

		var error = setting.error;
		if(error){
			setting.error = function(request, textStatus, errorThrown){
				try{
					showTip(_s_i18n['info.ajax.request.error']);
				}catch(e){};

				error(request, textStatus, errorThrown);
			};
		}else{
			setting.error=function(request, textStatus, errorThrown){alert(9)
				try{
					showTip(_s_i18n['info.ajax.request.error']);
				}catch(e){};
			}
		}
		$._ajax_(url,setting);
	};
})(jQuery);

//easyui 默认设置
(function($){
	//表单提交加默认出错事件处理器
	$.fn.form._submit = $.fn.form.submit;
	$.fn.form.submit = function(jq, options){
		var setting = {};
		$.extend(setting,options||{});
		if(setting.onLoadError){
			var _onLoadError = setting.onLoadError;
			setting.onLoadError = function(){
				try{
					showTip(_s_i18n['info.ajax.request.error']);
				}catch(e){}
				_onLoadError();
			}
		}else{
			setting.onLoadError = function(){
				try{
					showTip(_s_i18n['info.ajax.request.error']);
				}catch(e){}
			};
		}
		$.fn.form._submit(jq, setting);
	};

	//为树增加取得所有选中状态和中间状态的结点
	$.fn.tree.methods.getXX = function(jq){
		var $this = this;
		var rst = [];
		jq.find('span.tree-checkbox2').each(function(){
			var n = $this.getNode(jq,$(this).parent());
			rst.push(n);
		});
		rst = rst.concat($this.getChecked(jq));
		return rst;
	};
	//为treegrid增加查询辅助方法
	$.fn.treegrid.methods._testTreeNode = function (data,reg,rst){
		var name = data['name']||"";
		if(reg.test(name)){
			rst.push(data);
		}
		var children = data['children'];
		if(children&&children.length>0){
			for(var i=0;i<children.length;i++){
				arguments.callee(children[i],reg,rst);
			}
		}
	}
	//为treegrid增加查询方法
	$.fn.treegrid.methods.search = function(jq,name){
		var jqbody = $(jq.parents('.datagrid-view')[0]).find('.datagrid-body');
		jqbody.find('.datagrid-row-query_result').removeClass('datagrid-row-query_result');
		var $this = $.fn.treegrid.methods;
		var queryName = name||"";
		queryName = queryName.replace(/(\s|　)+/g,' ').trim().replace(/ /g,'|');
		if(queryName.indexOf('|')>-1){
			queryName = '.*('+queryName+').*';
		}else{
			queryName = '.*'+queryName+'.*';
		}

		var query = new RegExp(queryName,'ig');

		var datas = $this.getData(jq);
		var requireds = [];
		if(datas){
			for(var i=0;i<datas.length;i++){
				$this._testTreeNode(datas[i],query,requireds);
			}
		}

		$this.collapseAll(jq);
		for(var i=0;i<requireds.length;i++){
			var id = requireds[i]['id'];
			$this.expandTo(jq,id);
			var jqbody = $(jq.parents('.datagrid-view')[0]).find('.datagrid-body');
			jqbody.find('tr[node-id='+id+']').addClass('datagrid-row-query_result');
		}
	};
})(jQuery);


**/

//应用程序布局管理
(function($){
	var Utils = {//工具方法
		//解析数据选项
		getDataOptions:function(obj){
			var dos = obj.attr('data-options');
			if(dos){
				eval('dos={'+dos+'}');
			}
			return dos||{};
		},
		//解析数值
		parseNumber:function(str){
			str+=""
			str = str.replace('px','').replace('auto','');//.trim();
			if(isNaN(str))return 0;
			return str-0;
		},
		//取容器外部高度,包含margin,padding,
		getOuterHeight:function(obj){
			var $this = $(obj);
			var mt = $this.css('margin-top');
			var mb = $this.css('margin-bottom');
			var m = this.parseNumber(mt)+this.parseNumber(mb);
			return $this.innerHeight()+m;
		},
		//取容器内部高度,除去margin,padding,
		getInnerHeight:function(obj){
			var $this = $(obj);
			var pt = $this.css('padding-top');
			var pb = $this.css('padding-bottom');
			var p = this.parseNumber(pt)+this.parseNumber(pb);
			return $this.innerHeight()-p;
		},
		//取容器外部宽度,包含margin,padding,
		getOuterWidth:function(obj){
			var $this = $(obj);
			var ml = $this.css('margin-left');
			var mr = $this.css('margin-right');
			var m = this.parseNumber(ml)+this.parseNumber(mr);
			return $this.innerWidth()+m;
		},
		//取容器内部宽度,除去margin,padding,
		getInnerWidth:function(obj){
			var $this = $(obj);
			var pl = $this.css('padding-left');
			var pr = $this.css('padding-right');
			var p = this.parseNumber(pl)+this.parseNumber(pr);
			if($this.hasClass('_s_scrollctn')){
			//	p+=20;
			}
			return $this.innerWidth()-p;
		},
		//取容器内部尺寸,除去margin,padding,
		getInnerSize:function(obj){
			var w = this.getInnerWidth(obj);
			var h = this.getInnerHeight(obj);
			return {width:w,height:h};
		},
		//取容器外部尺寸,包含margin,padding,
		getOuterSize:function(obj){
			var w = this.getOuterWidth(obj);
			var h = this.getOuterHeight(obj);
			return {width:w,height:h};
		},
		//设置窗口高度,除去margin,padding
		setHeight:function(obj,h){
			var $this = $(obj);
			var $ithis = $this.find('>._s_layout_inner');
			if($ithis.length>0){
				//$this = $ithis;
			}
			var mt = $this.css('margin-top');
			var mb = $this.css('margin-bottom');
			var pt = $this.css('padding-top');
			var pb = $this.css('padding-bottom');
			var m = this.parseNumber(mt)
					+this.parseNumber(mb)
					+this.parseNumber(pt)
					+this.parseNumber(pb);
			$this.height(h-m);

		},
		//设置窗口宽度,除去margin,padding
		setWidth:function(obj,w){
			var $this = $(obj);
			var $ithis = $this.find('>._s_layout_inner');
			if($ithis.length>0){
				//$this = $ithis;
			}
			var mt = $this.css('margin-left');
			var mb = $this.css('margin-right');
			var pt = $this.css('padding-left');
			var pb = $this.css('padding-right');
			var m = this.parseNumber(mt)
					+this.parseNumber(mb)
					+this.parseNumber(pt)
					+this.parseNumber(pb);
			$this.width(w-m)
		},
		//设置窗口尺寸,除去margin,padding
		setSize:function(obj,size){
			this.setHeight(obj, size.height);
			this.setWidth(obj, size.width);
		}
	};
	//取兄弟节点的总尺寸
	function getSiblingsSize(node){
		var rst = {width:0,height:0};
		var siblings = node.siblings(':visible');
		siblings.each(function(){
			var $this = $(this);
			var size = Utils.getOuterSize($this);
			rst.width += size.width;
			rst.height += size.height;
		});
		siblings=null;
		return rst;
	}
	//计算页面的大小
	function calculatePageSize(){
		var winH = $(window).height();
		var winW = $(window).width();
		Utils.setSize($('._s_page'), {'width':winW,'height':winH});
		setInnerDivSize($('._s_page'));
	}

	//重新布局Easyui组件
	function resizeEasyUiComponent(ctx){
		try{
			ctx.find('._s_datagrid').each(function(){
				var $this = $(this);
				var p  = $this.parents('._s_autosizectn');
				p = $(p[0]);
				var pisize = Utils.getInnerSize(p.find('>._s_layout_inner'));
				$this.datagrid('resize',pisize);

			});
			ctx.find('._s_tabs').each(function(){
				var $this = $(this);
				$this.tabs('resize');
			});
			ctx.find('._s_xmenu').each(function(){
				var $this = $(this);
				$this.xmenu('resize');
			});
		}catch(e){}
	}

	//计算布局项
	function calculateLayoutItemSize(layoutItem,autoSizeStack){
		var $this = $(layoutItem);
		var p = $this.parent();
		var pp = p.parent();
		var psize = Utils.getInnerSize(p);
		var dataOptions = Utils.getDataOptions($this);
		if(pp.hasClass('_s_layout_hsplit')){
			var cw = dataOptions.width;
			if(!!!cw){
				autoSizeStack.push($this);
				return;
			}
			if(cw<=1){
				cw = cw*psize.width;
			}
			Utils.setSize($this, {width:Math.floor(cw),height:psize.height});
		}else if(pp.hasClass('_s_layout_vsplit')){
			var ch = dataOptions.height;
			if(!!!ch){
				autoSizeStack.push($this);
				return;
			}
			if(ch<=1){
				ch = psize.height*ch;
			}

			Utils.setSize($this, {width:psize.width,height:Math.floor(ch)});
		}
		setInnerDivSize($this);
	}

	//设置布局组件的尺寸
	function lay(obj,autoSizeStack){
		var $this = $(obj);
		if($this.data('layout')===true)return;
		$this.data('layout',true);
		var p = $this.parent();
		var pp = p.parent();
		var psize = Utils.getInnerSize(p);
		if($this.hasClass('_s_layout_hsplit')){
			if(pp.hasClass('_s_scrollctn')){
				//Utils.setWidth($this,psize.width-20);
				Utils.setSize($this, {width:psize.width-20,height:psize.height});
			}else{
				Utils.setSize($this, psize);
			}
			setInnerDivSize($this);
		}else if($this.hasClass('_s_layout_vsplit')){
			if(pp.hasClass('_s_scrollctn')){
				//Utils.setWidth($this,psize.width-20);
				Utils.setSize($this, {width:psize.width-20,height:psize.height});
			}else{
				if(p.hasClass('_s_tabs_item')){
					//alert(Utils.getInnerSize(p).height)
				}
				Utils.setSize($this, psize);
			}
			setInnerDivSize($this);
		}else if($this.hasClass('_s_layout_item')){
			calculateLayoutItemSize($this,autoSizeStack);
		}else if($this.hasClass('_s_autosizectn')){
			$this.data('layout',false);
			autoSizeStack.push($this[0]);
		}else if($this.hasClass('_s_scrollctn')){
			Utils.setSize($this, psize);
			setInnerDivSize($this);
		}else if($this.hasClass('_s_fieldset')){
			$this.data('layout',false);
			calulateFieldsetSize($this)
		}else if($this.hasClass('_s_tabs')){
			$this.data('layout',false);
			calutateTabsSize($this);
		}
	}
	//设置tabs页尺寸
	function calutateTabsSize(tab){
		var $this = $(tab);
		if($this.data('layout')===true)return;
		$this.data('layout',true);
		var p  = $this.parent();
		var size = Utils.getInnerSize(p);
		Utils.setSize($this, size);
		var items = $this.find('._s_tabs_item');
		items.each(function(){
			var $ithis = $(this);
			Utils.setSize($ithis,{width:size.width-12,height:size.height-41})
		});

	}
	//设置fieldset组件的尺寸
	function calulateFieldsetSize(fieldset){
		var $this = $(fieldset);
		if($this.data('layout')===true)return;
		$this.data('layout',true);

		var p = $this.parent();
		var psize = Utils.getInnerSize(p);
		Utils.setSize($this,psize);
		var cc = $this.find('.cc');
		Utils.setSize(cc,{width:psize.width-20,height:psize.height-40});
		$this.find('>div>.tc,>div>.bc').each(function(){
			Utils.setWidth($(this),psize.width-20);
		});
		$this.find('>div>.lc,>div>.rc').each(function(){
			Utils.setHeight($(this),psize.height-40);
		});
		setInnerDivSize(cc);
	}
	//设置内层div尺寸
	function setInnerDivSize(div){
		var $this = $(div);
		var inner = $this.find('>._s_layout_inner');
		var size = Utils.getInnerSize($this);

		Utils.setSize(inner,size);


	}
	//设置自适应div的尺寸
	function autol(autoSizeStack){
		if(autoSizeStack.length>0){
			for(var i=0;i<autoSizeStack.length;i++){
				var node = $(autoSizeStack[i]);
				var p = node.parent();
				var pp = p.parent();
				var psize = Utils.getInnerSize(p);
				var pW = psize.width;
				var pH = psize.height;

				var size = getSiblingsSize(node);
				Utils.setSize(node, {width:0,height:0});
				if(pp.hasClass('_s_layout_hsplit')){
					Utils.setSize(node, {width:pW-size.width-2,height:pH});
				}else if(pp.hasClass('_s_layout_vsplit')){
					Utils.setSize(node, {width:pW,height:pH-size.height});
				}else if(node.hasClass('_s_autosizectn')){
					Utils.setSize(node, {width:pW,height:pH-size.height});
				}
				setInnerDivSize(node);
			}
		}
	}
	//计算所有布局项
	function calculateLayoutSize(ctx){

		var autoSizeStack = [];
		var arr = ctx.find('._s_layout');
		arr.each(function(){
			var autoSizeStack = [];
			var $this = $(this);
			lay($this,autoSizeStack);
			autol(autoSizeStack);

			autoSizeStack = [];
			$this.siblings('._s_layout').each(function(){
				lay($(this),autoSizeStack);
			});
			autol(autoSizeStack);

			autoSizeStack = [];
			$this.find('>._s_layout').each(function(){
				lay($(this),autoSizeStack);
			});
			autol(autoSizeStack);
			autoSizeStack=null;
		});
		arr.each(function(){
			$(this).data('layout',false);
		});

	}

	//布局主函数
	function doLayout(){
		$('.page_loader_mask').show();
		calculatePageSize();
		calculateLayoutSize($('body'));
		resizeEasyUiComponent($('body'));
		$('.page_loader_mask').hide();
	}
	$(function(){
		//布局
		var winResizeTimer;
		$(window).resize(function(){
			if(winResizeTimer){
				clearTimeout(winResizeTimer);
			}
			winResizeTimer = setTimeout(doLayout,200);
		});
		$(window).resize();

		//统一事件
		$('._s_togglebutton').click(function(){
			var domId = '#'+$(this).attr('_domId');
			var jq = $(domId);
			var dsp = jq.css('display');
			if(dsp==='none'){
				jq.show();
			}else{
				jq.hide();
			}
			$(window).resize();
		});
	});
	//组件内部布局
	window.componentLayout = function (){//alert(9)
		calculateLayoutSize($(this));
		resizeEasyUiComponent($(this));
	};
})(jQuery);

(function($){
	$.fn.getJsonFormData = function(){
		var result = {};
		var fields = $(this).serializeArray();
		$.each(fields,function(i,field){
			var old = result[field.name];
			if(old){
				result[field.name] = old+','+field.value;
			}else{
				result[field.name] = field.value;
			}
		});
		return result;
	}
})(jQuery);
//系统提示窗口
(function(win){
	var tmpl = '<div><div class="_s_tipicon _iconflag_"></div>_msg_</div>';
	win.showTip = function(msg,isOk,title){
		var title;
		msg = tmpl.replace(/_msg_/g, msg);
		if(!!isOk){
			if(!title)
				title = _s_i18n['info.success'];
			msg = msg.replace(/_iconflag_/g, "_s_tipicon_ok");
		}else{
			if(!title)
				title = _s_i18n['info.error'];
			msg = msg.replace(/_iconflag_/g, "_s_tipicon_error");
		}
		$.messager.show({
			title:title,
			msg:msg,
			timeout:3000,
			showType:'slide'
		});
	};
})(window);





