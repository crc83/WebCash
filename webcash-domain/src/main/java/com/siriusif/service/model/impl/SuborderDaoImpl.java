package com.siriusif.service.model.impl;

import org.springframework.stereotype.Repository;

import com.siriusif.model.Suborder;
import com.siriusif.service.HibernateDaoImpl;
import com.siriusif.service.model.SuborderDao;

@Repository("suborderDao")
public class SuborderDaoImpl extends HibernateDaoImpl<Suborder, Long> implements
		SuborderDao {

}
