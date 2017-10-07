$(function() {
    $('#tg').treegrid({
        idField: 'id',
        treeField: 'text',
        title: '机构管理',
        url: 'department/find',
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
            {field: 'text',title: '机构名称',width: 100},
            {field: 'src',title: '机构描述',width: 60}
        ]],
        onClickRow: function(rowData) {
            rowid = rowData.id;
            gridname = 'tg';
        }
    });
    $('#dlg').window({
        width:500,
        height:330,
        minimizable:false,
        resizable:false,
        collapsible:false
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

//下部js
var url;
/**
 创建新表单
 **/
function newDepartment(){
    $('#ff').form('clear');
    $('#dlg').window('open').dialog('setTitle','新建');
    // 初始化上级机构
    $("#pdepartment").combotree({
        idField: 'id',
        treeField: 'text',
        url:'department/find'
        //选择树节点
    });
    url = 'department/add';
}
/**
 编辑菜单
 **/
function editDepartment() {
    $("#save-btn").attr("disabled","true");
    var row = $('#tg').treegrid('getSelected');
    if (!row) {
        $.messager.show({
            title:"提示",
            msg:"请选中一条记录再进行修改！",
            showType:"slide",
            timeout:3000
        });
        return;
    } else {
        $("#save-btn").removeAttr('disabled');
        $('#ff').form('clear');
        $('#ff').form('load', {
            name: row.text,
            description:row.src
        });
        //初始化combotree
        $("#pdepartment").combotree({
            idField: 'id', //设置哪一个字段为id
            treeField: 'text',  //设置数据中哪一个的显示的字段
            url:'department/find' //表示在哪里获取数据
            //选择树节点
        })
        // 表单中父目录项进行赋值
        $.ajax({
            url: 'department/'+row.id,
            //根据选中的节点去获取这个节点的所有的信息
            success: function(data) {
                $("#sysDepartmentId").val(data.sysDepartmentId);
                //$("#name").textbox("setValue",data.name);
                //如果说这个节点有父，就将这个节点的数值赋值给父目录显示控件
                if (data.pdepartment!=null && data.pdepartment!="") {
                    $('#pdepartment').combotree('setValue', data.pdepartment.sysDepartmentId);
                    $('#pdepartment').combotree('setText', data.pdepartment.name);
                    //else 表示没有父节点
                } else {
                    //初始化一个tree
                    $("#pdepartment").combotree({
                        idField: 'id', //设置哪一个字段为id
                        treeField: 'text',  //设置数据中哪一个的显示的字段
                        url:'department/find' //表示在哪里获取数据
                        //选择树节点
                    })
                }
                //$('#pdepartment').combobox("disable");
                //$("#description").val(data.description);
            }
        });

        $('#dlg').window('open').dialog('setTitle','编辑机构');

        // 根据row。id查询数据

    }
    url='department/update';
}

/**
 删除
 **/
function deleteDepartment() {
    var row = $('#tg').treegrid('getSelected');
    if (!row) {
        $.messager.show({
            title:"提示",
            msg:"请选中一条记录再进行删除！",
            showType:"slide",
            timeout:3000
        });
        return;
    } else {
        $.messager.confirm("提示", "是否删除选中的数据?", function (r) {
            if(r){
                $.ajax({
                    type:"GET",
                    url:"department/delete/"+row.id,
                    success:function(msg){
                        $.messager.show({
                            title:"提示操作",
                            msg:msg,
                            showType:"slide",
                            timeout:3000
                        });
                        $('#tg').treegrid('reload'); // reload the menu data
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
    if($('#ff').form('validate')){
        $("#save-btn").attr("disabled","true");
        $('#ff').form('submit', {
            url : url,
            method : "POST",
            onSubmit : function() {
                return $(this).form('validate');
            },
            success : function(result) {
                $.messager.show({
                    title:"提示操作",
                    msg:result,
                    showType:"slide",
                    timeout:3000
                });
                $("#save-btn").removeAttr('disabled');
                $("#dlg").window('close'); // close the menu form
                $('#tg').treegrid('reload'); // reload the menu data
            }
        });
    }else{
        $.messager.alert('提示', "请按照要求填写正确的数据！");
    }

}
function clearForm() {
    $('#ff').form('clear');
}