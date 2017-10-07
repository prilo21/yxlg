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

$(document).ready(function() {	
	$('#searchbtn').click(function() {
		$('#dg').datagrid('load', serializeForm($('#mysearch')));
	});
	loadGrid();
});

function loadGrid() {
	$('#dg').datagrid(
			{
				url:'storeMeasurement/storeMeasurementList',
				rownumbers:true,
				loadMsg : '加载中，请稍候...',
				singleSelect : true,
				pagination : true,// 页码
				pageNumber : 1,// 初始页码
				pageSize : 15,
				pageList : [ 15, 30, 45, 60 ],
				toolbar:'#toolbar'
			});
}

function exportExcel(){
	$('#mysearch').form('submit', {
		url : "storeMeasurement/exportStoreMeasurement"
	});
}


