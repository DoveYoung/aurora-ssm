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
		<title>查看物流</title>
		<%@ include file="../index/headLink.jsp"%>
		<style>
			.layui-timeline-item{
				position:relative;
				padding:20px 0;
			}
			.layui-icon{
				position:absolute;
				top:19px;
			}
			.layui-icon b{
				display: inline-block;
				width:5px !important;height:5px;
				background:#999;
				border-radius: 50%;
				line-height: 20px;
				text-align: center;
			}
			.layui-timeline{
				padding-left: 200px;
				margin-top:20px;	
			}
			.layui-timeline .layui-timeline-item:first-child .layui-icon b{
				background:#4e92df;
			}
			.layui-timeline-content .layui-timeline-title,.layui-timeline-content p{
				padding-left: 20px;
				text-align: left;
				font-size:16px;
				line-height: 20px;
				color:#999;
			}
			.layui-timeline-content{
				position:relative;
			}
			.layui-timeline-content .layui-timeline-title{
				position:absolute;
				left:-200px;
			}
			.layui-timeline .layui-timeline-item:first-child .layui-timeline-title,
			.layui-timeline .layui-timeline-item:first-child p{
				color:#4e92df;
			}
		</style>
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
			<div id="logistic-info">
				<c:if test="${logisticInfoMap.logisticInfos.size() != '0'}">
					<c:forEach items="${logisticInfoMap.logisticInfos}" var="info" varStatus="vs">
					<div class="info-list">
						<h2 class="title">包裹${vs.index+1}</h2>
						<div class="state"><i>!</i>当前状态：已发货，物流小哥正在运输中...</div>
						<div class="company">
							物流公司：${info.expCompany} <a href="${info.expUrl}" target="_blank">${info.expUrl}</a>&nbsp;&nbsp;&nbsp;运单号：${info.logisticInfo.LogisticCode}
						</div>
						<h5>温馨提示：因海关政策，单包裹价值不能超过¥2000.00，所以您的订单可能会被拆分为几个包裹发货</h5>
						<ul class="layui-timeline">
							<c:forEach items="${info.logisticInfo.Traces}" var="list" varStatus="l">
							 	<li class="layui-timeline-item">
								    <i class="layui-icon layui-timeline-axis"><b></b></i>
								    <div class="layui-timeline-content layui-text">
								    	<h3 class="layui-timeline-title">${(info.logisticInfo.Traces[fn:length(info.logisticInfo.Traces) - 1 - l.index]).AcceptTime}</h3>
								      	<p>${(info.logisticInfo.Traces[fn:length(info.logisticInfo.Traces) - 1 - l.index]).AcceptStation}</p>
								    </div>
							  	</li>
						  	</c:forEach>
						</ul>  
					</div>
					</c:forEach>
				</c:if>
				<c:if test="${logisticInfoMap.logisticInfos.size() == '0'}">
					<h3 style="margin:20px 0;text-indent: 20px;color: #333;font-weight: normal;">暂无物流信息</h3>
				</c:if>
			</div>
			<div class="tips">
				<p class="safety-tips"><b></b><i>安全提醒：</i>请不要将银行卡、密码、手机验证码提供给他人，北极光不会通过任何非官方电话，QQ、微博、微信与您联系。任何以“缺货、海关卡单、商品检验不合格、假货”等要求您点击山寨北极光链接进行退款或取消订单的都是骗子！北极光官方客服电话：0571-87916639</p>
				<p><i>温馨提示：</i>海外直邮产品在配送期间，若产生税费，请保留税费单，并联系客服</p>
			</div>
			<!--订单信息-->
			<div class="order-info">
				<h3>订单信息</h3>
				<c:if test="${logisticInfoMap.orderGoods[0].pay_type == '1'}">
					<h5><i>收货地址</i>：${logisticInfoMap.orderGoods[0].consignee} <b id="consignee_mobile">${logisticInfoMap.orderGoods[0].consignee_mobile}</b> ${logisticInfoMap.orderGoods[0].ship_address}</h5>
					<script>
						function mobile_show(i){
							var str = i.text();
							var str2 = str.substr(0,3)+"****"+str.substr(7);
							i.text(str2)
						}
						mobile_show($('#consignee_mobile'))
					</script>
				</c:if>
				<c:if test="${logisticInfoMap.orderGoods[0].pay_type == '2'}">
					<h5><i>收货地址</i>：${logisticInfoMap.orderGoods[0].consignee} ${logisticInfoMap.orderGoods[0].consignee_mobile} ${logisticInfoMap.orderGoods[0].ship_address}</h5>
				</c:if>
				<h5><i class="letter">订单号</i>：${logisticInfoMap.orderGoods[0].order_id}</h5>
				<h5><i>支付金额</i>：${logisticInfoMap.orderGoods[0].pay_money}</h5>
				<h5><i>下单时间</i>：${logisticInfoMap.orderGoods[0].logistics_time}</h5>
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
				<c:forEach items="${logisticInfoMap.orderGoods}" var="good">
					<div class="good-list">
						<div class="col col2">
							<a class="good-img"href="<%=basePath%>detailPage/goGoodsdetail?goodsID=${good.goods_id}" target="_blank">
								<img src="../static/assets/img/blank.gif" data-echo="${good.goods_map}"/>
							</a>
							<a class="i-box" href="<%=basePath%>detailPage/goGoodsdetail?goodsID=${good.goods_id}" target="_blank"><b>${good.goods_name}</b></a>
						</div>
						<div class="col col3">重量（kg）</div>
						<div class="col col4">￥${good.goods_price}</div>
						<div class="col col5">${good.goods_num}</div>
						<div class="col col6">￥${good.goods_price - good.goods_price / 1.119}</div>
					</div>
				</c:forEach>
				<div class="info-total">
					<h5><i>商品总价：</i><b>￥${logisticInfoMap.orderGoods[0].total_money}</b></h5>
					<h5><i>运费：</i><b>￥${logisticInfoMap.orderGoods[0].postage}</b></h5>
					<h5><i>税费：</i><b>￥${logisticInfoMap.orderGoods[0].total_money - logisticInfoMap.orderGoods[0].total_money / 1.119}</b></h5>
					<h5><i>实付款：</i><b class="pay_money">￥${logisticInfoMap.orderGoods[0].pay_money}</b></h5>
				</div>
			</div>
		</div>
		<hr />
	
		<!--页脚-->
		<%@ include file="../index/footer.jsp"%>
		<!--右侧固定-->
		<%@ include file="../index/rightFixed.jsp"%>
	</body>	
</html>