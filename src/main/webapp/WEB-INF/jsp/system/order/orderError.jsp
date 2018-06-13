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
		<title>订单被关闭</title>
		<%@ include file="../index/headLink.jsp"%>
	</head>

	<body id="product-body">

		<!--header-->
		<div id="search-header">
			<div class="search-header-box">
				<h3 class="top">
					<%@ include file="../index/topNotLogin.jsp"%>
				</h3>
			</div>
			<%@ include file="../index/headerSearch.jsp"%>
		</div>
		<nav id="topTableBox">
			<ul id="topTable">
				<li class="topTable-active">所有分类</li>
				<li>保税仓直邮</li>
				<li>海外直邮</li>
				<li>国内现货</li>
			</ul>
		</nav>
		<div id="order-error">
			<img src="../static/assets/img/blank.gif" data-echo="static/assets/img/emptyImg.jpg"/>
			<div>
				<h3>订单被关闭，可能因为较长时间未支付或其他特殊原因</h3>
				<h4>
					<a target="_blank" href="<%=basePath%>footer/contactUs">联系客服</a>
					<!--<a href="javascript:;" class="my-order">我的订单</a>-->					
				</h4>
			</div>
		</div>
		<hr />
		<%@ include file="../index/footer.jsp"%>
	</body>
</html>