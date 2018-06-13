<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<header id="docHead">
	<a href="<%=basePath%>" class="logo-index"></a>
	<div class="m-search-box">
		<div class="toSearch">
			<form method="get" action="../searchPage/search" id="searchFrom" name="searchFrom" class="form-inline" target="_blank">
				<!--邮寄方式-->
				<input type="hidden" class="toSearchInput" value="${pd.shipType}" name="shipType" id="shipType" />
				<input type="text" class="toSearchInput" value="${pd.keyword}" name="keyword" id="keyword" />
				<input type="button" class="search-btn toSearchBtn" value="搜索" onclick="headerSearch();" />
			</form>
			<b>或</b>
			<a target="_blank" href="javascript:;" onclick="go_inquiry()" class="toSearchSupply">寻找货源</a>
		</div>
		<ul id="suggestlist">
			<c:forEach items="${homeHotWordList}" var="list">
				<li>
					<a href="javascript:;">${list}</a>
				</li>
			</c:forEach>
		</ul>
		
		<script>
			function headerSearch(){
				$('#keyword').val($.trim($('#keyword').val()))
				if($('#keyword').val() == ''){
					return
				}
				$('#keyword').val($.trim($('#keyword').val()));
				$("#searchFrom").submit();
			}
			$('#keyword').on('keydown',function(event){
				var e = event || window.event || arguments.callee.caller.arguments[0];
				if(e && e.keyCode == 13){
					headerSearch();
				}
			})
			$('#suggestlist li').on('click','a',function(){
				$('#keyword').val($(this).text());
				$("#searchFrom").submit();
			})
		</script>
	</div>
</header>