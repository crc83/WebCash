package com.siriusif.service.model;

import java.util.Date;
import com.siriusif.model.Workshift;
import com.siriusif.service.GenericDao;

public interface WorkshiftDao extends GenericDao<Workshift, Long> {

	int countForDate(Date currentWorkshiftDate);

}
