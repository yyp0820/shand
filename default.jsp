<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
$(document).ready(function(){
	//菜单
	var timoutid;
 	$(".hmain").hover(function(){
 		var obj=$(this);
 		timoutid=setTimeout(function(){
 			var ulNode=obj.children("ul");
     		  ulNode.show();
 		},300);
 	},function(){
 			clearTimeout(timoutid);
     		var obj=$(this);
     		var ulNode=obj.children("ul");
     		if (ulNode.css("display")!="none") {
     			ulNode.hide();
     		}
 	});
 	
 	//模块
 	$("#Btn1").bind("click",function(){
 		window.location.href="page_index.action";
 	});
 	
 	$("#Btn2").bind("click",function(){
 		window.open("page_goodsBuy.action", "商品求购")
 	});
 	
 	$("#Btn3").bind("click",function(){
 		window.open("page_goodsExchange.action", "商品交换")
 	});
 	
 	$("#Btn4").bind("click",function(){
 		var user_id = "${userFront.user_id}";
 		if(user_id==''){
 			alert("请先登录！");
 			return;
 		}
 		window.open("page_listMyGoodss.action", "商品发布")
 	});
	
 	//商品类别
 	var type1V = "";
 	var type2V = "";
 	var type1 = $("#paramsGoods\\.goods_type1");
 	var type2 = $("#paramsGoods\\.goods_type2");
 	$(".hmain a").bind("click",function(){
 		var liP = $(this).parent("li");
 		if(liP.hasClass("hmain")){
 			type1V = $(this).text();
 			type2V = "";
 		}else{
 			type2V = $(this).text();
 			type1V = liP.parent("ul").prev("a").text() ;
 		}
 		//alert("type1--"+type1V+";type2--"+type2V);
 		type1.val(type1V);
 		type2.val(type2V);
 		$("#info").attr("action","page_index.action").submit();
 	});

 	//搜索
 	$("#big_searchBtn").bind("click",function(){
 		$("#info").attr("action","page_index.action").removeAttr("target").submit();
 	});
}); 
	
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
	<div id="left">
		<jsp:include page="content.jsp"></jsp:include>
	</div>	 
	<div id="right">
		<div id="model">
			<input type="button"  id="Btn1" value="最新发布" class="btnstyle"/>
			<input type="button"  id="Btn2"  style="margin-left:120px"  value="商品求购" class="btnstyle"/>
			<input type="button"  id="Btn3" style="margin-left:120px" value="商品交换" class="btnstyle"/>
			<input type="button"  id="Btn4" style="float:right" value="我要发布" class="btnstyle"/>
		</div>
		<!--  商品列表 -->
		<div id="list">
			<div class="products">
				<c:if test="${goodss!=null &&  fn:length(goodss)>0}">
   				<c:forEach items="${goodss}" var="goods" varStatus="status">
				<div class="product">
					<div class="productPic"><a href="page_queryGoods.action?goods_id=${goods.goods_id}" target="_blank"><img src="images/goodss/${goods.goods_pic}"></a></div>
					<div class="productText">
						 <span class="title">${goods.goods_name}</span>
						<br/>价格：￥${goods.goods_price}
						<br/>可否小刀：${goods.goods_saleDesc}
					</div>
				</div>
				</c:forEach>
				</c:if>

			</div>
	     	
			<!--  商品分页 -->
		   <jsp:include page="page.jsp"></jsp:include>
			<!--  商品分页 -->

		</div>
		<!--  商品列表 -->
		 
	</div>	 
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
	  document.getElementById("pageNo").value=pagenum;
	  document.info.action="page_index.action";
	  document.info.target="_self";
	  document.info.submit();
	}
	function ChangePage(pagenum)
	{
		 document.getElementById("pageNo").value=pagenum;
		 document.info.action="page_index.action";
		 document.info.target="_self";
		 document.info.submit();
	}
</script>
</body>
</html>