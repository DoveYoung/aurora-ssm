<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--购物车定金-->
<div class="payway-box">
	<div class="order">	
		<h1>
			<img src="../static/assets/img/blank.gif" data-echo="../static/assets/img/cue-right.gif"/>
			<i>订单交易成功，只差最后一步啦！</i>
		</h1>
		<div class="order-time">
			请您在提交订单后<i>1小时内</i>完成支付，否则订单会自动取消！
		</div>
		<hr/>	
			<c:if test="${orderPayInfo.directGoods.size() > 0}">
			<div class="mail Germany">
				<c:forEach items="${orderPayInfo.directGoods}" var="good" varStatus="vs">
					<h5>${good.goodsName}</h5>
				</c:forEach>
				<div class="shipway">海外直邮</div>
			</div>	
		</c:if>
		<c:if test="${orderPayInfo.bondedGoods.size() > 0}">
			<div class="mail Germany">
				<c:forEach items="${orderPayInfo.bondedGoods}" var="good" varStatus="vs">
					<h5>${good.goodsName}</h5>
				</c:forEach>
				<div class="shipway">保税仓代发</div>
			</div>	
		</c:if>
		<c:if test="${orderPayInfo.generalGoods.size() > 0}">
			<div class="mail Germany">
				<c:forEach items="${orderPayInfo.generalGoods}" var="good" varStatus="vs">
					<h5>${good.goodsName}</h5>
				</c:forEach>
				<div class="shipway">一般贸易</div>
			</div>	
		</c:if>
		<div class="address">
			收货信息：存入${orderPayInfo.address}
		</div>
		<!--<div class="my-order">
			<a href="javascript:;">我的订单></a>
		</div>-->
	</div>
	<div class="payway">
		<div class="pay-num">
			支付金额：<i>￥${orderPayInfo.shouldPayment}</i>
			<!--货款全额：<b>￥${pDMoney}</b>-->
			<!--定金比例：<b></b>-->
		</div>
		<hr />
		<div class="payWay payWay-active">
			<input type="radio" name="pay-way" checked="checked"/>
			<div class="payWay-img alipay">
				<i></i>
			</div>
		</div>
		<div class="payWay">
			<input type="radio" name="pay-way"/>
			<div class="payWay-img wechatPay">
				<i></i>
			</div>
		</div>
		<div class="payWay">
			<input type="radio" name="pay-way"/>
			<div class="payWay-img kqPay">
				<i></i>
			</div>
		</div>
		<b class="to-pay">去支付</b>
	</div>
</div>	