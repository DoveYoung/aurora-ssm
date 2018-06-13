<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="payway-box">
	<div class="order-success">
		<h1>
			<img src="../static/assets/img/blank.gif" data-echo="../static/assets/img/cue-right.gif"/>
			<i>您已成功付款！</i>
		</h1>
		<hr />
		<div class="info"><b>收货地址：</b>${pdm.ship_address} ${pdm.consignee} ${pdm.consignee_mobile}</div>
		<div class="info"><b>实付定金：</b><i>¥${pdm.should_payment}</i></div>
		<div class="info"><b>货款总额：</b>¥${pdm.total_money}</div>
		<!--<div class="info"><b>定金比例：</b>10%</div>-->
		<div class="info" style="margin-bottom: 10px;">您可以<a href="../customerOrder/orderList">查看已买到的商品</a></div>
	</div>
</div>