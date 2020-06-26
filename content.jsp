<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div id="navMenu">
	<ul>
		<li class="hmain">
			 <a href="javascript:void(0)">校园代步</a>
			<ul>
			   <li style="float:left;"><a href="javascript:void(0)">自行车</a></li>
		       <li style="float:left;"><a href="javascript:void(0)">电动车</a></li> 
		     </ul>
		</li>
		<li class="hmain"><a href="javascript:void(0)">手机</a></li>
		<li class="hmain">
			<a href="javascript:void(0)">电脑</a>
			<ul>
			   <li style="float:left;"><a href="javascript:void(0)">笔记本</a></li>
		       <li style="float:left;"><a href="javascript:void(0)">台式机</a></li> 
		     </ul>
		</li>
		<li class="hmain">
			<a href="javascript:void(0)">数码配件</a>
			<ul>
			   <li style="float:left;line-height:30px;"><a href="javascript:void(0)">耳机</a></li>
		       <li style="float:left;line-height:30px;"><a href="javascript:void(0)">移动硬盘</a></li> 
			   <li style="float:left;line-height:30px;"><a href="javascript:void(0)">键盘</a></li> 
			   <li style="float:left;line-height:30px;"><a href="javascript:void(0)">鼠标</a></li> 
			   <li style="float:left;line-height:30px;"><a href="javascript:void(0)">充电器</a></li> 
			   <li style="float:left;line-height:30px;"><a href="javascript:void(0)">显示器</a></li> 
			   <li style="float:left;line-height:30px;"><a href="javascript:void(0)">移动电源</a></li> 
			   <li style="float:left;line-height:30px;"><a href="javascript:void(0)">其他</a></li> 
		     </ul>
		</li>
		<li class="hmain">
			<a href="javascript:void(0)">数码</a>
			<ul>
			   <li style="float:left;line-height:30px;"><a href="javascript:void(0)">mp3/mp4</a></li>
		       <li style="float:left;line-height:30px;"><a href="javascript:void(0)">相机</a></li> 
			   <li style="float:left;line-height:30px;"><a href="javascript:void(0)">单反</a></li> 
			   <li style="float:left;line-height:30px;"><a href="javascript:void(0)">游戏机</a></li> 
			   <li style="float:left;line-height:30px;"><a href="javascript:void(0)">平板</a></li> 
			   <li style="float:left;line-height:30px;"><a href="javascript:void(0)">其他</a></li> 
		     </ul>
		</li>
		<li class="hmain">
			<a href="javascript:void(0)">电器</a>
			<ul>
			   <li style="float:left;line-height:30px;"><a href="javascript:void(0)">电扇</a></li>
		       <li style="float:left;line-height:30px;"><a href="javascript:void(0)">台灯</a></li> 
			   <li style="float:left;line-height:30px;"><a href="javascript:void(0)">洗衣机</a></li> 
			   <li style="float:left;line-height:30px;"><a href="javascript:void(0)">电吹风</a></li> 
			   <li style="float:left;line-height:30px;"><a href="javascript:void(0)">电水壶</a></li> 
			   <li style="float:left;line-height:30px;"><a href="javascript:void(0)">空调</a></li> 
			   <li style="float:left;line-height:30px;"><a href="javascript:void(0)">电视</a></li> 
			   <li style="float:left;line-height:30px;"><a href="javascript:void(0)">其他</a></li> 
		     </ul>
		</li>
		<li class="hmain">
			<a href="javascript:void(0)">运动健身</a>
			<ul>
			   <li style="float:left"><a href="javascript:void(0)">篮球</a></li>
		       <li style="float:left"><a href="javascript:void(0)">足球</a></li> 
			   <li style="float:left"><a href="javascript:void(0)">球拍</a></li> 
			   <li style="float:left"><a href="javascript:void(0)">哑铃</a></li> 
			   <li style="float:left"><a href="javascript:void(0)">轮滑</a></li>  
			   <li style="float:left"><a href="javascript:void(0)">其他</a></li> 
		     </ul>
		</li>
		<li class="hmain">
			<a href="javascript:void(0)">衣物伞帽</a>
			<ul>
			   <li style="float:left"><a href="javascript:void(0)">上衣</a></li>
		       <li style="float:left"><a href="javascript:void(0)">裤子</a></li> 
			   <li style="float:left"><a href="javascript:void(0)">背包</a></li> 
			   <li style="float:left"><a href="javascript:void(0)">雨伞</a></li> 
			   <li style="float:left"><a href="javascript:void(0)">鞋</a></li>  
			   <li style="float:left"><a href="javascript:void(0)">帽子</a></li>   
			   <li style="float:left"><a href="javascript:void(0)">其他</a></li> 
		     </ul>
		</li>
		<li class="hmain">
			<a href="javascript:void(0)">图书教材</a>
			<ul>
			   <li style="float:left"><a href="javascript:void(0)">教材</a></li>
		       <li style="float:left"><a href="javascript:void(0)">考研</a></li> 
			   <li style="float:left"><a href="javascript:void(0)">托福/雅思/GRE</a></li> 
			   <li style="float:left"><a href="javascript:void(0)">课外书</a></li>  
			   <li style="float:left"><a href="javascript:void(0)">其他</a></li> 
		     </ul>
		</li>
		<li class="hmain">
			<a href="javascript:void(0)">租赁</a>
			<ul>
			   <li style="float:left"><a href="javascript:void(0)">租房</a></li>
		       <li style="float:left"><a href="javascript:void(0)">服装</a></li> 
			   <li style="float:left"><a href="javascript:void(0)">道具</a></li>  
			   <li style="float:left"><a href="javascript:void(0)">其他</a></li> 
		     </ul>
		</li>
		<li class="hmain">
			<a href="javascript:void(0)">生活娱乐</a>
			<ul>
			   <li style="float:left;line-height:30px;"><a href="javascript:void(0)">乐器</a></li>
		       <li style="float:left;line-height:30px;"><a href="javascript:void(0)">日常用品</a></li> 
			   <li style="float:left;line-height:30px;"><a href="javascript:void(0)">虚拟账号</a></li> 
			   <li style="float:left;line-height:30px;"><a href="javascript:void(0)">会员卡</a></li>  
			   <li style="float:left;line-height:30px;"><a href="javascript:void(0)">化妆品</a></li> 
			   <li style="float:left;line-height:30px;"><a href="javascript:void(0)">其他</a></li> 
		     </ul>
		</li>
		<li class="hmain"><a href="javascript:void(0)">其他</a></li>
	</ul>
</div>