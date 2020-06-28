package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

public class Sblog extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -674161960515333295L;
	private int sblog_id; // 
	private int goods_id; // 
	private int user_id; // 
	private String sblog_title; // 
	private String sblog_content; // 
	private String sblog_date; // 
	private String sblog_pic; // 
	
	private String nick_name; // 
	private String random; // 
	private String ids; // 
	
	public void setSblog_id(int sblog_id){
		this.sblog_id=sblog_id;
	}

	public int getSblog_id(){
		return sblog_id;
	}

	public void setSblog_title(String sblog_title){
		this.sblog_title=sblog_title;
	}

	public String getSblog_title(){
		return sblog_title;
	}

	public void setSblog_content(String sblog_content){
		this.sblog_content=sblog_content;
	}

	public String getSblog_content(){
		return sblog_content;
	}

	public void setSblog_pic(String sblog_pic){
		this.sblog_pic=sblog_pic;
	}

	public String getSblog_pic(){
		return sblog_pic;
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

	public String getSblog_date() {
		return sblog_date;
	}

	public void setSblog_date(String sblog_date) {
		this.sblog_date = sblog_date;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

}
