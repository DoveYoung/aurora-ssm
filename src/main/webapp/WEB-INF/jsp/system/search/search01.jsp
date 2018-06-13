<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--商品品牌-->
<!--面包屑-->
<ul class="breadcrumb">
	<li>所有分类</li>
	<li>${category.get(0).brand_category_name}</li>
	<li>所有商品</li>
</ul>
<!--品牌-->
<div class="m-brand">
	<a href="javascript:;" id="m-brandImg" title="${brand.getBrandName()}">
		<!--品牌广告图 -->
		<img src="../static/assets/img/blank.gif" data-echo="${brand.getAdvertiseMap()}" />
	</a>
	<div class="m-brandInfo">
		<div class="m-brandInfo-box">
			<div class="m-brandInfo-top">
				<a href="javascript:;" id="m-brandLogo">
					<!--品牌logo -->
					<img src="../static/assets/img/blank.gif" data-echo="${brand.getBrandMap()}" />
				</a>
				<div id="m-brandName">
					<a href="javascript:;" title="${brand.getBrandName()}">${brand.getBrandName()}</a>
					<span>
						<b>${brand.sellStoreNum}</b>个淘宝店铺在售
					</span>
				</div>
				<div id="m-brandNation">
					<ul>
						<li>${brand.getCountryEName()}</li>
						<li>${brand.getCountryCName()}</li>
					</ul>
					<img src="../static/assets/img/blank.gif" data-echo="${brand.getNationalFlag()}" />
				</div>
			</div>
			<!--BrandDescribe()品牌描述-->
			
			<div class="m-brandInfo-center">
				<p title="${brand.getBrandDescribe2()}">${brand.getBrandDescribe2()}</p>
			</div>
			<div class="m-brandInfo-bottom" brandID="${brand.brandID}">
				<c:choose>
					<c:when test="${hasAttention == 'success'}">
						<a class="Concern" href="javascript:;">已关注</a>
						<a class="cancelFocus" href="javascript:;">取消关注</a>
					</c:when>
					<c:otherwise>
						<a class="brandToFocus" href="javascript:;">+关注</a>
					</c:otherwise>
				</c:choose>
				<span>
					<i class="brandFocusNum">${brand.getCareNum()}</i>人已关注	
				</span>
			</div>
		</div>
	</div>
</div>
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
<!--列表-->
<div id="m-result">
	<ul class="m-result-box">
		<c:choose>
			<c:when test="${not empty goodsList1}">
				<c:forEach items="${goodsList1}" var="good" varStatus="vs">
					<li class="m-goods">
						<c:if test="${good.deposit != '100'}">
							<div class="marks marks-deposit"></div>
						</c:if>
						<c:if test="${good.brand_id == '70' || good.brand_id == '76' }">
							<div class="marks marks-overseas"></div>
						</c:if>
						<div class="goodswrap">
							<a target="_blank" href="<%=basePath%>detailPage/goGoodsdetail?category2ID=${good.category2_id}&goodsID=${good.goods_id}" class="m-goodsImg" title="${good.goods_name}">
								<img src="../static/assets/img/blank.gif" data-echo="${good.main_map}" />
								<span>${good.goods_sign}</span>
							</a>
							<h3>剩余库存：<i>${good.goods_stock}</i></h3>
							<div class="m-goodsInfo">
								<i>￥<b>${good.goods_price2}</b></i>
								<span>
									市场售价：￥<b>${good.market_price}</b>
								</span>
							</div>
							<h4 class="m-delivery"><a target="_blank" href="<%=basePath%>detailPage/goGoodsdetail?category2ID=${good.category2_id}&goodsID=${good.goods_id}" title="${good.goods_name}"><c:if test="${good.ship_type == 1}">【保税仓直邮】</c:if><c:if test="${good.ship_type == 2}">【海外直邮】</c:if><c:if test="${good.ship_type == 3}">【国内现货】</c:if>${good.goods_name}</a></h4>
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
		<i> </i>
	</h4>
	<div class="guess-goods-list">
		<c:choose>
			<c:when test="${not empty likeBrand1}">
				<c:forEach items="${likeBrand1}" var="good" varStatus="vs">
					<div class="guess-goods-list-pic">
						<div class="guess-goods-list-hover" brandID="${good.brand_id}" brandName="${good.brand_name}">
							<b>+关注</b>
							<h5>${good.care_num}人已关注该品牌</h5>
							<span class="attention-in">进入</span>
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
<!--大家都在买-->
<div id="m-hot">
	<h4 class="m-title">
		大家都在买
		<i>全站最热门的商品推荐</i>
	</h4>
	<ul id="m-hot-goods">
		<c:choose>
			<c:when test="${not empty hotGoods1}">
				<c:forEach items="${hotGoods1}" var="good" varStatus="vs">
					<li class="m-goods">
						<c:if test="${good.deposit != '100'}">
							<div class="marks marks-deposit"></div>
						</c:if>
						<c:if test="${good.brand_id == '70' || good.brand_id == '76' }">
							<div class="marks marks-overseas"></div>
						</c:if>
						<div class="goodswrap">
							<a target="_blank" href="<%=basePath%>detailPage/goGoodsdetail?goodsID=${good.goods_id}"  class="m-goodsImg" title="${good.goods_name}">
								<img src="../static/assets/img/blank.gif" data-echo="${good.main_map}" />
								<span>${good.goods_sign}</span>
							</a>
							<h3>剩余库存：<i>${good.goods_stock}</i></h3>
							<div class="m-goodsInfo">
								<i>￥<b>${good.goods_price2}</b></i>
								<span>
									市场售价：￥<b>${good.goods_price2}</b>
								</span>
							</div>
							<h4 class="m-delivery"><a target="_blank" href="<%=basePath%>detailPage/goGoodsdetail?category2ID=${good.category2_id}&goodsID=${good.goods_id}" title="${good.goods_name}"><c:if test="${good.ship_type == 1}">【保税仓直邮】</c:if><c:if test="${good.ship_type == 2}">【海外直邮】</c:if><c:if test="${good.ship_type == 3}">【国内现货】</c:if>${good.goods_name}</a></h4>
						</div>
					</li>
				</c:forEach>
			</c:when>
		</c:choose>
	</ul>
</div>
<script>
	$(function(){
		//关注显示
		//+关注
		$('.m-brandInfo-bottom').on('click','.brandToFocus',function(){
			if(!customer()){
				return 	to_login()
			}
			var box = $(this)
			var brandID = $(this).parent().attr('brandID');
			$.ajax({
				type:"post",
				url:"../customerAttention/attentionBrand",
				data:{'brandID':brandID},
				dataType:'json',
				success:function(data){
					if(data.state == 'success'){
						layer.msg('关注成功');
						box.before("<a class='Concern' href='javascript:;'>已关注</a><a class='cancelFocus' href='javascript:;'>取消关注</a>");
						box.siblings('span').find('.brandFocusNum').text(data.careNum)
						box.remove()
					}else if(data.state == 'error'){
						layer.msg(data.msg)
					}else if(data.state == 'failed'){
						layer.msg(data.msg)
					}
				}
			});
		})
		//取消关注
		$('.m-brandInfo-bottom').on('click','.cancelFocus',function(){
			if(!customer()){
				return 	to_login()
			}
			var box = $(this);
			var brandID = $(this).parent().attr('brandID');
			layer.confirm('确定要取消关注？',function(index){
				$.ajax({
				    type:'post',
				    url:'../customerAttention/cancelBrandCare',
				    data:{'brandID':brandID},
				    dataType:'json',
				    success:function(data){
				        if(data.state == 'success'){
				            layer.msg('取消关注成功')
				            box.before("<a class='brandToFocus' href='javascript:;'>+关注</a>");
				            box.siblings('.Concern').remove();
				            box.siblings('span').find('.brandFocusNum').text(data.careNum);
				            box.remove();
				        }else if(data.state == 'error'){
				            layer.msg(data.msg)
				        }else if(data.state == 'failed'){
				            layer.msg(data.msg)
				        }
				    }
				});
				layer.close(index)
			})
		})	
		//鼠标滑过
		$('.guess-goods-list-pic').on('mouseenter',function(){
			var brank_list_hover = $(this).find('.guess-goods-list-hover');
			var brandID = brank_list_hover.attr('brandID');
			if(!customer()){
				return 	brank_list_hover.find('.attention-in').replaceWith("<span class='attention-in'>进入</span>");
			}
			$.ajax({
			    type:'post',
			    url:'../customerAttention/judgeBrandCare',
			    data:{'brandID':brandID},
			    dataType:'json',
			    success:function(data){
//			    	console.log(data)
			        if(data.state == 'success'){
			        	brank_list_hover.find('.attention-in').replaceWith("<span class='attention-in'>进入</span>")
			        	brank_list_hover.find('b').replaceWith("<b class='attentioned'>已关注</b>")
			        }
			    }
			});
		})
		//列表关注
		$('.guess-goods-list-pic').on('click','b',function(){
			if(!customer()){
				return 	to_login()
			}
			if($(this).hasClass('attentioned')){
				return 
			}
			var box = $(this)
			var brandID = $(this).parent().attr('brandID');
			$.ajax({
				type:"post",
				url:"../customerAttention/attentionBrand",
				data:{'brandID':brandID},
				dataType:'json',
				success:function(data){
					console.log(data)
					if(data.state == 'success'){
						layer.msg('关注成功');
						box.siblings('h5').text(data.careNum+"人已关注该品牌")
						box.replaceWith("<b class='attentioned'>已关注</b>");
						
					}else if(data.state == 'error'){
						layer.msg(data.msg)
					}else if(data.state == 'failed'){
						layer.msg(data.msg)
					}
				}
			});
		})
		//列表进入
		$('.guess-goods-list-pic').on('click','.attention-in',function(){
			var brandName = $(this).parent().attr('brandName');
			$('#keyword').val(brandName);
			search();
//			if(!customer()){
//				$('#keyword').val(brandName);
//				search()
//				return 	
//			}
//			var brandID = $(this).parent().attr('brandID');
//			var box_atn = $(this).siblings('.attention');
//			if($(this).hasClass('attention-in-active')){
//				$.ajax({
//					type:"post",
//					url:"../customerAttention/attentionBrand",
//					async:false,
//					data:{'brandID':brandID},
//					dataType:'json',
//					success:function(data){
//						console.log(data)
//						if(data.state == 'success'){
//							layer.msg('关注成功');
//							box_atn.siblings('.attention-in').replaceWith("<span class='attention-in'>进入</span>")
//							box_atn.replaceWith('<b>已关注</b>');
//							$('#keyword').val(brandName);
//							$('.toSearchBtn').click();
//						}else if(data.state == 'error'){
//							layer.msg(data.msg)
//						}else if(data.state == 'failed'){
//							layer.msg(data.msg)
//						}
//					}
//				})
//			}else{
//				$('#keyword').val(brandName);
//				
//			}
		})
	})
</script>