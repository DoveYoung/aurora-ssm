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
		<title>注册</title>
		<%@ include file="../index/headLink.jsp"%>
		
	</head>
	<body id="aurorascm-register">
		<div class="register-main">
			<div class="header">
				<a href="<%=basePath%>" class="logo">
					<img src="../static/assets/img/logo.gif"/>
				</a>
				<a href="goLoginPage" class="to-login">
					已有账号?<i>去登录</i>
				</a>
			</div>
			<div class="register-box">
				<i class="register-box-t">注册账号</i>
				<div class="u-input">
					<label>账号：</label>
					<input id="inpt-count" class="i-inpt" placeholder="请输入您的邮箱作为登录账号"/>
					<div class="side-tip">
						
					</div>
				</div>
				<div class="u-input">
					<label>密码：</label>
					<input id="inpt-pw" class="i-inpt" type="password" placeholder="数字、字母、符号、至少两个组合，6-16个字符"/>
					<div class="side-tip"></div>
				</div>
				<div class="u-input">
					<label>确认密码：</label>
					<input id="inpt-pw2" class="i-inpt" type="password" placeholder="再次输入密码"/>
					<div class="side-tip"></div>
				</div>
				<div class="u-input">
					<label>手机号：</label>
					<input id="inpt-phone" class="i-inpt" placeholder="11位大陆手机号"/>
					<div class="side-tip"></div>
				</div>
				<div class="u-input">
					<label>短信验证码：</label>
					<input id="inpt-sms" class="i-inpt sms-code" placeholder="请输入短信验证码"/>
					<div class="getSms-code getSms-not">获取验证码</div>
					<!--<div class="side-tip"></div>-->
				</div>
				<div class="u-input">
					<label></label>
					<div class="b-btn unable-register" id="to-register">注&nbsp;&nbsp;册 </div>
					<div class="side-tip"></div>
				</div>
				<div class="u-input">
					<label></label>
					<div class="sign-tip">
						<input id="input-sign" type="checkbox" checked="checked" />
						<span>用户勾选即代表同意
							<a href="javascript:;" id="protocol">《北极光用户注册协议》</a>
						</span>
					</div>
				</div>
			</div>
			<span class="Record-number">CopyRight © 2017 杭州络驿电子商务有限公司 aurorascm.com 浙ICP备17030413号-2 All Rights Reserved</span>
			<div class="register-protocol">
				<h1>北极光供应链注册协议</h1>
				<div class="box">
					郑重提示：<br />
					       <i>北极光供应链将对用户所提供的资料进行严格的管理及保护，用户自愿注册个人信息，用户
					在注册时提供的所有信息，都是基于自愿，用户有权在任何时候拒绝提供这些信息。北极光供应
					链保证不对外公开或向第三方提供用户注册的个人资料，及用户在使用服务时存储的非公开内容，
					但下列情况除外：</i><br />
					1.事先获得您的明确授权。<br /> 
					2.根据有关的法律法规要求。 <br />
					3.按照相关司法机构或政府主管部门的要求。<br /> 
					4.只有透露您的个人资料，才能提供你所要求的产品和服务。 <br />
					5.因黑客行为或用户的保管疏忽导致帐号、密码遭他人非法使用。 <br />
					6.由于您将用户密码告知他人或与他人共享注册帐户，由此导致的任何个人资料泄露。 北极光供
					   应链承诺尊重您的隐私和您的个人信息安全，并且承诺尽可能地为您提供最佳的服务。比如，
					   北极光供应链会利用通过网站运作而收集到的这些信息，来制定您的个性化沟通方式和购物经
					   历、也可以更好地对您的客户服务调查做出反应、对您的订单和帐户信息及客户服务需求与您
					   进行沟通、就北极光供应链网站中的商品和活动与您进行沟通以及为了其他推广宣传目的、优
					   化管理、促销、调查等其他本网站的特别项目使用这些信息。
				</div>
				<b class="close-protocol">确认</b>
			</div>
		</div>
		
		<script>
			$(function(){
				//错误提示
				function cue_error(i,j){
					i.parent().find('.side-tip').removeClass('cue-right');
					i.parent().find('.side-tip').addClass('cue-error');
					i.parent().find('.side-tip').text(j);
					i.parent().find('.side-tip').addClass('animated shake')
					setTimeout(function(){i.parent().find('.side-tip').removeClass('animated shake')},1000);
					i.css('border-color','red');
					
				}
				//验证正确
				function cue_right(i){
					i.parent().find('.side-tip').removeClass('cue-error');
					i.parent().find('.side-tip').addClass('cue-right');
					i.parent().find('.side-tip').text('');
					i.css('border-color','#d9d9d9');
				}
				//验证信息是否齐全
				function check_allow(){
					if(cOunt && pW && pW2 && pHone && agree && code_m){
						$('#to-register').removeClass('unable-register');
					}else{
						$('#to-register').addClass('unable-register');
					}
				}
				//注册协议
				$('#protocol').on('click',function(){
					protocol_index = layer.open({
						type: 1,
						title: false,
						area: ['800px'],
						shade: 0.8,
						skin:'#fff',
						closeBtn: 1,
						scrollbar: false,//父页面禁止滚动条
						shadeClose: true,
						content: $('.register-protocol')
					})
				})
				$('.close-protocol').on('click',function(){
					layer.close(protocol_index);
				})
				var cOunt=false, pW = false, pW2= false, pHone = false, sMs = false,code_m = false,agree = true;
				//账号邮箱
				$('#inpt-count').on('blur',function(){
					var count = $(this).val();
					var reg = /^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/gi;
					if(reg.test(count)){
						cue_right($(this));
						cOunt = true;
					}else{
						cue_error($(this),'请输入正确的邮箱地址');
						cOunt = false;
					}
					check_allow()
				})
				//密码				
				$('#inpt-pw').on('blur',function(){
					var pw = $(this).val();
					var pw2 = $('#inpt-pw2').val()
					var reg = /^(?![0-9]+$)(?![a-zA-Z]+$)(?!([^(0-9a-zA-Z)]|[\(\)])+$)([^(0-9a-zA-Z)]|[\(\)]|[a-zA-Z]|[0-9]){6,}$/
					if(pw.length < 6){
						return cue_error($(this),'密码长度不能小于6位!');
					}
					if(pw.length > 16){
						return cue_error($(this),'密码长度不能大于16位!')
					}
					if(!reg.test(pw)){
						cue_error($(this),'密码至少是数字、字母、符号，任意两种组合以上！');
						pW = false;
					}else{
						cue_right($(this))
						if(pw === pw2){
							cue_right($('#inpt-pw2'));
							pW = true,pW2 = true;
						}else{
							cue_error($('#inpt-pw2'),'两次密码不一致')
							pW = true,pW2 = false;
						}	
					}
					check_allow()
				})
				//确认密码
				$('#inpt-pw2').on('blur',function(){
					var pw2 = $(this).val();
					var pw = $('#inpt-pw').val();
					if(!pW){
						cue_error($(this),'密码不符合规范');
						pW2 = false;
					}else if(pw2 !== pw){
						cue_error($(this),'两次密码不一致');
						pW2 = false;
					}else{
						cue_right($(this));
						pW2 = true;
					}
					check_allow()
				})
				//手机号
				$('#inpt-phone').on('blur',function(){
					var phone_n = $(this).val();
					var reg = /^1(3|4|5|7|8)[0-9]\d{8}$/
					if(!reg.test(phone_n)){
						cue_error($(this),'请输入正确的手机号');
						$('.getSms-code').addClass('getSms-not');
						pHone = false;
					}else{
						cue_right($(this));
						$('.getSms-code').removeClass('getSms-not');
						pHone = true;
					}
					check_allow()
				})
				//短信验证
				//获取验证码
				$('.getSms-code').on('click',function(){
					if(!$(this).hasClass('getSms-not')){
						var mobile = $('#inpt-phone').val();
						$.ajax({
							type:'POST',
							url:'../registerLogin/sendRSMS',
							data:{
								'mobile':mobile
							},
							dataType:'json',
							success:function(data){
								if(data.state == 'success'){
									mCode = data.mCode;
									code_m = true
									check_allow()
									$('.getSms-code').addClass('getSms-not');
									var i = 0;
									var mCodes = setInterval(function(){
										i++;
										$('.getSms-code').text((60-i)+'秒后重发')
										if(i == 60){
											clearInterval(mCodes)
											$('.getSms-code').text('获取验证码');
											$('.getSms-code').removeClass('getSms-not');
										}
									},1000);
								}else{
									layer.msg('网络异常，请稍后重试')
									$('#to-register').addClass('unable-register');
									code_m = false;
								}								
							}
						})
					}					
				})
				//手机验证码填入验证
				$('#inpt-sms').on('blur',function(){
					if(!pHone){
						cue_error($(this),'请先填写手机号');
						sMs = false;
					}
					if($(this).val() == ''){
						cue_error($(this),'请填写手机验证码');
						sMs = false;
					}else{
						cue_right($(this));
						sMs = true;
					}
					check_allow()
				})
				$('#input-sign').on('click',function(){
					if($(this).is(':checked')){
						$('#to-register').removeClass('unable-register');
						agree = true;
					}else{
						$('#to-register').addClass('unable-register');
						agree = false;
					}
					check_allow()
				})
				//去注册
				$('#to-register').on('click',function(){
					if(!cOunt){
						return layer.msg('请填写正确的邮箱格式')
					}
					if(!pW){
						return layer.msg('请填写正确的密码格式')
					}
					if(!pW2){
						return layer.msg('两次密码不一致')
					}
					if(!pHone){
						return layer.msg('请填写正确的手机号')
					}
					if(!sMs){
						return layer.msg('请填写完6位数手机验证码')
					}
					if(mCode != $('#inpt-sms').val()){
						return layer.msg('请填写正确的手机验证码')
					}
					if(!$('#input-sign').is(':checked')){
						return 
					}
					var customerEmail = $('#inpt-count').val();
					var customerPassword = $('#inpt-pw').val();
					var customerMobile = $('#inpt-phone').val();
					$.ajax({
						type:'POST',
						url:'../registerLogin/saveRegister',
						data:{
							'customerEmail':customerEmail,
							'customerPassword':customerPassword,
							'customerMobile':customerMobile
						},
						dataType:'json',
						success:function(data){
							if(data.state == 'success'){
								window.location.href="<%=basePath%>";
							}else{
								console.log(data)
								imp_msg(data.msg)
							}
						},
						error:function(XMLHttpRequest, textStatus, errorThrown){
//							console.log(XMLHttpRequest);
//							console.log(textStatus);
//							console.log(errorThrown);
							imp_msg('注册失败')
						}
					})
				})
			})
		</script>
	</body>
</html>
