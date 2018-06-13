//商品详情页

//邮费计算
function postage() {
	if(!$('#postage').length) {
		return
	}
	if($('.payway-box .active').index()) {
		return $('#postage').text('')
	}
	var province = $('#address-choose').val();
	var weight = $('#uWeight').text();
	var buyNum = $('#product-num').val();
	//				return console.log(buyNum)
	var shipType = $('#shipWay .active').find('i').text();
	$.ajax({
		type: 'post',
		url: 'getPosttage',
		data: {
			'province': province,
			'weight': weight,
			'buyNum': buyNum,
			'shipType': shipType
		},
		dataType: 'json',
		success: function(data) {
			var m = (data.posttage).toFixed(2);
			$('#postage').text('￥' + m)
		}
	})
}
function inquiry_p(i){
	var url_q = $(i).data('url')
	window.open(encodeURI(url_q));
}
function quota(num){
//	console.log($('.payway-box .active').data('pay'))
	if($('.quota').data('shiptype') == 3 || $('.payway-box .active').data('pay') == 2){
		return $('.quota').addClass('un-quota')
	}
	var num2 = parseInt($('.quota').data('num2')) + 1,
		p1 = parseFloat($('.quota').data('p1')),
		p2 = parseFloat($('.quota').data('p2')),
		p = 0;
	if(num < num2){
		p = p1 * num;
	}else{
		p = p2 * num;
	}
	console.log(p)
	if($('.quota').data('shiptype') != 3){
		if(p < 20000){
			$('.quota').addClass('un-quota');
		}else{
			$('.quota').removeClass('un-quota')
		}
	}else{
//		console.log(1)
//		if(p < 20000){
//			$('.quota').addClass('un-quota');
//		}else{
//			$('.quota').removeClass('un-quota')
//		}
	}
}
$(function() {
	setTimeout(function(){
		$.ajax({
		    type:'post',
		    url:'statisticGoodsDetail ',
		    data:{'goodsID':$('#m-product-data').data('id')},
		    dataType:'json',
		    success:function(data){
		        if(data.result == 'success'){
//		            layer.msg('成功')
		        }
		    }
		});
	},2000)
	postage()
	//放大镜开始
	var ione = $(".product-big"),
		ithe = $(".product-zoom"),
		itwo = $(".product-list img"),
		tthe = $("#Img img");
	//接收商品图片数组
	var productImg = [];
	for(var i = 0; i < $('#productImg img').length; i++) {
		productImg.push($('#productImg img').eq(i).attr('src'));
	}
	itwo.each(function(i) {
		$(this).on('mouseover', function() {
			$(".product-big img").attr("src", productImg[i])
			tthe.attr("src", productImg[i])
			itwo.removeClass("active")
			$(this).addClass("active")
		})
		ione.mousemove(function(a) {
			var evt = a || window.event
			ithe.css('display', 'block')
			var ot = evt.clientY - ($(".product-big").offset().top - $(document).scrollTop()) - 100;
			var ol = evt.clientX - ($(".product-big").offset().left - $(document).scrollLeft()) - 100;
			if(ol <= 0) {
				ol = 0;
			}
			if(ot <= 0) {
				ot = 0;
			}
			if(ol >= 200) {
				ol = 200
			}
			if(ot >= 200) {
				ot = 200
			}
			$("#productSpan").css({ 'left': ol, 'top': ot })
			var ott = ot / 400 * 800
			var oll = ol / 400 * 800
			tthe.css({ 'left': -oll, 'top': -ott })
		})
		ione.mouseout(function() {
			ithe.css('display', 'none')
		})
	})
	//放大镜结束
	//行业数据图标
	$.ajax({
	    type:'post',
	    url:'getIndustryData',
	    data:{
	    	'goodsID': $('#m-product-data').data('id')
	    },
	    dataType:'json',
	    success:function(data){
        	jpriceData = data.jpriceData
			tpriceData = data.tpriceData
			apriceData = data.apriceData
			tStoreData = data.tStoreData
			var option = {
				color: ['#ff3d3d', '#00a0e9', '#f603ff', '#00b419'],
				tooltip: {
					trigger: 'axis'
				},
				legend: {
					x: 'left',
					padding: [10, 20, 0, 20],
					data: ['淘宝售价', '京东售价', '本站售价', '淘宝在售商家'],
					selected: {}
				},
				grid: {
					left: '0',
					right: '3%',
					bottom: '3%',
					top: '13%',
					containLabel: true
				},
				xAxis: {
					type: 'category',
					boundaryGap: false,
					splitLine: { //网格线
						show: true,
						lineStyle: {
							color: ['#b1b1b1'],
							type: 'dashed'
						}
					},
					data: ['2017-1', '2017-2', '2017-3', '2017-4', '2017-5', '2017-6', '2017-7', '2017-8', '2017-9', '2017-10', '2017-11', '2017-12']
				},
				yAxis: {
					splitLine: { //网格线
						show: true,
						lineStyle: {
							color: ['#b1b1b1'],
							type: 'dashed'
						}
					}
				},
				series: [{
						name: '淘宝售价',
						type: 'line',
						data: tpriceData,
						label: {
							normal: {
								show: true,
								position: 'top' //值显示
							}
						}
					},

					{
						name: '京东售价',
						type: 'line',
						data: jpriceData,
						label: {
							normal: {
								show: true,
								position: 'top'
							}
						}
					},
					{
						name: '本站售价',
						type: 'line',
						data: apriceData,
						label: {
							normal: {
								show: true,
								position: 'top'
							}
						}
					},
					{
						name: '淘宝在售商家',
						type: 'line',
						data: tStoreData,
						label: {
							normal: {
								show: true,
								position: 'top'
							}
						}
					}
				]
			};
			//初始化echarts实例
			var myChart = echarts.init(document.getElementById('m-product-data'));
			//使用制定的配置项和数据显示图表
			myChart.setOption(option);
	    }
	});
	//立即购买全款、定金
	$('#buyNow').on('click', function() {
		customerStatus = customer()
		if(!customerStatus) {
			return to_login()
		}
		if($(this).parent().data('exw') == 1 && $(this).parent().data('exwnum') < $('#product-num').val()) {
			return imp_msg('大于'+$(this).parent().data('exwnum')+'件请询价')
		}
		if($('.payway-box .active').data('pay') == '1') {
			$('#fGoodsID').val($(this).parent().data('goodsid'))
			$('#fBuyNum').val($('#product-num').val())
			$('#goFSettleFLB').submit();
		} else {
			$('#dGoodsID').val($(this).parent().data('goodsid'))
			$('#dBuyNum').val($('#product-num').val())
			$('#goDSettleFLB').submit();
		}
	})

	//筛选排序
	$('#m-filterList').on('click', 'li', function() {
		$('#m-filterList li').removeClass('active');
		$(this).addClass('active');
	})
	//图片放大镜切换
	var imgLgh = $('#productImg img').length;
	if($('#productImg').width() <= 320) {
		$('#productImg').width(320)
	} else {
		$('#productImg').width(80 * imgLgh);
	}
	$('.icon-arrow-right').on('click', function() {
		var left = parseInt($('#productImg').css('left'));
		var w = $('#productImg').width() - 36
		if(w + left <= 320) {
		} else {
			$('#productImg').css('left', (left - 80) + 'px')
		}
	})
	$('.icon-arrow-left').on('click', function() {
		var left = parseInt($('#productImg').css('left'));
		var w = $('#productImg').width() - 36
		if(left >= 36) {

		} else {
			$('#productImg').css('left', (left + 80) + 'px')
		}
	})
	//商品数量加减
	var min = parseInt($('#product-num').attr('min'));
	var max = parseInt($('#product-num').attr('max'));
	$('#reduceNum').on('click', function() {
		var num = parseInt($('#product-num').val());
		if(num <= min) {
			num = min;
			imp_msg('本商品最小起订量为:' + min, { time: 3000 })
		} else {
			num--
		}
		return $('#product-num').val(num), postage(),quota(num);
	})
	$('#addNum').on('click', function() {
		var num = parseInt($('#product-num').val());
		//					console.log(typeof(max))
		if(num >= max) {
			num = max;
			if($('#product-num').data('maxtype') == 0){
				imp_msg('已达最大库存量'+max)
			}else{
				imp_msg('购买数量大于' + max + '请询价')
			}
		} else {
			num++;
		}
		
		return $('#product-num').val(num), postage(),quota(num);
	})
	//商品数量改变邮费事件
	$('#product-num').bind('change', function() {
		var num = parseInt($(this).val());
		if(num <= min) {
			num = min;
		} else if(num >= max) {
			num = max;
			if($('#product-num').data('maxtype') == 0){
				imp_msg('已达最大库存量'+ max)
			}else{
				imp_msg('购买数量大于' + max + '请询价')
			}
		}
		if(isNaN(num)) {
			num = min;
		}
		$('#product-num').val(num)
		postage()
		quota(num)
	})
	//省份改变邮费事件
	$('#address-choose').on('change', function() {
		postage()
	})
	//商品详情与行业数据切换
	$('#m-productbody-choose a').on('click', function() {
		var i = $(this).index();
		$('#m-productbody-choose a').removeClass('active');
		$('#m-productbody-box .m-productbody-b').removeClass('active');
		$(this).addClass('active');
		$('#m-productbody-box .m-productbody-b').eq(i).addClass('active');
	})

	//全款、定金切换
	$('.payway-box').on('click', 'span', function() {
		$('.payway-box span').removeClass('active');
		$(this).addClass('active');
		if($(this).index()) {
			$('#postage').text('')
		} else {
			postage()
		}
		console.log($('#product-num').val())
		quota($('#product-num').val())
	})
	//加入购物车
	$('#addShopCar').on('click', function(ev) {
		if($(this).parent().data('exw') == 1 && $(this).parent().data('exwnum') < $('#product-num').val()) {
			return imp_msg('大于'+$(this).parent().data('exwnum')+'件请询价')
		}
		var  ev=window.event||ev;  
		var xx = ev.clientX,yy = ev.clientY;
		customerStatus = customer()
		if(!customerStatus) {
			return to_login()
		}
		var goodsID = $(this).parent().data('goodsid');
		var category2ID = $(this).parent().data('category2id');
		var buyNum = $('#product-num').val();
		var shipType = $('#shipWay .active').find('i').text();
		var paymentType = $('.payway-box .active').data('pay');
		$.ajax({
			type: "POST",
			url: '../cartPage/addGToCart',
			data: {
				'goodsID': goodsID,
				'category2ID': category2ID,
				'buyNum': buyNum,
				'shipType': shipType,
				'paymentType': paymentType
			},
			dataType: "json",
			success: function(data) {
				console.log(data)
				if(data.state == 'success') {
					$('#cartImg-fly').css('left',xx);
					$('#cartImg-fly').css('top',yy);
//					console.log($('#cartImg-fly').css('left'))
					$('#cartImg-fly').css('display','block');
//					return 
					// 抛物线运动的触发
					parabola.init();
//					setTimeout(function(){
//						$('#cartImg-fly').css('display','none');
//					},500)
					layer.msg('添加成功！')
				} else {
					layer.msg(data.msg)
				}
			}
		})
	})
})