<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav id="zone-menu">
	<h2>个人中心</h2>
	<ul>
		<li>
			<a <c:if test="${menuIndex == 1}">class="active" href="javascript:;"</c:if><c:if test="${menuIndex != 1}">href="<%=basePath%>customerOrder/orderList"</c:if>>我的订单</a>
		</li>
		<li>
			<a <c:if test="${menuIndex == 2}">class="active" href="javascript:;"</c:if><c:if test="${menuIndex != 2}">href="<%=basePath%>customerInquiry/inquiryList"</c:if>>我的询价</a>
		</li>
		<li>
			<a <c:if test="${menuIndex == 3}">class="active" href="javascript:;"</c:if><c:if test="${menuIndex != 3}">href="<%=basePath%>customerContract/contractList"</c:if>>我的合同</a>
		</li>
		<li>
			<a <c:if test="${menuIndex == 4}">class="active" href="javascript:;"</c:if><c:if test="${menuIndex != 4}">href="<%=basePath%>customerMW/goMicroWarehouse"</c:if>>我的微仓</a>
		</li>
		<li>
			<a <c:if test="${menuIndex == 5}">class="active" href="javascript:;"</c:if><c:if test="${menuIndex != 5}">href="<%=basePath%>customerAttention/attentionList"</c:if>>我的关注</a>
		</li>
		<li>
			<a <c:if test="${menuIndex == 6}">class="active" href="javascript:;"</c:if><c:if test="${menuIndex != 6}">href="<%=basePath%>receiverAddress/addressList"</c:if>>收货地址</a>
		</li>
		<li>
			<a <c:if test="${menuIndex == 7}">class="active" href="javascript:;"</c:if><c:if test="${menuIndex != 7}">href="<%=basePath%>customerInfo/MyInfo"</c:if>>我的资料</a>
		</li>
	</ul>
</nav>