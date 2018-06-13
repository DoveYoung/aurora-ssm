<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<ul id="nav">
	<li class="top close-top <c:if test="${menuIndex < '4'}">show-top</c:if>">
		<h3 class="title ">
			交易指南
			<i class="icon-title"></i>
		</h3>
		<ul class="subnav">
			<li <c:if test="${menuIndex == '1'}">class="active"</c:if>><a href="depositMode">定金模式</a></li>
			<li <c:if test="${menuIndex == '2'}">class="active"</c:if>><a href="inquiry">询价功能</a></li>
			<li <c:if test="${menuIndex == '3'}">class="active"</c:if>><a href="procurementContract">采购合同</a></li>
		</ul>
	</li>
	<li class="top close-top <c:if test="${menuIndex == '4' || menuIndex == '5'}">show-top</c:if>">
		<h3 class="title">
			支付与物流
			<i class="icon-title"></i>
		</h3>
		<ul class="subnav">
			<li <c:if test="${menuIndex == '4'}">class="active"</c:if>><a href="microWarehouse">微仓服务</a></li>
			<li <c:if test="${menuIndex == '5'}">class="active"</c:if>><a href="crossBorder">跨境物流服务</a></li>
		</ul>
	</li>
	<li class="top close-top <c:if test="${menuIndex == '6' || menuIndex == '7' || menuIndex == '10'}">show-top</c:if>">
		<h3 class="title">
			售后服务
			<i class="icon-title"></i>
		</h3>
		<ul class="subnav">
			<li <c:if test="${menuIndex == '6'}">class="active"</c:if>><a href="noticeOfReturn">退货须知</a></li>
			<li <c:if test="${menuIndex == '7'}">class="active"</c:if>><a href="legalService">服务条款</a></li>
			<li <c:if test="${menuIndex == '10'}">class="active"</c:if>><a href="userAgreement">用户协议</a></li>
		</ul>
	</li>
	<li class="top close-top <c:if test="${menuIndex == '8' || menuIndex == '9'}">show-top</c:if>">
		<h3 class="title">
			关于我们
			<i class="icon-title"></i>
		</h3>
		<ul class="subnav">
			<li <c:if test="${menuIndex == '8'}">class="active"</c:if>><a href="aboutUs">关于北极光</a></li>
			<li <c:if test="${menuIndex == '9'}">class="active"</c:if>><a href="contactUs">联系我们</a></li>
		</ul>
	</li>
</ul>
<script>
	$(function(){
		$('.title').on('click',function(){
			var top = $(this).parent()
			if(top.hasClass('show-top')){
				top.removeClass('show-top');
				top.addClass('close-top');
			}else if(top.hasClass('close-top')){
				top.removeClass('close-top');
				top.addClass('show-top');
			}
		})
	})
</script>