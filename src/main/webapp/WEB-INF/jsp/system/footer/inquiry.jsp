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
		<title>询价功能</title>
		<%@ include file="../index/headLink.jsp"%>
		<script>
			function search() {
				if($('#keyword').val() == ''){
					return location.reload();
				}
				$("#searchFrom").submit();
			}
		</script>
	</head>

	<body id="product-body">
		<!--header-->		
		<%@ include file="../index/topNotLogin.jsp"%>
		<div id="introduce-page">
			<%@ include file="footerMenuList.jsp"%>
			<div class="persRight">
				<h2>询价功能</h2>
				<h3>您需要大批量采购？没找到您需要的商品？没关系，您可以通过本站的询价功能，填写所需商品的信息，专业采购人员会帮您寻找优质货源并在第一时间以邮件或电话的形式通知您</h3>
				<div class="attention">
					<h4>注意事项</h4>
					<h6>1.询价功能仅限本站注册用户使用</h6>
					<h6>2.询价的商品只支持以托盘为单位计算运费，体积不足一托盘的按一托盘计算，海运以20尺或40尺集装箱计算运费，不足一集装箱按一集装箱计算</h6>
					<h6>3.对于询价商品数量过少的，我们会提供给您一个最小采购量，您可以按照最小采购量进行采购</h6>
					<h6>4.如果您对询价结果有任何不满意，可以直接联系我们的客服进行沟通</h6>
					<h6>5.因为海外商品受货源及汇率等影响，价格会时有波动，我们所给的询价结果存在有效期限，超过有效期的询价，需要重新提交商品信息询价</h6>
					<h6>6.经过最终确认，您对询价结果满意时，可直接在“个人中心”签署系统生成合同</h6>
				</div>
			</div>
		</div>
		<!--页脚-->
		<%@ include file="../index/footer.jsp"%>
		<!--右侧固定-->
		<%@ include file="../index/rightFixed.jsp"%>
	</body>	
</html>