//上部的代码
$(document).ready(function(){
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
		onSelect : function(rowIndex,rowData){
			setMenubyrole(rowData.roleId);
			setUserByRole(rowData.roleId);
			//当重新选中一个角色时，需要重新查询门店
			listUserStore('');
		}
	});
});
/**
 初始化右侧菜单树
 **/
function setMenubyrole(roleId) {
	$("#menu_tree").tree({
		checkbox : true,
		url : 'roleMenu/findMenu',
		queryParams : {roleId: roleId},
		onLoadSuccess : function(node) {
			expandAll();
		}
	});
	// 将roleId设置到隐藏域中
	$("#roleId").val(roleId);
}

//初始化右侧用户列表
function setUserByRole(roleId) {
	$("#user_list").datagrid({
		url: 'role/findUserByRoleId',
		singleSelect:true,
		rownumbers : true,
		columns:[[
			{field:'sysUserId',title:'id',hidden:true},
			{field:'userName',title:'账号',width:100},
			{field:'realName',title:'姓名',width:100},
		]],
		queryParams : {roleId: roleId},
		onSelect: function(index, data){
			listUserStore(data.sysUserId);
		}

	});
}

function expandAll() {
	var node = $('#menu_tree').tree('getSelected');
	if (node) {
		$('#menu_tree').tree('expandAll', node.target);
	} else {
		$('#menu_tree').tree('expandAll');
	}
}

/**
 保存角色菜单权限
 **/
function saveMenuTree() {
	var selectNodes = GetNode();
	// 提交
	$.ajax({
		type: "POST",
		url: "roleMenu/saveRoleMenu",
		data: "roleId="+$("#roleId").val()+"&selectNodes="+selectNodes,
		success: function(result){
			$.messager.alert('提示', result);
		}
	});
}

/**
 获取选中节点
 **/
function GetNode() {
	var node = $('#menu_tree').tree('getChecked');
	var cnodes = '';
	var pnodes = '';
	var prevNode = ''; //保存上一步所选父节点
	for ( var i = 0; i < node.length; i++) {
		if ($('#menu_tree').tree('isLeaf', node[i].target)) {
			cnodes += node[i].id + ',';
			var pnode = $('#menu_tree').tree('getParent', node[i].target); //获取当前节点的父节点
			if (pnode!=null&&prevNode != pnode.id) //保证当前父节点与上一次父节点不同
			{
				pnodes += pnode.id + ',';
				prevNode = pnode.id; //保存当前节点
			}
		}
	}
	cnodes = cnodes.substring(0, cnodes.length - 1);
	pnodes = pnodes.substring(0, pnodes.length - 1);
	return cnodes + "," + pnodes;
};


//下部的js代码
var url;
/**
 创建角色
 **/
function newRole(){
	$('#ff').form('clear');
	$('#dlg').window('open').dialog('setTitle','新建角色');
	url = 'role/save';
}

/**
 修改角色
 **/
function editRole() {
	//$("#save-btn").attr("disabled","true");
	var row = $('#dg').datagrid('getSelected');
	if (!row) {
		$.messager.alert('提示', "请选中一条记录再进行修改！");
		return;
	} else {
		$("#save-btn").removeAttr('disabled');
		$('#ff').form('clear');
		$('#dlg').window('open').dialog('setTitle','编辑角色');
		$('#ff').form('load', row);
	}
	url='role/update';
}

/**
 删除角色
 **/
function deleteRole() {
	var row = $('#dg').datagrid('getSelected');
	if (!row) {
		$.messager.alert('提示', "请选中一条记录再进行删除！");
		return;
	} else {
		$.messager.confirm("提示", "是否删除选中的数据?", function (r) {
			if(r){
				$.ajax({
					type:"POST",
					url:"role/del",
					data:{"roleId":row.roleId },
					success:function(msg){
						$.messager.alert('提示', msg);
						$('#dg').datagrid('reload')
					}
				});
			}
		});
	}
}

function saveForm() {
	//$('#save-btn').attr({"disabled":"disabled"});
	$('#ff').form('submit', {
		url : url,
		method : "POST",
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(result) {
			$('#save-btn').removeAttr("disabled");
			if(result.indexOf("成功")!=-1){
				$("#dlg").window('close'); // close the role form
				$('#dg').datagrid('reload'); // reload the role data
			}
			$.messager.alert('提示', result);
		}
	});
}
function clearForm() {
	$('#ff').form('clear');
}