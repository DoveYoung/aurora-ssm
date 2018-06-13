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
		<title>个人中心--我的微仓</title>
		<link rel="stylesheet" type="text/css" href="../static/assets/plugin/bootstrap-data/bootstrap-datetimepicker.min.css">
		<link rel="stylesheet" type="text/css" href="../static/assets/plugin/bootstrap-3.3.7/css/bootstrap-select.css">		
		<%@ include file="../index/headLink.jsp"%>
		<script src="../static/assets/plugin/bootstrap-data/bootstrap-datetimepicker.min.js" language="javascript"></script>
		<script src="../static/assets/plugin/bootstrap-data/bootstrap-datetimepicker.fr.js" language="javascript"></script>
		<script src="../static/assets/plugin/bootstrap-data/bootstrap-datetimepicker.zh-CN.js" language="javascript"></script>
	</head>

	<body id="product-body">
		
		<!--header-->
		<div id="search-header">			
			<%@ include file="../index/topLogin.jsp"%>			
			<%@ include file="../index/headerSearch.jsp"%>
		</div>
		<%@ include file="../index/shipType.jsp"%>
		 
		<div id="myZone">
			<%@ include file="../myzone/myzoneMenuList.jsp"%>
			<div id="zone-content">
				<c:if test="${rType == 'wchw'}"><%@ include file="../myzone/MW/wchw.jsp"%></c:if>
				<c:if test="${rType == 'wcfh'}"><%@ include file="../myzone/MW/wcfh.jsp"%></c:if>
				<c:if test="${rType == 'ddfk'}"><%@ include file="../myzone/MW/ddfk.jsp"%></c:if>	
			</div>
			
		</div>
		<c:if test="${rType == 'wcfh'}">
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
						<input placeholder="不能为昵称、x先生、x小姐等，请使用真实姓名" id="add-name"/>
					</div>
				</div>
				<div class="info-add">
					<h3><i>*</i>电话号码</h3>
					<div class="inpt-info-add">
						<input placeholder="手机号码、电话号码必须填一项" id="add-mobile"/>
					</div>
				</div>
				<!--座机-->
				<!--<div class="info-add">
					<h3>电话号码</h3>
					<div class="inpt-info-add landline">
						<input style="width:120px" placeholder="区号"/>
						<input style="width:180px;margin:0 15px" placeholder="电话号码"/>
						<input style="width:130px" placeholder="分级号码"/>
					</div>
				</div>-->
				<div class="info-add">
					<h3><i>*</i>身份证号码</h3>
					<div class="inpt-info-add">
						<input placeholder="海关检查必须要有身份证验证" id="add-id-card"/>
					</div>
				</div>
				<b class="quit-add">取消</b>
				<b class="save-add">保存新地址</b>
			</div>
		</c:if>
		<c:if test="${rType == 'ddfk'}">
			<div class="totalbox">
				<div class="totalbarBox">
					<div class="totalbar" style="padding-left: 160px;">
						<input type="checkbox" id="checkAll02" />
						<i class="checkAll-box">全选</i>
						<a href="javascript:;" id="delete-allChecked">删除订单</a>
						<span id="settlement" class="not-checked" style="margin-right: 0;">去结算</span>
						<span class="total-box">需支付：<b>￥<i id="totalPrice">0.00</i></b></span>
						<span class="goods-num-box">已选择<i id="totalNum">0</i>个订单</span>
					</div>
				</div>
			</div>
			<form method="post" action="goMWOrderSettle" name="goMWOrderSettle" id="goMWOrderSettle">
				<input type="hidden" name="mwOrderID" id="mwOrderID"/>
			</form>
			<script>
				function fix() {
					var bottom = $(document).height() - $(document).scrollTop() - $(window).height();
					//可视区距页面底部
					if(bottom > 480) {
						$('.totalbarBox').addClass('totalbarFix');
					} else {
						$('.totalbarBox').removeClass('totalbarFix');
					}
				}
				$(function() {
					window.onscroll = function() {
						fix()
					}
					fix();
				})
			</script>
		</c:if>
		<script type="text/javascript" src="../static/assets/plugin/bootstrap-3.3.7/js/bootstrap-select.js"></script>
    	<script type="text/javascript" src="../static/assets/plugin/bootstrap-3.3.7/js/bootstrap.js"></script>
		<script>
			$(function(){
				$('#order_c').on('click','b',function(){
					if(!$(this).hasClass('active')){
						var rType = $(this).attr('state');
						if(rType == 'wchw'){
							$('#goMicroWarehouse').submit();
						}else if(rType == 'wcfh'){
							$('#goDeliverGoods').submit();
						}else if(rType == 'ddfk'){
							$('#goMWOrder').submit();
						}
					}
				})
			})
		</script>
		<!--页脚-->
		<%@ include file="../index/footer.jsp"%>
		<!--右侧固定-->
		<%@ include file="../index/rightFixed.jsp"%>
		
	</body>	
</html>