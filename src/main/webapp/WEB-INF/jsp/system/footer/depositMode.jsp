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
		<title>定金模式</title>
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
				<h2>定金模式</h2>
				<h3>对于支持定金购买的商品，您可以选择预支付货款的一部分作为定金，我们将以当前条件下的价格为您锁定该批次库存，并把货物加入您的微仓。在您需要从微仓发货时，补足该商品的尾款和运费即可发货</h3>
				<div class="attention">
					<h4>注意事项</h4>
					<h6>1.仅限支持定金模式的商品才可以使用定金付款，一旦选用定金付款即视为您定下该商品，我们为您锁定该商品库存，定金不能以任何理由退还。</h6>
					<h6>2.定金付款的商品只能选择进入微仓，不可直接发货</h6>
					<h6>3.定金付款产品从微仓发货时需支付尾款和运费</h6>
					<h6>4.定金购买的商品以支付定金时的价格为准，不受后期商品价格波动影响</h6>
					<h6>5.通过定金购买的商品库存锁定期为2个月。即，您需在2个月内陆续发货完毕，到期后未发货的剩余商品需一次性补足尾款。我们的客服会在到期前通过邮件或电话提醒您支付尾款完成购买，逾期未支付尾款该商品会失效，无法发货，定金不予退还</h6>
				</div>
			</div>
		</div>
		<!--页脚-->
		<%@ include file="../index/footer.jsp"%>
		<!--右侧固定-->
		<%@ include file="../index/rightFixed.jsp"%>
	</body>	
</html>