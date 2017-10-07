<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../../../include/sys-common.jsp" />
<!DOCTYPE html >
<html>
  <head>
    <script type="text/javascript" src="public/easyui/plugins/jquery.datagrid.ex.js"></script>
    <script type="text/javascript" src="public/member/css/memberRelation.css"></script>
  	<script type="text/javascript" src="public/member/js/memberRelation.js"></script>
  	<script type="text/javascript">
  	
  	var phoneNo="";
  	
    //js方法：序列化表单 			
	function serializeForm(form) {
		var obj = {};
		$.each(form.serializeArray(), function(index) {
			if (obj[this['name']]) {
				obj[this['name']] = obj[this['name']] + ',' + this['value'];
			} else {
				obj[this['name']] = this['value'];
			}
		});
		return obj;
	}
  	
	//按回车查询 figo.liu 2015-07-09
	var isOpen=false;
	$(document).keydown(function(e){
		if(e.keyCode == 13) {
			if(isOpen==false){
				$('#searchbtn').click();
			}
		}
	});	
  
   $(document).ready(function(){ 
	   
	   $('#searchbtn').click(function() {
			
			phoneNo = $('#phoneNo').val();
			memberName = $('#memberName').val();
			memberType = $('#memberType').combobox('getValue');
			$('#dg').datagrid('load', serializeForm($('#mysearch')));  
			$('#dg').datagrid('unselectAll');
		});
	  	
	  	$('#clearbtn').click(function() {
	  		phoneNo = "";
	  		memberName = "";
			$('#mysearch').form('clear');
			$('#dg').datagrid('load', {});
			$('#dg').datagrid('unselectAll');
		});
	  	
	    	loadGrid();  
	    	$('#dlg').window({   
		          width:500,   
		          height:330,
		          modal: true,
		          minimizable : false,
		  		  top : 100,
		  		  left : 350,
		          resizable:false,
		          onOpen:function(){
		  			isOpen = true;
			  	  },
			  		onClose:function(){
			  			isOpen = false;
			  	  }
	          });
	    	$('#dlg').window("close");
		});
		
	function loadGrid()  {
	    	$('#dg').datagrid({
	        	nowrap:false,
	        	loadMsg:'加载中，请稍候...',
	        	fitColumns:true,
	        	pagination : true,//页码
	 		    pageNumber : 1,//初始页码
	 		    pageSize : 15,
	 		    pageList : [15,30,45,60],
	        	detailFormatter:function(index,row){
	            	return '<div style="padding:5px"><table id="ddv-' + index + '"></table></div>';
	        	},
	        	pagination:true,
	    	});
		};
	
	
	</script>
	<style>
	#mysearch{margin-bottom: 0;}
	</style>
	
  </head>
  
  <body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
	<div id="lay" class="easyui-layout" style="width: 100%; height: 100%">
	
		<div id="toolbar">
			<form id="mysearch" method="post">
				<div style="height: 5px;"></div>
				推荐会员的姓名: <input id="memberName" name="memberName" style="width: 100px;" />
				推荐会员类型:<select class="easyui-combobox" name="memberType" id="memberType">
							<option value="">全部</option>
							<option value="普通会员">普通会员</option>
							<option value="营销会员">营销会员</option>
							<option value="内部会员">内部会员</option>
							<option value="内部营销会员">内部营销会员</option>
						</select>
				推荐会员或者被推荐会员手机号: <input id="phoneNo" name="phoneNo" style="width: 100px;" /> 
				&nbsp; 被推荐会员姓名<input id="rMemberName" name="rMemberName" style="width: 100px;" /> 
				<a id="searchbtn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a> 
				<a id="clearbtn" class="easyui-linkbutton" data-options="iconCls:'icon-print'" style="width:80px">清空</a>
			</form>
	    </div> 
		<div region="center">
			<table id="dg" title="会员关系管理" style="width: 100%; height: 100%"
				data-options="striped:true,rownumbers:true,singleSelect:true,url:'memberRelation/findByDetachedCriteria',method:'post', 
       				 multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar',pagination:'true'">
				<thead>
					<tr>
						<th data-options="field: 'id', hidden:'true'">编号</th>
						<th data-options="field: 'member.memberName', width:'80'">推荐会员姓名</th>
						<th data-options="field: 'member.phoneNo', width:'80'">推荐会员手机号</th>
						<th data-options="field: 'member.memberType.memberType', width:'80'">推荐会员类型</th>
						<th data-options="field: 'member.memberId',  hidden:'true'">会员ID</th>
						<th data-options="field: 'memberName', width:'80'">被推荐会员姓名</th>
						<th data-options="field: 'phoneNo', width:'100'">被推荐会员手机号</th>
						<th data-options="field: 'createTime', width:'80'">推荐时间</th>
						<th data-options="field: 'bindingMode', width:'120'">绑定原因</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</body>
</html>