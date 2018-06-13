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
		<title>北极光供应链</title>
		<%@ include file="../index/headLink.jsp"%>
		<script>
			function search(i) {
//				if($('#keyword').val() == ''){
//					return location.reload();
//				}		
				if(i==1){
					$('#currentPage').val(1);
					$('#fromIndex').val(0);
				}else{
					$('#shipType').val('');
				}
				$("#searchFrom").submit();
			}
		</script>
	</head>

	<body id="search-body">
		<!--header-->
		<!--登录-->
		<c:if test="${customerID == null}">
		<div id="layer-login">
			<h2>账号密码登录</h2>
			<div class="error-tip"></div>
			<input id="inpt-count" placeholder="请输入您的邮箱登录" />
			<input id="inpt-pw" type="password" placeholder="请输入您的密码" />
			<input type="button" id="login-this" value="登录" />
			<div class="other-operate">
				<a target="_blank" href="<%=basePath%>registerLogin/goRegister">注册新账号</a>
				<a class="forgot-pw" target="_blank" href="<%=basePath%>registerLogin/goResetPwd">忘记密码</a>
			</div>
			<script>
				$(function(){
					//登录
					$('#to-login').on('click', function() {
						to_login()
					})
					$('#inpt-count').on('blur', function() {
						var count = $(this).val();
						var reg = /^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/gi;
						if(!reg.test(count)) {
							$('.error-tip').text('请填入正确的账号');
						} else {
							$('.error-tip').text('');
						}
					})
					$('#inpt-pw').on('blur', function() {
						if($(this).val() == '') {
							$('.error-tip').text('请输入密码');
						}
					}).on('keydown',function(event){
						var e = event || window.event || arguments.callee.caller.arguments[0];
						if(e && e.keyCode == 13){
							login();
						}
					})
					$('#login-this').on('click', function() {
						login()
					})
				})
				console.log(${homeCategory})
			</script>
		</div>
		</c:if>
		<form method="get" action="search" id="searchFrom" name="searchFrom" class="form-inline" >
		<div id="header" class="header-border">
			<div class="top-box">
				<div class="top">
				<c:choose>
					<c:when test="${customerID == null}">
						<i>您好！欢迎来到北极光跨境供应链</i>
						<a class="to-login" id="to-login" href="javascript:;">登录</a>
						<em>|</em>
						<a class="to-register" target="_blank" href="<%=basePath%>registerLogin/goRegister">免费注册</a>
						
					</c:when>
					<c:otherwise>
						<i>您好，会员${customerEmail}</i>
						<em>|</em>
						<i class="login-out" id="logout">退出登录</i>
						<script>
							$('#logout').on('click',function(){
								layer.confirm('确定退出登录？', function(index) {
									$.ajax({
										type:'POST',
										url:'../registerLogin/logout',
										success:function(data){
											if(data.state == 'success'){
												window.location.href = '<%=basePath%>'
											}else{
												console.log(data.msg)
											}
										},
										error:function(){
											layer.msg('网络超时，与服务器断开连接')
										}
									})
									layer.close(index)
								})
							})
						</script>
					</c:otherwise>
				</c:choose>
					<a class="help-center" target="_blank" href="<%=basePath%>footer/depositMode">帮助中心</a>
					<b onclick="go_customer_center()">个人中心</b>
				</div>
			</div>
			<div class="header-search">
				<div class="search-box">
					<a class="logo" href="<%=basePath%>"></a>
					<div class="ipt-box">
						<div class="ipt-left">
							
								<select id="all-goods" name="searchCategory">
									<option value="">所有商品</option>
									<c:forEach items="${homeCategory}" var="Cate" varStatus="vs">
										<option value="${Cate.categoryName}">${Cate.categoryName}</option>
									</c:forEach>
								</select>
								<i class="boundary"></i>
								<input name="keyword" id="keyword" placeholder="${pd.keyword}"/>
								<input type="button" class="search-btn toSearchBtn" value="搜索" onclick="search(1);" />
								<!--邮寄方式-->
								<input type="hidden" class="toSearchInput" value="${pd.shipType}" name="shipType" id="shipType" />
								<!--品牌ID-->
								<input type="hidden" class="toSearchInput" value="${pd.sBrand}" name="sBrand" id="sBrand" />
								<!--排序字段-->
								<input type="hidden" class="toSearchInput" value="${pd.orderBY}" name="orderBY" id="orderBY" />
								<!--升降序-->
								<input type="hidden" class="toSearchInput" value="${pd.orderAD}" name="orderAD" id="orderAD" />
								
						</div>
						<a href="javascript:;" class="toSearchSupply">发布询价</a>
						<a href="javascript:;" class="my-cart" onclick="go_cart()">
							我的购物车
							<i>${customerCartNum != null ? (customerCartNum > 100 ? '99+' : customerCartNum) : '0'}</i>
						</a>
						<ul class="hot-word">
							<c:forEach items="${homeSearch}" var="val">
								<li>
									<a target="_blank" href="<%=basePath%>searchPage/search?keyword=${val}">${val}</a>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			<div id="nav">
				<nav>
					<div class="all-cate">
						<i>全部商品分类</i>
						<div class="cate-box cate-box-none" id="cate-box-page">
							<ul class="cate-list">
								<c:forEach items="${homeCategory}" var="Cate" varStatus="vs">
									<li>
										<h1>
											<a target="_blank" href="<%=basePath%>searchPage/search?keyword=${Cate.categoryName}">
												<b class="icon-${Cate.categoryID}"></b>
												${Cate.categoryName}<i></i>
											</a>
										</h1>
										<p>
											<c:forEach items="${Cate.subcategory}" var="cate02" begin="0" end="2" step="1" varStatus="vs02">
												<a target="_blank" href="<%=basePath%>searchPage/search?keyword=${cate02.categoryName}">${cate02.categoryName}</a>
											</c:forEach>
										</p>
										<ul>
											<c:forEach items="${Cate.subcategory}" var="cate02">
												<li>
													<h1><a target="_blank" href="<%=basePath%>searchPage/search?keyword=${cate02.categoryName}">${cate02.categoryName}</a></h1>
													<div class="item-namebox">
														<c:forEach items="${cate02.subcategory}" var="cate03">
															<i><a target="_blank" <c:if test="${cate03.red == 1}">class="red"</c:if> href="<%=basePath%>searchPage/search?keyword=${cate02.categoryName}+${cate03.categoryName}">${cate03.categoryName}</a></i>
														</c:forEach>
													</div>
												</li>
											</c:forEach>
										</ul>
										<i style="top:${vs.index*72+1}px"></i>
									</li>
								</c:forEach>
								
							</ul>
						</div>
					</div>
					<a class="left" target="_blank" href="<%=basePath%>largeBuyPage/largeBuyList">大额采购</a>
					<a class="left" href="javascript:;" onclick="go_inquiry()">批发询价</a>
					<a class="left" href="javascript:;">我要供货</a>
					<a class="center" href="javascript:;">|</a>
					<a target="_blank" href="<%=basePath%>searchPage/search?shipType=1">保税代发</a>
					<a target="_blank" href="<%=basePath%>searchPage/search?shipType=2">海外直邮</a>
					<a target="_blank" href="<%=basePath%>searchPage/search?shipType=3">一般贸易</a>
				</nav>
			</div>
		</div>
		<script>
			$('.all-cate').on('mouseover',function(){
				$(this).children('.cate-box').removeClass('cate-box-none')
				
			}).on('mouseout',function(){
				$(this).children('.cate-box').addClass('cate-box-none')
			})
			$('.cate-list').children('li').on('mouseover',function(){
				var i = $(this).index();
			//	console.log(111)
			//	$(this).children('h1').eq(0).css('border','0');
				if(i < $('.cate-list').children('li').length-1){
					$(this).next().children('h1').eq(0).addClass('h1-active');
				}
				
			}).on('mouseout',function(){
				var i = $(this).index();
				if(i < $('.cate-list').children('li').length-1){
					$(this).next().children('h1').eq(0).removeClass('h1-active');
				}
			})
					
					$('#keyword').on('keydown',function(event){
						var e = event || window.event || arguments.callee.caller.arguments[0];
						if(e && e.keyCode == 13){
							$("#searchFrom").submit();
						}
					})
				</script>
			</header>
		</div>

		<!--layout-->
		<div class="body-box">	
			<div class="m-layout">
				<c:choose>
					<c:when test="${state == 1}">
						<%@ include file="search01.jsp"%>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${state == 2}">
						<%@ include file="search03.jsp"%>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${state == 3}">
						<%@ include file="search02.jsp"%>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${state == 4}">
						<%@ include file="search04.jsp"%>
					</c:when>
				</c:choose>
			</div>	
		</div>
		</form>
		<!--页脚-->
		<%@ include file="../index/footer.jsp"%>
		<!--右侧固定-->
		<%@ include file="../index/rightFixed.jsp"%>
		<script>
			function goPage(pageNo) {
				$('#currentPage').val(pageNo);
				var fromIndex = (pageNo - 1) * $('#pageSize').val();
//				return console.log(fromIndex)
				if(fromIndex < 0) {
					fromIndex = 0;
				}
				$('#fromIndex').val(fromIndex);
				$("#searchFrom").submit();
			}
			$(function(){
				//回到顶部
				$('#toTop').on('click',function(){
				   	var sc=$(window).scrollTop();
				   	$('body,html').animate({scrollTop:0},200);
				})
				//筛选排序
				$('#m-filterList').on('hover','li',function(){
					$('#m-filterList li').removeClass('active');
					$(this).addClass('active');
				})
				//升降序
				$('#m-filterList').on('click','li',function(){
					var i = $(this).index();
					var arr = ['4','1','2','3']			
					$('#orderBY').val(arr[i]);
					if(i != 3){
						$('#orderAD').val('DESC');
					}else{
						if($(this).find('i').hasClass('icon-down')){
							$('#orderAD').val('ASC');
						}else{
							$('#orderAD').val('DESC');
						}
					}
//					return console.log($('#orderAD').val())
					search(1);
				})
				
			})					
		</script>
	</body>
</html>