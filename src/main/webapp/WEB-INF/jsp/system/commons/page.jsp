<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--跳转页容器-->
<input name="currentPage" id="currentPage" value="${page.currentPage}" type="hidden" />
<input name="fromIndex" id="fromIndex" value="${page.fromIndex}" type="hidden" />
<input type="hidden" name="pageSize" id="pageSize" value="${page.pageSize}" />
<!--<select name="pageSize" id="pageSize">
		<!--<option value="10" >10</option>-->
<!--<option value="15">15</option>-->
<!--<option value="50">50</option>-->
<!--</select>-->

<!--首页-->
<div id="page-list">
	<c:choose>
		<c:when test="${page.currentPage == 1}">
			<a href="javascript:;" class="disabled">首页</a>
		</c:when>
		<c:otherwise>
			<a href="javascript:;" onclick="goPage(1)">首页</a>
		</c:otherwise>
	</c:choose>
	<!--上一页-->
	<c:choose>
		<c:when test="${page.currentPage == 1}">
			<a href="javascript:;" class="disabled">上一页</a>
		</c:when>
		<c:otherwise>
			<a href="javascript:;" onclick="goPage(${page.currentPage}-1)">上一页</a>
		</c:otherwise>
	</c:choose>
	<!--下一页-->
	<c:choose>
		<c:when test="${page.currentPage == page.totalPage}">
			<a href="javascript:;" class="disabled">下一页</a>
		</c:when>
		<c:otherwise>
			<a href="javascript:;" onclick="goPage(${page.currentPage}+1)">下一页</a>
		</c:otherwise>
	</c:choose>
	<!--末页-->
	<c:choose>
		<c:when test="${page.currentPage == page.totalPage}">
			<a href="javascript:;" class="disabled">末页</a>
		</c:when>
		<c:otherwise>
			<a href="javascript:;" onclick="goPage(${page.totalPage})">末页</a>
		</c:otherwise>
	</c:choose>
	<!--跳页-->
	<input name="toPage" id="toPage" value="${page.currentPage}" min="1" max="${page.totalPage}" type="text" class="am-form-field" autocomplete="off" onKeyUp="only_num(this)" onafterpaste="only_num(this)"/>
	<a href="javascript:;" onclick="goPage($('#toPage').val() >= ${page.totalPage} ? ${page.totalPage}:$('#toPage').val())">跳转</a>
	<i>共${page.totalPage}页,${page.totalRecord}条记录.</i>
	<!--每页显示${page.pageSize}条，-->
</div>
<script>
function only_num(i){
	$(i).val($(i).val().replace(/\D/g,''))
}
</script>