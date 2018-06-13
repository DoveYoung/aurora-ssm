<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<style>
		.input-group{
			margin-right: 230px;
		}
		.datetimepicker{
			background:#fff !important;
			height:40px;
		}
		#screen{
			display: inline-block;
			float:right;
			margin-right: 50px;
			width:50px;height:40px;
			line-height: 40px;
			border-radius: 3px;
			border: 1px solid #ccc;
			text-align: center;
			cursor: pointer;
			background:#eee;
		}
		.car-head{
			margin-bottom:0 !important;
			height:40px !important;
			border-bottom: 1px solid #e4e4e4;	
		}
		.orderList-goods .col0{
			padding-bottom: 15px !important;
		}
		.orderList-goods .col2{
			line-height: 130px;
		}
		.orderList-goods .col3{
			border-left:1px solid #e4e4e4;
		}
		.orderList-l{
			border-right:0 !important;
		}
		.i-box{
			width:160px;
		}
		.col2{
			width:110px !important;
		}
		.col3{
			width:130px !important;
		}
		.orderList-l{
			width:100% !important;
		}
		.table-condensed{
			background:#fff;
		}
		.notice{
			color:#EC0033 !important;
		}
		.orderList{
			padding-bottom: 0 !important;
		}
		.orderList .orderList-box{
			border-top: 0 !important;
		}
		#page-list{
			margin-top:30px;	
		}
	</style>
	<h2 id="order_c">
		<b class="active">微仓货物</b>
		<b state="wcfh">微仓发货</b>
		<b state="ddfk">订单付款</b>
	</h2>
<form method="post" action="goMicroWarehouse" name="goMicroWarehouse" id="goMicroWarehouse" class="form-inline">
	<div class="search-box">
		<div class="input-group">
            <input type="text" class="form-control" name="keyword" id="keyword" value="${pd.keyword}" placeholder="请输入商品或订单编号搜索" />
            <span class="input-group-addon pointer" id="searchOrder">搜索</span>
       </div>
       <input name="beginTime" id="beginTime" class="datetimepicker form-control" value="${pd.beginTime}" type="text" readonly />
		至
		<input name="endTime" id="endTime" class="datetimepicker form-control" value="${pd.endTime}" type="text" readonly />
		<b class="btn-t" id="screen">筛选</b>
	</div>
	<div class="list-box">
		<div class="car-head">
			<div class="col col0">商品详情</div>
			<div class="col col2">可用库存</div>
			<div class="col col2">买入价格</div>
			<div class="col col2">付款形式</div>
			<div class="col col2">网站库存</div>
			<div class="col col2">剩余免仓期</div>
			<div class="col col3">操作</div>
		</div>
		<c:forEach items="${customerMWGoods}" var="good">
			<div class="orderList" orderID="${good.value[0].order_id}">
				<div class="orderList-box">
					<div class="orderList-l">
						<div class="orderList-goods">
							<div class="col col0">
								<a class="goods-img" href="<%=basePath%>detailPage/goGoodsdetail?goodsID=${good.goods_id}" target="_blank">
									<img src="../static/assets/img/blank.gif" data-echo="${good.goods_map}"/>
								</a>
								<i class="i-box">
									<a class="goods-name" href="<%=basePath%>detailPage/goGoodsdetail?goodsID=${good.goods_id}" target="_blank">${good.goods_name}</a>
								</i>
							</div>
							<div class="col col2 <c:if test="${good.goods_num == '0'}">notice</c:if>">${good.goods_num}件</div>
							<div class="col col2">￥${good.goods_price}</div>
							<div class="col col2">
								<c:if test="${good.pay_type == '1'}">全款</c:if>
								<c:if test="${good.pay_type == '2'}">定金</c:if>
							</div>
							<div class="col col2">
								<c:if test="${good.goods_stock > '10'}">库存充足</c:if>
								<c:if test="${good.goods_stock < '10' && good.goods_stock > '0'}">库存紧张</c:if>
								<c:if test="${good.goods_stock == '0'}">无库存</c:if>
							</div>
							<div class="col col2">${good.free_day}天</div>
							<div class="col col3">
								<i class="i-box" mwgID="${good.mwg_id}">
									<c:if test="${good.goods_stock > '0'}"><b><a href="<%=basePath%>detailPage/goGoodsdetail?goodsID=${good.goods_id}" target="_blank">补仓</a></b></c:if>
									<c:if test="${good.goods_num == '0'}">
										<b class="pointer delete-good">删除商品</b>
									</c:if>
									<c:if test="${good.goods_stock == '0'}">
										<b>有货提醒</b>
									</c:if>
								</i>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	<%@ include file="../../commons/page.jsp"%>
</form>
<form method="post" action="goDeliverGoods" name="goDeliverGoods" id="goDeliverGoods" class="form-inline">
</form>
<form method="post" action="goMWOrder" name="goMWOrder" id="goMWOrder" class="form-inline">
</form>
<script>
	function search_goMicroWarehouse(i){
		if(i==1){
			$('#currentPage').val(1);
			$('#fromIndex').val(0);
		}
		$('#goMicroWarehouse').submit();
	}
	function goPage(pageNo) {
		$('#currentPage').val(pageNo);
		var fromIndex = (pageNo - 1) * $('#pageSize').val();
		if(fromIndex < 0) {
			fromIndex = 0;
		}
		$('#fromIndex').val(fromIndex);
		search_goMicroWarehouse()
	}
	$(function(){
		$('#searchOrder').on('click',function(){
			$('#beginTime').val('');
			$('#endTime').val('');
			search_goMicroWarehouse(1);
		})
		$('#screen').on('click',function(){
			search_goMicroWarehouse(1);
		})
		//时间选择初始化
		$('#beginTime').datetimepicker({
			startView: 'year',
			minView:'month',
			maxView:'decade',
		  	format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式 
		    language: 'zh-CN', //汉化 
		    autoclose:true, //选择日期后自动关闭 
		    todayBtn: true,//显示今日按钮
		}).on("click",function(){
	        $("#beginTime").datetimepicker("setEndDate",$("#endTime").val())
	    });
	    $('#endTime').datetimepicker({
			startView: 'year',
			minView:'month',
			maxView:'decade',
		  	format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式 
		    language: 'zh-CN', //汉化 
		    autoclose:true, //选择日期后自动关闭 
		    todayBtn: true,//显示今日按钮
		}).on("click",function(){
	        $("#endTime").datetimepicker("setStartDate",$("#beginTime").val())
	    });
	    //删除商品
	    $('.delete-good').on('click',function(){
	    	var mwgID = $(this).parent().attr('mwgID');
	    	layer.confirm('确定删除此商品？',function(index){
		    	$.ajax({
		    		type:"post",
		    		url:"deleteMWGoods",
		    		data:{
		    			'mwgID':mwgID
		    		},
		    		dataType:'json',
		    		success:function(data){
		    			layer.close(index)
		    			if(data.state == 'success'){
		    				layer.msg('删除商品成功')
		    				setTimeout(function(){
		    					window.location.reload();
		    				},500)
		    			}else if(data.state == 'error'){
							layer.msg(data.msg)
						}else if(data.state == 'failed'){
							layer.msg(data.msg)
						}
		    		}
		    	});
	    	})
	    })
	})
	
</script>