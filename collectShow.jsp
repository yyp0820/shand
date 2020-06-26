<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>我的商品收藏</title>
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
<script language="javascript" type="text/javascript"> 
var userId="${userFront.user_id}";
if(userId==null || userId==''){
	alert("请先登录！");
	window.location.href="page_index.action";
}
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
			<div class="title">个人中心  &gt;&gt;  我的商品收藏</div>
			<div style="margin-top:5px">
				 <table class="ptable" style="margin-bottom:5px;">
					<tr class="head_text">
						 <td width="40" align="center">序号</td>
					     <td width="" align="center">商品名称</td>
					     <td width="" align="center">收藏时间</td>
					     <td width="" align="center">操作</td>
					</tr>
					<c:if test="${collects!=null &&  fn:length(collects)>0}">
   					<c:forEach items="${collects}" var="collect" varStatus="status">
					   <tr class="list0" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
					     <td width="" align="center">${status.index+1}</td>
					     <td width="" align="center">
					     	<a href="page_queryGoods.action?goods_id=${collect.goods_id}" target="_blank" title="${collect.goods_name}">
					     	${fn:length(collect.goods_name)>12 ? fn:substring(collect.goods_name,0,11) : collect.goods_name}
					     	</a>
					     </td>
					     <td width="" align="center">${fn:substring(collect.collect_date,0,19)}</td>
					     <td width="" align="center">
					       <a id="delCollects_${collect.collect_id}" href="javascript:void(0)">删除</a>&nbsp;
					     </td>
					   </tr> 
					   </c:forEach>
					</c:if>
				    <c:if test="${collects==null ||  fn:length(collects)==0}">
				    <tr>
				      <td height="60" colspan="6" align="center"><b>&lt;不存在商品收藏信息&gt;</b></td>
				    </tr>
				    </c:if>
				 </table>
			</div>
			<div class="pages">
				<jsp:include page="page.jsp"></jsp:include>
			</div>
		</div>
	 <!--  购物车 -->
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script language="javascript" type="text/javascript">
	function GoPage()
	{
	  var pagenum=document.getElementById("goPage").value;
	  var patten=/^\d+$/;
	  if(!patten.exec(pagenum))
	  {
	    alert("页码必须为大于0的数字");
	    return false;
	  }
	  window.location.href="page_listMyCollects.action?pageNo="+pagenum;
	}
	function ChangePage(pagenum)
	{
		window.location.href="page_listMyCollects.action?pageNo="+pagenum;
	}
	
	$(document).ready(function(){
		$("a[id^='delCollects']").bind('click',function(){
		    if(confirm('确认删除吗!?'))
		    {
		    	var collect_id=$(this).attr('id').split('_')[1];
		    	var aQuery = {
						'ids':collect_id
				};  
				$.post('page_delCollects.action',aQuery,
					function(responseObj) {
							if(responseObj.success) {	
								 alert('删除成功！');
								 window.location.reload();
							}else  if(responseObj.err.msg){
								 alert('删除失败：'+responseObj.err.msg);
							}else{
								 alert('删除失败：服务器异常！');
							}	
				},'json');
		    }
		 });
		
	});
</script>
</body>
</html>