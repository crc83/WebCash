package com.siriusif.service.model.impl;

import java.util.Date;
import org.springframework.stereotype.Repository;
import com.siriusif.model.Workshift;
import com.siriusif.service.HibernateDaoImpl;
import com.siriusif.service.model.WorkshiftDao;

@Repository("workshiftDao")
public class WorkshiftDaoImpl extends HibernateDaoImpl<Workshift, Long> implements WorkshiftDao{

	@Override
	public int countForDate(Date currentWorkshiftDate) {
		// TODO Auto-generated method stub
		return 0;
	}

}
