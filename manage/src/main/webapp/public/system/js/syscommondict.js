/**
 * created by David
 */

/**
 * 页面初始化加载字典索引
 */
$(document).ready(function() {
	loadDictIndex('');
})
//扩展验证方法
$.extend($.fn.validatebox.defaults.rules, {
	length: {//param的值为[]中值  
       validator: function (value, param) { 
           return  (value.length >= 1) && ( value.length<=40 )
       }, message:  '代码长度必须在' + 1 + '至' + 40 + '范围'
   },
   codeExist: {//param的值为[]中值  
       validator: function (value,param) { 
    	   var flag;
    	   $.ajax({
    		   url: 'sysCommDict/indexCodeUnique',
    		   data: {'indexCode': value},
    		   type: 'post',
    		   async:  false,
    		   success: function(pass) {
    			   flag = pass;
    		   }
    	   })
    	   return flag;
      }, message: '索引代码已存在'  
   }
 });

/**
 * 字典索引加载方法
 * @param indexName
 */
function loadDictIndex(indexName) {
	$('#indexTb').datagrid({
		url : "sysCommDict/listDictIndex",
		loadMsg : '加载中，请稍候...',
		fitColumns : false,
		pagination : true,// 页码
		pageNumber : 1,// 初始页码
		pageSize : 15,
		pageList : [ 15, 30, 45, 60 ],
		queryParams : {
		'indexName' : indexName
		 },
		onLoadSuccess : function(data) {
			if (data.rows.length > 0) {// 如果表格数据不为空
				$('#indexTb').datagrid("selectRow", 0);// 选中第一行
			}else{//如果没有索引，则字典值table也需要显示为空
				loadDictValue('');
			}
		},
		onSelect : function(rowIndex, rowData) {
			loadDictValue(rowData.indexId);// 加载索引对应的值
		},
	});
}

/**
 * 根据索引ID加载字典值
 * @param indexId
 */
function loadDictValue(indexId) {
	$('#valueTb').datagrid({
		url : "sysCommDict/getDictValue",
		loadMsg : '加载中，请稍候...',
		fitColumns : false,
		pagination : true,// 页码
		pageNumber : 1,// 初始页码
		pageSize : 15,
		pageList : [ 15, 30, 45, 60 ],
		queryParams : {
			'indexId' : indexId
		},
		onLoadSuccess : function(data) {
			if (data.rows.length == 0) {
				$('#valueTb').datagrid('appendRow', {
					valueCode : "没有数据"
				});
			}
		}
	});
}

/**
 * 增加或编辑字典索引
 * @param operation
 */
function openIndexDlg(operation) {
	$('#indexform').form('clear');
	if ('save' == operation) {// 新增索引
		$('#addIndexDlg').dialog({
			title : '增加字典索引',
			iconCls : 'icon-add',
		});
		$('#addIndexDlg').dialog('open');
	} else {// 其他情况属于更新
		var row = $('#indexTb').datagrid("getSelected");// 被选中的索引
		if (row != null) {
			$('#indexform').form('load', row);
			$('#addIndexDlg').dialog({
				title : '编辑字典索引',
				iconCls : 'icon-edit',
			});
			$('#addIndexDlg').dialog('open');
		} else {
			$.messager.alert('编辑索引', '请先选定一条索引', 'info');
		}
	}
}

/**
 * 保存字典索引
 */
function addNewIndex() {
	$('#indexform').form('submit', {
		url : 'sysCommDict/saveIndex',
		onSubmit : function() {// 对表单进行合法性验证
				return $(this).form('validate');
		},
		success : function(data) {
			$.messager.alert('保存索引', data, 'info');
			$('#indexTb').datagrid('reload');
			$('#addIndexDlg').dialog('close');
		}
	})
}

/**
 * 删除字典索引
 */
function deleteIndex() {
	var row = $('#indexTb').datagrid("getSelected");
	if (row != null) {
		$.messager.confirm('删除索引', '删除索引：' + row.indexName + ' ?', function(r) {
			if (r) {
				$.post('sysCommDict/deleteIndex', {
					indexId : row.indexId
				}, function(data) {
					$.messager.alert('删除索引', data, 'info');
					$('#indexTb').datagrid('reload');
				})
			}
		});
	} else {
		$.messager.alert('删除索引', '请先选定一条索引', 'info');
	}
}

/**
 * 增加和更新字典值
 * @param operation
 */
function openValueDlg(operation){
	var indexRow = $('#indexTb').datagrid("getSelected");
	if(indexRow == null){//如果索引没有选中，提示先选中一条索引
		$.messager.alert('编辑字典值', '请先选定一条索引', 'info');
	}else{
		$('#valueform').form('clear');
		if ('save' == operation) {// 新增字典值
			$('#valueform').form('load',{indexId: indexRow.indexId});//加载索引ID
			$('#addValueDlg').dialog({
				title : '增加字典值',
				iconCls : 'icon-add',
			});
			$('#addValueDlg').dialog('open');
		} else {// 其他情况属于更新
			var row = $('#valueTb').datagrid("getSelected");// 被选中的字典值
			if (row != null) {
				$('#valueform').form('load', row);
				$('#addValueDlg').dialog({
					title : '编辑字典值',
					iconCls : 'icon-edit',
				});
				$('#addValueDlg').dialog('open');
			} else {
				$.messager.alert('编辑字典值', '请先选定一条字典值', 'info');
			}
		}
	}
}

/**
 * 增加字典值
 */
function addNewValue() {
	$('#valueform').form('submit', {
		url : 'sysCommDict/saveValue',
		onSubmit : function() {// 先对表单进行合法性验证
			return $(this).form('validate');
		},
		success : function(data) {
			$.messager.alert('保存字典值', data, 'info');
			$('#valueTb').datagrid('reload');
			$('#addValueDlg').dialog('close');
		}
	})
}

/**
 * 删除字典值
 */
function deleteValue(){
	var row = $('#valueTb').datagrid("getSelected");
	if (row != null) {
		$.messager.confirm('删除字典值', '删除字典值：' + row.valueName + ' ?', function(r) {
			if (r) {
				$.post('sysCommDict/deleteValue', {
					valueId : row.dictValueId
				}, function(data) {
					$.messager.alert('删除字典值', data, 'info');
					$('#valueTb').datagrid('reload');
				})
			}
		});
	} else {
		$.messager.alert('删除字典值', '请先选定一条字典值', 'info');
	}
}

