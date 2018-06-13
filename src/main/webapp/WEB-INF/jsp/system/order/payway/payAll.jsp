<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:if test="${pType == 'fpflb'}">
	<!--立即购买全款支付-->
	<div class="payway-box">
		<div class="order">		
			<h1>
				<img src="<%=basePath%>static/assets/img/blank.gif" data-echo="../static/assets/img/cue-right.gif"/>
				<i>订单交易成功，只差最后一步啦！</i>
			</h1>
			<div class="order-time">
				请您在提交订单后<i>1小时内</i>完成支付，否则订单会自动取消！
			</div>
			<hr/>	
			<div class="mail Germany">
				<h5>${gManage.goodsDetails.goodsName} kg</h5>	
				<c:if test="${gManage.shipType == '1'}">
					<div class="shipway">保税仓直邮</div>
				</c:if>
				<c:if test="${gManage.shipType == '2'}">
					<div class="shipway">海外直邮</div>
				</c:if>
				<c:if test="${gManage.shipType == '3'}">
					<div class="shipway">国内现货</div>
				</c:if>
			</div>	
			<div class="address">
				收货信息：${shipAddr.province}省${shipAddr.city}市下城区 ${shipAddr.detail_address} ${shipAddr.name}收 手机：${shipAddr.mobile}
			</div>
			<!--<div class="my-order">
				<a href="javascript:;">我的订单></a>
			</div>-->
		</div>
		<div class="payway">
			<div class="pay-num">
				支付金额：<i>￥${tPayment}</i>
			</div>
			<hr />
			<div class="payWay payWay-active">
				<input type="radio" name="pay-way" checked="checked"/>
				<div class="payWay-img alipay">
					<i></i>
				</div>
				<i style="margin-left:10px;line-height: 100px;color:#e60031">手机用户推荐使用支付宝支付</i>
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
</c:if>
<c:if test="${orderPayInfo.payType == '1'}">
	<!--购物车全款支付-->
	<script>
		console.log(1,${order})
	</script>
	<div class="payway-box">
		<div class="order">		
			<h1>
				<img src="<%=basePath%>static/assets/img/blank.gif" data-echo="<%=basePath%>static/assets/img/cue-right.gif"/>
				<i>订单交易成功，只差最后一步啦！</i>
			</h1>
			<div class="order-time">
				请您在提交订单后<i>1小时内</i>完成支付，否则订单会自动取消！
			</div>
			<hr/>	
			<c:if test="${orderPayInfo.directGoods.size() > 0}">
				<div class="mail Germany">
					<c:forEach items="${orderPayInfo.directGoods}" var="good" varStatus="vs">
						<h5>${good.goodsName} ${good.weight}kg</h5>
					</c:forEach>
					<div class="shipway">海外直邮</div>
				</div>	
			</c:if>
			<c:if test="${orderPayInfo.bondedGoods.size() > 0}">
				<div class="mail Germany">
					<c:forEach items="${orderPayInfo.bondedGoods}" var="good" varStatus="vs">
						<h5>${good.goodsName} ${good.weight}kg</h5>
					</c:forEach>
					<div class="shipway">保税仓代发</div>
				</div>	
			</c:if>
			<c:if test="${orderPayInfo.generalGoods.size() > 0}">
				<div class="mail Germany">
					<c:forEach items="${orderPayInfo.generalGoods}" var="good" varStatus="vs">
						<h5>${good.goodsName} ${good.weight}kg</h5>
					</c:forEach>
					<div class="shipway">一般贸易</div>
				</div>	
			</c:if>
			
			<div class="address">
				收货信息：${orderPayInfo.address} ${orderPayInfo.consignee}收 手机：${orderPayInfo.consigneeMobile}
			</div>
			<!--<div class="my-order">
				<a href="javascript:;">我的订单></a>
			</div>-->
		</div>
		<div class="payway">
			<div class="pay-num">
				支付金额：<i>￥${orderPayInfo.shouldPayment}</i>
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
</c:if>
<c:if test="${pType == 'mop'}">
	<!--购物车全款支付-->
	<div class="payway-box">
		<div class="payway">
			<div class="pay-num">
				支付金额：<i>￥${shouldPay}</i> 订单编号：${orderID}
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
</c:if>