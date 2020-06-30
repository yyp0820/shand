<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品信息详情</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	 
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">商品信息详情</span>
</div>
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">商品信息详情</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
        </TABLE>
     </td>
   </tr>
   <tr>
     <td height="1" bgcolor="#8f8f8f"></td>
   </tr>
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
        <tr>
          <td width="40%" align="center">
          	<c:if test="${goods.goods_flag==1}">
          	<img src="../images/goodss/${goods.goods_pic}" width="200" /> 
          	</c:if>
          	<s:else>
          	<img src="../images/buy.jpg" width="200" /> 
          	</s:else>
          </td>
          <td width="60%" style="line-height:22px;padding-left:5px;">
          	商品名称：${goods.goods_name}
          	<br/>商品板块：${goods.goods_flagDesc}
          	<c:if test="#attr.goods.goods_flag==1">
          	<br/>商品类别：${goods.goods_type1}-${goods.goods_type2}
          	</c:if>
          	<br/>商品价格：￥${goods.goods_price} 元
          	<br/>商品描述：${goods.goods_descShow}
          	<br/>浏览次数：${goods.goods_click}
          	<br/>卖家手机：${goods.user_phone}
          	<br/>卖家QQ：${goods.user_qq}
          	<br/>交易地点：${goods.goods_address}
          	<br/>发布人：${goods.nick_name}
          	<br/>发布时间：${fn:substring(goods.goods_date,0,19)}
          </td>
        </tr> 
     </table>
     </td>
   </tr> 
   <tr>
     <td>
       <table width="100%" align="center" cellpadding="0" cellspacing="0" class="editbody">
        <tr class="editbody">
          <td align="center" height="30">
          	<input type="button" id="editBtn" Class="btnstyle" value="返 回" onclick="window.history.back(-1);"/> 
          </td>
        </tr>
      </table>
     </td>
   </tr> 
</table>
</body>
</html>