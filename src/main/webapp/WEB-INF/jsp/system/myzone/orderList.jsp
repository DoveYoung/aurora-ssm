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
		<title>个人中心--我的订单</title>
		<%@ include file="../index/headLink-p.jsp"%>
		<style>
			.view-logistics{
				position:relative;
				overflow: visible;
				z-index: 1000;
			}
			#myZone #zone-content .logistics-box{
				display: none;
				position:absolute;
				/*transform: scale(1);*/
				z-index: 100000;
				left: -85px;top:0;
				width:310px;min-height:100px;
				padding-top:20px;
			}
			#myZone #zone-content .logistics-box h2{
				height:14px;
				margin-top:18px;
				border:0 ;
				text-align: left;
				font-size:14px;
				line-height: 14px;
				color:#333;
			}
			#myZone #zone-content .logistics-box h2 a{
				display:inline-block;
				float:right;
			}
			.logistics{
				width:100%;height:auto;
				overflow: auto;
				padding:0 20px;
				border:1px solid #4e92df;
				border-top:2px solid #4e92df;
				background:#fff;
			}
			.triangle_border_up{
			    width:0;
			    height:0;
			    margin:0 auto;
			    border-width:0 10px 10px;
			    border-style:solid;
			    border-color:transparent transparent #4e92df;/*透明 透明  灰*/
			    position:relative;
			}
			.layui-icon b{
				display: inline-block;
				
				line-height: 20px;
				text-align: center;
			}
			.timeline li{
				height:auto;
				overflow: hidden;
			}
			.timeline li i{
				display: block;
				width:5px !important;height:22px;
				float:left;
				margin-right: 10px;
			}
			.timeline li i b{
				display: block;
				width:5px !important;height:5px;
				background:#999;
				border-radius: 50%;
			}
			.timeline li:first-child i b{
				display: block;
				width:5px;height:5px;
				/*margin-top: 8.5px;*/
				background:#4e92df;
			}
			.timeline li div{
				width:250px;
				float:right;
				text-align: left !important;
			}
			.timeline li:first-child div{
				color:#4e92df;
			}
			.timeline li div h3,.timeline li div p{
				font-size:14px;
				margin-bottom: 10px;
			}
			.money-b b{
				text-align: left !important;
			}
			.money-b b i{
				text-align: right !important;
			}
		</style>
	</head>

	<body id="personal-body">
		
		<!--header-->		
		<%@ include file="../index/topLogin-center.jsp"%>
		<form method="post" action="myOrder" name="orderList-s" id="orderList-s" class="form-inline">
		<div id="myZone">
			<%@ include file="personalMenuList.jsp"%>
			<div id="zone-content">
				<div class="title-name">个人订单</div>
				<h2 id="order_c">
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
				</div>
				<div class="list-box">
					<div class="car-head">
						<div class="col col0">商品详情</div>
						<div class="col col2">单价（元）</div>
						<div class="col col3">数量</div>
						<div class="col col4">金额（元）</div>
						<div class="col col5">订单状态</div>
						<div class="col col6">操作</div>
					</div>
					<c:forEach items="${myOrderList}" var="order">
						<div class="orderList" orderID="${order.value[0].order_id}">
							
							<div class="orderList-head">
								<i>北极光自营</i>
								<i>订单编号：${order.orderID}</i>
								<i>下单时间：${order.orderTime}</i>
								<!--<b>
									<c:if test="${order.value[0].pay_type == '1'}">全款订单</c:if>
									<c:if test="${order.value[0].pay_type == '2'}">定金订单</c:if>
								</b>							-->
							</div>
							<div class="orderList-box">
								
								<div class="orderList-l">
									<c:forEach items="${order.orderGoods}" var="list">
									<div class="orderList-goods">
										<div class="col col0">
											<a class="goods-img" href="<%=basePath%>detailPage/goGoodsdetail?goodsID=${list.goodsID}" target="_blank">
												<img src="../static/assets/img/blank.gif" data-echo="${list.goodsMap}"/>
											</a>
											<i>
												<a class="goods-name" href="<%=basePath%>detailPage/goGoodsdetail?goodsID=${list.goodsID}" target="_blank">${list.goodsName}</a>
											</i>
										</div>
										<div class="col col2">
											<i class="i-box">
												<b>￥${list.goodsPrice}</b>
											</i>
										</div>
										<div class="col col3">
											<i class="i-box">
												<b>${list.goodsPrice}</b>
											</i>
										</div>
									</div>
									</c:forEach>
								</div>
								<div class="orderList-r">
									<div class="col col4">
										<i class="i-box money-b">
											<b><i>订单总额：</i>￥${order.shouldPayment}</b>
											<b><i>实付：</i>￥${order.value[0].pay_money}</b>
											<b><i>邮费：</i>￥${order.value[0].postage}</b>
											
										</i>	
									</div>
									<div class="col col5">
										<i class="i-box" orderID="${order.value[0].order_id}">											
											<c:if test="${order.value[0].order_state == '1'}"><b>待支付</b></c:if>
											<c:if test="${order.value[0].order_state == '2'}"><b>已付款,待接单</b></c:if>
											<c:if test="${order.value[0].order_state == '3' || order.value[0].order_state == '10'}"><b>已取消</b></c:if>
											<c:if test="${order.value[0].order_state == '4'}"><b>待退款</b></c:if>
											<!--<c:if test="${order.value[0].order_state == '5' || order.value[0].order_state == '9'}"><b>已退款</b></c:if>-->
											<c:if test="${order.value[0].order_state == '5'}"><b>已退款</b></c:if>
											<c:if test="${order.value[0].order_state == '9'}"><b>已关闭</b></c:if>
											<c:if test="${order.value[0].order_state == '6'}"><b>已接单,待发货</b></c:if>
											<c:if test="${order.value[0].order_state == '7'}">
												<b class="pointer view-logistics">物流运输中
													<div class="logistics-box"></div>
												</b>
											</c:if>
											<c:if test="${order.value[0].order_state == '8'}"><b>已完成</b></c:if>										
										</i>
									</div>
									<div class="col col6" orderID="${order.value[0].order_id}" payType="${order.value[0].pay_type}">
										<i class="i-box" orderID="${order.value[0].order_id}">
											<b class="pointer orderDetail">查看详情</b>
										<c:if test="${order.value[0].order_state == '1'}">
											<b class="pointer payOrder">付款</b>
											<b class="pointer cancelOrder">取消订单</b>
										</c:if>
										<c:if test="${order.value[0].order_state == '2'}">
											<b class="pointer cancelOrder">取消订单</b>
										</c:if>
										<c:if test="${order.value[0].order_state == '3' || order.value[0].order_state == '10' || order.value[0].order_state == '5' || order.value[0].order_state == '9' || order.value[0].order_state == '8'}">
											<b class="pointer buy-again">再次购买</b>
										</c:if>
										<c:if test="${order.value[0].order_state == '7'}">
											<b class="pointer see-logistics">查看物流</b>	
											<b class="pointer confirm-goods">确认收货</b>	
										</c:if>
										</i>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
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
				//确认收货
				$('.confirm-goods').on('click',function(){
					var orderID = $(this).parent().attr('orderID');
					layer.confirm('确认收货？',function(index){
						$.ajax({
						    type:'post',
						    url:'confirmReceipt',
						    data:{'orderID':orderID},
						    dataType:'json',
						    success:function(data){
						        if(data.result == 'success'){
						            layer.msg('成功')
						            layer.close(index);
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
					});		
				})
				//选择状态
				$('#order_c').on('click','b',function(){
					$('#orderState').val($(this).attr('state'));
					$('#keyWord').val('');
					$('#beginTime').val(''); 
					search_orderList(1);
				})
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
									layer.close(index);
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
						
					})
				})
				//付款
				$('.payOrder').on('click',function(){
					var orderID = $(this).parent().attr('orderID');
					$('#pay-orderID').val(orderID);
					$('#orderPay').submit()
				})
				//查看第一个包裹物流预览
				$('.view-logistics').on('mouseenter',function(){
					var orderID = $(this).parent().attr('orderID');
					var box = $(this).find('.logistics-box').eq(0);				
					$.ajax({
						type:"post",
						url:"getLogisticInfo",
						data:{'orderID':orderID},
						dataType:'json',
						success:function(data){
							if(data.result == 'success'){
//								console.log(data)
								//无需物流
								if(data.logisticNum == 0){
									box.html('')
									var c_1 = "<div class='triangle_border_up'><span></span></div>"
									var c_2 = "<div class='logistics'>"
											+"<h2>无需物流</h2><hr/>"
											+"</div>"
									box.append(c_1+c_2)
									return box.css('display','block')
								}
								var info = JSON.parse(data.logisticInfo);
								var list = info.Traces;
//								console.log(list)
								var arr = []
								var j = 0;
								for(var i = list.length-1;i >= 0;i--){
									j++
									arr.push(list[i])
									if(j>=4){
										break
									}	
								}
								//layui 时间线
								var timeline = "<ul class='timeline'>"
								for(var i = 0;i < arr.length;i++){
									timeline+= "<li>"
    												+"<i><b></b></i>"
												    +"<div>"
														+"<h3 class='layui-timeline-title'>"+arr[i].AcceptStation+"</h3>"
												     	+"<p>"+arr[i].AcceptTime+"</p>"
												    +"</div>"
												+"</li>"
								}
								timeline+= "</ul>"
//								console.log(arr)
								box.html('')
								var c_1 = "<div class='triangle_border_up'><span></span></div>"
								var c_2 = "<div class='logistics'>"
										+"<h2 orderID='"+orderID+"'>您一共有"+data.logisticNum+"个包裹<a href='javascript:;' class='see-logistics'>查看更多</a></h2>"
										+"<h2>"+data.expCompany+" "+info.LogisticCode+"</h2><hr/>"
										+timeline
										+"</div>"
								box.append(c_1+c_2)
								box.parent().parent().parent().parent().parent().css('z-index','102');
								box.css('display','block')
							}else if(data.result == 'error'){
								layer.msg(data.msg)
							}else if(data.result == 'failed'){
								layer.msg(data.msg)
							}
						}
					});
				})
				$('.logistics-box').on('mouseleave',function(){
					$(this).html('');
					$(this).css('display','none')
					$(this).parent().parent().parent().parent().parent().css('z-index','100')
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