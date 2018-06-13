<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
	.car-head{
		width:100%;height:40px !important;
		margin-top: 30px;margin-bottom: 0 !important;
		line-height: 40px !important;
		border:1px solid #ddd;
		background:#f7f7f7;
	}
	.col2{
		width:110px !important;
	}
	.col4{
		width:130px !important;
	}
	.col3{
		width:120px !important;
	}
	.col3 i{
		display:block;
		float:left;
		width:24px;height:24px;
		margin-top:50px !important;
		border:1px solid #999;
		margin:0;
	}
	/*.minus{
		margin-left: 60px !important;
	}*/
	.col3 input{
		display:block;
		float:left;
		width:60px;height:24px;
		margin-top:50px;
		text-align: center;
		border:1px solid #999;
		border-right:0;
		border-left: 0;
	}
	.orderList{
		padding-bottom: 0 !important;	
	}
	.orderList .orderList-box{
		border-top: 0 !important;
	}
	.orderList-goods .col0{
		padding-bottom: 15px !important;
	}
	.orderList-goods .col2,.orderList-goods .col4{
		line-height: 130px;
	}
	.orderList-goods .col4{
		border-left:1px solid #e4e4e4;
	}
	.orderList-l{
		width:100% !important;
	}
	.bootstrap-select{
		width:350px !important;
	}
	.creat-order{
		float:right;
		width:100px;height:30px;
		margin-top:30px;
		line-height: 30px;
		font-size:16px;
		text-align: center;
		pointer-events: none;
        cursor: not-allowed;
        opacity: 0.6;
		color: #fff;
		background:#4E92E9;
	}
	.creat-order-active{
        pointer-events:auto;
        cursor: pointer;
        opacity: 1;
    }
</style>
<h2 id="order_c">
	<b state="wchw">微仓货物</b>
	<b class="active">微仓发货</b>
	<b state="ddfk">订单付款</b>
</h2>
<form method="post" action="goMicroWarehouse" name="goMicroWarehouse" id="goMicroWarehouse" class="form-inline">
</form>
<form method="post" action="goDeliverGoods" name="goDeliverGoods" id="goDeliverGoods" class="form-inline">
	<div id="info-box">	
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
	<!--选择商品-->
	<span class="tit01">选择商品</span>
	<div class="input-group">
		<select id="good_select" class="selectpicker" data-live-search="true" style="width:120px">  
	        <option value="">点击s输入商品名称进行搜索</option>
	        <c:forEach items="${mwGoods}" var="good">
	        	<option value="${good.mwg_id}">${good.goods_name}(${good.goods_num})</option>
	        </c:forEach>
    	</select>
    	<script>
		$(window).on('load', function () {
			//激活
			$('.selectpicker').selectpicker('refresh');
//			$(".selectpicker Tr").show();
			$('.dropdown-toggle').dropdown()
			$.ajax({
				type:"post",
				url:"getCustomerMWG",
				async:false,
				success:function(data){
					console.log(data)
					if(data.state == 'success'){
						good = data.customerMWG;
						$('#id_select').html('');
						var select_c = "<option value=''>点击输入商品名称进行搜索</option>"
						for(var i = 0;i < good.length;i++){
							if(1){
								
							}
							 select_c += "<option value='"+good[i].mwg_id+"'>"+good[i].goods_name+"</option>"
						}
						$('#good_select').html(select_c)
						//激活
						$('.selectpicker').selectpicker('refresh');
//						$(".selectpicker Tr").show();
						$('.dropdown-toggle').dropdown()
					}else if(data.staty == 'failed'){
						layer.msg(data.msg)
					}else if(data.state == 'error'){
						layer.msg(data.msg)
					}	
				}
			});
        }); 
	</script>
        <span class="input-group-addon pointer" id="goods-search" title="添加" style="background:#4E92DF;color:#fff;border:none;"><i class="glyphicon">添加</i></span>       	
	</div>
	<div id="search-goods">
		
		<div class="list-box">
			<div class="car-head">
				<div class="col col0">商品详情</div>
				<div class="col col2">可用库存</div>
				<div class="col col2">买入价格</div>
				<div class="col col2">付款形式</div>
				<div class="col col2">发货方式</div>
				<div class="col col3">发货数量</div>
				<div class="col col4">操作</div>
			</div>
		</div>
		<div class="creat-order">生成订单</div>
	</div>
</form>
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
<form method="post" action="goMWOrder" name="goMWOrder" id="goMWOrder" class="form-inline">
</form>
<script>
	
	$(function(){
		addr_page(2);
		$('#flip').on('click','i',function(){
			var pageNum = $('#flip').data('page');
			if($(this).index() == 0){
				$('#flip').data('page',pageNum - 1)
			}else{
				$('#flip').data('page',pageNum + 1)
			}
			addr_page(2)
		})
		$('#good_select').selectpicker('show')
		//搜索 地址
		$('#search-address').on('click',function(){
			search_address()
		})
		var now_h = -1;
		$('#address-keyword').on('keyup',function(event){
			now_h = get_addr(now_h)
		})
		$('.search-box').on('mouseleave',function(){
			$('#likeAddrBox').css('display','none')
		})
		//选中搜索地址
		$('#likeAddrBox').on('click', 'li', function() {
			$('#new-address').html('');
			var child_i = $(this).find('i').text();
			$('#new-address').html('');
			var child = "<a href='javascript:;' class='address-check' title='"+child_i+"'>"
							        		+"<span><i>寄送给</i></span>"
							        		+"<em></em>"
							        		+"<input type='radio' name='address' />"
							        		+"<i>"+child_i+"</i>"
							        		+"<b class='hidden'>"+$(this).find('b').text()+"</b>"
						        		+"</a>"
			
			$('#new-address').append(child);
			//模拟点击
			$('#address-box #new-address').find('a').trigger('click');
			$('#likeAddrBox').css('display', 'none')
		}).on('mouseover', 'li', function() {
			$('#likeAddrBox li').removeClass('li-h');
			$(this).addClass('li-h');
			now_h = $('#likeAddrBox .li-h').index();
		})
		//选中地址获取信息
		address_c($('.address-active'));
		$('#address-box').on('click','.address-check',function(){
			address_c($(this));
		})
		//删除地址 delete-addr
		$('.delete-addr').on('click',function(){
			var saID = $('.address-active').find('b').text();
	//					return console.log(saID)
			layer.confirm('是否删除此地址？',function(){	
			$.ajax({
				    type:'post',
				    url:'../receiverAddress/deleteShipAddr',
				    data:{'saID':saID},
				    dataType:'json',
				    success:function(data){
				        if(data.state == 'success'){
				            layer.msg('删除成功')
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
	//				delete_adr(saID)
			})
		})
		//修改地址请求
		$('.alter-addr').on('click',function(){
			var saID = $('.address-active').find('b').text();
			$.ajax({
			    type:'post',
			    url:'../areaAddr/getSAByID',
			    data:{'saID':saID},
			    dataType:'json',
			    success:function(data){
			        if(data.state == 'success'){
			            var addr = data.uShipAddr;
			            console.log(data)
			            $('#alter-province').val(addr.province)
			            $('#alter-province').attr('provincePin',addr.province_pin)
			            $('#alter-city').val(addr.city)
			            $('#alter-area').val(addr.area)
						$('#alter-name').val(addr.name)
						$('#alter-mobile').val(addr.mobile)
						$('#alter-province').val(addr.province)
						$('#alter-city').val(addr.city)
						$('#alter-area').val(addr.area)
						$('#alter-detail-address').val(addr.detail_address)
						$('#alter-id-card').val(addr.id_card)
						$('#alter-address-box').attr('saID',addr.sa_id)
						alterIndex = layer.open({
							type: 1,
							title: false,
							closeBtn: 1,
							area: '660px',
							skin: 'layui-layer-nobg', //没有背景色
							shadeClose: false,
							content: $('#alter-address-box')
						})
			        }else if(data.state == 'error'){
			            layer.msg(data.msg)
			        }else if(data.state == 'failed'){
			            layer.msg(data.msg)
			        }
			    }
			});
		})
		//修改地址选择省
		$('#alter-province-new').on('change',function(){
			var areaID = $(this).val();
			change_region(areaID,'#alter-city','#alter-area')
		})
		//修改地址选择市
		$('#alter-city-new').on('change',function(){
			var areaID = $(this).val();
			change_region(areaID,'#alter-area')
		})
		//保存修改
		$('.save-alter').on('click',function(){
			if( $('#alter-detail-address').val() == ''){
				return tips('请填写详细地址','#alter-detail-address');
			}
			if( $('#alter-name').val() == ''){
				return tips('请填写收货人姓名','#alter-name');
			}
			if( $('#alter-mobile').val() == ''){
				return tips('请填写联系电话','#alter-mobile');
			}
			if( $('#alter-id-card').val() == ''){
				return tips('请填写联系电话','#alter-id-card');
			}
			var info = {};
			info.saID = $('#alter-address-box').attr('saID');
			//是否修改地址
			if($('#alter-area-new').val() == null){
				info.province = $('#alter-province').val();
				info.provincePin = $('#alter-province').attr('provincePin');
				info.city = $('#alter-city').val();
				info.area = $('#alter-area').val();
			}else{
				info.province = $('#alter-province-new').find("option:selected").text();
				info.provincePin = $('#alter-province-new').find("option:selected").attr('pinyin')
				info.city = $('#alter-city-new').find("option:selected").text();
				info.area = $('#alter-area-new').find("option:selected").text();
			}
			info.detailAddr = $('#alter-detail-address').val();
			info.name = $('#alter-name').val();
			info.mobile = $('#alter-mobile').val();
			info.IDCard = $('#alter-id-card').val();
//			console.log(info)
			$.ajax({
			    type:'post',
			    url:'../receiverAddress/updateShipAddr',
			    data:info,
			    dataType:'json',
			    success:function(data){
			        if(data.state == 'success'){
			            layer.msg('修改成功')
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
		$('.quit-alter').on('click',function(){
			layer.close(alterIndex);
		})
		//新增收货地址请求
		$('#add-address').on('click',function(){
			customer()
			if(!customerStatus){
				return to_login()
			}
			$.ajax({
				type:"post",
				url:"../areaAddr/goAddShipAddr",
				success:function(data){
					if(data.state == 'success'){
						$('#add-province').html('');
						var province = data.province;
						var child = "<option value=''>选择省</option>";
						for(var i = 0;i < province.length;i++){
							child += "<option value='" + province[i].area_id + "' pinyin='"+province[i].area_value+"'>"+ province[i].area_name +"</option>"
						}
						$('#add-province').append(child)
						addIndex = layer.open({
							type: 1,
							title: false,
							closeBtn: 1,
							area: '660px',
							skin: 'layui-layer-nobg', //没有背景色
							shadeClose: false,
							content: $('#add-address-box')
						})
					}
				}
			});	
		})
		//修改地址选择省
		$('#alter-province-new').on('change',function(){
			var areaID = $(this).val();
			change_region(areaID,'#alter-city-new','#alter-area-new')
		})
		//修改地址选择市
		$('#alter-city-new').on('change',function(){
			var areaID = $(this).val();
			change_region(areaID,'#alter-area-new')
		})
		//选择省
		$("#add-province").change(function(){
			var areaID = $(this).val();
			change_region(areaID,'#add-city','#add-area');
		})
		//选择市
		$("#add-city").change(function(){
			var areaID = $(this).val();
			change_region(areaID,'#add-area');
		})
		//保存新增地址
		$('.save-add').on('click',function(){
			add_addr()
		})
		//取消保存
		$('.quit-add').on('click',function(){
			layer.close(addIndex);
		})
		//搜索添加商品
		var arr_ID = [];
		$('#goods-search').on('click',function(){
			if($('#good_select').val() == ''){
				return layer.msg('搜索不能为空');
			}
			var mwgID = $('#good_select').val();
			var flag = true;
			$.each(arr_ID,function(key,val) {
				if(val == mwgID){
					layer.msg('此商品已在列表中');
					return flag = false;
				}
			})
			if(!flag){
				return
			}
			$.ajax({
				type:"post",
				url:"getGoodsByMwgID",
				data:{'mwgID':mwgID},
				dataType:'json',
				success:function(data){
					console.log(data)
					if(data.state == 'success'){
		    			var good = data.mwGoods;
		    			if(good.goods_num <= 0){
		    				return layer.msg('该商品异常')
		    			}
		    			if(good.pay_type == 2){
		    				pay_type = "定金"+ good.deposit+"%"
		    			}else{
		    				pay_type = "全款"
		    			}
		    			if(good.trade_type == 1){
		    				trade_type = '保税仓'
		    			}else if(good.trade_type == 2){
		    				trade_type = '海外直邮'
		    			}else{
		    				trade_type = '国内现货'
		    			}
		    			var list = "<div class='orderList' mwgID='"+good.mwg_id+"'><div class='orderList-box'><div class='orderList-l'><div class='orderList-goods'>"
										+"<div class='col col0'>"
											+"<a class='goods-img' href='<%=basePath%>detailPage/goGoodsdetail?goodsID="+good.goods_id+"' target='_blank'>"
												+"<img src='"+good.goods_map+"'/>"
											+"</a>"
											+"<i class='i-box'>"
												+"<a class='goods-name' href='<%=basePath%>detailPage/goGoodsdetail?goodsID="+good.goods_id+"' target='_blank'>"+good.goods_name+"</a>"	
											+"</i>"
										+"</div>"
										+"<div class='col col2'>"+good.goods_num+"</div>"
										+"<div class='col col2'>￥"+good.goods_price+"</div>"
										+"<div class='col col2'>"+pay_type+"</div>"
										+"<div class='col col2'>"+trade_type+"</div>"
										+"<div class='col col3'>"
											+"<i class='minus pointer'>-</i><input class='sendNum' value='1' min='1' max='"+good.goods_num+"'/><i class='add pointer'>+</i>"
										+"</div>"
										+"<div class='col col4'>"
											+"<i class='pointer delete-good' mwgID='"+good.mwg_id+"'>删除商品</i>"
										+"</div>"
									+"</div></div></div></div>"
						$('.list-box').append(list);
						$('.creat-order').addClass('creat-order-active');
						arr_ID.push(mwgID);
	    			}else if(data.state == 'error'){
						layer.msg(data.msg)
					}else if(data.state == 'failed'){
						layer.msg(data.msg)
					}
				}
			});
		})
		//商品数量直接改变
		$('.list-box').on('change','.sendNum',function(){
			inpt_num($(this))
		})
		//商品数量加减
		$('.list-box').on('click','.minus',function(){
			var sendNum = $(this).siblings('.sendNum');
			num = parseInt(sendNum.val());
			if(num <= 1) {
				num = 1;
				layer.msg('商品数量最少为1')
			} else {
				num--
			}
			sendNum.val(num);
		})
		$('.list-box').on('click','.add',function(){
			var sendNum = $(this).siblings('.sendNum');
			var max = sendNum.attr('max');
			num = parseInt(sendNum.val());
			if(num >= max) {
				num = max;
				layer.msg('已到达此商品最大可用数量！')
			} else {
				num++
			}
			sendNum.val(num);
		})
		//删除商品
		$('.list-box').on('click','.delete-good',function(){
			var mwgID = $(this).attr('mwgID');
			var box = $(this).parent().parent().parent().parent().parent()
			layer.confirm('确定将此商品从列表中删除？',function(index){
				$.each(arr_ID,function(key,val) {
					if(val == mwgID){
						console.log(val)
						arr_ID.splice(key,1);
						return 
					}
				})
				box.remove();
				if(!arr_ID.length){
					$('.creat-order').removeClass('creat-order-active');
				}
				layer.close(index);
			})
		})
		//生成订单
		$('.creat-order').on('click',function(){
			var mwgIDAndNum = [];
			var check_amount = false;
			var list = $('.orderList');
			for(var i = 0;i < list.length;i++){
				var mwgID = arr_ID[i];
				var sendNum = list.find('.sendNum').val();
				mwgIDAndNum[i] = {'mwgID':mwgID,'sendNum':sendNum};
			}
			customerRemark = '';
			mwgIDAndNum = JSON.stringify(mwgIDAndNum);
//			$.ajax({
//			    type:'post',
//			    url:'checkAmount',
//			    async:false,
//			    data:{
//			    	'mwgIDAndNum':mwgIDAndNum,
//					'saID':saID
//			    },
//			    dataType:'json',
//			    success:function(data){
//			        if(data.state == 'success'){
//			            check_amount = true;
//			        }else if(data.state == 'error'){
//			            imp_msg(data.msg)
//			        }else if(data.state == 'failed'){
//			            imp_msg(data.msg)
//			        }
//			    }
//			});
//			if(!check_amount){
//				return 
//			}
//			mwgIDAndNum = mwgIDAndNum.replace('[','{');
//			mwgIDAndNum = mwgIDAndNum.replace(']','}')
//			return console.log(mwgIDAndNum)
			$.ajax({
				type:"post",
				url:"generateOrder",
				data:{
					'mwgIDAndNum':mwgIDAndNum,
					'saID':saID,
					'customerRemark':customerRemark
				},
				dataType:'json',
				success:function(data){
					if(data.state == 'success'){
	    				layer.msg('生成订单成功')
	    				setTimeout(function(){
	    					window.location= 'goMWOrder';
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
</script>