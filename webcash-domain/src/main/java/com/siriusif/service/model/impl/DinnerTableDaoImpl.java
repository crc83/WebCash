package com.siriusif.service.model.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.siriusif.model.DinnerTable;
import com.siriusif.service.HibernateDaoImpl;
import com.siriusif.service.model.DinnerTableDao;

@Repository("tablesDao")
public class DinnerTableDaoImpl extends HibernateDaoImpl<DinnerTable, Long> implements DinnerTableDao{
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.siriusif.service.HibernateDaoImpl#find(java.io.Serializable)
	 */
	@Override
	@Transactional
	public DinnerTable find(Long key) {
		DinnerTable dt = super.find(key);
		initializeCollection(dt.getOrders());
		return dt;
	}

}
