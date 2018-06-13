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
		<title>关于我们</title>
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
				<h2>关于我们</h2>
				<h3>北极光供应链隶属于杭州络驿电子商务有限公司，致力于为中小商家客户提供个性化的跨境货源解决方案。借助北极光提供的商品行业数据分析为您的采购决策保驾护航，通过我们的“批发询价”功能，为您提供极富竞争力的“门到门”报价方案，同时，北极光独有的“定金买货”及“个人微仓”模式，为您省下大笔流动资金的同时提供灵活的发货操作方式。</h3>
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