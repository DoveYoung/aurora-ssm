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
		<title>跨境物流服务</title>
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
				<h2>跨境物流服务</h2>
				<h3>本站目前提供海外交货、海外仓直邮、保税仓代发等多种物流服务模式，可以根据用户实际情况，为用户推荐匹配的物流方案</h3>
				<h5>配送方式</h5>
				<div class="delivery">
					<i>1</i>
					<b>保税仓商品配送</b>
				</div>
				<img class="crossBorder-img" src="../static/assets/img/crossBorder01.jpg"/>
				<div class="delivery">
					<i>2</i>
					<b>海外仓商品配送</b>
				</div>
				<img class="crossBorder-img" src="../static/assets/img/crossBorder02.jpg" style="height:173px"/>
				<hr />
				<div class="attention">
					<h4>税费计算</h4>
					<h6>1.根据国家法律相关规定，跨境电子商务零售进口商品的单次交易限值为人民币2000元，个人年度交易限值为人民币20000元。个人年度已使用额度、可用额度官方查询地址：http://ceb2pub.chinaport.gov.cn</h6>
					<h6>2.跨境电商综合税率 = （消费税率+增值税率）/（1-消费税率）×0.7 （高档化妆品与奢侈品除外）本站所售直邮商品均为包税价格，不用再额外缴纳税费。</h6>
				</div>
			</div>
		</div>
		<!--页脚-->
		<%@ include file="../index/footer.jsp"%>
		<!--右侧固定-->
		<%@ include file="../index/rightFixed.jsp"%>
	</body>	
</html>