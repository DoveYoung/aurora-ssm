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
		<title>选择支付方式</title>
		<%@ include file="../index/headLink-p.jsp"%>
	</head>

	<body id="comfirm-body" class="payway-body">
		<c:set value="${orderPayInfo.orderID}" var="orderID" />   
		<!--header-->
		<%@ include file="../index/topLogin-pay.jsp"%>
		<script>
			console.log(1,${orderPayInfo})
		</script>
		<c:if test="${orderPayInfo.payType == '1'}">
			<!--payAll & mop个人中心订单-->
			<%@ include file="payway/payAll.jsp"%>
			<form method="get" action="<%=basePath%>alipay/payment?orderID=${orderID}" id="apsearchFrom" name="apsearchFrom" class="form-inline" target="_blank">
				<input type="hidden" id="orderID-ap" name="orderID" value="${orderID}"/>
			</form>
			<form method="get" action="<%=basePath%>wxpay/weixinPage?orderID=${orderID}" id="wpsearchFrom" name="wpsearchFrom" class="form-inline" target="_blank">
				<input type="hidden" id="orderID-wp" name="orderID" value="${orderID}"/>
			</form>
			<form method="get" action="<%=basePath%>kqpay/goKQSend?orderID=${orderID}" id="kpsearchFrom" name="kpsearchFrom" class="form-inline" target="_blank">
				<input type="hidden" id="orderID-kp" name="orderID" value="${orderID}"/>
			</form>
		</c:if>
		<c:if test="${orderPayInfo.payType == '2'}">
			<!--payDeposit-->
			<%@ include file="payway/payDeposit.jsp"%>
			<form method="get" action="<%=basePath%>alipay/payment?orderID=${orderID}" id="apsearchFrom" name="apsearchFrom" class="form-inline" target="_blank">
				<input type="hidden" id="orderID-ap" name="orderID" value="${orderID}"/>
			</form>
			<form method="get" action="<%=basePath%>wxpay/weixinPage?orderID=${orderID}" id="wpsearchFrom" name="wpsearchFrom" class="form-inline" target="_blank">
				<input type="hidden" id="orderID-wp" name="orderID" value="${orderID}"/>
			</form>
			<form method="get" action="<%=basePath%>kqpay/goKQSend?orderID=${orderID}" id="kpsearchFrom" name="kpsearchFrom" class="form-inline" target="_blank">
				<input type="hidden" id="orderID-kp" name="orderID" value="${orderID}"/>
			</form>
		</c:if>
		<c:if test="${pType == 'mcp'}">
			<!--合同付款-->
			<%@ include file="payway/payContract.jsp"%>
			<form method="get" action="<%=basePath%>alipayContract/payment?contractID=${contractID}" id="apsearchFrom" name="apsearchFrom" class="form-inline" target="_blank">
				<input type="hidden" id="orderID-ap" name="contractID" value="${contractID}"/>
			</form>
			<form method="get" action="<%=basePath%>wxContractpay/weixinPage?contractID=${contractID}" id="wpsearchFrom" name="wpsearchFrom" class="form-inline" target="_blank">
				<input type="hidden" id="contractID" name="contractID" value="${contractID}"/>
			</form>
			<form method="get" action="<%=basePath%>kqpayContract/goKQSend?orderID=${contractID}" id="kpsearchFrom" name="kpsearchFrom" class="form-inline" target="_blank">
				<input type="hidden" id="orderID-kp" name="contractID" value="${contractID}"/>
			</form>
		</c:if>
		
		<!--页脚-->
		<%@ include file="../index/footer.jsp"%>
		<div class="pay-box-layer">
			<b class="icon">!</b>
			<div>
				<h1>支付提交成功</h1>
				<h3>请在第三方支付页面完成付款，付款前不要关闭此窗口</h3>
				<!--个人订单付款 返回个人订单  微仓付款 返回对应目录-->
				<h5><a class="pay-end" href="<%=basePath%>personal/myOrder">我已付款成功</a><i class="pay-error">付款遇到问题重新支付</i></h5>
				<h6><a target="_blank" href="<%=basePath%>footer/contactUs">>联系客服</a></h6>
			</div>
		</div>
		<script>
			//支付宝支付
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
					var c_pay = $('.payWay-active').find('.payWay-img')
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
						aqsearch();
					}else if(c_pay.hasClass('wechatPay')){
						//微信支付
						wpsearch();
					}else if(c_pay.hasClass('kqPay')){
						//快钱支付
						kqsearch();
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