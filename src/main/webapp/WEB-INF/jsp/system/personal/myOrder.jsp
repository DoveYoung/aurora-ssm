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
		<title>我的订单-个人中心-北极光供应链</title>
		<%@ include file="../index/headLink-p.jsp"%>
		<style>
			
			
			.money-b b{
				text-align: left !important;
			}
			.money-b b i{
				text-align: right !important;
			}
		</style>
		<script>
			$(function(){
			console.log(${page})
			page = ${page}
			if(!page.t.orderStateFront1){
				page.t.orderStateFront1 = '';
			}
			if(!page.t.orderStateFront2){
				page.t.orderStateFront2 = '';
			}
			if(!page.t.beginTime){
				page.t.beginTime = '';
			}
			if(!page.t.searchWord){
				page.t.searchWord = '';
			}
			obligationNum = ${obligationNum}
			trsNum = ${trsNum}
			doneNum = ${doneNum}
			orderList = ${myOrderList}
			new Vue({
				el:'#zone-content-box',
				data:{
					obligationNum:obligationNum,//待付款
					trsNum:trsNum,//待收货
					doneNum:doneNum,//已完成
					page:page,
					orderList:orderList,
					arr_log:[],//查看物流包裹flag标记
				},
				methods:{
					search_states:function(i){//左侧大状态筛选
						$('#orderStateFront1').val(i)
//						return console.log($('#orderStateFront1').val());
						//清空所有小状态 ：关键字，时间，订单状态 页码
						$('#searchWord').val('');
						$('#beginTime').val('');
						$('#orderStateFront2').val('')
						$('#currentPage').val(1);
						$('#fromIndex').val(0);
						$('#orderList-s').submit();
					},
					search_small:function(i){//右侧小状态筛选
						if(i == 1){//beginTime  (清空关键词和页码)
							$('#searchWord').val('');
							$('#currentPage').val(1);
							$('#fromIndex').val(0);
							$('#orderList-s').submit();
						}else if(i == 2){//orderStateFront2 (清空关键词和页码)
							$('#searchWord').val('');
							$('#currentPage').val(1);
							$('#fromIndex').val(0);
							$('#orderList-s').submit();
						}else if(i == 3){//searchWord (清空时间，小状态和页码)
							$('#beginTime').val('');
							$('#orderStateFront2').val('')
							$('#currentPage').val(1);
							$('#fromIndex').val(0);
							$('#orderList-s').submit();
						}
					},
					qiut_order:function(i){//取消订单
						var self = this;
						layer.confirm('亲，确认取消订单？',function(){
							$.ajax({
							    type:'post',
							    url:'orderDetail/cancelOrder',
							    data:{
							    	orderID:i
							    },
							    dataType:'json',
							    success:function(data){
							    	console.log(data)
							        if(data.state == 'success'){
							            layer.msg('订单取消成功')
							            setTimeout(function(){window.location.reload()},500) 
							        }else if(data.state == 'error'){
							            layer.msg(data.msg)
							        }else if(data.state == 'failed'){
							            layer.msg(data.msg)
							        }
							    }
							});
						})
					},
					reminder:function(){
						layer.msg('您的专属客服已收到催单信息，请耐心等候……')
					},
					go_cart:function(i){
//						订单类型：1微仓采购订单 ；2个人订单 3.微仓销售订单
//						pay_type  付款方式：1全款支付；2定金
						setTimeout(function(){
				            if(i == 1){
								window.open("<%=basePath%>cartPage/getCartFPGoods");
							}else{
								window.open("<%=basePath%>cartPage/getCartDPGoods");
							}
						},500);
					},
					buy_again:function(ev,i,j){//再次购买
						var self = this;
						$(ev.target).addClass('disabled')
						$.ajax({
						    type:'post',
						    url:'orderDetail/buyAgain',
						    async:false,
						    data:{
								orderID:i
							},
						    dataType:'json',
						    success:function(data){
						        if(data.state == 'success'){
						            layer.msg('即将进入购物车')
						            $(ev.target).removeClass('disabled')
						            self.go_cart(j)
						        }else if(data.state == 'error'){
						            layer.msg(data.msg)
						            $(ev.target).removeClass('disabled')
						        }else if(data.state == 'failed'){
						            layer.msg(data.msg)
						            $(ev.target).removeClass('disabled')
						        }
						    },
						    error:function(){
						    	$(ev.target).removeClass('disabled')
						    }
						});
					},
					confirm_receipt:function(i){//确认收货
						var self = this;
						layer.confirm('确认收货？',function(){
							$.ajax({
							    type:'post',
							    url:'orderDetail/confirmReceipt',
							    data:{
							    	orderID:i
							    },
							    dataType:'json',
							    success:function(data){
							        if(data.state == 'success'){
							            layer.msg('您已确认收货')
							            setTimeout(function(){window.location.reload()},500) 
							        }else if(data.state == 'error'){
							            layer.msg(data.msg)
							        }else if(data.state == 'failed'){
							            layer.msg(data.msg)
							        }
							    }
							});
						})
					},
					get_logistic_info:function(ev,i,j){//查看第一个物流信息
						var box = $(ev.target).find('.logistics-box').eq(0)
							orderID = i;
						if(this.arr_log.indexOf(j) == -1){
							this.arr_log.push(j)
						}else{
							box.css('display','block')
							return 
						}
						
						$.ajax({
						    type:'post',
						    url:'orderDetail/getLogisticInfo',
						    data:{
						    	orderID:i
						    },
						    dataType:'json',
						    success:function(data){
						    	console.log(data)
						        if(data.state == 'success'){
//						            layer.msg('成功')
									var log = data.result.logisticParcels
									var info = JSON.parse(log[0].logisticTrack);
									var list = info.Traces.reverse().slice(0,4);//倒序  最多显示四个 
									console.log(list)
									var timeline = "<ul class='timeline'>"
									for(var i = 0;i < list.length;i++){
										timeline+= "<li>"
	    												+"<i><b></b></i>"
													    +"<div>"
															+"<h3 class='layui-timeline-title'>"+list[i].AcceptStation+"</h3>"
													     	+"<p>"+list[i].AcceptTime+"</p>"
													    +"</div>"
													+"</li>"
									}
									timeline+= "</ul>"
	//								console.log(list)
									box.html('')
									var c_1 = "<div class='triangle_border_up'><span></span></div>"
									var c_2 = "<div class='logistics'>"
											+"<h2>您一共有"+log.length+"个包裹<a href='javascript:;' class='see-logistics'>查看更多</a></h2>"
											+"<h2>"+log[0].expCompany+" "+log[0].expNo+"</h2><hr/>"
											+timeline
											+"</div>"
									box.append(c_1+c_2)
//									box.parent().parent().parent().parent().parent().css('z-index','102');
									box.css('display','block')
						        }else if(data.state == 'error'){
						            layer.msg(data.msg)
						        }else if(data.state == 'failed'){
						            layer.msg(data.msg)
						        }
						    }
						});
					},
					close_log:function(ev){
						console.log(111)
						var box = $(ev.target).find('.logistics-box').eq(0);
						box.css('display','none')
					}
				}
			})
			})
		</script>
	</head>
	
	<body id="personal-body">
		
		<!--header-->		
		<%@ include file="../index/topLogin-center.jsp"%>
		<form method="post" action="myOrder" name="orderList-s" id="orderList-s" class="form-inline">
		<div id="myZone">
			<%@ include file="personalMenuList.jsp"%>
			<div id="zone-content">
				<div id="zone-content-box">
					<div class="title-name">个人订单</div>
					<div id="filters">
						<!--page, String searchWord, String beginTimeFront(一个月前29···), String orderStateFront1(逗号拼接字符串),String orderStateFront2(逗号拼接字符串)
	 					page  ,回显page.t.searchWord,page.t.beginTime,page.t.orderStateFront,myOrderList订单列表,obligationNum代付款,trsNum待收货,doneNum已完成-->
						<input type="hidden" name="orderStateFront1" id="orderStateFront1" :value="page.t.orderStateFront1"/>
						<ul>
							<li :class="{active:page.t.orderStateFront1 == ''}" @click="search_states('')">所有订单</li>
							<li :class="{active:page.t.orderStateFront1 == '1'}" @click="search_states('1')">待付款<i v-if="obligationNum != 0">{{obligationNum}}</i></li>
							<li :class="{active:page.t.orderStateFront1 == '6,7'}" @click="search_states('6,7')">待收货<i v-if="trsNum != 0">{{trsNum}}</i></li>
							<li :class="{active:page.t.orderStateFront1 == '8'}" @click="search_states('8')">已完成<i v-if="doneNum != 0">{{doneNum}}</i></li>
						</ul>
						<i class="separate"></i>
						<div class="search-box">
							<select name="beginTimeFront" id="beginTime" @change="search_small(1)" v-if="page.t.orderStateFront1 == ''" :value="page.t.beginTimeFront">
								<option value="">下单时间</option>
								<option value="29">近一个月</option>
								<option value="89">近三个月</option>
								<option value="364">近一年</option>
							</select>
							<select name="orderStateFront2" id="orderStateFront2" @change="search_small(2)" v-if="page.t.orderStateFront1 == ''" :value="page.t.orderStateFront2">
								<option value="">订单状态</option>
				            	<option value="1">待付款</option>
				            	<option value="2">已付款</option>
				            	<option value="3,10">已取消</option>
				            	<option value="4">待退款</option>
				            	<option value="5,9">已退款</option>
				            	<option value="6,7">待收货</option>
				            	<option value="8">已完成</option>	
							</select>
							<i class="search-btn" @click="search_small(3)"></i>
							<input name="searchWord" id="searchWord" @keyup.13="search_small(3)" :value="page.t.searchWord" placeholder="请输入商品或订单编号搜索"/>
						</div>
					</div>
					<!--<h2 id="order_c">
						<b state="" <c:if test="${pd.orderState == null}">class="active"</c:if>>所有订单</b>
						<b state="1" <c:if test="${pd.orderState == '1'}">class="active"</c:if>>待付款<i>${pendingPayONum}</i></b>
						<b state="6,7" <c:if test="${pd.orderState == '6,7'}">class="active"</c:if>>待收货<i>${pendingTakeONum}</i></b>
						<b state="8" <c:if test="${pd.orderState == '8'}">class="active"</c:if>>已完成<i>${finishONum}</i></b>
					</h2>
					<div class="search-box">
						<div class="input-group">
				            <input type="text" class="form-control" name="keyWord" id="keyWord" value="${pd.keyWord}" placeholder="请输入商品或订单编号搜索" />
				            <span class="input-group-addon pointer" id="searchOrder">搜索</span>
				        </div>
				        <div class="input-group float-right">
				        	<span class="input-group-addon">订单状态</span>
				            <select type="text" class="form-control" id="orderState" name="orderState">
				            	<option value="" <c:if test="${pd.orderState == null}">selected="selected"</c:if>>选择订单状态</option>
				            	<option value="1" <c:if test="${pd.orderState == '1'}">selected="selected"</c:if>>待付款</option>
				            	<option value="2" <c:if test="${pd.orderState == '2'}">selected="selected"</c:if>>已付款</option>
				            	<option value="3,10" <c:if test="${pd.orderState == '3,10'}">selected="selected"</c:if>>已取消</option>
				            	<option value="4" <c:if test="${pd.orderState == '4'}">selected="selected"</c:if>>待退款</option>
				            	<option value="5,9" <c:if test="${pd.orderState == '5,9'}">selected="selected"</c:if>>已退款</option>
				            	<option value="6,7" <c:if test="${pd.orderState == '6,7'}">selected="selected"</c:if>>待收货</option>
				            	<option value="8" <c:if test="${pd.orderState == '8'}">selected="selected"</c:if>>已完成</option>	
				            </select>
				        </div>
				        <div class="input-group float-right">
				        	<span class="input-group-addon">下单时间</span>
				            <select type="text" class="form-control" id="beginTime" name="beginTime">
				            	<option value="" <c:if test="${pd.beginTime == null}">selected="selected"</c:if>>选择下单时间</option>
				            	<option value="30" <c:if test="${pd.beginTime == '30'}">selected="selected"</c:if>>近一个月</option>
				            	<option value="90" <c:if test="${pd.beginTime == '90'}">selected="selected"</c:if>>近三个月</option>
				            	<option value="360" <c:if test="${pd.beginTime == '360'}">selected="selected"</c:if>>近一年</option>
				            </select>
				        </div>			       
					</div>-->
					<div class="list-box">
						<div class="car-head">
							<div class="col col0">商品详情</div>
							<div class="col col2">单价（元）</div>
							<div class="col col3">数量</div>
							<div class="col col4">金额（元）</div>
							<div class="col col5">订单状态</div>
							<div class="col col6">操作</div>
						</div>
						<table v-cloak v-for="(order,index) in orderList" class="my-order" border="0" cellpadding="0" cellspacing="0">
							<thead>
								<tr class="top-title">
									<th colspan="4">
										<i>北极光自营</i>
										<i><em>订单编号：</em>{{order.orderID}}</i>
										<b><em>下单时间：</em>{{order.orderTime}}</b>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="order-items">
										<table class="good-list">
											<tbody>
												<tr v-for="goods in order.orderGoods">
													<td class="goods-img">
														<a target="_blank" :href="'<%=basePath%>detailPage/goGoodsdetail?goodsID='+goods.goodsID">
															<img :src="goods.goodsMap"/>
														</a>
													</td>
													<td class="goods-name">
														<a target="_blank" :href="'<%=basePath%>detailPage/goGoodsdetail?goodsID='+goods.goodsID">{{goods.goodsName}}</a>
													</td>
													<td class="price">￥{{goods.goodsPrice.toFixed(2)}}</td>
													<td class="goods-num">{{goods.goodsNum}}</td>
												</tr>
											</tbody>
										</table>
									</td>
									<td class="pay" :rowspan="order.orderGoods.length">
										<b>
											<i>订单总额：￥{{order.shouldPayment.toFixed(2)}}</i>
											<em>（含运费：￥{{order.postage.toFixed(2)}}）</em>
											<em>（含税费：￥{{order.tax.toFixed(2)}}）</em>
										</b>
									</td>
									<td class="state" :rowspan="order.orderGoods.length">
										<i>
											<b v-if="order.orderState == 1">待付款</b>
											<b v-if="order.orderState == 2">备货中</b>
											<b v-if="order.orderState == 3 || order.orderState == 10">已取消</b>
											<b v-if="order.orderState == 4">退款中</b>
											<b v-if="order.orderState == 5">已退款</b>
											<b v-if="order.orderState == 6">已接单，待发货</b>
											<b v-if="order.orderState == 7" class="pointer view-logistics" @mouseenter="get_logistic_info($event,order.orderID,index)" @mouseleave="close_log($event)">物流运输中
												<div class="logistics-box"></div>
											</b>
											<b v-if="order.orderState == 8">已完成</b>
											<b v-if="order.orderState == 9">订单异常</b>
											<b v-if="order.orderState == 11">支付金额异常</b>
											<a v-if="order.orderState != 4 && order.orderState != 9" target="_blank" :href="'<%=basePath%>personal/orderDetail?orderID='+order.orderID">【查看详情】</a>
										</i>
									<!--{{order.orderState}}-->
									</td>
									<td class="operate" :rowspan="order.orderGoods.length">
										<!--付款-->
										<a class="btns" v-if="order.orderState == 1" target="_blank" :href="'<%=basePath%>personal/orderDetail/goPay?orderID='+order.orderID">付款</a>
										<!--催单-->
										<i class="btns" v-if="order.orderState == 2" @click="reminder()">催单</i>
										<!--取消订单-->
										<i class="quit" v-if="order.orderState == 1 || order.orderState == 2" @click="qiut_order(order.orderID)">取消订单</i>
										<!--再次购买-->
										<i class="btns" v-if="order.orderState == 3 || order.orderState == 5 || order.orderState == 8 || order.orderState == 9 || order.orderState == 10" @click="buy_again($event,order.orderID,order.payType)" >再次购买</i>
										<!--查看进度-->
										<a class="quit" v-if="order.orderState == 4 || order.orderState == 9" target="_blank" :href="'<%=basePath%>personal/orderDetail?orderID='+order.orderID">查看进度</a>
										<!--确认收货-->
										<i class="btns" v-if="order.orderState == 7" @click="confirm_receipt(order.orderID)">确认收货</i>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div id="no-order" v-cloak v-if="!orderList.length">
					<i>!</i>
					没有交易信息，赶紧行动吧！
				</div>
			<%@ include file="../commons/page.jsp"%>
			</div>
		</div>
		</form>
		<!--查看物流信息-->
		<form method="post" action="getMoreLogisticInfo" name="getMoreLogisticInfo" id="getMoreLogisticInfo" target="_blank" class="form-inline">
			<input type="hidden" id="l-orderID" name="orderID"/>
		</form>
		<!--订单详情-->
		<form method="post" action="goOrderDetail" name="goOrderDetail" id="goOrderDetail" target="_blank" class="form-inline">
			<input type="hidden" id="detail-orderID" name="orderID"/>
		</form>
		<!--订单支付-->
		<form method="post" action="orderPay" name="orderPay" id="orderPay" target="_blank" class="form-inline">
			<input type="hidden" id="pay-orderID" name="orderID"/>
		</form>
		<script>
			console.log(${page})
			console.log(${myOrderList})
			function search_orderList(i){
				if(i==1){
					$('#currentPage').val(1);
					$('#fromIndex').val(0);
				}
				$('#orderList-s').submit();
			}
			function goPage(pageNo) {
				$('#currentPage').val(pageNo);
				var fromIndex = (pageNo - 1) * $('#pageSize').val();
				if(fromIndex < 0) {
					fromIndex = 0;
				}
				$('#fromIndex').val(fromIndex);
				search_orderList()
			}
			$(function(){
				$('#searchOrder').on('click',function(){
					search_orderList(1);
				})
				
				//选择状态
				$('#order_c').on('click','b',function(){
					$('#orderState').val($(this).attr('state'));
					$('#keyWord').val('');
					$('#beginTime').val(''); 
					search_orderList(1);
				})
				
				
				//去查看物流详情页；
				$('.orderList').on('click','.see-logistics',function(){
					$('#l-orderID').val($(this).parent().attr('orderID'));
//					return console.log($('#l-orderID').val())
					$('#getMoreLogisticInfo').submit()
				})
				//去查看订单详情页面 orderDetail
				$('.orderList').on('click','.orderDetail',function(){
					$('#detail-orderID').val($(this).parent().attr('orderID'));
//					return console.log($('#l-orderID').val())
					$('#goOrderDetail').submit()
				})
				//再次购买 跳购物车 buy-again
				$('.buy-again').on('click',function(){
					var payType = $(this).parent().parent().attr('payType');
					var orderID = $(this).parent().attr('orderID');
					$.ajax({
						type:"post",
						url:"buyAgain",
						data:{'orderID':orderID},
						dataType:'json',
						async:false,
						success:function(data){
							console.log(data)
							if(data.result == 'success'){
								layer.msg('即将进入购物车');
								if(payType == '1'){
									setTimeout(function(){
										window.open("<%=basePath%>cartPage/getCartFPGoods");
									},500);
								}else{
									setTimeout(function(){
										window.open("<%=basePath%>cartPage/getCartDPGoods");
									},500);
								}
							}else if(data.result == 'error'){
								layer.msg(data.msg)
							}else if(data.result == 'failed'){
								layer.msg(data.msg)
							}
						}
					});
				})
			})
		</script>
		<!--页脚-->
		<%@ include file="../index/footer.jsp"%>
		<!--右侧固定-->
		<%@ include file="../index/rightFixed.jsp"%>
	</body>	
</html>