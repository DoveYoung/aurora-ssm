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
		<title>微仓服务</title>
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
				<h2>微仓服务</h2>
				<h3>微仓是由本站提供的免费仓储服务。您全款购买或通过定金购买的商品，在未发货前都将存放在您自己的个人微仓中。您可以随时对商品进行自由组合批量发货，我们将以最快的速度把货物送到您的客户手中</h3>
				<div class="attention">
					<h4>注意事项</h4>
					<h6>1.微仓提供自下单之日起两个月的免费仓储时间，到期前我们会邮件或电话提醒您</h6>
					<h6>2.货物进入微仓不需要支付邮费，但是从微仓发货时需要支付邮费</h6>
					<h6>3.根据海关政策，微仓单笔发货金额不得超过¥2000（含运费，税费等），超过¥2000的金额订单我平台不予发货</h6>
					<h6>4.根据海关政策，单张身份证一年海购限额为¥20000，对于身份证超过限额的订单，海关不予通过</h6>
					<h6>5.微仓的货物在交易被取消后，被取消的数量会恢复到微仓库存中</h6>
					<h6>6.定金购买的商品从微仓发货时要补足尾款和邮费后才能发货</h6>
					<h6>7.微仓的发货请求，系统一旦接单之后，将不可取消订单，任何疑问请联系客服解决</h6>
				</div>
			</div>
		</div>
		<!--页脚-->
		<%@ include file="../index/footer.jsp"%>
		<!--右侧固定-->
		<%@ include file="../index/rightFixed.jsp"%>
	</body>	
</html>