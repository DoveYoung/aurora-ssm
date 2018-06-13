function scroll_floor(){
	var sTop = $(window).scrollTop(),
		floor1 = $(".flag-floor[name='floor-0']").offset().top,
		floor2 = $(".flag-floor[name='floor-1']").offset().top,
		floor3 = $(".flag-floor[name='floor-2']").offset().top,
		floor4 = $(".flag-floor[name='floor-3']").offset().top,
		floor5 = $(".flag-floor[name='floor-4']").offset().top,
		floor6 = $(".flag-floor[name='floor-5']").offset().top,
		floor7 = $(".flag-floor[name='floor-6']").offset().top,
		floor8 = $(".flag-floor[name='floor-7']").offset().top;
	$('.floor_num').removeClass('active');
//	console.log(sTop)
	if(sTop>90){
		$('.header-search').addClass('header-search-active');
	}else{
		$('.header-search').removeClass('header-search-active');
	}
	if(sTop>750){
		$('#floor-list').css('display','block');
	}else{
		$('#floor-list').css('display','none');
	}
	if(sTop < floor1){
		
	}else if(sTop < floor2){
		$('.floor_num').eq(0).addClass('active')
	}else if(sTop < floor3){
		$('.floor_num').eq(1).addClass('active')
	}else if(sTop < floor4){
		$('.floor_num').eq(2).addClass('active')
	}else if(sTop < floor5){
		$('.floor_num').eq(3).addClass('active')
	}else if(sTop < floor6){
		$('.floor_num').eq(4).addClass('active')
	}else if(sTop < floor7){
		$('.floor_num').eq(5).addClass('active')
	}else if(sTop < floor8){
		$('.floor_num').eq(6).addClass('active')
	}else if(sTop < floor8+$(".flag-floor[name='floor-7']").height()){
		$('.floor_num').eq(7).addClass('active')
	}
}

$('.cate-list').children('li').on('mouseover',function(){
	var i = $(this).index();
	console.log(111)
//	$(this).children('h1').eq(0).css('border','0');
	if(i < $('.cate-list').children('li').length-1){
		$(this).next().children('h1').eq(0).addClass('h1-active');
	}
	
}).on('mouseout',function(){
	var i = $(this).index();
	if(i < $('.cate-list').children('li').length-1){
		$(this).next().children('h1').eq(0).removeClass('h1-active');
	}
})
hotsell_swiper = ['swiper_0','swiper_1','swiper_2',]
$('.goods-cate li').on('mouseover',function(){
	var i = $(this).index();
	$('.goods-cate li').removeClass('active');
	$('.cate-box').children('li').removeClass('active');
	$('.goods-cate li').eq(i).addClass('active');
	$('.cate-box').children('li').eq(i).addClass('active');
})
$('#LargeCargo li').on('mouseover',function(){
	var i = $(this).index();
	$('#LargeCargo li').removeClass('active');
	$('.table-box table').removeClass('active');
	$('#LargeCargo li').eq(i).addClass('active');
	$('.table-box table').eq(i).addClass('active')
})
directCountries = new Vue({//海外直邮，各国好货
	el:'#direct-countries',
	data:{
		direct:[],
		countries:[],
		left:0,
		right:0
	},
	mounted:function () {
		this.getAjax()
	},
	methods:{
		getAjax:function(){
			var self = this;
			$.ajax({
			    type:'post',
			    url:'home/getDirectPostAndCountriesGoods',
			    data:{},
			    async:false,
			    dataType:'json',
			    success:function(data){
//			    	console.log(data)
			        if(data.state == 'success'){
			      		self.direct = data.result.directPost;
			      		console.log(self.direct)
			      		self.countries = data.result.countriesGoods;
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
topBrand = new Vue({//热门品牌
	el:'#hotBrand',
	data:{
		pageNumber:1,
		brandList:[],
		maxPage:1,
		pageNumber:0,
		brandNo:0
	},
	mounted:function () {
		this.getAjax()
	},
	methods:{
		getAjax:function(){
			var self = this;
			if(this.pageNumber < this.maxPage){
				var pageNumber = this.pageNumber + 1;
			}else{
				var pageNumber = 1
			}
			$.ajax({
			    type:'post',
			    url:'home/getTopBrand',
			    data:{pageNumber:pageNumber},
			    dataType:'json',
			    success:function(data){
			    	console.log(data)
					var arr_id = []
			        if(data.state == 'success'){
			           self.brandList = data.result.topBrand;
			           self.maxPage = data.result.maxPage
			           self.pageNumber = data.result.pageNumber
			           for(i = 0;i < data.result.topBrand.length;i++){
        				Vue.set(self.brandList[i],'ate',false);
        				Vue.set(self.brandList[i],'msr',false);
        				arr_id.push(self.brandList[i].brand_id)
        			}
        				self.attentionTF(arr_id)
			        }else if(data.state == 'error'){
			            layer.msg(data.msg)
			        }else if(data.state == 'failed'){
			            layer.msg(data.msg)
			        }
			    }
			});
		},
		attentionTF:function(i){//关注验证，批量
			var self = this;
			if(customer_index()){//是否在线
				var i = i.join(',');
					console.log(i)
				$.ajax({
				    type:'post',
				    url:'customerAttention/judgeBrandCare',
				    data:{brandIDs:i},
				    dataType:'json',
				    success:function(data){
				    	console.log(data)
				        if(data.state == 'success'){
				            for(var j = 0;j < data.result.length;j++){
								self.brandList[j].ate = data.result[j]
							}
				        }else if(data.state == 'error'){
				            layer.msg(data.msg)
				        }else if(data.state == 'failed'){
				            layer.msg(data.msg)
				        }
				    }
				});
			}
		},
		attentionBrand:function(i,j){//关注
			var self = this;
			if(!customer_index()){//是否在线
				return to_login()
			}
			$.ajax({
			    type:'post',
			    url:'customerAttention/attentionBrand',
			    data:{brandID:i},
			    dataType:'json',
			    success:function(data){
			    	console.log(data)
			        if(data.state == 'success'){
			            self.hotBrand[j].ate = true;
						self.hotBrand[j].care_num = data.careNum
						layer.msg('关注成功！')
			        }else if(data.state == 'error'){
			            layer.msg(data.msg)
			        }else if(data.state == 'failed'){
			            layer.msg(data.msg)
			        }
			    }
			});
//			this.$http.post('../customerAttention/attentionBrand',{
//				brandID:i
//			},{
//			    emulateJSON:true
//			}).then(function(res){
////					console.log(res)
//				if(res.data.state == 'success'){
//					this.hotBrand[j].ate = true;
//					this.hotBrand[j].care_num = res.data.result
//					layer.msg('关注成功！')
//				}
//			},function(res){
//			    console.log(res.status);
//			});
		},
	}
})
bonded = new Vue({//保税仓热卖
	el:'#bonded',
	data:{
		keyword:[],
		goodList:[],
		bannerList:[],
	},
	mounted:function () {
		this.getAjax()
	},
	methods:{
		getAjax:function(){
			var self = this
			$.ajax({
			    type:'post',
			    url:'home/getHomeBonded',
			    data:{},
			    dataType:'json',
			    success:function(data){
//			    	console.log(data)
			        if(data.state == 'success'){
			           self.goodList = data.result.bondedGoodsList;
			           self.keyword = data.result.bondedKeyword.split(',')
			           self.bannerList = data.result.bondedSpecialList
//			           console.log(self.goodList)
			           	
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
floorList = new Vue({//楼层类目
	el:'#cate-floor',
	data:{
		floorList:[]
	},
	mounted:function () {
		this.getAjax()
	},
	methods:{
		getAjax:function(){
			var self = this;
			$.ajax({
			    type:'post',
			    url:'home/getHomeFloorList',
			    data:{},
			    async:false,
			    dataType:'json',
			    success:function(data){
			        if(data.state == 'success'){
			           	self.floorList = data.result;
//			           	console.log(self.floorList[0].floorBrandList)
			           	var list = self.floorList;
			           	console.log(list)
			           	return 
//			           	var swipers = {};
//			           	setTimeout(function(){
//						for(var i = 0;i < list.length;i++){
//							var id = '#floor-'+list[i].category1ID
//							var parentEl = '#floor-'+list[i].category1ID
//							var pagiEl = '.floor-pagination-'+list[i].category1ID
//							var nextEl = '.floor-next-'+list[i].category1ID
//							var preEl = '.floor-prev-'+list[i].category1ID
//							swipers[id] = new Swiper(parentEl, {
//								spaceBetween: 30,
//								centeredSlides: true,
//								observer:true,//修改swiper自己或子元素时，自动初始化swiper
//								observeParents:true,//修改swiper的父元素时，自动初始化swiper
//								autoplay: {
//									delay: 1000,
//									disableOnInteraction: true,
//								},
//								pagination: {
//									el: pagiEl,
//									clickable: true,
//								},
//								navigation: {
//									nextEl:nextEl,
//									prevEl:preEl
//								},
//							});
//							swipers[id].el.onmouseenter = function(e){
//								console.log(11)
////								var f = e.target.id
//								swipers[id].autoplay.stop();
//							}
//							swipers[id].el.onmouseleave = function(e){
////								var f = e.target.id
//								swipers[id].autoplay.start();
//							}
//						}
//						},500)
			        }else if(data.state == 'error'){
			            layer.msg(data.msg)
			        }else if(data.state == 'failed'){
			            layer.msg(data.msg)
			        }
			    }
			});
			scroll_floor()
		}
	}
})

