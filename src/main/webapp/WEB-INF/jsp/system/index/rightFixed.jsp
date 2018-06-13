<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="rightBarNew">
	<ul>
		<li>
			<a target="_blank" href="<%=basePath%>footer/contactUs">
				<i></i>
				<b>客服</b>
				<em></em>
			</a>
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
			<!--<div class="more-info">
				<div class="info-box">
					<h3>电话客服</h3>
					<h5>0571-87916639</h5>
					<h3>QQ在线客服</h3>
					<h5><a class="qq-icon" target="_blank" title="点击这里给小北发消息" href="http://wpa.qq.com/msgrd?v=3&uin=2303550195&site=qq&menu=yes">2303550195</a></h5>
					<h3>微信客服</h3>
					<a class="wechat" href="javascript:;"></a>
					<b class="b-triangle"></b>
				</div>
			</div>-->
		</li>
		<li>
			<a href="javascript:;" id="goCart" onclick="go_cart()">
				<i></i>
				<b>购物车</b>
				<em></em>
			</a>
		</li>
		<li>
			<a href="javascript:;" onclick="go_inquiry()">
				<i></i>
				<b>找货源</b>
			</a>
			<span class="li-h" onclick="go_inquiry()">点击询价</span>
		</li>
	</ul>
	<a href="javascript:;" id="toTop" onclick="scroll_top()">
		<i></i>
		<b>TOP</b>
		<span>返回顶部</span>
	</a>
</div>