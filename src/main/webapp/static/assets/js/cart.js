function fix() {
	var bottom = $(document).height() - $(document).scrollTop() - $(window).height();
//				console.log(bottom)
	//可视区距页面底部
	if(bottom > 770) {
		$('.totalbarBox').addClass('totalbarFix');
	} else {
		$('.totalbarBox').removeClass('totalbarFix');
	}
}
function search() {
	if($('#keyword').val() == ''){
		return location.reload();
	}
	$("#searchFrom").submit();
}
$('.checkAll-box').on('click',function(){
	console.log(11)
	$(this).siblings('input[type=checkbox]').click()
})

$(document).scroll(function(){
	fix()
})
$(function(){
	
	var hlTotal = {payM:0.00,tax:0.00,limit:0},
		blTotal = {payM:0.00,tax:0.00,limit:0},
		glTotal = {payM:0.00,tax:0.00,limit:0};
		console.log(hLCart)
	new Vue({
		el:'.carBox',
		data:{
			deposit_TF:deposit_TF,
			hLCart:hLCart,//海外直邮
			bLCart:bLCart,//保税仓直邮
			gLCart:gLCart,//国内现货
			hlTotal:hlTotal,
			blTotal:blTotal,
			glTotal:glTotal,
			checkAll:false,
			arr_cke:['.hl-cke','.bl-cke','.gl-cke'],//三种方式多选框
			arr_type:['#hL','#bL','#gL'], //三种方式全选集合
			total_money:0.00,
			buyGoodsNum:0,
			total_tax:0.00
		},
		mounted:function () {
			fix()
		},
		methods:{
			good_check:function(ev,t){//点击商品选择框
				console.log(t)
				var self = ev.currentTarget
				if($(self).is(':checked')){
					$(self).parent().parent().addClass('active')
					var j = 0;
					$(this.arr_cke[t]).each(function() {
						if($(this).is(':checked')){
							j++
						}else{
							return false
						}
					})
//					console.log(j)
					console.log($(this.arr_cke[t]).length)
					if(j == $(this.arr_cke[t]).length){//是否满选
						$(this.arr_type[t]).prop("checked", true);
						var i = 0;
						$('.u-cke').each(function(){
							if($(this).is(':checked')){
								i++
							}else{
								return false
							}
						})
						if(i == $('.u-cke').length){
							this.checkAll = true
						}
					}
				}else{
					this.checkAll = false
					$(this.arr_type[t]).prop("checked", false);
					$(self).parent().parent().removeClass('active')
				}
				this.total_limit()
			},
			type_check:function(ev,t){//三种分类方式选择框
				var self = ev.currentTarget;
				if($(self).is(':checked')){
					$(this.arr_cke[t]).prop('checked',true)
					$(this.arr_cke[t]).parent().parent().addClass('active');
					var i = 0;
					$('.type-cke').each(function(){
						if($(this).is(':checked')){
							i++
						}else{
							return false
						}
					})
					if(i == $('.type-cke').length){
						this.checkAll = true
					}
				}else{
					this.checkAll = false
					$(this.arr_cke[t]).prop('checked',false)
					$(this.arr_cke[t]).parent().parent().removeClass('active');
				}
				this.total_limit()
			},
			check_all:function(ev){//点击全选
				this.checkAll = !this.checkAll
				var self = ev.currentTarget;
				if($(self).is(':checked')){
					$('.u-cke').prop('checked',true)
					$('.type-cke').prop("checked", true);
					$('.u-cke').each(function(){
						$(this).parent().parent().addClass('active');
					})
				}else{
					$('.u-cke').prop('checked',false)
					$('.type-cke').prop("checked", false);
					$('.u-cke').each(function(){
						$(this).parent().parent().removeClass('active');
					})
				}
				this.total_limit()
			},
			checkAll_box:function(ev){//模拟全选
				var self = ev.currentTarget;
				$(self).siblings('input[type=checkbox]').click()
			},
			total_limit:function(){//小计、总计和20000限额
				var self = this,
					obj_h = {payM:0,tax:0},
					i = 0;
				$('#German-box .gooditm').each(function(){//海外直邮
					if($(this).hasClass('active')){
						if(!self.deposit_TF){//全款统计
							obj_h.payM += self.hLCart[$(this).index()].uMoney
						}else{//定金统计
							obj_h.payM += self.hLCart[$(this).index()].uDMoney
						}
						obj_h.tax += self.hLCart[$(this).index()].tax
						i++
					}
				})
				this.hlTotal = Object.assign({},obj_h)
				var obj_b = {payM:0,tax:0};
				$('#wareHouse-box .gooditm').each(function(){//保税仓
					if($(this).hasClass('active')){
						if(!self.deposit_TF){
							obj_b.payM += self.bLCart[$(this).index()].uMoney
						}else{
							obj_b.payM += self.bLCart[$(this).index()].uDMoney
						}
						obj_b.tax += self.bLCart[$(this).index()].tax
						i++
					}
				})
				this.blTotal = Object.assign({},obj_b)
				var obj_g = {payM:0,tax:0};
				$('#theSpot-box .gooditm').each(function(){//国内现货
					if($(this).hasClass('active')){
						if(!self.deposit_TF){
							obj_g.payM += self.gLCart[$(this).index()].uMoney
						}else{
							obj_g.payM += self.gLCart[$(this).index()].uDMoney
						}
						obj_g.tax += self.gLCart[$(this).index()].tax
						i++
					}
				})
				this.glTotal = Object.assign({},obj_g)
				this.total_money = this.hlTotal.payM + this.blTotal.payM + this.glTotal.payM
				this.buyGoodsNum = i
				this.total_tax = this.hlTotal.tax + this.blTotal.tax + this.glTotal.tax
			},
			minus:function(i,j,ev){//减
				var ev = ev.currentTarget,
					num = this[i][j].buyNum-1;
				this.buy_num(i,j,num,$(ev).siblings('input'))
			},
			add:function(i,j,ev){//加
				var ev = ev.currentTarget,
					num = this[i][j].buyNum+1;
				this.buy_num(i,j,num,$(ev).siblings('input'))
			},
			change_num:function(i,j,ev){//直接修改
				var ipt = ev.currentTarget;
				this.buy_num(i,j,parseInt($(ipt).val()),$(ipt))
			},
			buy_num:function(i,j,k,ev){//修改商品数
				var self = this,
					goodsVo = {},
					ajax_url = self.deposit_TF ? 'changeCartDPGN' : 'changeCartFPGN';
				goodsVo.cartID = this[i][j].cartID
				goodsVo.buyNum = k
				goodsVo.goodsID = this[i][j].goodsID
				$.ajax({
				    type:'post',
				    url:ajax_url,
				    data:goodsVo,
				    dataType:'json',
				    success:function(data){
//				    	console.log(data)
				        if(data.state == 'success'){
				        	self[i][j].buyNum = data.result.buyNum
				        	self[i][j].tax = data.result.tax
				        	if(self.deposit_TF){//定金
				        		self[i][j].uDMoney = data.result.udmoney
				        		self[i][j].uFMoney = data.result.ufmoney
				        	}else{
				        		self[i][j].uMoney = data.result.umoney
				        	}
				        	self.total_limit()
				        }else if(data.state == 'error'){
				            layer.msg(data.msg)
				        }else if(data.state == 'failed'){
				            layer.msg(data.msg)
				        }
				    }
				});
			},
			delete_one:function(i,j,k){//单个删除
				arr = [[],[],[]];
				arr[i].push(j)
				this.delete_goods(k,arr);
			},
			delete_more:function(){//删除多个
				var self = this,
					arr_id = [],
					arr_index = [[],[],[]];
				$('#German-box .gooditm').each(function(){//海外直邮
					if($(this).hasClass('active')){
						arr_id.push(self.hLCart[$(this).index()].cartID)
						arr_index[0].push($(this).index())
					}
				})
				$('#wareHouse-box .gooditm').each(function(){//保税仓
					if($(this).hasClass('active')){
						arr_id.push(self.bLCart[$(this).index()].cartID)
						arr_index[1].push($(this).index())
					}
				})
				$('#theSpot-box .gooditm').each(function(){//国内现货
					if($(this).hasClass('active')){
						arr_id.push(self.gLCart[$(this).index()].cartID)
						arr_index[2].push($(this).index())
					}
				})
				if(!arr_id.length){
					return layer.msg('请选择要删除的商品')
				}
				self.delete_goods(arr_id.join(','),arr_index)
			},
			delete_noStock:function(){
				var self = this,
					arr_id = [],
					arr_index = [[],[],[]];
				$('#German-box .gooditm').each(function(){//海外直邮
					if(self.hLCart[$(this).index()].goodsStock <= 0){
						arr_id.push(self.hLCart[$(this).index()].cartID)
						arr_index[0].push($(this).index())
					}
				})
				$('#wareHouse-box .gooditm').each(function(){//保税仓
					if(self.bLCart[$(this).index()].goodsStock <= 0){
						arr_id.push(self.bLCart[$(this).index()].cartID)
						arr_index[1].push($(this).index())
					}
				})
				$('#theSpot-box .gooditm').each(function(){//国内现货
					if(self.gLCart[$(this).index()].goodsStock <= 0){
						arr_id.push(self.gLCart[$(this).index()].cartID)
						arr_index[2].push($(this).index())
					}
				})
				if(!arr_id.length){
					return layer.msg('亲,没有可删除的无货商品')
				}
			},
			delete_goods:function(cartIDs,arr_index){
				var self = this;
//				console.log(arr_index)
				delete_Index = layer.confirm("确定删除？", function(index) {
					$.ajax({
					    type:'post',
					    url:'bDeleteCartG',
					    data:{
					    	cartIDs:cartIDs
					    },
					    dataType:'json',
					    success:function(data){
					    	console.log(data)
					    	layer.close(delete_Index)
					        if(data.state == 'success'){
						        if(arr_index[0].length && arr_index[0].length){//海外直邮
									for(var i = arr_index[0].length-1;i >= 0;i--){
										self.hLCart.splice([arr_index[0][i]],1)
									}
								}
								if(arr_index[1].length && arr_index[1].length){//保税仓直邮
									for(var i = arr_index[1].length-1;i >= 0;i--){
										self.bLCart.splice([arr_index[1][i]],1)
									}
								}
								if(arr_index[2].length && arr_index[2].length){//国内现货
									for(var i = arr_index[2].length-1;i >= 0;i--){
										self.gLCart.splice([arr_index[2][i]],1)
									}
								}
								self.checkAll = false
								self.total_money = 0;
								self.total_tax = 0;
								self.buyGoodsNum = 0;
								$('.gooditm').removeClass('active')
								$("input[type='checkbox']").prop("checked", false)
								fix()
					            layer.msg('删除成功')
					        }else if(data.state == 'error'){
					            layer.msg(data.msg)
					        }else if(data.state == 'failed'){
					            layer.msg(data.msg)
					        }
					    },
					    error:function(){
					    	layer.msg('网络超时')
					    	layer.close(delete_Index)
					    }
					});
				})
			},
			settlement:function(i){
//				console.log(this.hlTotal.payM)
				$('#hCartIDs').val('');
				$('#bCartIDs').val('');
				$('#gCartIDs').val('');
				if(!this.deposit_TF && i == 1){//全款立即购买20000限额
					if(this.hlTotal.payM > 20000){
						return imp_msg('海外直邮订单超过¥20000，不能通过海关发货，请选择分批发货或通过立即采购存入微仓')
					}
					if(this.blTotal.payM > 20000){
						return imp_msg('保税仓直邮订单超过¥20000，不能通过海关发货，请选择分批发货或通过立即采购存入微仓')
					}
				}
				var self = this 
					hCartIDs = [],
					bCartIDs = [],
					gCartIDs = [];
				$('#German-box .gooditm').each(function(){//保税仓
					if($(this).hasClass('active')){
						if(self.hLCart[$(this).index()].buyNum > self.hLCart[$(this).index()].goodsStock){
							return imp_msg('海外直邮：'+self.hLCart[$(this).index()].goodsName+'商品数量超过库存无法结算！')
						}
						hCartIDs.push(self.hLCart[$(this).index()].cartID)
					}
				})
				hCartIDs = hCartIDs.join(',')
				if(hCartIDs == ''){
					hCartIDs = null;
				}
				$('#wareHouse-box .gooditm').each(function(){//保税仓
					if($(this).hasClass('active')){
						if(self.bLCart[$(this).index()].buyNum > self.bLCart[$(this).index()].goodsStock){
							return imp_msg('保税仓直邮：'+self.bLCart[$(this).index()].goodsName+'商品数量超过库存无法结算！')
						}
						bCartIDs.push(self.bLCart[$(this).index()].cartID)
					}
				})
				bCartIDs = bCartIDs.join(',')
				if(bCartIDs == ''){
					bCartIDs = null;
				}
				$('#theSpot-box .gooditm').each(function(){//保税仓
					if($(this).hasClass('active')){
						if(self.gLCart[$(this).index()].buyNum > self.gLCart[$(this).index()].goodsStock){
							return imp_msg('国内现货：'+self.gCartIDs[$(this).index()].goodsName+'商品数量超过库存无法结算！')
						}
						gCartIDs.push(self.gLCart[$(this).index()].cartID)
					}
				})
				gCartIDs = gCartIDs.join(',')
				if(gCartIDs == ''){
					gCartIDs = null;
				}
//				alert(gCartIDs)
				if(hCartIDs == null && bCartIDs == null && gCartIDs == null){
					return layer.msg('请先选择商品！')
				}
				if(i == 1 || i == 3){//全款 立即购买 &&定金立即采购
					$('#hCartIDs').val(hCartIDs)
					$('#bCartIDs').val(bCartIDs)
					$('#gCartIDs').val(gCartIDs)
					$('#goFSettleFC').submit()
					console.log(hCartIDs,bCartIDs,gCartIDs)
				}else if(i == 2){
					$('#hCartIDs-2').val(hCartIDs)
					$('#bCartIDs-2').val(bCartIDs)
					$('#gCartIDs-2').val(gCartIDs)
					$('#goFSettleFC-2').submit()
					console.log(hCartIDs,bCartIDs,gCartIDs)
				}
			}
		}
	})
}) 