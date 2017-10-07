// js方法：序列化表单
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

$(document).ready(function() {

	$('#searchbtn').click(function() {
		$('#dg').datagrid('load', serializeForm($('#mysearch')));
		$('#dg').datagrid('unselectAll');
	});

	$('#clearbtn').click(function() {
		$('#mysearch').form('clear');
		$('#dg').datagrid('load', {});
		$('#dg').datagrid('unselectAll');
	});

	loadGrid();

	$('#dlg').window({
		width : 500,
		height : 330,
		modal : true,
		minimizable : false,
		top : 100,
		left : 350,
		resizable : false
	});
	$('#dlg').window("close");
});

function loadGrid() {
	$('#dg').datagrid(
			{
				nowrap : false,
				loadMsg : '加载中，请稍候...',
				fitColumns : true,
				pagination : true,// 页码
				pageNumber : 1,// 初始页码
				pageSize : 15,
				pageList : [ 15, 30, 45, 60 ],
				detailFormatter : function(index, row) {
					return '<div style="padding:5px"><table id="ddv-' + index
							+ '"></table></div>';
				},
				pagination : true,
			});
};
