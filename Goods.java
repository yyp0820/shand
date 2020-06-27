package com.nkl.page.domain;

import java.util.ArrayList;
import java.util.List;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;

public class Goods extends BaseDomain {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -6925524708882684408L;
	private int goods_id; // 
	private int user_id; // 
	private String goods_name; // 
	private String goods_type1; // 
	private String goods_type2; // 
	private double goods_price; // 
	private String goods_desc; // 
	private int goods_sale; // 1:不可小刀 2:可小刀
	private int goods_click; // 
	private String user_phone; // 
	private String user_qq; // 
	private String goods_address; // 
	private String goods_date; // 
	private int goods_flag; // 1:商品发布 2:商品求购 3:商品交换
	private int goods_status; // 1:待审核 2:审核通过
	
	private String nick_name; // 
	private String goods_pic; // 
	private String goods_type; // 
	private String ids;
	
	private String goods_head; // 
	private String goods_pics; // 
	private List<Sblog> sblogs = new ArrayList<Sblog>();
	
	public String getGoods_statusDesc(){
		switch (goods_status) {
		case 1:
			return "待审核";
		case 2:
			return "审核通过"; 
		default:
			return "";
		}
	}
	
	public String getGoods_saleDesc(){
		switch (goods_sale) {
		case 1:
			return "不可小刀";
		case 2:
			return "可小刀"; 
		default:
			return "";
		}
	}
	
	public String getGoods_flagDesc(){
		switch (goods_flag) {
		case 1:
			return "商品发布";
		case 2:
			return "商品求购";
		case 3:
			return "商品交换";
		default:
			return "";
		}
	}
	
	public int getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public double getGoods_price() {
		return goods_price;
	}

	public void setGoods_price(double goods_price) {
		this.goods_price = goods_price;
	}

	public String getGoods_descShow(){
		if (!StringUtil.isEmptyString(goods_desc)) {
			return Transcode.htmlDiscode(goods_desc);
		}
		return goods_desc;
	}
	
	public String getGoods_desc() {
		return goods_desc;
	}

	public void setGoods_desc(String goods_desc) {
		this.goods_desc = goods_desc;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public int getGoods_flag() {
		return goods_flag;
	}

	public void setGoods_flag(int goods_flag) {
		this.goods_flag = goods_flag;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getGoods_click() {
		return goods_click;
	}

	public void setGoods_click(int goods_click) {
		this.goods_click = goods_click;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_qq() {
		return user_qq;
	}

	public void setUser_qq(String user_qq) {
		this.user_qq = user_qq;
	}

	public String getGoods_address() {
		return goods_address;
	}

	public void setGoods_address(String goods_address) {
		this.goods_address = goods_address;
	}

	public String getGoods_date() {
		return goods_date;
	}

	public void setGoods_date(String goods_date) {
		this.goods_date = goods_date;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getGoods_pic() {
		return goods_pic;
	}

	public void setGoods_pic(String goods_pic) {
		this.goods_pic = goods_pic;
	}

	public List<Sblog> getSblogs() {
		return sblogs;
	}

	public void setSblogs(List<Sblog> sblogs) {
		this.sblogs = sblogs;
	}

	public String getGoods_type1() {
		return goods_type1;
	}

	public void setGoods_type1(String goods_type1) {
		this.goods_type1 = goods_type1;
	}

	public String getGoods_type2() {
		return goods_type2;
	}

	public void setGoods_type2(String goods_type2) {
		this.goods_type2 = goods_type2;
	}

	public String getGoods_type() {
		return goods_type;
	}

	public void setGoods_type(String goods_type) {
		this.goods_type = goods_type;
	}

	public int getGoods_sale() {
		return goods_sale;
	}

	public void setGoods_sale(int goods_sale) {
		this.goods_sale = goods_sale;
	}

	public String getGoods_head() {
		return goods_head;
	}

	public void setGoods_head(String goods_head) {
		this.goods_head = goods_head;
	}

	public String getGoods_pics() {
		return goods_pics;
	}

	public void setGoods_pics(String goods_pics) {
		this.goods_pics = goods_pics;
	}

	public int getGoods_status() {
		return goods_status;
	}

	public void setGoods_status(int goods_status) {
		this.goods_status = goods_status;
	}

}
