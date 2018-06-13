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
		<title>北极光-登录</title>
		<%@ include file="../index/headLink.jsp"%>
		<style>
			body{
				background-image:none !important;
				background:#fff !important;
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
			<script>
			var _hmt = _hmt || [];
			(function() {
			  var hm = document.createElement("script");
			  hm.src = "https://hm.baidu.com/hm.js?ba1be72b0d777b181425157f7d67a676";
			  var s = document.getElementsByTagName("script")[0]; 
			  s.parentNode.insertBefore(hm, s);
			})();
		</script>
	</head>

	<body id="aurorascm-index">
		<div id="header">
			<div id="top">
				<!--logo-->
				<div class="logo">
					<a href="<%=basePath%>">
						<img src="../static/assets/img/website-logo.png" />
					</a>
					<!--<i class="logo-tip"></i>-->
				</div>
			</div>
			<div id="lg-bg">
				<div class="lg-box">
					<div class="box">
						<h2>账号密码登录</h2>
						<h5><i></i><input id="inpt-count" placeholder="请输入您的邮箱登录"/></h5>
						<h5><i class="pw"></i><input id="inpt-pw" type="password" placeholder="请输入您的密码"/></h5>
						<div id="login-this">登录</div>
						<div class="other-operate">
							<a target="_blank" href="<%=basePath%>registerLogin/goRegister">注册新账号</a>
							<a class="forgot-pw" target="_blank" href="<%=basePath%>registerLogin/goResetPwd">忘记密码</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--页脚-->
		<%@ include file="../index/footer.jsp"%>
		</script>
		<script>
			$('#inpt-count').on('blur', function() {
				var count = $(this).val();
				var reg = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
				if(!reg.test(count)) {
					$('.error-tip').text('请填入正确的账号');
				} else {
					$('.error-tip').text('');
				}
			})
			$('#inpt-pw').on('blur', function() {
				if($(this).val() == '') {
					$('.error-tip').text('请输入密码');
				}
			}).on('focus',function(){
				document.onkeydown=function(event){
	            	var e = event || window.event || arguments.callee.caller.arguments[0];
					if(e && e.keyCode == 13){
						login()
					}
				}
			})
			$('#login-this').on('click', function() {
				if($('#inpt-count').val() == ''){
					return layer.tips('请填写登录名','#inpt-count',{
						tips:[2,'#78BA32']
					})
				}
				if($('#inpt-pw').val() == ''){
					return layer.tips('请填写密码','##inpt-pw',{
						tips:[2,'#78BA32']
					})
				}
				var customerEmail = $('#inpt-count').val();
				var customerPassword = $('#inpt-pw').val();
				$.ajax({
					type: "POST",
					url: '../registerLogin/interceptLogin',
					data: {
						customerEmail:customerEmail,
						customerPassword:customerPassword
					},
					dataType: 'json',
					success: function(data){
//						return console.log(data)
						if(data.state == 'success'){
//							return console.log(data.goURL)
							var curPath = window.document.location.href;
							var pathName = window.document.location.pathname;
							var basePath = curPath.substring(0,curPath.indexOf(pathName)) + "/";
							if(data.result=='homePage/goHomePage'){
								data.result = '';
							}
							if(basePath == 'http://localhost:8080/'){
								window.location.href = basePath+'aurorascm/'+data.result;
							}else{
								window.location.href = basePath+data.result;
							}
						}else if(data.state == 'usererror'){
							return layer.msg('账号错误！')
						}else if(data.state == 'passworderror'){
							return layer.msg('密码错误！')
						}
					}
				});
			})
		</script>
	</body>	
</html>