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
<html style="height:600px !important">

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>支付成功</title>
		<%@ include file="../index/headLink.jsp"%>
	</head>

	<body id="comfirm-body" class="paySuccess-body" >
		<!--header-->
		<div id="search-header">
			<div class="search-header-box">
				<h3 class="top">
					<%@ include file="../index/topNotLogin.jsp"%>
				</h3>
			</div>
			<div id="logo-index">
				<a href="javascript:;">
					<img src="../static/assets/img/blank.gif" data-echo="../static/assets/img/logo.gif"/>
				</a>
			</div>	
		</div>	
		<!--all-->
		<!--deposit-->
		<c:if test="${pdm.pay_type == '1'}">
			<%@ include file="paySuccess/all.jsp"%>
		</c:if>
		<c:if test="${pdm.pay_type == '2'}">
			<%@ include file="paySuccess/deposit.jsp"%>
		</c:if>
		<!--页脚-->
		<%@ include file="../index/footer.jsp"%>
	</body>

</html>