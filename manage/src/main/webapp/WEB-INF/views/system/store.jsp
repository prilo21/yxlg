<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../../../include/sys-common.jsp" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html >
<html>
<head>
<style>
.form-dialog {
	margin: 0;
	padding: 10px 30px;
}

.ftitle {
	font-size: 14px;
	font-weight: bold;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

.fitem{
            margin-bottom:5px;
        }
.fitem label{
            display:inline-block;
            width:80px;
        }
.fitem input{
            width:180px;
        }

.combogrid-input span{
            height:23px !important;
        }
        
.combogrid-input .easyui-fluid {
        	height:23px !important;
        }
.combogrid-input .textbox-icon, .combo-arrow{
        	height:23px !important;
        }
.combogrid-input .textbox-text, .validatebox-text{
        	width: 160px !important;
        }
</style>
<script type="text/javascript" src="././public/system/js/storeManage.js"></script>
</head>

<body class="easyui-layout" fit="true">
	<div data-options="region:'center',border:false" style="width: 70%;">
	 	<table id="storeDg" title="加盟商管理" style="width: 100%; height: 100%">
			<thead>
				<tr>
					<th data-options="field: 'ck',checkbox:true,align:'center'"></th>
					<th data-options="field: 'storeId',hidden:true, width:'80',align:'center'">Id</th>
					<th data-options="field: 'storeName', width:'100',align:'center'">加盟商名称</th>
					<th data-options="field: 'storeUser', width:'100',align:'center',formatter: 
					function(value,row,index){
				         return row.user.userName ;
				       }">加盟商管理人</th>
				       <th data-options="field: 'phone', width:'100',align:'center'">联系电话</th>
				      <th data-options="field: 'idCard', width:'100',align:'center'">身份证号</th>
					<th data-options="field: 'createDate', width:'160',align:'center'">创建时间</th>
					<th
						data-options="field: 'status', width:'80',align:'center',formatter: function(value,row,index){
				if (row.deleteFlag == 0){
					return '有效';
				} else {
					return '无效';
				}
				}">有效状态</th>
				<th data-options="field: 'storeDescription', width:'200',align:'center'">加盟商信息描述</th>
				</tr>
			</thead>
		</table>
	<div id="toolbar">
			<form id="search_form" method="post"
				style="padding: 0px 0px 0px 0px; margin: 0px; ">
				<label>加盟商名称</label> <input name="storeNameDto" id="storeNameDto" class="easyui-textbox" />
				 <label>管理人名称:</label> <input name="userNameDto" id="userNameDto" class="easyui-textbox" /> 
				 <label>门店名称</label> <input name="companyNameDto" id="companyNameDto" class="easyui-textbox" /> 
				 <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search',plain:'true'"
					onclick="Search()">查询</a>
					 <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-clear',plain:'true'"
					onclick="resetSearch()">重置</a>
			</form>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:'true'" onclick="newStore()">新建</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-edit',plain:'true'"
				onclick="editStore()">编辑</a> 
				<!-- <a href="javascript:void(0)"
				class="easyui-linkbutton"
				data-options="iconCls:'icon-edit',plain:'true'" onclick="setRuleFlag(0)">有效</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel',plain:'true'"
				onclick="setRuleFlag(1)">无效</a> -->
		</div>

	<!-- <div id="searchBar" class="fitem">
			<label>管理者账户名</label> <input id="realName" name="storeUserName"
				class="easyui-textbox" /> <a href="javascript:void(0)"
				class="easyui-linkbutton"
				data-options="iconCls:'icon-search',plain:'true'"
				onclick="initStore()">查找</a>
		</div> -->
		
		<div id="dlg" class="easyui-window"
			style="padding: 10px 20px"
			data-options="closed:'true',buttons:'#dlg-buttons'">
			<div class="easyui-layout" data-options="fit:true,border:false">
				<div data-options="region:'center'">
					<form id="ff" method="post" class="form-dialog">
					<input type="hidden" name='storeId'>
					<input type="hidden" id='userId'>
						<div class="fitem">
							<label>加盟商名称:</label> <input name="storeName"
								class="easyui-textbox" data-options="required: true"
								missingMessage="该输入项为必填项" />
						</div>
						<div class="fitem" >
							<label>管理人用户名:</label>
							<input name="user.userName" id="userName" class="easyui-textbox"
							 data-options="required: true,events:{blur:function(){
                             checkoutUser()
                             }}"  missingMessage="该输入项为必填项"  />
							 <!-- <input name="user.maintainerId"
								id="storeUser" class="easyui-combogrid"
								data-options="required: true" style="width: 182px;"/> -->
						</div>
					<div id='pwd'>	
					<div class="fitem">
		        		<label>密码:</label>
		        		<input type="password" id="form_userPassword"  name="user.userPassword" class="easyui-textbox" data-options="required: true,validType:{length:[6,16]}" missingMessage="该输入项为必填项" >
		        		</div>
		        		<div class="fitem">
		            	<label>确认密码:</label>
		            	<input type="password" id="comfirmPassword"  class="easyui-textbox" data-options="required: true" missingMessage="该输入项为必填项" validType="equalTo['#form_userPassword']"/>
		            	</div>
						
		            </div>
						<div class="fitem">
							<label>加盟商描述:</label> <textarea name="storeDescription"
								style="width: 182px;high:200px;"></textarea>
						</div>
						<div class="fitem">
	                <label>身份证号:</label>
	                <input  name="idCard" class="easyui-textbox" data-options="required: true"  missingMessage="该输入项为必填项"/>
	            	</div>
						<div class="fitem">
							<label>联系电话:</label> <input name="phone" id="userPhone"
								data-options="required: true" validType="integer"
								missingMessage="该输入项为必填项" class="easyui-numberbox" />
						</div>
						<div class="fitem">
							<label>详细地址:</label> <input name="detail" class="easyui-textbox" />
						</div>
						<div class="fitem">
							<label>是否有效:</label> <input id="ff_isAutoSend" name="deleteFlag"
								type="radio" value=0 style="width: 30px;" />有效 <input
								id="ff_isAutoSend" name="deleteFlag" type="radio" value=1
								style="width: 30px;" />无效
						</div>
					</form>
				</div>
				<div id="dlg-buttons" data-options="region:'south',border:false"
					style="text-align: right; margin-bottom: 15px;">
					<button class="btn-lg-ok" id="new-save-btn"
						data-options="iconCls:'icon-ok'" onclick="saveFF()"
						style="width: 50px">保存</button>
					<button class="btn-lg-cancel" data-options="iconCls:'icon-cancel'"
						onclick="javascript:$('#dlg').dialog('close')" style="width: 50px">取消</button>
					<button class="btn-lg-reset" onclick="clearFF()"
						style="width: 50px">重置</button>
				</div>
			</div>
		</div>
	</div>
	
	
<div region="east" border="false" style="width: 30%;">

		<div class="easyui-layout" data-options="fit:true,border:false">
			<div
				data-options="region:'east',fix:true,title:'门店列表',tools:[{iconCls:'icon-save',handler:function(){saveUserStores()}}]"
				>
				<table id="store_list" class="easyui-datagrid">
					<thead>
						<tr>
							<th data-options="field:'checked',checkbox:true"></th>
							<th data-options="field:'textField'">门店名称</th>
						</tr>
					</thead>
				</table>
			</div>
			<div id="searchBar2" class="fitem">
			<label>门店名称</label> 
			<input id="sysUserId" type="hidden">
			<input id="storeName_s" name="storeName_s"
				class="easyui-textbox" /> <a href="javascript:void(0)"
				class="easyui-linkbutton"
				data-options="iconCls:'icon-search',plain:'true'"
				onclick="listUserStore()">查找</a>
		</div>
		</div>
	</div>
</body>
</html>