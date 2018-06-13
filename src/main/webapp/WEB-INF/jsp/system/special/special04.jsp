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
		<title>海外直邮-北极光供应链</title>
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
		<<%@ include file="../index/topNotLogin.jsp"%>
		<div id="special">
			<div class="special special04" style="background-image:url(${specialBackground})">
				<div id="m-result">
					<ul class="m-result-box list01">
						<i class="circle"></i>
						<i class="circle"></i>
						<i class="circle"></i>
						<i class="circle"></i>
						<i class="rectangle"></i>
						<i class="rectangle rectangle02"></i>
						<b style="display:block;height:1px;"></b>
						<div class="title"><i>&nbsp;</i><b>限时打折</b><b>每日更新</b><i class="i_02">&nbsp;</i></div>
						<div class="tit">每日12点准时更新商品</div>
						<c:forEach items="${specialGoods[0]}" var="good" varStatus="vs">
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
										<span></span>
									</a>
									<h3>剩余库存：<i>${good.goods_stock}</i></h3>
									<div class="m-goodsInfo">
										<i>￥<b>${good.goods_price2}</b></i>
										<span>
											原价：￥<b>${good.goods_price2 / 0.7}</b>
										</span>
									</div>
									<h4 class="m-delivery"><a target="_blank" href="<%=basePath%>detailPage/goGoodsdetail?category2ID=${good.category2_id}&goodsID=${good.goods_id}" title="${good.goods_name}"><c:if test="${good.ship_type == 1}">【保税仓直邮】</c:if><c:if test="${good.ship_type == 2}">【海外直邮】</c:if><c:if test="${good.ship_type == 3}">【国内现货】</c:if>${good.goods_name}</a></h4>
								</div>
							</li>
						</c:forEach>
					</ul>
					<ul class="m-result-box list02">
						<c:forEach items="${specialGoods[1]}" var="good" varStatus="vs">
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
									</a>
									<h3>剩余库存：<i>${good.goods_stock}</i></h3>
									<div class="m-goodsInfo">
										<i>￥<b>${good.goods_price2}</b></i>
										<span>
											原价：￥<b>${good.goods_price2 / 0.8}</b>
										</span>
									</div>
									<h4 class="m-delivery"><a target="_blank" href="<%=basePath%>detailPage/goGoodsdetail?category2ID=${good.category2_id}&goodsID=${good.goods_id}" title="${good.goods_name}"><c:if test="${good.ship_type == 1}">【保税仓直邮】</c:if><c:if test="${good.ship_type == 2}">【海外直邮】</c:if><c:if test="${good.ship_type == 3}">【国内现货】</c:if>${good.goods_name}</a></h4>
								</div>
							</li>
						</c:forEach>
					</ul>
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