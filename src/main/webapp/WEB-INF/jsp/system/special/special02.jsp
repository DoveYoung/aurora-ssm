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
		<title>双12备货-北极光供应链</title>
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
		<!--b3e3e5-->
		<!--header-->
		<%@ include file="../index/topNotLogin.jsp"%>
		<div id="special">
			<div class="special special02" style="background-image:url(${specialBackground})">
				<div id="m-result">
					<c:forEach items="${specialGoods}" var="list" varStatus="vs">
						<div class="title tit0${vs.index+1}"></div>
						<ul class="m-result-box">	
						<c:forEach items="${list}" var="good" varStatus="vs">
							<li class="m-goods">
								<c:if test="${good.deposit != '100'}">
									<div class="marks marks-deposit"></div>
								</c:if>
								<c:if test="${good.brand_id == '70' || good.brand_id == '76'}">
									<div class="marks marks-overseas"></div>
								</c:if>
								<div class="goodswrap">
									<a target="_blank" href="<%=basePath%>detailPage/goGoodsdetail?category2ID=${good.category2_id}&goodsID=${good.goods_id}" class="m-goodsImg" title="${good.goods_name}">
										<img src="../static/assets/img/blank.gif" data-echo="${good.main_map}" />
										<span>${good.goods_sign}</span>
										<i class="sale">销量：<b>${good.goods_sales}</b></i>
									</a>
									<h3>剩余库存：<i>${good.goods_stock}</i></h3>
									<div class="m-goodsInfo">
										<i>￥<b>${good.goods_price2}</b></i>
										<span>
											市场售价：￥<b>${good.market_price}</b>
										</span>
									</div>
									<h4 class="m-delivery"><a target="_blank" href="<%=basePath%>detailPage/goGoodsdetail?category2ID=${good.category2_id}&goodsID=${good.goods_id}" title="${good.goods_name}"><c:if test="${good.ship_type == 1}">【保税仓直邮】</c:if><c:if test="${good.ship_type == 2}">【海外直邮】</c:if><c:if test="${good.ship_type == 3}">【国内现货】</c:if>${good.goods_name}</a></h4>
								</div>
							</li>
						</c:forEach>
						</ul>
					</c:forEach>
				</div>
			</div>
		</div>
		<!--页脚-->
		<%@ include file="../index/footer.jsp"%>
		<script>
			Echo.init({
				offset:100,
				throttle:0
			});
		</script>
	</body>
</html>
