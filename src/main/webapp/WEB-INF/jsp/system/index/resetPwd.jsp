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
		<title>找回密码</title>
		<%@ include file="../index/headLink.jsp"%>
	</head>

	<body id="aurorascm-resetpwd">
		<div class="resetpwd-main">
			<div class="header">
				<a href="<%=basePath%>" class="logo">
					<img src="../static/assets/img/logo.gif" />
				</a>
			</div>
			<div class="step-bar">
				<div class="step-num">
					<i class="step-i step-active">1</i>
					<b class="step-b step-b-active"></b>
					<i class="step-i">2</i>
					<b class="step-b"></b>
					<i class="step-i">3</i>
					<b class="step-b"></b>
					<i class="step-i">4</i>
				</div>
				<div class="step-font">
					<i>填写账号</i>
					<i style="margin-right: 230px;">身份验证</i>
					<i style="margin-right: 240px;">设置新密码</i>
					<i style="margin-right: 0;">完成</i>
				</div>
			</div>
			<div class="resetpwd-box">
				<a class="title" href="javascript:;"></a>
				<!--第一步-->
				<div class="step-box step-box-active">
					<div class="u-input">
						<label>请输入您的账号：</label>
						<input id="inpt-count" class="i-inpt" placeholder="请输入您的邮箱作为登录账号" />
						<div class="side-tip">

						</div>
					</div>
					<div class="u-input">
						<label>验证码：</label>
						<input class="i-inpt" id="iCode" placeholder="请输入验证码" />
						<div class="side-tip"></div>
					</div>			
					<div class="u-input" style="margin-top:10px;height:50px;">
						<label></label>
						<div class="u-code">
							<div class="u-code-img">
								<img id="codeImg" src="" alt="点击更换" title="点击更换"/>
							</div>
							<span class="u-code-cut" onclick="changeCode()">看不清？换一张</span>
						</div>
					</div>
					<div class="refer refer01 unable-reset">提交</div>
				</div>
				<!--第二步-->
				<div class="step-box">
					<div class="u-input">
						<label>已绑定手机号：</label>
						<input id="inpt-phone" class="i-inpt hidden"/>
						<input id="phone-hide" class="i-inpt" readonly="readonly" style="border:none;" />	
					</div>
					<div class="u-input">
						<label>短信验证码：</label>
						<input id="inpt-sms" class="i-inpt sms-code" placeholder="请输入短信验证码" />
						<div class="getSms-code">获取验证码</div>
						<div class="side-tip"></div>
					</div>
					<div class="refer refer02 unable-reset">提交</div>
				</div>
				<!--第三步-->
				<div class="step-box">
					<div class="u-input">
						<label>新密码：</label>
						<input id="inpt-pw" class="i-inpt" type="password" placeholder="数字、字母、符号、至少两个组合，6-20个字符" />
						<div class="side-tip"></div>
					</div>
					<div class="u-input">
						<label>确认新密码：</label>
						<input id="inpt-pw2" class="i-inpt" type="password" placeholder="再次输入密码" />
						<div class="side-tip"></div>
					</div>
					<div class="refer refer03 unable-reset">提交</div>
				</div>
				<!--第四步-->
				<div class="step-box">
					<div class="refer-successs">
						<img src="../static/assets/img/reset_success.png"/>
						<span>
							<b>您的密码已经重置,请妥善保管密码</b>
						</span>
						<span>
							<i id="times">3</i>秒后将跳转到首页，如不想等待，
							<a href="<%=basePath%>">立即跳转</a>
						</span>	
					</div>
				</div>
			</div>
			<span class="Record-number">CopyRight © 2017 杭州络驿电子商务有限公司 aurorascm.com 浙ICP备17030413号-2 All Rights Reserved</span>
		</div>
		<script>
			$(document).ready(function() {
				changeCode();
				$("#codeImg").bind("click", changeCode);
			});
			function genTimestamp() {
				var time = new Date();
				return time.getTime();
			}
			function changeCode() {
				$("#codeImg").attr("src", "<%=basePath%>code.do?t=" + genTimestamp());
			}
			function mobile_show(i){
				var str = i.val();
				console.log(str)
				var str2 = str.substr(0,3)+"****"+str.substr(7);
				i.val(str2)
			}
			$(function(){
				//错误提示
				function cue_error(i,j){
					i.parent().find('.side-tip').removeClass('cue-right');
					i.parent().find('.side-tip').addClass('cue-error');
					i.parent().find('.side-tip').text(j);
					i.parent().find('.side-tip').addClass('animated shake');
					setTimeout(function(){
						i.parent().find('.side-tip').removeClass('animated shake');
					},1000);
				}
				//验证正确
				function cue_right(i){
					i.parent().find('.side-tip').removeClass('cue-error');
					i.parent().find('.side-tip').addClass('cue-right');
					i.parent().find('.side-tip').text('');
				}
//				邮箱
				var cOunt = false,iCode = false;
				$('#inpt-count').on('blur',function(){
					var count = $(this).val();
					var reg = /^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/gi;
					if(reg.test(count)){
						cue_right($(this));
						if(iCode){
							$('.refer01').removeClass('unable-reset');
						}
						return cOunt = true;
					}else{
						cue_error($(this),'请输入正确的邮箱地址');
						$('.refer01').addClass('unable-reset');
						return cOunt = false;
					}
				})
				//验证码
				$('#iCode').on('blur',function(){
					if($(this).val() == ''){
						cue_error($(this),'请输入验证码');
						$('.refer01').addClass('unable-reset');
						return iCode = false
					}else{
						cue_right($(this));
						if(cOunt){
							$('.refer01').removeClass('unable-reset');
						}
						return iCode = true;
					}
				})
				//密码
				var pW = false,pW2 = false;
				$('#inpt-pw').on('blur',function(){
					var pw = $(this).val();
					var pw2 = $('#inpt-pw2').val();
					var reg = /^(?![0-9]+$)(?![a-zA-Z]+$)(?!([^(0-9a-zA-Z)]|[\(\)])+$)([^(0-9a-zA-Z)]|[\(\)]|[a-zA-Z]|[0-9]){6,}$/;
					if(pw.length < 6){
						cue_error($(this),'密码长度不能小于6位!')
						return pW = false;
					}
					if(pw.length > 16){
						cue_error($(this),'密码长度不能大于16位!')
						return pW = false;
					}
					if(!reg.test(pw)){
						cue_error($(this),'密码至少是数字、字母、符号，任意两种组合以上！');
						return pW = false;
					}else{
						cue_right($(this))
						if(pw === pw2){
							cue_right($('#inpt-pw2'));
							return pW = true,pW2 = true;
							$('.refer03').removeClass('unable-reset');
						}else{
							cue_error($('#inpt-pw2'),'两次密码不一致');
							$('.refer03').addClass('unable-reset');
							return pW = true,pW2 = false;
						}	
					}
				})
				//确认密码
				$('#inpt-pw2').on('blur',function(){
					var pw2 = $(this).val();
					var pw = $('#inpt-pw').val();
					if(!pW){
						cue_error($('#inpt-pw'),'数字、字母、符号、至少两个组合，6-16个字符');
						$('.refer03').addClass('unable-reset');
						return pW2 = false;
					}
					if(pw2 !== pw){
						cue_error($(this),'两次密码不一致');
						$('.refer03').addClass('unable-reset');
						return pW2 = false;
					}else{
						cue_right($(this));
						$('.refer03').removeClass('unable-reset');
						return pW2 = true;
						
					}
				})
				//第一步提交
				$('.refer01').on('click',function(){
					var customerEmail = $('#inpt-count').val();
					var iCode = $('#iCode').val();
					$.ajax({
						type:'POST',
						url:'../registerLogin/resetPW1',
						data:{
							'customerEmail':customerEmail,
							'iCode':iCode
						},
						dataType:'json',
						success:function(data){
							console.log(data)
							if(data.state == 'success'){
								console.log((data.customer).customer_mobile)
								$('.step-box').removeClass('step-box-active');
								$('.step-box').eq(1).addClass('step-box-active');
								$('.step-i').eq(1).addClass('step-active');
								$('.step-b').eq(0).addClass('step-b-over');
								$('#inpt-phone').val((data.customer).customer_mobile);
								$('#phone-hide').val((data.customer).customer_mobile);
								mobile_show($('#phone-hide'));
							}else{
								imp_msg(data.msg)
							}
						}
					})		
				})
				//获取短信验证码
				$('.getSms-code').on('click',function(){
					var customerEmail = $('#inpt-count').val();
					var customerMobile = $('#inpt-phone').val();
					$.ajax({
						type:'POST',
						url:'../registerLogin/resetPW2',
						data:{},
						dataType:'json',
						success:function(data){
//							console.log(data)
							if(data.state == 'success'){
//								mCode = data.mCode;
								var i = 0;
								$('.getSms-code').addClass('unable-reset');
								var mCodes = setInterval(function(){
									i++;
									$('.getSms-code').text((60-i)+'秒后重发')
									if(i == 60){
										clearInterval(mCodes)
										$('.getSms-code').text('获取验证码');
										$('.getSms-code').removeClass('unable-reset');
									}
								},1000);
								$('.refer02').removeClass('unable-reset');
							}else{
								imp_msg(data.msg)
							}
						}
					})
				})
				//第二步提交
				$('.refer02').on('click',function(){
					if($('#inpt-sms').val() == ''){
						return layer.msg('请输入验证码')
					}else{
						var customerEmail = $('#inpt-count').val();
						var customerMobile = $('#inpt-phone').val();
						$.ajax({
							type:'POST',
							url:'../registerLogin/resetPW3',
							data:{
								code:$('#inpt-sms').val()
							},
							dataType:'json',
							success:function(data){
//								console.log(data)
								if(data.state == 'success'){
//									console.log(data)
									$('.step-box').removeClass('step-box-active');
									$('.step-box').eq(2).addClass('step-box-active');
									$('.step-i').eq(2).addClass('step-active');
									$('.step-b').eq(1).addClass('step-b-over');
								}else{
									imp_msg(data.msg)
								}
							}
						})
					}
				})
				//第三步提交
				$('.refer03').on('click',function(){
					var customerEmail = $('#inpt-count').val();
					var customerPassword = $('#inpt-pw').val();
					$.ajax({
						type:'POST',
						url:'../registerLogin/resetPW4',
						data:{
							'customerEmail':customerEmail,
							'customerPassword':customerPassword
						},
						dataType:'json',
						success:function(data){
//							console.log(data)
							if(data.state == 'success'){
								$('.step-box').removeClass('step-box-active');
								$('.step-box').eq(3).addClass('step-box-active');
								$('.step-i').eq(3).addClass('step-active');
								$('.step-b').eq(2).addClass('step-b-over');
								var iter = setInterval(function(){
									var n = parseInt($('#times').text())
									$('#times').text(n-1);
									if(n == 1){
										clearInterval(iter);
										window.location.href = "<%=basePath%>";
									}
								},1000)
							}else{
								imp_msg(data.msg)
							}
						}
					})	
					
				})
			})
		</script>
	</body>

</html>