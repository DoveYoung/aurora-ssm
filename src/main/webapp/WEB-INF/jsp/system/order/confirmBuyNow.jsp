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
		<title>立即购买结算</title>
		<%@ include file="../index/headLink.jsp"%>
	</head>

	<body id="comfirm-body">
		<!--header-->
		<div id="search-header">
			<div class="search-header-box">
				<h3 class="top">
					<%@ include file="../index/topNotLogin.jsp"%>
				</h3>
			</div>
			<div id="logo-index">
				<a href="<%=basePath%>">
					<img src="../static/assets/img/blank.gif" data-echo="../static/assets/img/logo.gif"/>
				</a>
			</div>
		</div>
		<c:if test="${sType == 'fsflb'}">
			<!--buyAll-->
			<%@ include file="buyNow/buyAll.jsp"%>
		</c:if>
		<c:if test="${sType == 'dsflb'}">
			<!--buyDeposit-->
			<%@ include file="buyNow/buyDeposit.jsp"%>
		</c:if>
		<hr />
		<%@ include file="../index/footer.jsp"%>
	</body>

</html>