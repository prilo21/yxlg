<%@ page contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" href="public/index/css/icons.css" type="text/css"></link>
<link rel="stylesheet" href="public/index/css/accordion.css" type="text/css"></link>

<script type="text/javascript" src="public/index/js/leftmenu.js"></script>
<div id="nav" class="easyui-accordion" fit="false" border="false" overflow="auto">
	${_currentUser.functionList}
</div>
<!-- /.sidebar-collapse -->