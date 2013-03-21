package com.siriusif.service.model.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.siriusif.model.Group;
import com.siriusif.service.HibernateDaoImpl;
import com.siriusif.service.model.GroupDao;

@Repository("groupDao")
public class GroupDaoImpl extends HibernateDaoImpl<Group, Long> implements GroupDao {
	
	/* (non-Javadoc)
	 * @see com.siriusif.service.HibernateDaoImpl#find(java.io.Serializable)
	 */
	@Override
	@Transactional
	public Group find(Long key) {
		Group so = super.find(key);
		initializeCollection(so.getSubGroups());
		initializeCollection(so.getGoods());
		return so; 
	}

}
