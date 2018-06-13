<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav id="zone-menu">
	<ul>
		<li class="title">订单中心</li>
		<li class="sep${menuIndex == '1' ? ' active' : ''}">
			<a href="${menuIndex == '1' ? '../personal/myOrder' : 'javascript:;'}">我的订单</a>
		</li>
		<li class="sep${menuIndex == '2' ? ' active' : ''}">
			<a href="${menuIndex == '2' ? '../personal/purchaseOrder' : 'javascript:;'}">微仓采购订单</a>
		</li>
		<li class="sep${menuIndex == '3' ? ' active' : ''}">
			<a href="${menuIndex == '3' ? '../personal/sellOrder' : 'javascript:;'}">微仓销售订单</a>
		</li>
		<li class="title">个人中心</li>
		<li class="sep${menuIndex == '4' ? ' active' : ''}">
			<a href="${menuIndex == '4' ? '../personal' : 'javascript:;'}">我的信息</a>
		</li>
		<li class="sep${menuIndex == '5' ? ' active' : ''}">
			<a href="${menuIndex == '5' ? 'myOrder' : 'javascript:;'}">我的关注</a>
		</li>
		<li class="sep${menuIndex == '6' ? ' active' : ''}">
			<a href="${menuIndex == '6' ? 'myOrder' : 'javascript:;'}">个人资料</a>
		</li>
		<li class="sep${menuIndex == '7' ? ' active' : ''}">
			<a href="${menuIndex == '7' ? 'myOrder' : 'javascript:;'}">收货地址</a>
		</li>
		<li class="title">微仓中心</li>
		<li class="sep${menuIndex == '8' ? ' active' : ''}">
			<a href="${menuIndex == '8' ? 'myOrder' : 'javascript:;'}">微仓库存</a>
		</li>
		<li class="sep${menuIndex == '9' ? ' active' : ''}">
			<a href="${menuIndex == '9' ? 'myOrder' : 'javascript:;'}">微仓发货</a>
		</li>
		<li class="sep${menuIndex == '10' ? ' active' : ''}">
			<a href="${menuIndex == '10' ? 'myOrder' : 'javascript:;'}">合并付款</a>
		</li>
		<li class="title">询价中心</li>
		<li class="sep${menuIndex == '11' ? ' active' : ''}">
			<a href="${menuIndex == '11' ? 'myOrder' : 'javascript:;'}">我的询价</a>
		</li>
		<li class="sep${menuIndex == '12' ? ' active' : ''}">
			<a href="${menuIndex == '12' ? 'myOrder' : 'javascript:;'}">我的合同</a>
		</li>
	</ul>
</nav>