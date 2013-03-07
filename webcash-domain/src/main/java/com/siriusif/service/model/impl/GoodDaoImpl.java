package com.siriusif.service.model.impl;

import org.springframework.stereotype.Repository;

import com.siriusif.model.Good;
import com.siriusif.service.HibernateDaoImpl;
import com.siriusif.service.model.GoodDao;

@Repository("goodDao")
public class GoodDaoImpl extends HibernateDaoImpl<Good, Long> implements GoodDao {

}
