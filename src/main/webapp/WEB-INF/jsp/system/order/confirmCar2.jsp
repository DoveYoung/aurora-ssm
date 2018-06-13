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
		<title>购物车结算</title>
		<%@ include file="../index/headLink.jsp"%>
		<style>
			.b-post {
				float: right;
				margin-right: 30px;
			}
		</style>
	</head>

	<body id="comfirm-body">

		<!--header-->

		<%@ include file="../index/topLogin.jsp"%>
		<!--carAll-->
		<!--收货人信息-->
		<!--<div id="info-box">
			<h1>收货人信息</h1>
			<div class="info-box-l" style="height:300px">
				<div id="address-box">
					<ul id="addr-ul" style="border:none">
						<li class="address-list">
							<a href="javascript:;" class="address-check address-active" style="pointer-events:none;">
								<span><i>寄送给</i></span>
								<em></em>
								<i style="width:260px">北极光微仓（免费仓储，批量发货）</i>
							</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="info-box-r">
				<div class="inpt-box">
					<i>收货人：</i>
					<input readonly="readonly" id="c-name" value="${map.wc1Address.name}" />
				</div>
				<div class="inpt-box">
					<i>电话号码：</i>
					<input readonly="readonly" id="c-mobile" value="${map.wc1Address.mobile}" />
				</div>
				<div class="inpt-box inpt-box-02">
					<i class="left-01">省份：</i>
					<input readonly="readonly" id="c-province" value="${map.wc1Address.province}" />
				</div>
				<div class="inpt-box inpt-box-03">
					<i>城市：</i>
					<input readonly="readonly" id="c-city" value="${map.wc1Address.city}" />
				</div>
				<div class="inpt-box inpt-box-03">
					<i>地区：</i>
					<input readonly="readonly" id="c-area" value="${map.wc1Address.area}" //>
				</div>
				<div class="inpt-box inpt-box-01">
					<i>详细地址：</i>
					<textarea readonly="readonly" id="detail-address">${map.wc1Address.detail_address}</textarea>
				</div>
			</div>
		</div>-->
		<div class="carBox">
			<div id="good-category">
				<a href="javascript:;">确认商品信息</a>
			</div>
			<div class="car-head">
				<div class="col col2">商品信息</div>
				<div class="col col3">数量</div>
				<div class="col col4">单价（元）</div>
				<div class="col col5">重量（千克）</div>
				<div class="col col6">金额（元）</div>
			</div>
			<!--德国直邮-->
			<c:if test="${map.hCartFPMath.size() > 0}">
				<div id="German" class="mail">
					<i>海外直邮</i>
					<b class="b-quote"></b>
					<b class="b-post"></b>
				</div>
				<div class="goods">
					<ul class="goodsBox" id="German-box">
						<c:forEach items="${map.hCartFPMath}" var="good">
							<li class="gooditm">
								<div class="hidden">${good.cartID}</div>
								<div class="col col2">
									<a href="javascript:;" class="goodImg">
										<img src="../static/assets/img/blank.gif" data-echo="${good.mainMap}" />
									</a>
									<span>${good.goodsName}</span>
								</div>
								<div class="col col3">${good.buyNum}</div>
								<div class="col col4">${good.goodsPrice}</div>
								<div class="col col5">${good.weight * good.buyNum}</div>
								<div class="col col6 total-list">${good.uMoney}</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>

			<!--保税仓直邮-->
			<c:if test="${map.bCartFPMath.size() > 0}">
				<div id="wareHouse" class="mail">
					<i>保税仓代发</i>
					<b class="b-quote"></b>
					<b class="b-post"></b>
				</div>
				<div class="goods">
					<ul class="goodsBox" id="wareHouse-box">
						<c:forEach items="${map.bCartFPMath}" var="good">
							<li class="gooditm">
								<div class="hidden">${good.cartID}</div>
								<div class="col col2">
									<a href="javascript:;" class="goodImg">
										<img src="../static/assets/img/blank.gif" data-echo="${good.mainMap}" />
									</a>
									<span>${good.goodsName}</span>
								</div>
								<div class="col col3">${good.buyNum}</div>
								<div class="col col4">${good.goodsPrice}</div>
								<div class="col col5">${good.weight * good.buyNum}</div>
								<div class="col col6 total-list">${good.uMoney}</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
			<!--国内现货-->
			<c:if test="${map.gCartFPMath.size() > 0}">
				<div id="theSpot" class="mail">
					<i>一般贸易</i>
				</div>
				<div class="goods">
					<ul class="goodsBox" id="theSpot-box">
						<c:forEach items="${map.gCartFPMath}" var="good">
							<li class="gooditm">
								<div class="hidden">${good.cartID}</div>
								<div class="col col2">
									<a href="javascript:;" class="goodImg">
										<img src="../static/assets/img/blank.gif" data-echo="${good.mainMap}" />
									</a>
									<span>${good.goodsName}</span>
								</div>
								<div class="col col3">${good.buyNum}</div>
								<div class="col col4">${good.goodsPrice}</div>
								<div class="col col5">${good.weight * good.buyNum}</div>
								<div class="col col6 total-list">${good.uMoney}</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
		</div>

		<!--提交订单-->
		<div class="payBox">
			<div class="payBox-l">
				<h6>物流须知：</h6>
				<p>1.根据国家法律相关规定，跨境电子商务零售进口商品的单次交易限值为人民币2000元，个人年度交易限值为人民币20000元。个人年度已使用额度、可用额度官方查询地址：http://ceb2pub.chinaport.gov.cn</p>
				<p>2.超过国家限额的订单无法通过海关发货，您可以分批次提交订单购买或存入北极光微仓后，在需要的时候分批发货</p>
			</div>
			<div class="payBox-r">
				<div class="list">
					<span>商品价格总计：</span>
					<i id="payment">￥${map.tMoney}</i>
				</div>
				<div class="list">
					<span>运费：</span>
					<i id="postage">￥${map.tPostage}</i>
				</div>
				<div class="list">
					<span>应付金额总计：</span>
					<i class="all" id="tPayment">￥${map.pMoney}</i>
				</div>
				<div class="btnBox">
					<a href="javascript:;" id="z-submit">提交订单</a>
				</div>
				<h6 id="info-show"><i>${map.wc1Address.name}</i> 收    <i>${map.wc1Address.province}</i><i>${map.wc1Address.city}</i><i>${map.wc1Address.area}</i><i>${map.wc1Address.detail_address}</i>   电话  <i>${map.wc1Address.mobile}</i></h6>
				<i class="hidden" id="sa_id"></i>
			</div>
		</div>
		<!--提交订单-->
		<form method="get" action="goPay" id="goPay" name="goPay" class="form-inline">
			<input type="hidden" id="orderID-pay" name="orderID"/>
		</form>
		
		<script src="../static/assets/js/confirmCar_all.js"></script>
		<script>
			console.log(${map})
		</script>
		<hr />
		<%@ include file="../index/footer.jsp"%>
	</body>

</html>