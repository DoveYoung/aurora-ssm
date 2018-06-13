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
		<title>微信支付</title>
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
				<a href="javascript:;">
					<img src="../static/assets/img/blank.gif" data-echo="../static/assets/img/logo.gif"/>
				</a>
			</div>	
		</div>
		<div class="payway-box">
			<div class="order">
				<h1>
					<img src="../static/assets/img/blank.gif" data-echo="../static/assets/img/cue-right.gif"/>
					<i>订单处理成功请尽快付款，合同编号： ${contractID}</i>
					<span>支付金额：<b>￥${shouldPay}</b></span>
				</h1>
				<div class="order-time">
					请您在提交订单后<i>1小时内</i>完成支付，否则订单会自动取消！
				</div>
				<hr style="margin-bottom: 30px;"/>
				<div class="code-box">
					<img src="../static/assets/img/blank.gif" data-echo="../wxContractpay/payment?contractID=${contractID}" id="QRcode" contractID="${contractID}"/>
					<div class="wx-tip">请使用微信扫一扫扫描二维码支付</div>
				</div>
				<div class="wx-example"></div>
			</div>
		</div>
		<div>
			
		</div>
		<!--页脚-->
		<%@ include file="../index/footer.jsp"%>
		<form method="get" action="../wxpay/weixinPage?contractID=${contractID}" id="wpsearchFrom" name="wpsearchFrom" class="form-inline" target="_blank">
			<input type="hidden" id="contractID-wp" name="contractID" value="${contractID}"/>
		</form>
		<script>
			//轮询
			$(document).ready(function() {
				var n = 1;
				var contractID = $('#QRcode').attr('contractID');
				(function poll() {
				    setTimeout(function() {
				        $.ajax({
				            url: 'query',
				            type: 'POST',
				            data:{
				            	'contractID':contractID
				            },
				            dataType: "json",
				            complete: poll,
//				            timeout: 1000,
				            success: function(data) {
				               	if(data.state == 'success'){
				               		window.location.href = "../pay/goPaySuccess?contractID="+contractID;
				               	}else if(data.state == 'error'){
				               		layer.msg(data.msg)
				               	}else{
				               	}
				            },
				            error:function(data){
				            	
				           	},
				        })   
				    },5000); // <-- should be here instead
				})();
			})
		</script>
	</body>

</html>