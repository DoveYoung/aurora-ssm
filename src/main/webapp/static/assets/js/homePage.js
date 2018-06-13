var layero_video;

function go_inquirys(i) {
	var good_id = $(i).attr('goodID');
	window.open(basePath + 'customerInquiry/goAddInquiryPage');
}

function industry_brand(i) {
	$('#keyword').val($(i).text());
	search()
}

function brand_name(j) {
	var i = $(j).index();
	$(j).parent().find('b').removeClass('wholesale-active');
	$(j).addClass('wholesale-active');
	$(j).parent().parent().find('.small-wholesale-table').removeClass('active');
	$(j).parent().parent().find('.small-wholesale-table').eq(i).addClass('active');
}

function wholesale_more(i, j) {
	if(!j) {
		$('#keyword').val($(i).data('cate'));
		search();
	} else {
		$('#keyword').val($(i).data('brand'));
		search();
	}
}
//视频点击
//播放&暂停
function video_p(){
	//jq 不识别play()
	var player = document.getElementById('vd');
	clearTimeout(video_time)
	video_time = setTimeout(function(){
        if(!player.paused){
            player.pause();
        }else{
            player.play(); 
        }
        monitor(3)
	},300)
}
function props(){
	var e=arguments.callee.caller.arguments[0] || event;//这里是因为除了IE有event其他浏览器没有所以要做兼容
    if(window.event){       //这是IE浏览器
        e.cancelBubble=true;//阻止冒泡事件
        e.returnValue=false;//阻止默认事件
    }else if(e && e.stopPropagation){     //这是其他浏览器
        e.stopPropagation();//阻止冒泡事件
        e.preventDefault();//阻止默认事件
    }
}
//关闭拖拽
function drag_close(i){
	var player = document.getElementById('vd');
	player.pause()
	$('#drag-box').removeClass('suspension');
	var e=arguments.callee.caller.arguments[0] || event;//这里是因为除了IE有event其他浏览器没有所以要做兼容
    if(window.event){       //这是IE浏览器
        e.cancelBubble=true;//阻止冒泡事件
        e.returnValue=false;//阻止默认事件
    }else if(e && e.stopPropagation){     //这是其他浏览器
        e.stopPropagation();//阻止冒泡事件
        e.preventDefault();//阻止默认事件
    }
}
//全屛收缩判断
function video_fullScreen(i){
	var player = document.getElementById('vd');
	clearTimeout(video_time);
	if(!isFullscreenEnabled()){
//		layer.full(layero_video)
		var id_v = '#layui-layer'+layero_video;
		console.log(typeof(id_v))
		console.log($($(id_v)).width)
//		console.log($('#vd').parent())
//		player.width = $('#vd').parent()[0].width = $(document.body).width();
//	    player.height = $('#vd').parent()[0].height = $(document.body).height();
//	    	return 
		if (player.requestFullscreen) {
	   	 	player.requestFullscreen();
	    } else if (player.mozRequestFullScreen) {
	        player.mozRequestFullScreen();
	    } else if (player.webkitRequestFullScreen) {
	        player.webkitRequestFullScreen();
	    }else if (!!window.ActiveXObject || "ActiveXObject" in window){
	    	
	    	
	    }
	}else{
		if (document.exitFullscreen) {
	        document.exitFullscreen();
	    } else if (document.mozCancelFullScreen) {
	        document.mozCancelFullScreen();
	    } else if (document.webkitCancelFullScreen) {
	        document.webkitCancelFullScreen();
	    }
    }
//		document.webkitCancelFullScreen()
//	}
}
function isFullscreenEnabled(){
     return document.fullscreenElement    	||
           document.msFullscreenElement  	||
           document.mozFullScreenElement 	||
           document.webkitFullscreenElement || false;
}
//var docElm = document.documentElement; 
////W3C
//if(docElm.requestFullscreen){
//docElm.requestFullscreen();
//}
////FireFox
//else if (docElm.mozRequestFullScreen) {
//docElm.mozRequestFullScreen();
//}
////Chrome等
//else if (docElm.webkitRequestFullScreen) {
//docElm.webkitRequestFullScreen();
//}
////IE11
//else if (elem.msRequestFullscreen) {
//elem.msRequestFullscreen();
//}
//
////退出全屏
//
//if (document.exitFullscreen) {
//	document.exitFullscreen();
//}else if (document.mozCancelFullScreen) {
//	document.mozCancelFullScreen();
//}else if (document.webkitCancelFullScreen) {
//	document.webkitCancelFullScreen();
//}else if (document.msExitFullscreen) {
//	document.msExitFullscreen();
//}
//首页监测

$(function() {
	//本站热卖
	$.ajax({
		type: 'post',
		url: 'homePage/getHSiteHS',
		dataType: 'json',
		success: function(data) {
			if(data.result == 'success') {
				var cate_l = data.category1,
					list = data.hotSellList,
					cate_child = "<div class='goods-category'>"
					ws_child = '';
				
				for(var i = 0;i < cate_l.length;i++){
					if(i==0){
						cate_child += "<a href='javascript:;' style='color: #272b88;'>"+cate_l[i].category_name+"</a>"
					}else{
						cate_child += "<a href='javascript:;'>"+cate_l[i].category_name+"</a>"
					}
				}
				for(var i = 0; i < list.length; i++) {
					var child_c01 = "<ul class='goods-category-l'>";
					var child_c02 = "</ul><ul class='goods-category-r'>";
					var marks = ''
					if(i<=1){
						marks = "<div class='marks'></div>"
					}
					for(var j = 0; j < list[i].length; j++) {
						if(j < 4) {
							child_c01 += "<li class='brick-item-m'>" + marks +
								"<a class='goods-name' target='_blank' onclick='monitor(15)' href='" + basePath + "detailPage/goGoodsdetail?goodsID=" + list[i][j].goods_id + "' title='" + list[i][j].goods_name_new + "'>" +
								"<img src='" + list[i][j].advertise_map + "' />" +
								"</a>" +
								"<h6></h6>" +
								"<h5><a target='_blank' href='" + basePath + "detailPage/goGoodsdetail?goodsID=" + list[i][j].goods_id + "'>" + list[i][j].goods_name_new + "</a><i>￥" + list[i][j].goods_price2 + "</i></h5>" +
								"</li>"
						} else {
							child_c02 += "<li class='brick-item-m'>" + marks +
								"<a class='goods-name' onclick='monitor(15)' target='_blank' href='" + basePath + "detailPage/goGoodsdetail?goodsID=" + list[i][j].goods_id + "' title='" + list[i][j].goods_name_new + "'>" +
								"<img src='" + list[i][j].advertise_map + "' />" +
								"</a>" +
								"<h6></h6>" +
								"<h5><a target='_blank' href='" + basePath + "detailPage/goGoodsdetail?goodsID=" + list[i][j].goods_id + "'>" + list[i][j].goods_name_new + "</a><i>￥" + list[i][j].goods_price2 + "</i></h5>" +
								"</li>"
						}
					}
					if(i == 0) {
						ws_child += "<div class='goods-category-list category-active'>" + child_c01 + child_c02 + "</ul></div>"
					} else {
						ws_child += "<div class='goods-category-list'>" + child_c01 + child_c02 + "</ul></div>"
					}
				}
				$('#website-list').append(cate_child+"</div>"+ws_child);
			} else if(data.result == 'error') {
				layer.msg(data.msg)
			} else if(data.result == 'failed') {
				layer.msg(data.msg)
			}
		}
	});
	//品牌
	$('#brank-list-box').on('mouseenter', '.brank-list-pic', function() {
		var brank_list_hover = $(this).find('.brank-list-hover');
		var brandID = brank_list_hover.attr('brandID');
		if(!customer_index()) {
			return brank_list_hover.find('.attention-in').replaceWith("<span class='attention-in' onclick='monitor(16)'>进入</span>");
		}
		$.ajax({
			type: 'GET',
			url: 'customerAttention/judgeBrandCare?brandID=' + brandID,
			cache: true, //缓存
			dataType: 'json',
			headers: {
				'Cache-Control': 'CACHE'
			},
			success: function(data) {
				if(data.result == 'success') {
					brank_list_hover.find('.attention-in').replaceWith("<span class='attention-in' onclick='monitor(16)'>进入</span>")
					brank_list_hover.find('b').replaceWith('<b>已关注</b>')
				} else if(data.result == 'failed') {
//					brank_list_hover.find('.attention-in').text('关注并进入');
//					brank_list_hover.find('.attention-in').addClass('attention-in-active');
				}
			},
			complete: function(xhr, textStatus) {
//		        console.log(xhr.status);
		    } 
		});
	})
	//关注
	$('#brank-list-box').on('click', '.attention', function() {
		if(!customer_index()) {
			return to_login()
		}
		var brandID = $(this).parent().attr('brandID');
		//return console.log(brandID)
		var box_atn = $(this);
		$.ajax({
			type: "post",
			url: "customerAttention/attentionBrand",
			data: { 'brandID': brandID },
			dataType: 'json',
			success: function(data) {
//				console.log(data)
				if(data.result == 'success') {
					layer.msg('关注成功');
					box_atn.siblings('h5').text(data.careNum + '人已关注该品牌');
					box_atn.siblings('.attention-in').replaceWith("<span class='attention-in' onclick='monitor(16)'>进入</span>");
					box_atn.replaceWith('<b>已关注</b>');
					//										box_atn.text()
				} else if(data.result == 'error') {
					layer.msg(data.msg)
				} else if(data.result == 'failed') {
					layer.msg(data.msg)
				}
			}
		});
	})
	//关注并进入 attention-in
	$('#brank-list-box').on('click', '.attention-in', function() {
		var brandName = $(this).parent().attr('brandName');
		$('#keyword').val(brandName);
		$('.index-search').click();
//		if(!customer_index()) {
//			$('#keyword').val(brandName);
//			search()
//			return
//		}
//		var brandID = $(this).parent().attr('brandID');
//		var box_atn = $(this).siblings('.attention');
//		if($(this).hasClass('attention-in-active')) {
//			$.ajax({
//				type: "post",
//				url: "customerAttention/attentionBrand",
//				async: false,
//				data: { 'brandID': brandID },
//				dataType: 'json',
//				success: function(data) {
//					if(data.result == 'success') {
//						layer.msg('关注成功,即将进入');
//						box_atn.siblings('h5').text(data.careNum + '人已关注该品牌')
//						box_atn.siblings('.attention-in').replaceWith("<span class='attention-in' onclick='monitor(16)'>进入</span>")
//						box_atn.replaceWith('<b>已关注</b>');
//						$('#keyword').val(brandName);
//						setTimeout(function() {
//							$('.index-search').click();
//						}, 500)
//					} else if(data.result == 'error') {
//						layer.msg(data.msg)
//					} else if(data.result == 'failed') {
//						layer.msg(data.msg)
//					}
//				}
//			})
//		} else {
//			$('#keyword').val(brandName);
//			search();
//		}
	})
	//热门品牌 换一批
	hot_brand = 0;
	hot_brand_click = 0;
	$('#hot-brand-change').on('click', function() {
		if(hot_brand_click != 0){
			monitor(16)
		}
		hot_brand_click++;
		hot_brand++;
		$.ajax({
			type: "POST",
			url: "homePage/changeHotBrand",
			data: {
				'pageNum': hot_brand
			},
			dataType: "json",
			success: function(data) {
				var bpn = parseInt(data.hbMaxPN);
				if(hot_brand >= bpn) {
					hot_brand = 0;
				}
				$('#brank-list-box').html('');
				var hotBrand = data.hotBrand;
				var box = '';
				for(var i = 0; i < hotBrand.length; i++) {
					box += "<div class='brank-list-pic'>" +
						"<div class='brank-list-hover' brandID='" + hotBrand[i].brand_id + "' brandName='" + hotBrand[i].brand_name + "'>" +
						"<b class='attention' onclick='monitor(16)'>+关注</b>" +
						"<h5>" + hotBrand[i].care_num + "人已关注该品牌</h5>" +
						"<span class='attention-in' onclick='monitor(16)'>进入</span>" +
						"</div>" +
						"<a href='" + "<%=basePath%>" + "detailPage/goGoodsdetail?category2ID=" + hotBrand[i].category2_id + "&goodsID=" + hotBrand[i].goods_id + "'title='" + hotBrand[i].brand_name +
						"'>" +
						"<img src='" + hotBrand[i].recommend_map + "' />" +
						"</a>" +
						"<i>" + hotBrand[i].brand_describe1 + "</i>" +
						"</div>"
				}
				$('#brank-list-box').html(box);
			}
		})
	})
	$('#hot-brand-change').click();
	//新货推荐 换一批
	new_recommend = 0;
	new_recommend_click = 0;
	$('#new-recommend-change').on('click', function() {
		if(new_recommend_click != 0){
			monitor(17)
		}
		new_recommend++;
		new_recommend_click++
		$.ajax({
			type: "GET",
			url: "homePage/changeHomeNewGoods",
			data: {
				'pageNum': new_recommend
			},
			dataType: "json",
			success: function(data) {
//				console.log(data)
				var npn = parseInt(data.ngMaxPN);
				if(new_recommend >= npn) {
					new_recommend = 0;
				}
				$('#new-recommend-box').html('');
				var box = '';
				var data = data.homeNewGoods
				for(var i = 0; i < data.length; i++) {
					box += "<div class='recommend-list-pic brick-item-m'>" +
						"<div class='price-contrast review-wrapper'>" +
						"<h3>京东售价：<i>￥" + data[i].jd_price + "</i></h3>" +
						"<h3>淘宝平均售价：<i>￥" + data[i].tb_price + "</i></h3>" +
						"<h3>淘宝在售商家：<i>" + data[i].sell_store_num + "家</i></h3>" +
						"</div>" +
						"<a target='_blank' onclick='monitor(17)' href='" + basePath + "detailPage/goGoodsdetail?category2ID=" + data[i].category2_id + "&goodsID=" + data[i].goods_id + "'title='" + data[i].goods_name_new +
						"'><img src='" + data[i].main_map + "'></a><h4>￥" + data[i].goods_price2 + "</h4><h5>" + data[i].goods_name_new + "</h5></div>"
				}
				$('#new-recommend-box').html(box);
			}
		})
	})
	$('#new-recommend-change').click();
	//大额采购 小额批发
	$.ajax({
		type: 'post',
		url: 'homePage/getHBHLL',
		dataType: 'json',
		success: function(data) {
//			console.log(data)
			if(data.result == 'success') {
				var arr_id = ['', 'Beauty-cosmetics', 'Household', 'Nutritional-health', 'Digital-appliances', 'Overseas-direct', 'Hardware-bathroom'];
				var homeBHotSell = data.homeBHotSell;
				var bh = '';
				var homeHHotSell = data.homeHHotSell;
				var hh = '';
				var purchase = data.homePurchase;
				var list_p = '';
				//保税仓
				for(var i = 0; i < homeBHotSell.length; i++) {
					bh += "<div class='warehouse-list-pic'>" +
						"<a class='good-img' onclick='monitor(18)' target='_blank' href='" + basePath + "detailPage/goGoodsdetail?goodsID=" + homeBHotSell[i].goods_id + "' title='" + homeBHotSell[i].goods_name_new + "' >" +
						"<img src='" + homeBHotSell[i].main_map + "' />" +
						"</a>" +
						"<h4>￥" + homeBHotSell[i].goods_price2 + "<i>上月交易￥" + homeBHotSell[i].month_turnover + "</i></h4>" +
						"<h5><a target='_blank' onclick='monitor(18)' href='" + basePath + "detailPage/goGoodsdetail?goodsID=" + homeBHotSell[i].goods_id + "' title='" + homeBHotSell[i].goods_name_new + "'>" + homeBHotSell[i].goods_name_new + "</a></h5>" +
						"</div>"
				}
				$('.warehouse-list').append(bh);
				//海外直邮
				for(var i = 0; i < homeHHotSell.length; i++) {
					var marks_h = '';
					if( homeHHotSell[i].brand_id == 70 || homeHHotSell[i].brand_id == 76){
						marks_h = "<div class='marks'></div>"
					}
					hh += "<div class='directMail-list-pic'>" + marks_h +
						"<a class='good-img' onclick='monitor(19)' target='_blank' href='" + basePath + "detailPage/goGoodsdetail?goodsID=" + homeHHotSell[i].goods_id + "' title='" + homeHHotSell[i].goods_name_new + "' >" +
						"<img src='" + homeHHotSell[i].main_map + "' />" +
						"</a>" +
						"<h4>￥" + homeHHotSell[i].goods_price2 + "<i>上月交易￥" + homeHHotSell[i].month_turnover + "</i></h4>" +
						"<h5><a onclick='monitor(19)' target='_blank' href='" + basePath + "detailPage/goGoodsdetail?goodsID=" + homeHHotSell[i].goods_id + "' title='" + homeHHotSell[i].goods_name_new + "'>" + homeHHotSell[i].goods_name_new + "</a></h5>" +
						"</div>"
				}
				$('.directMail-list').append(hh);
				for(var i = 0; i < purchase.length; i++) {
					//大额采购
					var child_p = "<section id='" + arr_id[i] + "' class='wholesale-table cate-list-color'>";
					var child_p_01 = "<div class='main-box'>" +
						"<div class='bulk-purchasing'>" +
						"<div class='list-title'>" +
						data.category1[i].category_name +
						"<a class='see-more' onclick='monitor("+(20+2*i)+")' target='_blank' href='" + basePath + "largeBuyPage/largeBuyList'>查看更多</a>" +
						"</div>" +
						"<h3 class='head-tit'>大额采购</h3>" +
						"<ul class='tr-ul'><li>产品名称</li><li>最小起订量</li><li>自提价</li><li>操作</li></ul>" +
						"<div class='bulk-purchasing-table'>" +
						"<table class='table table-condensed'>" +
						"<tbody>"
					var child_p_01_list = '';
					var list01 = purchase[i][0];
					for(var j = 0; j < list01.length; j++) {
						child_p_01_list += "<tr>" +
							"<td class='name-td'><a onclick='monitor("+(20+2*i)+")' target='_blank' href='" + basePath + "detailPage/goGoodsdetail?goodsID=" + list01[j].goods_id + "' title='" + list01[j].goods_name_new + "'>" + list01[j].goods_name_new + "</a></td>" +
							"<td>" + list01[j].num + list01[j].norm + "</td>" +
							"<td>￥" + list01[j].exw + "</td>" +
							"<td>" +
							"<a class='see-detail' href='javascript:;' onclick='inquiry_index(this);monitor("+(20+2*i)+")' goodID='" + list01[j].goods_id + "' data-src='" + basePath + "customerInquiry/goAddInquiryPage?goodsID=" + list01[j].goods_id + "'>询价</a>" +
							"</td>" +
							"</tr>"
					}
					list_p += child_p + child_p_01 + child_p_01_list + child_p_01_list + "</tbody></table></div></div>"
					//小额批发
					var child_w = "<div class='small-wholesale'><div class='list-title brand-choose'>" +
						"<b class='wholesale-active' onmouseover='brand_name(this)'>热门品牌</b>"+
						"<b onmouseover='brand_name(this)'>" + data.lessBuyBrand[i][0].brand_name + "</b>" +
						"<b onmouseover='brand_name(this)'>" + data.lessBuyBrand[i][1].brand_name + "</b>" +
						"<b onmouseover='brand_name(this)'>" + data.lessBuyBrand[i][2].brand_name + "</b></div>" 
						
					var child_p_02_list = '';
					var list02 = purchase[i][1];
					var s_class = '';
					var s_ship_type = '';
					for(var j = 0; j < list02.length; j++) {
						var list02_tab = list02[j]
						if(j == 0) {
							var brand_name = 0;
							var barnds_name = '';
							s_class = "small-wholesale-table active"
						} else {
							s_class = "small-wholesale-table";
							var brand_name = 1;
//							console.log(data.lessBuyBrand[i][j-1].brand_name)
							var barnds_name = data.lessBuyBrand[i][j-1].brand_name;
						}
						child_p_02_list += "<div class='" + s_class + "'>" +
							"<table class='table table-condensed'>" +
							"<caption>小额批发<a href='javascript:;' class='seeMoreBrand' onclick='monitor("+(21+2*i)+");wholesale_more(this," + brand_name + ")' data-brand='" + barnds_name + "' data-cate='" + data.category1[i].category_name + "' >查看更多 >></a></caption>" +
							"<thead><tr>" +
							"<th style='width:240px'>产品名称</th><th>价格</th><th>起订量</th><th>发货方式</th><th>操作</th>" +
							"</tr></thead><tbody>"
						for(var k = 0; k < list02_tab.length; k++) {
							if(list02_tab[k].ship_type == 1) {
								s_ship_type = '保税仓';
							} else if(list02_tab[k].ship_type == 2) {
								s_ship_type = '海外直邮';
							} else if(list02_tab[k].ship_type == 3) {
								s_ship_type = '国内现货';
							}
							child_p_02_list += "<tr>" +
								"<td class='name-td'><a onclick='monitor("+(21+2*i)+")' target='_blank' href='" + basePath + "detailPage/goGoodsdetail?goodsID=" + list02_tab[k].goods_id + "' title='" + list02_tab[k].goods_name_new + "'>" + list02_tab[k].goods_name_new + "</a></td>" +
								"<td>￥" + list02_tab[k].goods_price2 + "</td>" +
								"<td>" + list02_tab[k].rnum2 + "件</td>" +
								"<td>" + s_ship_type + "</td>" +
								"<td>" +
								"<a class='see-detail' onclick='monitor("+(21+2*i)+")' target='_blank' href='" + basePath + "detailPage/goGoodsdetail?goodsID=" + list02_tab[k].goods_id + "'>查看</a>" +
								"</td>" +
								"</tr>"
						}
						child_p_02_list += "</tbody>" +
							"</table>" +
							"</div>"

					}
					list_p += child_w + child_p_02_list + "</div></div></section>"
				}
				$('#bs-sections').replaceWith(list_p);
			} else if(data.result == 'error') {
				layer.msg(data.msg)
			} else if(data.result == 'failed') {
				layer.msg(data.msg)
			}
		}
	});
	//菜单类目点击搜索事件
	$('.search-category').on('click', function() {
		if($(this).hasClass('search-cate01')) {
			$('#keyword').val($(this).text());
		} else {
			$('#keyword').val($(this).parent().parent().siblings('h1').text() + ' ' + $(this).text());
		}
		search();
	})
	//保税仓查看更多
	$('#seeMoreWarehouse').on('click', function() {
		$('#keyword').val('');
		$('#shipType').val('1');
		$('#searchFrom').submit();
		$('#shipType').val('');
	})
	//海外直邮查看更多
	$('#seeMoreDirectMail').on('click', function() {
		$('#keyword').val('');
		$('#shipType').val('2');
		$('#searchFrom').submit();
		$('#shipType').val('');
	})
	//播放视频
	//	$('#playVideo').on('click', function() {
	//		playVideo();
	//	})
	//	$('.introduce-video').on('click', function() {
	//		playVideo();
	//	})
	$('.Catalog').on('mouseover', function() {
		$('.menu-items-mark').css('display', 'block');
		$('#menu-items').css('display', 'block');
	})
	$('.Catalog').on('mouseout', function() {
		$('.menu-items-mark').css('display', 'none');
		$('#menu-items').css('display', 'none');

	})
	//一级菜单
	$('#first-items').on('mouseover', 'li', function() {
		var i = $(this).index();
		$('#first-items li').removeClass('first-items-active');
		$('#second-items li').removeClass('second-items-active');
		$(this).addClass('first-items-active');
		$('#second-items li').eq(i).addClass('second-items-active');
	})
	//定义颜色
	var arr_c = ['#272b88','#e20873','#fb99ad', '#a599da', '#c1a89b', '#91b846', '#8fa8c3', 'deepskyblue', '#f5ac45'];
	$('#website-list').on('mouseover', '.goods-category a', function() {
		var i = $(this).index();
		$('.goods-category a').css('color', '#000');
		$('.goods-category-list').removeClass('category-active');
		$('.goods-category a').eq(i).css('color', arr_c[i]);
		$('.goods-category-list').eq(i).addClass('category-active');
	})
	//回到顶部
	$('#toTop').on('click', function() {
		var sc = $(window).scrollTop();
		$('body,html').animate({ scrollTop: 0 }, 200);
	})
})