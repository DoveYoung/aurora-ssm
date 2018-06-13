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
		<title>微仓-选择支付方式</title>
		<%@ include file="../index/headLink.jsp"%>
	</head>

	<body id="comfirm-body" class="payway-body">

		<!--header-->
		<div id="search-header">
			<div class="search-header-box">
				<h3 class="top">
					<%@ include file="../index/topNotLogin.jsp"%>
				</h3>
			</div>
			<div id="logo-index">
				<a target="_blank" href="<%=basePath%>">
					<img src="../static/assets/img/blank.gif" data-echo="../static/assets/img/logo.gif"/>
				</a>
			</div>	
		</div>
		<div class="payway-box">
			<div class="payway">
				<div class="pay-num">
					支付金额：<i>￥${payment}</i>
					货款全额：<b>￥${payment}</b>
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
		<div class="pay-box-layer">
			<b class="icon">!</b>
			<div>
				<h1>支付提交成功</h1>
				<h3>请在第三方支付页面完成付款，付款前不要关闭此窗口</h3>
				<h5><a class="pay-end" href="<%=basePath%>customerOrder/orderList">我已付款成功</a><i class="pay-error">付款遇到问题重新支付</i></h5>
				<h6><a target="_blank" href="<%=basePath%>footer/contactUs">>联系客服</a></h6>
			</div>
		</div>
		<!--页脚-->
		<%@ include file="../index/footer.jsp"%>
		<form method="get" action="../alipayMerge/payment?payOrderID=${payOrderID}" id="apsearchFrom" name="apsearchFrom" class="form-inline" target="_blank">
			<input type="hidden" id="orderID-ap" name="payOrderID" value="${payOrderID}"/>
		</form>
		<form method="get" action="../wxMergePay/weixinPage?payOrderID=${payOrderID}" id="wpsearchFrom" name="wpsearchFrom" class="form-inline" target="_blank">
			<input type="hidden" id="orderID-wp" name="payOrderID" value="${payOrderID}"/>
		</form>
		<form method="get" action="../kqpayMWMerge/goKQSend?orderID=${payOrderID}" id="kpsearchFrom" name="kpsearchFrom" class="form-inline" target="_blank">
			<input type="hidden" id="orderID-kp" name="orderID" value="${payOrderID}"/>
		</form>
		<script>
			//支付宝
			function aqsearch() {	
				$("#apsearchFrom").submit();
			}
			//快钱支付
			function kqsearch() {	
				$("#kpsearchFrom").submit();
			}
			//微信支付
			function wpsearch() {	
				$("#wpsearchFrom").submit();
			}
			//微信回调
			function wnsearch() {	
				$("#wnsearchFrom").submit();
			}
			$(function(){
				//支付方式选择
				$("input[name='pay-way']").on('click',function(){
					$('.payWay').removeClass('payWay-active');
					$(this).parent().addClass('payWay-active');
				})
				$('.payWay-img').on('click',function(){
					$(this).siblings('input').click()
				})
				$('.to-pay').on('click',function(){
					var c_pay = $('.payWay-active').find('.payWay-img');
					setTimeout(pay_box = layer.open({
									type: 1,
									title: false,
									closeBtn: 0,
									area: '600px',
//									skin: 'layui-layer-nobg', //没有背景色
									shadeClose: false,
									content: $('.pay-box-layer')
								}),500)
					if(c_pay.hasClass('alipay')){
						aqsearch()
					}else if(c_pay.hasClass('wechatPay')){
						wpsearch()
					}else if(c_pay.hasClass('kqPay')){
						kqsearch()
					}
				})
				//重新支付
				$('.pay-error').on('click',function(){
					layer.close(pay_box);
				})
			})
		</script>
	</body>

</html>