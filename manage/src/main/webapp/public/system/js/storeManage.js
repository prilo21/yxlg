/**
 * 加盟商管理js
 */
$.extend($.fn.textbox.defaults.rules, {
    /*必须和某个字段相等*/
    equalTo: {
        validator: function(value, param) {
            return $(param[0]).val() == value;
        },
        message: '两次输入的密码不一致'
    },
    //移动手机号码验证  
    mobile: {
        //value值为文本框中的值  
        validator: function(value) {
            var reg = /^1[3|4|5|8|9]\d{9}$/;
            return reg.test(value);
        },
        message: '输入手机号码格式不正确.'
    },

});
var companyNameDto = '';
var storeNameDto = '';
var userNameDto = '';
$(document).ready(function() {
    loadStoreGrid();
    $('#dlg').window({
        width: 420,
        height: 400,
        minimizable: false,
        resizable: false,
        collapsible: false,
        onOpen: function() {
            isOpen = true;
        },
        onClose: function() {
            isOpen = false;
        }
    });
    $('#dlg').window("close");
});

function loadStoreGrid() {
    $('#storeDg').datagrid({
        loadMsg: '加载中，请稍候...',
        fitColumns: false,
        pageNumber: 1,
        // 初始页码
        pageSize: 15,
        pageList: [15, 30, 45, 60],
        url: 'sysBusiness/findStore',
        pagination: true,
        striped: true,
        rownumbers: true,
        singleSelect: true,
        method: 'post',
        multiSort: true,
        fit: true,
        nowrap: false,
        toolbar: '#toolbar',
        onLoadSuccess:function(data){
            $('#store_list').datagrid('unselectAll');
        },
        queryParams: {
            'companyNameDto': $('#companyNameDto').textbox('getText'),
            'storeNameDto': $('#storeNameDto').textbox('getText'),
            'userNameDto': $('#userNameDto').textbox('getText')
        },
        onSelect: function(index, data) {
            $('#sysUserId').val(data.user.sysUserId);
            listUserStore();
        }
    });
}

var url;
function Search() {

    companyNameDto = $("#companyNameDto").val();
    storeNameDto = $("#storeNameDto").val();
    userNameDto = $("#userNameDto").val();
    loadStoreGrid();
    $('#storeDg').datagrid('unselectAll');
}

function resetSearch() {
    $('#search_form').form('clear');
    $('#storeDg').datagrid('load', {});
    $('#storeDg').datagrid('unselectAll');
}

/**
 * 添加新加盟商
 */
function newStore() {
    // 添加onclick属性
    $("#new-save-btn").attr("onclick", "saveFF();");
    $('#ff').form('clear');
    $('#form_userPassword').textbox('enableValidation');
    $('#comfirmPassword').textbox('enableValidation');
    $('#pwd').show();
    $($('#ff_isAutoSend').eq(0)).prop('checked', true);
    $('#dlg').window('open').dialog("setTitle", "添加新加盟商");
    url = 'sysBusiness/saveStore';
}

/**
 * 编辑加盟商
 */
function editStore() {
    $("#new-save-btn").attr("onclick", "saveFF();");
    var row = $('#storeDg').datagrid('getSelected');
    if (!row) {
        $.messager.alert("提示", "请选中一条记录再进行修改！", "info");
        return;
    } else {
        $('#ff').form('load', row);
        $('#userName').textbox('setText', row.user.userName);
        $('#userId').val(row.user.sysUserId);
        $('#form_userPassword').textbox('disableValidation');
        $('#comfirmPassword').textbox('disableValidation');
        $('#pwd').hide();
        $('#dlg').window('open').dialog("setTitle", "编辑加盟商");
        $('#storeUser').combogrid('setValue', row.maintainerId);
        url = 'sysBusiness/editStore';
    }

}

//列表初始化门店
function listUserStore(sysUserId){
	$("#store_list").datagrid({
		url: 'role/findAllUserStore',
		singleSelect: false,
		queryParams : {sysUserId: $('#sysUserId').val()},
		onLoadSuccess: function(data){
			$.each(data.rows, function(index, item){
				if(item.checked){
					$("#store_list").datagrid('checkRow',index);
				}
			})
		}
	})
}


//保存门店
function saveUserStores() {
    var store = $("#storeDg").datagrid('getSelected');
    if (store == null) {
        $.messager.alert('保存用户门店', '请先选择一个加盟商用户', 'info');
    } else {
        var stores = $("#store_list").datagrid('getChecked');
        var storeIds = '';
        $.each(stores, function(index, store) {
            storeIds = storeIds + store.valueField + ',';
        });
        $.post('role/saveUserStores', {'userId': store.user.sysUserId,'storeIds': storeIds},
        function(data) {
            $.messager.alert('保存加盟商用户门店', data, 'info');
        })
    }
}
/**
 * 保存Form表单
 */
function saveFF() {
    $('#ff').form('submit', {
        url: url,
        method: "POST",
        date: serializeForm($('#ff')),
        onSubmit: function() {
            var isValid = $(this).form('validate');
            if (isValid) {
                // 验证通过移除onclick属性
                $('#new-save-btn').removeAttr('onclick');
                return true;
            } else {
                return false;
            }
        },
        success: function(result) {
            $("#dlg").window('close'); // close the schedule form
            $('#storeDg').datagrid('reload'); // reload the schedule data
            $.messager.show({
                title: "提示操作",
                msg: result,
                showType: "slide",
                timeout: 3000
            });
        }
    });
}

function checkoutUser(){
    $.post("security/checkoutUserName",
	    {"userName":$('#userName').textbox("getText"),"userId":$('#userId').val()},
	    function(data){
	if(data==1){
	    $('#userName').textbox("setText",'')
	    $.messager.alert('温馨提示','该用户名已存在请重新输入');
	}
    })
}

/**
 * 清空表单
 */
function clearFF() {
    $('#ff').form('clear');
}