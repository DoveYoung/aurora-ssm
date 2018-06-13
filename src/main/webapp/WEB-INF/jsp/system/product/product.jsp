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
		<title>${goodsDM.goodsDetails.goodsName}</title>
		<%@ include file="../index/headLink.jsp"%>
		<script src="https://cdn.bootcss.com/echarts/3.6.2/echarts.common.min.js"></script>
		<script>
			goodsDM = ${goodsDM}
			console.log(goodsDM)
			function search() {
				if($('#keyword').val() == ''){
					return location.reload();
				}
				$("#searchFrom").submit();
			}
		</script>
		<style>
			.fly_item {
			    border: 2px solid #B30000;
			    width: 50px;
			    height: 50px;
			    overflow: hidden;
			    position: absolute;
			    visibility: hidden;
			    opacity: .5;
			    filter: alpha(opacity=50);
    		}
    		#cartImg-fly{
	            position:fixed;
	            display: none;
	            width:46px;height:46px;
	            left:0;top:0;
	            z-index: 101;
	        }
	        .pointer:hover{
	        	text-decoration: underline;
	        }
		</style>
	</head>

	<body id="product-body">
		
		<!--header-->		
		<%@ include file="../index/topNotLogin.jsp"%>
		<!--更好的货源-->
		<div class="layer-delivery" id="layer-better-source"><!--全站供货-->
			<h1>我有更好的货源<i onclick="quit_better_source()"></i></h1>
			<div class="delivery-box">
				<h2>基本信息</h2>
				<div class="info-box">
					<label><i>* </i>商品名称：</label>
					<input class="info" placeholder="请输入商品名称" value="${goodsDM.goodsDetails.goodsName}"/>
				</div>
				<div class="info-box">
					<label><i>* </i>您的姓名：</label>
					<input class="info" placeholder="请使用真实的姓名"/>
				</div> 
				<div class="info-box">
					<label><i>* </i>联系电话：</label>
					<input class="info" placeholder="请留下一个联系方式"/>
				</div>
				<h2>供货商品</h2>
				<div class="info-box">
					<label><i>* </i>联系时间：</label>
					<select class="info">
						<option value="">请选择您方便的时间，我们会第一时间与您沟通</option>
						<option value="08:00~12:00">08:00~12:00</option>
						<option value="14:00~18:00">14:00~18:00</option>
						<option value="18:00~22:00">18:00~22:00</option>
					</select>
				</div>
				<div class="info-box">
					<label><i>* </i>价格：</label>
					<input class="info" placeholder="请输入您的报价"/>
				</div>
				<div class="info-box">
					<label><i>* </i>交货方式：</label>
					<input class="info" placeholder="请输入交货方式"/>
				</div>
				<i class="btn-sub" onclick="sub_better_source()">提交供货</i>
				<i class="btn-sub quit-sub" onclick="quit_better_source()">取消</i>
			</div>
			<script>
				flag_better_source = true
				function better_source(){//我要供货
					better_source_index = layer.open({
						type: 1,
						title: false,
						closeBtn: 0,
						scrollbar: false,
						area: '750px',
						skin: 'layui-layer-nobg', //没有背景色
						content: $('#layer-better-source')
					});
				}
				function quit_better_source(){
					layer.close(better_source_index)
				}
				function sub_better_source(){
					if(!flag_better_source){
						return 
					}
					//supplyGoodsIntentionJson :{String goodsID,goodsName,contacts,phone,deliveryType,price,convenientTime,inputTime}
					console.log('${goodsDM.goodsDetails.goodsID}')
					var obj = {},arr_source = ['goodsName','contacts','phone','convenientTime','price','deliveryType'];
					obj.goodsID = '${goodsDM.goodsDetails.goodsID}';
					for(var i = 0;i < arr_source.length;i++){
						if($('#layer-better-source .info').eq(i).val() == ''){
							var info_str = $('#layer-better-source .info').eq(i).siblings('label').text()
								info_str = info_str.replace('* ','')
								info_str = info_str.replace('：','')
							return layer.msg('请完善'+info_str+'信息')
						}
						obj[arr_source[i]] = $('#layer-better-source .info').eq(i).val();
					}
					supplyGoodsIntentionJson = JSON.stringify(obj)
					flag_better_source = false;
					$.ajax({
					    type:'post',
					    url:'../supplyIntention/addSupplyGoodsIntention',
					    data:{
					    	supplyGoodsIntentionJson:supplyGoodsIntentionJson
					    },
					    dataType:'json',
					    success:function(data){
					    	flag_better_source = true;
					    	console.log(data)
					        if(data.state == 'success'){
					        	layer.close(better_source_index)
					            layer.msg('成功')
					        }else if(data.state == 'error'){
					            layer.msg(data.msg)
					        }else if(data.state == 'failed'){
					            layer.msg(data.msg)
					        }
					    },
					    error:function(){
					    	flag_better_source = true;
					    }
					});
					console.log(obj)
				}
			</script>
		</div>
		<!--layout-->
		<div class="body-box p-body-box">
			<script>
				//跳转到二级类目搜索页
				function brand_name(i){
					$('#keyword').val($(i).text());
					headerSearch()
				}
			</script>
			<div class="m-layout p-layout">
				<ul class="breadcrumb">
					<li>所有分类</li>
					<li class="pointer" onclick="brand_name(this)">${goodsDM.goodsCommon.brandName}</li>
					<li>${goodsDM.goodsDetails.goodsName}</li>
				</ul>
				<!--商品放大镜-->
				<div id="m-producthead">
					<div id="m-producthead-l">
						<div class="m-producthead-box">
							<div class="product-big">
								<c:if test="${goodsDM.deposit != '100'}">
									<div class="marks marks-deposit"></div>
								</c:if>
								<c:if test="${goodsDM.goodsCommon.brandID == '70' || goodsDM.goodsCommon.brandID == '76'}">
									<div class="marks marks-overseas"></div>
								</c:if>
								<img src="${goodsDM.goodsDetails.mainMap}" />
								<span id="productSpan"></span>
							</div>
							<div id="img-box-list">
								<a href="javascript:;" class="icon-arrow-left"></a>
								<div class="product-list" id="productImg">
									<img class="active" src="${goodsDM.goodsDetails.mainMap}" />
									<c:forEach items="${map6}" var="good">
										<img src="${good}"/>
									</c:forEach>
								</div>
								<a href="javascript:;" class="icon-arrow-right"></a>
							</div>
						</div>
						<div class="product-zoom">
							<div id="Img">
								<img src="${goodsDM.goodsDetails.mainMap}" />
							</div>
						</div>
					</div>					
					<div id="productBox">
						<div class="productNation">
							<img src="${brand.nationalFlag}" />
							<i>${brand.countryCName}</i>|
							<b class="pointer" onclick="brand_name(this)">${goodsDM.goodsCommon.brandName}</b>
						</div>
						<h1>${goodsDM.goodsDetails.goodsName}</h1>
						<h2>
							<c:if test="${goodsDM.discount != '100'}">
							<i class="discount">${goodsDM.discount / 10}折</i>
							</c:if>
							${goodsDM.goodsDetails.describe}
						</h2>
						<c:if test="${goodsDM.isActivity == '1'}">
							<div class="isActivity">
								<b>限时折扣</b>
								该商品距活动结束还剩：
								<i></i>小时
								<i></i>分钟
								<i></i>秒
								<i></i>
							</div>
							<script>
								function timed_activity(){
									var end_times = '${goodsDM.timedActivity.endTime}'
				//						end_times = end_times.replace('-','/')
									var endtime = new Date(end_times);
					            	var nowtime = new Date();
					            	var seconds = parseInt((endtime.getTime() - nowtime.getTime()) / 1000);
					            	if(seconds <= 0){
					            		$('.isActivity i').eq(0).text('00');
							            $('.isActivity i').eq(1).text('00');
							            $('.isActivity i').eq(2).text('00');
							            $('.isActivity i').eq(3).text('0');
					            		return clearInterval(fresh)
					            	}
				//	            	console.log(seconds)
					            	d = parseInt(seconds / 3600 / 24);
					            	h = parseInt((seconds / 3600) % 24)
						            h_day_no = parseInt(seconds / 3600);
						            m = parseInt((seconds / 60) % 60);
						            s = parseInt(seconds % 60);
						            ms = parseInt((endtime.getTime() - nowtime.getTime()) / 100) % 10;
//						            console.LOG2Eg(ms)
						            //格式筛选
						            h = parseInt(h / 10) == 0 ? '0'+h : h;
						            h_day_no = parseInt(h_day_no / 10) == 0 ? '0'+h_day_no : h_day_no;
						            m = parseInt(m / 10) == 0 ? '0'+m : m;
						            s = parseInt(s / 10) == 0 ? '0'+s : s;
						            $('.isActivity i').eq(0).text(h_day_no);
						            $('.isActivity i').eq(1).text(m);
						            $('.isActivity i').eq(2).text(s);
						            $('.isActivity i').eq(3).text(ms);
								}
								var fresh;
				        		fresh = setInterval(timed_activity, 100);
							</script>
						</c:if>
						<div class="productPrice productPrice-active" <c:if test="${goodsDM.exw == '1'}">style="height:86px"</c:if>>
							<ul class="productPrice-l">
								<c:if test="${goodsDM.exw == '1'}">
									<li>零售价</li>
									<li>EXW价</li>
								</c:if>
								<c:if test="${goodsDM.exw != '1'}">
									<li>零售价</li>
									<li>批发价</li>
									<li>EXW价</li>
								</c:if>
							</ul>
							<c:if test="${goodsDM.exw == '1'}">
								<ul class="productPrice-list productPrice-exw">
									<li>
										<i>￥${goodsDM.goodsPrice1}</i>
										<span>${goodsDM.rnum1}-${goodsDM.rnum2}件</span>
										<span>市场价￥${goodsDM.marketPrice}/件</span>
									</li>
									<li>
										<i>￥${goodsDM.goodsPrice2}</i>
										<span>>${goodsDM.rnum3}件</span>
										<a class="exw-inq" target="_blank" href="javascript:;" onclick="inquiry_p(this)" data-url="<%=basePath%>customerInquiry/goAddInquiryPage?goodsID=${pd.goodsID}">请询价</a>
										<span>exw价格为客户自提价,如需配送请询价</span>
									</li>
								</ul>
							</c:if>
							<c:if test="${goodsDM.exw != '1'}">
								<ul class="productPrice-list productPrice-exw">
									<li>
										<i>￥${goodsDM.goodsPrice1}</i>
										<span>${goodsDM.rnum1}-${goodsDM.rnum2}件</span>
										<c:if test="${goodsDM.discount == '100'}">
											<span>市场价￥${goodsDM.marketPrice}/件</span>
										</c:if>
										<c:if test="${goodsDM.discount != '100'}">
											<b>原价<em>￥${goodsDM.goodsPrice1 * 100 / goodsDM.discount }</em></b>
										</c:if>
									</li>
									<li>
										<i>￥${goodsDM.goodsPrice2}</i>
										<span>${goodsDM.rnum2 + 1}-${goodsDM.rnum3}件</span>
										<c:if test="${goodsDM.discount != '100'}">
											<b>原价<em>￥${goodsDM.goodsPrice2 * 100 / goodsDM.discount }</em></b>
										</c:if>
									</li>
									<li>
										<a class="exw-inq" target="_blank" href="javascript:;" onclick="inquiry_p(this)" data-url="<%=basePath%>customerInquiry/goAddInquiryPage?goodsID=${pd.goodsID}">请询价</a>
										<span>exw价格为客户自提价,如需配送请询价</span>
									</li>
								</ul>
								
							</c:if>
						</div>
						<!--贸易方式-->
						<div id="trade-mode" class="fl">
							<div class="fl-l">贸易方式</div>
							<div class="fl-r" id="shipWay">
								<c:forEach items="${sgIDMap}" var="map" varStatus="vs">
									<c:if test="${map.key == '1'}">
										<c:choose>
											<c:when test="${map.key == goodsDM.shipType}">			
												<a class="choose-way active" href="javascript:;">保税仓代发<i class="hidden">1</i></a>
											</c:when>
											<c:otherwise>
												<a class="choose-way" href="<%=basePath%>detailPage/goGoodsdetail?category2ID=${pd.category2ID}&goodsID=${map.value}&sShipType=${map.key}">保税仓代发</a>
											</c:otherwise>
										</c:choose>
									</c:if>
									<c:if test="${map.key == '2'}">
										<c:choose>
											<c:when test="${map.key == goodsDM.shipType}">
												<a class="choose-way active" href="javascript:;">海外直邮<i class="hidden">2</i></a>
											</c:when>
											<c:otherwise>
												<a class="choose-way" href="<%=basePath%>detailPage/goGoodsdetail?category2ID=${pd.category2ID}&goodsID=${map.value}&sShipType=${map.key}">海外直邮</a>
											</c:otherwise>
										</c:choose>
									</c:if>
									<c:if test="${map.key == '3'}">
										<c:choose>
											<c:when test="${map.key == goodsDM.shipType}">
												<a class="choose-way active" href="javascript:;">一般贸易<i class="hidden">3</i></a>
											</c:when>
											<c:otherwise>
												<a class="choose-way" href="<%=basePath%>detailPage/goGoodsdetail?category2ID=${pd.category2ID}&goodsID=${map.value}&sShipType=${map.key}">国内现货</a>
											</c:otherwise>
										</c:choose>
									</c:if>
								</c:forEach>
							</div>
						</div>
						<!--邮费-->
						<div class="fl">
							<div class="fl-l">邮费</div>
							<div class="fl-r">
								<b>至</b>								
								<select id="address-choose">
									<c:forEach items="${province}" var="good" varStatus="vs">
										<option value="${good.area_value}">${good.area_name}</option>
									</c:forEach>									
								</select>
								<c:if test="${goodsDM.postageStyle == 1}"><b>包邮</b></c:if>
								<c:if test="${goodsDM.postageStyle != 1}"><b id="postage"></b></c:if>
								<b class="hidden" id="uWeight">${goodsDM.weight}</b>
							</div>
						</div>
						<!--税费-->
						<div class="fl">
							<div class="fl-l">税费</div>
							<!--<div class="fl-r">
								<b>售价已包含<i>¥${goodsDM.goodsPrice1 * 119 / 1000}<span id="taxation"></span></i>跨境综合税</b>
							</div>-->
							<div class="fl-r">
								<b>售价已包含<i>11.9%<span id="taxation"></span></i>跨境综合税</b>
							</div>
						</div>
						<!--数量--> 
						<div class="fl">
							<div class="fl-l">数量</div>
							<input type="hidden" value="${goodsDM.rnum1}" id="minNum"/>
							<div class="fl-r">
								<a href="javascript:;" id="reduceNum">-</a>
								<input type="text" id="product-num" autocomplete="off" min="${goodsDM.rnum1}" max="${goodsDM.goodsStock < goodsDM.rnum3 ? goodsDM.goodsStock : goodsDM.rnum3}" step="1" value="${goodsDM.rnum1}" data-maxtype="${goodsDM.goodsStock < goodsDM.rnum3 ? '0': '1'}" />
								<a href="javascript:;" id="addNum">+</a>
								<span>
									<c:if test="${goodsDM.goodsStock > goodsDM.stockEmergency}">库存充足</c:if> 
									<c:if test="${goodsDM.goodsStock <= goodsDM.stockEmergency}">剩余库存：<i>${goodsDM.goodsStock}</i></c:if> 
								</span>
								<label  class="${(goodsDM.shipType == 2 && goodsDM.goodsPrice1*goodsDM.rnum1 < '20000') || (goodsDM.shipType == 1 && goodsDM.goodsPrice1*goodsDM.rnum1 < '2000' || goodsDM.shipType == 3)? 'quota un-quota' : 'quota'}" data-shiptype="${goodsDM.shipType}" data-p1="${goodsDM.goodsPrice1}" data-p2="${goodsDM.goodsPrice1}" data-rnum2="${goodsDM.rnum2}">
									抱歉，您已超过限额<em>¥${goodsDM.shipType != 3 ? '20000' :'2000'}</em>，请分次购买或买入微仓后分次发货
								</label>
							</div>
						</div>
						<!--付款方式-->
						<div id="pay-way" class="fl">
							<div class="fl-l">付款方式</div>
							<div class="fl-r" id="payWay">
								<div class="payway-box">
									<span class="choose-way active" data-pay="1">全款</span>
									<c:if test="${goodsDM.deposit != '100'}">
										<span class="choose-way" data-pay="2">${goodsDM.deposit}%定金</span>
									</c:if>
								</div>
							</div>
						</div>
						<c:if test="${goodsDM.deposit != '100'}">
						<div class="fl fl-deposit">
							<div class="fl-l"></div>
							<div class="fl-r deposit-info">
								您可以选择预支付<i>50％</i>的货款作为定金，提前把货物购入微仓，然后在需要发货的时候补足尾款和运费即可发货...<a href="<%=basePath%>footer/depositMode" target="_blank">查看详细说明</a>
							</div>
						</div>
						</c:if>
						<div id="quotation">
							<i>!</i>
							<b>诚邀！如果您有更优质的货源，有合作意向可以向我们<a href="javascript:;" onclick="better_source()">立刻报价</a>，审核通过后我们将和您洽谈合作。</b>
						</div>
						<div class="buyBtns" data-goodsid="${pd.goodsID}" data-category2id="${pd.category2ID}" data-exw="${goodsDM.exw}" data-exwnum="${goodsDM.exw == '1' ? goodsDM.rnum2 : goodsDM.rnum2 + 1}">
							<c:if test="${goodsState == '4'}">
								<!--<a href="javascript:;" id="buyNow" >立即购买</a>-->
								<a href="javascript:;" id="addShopCar">加入货单</a>
							</c:if>
							<c:if test="${goodsState != '4'}">
								<i style="font-weight:bold;font-size:20px;">亲，该商品已下架,可以选择<b style="font-weight:bold;cursor: pointer;color:#e60031" onclick="inquiry_p(this)" data-url="<%=basePath%>customerInquiry/goAddInquiryPage?goodsID=${pd.goodsID}">询价</b>哟</i>
							</c:if>
						</div>
					</div>
				</div>
				<div id="m-productbody">
					<!--相关推荐-->
					<h3>相关推荐</h3>
					<ul id="recommend">
						<c:choose>
							<c:when test="${not empty likeGoods}">
								<c:forEach items="${likeGoods}" var="good" varStatus="vs" begin="0" end="5">
									<li>
										<c:if test="${good.deposit != '100'}">
											<div class="marks marks-deposit"></div>
										</c:if>
										<c:if test="${good.brand_id == '70' || good.brand_id == '76'}">
											<div class="marks marks-overseas"></div>
										</c:if>
										<a class="recommend-goods" target="_blank" href="<%=basePath%>detailPage/goGoodsdetail?category2ID=${good.category2_id}&goodsID=${good.goods_id}&sShipType=${good.ship_type}" title="${good.goods_name}">
											<img src="../static/assets/img/blank.gif" data-echo="${good.main_map}" />
											<i>${good.goods_name}</i>
											<span>￥<b>${good.goods_price2}</b></span>
										</a>
									</li>
								</c:forEach>
							</c:when>
						</c:choose>
					</ul>
					<input id="jpriceData" class="hidden" value="${jpriceData}"/>
					<input id="tpriceData" class="hidden" value="${tpriceData}"/>
					<input id="apriceData" class="hidden" value="${apriceData}"/>
					<input id="tStoreData" class="hidden" value="${tStoreData}"/>
					<!--商品详情&行业数据-->
					<div id="m-productbody-choose">
						<a href="javascript:;" class="active">商品详情</a>
						<a href="javascript:;">行业数据</a>
					</div>
					<div id="m-productbody-box">
						<div id="m-product-details" class="m-productbody-b active">
							<ul class="details">
								<li class="pn">品牌:</li><li class="pv">${goodsDM.goodsCommon.brandName}</li>
								<li class="pn">原产地:</li><li class="pv">${goodsDM.goodsDetails.productArea}</li>
								<li class="pn">重量:</li><li class="pv">${goodsDM.goodsDetails.weight}kg</li>
								<li class="pn">体积:</li><li class="pv">${goodsDM.goodsDetails.volume}cm</li>
								<li class="pn">条形码:</li><li class="pv">${goodsDM.goodsDetails.goodsCode}</li>
								<c:forEach items="${property}" var="map" varStatus="vs">
									<c:if test="${vs.index%2 == 0 }">
										<li class="pn">${map}:</li>
									</c:if>
									<c:if test="${vs.index%2 == 1 }">
										<li class="pv" title="${map}">${map}</li>
									</c:if>
								</c:forEach>
							</ul>
							<hr />
							<c:forEach items="${advertiseMap}" var="img" varStatus="vs">
								<img src="../static/assets/img/blank.gif" data-echo="${img}" class="Ad"/>
							</c:forEach>		
						</div>
						<div id="m-product-data" class="m-productbody-b" data-id="${pd.goodsID}">
						</div>
					</div>
					<div id="aurora-tip">
						<h4>北极光温馨提示</h4>
						<p>1,外国商品的包装更注重环保、便捷与实用，因此许多包括国际一线品牌的包装非常简单，属正常现象。</p>
						<p>2,许多国外商品的包装只标注保质期、生产日期、有效期其中的一个或几个。也有只标注出厂批号的情况。</p>
						<p>3,由于部分国外商品产品与包装更新换代比较频繁，因此您收到的货品有可能与图片不完全一致，页面图片及描述仅供参考，请您以最终收到的实物为准。同时我们会尽量及时更新图片，由此给您带来的不便请多谅解！</p>
					</div>
				</div>
			</div>
		</div>
		<img id="cartImg-fly" src="${goodsDM.goodsDetails.mainMap}">
		<!--全款立即购买-->
		<form method="get" action="../buySettleFLB/goFSettleFLB" id="goFSettleFLB" name="goFSettleFLB" class="form-inline">
			<!--<input type="hidden" value="${pd.category2ID}" name="category2ID" id="category2ID" />-->
			<input type="hidden" value="${pd.fGoodsID}" name="fGoodsID" id="fGoodsID" />
			<input type="hidden" value="${pd.fBuyNum}" name="fBuyNum" id="fBuyNum" />
			<input type="hidden" name="saID" id="saID" />
		</form>
		<!--定金立即购买-->
		<form method="get" action="../buySettleFLB/goDSettleFLB" id="goDSettleFLB" name="goDSettleFLB" class="form-inline">
			<!--<input type="hidden" value="${pd.category2ID}" name="category2ID" id="category2ID" />-->
			<input type="hidden" value="${pd.dGoodsID}" name="dGoodsID" id="dGoodsID" />
			<input type="hidden" value="${pd.dBuyNum}" name="dBuyNum" id="dBuyNum" />
		</form>
		<!--页脚-->
		<%@ include file="../index/footer.jsp"%>
		<!--右侧固定-->
		<%@ include file="../index/rightFixed.jsp"%>
		<script src="../static/assets/js/parabola.js"></script>
		<script>	
			/* 元素 */
			var addShopCar = document.getElementById("cartImg-fly"), target = document.getElementById("goCart");
			// 抛物线元素的的位置标记
			var parabola = funParabola(addShopCar, target).mark();			
		</script>
		<script src="../static/assets/js/product.js"></script>
	</body>
</html>