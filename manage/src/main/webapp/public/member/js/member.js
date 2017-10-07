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
	//会员折扣等级
	$("#MemberLevelDiscountNoId").combobox({
		valueField : 'id',
		textField : 'text',
		multiple : false,
		editable : false
	});
	/*
	 * 获取会员等级折扣
	 */
	discountNoLoad();
	
	$('#searchbtn').click(function() {
		$('#dg').datagrid('load', serializeForm($('#mysearch')));
		$('#dg').datagrid('unselectAll');
	});

	$('#clearbtn').click(function() {
		$('#mysearch').form('clear');
		$('#dg').datagrid('load', {});
		$('#dg').datagrid('unselectAll');
	});

	$('#st').window({
		width : 600,
		height : 330,
		resizable : false
	});
	$('#st').window("close");

	findDiscountNo();
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
	
	$('#dlgDiscount').window({
		width : 500,
		height : 330,
		modal : true,
		minimizable : false,
		top : 100,
		left : 350,
		resizable : false
	});
	
	$('#dlgDiscount').window("close");
	
	$('#dlgg').window({
		width : 500,
		height : 330,
		modal : true,
		minimizable : false,
		top : 100,
		left : 350,
		resizable : false
	});
	
	$('#dlgg').window("close");
	
	$('#w').window({
		width : 700,
		height : 450,
		modal : true,
		minimizable : false,
		top : 100,
		left : 350,
		resizable : false
	});
	
	$('#w').window("close");
	
	//隐藏门店windows
	$('#store').window({
		width : 800,
		height : 520,
		modal : true,
		minimizable : false,
		top : 100,
		left : 350,
		resizable : false
	});
	
	$('#store').window("close");
});

var findDiscountNoData;
function findDiscountNo() {
	$.ajax({
		url:'member/findLevelDiscountNo2',
		type:'POST',
	    success:function(data){
	    	findDiscountNoData = data;
	    }
	});
}

/**
 * 获取会员等级折扣
 */
function discountNoLoad() {
	$.ajax({
		method : 'GET',
		url : 'member/discountNo',
		success : function(data) {
			var data1 = eval(data);
			var item1 = {};
			data1.splice(0, 0, item1);
			$("#MemberLevelDiscountNoId").combobox('loadData', data1);
		}
	});
}

function loadGrid() {
	$('#dg').datagrid(
			{
				nowrap : false,
				loadMsg : '加载中，请稍候...',
				fitColumns : false,
				singleSelect : true,
				pagination : true,// 页码
				pageNumber : 1,// 初始页码
				pageSize : 15,
				pageList : [ 15, 30, 45, 60 ],
				detailFormatter : function(index, row) {
					return '<div style="padding:5px"><table id="ddv-' + index
							+ '"></table></div>';
				},
				pagination : true,
				onDblClickRow :function(rowIndex,rowData){
					showTable();
				},
			});
}

// 会员类型升级
function modifyMemberType() {
	var row = $('#dg').datagrid('getSelected');

	if (!row) {
		$.messager.show({
			title : "提示操作",
			msg : "请选中一条记录再进行修改！",
			showType : "slide",
			timeout : 3000
		});
		return;
	} else {
		$('#dlg').window('open').dialog('setTitle', '修改会员类型');
		$('#lts').datagrid(
				{
					nowrap : false,
					loadMsg : '加载中，请稍候...',
					fitColumns : true,
					pagination : true,// 页码
					pageNumber : 1,// 初始页码
					pageSize : 15,
					pageList : [ 15, 30, 45, 60 ],
					detailFormatter : function(index, row) {
						return '<div style="padding:5px"><table id="ddv-'
								+ index + '"></table></div>';
					},
					pagination : true,
				});
	}
	// url='person/update';
}

/* 确认类型升级 */
function upgrade() {
	var row = $('#lts').datagrid('getSelected');
	if (!row) {
		// $.messager.alert('提示', "请选中一条记录再进行删除！");
		$.messager.show({
			title : "提示操作",
			msg : "请选中会员类型再操作！",
			showType : "slide",
			timeout : 3000
		});
		return;
	} else {
		var prompt = "修改会员类型时，会员等级会相应的设为初级，确定要修改吗?";
		if (row.memberTypeId == '1') {
			prompt = "修改会员类型为普通会员时,其相应的会员绑定关系会删除,会员等级会相应的设为初级，确定要修改吗?";
		}
		$.messager.confirm("提示", prompt, function(r) {
			if (r) {
				var arr = $('#dg').datagrid('getSelections');
				var ids = '';
				for (var i = 0; i < arr.length; i++) {
					ids += arr[i].memberId + ',';
				}
				// ids = ids.substring(0, ids.length - 1);
				var memberTypeId = row.memberTypeId;

				$.ajax({
					type : "post",
					url : "member/promoteMember/",
					data : {
						"memberTypeId" : memberTypeId,
						"memberId" : ids
					},
					success : function(msg) {
						$.messager.show({
							title : "提示操作",
							msg : msg,
							showType : "slide",
							timeout : 3000
						});
						// $.messager.alert('提示', msg);
						$("#dlg").window('close');
						loadGrid();
					}
				});
			}
		});
	}
}

//会员等级升级
function memberGrade() {
	var row = $('#dg').datagrid('getSelections');
	
	//var aa = true;
//	for(var i=0;i<row.length;i++)
//	{
//		var rowValue = row[i].memberType.memberType;
//		if (rowValue!="内部会员") {
//			$.messager.show({
//				title : "提示操作",
//				msg : "请选中会员类型为内部会员的记录再进行升级！",
//				showType : "slide",
//				timeout : 3000
//			});
//			
//			aa=false;
//			return false;
//		}
//		
//	}
	if(row){
		$('#dlgg').window('open').dialog('setTitle', '修改会员等级');
		$('#ltss').datagrid(
				{
					url:'member/findMemberGrade',
					method:'POST',
					queryParams : {
						"memberTypeId" : row[0].memberType.memberTypeId
					},
	       		 	fit:true,
					nowrap : false,
					loadMsg : '加载中，请稍候...',
					striped:true,
					rownumbers:true,
					singleSelect:true,
					fitColumns : true,
					pagination : true,// 页码
					pageNumber : 1,// 初始页码
					pageSize : 15,
					pageList : [ 15, 30, 45, 60 ],
					/*detailFormatter : function(index, row) {
						return '<div style="padding:5px"><table id="ddv-'
								+ index + '"></table></div>';
					},*/
					toolbar:'#toolbarThree'
				});
	}
	// url='person/update';
}

/* 确认等级升级 */
function upgradeTwo() {
	var row = $('#ltss').datagrid('getSelected');
	if (!row) {
		// $.messager.alert('提示', "请选中一条记录再进行删除！");
		$.messager.show({
			title : "提示操作",
			msg : "请选中会员等级再操作！",
			showType : "slide",
			timeout : 3000
		});
		return;
	} else {
		$.messager.confirm("提示", "确定要修改吗?", function(r) {
			if (r) {
				var arr = $('#dg').datagrid('getSelections');
				var ids = '';
				for (var i = 0; i < arr.length; i++) {
					ids += arr[i].memberId + ',';
				}
				// ids = ids.substring(0, ids.length - 1);
				var memberGradeId = row.memberGradeId;

				$.ajax({
					type : "post",
					url : "member/promoteMemberGrade/",
					data : {
						"memberGradeId" : memberGradeId,
						"memberId" : ids
					},
					success : function(msg) {
						$.messager.show({
							title : "提示操作",
							msg : msg,
							showType : "slide",
							timeout : 3000
						});
						// $.messager.alert('提示', msg);
						$("#dlgg").window('close');
						loadGrid();
					}
				});
			}
		});
	}
}


function showTable() {
	var row = $('#dg').datagrid('getSelected');
	var rows = $('#dg').datagrid('getSelections');
	if (!row || rows.length > 1) {
		$.messager.show({
			title : "提示",
			msg : "请选中一条记录再进行修改！",
			showType : "slide",
			timeout : 3000
		});
		return;
	} else {
		$.ajax({
			type : "post",
			url : "member/findPhoneNoAndName/",
			data : {
				"memberId" : row.memberId
			},
			success : function(data) {
				var tableStr = "<table>";
				for (var i = 0; i < data.length; i++) {
					tableStr += "<tr><td>";
					tableStr += "被推荐人姓名：";
					tableStr +="</td><td>";
					tableStr += data[i].memberName;
					tableStr += "</td></tr>";
					
					tableStr += "<tr><td>";
					tableStr += "被推荐人手机：";
					tableStr +="</td><td>";
					tableStr += data[i].phoneNo;
					tableStr += "</td></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>";
				}
				tableStr += "</table>";
				/*$("#tuijian-div").append(tableStr);*/
				$("#tuijian-div").html(tableStr); //html 全部替换  ,append 只是添加
			}
		});
		$.ajax({
			type : "post",
			url : "member/findAddress/",
			data : {
				"memberId" : row.memberId
			},
			success : function(data) {
				var tableStr = "<table>";
				for (var i = 0; i < data.length; i++) {
					tableStr += "<tr><td>";
					tableStr += "收货人：";
					tableStr +="</td><td>";
					tableStr += data[i].receiver;
					tableStr += "</td></tr>";
					
					tableStr += "<tr><td>";
					tableStr += "收货电话：";
					tableStr +="</td><td>";
					tableStr += data[i].phoneNo;
					tableStr += "</td></tr>";
					
					tableStr += "<tr><td>";
					tableStr += "收货地址：";
					tableStr +="</td><td>";
					tableStr += data[i].detail;
					tableStr += "</td></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>";
				}
				tableStr += "</table>";
				/*$("#address-div").append(tableStr);*/
				$("#address-div").html(tableStr);
			}
		});
		$.ajax({
			type : "post",
			url : "member/findRedPackage/",
			data : {
				"memberId" : row.memberId
			},
			success : function(data) {
				var tableStr = "<table>";
				for (var i = 0; i < data.length; i++) {
					tableStr += "<tr><td>";
					tableStr += "红包金额：";
					tableStr +="</td><td>";
					tableStr += data[i].amount;
					tableStr += "</td></tr>";
					
					tableStr += "<tr><td>";
					tableStr += "有效期至：";
					tableStr +="</td><td>";
					tableStr += data[i].ruleEnd;
					tableStr += "</td></tr>";
					
					tableStr += "<tr><td>";
					tableStr += "使用范围：";
					tableStr +="</td><td>";
					tableStr += data[i].useageRangeName;
					tableStr += "</td></tr>";
					
					tableStr += "<tr><td>";
					tableStr += "使用条件：";
					tableStr +="</td><td>";
					tableStr += "满" + data[i].amountRange + "元使用";
					tableStr += "</td></tr>";
					
					tableStr += "<tr><td>";
					tableStr += "红包状态：";
					tableStr +="</td><td>";
					tableStr += data[i].status;
					tableStr += "</td></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>";
				}
				tableStr += "</table>";
				$("#redPackage-div").html(tableStr);
			}
		});
		$.ajax({
			type : "post",
			url : "member/findDiscountCard/",
			data : {
				"memberId" : row.memberId
			},
			success : function(data) {
				var tableStr = "<table>";
				for (var i = 0; i < data.length; i++) {
					tableStr += "<tr><td>";
					tableStr += "折扣：";
					tableStr +="</td><td>";
					tableStr += data[i].discountCardPercent;
					tableStr += "</td></tr>";
					
					tableStr += "<tr><td>";
					tableStr += "有效期至：";
					tableStr +="</td><td>";
					tableStr += data[i].validTo;
					tableStr += "</td></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>";
				}
				tableStr += "</table>";
				$("#discountCard-div").html(tableStr);
			}
		});
		//银行卡信息
		$.ajax({
			type : "post",
			url : "member/findBankCard/",
			data : {
				"memberId" : row.memberId
			},
			success : function(data) {
				var tableStr = "<table>";
				for (var i = 0; i < data.length; i++) {
					tableStr += "<tr><td>";
					tableStr += "持卡人姓名：";
					tableStr +="</td><td>";
					tableStr += data[i].ownerName;
					tableStr += "</td></tr>";
					
					tableStr += "<tr><td>";
					tableStr += "所属银行：";
					tableStr +="</td><td>";
					tableStr += data[i].bankName;
					tableStr += "</td></tr>";
					
					tableStr += "<tr><td>";
					tableStr += "银行卡号：";
					tableStr +="</td><td>";
					tableStr += data[i].bankCardNo;
					tableStr += "</td></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>";
				}
				tableStr += "</table>";
				$("#bankCard-div").html(tableStr);
			}
		});
		
		var tableStr = "<table>";
			tableStr += "<tr><td>";
			tableStr += "系统平台：";
			tableStr +="</td><td>";
			tableStr += row.platform;
			tableStr += "</td></tr>";
			
			tableStr += "<tr><td>";
			tableStr += "版本：";
			tableStr +="</td><td>";
			tableStr += row.appversion;
			tableStr += "</td></tr>";
			
			tableStr += "<tr><td>";
			tableStr += "最后登录时间：";
			tableStr +="</td><td>";
			tableStr += row.lastLoginTime;
			tableStr += "</td></tr>";
		tableStr += "</table>";
		$("#app-div").html(tableStr);
		
		$("#st").window('open');
		$("#oi").form('load', row);
	}
}
/*
$(function() {
	$('#gender').combobox({
		required : false,
		multiple : false,
		disabled : false
	});
});
*/
function openEditAddedNameDlg(){
	var row = $("#dg").treegrid("getSelected");
	if(!row){
		$.messager.alert('提示', "请选中一个会员再进行编辑！");
		return;	
	}
	$("#editAddedNameForm").form("load",row);
	$('#editAddedNameDlg').dialog('open').dialog('setTitle','编辑会员姓名');
}
function editAddedName(){
	$("#editAddedNameForm").form("submit",{
		url:"member/editAddedName",
		success:function(result){
			if(result==200){
				$.messager.show({
					title:"提示操作",
					msg:"保存成功",
					showType:"slide",
					timeout:3000
				});
				$("#editAddedNameDlg").dialog("close");
				$("#dg").datagrid("reload");
			}
		}
	})
}
/**
 * 生成代金券发放需要的二维码
 * 2015-10-20
 */
 function getVouchersQrcode() {
	var rows = $('#dg').datagrid('getSelections');
	if (!rows || rows == null || rows == "") {
		$.messager.show({
			title : "提示操作",
			msg : "请先选择至少一位内部会员再操作",
			showType : "slide",
			timeout : 3000
		});
		return;
	}
	
	var ids = '';
	for (var i = 0; i < rows.length; i++) {
		var memType = rows[i].memberType.memberType;
		if (memType!="内部会员") {
			$.messager.show({
				title : "提示操作",
				msg : "亲，会员" + rows[i].memberName + "不是内部会员，请取消选择的该会员再操作！",
				showType : "slide",
				timeout : 3000
			});
			return;
		}
		ids += rows[i].memberId + ',';
	}
	$.ajax({
		type : "POST",
		url : "member/getVouchersQrcode",
		data : {"memberIds":ids},
		success : function (msg) {
			$.messager.show({
				title : "提示操作",
				msg : msg,
				showType : "slide",
				timeout : 3000
			});
			loadGrid();
		}
	});
}
 
 /**
  * 查看二维码
  * @param value
  * @param row
  * @param index
  * @returns {String}
  */
 function setQRcodeUrl(value, row, index) {
		//<a href="http://www.baidu.com" target="_Blank">百度</a>
		var alink = '';
		if(value){
			//href="javascript:void(0);" onClick
			alink += '<a herf="javascript:void(0);" onClick="openQRcodePage(\''+value+'\')"><span style="color:blue;">店员二维码</span></a>';
		}
		return alink;
	}

	function openQRcodePage(url){
		window.open(url);
	}
	
function showRebateDlg(){
	var row = $('#dg').datagrid('getSelected');
	if (!row) {
		$.messager.show({
			title : "提示操作",
			msg : "请至少先选中一条记录",
			showType : "slide",
			timeout : 3000
		});
		return;
	} else {
		$('#rebateDlg').dialog('open');
	}
}
//设置会员返不返利
function setRebate(rebate){
	var arr = $('#dg').datagrid('getSelections');
	var ids = '';
	for (var i = 0; i < arr.length; i++) {
		ids += arr[i].memberId + ',';
	}
	var alertmsg ='';
	if(rebate == 1){
		alertmsg ="设置返利"
	}else{
		alertmsg ="设置不返利"
	}
	
	$.ajax({
		type : "post",
		url : "member/setMemberRebate",
		data : {
			"rebate" : rebate,
			"memberIds" : ids
		},
		success : function(msg) {
			$.messager.show({
				title : "提示操作",
				msg : alertmsg+msg,
				showType : "slide",
				timeout : 4000
			});
			$("#rebateDlg").dialog('close');
			loadGrid();
		}
	});
}

function formatterRebate(value, row, index){
	if(value== 1){
		return "返利";
	}else{
		return "<span style='color:red;'>不返利<span>";
	}
}

//会员等级折扣设定
function memberLevelDiscountNo() {
	var row = $('#dg').datagrid('getSelected');

	if (!row) {
		$.messager.show({
			title : "提示操作",
			msg : "请选中一条记录再进行修改！",
			showType : "slide",
			timeout : 3000
		});
		return;
	} else {
		$('#dlgDiscount').window('open').dialog('setTitle', '设定会员等级折扣');
		$('#ltsDiscount').datagrid(
				{
					nowrap : false,
					loadMsg : '加载中，请稍候...',
					fitColumns : true,
					pagination : true,// 页码
					pageNumber : 1,// 初始页码
					pageSize : 15,
					pageList : [ 15, 30, 45, 60 ],
					detailFormatter : function(index, row) {
						return '<div style="padding:5px"><table id="ddv-'
								+ index + '"></table></div>';
					},
					pagination : true,
					toolbar:'#toolbarFour'
				});
	}
}

/* 确认设定会员等级折扣 */
function upgradeFour() {
	var row = $('#ltsDiscount').datagrid('getSelected');
	if (!row) {
		$.messager.show({
			title : "提示操作",
			msg : "请选中等级折扣再操作！",
			showType : "slide",
			timeout : 3000
		});
		return;
	} else {
		var prompt = "确定要设定会员等级折扣吗?";
		$.messager.confirm("提示", prompt, function(r) {
			if (r) {
				var arr = $('#dg').datagrid('getSelected');
				var memberId = arr.memberId;
				

				$.ajax({
					type : "post",
					url : "member/setLevelDiscountNo/",
					data : {
						"MemberLevelDiscountNoId" : row.memberLevelDiscountNoId,
						"memberId" : memberId
					},
					success : function(msg) {
						$.messager.show({
							title : "提示操作",
							msg : msg,
							showType : "slide",
							timeout : 3000
						});
						// $.messager.alert('提示', msg);
						$("#dlgDiscount").window('close');
						loadGrid();
					}
				});
			}
		});
	}
}
 function formatterDiscountNo(value, row, index) {
	 return findDiscountNoData[value];
 }