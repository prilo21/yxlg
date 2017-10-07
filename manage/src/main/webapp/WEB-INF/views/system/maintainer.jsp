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
    <script type="text/javascript" src="public/easyui/plugins/jquery.datagrid.ex.js"></script>
  <script type="text/javascript">
  	
  	  	
  
	//按回车查询 figo.liu 2015-07-09
	var isOpen=false;
	$(document).keydown(function(e){
		if(e.keyCode == 13) {
			if(isOpen==false){
				$('#searchbtn').trigger('click');
			}
		}
	});

  
   $(document).ready(function(){ 
	   
	   $('#searchbtn').on('click',function() {
	
			$('#dg').datagrid('reload', {
				'userName': $("input",$("#q_account").next("span")).val(),  
				'maintainerName' : $("input",$("#maintainerName").next("span")).val()
			});  
			$('#dg').datagrid('unselectAll');
		});
	   
	   $('#clearbtn').on('click',function() {

			$('#search_form').form('clear');
			$('#dg').datagrid('load',{});
			$('#dg').datagrid('unselectAll');
		});
	  	
	    	loadGrid();  
	    	$('#dlg').window({   
	          width:500,   
	          height:330,
	          minimizable:false,
	          resizable:false,
		      collapsible:false,
		      onOpen:function(){
					isOpen = true;
				},
			  onClose:function(){
					isOpen = false;
				}
	          });
	    	$('#dlg').window("close");
	    	
	    	$('#edlg').window({   
		          width:500,   
		          height:330,
	    	  	  minimizable:false,
		          resizable:false,
				  collapsible:false
		     });
		    $('#edlg').window("close");
		});
		
	function loadGrid()  {
	    	$('#dg').datagrid({
	    		fit:true,
	    		striped:true,
	    		nowrap:false,
	        	loadMsg:'加载中，请稍候...',  			
       			multiSort:true,
	        	fitColumns:true,
	        	rownumbers:true,
	        	singleSelect:true,
	        	url:'sysMaintainer/findByDetachedCriteria',
	 		    pageNumber : 1,//初始页码
	 		    pageSize : 15,
	 		    pageList : [15,30,45,60],
	 		    pagination:'true',
	 		    toolbar:'#toolbar'
	    	});
		}
	
	</script>
  </head>
  
  <body class="easyui-layout"  fit="true">   <!-- style="overflow-y: hidden"  scroll="no" -->
  	  <div data-options="region:'north',border:false,title:'运维人员查询'" style="height:61px;">
		<form id="search_form"  style="margin-left: 20px;vertical-align: middle;">
			<label>姓名:</label>
	        <input  id="maintainerName" type="text" class="easyui-textbox" style="width: 200px;" />
			<label>账号:</label>
	        <input  id="q_account" class="easyui-textbox" style="width: 200px;" />
            <a id="searchbtn"  class="easyui-linkbutton" data-options ="iconCls:'icon-search',plain:'true'">查找</a>
            <a id="clearbtn"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'">重置</a>
		</form>
	</div>
  	<div data-options="region:'center',border:false" >
			<table id="dg"  title="运维人员管理" style="width:100%;height:100%"
>
		<thead data-options="fit:true">
			<tr>
				<th data-options="field: 'maintainerId', hidden:'true'">id</th>
				<th data-options="field: 'maintainerName', width:'150'">姓名</th>
				<th data-options="field: 'sysUser.userName', width:'150'">账号</th>
				<th data-options="field: 'phone', width:'150'">手机号码</th>
				<th data-options="field: 'email', width:'150'">电子邮箱</th>
				<th data-options="field: 'address', width:'150'">地址</th>
		</tr>
		</thead>
	</table>

	<div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="addMaintainer()">新建</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="editMaintainer()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onclick="deleteMaintainer()">删除</a>
    </div>
    <div id="dlg" class="easyui-window" style="width:300px;height:600px;padding:10px 20px" data-options="closed:'true',buttons:'#dlg-buttons'">
      <div class="easyui-layout" data-options="fit:true,border:false">
	      <div data-options="region:'center'">
	        <form id="ff" method="post">
	        	<div class="fitem">
	        		<input id="id" name="maintainerId" hidden="true">
	        	</div>
	        	<div class="fitem">
	        		<label>账号:</label>
	        		<input id="form_userName" name="sysUser.userName"  class="easyui-validatebox" data-options="required: true,validType:['account','accountCheck']"  style="width: 250px;"/><%-- ,remote:['<%=basePath%>sysMaintainer/checkUserName','sysUser.userName']  --%>
	        	</div>
	        	<div class="fitem">
	        		<label>密码:</label>
	        		<input id="form_userPassword" type="password"   name="sysUser.userPassword" class="easyui-validatebox"  data-options="required: true" validtype="password"  style="width: 250px;">
	        	</div>
	        	<div class="fitem">
	        	    <label>确认密码:</label>
	        	    <input id="confirmPassword" type="password" name="confirmPassword"  class="easyui-validatebox" data-options="required:true" validType="equalTo['#form_userPassword']" invalidMessage="两次输入密码不一致" style="width: 250px;">
	        	</div>
	            <div class="fitem">
	                <label>姓名:</label>
	                <input id="maintainerName" name="maintainerName" class="easyui-validatebox" data-options="required: true" validtype="name"  style="width: 250px;"/>
	            </div>
	            <div class="fitem">
	                <label>手机号码:</label>
	                <input id="phone" name="phone" class="easyui-validatebox" data-options="required: true" validtype="mobile" style="width: 250px;"/>
	            </div>
	            <div class="fitem">
	                <label>电子邮件:</label>
	                <input id="email" name="email" class="easyui-validatebox" data-options="required: true" validType="email" style="width: 250px;"/>
	            </div>
	            <div class="fitem">
	                <label>地址:</label>
	                <input id="address" name="address" class="easyui-validatebox" data-options="required: false" style="width: 250px;"/>
	            </div>
	        </form>
	      </div>
	      <div id="dlg-buttons" data-options="region:'south',border:false" style="text-align: right;margin-bottom: 15px;">
	        <button class="btn-lg-ok" id="new-save-btn" data-options="iconCls:'icon-ok'" onclick="saveForm()" style="width:90px">保存</button>
	        <button class="btn-lg-cancel" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</button>
	        <button class="btn-lg-reset" onclick="clearForm()">清空</button>
	      </div>
      </div>
    </div> 
    
  </div>  
    <div id="edlg" class="easyui-window" style="width:300px;height:600px;padding:10px 20px" data-options="closed:'true',buttons:'#dlg-buttons'">
      <div class="easyui-layout" data-options="fit:true,border:false">
	      <div data-options="region:'center'">
	        <form id="eff" method="post">
	        	<div class="fitem">
	        		<input id="id2" name="maintainerId" hidden="true">
	        	</div>
	        	<div class="fitem">
	        		<input id="form_sysUserId2" name="sysUser.sysUserId" hidden="true">
	        	</div>
	        
	            <div class="fitem">
	                <label>姓名:</label>
	                <input id="maintainerName" name="maintainerName" class="easyui-validatebox" data-options="required: true" validtype="name"  style="width: 250px;"/>
	            </div>	            		        	
	            <div class="fitem">
	                <label>手机号码:</label>
	                <input id="phone" name="phone" class="easyui-validatebox" data-options="required: true" validtype="mobile" style="width: 250px;"/>
	            </div>
	            <div class="fitem">
	                <label>电子邮件:</label>
	                <input id="email" name="email" class="easyui-validatebox" data-options="required: true" validType="email" style="width: 250px;"/>
	            </div>
	            <div class="fitem">
	                <label>地址:</label>
	                <input id="address" name="address" class="easyui-validatebox" data-options="required: false" style="width: 250px;"/>
	            </div>
	        </form>
	      </div>
	      <div id="dlg-buttons" data-options="region:'south',border:false" style="text-align: right;margin-bottom: 15px;">
	        <button class="btn-lg-ok" id="edit-save-btn" data-options="iconCls:'icon-ok'" onclick="saveForm2()" style="width:90px">保存</button>
	        <button class="btn-lg-cancel" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#edlg').dialog('close')" style="width:90px">取消</button>
	      </div>
      </div>
    </div>
    <script type="text/javascript">
    	var url;
    /* 	$.extend($.validator.messages, {
    	    remote: "账号已存在"
    	}
    	); */
    	//扩展验证方法
        $.extend($.fn.validatebox.defaults.rules, {
            equalTo: { 
            	validator: function (value, param) 
            	{ 
            		return $(param[0]).val() == value; 
            	}, 
            	message: '字段不匹配' 
            	},
           //移动手机号码验证  
           mobile: {
        	   //value值为文本框中的值  
               validator: function (value) {  
                   var reg = /^1[3|4|5|8|9]\d{9}$/;  
                   return reg.test(value);  
               },  
               message: '输入手机号码格式不正确.'  
           },
           account: {//param的值为[]中值  
               validator: function (value, param) { 
                   if (value.length<4 || value.length>20 ) 
                   {  
                       $.fn.validatebox.defaults.rules.account.message = '用户名长度必须在' + 4 + '至' + 20 + '范围';
                       
                       return false;  
                   } else {  
                       if (!/^[\w]+$/.test(value)) {  
                           $.fn.validatebox.defaults.rules.account.message = '用户名只能由数字、字母、下划线组成.';  
                           return false;  
                       } else {  
                           return true;  
                       }  
                   }
               }, message: ''  
           },
           accountCheck: {//param的值为[]中值  
               validator: function (value,param) { 
            	   var bool=false;
            	   $.ajax({  
        		       method:"post",  
        		       url:"<%=basePath%>sysMaintainer/checkUserName",  
        		       data: {"sysUser.userName":value},
        		       async: false,
        		       success:function(d){
        		    	   bool=d;
        		       }
        		   });
            	   return bool;
              }, message: '账号已存在'  
           },       
           name : {// 验证姓名，可以是中文或英文
				validator: function (value) {
				return /^[\u4e00-\u9fa5_a-zA-Z0-9]+$/.test(value);
				},
				message: '非空值、请输入汉字、英文字母、下划线'
			},
       password: {
           validator: function (value) {
                
               return  /^[0-9a-zA-Z]{3,20}$/i.test(value); 
           },
           message: '请输入3-20位字母或数字'
       }
  });
        	
    	/**		  
    	          创建人员	      
		**/
        function addMaintainer(){
        	$("#new-save-btn").removeAttr('disabled');
            $('#ff').form('clear');
            $('#dlg').window('center');
            $('#dlg').window('open').dialog("setTitle","新建人员");
            url = 'sysMaintainer/save';
        }
    	
    	/**
    	   	编辑人员
    	**/
    	function editMaintainer() {
    		$("#edit-save-btn").attr("disabled","true");
    		var row = $('#dg').datagrid('getSelected');
            if (!row) {
                $.messager.alert("提示","请选中一条记录再进行修改！","info");
               return;
            } else {
            	$("#edit-save-btn").removeAttr('disabled');
    			$('#eff').form('clear');
            	$('#edlg').window('open').dialog('setTitle','编辑人员');
            	$('#eff').form('load', row);
            	// 设置用户名
            	$("#form_userName").val(row.sysUser.userName);
            	$("#form_sysUserId2").val(row.sysUser.sysUserId);
            	
            }
            url='sysMaintainer/update';
    	}
    	
    	/**
    	   保存Form表单
    	**/
    	function saveForm() {
    		if($('#ff').form('validate')){
    			$('#ff').form('submit', {
    				url : url,
    				method : "POST",
    				success : function(result) {
    					$.messager.alert("操作提示",
    							result,"info");
    					$("#dlg").window('close'); // close the schedule form
    					$('#dg').datagrid('reload'); // reload the schedule data
    				}
    			});   		  		
    		}	
    		
    	}
    	
    	/**
    	*/
    	function saveForm2() {
    		$('#eff').form('submit', {
				url : url,
				method : "POST",
				success : function(result) {
					$.messager.alert("操作提示",
							result,"info");
					$("#edlg").window('close'); // close the schedule form
					$('#dg').datagrid('reload'); // reload the schedule data
				}
			});
    	}
    	/**
    	   	删除人员
    	**/
    	function deleteMaintainer() {
    		var row = $('#dg').datagrid('getSelected');
            if (!row || row.length == 0) {
               $.messager.alert('提示', "请选中一条记录再进行删除！","info"); 
               return;
			} else {
               $.messager.confirm("提示", "是否删除选中的数据?", function (r) {
    		       if(r){
    			       $.ajax({  
    				       type:"post",  
    				       url:"sysMaintainer/delete",  
    				       data: {"maintainerId":row.maintainerId},  
    				       success:function(msg){ 
    				    	   $.messager.alert('提示', msg);
    				           loadGrid();
    				       }  
    				   });
    				}
    			});
            }
    	}
    	
    	/**
    	         清空表单
    	**/
        function clearForm() {
            $('#ff').form('clear');
        }
    </script>
  </body>
</html>
