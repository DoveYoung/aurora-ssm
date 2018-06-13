<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="icon" href="<%=basePath%>static/assets/img/logo_tit.png" type="image/x-icon"/>
<link rel="stylesheet" href="<%=basePath%>static/assets/plugin/bootstrap-3.3.7/css/bootstrap.min.css">
<!--<link href="//cdn.bootcss.com/bootstrap/4.0.0-alpha.6/css/bootstrap-grid.min.css" rel="stylesheet" />-->
<link rel="stylesheet" type="text/css" href="<%=basePath%>static/assets/css/animate.min.css" />
<c:set var="base_url" value="<%=basePath%>"></c:set>
<c:if test="${base_url != 'http://localhost:8080/aurorascm/'}">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>static/assets/css/admin.css" />
</c:if>
<c:if test="${base_url == 'http://localhost:8080/aurorascm/'}">
	<link rel="stylesheet/less" type="text/css" href="<%=basePath%>static/assets/css/admin.less" />
	<script src="<%=basePath%>static/assets/js/less.js" language="javascript"></script>
</c:if>
<script src="<%=basePath%>static/assets/js/common.js" language="javascript"></script>
<script src="<%=basePath%>static/assets/js/jquery-3.2.1.min.js"></script>
<script src="<%=basePath%>static/assets/plugin/echo/echo.min.js"></script>
<script src="<%=basePath%>static/assets/plugin/layer/layer.js"></script>
<script src="<%=basePath%>static/assets/plugin/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script src="<%=basePath%>static/assets/js/vue.js"></script>
<script src="<%=basePath%>static/assets/js/vue-resource.js"></script>	
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?ba1be72b0d777b181425157f7d67a676";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>
<!-- 中国版  Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-114912233-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-114912233-1');
</script>
<style>
	[v-cloak] {
	  	display: none !important;
	}
</style>

