<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!--length函数-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
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
		<title>查看订单</title>
		<%@ include file="../index/headLink.jsp"%>
	</head>

	<body id="product-body">
		
		<!--header-->
		<div id="search-header">			
			<%@ include file="../index/topLogin.jsp"%>			
			<div id="logo-index">
				<a href="<%=basePath%>">
					<img src="../static/assets/img/blank.gif" data-echo="../static/assets/img/logo.gif"/>
				</a>
			</div>
		</div>
		<div id="more-logistic">
			<div id="order-state" orderID="${orderGoods[0].order_id}">
				<c:if test="${orderGoods[0].order_state == '1'}">
					<h3><i>!</i>当前状态：订单待付款</h3>
					<b class="payOrder">支付货款</b>
				</c:if>
				<c:if test="${orderGoods[0].order_state == '2'}">
					<h3><i>!</i>当前状态：订单未接单</h3>
					<b class="cancelOrder">取消订单</b>
				</c:if>
				<c:if test="${orderGoods[0].order_state == '6'}">
					<h3><i>!</i>当前状态：待发货</h3>
				</c:if>
				<c:if test="${orderGoods[0].order_state == '7'}">
					<h3><i>!</i>当前状态：物流运输中</h3>
					<b class="see-logistics">查看物流</b>
				</c:if>
				<c:if test="${orderGoods[0].order_state == '3' || orderGoods[0].order_state == '5' || orderGoods[0].order_state == '9' || orderGoods[0].order_state == '10'}">
					<h3><i>!</i>当前状态：订单关闭</h3>
					<b class="buy-again">再次购买</b>
				</c:if>
				<c:if test="${orderGoods[0].order_state == '4'}">
					<h3><i>!</i>当前状态：待退款</h3>
				</c:if>
				<c:if test="${orderGoods[0].order_state == '8'}">
					<h3><i>!</i>当前状态：订单已完成</h3>
					<b class="buy-again">再次购买</b>
				</c:if>
			</div>
			<div class="tips">
				<p class="safety-tips"><b></b><i>安全提醒：</i>请不要将银行卡、密码、手机验证码提供给他人，北极光不会通过任何非官方电话，QQ、微博、微信与您联系。任何以“缺货、海关卡单、商品检验不合格、假货”等要求您点击山寨北极光链接进行退款或取消订单的都是骗子！北极光官方客服电话：0571-87916639</p>
				<p><i>温馨提示：</i>海外直邮产品在配送期间，若产生税费，请保留税费单，并联系客服</p>
			</div>
			<!--订单信息-->
			<div class="order-info">
				<h3>订单信息</h3>
				<c:if test="${orderGoods[0].pay_type == '1'}">
					<h5><i>收货地址</i>：${orderGoods[0].consignee} <b id="consignee_mobile">${orderGoods[0].consignee_mobile}</b> ${orderGoods[0].ship_address}</h5>
					<script>
						function mobile_show(i){
							var str = i.text();
							var str2 = str.substr(0,3)+"****"+str.substr(7);
							i.text(str2)
						}
						mobile_show($('#consignee_mobile'))
					</script>
				</c:if>
				<c:if test="${orderGoods[0].pay_type == '2'}">
					<h5><i>收货地址</i>：${orderGoods[0].consignee} ${orderGoods[0].consignee_mobile} ${orderGoods[0].ship_address}</h5>
				</c:if>
				<h5><i class="letter">订单号</i>：${orderGoods[0].order_id}</h5>
				<h5><i>支付金额</i>：${orderGoods[0].pay_money}</h5>
				<h5><i>下单时间</i>：${orderGoods[0].order_time}</h5>
			</div>
			<!--商品信息-->
			<div class="goods-info">
				<div class="good-head">
					<div class="col col2">商品信息</div>
					<div class="col col3">重量（kg）</div>
					<div class="col col4">售价（元）</div>
					<div class="col col5">数量</div>
					<div class="col col6">税费（元）</div>
				</div>
				<c:forEach items="${orderGoods}" var="good">
					<div class="good-list">
						<div class="col col2">
							<a class="good-img"href="<%=basePath%>detailPage/goGoodsdetail?goodsID=${good.goods_id}" target="_blank">
								<img src="../static/assets/img/blank.gif" data-echo="${good.goods_map}"/>
							</a>
							<a class="i-box" href="<%=basePath%>detailPage/goGoodsdetail?goodsID=${good.goods_id}" target="_blank"><b>${good.goods_name}</b></a>
						</div>
						<div class="col col3">${good.weight}</div>
						<div class="col col4">￥${good.goods_price}</div>
						<div class="col col5">${good.goods_num}</div>
						<div class="col col6">￥${good.goods_price - good.goods_price / 1.119}</div>
					</div>
				</c:forEach>
				<div class="info-total">
					<h5><i>商品总价：</i><b>￥${orderGoods[0].total_money}</b></h5>
					<h5><i>运费：</i><b>￥${orderGoods[0].postage}</b></h5>
					<h5><i>税费：</i><b>￥${orderGoods[0].total_money - orderGoods[0].total_money / 1.119}</b></h5>
					<h5><i>实付款：</i><b class="pay_money">￥${orderGoods[0].pay_money}</b></h5>
				</div>
			</div>
		</div>
		<hr />
		<form method="post" action="getMoreLogisticInfo" name="getMoreLogisticInfo" id="getMoreLogisticInfo" target="_blank" class="form-inline">
			<input type="hidden" id="l-orderID" name="orderID"/>
		</form>
		<form method="post" action="orderPay" name="orderPay" id="orderPay" target="_blank" class="form-inline">
			<input type="hidden" id="pay-orderID" name="orderID"/>
		</form>
		<script>
			$(function(){
				//取消订单
				$('.cancelOrder').on('click',function(){
					var orderID = $(this).parent().attr('orderID');
					layer.confirm('确认取消此订单？',function(index){
						$.ajax({
							type:"post",
							url:"cancelOrder",
							data:{'orderID':orderID},
							dataType:'json',
							success:function(data){
								console.log(data)
								if(data.result == 'success'){
									layer.msg('订单取消成功');
									setTimeout(function(){
										 window.location.reload();
									},500)
								}else if(data.result == 'error'){
									layer.msg(data.msg)
								}else if(data.result == 'failed'){
									layer.msg(data.msg)
								}
							}
						});
						layer.close(index)
					})
				})
				//查看物流
				$('.see-logistics').on('click',function(){
					$('#l-orderID').val($(this).parent().attr('orderID'));
//					return console.log($('#l-orderID').val())
					$('#getMoreLogisticInfo').submit()
				});
				//支付货款
				$('.payOrder').on('click',function(){
					var orderID = $(this).parent().attr('orderID');
					$('#pay-orderID').val(orderID);
					$('#orderPay').submit()
				})
				//再次购买 跳购物车 buy-again
				$('.buy-again').on('click',function(){
					var orderID = $(this).parent().attr('orderID');
					var cart = false
					$.ajax({
						type:"post",
						url:"buyAgain",
						async:false,
						data:{'orderID':orderID},
						dataType:'json',
						success:function(data){
							console.log(data)
							if(data.result == 'success'){
								layer.msg('即将进入购物车');
								return cart = true;
							}else if(data.result == 'error'){
								layer.msg(data.msg)
							}else if(data.result == 'failed'){
								layer.msg(data.msg)
							}
						}
					});
					if(cart){
						setTimeout(function(){
							window.open('<%=basePath%>cartPage/goCartPage')
						},500)
					}
				})
			})
		</script>
		<!--页脚-->
		<%@ include file="../index/footer.jsp"%>
		<!--右侧固定-->
		<%@ include file="../index/rightFixed.jsp"%>
	</body>	
</html>