package com.nkl.admin.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nkl.common.util.Md5;
import com.nkl.common.util.StringUtil;
import com.nkl.page.dao.ICollectDao;
import com.nkl.page.dao.IGoodsDao;
import com.nkl.page.dao.IGoodsPicDao;
import com.nkl.page.dao.ILogisticsDao;
import com.nkl.page.dao.IOrdersDao;
import com.nkl.page.dao.ISblogDao;
import com.nkl.page.dao.IUserDao;
import com.nkl.page.domain.Goods;
import com.nkl.page.domain.Logistics;
import com.nkl.page.domain.Orders;
import com.nkl.page.domain.User;

@Service
public class AdminManager {
	
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
	 * @Title: listUsers
	 * @Description: 用户查询
	 * @param user
	 * @return List<User>
	 */
	public List<User>  listUsers(User user,int[] sum){
		
		if (sum!=null) {
			sum[0] = userDao.listUsersCount(user);
		}
		List<User> users = userDao.listUsers(user);
		
		
		return users;
	}
	
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
	 * @Title: addUser
	 * @Description: 添加用户
	 * @param user
	 * @return void
	 */
	public void  addUser(User user){
		
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		userDao.addUser(user);
		
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
	 * @Title: delUsers
	 * @Description: 删除用户信息
	 * @param user
	 * @return void
	 */
	public void  delUsers(User user){
		
		userDao.delUsers(user.getIds().split(","));
		
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
	
	/**
	 * @Title: queryGoods
	 * @Description: 产品查询
	 * @param goods
	 * @return Goods
	 */
	public Goods queryGoods(Goods goods) {
		
		Goods _goods = goodsDao.getGoods(goods);
		
		return _goods;
	}
	
	/**
	 * @Title: updateGoods
	 * @Description: 更新产品
	 * @param goods
	 * @return void
	 */
	public void updateGoods(Goods goods) {
		
		goodsDao.updateGoods(goods);
		
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
	 * @Title: delOrderss
	 * @Description: 删除商品订单信息
	 * @param orders
	 * @return void
	 */
	public void  delOrderss(Orders orders){
		
		ordersDao.delOrderss(orders.getIds().split(","));
		
	}
	
	/**
	 * @Title: sendOrders
	 * @Description: 订单发货
	 * @param orders
	 * @return void
	 */
	public void sendOrders(Orders orders) {
		
		//确认订单信息
		orders.setOrders_flag(2);//2-已发货 
		ordersDao.updateOrders(orders);
		
	}
	
	
	/**
	 * @Title: listLogisticss
	 * @Description: 商品订单物流信息查询
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
	 * @Title: addLogistics
	 * @Description: 新增物流信息
	 * @param logistics
	 * @return void
	 */
	public void addLogistics(Logistics logistics) {
		
		logisticsDao.addLogistics(logistics);
		
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
