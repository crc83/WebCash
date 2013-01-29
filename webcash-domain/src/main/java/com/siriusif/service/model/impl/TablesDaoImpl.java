package com.siriusif.service.model.impl;

import org.springframework.stereotype.Repository;

import com.siriusif.model.TablesHall;
import com.siriusif.service.HibernateDaoImpl;
import com.siriusif.service.model.TablesDao;

@Repository("tablesDao")
public class TablesDaoImpl extends HibernateDaoImpl<TablesHall, Long> implements TablesDao{

}
