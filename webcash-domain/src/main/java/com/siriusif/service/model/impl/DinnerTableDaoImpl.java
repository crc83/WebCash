package com.siriusif.service.model.impl;

import org.springframework.stereotype.Repository;

import com.siriusif.model.DinnerTable;
import com.siriusif.service.HibernateDaoImpl;
import com.siriusif.service.model.DinnerTableDao;

@Repository("tablesDao")
public class DinnerTableDaoImpl extends HibernateDaoImpl<DinnerTable, Long> implements DinnerTableDao{

}
