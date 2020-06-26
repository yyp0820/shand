package com.nkl.page.dao;

import java.util.List;
import com.nkl.page.domain.Logistics;
import com.nkl.page.domain.User;

public interface ILogisticsDao {

	public abstract int addLogistics(Logistics logistics);

	public abstract int delLogistics(String logistics_id);

	public abstract int delLogisticss(String[] logistics_ids);

	public abstract int updateLogistics(Logistics logistics);

	public abstract Logistics getLogistics(Logistics logistics);

	public abstract int getLogisticsId(User user);

	public abstract List<Logistics>  listLogisticss(Logistics logistics);

	public abstract int listLogisticssCount(Logistics logistics);

}
