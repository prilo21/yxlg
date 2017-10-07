<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="../../../include/sys-common.jsp" />
<!DOCTYPE html >
<html>
<head>
    <Meta http-equiv="Content-Type" Content="application/json">
    <script type="text/javascript" src="<%=basePath%>public/easyui/common.js"></script>
    <script type="text/javascript" src="<%=basePath%>public/easyui/plugins/jquery.datagrid.ex.js"></script>

    <style type="text/css">
        .mytable
        {
            padding: 0;
            margin: 10px auto;
            border-collapse: collapse;
            font-family: @宋体;
            empty-cells: show;
        }

        .mytable caption
        {
            font-size: 12px;
            color: #0E2D5F;
            height: 16px;
            line-height: 16px;
            border: 1px dashed red;
            empty-cells: show;
        }

        .mytable tr th
        {
            border: 1px dashed #C1DAD7;
            letter-spacing: 2px;
            text-align: left;
            padding: 6px 6px 6px 12px;
            font-size: 13px;
            height: 16px;
            line-height: 16px;
            empty-cells: show;
        }

        .mytable tr td
        {
            font-size: 12px;
            border: 1px dashed #C1DAD7;
            padding: 6px 6px 6px 12px;
            empty-cells: show;
            border-collapse: collapse;
        }
        .cursor
        {
            cursor: pointer;
        }
        .tdbackground
        {
            background-color: #FFE48D;
        }

    </style>

</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
<%--主界面--%>
<div style="width: 100%; height: 100%">
    <table id="dg" title="视图测试" class="easyui-datagrid"
           data-options=" url:'${ctx}/c2mmanage/demo/test',striped:true, rownumbers:true, singleSelect:false, method:'get',
       				  multiSort:'false', fit:true, nowrap:false, pagination:'false'">
        <thead>
        <tr>
            <th field="trade_order_no" align="center" width="200">订单号</th>
            <th field="actual_pay" align="center" width="100">实际付款金额</th>
            <th field="member_name"align="center"  width="100" >会员名称</th>

        </tr>
        </thead>
    </table>
</div>
</body>

</html>