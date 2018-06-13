<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav id="topTableBox">
	<ul id="topTable">
		<li class="Catalog topTable-active">所有分类&nbsp;∨
		<div id="menu-items">
			<ul id="first-items">
			<c:choose>
				<c:when test="${not empty category1ALL}">
					<c:forEach items="${category1ALL}" var="good" varStatus="vs">
						<li class="search-category search-cate01 <c:if test="${vs.index == 0}">first-items-active</c:if>">${good.category_name}</li>
					</c:forEach>
				</c:when>
			</c:choose>
			</ul>
			<ul id="second-items">
				<c:forEach items="${categoryALL}" var="l1Category" varStatus="vs">
					<li class="item-box <c:if test="${vs.index == 0}">second-items-active</c:if>">
						<c:forEach items="${l1Category}" var="menu1">
							<div class="itemBox">
								<h1><a href="javascript:;" class="search-category search-cate01">${menu1.categoryName}</a></h1>
								<c:if test="${menu1.subCategory != '[]'}">
									<div class="item-namebox">
										<c:forEach items="${menu1.subCategory}" var="menu2">
											<i><a href="javascript:;" class="search-category">${menu2.categoryName}</a></i>
										</c:forEach>
									</div>	
								</c:if>
								<br>
							</div>
						</c:forEach>
					</li>
				</c:forEach>
			</ul>
		</div>
		</li>
		<li class="ship-s <c:if test="${pd.shipType == 1}">active</c:if>">
			保税仓直邮</li>
		<li class="ship-s <c:if test="${pd.shipType == 2}">active</c:if>">
			海外直邮</li>
		<li class="ship-s <c:if test="${pd.shipType == 3}">active</c:if>">
			国内现货</li>
	</ul>
</nav>
<script>
	$('#topTable').on('click','.ship-s',function(){
		var i = $(this).index();
		if(i == 0){
			$('#shipType').val('');
			return 
		}else{
			$('#shipType').val(i);
		}
		$('#keyword').val('');
		$("#searchFrom").submit();
	})
	$('.Catalog').on('mouseover', function() {
		$('#menu-items').css('display', 'block');
	})
	$('.Catalog').on('mouseout', function() {
		$('#menu-items').css('display', 'none');
	})
	//一级菜单
	$('#first-items').on('mouseover', 'li', function() {
		var i = $(this).index();
		$('#first-items li').removeClass('first-items-active');
		$('#second-items li').removeClass('second-items-active');
		$(this).addClass('first-items-active');
		$('#second-items li').eq(i).addClass('second-items-active');
	})
	//菜单类目点击搜索事件
	$('.search-category').on('click',function(){
		if($(this).hasClass('search-cate01')){
			$('#keyword').val($(this).text());
		}else{
			$('#keyword').val($(this).parent().parent().siblings('h1').text()+' '+$(this).text());
		}
		$('#shipType').val('');
		search();
	})
</script>