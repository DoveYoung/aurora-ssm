<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<style>
		.car-head{
			margin-top:30px;margin-bottom: 20px;
		}
		.col1{
			width:54px;
		}
		.col0{
			width:320px !important;
		}
		.col2{
			width:80px !important;
		}
		.col3{
			width:130px !important;
		}
		.orderList .col{
			height:105px !important;
		}
		.orderList .col0{
			padding-left:0px !important;
			padding-top:0px !important;
		}
		.orderList-goods{
			height:105px !important;
		}
		.orderList .col0 .goods-img{
			margin-top:20px;
			margin-left: 20px;
		}
		.orderList .col0 .goods-img,.orderList .col0 img{
			width:64px !important;
			height:64px !important;
		}
		.orderList .col0 .i-box{
			padding:0 !important;
			width:200px !important;
		}
		.orderList-l{
			width:642px !important;
			margin-left: 54px;
		}
		.orderList-l .col2{
			line-height: 105px;
		}
		.orderList-r{
			width:340px !important;
		}
		.orderList-inpt{
			position:absolute;
			width:54px;
			height: 100%;
			border-right:1px solid #e4e4e4;
		    display: -webkit-flex;
		    display: flex;
		    -webkit-align-items: center;
		    align-items: center;
		    -webkit-justify-content: center;
		    justify-content: center;
		}
		.payment{
			color:#3994DC;
		}
	</style>
	<h2 id="order_c">
		<b state="wchw">微仓货物</b>
		<b state="wcfh">微仓发货</b>
		<b class="active">订单付款</b>
	</h2>
<form method="post" action="goMicroWarehouse" name="goMicroWarehouse" id="goMicroWarehouse" class="form-inline">
</form>
<form method="post" action="goDeliverGoods" name="goDeliverGoods" id="goDeliverGoods" class="form-inline">
</form>
<form method="post" action="goMWOrder" name="goMWOrder" id="goMWOrder" class="form-inline">
	<div class="list-box">
		<div class="car-head">
			<div class="col col1" style="height:10px"></div>
			<div class="col col0">商品详情</div>
			<div class="col col2">数量</div>
			<div class="col col2">单价(元)</div>
			<div class="col col2">预付款(元)</div>
			<div class="col col2">尾款(元)</div>
			<div class="col col2">运费(元)</div>
			<div class="col col3">收货人</div>
			<div class="col col3">操作</div>
		</div>
		<c:forEach items="${mwOrderMap}" var="order">
			
			<div class="orderList" mwOrderID="${order.value[0].mw_order_id}">
				<div class="orderList-head">
					<i>订单编号：${order.value[0].mw_order_id}</i>
					<i>下单时间：${order.value[0].order_time}</i>	
				</div>
				<div class="orderList-box">
					<div class="orderList-inpt">
						<div class="col col1">
							<i class="i-box">
								<input type="checkbox" name="order_c"/>
							</i>	
						</div>
					</div>
					<div class="orderList-l">
					<c:forEach items="${order.value}" var="good">
						<div class="orderList-goods">
							<div class="col col0">
								<a class="goods-img" href="<%=basePath%>detailPage/goGoodsdetail?goodsID=${good.goods_id}" target="_blank">
									<img src="../static/assets/img/blank.gif" data-echo="${good.goods_map}"/>
								</a>
								<i class="i-box">
									<a class="goods-name" href="<%=basePath%>detailPage/goGoodsdetail?goodsID=${good.goods_id}" target="_blank">${good.goods_name}</a>
								</i>
							</div>
							<div class="col col2">${good.send_num}</div>
							<div class="col col2">${good.goods_price}</div>
							<div class="col col2">${good.advance_pay}</div>
							<div class="col col2 final_pay">${good.final_pay}</div>
						</div>
					</c:forEach>
					</div>
					<div class="orderList-r">
						<div class="col col2">
							<i class="i-box postage">${order.value[0].postage}</i>	
						</div>
						<div class="col col3">
							<i class="i-box">${order.value[0].consignee}</i>	
						</div>
						<div class="col col3">
							<i class="i-box">
								<b class="pointer payment" mwOrderID="${order.value[0].mw_order_id}">支付尾款</b>
							</i>	
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</form>
<script>
	function pay_c(){
		var n = $('.orderList-active').length;
		if(n){
			$('#settlement').removeClass('not-checked');
			$('#totalNum').text(n);
			var m = 0;
			for(var i = 0;i < n;i++){
				var x = $('.orderList-active').eq(i).find('.final_pay');
				for(var j = 0;j < x.length;j++){
					m += parseFloat(x.eq(j).text())
				}
				m += parseFloat($('.orderList-active').eq(i).find('.postage').eq(0).text())
			}
			m = m.toFixed(2)
			$('#totalPrice').text(m)
		} else {
			$('#settlement').addClass('not-checked');
			$('#totalNum').text(0);
			$('#totalPrice').text('0.00');
		}
	}
	function check_box(i,j){
		$(i).on('click',function(){
			if($(this).is(':checked')){
				//全选
				$(j).each(function(){
					$(this).prop("checked",true)
					$(this).parent().parent().parent().parent().parent().addClass('orderList-active')
				})
			}else{
				//撤销全选
				$(j).each(function(){
					$(this).prop("checked",false)
					$(this).parent().parent().parent().parent().parent().removeClass('orderList-active')
				})
			}
			pay_c()
		})
		//td-checked 判定
		$(j).on('click',function(){
			//取消全选
			if($(this).is(':checked')){
				var check = false
				$(this).parent().parent().parent().parent().parent().addClass('orderList-active')
				$(j).each(function(){
					if(!$(this).is(':checked')){
						return check = false;
					}else{
						return check = true;
					}	
				})
				if(check){
					$(i).prop("checked",true);
				}		
			}else{
				$(this).parent().parent().parent().parent().parent().removeClass('orderList-active');
				$(i).prop("checked",false);
			}
			pay_c()
		})
	}
	
	$(function(){
		check_box($('#checkAll02'),$("input[name='order_c']"));
		//支付尾款
		$('.payment').on('click',function(){
			var mwOrderID = $(this).attr('mwOrderID');
			$('#mwOrderID').val(mwOrderID);
			$('#goMWOrderSettle').submit();
		})
		//合并付款
		$('#settlement').on('click',function(){
			var orders = $('.orderList-active');
			var mwOrderID = ''
			for(var i = 0;i < orders.length;i++){
				mwOrderID += ','+ orders.eq(i).attr('mwOrderID');
			}
			mwOrderID = mwOrderID.substring(1);
			$('#mwOrderID').val(mwOrderID);
			console.log(mwOrderID)
			$('#goMWOrderSettle').submit();
		})
		//删除订单
		$('#delete-allChecked').on('click',function(){
			var num = 0;
			var mwOrderID = '';
			for(var i = 0; i < $('.orderList').length; i++) {
				if($('.orderList').eq(i).hasClass('orderList-active')){
					mwOrderID += ','+$('.orderList').eq(i).attr('mwOrderID');
					num++;
				}
			}
			if(num){
				layer.confirm('确定删除选中的订单？', function(index) {
					mwOrderID = mwOrderID.substring(1);
					$.ajax({
						type: "POST",
						url: "deleteMWOrder",
						data: {'mwOrderID':mwOrderID},
						dataType: "json",
						success: function(data) {
									console.log(data)
							if(data.state == 'success') {
								for(var i = $('.orderList-active').length - 1; i >= 0; i--) {
									$('.orderList-active').eq(i).remove();
								}
								layer.close(index);
							} else {
								layer.msg('删除失败')
							}
						}
					})	
				})
			} else {
				layer.confirm('请选择需要删除的订单', function(index) {
					layer.close(index);
				})
			}
		})
	})
</script>