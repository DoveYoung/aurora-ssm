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
		<%@ include file="../../index/headLink.jsp"%>
		<style>
			.info b{
				width:100px !important;
			}
		</style>
	</head>

	<body id="comfirm-body" class="paySuccess-body" >
		<!--header-->
		<%@ include file="../../index/topLogin-pay.jsp"%>
		<div class="payway-box">
			<div class="order-success">
				<h1>
					<img src="../../static/assets/img/cue-right.gif"/>
					<i>您已成功付款！</i>
				</h1>
				<hr />
				<div class="info"><b>支付宝交易号：</b><i>${trade_no}</i></div>
				<div class="info"><b>订单号：</b><i>${out_trade_no}</i></div>
				<div class="info"><b>实付款：</b><i>¥${total_amount}</i></div>
				<div class="info">您可以<a href="../../customerOrder/orderList">查看已买到的商品</a></div>
			</div>
		</div>
		
		<!--页脚-->
		<%@ include file="../../index/footer.jsp"%>			
	</body>

</html>