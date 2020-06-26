package com.nkl.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nkl.admin.manager.AdminManager;
import com.nkl.common.util.DateUtil;
import com.nkl.common.util.Md5;
import com.nkl.common.util.PaperUtil;
import com.nkl.page.domain.Goods;
import com.nkl.page.domain.Logistics;
import com.nkl.page.domain.Orders;
import com.nkl.page.domain.User;

@Controller
public class AdminAction{

	@Autowired
	AdminManager adminManager;
	public AdminManager getAdminManager() {
		return adminManager;
	}
	public void setAdminManager(AdminManager adminManager) {
		this.adminManager = adminManager;
	}

	String tip;
	
	/**
	 * @Title: saveAdmin
	 * @Description: 保存修改个人信息
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_saveAdmin.action")
	public String saveAdmin(User paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//验证用户会话是否失效
			if (!validateAdmin(httpSession)) {
				return "loginTip";
			}
			 //保存修改个人信息
			adminManager.updateUser(paramsUser);
			//更新session
			User admin = new User();
			admin.setUser_id(paramsUser.getUser_id());
			admin = adminManager.getUser(admin);
			httpSession.setAttribute("admin", admin);

			setSuccessTip("编辑成功", "modifyInfo.jsp", model);
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("编辑异常", "modifyInfo.jsp", model);
		}
		return "infoTip";
	}
	
	/**
	 * @Title: saveAdminPass
	 * @Description: 保存修改个人密码
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_saveAdminPass.action")
	public String saveAdminPass(User paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//验证用户会话是否失效
			if (!validateAdmin(httpSession)) {
				return "loginTip";
			}
			//验证原密码
			User admin = (User)httpSession.getAttribute("admin");
			String user_passOld1 = admin.getUser_pass();
			//String user_passOld2 = Md5.makeMd5(paramsUser.getUser_passOld());
			if (!user_passOld1.equals(paramsUser.getUser_passOld())) {
				setErrorTip("原密码不正确", "modifyPwd.jsp", model);
				return "infoTip";
			}
			 //保存修改个人密码
			adminManager.updateUser(paramsUser);
			//更新session
			if (admin!=null) {
				admin.setUser_pass(paramsUser.getUser_pass());
				httpSession.setAttribute("admin", admin);
			}

			setSuccessTip("修改成功", "modifyPwd.jsp", model);
		} catch (Exception e) {
			setErrorTip("修改异常", "modifyPwd.jsp", model);
		}
		return "infoTip";
	}
	
	/**
	 * @Title: listUsers
	 * @Description: 查询用户
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_listUsers.action")
	public String listUsers(User paramsUser,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsUser==null) {
				paramsUser = new User();
			}
			if (paperUtil==null) {
				paperUtil = new PaperUtil();
			}
			//设置分页信息
			paperUtil.setPagination(paramsUser);
			//总的条数
			int[] sum={0};
			//查询用户列表
			paramsUser.setUser_type(1);
			List<User> users = adminManager.listUsers(paramsUser,sum); 
			model.addAttribute("users", users);
			model.addAttribute("paramsUser", paramsUser);
			paperUtil.setTotalCount(sum[0]);

		} catch (Exception e) {
			setErrorTip("查询用户异常", "main.jsp", model);
			return "infoTip";
		}
		
		return "userShow";
	}
	
	/**
	 * @Title: addUserShow
	 * @Description: 显示添加用户页面
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addUserShow.action")
	public String addUserShow(ModelMap model){
		return "userEdit";
	}
	
	/**
	 * @Title: addUser
	 * @Description: 添加用户
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addUser.action")
	public String addUser(User paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			//检查用户是否存在
			User user = new User();
			user.setUser_name(paramsUser.getUser_name());
			user = adminManager.getUser(user);
			if (user!=null) {
				model.addAttribute("tip","失败，该用户名已经存在！");
				model.addAttribute("user", paramsUser);
				
				return "userEdit";
			}
			 //添加用户
			paramsUser.setUser_type(1);
			paramsUser.setReg_date(DateUtil.getCurDateTime());
			adminManager.addUser(paramsUser);
			
			setSuccessTip("添加成功", "Admin_listUsers.action", model);
		} catch (Exception e) {
			setErrorTip("添加用户异常", "Admin_listUsers.action", model);
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editUser
	 * @Description: 编辑用户
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_editUser.action")
	public String editUser(User paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //得到用户
			User user = adminManager.getUser(paramsUser);
			model.addAttribute("user", user);
			
		} catch (Exception e) {
			setErrorTip("查询用户异常", "Admin_listUsers.action", model);
			return "infoTip";
		}
		
		return "userEdit";
	}
	
	/**
	 * @Title: saveUser
	 * @Description: 保存编辑用户
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_saveUser.action")
	public String saveUser(User paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //保存编辑用户
			adminManager.updateUser(paramsUser);
			
			setSuccessTip("编辑成功", "Admin_listUsers.action", model);
		} catch (Exception e) {
			setErrorTip("编辑用户异常", "Admin_listUsers.action", model);
			return "infoTip";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delUsers
	 * @Description: 删除用户
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_delUsers.action")
	public String delUsers(User paramsUser,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //删除用户
			adminManager.delUsers(paramsUser);
			
			setSuccessTip("删除用户成功", "Admin_listUsers.action", model);
		} catch (Exception e) {
			setErrorTip("删除用户异常", "Admin_listUsers.action", model);
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listGoodss
	 * @Description: 查询产品
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_listGoodss.action")
	public String listGoodss(Goods paramsGoods,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsGoods==null) {
				paramsGoods = new Goods();
			}
			//设置分页信息
			paperUtil.setPagination(paramsGoods);
			int[] sum={0};
			List<Goods> goodss = adminManager.listGoodss(paramsGoods,sum); 
			
			model.addAttribute("goodss", goodss);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询产品异常","main.jsp",model);
			return "infoTip";
		}
		
		return "goodsShow";
	}
	
	/**
	 * @Title: queryGoods
	 * @Description: 查询产品详情
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_queryGoods.action")
	public String queryGoods(Goods paramsGoods,ModelMap model){
		try {
			 //得到产品
			Goods goods = adminManager.queryGoods(paramsGoods);
			model.addAttribute("goods", goods);
			
		} catch (Exception e) {
			setErrorTip("查询产品异常","Admin_listGoodss.action",model);
			return "infoTip";
		}
		
		return "goodsDetail";
	}
	
	/**
	 * @Title: assessGoods
	 * @Description: 审核产品
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_assessGoods.action")
	public String assessGoods(Goods paramsGoods,ModelMap model){
		try {
			 //审核产品
			paramsGoods.setGoods_status(2);
			adminManager.updateGoods(paramsGoods);

			setSuccessTip("审核成功","Admin_listGoodss.action",model);
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("审核异常","Admin_listGoodss.action",model);
		}
		return "infoTip";
	}
	 
	/**
	 * @Title: delGoodss
	 * @Description: 删除产品
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_delGoodss.action")
	public String delGoodss(Goods paramsGoods,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //删除产品
			adminManager.delGoodss(paramsGoods);

			setSuccessTip("删除产品成功","Admin_listGoodss.action",model);
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("删除产品异常","Admin_listGoodss.action",model);
		}
		return "infoTip";
	}
	
	/**
	 * @Title: listOrderss
	 * @Description: 查询商品订单
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_listOrderss.action")
	public String listOrderss(Orders paramsOrders,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsOrders==null) {
				paramsOrders = new Orders();
			}
			//设置分页信息
			paperUtil.setPagination(paramsOrders);
			//总的条数
			int[] sum={0};
			//查询商品订单列表
			List<Orders> orderss = adminManager.listOrderss(paramsOrders,sum); 
			
			model.addAttribute("orderss", orderss);
			paperUtil.setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询商品订单异常","main.jsp",model);
			return "infoTip";
		}
		
		return "ordersShow";
	}
	
	/**
	 * @Title: sendOrders
	 * @Description: 订单发货
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_sendOrders.action")
	public String sendOrders(Orders paramsOrders,ModelMap model){
		try {
			 //订单发货
			adminManager.sendOrders(paramsOrders);
			
			setSuccessTip("订单发货成功","Admin_listOrderss.action",model);
		} catch (Exception e) {
			setErrorTip("订单发货异常","Admin_listOrderss.action",model);
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delOrderss
	 * @Description: 删除商品订单
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_delOrderss.action")
	public String delOrderss(Orders paramsOrders,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			 //删除商品订单
			adminManager.delOrderss(paramsOrders);
			
			setSuccessTip("删除商品订单成功","Admin_listOrderss.action",model);
		} catch (Exception e) {
			setErrorTip("删除商品订单异常","Admin_listOrderss.action",model);
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listLogisticss
	 * @Description: 查询商品订单物流信息
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_listLogisticss.action")
	public String listLogisticss(Logistics paramsLogistics,PaperUtil paperUtil,
			ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
		try {
			if (paramsLogistics==null) {
				paramsLogistics = new Logistics();
			}
			//设置分页信息不分页
			paramsLogistics.setStart(-1);
			//查询商品订单物流信息
			List<Logistics> logisticss = adminManager.listLogisticss(paramsLogistics,null); 
			model.addAttribute("logisticss", logisticss);
			
			model.addAttribute("orders_no", paramsLogistics.getOrders_no());
			
		} catch (Exception e) {
			setErrorTip("查询商品订单物流信息异常","main.jsp",model);
			return "infoTip";
		}
		
		return "logisticsShow";
	}
	
	/**
	 * @Title: addLogisticsShow
	 * @Description: 显示添加物流信息页面
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addLogisticsShow.action")
	public String addLogisticsShow(Logistics paramsLogistics,ModelMap model){
		model.addAttribute("orders_no", paramsLogistics.getOrders_no());
		return "logisticsEdit";
	}
	
	/**
	 * @Title: addLogistics
	 * @Description: 添加物流信息
	 * @return String
	 */
	@RequestMapping(value="admin/Admin_addLogistics.action")
	public String addLogistics(Logistics paramsLogistics,ModelMap model){
		try {
			 //添加物流信息
			adminManager.addLogistics(paramsLogistics);

			setSuccessTip("添加物流信息成功", "Admin_listLogisticss.action?orders_no="+paramsLogistics.getOrders_no(), model);
		} catch (Exception e) {
			setErrorTip("添加物流信息异常", "Admin_listLogisticss.action?orders_no="+paramsLogistics.getOrders_no(), model);
		}
		return "infoTip";
	}
	
	private boolean validateAdmin(HttpSession httpSession){
		User admin = (User)httpSession.getAttribute("admin");
		if (admin!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	private void setErrorTip(String tip,String url,ModelMap model){
		model.addAttribute("tipType", "error");
		model.addAttribute("tip", tip);
		model.addAttribute("url1", url);
		model.addAttribute("value1", "确 定");
	}
	
	private void setSuccessTip(String tip,String url,ModelMap model){
		model.addAttribute("tipType", "success");
		model.addAttribute("tip", tip);
		model.addAttribute("url1", url);
		model.addAttribute("value1", "确 定");
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}


}
