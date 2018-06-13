<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--收货人信息-->

<div id="info-box">
	<h1>收货人信息</h1>
	<div class="info-box-l">
		<h2>选择收货人</h2>
		<div class="input-group search-box">
	        <input type="text" class="form-control" id="address-keyword" placeholder="输入收货人姓名可进行搜索"/>
	        <span class="input-group-addon"><i id="search-address" class="glyphicon glyphicon-search"></i></span>
        	<ul id="likeAddrBox"></ul>
		</div>
        <div id="address-box">
        	<a href="javascript:;" id="flip" data-page="1"><i class="dis"><</i><i>></i></a>
        	<ul id="addr-ul"></ul>
        	<div class="address-list" id="new-address">	</div>
        </div>
        <i id="add-address">+新增收货地址</i>
	</div>
	<div class="info-box-r">		
		<div class="inpt-box">
			<i>收货人：</i>
			<input readonly="readonly" id="c-name"/>
		</div>
		<div class="inpt-box">
			<i>电话号码：</i>
			<input readonly="readonly" id="c-mobile"/>
		</div>
		<div class="inpt-box inpt-box-02">
			<i class="left-01">省份：</i>
			<input readonly="readonly" id="c-province"/>
		</div>
		<div class="inpt-box inpt-box-03">
			<i>城市：</i>
			<input readonly="readonly" id="c-city"/>
		</div>
		<div class="inpt-box inpt-box-03">
			<i>地区：</i>
			<input readonly="readonly" id="c-area"/>
		</div>
		<div class="inpt-box inpt-box-01">
			<i>详细地址：</i>
			<textarea readonly="readonly" id="detail-address"></textarea>
		</div>	
		<div class="inpt-box inpt-box-01">
			<i>身份证号码：</i>
			<input readonly="readonly" id="id-card"/>
		</div>
		<a href="javascript:;" class="delete-addr">删除</a>
		<a href="javascript:;" class="alter-addr">修改</a>
	</div>
</div>	
<div class="carBox">
	<div id="good-category">
		<a href="javascript:;">确认商品信息</a>
	</div>
	<div class="car-head">
		<div class="col col2">商品信息</div>
		<div class="col col3">数量</div>
		<div class="col col4">单价（元）</div>
		<div class="col col5">总重量（千克）</div>
		<div class="col col6">金额（元）</div>
	</div>
	<c:if test="${gManage.shipType == '1'}">
		<!--保税仓直邮-->
		<div id="German" class="mail">
			<i>保税仓直邮</i>
			<b class="b-quote"></b>
		</div>		
	</c:if>
	<c:if test="${gManage.shipType == '2'}">
		<!--海外直邮-->
		<div id="German" class="mail">
			<i>海外直邮</i>
			<b class="b-quote"></b>
		</div>
	</c:if>
	<c:if test="${gManage.shipType == '3'}">
		<!--国内现货-->
		<div id="German" class="mail">
			<i>国内现货</i>
			<b></b>
		</div>
	</c:if>
	<div class="goods">
		<ul class="goodsBox">
			<li class="gooditm">
				<div class="hidden" id="good">
					<i>${gManage.goodsID}</i>
				</div>
				<div class="col col2">
					<a href="javascript:;" class="goodImg">
						<img src="../static/assets/img/blank.gif" data-echo="${gManage.goodsDetails.mainMap}" />
					</a> 
					<span>${gManage.goodsDetails.goodsName}</span>
				</div>
				<div class="col col3" id="good-num">${buyNum}</div>
				<div class="col col4">${goodsPrice}</div>
				<div class="col col5">${gManage.weight*buyNum}</div>
				<div class="col col6">${payment}</div>
			</li>	
		</ul>
	</div>
</div>
<!--提交订单-->
<div class="payBox">
	<div class="payBox-l">
		<h6>物流须知：</h6>
		<p>1.根据国家法律相关规定，跨境电子商务零售进口商品的单次交易限值为人民币2000元，个人年度交易限值为人民币20000元。个人年度已使用额度、可用额度官方查询地址：http://ceb2pub.chinaport.gov.cn</p>
		<p>2.超过国家限额的订单无法通过海关发货，您可以分批次提交订单购买或存入北极光微仓后，在需要的时候分批发货</p>
	</div>
	<div class="payBox-r">
		<div class="list">
			<span>商品价格总计：</span>
			<i id="payment">￥${payment}</i>
		</div>
		<div class="list">
			<span>运费：</span>
			<i id="postage">￥${postage}</i>
		</div>
		<div class="list">
			<span>应付金额总计：</span>
			<i class="all" id="tPayment">￥${tPayment}</i>
		</div>
		<div class="btnBox">
			<a href="javascript:;" id="z-submit">提交订单</a>
		</div>
		<h6 id="info-show"><i>${wc1Address.name}</i>收    <i>${wc1Address.province}</i><i>${wc1Address.city}</i><i>${wc1Address.area}</i><i>${wc1Address.detail_address}</i>  电话  <i>${wc1Address.mobile}</i></h6>
		<i class="hidden" id="sa_id"></i>
	</div>
</div>
<!--新增地址容器-->
<div id="add-address-box">
	<h1>新增收货地址</h1>
	<div class="info-add" style="margin-top:40px">
		<h3><i>*</i>所在地区</h3>
		<div class="inpt-info-add">
			<select class="address-add" placeholder="省" id="add-province"></select>
			<select class="address-add" style="margin:0 20px" placeholder="市" id="add-city"></select>
			<select class="address-add" placeholder="区" id="add-area"></select>
		</div>
	</div>
	<div class="info-add detailed-address">
		<h3><i>*</i>详细地址</h3>
		<div class="inpt-info-add">
			<textarea rows="3" placeholder="无需重复填写省市区，小于75字" id="add-detail-address"></textarea>
		</div>
	</div>
	<div class="info-add">
		<h3><i>*</i>收货人姓名</h3>
		<div class="inpt-info-add">
			<input placeholder="不能为昵称、x先生、x小姐等，请使用真实姓名" id="add-name"/>
		</div>
	</div>
	<div class="info-add">
		<h3><i>*</i>手机号码</h3>
		<div class="inpt-info-add">
			<input placeholder="手机号码、电话号码必须填一项" id="add-mobile"/>
		</div>
	</div>
	<!--座机-->
	<!--<div class="info-add">
		<h3>电话号码</h3>
		<div class="inpt-info-add landline">
			<input style="width:120px" placeholder="区号"/>
			<input style="width:180px;margin:0 15px" placeholder="电话号码"/>
			<input style="width:130px" placeholder="分级号码"/>
		</div>
	</div>-->
	<div class="info-add">
		<h3><i>*</i>身份证号码</h3>
		<div class="inpt-info-add">
			<input placeholder="海关检查必须要有身份证验证" id="add-id-card"/>
		</div>
	</div>
	<b class="quit-add">取消</b>
	<b class="save-add">保存新地址</b>
</div>
<div id="alter-address-box">
	<h1>修改收货地址</h1>
	<div class="info-add">
		<h3>原收货地区</h3>
		<div class="inpt-info-add">
			<input class="address-add" id="alter-province" readonly="readonly"/>
			<input class="address-add" style="margin:0 20px" id="alter-city" readonly="readonly"/>
			<input class="address-add" id="alter-area" readonly="readonly"/>
		</div>
	</div>
	<div class="info-add">
		<h3>修改地区</h3>
		<div class="inpt-info-add">
			<select class="address-add" id="alter-province-new">
				<option value=''>请选择省</option>
				<c:forEach items="${provinceList}" var="pro">
					<option value="${pro.area_id}" provincePin="${pro.area_value}">${pro.area_name}</option>
				</c:forEach>
			</select>
			<select class="address-add" style="margin:0 20px"  id="alter-city-new"></select>
			<select class="address-add" id="alter-area-new"></select>
		</div>
	</div>
	<div class="info-add detailed-address">
		<h3><i>*</i>详细地址</h3>
		<div class="inpt-info-add">
			<textarea rows="3" placeholder="无需重复填写省市区，小于75字" id="alter-detail-address"></textarea>
		</div>
	</div>
	<div class="info-add">
		<h3><i>*</i>收货人姓名</h3>
		<div class="inpt-info-add">
			<input placeholder="不能为昵称、x先生、x小姐等，请使用真实姓名" id="alter-name"/>
		</div>
	</div>
	<div class="info-add">
		<h3><i>*</i>联系电话</h3>
		<div class="inpt-info-add">
			<input placeholder="手机号码、电话号码必须填一项" id="alter-mobile"/>
		</div>
	</div>
	<!--座机-->
	<!--<div class="info-add">
		<h3>电话号码</h3>
		<div class="inpt-info-add landline">
			<input style="width:120px" placeholder="区号"/>
			<input style="width:180px;margin:0 15px" placeholder="电话号码"/>
			<input style="width:130px" placeholder="分级号码"/>
		</div>
	</div>-->
	<div class="info-add">
		<h3><i>*</i>身份证号码</h3>
		<div class="inpt-info-add">
			<input placeholder="海关检查必须要有身份证验证" id="alter-id-card"/>
		</div>
	</div>
	<b class="quit-alter">取消</b>
	<b class="save-alter">保存修改</b>
</div>
<!--提交订单-->
<form method="get" action="goFPayFLB" id="goFPayFLB" name="goFPayFLB" class="form-inline">
	<input type="hidden" value="${pd.goodsID}" name="goodsID" id="goodsID" />
	<input type="hidden" value="${pd.buyNum}" name="buyNum" id="buyNum" />
	<!--saID-->
	<input type="hidden" value="${pd.saID}" name="saID" id="saID" />
	<!--客户备注 customerRemark-->
	<input type="hidden" value="" name="customerRemark" id="customerRemark" />
</form>
<script src="../static/assets/js/buyNow_all.js"></script>