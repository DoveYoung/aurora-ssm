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
		<meta name="viewport" content="width=1200" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!--<metahttp-equiv="Content-Security-Policy"content="upgrade-insecure-requests"/>-->
		<title>北极光供应链-专注正品 货源国本土采购 跨境供应链领航家！</title>
		<link rel="icon" href="static/assets/img/logo_tit.png" type="image/x-icon"/>
		<meta name="keywords" content="北极光供应链，北极光，跨境，跨境电商，进口，跨境进口，保税进口，进口保税，进口货源，保税货源，海外直邮，保税区发货，一件代发，一键代发，进口分销，货源分销，海外供应链，欧洲货源，电器进口，跨境集采，跨境批发，海外批发" />
		<meta name="description" content="北极光供应链，中国进口跨境供应链领航者。坚持货源国本土采购，100%正品保证，海外授权一手货源，自营海外仓，全程供应链路可追溯。主营类目美妆护肤，母婴保健，家清日化，厨具家电。提供海外仓自提，海外直邮，保税仓代发，国内现货，以及海陆空立体运输方案。万千平台与客户的首选！"/>
		<link rel="stylesheet" href="static/assets/plugin/bootstrap-3.3.7/css/bootstrap.min.css">
		<!--<link href="//cdn.bootcss.com/bootstrap/4.0.0-alpha.6/css/bootstrap-grid.min.css" rel="stylesheet" />-->
		<link rel="stylesheet" type="text/css" href="static/assets/css/animate.min.css" />
		<c:set var="base_url" value="<%=basePath%>"></c:set>
		<c:if test="${base_url != 'http://localhost:8080/aurorascm/'}">
			<link rel="stylesheet" type="text/css" href="static/assets/css/admin.css" />
		</c:if>
		<c:if test="${base_url == 'http://localhost:8080/aurorascm/'}">
			<link rel="stylesheet/less" type="text/css" href="static/assets/css/admin.less" />
			<script src="static/assets/js/less.js" language="javascript"></script>
		</c:if>
		<script src="static/assets/js/common.js" language="javascript"></script>
		<script src="static/assets/js/jquery-3.2.1.min.js"></script>
		<script src="static/assets/plugin/echo/echo.min.js"></script>
		<script src="static/assets/plugin/layer/layer.js"></script>
		<script src="static/assets/plugin/bootstrap-3.3.7/js/bootstrap.min.js"></script>
		<script>
			var _hmt = _hmt || [];
			(function() {
			  var hm = document.createElement("script");
			  hm.src = "https://hm.baidu.com/hm.js?ba1be72b0d777b181425157f7d67a676";
			  var s = document.getElementsByTagName("script")[0]; 
			  s.parentNode.insertBefore(hm, s);
			})();
		</script>
		<!-- 中国版  Global site tag (gtag.js) - Google Analytics -->
		<script async src="https://www.googletagmanager.com/gtag/js?id=UA-114912233-1"></script>
		<script>
		  window.dataLayer = window.dataLayer || [];
		  function gtag(){dataLayer.push(arguments);}
		  gtag('js', new Date());
		
		  gtag('config', 'UA-114912233-1');
		</script>
		<script>
			function monitor(i){
				var modules ='module'+i;
				console.log(modules);
				_hmt.push(['_trackEvent', modules, 'click', 'num']);
			}
			function search() {
				$('#keyword').val($.trim($('#keyword').val()))
				if($('#keyword').val() == ''){
					return console.log('空的')
				}
				$("#searchFrom").submit();
			}
		</script>
		<style>
			#main{
				margin:0 auto !important;
			}
		</style>
	</head>

	<body id="aurorascm-index">
		<%  String customerID = (String)session.getAttribute("customerID");%>
		<input id="customerID" type="hidden" value="${customerID}" />
		<!--登录-->
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
						monitor(1);
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
							login(1);
						}
					})
					$('#login-this').on('click', function() {
						login(1)
					})
				})
			</script>
		</div>
		<!--菜单类目 遮罩层-->
		<div class="menu-items-mark"></div>
		<!--header-->
		<input id="customerIDinpt" type="hidden" value="${customerID}" />
		<div id="header">
			<c:choose>
				<c:when test="${customerID == null}">
					<!--顶部-->
					<div id="top">
						<!--logo-->
						<div class="logo">
							<a href="<%=basePath%>">
								<img src="static/assets/img/logo.gif" />
							</a>
							<i class="logo-tip"></i>
						</div>
						<!--注册与登录-->
						<div class="top-r">
							<a href="<%=basePath%>registerLogin/goRegister">注册</a>
							<a id="to-login" href="javascript:;">登录</a>
							<!--<a target="_blank" href="<%=basePath%>registerLogin/goLoginPage">登录测试页面</a>-->
						</div>
					</div>
					<script>
						//顶部查询
						window.onscroll = function() {
							if($(document).scrollTop() > 120) {
								$('#nav').addClass('top0');
							} else {
								$('#nav').removeClass('top0');
							}
							if($(document).scrollTop() > 390 && !document.getElementById('vd').paused){
								$('#drag-box').addClass('suspension');
							}else{
								$('#drag-box').removeClass('suspension');
							}
							if($('.industry-data').offset().top - $(document).scrollTop() > 300){
								$('.rightBarNew').addClass('rightBarNew_index');
							}else{
								$('.rightBarNew').removeClass('rightBarNew_index');
							}
						}
						if($(document).scrollTop() > 120) {
							$('#nav').addClass('top0');
						} else {
							$('#nav').removeClass('top0');
						}
						
					</script>
				</c:when>
				<c:otherwise>
					<script>
						//顶部查询
						window.onscroll = function() {
							if($(document).scrollTop() > 0) {
								$('#nav').addClass('top0');
							} else {
								$('#nav').removeClass('top0');
							}
							if($(document).scrollTop() > 330 && !document.getElementById('vd').paused){
								$('#drag-box').addClass('suspension');
							}else{
								$('#drag-box').removeClass('suspension');
							}
							if($('.industry-data').offset().top - $(document).scrollTop() > 0){
								$('.rightBarNew').addClass('rightBarNew_index02');
							}else{
								$('.rightBarNew').removeClass('rightBarNew_index02');
							}
						}
						if($(document).scrollTop() > 0) {
							$('#nav').addClass('top0');
						} else {
							$('#nav').removeClass('top0');
						}
					</script>
				</c:otherwise>
			</c:choose>
			<!--nav-->
			<div id="nav">
				<section>
					<div class="logo">
						<c:choose>
							<c:when test="${customerID == null}">
							</c:when>
							<c:otherwise>
								<img src="static/assets/img/logo02.png" />
							</c:otherwise>
						</c:choose>						
					</div>
					<div class="Catalog">
						<img src="static/assets/img/catalog01.gif" />
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
									<li class="item-box <c:if test="${vs.index == '0'}">second-items-active</c:if>">
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
					</div>
					<div class="nav-search">
						<form method="get" action="searchPage/search" id="searchFrom" name="searchFrom" class="form-inline" target="_blank">
							<input type="text" class="search-frame" value="${pd.keyword}" name="keyword" id="keyword" />
							<input type="hidden" class="search-frame hidden" value="${pd.shipType}" name="shipType" id="shipType" />
							<input type="button" class="search-btn index-search" value="搜索" onclick="search();monitor(2)" />
						</form>
					</div>
					<div class="findGoods">
						<a href="javascript:;" onclick="go_inquiry(1);monitor(2)">我要找货</a>
					</div>
					<c:choose>
						<c:when test="${customerID == null}">
						</c:when>
						<c:otherwise>
							<div class="Personal-Center">
								<a onclick="monitor(2)" href="<%=basePath%>customerOrder/orderList" target="_blank">个人中心</a>	
							</div>
							<div class="cart">
								<a onclick="monitor(2)" href="<%=basePath%>cartPage/goCartPage" target="_blank"><img src="static/assets/img/cart_index.gif"></a>
							</div>
						</c:otherwise>
					</c:choose>	
				</section>
			</div> 			
		</div>
		
		<!--main-->
		<form method="get" action="searchPage/seeMoreByBID" id="seeMoreByBID" name="seeMoreByBID" class="form-inline">
			<input type="hidden" class="search-frame hidden" value="${pd.brandID}" name="brandID" id="brandID" />
		</form>
		<div id="main">
			<section class="introduce-info introduce-info-new">
				<div class="main-box">
					<div class="introduce-font">
						<span>专业贴心的<b>供应链</b>服务</span>
						<p>本站独特的微仓服务和定金支付系统，能为您节约<i>50％</i>以上的流动资金。</p>
						<p class="phone">服务专线：0571-8791 6639</p>
						<a id="playVideo" href="javascript:;" onclick="video_p();">查看视频</a>
						<!--<i>2018年春节客服放假安排 :</i>
						<i>2018年2月13日至2018年2月21日</i>-->
					</div>
					<div class="introduce-video">
						<!--<img src="static/assets/img/blank.gif"  data-echo="static/assets/img/playVideo.png" />-->
						<!--static/assets/video/1913e2f2661131c623fb10e56fdb7e03.mp4-->
						<div id="drag-box" data-title="按下鼠标可拖拽" style="background:#ccc;">
							<div class="vd-box" onclick="props()">
								<video id="vd" onclick="video_p()" ondblclick="video_fullScreen(this)" autoplay="autoplay" muted  src="static/assets/video/promotional_video.mp4" preload="meta" allowfullscreen="allowfullscreen" controls="controls" poster="static/assets/img/video_poster.png">
								Your browser does not support the video tag.
								</video>
								<a class="close-sign" title="点击关闭视屏" href="javascript:;" onclick="drag_close(this)"></a>
							</div>
							
						</div>
					</div>
					<div class="introduce-banner">
						<ul>
							<li>
								<img src="static/assets/img/banner01.png" />
								<i>50%</i>
								<span>定金拿货</span>
							</li>
							<li>
								<img src="static/assets/img/banner02.png" />
								<i>48小时</i>
								<span>急速询盘报价</span>
							</li>
							<li>
								<img src="static/assets/img/banner03.png" />
								<i>24小时</i>
								<span>微仓随时发货</span>
							</li>
							<li class="introduce-bannner-04">
								<img src="static/assets/img/banner04.png" />
								<i>5868</i>
								<span>款各种商品</span>
							</li>
						</ul>
					</div>
				</div>
			</section>
			<!--行业数据-->		
			<section class="industry-data">
				<div class="main-box">
					<!--排行榜-->
					<div class="ranking-list">
						<!--行业数据-->
						<div class="list-title">
							<span>行业数据</span>
							<a href="javascript:;">${IndustryData[0][0].time1}-${IndustryData[0][0].time2}</a>
						</div>
						<div class="list-move">
							<c:forEach items="${IndustryData}" var="listC" varStatus="vs">
								<div class="lists-move">
									<table class="table table-condensed">
										<caption>
											<c:if test="${vs.index == 0}">产品销量排行榜</c:if>
											<c:if test="${vs.index == 1}">产品销量上升榜</c:if>
											<c:if test="${vs.index == 2}">品牌关注排行榜</c:if>
											<c:if test="${vs.index == 3}">新品销量上升榜</c:if>
											<c:if test="${vs.index == 4}">热词搜索榜</c:if>
										</caption>
										<thead>
											<tr>
											<c:if test="${vs.index == 0 || vs.index == 1 || vs.index == 3}"><th>产品名称</th></c:if>	
											<c:if test="${vs.index == 2}"><th>品牌名称</th></c:if>
											<c:if test="${vs.index == 4}"><th>热搜词</th></c:if>
											<c:if test="${vs.index == 0}"><th>上月销量</th></c:if>
											<c:if test="${vs.index == 2}"><th>历史关注数量</th></c:if>
											<c:if test="${vs.index == 1 || vs.index == 3}"><th>上月销售趋势</th></c:if>
											<c:if test="${vs.index == 4}"><th>上月搜索次数</th></c:if>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${listC}" var="list" varStatus="status">
												<tr>
													<td class="font-wap" title="${list.keyword}">
														<span class="condensed-num <c:if test="${status.index > 2}">condensed-num-b</c:if>">${status.index+1}</span>
														<c:if test="${vs.index == 0 || vs.index == 1 || vs.index == 3}">
															<i><a target="_blank" onclick="monitor(${'4'+vs.index})" href="<%=basePath%>detailPage/goGoodsdetail?goodsID=${list.goods_id}">${list.keyword}</a></i>
														</c:if>
														<c:if test="${vs.index == 2 || vs.index == 4}">
															<i><a href="javascript:;" onclick="monitor(${'4'+vs.index});industry_brand(this)">${list.keyword}</a></i>
														</c:if>
													</td>
													<td class="text-left <c:if test="${vs.index == 0 || vs.index == 2 || vs.index == 4}">text-num</c:if>">
														<c:if test="${list.trend_sign == '1' && vs.index != 0 && vs.index != 2 && vs.index != 4}"><span class="glyphicon glyphicon-arrow-up"></span></c:if>
														<c:if test="${list.trend_sign == '2' && vs.index != 0 && vs.index != 2 && vs.index != 4}"><span class="glyphicon glyphicon-arrow-down"></span></c:if>
														${list.trend_index}<c:if test="${vs.index != 0 && vs.index != 2 && vs.index != 4}">%</c:if>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</section>
			<!--淘宝京东热卖-->
			<section class="tbjd-hot-sale">
				<div class="main-box">
					<!--淘宝热卖-->
					<div class="tbjd-sale">
						<div class="tbjds-sale tb-hot">
							<div class="list-title">
								<span>淘宝热卖</span>
								<i class="times">${tHotSell[0].time_period}</i>
							</div>
							<div class="sale-table">
								<table class="table table-condensed" style="width:100%;height:100%">
									<thead>
										<tr>
											<th style="width:330px">产品名称</th>
											<th style="width:80px">价格走势</th>
											<th style="width:80px">销量走势</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${not empty tHotSell}">
												<c:forEach items="${tHotSell}" var="good" varStatus="vs">
													<tr>
														<td class="name-td" title="${good.goods_name_new}"><a target="_blank" onclick="monitor(9)" href="<%=basePath%>detailPage/goGoodsdetail?goodsID=${good.goods_id}">${good.goods_name_new}</a></td>
														<!-- 趋势标记${good.price_trend_sign}-->
														<td class="text-left"><span class="glyphicon <c:if test="${good.price_trend_sign == '1'}">glyphicon-arrow-up</c:if><c:if test="${good.price_trend_sign == '2'}">glyphicon-arrow-down</c:if>"></span>${good.price_trend_index}%</td>
														<!--sale_trend_sign-->
														<td class="text-left"><span class="glyphicon <c:if test="${good.sale_trend_sign == '1'}">glyphicon-arrow-up</c:if><c:if test="${good.sale_trend_sign == '2'}">glyphicon-arrow-down</c:if>"></span>${good.sale_trend_index}%</td>
														<td>
															<a class="see" target="_blank" onclick="monitor(9)" href="<%=basePath%>detailPage/goGoodsdetail?goodsID=${good.goods_id}">查看</a>															
														</td>
													</tr>
												</c:forEach>
											</c:when>
										</c:choose>
									</tbody>
								</table>
							</div>
						</div>
						<div class="tbjds-sale jd-hot">
							<div class="list-title">
								<span>京东热卖</span>
								<i class="times">${jHotSell[0].time_period}</i>
							</div>
							<div class="sale-table">
								<table class="table table-condensed" style="width:100%;height:100%">
									<thead>
										<tr>
											<th style="width:330px">产品名称</th>
											<th style="width:80px">价格走势</th>
											<th style="width:80px">销量走势</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${not empty jHotSell}">
												<c:forEach items="${jHotSell}" var="good" varStatus="vs">
													<tr>
														<td class="name-td" title="${good.goods_name_new}"><a onclick="monitor(10)" target="_blank" href="<%=basePath%>detailPage/goGoodsdetail?goodsID=${good.goods_id}">${good.goods_name_new}</a></td>
														<!-- 趋势标记${good.price_trend_sign}-->
														<td class="text-left"><span class="glyphicon <c:if test="${good.price_trend_sign == '1'}">glyphicon-arrow-up</c:if><c:if test="${good.price_trend_sign == '2'}">glyphicon-arrow-down</c:if>"></span>${good.price_trend_index}%</td>
														<!--sale_trend_sign-->
														<td class="text-left"><span class="glyphicon <c:if test="${good.sale_trend_sign == '1'}">glyphicon-arrow-up</c:if><c:if test="${good.sale_trend_sign == '2'}">glyphicon-arrow-down</c:if>"></span>${good.sale_trend_index}%</td>
														<td>
															<a class="see" target="_blank" onclick="monitor(10)" href="<%=basePath%>detailPage/goGoodsdetail?goodsID=${good.goods_id}">查看</a>
														</td>
													</tr>
												</c:forEach>
											</c:when>
										</c:choose>								
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</section>
			<!--专题活动-->
			<section class="thematic-activities">
				<div class="main-box">
					<div class="thematic-activities-list">
						<c:choose>
							<c:when test="${not empty homeSpecial}">
								<c:forEach items="${homeSpecial}" var="good" varStatus="vs">
									<div class="activities-list">
										<div class="activities-list-box">
											<a target="_blank" onclick="monitor(${vs.index+'11'})" href="<%=basePath%>specialPage/getSpecialGoods?specialID=${good.special_id}">
												<img src="static/assets/img/blank.gif"  data-echo="${good.special_map}" />
											</a>
										</div>
									</div>
								</c:forEach>
							</c:when>
						</c:choose>
					</div>
				</div>
			</section>
			<!--本站热卖-->
			<section class="hot-sale">
				<div class="main-box">
					<!--本站热卖-->
					<div class="website-sale" id="website-list">
						<div class="list-title">
							<span>本站热卖</span>
						</div>
						
					</div>
				</div>
			</section>
			<!--热门品牌-->
			<section>
				<div class="main-box">
					<div class="hot-brand">
						<div class="list-title">
							<span>热门品牌</span>
							<a href="javascript:;" id="hot-brand-change">换一批&emsp;&emsp;</a>
						</div>
						<div class="brank-list" id="brank-list-box"></div>
					</div>
				</div>
			</section>
			<!--新货推荐-->
			<section>
				<div class="main-box">
					<div class="new-recommend">
						<div class="list-title">
							<span>新货推荐</span>
							<a href="javascript:;" id="new-recommend-change">换一批&emsp;&emsp;</a>
						</div>
						<div class="recommend-list" id="new-recommend-box"></div>
					</div>
				</div>
			</section>
			<!--保税仓&德国直邮-->
			<section>
				<div class="main-box">
					<div class="warehouse-directMail">
						<!--保税仓热卖-->
						<div class="warehouse">
							<div class="list-title">
								<span>保税仓热卖</span>
								<a href="javascript:;" id="seeMoreWarehouse" onclick="monitor(18)">查看更多 >>&emsp;&emsp;</a>
							</div>
							<div class="warehouse-list"></div>
						</div>
						<!--海外直邮热卖-->
						<div class="directMail">
							<div class="list-title">
								<span>海外直邮热卖</span>
								<a href="javascript:;" id="seeMoreDirectMail" onclick="monitor(19)">查看更多 >>&emsp;&emsp;</a>
							</div>
							<div class="directMail-list"></div>
						</div>
					</div>
				</div>
			</section>
			<i id="bs-sections" style="width:32px;height:32px;margin:10px auto;background: url(static/assets/img/loading-index.gif);"></i>
			
			<!--本站特色-->
			<section class="website-features">
				<div class="main-box">
					<div class="website-features-t">
						<span>网站特色</span>
						<a onclick="monitor(30)" target="_blank" href="<%=basePath%>registerLogin/goRegister">现在就注册</a>
					</div>
					<div class="website-features-b">
						<div class="website-features-list">
							<h4>定金</h4>
							<p>本站有特色的定金模式可以为您更多的流水资金</p>
						</div>
						<div class="website-features-list">
							<h4>询价</h4>
							<p>对您搜不到的商品，我们提供专业定制化采购服务，提供优质货源</p>
						</div>
						<div class="website-features-list">
							<h4>数据</h4>
							<p>专业的行业数据，可助您成功把握商机</p>
						</div>
						<div class="website-features-list">
							<h4>微仓</h4>
							<p>微仓可以免费替您管理仓储物流，随时发货，为您解决仓储物流之忧</p>
						</div>
						<div class="website-features-list">
							<h4>低价</h4>
							<p>专业批发/一件代发，为您提供最优的价格</p>
						</div>
						<div class="website-features-list">
							<h4>正品</h4>
							<p>产品货源地直采，确保每一件商品都是正品</p>
						</div>
					</div>
				</div>
			</section>
		</div>
		<!--右侧固定-->
		<div class="rightBarNew <c:if test="${customerID == null}">rightBarNew_index</c:if><c:if test="${customerID != null}">rightBarNew_index02</c:if>">
			<ul>
				<li>
					<a target="_blank" href="<%=basePath%>footer/contactUs">
						<!--<img src="../static/assets/img/Customer.gif" />-->
						<i></i>
						<b>客服</b>
					</a>
					<div class="more-info">
						<div class="info-box">
							<h3>电话客服</h3>
							<h5>0571-87916639</h5>
							<h3>QQ在线客服</h3>
							<h5><a class="qq-icon" target="_blank" title="点击这里给小北发消息" href="http://wpa.qq.com/msgrd?v=3&uin=2303550195&site=qq&menu=yes">2303550195</a></h5>
							<h3>微信客服</h3>
							<a class="wechat" href="javascript:;"></a>
							<b class="b-triangle"></b>
						</div>
					</div>
				</li>
				<li>
					<a href="javascript:;" id="goCart" onclick="go_cart(1)">
						<!--<img src="../static/assets/img/ShoppingCar.gif" />-->
						<i></i>
						<b>购物车</b>
					</a>
				</li>
				<li>
					<a href="javascript:;" onclick="go_inquiry(1)">
						<!--<img src="../static/assets/img/Sourcing.gif" />-->
						<i></i>
						<b>找货源</b>
					</a>
					<span class="li-h" onclick="go_inquiry(1)">点击询价</span>
				</li>
			</ul>
			<a href="javascript:;" id="toTop">
				<i></i>
				<b>TOP</b>
				<span>返回顶部</span>
			</a>
		</div>
		<!--footer-->
		<div id="footer">
			<div class="index-footer">
				<div class="footer-logo">
					<a href="<%=basePath%>"><img src="static/assets/img/logo.gif" /></a>
					<span class="Customer-service">客服电话：0571-8791&nbsp;6639</span>
				</div>
				<div class="footer-list">
					<ul>
						<li>
							<h5>交易指南</h5>
							<a onclick="monitor(30)" href="<%=basePath%>footer/depositMode" target="_blank">定金模式</a>
							<a onclick="monitor(30)" href="<%=basePath%>footer/inquiry" target="_blank">询价功能</a>
							<a onclick="monitor(30)" href="<%=basePath%>footer/procurementContract" target="_blank">采购合同</a>
						</li>
						<li>
							<h5>支付与物流</h5>
							<a onclick="monitor(30)" href="<%=basePath%>footer/microWarehouse" target="_blank">微仓服务</a>
							<a onclick="monitor(30)" href="<%=basePath%>footer/crossBorder" target="_blank">跨境物流服务</a>
						</li>
						<li>
							<h5>会员管理</h5>
							<a onclick="monitor(30)" href="<%=basePath%>footer/aboutUs" target="_blank">登陆注册</a>
						</li>
						<li>
							<h5>售后服务</h5>
							<a onclick="monitor(30)" href="<%=basePath%>footer/noticeOfReturn" target="_blank">退货须知</a>
							<a onclick="monitor(30)" href="<%=basePath%>footer/legalService" target="_blank">服务条款</a>
							<a onclick="monitor(30)" href="<%=basePath%>footer/userAgreement" target="_blank">用户协议</a>
						</li>
						<li>
							<h5>关于我们</h5>
							<a onclick="monitor(30)" href="<%=basePath%>footer/aboutUs" target="_blank">关于北极光</a>
							<a onclick="monitor(30)" href="<%=basePath%>footer/contactUs" target="_blank">联系我们</a>
						</li>
					</ul>
				</div>
				<div class="wechat">
					<span>微信服务号</span>
					<img src="static/assets/img/WechatIMG12.jpg" />
				</div>
				<div class="Record-number">
					CopyRight © 2017 杭州络驿电子商务有限公司 aurorascm.com 浙ICP备17030413号-2 All Rights Reserved
				</div>
			</div>
		</div>
		
		<script>
			//懒加载
			Echo.init({
				offset:100,
				throttle:0
			});
			var basePath = '<%=basePath%>';
			var video_time = null;
			function dragable(id) {
				var d = document,
					o = d.getElementById(id),
					s = o.style,
					o_flag = 0,
					x, y, p = 'onmousemove';
				o.onmousedown = function(e) {
					if(!$('#drag-box').hasClass('suspension')){
						return 
					}
					e = e || event;
					x = e.clientX - o.offsetLeft;
					y = e.clientY - o.offsetTop;
					o_flag = 0;
					d[p] = function(e) {
						e = e || event;
						s.left = e.clientX - x + 'px';
						s.top = e.clientY - y + 'px';
						o_flag = 1;
					};
					d.onmouseup = function() { 
						d[p] = null;
						//拖拽与点击事件分离
						if(o_flag === 1){
							setTimeout(function(){
								clearTimeout(video_time)
							},200)
						}
					}
				}
			}
			dragable("drag-box");
		</script>
		<script src="static/assets/js/homePage.js"></script>
	</body>

</html>