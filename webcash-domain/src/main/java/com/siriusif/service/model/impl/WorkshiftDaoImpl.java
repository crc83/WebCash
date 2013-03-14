package com.siriusif.service.model.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.siriusif.model.Workshift;
import com.siriusif.service.HibernateDaoImpl;
import com.siriusif.service.model.WorkshiftDao;

@Repository("workshiftDao")
public class WorkshiftDaoImpl extends HibernateDaoImpl<Workshift, Long> implements WorkshiftDao{

	public int countForDate(Date currentWorkshiftDate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressWarnings("unchecked")
	public List<Workshift> getOpenedWorkshiftsList() {
		return currentSession()
			.createCriteria(Workshift.class)
			.add(Restrictions.isNull(Workshift.CLOSED_AT))
			.list();
	}

}
