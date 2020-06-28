package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

public class Orders extends BaseDomain {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -6925524708882684408L;
	private int orders_id; // 
	private String orders_no;
	private int goods_id; // 
	private String goods_name;
	private double goods_price; 
	private String orders_date;
	private int user_id; // 
	private String real_name;
	private String user_address; // 
	private String user_phone; // 
	private int orders_flag; // 1：待发货 2-已发货 3-已收货

	private int sell_id; // 
	private String orders_date_min;
	private String orders_date_max;
	
	private String ids;
	
	public String getOrders_flagDesc() {
		switch (orders_flag) {
			case 1:
				return "待发货";
			case 2:
				return "已发货";
			case 3:
				return "已收货"; 
			default:
				return "";
		}
	}
	
	public int getOrders_flag() {
		return orders_flag;
	}

	public void setOrders_flag(int orders_flag) {
		this.orders_flag = orders_flag;
	}

	public int getOrders_id() {
		return orders_id;
	}

	public void setOrders_id(int orders_id) {
		this.orders_id = orders_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getOrders_date() {
		return orders_date;
	}

	public void setOrders_date(String orders_date) {
		this.orders_date = orders_date;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getOrders_no() {
		return orders_no;
	}

	public void setOrders_no(String orders_no) {
		this.orders_no = orders_no;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getOrders_date_min() {
		return orders_date_min;
	}

	public void setOrders_date_min(String orders_date_min) {
		this.orders_date_min = orders_date_min;
	}

	public String getOrders_date_max() {
		return orders_date_max;
	}

	public void setOrders_date_max(String orders_date_max) {
		this.orders_date_max = orders_date_max;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public int getGoods_id() {
		return goods_id;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public double getGoods_price() {
		return goods_price;
	}

	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public void setGoods_price(double goods_price) {
		this.goods_price = goods_price;
	}

	public int getSell_id() {
		return sell_id;
	}

	public void setSell_id(int sell_id) {
		this.sell_id = sell_id;
	}

}
