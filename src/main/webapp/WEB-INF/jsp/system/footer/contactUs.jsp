<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>联系我们</title>
		<%@ include file="../index/headLink.jsp"%>
		<script>
			function search() {
				if($('#keyword').val() == ''){
					return location.reload();
				}
				$("#searchFrom").submit();
			}
		</script>
	</head>

	<body id="product-body">
		<!--header-->		
		<%@ include file="../index/topNotLogin.jsp"%>
		<div id="introduce-page">
			<%@ include file="footerMenuList.jsp"%>
			<div class="persRight">
				
				<h2>联系我们</h2>
				<h3>客服电话：0571-8791&nbsp;6639&nbsp;</h3>
				<h3>客服邮箱：info@loiitrade.com</h3>
				<h3>官方网址：<a href="http://www.loiitrade.com/" target="_blank">http://www.loiitrade.com/</a></h3>
				<h3>官方微信：loiishop</h3>
				<h3>客服QQ：<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=2303550195&site=qq&menu=yes">2303550195</a></h3>
				<h3>工作时间：10：00 -- 19：00（周一至周五）</h3>
				<h3>浙江省杭州市下城区跨贸小镇10幢601</h3>
				<div class="attention">
					<h4>我们的服务</h4>
					<h6>原产地集采批发</h6>
					<h6>海外直邮/保税仓代发</h6>
					<h6>个性化商品定向挖掘开发</h6>
					<h6>多重物流解决方案</h6>
				</div>
			</div>
		</div>
		<!--页脚-->
		<%@ include file="../index/footer.jsp"%>
		<!--右侧固定-->
		<%@ include file="../index/rightFixed.jsp"%>
	</body>	
</html>