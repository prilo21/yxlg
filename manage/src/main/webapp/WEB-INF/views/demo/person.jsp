<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../../../include/sys-common.jsp" />
<!DOCTYPE html >
<html>
  <head>
  	<style type="text/css">
        #ff{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            width:80px;
        }
        .fitem input{
            width:160px;
        }
    </style>
    
  	<script type="text/javascript">
		$(document).ready(function(){  
	    	loadGrid();  
	    	$('#dlg').window({   
	          width:500,   
	          height:330,   
	          resizable:false  
	          });
	    	$('#dlg').window("close");
		});
		
		function loadGrid()  {
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
	        	pagination:true,
	    	});
		}
	</script>
  </head>
  
  <body>
	<table id="dg" title="人员管理" style="width:100%;height:100%"
			data-options="striped:true,rownumbers:true,singleSelect:true,url:'person/r',method:'get', 
       				 multiSort:true,fit:true,nowrap:false,toolbar:'#toolbar',pagination:'true'">
		<thead>
			<tr>
				<th data-options="field: 'id', hidden:'true'">编号</th>
				<th data-options="field: 'name', width:'200'">姓名</th>
				<th data-options="field: 'address', width:'550'">地址</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="addPerson()">新建</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="editPerson()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onclick="deletePerson()">删除</a>
    </div>
    <div id="dlg" class="easyui-window" style="width:300px;height:600px;padding:10px 20px" data-options="closed:'true',buttons:'#dlg-buttons'">
      <div class="easyui-layout" data-options="fit:true,border:false">
	      <div data-options="region:'center'">
	        <form id="ff" method="post">
	        	<div class="fitem">
	        		<input id="id" name="id" hidden="true">
	        	</div>
	            <div class="fitem">
	                <label>姓名:</label>
	                <input id="name" name="name" class="easyui-validatebox" data-options="required: true" style="width: 250px;"/>
	            </div>
	            <div class="fitem">
	                <label>地址:</label>
	                <input id="address" name="address" class="easyui-validatebox" data-options="required: true" style="width: 250px;"/>
	            </div>
	        </form>
	      </div>
	      <div id="dlg-buttons" data-options="region:'south',border:false" style="text-align: right;margin-bottom: 15px;">
	        <button class="btn-lg-ok" id="save-btn" data-options="iconCls:'icon-ok'" onclick="saveForm()" style="width:90px">保存</button>
	        <button class="btn-lg-cancel" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</button>
	        <button class="btn-lg-reset" onclick="clearForm()">清空</button>
	      </div>
      </div>
    </div>
    <script type="text/javascript">
    	var url;
    	/**
			创建人员
		**/
        function addPerson(){
            $('#ff').form('clear');
            $('#dlg').window('open').dialog('setTitle','新建人员');
            url = 'person/add';
        }
    	
    	/**
    	   	编辑人员
    	**/
    	function editPerson() {
    		$("#save-btn").attr("disabled","true");
    		var row = $('#dg').datagrid('getSelected');
            if (!row) {
               // $.messager.alert("提示","请选中一条记录再进行修改！");
               $.messager.show({
            	   title:"提示操作",
            	   msg:"请选中一条记录再进行修改！",
            	   showType:"slide",
            	   timeout:3000
               });
               return;
            } else {
            	$("#save-btn").removeAttr('disabled');
    			$('#ff').form('clear');
            	$('#dlg').window('open').dialog('setTitle','编辑人员');
            	$('#ff').form('load', row);
            }
            url='person/update';
    	}
    	
    	/**
    	   保存Form表单
    	**/
    	function saveForm() {
    		$('#ff').form('submit', {
				url : url,
				method : "POST",
				success : function(result) {
					$.messager.show({
						title:"提示操作",
						msg:result,
						showType:"slide",
						timeout:3000
					});
					// $.messager.alert('提示', result);
					$("#dlg").window('close'); // close the schedule form
					$('#dg').datagrid('reload'); // reload the schedule data
				}
			});
    	}
    	
    	/**
    	   	删除人员
    	**/
    	function deletePerson() {
    		var row = $('#dg').datagrid('getSelected');
            if (!row) {
               // $.messager.alert('提示', "请选中一条记录再进行删除！");
               $.messager.show({
					title:"提示操作",
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
    				       url:"person/delete/"+row.id,  
    				       //data: {id:row.id},  
    				       success:function(msg){ 
    				    	   $.messager.show({
    								title:"提示操作",
    								msg:msg,
    								showType:"slide",
    								timeout:3000
    							});
    				    	   // $.messager.alert('提示', msg);
    				           loadGrid();
    				       }  
    				   });
    				}
    			});
            }
    	}
    	
    	/**
    	         清空表单
    	**/
        function clearForm() {
            $('#ff').form('clear');
        }
    </script>
  </body>
</html>