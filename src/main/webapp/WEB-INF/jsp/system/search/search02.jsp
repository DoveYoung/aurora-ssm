<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--类似商品-->

<div class="m-similarTips">
	没找到与
	<i>“${pd.keyword}”</i>相关的商品，北鼻为你推荐
	<i>“${brands3.get(0)}”</i>相关的产品，或者你可以
	<a href="javascript:;"  onclick="go_inquiry()">“寻找货源”</a>
</div>
<!--面包屑-->
<ul class="breadcrumb">
	<li>所有分类</li>
	<li>${brands3.get(0)}</li>
	<li>所有商品</li>
</ul>
<!--相关品牌-->
<div class="brand-opertaion">
	<div>品牌：</div>
	<ul class="brand-opertaion-list">
		<c:choose>
			<c:when test="${not empty brands3}">
				<c:forEach items="${brands3}" var="good" varStatus="vs">
					<li><a href="javascript:;" title="${good}">${good}</a></li>
				</c:forEach>
			</c:when>
		</c:choose>
	</ul>
</div>
<!--排序-->
<nav id="m-filter">
	<ul id="m-filterList">		
		<li <c:if test="${pd.orderBY == '4'}">class="active"</c:if>>
			综合</li>
		<li <c:if test="${pd.orderBY == '1'}">class="active"</c:if>>
			销量</li>
		<li <c:if test="${pd.orderBY == '2'}">class="active"</c:if>>
			新货</li>
		<li <c:if test="${pd.orderBY == '3'}">class="active"</c:if>>
			价格<i class="<c:if test="${pd.orderBY == '3' && pd.orderAD == 'ASC'}">icon-up</c:if><c:if test="${pd.orderBY == '3' && pd.orderAD == 'DESC'}">icon-down</c:if>"></i></li>
	</ul>
</nav>
<div id="m-result">
	<ul class="m-result-box">
		<c:choose>
			<c:when test="${not empty goodsList3}">
				<c:forEach items="${goodsList3}" var="good" varStatus="vs">
					<li class="m-goods">
						<div class="goodswrap">
							<a target="_blank" href="<%=basePath%>detailPage/goGoodsdetail?category2ID=${good.category2ID}&goodsID=${good.goodsID}" class="m-goodsImg" title="${good.goodsName}">
								<img src="../static/assets/img/blank.gif" data-echo="${good.mainMap}" />
								<!--<span>${good.goodsSign}</span>-->
								<i class="hidden category1ID">${good.category1ID}</i>
								<i class="hidden category2ID">${good.category2ID}</i>
								<i class="hidden goodsID">${good.goodsID}</i>								
							</a>
							<h3>剩余库存：<i>${good.gManage.goodsStock}</i></h3>
							<div class="m-goodsInfo">
								<i>￥<b>${good.gManage.goodsPrice2}</b></i>
								<span>
									市场售价：￥<b>${good.gManage.marketPrice}</b>
								</span>
							</div>
							<h4 class="m-delivery"><c:if test="${good.ship_type == '1'}">【保税仓直邮】</c:if><c:if test="${good.ship_type == '2'}">【海外直邮】</c:if><c:if test="${good.ship_type == '3'}">【国内现货】</c:if>${good.goodsName}</h4>
						</div>
					</li>
				</c:forEach>
			</c:when>
		</c:choose>
	</ul>
</div>