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
	<c:if test="${hCartDPMath.size() > 0}">
		<div id="German" class="mail">
			<i>德国直邮</i>
			<b></b>
		</div>

		<div class="goods">
			<ul class="goodsBox" id="German-box">
				<c:forEach items="${hCartDPMath}" var="good">
					<li class="gooditm">
						<div class="hidden">${good.cartID}</div>
						<div class="col col2">
							<a href="javascript:;" class="goodImg">
								<img src="../static/assets/img/blank.gif" data-echo="${good.mainMap}" />
							</a>
							<span>${good.goodsName}</span>
						</div>
						<div class="col col3">${good.buyNum}</div>
						<div class="col col4">${good.goodsPrice}</div>
						<div class="col col5"></div>
						<div class="col col6">${good.deposit}%</div>
						<div class="col col7">${good.uDMoney}</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>

	<!--保税仓直邮-->
	<c:if test="${bCartDPMath.size() > 0}">
		<div id="wareHouse" class="mail">
			<i>保税仓直邮</i>
			<b></b>
		</div>
		<div class="goods">
			<ul class="goodsBox" id="wareHouse-box">
				<c:forEach items="${bCartDPMath}" var="good">
					<li class="gooditm">
						<div class="hidden">${good.cartID}</div>
						<div class="col col2">
							<a href="javascript:;" class="goodImg">
								<img src="../static/assets/img/blank.gif" data-echo="${good.mainMap}" />
							</a>
							<span>${good.goodsName}</span>
						</div>
						<div class="col col3">${good.buyNum}</div>
						<div class="col col4">${good.goodsPrice}</div>
						<div class="col col5"></div>
						<div class="col col6">${good.deposit}%</div>
						<div class="col col7">${good.uDMoney}</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
	<!--国内现货-->
	<c:if test="${gCartDPMath.size() > 0}">
		<div id="theSpot" class="mail">
			<i>国内现货</i>
		</div>
		<div class="goods">
			<ul class="goodsBox" id="theSpot-box">
				<c:forEach items="${gCartDPMath}" var="good">
					<li class="gooditm">
						<div class="hidden">${good.cartID}</div>
						<div class="col col2">
							<a href="javascript:;" class="goodImg">
								<img src="../static/assets/img/blank.gif" data-echo="${good.mainMap}" />
							</a>
							<span>${good.goodsName}</span>
						</div>
						<div class="col col3">${good.buyNum}</div>
						<div class="col col4">${good.goodsPrice}</div>
						<div class="col col5"></div>
						<div class="col col6">${good.deposit}%</div>
						<div class="col col7">${good.uDMoney}</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
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
			<i>￥${tFMoney}</i>
		</div>
		<div class="list">
			<span>应付金额总计：</span>
			<i class="all">￥${pDMoney}</i>
		</div>
		<div class="btnBox">
			<a href="javascript:;" id="z-submit">提交订单</a>
		</div>
		<h6>${wc1Address.name}收    ${wc1Address.province}${wc1Address.city}${wc1Address.area}   电话  ${wc1Address.mobile}</h6>
	</div>
</div>
<!--提交订单-->
<form method="get" action="goDPayFC" id="goDPayFC" name="goDPayFC" class="form-inline">
	<!--保税仓-->
	<input type="hidden" value="${pd.bCartIDs}" name="bCartIDs" id="bCartIDs" />
	<!--海外直邮-->
	<input type="hidden" value="${pd.hCartIDs}" name="hCartIDs" id="hCartIDs" />
	<!--国内直邮-->
	<input type="hidden" value="${pd.gCartIDs}" name="gCartIDs" id="gCartIDs" />
</form>
<script>
	function settlement() {
		var shipBox = ['#German-box', '#wareHouse-box', '#theSpot-box'];
		var ship_type = ['', '', ''];
		for(var i = 0; i < 3; i++) {
			if($(shipBox[i]).find('li')) {
				for(var j = 0; j < $(shipBox[i]).find('li').length; j++) {
					ship_type[i] += ',' + $(shipBox[i]).find('li').eq(j).find('.hidden').text();
				}
				ship_type[i] = ship_type[i].substring(1);
			}
			//					console.log(ship_type[i])
		}
		var quoteVo = {};
		quoteVo.hCartIDs = ship_type[0];
		quoteVo.bCartIDs = ship_type[1];
		quoteVo.gCartIDs = ship_type[2];
		$.ajax({
			type: 'post',
			url: 'checkAmountDP',
			data: quoteVo,
			dataType: 'json',
			success: function(data) {
				//		    	console.log(data);
				if(data.result == 'success') {
					$('#hCartIDs').val(ship_type[0]);
					$('#bCartIDs').val(ship_type[1]);
					$('#gCartIDs').val(ship_type[2]);
					//		return console.log($('#saID').val());
					$('#goDPayFC').submit();;
				} else if(data.result == 'error') {
					layer.msg(data.msg)
				} else if(data.result == 'failed') {
					layer.msg(data.msg)
				}
			}
		});
	}
	$(function() {
		$('#z-submit').on('click', function() {
			settlement()
		})
	})
</script>