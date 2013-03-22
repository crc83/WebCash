package com.siriusif.service.model.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.siriusif.model.Suborder;
import com.siriusif.service.HibernateDaoImpl;
import com.siriusif.service.model.SuborderDao;

@Repository("suborderDao")
public class SuborderDaoImpl extends HibernateDaoImpl<Suborder, Long> implements
		SuborderDao {

	/* (non-Javadoc)
	 * @see com.siriusif.service.HibernateDaoImpl#find(java.io.Serializable)
	 */
	@Override
	@Transactional
	public Suborder find(Long key) {
		Suborder so = super.find(key); 
		initializeCollection(so.getSales());
		return so; 
	}
	
	

}
