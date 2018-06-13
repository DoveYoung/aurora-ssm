<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!--购物车全款支付-->
<div class="payway-box">
	<div class="payway">
		<div class="pay-num">
			支付金额：<i>￥${shouldPay}</i> 合同编号：${contractID}
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