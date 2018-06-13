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
		<title>个人中心--我的资料</title>
		<link href="../static/assets/plugin/bootstrap-data/bootstrap-datetimepicker.min.css" rel="stylesheet">
		<%@ include file="../index/headLink.jsp"%>
		<script src="../static/assets/plugin/bootstrap-data/bootstrap-datetimepicker.min.js" language="javascript"></script>
		<script src="../static/assets/plugin/bootstrap-data/bootstrap-datetimepicker.fr.js" language="javascript"></script>
		<script src="../static/assets/plugin/bootstrap-data/bootstrap-datetimepicker.zh-CN.js" language="javascript"></script>
		
	</head>

	<body id="product-body">
		
		<!--header-->
		<div id="search-header">			
			<%@ include file="../index/topLogin.jsp"%>			
			<%@ include file="../index/headerSearch.jsp"%>
		</div>
		<%@ include file="../index/shipType.jsp"%>
		
		<div id="myZone">
			<%@ include file="../myzone/myzoneMenuList.jsp"%>
			<div id="zone-content">
				<h2 id="order_c" style=" border-bottom: 1px solid #e7e7e7;">我的资料</h2>
				<div class="info-tips">您的资料信息请尽量填写完整。当您的订单信息有误、出现售后等问题，或者本站有优惠活动时，方便客户人员及时联系到您。您的资料信息保证不被外泄，仅供本站沟通联系使用。</div>
				<div class="info-box">
					<i><b>* </b>姓名</i><input id="c_name" value="${customer.customer_name}"/>
				</div>
				<div class="info-box">
					<i>生日</i><input  id="birthday" class="datetimepicker form-control" value="${customer.customer_birthday}" type="text" readonly />
				</div>
				<div class="info-box">
					<i><b>* </b>性别</i>
					<select id="sex" >
						<option value="" <c:if test="${customer.customer_sex == null}">selected="selected"</c:if>>请选择性别</option>
						<option value="1" <c:if test="${customer.customer_sex == '1'}">selected="selected"</c:if>>男</option>
						<option value="2" <c:if test="${customer.customer_sex == '2'}">selected="selected"</c:if>>女</option>
					</select>
				</div>
				<div class="info-box">
					<i><b>* </b>手机</i><input id="mobile" value="${customer.customer_mobile}"/>
					<input type="checkbox" id="smsPush" <c:if test="${customer.sms_push == '1'}">checked="checked" value="1"</c:if> <c:if test="${customer.sms_push != '1'}">value="2"</c:if>/>
					<b <c:if test="${customer.sms_push == '1'}">class="b-active"</c:if>>已接受推送</b>
					<b <c:if test="${customer.sms_push != '1'}">class="b-active"</c:if>>接受推送</b>
				</div>
				<b class="push-tip">接受推送后，我们会以短信的形式给您推送我们的最新优惠货源</b>
				<div class="info-box">
					<i><b>* </b>登录邮箱</i><input id="email" value="${customer.customer_email}"/>
					<input type="checkbox" id="emailPush" <c:if test="${customer.email_push == '1'}">checked="checked" value="1"</c:if> <c:if test="${customer.email_push != '1'}">value="2"</c:if>/>
					<b <c:if test="${customer.email_push == '1'}">class="b-active"</c:if>>已接受推送</b>
					<b <c:if test="${customer.email_push != '1'}">class="b-active"</c:if>>接受推送</b>
				</div>
				<b class="push-tip">接受推送后，我们会以邮件的形式给您推送我们的最新优惠货源</b>
				<div class="info-box">
					<div class="pointer save">保存修改</div>
				</div>
			</div>
		</div>
		<script>
			$(function(){
				//时间选择初始化
				$('#birthday').datetimepicker({
					startView: 'year',
					minView:2,
					maxView:'decade',
				  	format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式 
				    language: 'zh-CN', //汉化 
				    autoclose:true, //选择日期后自动关闭 
				    todayBtn: false,//显示今日按钮
				    forceParse:false
				})
				//推送选择
				$('#smsPush').on('change',function(){
					var sp = $(this).siblings('b')
					sp.removeClass('b-active')
					if($(this).is(':checked')){
						sp.eq(0).addClass('b-active')
						$(this).val(1)
					}else{
						sp.eq(1).addClass('b-active')
						$(this).val(2)
					}
				})
				$('#emailPush').on('change',function(){
					var sp = $(this).siblings('b')
					sp.removeClass('b-active')
					if($(this).is(':checked')){
						sp.eq(0).addClass('b-active')
						$(this).val(1)
					}else{
						sp.eq(1).addClass('b-active')
						$(this).val(2)
					}
				})
				//保存修改
				$('.save').on('click',function(){
					if($('#c_name').val()===''){
						return layer.msg('请填写姓名')
					}
					if($('#mobile').val()===''){
						return layer.msg('请填写手机号')
					}
					if($('#sex').val()===''){
						return layer.msg('请选择性别')
					}
					if($('#email').val()===''){
						return layer.msg('请填写邮箱')
					}
					var info = {};
					info.name = $('#c_name').val();
					info.mobile = $('#mobile').val();
					info.email = $('#email').val();
					info.birthday = $('#birthday').val();
					info.sex = $('#sex').val();
					info.smsPush = $('#smsPush').val();
					info.emailPush = $('#emailPush').val();
					$.ajax({
						type:"post",
						url:"updateMyInfo",
						data:info,
						dataType:'json',
						success:function(data){
							if(data.result == 'success'){
								layer.msg('保存成功');
								setTimeout(function(){
									 window.location.reload();
								},500)
							}else if(data.result == 'error'){
								layer.msg(data.msg)
							}else if(data.result == 'failed'){
								layer.msg(data.msg)
							}
						}
					});
				})
			})
		</script>
		<!--页脚-->
		<%@ include file="../index/footer.jsp"%>
		<!--右侧固定-->
		<%@ include file="../index/rightFixed.jsp"%>
	</body>	
</html>