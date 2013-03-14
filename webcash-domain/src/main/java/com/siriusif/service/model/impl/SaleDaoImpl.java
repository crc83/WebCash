package com.siriusif.service.model.impl;

import org.springframework.stereotype.Repository;

import com.siriusif.model.Sale;
import com.siriusif.service.HibernateDaoImpl;
import com.siriusif.service.model.SaleDao;

@Repository("saleDao")
public class SaleDaoImpl extends HibernateDaoImpl<Sale, Long> implements SaleDao {

}
