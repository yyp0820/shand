package com.nkl.page.dao;

import java.util.List;
import com.nkl.page.domain.GoodsPic;

public interface IGoodsPicDao {

	public abstract int addGoodsPic(GoodsPic goodsPic);

	public abstract int delGoodsPic(String pic_id);

	public abstract int delGoodsPics(String[] pic_ids);

	public abstract int delGoodsPicByGoodsId(String goods_id);

	public abstract int updateGoodsPic(GoodsPic goodsPic);

	public abstract GoodsPic getGoodsPic(GoodsPic goodsPic);

	public abstract List<GoodsPic>  listGoodsPics(GoodsPic goodsPic);

	public abstract int listGoodsPicsCount(GoodsPic goodsPic);

}
