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
		<title>大额采购-北极光供应链</title>
		<%@ include file="../index/headLink.jsp"%>
		<style>
			select{
				width:80px;
				border:none;
				background:#f9f9f9;
			}
		</style>
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
		<form method="post" action="largeBuyList" name="homeLargeBuy" id="homeLargeBuy" class="form-inline">
			<div id="largeBuy-box">
				<div class="tab-list">
					<table class="table table-striped" style="border-bottom: 1px solid #e4e4e4;">
						<tbody id="g-tbody">
							<tr class="tr-head">
								<td class="center">
									<select name="category1ID" id="category1ID" onchange="LargeBuy(1)">
										<option value=''>产品类目</option>
										<c:forEach items="${category1}" var="cate">
											<option value="${cate.category_id}" <c:if test="${cate.category_id == pd.category1ID}">selected="selected"</c:if>>${cate.category_name}</option>
										</c:forEach>
									</select>
								</td>
								<td class="center">
									<select name="brandID" id="brandID" onchange="LargeBuy(1)">
										<option value=''>品牌选择</option>
										<c:forEach items="${brandLargeBuy}" var="brand">
											<option value="${brand.brand_id}" <c:if test="${brand.brand_id == pd.brandID}">selected="selected"</c:if>>${brand.brand_name}</option>
										</c:forEach>
									</select>
								</td>
								<td class="center goodsName">商品名称</td>
								<td class="center">EXW报价</td>
								<td class="center">数量</td>
								<td class="center">有效期</td>
								<td class="center">发布时间</td>
								<td class="center">操作</td>
							</tr>
							<!-- 开始循环 -->
							<c:forEach items="${largeBuyGoods}" var="good">
								<tr class="tbody-tr" data-goodid="${good.goods_id}" data-brandid="${good.brand_id}" data-listid="${good.id}">
									<td class="center">${good.category_name}</td>
									<td class="center">${good.brand_name}</td>
									<td class="center goodsName"><i><a target="_blank" href="<%=basePath%>detailPage/goGoodsdetail?goodsID=${good.goods_id}" title="${good.goods_name_new}">${good.goods_name_new}</a></i></td>
									<td class="center">￥<i>${good.exw}</i></td>
									<td class="center">${good.num}${good.norm}</td>
									<td class="center">${good.valid_days}天</td>
									<td class="center">${good.operate_time}</td>
									<td class="center">
										<a class="see-detail" href="javascript:;" onclick="inquiry_no_index(this)" goodID="${good.goods_id}" data-src="<%=basePath%>customerInquiry/goAddInquiryPage?goodsID=${good.goods_id}">询价</a>
									</td>
								</tr>
							</c:forEach>		
						</tbody>
					</table>
				</div>
				<%@ include file="../commons/page.jsp"%>
			</div>
		</form>
		<%@ include file="../index/footer.jsp"%>
		<script>
			function goPage(pageNo) {
				$('#currentPage').val(pageNo);
				var fromIndex = (pageNo - 1) * $('#pageSize').val();
//				return console.log(fromIndex)
				if(fromIndex < 0) {
					fromIndex = 0;
				}
				$('#fromIndex').val(fromIndex);
				LargeBuy()
			}
			function LargeBuy(i){
				if(i == 1){
					$('#fromIndex').val(0)
				}
				$('#homeLargeBuy').submit();
			}
		</script>
	</body>
</html>
