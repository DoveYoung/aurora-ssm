<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--登录-->
<c:if test="${customerID == null}">
<div id="layer-login">
	<h2>账号密码登录</h2>
	<div class="error-tip"></div>
	<input id="inpt-count" placeholder="请输入您的邮箱登录" />
	<input id="inpt-pw" type="password" placeholder="请输入您的密码" />
	<input type="button" id="login-this" value="登录" />
	<div class="other-operate">
		<a target="_blank" href="<%=basePath%>registerLogin/goRegister">注册新账号</a>
		<a class="forgot-pw" target="_blank" href="<%=basePath%>registerLogin/goResetPwd">忘记密码</a>
	</div>
	<script>
		$(function(){
			//登录
			$('#to-login').on('click', function() {
				to_login()
			})
			$('#inpt-count').on('blur', function() {
				var count = $(this).val();
				var reg = /^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/gi;
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
			}).on('keydown',function(event){
				var e = event || window.event || arguments.callee.caller.arguments[0];
				if(e && e.keyCode == 13){
					login();
				}
			})
			$('#login-this').on('click', function() {
				login()
			})
		})
		console.log(${homeCategory})
	</script>
</div>
</c:if>
<div id="header" class="header-bg">
	<div class="top-box">
		<div class="top">
		<c:choose>
			<c:when test="${customerID == null}">
				<i>您好！欢迎来到北极光跨境供应链</i>
				<a class="to-login" id="to-login" href="javascript:;">登录</a>
				<em>|</em>
				<a class="to-register" target="_blank" href="<%=basePath%>registerLogin/goRegister">免费注册</a>
				
			</c:when>
			<c:otherwise>
				<i>您好，会员${customerEmail}</i>
				<em>|</em>
				<i class="login-out" id="logout">退出登录</i>
				<script>
					$('#logout').on('click',function(){
						layer.confirm('确定退出登录？', function(index) {
							$.ajax({
								type:'POST',
								url:'../registerLogin/logout',
								success:function(data){
									if(data.state == 'success'){
										window.location.href = '<%=basePath%>'
									}else{
										console.log(data.msg)
									}
								},
								error:function(){
									layer.msg('网络超时，与服务器断开连接')
								}
							})
							layer.close(index)
						})
					})
				</script>
			</c:otherwise>
		</c:choose>
			<a class="help-center" target="_blank" href="<%=basePath%>footer/depositMode">帮助中心</a>
			<b onclick="go_customer_center()">个人中心</b>
		</div>
	</div>
	<div class="header-search" id="header-search-bg">
		<div class="search-box">
			<a class="logo" href="<%=basePath%>"></a>
		</div>
	</div>
	
</div>
<script>
	$('.all-cate').on('mouseover',function(){
		$(this).children('.cate-box').removeClass('cate-box-none')
		
	}).on('mouseout',function(){
		$(this).children('.cate-box').addClass('cate-box-none')
	})
	$('.cate-list').children('li').on('mouseover',function(){
		var i = $(this).index();
	//	console.log(111)
	//	$(this).children('h1').eq(0).css('border','0');
		if(i < $('.cate-list').children('li').length-1){
			$(this).next().children('h1').eq(0).addClass('h1-active');
		}
		
	}).on('mouseout',function(){
		var i = $(this).index();
		if(i < $('.cate-list').children('li').length-1){
			$(this).next().children('h1').eq(0).removeClass('h1-active');
		}
	})
</script>
<!--<script>
	function go_myZone(){
		if(!customer()){
			return to_login();
		}
		window.open('<%=basePath%>customerOrder/orderList');
	}
	//回到顶部
	$(function(){
		$('#toTop').on('click', function() {
			var sc = $(window).scrollTop();
			$('body,html').animate({ scrollTop: 0 }, 200);
		})
		
	})
</script>-->