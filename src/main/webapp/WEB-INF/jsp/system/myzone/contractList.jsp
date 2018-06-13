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
		<title>个人中心--我的合同</title>
		<%@ include file="../index/headLink.jsp"%>
		<style type="text/css">
			.goods-name:hover{
				color:#000 !important;
			}
		</style>
	</head>

	<body id="product-body">
		
		<!--header-->
		<div id="search-header">			
			<%@ include file="../index/topLogin.jsp"%>			
			<%@ include file="../index/headerSearch.jsp"%>
		</div>
		<%@ include file="../index/shipType.jsp"%>
		<form method="post" action="contractList" name="contractServiceImpl" id="contractServiceImpl" class="form-inline">
		<div id="myZone">
			<%@ include file="../myzone/myzoneMenuList.jsp"%>
			<div id="zone-content">
				
				<h2 id="order_c">
					<b state="" :class="{active : pd.contractState == ''}">所有合同</b>
					<b state="1" :class="{active : pd.contractState == '1'}">待上传<i>${pendingUploadNum}</i></b>
					<b state="2" :class="{active : pd.contractState == '2'}">待付款<i>${pendingPayNum}</i></b>
					<b state="3" :class="{active : pd.contractState == '3'}">已完成<i>${finishNum}</i></b>
				</h2>
				<div class="search-box">
					<div class="input-group">
			            <input type="text" class="form-control" name="keyWord" id="keyword_contractList" value="${pd.keyWord}" placeholder="请输入商品或订单编号搜索" />
			            <span class="input-group-addon pointer" id="searchOrder">搜索</span>
			        </div>
			        <div class="input-group float-right">
			        	<span class="input-group-addon">合同状态</span>
			            <select type="text" class="form-control" id="contractState" name="contractState" V-bind:value="pd.contractState">
			            	<option value="">选择合同状态</option>
			            	<option value="1">合同待上传</option>
			            	<option value="2">待付款</option>
			            	<option value="3">已付款</option>
			            	<option value="5">失效</option>	
			            </select>
			        </div>
			        <div class="input-group float-right">
			        	<span class="input-group-addon">下单时间</span>
			            <select type="text" class="form-control" id="beginTime" name="beginTime" :value="pd.beginTime">
			            	<option value="">选择下单时间</option>
			            	<option value="29">近一个月</option>
			            	<option value="89">近三个月</option>
			            	<option value="364">近一年</option>
			            </select>
			        </div>
				</div>
				<div class="list-box">
					<div class="car-head">
						<div class="col col0">合同商品</div>
						<div class="col col2">单价/邮费(元)</div>
						<div class="col col3">数量</div>
						<div class="col col4">实付款/应付款(元)</div>
						<div class="col col5">合同状态</div>
						<div class="col col6">操作</div>
					</div>
					<div class="orderList" v-for="order in contractList">
						<div class="logistics-box"></div>
						<div class="orderList-head">
							<i>合同编号：{{order.contractID}}</i>
							<i>生成时间：{{order.createTime}}</i>
							<b v-if="order.contractState == 2 || order.contractState == 3 || order.contractState == 4"><a :href="order.contractFile" target="_blank">下载合同</a></b>					
						</div>
						<div class="orderList-box">
							<div class="orderList-l">
								<div class="orderList-goods" v-for="good in order.contractGoodsList">
									<div class="col col0">
										<a class="goods-img" href="javascript:;">
											<img :src="good.goodsMap" alt="无图"/>
										</a>
										<i>
											<a class="goods-name" href="javascript:;">{{good.goodsName}}</a>
										</i>
									</div>
									<div class="col col2">
										<i class="i-box">
											<b>{{good.currencySign}}{{good.goodsPrice}}</b>
											<b>{{good.currencySign}}{{good.postage}}</b>
										</i>
									</div>
									<div class="col col3">
										<i class="i-box">
											<b>{{good.goodsNum != '0' ? good.goodsNum : '无法报价'}}</b>
										</i>
									</div>
								</div>
							</div>
							<div class="orderList-r">
								<div class="col col4">
									<i class="i-box">
										<b>{{order.payMoney == '' ? '待付' : (order.contractGoodsList[0].currencySign+''+order.payMoney)}}/{{order.contractGoodsList[0].currencySign}}{{order.contractMoney}}</b>
										<b>(总邮费：{{order.contractGoodsList[0].currencySign}}{{order.totalPostage}})</b>
									</i>	
								</div>
								<div class="col col5">
									<i class="i-box">	
										<b v-if="order.contractState == 1">合同待上传</b>
										<b v-if="order.contractState == 2">待付款</b>
										<b v-if="order.contractState == 3">已线上付款</b>
										<b v-if="order.contractState == 4">已线下付款</b>
										<b v-if="order.contractState == 5">作废</b>
										<b v-if="order.contractState == 11">实付金额与应付金额不等值</b>
									</i>
								</div>
								<div class="col col6" contractID="${order.value[0].contract_id}">
									<i class="i-box" contractID="${order.value[0].contract_id}">
										<b class="pointer payOrder" v-if="order.contractState == 2" @click="contractPay(order.contractID)">去付款</b>
										<b class="pointer cancelOrder" v-if="order.contractState != 1">
											<a :href="'<%=basePath%>customerInquiry/inquiryAgainPage?inquirySID='+order.contractID" target="_blank" >再次询价</a>
										</b>
									</i>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%@ include file="../commons/page.jsp"%>
		</div>
		</form>
		<form method="post" action="getcontractPay" name="contractPay" id="contractPay" target="_blank">
			<input type="hidden" name="contractID" id="contractID"/>
		</form>
		<script>
			var contractList = ${contractList},
			  	pd = ${pd};
			  	if(!pd.contractState){
			  		pd.contractState = ''
			  	}
			console.log(pd)
			new Vue({
				el:'#zone-content',
				data:{
					contractList:contractList,
					pd:pd,
				},
				methods:{
					contractPay:function(i){
						console.log(i)
//						return
						$('#contractID').val(i);
						$('#contractPay').submit();
					}
				}
			})
		</script>
		<script>
			function search_contractServiceImpl(i){
				if(i==1){
					$('#currentPage').val(1);
					$('#fromIndex').val(0);
				}
				$('#contractServiceImpl').submit();
			}
			function goPage(pageNo) {
				$('#currentPage').val(pageNo);
				var fromIndex = (pageNo - 1) * $('#pageSize').val();
				if(fromIndex < 0) {
					fromIndex = 0;
				}
				$('#fromIndex').val(fromIndex);
				search_contractServiceImpl()
			}
			$(function(){
				$('#searchOrder').on('click',function(){
					search_contractServiceImpl(1);
				})
				//选择状态
				$('#order_c').on('click','b',function(){
					$('#contractState').val($(this).attr('state'));
					$('#keyword_contractList').val('');
					$('#beginTime').val(''); 
					search_contractServiceImpl(1);
				})
				//付款
				$('.payOsrder').on('click',function(){
					var contractID = $(this).parent().attr('contractID');
					$('#contractID').val(contractID);
//					console.log($('#contractID').val())
					$('#contractPay').submit();
				})
			})
		</script>
		<!--页脚-->
		<%@ include file="../index/footer.jsp"%>
		<!--右侧固定-->
		<%@ include file="../index/rightFixed.jsp"%>
	</body>	
</html>