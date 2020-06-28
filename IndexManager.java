package com.nkl.page.manager;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nkl.common.util.DateUtil;
import com.nkl.common.util.Md5;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;
import com.nkl.page.dao.ICollectDao;
import com.nkl.page.dao.IGoodsDao;
import com.nkl.page.dao.IGoodsPicDao;
import com.nkl.page.dao.ILogisticsDao;
import com.nkl.page.dao.IOrdersDao;
import com.nkl.page.dao.ISblogDao;
import com.nkl.page.dao.IUserDao;
import com.nkl.page.domain.Collect;
import com.nkl.page.domain.Goods;
import com.nkl.page.domain.GoodsPic;
import com.nkl.page.domain.Logistics;
import com.nkl.page.domain.Orders;
import com.nkl.page.domain.Sblog;
import com.nkl.page.domain.User;

@Service
public class IndexManager {

	@Autowired
	IUserDao userDao;
	@Autowired
	IGoodsDao goodsDao;
	@Autowired
	IGoodsPicDao goodsPicDao;
	@Autowired
	ISblogDao sblogDao;
	@Autowired
	IOrdersDao ordersDao;
	@Autowired
	ILogisticsDao logisticsDao;
	@Autowired
	ICollectDao collectDao;
	
	/**
	 * @Title: getUser
	 * @Description: 用户查询
	 * @param user
	 * @return User
	 */
	public User  getUser(User user){
		
		User _user = userDao.getUser(user);
		
		return _user;
	}
	 
	/**
	 * @Title: updateUser
	 * @Description: 更新用户信息
	 * @param user
	 * @return void
	 */
	public void  updateUser(User user){
		
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		userDao.updateUser(user);
		
	}
	
	/**
	 * @Title: listGoodss
	 * @Description: 查询产品信息
	 * @param goods
	 * @return List<Goods>
	 */
	public List<Goods>  listGoodss(Goods goods,int[] sum){
		
		if (sum!=null) {
			sum[0] = goodsDao.listGoodssCount(goods);
		}
		List<Goods> goodss = goodsDao.listGoodss(goods);
		
		return goodss;
	}
	
	public List<GoodsPic>  listGoodsPics(GoodsPic goodsPic,int[] sum){
		
		if (sum!=null) {
			sum[0] = goodsPicDao.listGoodsPicsCount(goodsPic);
		}
		List<GoodsPic> goodsPics = goodsPicDao.listGoodsPics(goodsPic);
		
		return goodsPics;
	}
	
	/**
	 * @Title: queryGoods
	 * @Description: 产品查询
	 * @param goods
	 * @return Goods
	 */
	public Goods queryGoods(Goods goods) {
		
		Goods _goods = goodsDao.getGoods(goods);
		_goods.setGoods_click(_goods.getGoods_click()+1);
		goodsDao.updateGoods(_goods);
		
		return _goods;
	}
	
	/**
	 * @Title: addGoods
	 * @Description: 添加商品
	 * @param goods
	 * @return void
	 */
	public void addGoods(Goods goods,HttpSession httpSession) {
		
		if (!StringUtil.isEmptyString(goods.getGoods_desc())) {
			goods.setGoods_desc(Transcode.htmlEncode(goods.getGoods_desc()));
		}
		goods.setGoods_date(DateUtil.dateToDateString(new Date()));
		if (goods.getGoods_flag()==1) {
			goods.setGoods_status(1);//待审核
		}else {
			goods.setGoods_status(2);//无需审核
		}
		
		goodsDao.addGoods(goods);
		
		//处理图片
		String goods_pics = goods.getGoods_pics();
		if (!StringUtil.isEmptyString(goods_pics)) {
			User userFront = (User)httpSession.getAttribute("userFront");
			int goods_id = goodsDao.getGoodsId(userFront.getUser_id());
			String[] pics = goods_pics.split(",");
			for (String pic : pics) {
				GoodsPic goodsPic = new GoodsPic();
				goodsPic.setGoods_id(goods_id);
				goodsPic.setGoods_pic(pic);
				goodsPicDao.addGoodsPic(goodsPic);
			}
		}
		
		
		
	}

	/**
	 * @Title: updateGoods
	 * @Description: 更新商品信息
	 * @param goods
	 * @return void
	 */
	public void updateGoods(Goods goods) {
		
		if (!StringUtil.isEmptyString(goods.getGoods_desc())) {
			goods.setGoods_desc(Transcode.htmlEncode(goods.getGoods_desc()));
		}
		goodsDao.updateGoods(goods);
		
		//处理图片
		String goods_pics = goods.getGoods_pics();
		if (!StringUtil.isEmptyString(goods_pics)) {
			int goods_id = goods.getGoods_id();
			goodsPicDao.delGoodsPicByGoodsId(goods_id+"");
			String[] pics = goods_pics.split(",");
			for (String pic : pics) {
				GoodsPic goodsPic = new GoodsPic();
				goodsPic.setGoods_id(goods_id);
				goodsPic.setGoods_pic(pic);
				goodsPicDao.addGoodsPic(goodsPic);
			}
		}
		
	}

	/**
	 * @Title: delGoods
	 * @Description: 删除商品信息
	 * @param goods
	 * @return void
	 */
	public void delGoodss(Goods goods) {
		
		goodsDao.delGoodss(goods.getIds().split(","));
		
	}
	
	/**
	 * @Title: listSblogs
	 * @Description: 查询评论
	 * @param sblog
	 * @return List<Sblog>
	 */
	public List<Sblog>  listSblogs(Sblog sblog,int[] sum){
		
		if (sum!=null) {
			sum[0] = sblogDao.listSblogsCount(sblog);
		}
		List<Sblog> sblogs = sblogDao.listSblogs(sblog);
		
		return sblogs;
	}
	
	/**
	 * @Title: addSblog
	 * @Description: 新增评论
	 * @param sblog
	 * @return void
	 */
	public void  addSblog(Sblog sblog){
		
		sblog.setSblog_date(DateUtil.dateToDateString(new Date()));
		sblogDao.addSblog(sblog);
		
		
	}
	
	/**
	 * @Title: addOrders
	 * @Description: 添加商品订单
	 * @param orders
	 * @return Orders
	 */
	public void addOrders(Orders orders) {
		
		//生成订单号
		String orders_no = DateUtil.dateToDateString(new Date(), "yyyyMMddHHmmss")+orders.getUser_id();
		orders.setOrders_no(orders_no);
		//订单日期
		orders.setOrders_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd"));
		//1：待发货
		orders.setOrders_flag(1);
		//保存订单
		ordersDao.addOrders(orders);
		
		
	}
	
	/**
	 * @Title: listOrderss
	 * @Description: 商品订单查询
	 * @param orders
	 * @return List<Orders>
	 */
	public List<Orders>  listOrderss(Orders orders,int[] sum){
		
		if (sum!=null) {
			sum[0] = ordersDao.listOrderssCount(orders);
		}
		List<Orders> orderss = ordersDao.listOrderss(orders);
		
		
		return orderss;
	}
	
	/**
	 * @Title: queryOrders
	 * @Description: 商品订单查询
	 * @param orders
	 * @return Orders
	 */
	public Orders  queryOrders(Orders orders){
		
		Orders _orders = ordersDao.getOrders(orders);
		
		return _orders;
	}
	
	/**
	 * @Title: listLogisticss
	 * @Description: 查询商品物流信息
	 * @param logistics
	 * @return List<Logistics>
	 */
	public List<Logistics>  listLogisticss(Logistics logistics,int[] sum){
		
		if (sum!=null) {
			sum[0] = logisticsDao.listLogisticssCount(logistics);
		}
		List<Logistics> logisticss = logisticsDao.listLogisticss(logistics);
		
		return logisticss;
	}
	
	/**
	 * @Title: finishOrders
	 * @Description: 确认收货
	 * @param Orders
	 * @return void
	 */
	public void finishOrders(Orders orders) {
		
		//确认收货
		orders.setOrders_flag(3);
		ordersDao.updateOrders(orders);
		
	}
	
	/**
	 * @Title: listCollects
	 * @Description: 查询收藏信息
	 * @param collect
	 * @return List<Collect>
	 */
	public List<Collect>  listCollects(Collect collect,int[] sum){
		
		if (sum!=null) {
			sum[0] = collectDao.listCollectsCount(collect);
		}
		List<Collect> collects = collectDao.listCollects(collect);

		
		return collects;
	}
	
	/**
	 * @Title: addCollect
	 * @Description: 添加收藏
	 * @param report
	 * @return void
	 */
	public void addCollect(Collect collect) {
		
		//添加收藏
		Collect _collect = collectDao.getCollect(collect);
		if (_collect==null) {
			collect.setCollect_date(DateUtil.getCurDateTime());
			collectDao.addCollect(collect);
		}
		
	}
	
	/**
	 * @Title: delCollect
	 * @Description: 删除商品收藏
	 * @param collect
	 * @return void
	 */
	public void delCollects(Collect collect) {
		
		collectDao.delCollects(collect.getIds().split(","));
		
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public IGoodsDao getGoodsDao() {
		return goodsDao;
	}

	public IGoodsPicDao getGoodsPicDao() {
		return goodsPicDao;
	}

	public ISblogDao getSblogDao() {
		return sblogDao;
	}

	public IOrdersDao getOrdersDao() {
		return ordersDao;
	}

	public ILogisticsDao getLogisticsDao() {
		return logisticsDao;
	}

	public ICollectDao getCollectDao() {
		return collectDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public void setGoodsDao(IGoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	public void setGoodsPicDao(IGoodsPicDao goodsPicDao) {
		this.goodsPicDao = goodsPicDao;
	}

	public void setSblogDao(ISblogDao sblogDao) {
		this.sblogDao = sblogDao;
	}

	public void setOrdersDao(IOrdersDao ordersDao) {
		this.ordersDao = ordersDao;
	}

	public void setLogisticsDao(ILogisticsDao logisticsDao) {
		this.logisticsDao = logisticsDao;
	}

	public void setCollectDao(ICollectDao collectDao) {
		this.collectDao = collectDao;
	}
}
