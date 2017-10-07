
var originalParentId = "";

function turnOut(){
    $.messager.confirm("提示", "修改权限需要重新登录才能生效", function(r){
        if(r){
            window.top.location.href="<%=basePath%>";
        }
    })
}
function ee(){
    $('#tg').treegrid({
        idField: 'id',
        treeField: 'text',
        title: '权限管理',
        url: 'sysRes/find',
        fit: true,
        loadMsg: '数据加载中...',
        pagination: false,
        sortOrder: 'asc',
        rownumbers: true,
        singleSelect: true,
        fitColumns: true,
        showFooter: true,
        toolbar:'#toolbar',
        frozenColumns: [[]],
        columns: [[
            {field: 'id',title: '编号',width: 30,hidden: true},
            {field: 'text',title: '名称',width: 100},
            {field: 'type',title: '类型',width: 30},
            {field: 'src',title: '地址',width: 60},
            {field: 'order',title: '顺序',width: 60}
        ]]
    });
}
$(function() {
    $('#tg').treegrid({
        idField: 'id',
        treeField: 'text',
        title: '权限管理',
        url: 'sysRes/find',
        fit: true,
        loadMsg: '数据加载中...',
        pagination: false,
        sortOrder: 'asc',
        rownumbers: true,
        singleSelect: true,
        fitColumns: true,
        showFooter: true,
        toolbar:'#toolbar',
        frozenColumns: [[]],
        columns: [[
            {field: 'id',title: '编号',width: 30,hidden: true},
            {field: 'text',title: '名称',width: 100},
            {field: 'type',title: '类型',width: 30},
            {field: 'src',title: '地址',width: 60},
            {field: 'order',title: '顺序',width: 60}
        ]],
        onClickRow: function(rowData) {
            rowid = rowData.id;
            gridname = 'tg';
        }
    });
    $('#dlg').window({
        width:500,
        height:330,
        modal : true,
        resizable:false
    });
    $('#dlg').window("close");
});
function reloadTable() {
    $('#' + gridname).treegrid('reload');
}
function reloadmenuList() {
    $('#tg').treegrid('reload');
}
function getmenuListSelected(field) {
    return getSelected(field);
}
function getSelected(field) {
    var row = $('#' + gridname).treegrid('getSelected');
    if (row != null) {
        value = row[field];
    } else {
        value = '';
    }
    return value;
}
function getmenuListSelections(field) {
    var ids = [];
    var rows = $('#tg').treegrid('getSelections');
    for (var i = 0; i < rows.length; i++) {
        ids.push(rows[i][field]);
    }
    ids.join(',');
    return ids
};

function typeChange(){
    var menuType=$("#type").combobox("getValue");
    if(menuType=="menu"){
        $("#menuLevel").show();
        //$("#level").combobox({
        //    required:true
        //});
        $("#level").combobox("clear");
    }
    else{
        $("#menuLevel").hide();
        $("#parentMenu").hide();
        $("#level").combobox("clear");
        $("#pid").combobox("clear");
        $("#level").combobox({
            required:false
        });
        $("#pid").combobox({
            required:false
        });
    }
}

//下方js
var url;

$.extend($.fn.validatebox.defaults.rules,{
    order: {//param的值为[]中值
        validator: function (value, param) {
            if (value.length<0 || value.length>3 )
            {
                $.fn.validatebox.defaults.rules.order.message = '必须在' + 0 + '至' + 999 + '之间';
                return false;
            } else {
                if(!/^[0-9]*[0-9][0-9]*$/.test(value)) {
                    $.fn.validatebox.defaults.rules.order.message = '不可用的顺序格式';
                    return false;
                } else {
                    return true;
                }
            }
        }, message: ''
    }
});

/**
 创建新表单
 **/
function newMenu(){
    $('#ff').form('clear');
    $('#ff').get(0).reset();
    $("#parentMenu").hide();
    $("#menuLevel").hide();
    $('#dlg').window('open').dialog('setTitle','新建');
    url = 'sysRes/save';
}

/**
 编辑菜单
 **/
function editMenu() {
    //$("#save-btn").attr("disabled","true");

    //首先加载父类类型的type
    $("#pid").combobox({
        url:'sysRes/getFirstLevelRes',
        valueField: 'sysResourceId',
        textField: 'resName',
        editable: false,
        missingMessage:'父菜单名称'
    });

    var row = $('#tg').treegrid('getSelected');
    if (!row) {
        $.messager.alert('提示', "请选中一条记录再进行修改！");
        return;
    } else {
        //$("#save-btn").removeAttr('disabled');

        $('#ff').form('clear');
        // 表单赋值
        $("#id").val(row.id);
        $("#name").textbox("setValue",row.text);
        if(row.parentId=="") {
            $("#parentMenu").hide();
            $("#level").combobox("setValue",'1');

            $("#pid").combobox({
                required:false
            });
        } else {
            $("#level").combobox("setValue",'2');
            $("#menuLevel").show();
            $("#parentMenu").show();
        }
        $("#pid").combobox("setValue",row.parentId);

        //在这里将父id进行保存
        originalParentId = $("#pid").combobox("getValue");


        $("#resUrl").textbox("setValue",row.src);
        $("#type").combobox("setValue", row.type);
        $("#showOrder").textbox("setValue",row.order);
        $('#dlg').window('open').dialog('setTitle','编辑菜单');
    }
    $('#tg').treegrid('unselectAll');
    url='sysRes/update';
}

/**
 删除菜单
 **/
function deleteMenu() {
    var row = $('#tg').treegrid('getSelected');
    if (!row||row==null) {
        $.messager.alert('提示', "请选中一条记录再进行删除！");
        return;
    } else {
        $.messager.confirm("提示", "确定要删除吗？", function (flag) {
            if(flag){
                $.ajax({
                    type:"post",
                    url: "sysRes/del",
                    data: {"id":row.id},
                    success:function(mes){
                        $.messager.alert('提示', mes,function(){ turnOut();});
                        $('#tg').treegrid('reload');
                        $('#tg').treegrid('unselectAll');
                    }
                });
            }
        });
    }
}

/**
 保存Form表单
 **/
function saveForm() {
//<%--$('#save-btn').attr({"disabled":"disabled"});--%>

    $('#ff').form('submit', {
        url : url,
        method : "POST",
        onSubmit : function() {
            return $(this).form('validate');
        },
        success : function(result) {
            //$('#save-btn').removeAttr("disabled");
            $.messager.alert('提示',result);
            if(result != "名称已存在"){
                $("#dlg").window('close'); // close the menu form
                $('#tg').treegrid('reload'); // reload the menu data
            }
        }
    });

}

/**
 初始化父菜单菜单
 **/
function initParentMenu() {
    // 得到菜单等级
    var level = $("#level").combobox("getValue");
    // 一级菜单
    if (level==1) {
        // 隐藏父级菜单
        $("#parentMenu").hide();
        // 清空父菜单值
        // 父菜单赋值
        $("#pid").combobox({
            url:'sysRes/getFirstLevelRes',
            valueField: 'sysResourceId',
            textField: 'resName',
            editable: false,
            missingMessage:'父菜单名称'
        });


        // 二级菜单
    } else if(level==2) {
        // 显示父级菜单
        $("#parentMenu").show();
        //$("#menuLevel").show();
        //vat $("#menuLevel").getText();

        // 父菜单赋值
        $("#pid").combobox({
            url:'sysRes/getFirstLevelRes',
            valueField: 'sysResourceId',
            textField: 'resName',
            editable: false,
            missingMessage:'父菜单名称'
        });

        //再次编辑时候，将父菜单的数据赋值
        if(originalParentId != ""){
            $("#pid").combobox("setValue",originalParentId);
        }
    }
}
/**
 清空表单
 **/
function clearForm() {
    $('#ff').form('clear');
}







