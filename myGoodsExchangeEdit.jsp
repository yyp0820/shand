<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${goods!=null && goods.goods_id!=0?'编辑':'添加'}商品交换</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/store.css">
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script charset="utf-8" src="admin/editor/kindeditor.js"></script>
<script language="javascript" type="text/javascript"> 
	
</script>
<style type="text/css">
 body,td,div
 {
   font-size:12px;
 }
</style>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div id="middle">
	<jsp:include page="leftMenu.jsp"></jsp:include>
	 <!--  购物车 -->
	 <div id="product_info">
			<div class="title">个人中心  &gt;&gt;  ${goods!=null && goods.goods_id!=0?'编辑':'添加'}商品交换</div>
			<div style="margin-top:5px">
				 <form id="infoSelf" name="infoSelf" action="page_saveGoods.action" method="post" style="width:100%;height:100%">    
				 <input type="hidden" name="goods_id" id="goods_id" value="${goods.goods_id}"/>
				 <input type="hidden" name="user_id" id="user_id" value="${userFront.user_id}"/>
				 <input type="hidden" name="goods_flag" id="goods_flag" value="3"/>
				 <table class="ptable" style="margin-bottom:5px;">
			        <tr>
			          <td width="15%" align="right" style="padding-right:5px">商品名称：</td>
			          <td width="*">
			          	<input type="text" style="width:450px;" name="goods_name" id="goods_name" value="${goods.goods_name}"/> 
			          </td>
			        </tr> 
			        <tr>
			          <td width="15%" align="right" style="padding-right:5px">弥补价格：</td>
			          <td width="*">
			          	<input type="text" style="width:450px;" name="goods_price" id="goods_price" value="${goods.goods_price}"/> 
			          </td>
			        </tr>
			       <tr>
			          <td width="15%" align="right" style="padding-right:5px">买家手机：</td>
			          <td width="*">
			          	<input type="text" style="width:450px;" name="user_phone" id="user_phone" value="${goods.user_phone}"/> 
			          </td>
			        </tr>
			        <tr>
			          <td width="15%" align="right" style="padding-right:5px">买家QQ：</td>
			          <td width="*">
			          	<input type="text" style="width:450px;" name="user_qq" id="user_qq" value="${goods.user_qq}"/> 
			          </td>
			        </tr>
			         <tr>
			          <td width="15%" align="right" style="padding-right:5px">交易地点：</td>
			          <td width="*">
			          	<input type="text" style="width:450px;" name="goods_address" id="goods_address" value="${goods.goods_address}"/> 
			          </td>
			        </tr>
			        <tr>
			          <td align="right" style="padding-right:5px">商品描述：</td>
			          <td class="KEEdit">
			          	<textarea style="width:450px;height:150px" name="goods_desc" id="noticeContent">${goods.goods_desc}</textarea> 
			          </td>
			        </tr> 
			        <tr class="">
			          <td align="center" height="30" colspan="2">
			            <input type="button"  Class="btnstyle" value="返 回" onclick="window.history.back();"/>&nbsp;
			            <c:if test="${goods!=null && goods.goods_id!=0}">
						<input type="button" id="editBtn" Class="btnstyle" value="编 辑"/> 
						</c:if>
						<c:if test="${goods==null || goods.goods_id==0}">
						<input type="button" id="addBtn" Class="btnstyle" value="发 布" />
						</c:if>
			          </td>
			        </tr>
			     </table>
			     </form>
			</div>
		</div>
	 <!--  购物车 -->
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script language="javascript" type="text/javascript">
	$(document).ready(function(){
		
		var num=/^\d+(\.\d+)?$/;
		var num2=/^\d+$/;
		$("#addBtn").bind('click',function(){
			KE.sync('noticeContent');
			if($("#goods_name").val()==''){
				alert('商品名称不能为空');
				return;
			}
			if(!num.exec($("#goods_price").val())){
				alert('弥补价格必须为数字');
				return;
			}
			if($("#user_phone").val()=='' && $("#user_qq").val()==''){
				alert('买家手机和QQ至少有一个不能为空');
				return;
			}
			if($("#goods_address").val()==''){
				alert('交易地点不能为空');
				return;
			} 
			if($("#noticeContent").val()==''){
				alert('商品描述不能为空');
				return;
			}
			$("#goods_id").val(0);
			var aQuery = $("#infoSelf").serialize();  
			$.post('page_addGoods.action',aQuery,
				function(responseObj) {
						if(responseObj.success) {	
							 alert('新增成功！');
							 window.location.href="page_listMyGoodsExchanges.action";
						}else  if(responseObj.err.msg){
							 alert('新增失败：'+responseObj.err.msg);
						}else{
							 alert('新增失败：服务器异常！');
						}	
			},'json');
		 });
		
		 $("#editBtn").bind('click',function(){
			KE.sync('noticeContent');
			if($("#goods_name").val()==''){
				alert('商品名称不能为空');
				return;
			}
			if(!num.exec($("#goods_price").val())){
				alert('弥补价格必须为数字');
				return;
			}
			if($("#user_phone").val()=='' && $("#user_qq").val()==''){
				alert('买家手机和QQ至少有一个不能为空');
				return;
			}
			if($("#goods_address").val()==''){
				alert('交易地点不能为空');
				return;
			} 
			if($("#noticeContent").val()==''){
				alert('商品描述不能为空');
				return;
			}
			var aQuery = $("#infoSelf").serialize();  
			$.post('page_saveGoods.action',aQuery,
				function(responseObj) {
						if(responseObj.success) {	
							 alert('编辑成功！');
							 window.location.href="page_listMyGoodsExchanges.action";
						}else  if(responseObj.err.msg){
							 alert('编辑失败：'+responseObj.err.msg);
						}else{
							 alert('编辑失败：服务器异常！');
						}	
			},'json');
		 });
		
	});	 
	
	KE.show({ 
	    id : 'noticeContent',
	    items:['plainpaste', '|', 'selectall', 'bold','italic'],
	    resizeMode:1
	    
	            
	});
</script>
</body>
</html>