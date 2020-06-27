package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

public class GoodsPic extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1084515258017756271L;
	private int pic_id; // 
	private int goods_id; // 
	private String goods_pic; // 

	private String ids;
	private String random;

	public void setPic_id(int pic_id){
		this.pic_id=pic_id;
	}

	public int getPic_id(){
		return pic_id;
	}

	public void setGoods_id(int goods_id){
		this.goods_id=goods_id;
	}

	public int getGoods_id(){
		return goods_id;
	}

	public void setGoods_pic(String goods_pic){
		this.goods_pic=goods_pic;
	}

	public String getGoods_pic(){
		return goods_pic;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIds() {
		return ids;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getRandom() {
		return random;
	}

}
