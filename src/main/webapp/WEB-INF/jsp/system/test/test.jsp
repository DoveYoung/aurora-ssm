<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8" />
		<title>测试页面</title>
		<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="//cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
		
		<script>
			function search() {	
				$("#searchFrom").submit();
			}
		</script>
	</head>
	<body>
		<h1>form表单提交</h1>
		<form method="get" action="areaAddr/getLogisticInfo" id="searchFrom" name="searchFrom" class="form-inline">
			<input type="text" class="toSearchInput" value="${pd.expCode}" name="expCode" id="expCode" />
			<input type="text" class="toSearchInput" value="${pd.expNo}" name="expNo" id="expNo" />
			<input type="button" name="" id="" class="toSearchBtn" onclick="search()" value="搜索" />
		</form>
		<h1>ajax</h1>
		<b class="btn btn-success" id="ajax-btn">触发POST/ajax</b>
		<b class="btn btn-default" id="ajax-btn02">触发GET/ajax</b>
	</body>
	<script>
		$(function(){
			//ajax例子
//			jQuery.ajax({
//				type: "POST",
//				url: "login/customerRegister",
//				data: {'name':name,'ID':id},
//				dataType: "json",
//				success: function(result) {
//					//提交成功后的操作
//					console.log('提交成功！')
//					
//				}
//			})
			//POST
			$('#ajax-btn').on('clcik',function(){
				jQuery.ajax({
					type: "POST",
					url: "",
					data: {},
					dataType: "json",
					success: function(data) {
						console.log(data)
						if(data == 'success'){
							console.log('成功')
						}else{
							console.log('失败')
						}
					}
				})
			})
			//GET
			$('#ajax-btn02').on('clcik',function(){
				jQuery.ajax({
					type: "GET",
					url: "",
					data: {},
					dataType: "json",
					success: function(data) {
						console.log(data)
						if(data == 'success'){
							console.log('成功')
						}else{
							console.log('失败')
						}						
					}
				})
			})
		})
	</script>
</html>
