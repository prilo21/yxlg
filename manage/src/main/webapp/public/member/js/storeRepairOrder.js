
$(document).ready(function() {	
	initStoreList();
	initCategoryList();
	$('#searchbtn').click(function() {
		$('#dg').datagrid('load', serializeForm($('#mysearch')));
	});
	loadGrid();
});

/**
 * 加载门店列表
 */
function initStoreList(){
	$.ajax({
		method : 'post',
		url:'storeMemberOrder/initStoreList',
		success : function(data) {
			var data1 = eval(data);
			$("#storeId").combobox('loadData', data1);
		}
	});
}

/**
 * 加载服装品类
 */
function initCategoryList(){
	$.ajax({
		method : 'post',
		url:'storeMemberOrder/initCategoryList',
		success : function(data) {
			var data1 = eval(data);
			$("#categoryId").combobox('loadData', data1);
		}
	});
}
/**
 * 门店会员返修订单列表加载
 */
function loadGrid() {
	$('#dg').datagrid(
			{
				url:'storeRepairOrder/storeRepairOrderList',
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
		url : "storeRepairOrder/exportStoreRepairOrder"
	});
}
function search(){
    $('#dg').datagrid('load', serializeForm($('#mysearch')));
}

