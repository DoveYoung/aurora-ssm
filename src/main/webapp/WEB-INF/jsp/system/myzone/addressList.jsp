<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>个人中心--收货地址</title>
		<%@ include file="../index/headLink.jsp"%>
		<script>
			function search() {
				if($('#keyword').val() == ''){
					return location.reload();
				}
				$("#searchFrom").submit();
			}
		</script>
	</head>

	<body id="product-body">
		
		<!--header-->
		<div id="search-header">			
			<%@ include file="../index/topLogin.jsp"%>			
			<%@ include file="../index/headerSearch.jsp"%>
		</div>
		<%@ include file="../index/shipType.jsp"%>
		<form method="post" action="addressList" name="addressList" id="addressList" class="form-inline">
		<div id="myZone">
			<%@ include file="../myzone/myzoneMenuList.jsp"%>
			<div id="zone-content">
				<h2 id="order_c"><b>收货地址</b></h2>
				<div class="address-title">新增收货地址</div>
				<div class="new-info new-info-select">
					<i><b>*</b>所在地区</i>
					<select class="new-info-box" id="new-province">
						<option value=''>请选择省</option>
						<c:forEach items="${provinceList}" var="pro">
							<option value="${pro.area_id}" provincePin="${pro.area_value}">${pro.area_name}</option>
						</c:forEach>
					</select>
					<select class="new-info-box" id="new-city"></select>
					<select class="new-info-box" id="new-area"></select>
				</div>
				<div class="new-info new-info-textarea">
					<i><b>*</b>详细地址</i>
					<textarea class="new-info-box" id="new-detail-address"></textarea>
				</div>
				<div class="new-info">
					<i><b>*</b>收货人姓名</i>
					<input class="new-info-box" id="new-name" placeholder="不能为昵称、x先生、x小姐等，请使用真实姓名"/>
				</div>
				<div class="new-info">
					<i><b>*</b>手机号码</i>
					<input class="new-info-box" id="new-mobile" placeholder="手机号码、电话号码选填一项"/>
				</div>
				<!--<div class="new-info">
					<i>电话号码</i>
					<input class="new-info-box" style="width:118px" placeholder="区号"/>
					<input class="new-info-box" style="width:178px" placeholder="电话号码"/>
					<input class="new-info-box" style="width:128px" placeholder="分机号码（可选）"/>
				</div>-->
				<div class="new-info">
					<i><b>*</b>身份证号码</i>
					<input class="new-info-box" id="new-id-card" placeholder="海关检查必须要有身份证验证"/>
				</div>
				<div class="save-new-address">保存新地址</div>
				<div class="address-title">已保存地址</div>
				<div class="all-box">
					<input type="checkbox" name="checkAll" class="checkAll" />
					<i class="checkAll-box">全选</i>
					<i class="delete_all">删除选中项</i>
					<!--<b class="import-template">导入模板</b>
					<b class="download-template">下载模板</b>-->
				</div>
				<div class="address-box">
					<table class="table table-condensed" style="margin-bottom:0">
						<thead>
							<tr>
								<th style="width:30px"></th>
								<th></th>
								<th style="text-align: left;">收货人</th>
								<th style="width:260px">收货地址</th>
								<th>联系电话</th>
								<th>身份证号码</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${addressList}" var="adr">
								<tr saID="${adr.sa_id}" class="adr-tr">
									<td></td>
									<td><input type="checkbox" name="check_address"/></td>
									<td>${adr.name}</td>
									<td>${adr.province}${adr.city}${adr.area}${adr.detail_address}</td>
									<td class="center">${adr.mobile}</td>
									<td class="center">${adr.id_card}</td>
									<td class="center"><b class="alter-adr">修改</b><b> | </b><b class="delete-adr">删除</b></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="all-box">
					<input type="checkbox" name="checkAll" class="checkAll" />
					<i class="checkAll-box">全选</i>
					<i class="delete_all">删除选中项</i>
				</div>
				<%@ include file="../commons/page.jsp"%>
			</div>
			
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
		<script>
			function search_addressList(){
				$('#addressList').submit();
			}
			function goPage(pageNo) {
				$('#currentPage').val(pageNo);
				var fromIndex = (pageNo - 1) * $('#pageSize').val();
				if(fromIndex < 0) {
					fromIndex = 0;
				}
				$('#fromIndex').val(fromIndex);
				search_addressList()
			}
			//删除地址 单个、批量
			function delete_adr(saID){
				$.ajax({
				    type:'post',
				    url:'deleteShipAddr',
				    data:{'saID':saID},
				    dataType:'json',
				    success:function(data){
				        if(data.result == 'success'){
				            layer.msg('删除成功')
				            setTimeout(function(){
				            	window.location.reload();
				            },500)
				        }else if(data.result == 'error'){
				            layer.msg(data.msg)
				        }else if(data.result == 'failed'){
				            layer.msg(data.msg)
				        }
				    }
				});
			}
			//选中 全选
			function checked_adr(i,j){
				$(i).on('click',function(){
					if($(this).is(':checked')){
						$(i).prop("checked",true);
						//全选
						$(j).each(function(){
							$(this).prop("checked",true)
							$(this).parent().parent().addClass('adr-tr-active')
						})
					}else{
						$(i).prop("checked",false);
						//撤销全选
						$(j).each(function(){
							$(this).prop("checked",false)
							$(this).parent().parent().removeClass('adr-tr-active')
						})
					}
				})
				//td-checked 判定
				$(j).on('click',function(){
					//取消全选
					if($(this).is(':checked')){
						var check = false
						$(this).parent().parent().addClass('adr-tr-active')
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
						$(this).parent().parent().removeClass('adr-tr-active');
						$(i).prop("checked",false);
					}
				})
			}
			$(function(){
				checked_adr("input[name='checkAll']","input[name='check_address']");
				$('.checkAll-box').on('click',function(){
					$(this).siblings('input').click();
				})
				//添加新地址
				$('.save-new-address').on('click',function(){
					if( $('#new-area').val() == null || $('#new-area').val() == ''){
						return tips('请完善地区信息','#new-area');
					}
					if( $('#new-detail-address').val() == ''){
						return tips('请填写详细地址','#new-detail-address');
					}
					if( $('#new-name').val() == ''){
						return tips('请填写收货人姓名','#new-name');
					}
					if( $('#new-mobile').val() == ''){
						return tips('请填写联系电话','#new-mobile');
					}
					if( $('#new-id-card').val() == ''){
						return tips('请填写联系电话','#new-id-card');
					}
					var newInfo = {}
					newInfo.name = $('#new-name').val();
					newInfo.mobile = $('#new-mobile').val();
					newInfo.province = $('#new-province option:selected').text();
					newInfo.provincePin = $('#new-province option:selected').attr('provincePin');
					newInfo.city = $('#new-city option:selected').text();
					newInfo.area = $('#new-area option:selected').text();
					newInfo.detailAddr = $('#new-detail-address').val();
					newInfo.IDCard = $('#new-id-card').val();
					$.ajax({
					    type:'post',
					    url:'../areaAddr/addShipAddr',
					    data:newInfo,
					    dataType:'json',
					    success:function(data){
					        if(data.result == 'success'){
					            layer.msg('添加新地址成功');
					            setTimeout(function(){
					            	window.location.reload();
					            },500)
					        }else if(data.result == 'error'){
					            layer.msg(data.msg)
					        }else if(data.result == 'failed'){
					            layer.msg(data.msg)
					        }
					    }
					});
				})
				//删除地址
				$('.delete-adr').on('click',function(){
					var saID = $(this).parent().parent().attr('saID');
//					return console.log(saID)
					layer.confirm('是否删除此地址？',function(){
						delete_adr(saID)
					})
				})
				//批量删除
				$('.delete_all').on('click',function(){
					var saID = ''
					var num = $('.adr-tr-active').length;
					for(var i = 0;i < num;i++){
						saID += ','+ $('.adr-tr-active').eq(i).attr('saID');
					}
					saID = saID.substr(1);
					layer.confirm('是否删选中地址？',function(){
						delete_adr(saID)
					})
				})
				//增加地址选择省
				$('#new-province').on('change',function(){
					var areaID = $(this).val();
					change_region(areaID,'#new-city','#new-area')
				})
				//增加地址选择市
				$('#new-city').on('change',function(){
					var areaID = $(this).val();
					change_region(areaID,'#new-area');
				})
				//修改地址请求
				$('.alter-adr').on('click',function(){
					var saID = $(this).parent().parent().attr('saID');
					$.ajax({
					    type:'post',
					    url:'../areaAddr/getSAByID',
					    data:{'saID':saID},
					    dataType:'json',
					    success:function(data){
					        if(data.result == 'success'){
					            var addr = data.uShipAddr;
					            console.log(addr)
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
					        }else if(data.result == 'error'){
					            layer.msg(data.msg)
					        }else if(data.result == 'failed'){
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
						info.provincePin = $('#alter-province-new').find("option:selected").attr('provincePin')
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
					    url:'updateShipAddr',
					    data:info,
					    dataType:'json',
					    success:function(data){
					        if(data.result == 'success'){
					            layer.msg('修改成功')
					            setTimeout(function(){
					            	window.location.reload();
					            },500)
					        }else if(data.result == 'error'){
					            layer.msg(data.msg)
					        }else if(data.result == 'failed'){
					            layer.msg(data.msg)
					        }
					    }
					});
				})
				$('.quit-alter').on('click',function(){
					layer.close(alterIndex);
				})
			})
		</script>
		<!--页脚-->
		<%@ include file="../index/footer.jsp"%>
		<!--右侧固定-->
		<%@ include file="../index/rightFixed.jsp"%>
	</body>	
</html>