<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../../../include/sys-common.jsp" />
<!DOCTYPE html >
<html>
<head>
<script type="text/javascript"
	src="public/easyui/plugins/jquery.datagrid.ex.js"></script>
<script type="text/javascript" src="public/member/js/member.js"></script>
<!-- <script type="text/javascript" src="public/member/css/member.css"></script> -->
<style type="text/css">
.combo {
    width: 120px !important;
}
table.gridtable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}
table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
</style>
</head>

<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<div id="lay" class="easyui-layout" style="width: 100%; height: 100%">
		<div region="center">
			<table id="dg" title="会员管理" style="width: 100%; height: 100%"
				data-options="striped:true,rownumbers:true,singleSelect:true,url:'member/findByDetachedCriteria',method:'post', 
       				 multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar',pagination:'true'">
				<thead>
					<tr>
						<!-- <th data-options="field: 'ck',width:'50',checkbox:'true'"></th> -->
						<th data-options="field: 'memberId', hidden:'true'">id</th>
						<th data-options="field: 'memberName', width:'100'">会员名</th>
						<th data-options="field: 'memberType.memberType', width:'100'">会员类型</th>
						<th data-options="field: 'certification', width:'100',formatter:getCertification">实名认证</th>
						<th data-options="field: 'memberGrade.memberGradeName', width:'100'">会员等级</th>
						<th data-options="field: 'memberLevelDiscountNoId', width:'100', formatter: formatterDiscountNo">会员折扣等级</th>
						<th data-options="field: 'addedName', width:'100'">姓名</th>
						<!-- <th data-options="field: 'password', width:'100'">会员密码</th> -->
						<th data-options="field: 'gender', width:'100'">性别</th>
						<th data-options="field: 'phoneNo', width:'100'">手机号</th>
						<th data-options="field: 'email', width:'150'">邮箱</th>
<!-- 						<th data-options="field: 'memAdditionalInfo.height', width:'200'"  formatter="formatHeight">身高</th> -->
<!-- 						<th data-options="field: 'memAdditionalInfo.weight', width:'200'" formatter="formatWeight">体重</th> -->
				        <!-- <th data-options="field: 'memAdditionalInfo.bodyType', width:'200'" formatter="formatBodyType">体型</th> -->
						<!-- <th data-options="field: 'icon', width:'100'">头像</th> -->
						<th data-options="field: 'virtualCurrency', width:'100'">酷特币</th>
						<th data-options="field: 'giftCardsCount', width:'100'">礼品卡金额</th>
						<th data-options="field: 'cashBalance', width:'100'">现金余额</th>
						<th data-options="field: 'stroeName', width:'160', formatter:showStoreName">报备门店</th>
						<th data-options="field: 'registTime', width:'130'">注册时间</th>
						<th data-options="field: 'province', width:'100'">会员省份</th>
						<th data-options="field: 'city', width:'100'">会员城市</th>
						<th data-options="field: 'nationId', width:'100'">会员地区号</th>
						<!-- <th data-options="field: 'qrcode', width:'100'">二维码</th> -->
						<th data-options="field: 'birthday', width:'100'">生日</th>
						<th data-options="field: 'height', width:'100'">身高</th>
						<th data-options="field: 'weight', width:'100'">体重</th>
						<th data-options="field: 'signature', width:'100'">个性签名</th>
						<th data-options="field: 'rebate', width:'50',align:'center', formatter: formatterRebate">返利</th>
						<th data-options="field: 'appversion', width:'100'">App版本</th>
						<th data-options="field: 'osversion', width:'100'">系统型号</th>
						<th data-options="field: 'deviceType', width:'100'">硬件版本</th>
						<th data-options="field: 'platform', width:'100'">系统平台</th>
<!-- 						<th data-options="field: 'vouchersQrcode', width:'100',align:'center', formatter: setQRcodeUrl">内部店员二维码</th> -->
					</tr>
				</thead>
			</table>
		</div>

		<div id="toolbar">
			<form id="mysearch" method="post">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="modifyMemberType()">修改会员类型 </a> 
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="memberGrade()">修改会员等级 </a>
					<a href="javascript:openEditAddedNameDlg();" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑用户姓名</a>
					<!-- <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="getVouchersQrcode()">生成代金券领取二维码</a> -->
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="showRebateDlg()">会员返利设定</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="memberLevelDiscountNo()">会员等级折扣设定</a>
					<br>
					会员ID: <input id="memberId" name="memberId" class="easyui-textbox" style="width: 150px;" />
					会员名: <input id="memberName" name="memberName"  class="easyui-textbox" style="width: 100px;" />
					手机号: <input id="phoneNo" name="phoneNo" class="easyui-textbox" style="width: 100px;" />
					会员类型：
					<select class="easyui-combobox" name="memberType">
						<option value="">全部</option>
						<option value="普通会员">普通会员</option>
						<option value="营销会员">营销会员</option>
						<option value="内部会员">内部会员</option>
						<option value="内部营销会员">内部营销会员</option>
						<option value="客服营销会员">客服营销会员</option>
					</select>
					会员折扣等级:<input id="MemberLevelDiscountNoId" name="MemberLevelDiscountNoId" class="easyui-combobox" style="width: 100px;" />
					 邮箱: <input id="email" name="email" class="easyui-textbox" style="width: 100px;" /> 
					<label>
					&nbsp; 生日:<input id="birthday" name="birthday" class="easyui-datebox"/></label>
					&nbsp; 注册时间: 
					<input id="registerTimeFrom" class="easyui-datebox" name = "registerTimeFrom"/> -
				    <input id="registerTimeTo" name="registerTimeTo" class="easyui-datebox" />
				    &nbsp; 系统平台：<input id="platform" name="platform" class="easyui-textbox" style="width: 80px;" /> 
				    &nbsp; 硬件版本：<input id="deviceType" name="deviceType" class="easyui-textbox" style="width: 80px;" /> 
					归属城市：<input id="city" name="city" class="easyui-textbox" style="width: 80px;" /> 
				    <a id="searchbtn" class="easyui-linkbutton">查询</a> 
				    <a id="clearbtn" class="easyui-linkbutton">清空</a>
			</form>
		</div>
		<div id="dlg" class="easyui-window">
			<table id="lts" style="width: 100%; height: 100%"
				data-options="striped:true,rownumbers:true,singleSelect:true,url:'member/findMemberType',method:'POST', 
       		 	multiSort:true,fit:true,nowrap:false,toolbar:'#toolbarTwo',pagination:'true'">
				<thead>
					<tr>
						<th data-options="field: 'memberTypeId', hidden:'true'">会员类型id</th>
						<th data-options="field: 'memberType', width:'200'">会员类型</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="toolbarTwo" style="display:none">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-edit',plain:'true'" onclick="upgrade()">设定</a>
		</div>
		
		<div id="dlgDiscount" class="easyui-window">
			<table id="ltsDiscount" style="width: 100%; height: 100%"
				data-options="striped:true,rownumbers:true,singleSelect:true,url:'member/findLevelDiscountNo',method:'POST', 
       		 	multiSort:true,fit:true,nowrap:false,pagination:'true'">
				<thead>
					<tr>
						<th data-options="field: 'MemberLevelDiscountNoId', hidden:'true'">会员等级折扣id</th>
						<th data-options="field: 'level', width:'200'">打折等级</th>
						<th data-options="field: 'levelName', width:'200'">等级名称</th>
						<th data-options="field: 'discountNo', width:'200'">折扣</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="toolbarFour" style="display:none">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-edit',plain:'true'" onclick="upgradeFour()">设定</a>
		</div>
		<div id="dlgg" class="easyui-window">
			<table id="ltss" style="width: 100%; height: 100%">
				<!-- data-options="striped:true,rownumbers:true,singleSelect:true,url:'member/findMemberGrade',method:'POST', 
       		 	multiSort:true,fit:true,nowrap:false,toolbar:'#toolbarThree',pagination:'true'"> -->
				<thead>
					<tr>
						<th data-options="field: 'memberGradeId', hidden:'true'">会员等级id</th>
						<th data-options="field: 'memberGradeName', width:'200'">会员等级</th>

					</tr>
				</thead>
			</table>
		</div>
		<div id="toolbarThree" style="display:none">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-edit',plain:'true'"
				onclick="upgradeTwo()">设定</a>
		</div>
		<div id="editAddedNameDlg" class="easyui-dialog" closed="true" buttons="#editAddedNameBtn" style="width:300px;height:200px;">
			<form id="editAddedNameForm" method="post" url="editAddedName" align="center" style="margin-top:10%;">
				<input name="memberId" type="hidden">
				&nbsp;会员名：<input name="memberName" disabled="disabled"><br><br>
				会员姓名：<input name="addedName">
			</form>
		</div>
		<div id="editAddedNameBtn">
			<button class="easyui-linkbutton" iconCls="icon-ok" onclick="editAddedName()">保存</button>
			<button class="easyui-linkbutton" iconCls="icon-ok" onclick="javascript:$('#editAddedNameDlg').dialog('close');">取消</button>
		</div>
		
		<div id="rebateDlg" class="easyui-dialog" title="会员返利设定" style="width:200px;height:120px;padding:10px" closed="true" 
			data-options="
				modal: true,
				buttons: [{
					text:'返利',
					iconCls:'icon-ok',
					handler:function(){
						setRebate(1);
					}
				},{
					text:'不返利',
					iconCls:'icon-cancel',
					handler:function(){
						setRebate(0);
					}
				}]
			">
			点击按钮设置返利或不返利
	</div>
	</div>
	<div id="w" class="easyui-window" title="实名认证" >
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'" style="padding:10px;">
			<form>
			    <input type="hidden" id="certificationId" name="certificationId"/>
			    <table class="gridtable">
			        <tr>
			            <td>真实姓名</td>
			            <td><input class="easyui-textbox" style="background:red" type="text" name="name" id="cname"  readonly="readonly" /></td>
			            <td>性别</td>
			            <td><input class="easyui-textbox" type="text" name="gender" id="cgender" readonly="readonly"/></td>
			        </tr>
			        <tr>
			            <td>省</td>
			            <td><input class="easyui-textbox" type="text" name="province" id="province" readonly="readonly" /></td>
			            <td>市</td>
			            <td><input class="easyui-textbox" type="text" name="city" id="city" readonly="readonly" /></td>
			        </tr>
			        <tr>
			            <td>电话号码</td>
			            <td><input class="easyui-textbox" type="text" name="phoneNo" id="cphoneNo" readonly="readonly" /></td>
			            <td>身份证号</td>
			            <td><input class="easyui-textbox" type="text" name="idNumber" id="idNumber" readonly="readonly" /></td>
			        </tr>
			        <tr>
			            <td>身份证图片</td>
			            <td colspan='3'><img id="idPhoto" alt="身份证照片" style="width:500px;height:300px" src=""></td>
			        </tr>
			        <tr>
			            <td>审核通过</td>
			            <td colspan='3'>
			                 <input type="radio" value="03" name="certificationStatus" checked>是
			                 <input type="radio" value="01" name="certificationStatus" onclick="getFailure()">否
			            </td>
			        </tr>
			        <tr>
			            <td>失败原因</td>
			            <td colspan='3'><input class="easyui-combobox" style="width:300px;" type="text" name="failure" id="failure" /></td>
			        </tr>
			    </table>
			</form>
			</div>
			<div id="wtoolbar" data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
				<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"  onclick="submitCertification()" style="width:80px">提交</a>
			</div>
		</div>
	</div>
	
	<div id="store" class="easyui-window">
			<table id="storeTable" style="width:100%;height:100%" >
				<thead>
				<tr>
					<th data-options="field: 'storeId',hidden:'true'" >门店号</th> 
					<th data-options="field: 'storeName', width:'50'">门店名称</th>
	 				<th data-options="field: 'storeProvince', width:'50'">省</th> 
	 				<th data-options="field: 'storeCity', width:'50'">市</th>
	 				<th data-options="field: 'storeDistrict', width:'50'">区</th> 
	 				<th data-options="field: 'storeAddress', width:'200'">详细地址</th>
				</tr>
				</thead>
			</table>
	    <div id="storetoolbar">
	    	<form id="searchStore" method="post">
	        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="registeredToStore()">报备到门店</a>
	        
		<!--门店检索功能待开发
 	    	<a href="javascript:void(0)" style="line-height: 24px;outline: none;color: #444;text-decoration:none;margin-top: 3px">门店名称：</a> 
			<input style="height: 23px;" type="text" name=storeName id="storeName" class="easyui-textbox"/> 
			<a href="javascript:loadStoreGrid()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">查询</a>
			<a id="storeclearbtn" class="easyui-linkbutton">清空</a>
		 -->
			</form>
	    </div>
    </div>
</body>
<script type="text/javascript">
			var map = new Map();
			$(function() {
				$('#gender').combobox({
					required : false,
					multiple : false,
					disabled : false
				});
			});
			function getCertification(val, row, type){
				var btn = "";
				if(row.memberType && row.memberType.memberType == "营销会员") {
					$.ajax({
						async:false,
						url:'member/certificationStatus',
						type:'POST',
						data:{
							"memberId" : row.memberId
						},
						success:function(data){
							var status = "00";
							if (data != "") {
								status = data.certificationStatus;
							}
							if(status == '02'){
								btn = "<a onclick=\"openCertificationWin('"+row.memberId+"',2)\"><font color=\"#0066FF\">待审核</font></a>";
							}
							if(status == '01'){
								btn = "<font color=\"red\">认证失败</font>|<a onclick=\"openCertificationWin('"+row.memberId+"',1)\"><font color=\"#0066FF\">查看</font></a>";
							}
							if(status == '03'){
								btn = "<font color=\"red\">认证成功</font>|<a onclick=\"openCertificationWin('"+row.memberId+"',3)\"><font color=\"#0066FF\">查看</font></a>";
							}
							if(status == '00'){
								btn = "<font color=\"#0000\">未认证</font>";
							}
								
							if (data == "") {
								map[row.memberId] = "";
							} else {
							if (data.store == "" || data.store == null) {
								map[row.memberId] = "未选择";
							} else {
								map[row.memberId] = data.store.storeName;
								}
							}
						}
					});
					
				}
				return btn;
			}
            function openCertificationWin(memberId,status){
            	
            	if(status != 2) {
            		$("#wtoolbar").css("display","none");
            	}else{
            		$("#wtoolbar").css("display","block");
            	}
            	$.ajax({
            		async:false,
            		url:'member/getCertificationInfo',
            		type:'POST',
            		data:{
            			"memberId" : memberId
            		},
            		success:function(data){
            			$("#certificationId").val(data.certificationId);
            			$("#cname").textbox('setValue',data.name);
            			$("#cgender").textbox('setValue',data.gender);
            			$("#province").textbox('setValue',data.province);
            			$("#city").textbox('setValue',data.city);
            			$("#cphoneNo").textbox('setValue',data.phoneNo);
            		    $("#idNumber").textbox('setValue',data.idNumber);
            			$("#idPhoto").attr("src",data.idPhoto);
            			if(status == 3) {
            				$("input[name='certificationStatus'][value="+data.certificationStatus+"]").attr("checked",true);
                    	}
            			if(status == 1){
            				$("input[name='certificationStatus'][value="+data.certificationStatus+"]").attr("checked",true);
            		//		$('#failure').combobox('setValues',data.failure.split(','));
            			}
            		}
            	});
            	$('#w').window('open');
            }
            function getFailure(){
            	$('#failure').combobox({
            		width : 300,
            		url: 'member/getFailureReason',
            		valueField: 'failureCode',
            		textField: 'failureReason',
            		multiple:true
            	});
            }
            function submitCertification(){
            	$.ajax({
            		url:'member/updateCertificationStatus',
            		type:'POST',
            		data:{
            			"certificationId" : $("#certificationId").val(),
            			"status" : $("input[name='certificationStatus']:checked").val(),
            			"reason" : $('#failure').combobox('getValues').join(",")
            		},
            	    success:function(data){
            	    	$('#w').window('close');
            	    	$('#dg').datagrid('load', serializeForm($('#mysearch')));
            	    }
            	});
            }
            
            //显示营销会员报备门店
            function showStoreName(val, row) {
            	if (row.memberId in map) {
            		var storeName = map[row.memberId];
            		return "<a onclick=\"openStoreNameWin()\"><font color=\"#0066FF\">"+storeName+"</font></a>";
            	} else	if (storeName != "" || storeName != null) {
            		return "";
            	}
            }
            
            /**
             * 报备门店
             */
            function openStoreNameWin() {
            	var rows = $('#dg').datagrid('getSelections');
            	if (!rows || rows.length == 0) {
            		$.messager.show({
            			title : "提示操作",
            			msg : "请选中一条记录再报备门店！",
            			showType : "slide",
            			timeout : 3000
            		});
            		return;
            	} else if(rows.length > 1){
            		$.messager.show({
            			title : "提示操作",
            			msg : "只能选中一条记录报备门店！",
            			showType : "slide",
            			timeout : 3000
            		});
            	} else {
            		var row = rows[0];
            		$('#store').window('open').dialog('setTitle', '营销会员报备门店');
            		
            		var cityXd = row.cityXd;
            		var provinceXd = row.provinceXd;
            		$("#storeProvince").val(provinceXd);
            		$("#storeCity").val(cityXd);
            		loadStoreGrid();
            	}
            }
            
    		function loadStoreGrid()  {
    	    	$('#storeTable').datagrid({
//     	    		url:'store/find?storeName='+$("input",$("#storeName").next("span")).val(),
					url:'store/find',
        			nowrap : false,
        			loadMsg : '加载中，请稍候...',
        			fitColumns : true,
        			nowarp : false,
        			pagination : true,// 页码
        			pageNumber : 1,// 初始页码
        			pageSize : 15,
        			pageList : [ 15, 30, 45, 60 ],
        			detailFormatter : function(index, row) {
        				return '<div style="padding:5px"><table id="ddv-' + index
        						+ '"></table></div>';
        			},
        			striped : true,
        			rownumbers : true,
        			singleSelect : true,
        			method : 'get',
        			multiSort : true,
        			fit : true,
        			toolbar : '#storetoolbar'
    	    	});
    		}
    		
    		function registeredToStore(row) {
            	var row = $('#storeTable').datagrid('getSelected');
            	if (!row || row.length == 0) {
            		$.messager.show({
            			title : "提示操作",
            			msg : "请选中一条记录再点击报备到门店！",
            			showType : "slide",
            			timeout : 3000
            		});
            		return;
            	}
            	var memberRow = $('#dg').datagrid('getSelected');
    			$.ajax({
    				url:'member/marketMember/store',
    				type:'POST',
    				data:{
            			"memberId" : memberRow.memberId,
            			"storeId" : row.storeId
            		},
    				success:function(data) {
   						$.messager.show({
	   	            		title : "提示操作",
	   	            		msg : "报备成功",
	   	            		showType : "slide",
	   	            		timeout : 3000
   	            		});
             	    	$('#store').window('close');
             	    	$('#dg').datagrid('load', serializeForm($('#mysearch')));
             	    },
             	    error:function(data){
             	    	alert("error");
             	    }
    			});
    		}
		</script>
</html>