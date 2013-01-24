package com.siriusif.service.model.impl;

import org.springframework.stereotype.Repository;

import com.siriusif.model.Hall;
import com.siriusif.service.HibernateDaoImpl;
import com.siriusif.service.model.HallDao;

@Repository("hallDao")
public class HallDaoImpl extends HibernateDaoImpl<Hall, Long> implements HallDao{

}
