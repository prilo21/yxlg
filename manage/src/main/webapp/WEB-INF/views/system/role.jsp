<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../../../include/sys-common.jsp" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html >
<html>
<head>
<style type="text/css">
#ff {
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

.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 80px;
}

.fitem input {
	width: 160px;
}
</style>
	<script type="text/javascript" src="././public/system/js/role.js"></script>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div region="center" border="false" style="padding: 1px;">
			<table id="dg" title="角色管理" style="width: 100%; height: 100%"
				data-options="striped:true,rownumbers:true,singleSelect:true,url:'role/find',method:'get',
                     multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar',pagination:'true'">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field: 'roleId', hidden:'true'">角色ID</th>
						<th data-options="field: 'code', width:'200'">角色编号</th>
						<th data-options="field: 'roleName', width:'300'">角色名称</th>
					</tr>
				</thead>
			</table>
			<div id="toolbar">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-add',plain:'true'" onclick="newRole()">新建</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-edit',plain:'true'"
					onclick="editRole()">编辑</a> <a href="javascript:void(0)"
					class="easyui-linkbutton"
					data-options="iconCls:'icon-remove',plain:'true'"
					onclick="deleteRole()">删除</a>
			</div>
			<div id="dlg" class="easyui-window"
				style="width:530px; height: 330px; padding: 10px 20px;"
				data-options="minimizable:false,maximizable:false,collapsible:false,resizable:false,closed:true,buttons:'#dlg-buttons'">
				<div class="easyui-layout" data-options="fit:true,collapsible:'false',border:false">
					<div data-options="region:'center'">
						<form id="ff" method="post">
							<div class="fitem">
								<input id="id" name="roleId" type="hidden">
							</div>
							<div class="fitem">
								<label>角色编号:</label> <input id="code" name="code"
									class="easyui-validatebox" data-options="required: true,validType:['length[0,10]']"
									style="width: 180px;" />
							</div>
							<div class="fitem">
								<label>角色名称:</label> <input id="name" name="roleName"
									class="easyui-validatebox" data-options="required: true,validType:['length[0,10]']"
									style="width: 180px;" />
							</div>
						</form>
					</div>
					<div id="dlg-buttons" data-options="region:'south',border:false"
						style="text-align: right; margin-bottom: 15px;">
						<button class="easyui-linkbutton" id="save-btn"
							data-options="iconCls:'icon-ok'" onclick="saveForm()"
							style="width: 90px">保存</button>
						<button class="easyui-linkbutton"
							data-options="iconCls:'icon-cancel'"
							onclick="javascript:$('#dlg').dialog('close')"
							style="width: 90px">取消</button>
					</div>
				</div>
			</div>
		</div>
		<div region="east" border="false" style="width: 700px;">
			<div class="easyui-layout" data-options="fit:true,border:false">
				<div
					data-options="region:'west',fix:true,title:'权限设置',tools:[{iconCls:'icon-save',handler:function(){saveMenuTree()}}]"
					style="width: 300px;">
					<input type="hidden" id="roleId" name="roleId">
					<ul id="menu_tree"></ul>
				</div>
				<div data-options="region:'center',fix:true,title:'用户列表'"
					style="width: 400px;">
					<ul id="user_list"></ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>