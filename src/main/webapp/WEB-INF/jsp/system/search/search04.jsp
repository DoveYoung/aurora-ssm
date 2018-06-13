<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--完全找不到-->
<div class="m-empty">
	<img src="../static/assets/img/blank.gif" data-echo="../static/assets/img/emptyImg.jpg"/>
	<div>
		<h3>
			抱歉，没有找到与<i>“${pd.keyword}”</i>相关的商品
		</h3>
		<h4 style="margin-bottom: 20px;">建议您：</h4>
		<h4>1.<a href="javascript:;" onclick="go_inquiry()">询价</a></h4>
		<h4>2.看看输入文字是否有误；或者分割关键字</h4>
	</div>
</div>
<!--大家都在买-->
<div id="m-hot">
	<h4 class="m-title">
		大家都在买
		<i>全站最热门的商品推荐</i>
	</h4>
	<ul id="m-hot-goods">
		<c:choose>
			<c:when test="${not empty hotGoods4}">
				<c:forEach items="${hotGoods4}" var="good" varStatus="vs">
					<li class="m-goods">
						<c:if test="${good.gManage.deposit != '100'}">
							<div class="marks marks-deposit"></div>
						</c:if>
						<c:if test="${good.gManage.shipType == '2'}">
							<div class="marks marks-overseas"></div>
						</c:if>
						<div class="goodswrap">
							<a target="_blank" href="<%=basePath%>detailPage/goGoodsdetail?goodsID=${good.goods_id}" class="m-goodsImg" title="${good.goods_name}">
								<img src="../static/assets/img/blank.gif" data-echo="${good.main_map}" />
								<span>${good.goods_sign}</span>
								<i class="hidden category1ID">${good.category1_id}</i>
								<i class="hidden category2ID">${good.category2_id}</i>
								<i class="hidden goodsID">${good.goods_id}</i>		
							</a>
							<h3>剩余库存：<i>999</i></h3>
							<div class="m-goodsInfo">
								<i>￥<b>${good.goods_price2}</b></i>
								<span>
									市场售价：￥<b>${good.market_price}</b>
								</span>
							</div>
							<h4 class="m-delivery"><a target="_blank" href="<%=basePath%>detailPage/goGoodsdetail?category2ID=${good.category2ID}&goodsID=${good.goods_id}"title="${good.goodsName}"><c:if test="${good.ship_type == '1'}">【保税仓直邮】</c:if><c:if test="${good.ship_type == '2'}">【海外直邮】</c:if><c:if test="${good.ship_type == '3'}">【国内现货】</c:if>${good.goods_name}</a></h4>
						</div>
					</li>
				</c:forEach>
			</c:when>
		</c:choose>
	</ul>
</div>
<script>
	function go_inquiry(){
		customer();
		if(!customerStatus){
			return to_login();
		}
		window.open('<%=basePath%>customerInquiry/goAddInquiryPage');
	}
</script>