<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--登录-->
<c:if test="${customerID == null}">
<div id="layer-login">
	<h2>账号密码登录</h2>
	<div class="error-tip"></div>
	<input id="inpt-count" placeholder="请输入您的邮箱登录" />
	<input id="inpt-pw" type="password" placeholder="请输入您的密码" />
	<input type="button" id="login-this" value="登录" />
	<div class="other-operate">
		<a target="_blank" href="<%=basePath%>registerLogin/goRegister">注册新账号</a>
		<a class="forgot-pw" target="_blank" href="<%=basePath%>registerLogin/goResetPwd">忘记密码</a>
	</div>
	<script>
		$(function(){
			//登录
			$('#to-login').on('click', function() {
				to_login()
			})
			$('#inpt-count').on('blur', function() {
				var count = $(this).val();
				var reg = /^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/gi;
				if(!reg.test(count)) {
					$('.error-tip').text('请填入正确的账号');
				} else {
					$('.error-tip').text('');
				}
			})
			$('#inpt-pw').on('blur', function() {
				if($(this).val() == '') {
					$('.error-tip').text('请输入密码');
				}
			}).on('keydown',function(event){
				var e = event || window.event || arguments.callee.caller.arguments[0];
				if(e && e.keyCode == 13){
					login();
				}
			})
			$('#login-this').on('click', function() {
				login()
			})
		})
		console.log(${homeCategory})
	</script>
</div>
</c:if>
<div id="header" class="header-border">
	<div class="top-box">
		<div class="top">
		<c:choose>
			<c:when test="${customerID == null}">
				<i>您好！欢迎来到北极光跨境供应链</i>
				<a class="to-login" id="to-login" href="javascript:;">登录</a>
				<em>|</em>
				<a class="to-register" target="_blank" href="<%=basePath%>registerLogin/goRegister">免费注册</a>
				
			</c:when>
			<c:otherwise>
				<i>您好，会员${customerEmail}</i>
				<em>|</em>
				<i class="login-out" id="logout">退出登录</i>
				<script>
					$('#logout').on('click',function(){
						layer.confirm('确定退出登录？', function(index) {
							$.ajax({
								type:'POST',
								url:'../registerLogin/logout',
								success:function(data){
									if(data.state == 'success'){
										window.location.href = '<%=basePath%>'
									}else{
										console.log(data.msg)
									}
								},
								error:function(){
									layer.msg('网络超时，与服务器断开连接')
								}
							})
							layer.close(index)
						})
					})
				</script>
			</c:otherwise>
		</c:choose>
			<a class="help-center" target="_blank" href="<%=basePath%>footer/depositMode">帮助中心</a>
			<b onclick="go_customer_center()">个人中心</b>
		</div>
	</div>
	<div class="header-search">
		<div class="search-box">
			<a class="logo" href="<%=basePath%>"></a>
			<div class="ipt-box">
				<div class="ipt-left">
					<form method="get" action="../searchPage/search" id="searchFrom" name="searchFrom" class="form-inline" target="_blank">
						<select id="all-goods" name="searchCategory">
							<option value="">所有商品</option>
							<c:forEach items="${homeCategory}" var="Cate" varStatus="vs">
								<option value="${Cate.categoryName}">${Cate.categoryName}</option>
							</c:forEach>
						</select>
						<i class="boundary"></i>
						<input name="keyword" id="keyword" placeholder="输入产品名称、品牌，或询价"/>
						<input type="button" class="search-btn toSearchBtn" value="搜索" onclick="search();" />
						<input type="hidden" class="toSearchInput" value="${pd.shipType}" name="shipType" id="shipType" />
					</form>	
				</div>
				<a href="javascript:;" class="toSearchSupply" onclick="go_inquiry()">发布询价</a>
				<a href="javascript:;" class="my-cart" onclick="go_cart()">
					我的购物车
					<i>${customerCartNum != null ? (customerCartNum > 100 ? '99+' : customerCartNum) : '0'}</i>
				</a>
				<ul class="hot-word">
					<c:forEach items="${homeSearch}" var="val">
						<li>
							<a target="_blank" href="<%=basePath%>searchPage/search?keyword=${val}">${val}</a>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<div id="nav">
		<nav>
			<div class="all-cate">
				<i>全部商品分类</i>
				<div class="cate-box cate-box-none" id="cate-box-page">
					<ul class="cate-list">
						<c:forEach items="${homeCategory}" var="Cate" varStatus="vs">
							<li>
								<h1>
									<a target="_blank" href="<%=basePath%>searchPage/search?keyword=${Cate.categoryName}">
										<b class="icon-${Cate.categoryID}"></b>
										${Cate.categoryName}<i></i>
									</a>
								</h1>
								<p>
									<c:forEach items="${Cate.subcategory}" var="cate02" begin="0" end="2" step="1" varStatus="vs02">
										<a target="_blank" href="<%=basePath%>searchPage/search?keyword=${cate02.categoryName}">${cate02.categoryName}</a>
									</c:forEach>
								</p>
								<ul>
									<c:forEach items="${Cate.subcategory}" var="cate02">
										<li>
											<h1><a target="_blank" href="<%=basePath%>searchPage/search?keyword=${cate02.categoryName}">${cate02.categoryName}</a></h1>
											<div class="item-namebox">
												<c:forEach items="${cate02.subcategory}" var="cate03">
													<i><a target="_blank" <c:if test="${cate03.red == 1}">class="red"</c:if> href="<%=basePath%>searchPage/search?keyword=${cate02.categoryName}+${cate03.categoryName}">${cate03.categoryName}</a></i>
												</c:forEach>
											</div>
										</li>
									</c:forEach>
								</ul>
								<i style="top:${vs.index*72+1}px"></i>
							</li>
						</c:forEach>
						
					</ul>
				</div>
			</div>
			<a class="left" target="_blank" href="<%=basePath%>largeBuyPage/largeBuyList">大额采购</a>
			<a class="left" href="javascript:;" onclick="go_inquiry()">批发询价</a>
			<a class="left" href="javascript:;" onclick="want_delivery()">我要供货</a>
			<a class="center" href="javascript:;">|</a>
			<a target="_blank" href="<%=basePath%>searchPage/search?shipType=1">保税代发</a>
			<a target="_blank" href="<%=basePath%>searchPage/search?shipType=2">海外直邮</a>
			<a target="_blank" href="<%=basePath%>searchPage/search?shipType=3">一般贸易</a>
		</nav>
	</div>
</div>
<div class="layer-delivery" id="layer-delivery"><!--全站供货-->
	<h1>我要供货<i onclick="quit_want_delivery()"></i></h1>
	<div class="delivery-box">
		<h2>基本信息</h2>
		<div class="info-box">
			<label><i>* </i>公司名称：</label>
			<input class="info" placeholder="请输入公司名称"/>
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
		<!--//1.品牌方;2.总代理;3一级代理;4.普通链路-->
		<div class="info-box">
			<label><i>* </i>品牌授权：</label>
			<select class="info">
				<option value="">请选择授权方式</option>
				<option value="1">品牌方</option>
				<option value="2">总代理</option>
				<option value="3">一级代理</option>
				<option value="4">普通链路</option>
			</select>
		</div>
		<div class="info-box">
			<label><i>* </i>优势商品：</label>
			<input class="info" placeholder="请输入您的优势品牌"/>
		</div>
		<i class="btn-sub" onclick="sub_want_delivery()">提交供货</i>
		<i class="btn-sub quit-sub" onclick="quit_want_delivery()">取消</i>
	</div>
</div>

<script>
	flag_want_delivery = true
	function want_delivery(){//我要供货
		want_delivery_index = layer.open({
			type: 1,
			title: false,
			closeBtn: 0,
			scrollbar: false,
			skin: 'layer-box-shadow',
			area: '750px',
//			skin: 'layui-layer-nobg', //没有背景色
			content: $('#layer-delivery')
		});
	}
	function quit_want_delivery(){
		layer.close(want_delivery_index)
	}
	function sub_want_delivery(){
		if(!flag_want_delivery){
			return 
		}
		//supplyIntentionJson :{String convenientTime, String companyName, Integer chainPath, String contacts, String phone, String advantageBrand}
		var obj = {},arr_source = ['companyName','contacts','phone','convenientTime','chainPath','advantageBrand'];
		for(var i = 0;i < arr_source.length;i++){
			if($('#layer-delivery .info').eq(i).val() == ''){
				var info_str = $('#layer-delivery .info').eq(i).siblings('label').text()
					info_str = info_str.replace('* ','')
					info_str = info_str.replace('：','')
				return layer.msg('请完善'+info_str+'信息')
			}
			obj[arr_source[i]] = $('#layer-delivery .info').eq(i).val();
		}
		console.log(obj)
		supplyIntentionJson = JSON.stringify(obj)
		flag_want_delivery = false;
		$.ajax({
		    type:'post',
		    url:'../supplyIntention/addSupplyIntention',
		    data:{
		    	supplyIntentionJson:supplyIntentionJson
		    },
		    dataType:'json',
		    success:function(data){
		    	flag_want_delivery = true;
		    	console.log(data)
		        if(data.state == 'success'){
		        	layer.close(want_delivery_index)
		            layer.msg('提交成功')
		        }else if(data.state == 'error'){
		            layer.msg(data.msg)
		        }else if(data.state == 'failed'){
		            layer.msg(data.msg)
		        }
		    },
		    error:function(){
		    	flag_want_delivery = true;
		    }
		});
		console.log(obj)
	}
	$('.all-cate').on('mouseover',function(){
		$(this).children('.cate-box').removeClass('cate-box-none')
		
	}).on('mouseout',function(){
		$(this).children('.cate-box').addClass('cate-box-none')
	})
	$('.cate-list').children('li').on('mouseover',function(){
		var i = $(this).index();
	//	console.log(111)
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
</script>