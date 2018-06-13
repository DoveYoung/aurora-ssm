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
		<title>购物车-北极光供应链</title>
		<%@ include file="../index/headLink.jsp"%>
		<script>
			var hLCart = ${hLCartFPMath},
				bLCart = ${bLCartFPMath},
				gLCart = ${gLCartFPMath};
			var deposit_TF = false;	
			console.log(${bLCartFPMath})
		</script>
	</head>

	<body id="carBox-body">
		<!--header-->
		<%@ include file="../index/topLogin.jsp"%>
		<div class="carBox">
			<div id="good-category">
				<a href="javascript:;" class="active">全款商品</a>
				<a href="<%=basePath%>cartPage/getCartDPGoods">定金商品</a>
			</div>
			<div class="car-head">
				<div class="col col0">
					<input type="checkbox" id="checkAll" @click="check_all($event)" v-model="checkAll" style="margin-left: 15px;margin-right: 10px;"/>
					<i class="checkAll-box" @click="checkAll_box($event)" style="cursor: pointer;">全选</i>
				</div>
				<div class="col col2">商品信息</div>
				<div class="col col3">数量</div>
				<div class="col col4">单价（元）</div>
				<div class="col col5">小计（元）</div>
				<div class="col col6">操作</div>
			</div>
			<div class="empty-cart" v-cloak v-if="hLCart.length <= 0 && bLCart.length <= 0 && gLCart.length <= 0">
				<img src="../static/assets/img/empty_cart.png"/>
				<i>购物车空空的哦~，去<a href="<%=basePath%>" target="_blank">逛逛吧＞</a></i>
			</div>
			<!--海外直邮-->
			
			<div id="German" class="mail" v-cloak v-if="hLCart.length">
				<input @click="type_check($event,0)" type="checkbox" name="hL" id="hL" class="type-cke"/>
				<i>海外直邮</i>
				<b v-if="hlTotal.payM > 20000">该笔订单超过¥20000，不能通过海关发货，请选择分批发货或暂时存入微仓</b>
			</div>
			<div class="goods" v-cloak v-if="hLCart.length">
				<ul class="goodsBox" id="German-box">
					<li v-for="(good,index) in hLCart" class="gooditm" :class="{'no-gooditm' : good.goodsStock == 0 || good.goodsState != 4,'stockOut' : good.goodsStock > 0 && good.goodsStock < good.buyNum}" cartID="${good.cartID}" goodsID="${good.goodsID}" >
						<div class="col col0">
							<input @click="good_check($event,0)" v-if="good.goodsStock > 0 && good.goodsState == 4" type="checkbox" class="u-cke hl-cke" />
							<input v-else type="checkbox" class="dis-cke" />
						</div>
						<div class="col col2">
							<a class="goodImg" target="_blank" :href="'<%=basePath%>detailPage/goGoodsdetail?goodsID='+good.goodsID" :title="good.goodsName">
								<img :src="good.mainMap" />
							</a>
							<span>
								<a target="_blank" :href="'<%=basePath%>detailPage/goGoodsdetail?goodsID='+good.goodsID" :title="good.goodsName">
									{{good.goodsName}}
								</a>
							</span>
						</div>
						<div class="col col3">
							<div class="goodsNum">
								<a href="javascript:;" class="minus" @click="minus('hLCart',index,$event)" :class="{'z-dis' : good.rnum1 == good.buyNum}">-</a>									
								<input class="ipt" :min="good.rnum1" @change="change_num('hLCart',index,$event)" :value="good.buyNum" max="good.exw == '1' ? good.rnum2 : good.rnum3"/>
								<a href="javascript:;" class="add" @click="add('hLCart',index,$event)" :class="{'z-dis' : (good.exw == 1 && good.rnum2 <= good.buyNum) || (good.exw != 1 && good.rnum3 <= good.buyNum) || (good.buyNum >= good.goodsStock)}">+</a>
							</div>
							<i>
								{{good.goodsState == 4 ? (good.goodsStock <= 0 ? '无货': (good.goodsStock <= good.buyNum || good.goodsStock <= good.rnum1) ? ('库存仅剩'+good.goodsStock+'件') : '') : '商品已下架'}}
								
							</i>
						</div>
						<div class="col col4">{{good.goodsPrice.toFixed(2)}}</div>
						<div class="col col5">
							{{good.uMoney.toFixed(2)}}
							<div class="tax">
								<div>
									<em>
										<a href="javascript:;">?</a>
										<div>
											<div class="tag-top">  
											    税费解释
											</div>
										</div>
									</em>
									含税费：{{good.tax.toFixed(2)}}
								</div>
							</div>
						</div>
						<div class="col col6">
							<i class="i-box">
								<b class="pointer deleteThis" @click="delete_one(0,index,good.cartID)">删除</b>
							</i>
						</div>
					</li>
					<li class="type-total">应付总计：{{hlTotal.payM.toFixed(2)}} <i></i>含税费：{{hlTotal.tax.toFixed(2)}}</li>
				</ul>
			</div>
			<!--保税仓直邮-->
			
			<div id="wareHouse" class="mail" v-cloak v-if="bLCart.length">
				<input @click="type_check($event,1)" type="checkbox" name="bL" id="bL" class="type-cke"/>
				<i>保税仓代发</i>
				<b v-if="blTotal.payM > 20000">该笔订单超过¥20000，不能通过海关发货，请选择分批发货或暂时存入微仓</b>
			</div>
			<div class="goods" v-cloak v-if="bLCart.length">
				<ul class="goodsBox" id="wareHouse-box">
					<li v-for="(good,index) in bLCart" class="gooditm" :class="{'no-gooditm' : good.goodsStock == 0 || good.goodsState != 4,'stockOut' : good.goodsStock > 0 && good.goodsStock < good.buyNum}" cartID="${good.cartID}" goodsID="${good.goodsID}" >
						<div class="col col0">
							<input @click="good_check($event,1)" v-if="good.goodsStock > 0 && good.goodsState == 4" type="checkbox" class="u-cke bl-cke" />
							<input v-else  type="checkbox" class="dis-cke" />
						</div>
						<div class="col col2">
							<a class="goodImg" target="_blank" :href="'<%=basePath%>detailPage/goGoodsdetail?goodsID='+good.goodsID" :title="good.goodsName">
								<img :src="good.mainMap" />
							</a>
							<span>
								<a target="_blank" :href="'<%=basePath%>detailPage/goGoodsdetail?goodsID='+good.goodsID" :title="good.goodsName">
									{{good.goodsName}}
								</a>
							</span>
						</div>
						<div class="col col3">
							<div class="goodsNum">
								<a href="javascript:;" class="minus" @click="minus('bLCart',index,$event)" :class="{'z-dis' : good.rnum1 >= good.buyNum}">-</a>									
								<input class="ipt" :min="good.rnum1" @change="change_num('bLCart',index,$event)" :value="good.buyNum" max="good.exw == '1' ? good.rnum2 : good.rnum3"/>
								<a href="javascript:;" class="add" @click="add('bLCart',index,$event)" :class="{'z-dis' : (good.exw == 1 && good.rnum2 <= good.buyNum) || (good.exw != 1 && good.rnum3 <= good.buyNum) || (good.buyNum >= good.goodsStock)}">+</a>
							</div>
							<i>
								{{good.goodsState == 4 ? (good.goodsStock <= 0 ? '无货': (good.goodsStock <= good.buyNum || good.goodsStock <= good.rnum1) ? ('库存仅剩'+good.goodsStock+'件') : '') : '商品已下架'}}
							</i>
						</div>
						<div class="col col4">{{good.goodsPrice.toFixed(2)}}</div>
						<div class="col col5">{{good.uMoney.toFixed(2)}}
							<div class="tax">
								<div>
									<em>
										<a href="javascript:;">?</a>
										<div>
											<div class="tag-top">  
											    税费解释
											</div>
										</div>
									</em>
									含税费：{{good.tax.toFixed(2)}}
								</div>
							</div>
						</div>
						<div class="col col6">
							<i class="i-box">
								<b class="pointer deleteThis" @click="delete_one(1,index,good.cartID)">删除</b>
							</i>
						</div>
					</li>
					<li class="type-total">应付总计：{{blTotal.payM.toFixed(2)}} <i></i>含税费：{{blTotal.tax.toFixed(2)}}</li>
				</ul>
			</div>
			<!--国内现货-->
			<div id="theSpot" class="mail" v-cloak v-if="gLCart.length">
				<input  @click="type_check($event,2)" type="checkbox" name="gL" id="gL" class="type-cke"/>
				<i>一般贸易</i>
			</div>
			<div class="goods" v-cloak v-if="gLCart.length">
				<ul class="goodsBox" id="theSpot-box">
					<li v-for="(good,index) in gLCart" class="gooditm" :class="{'no-gooditm' : good.goodsStock == 0 || good.goodsState != 4,'stockOut' : good.goodsStock > 0 && good.goodsStock < good.buyNum}" cartID="${good.cartID}" goodsID="${good.goodsID}" >
						<div class="col col0">
							<input @click="good_check($event,2)" v-if="good.goodsStock > 0 && good.goodsState == 4" type="checkbox" class="u-cke gl-cke" />
							<input v-else  type="checkbox" class="dis-cke" />
						</div>
						<div class="col col2">
							<a class="goodImg" target="_blank" :href="'<%=basePath%>detailPage/goGoodsdetail?goodsID='+good.goodsID" :title="good.goodsName">
								<img :src="good.mainMap" />
							</a>
							<span>
								<a target="_blank" :href="'<%=basePath%>detailPage/goGoodsdetail?goodsID='+good.goodsID" :title="good.goodsName">
									{{good.goodsName}}
								</a>
							</span>
						</div>
						<div class="col col3">
							<div class="goodsNum">
								<a href="javascript:;" class="minus" @click="minus('gLCart',index,$event)" :class="{'z-dis' : good.rnum1 == good.buyNum}">-</a>									
								<input class="ipt" :min="good.rnum1" @change="change_num('gLCart',index,$event)" :value="good.buyNum" max="good.exw == '1' ? good.rnum2 : good.rnum3"/>
								<a href="javascript:;" class="add" @click="add('gLCart',index,$event)" :class="{'z-dis' : (good.exw == 1 && good.rnum2 <= good.buyNum) || (good.exw != 1 && good.rnum3 <= good.buyNum) || (good.buyNum >= good.goodsStock)}">+</a>
							</div>
							<i>
								{{good.goodsState == 4 ? (good.goodsStock <= 0 ? '无货': (good.goodsStock <= good.buyNum || good.goodsStock <= good.rnum1) ? ('库存仅剩'+good.goodsStock+'件') : '') : '商品已下架'}}
								
							</i>
						</div>
						<div class="col col4">{{good.goodsPrice.toFixed(2)}}</div>
						<div class="col col5">
							{{good.uMoney.toFixed(2)}}
							<div class="tax">
								<div>
									<em>
										<a href="javascript:;">?</a>
										<div>
											<div class="tag-top">  
												税费解释
											</div>
										</div>
									</em>
									含税费：{{good.tax.toFixed(2)}}
								</div>
							</div>
						</div>
						<div class="col col6">
							<i class="i-box">
								<b class="pointer deleteThis" @click="delete_one(2,index,good.cartID)">删除</b>
							</i>
						</div>
					</li>
					<li class="type-total">应付总计：{{glTotal.payM.toFixed(2)}} <i></i>含税费：{{glTotal.tax.toFixed(2)}}</li>
				</ul>
			</div>
			<!--结算-->
			<div class="totalbox" v-if="hLCart.length > 0 || bLCart.length > 0 || gLCart.length > 0">
				<div class="totalbarBox">
					<div class="totalbar">
						<input type="checkbox" id="checkAll02" @click="check_all($event)" v-model="checkAll"/>
						<i class="checkAll-box" @click="checkAll_box($event)">全选</i>
						<a href="javascript:;" id="delete-allChecked" @click="delete_more()">删除选中商品</a>
						<a href="javascript:;" id="delete-noGoods" @click="delete_noStock()">清除无货商品</a>
						<span id="settlement" class="btn-buy">
							<i @click="settlement(2)">立即采购</i>
							<div>
								<div class="tag-top">  
									订单购买后<br />货物进入微仓
									<i>
										<a href="javascript:;">?</a>
										<em>文字介绍性文字介绍性文字介绍性文字介绍性 文字介绍性文字介绍性文字介绍性文字介绍性文字介绍性文字介绍性文字</em>
									</i>
								</div>
							</div>
						</span>
						<span id="buyNow" class="btn-buy">
							<i @click="settlement(1)">立即购买</i>
							<div>
								<div class="tag-top">  
									订单购买后<br />会立即安排发货
								</div>
							</div>
						</span>
						<div class="total-box">
							<div class="total">
								<span class="total-money">总价（不含运费）：<b>￥<i id="totalPrice">{{total_money.toFixed(2)}}</i></b></span>
								<span class="goods-num-box">已选择<i id="totalNum">{{buyGoodsNum}}</i>件商品</span>
							</div>
							<div class="tax">
								<div>
									<em>
										<a href="javascript:;">?</a>
										<div>
											<div class="tag-top">  
											    税费解释
											</div>
										</div>
									</em>
									含税费：{{total_tax.toFixed(2)}}
								</div>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
		
		<!--立即购买-->
		<form method="post" action="../cartSettle/goFSettleFC1" id="goFSettleFC" name="goFSettleFC" class="form-inline">
			<!--保税仓-->
			<input type="hidden" value="${pd.bCartIDs}" name="bCartIDs" id="bCartIDs" />
			<!--海外直邮-->
			<input type="hidden" value="${pd.hCartIDs}" name="hCartIDs" id="hCartIDs" />
			<!--国内直邮-->
			<input type="hidden" value="${pd.gCartIDs}" name="gCartIDs" id="gCartIDs" />
		</form>
		<!--立即采购-->
		<form method="post" action="../cartSettle/goFSettleFC2" id="goFSettleFC-2" name="goFSettleFC" class="form-inline">
			<!--保税仓-->
			<input type="hidden" value="${pd.bCartIDs}" name="bCartIDs" id="bCartIDs-2" />
			<!--海外直邮-->
			<input type="hidden" value="${pd.hCartIDs}" name="hCartIDs" id="hCartIDs-2" />
			<!--国内直邮-->
			<input type="hidden" value="${pd.gCartIDs}" name="gCartIDs" id="gCartIDs-2" />
		</form>
		<hr />
		<!--页脚-->
		<%@ include file="../index/footer.jsp"%>
		<script src="../static/assets/js/cart.js"></script>
		<!--<script src="../static/assets/js/cart_full.js"></script>-->
	</body>
</html>