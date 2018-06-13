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
		<title>个人中心--我的关注</title>
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
			#page-list{
				margin-top:50px
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
		<form method="post" action="attentionList" name="attentionList" id="attentionList" class="form-inline">
		<div id="myZone">
			<%@ include file="../myzone/myzoneMenuList.jsp"%>
			<div id="zone-content">
				<h2 id="order_c"><b>我的关注</b></h2>
				<c:if test="${attention != '{=[]}'}">
					<c:forEach items="${attention}" var="brand">
						<div class="brand_box">
							<div class="brand-l">
								<a class="brand-img" target="_blank" href="<%=basePath%>searchPage/search?keyword=${brand.value[0].brand_name}"><img src="../static/assets/img/blank.gif" data-echo="${brand.value[0].brand_map}"/></a>
								<b class="follow active" brandID="${brand.value[0].brand_id}">取消关注</b>
							</div>
							<div class="brand_list">
								<h3 class="title"><span><a target="_blank" href="<%=basePath%>searchPage/search?keyword=${brand.value[0].brand_name}">${brand.value[0].brand_name}</a></span><b class="see-allB">查看全部<i>></i></b></h3>
								<ul class="m-result-box">
									<c:forEach items="${brand.value}" var="good" varStatus="vs">
										
										<c:if test="${good.goods_id != null}">
											<li class="m-goods">
												<div class="goodswrap">
													<a target="_blank" href="<%=basePath%>detailPage/goGoodsdetail?goodsID=${good.goods_id}" class="m-goodsImg" title="${good.goods_name}">
														<img src="../static/assets/img/blank.gif" data-echo="${good.main_map}" />
														<span>${good.goods_sign}</span>
													</a>
													<div class="m-goodsInfo">
														<i><b>${good.goods_price2}</b></i>
														<span>
															市场价:￥<b>${good.market_price}</b>
														</span>
													</div>
													<h4 class="m-delivery"><c:if test="${good.ship_type == 1}">【保税仓直邮】</c:if><c:if test="${good.ship_type == 2}">【海外直邮】</c:if><c:if test="${good.ship_type == 3}">【国内现货】</c:if>${good.goods_name}</h4>
												</div>
											</li>
										</c:if>
									</c:forEach>
									
								</ul>
							</div>
						</div>
					</c:forEach>
					<%@ include file="../commons/page.jsp"%>
				</c:if>
				
			</div>
			
		</div>
		</form>
		<script>
			function search_attentionList(){
				$('#attentionList').submit();
			}
			function goPage(pageNo) {
				$('#currentPage').val(pageNo);
				var fromIndex = (pageNo - 1) * $('#pageSize').val();
				if(fromIndex < 0) {
					fromIndex = 0;
				}
				$('#fromIndex').val(fromIndex);
				search_attentionList()
			}
			$(function(){
				//取消关注
				$('.follow').on('click',function(){
					var brandID = $(this).attr('brandID');
					var box = $(this)
					if(!$(this).hasClass('active')){
						$.ajax({
						    type:'post',
						    url:'attentionBrand',
						    data:{'brandID':brandID},
						    dataType:'json',
						    success:function(data){
						        if(data.result == 'success'){
						        	box.addClass('active');
						        	box.text('取消关注')
						            layer.msg('关注成功')
						        }else if(data.result == 'error'){
						            layer.msg(data.msg)
						        }else if(data.result == 'failed'){
						            layer.msg(data.msg)
						        }
						    }
						});
					}else{
						layer.confirm('确定取消对该品牌的关注？',function(index){
							$.ajax({
								type:"post",
								url:"cancelBrandCare",
								data:{'brandID':brandID},
								dataType:'json',
								success:function(data){
									if(data.result == 'success'){
										box.removeClass('active');
										box.text('关注')
					    				layer.msg('取消关注成功')
					    			}else if(data.result == 'error'){
										layer.msg(data.msg)
									}else if(data.result == 'failed'){
										layer.msg(data.msg)
									}
								}
							});
						})
					}
				})
				//查看全部
				$('.see-allB').on('click',function(){
					var str = $(this).siblings('span').text();
					$('#keyword').val(str);
					headerSearch();
				})
			})
		</script>
		<!--页脚-->
		<%@ include file="../index/footer.jsp"%>
		<!--右侧固定-->
		<%@ include file="../index/rightFixed.jsp"%>
	</body>	
</html>