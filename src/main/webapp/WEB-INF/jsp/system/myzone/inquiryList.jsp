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
		<title>个人中心--我的询价</title>
		<%@ include file="../index/headLink.jsp"%>
		<script>
			function search() {
				if($('#keyword').val() == ''){
					return location.reload();
				}
				$("#searchFrom").submit();
			}
		</script>
		<style>
			.i-box .see-inquiry{
				color:#3994dc !important;
			}
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
		<form method="post" action="inquiryList" name="inquiryList" id="inquiryList" class="form-inline">
		<div id="myZone">
			<%@ include file="../myzone/myzoneMenuList.jsp"%>
			<div id="zone-content">
			 
				<h2 id="order_c">
					<b state="" :class="{active : pd.inquiryState == ''}">全部询价</b>
					<b state="1" :class="{active : pd.inquiryState == 1}">待报价<i>${pendingNum}</i></b>
					<b state="2" :class="{active : pd.inquiryState == 2}">已报价<i>${finishNum}</i></b>
					<b state="3" :class="{active : pd.inquiryState == 3}">已失效询价商品<i>${overdueNum}</i></b>
				</h2>
				<div class="search-box">
					<div class="input-group">
			            <input type="text" class="form-control" name="keyWord" id="keyword_inquiryList" :value="pd.keyWord" placeholder="请输入商品或订单编号搜索" />
			            <span class="input-group-addon pointer" id="searchOrder">搜索</span>
			       </div>
			        <div class="input-group float-right">
			        	<span class="input-group-addon">询价状态</span>
			            <select type="text" class="form-control" id="inquiryState" name="inquiryState" v-bind:value="pd.inquiryState">
			            	<option value="">选择询价状态</option>
			            	<option value="1">未报价</option>
			            	<option value="2">已报价</option>
			            	<option value="3">报价失效</option>
			            </select>
			        </div>
			        <div class="input-group float-right">
			        	<span class="input-group-addon">询价时间</span>
			            <select type="text" class="form-control" id="beginTime" name="beginTime" v-bind:value="pd.beginTime">
			            	<option value="">选择询价时间</option>
			            	<option value="29">近一个月</option>
			            	<option value="89">近三个月</option>
			            	<option value="364">近一年</option>
			            </select>
			        </div>
				</div>
				<div class="list-box" id="inquiry-list">
					<div class="car-head">
						<div class="col col0">商品详情</div>
						<div class="col col2">数量</div>
						<div class="col col3">报价（元）</div>
						<div class="col col4">询价结果</div>
						<div class="col col5">有效时间</div>
						<div class="col col6">操作</div>
					</div>
						<div class="orderList" v-for="order in inquiryList">
							<div class="orderList-head">
								<i>询价单编号：{{order.inquiryID}}</i>
								<i>询价时间：{{order.inquiryTime}}</i>							
							</div>
							<div class="orderList-box">
								<div class="orderList-l inquiryList-l">
									<div class="orderList-goods" v-for="good in order.inquiryGoodsList">
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
												<b>{{good.buyNum}}</b>
											</i>
										</div>
										<div class="col col3">
											<i class="i-box">
												<b>{{good.goodsPrice ? good.currencySign+''+good.goodsPrice : '暂未报价'}}</b>
											</i>
										</div>
										<div class="col col4">
											<i class="i-box">
												<b v-if="good.inquiryGoodsState == 1">未报价</b>
												<b v-if="good.inquiryGoodsState == 2">已报价</b>
												<b v-if="good.inquiryGoodsState == 3">无法报价</b>
												<b v-if="good.inquiryGoodsState == 4">已失效</b>
											</i>	
										</div>
										<div class="col col5">
											<i class="i-box">
												<b>{{good.validTime}}</b>
											</i>
										</div>
									</div>
									
								</div>
								<div class="orderList-r inquiryList-r">
									<div class="col col6">
										<i class="i-box">
											<b class="pointer see-inquiry" v-if="order.inquiryState == 2" @click="getInquiry(order.inquiryID)">查看报价</b>
											<b class="pointer" v-if="order.inquiryState == 1">暂未报价</b>
											<b class="pointer"><a :href="'<%=basePath%>customerInquiry/inquiryAgainPage?inquiryID='+order.inquiryID" target="_blank">重新询价</a></b>
										</i>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<%@ include file="../commons/page.jsp"%>
			</div>
		</div>
		</form>
		<form method="post" action="getInquiry" name="getInquiry" id="getInquiry" target="_blank" class="form-inline">
			<input type="hidden" id="inquiryID" name="inquiryID"/>
		</form>
		<script>
			var inquiryList = ${inquiryList},
				pd = ${pd};
			if(!pd.inquiryState){
				pd.inquiryState = '';
			}
			console.log(pd)
			new Vue({
				el:'#zone-content',
				data:{
					inquiryList:inquiryList,
					pd:pd
				},
				methods:{
					getInquiry:function(i){//查看报价页面,显示已经报价的商品,其余状态商品不显示
						$('#inquiryID').val(i);
						$('#getInquiry').submit()
					}
				}
			})
		</script>
		<script>
			function search_inquiryList(i){
				if(i==1){
					$('#currentPage').val(1);
					$('#fromIndex').val(0);
				}
				$('#inquiryList').submit();
			}
			function goPage(pageNo) {
				$('#currentPage').val(pageNo);
				var fromIndex = (pageNo - 1) * $('#pageSize').val();
				if(fromIndex < 0) {
					fromIndex = 0;
				}
				$('#fromIndex').val(fromIndex);
				search_inquiryList()
			}
			$(function(){
				$('#searchOrder').on('click',function(){
					search_inquiryList(1);
				})
				//选择状态
				$('#order_c').on('click','b',function(){
					$('#inquiryState').val($(this).attr('state'));
					$('#keyword_inquiryList').val('');
					$('#beginTime').val('');
					search_inquiryList(1);
				})
				
			})
		</script>
		<!--页脚-->
		<%@ include file="../index/footer.jsp"%>
		<!--右侧固定-->
		<%@ include file="../index/rightFixed.jsp"%>
	</body>	
</html>