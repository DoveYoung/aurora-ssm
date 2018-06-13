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
		<title>500-北极光供应链</title>
		<%@ include file="system/index/headLink-p.jsp"%>
		<link rel="icon" href="static/assets/img/logo_tit.png" type="image/x-icon" />	
	</head>

	<body>
		<%@ include file="system/index/topLogin-center.jsp"%>
		<div id="error-box">
			<div class="error-img"></div>
			<div class="error-info">
				<p>${exception != null ? exception : '抱歉，系统繁忙'}</p>
				<p>你可以尝试以下方式进行操作  </p>
				<i>
					<a class="go-home" href="<%=basePath%>">返回首页</a>
					<a class="contactUs" href="<%=basePath%>footer/contactUs">联系客服</a>
				</i>
			</div>
		</div>
		<%@ include file="system/index/footer.jsp"%>
	</body>

</html>