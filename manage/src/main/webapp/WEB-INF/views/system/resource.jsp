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
	<script type="text/javascript" src="././public/system/js/resource.js"></script>
  </head>
  
  <body>
	<div id="tg" title="权限管理" style="width:100%;height:100%"> </div>
	<div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="newMenu()">新建</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="editMenu()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onclick="deleteMenu()">删除</a>
    </div>
    <div id="dlg" class="easyui-window" style="width:300px;height:600px;padding:10px 20px" data-options="minimizable:false,maximizable:false,collapsible:false,closed:true, resizable:false ,buttons:'#dlg-buttons'">
      <div class="easyui-layout" data-options="fit:true,border:false">
	      <div data-options="region:'center'">
	        <form id="ff" method="post">
	        	<div class="fitem">
	        		<input id="id" name="sysResourceId" type="hidden">
	        	</div>
	            <div class="fitem">
	                <label>名称:</label>
	                <input id="name" name="resName" class="easyui-textbox" data-options="required: true,validType:['length[0,20]'],missingMessage:'不允许为空！'"  style="width:180px;"/>
	            </div>
	            <div class="fitem">
	            	<label>类型名称:</label>
	            	<select id="type" name="resType" class="easyui-combobox" data-options="editable: false,required: true,onSelect:function(){typeChange();}" style="width:200px" >
	            		<option value="menu">菜单</option>
	            	</select>
	            </div>
	            <div class="fitem" id="menuLevel">
	                <label>等级:</label>
	                <select id="level" name="leve" class="easyui-combobox" data-options="required:true,editable: false,onSelect:function(){initParentMenu();}" style="width:200px;">
	                	<option value="1">一级菜单</option>
	                	<option value="2">二级菜单</option>
	                </select>
	            </div>
	            <div class="fitem" id="parentMenu">
	                <label>父菜单:</label>
	                <input id="pid" name="presource.sysResourceId" class="easyui-combobox" style="width:200px;">
	            </div>
	            <div class="fitem">
	            	<label>地址:</label>
	            	<input id="resUrl" name="resUrl" class="easyui-textbox"  data-options="validType:['length[0,100]']" style="width:180px;"/>
	            </div>
	            <div class="fitem">
	            	<label>顺序:</label>
	            	<input id="showOrder" name="showOrder"   class="easyui-textbox" data-options="required: true,validType:{order:[0,999]}, missingMessage:'该输入项为必填项'"  style="width:180px;">
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