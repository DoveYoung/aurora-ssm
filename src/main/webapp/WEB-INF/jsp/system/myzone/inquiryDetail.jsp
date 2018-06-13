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
		<title>询价单</title>
		<%@ include file="../index/headLink.jsp"%>
		<style>
			.blue,.blue a{
				color:#3392DF !important;
			}
			.disable{
				pointer-events: none !important;
			    cursor: not-allowed !important;
			    opacity: 0.6 !important;
			}
			.col4 .i-box{
			    display: table-cell;
    			vertical-align: middle;
        		width:110px !important;
        		height: 110px !important;
				line-height:normal !important;
				text-align: center;
			}
		</style>
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
					<b class="step-b step-b-over"></b>
					<i class="step-i step-active">2</i>
					<b class="step-b step-b-active"></b>
					<i class="step-i">3</i>
					<b class="step-b"></b>
					<i class="step-i">4</i>
				</div>
				<div class="step-font">
					<i>客户填写货物信息</i>
					<i style="margin-right: 211px">商城寻找合适货源</i>
					<i style="margin-right: 172px">客户下达采购合同</i>
					<i style="margin-right: 0;">商城采购货物，达成交易</i>
				</div>
			</div>
			<div id="inquiry-box">
				<h1 class="title">询价单</h1>
				<div class="info">
					<i>* </i>公司名称
					<input type="text" id="company" placeholder="请填写您公司的详细名称，用于签订合同"/>
					<i>* </i>详细收货地址
					<input type="text" id="address" placeholder="请填写您收货的详细地址"/>
				</div>
				<!--商品信息-->
				<div class="goods-info">
					<div class="good-head">
						<div class="col col0"></div>
						<div class="col col2">商品详情</div>
						<div class="col col3">商品条码</div>
						<div class="col col4">购买数量</div>
						<div class="col col4">交货方式</div>
						<div class="col col4">交货地点</div>
						<div class="col col4">货物体积</div>
						<div class="col col4">备货期</div>
						<div class="col col3">货款(总价)</div>
					</div>
					<div class="good-list" :class="{disable : good.inquiryGoodsState == 4,blue : good.updateNum != good.buyNum}" v-for="(good,index) in inquiryList.inquiryGoodsList">
						<div class="col col0">
							<input type="checkbox" @click="c_good()" name="good_c" :data-index="index" v-if="good.inquiryGoodsState != 4"/>
							<input type="checkbox" v-if="good.inquiryGoodsState == 4"/>
						</div>
						<div class="col col2">
							<a class="good-img"href="javascript:;">
								<img :src="good.goodsMap" alt="无图"/>
							</a>
							<a class="i-box" href="javascript:;"><b>{{good.goodsName}}</b></a>
						</div>
						<div class="col col3">{{good.goodsCode}}</div>
						<div class="col col4">{{good.updateNum}}</div>
						<div class="col col4">
							<i v-if="good.tradeType == '1'">CIF</i>
							<i v-if="good.tradeType == '2'">FCA</i>
							<i v-if="good.tradeType == '3'">EXW</i>
							<i v-if="good.tradeType == '4'">FOB</i>
						</div>
						<div class="col col4">
							<span class="i-box"><b>{{good.deliverCity}}</b></span>
						</div>
						<div class="col col4">{{good.volume}}m³</div>
						<div class="col col4">{{good.readyTime}}天</div>
						<div class="col col3">{{good.currencySign}}{{good.supplyPrice}}</div>
					</div>
					<div class="all">
						<input type="checkbox" id="check_all" @click="c_all()"/>
						<i class="pointer all-i" @click="c_bind($event)">全选</i>
						(标蓝商品因为您询价的数量过少，无法发货，所以我们推荐了该商品的最低起订量，如果您对此有异议，请<a target="_blank" href="<%=basePath%>footer/contactUs">联系客服</a>详细咨询)
					</div>
					<div class="info-total">
						<div class="notice">
							<div class="notice-list">
								<b>询价须知：</b>
								<i>1.点击我要采购后会有客服来和您核对信息，并制定合同，交易已合同内容为准</i>
								<i>2.合同内容需要您公司的详细名称与详细收货地址</i>
								<i>3.您对合同内容有异议，可以随时联系客服咨询</i>
							</div>
							<div class="notice-list">
								<b>物流须知：</b>
								<i>1.空运货物以托盘为运费计算单位，一托盘体积为8m³，体积不足一托盘按一托盘计算</i>
								<i>2.陆运货物以托盘为运费计算单位，一托盘体积为8m³，体积不足一托盘按一托盘计算</i>
								<i>3.海运货物以柜为运费计算单位，一柜为20托盘，体积不足一柜按一柜计算</i>
							</div>
						</div>
						<div class="price">
							<h5><i>商品价格总计：</i><b>{{inquiryList.inquiryGoodsList[0].currencySign}}<b class="total_g">{{gPay}}</b></b></h5>
							<h5><i>运费：</i><b>{{inquiryList.inquiryGoodsList[0].currencySign}}<b class="total_post">{{postage}}</b></b></h5>
							<h5><i>应付金额总计：</i><b class="pay_money">{{inquiryList.inquiryGoodsList[0].currencySign}}<b class="total_pay">{{totalPay}}</b></b></h5>					
							<b class="purchase" :class="{'purchase-active' : inquiryGoodsID != ''}" @click="saveInquiry(inquiryList.inquiryID)">我要采购</b>
						</div>
					</div>
				</div>
			</div>
			<span class="Record-number">CopyRight © 2017 杭州络驿电子商务有限公司 aurorascm.com 浙ICP备17030413号-2 All Rights Reserved</span>
		</div>
		<script src="../static/assets/js/common.js" language="javascript"></script>
		<script>
			var inquiryList = ${inquiry}
			console.log(inquiryList)
			iq = new Vue({
				el:'#inquiry-box',
				data:{
					inquiryList:inquiryList,
					gPay:'0.00',
					postage:'0.00',
					totalPay:'0.00',
					inquiryGoodsIDs:''
				},
				methods:{
					c_good:function(){
						var j = 0;
						$("input[name='good_c']").each(function(){
							if($(this).is(':checked')){
								j++
							}
						})
						if(j == $("input[name='good_c']").length){
							$('#check_all').prop("checked", true)
						}else{
							$('#check_all').prop("checked", false)
						}
						this.price_show()
					},
					c_all:function(){
//						console.log($('#check_all').is(':checked'))
						if($('#check_all').is(':checked')){
							
							$("input[name='good_c']").prop("checked", true)
						}else{
							$("input[name='good_c']").prop("checked", false)
						}
						this.price_show()
					},
					c_bind:function(ev){
						$(ev.target).siblings('#check_all').click();
					},
					price_show:function(){
						var gPay = postage = totalPay = 0,
							num = 0,arr = [];
						$("input[name='good_c']").each(function(){
							if($(this).is(':checked')){
								var i = parseInt($(this).data('index'));
								gPay += iq.inquiryList.inquiryGoodsList[i].supplyPrice;
								postage += iq.inquiryList.inquiryGoodsList[i].postage;
								num ++
								arr.push(iq.inquiryList.inquiryGoodsList[i].inquiryGoodsID)
							}
						})
						this.gPay = gPay;
						this.postage = postage;
						this.totalPay = this.gPay + this.postage;
						if(num){
							$('.purchase').addClass('purchase-active')
							this.inquiryGoodsIDs = arr.join(',');
						}else{
							this.inquiryGoodsIDs = '';
						}
					},
					saveInquiry:function(i){
						if($('#company').val() == ''){
							return layer.msg('请填写公司名称');
						}
						if($('#address').val() == ''){
							return layer.msg('请填写详细收货地址');
						}
						var company = $('#company').val(),
							address = $('#address').val(),
							inquiryID = i,
							inquiryGoodsIDs = this.inquiryGoodsIDs;
							console.log(inquiryGoodsIDs)
						$.ajax({
						    type:'post',
						    url:'../customerContract/createContract',
						    data:{
						    	'company':company,
								'address':address,
								'inquiryGoodsIDs':inquiryGoodsIDs,
								'inquiryID':inquiryID
						    },
						    dataType:'json',
						    success:function(data){
						    	console.log(data)
						        if(data.state == 'success'){
						           	layer.msg('生成合同采购单成功，即将前往个人中心--合同页面');
									setTimeout(function(){
										self.location = '../customerContract/contractList'
	//									 window.location.reload();
									},500)
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
		</script>
		<script>
			
			$(function(){
				$('.purchaswse').on('click',function(){
					
					var company = $('#company').val();
					var address = $('#address').val();
					var inquirySID = $(this).attr('inquirySID');
					var inquiryID = '';
					$($('.good-list-active')).each(function(){
						inquiryID += ',' + $(this).attr('inquiryID')
					})
					inquiryID = inquiryID.substring(1)
					$.ajax({
						type:"post",
						url:"../customerContract/createContract",
						data:{
							'company':company,
							'address':address,
							'inquirySID':inquirySID,
							'inquiryID':inquiryID
						},
						dataType:'json',
						success:function(data){
							if(data.result == 'success'){
								layer.msg('生成合同采购单成功，即将前往个人中心--合同页面');
								setTimeout(function(){
									self.location = '../customerContract/contractList'
//									 window.location.reload();
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
	</body>
</html>
