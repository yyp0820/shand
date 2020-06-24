<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${goods!=null && goods.goods_id!=0?'编辑':'添加'}商品发布</title>
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
<script type="text/javascript" src="js/goodsType.js"></script>
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
			<div class="title">个人中心  &gt;&gt;  ${goods!=null && goods.goods_id!=0?'编辑':'添加'}商品发布</div>
			<div style="margin-top:5px">
				 <form id="infoSelf" name="infoSelf" action="page_saveGoods.action" method="post" style="width:100%;height:100%">    
				 <input type="hidden" name="goods_id" id="goods_id" value="${goods.goods_id}"/>
				 <input type="hidden" name="user_id" id="user_id" value="${userFront.user_id}"/>
				 <input type="hidden" name="goods_flag" id="goods_flag" value="1"/>
				 <input type="hidden" name="goods_pics" id="goods_pics" value="${goods.goods_pics}"/>
				 <table class="ptable" style="margin-bottom:5px;">
			        <tr>
			          <td width="15%" align="right" style="padding-right:5px">商品名称：</td>
			          <td width="*">
			          	<input type="text" style="width:450px;" name="goods_name" id="goods_name" value="${goods.goods_name}"/> 
			          </td>
			        </tr> 
			        <tr>
			          <td width="15%" align="right" style="padding-right:5px">商品类型：</td>
			          <td width="*">
			          	<select id="goods_type1" name="goods_type1" class="selectstyle" style="width:120px;">
			          		<option value="">请选择</option>
					    </select> 
					    <span id="type2Select" style="display:none">
					    -
					    <select id="goods_type2" name="goods_type2" class="selectstyle" style="width:120px;">
			          		<option value="">请选择</option>
					    </select>
					    </span>
			          </td>
			        </tr>
			        <tr>
					  <td align="right" style="padding-right:5px">商品图片：</td>
					  <td align="left" height="80">
					    <div id="pic" style="width:95%;height:80px;overflow:auto">
					    	<c:if test="${goodsPics!=null && fn:length(goodsPics)>0 }">
					    	<c:forEach items="${goodsPics}" var="goodsPic" varStatus="status">
					    	<div style='margin-left:5px;height:80px;width:60px;float:left;text-align:center'>
					    		<img src='images/goodss/${goodsPic.goods_pic}' width='60' height='60'/>
					    		<br/><a href='javascript:void(0)' id="delPic_${fn:replace(goodsPic.goods_pic,'.','P') }">删除</a>
					    	</div>
					    	</c:forEach>
					    	</c:if>
					    </div>
	        			&nbsp;<span id="op" style="display:none"><img src="images/loading001.gif" /></span>
				      </td>
				    </tr>
				    <tr>
					  <td align="right" style="padding-right:5px">上传图片：</td>
				      <td align="left"> 
				          <iframe name="uploadPage" src="uploadImg.jsp" width="100%" height="50" marginheight="0" marginwidth="0" scrolling="no" frameborder="0"></iframe>            
				       </td>
				    </tr>
			        <tr>
			          <td width="15%" align="right" style="padding-right:5px">商品价格：</td>
			          <td width="*">
			          	<input type="text" style="width:450px;" name="goods_price" id="goods_price" value="${goods.goods_price}"/> 
			          </td>
			        </tr>
			        <tr>
			          <td width="15%" align="right" style="padding-right:5px">可否小刀：</td>
			          <td width="*">
			                <select name="goods_sale" id="goods_sale" class="selectstyle" style="width:120px;">
			                	<option value="1">不可小刀</option>
			                	<option value="2">可小刀</option>
			                </select>
			          </td>
			        </tr>
			       <tr>
			          <td width="15%" align="right" style="padding-right:5px">卖家手机：</td>
			          <td width="*">
			          	<input type="text" style="width:450px;" name="user_phone" id="user_phone" value="${goods.user_phone}"/> 
			          </td>
			        </tr>
			        <tr>
			          <td width="15%" align="right" style="padding-right:5px">卖家QQ：</td>
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
			          <td align="right" style="padding-right:5px">商品介绍：</td>
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
		var goods_type1V = '<s:property value="#attr.goods.goods_type1"/>';
		var goods_type2V = '<s:property value="#attr.goods.goods_type2"/>';
		var goods_type1 = $("#goods_type1");
		var goods_type2 = $("#goods_type2");
		var type2Select = $("#type2Select");
		
		goods_type1.empty();
		for(var i=0;i<type1.length;i++){
			if(goods_type1V == type1[i]){
				goods_type1.append("<option selected='selected' value='"+type1[i]+"'>"+type1[i]+"</option>"); 
			}else{
				goods_type1.append("<option value='"+type1[i]+"'>"+type1[i]+"</option>"); 
			}
		}
		
		function loadType2(item){
			goods_type2.empty();
			var type1Index = goods_type1.get(0).selectedIndex;
			if(goods_type1.val()!=''){
				if(type2[type1Index].length>0){
					type2Select.show();
					for(var j=0;j<type2[type1Index].length;j++){
						if(item && item == type2[type1Index][j]){
							goods_type2.append("<option selected='selected' value='"+type2[type1Index][j]+"'>"+type2[type1Index][j]+"</option>"); 
						}else{
							goods_type2.append("<option value='"+type2[type1Index][j]+"'>"+type2[type1Index][j]+"</option>"); 
						}
					}
				}else{
					type2Select.hide();
					goods_type2.empty();
				}
			}else{
				type2Select.hide();
				goods_type2.empty();
			}
		}
		loadType2(goods_type2V);
		goods_type1.change(function(){
			loadType2();
		});
		
		
		var goods_pics = $("#goods_pics");
		var goods_pics_temp = "";
		$("a[id^='delPic']").live("click",function(){
			var pic = $(this).attr("id").split("_")[1].replace("P","."); 
			var goods_picsV = goods_pics.val().split(",");
			for(var i=0;i<goods_picsV.length;i++){
				if(pic != goods_picsV[i]){
					if(goods_pics_temp==""){
						goods_pics_temp=goods_picsV[i];
					}else{
						goods_pics_temp+=","+goods_picsV[i];
					}
					
				}
			}
			goods_pics.val(goods_pics_temp);
			$(this).parent().remove();
		});
		
		var num=/^\d+(\.\d+)?$/;
		var num2=/^\d+$/;
		$("#addBtn").bind('click',function(){
			KE.sync('noticeContent');
			if($("#goods_name").val()==''){
				alert('商品名称不能为空');
				return;
			}
			if($("#goods_type1").val()==''){
				alert('商品大类不能为空');
				return;
			}
			var type1Index = goods_type1.get(0).selectedIndex;
			if(type2[type1Index].length>0 && $("#goods_type2").val()==''){
				alert('商品小类不能为空');
				return;
			}
			if($("#goods_pics").val()==''){
				alert('商品图片不能为空');
				return;
			}
			if(!num.exec($("#goods_price").val())){
				alert('商品价格必须为数字');
				return;
			}
			if($("#user_phone").val()=='' && $("#user_qq").val()==''){
				alert('卖家手机和QQ至少有一个不能为空');
				return;
			}
			if($("#goods_address").val()==''){
				alert('交易地点不能为空');
				return;
			} 
			if($("#noticeContent").val()==''){
				alert('商品介绍不能为空');
				return;
			}
			$("#goods_id").val(0);
			var aQuery = $("#infoSelf").serialize();  
			$.post('page_addGoods.action',aQuery,
				function(responseObj) {
						if(responseObj.success) {	
							 alert('新增成功！请等待审核！');
							 window.location.href="page_listMyGoodss.action";
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
			if($("#goods_type1").val()==''){
				alert('商品大类不能为空');
				return;
			}
			var type1Index = goods_type1.get(0).selectedIndex;
			if(type2[type1Index].length>0 && $("#goods_type2").val()==''){
				alert('商品小类不能为空');
				return;
			}
			if($("#goods_pics").val()==''){
				alert('商品图片不能为空');
				return;
			}
			if(!num.exec($("#goods_price").val())){
				alert('商品价格必须为数字');
				return;
			}
			if($("#user_phone").val()=='' && $("#user_qq").val()==''){
				alert('卖家手机和QQ至少有一个不能为空');
				return;
			}
			if($("#goods_address").val()==''){
				alert('交易地点不能为空');
				return;
			} 
			if($("#noticeContent").val()==''){
				alert('商品介绍不能为空');
				return;
			}
			var aQuery = $("#infoSelf").serialize();  
			$.post('page_saveGoods.action',aQuery,
				function(responseObj) {
						if(responseObj.success) {	
							 alert('编辑成功！');
							 window.location.href="page_listMyGoodss.action";
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