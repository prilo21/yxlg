<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../../../include/sys-common.jsp" />

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
	<script type="text/javascript" src="././public/system/js/department.js"></script>
  </head>
  
  <body>
	<div id="tg" title="机构管理" style="width:100%;height:100%"></div>
	<div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="newDepartment()">新建</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="editDepartment()">编辑</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onclick="deleteDepartment()">删除</a>
    </div>
    <div id="dlg" class="easyui-window" style="width:300px;height:600px;padding:10px 20px" data-options="closed:'true',buttons:'#dlg-buttons'">
      <div class="easyui-layout" data-options="fit:true,border:false">
	      <div data-options="region:'center'">
	        <form id="ff" method="post">
	        	<div class="fitem">
	        		<input id="sysDepartmentId" name="sysDepartmentId" type="hidden">
	        	</div>
	            <div class="fitem">
	                <label>机构名称:</label>
	                <input id="name" name="name" class="easyui-validatebox" data-options="required: true" style="width:180px;"/>
	            </div>
	            <div class="fitem" id="parentDepartment">
	                <label>父级机构:</label>
	                <input id="pdepartment" name="pdepartment.sysDepartmentId" style="width:200px;">
	            </div>
	            <div class="fitem">
	            	<label>机构描述:</label>
	            	<%--<textarea rows="3" cols="1" id="description" name="description" style="width:180px;"></textarea>--%>
	            	<input id="description" name="description" class="easyui-textbox" style="width:180px;"/>
	            </div>
	        </form>
	      </div>
	      <div id="dlg-buttons" data-options="region:'south',border:false" style="text-align: right;margin-bottom: 15px;">
	        <button class="easyui-linkbutton" id="save-btn" data-options="iconCls:'icon-ok'" onclick="saveForm()" style="width:90px">保存</button>
	        <button class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</button>
	      </div>
      </div>
    </div>
  </body>
</html>