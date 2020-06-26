package com.nkl.page.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nkl.common.util.JSONData;
import com.nkl.common.util.PaperUtil;
import com.nkl.page.domain.Collect;
import com.nkl.page.domain.Goods;
import com.nkl.page.domain.GoodsPic;
import com.nkl.page.domain.Logistics;
import com.nkl.page.domain.Orders;
import com.nkl.page.domain.Sblog;
import com.nkl.page.domain.User;
import com.nkl.page.manager.IndexManager;

@Controller
public class IndexAction {

	@Autowired
	IndexManager indexManager;
	String tip;
	
	public IndexManager getIndexManager() {
		return indexManager;
	}

	public void setIndexManager(IndexManager indexManager) {
		this.indexManager = indexManager;
	}
	
	/**
	 * @Title: index
	 * @Description: 展示首页信息
	 * @return String
	 */
	@RequestMapping(value="page_index.action")
	public String index(ModelMap model, Goods paramsGoods,PaperUtil paperUtil){
		try {
			//查询发布商品集合
			if (paramsGoods==null) {
				paramsGoods = new Goods();
			}
			//分页信息设置
			paperUtil.setPagination(paramsGoods);
			int[] sum = {0};
			//商品类别
			paramsGoods.setGoods_flag(1);
			paramsGoods.setGoods_status(2);
			List<Goods> goodss = indexManager.listGoodss(paramsGoods,sum); 
			model.addAttribute("goodss", goodss);
			paperUtil.setTotalCount(sum[0]);

			model.addAttribute("paramsGoods", paramsGoods);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "default";
	}
	
	/**
	 * @Title: queryGoods
	 * @Description: 查询产品详情
	 * @return String
	 */
	@RequestMapping(value="page_queryGoods.action")
	public String queryGoods(Goods paramsGoods,Sblog paramsSblog,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到产品
			Goods goods = indexManager.queryGoods(paramsGoods);
			model.addAttribute("goods", goods);
			
			//查询图片
			GoodsPic goodsPic = new GoodsPic();
			goodsPic.setGoods_id(paramsGoods.getGoods_id());
			goodsPic.setStart(-1);
			List<GoodsPic> goodsPics = indexManager.listGoodsPics(goodsPic, null);
			model.addAttribute("goodsPics", goodsPics);
			
			//查询商品评论
			if (paramsSblog==null) {
				paramsSblog  = new Sblog();
				paramsSblog.setGoods_id(paramsGoods.getGoods_id());
			}
			//分页信息设置
			paperUtil.setPagination(paramsSblog);
			int[] sum = {0};
			List<Sblog> sblogs = indexManager.listSblogs(paramsSblog, sum);
			model.addAttribute("sblogs", sblogs);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "goodsDetail";
	}
	
	/**
	 * @Title: addCollect
	 * @Description: 收藏商品
	 * @return String
	 */
	@RequestMapping(value="page_addCollect.action")
	@ResponseBody
	public JSONData addCollect(Collect paramsCollect,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			//收藏商品
			indexManager.addCollect(paramsCollect);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		
		return jsonData;
	}
	
	
	/**
	 * @Title: goodsBuy
	 * @Description: 查询商品求购信息集合
	 * @return String
	 */
	@RequestMapping(value="page_goodsBuy.action")
	public String goodsBuy(Goods paramsGoods,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//查询商品求购集合
			if (paramsGoods==null) {
				paramsGoods = new Goods();
			}
			//分页信息设置
			paperUtil.setPagination(paramsGoods);
			int[] sum = {0};
			//商品类别
			paramsGoods.setGoods_flag(2);
			List<Goods> goodss = indexManager.listGoodss(paramsGoods,sum); 
			model.addAttribute("goodss", goodss);
			paperUtil.setTotalCount(sum[0]);
			model.addAttribute("paramsGoods", paramsGoods);
			
			//查询评论信息
			if (goodss!=null && goodss.size()>0) {
				for (Goods goods : goodss) {
					Sblog sblog = new Sblog();
					sblog.setStart(-1);
					sblog.setGoods_id(goods.getGoods_id());
					List<Sblog> sblogs = indexManager.listSblogs(sblog, null);
					goods.setSblogs(sblogs);
					goods.setGoods_head((goods.getGoods_id()%5==0?5:goods.getGoods_id()%5)+"");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "goodsBuy";
	}
	
	/**
	 * @Title: goodsExchange
	 * @Description: 查询商品交换信息集合
	 * @return String
	 */
	@RequestMapping(value="page_goodsExchange.action")
	public String goodsExchange(Goods paramsGoods,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//查询商品交换集合
			if (paramsGoods==null) {
				paramsGoods = new Goods();
			}
			//分页信息设置
			paperUtil.setPagination(paramsGoods);
			int[] sum = {0};
			//商品类别
			paramsGoods.setGoods_flag(3);
			List<Goods> goodss = indexManager.listGoodss(paramsGoods,sum); 
			model.addAttribute("goodss", goodss);
			paperUtil.setTotalCount(sum[0]);
			model.addAttribute("paramsGoods", paramsGoods);
			
			//查询评论信息
			if (goodss!=null && goodss.size()>0) {
				for (Goods goods : goodss) {
					Sblog sblog = new Sblog();
					sblog.setStart(-1);
					sblog.setGoods_id(goods.getGoods_id());
					List<Sblog> sblogs = indexManager.listSblogs(sblog, null);
					goods.setSblogs(sblogs);
					goods.setGoods_head((goods.getGoods_id()%5==0?5:goods.getGoods_id()%5)+"");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "goodsExchange";
	}
	
	/**
	 * @Title: addSblog
	 * @Description: 新增评论
	 * @return String
	 */
	@RequestMapping(value="page_addSblog.action")
	@ResponseBody
	public JSONData addSblog(Sblog paramsSblog,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			//新增评论
			indexManager.addSblog(paramsSblog);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		
		return jsonData;
	}
	
	/**
	 * @Title: addOrders
	 * @Description: 新增订单
	 * @return String
	 */
	@RequestMapping(value="page_addOrders.action")
	@ResponseBody
	public JSONData addOrders(Orders paramsOrders,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			//新增订单
			indexManager.addOrders(paramsOrders);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		
		return jsonData;
	}
	
	/**
	 * @Title: listMyGoodss
	 * @Description: 查询我的商品发布
	 * @return String
	 */
	@RequestMapping(value="page_listMyGoodss.action")
	public String listMyGoodss(Goods paramsGoods,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsGoods==null) {
				paramsGoods = new Goods();
			}
			//获取用户,用户只能查询自己的
			User userFront = (User)httpSession.getAttribute("userFront");
			if (userFront.getUser_type()==1) {
				paramsGoods.setUser_id(userFront.getUser_id());
			}
			//设置分页信息
			paperUtil.setPagination(paramsGoods);
			int[] sum={0};
			paramsGoods.setGoods_flag(1);
			List<Goods> goodss = indexManager.listGoodss(paramsGoods,sum); 
			
			model.addAttribute("goodss", goodss);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "myGoodsShow";
	}
	
	/**
	 * @Title: listMyGoodsBuys
	 * @Description: 商品求购信息集合
	 * @return String
	 */
	@RequestMapping(value="page_listMyGoodsBuys.action")
	public String listMyGoodsBuys(Goods paramsGoods,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsGoods==null) {
				paramsGoods = new Goods();
			}
			//获取用户,用户只能查询自己的
			User userFront = (User)httpSession.getAttribute("userFront");
			if (userFront.getUser_type()==1) {
				paramsGoods.setUser_id(userFront.getUser_id());
			}
			//设置分页信息
			paperUtil.setPagination(paramsGoods);
			int[] sum={0};
			paramsGoods.setGoods_flag(2);
			List<Goods> goodss = indexManager.listGoodss(paramsGoods,sum); 
			
			model.addAttribute("goodss", goodss);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "myGoodsBuyShow";
	}
	
	/**
	 * @Title: listMyGoodsExchanges
	 * @Description: 商品交换信息集合
	 * @return String
	 */
	@RequestMapping(value="page_listMyGoodsExchanges.action")
	public String listMyGoodsExchanges(Goods paramsGoods,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsGoods==null) {
				paramsGoods = new Goods();
			}
			//获取用户,用户只能查询自己的
			User userFront = (User)httpSession.getAttribute("userFront");
			if (userFront.getUser_type()==1) {
				paramsGoods.setUser_id(userFront.getUser_id());
			}
			//设置分页信息
			paperUtil.setPagination(paramsGoods);
			int[] sum={0};
			paramsGoods.setGoods_flag(3);
			List<Goods> goodss = indexManager.listGoodss(paramsGoods,sum); 
			
			model.addAttribute("goodss", goodss);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "myGoodsExchangeShow";
	}
	
	/**
	 * @Title: queryMyGoods
	 * @Description: 查看商品
	 * @return String
	 */
	@RequestMapping(value="page_queryMyGoods.action")
	public String queryMyGoods(Goods paramsGoods,ModelMap model){
		try {
			 //得到商品
			Goods goods = indexManager.queryGoods(paramsGoods);
			model.addAttribute("goods", goods);
			
			//查询图片
			GoodsPic goodsPic = new GoodsPic();
			goodsPic.setGoods_id(paramsGoods.getGoods_id());
			goodsPic.setStart(-1);
			List<GoodsPic> goodsPics = indexManager.listGoodsPics(goodsPic, null);
			model.addAttribute("goodsPics", goodsPics);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "myGoodsDetail";
	}
	
	/**
	 * @Title: queryMyGoodsBuy
	 * @Description: 查看商品求购信息
	 * @return String
	 */
	@RequestMapping(value="page_queryMyGoodsBuy.action")
	public String queryMyGoodsBuy(Goods paramsGoods,ModelMap model){
		try {
			 //得到商品
			Goods goods = indexManager.queryGoods(paramsGoods);
			model.addAttribute("goods", goods);
			
			//查询图片
			GoodsPic goodsPic = new GoodsPic();
			goodsPic.setGoods_id(paramsGoods.getGoods_id());
			goodsPic.setStart(-1);
			List<GoodsPic> goodsPics = indexManager.listGoodsPics(goodsPic, null);
			model.addAttribute("goodsPics", goodsPics);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "myGoodsBuyDetail";
	}
	
	/**
	 * @Title: queryMyGoodsExchange
	 * @Description: 查看商品交换信息
	 * @return String
	 */
	@RequestMapping(value="page_queryMyGoodsExchange.action")
	public String queryMyGoodsExchange(Goods paramsGoods,ModelMap model){
		try {
			 //得到商品
			Goods goods = indexManager.queryGoods(paramsGoods);
			model.addAttribute("goods", goods);
			
			//查询图片
			GoodsPic goodsPic = new GoodsPic();
			goodsPic.setGoods_id(paramsGoods.getGoods_id());
			goodsPic.setStart(-1);
			List<GoodsPic> goodsPics = indexManager.listGoodsPics(goodsPic, null);
			model.addAttribute("goodsPics", goodsPics);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "myGoodsExchangeDetail";
	}
	
	/**
	 * @Title: addGoodsShow
	 * @Description: 显示添加商品页面
	 * @return String
	 */
	@RequestMapping(value="page_addGoodsShow.action")
	public String addGoodsShow(Goods paramsGoods,ModelMap model){
		return "myGoodsEdit";
	}
	
	@RequestMapping(value="page_addGoodsBuyShow.action")
	public String addGoodsBuyShow(Goods paramsGoods,ModelMap model){
		return "myGoodsBuyEdit";
	}
	
	@RequestMapping(value="page_addGoodsExchangeShow.action")
	public String addGoodsExchangeShow(Goods paramsGoods,ModelMap model){
		return "myGoodsExchangeEdit";
	}
	
	/**
	 * @Title: addGoods
	 * @Description: 添加商品
	 * @return String
	 */
	@RequestMapping(value="page_addGoods.action")
	@ResponseBody
	public JSONData addGoods(Goods paramsGoods,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			 //添加商品
			indexManager.addGoods(paramsGoods,httpSession);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		
		return jsonData;
	}
	
	/**
	 * @Title: editGoods
	 * @Description: 编辑商品
	 * @return String
	 */
	@RequestMapping(value="page_editGoods.action")
	public String editGoods(Goods paramsGoods,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到商品
			Goods goods = indexManager.queryGoods(paramsGoods);
			
			//查询图片
			GoodsPic goodsPic = new GoodsPic();
			goodsPic.setGoods_id(paramsGoods.getGoods_id());
			goodsPic.setStart(-1);
			List<GoodsPic> goodsPics = indexManager.listGoodsPics(goodsPic, null);
			model.addAttribute("goodsPics", goodsPics);
			
			String goods_pics = "";
			for (GoodsPic goodsPic2 : goodsPics) {
				goods_pics += "," + goodsPic2.getGoods_pic();
			}
			goods.setGoods_pics(goods_pics.substring(1));
			model.addAttribute("goods", goods);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "myGoodsEdit";
	}
	
	@RequestMapping(value="page_editGoodsBuy.action")
	public String editGoodsBuy(Goods paramsGoods,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到商品
			Goods goods = indexManager.queryGoods(paramsGoods);
			model.addAttribute("goods", goods);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "myGoodsBuyEdit";
	}
	@RequestMapping(value="page_editGoodsExchange.action")
	public String editGoodsExchange(Goods paramsGoods,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到商品
			Goods goods = indexManager.queryGoods(paramsGoods);
			model.addAttribute("goods", goods);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "myGoodsExchangeEdit";
	}
	
	
	/**
	 * @Title: saveGoods
	 * @Description: 保存编辑商品
	 * @return String
	 */
	@RequestMapping(value="page_saveGoods.action")
	@ResponseBody
	public JSONData saveGoods(Goods paramsGoods,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			 //保存编辑商品
			indexManager.updateGoods(paramsGoods);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		
		return jsonData;
	}
	
	/**
	 * @Title: delGoodss
	 * @Description: 删除商品
	 * @return String
	 */
	@RequestMapping(value="page_delGoodss.action")
	@ResponseBody
	public JSONData delGoodss(Goods paramsGoods,PaperUtil paperUtil,
				ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
		 //删除商品
		indexManager.delGoodss(paramsGoods);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		
		return jsonData;
	}
	
	/**
	 * @Title: listMyCollects
	 * @Description: 查询我的商品收藏
	 * @return String
	 */
	@RequestMapping(value="page_listMyCollects.action")
	public String listMyCollects(Collect paramsCollect,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsCollect==null) {
				paramsCollect = new Collect();
			}
			//获取用户,用户只能查询自己的商品收藏
			User userFront = (User)httpSession.getAttribute("userFront");
			if (userFront==null) {
				model.addAttribute("collects", null);
				return "collectShow";
			}
			paramsCollect.setUser_id(userFront.getUser_id());
			//设置分页信息
			paperUtil.setPagination(paramsCollect);
			//总的条数
			int[] sum={0};
			//查询商品列表
			List<Collect> collects = indexManager.listCollects(paramsCollect,sum); 
			
			model.addAttribute("collects", collects);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "collectShow";
	}
	
	/**
	 * @Title: delCollects
	 * @Description: 删除商品收藏
	 * @return String
	 */
	@RequestMapping(value="page_delCollects.action")
	@ResponseBody
	public JSONData delCollects(Collect paramsCollect,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			 //删除商品
			indexManager.delCollects(paramsCollect);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		
		return jsonData;
	}
	
	/**
	 * @Title: saveAdmin
	 * @Description: 保存修改个人信息
	 * @return String
	 */
	@RequestMapping(value="page_saveUserFront.action")
	@ResponseBody
	public JSONData saveUserFront(User paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			 //保存修改个人信息
			indexManager.updateUser(paramsUser);
			//更新session
			User admin = new User();
			admin.setUser_id(paramsUser.getUser_id());
			admin = indexManager.getUser(admin);
			httpSession.setAttribute("userFront", admin);
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		return jsonData;
	}
	
	/**
	 * @Title: saveUserFrontPass
	 * @Description: 保存修改个人密码
	 * @return String
	 */
	@RequestMapping(value="page_saveUserFrontPass.action")
	@ResponseBody
	public JSONData saveUserFrontPass(User paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			 //保存修改个人信息
			indexManager.updateUser(paramsUser);
			//更新session
			User userFront = (User)httpSession.getAttribute("userFront");
			if (userFront!=null) {
				userFront.setUser_pass(paramsUser.getUser_pass());
				httpSession.setAttribute("userFront", userFront);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		return jsonData;
	}
	
	/**
	 * @Title: listMyOrderss1
	 * @Description: 查询我的卖出订单
	 * @return String
	 */
	@RequestMapping(value="page_listMyOrderss1.action")
	public String listMyOrderss1(Orders paramsOrders,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsOrders==null) {
				paramsOrders = new Orders();
			}
			//获取用户,用户只能查询自己的订单
			User userFront = (User)httpSession.getAttribute("userFront");
			if (userFront.getUser_type()==1) {
				paramsOrders.setSell_id(userFront.getUser_id());
			}
			//设置分页信息
			paperUtil.setPagination(paramsOrders);
			//总的条数
			int[] sum={0};
			//查询商品预约列表
			List<Orders> orderss = indexManager.listOrderss(paramsOrders,sum); 
			
			model.addAttribute("orderss", orderss);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "ordersShow1";
	}
	
	/**
	 * @Title: listMyOrderss2
	 * @Description: 查询我的购买订单
	 * @return String
	 */
	@RequestMapping(value="page_listMyOrderss2.action")
	public String listMyOrderss2(Orders paramsOrders,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsOrders==null) {
				paramsOrders = new Orders();
			}
			//获取用户,用户只能查询自己的订单
			User userFront = (User)httpSession.getAttribute("userFront");
			if (userFront.getUser_type()==1) {
				paramsOrders.setUser_id(userFront.getUser_id());
			}
			//设置分页信息
			paperUtil.setPagination(paramsOrders);
			//总的条数
			int[] sum={0};
			//查询商品预约列表
			List<Orders> orderss = indexManager.listOrderss(paramsOrders,sum); 
			
			model.addAttribute("orderss", orderss);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "ordersShow2";
	}
	 
	/**
	 * @Title: listLogisticss
	 * @Description: 展示物流信息列表
	 * @return String
	 */
	@RequestMapping(value="page_listLogisticss.action")
	public String listLogisticss(Logistics paramsLogistics,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//查询物流信息集合
			if (paramsLogistics==null) {
				paramsLogistics = new Logistics();
			}
			//设置分页信息
			paramsLogistics.setStart(-1);
			List<Logistics> logisticss = indexManager.listLogisticss(paramsLogistics,null); 
			model.addAttribute("logisticss", logisticss);
			
			//订单信息
			model.addAttribute("orders_no", paramsLogistics.getOrders_no());
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "logisticsShow";
	}
	
	/**
	 * @Title: finishOrders
	 * @Description: 确认收货
	 * @return String
	 */
	@RequestMapping(value="page_finishOrders.action")
	@ResponseBody
	public JSONData finishOrders(Orders paramsOrders,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		JSONData jsonData = new JSONData();
		try {
			//确认收货
			indexManager.finishOrders(paramsOrders);
			
		}  catch (Exception e) {
			e.printStackTrace();
			jsonData.setErrorReason("后台服务器异常");
			return jsonData;
		}
		
		return jsonData;
	}
	
	/**
	 * @Title: reg
	 * @Description: 跳转注册页面
	 * @return String
	 */
	@RequestMapping(value="page_reg.action")
	public String reg(){
		return "reg";
	}
	
	/**
	 * @Title: myInfo
	 * @Description: 跳转个人信息页面
	 * @return String
	 */
	@RequestMapping(value="page_myInfo.action")
	public String myInfo(){
		return "myInfo";
	}
	
	/**
	 * @Title: myPwd
	 * @Description: 跳转个人密码页面
	 * @return String
	 */
	@RequestMapping(value="page_myPwd.action")
	public String myPwd(){
		return "myPwd";
	}
	
	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}


}
