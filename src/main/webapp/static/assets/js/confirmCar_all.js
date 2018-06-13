function settlement() {
	var shipBox = ['#German-box', '#wareHouse-box', '#theSpot-box'];
	var ship_type = ['', '', ''];
	var saID = $('.address-active').find('b').text();
	for(var i = 0; i < 3; i++) {
		if($(shipBox[i]).find('li')) {
			for(var j = 0; j < $(shipBox[i]).find('li').length; j++) {
				ship_type[i] += ',' + $(shipBox[i]).find('li').eq(j).find('.hidden').text();
			}
			ship_type[i] = ship_type[i].substring(1);
		}
		//					console.log(ship_type[i])
	}
	//订单提交预处理
	var quoteVo = {};
	quoteVo.hCartIDs = ship_type[0];
	quoteVo.bCartIDs = ship_type[1];
	quoteVo.gCartIDs = ship_type[2];
	quoteVo.saID = saID;
	$.ajax({
		type: 'post',
		url: 'addFOrderFSC',
		data: quoteVo,
		dataType: 'json',
		success: function(data) {
			console.log(data);
//			return 
			if(data.state == 'success') {
				console.log(data.result)
				$('#orderID-pay').val(data.result)
				$('#goPay').submit();
			} else if(data.state == 'error') {
				layer.msg(data.msg)
			} else if(data.state == 'failed') {
				layer.msg(data.msg)
			}
		}
	});
}
//地址变更后的邮费计算
function addr_change() {
	var shipBox = ['#German-box', '#wareHouse-box', '#theSpot-box'];
	var ship_type = ['', '', ''];
	var saID = $('.address-active').find('b').text();
	for(var i = 0; i < 3; i++) {
		if($(shipBox[i]).find('li')) {
			for(var j = 0; j < $(shipBox[i]).find('li').length; j++) {
				ship_type[i] += ',' + $(shipBox[i]).find('li').eq(j).find('.hidden').text();
			}
			ship_type[i] = ship_type[i].substring(1);
		}
		//					console.log(ship_type[i])
	}
	hCartIDs = ship_type[0];
	bCartIDs = ship_type[1];
	gCartIDs = ship_type[2];
	$.ajax({
		type: 'post',
		url: 'getFSettleInfoFC',
		data: {
			'saID': saID,
			'bCartIDs': bCartIDs,
			'hCartIDs': hCartIDs,
			'gCartIDs': gCartIDs
		},
		dataType: 'json',
		success: function(data) {
			console.log(data)
			if(data.state == 'success') {
				var data = data.result
				$('#payment').text('￥' + data.tMoney)
				$('#postage').text('￥' + data.tPostage)
				$('#tPayment').text('￥' + data.pMoney)

				if(data.shipAddr.address_type != 4) {
					$('.b-quote').eq(0).text('');
					$('.b-quote').eq(1).text('');
					$('.b-post').eq(0).text('邮费:￥0');
					$('.b-post').eq(0).text('邮费:￥0');
				} else {
					if(data.bCartFPMath != null) {
						var b_quote = data.btMoney+data.bPostage;
						var b_post = data.bPostage;
						
						if(b_quote > 20000) {
							$('.b-quote').eq(1).text('已超出海关单笔订单￥20000限额，请返回购物车分开购买或存入北极光微仓')
						} else {
							$('.b-quote').eq(1).text('')
						}
						$('.b-post').eq(1).text('邮费:￥' + b_post);
					}
					if(data.hCartFPMath != null) {
						var h_quote = data.htMoney+data.hPostage;
						var h_post = data.hPostage;
						if(h_quote > 20000) {
							$('.b-quote').eq(0).text('已超出海关单笔订单￥200000限额，请返回购物车分开购买或存入北极光微仓')
						} else {
							$('.b-quote').eq(0).text('')
						}
						$('.b-post').eq(0).text('邮费:￥' + h_post);
					}
				}
			} else if(data.state == 'error') {
				layer.msg(data.msg)
			} else if(data.state == 'failed') {
				layer.msg(data.msg)
			}
		}
	});
}
$(function() {
	addr_page(1);
	$('#flip').on('click','i',function(){
		var pageNum = $('#flip').data('page');
		if($(this).index() == 0){
			$('#flip').data('page',pageNum - 1)
		}else{
			$('#flip').data('page',pageNum + 1)
		}
		addr_page(1)
	})
	function mobile_show(i) {
		var str = i.text();
		var str2 = str.substr(0, 3) + "****" + str.substr(7);
		i.text(str2)
	}
	mobile_show($('#info-show i').eq(4));
	//搜索
	$('#search-address').on('click', function() {
		search_address()
	})
	var now_h = -1;
	$('#address-keyword').on('keyup', function(event) {
		now_h = get_addr(now_h)
	})
	$('.search-box').on('mouseleave', function() {
		$('#likeAddrBox').css('display', 'none')
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
	$('#address-box').on('click', '.address-check', function() {
		var addr = address_c($(this));
		var list = addr();
					console.log(list)
		$('#info-show i').eq(0).text(list.name);
		$('#info-show i').eq(1).text(list.province);
		$('#info-show i').eq(2).text(list.city);
		$('#info-show i').eq(3).text(list.area);
		$('#info-show i').eq(4).text(list.detail_address);
		$('#info-show i').eq(5).text(list.mobile);
		if($(this).parent().index()) {
			mobile_show($('#info-show i').eq(5));
		}
		addr_change()
	})
	//新增收货地址请求
	$('#add-address').on('click', function() {
		customer()
		if(!customerStatus) {
			return to_login()
		}
		$.ajax({
			type: "post",
			url: "../areaAddr/goAddShipAddr",
			success: function(data) {
				console.log(data)
				if(data.state == 'success') {
					$('#add-province').html('');
					var province = data.province;
					console.log(province)
					var child = '<option>选择省/直辖市</option>';
					for(var i = 0; i < province.length; i++) {
						child += "<option value='" + province[i].area_id + "' pinyin='" + province[i].area_value + "'>" + province[i].area_name + "</option>"
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
	$("#add-province").change(function() {
		var areaID = $(this).val();
		change_region(areaID, '#add-city', '#add-area');
	})
	//选择市
	$("#add-city").change(function() {
		var areaID = $(this).val();
		change_region(areaID, '#add-area');
	})
	//保存新增地址
	$('.save-add').on('click', function() {
		add_addr()
//		var name = $('#add-name').val();
//		var mobile = $('#add-mobile').val();
//		var province = $('#add-province option:selected').text();
//		var provincePin = $('#add-province option:selected').attr('pinyin');
//		var city = $('#add-city option:selected').text();
//		var Area = $('#add-area option:selected').text();
//		if(Area == '') {
//			Area = city;
//		}
//		var detailAddr = $('#add-detail-address').val();
//		var IDCard = $('#add-id-card').val();
//		$.ajax({
//			type: "POST",
//			url: "../areaAddr/addShipAddr",
//			data: {
//				'name': name,
//				'mobile': mobile,
//				'province': province,
//				'provincePin': provincePin,
//				'city': city,
//				'area': Area,
//				'detailAddr': detailAddr,
//				'IDCard': IDCard
//			},
//			dataType: "json",
//			success: function(data) {
//				console.log(data)
//				if(data.state == 'success') {
//					var addr = data.pd;
//					$('#new-address').html('');
//					var child_i = addr.name + '  ' + addr.province + addr.city + addr.detailAddr,
//						child = "<a href='javascript:;' class='address-check' title='"+child_i+"'>"
//						        		+"<span><i>寄送给</i></span>"
//						        		+"<em></em>"
//						        		+"<input type='radio' name='address' />"
//						        		+"<i>"+child_i+"</i>"
//						        		+"<b class='hidden'>"+(data.newShipAddr).sa_id+"</b>"
//					        		+"</a>"
//					$('#new-address').append(child);
//					//模拟点击
//					$('#address-box #new-address').find('a').trigger('click');
//					layer.close(addIndex);
//				}
//			}
//		})

	})
	//取消保存
	$('.quit-add').on('click', function() {
		layer.close(addIndex);
	})
	//提交订单
	$('#z-submit').on('click', function() {
		//			return console.log(customerID);
		customer()
		if(!customerStatus) {
			return to_login()
		}
		settlement()
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
		            var addr = data.result;
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
		console.log(info)
		$.ajax({
		    type:'post',
		    url:'../receiverAddress/updateShipAddr',
		    data:info,
		    dataType:'json',
		    success:function(data){
		    	console.log(data)
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
})