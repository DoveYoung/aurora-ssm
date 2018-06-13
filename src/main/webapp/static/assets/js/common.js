//layer 提示信息 右侧 黄色背景
function tips(i,j){
	layer.tips(i, j, {
		tips: [2, '#FFA500']
	})
}
//重要提示信息 点击按钮关闭
function imp_msg(i){
	layer.msg(i,{
		time:false,
  		closeBtn:1
	});    
}
//全选，取消全选函数封装
function check_box(i,j){
	$(i).on('click',function(){
		if($(this).is(':checked')){
			//全选
			$(j).each(function(){
				$(this).prop("checked",true);

			})
		}else{
			//撤销全选
			$(j).each(function(){
				$(this).prop("checked",false);
			})
		}
	})
	//td-checked 判定
	$(j).on('click',function(){
		//取消全选
		if($(this).is(':checked')){
			var check = false
			$(j).each(function(){
				if(!$(this).is(':checked')){
					return check = false;
				}else{
					return check = true;
				}	
			})
			if(check){
				$(i).prop("checked",true);
			}		
		}else{
			$(i).prop("checked",false);
		}
	})
}

//登录显示
function to_login(){
	layer.open({
		type: 1,
		title: false,
		closeBtn: 0,
		scrollbar: false,
		area: '390px',
		skin: 'layui-layer-nobg', //没有背景色
		shadeClose: true,
		content: $('#layer-login')
	});
}
function login(i){
	if($('#inpt-count').val() == ''){
		return layer.msg('请输入账号！')
	}
	if($('#inpt-pw').val() == ''){
		return layer.msg('请输入密码！')
	}
	if(i == 1){
		var url_l = 'registerLogin/requestLogin'
	}else{
		var url_l = '../registerLogin/requestLogin'
	}
	var customerEmail = $('#inpt-count').val();
	var customerPassword = $('#inpt-pw').val();
	console.log(1)
	$.ajax({
		type: "POST",
		url: url_l,
		data: {
			customerEmail: customerEmail,
			customerPassword:customerPassword
		},
		dataType: 'json',
		success: function(data){
			if(data.state == 'success'){
				window.location.reload();
			}else if(data.state == 'usererror'){
				return layer.msg('账号错误！')
			}else if(data.state == 'passworderror'){
				return layer.msg('密码错误！')
			}
		}
	});
}
//获取session中的登录状态
//首页获取
function customer_index(){
	$.ajax({
		type:'post',
		url:'registerLogin/getSession',
		async:false,
		success:function(data){
			console.log(data)
			if(data.state == 'success'){
				customerStatus = true; 
			}else{			
				customerStatus = false;
				$('#customerIDinpt').val('')
			}
		}
	})
	return customerStatus
}
function customer(){
	$.ajax({
		type:'post',
		url:'../registerLogin/getSession',
		async:false,
		success:function(data){
//			console.log(data)
			if(data.state == 'success'){
				customerStatus = true; 
			}else{			
				customerStatus = false;
				$('#customerIDinpt').val('')
			}
		}
	})
	return customerStatus
}
//地址翻页
function addr_page(i){
	var pageNum = $('#flip').data('page'),
		mw = i;
	$.ajax({
	    type:'post',
	    url:'../receiverAddress/get3ShipAddr',
	    data:{
	    	'pageNum':pageNum,
	    	'mw':mw
	    },
	    dataType:'json',
	    success:function(data){
	        if(data.result == 'success'){
	            $('#addr-ul').html('');
	            var addr_li = '';
	            if(pageNum == 1){
	            	$('#flip i').eq(0).addClass('dis');
	            	$('#alter-province-new').html('');
					var province = data.provinceList;
					var child = "<option value=''>选择省</option>";
					for(var i = 0; i < province.length; i++) {
						child += "<option value='" + province[i].area_id + "' pinyin='" + province[i].area_value + "'>" + province[i].area_name + "</option>"
					}
					$('#alter-province-new').append(child);
					if(mw == 1){
						addr_li += "<li class='address-list'>"
					        			+"<a href='javascript:;' class='address-check' title='"+data.wc1Address.name+' '+data.wc1Address.province+data.wc1Address.city+data.wc1Address.area+data.wc1Address.detail_address+"'>"
							        		+"<span><i>寄送给</i></span>"
							        		+"<em></em>"
							        		+"<input type='radio' name='address' />"
							        		+"<i>"+data.wc1Address.name+' '+data.wc1Address.province+data.wc1Address.city+data.wc1Address.area+data.wc1Address.detail_address+"</i>"
							        		+"<b class='hidden'>"+data.wc1Address.sa_id+"</b>"
						        		+"</a>"
						        	+"</li>"
					}
	            }else{
	            	$('#flip i').eq(0).removeClass('dis');
	            }
	            if(data.maxPageNum > pageNum){
	            	$('#flip i').eq(1).removeClass('dis');
	            }else{
	            	$('#flip i').eq(1).addClass('dis');
	            }
	            
	            var list = data.shipAddress5,
	            	new_addr = data.newShipAddr;
	            	console.log(list)
	            for(var i = 0;i < list.length;i++){
	            	addr_li += "<li class='address-list'>"
				        			+"<a href='javascript:;' class='address-check' title='"+list[i].name+' '+list[i].province+list[i].city+list[i].area+list[i].detail_address+"'>"
						        		+"<span><i>寄送给</i></span>"
						        		+"<em></em>"
						        		+"<input type='radio' name='address' />"
						        		+"<i>"+list[i].name+' '+list[i].province+list[i].city+list[i].area+list[i].detail_address+"</i>"
						        		+"<b class='hidden'>"+list[i].sa_id+"</b>"
					        		+"</a>"
					        	+"</li>"
	            }
	            $('#addr-ul').append(addr_li);
	            $('#new-address').html('');
//	            console.log(new_addr)
	            var addr_new = "<a href='javascript:;' class='address-check' title='"+new_addr.name+' '+new_addr.province+new_addr.city+new_addr.area+new_addr.detail_address+"'>"
					        		+"<span><i>寄送给</i></span>"
					        		+"<em></em>"
					        		+"<input type='radio' name='address' />"
					        		+"<i>"+new_addr.name+' '+new_addr.province+new_addr.city+new_addr.area+new_addr.detail_address+"</i>"
					        		+"<b class='hidden'>"+new_addr.sa_id+"</b>"
				        		+"</a>"
	            $('#new-address').append(addr_new)
	            $('#addr-ul').find('li').eq(0).find('a').click();	            
	        }else if(data.result == 'error'){
	            layer.msg(data.msg)
	        }else if(data.result == 'failed'){
	            layer.msg(data.msg)
	        }
	    }
	});
}
//结算页面搜索地址
function search_address(){
	var keyword = $('#address-keyword').val();
	var customerID = $('#customerIDinpt').val();
//	if(!keyword){
//		return layer.msg('请输入搜索关键词')
//	}
	$.ajax({
		type:'POST',
		url:'../areaAddr/likeShipAddr',
		data:{
			'customerID':customerID,
			'keyword':keyword
		},
		dataType:'json',
		success:function(data){
			console.log(data);
			if(data.result == 'success'){
				$('#likeAddrBox').html('')
				var Addr = data.likeShipAddr;
				var child = ''
				for(var i = 0;i < Addr.length;i++){
					child += "<li><i>"+ Addr[i].name +'  '+ Addr[i].province + Addr[i].city + Addr[i].area + Addr[i].detail_address +"</i><b class='hidden'>"+ Addr[i].sa_id +"</b></li>"
				}
				$('#likeAddrBox').append(child);
				$('#likeAddrBox').css('display','block')
			}
		}
	})
}
//搜索地址键盘事件
function get_addr(now_h){
	$('#likeAddrBox').css('display','block')
	var e = event || window.event || arguments.callee.caller.arguments[0];
	//up
	if(e.keyCode == 38){
		e.preventDefault()
		now_h --;
//					console.log(now_h)
		$('#likeAddrBox li').removeClass('li-h');
		if(now_h == -2){
			now_h = $('#likeAddrBox li').length -1;
			$('#likeAddrBox li').eq(now_h).addClass('li-h');
		}else{
			$('#likeAddrBox li').eq(now_h).addClass('li-h');
			now_h = $('#likeAddrBox .li-h').index();
			
		}
		var container = $('#likeAddrBox');
		scrollTo = $('#likeAddrBox .li-h');
		container.scrollTop(
		    scrollTo.offset().top - container.offset().top + container.scrollTop()
		);
//					console.log(now_h)
	}else if(e.keyCode == 40){
		now_h ++ ;
		$('#likeAddrBox li').removeClass('li-h');
//					console.log(now_h)
		if(now_h == $('#likeAddrBox li').length){
			$('#likeAddrBox li').eq(0).addClass('li-h');
			now_h = 0;
		}else{
			$('#likeAddrBox li').eq(now_h).addClass('li-h');
			now_h = $('#likeAddrBox .li-h').index();
			scrollTo = $('#likeAddrBox .li-h');
		}
		var container = $('#likeAddrBox');
		scrollTo = $('#likeAddrBox .li-h');
		container.scrollTop(
		    scrollTo.offset().top - container.offset().top + container.scrollTop()
		);
	}else if(e.keyCode == 13){
		now_h = -1;
		$('#likeAddrBox').find('.li-h').eq(0).click();
	}else{
	 	search_address()
	}
	return now_h
}
//保存新增地址
function add_addr(){
	if( $('#add-area').val() == null || $('#add-area').val() == ''){
		return tips('请完善地区信息','#add-area');
	}
	if( $('#add-detail-address').val() == ''){
		return tips('请填写详细地址','#add-detail-address');
	}
	if( $('#add-name').val() == ''){
		return tips('请填写收货人姓名','#add-name');
	}
	if( $('#add-mobile').val() == ''){
		return tips('请填写联系电话','#add-mobile');
	}
	if( $('#add-id-card').val() == ''){
		return tips('请填写联系电话','#add-id-card');
	}
	var info = {};
	info.name = $('#add-name').val();
	info.mobile = $('#add-mobile').val();
	info.province = $('#add-province option:selected').text();
	info.provincePin = $('#add-province option:selected').attr('pinyin');
	info.city = $('#add-city option:selected').text();
	info.area = $('#add-area option:selected').text();
	info.detailAddr = $('#add-detail-address').val();
	info.IDCard = $('#add-id-card').val();
	$.ajax({
		type: "POST",
		url: "../areaAddr/addShipAddr",
		data:info,
		dataType: "json",
		success: function(data) {
//			console.log(data)
			if(data.result == 'success'){
				layer.msg('添加新地址成功')
				var addr = data.pd;
				$('#new-address').html('');
				var child_i = addr.name + '  ' + addr.province + addr.city + addr.detailAddr,
				child = "<a href='javascript:;' class='address-check' title='"+child_i+"'>"
				        		+"<span><i>寄送给</i></span>"
				        		+"<em></em>"
				        		+"<input type='radio' name='address' />"
				        		+"<i>"+child_i+"</i>"
				        		+"<b class='hidden'>"+(data.newShipAddr).sa_id+"</b>"
			        		+"</a>"
				$('#new-address').append(child);
				//模拟点击
				$('#address-box #new-address').find('a').trigger('click');
				layer.close(addIndex);
			}
			if(data.result == 'failed'){
				layer.msg('添加地址失败。')
			}
		}
	})
}
//商品数量改变函数
function inpt_num(i){
	var num = parseInt(i.val());
	var min = parseInt(i.attr('min'));
	var max = parseInt(i.attr('max'));
	if(num<=min){
		num = min;
	}else if(num>=max){
		num = max;
	}
	if(isNaN(num)){
		num = min;
	}
	i.val(num);
}
//选择 收货地址 获取信息
function address_c(i){
	var addr
	saID = i.find('b').text();
	i.find('input').prop("checked", true);
	$('.address-check').removeClass('address-active');
	i.addClass('address-active');
	if(!saID){
		return
	}
	$.ajax({
		type:'POST',
		url:'../areaAddr/getSAByID',
		async:false,
		data:{
			'saID':saID
		},
		dataType:'json',
		success:function(data){		
//			console.log(data)
			if(data.state == 'success'){	
				addr = data.result;
				$('#c-name').val(addr.name)
				$('#c-mobile').val(addr.mobile)
				$('#c-province').val(addr.province)
				$('#c-city').val(addr.city)
				$('#c-area').val(addr.area)
				$('#detail-address').val(addr.detail_address)
				$('#id-card').val(addr.id_card)
				return addr
			}
		}
	})
	return addrs = function(){
		var a = addr;
		return a
	}
}
//	选择省市区
function change_region(areaID,x,j){
	$(x).html('');
	var box = $(x);
	$(j).html('');
	var str = '';
	if(j == undefined){
		str = '区';
	}else{
		str = '市'
	}
	$.ajax({
		type:'post',
		url:'../areaAddr/getUArea',
		data:{
			'areaID':areaID
		},
		dataType:'json',
		success:function(data){
			if(data.result == 'success'){
				var area = data.area;						
				var child = "<option value=''>选择"+str+"</option>";
				for(var i = 0;i < area.length;i++){
					child += "<option value='" + area[i].area_id + "'>"+ area[i].area_name +"</option>"
				}
				box.append(child)
			}else if(data.result == 'error'){
				layer.msg('查询区域错误！')
			}
		}
	});
}
//去购物车判断
function go_cart(i){
	if(i == 1){
		if(!customer_index()){
			return to_login();
		}
	}else{
		if(!customer()){
			return to_login();
		}
	}
	var curPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var basePath = curPath.substring(0,curPath.indexOf(pathName)) + "/";
//	return console.log(basePath)
	if(basePath == 'http://localhost:8080/'){
		window.open(basePath+'aurorascm/cartPage/goCartPage');
	}else{
		window.open(basePath+'cartPage/goCartPage');
	}
}
//去个人中心判断
function go_customer_center(i){
	if(i == 1){
		if(!customer_index()){
			return to_login();
		}
	}else{
		if(!customer()){
			return to_login();
		}
	}
	var curPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var basePath = curPath.substring(0,curPath.indexOf(pathName)) + "/";
//	return console.log(basePath)
	if(basePath == 'http://localhost:8080/'){
		window.open(basePath+'aurorascm/personal/myOrder');
	}else{
		window.open(basePath+'personal/myOrder');
	}
}
//找货源 --询价
function go_inquiry(i){
	if(i == 1){
		if(!customer_index()){
			return to_login();
		}
	}else if(!customer()){
		return to_login();
	}
	var curPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var basePath = curPath.substring(0,curPath.indexOf(pathName)) + "/";
	if(basePath == 'http://localhost:8080/'){
		window.open(basePath+'aurorascm/customerInquiry/goAddInquiryPage');
	}else{
		window.open(basePath+'customerInquiry/goAddInquiryPage');
	}
}
//首页 大额采购询价
function inquiry_index(i){
	if(!customer_index()){
			return to_login();
	}else{
		window.open($(i).data('src'));
	}
	
}
function inquiry_no_index(i){
	if(!customer()){
			return to_login();
	}else{
		window.open($(i).data('src'));
	}	
}
function scroll_top(){
	$("html,body").animate({scrollTop:0},300);
}
