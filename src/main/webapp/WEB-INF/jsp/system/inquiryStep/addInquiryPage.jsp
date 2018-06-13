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
		<title>询价</title>
		<%@ include file="../index/headLink.jsp"%>
		<script>
			window.onload=function(){
				var inquiry = ${inquiry}
				console.log(inquiry)
			}
		</script>
	</head>
	<body id="inquiryDetail">
		<div class="inquiry-main">
			<div class="header">
				<a href="<%=basePath%>" class="logo">
					<img src="../static/assets/img/logo.gif"/>
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
					<i style="margin-right:190px">客户填写货物信息</i>
					<i style="margin-right:190px">商城寻找合适货源</i>
					<i style="margin-right:170px">客户下达采购合同</i>
					<i style="margin-right: 0;">商城采购货物，达成交易</i>
				</div>
			</div>
			
			<select class="hidden" id="currency">
				<option value="">请选择结算货币类型</option>
				<c:forEach items="${currency}" var="c">
					<option value="${c.currency_sign}">${c.currency_name}</option>
				</c:forEach>
			</select>
			<div id="inquiry-box" style="width:920px;">
				<div class="title-l" id="goods-on">
					<b>询价</b>
					<i :class="{active : now == index}" v-for="(good,index) in goodList.inquiryGoodsList" @click="c_page(index)">{{index+1}}</i>
				</div>
				<div id="inquiry-good-box">
					<div class="inquiry-btn continue-inquiry" @click="add_page()">添加询价商品</div>
					<div class="inquiry-btn delete-active-page" @click="delete_page()">删除当前页</div>
					<div class="inquiry-btn" id="end-to-submit" @click="save_iq()">结束并提交</div>
					<div class="inquiry-good" :class="{'inquiry-good-active' : now == index}" v-for="(good,index) in goodList.inquiryGoodsList">
						<div class="info-box">
							<i><b>*</b>商品品牌：</i>
							<input class="info-inquiry" v-model="good.goodsBrand"/>
						</div>
						<!--/^[0-9]*$/-->
						<div class="info-box">
							<i><b>*</b>采购数量：</i>
							<input type="text" min="1" class="info-inquiry" v-model="good.buyNum" onKeyUp="only_num(this)" onafterpaste="only_num(this)"/>
						</div>
						<div class="info-box">
							<i><b>*</b>商品名称：</i>
							<input class="info-inquiry" v-model="good.goodsName"/>
						</div>
						<div class="info-box">
							<i>商品规格：</i>
							<input class="info-inquiry" v-model="good.goodsNorm"/>
						</div>
						<div class="info-box">
							<i><b>*</b>交货国家：</i>
							<input class="info-inquiry" v-model="good.deliverCountry"/>
						</div>
						<div class="info-box">
							<i>商品条码：</i>
							<input type="number" class="info-inquiry" v-model="good.goodsCode"/>
						</div>
						<div class="info-box">
							<i><b>*</b>物流方式：</i>
							<select class="info-inquiry" v-model="good.logisticsType">
								<option value="">请选择物流方式</option>
								<option value="1">海运</option>
								<option value="2">陆运</option>
								<option value="3">空运</option>
							</select>
						</div>
						<div class="info-box">
							<i><b>*</b>结算货币：</i>
							<select class="info-inquiry currency-synchro" v-model="good.currencySign" onchange="currency_synchro(this)">
								<option value="">请选择结算货币类型</option>
								<c:forEach items="${currency}" var="c">
									<option value="${c.currency_sign}">${c.currency_name}</option>
								</c:forEach>
							</select>
						</div><div class="info-box">
							<i><b>*</b>交货地点：</i>
							<input class="info-inquiry" v-model="good.deliverCity" placeholder="请输入您的提货城市"/>
						</div>
						<div class="info-box">
							<i><b>*</b>联系电话：</i>
							<input class="info-inquiry" v-model="goodList.customerMobile" placeholder="请选择用电话接收询价结果"/>
						</div>
						<div class="info-box">
							<i><b>*</b>贸易方式：</i>
							<select class="info-inquiry" v-model="good.tradeType">
								<option value="">请选择贸易方式</option>
								<option value="1">CIF 成本+保险+运费不包含进口清关手续</option>
								<option value="2">FCA 把货物交给卖家指定承运人，并办理出口清关手续</option>
								<option value="3">EXW 在本商城指定海外仓提货，本商城不承担除货款外的任何费用</option>
								<option value="4">FOB 把货物装至指定港口或船只，不含国际运费和报销费</option>
							</select>
						</div>
						<div class="info-box">
							<i><b>*</b>联系邮箱：</i>
							<input class="info-inquiry" v-model="goodList.customerEmail"/>
						</div>
						<div class="info-box info-box-b">
							<i>商品图片：</i>
							<div class="uploadImg" style="float:none">
								<span class="delete-img glyphicon glyphicon-remove" :class="{'delete-img-active':good.goodsMap}" @click="delete_img(index)"></span>
								<div class="preview" id="brandMap" onclick="click_img(this)">
									<img class="preview-img" :src="good.goodsMap" v-show="good.goodsMap">
								</div>
								<input id="" type="file" name="file" class="hidden" accept="image/jpg,image/jpeg,image/png,image/gif" @change="previewImage($event,index)" />
							</div>
							<p>有货物图片我们可以更快，更准确的帮您找到货源哦~</p>
							<input type="hidden" class="info-inquiry"/>
						</div>
						<div class="info-box info-box-b">
							<i>备注：</i>
							<input class="info-inquiry" v-model="good.customerRemark"/>
						</div>
					</div>
				</div>
				<div class="inquiry-subS">
					<img src="../static/assets/img/reset_success.png"/>
					<div>
						<h1>询价提交成功</h1>
						<h3>您可以在个人中心查看询价结果</h3>
						<h5><i id="times">5</i>秒后将跳转到个人中心，如不想等待，<a href="<%=basePath%>customerInquiry/inquiryList">立即跳转</a></h5>
					</div>
				</div>
			</div>
			<span class="Record-number">CopyRight © 2017 杭州络驿电子商务有限公司 aurorascm.com 浙ICP备17030413号-2 All Rights Reserved</span>
			
		</div>
		
		<script src="../static/assets/js/common.js" language="javascript"></script>
		<script>
			window.onload = function(){
				var goodList = ${inquiry};
				if(!goodList.inquiryGoodsList[0].currencySign){
					goodList.inquiryGoodsList[0].currencySign = ''
				}
				if(!goodList.inquiryGoodsList[0].logisticsType){
					goodList.inquiryGoodsList[0].logisticsType = ''
				}
				if(!goodList.inquiryGoodsList[0].tradeType){
					goodList.inquiryGoodsList[0].tradeType = ''
				}
				console.log(goodList)
				iq = new Vue({
					el:'#inquiry-box',
					data:{
						goodList:goodList,
						now:0,
					},
					methods:{
						c_page:function(i){
							this.now = i
						},
						previewImage:function(ev,i){
							if(ev.target.files && ev.target.files[0]) {
								if(ev.target.files[0].size>3*1024*1024){
					                layer.msg("图片不能大于3M");
									return;
								}
							   	var formData = new FormData();
					            formData.append('file', ev.target.files[0]);
					            jQuery.ajax({
					                url: '../upload/uploadImage',
					                type: 'POST',
					                cache: false,
					                data: formData,
					                processData: false,
					                contentType: false,
									dataType:'json',
					                success: function (data) {
					                	console.log(data)
					                	if(data.state == 'success'){
					                		//图片路径
					                		iq.goodList.inquiryGoodsList[i].goodsMap = data.url
					                	}else if(data.state == 'failed'){
											layer.msg(data.msg)
										}else if(data.state == 'error'){
											layer.msg(data.msg)
										}
					                }
					            })
						    }
						},
						delete_img:function(i){
							iq.goodList.inquiryGoodsList[i].goodsMap = ''
							layer.msg('删除成功！');
						},
						delete_page:function(){//删除当前页
							if(this.goodList.inquiryGoodsList.length <= 1){
								return layer.msg('商品询价总页数不能小于1')
							}
							this.goodList.inquiryGoodsList.splice(this.now,1);
							this.now = 0;
						},
						add_page:function(){//添加询价商品
							if(this.goodList.inquiryGoodsList.length >=8){
								return layer.msg('商品询价总页数不能大于8')
							}
							var i_l = $('#goods-on i').length+1;
							if(i_l > 1){
								for(var j = 0;j < 14;j++){
									if($('.inquiry-good').eq(i_l-2).find('.info-box').eq(j).find("i:has(b)").length){
										if($('.inquiry-good').eq(i_l-2).find('.info-inquiry').eq(j).val() == ''){
											var str = $('.inquiry-good').eq(i_l-2).find('.info-box').eq(j).find("i").text();
											str = str.substring(1,str.length-1);
											imp_msg('请完善第'+(i_l-1)+'个商品的'+str+'信息')
											//追踪未完善信息的商品 
											$('#goods-on i').removeClass('active')
											$('.inquiry-good').removeClass('inquiry-good-active');
											$('#goods-on i').eq(i_l-2).addClass('active')
											$('.inquiry-good').eq(i_l-2).addClass('inquiry-good-active');
											return false
										}
									}
								}
							}
							var obj = {};
							obj.currencySign = '';
							obj.logisticsType = '';
							obj.tradeType = '';
							this.goodList.inquiryGoodsList.push(obj);
							this.now = this.goodList.inquiryGoodsList.length - 1
						},
						save_iq:function(){
							
							var info_num = this.goodList.inquiryGoodsList.length;
							//必填项 非空验证
							for(var i = 0;i < info_num;i++){
								for(var j = 0;j < 14;j++){
									if($('.inquiry-good').eq(i).find('.info-box').eq(j).find("i:has(b)").length){
										if($('.inquiry-good').eq(i).find('.info-inquiry').eq(j).val() == ''){
											var str = $('.inquiry-good').eq(i).find('.info-box').eq(j).find("i").text();
											str = str.substring(1,str.length-1);
											imp_msg('请完善第'+(i+1)+'个商品的'+str+'信息')
											//追踪未完善信息的商品 
											this.now = i;
											return false
										}
									}
								}
								this.goodList.inquiryGoodsList[i].currencyName = $('.currency-synchro').eq(i).find('option:selected').text();
								this.goodList.inquiryGoodsList[i].buyNum = parseInt($('.info-buyNum').eq(i).val())
							}
							console.log(this.goodList);
							if(this.goodList.inquiryID){
								this.goodList.sourceID = this.goodList.inquiryID
							}else{
								this.goodList.sourceID = null;
							}
							var inquiryManageJson = JSON.stringify(this.goodList);
							$.ajax({
							    type:'post',
							    url:'saveInquiry',
							    data:{
							    	inquiryManageJson:inquiryManageJson
							    },
							    dataType:'json',
							    success:function(data){
//							    	console.log(data)
							        if(data.state == 'success'){
							            layer.msg('询价成功')
							            layer.open({
											type: 1,
											title: false,
											closeBtn: 0,
											area: '600px',
		//									skin: 'layui-layer-nobg', //没有背景色
											shadeClose: false,
											content: $('.inquiry-subS')
										})
							            var i = 5;
							            var goInqList = setInterval(function(){
							            	i--;
							            	$('#times').html(i);
							            	if(i == 0){
							            		clearInterval(goInqList)
												this.location = "inquiryList"
							            	}
							            },1000)
							        }else if(data.state == 'error'){
							            layer.msg(data.msg)
							        }else if(data.state == 'failed'){
							            layer.msg(data.msg)
							        }
							    }
							});
							
						}
					}
				})
			}
			//数量-正整数
			function only_num(i){
				$(i).val($(i).val().replace(/\D/g,''))
			}
			//同步货币
			function currency_synchro(i){
				console.log($(i).val())
				$('.currency-synchro').val($(i).val())
			}
			function click_img(i){
				$(i).siblings('input[type="file"]').click()
			}
		</script>
	</body>
</html>