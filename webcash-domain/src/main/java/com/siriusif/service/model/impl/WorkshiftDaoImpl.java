package com.siriusif.service.model.impl;

import org.springframework.stereotype.Repository;

import com.siriusif.model.Workshift;
import com.siriusif.service.HibernateDaoImpl;
import com.siriusif.service.model.WorkshiftDao;

@Repository("workshiftDao")
public class WorkshiftDaoImpl extends HibernateDaoImpl<Workshift, Long> implements WorkshiftDao{

}
