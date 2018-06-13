<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="info-box">
	<h1>收货人信息</h1>
	<div class="info-box-l">
		<div id="address-box">
			<ul id="addr-ul" style="border:none">
				<li class="address-list">
					<a href="javascript:;" class="address-check address-active" style="pointer-events:none;">
						<span><i>寄送给</i></span>
						<em></em>
						<i style="width:260px">北极光微仓（免费仓储，批量发货）</i>
					</a>
				</li>
			</ul>
		</div>
	</div>
	<div class="info-box-r">
		<div class="inpt-box">
			<i>收货人：</i>
			<input readonly="readonly" id="c-name" value="${wc1Address.name}" />
		</div>
		<div class="inpt-box">
			<i>电话号码：</i>
			<input readonly="readonly" id="c-mobile" value="${wc1Address.mobile}" />
		</div>
		<div class="inpt-box inpt-box-02">
			<i class="left-01">省份：</i>
			<input readonly="readonly" id="c-province" value="${wc1Address.province}" />
		</div>
		<div class="inpt-box inpt-box-03">
			<i>城市：</i>
			<input readonly="readonly" id="c-city" value="${wc1Address.city}" />
		</div>
		<div class="inpt-box inpt-box-03">
			<i>地区：</i>
			<input readonly="readonly" id="c-area" value="${wc1Address.area}" //>
		</div>
		<div class="inpt-box inpt-box-01">
			<i>详细地址：</i>
			<textarea readonly="readonly" id="detail-address">${wc1Address.detail_address}</textarea>
		</div>
	</div>
</div>
<div class="carBox depositBox">
	<div id="good-category">
		<a href="javascript:;">确认商品信息</a>
	</div>
	<div class="car-head">
		<div class="col col2">商品信息</div>
		<div class="col col3">数量</div>
		<div class="col col4">单价（元）</div>
		<div class="col col5">重量（千克）</div>
		<div class="col col6">定金比例</div>
		<div class="col col7">金额（元）</div>
	</div>
	<!--德国直邮-->
	<c:if test="${gManage.shipType == '1'}">
		<div id="German" class="mail">
			<i>保税仓直邮</i>
			<b></b>
		</div>
	</c:if>
	<c:if test="${gManage.shipType == '2'}">
		<div id="German" class="mail">
			<i>德国直邮</i>
			<b></b>
		</div>
	</c:if>
	<c:if test="${gManage.shipType == '3'}">
		<div id="German" class="mail">
			<i>国内现货</i>
			<b></b>
		</div>
	</c:if>
	<div class="goods">
		<ul class="goodsBox">
			<li class="gooditm">
				<div class="hidden" id="good-box" data-id="${gManage.goodsID}" data-num="${buyNum}"></div>
				<div class="col col2">
					<a href="javascript:;" class="goodImg">
						<img src="../static/assets/img/blank.gif" data-echo="${gManage.goodsDetails.mainMap}" />
					</a>
					<span>${gManage.goodsDetails.goodsName}</span>
				</div>
				<div class="col col3">${buyNum}</div>
				<div class="col col4">${goodsPrice}</div>
				<div class="col col5">${gManage.weight}</div>
				<div class="col col6">${gManage.deposit}%</div>
				<div class="col col7">${dPayment}</div>
			</li>
		</ul>
	</div>
	
</div>

<!--提交订单-->
<div class="payBox">
	<div class="payBox-l">
		<h6>物流须知：</h6>
		<p>1.仅限支持定金模式的商品才可以使用定金付款，一旦选用定金付款即视为您定下该商品，我们为您锁定该商品库存，定金不能以任何理由退还。</p>
		<p>2.定金付款的商品只能选择进入微仓，不可直接发货</p>
		<p>3.定金付款产品从微仓发货时需支付尾款和运费</p>
		<p>4.定金购买的商品以支付定金时的价格为准，不受后期商品价格波动影响</p>
		<p>5.通过定金购买的商品库存锁定期为2个月。即，您需在2个月内陆续发货完毕，到期后未发货的剩余商品需一次性补足尾款。我们的客服会在到期前通过邮件或电话提醒您支付尾款完成购买，逾期未支付尾款该商品会失效，无法发货，定金不予退还</p>
	</div>
	<div class="payBox-r">
		<div class="list">
			<span>商品价格总计：</span>
			<i>￥${tPayment}</i>
		</div>
		<!--<div class="list">
			<span>运费：</span>
			<i>￥10.00</i>
		</div>-->
		<div class="list">
			<span>应付定金金额总计：</span>
			<i class="all">￥${dPayment}</i>
		</div>
		<div class="btnBox">
			<a href="javascript:;" id="z-submit">提交订单</a>
		</div>
		<h6>${wc1Address.name}收    ${wc1Address.province}${wc1Address.city}${wc1Address.area}   电话  ${wc1Address.mobile}</h6>
	</div>
</div>
<form method="get" action="goDPayFLB" id="goDPayFLB" name="goDPayFLB" class="form-inline">
	<input type="hidden" value="${pd.goodsID}" name="goodsID" id="goodsID" />
	<input type="hidden" value="${pd.buyNum}" name="buyNum" id="buyNum" />
</form>
<script>
	$(function(){
		//提交订单
		$('#z-submit').on('click',function(){
			customer()
			if(!customerStatus){
				return to_login()
			}
			//订单提交预处理
			var quotaVo = {};
			console.log(1)
			quotaVo.goodsID = $('#good-box').data('id');
			quotaVo.buyNum = $('#good-box').data('num');
			$.ajax({
			    type:'post',
			    url:'checkAmountD',
			    data:quotaVo,
			    dataType:'json',
			    success:function(data){
			    	console.log(1)
			        if(data.result == 'success'){
			           	$('#goodsID').val($('#good-box').data('id'));
						$('#buyNum').val($('#good-box').data('num'));
						$('#goDPayFLB').submit();
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
