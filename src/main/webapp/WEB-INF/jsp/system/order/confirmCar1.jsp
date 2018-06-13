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
		<title>购物车结算</title>
		<%@ include file="../index/headLink.jsp"%>
		<style>
			.b-post {
				float: right;
				margin-right: 30px;
			}
		</style>
	</head>

	<body id="comfirm-body">

		<!--header-->

		<%@ include file="../index/topLogin.jsp"%>
		<!--carAll-->
		<!--收货人信息-->
		<div id="info-box">
			<h1>收货人信息</h1>
			<div class="info-box-l">
				<h2>选择收货人</h2>
				<div class="input-group search-box">
					<input type="text" class="form-control" id="address-keyword" placeholder="输入收货人姓名可进行搜索" />
					<span class="input-group-addon"><i id="search-address" class="glyphicon glyphicon-search"></i></span>
					<ul id="likeAddrBox"></ul>
				</div>
				<div id="address-box">
					<a href="javascript:;" id="flip" data-page="1"><i class="dis"><</i><i>></i></a>
					<ul id="addr-ul"></ul>
					<div class="address-list" id="new-address"> </div>
				</div>
				<i id="add-address">+新增收货地址</i>
			</div>
			<div class="info-box-r">
				<div class="inpt-box">
					<i>收货人：</i>
					<input readonly="readonly" id="c-name" />
				</div>
				<div class="inpt-box">
					<i>电话号码：</i>
					<input readonly="readonly" id="c-mobile" />
				</div>
				<div class="inpt-box inpt-box-02">
					<i class="left-01">省份：</i>
					<input readonly="readonly" id="c-province" />
				</div>
				<div class="inpt-box inpt-box-03">
					<i>城市：</i>
					<input readonly="readonly" id="c-city" />
				</div>
				<div class="inpt-box inpt-box-03">
					<i>地区：</i>
					<input readonly="readonly" id="c-area" />
				</div>
				<div class="inpt-box inpt-box-01">
					<i>详细地址：</i>
					<textarea readonly="readonly" id="detail-address"></textarea>
				</div>
				<div class="inpt-box inpt-box-01">
					<i>身份证号码：</i>
					<input readonly="readonly" id="id-card" />
				</div>
				<a href="javascript:;" class="delete-addr">删除</a>
				<a href="javascript:;" class="alter-addr">修改</a>
			</div>
		</div>
		<div class="carBox">
			<div id="good-category">
				<a href="javascript:;">确认商品信息</a>
			</div>
			<div class="car-head">
				<div class="col col2">商品信息</div>
				<div class="col col3">数量</div>
				<div class="col col4">单价（元）</div>
				<div class="col col5">重量（千克）</div>
				<div class="col col6">金额（元）</div>
			</div>
			<!--德国直邮-->
			<c:if test="${map.hCartFPMath.size() > 0}">
				<div id="German" class="mail">
					<i>海外直邮</i>
					<b class="b-quote"></b>
					<b class="b-post"></b>
				</div>
				<div class="goods">
					<ul class="goodsBox" id="German-box">
						<c:forEach items="${map.hCartFPMath}" var="good">
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
								<div class="col col5">${good.weight * good.buyNum}</div>
								<div class="col col6 total-list">${good.uMoney}</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>

			<!--保税仓直邮-->
			<c:if test="${map.bCartFPMath.size() > 0}">
				<div id="wareHouse" class="mail">
					<i>保税仓代发</i>
					<b class="b-quote"></b>
					<b class="b-post"></b>
				</div>
				<div class="goods">
					<ul class="goodsBox" id="wareHouse-box">
						<c:forEach items="${map.bCartFPMath}" var="good">
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
								<div class="col col5">${good.weight * good.buyNum}</div>
								<div class="col col6 total-list">${good.uMoney}</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
			<!--国内现货-->
			<c:if test="${map.gCartFPMath.size() > 0}">
				<div id="theSpot" class="mail">
					<i>一般贸易</i>
				</div>
				<div class="goods">
					<ul class="goodsBox" id="theSpot-box">
						<c:forEach items="${map.gCartFPMath}" var="good">
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
								<div class="col col5">${good.weight * good.buyNum}</div>
								<div class="col col6 total-list">${good.uMoney}</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
		</div>

		<!--提交订单-->
		<div class="payBox">
			<div class="payBox-l">
				<h6>物流须知：</h6>
				<p>1.根据国家法律相关规定，跨境电子商务零售进口商品的单次交易限值为人民币2000元，个人年度交易限值为人民币20000元。个人年度已使用额度、可用额度官方查询地址：http://ceb2pub.chinaport.gov.cn</p>
				<p>2.超过国家限额的订单无法通过海关发货，您可以分批次提交订单购买或存入北极光微仓后，在需要的时候分批发货</p>
			</div>
			<div class="payBox-r">
				<div class="list">
					<span>商品价格总计：</span>
					<i id="payment">￥${map.tMoney}</i>
				</div>
				<div class="list">
					<span>运费：</span>
					<i id="postage">￥${map.tPostage}</i>
				</div>
				<div class="list">
					<span>应付金额总计：</span>
					<i class="all" id="tPayment">￥${map.pMoney}</i>
				</div>
				<div class="btnBox">
					<a href="javascript:;" id="z-submit">提交订单</a>
				</div>
				<h6 id="info-show"><i></i> 收    <i></i><i></i><i></i><i></i>   电话  <i></i></h6>
				<i class="hidden" id="sa_id"></i>
			</div>
		</div>
		<!--提交订单-->
		<form method="get" action="goPay" id="goPay" name="goPay" class="form-inline">
			<input type="hidden" id="orderID-pay" name="orderID"/>
		</form>
		<!--新增地址容器-->
		<div id="add-address-box">
			<h1>新增收货地址</h1>
			<div class="info-add" style="margin-top:40px">
				<h3><i>*</i>所在地区</h3>
				<div class="inpt-info-add">
					<select class="address-add" placeholder="省" id="add-province"></select>
					<select class="address-add" style="margin:0 20px" placeholder="市" id="add-city"></select>
					<select class="address-add" placeholder="区" id="add-area"></select>
				</div>
			</div>
			<div class="info-add detailed-address">
				<h3><i>*</i>详细地址</h3>
				<div class="inpt-info-add">
					<textarea rows="3" placeholder="无需重复填写省市区，小于75字" id="add-detail-address"></textarea>
				</div>
			</div>
			<div class="info-add">
				<h3><i>*</i>收货人姓名</h3>
				<div class="inpt-info-add">
					<input placeholder="不能为昵称、x先生、x小姐等，请使用真实姓名" id="add-name" />
				</div>
			</div>
			<div class="info-add">
				<h3><i>*</i>手机号码</h3>
				<div class="inpt-info-add">
					<input placeholder="手机号码、电话号码必须填一项" id="add-mobile" />
				</div>
			</div>
			<div class="info-add">
				<h3><i>*</i>身份证号码</h3>
				<div class="inpt-info-add">
					<input placeholder="海关检查必须要有身份证验证" id="add-id-card" />
				</div>
			</div>
			<b class="quit-add">取消</b>
			<b class="save-add">保存新地址</b>
		</div>
		<div id="alter-address-box">
			<h1>修改收货地址</h1>
			<div class="info-add">
				<h3>原收货地区</h3>
				<div class="inpt-info-add">
					<input class="address-add" id="alter-province" readonly="readonly" />
					<input class="address-add" style="margin:0 20px" id="alter-city" readonly="readonly" />
					<input class="address-add" id="alter-area" readonly="readonly" />
				</div>
			</div>
			<div class="info-add">
				<h3>修改地区</h3>
				<div class="inpt-info-add">
					<select class="address-add" id="alter-province-new">
						<option value=''>请选择省</option>
						<c:forEach items="${provinceList}" var="pro">
							<option value="${pro.area_id}" provincePin="${pro.area_value}">${pro.area_name}</option>
						</c:forEach>
					</select>
					<select class="address-add" style="margin:0 20px" id="alter-city-new"></select>
					<select class="address-add" id="alter-area-new"></select>
				</div>
			</div>
			<div class="info-add detailed-address">
				<h3><i>*</i>详细地址</h3>
				<div class="inpt-info-add">
					<textarea rows="3" placeholder="无需重复填写省市区，小于75字" id="alter-detail-address"></textarea>
				</div>
			</div>
			<div class="info-add">
				<h3><i>*</i>收货人姓名</h3>
				<div class="inpt-info-add">
					<input placeholder="不能为昵称、x先生、x小姐等，请使用真实姓名" id="alter-name" />
				</div>
			</div>
			<div class="info-add">
				<h3><i>*</i>联系电话</h3>
				<div class="inpt-info-add">
					<input placeholder="手机号码、电话号码必须填一项" id="alter-mobile" />
				</div>
			</div>
			<div class="info-add">
				<h3><i>*</i>身份证号码</h3>
				<div class="inpt-info-add">
					<input placeholder="海关检查必须要有身份证验证" id="alter-id-card" />
				</div>
			</div>
			<b class="quit-alter">取消</b>
			<b class="save-alter">保存修改</b>
		</div>
		<script src="../static/assets/js/confirmCar_all.js"></script>
//		<script>
//			console.log(${map})
//		</script>
		<hr />
		<%@ include file="../index/footer.jsp"%>
	</body>

</html>