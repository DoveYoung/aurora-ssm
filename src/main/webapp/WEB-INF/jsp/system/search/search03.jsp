<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<ul class="breadcrumb">
	<li>所有分类</li>
	<li>${categoryName.get(0)}</li>
	<li>所有商品</li>
</ul>
<!--相关品牌-->
<div class="brand-opertaion">
	<div>品牌：</div>
	<ul class="brand-opertaion-list">
		<c:choose>
			<c:when test="${not empty brands2}">
				<c:forEach items="${brands2}" var="good" varStatus="vs">
					<li>
						<a class="brand-sh" href="javascript:;" title="${good}">${good}</a></li>
					</li>
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
			<c:when test="${not empty goodsList2}">
				
				<c:forEach items="${goodsList2}" var="good" varStatus="vs">
					<li class="m-goods">
						<c:if test="${good.gManage.deposit != '100'}">
							<div class="marks marks-deposit"></div>
						</c:if>
						<c:if test="${good.brandID == '70' || good.brandID == '76'}">
							<div class="marks marks-overseas"></div>
						</c:if>
						<div class="goodswrap">
							<a target="_blank" href="<%=basePath%>detailPage/goGoodsdetail?category2ID=${good.category2ID}&goodsID=${good.goodsID}" class="m-goodsImg" title="${good.goodsName}">
								<img src="../static/assets/img/blank.gif" data-echo="${good.mainMap}" />
								<span>${good.goodsSign}</span>
								<i class="hidden category1ID">${good.category1ID}</i>
								<i class="hidden category2ID">${good.category2ID}</i>
							</a>
							<h3>剩余库存：<i>${good.gManage.goodsStock}</i></h3>
							<div class="m-goodsInfo">
								<i>￥<b>${good.gManage.goodsPrice2}</b></i>
								<span>
									市场售价：￥<b>${good.gManage.marketPrice}</b>
								</span>
							</div>
							<h4 class="m-delivery"><a target="_blank" href="<%=basePath%>detailPage/goGoodsdetail?category2ID=${good.category2ID}&goodsID=${good.goodsID}"title="${good.goodsName}"><c:if test="${good.shipType == 1}">【保税仓直邮】</c:if><c:if test="${good.shipType == 2}">【海外直邮】</c:if><c:if test="${good.shipType == 3}">【国内现货】</c:if>${good.goodsName}</a></h4>
						</div>
					</li>
				</c:forEach>
			</c:when>
		</c:choose>
	</ul>
</div>
<%@ include file="../commons/page.jsp"%>
<!--类似-->
<div id="m-guess">
	<h4 class="m-title">
		类似货物品牌
		<i>全站最热门的品牌推荐</i>
	</h4>
	<div class="guess-goods-list">
		<c:choose>
			<c:when test="${not empty likeBrand2}">
				<c:forEach items="${likeBrand2}" var="good" varStatus="vs">
					<div class="guess-goods-list-pic">
						<div class="guess-goods-list-hover" brandID="${good.brand_id}" brandName="${good.brand_name}">
							<b class="attention">+关注</b>
							<h5>${good.care_num}人已关注该品牌</h5>
							<span class="attention-in attention-in-active">关注并进入</span>
						</div>
						<a target="_blank" href="<%=basePath%>detailPage/goGoodsdetail?category2ID=${good.category2_id}&goodsID=${good.goods_id}&sShipType=${good.ship_type}" title="${good.goods_name}">
							<img src="../static/assets/img/blank.gif" data-echo="${good.recommend_map}" />
						</a>
						<i>${good.brand_describe1}</i>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				暂时没有类似商品
			</c:otherwise>
		</c:choose>
	</div>
</div>
<div id="m-hot">
	<h4 class="m-title">
		大家都在买
		<i>全站最热门的商品推荐</i>
	</h4>
	<ul id="m-hot-goods">
		<c:choose>
			<c:when test="${not empty hotGoods2}">
				<c:forEach items="${hotGoods2}" var="good" varStatus="vs">
					
					<li class="m-goods">
						<c:if test="${good.deposit != '100'}">
							<div class="marks marks-deposit"></div>
						</c:if>
						<c:if test="${good.brand_id == '70' || good.brand_id == '76'}">
							<div class="marks marks-overseas"></div>
						</c:if>
						<div class="goodswrap">
							<a target="_blank" href="<%=basePath%>detailPage/goGoodsdetail?category2ID=${good.category2_id}&goodsID=${good.goods_id}" class="m-goodsImg" title="${good.goods_name}">
								<img src="../static/assets/img/blank.gif" data-echo="${good.main_map}" />
								<span>${good.goods_sign}</span>
							</a>
							<h3>剩余库存：<i>${good.goods_stock}</i></h3>
							<div class="m-goodsInfo">
								<i>￥<b>${good.goods_price1}</b></i>
								<span>
									市场售价：￥<b>${good.goods_price2}</b>
								</span>
							</div>
							<h4 class="m-delivery"><a target="_blank" href="<%=basePath%>detailPage/goGoodsdetail?category2ID=${good.category2ID}&goodsID=${good.goodsID}"title="${good.goodsName}"><c:if test="${good.ship_type == '1'}">【保税仓直邮】</c:if><c:if test="${good.ship_type == '2'}">【海外直邮】</c:if><c:if test="${good.ship_type == '3'}">【国内现货】</c:if>${good.goods_name}</a></h4>
						</div>
					</li>
				</c:forEach>
			</c:when>
		</c:choose>
	</ul>
</div>
<script>
	$(function(){
		//品牌选择
		$('.brand-sh').on('click',function(){
			$('#keyword').val($(this).text());
			$("#searchFrom").submit();
		})
		//关注
		$('.guess-goods-list-pic').on('mouseenter',function(){
			var brank_list_hover = $(this).find('.brank-list-hover');
			var brandID = brank_list_hover.attr('brandID');
			if(!customer()){
				return 	brank_list_hover.find('.attention-in').replaceWith("<span class='attention-in'>进入</span>");
			}
			$.ajax({
			    type:'GET',
			    url:'../customerAttention/judgeBrandCare?brandID='+brandID,
//							    data:{'brandID':brandID},
				cache: true,//缓存
			    dataType:'json',
			    headers: {
			        'Cache-Control': 'CACHE' 
			    },
			    success:function(data){
			    	console.log(data)
			        if(data.state == 'success'){
			        	brank_list_hover.find('.attention-in').replaceWith("<span class='attention-in'>进入</span>")
			        	brank_list_hover.find('b').replaceWith('<b>已关注</b>')
			        }
			    }
			});
		})
		//关注
		$('.guess-goods-list-pic').on('click','.attention',function(){
			if(!customer()){
				return 	to_login()
			}
			var brandID = $(this).parent().attr('brandID');
//							return console.log(brandID)
			var box_atn = $(this);
			$.ajax({
				type:"post",
				url:"../customerAttention/attentionBrand",
				data:{'brandID':brandID},
				dataType:'json',
				success:function(data){
					console.log(data)
					if(data.state == 'success'){
						layer.msg('关注成功');
						box_atn.siblings('h5').text(data.careNum+'人已关注该品牌');
						box_atn.siblings('.attention-in').replaceWith("<span class='attention-in'>进入</span>");
						box_atn.replaceWith('<b>已关注</b>');
//										box_atn.text()
					}else if(data.state == 'error'){
						layer.msg(data.msg)
					}else if(data.state == 'failed'){
						layer.msg(data.msg)
					}
				}
			});
		})
		//关注并进入 attention-in
		$('.guess-goods-list-pic').on('click','.attention-in',function(){
			var brandName = $(this).parent().attr('brandName');
			if(!customer()){
				$('#keyword').val(brandName);
				search()
				return 	
			}
			var brandID = $(this).parent().attr('brandID');
			var box_atn = $(this).siblings('.attention');
			if($(this).hasClass('attention-in-active')){
				$.ajax({
					type:"post",
					url:"../customerAttention/attentionBrand",
					async:false,
					data:{'brandID':brandID},
					dataType:'json',
					success:function(data){
						console.log(data)
						if(data.state == 'success'){
							layer.msg('关注成功,即将进入');
							box_atn.siblings('h5').text(data.careNum+'人已关注该品牌')
							box_atn.siblings('.attention-in').replaceWith("<span class='attention-in'>进入</span>")
							box_atn.replaceWith('<b>已关注</b>');
							$('#keyword').val(brandName);
							setTimeout(function(){
								$('.index-search').click();
							},500)
						}else if(data.state == 'error'){
							layer.msg(data.msg)
						}else if(data.state == 'failed'){
							layer.msg(data.msg)
						}
					}
				})
			}else{
				$('#keyword').val(brandName);
				search();
			}	
		})
	})
</script>