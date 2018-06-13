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
		<title>退货须知</title>
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
				<h2>退货须知</h2>
				<h3>在本站购买的商品后，您可以在确认收件后30天内申请退货，跨境商品（涉及海关通关手续及时限）可以在确认收件后7天内申请退货；不支持7天无理由退货，请留意商品页面上的特殊说明，您可以<a href="javascript:;">联系客服</a>提交退货申请</h3>
				<div class="attention">
					<h4>配送方式</h4>
					<h6>1.凡是在本站购买的商品，在系统接单前都可以取消订单，已支付的金额会在三个工作日内退回您的原账户</h6>
					<h6>2.由国家法律规定的功能性故障或商品质量问题，经由生产厂家指定或特约售后服务中心检测确定，并出具检测报告或经北极光售后确认属于商品质量问题的商品，可以联系客服进行退款或偿</h6>
					<h6>3.在运输过程中造成的损坏、漏液、破碎、性能故障，经售后人员核查情况属实后，客服会对订单进行退款或补偿</h6>
					<h6>4.商品缺件的情况经售后人员核实后，客服会对订单进行补发</h6>
					<h6>5.本站不支持客户因款式，颜色或个人喜好等原因的退换货</h6>
					<h6>6.跨境产品均不支持无理由退换货</h6>
				</div>
			</div>
		</div>
		<!--页脚-->
		<%@ include file="../index/footer.jsp"%>
		<!--右侧固定-->
		<%@ include file="../index/rightFixed.jsp"%>
	</body>	
</html>