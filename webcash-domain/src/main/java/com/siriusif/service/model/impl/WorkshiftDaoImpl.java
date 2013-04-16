package com.siriusif.service.model.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.siriusif.model.Workshift;
import com.siriusif.service.HibernateDaoImpl;
import com.siriusif.service.model.WorkshiftDao;

@Repository("workshiftDao")
public class WorkshiftDaoImpl extends HibernateDaoImpl<Workshift, Long> implements WorkshiftDao{
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.siriusif.service.HibernateDaoImpl#find(java.io.Serializable)
	 */
	@Override
	@Transactional
	public Workshift find(Long key) {
		Workshift w = super.find(key);
		initializeCollection(w.getOrders());
		return w;
	}

	
	public int countForDate(Date currentWorkshiftDate) {
		// TODO SB : Use javax query builder api or JPQL
		// TODO SB : Add PMD ceck to restrict imports like 
		//import org.hibernate.Criteria;
		//import org.hibernate.criterion.*;
		//
		Criteria criteria = currentSession()
				.createCriteria(Workshift.class)
				.add(Restrictions.eq("closedAt",currentWorkshiftDate))
				.setProjection(Projections.rowCount());
		return ((Long)criteria.list().get(0)).intValue();
	}

	@SuppressWarnings("unchecked")
	public List<Workshift> getOpenedWorkshiftsList() {
		return currentSession()
			.createCriteria(Workshift.class)
			.add(Restrictions.isNull("closedAt"))
			.list();
	}

}
