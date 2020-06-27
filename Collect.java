package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

public class Collect extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -674161960515333295L;
	private int collect_id; // 
	private int goods_id; // 
	private int user_id; // 
	private String collect_date; // 
	
	private String goods_name; // 
	private String random; // 
	private String ids; // 
	
	public void setCollect_id(int collect_id){
		this.collect_id=collect_id;
	}

	public int getCollect_id(){
		return collect_id;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public int getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getCollect_date() {
		return collect_date;
	}

	public void setCollect_date(String collect_date) {
		this.collect_date = collect_date;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
}
