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
		<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!--<metahttp-equiv="Content-Security-Policy"content="upgrade-insecure-requests"/>-->
		<title>北极光供应链-专注正品 货源国本土采购 跨境供应链领航家！</title>
		<link rel="icon" href="static/assets/img/logo_tit.png" type="image/x-icon"/>
		<meta name="keywords" content="北极光供应链，北极光，跨境，跨境电商，进口，跨境进口，保税进口，进口保税，进口货源，保税货源，海外直邮，保税区发货，一件代发，一键代发，进口分销，货源分销，海外供应链，欧洲货源，电器进口，跨境集采，跨境批发，海外批发" />
		<meta name="description" content="北极光供应链，中国进口跨境供应链领航者。坚持货源国本土采购，100%正品保证，海外授权一手货源，自营海外仓，全程供应链路可追溯。主营类目美妆护肤，母婴保健，家清日化，厨具家电。提供海外仓自提，海外直邮，保税仓代发，国内现货，以及海陆空立体运输方案。万千平台与客户的首选！"/>
		<link rel="stylesheet" href="static/assets/plugin/bootstrap-3.3.7/css/bootstrap.min.css">
		<!--<link href="//cdn.bootcss.com/bootstrap/4.0.0-alpha.6/css/bootstrap-grid.min.css" rel="stylesheet" />-->
		<!--<link rel="stylesheet" type="text/css" href="static/assets/css/animate.min.css" />-->
		<link rel="stylesheet" type="text/css" href="static/assets/plugin/swiper/dist/css/swiper.min.css" />
		<c:set var="base_url" value="<%=basePath%>"></c:set>
		<c:if test="${base_url != 'http://localhost:8080/aurorascm/'}">
			<link rel="stylesheet" type="text/css" href="static/assets/css/admin.css" />
		</c:if>
		<c:if test="${base_url == 'http://localhost:8080/aurorascm/'}">
			<link rel="stylesheet/less" type="text/css" href="static/assets/css/admin.less" />
			<script src="static/assets/js/less.js" language="javascript"></script>
		</c:if>
		<script src="static/assets/js/common.js" language="javascript"></script>
		<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
		<script src="static/assets/plugin/echo/echo.min.js"></script>
		<script src="static/assets/plugin/layer/layer.js"></script>
		<script src="static/assets/plugin/bootstrap-3.3.7/js/bootstrap.min.js"></script>
		<script src="static/assets/plugin/swiper/dist/js/swiper.min.js"></script>
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
				var word_str = '' 
				if($('#all-goods option:selected').val() == ''){
					word_str = $.trim($('#keyword').val())
				}else{
					word_str = $('#all-goods option:selected').text()+'+'+$.trim($('#keyword').val())
				}
				
				if($('#keyword').val() == '' && $('#all-goods option:selected').val() == ''){
					return console.log('空的')
				}
				window.open("<%=basePath%>searchPage/search?keyword="+word_str)
				return console.log(word_str)
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
		<% application.getAttribute("homeCategory");%>
		<script>
//			homeCategory = ${homeCategory}
//			hotSell = ${homeHotSell}
//			console.log(hotSell)
//			console.log(homeCategory)
//			console.log(111,${customerCartNum})
		</script>
		<input id="customerID" type="hidden" value="${customerID}" />
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
				console.log(${homeCategory})
			</script>
		</div>
		</c:if>
		<div id="header">
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
										url:'registerLogin/logout',
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
					<b onclick="go_customer_center(1)">个人中心</b>
				</div>
			</div>
			<div class="header-search">
				<div class="search-box">
					<a class="logo" href="<%=basePath%>"></a>
					<div class="ipt-box">
						<div class="ipt-left">
							<form method="get" action="searchPage/search" id="searchFrom" name="searchFrom" class="form-inline" target="_blank">
								<select id="all-goods" name="searchCategory">
									<option value="">所有商品</option>
									<c:forEach items="${homeCategory}" var="Cate" varStatus="vs">
										<option value="${Cate.categoryName}">${Cate.categoryName}</option>
									</c:forEach>
								</select>
								<i class="boundary"></i>
								<input name="keyword" id="keyword" placeholder="输入产品名称、品牌，或询价"/>
								<input type="button" class="search-btn toSearchBtn" value="搜索" onclick="search();" />
							</form>	
						</div>
						<a href="javascript:;" class="toSearchSupply" onclick="go_inquiry()">发布询价</a>
						<a href="javascript:;" class="my-cart" onclick="go_cart(1)">
							我的购物车
							<i>${customerCartNum != null ? customerCartNum : '0'}</i>
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
						<div class="cate-box">
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
					<a class="left" href="javascript:;" onclick="go_inquiry(1)">批发询价</a>
					<a class="left" href="javascript:;" onclick="want_delivery()">我要供货</a>
					<a class="center" href="javascript:;">|</a>
					<a target="_blank" href="<%=basePath%>searchPage/search?shipType=1">保税代发</a>
					<a target="_blank" href="<%=basePath%>searchPage/search?shipType=2">海外直邮</a>
					<a target="_blank" href="<%=basePath%>searchPage/search?shipType=3">一般贸易</a>
				</nav>
			</div>
		</div>
		<div class="layer-delivery" id="layer-delivery"><!--全站供货-->
			<h1>我要供货<i onclick="quit_want_delivery()"></i></h1>
			<div class="delivery-box">
				<h2>基本信息</h2>
				<div class="info-box">
					<label><i>* </i>公司名称：</label>
					<input class="info" placeholder="请输入公司名称"/>
				</div>
				<div class="info-box">
					<label><i>* </i>您的姓名：</label>
					<input class="info" placeholder="请使用真实的姓名"/>
				</div>
				<div class="info-box">
					<label><i>* </i>联系电话：</label>
					<input class="info" placeholder="请留下一个联系方式"/>
				</div>
				<h2>供货商品</h2>
				<div class="info-box">
					<label><i>* </i>联系时间：</label>
					<select class="info">
						<option value="">请选择您方便的时间，我们会第一时间与您沟通</option>
						<option value="08:00~12:00">08:00~12:00</option>
						<option value="14:00~18:00">14:00~18:00</option>
						<option value="18:00~22:00">18:00~22:00</option>
					</select>
				</div>
				<!--//1.品牌方;2.总代理;3一级代理;4.普通链路-->
				<div class="info-box">
					<label><i>* </i>品牌授权：</label>
					<select class="info">
						<option value="">请选择授权方式</option>
						<option value="1">品牌方</option>
						<option value="2">总代理</option>
						<option value="3">一级代理</option>
						<option value="4">普通链路</option>
					</select>
				</div>
				<div class="info-box">
					<label><i>* </i>优势商品：</label>
					<input class="info" placeholder="请输入您的优势品牌"/>
				</div>
				<i class="btn-sub" onclick="sub_want_delivery()">提交供货</i>
				<i class="btn-sub quit-sub" onclick="quit_want_delivery()">取消</i>
			</div>
		</div>
		
		<script>
			flag_want_delivery = true
			function want_delivery(){//我要供货
				want_delivery_index = layer.open({
					type: 1,
					title: false,
					scrollbar: false,
					skin: 'layer-box-shadow',
					area: '750px',
		//			skin: 'layui-layer-nobg', //没有背景色
					content: $('#layer-delivery')
				});
			}
			function quit_want_delivery(){
				layer.close(want_delivery_index)
			}
			function sub_want_delivery(){
				if(!flag_want_delivery){
					return 
				}
				//supplyIntentionJson :{String convenientTime, String companyName, Integer chainPath, String contacts, String phone, String advantageBrand}
				var obj = {},arr_source = ['companyName','contacts','phone','convenientTime','chainPath','advantageBrand'];
				for(var i = 0;i < arr_source.length;i++){
					if($('#layer-delivery .info').eq(i).val() == ''){
						var info_str = $('#layer-delivery .info').eq(i).siblings('label').text()
							info_str = info_str.replace('* ','')
							info_str = info_str.replace('：','')
						return layer.msg('请完善'+info_str+'信息')
					}
					obj[arr_source[i]] = $('#layer-delivery .info').eq(i).val();
				}
				console.log(obj)
				supplyIntentionJson = JSON.stringify(obj)
				flag_want_delivery = false;
				$.ajax({
				    type:'post',
				    url:'supplyIntention/addSupplyIntention',
				    data:{
				    	supplyIntentionJson:supplyIntentionJson
				    },
				    dataType:'json',
				    success:function(data){
				    	flag_want_delivery = true;
				    	console.log(data)
				        if(data.state == 'success'){
				        	layer.close(want_delivery_index)
				            layer.msg('提交成功')
				        }else if(data.state == 'error'){
				            layer.msg(data.msg)
				        }else if(data.state == 'failed'){
				            layer.msg(data.msg)
				        }
				    },
				    error:function(){
				    	flag_want_delivery = true;
				    }
				});
				console.log(obj)
			}
		</script>
		<section id="banner-hot">
		<div id="banner">
				<div class="swiper-container" id="swiper-container-banner">
					<div class="swiper-wrapper">
						<c:forEach items="${homeBanner}" var="banner" begin="0" end="2">
							<div class="swiper-slide">
								<a target="_blank" style="background-image:url(${banner.specialMap});" href="<%=basePath%>${banner.url}">
									<!--<img src=""/>-->
								</a>	
							</div>
						</c:forEach>
					</div>
					<!-- Add Pagination -->
				    <div class="swiper-pagination  banner-pagination"></div>
				    <!-- Add Arrows -->
				    <div class="swiper-button-next banner-next"></div>
				    <div class="swiper-button-prev banner-prev"></div>
				</div>
				<script>
					$(document).ready(function () {
						swiper_banner = new Swiper('#swiper-container-banner', {
							effect:'fade',
							slidesPerView: 1,
					      	spaceBetween: 30,
						    loop: true,
						    observer:true,//修改swiper自己或子元素时，自动初始化swiper
							observeParents:true,//修改swiper的父元素时，自动初始化swiper
						    autoplay: {
						        delay: 6000,
						        disableOnInteraction: true,
						    },
						    pagination: {
						        el: '.banner-pagination',
						        clickable: true,
						    },
						    navigation: {
						        nextEl: '.banner-next',
						        prevEl: '.banner-prev',
						    },
						});
						swiper_banner.el.onmouseenter = function(){
						  	swiper_banner.autoplay.stop();
						}
						swiper_banner.el.onmouseleave = function(){
						  	swiper_banner.autoplay.start();
						}
					})
				</script>
			</div>
			<div id="hot-sell">
				<h1 class="title-hotSell"><i></i><em>热卖商品</em></h1>
				<ul class="goods-cate">
					<c:forEach items="${homeHotSell}" var="hot" varStatus="vs" begin="0" end="2">
						<li <c:if test="${vs.index == '0'}">class="active"</c:if>>${hot.title_name}</li>
					</c:forEach>
				</ul>
				<ul class="cate-box">
					<c:forEach items="${homeHotSell}" var="hot" varStatus="vs">
						<li <c:if test="${vs.index == '0'}">class="active"</c:if>>
							<div id="banner-hotsell-${vs.index}" class="swiper-container banner-hotsell">
								<div class="swiper-wrapper">
									<c:forEach items="${hot.goods}" var="list" begin="0" end="2" step="1" varStatus="vs02">
										<div class="swiper-slide" >
											<c:forEach items="${hot.goods}" var="good" begin="0" end="4" step="1" varStatus="vs03">
												<div>
													<a target="_blank" href="<%=basePath%>detailPage/goGoodsdetail?goodsID=${hot.goods[vs02.index*5+vs03.index].goods_id}">
														<img src="${hot.goods[vs02.index*5+vs03.index].goods_show_map}"/>
													</a>
													<div class="goods-info">
														<a target="_blank" href="<%=basePath%>detailPage/goGoodsdetail?goodsID=${good.goods_id}">${hot.goods[vs02.index*5+vs03.index].goods_show_name}</a>
														<i>月销量${hot.goods[vs02.index*5+vs03.index].sales}</i>
														<b>￥${hot.goods[vs02.index*5+vs03.index].goods_price2}</b>
													</div>
												</div>
											</c:forEach>	
										</div>
									</c:forEach>
								</div>
								  <!-- Add Pagination -->
    							<div class="swiper-pagination swiper-page-${vs.index}"></div>
    							<script>
    								$(document).ready(function () {
	    								swiper_hotsell_${vs.index} = new Swiper('#banner-hotsell-${vs.index}', {
											spaceBetween: 30,
										    centeredSlides: true,
										    loop:true,
										    observer:true,//修改swiper自己或子元素时，自动初始化swiper
	  										observeParents:true,//修改swiper的父元素时，自动初始化swiper
										    autoplay: {
										        delay: 5000,
										        disableOnInteraction: true,
										    },
										    pagination: {
										        el: '.swiper-page-${vs.index}',
										        clickable: true,
										    },
										});
										swiper_hotsell_${vs.index}.el.onmouseenter = function(){
										  	swiper_hotsell_${vs.index}.autoplay.stop();
										}
										swiper_hotsell_${vs.index}.el.onmouseleave = function(){
										  	swiper_hotsell_${vs.index}.autoplay.start();
										}
									})
    							</script>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</section>
		<!--大货集散地-->
		<section>
			<div class="main-section large-cargo flag-floor" name="floor-0">
				<div class="section-head title-large-cargo" >
					<img src="static/assets/img/title-large-cargo.png"/>
					<i>Large cargo center</i>
					<ul class="name-box" id="LargeCargo">
						<c:forEach items="${homeLargeCargo}" var="list" varStatus="vs">			
							<li <c:if test="${vs.index == '0'}">class="active"</c:if>>${list.category_name}</li>
						</c:forEach>
					</ul>
				</div>
				<div class="table-box">
					<c:forEach items="${homeLargeCargo}" var="list" varStatus="vs">
						<table class="table${vs.index == '0' ? ' active' : ''}">
						  	<thead>
							    <tr>
							      	<th>商品名称</th>
							      	<th>提货价</th>
							      	<th>起订量</th>
							      	<th>规格</th>
							      	<th>有效期</th>
							      	<th>供应商</th>
							      	<th>操作</th>
							    </tr>
						  	</thead>
						 	<tbody>
						 		<c:forEach items="${list.goods}" var="good" varStatus="vs">
								    <tr>
								      	<td class="goodsName${good.hot == '1' ? ' hot' : ''}"><a title="${good.goods_show_name}">${good.goods_show_name}</a></td>
								      	<td>￥${good.price}</td>
								      	<td>${good.min_num}</td>
								      	<td>${good.norm}</td>
								      	<td>${good.period}</td>
								      	<td>${good.supplier}</td>
								      	<td class="handle">
								      		<a target="_blank" href="<%=basePath%>customerInquiry/goAddInquiryPage?goodsID=${good.goods_id}">询价</a>
								      	</td>
								    </tr>
							    </c:forEach>
						  	</tbody>
						</table>
					</c:forEach>
				</div>
			</div>
			<div id="floor-list">
				<ul>
					<li class="floor_num"><i>大货集散</i></li>
					<li class="floor_num"><i>限时抢购</i></li>
					<li class="floor_num"><i>海外直邮</i></li>
					<li class="floor_num"><i>保税热卖</i></li>
					<li class="floor_num"><i>母婴儿童</i></li>
					<li class="floor_num"><i>营养保健</i></li>
					<li class="floor_num"><i>美容护肤</i></li>
					<li class="floor_num"><i>数码家电</i></li>
				</ul>
				<img src="static/assets/img/WechatIMG12.png"/>
				<b>官方微信</b>
			</div>
		</section>
		<!--限时抢购-->
		<section>
			<div class="main-section flag-floor" name="floor-1" id="timed-activity">
				<div class="section-head title-time-cativity">
					<img src="static/assets/img/title-timed-activity.png"/>
					<i>Flash sales</i>
				</div>
				<ul class="activity-list">
					<li class="banner">
						<b>抢购进行中</b>
						<i>距本场结束还剩</i>
						<div class="time-box">
							<i></i>
							<i></i>
							<i></i>
						</div>
						<a class="sell-more">查看更多></a>
					</li>
					<c:forEach items="${homeTimedActivity[0].timedGoodsList}" var="good">
						<li class="goodsBox">
							<a class="goods-map" title="${good.goodsNewName}" target="_blank" href="<%=basePath%>detailPage/goGoodsdetail?goodsID=${good.goodsID}">
								<img src="${good.homeMap}"/>
							</a>
							<c:if test="${homeTimedActivity[0].activityState == '3'}">
								<!--活动到期-->
								<a class="mask-timeEnd" title="${good.goodsNewName}" target="_blank" href="<%=basePath%>detailPage/goGoodsdetail?goodsID=${good.goodsID}"></a>
							</c:if>
							<c:if test="${good.activityGoodsState == '2' && homeTimedActivity[0].activityState != '3'}">
								<!--已抢光-->
								<a class="mask-lootAll" title="${good.goodsNewName}" target="_blank" href="<%=basePath%>detailPage/goGoodsdetail?goodsID=${good.goodsID}"></a>
							</c:if>
							<a class="goods-name" title="${good.goodsNewName}" target="_blank" href="<%=basePath%>detailPage/goGoodsdetail?goodsID=${good.goodsID}">${good.goodsNewName}</a>
							<i class="rest">剩余商品${good.availableSellNum - good.soldNum}件</i>
							<div class="progress">
								<i class="active" style="width:${good.soldNum/good.availableSellNum*100}%;"></i>
							</div>
							<div class="price-buy">
								<i>￥${good.originalPrice2}</i>
								<b>￥${good.discountPrice}</b>
								<c:if test="${good.activityGoodsState != '2' && homeTimedActivity[0].activityState != '3'}">
									<a target="_blank" href="<%=basePath%>detailPage/goGoodsdetail?goodsID=${good.goodsID}"></a>
								</c:if>
								<c:if test="${good.activityGoodsState == '2'}">
									<a class="dark" href="javascript:;">已抢光</a>
								</c:if>
								<c:if test="${homeTimedActivity[0].activityState == '3'}">
									<a class="dark" href="javascript:;">活动结束</a>
								</c:if>
							</div>
							<div class="end-time-show">
								剩余时间：<i></i>天<i></i>小时<i></i>分<i></i>秒
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
			<script>
				function timed_activity(){
					var end_times = '${homeTimedActivity[0].endTime}'
//						end_times = end_times.replace('-','/')
					var endtime = new Date(end_times);
	            	var nowtime = new Date();
	            	var seconds = parseInt((endtime.getTime() - nowtime.getTime()) / 1000);
	            	if(seconds <= 0){
	            		$('.time-box i').eq(0).text('00');
			            $('.time-box i').eq(1).text('00');
			            $('.time-box i').eq(2).text('00');
			            for(var i = 0;i < $('.end-time-show').length;i++){
			            	$('.end-time-show').eq(i).find('i').eq(0).text('0');
			            	$('.end-time-show').eq(i).find('i').eq(1).text('00');
			            	$('.end-time-show').eq(i).find('i').eq(2).text('00');
			            	$('.end-time-show').eq(i).find('i').eq(3).text('00');
			            }
	            		return clearInterval(fresh)
	            	}
//	            	console.log(seconds)
	            	d = parseInt(seconds / 3600 / 24);
	            	h = parseInt((seconds / 3600) % 24)
		            h_day_no = parseInt(seconds / 3600);
		            m = parseInt((seconds / 60) % 60);
		            s = parseInt(seconds % 60);
		            //格式筛选
		            h = parseInt(h / 10) == 0 ? '0'+h : h;
		            h_day_no = parseInt(h_day_no / 10) == 0 ? '0'+h_day_no : h_day_no;
		            m = parseInt(m / 10) == 0 ? '0'+m : m;
		            s = parseInt(s / 10) == 0 ? '0'+s : s;
		            $('.time-box i').eq(0).text(h_day_no);
		            $('.time-box i').eq(1).text(m);
		            $('.time-box i').eq(2).text(s);
		            for(var i = 0;i < $('.end-time-show').length;i++){
		            	$('.end-time-show').eq(i).find('i').eq(0).text(d);
		            	$('.end-time-show').eq(i).find('i').eq(1).text(h);
		            	$('.end-time-show').eq(i).find('i').eq(2).text(m);
		            	$('.end-time-show').eq(i).find('i').eq(3).text(s);
		            }
				}
				var fresh;
        		fresh = setInterval(timed_activity, 1000);
			</script>
		</section>
		<section >
			<div class="main-between flag-floor" name="floor-2" id="direct-countries">
				<!--海外直邮-->
				<div class="section-left">
					<div class="section-head title-direct-post">
						<img src="static/assets/img/title-direct-post.png"/>
						<i>Overseas direct delivery service</i>
						<a target="_blank" href="<%=basePath%>searchPage/search?shipType=2" class="see-more">更多</a>	
					</div>
					<ul class="title-box">
						<li v-for="(list,index) in direct" :class="{'active' : left==index}" @mouseover="left=index">
							{{list.title_name}}
						</li>
					</ul>
					<div class="good-list" v-for="(list,index) in direct" v-show="left==index" >
						<div class="banner">
							<img  :src="list.banner"/>
							<!--{{list.bannerAndKeywords.banner}}-->
							<div class="info-box">
								<h2>{{direct[index].title_name}}</h2>
								<a class="see-more" target="_blank" :href="'<%=basePath%>searchPage/search?keyword='+direct[index].title_name">查看更多></a>
								<a class="words" v-for="word in direct[index].keywords.split(',')" target="_blank" :href="'<%=basePath%>searchPage/search?keyword='+word">{{word}}</a>
							</div>
						</div>
						<div class="goods-box" v-for="good in list.goods">
							<a class="goods-map" target="_blank" :title="good.goods_show_name" :href="'<%=basePath%>detailPage/goGoodsdetail?goodsID='+good.goods_id">
								<img :src="good.goods_show_map" />
							</a>
							<a class="goods-name" target="_blank" :title="good.goods_show_name" :href="'<%=basePath%>detailPage/goGoodsdetail?goodsID='+good.goods_id">
								{{good.goods_show_name}}
							</a>
							<i>
								<span>￥</span>
								<strong>{{good.goods_price2}}</strong>
								<a target="_blank" :href="'<%=basePath%>detailPage/goGoodsdetail?goodsID='+good.goods_id">查看详情</a>
							</i>
						</div>
					</div>
				</div>
				<!--各国好货-->
				<div class="section-right">
					<div class="section-head title-countries-goods">
						<img src="static/assets/img/title-countries-goods.png"/>
						<i>Good products of all countries</i>
						<!--<a target="_blank" @click="see_more(countries[right].title_name)" class="see-more">更多</a>-->	
					</div>
					<ul class="title-box">
						<li v-for="(list,index) in countries" :class="{'active-r' : right==index}" @mouseover="right=index">
							{{list.title_name}}
						</li>
					</ul>
					<div class="good-list" v-for="(list,index) in countries" v-show="right==index">
						<div class="banner">
							<img :src="list.banner"/>
						</div>
						<div class="goods-box" v-for="good in list.goods">
							<a class="goods-map" target="_blank" :title="good.goods_show_name" :href="'<%=basePath%>detailPage/goGoodsdetail?goodsID='+good.goods_id">
								<img :src="good.goods_show_map" />
							</a>
							<a class="goods-name" target="_blank" :title="good.goods_show_name" :href="'<%=basePath%>detailPage/goGoodsdetail?goodsID='+good.goods_id">
								{{good.goods_show_name}}
							</a>
							<i>
								<span>￥</span>
								<strong>{{good.goods_price2}}</strong>
								<a target="_blank" :href="'<%=basePath%>detailPage/goGoodsdetail?goodsID='+good.goods_id">查看详情</a>
							</i>
						</div>
					</div>
				</div>
			</div>
		</section>
		<!--热门品牌-->
		<section>
			<div class="main-section" id="hotBrand">
				<div class="section-head title-hot-brand">
					<img src="static/assets/img/title-hot-brand.png"/>
					<i>Popular brands</i>
					<a href="javascript:;" class="change-batch" @click="getAjax()">换一批</a>
				</div>
				<div class="brand-box">
					<ul class="brand-list">
						<li class="brand-active" v-for="(brand,index) in brandList" v-if="index == 0">
							<a target="_blank" :href="'<%=basePath%>searchPage/searchBrand?brandID='+brand.brand_id" onclick="monitor(16)">
								<img :src="brandList[0].brand_show_map"/>
							</a>
						</li>
						<li v-for="(brand,index) in brandList" v-if="index > 0">
							<div class="brank-list-hover">
								<b class="attention" v-if="!brand.ate" @click="attentionBrand(brand.brand_id,index)" onclick="monitor(16)">+关注</b>
								<b class="attention" v-else>已关注</b>
								<h5>{{brand.care_num}}人已关注该品牌</h5>
								<a class="attention-in" target="_blank" :href="'<%=basePath%>searchPage/searchBrand?brandID='+brand.brand_id" onclick="monitor(16)">进入</a>
							</div>
							<a href="">
								<img :src="brand.brand_show_map"/>
							</a>
						</li>
					</ul>
				</div>
			</div>
		</section>
		<!--保税仓热卖-->
		<section>
			<div class="main-section flag-floor" name="floor-3" id="bonded">
				<div class="section-head title-bonded">
					<img src="static/assets/img/title-bonded.png"/>
					<i>Hot Sale in Bonded warehouse</i>
					<a target="_blank" href="<%=basePath%>searchPage/search?shipType=1" class="see-more">更多</a>	
					<ul class="keyWord-box">
						<li v-for="(val,index) in keyword">
							<a target="_blank" :href="'<%=basePath%>searchPage/search?keyword='+val">{{val}}</a>
							<i v-if="index != 0"></i>
						</li>
					</ul>
				</div>
				<ul class="bonded-list">
					<li class="banner">
						<div class="swiper-container">
							<div class="swiper-wrapper">
								<div class="swiper-slide" v-for="banner in bannerList">
									<a target="_blank" :href="'<%=basePath%>'+banner.url">
										<img :src="banner.specialMap"/>
									</a>	
								</div>
							</div>
							<!-- Add Pagination -->
						    <div class="swiper-pagination  bonded-pagination"></div>
						    <!-- Add Arrows -->
						    <div class="swiper-button-next bonded-next"></div>
						    <div class="swiper-button-prev bonded-prev"></div>
						</div>
					</li>
					<li class="goods-box" v-for="good in goodList">
						<a class="goods-map" target="_blank" :alt="good.goodsNewName" :title="good.goodsNewName" :href="'<%=basePath%>detailPage/goGoodsdetail?goodsID='+good.goodsID">
							<img :src="good.homeMap" />
						</a>
						<a class="goods-name" target="_blank" :title="good.goodsNewName" :href="'<%=basePath%>detailPage/goGoodsdetail?goodsID='+good.goodsID">
							{{good.goodsNewName}}
						</a>
						<i>
							<span>￥</span>
							<strong>{{good.goodsPrice2}}</strong>
							<a target="_blank" :href="'<%=basePath%>detailPage/goGoodsdetail?goodsID='+good.goodsID">查看详情</a>
						</i>
					</li>
					<li class="bonded-active">
						<a></a>
					</li>
				</ul>
			</div>
			<script>
				$(document).ready(function(){
					swiper_bonded = new Swiper('#bonded .swiper-container', {
						spaceBetween: 30,
					    centeredSlides: true,
					    observer:true,//修改swiper自己或子元素时，自动初始化swiper
						observeParents:true,//修改swiper的父元素时，自动初始化swiper
					    loop: true,
					    autoplay: {
					        delay: 4000,
					        disableOnInteraction: true,
					    },
					    pagination: {
					        el: '.bonded-pagination',
					        clickable: true,
					    },
					      navigation: {
					        nextEl: '.bonded-next',
					        prevEl: '.bonded-prev',
					    },
					});
					swiper_bonded.el.onmouseenter = function(){
					  	swiper_bonded.autoplay.stop();
					}
					swiper_bonded.el.onmouseleave = function(){
					  	swiper_bonded.autoplay.start();
					}
				})
			</script>
		</section>
		<!--类目楼层-->
		<section id="cate-floor">
			<div class="main-section section-floor flag-floor" name="floor-4" v-if="!floorList.length"></div>
			<div class="main-section section-floor flag-floor" name="floor-5" v-if="!floorList.length"></div>
			<div class="main-section section-floor flag-floor" name="floor-6" v-if="!floorList.length"></div>
			<div class="main-section section-floor flag-floor" name="floor-7" v-if="!floorList.length"></div>
			<div class="main-section section-floor flag-floor" v-if="floorList.length" :name="'floor-'+(4+index)" v-for="(floor,index) in floorList">
				<div class="section-head" :class="'title-floor-'+floor.category1ID">
					<img :src="'static/assets/img/floor-title-'+floor.category1ID+'.png'"/>
					<i v-if="floor.category1ID == 10000">Mother Infant & Child</i>
					<i v-if="floor.category1ID == 20000">Beauty & Skin care</i>
					<i v-if="floor.category1ID == 30000">Kitchenware & Commodity</i>
					<i v-if="floor.category1ID == 40000">Foods & Health products</i>
					<i v-if="floor.category1ID == 50000">Digital appliances</i>
					<a target="_blank" :href="'<%=basePath%>searchPage/search?keyword='+floor.categoryName" class="see-more">更多</a>	
					<ul class="keyWord-box">
						<li v-for="(val,index2) in floor.keyword.split(',')" v-if="val != ''">
						<a target="_blank" :href="'<%=basePath%>searchPage/search?keyword='+val">{{val}}</a>
						<i v-if="index2 != 0"></i>
						</li>
					</ul>
				</div>
				<ul class="floor-list">
					<li class="banner">
						<div class="swiper-container" :id="'floor-'+floor.category1ID">
							<div class="swiper-wrapper">
								<div class="swiper-slide" v-for="banner in floor.specialList">
									<a target="_blank" :href="'<%=basePath%>'+banner.url">
										<img :src="banner.specialMap"/>
									</a>	
								</div>
							</div>
							<!-- Add Pagination -->
						    <div class="swiper-pagination" :class="'floor-pagination-'+floor.category1ID"></div>
						    <!-- Add Arrows -->
						    <div class="swiper-button-next" :class="'floor-next-'+floor.category1ID"></div>
						    <div class="swiper-button-prev" :class="'floor-prev-'+floor.category1ID"></div>
						</div>
					</li>
					<li class="goods-box" :class="{'goods-bottom' : index > 3}" v-for="(good,index) in floor.floorGoodsList">
						<a class="goods-map" target="_blank" :alt="good.goodsNewName" :title="good.goodsNewName" :href="'<%=basePath%>detailPage/goGoodsdetail?goodsID='+good.goodsID">
							<img :src="good.homeMap" />
						</a>
						<a class="goods-name" target="_blank" :title="good.goodsNewName" :href="'<%=basePath%>detailPage/goGoodsdetail?goodsID='+good.goodsID">
							{{good.goodsNewName}}
						</a>
						<i>
							<span>￥</span>
							<strong>{{good.goodsPrice2}}</strong>
							<a target="_blank" :href="'<%=basePath%>detailPage/goGoodsdetail?goodsID='+good.goodsID">查看详情</a>
						</i>
					</li>
				</ul>
				<div class="floor-brand">
					<a v-for="brand in floor.floorBrandList" target="_blank" :href="'<%=basePath%>searchPage/searchBrand?brandID='+brand.brandID">
						<img :src="brand.homeMap"/>
					</a>
				</div>
			</div>
		</section>
		<c:forEach	items="${homeFloorCategoryIDs}" var="cate_ID">
			<script>
				$(document).ready(function(){
					floor_${cate_ID} = new Swiper('#floor-${cate_ID}', {
						spaceBetween: 30,
					    centeredSlides: true,
//					    loop:true,
					    observer:true,//修改swiper自己或子元素时，自动初始化swiper
						observeParents:true,//修改swiper的父元素时，自动初始化swiper
					    autoplay: {
//					        delay: 1000,
					        disableOnInteraction: true,
					    },
					    pagination: {
					        el: '.floor-pagination-${cate_ID}',
					        clickable: true,
					    },
					    navigation: {
					        nextEl: '.floor-next-${cate_ID}',
					        prevEl: '.floor-prev-${cate_ID}',
					    }
					});
					floor_${cate_ID}.el.onmouseenter = function(){
					  	floor_${cate_ID}.autoplay.stop();
					}
					floor_${cate_ID}.el.onmouseleave = function(){
					  	floor_${cate_ID}.autoplay.start();
					}
				})
			</script>
		</c:forEach>
		<!--footer-->
		<div id="footer">
			<div class="website-features">
				<div>
					<h1>北极光特色</h1>
					<p>介绍性文字介绍性文字介绍性文字介绍性文字介绍性文字介绍性文字介绍性文字介绍性文字介绍性文字介绍性文字介绍性文字介绍性文字介绍性文字介绍性
文字介绍性文字介绍性文字介绍性文字介绍性文字介绍性文字介绍性文字</p>
					<img src="static/assets/img/website-features.png"/>
				</div>
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
				<div class="wechat">
					<span>官方微信</span>
					<img src="static/assets/img/WechatIMG12.png" />
				</div>
				<i class="border"></i>
			</div>
			<div class="friend-link">
				<div>
					<i>友情链接：</i>
					<a el="nofollow" target="_blank" href="https://www.tmall.com/">天猫</a>
					<a el="nofollow" target="_blank" href="https://www.jd.com/index.html">京东</a>
					<a el="nofollow" target="_blank" href="https://www.kaola.com">网易考拉海购</a>
					<a el="nofollow" target="_blank" href="http://www.globalegrow.com/">环球易购</a>
					<a el="nofollow" target="_blank" href="https://www.fengqu.com/">丰趣海淘</a>
					<a el="nofollow" target="_blank" href="http://www.xiaohongshu.com/">小红书</a>
					<a el="nofollow" target="_blank" href="https://www.vip.com/">唯品会</a>
					<a el="nofollow" target="_blank" href="https://www.mia.com/">蜜芽</a>
					<a el="nofollow" target="_blank" href="http://www.gegejia.com/">格格家</a>
					<a el="nofollow" target="_blank" href="http://www.loiigroup.com/index.html">络驿国际</a>
					<a el="nofollow" target="_blank" href="http://www.wolkensberg.de/index.html">云山咨询</a>
				</div>
			</div>
			<div class="Record-number">
				CopyRight © 2017 杭州络驿电子商务有限公司 aurorascm.com 浙ICP备17030413号-2 All Rights Reserved
			</div>
		</div>
		<!--右侧边栏-->
		<div id="fixed-right">
			<div>
				<ul id="fixed-right-box">
					<li>
						<a href="javascript:;" onclick="go_customer_center(1)"></a>
						<div><a href="javascript:;" onclick="go_customer_center(1)">个人中心</a></div>
					</li>
					<li>
						<a></a>
						<div class="service-box">
							<div class="more-info">
								<h2>在线咨询</h2>
								<div class="info-box">
									<h3 class="icon_phone">电话客服</h3>
									<h5>0571-87916639</h5>
									<h3 class="icon_qq">QQ在线客服</h3>
									<h5><a class="qq-icon" target="_blank" title="点击这里给小北发消息" href="http://wpa.qq.com/msgrd?v=3&uin=2303550195&site=qq&menu=yes">2303550195</a></h5>
									<h3 class="icon_weChat">微信客服</h3>
									<a class="wechat" href="javascript:;"></a>
									<b class="b-triangle"></b>
								</div>
							</div>
						</div>
					</li>
					<li>
						<a href="javascript:;" onclick="go_cart(1)"></a>
						<div><a href="javascript:;" onclick="go_cart(1)">购物中心</a></div>
					</li>
					<li>
						<a onclick="go_inquiry(1)"></a>
						<div><a href="javascript:;" onclick="go_inquiry(1)">寻找货源</a></div>
					</li>
				</ul>
				<a class="go-top" href="javascript:;" onclick="scroll_top()"></a>
			</div>
		</div>
		<script>
//			$('#fixed-right-box li').on('mouseover',function(){
//				$('#fixed-right-box li').children('div').removeClass('active');
//				$(this).children('div').addClass('active');
//			})
			$(window).scroll(function(){
				var sTop = $(window).scrollTop(),
					floor1 = $(".flag-floor[name='floor-0']").offset().top,
					floor2 = $(".flag-floor[name='floor-1']").offset().top,
					floor3 = $(".flag-floor[name='floor-2']").offset().top,
					floor4 = $(".flag-floor[name='floor-3']").offset().top,
					floor5 = $(".flag-floor[name='floor-4']").offset().top,
					floor6 = $(".flag-floor[name='floor-5']").offset().top,
					floor7 = $(".flag-floor[name='floor-6']").offset().top,
					floor8 = $(".flag-floor[name='floor-7']").offset().top;
				$('.floor_num').removeClass('active');
				if(sTop>90){
					$('.header-search').addClass('header-search-active');
				}else{
					$('.header-search').removeClass('header-search-active');
				}
				if(sTop>530){
					$('#floor-list').css('display','block');
				}else{
					$('#floor-list').css('display','none');
				}
				if(sTop < floor1){
//					console.log(floor1)
				}else if(sTop < floor2){
					$('.floor_num').eq(0).addClass('active')
				}else if(sTop < floor3){
					$('.floor_num').eq(1).addClass('active')
				}else if(sTop < floor4){
					$('.floor_num').eq(2).addClass('active')
				}else if(sTop < floor5){
					$('.floor_num').eq(3).addClass('active')
				}else if(sTop < floor6){
					$('.floor_num').eq(4).addClass('active')
				}else if(sTop < floor7){
					$('.floor_num').eq(5).addClass('active')
				}else if(sTop < floor8){
					$('.floor_num').eq(6).addClass('active')
				}else if(sTop < floor8+$(".flag-floor[name='floor-7']").height()){
					$('.floor_num').eq(7).addClass('active')
				}
			})
			$('.floor_num').on('click',function(){
				var floor = $(this).index();
//				console.log($(".floor").eq(floor).offset().top)
        		$("html,body").animate({scrollTop:($(".flag-floor").eq(floor).offset().top)},300);
			})
			//懒加载
			Echo.init({
				offset:100,
				throttle:0
			});
			var basePath = '<%=basePath%>';
			
		</script>
		<script src="static/assets/js/vue.js"></script>
		<script src="static/assets/js/home.js"></script>
	</body>

</html>