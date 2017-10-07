<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../../../include/sys-common.jsp" />

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html >
<html>
<head>
<style type="text/css">
#indexform {
	margin: 0;
	padding: 10px 30px;
}

#valueform {
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
<script type="text/javascript"
	src="././public/system/js/syscommondict.js"></script>
</head>

<body>

	<div class="easyui-layout" style="width: 1120px; height: 500px;">
		<div data-options="region:'west'" title="字典索引" style="width: 460px;">
			<table id="indexTb"
				data-options="method:'get',border:false,singleSelect:true,fit:true,fitColumns:true,toolbar:'#indextoolbar'">
				<thead>
					<tr>
						<th data-options="field:'indexName',halign:'center'" width="120">字典索引名称</th>
						<th data-options="field:'indexCode',halign:'center'" width="120">索引代码</th>
						<th data-options="field:'remark',halign:'center'" width="200">备注</th>
					</tr>
				</thead>
			</table>
			<div id="indextoolbar">
				 <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="openIndexDlg('save')">新增索引</a>
				 <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="openIndexDlg('update')">编辑索引</a>
				 <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:'true'" onclick="deleteIndex()">删除索引</a>
				 <input class="easyui-searchbox" data-options="prompt:'输入索引名',searcher:loadDictIndex" style="width:180px;"></input>
			</div>
		</div>
		<div data-options="region:'east'" title="字典值" style="width: 660px;">
		<table class="easyui-datagrid" id="valueTb"
				data-options="method:'post',border:false,singleSelect:true,fit:true,fitColumns:true,toolbar:'#valuetoolbar',pagination:true">
				<thead>
					<tr>
						<th data-options="field:'valueName',halign:'center'" width="150">字典值名称</th>
						<th data-options="field:'valueCode',halign:'center'" width="150">字典值</th>
						<th data-options="field:'orderCol',halign:'center'" width="100">排序</th>
						<th data-options="field:'remark',halign:'center'" width="250">备注</th>
					</tr>
				</thead>
			</table>
			<div id="valuetoolbar">
				 <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="openValueDlg('save')">新增字典值</a>
				 <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="openValueDlg('update')">编辑字典值</a>
				 <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:'true'" onclick="deleteValue()">删除字典值</a>
			</div>
		</div>
	</div>
	
	
	<!-- 保存或修改字典索引 -->
	<div id="addIndexDlg" class="easyui-dialog"  style="width:380px;height:200px;top:5px;left:250px" resizable="true" 
		data-options="
				closed:'true',
				modal:true,
				buttons: [{
					text:'保存',
					iconCls:'icon-ok',
					handler:function(){
						addNewIndex();
					}
				},{
					text:'取消',
					iconCls:'icon-cancel',
					handler:function(){
						$('#addIndexDlg').dialog('close');
					}
				}]
			">
			<form id="indexform" method="post">
					<div style="display:none">
						<input id="indexId" name="indexId" class="easyui-textbox"/>
					</div>
					<div class="fitem">
						<label>索引名称:</label> 
						<input id="indexName" name="indexName" class="easyui-validatebox" data-options="required: true,validType:'length[1,40]'"  missingMessage="名称不能为空" />
					</div>
					<div class="fitem">
						<label>索引代码:</label> 
						<input id="indexCode" name="indexCode" class="easyui-validatebox" data-options="required: true,validType:['length','codeExist']"  missingMessage="代码不能为空"  />
					</div>
					<div class="fitem">
						<label>备注:</label> 
						<input id="remark" name="remark" class="easyui-validatebox" data-options="validType:'length[0,400]'"/>
					</div>
			</form>
	</div>
	
	<!-- 保存或修改字典值 -->
	<div id="addValueDlg" class="easyui-dialog"  style="width:380px;height:250px;top:5px;left:250px" resizable="true" 
		data-options="
				closed:'true',
				modal:true,
				buttons: [{
					text:'保存',
					iconCls:'icon-ok',
					handler:function(){
						addNewValue();
					}
				},{
					text:'取消',
					iconCls:'icon-cancel',
					handler:function(){
						$('#addValueDlg').dialog('close');
					}
				}]
			">
			<form id="valueform" method="post">
					<div style="display:none">
						<input id="dictValueId" name="dictValueId" class="easyui-textbox"/>
						<input id="indexId" name="indexId" class="easyui-textbox"/>
					</div>
					<div class="fitem">
						<label>字典值名称:</label> 
						<input id="valueName" name="valueName" class="easyui-textbox" data-options="required: true" validType="length[1,40]" missingMessage="名称不能为空" />
					</div>
					<div class="fitem">
						<label>字典值:</label> 
						<input id="valueCode" name="valueCode" class="easyui-textbox" validType="length[0,40]"/>
					</div>
					<div class="fitem">
						<label>排序:</label> 
						<input id="orderCol" name="orderCol" class="easyui-numberbox" validType="length[0,3]"/>
					</div>
					<div class="fitem">
						<label>备注:</label> 
						<input id="remark" name="remark" class="easyui-textbox" validType="length[0,40]"/>
					</div>
			</form>
	</div>
</body>
</html>