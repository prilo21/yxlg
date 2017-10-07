<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx"  value="${pageContext.request.contextPath}"></c:set>
<div region="north" border="false" title="C2M平台" style="BACKGROUND:#E6E6FA;height: 85px; padding: 1px; overflow: hidden;">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td align="left" style="vertical-align:text-bottom">
				<img src="public/login/images/toplogo-main.png"> 
			</td>
			<td align="right" nowrap>
				<table>
					<tr>
						<td valign="top" height="50">
							<span style="color: #CC33FF">当前用户:</span>
							<span style="color: #666633">${_currentUser.user.userName}</span>
							<span style="color: #CC33FF">职务</span>:<span style="color: #666633">管理员</span>
						</td>
					</tr>
					<tr>
						<td>
							<div style="position: absolute; right: 0px; bottom: 0px;">
								<a href="javascript:void(0);" class="easyui-menubutton"
									menu="#layout_north_kzmbMenu" iconCls="icon-help">控制面板</a><a
									href="/j_spring_security_logout" class="easyui-menubutton"
									menu="#layout_north_zxMenu" iconCls="icon-back">注销</a>
							</div>
							<div id="layout_north_kzmbMenu"
								style="width: 100px; display: none;">
								<div onclick="openwindow('用户信息','userController.do?userinfo')">
									个人信息</div>
								<div class="menu-sep"></div>
								<div onclick="resetPasswordWin()">
									修改密码</div>
							</div>
							<div id="layout_north_zxMenu"
								style="width: 100px; display: none;">
								<div class="menu-sep"></div>
								<div onclick="logout() ;">
									退出系统</div>
							</div></td>
					</tr>
				</table></td>
			<td align="right">&nbsp;&nbsp;&nbsp;</td>
		</tr>
	</table>
</div>


    <div id="dlg" class="easyui-window" style="width:400px;height:300px;padding:10px 20px" data-options="closed:'true'">
    	      <div class="easyui-layout" data-options="fit:true,border:false">
    				      <div data-options="region:'center'">
    				         <form id="ff" method="post" dir="ltr"  >
    				         <table>
    				         	<tr>
	               				 <td>旧密码:</td>
	               				 <td>
	              			 	 <input id="oldpassword" data-options="required: true" name="oldpassword" type="password" class="easyui-validatebox" style="width: 120px; ">
	            				</td>
	            				</tr>
	            				<tr>
	            				<td>
	               				 新密码:
	               				 </td>
	               				 <td>
	              			 	 <input id="password"  data-options="required: true" name="newpassword" type="password" class="easyui-validatebox" style="width: 120px;">
	            				</td>
	            				</tr>
	            				<tr>
	               				 <td>确认密码:</td>
	              			 	<td> <input id="conformpassword" data-options="required: true" name="conformpassword" type="password" class="easyui-validatebox" style="width: 120px;">
	              			 	</td>
	              			 	</tr>
	            		</table>
						</form>
						
						 <div id="dlg-buttons" data-options="region:'south',border:false" style="text-align: right;margin-bottom: 15px;">
	       				     <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="saveForm()">保存</a>
	      				     <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlg').dialog('close')" >取消</a>
	      			</div>
   			  </div>
  		  </div>
	</div>


<script type="text/javascript">
	
	function logout(){location='${ctx}/security/logout';}
	
	//修改密码操作
	
	function resetPasswordWin(){
		$('#ff').form('clear');
		$('#dlg').window({ title: '修改密码'})
		$('#dlg').window('open');
	}
	
	 function saveForm(){
			
		 $('#ff').form('submit',{
			url:'SysUser/resetPassword',
			method : "POST",
			onSubmit:function(){  
	            return $(this).form('validate');  
	        }, 
	        success:function(data){ 
	        	$('#dlg').window('close');
	        	$.messager.show({
		            title:"提示",
		            msg:data,
		            showType:"slide",
		            timeout:3000
		        });
	        	/* if(data=="密码重置成功"){
	        		 $.messager.alert("操作提示", "重置密码成功,请重新登录");
	        			 logout();
	        	} */
	        	
	        }
		})
		 
	 }
	
	
	
</script>