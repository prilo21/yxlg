<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../../../include/sys-common.jsp" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html >
<html>
  <head>
  	<style type="text/css">
        #ff{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            width:80px;
        }
        .fitem input{
            width:160px;
        }
    </style>
    
  	<script type="text/javascript">
  		var datagrid;
  		// 获取用户类型
        var userType;
		$(document).ready(function(){  
	    	loadGrid(); 
	    	$('#dlg').window({   
	          width:500,   
	          height:330,   
	          resizable:false,
	          onOpen:function(){
	  			isOpen = true;
	  		  },
		  	  onClose:function(){
		  			isOpen = false;
		  	  }
	          });
	    	$('#dlg').window("close");
/* 	    	$("#importDlg").window({
	    		width:700,   
	          	height:430,   
	          	resizable:false  
	    	});
	    	$('#importDlg').window("close"); */	    	
		});
	    	
		
		function loadGrid()  {
	    	datagrid=$('#dg').datagrid({
	    		striped:true,
	        	nowrap:false,
	        	method:'POST',
	        	url:'SysUser/findUserAll',
	        	loadMsg:'加载中，请稍候...',
	        	fit:true,
	        	fitColumns:true,
	        	singleSelect:true,
	        	rownumbers:true,
	        	multiSort:true,
	        	pagination : true,//页码
	 		    pageNumber : 1,//初始页码
	 		    pageSize : 15,
	 		    pageList : [15,30,45,60],
	 		    toolbar:'#toolbar',
	        	detailFormatter:function(index,row){
	            	return '<div style="padding:5px"><table id="ddv-' + index + '"></table></div>';
	        	},
	        	onBeforeLoad:function(param){
	        		param.name=$("#name").val();
	        	},
	        	onSelect : function(rowIndex,rowData){
	        		setRolebyUser(rowData.sysUserId);
	        	}
	    	});
		}
		
		/**
           	初始化右侧角色树
        **/
        var resultObj;
		function setRolebyUser(sysUserId) {
			$("#role_tree").tree({
				checkbox : true,
				url : '<%=basePath%>SysUser/findRoleByUserId',
				queryParams : {sysUserId: sysUserId}
			});
			$("#sysUserId").val(sysUserId);
		}
		
		
		/**
		   	保存用户角色节点
		**/
		function saveRoleTree() {
			var selectNodes = GetNode();
			// 提交
			$.ajax({        
        		type: "POST",                                     
        		url: "<%=basePath%>SysUser/saveRoleUser",                                  
        		data: "sysUserId="+$("#sysUserId").val()+"&selectNodes="+selectNodes,   
        		success: function(result){ 
        			$.messager.alert('提示', result);                 
        		}   
      		}); 
		}
		
		/**
		   	获取选中的节点
		**/
		function GetNode() {
			var node = $('#role_tree').tree('getChecked');
			var cnodes = '';
			for ( var i = 0; i < node.length; i++) {
				cnodes += node[i].id + ',';
			}
			cnodes = cnodes.substring(0, cnodes.length - 1);
			return cnodes;
		}
		
		
	</script>
  </head>
  
  <body >
  <div class="easyui-layout" fit="true">
  	<div data-options="region:'north',border:false,title:'查询条件'" style="height:61px;">
		<form id="search_form" style="margin-left: 20px;vertical-align: middle;">
			<label>账号:</label>
	        <input name="userName" type="text" id="q_account" class="easyui-textbox" style="width: 200px;" />
			<label>姓名:</label>
	        <input name="realName" type="text" id="q_realName" class="easyui-textbox" style="width: 200px;" />
<!-- 			<label>电子邮箱:</label>
	        <input name="email" type="text" id="q_email" class="easyui-textbox" style="width: 200px;" /> -->
             		<!-- 按钮 -->
              <input id="user_search" type="button" value="查询" onclick="searchUser()" class="search-btn"/> 
              <input id="user_search" type="button" value="重置" onclick="cleanSearch()" class="search-btn"/>
		</form>
	</div>
  	<div data-options="region:'center',border:false">
	<table id="dg" title="用户管理" style="width:100%;height:100%">
		<thead>
			<tr>
			    <th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field: 'sysUserId', hidden:'true' ">用户编号</th>  <!-- -->
				<th data-options="field: 'userName', width:'200'">账号</th>
				<th data-options="field: 'realName', width:'200'">姓名</th>
			</tr>
		</thead>
	</table>
<!-- 	<div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onClick="newSysUser()">新建</a>
       <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onClick="importSysUser()">工作人员导入</a>  
       <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="editSysUser()">编辑</a> 
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onClick="deleteSysUser()">删除</a>
    </div> -->
    <div id="dlg" class="easyui-window" style="width:300px;height:600px;padding:10px 20px" data-options="closed:'true',buttons:'#dlg-buttons'">
      <div class="easyui-layout" data-options="fit:true,border:false">
	      <div data-options="region:'center'">
	        <form id="ff" method="post">
	        	<div class="fitem">
	        		<input type="hidden" id="id" name="sysUserId">
	        	</div>
	            <div class="fitem">
	                <label>用户名:</label>
	                <!-- <input id="userName" name="userName" class="easyui-validatebox" data-options="required:true,validType:['ckUser']"  style="width: 180px;" /> -->
	                <input id="userName" name="userName" class="easyui-validatebox" data-options="required:true,validType:{length:[4,50],remote:['<%=basePath%>SysUser/checkUserName','userName']}"  style="width: 180px;" />
	            </div>
	            <div class="fitem" id="pwd">
	                <label>密码:</label>
	                <input type="password" id="userPassword" name="userPassword" class="easyui-validatebox" data-options="required: true" style="width: 180px;" validType="length[6,30]"/>
	            </div>
	            <div class="fitem" id="confirmPwd">
	            	<label>确认密码:</label>
	            	<input type="password" id="comfirmPassword" name="comfirmPassword" class="easyui-validatebox" data-options="required: true" style="width: 180px;" validType="equals['#userPassword']"/>
	            </div>
	            <div class="fitem">
	                <label>姓名:</label>
	                <input id="name" name="realName" class="easyui-validatebox" data-options="required:true,validType:{length:[2,50]}" style="width: 180px;"/>
	            </div>
	            <div class="fitem">
	            	<label>电子邮箱:</label>
	            	<input id="mail" name="email" class="easyui-validatebox" style="width: 180px;" data-options="validType:'email'" />
	            </div>
	        </form>
	      </div>
	      <div id="dlg-buttons" data-options="region:'south',border:false" style="text-align: right;margin-bottom: 15px;">
	        <button class="search-btn" id="save-btn" data-options="iconCls:'icon-ok'" onClick="saveForm()" style="width:90px">保存</button>
	        <button class="search-btn" data-options="iconCls:'icon-cancel'" onClick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</button>
	        <button class="search-btn" iconCls="icon-remove" onClick="clearForm()" style="width:90px">重置</button>
	      </div>
      </div>
    </div>
   
    </div>
    <div data-options="region:'east',fix:true,title:'对应角色',tools:[{iconCls:'icon-save',handler:function(){saveRoleTree()}}]" style="width: 300px;">
		<input type="hidden" id="sysUserId" name="sysUserId">
		<ul id="role_tree"></ul>
	</div>
    </div>
    <script type="text/javascript">
	    
	  //按回车查询 figo.liu 2015-07-09
	    var isOpen=false;
	    $(document).keydown(function(e){
	    	if(e.keyCode == 13) {
	    		if(isOpen==false){
	    			searchUser();
	    		}
	    	}
	    });
	    
    	var url;
    	//扩展验证方法：验证两次密码是否输入一致
		$.extend($.fn.validatebox.defaults.rules, {
            /*必须和某个字段相等*/
            equalTo: { 
            	validator: function (value, param) { 
            		return $(param[0]).val() == value;
            		}, 
            		message: '字段不匹配' 
            		},
            		
           ckUser: {
           	validator: function (value, param) {
           		if(checkName(value)){
           			alert("true");
           			return true;
           		}
           		else{
           			alert("false");
           			return false;
           		}
           		},
           		message: '字段不匹配'
           		}	
        });	
	
    	function checkName(value){
    		 $.post('<%=basePath%>SysUser/checkUserName',{"userName":value},function(bool){
    			    alert("bool="+bool);
        			return  bool;
        		  });
    	}
    	/**
			创建新表单
		**/		
        function newSysUser(){
        	// 密码框显示
            $("#pwd").show();
            $("#confirmPwd").show();
            $('#ff').form('clear');
            $('#dlg').window('open').dialog('setTitle','新建用户');
            url = '<%=basePath%>SysUser/add';
        }
    	
    	/**
    	   	编辑表单
    	**/
    	function editSysUser() {
    		$("#save-btn").attr("disabled","true");
    		var row = $('#dg').datagrid('getSelected');
            if (!row) {
               $.messager.alert('提示', "请选中一条记录再进行修改！");
               return;
            } else {
            	$("#save-btn").removeAttr('disabled');
    			$('#ff').form('clear');
            	$('#dlg').window('open').dialog('setTitle','编辑用户');
            	$('#ff').form('load', row);
            	// 密码框隐藏
            	$("#pwd").hide();
            	$("#confirmPwd").hide();
            	$("#pwd").validatebox({required:false}); 
            	$("#confirmPwd").validatebox({required:false});
            }
            	$("#id").val(row.sysUserId);
           	 url='<%=basePath%>SysUser/updateUser';
    	}
    	
    	/**
    	   保存Form表单
    	**/
    	function saveForm() {
    		$('#ff').form('submit', {
				url : url,
				 onSubmit: function(){    
				       return true; 
				   },
				success : function(result) {
					$.messager.alert('提示', result);
					$("#dlg").window('close'); // close the sysuser form
					$('#dg').datagrid('reload'); // reload the sysuser data
				}
			});
    		$('#ff').submit();
    	}
    	
    	/**
    	   	删除用户
    	**/
    	function deleteSysUser() {
            
        	var selections = $('#dg').datagrid('getSelections');
			if (!selections || selections.length == 0) {
				showTip("请选择一条记录", false, "系统提示");
				return;
			}
			if (selections.length > 1) {
				showTip("只能选择一条记录", false, "系统提示");
				return;
			}
			$.messager.confirm("信息删除", "确定要删除此信息吗?", function(r) {
				if (r) {

					$.ajax({
						url : "SysUser/deleteById",
						type:"POST",
						data : {
							sysUserId : selections[0].sysUserId
						},
						  success:function(msg){ 
   				    	   $.messager.alert('提示', msg);
   				           loadGrid();
   				       }  

					});

				}
			});
            
    	}
    	
    	/**
    	         清空表单
    	**/
        function clearForm() {
            $('#ff').form('clear');
        }
        
        
        //用户类型匹配
        function getUserType(value, row , index) {
           for (var i=0; i<userType.length; i++) {
               if (value == userType[i].code) {
                   return userType[i].name;
               }
           }
       }
       
       /**
         	 匹配性别
       **/
       function getSexValue(value, row, index){
            if (row.sex == 1) {
                return "男";
            } else if (row.sex == 2) {
                return "女";
            }
        }
        
       function searchUser(){

    	   $('#dg').datagrid('reload',{
    		   userName:$("input",$("#q_account").next("span")).val(),
    		   realName:$("input",$("#q_realName").next("span")).val()
    	   });
       }
       
       function cleanSearch(){
    	   $('#search_form').form('clear');
    	   $('#dg').datagrid('reload',{});
       }
       
    </script>
  </body>
</html>