<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<script>
		wx.config({
      debug: false,
      appId: '${wxConfigInfo.appId}',
      timestamp: ${wxConfigInfo.timestamp},
      nonceStr: '${wxConfigInfo.nonceStr}',
      signature:  '${wxConfigInfo.signature}',
      jsApiList: [
<c:forEach var="apiItem" items="${wxConfigInfo.jsApiList}">
	"${apiItem}",
</c:forEach>
                  ],
  	});
	</script>