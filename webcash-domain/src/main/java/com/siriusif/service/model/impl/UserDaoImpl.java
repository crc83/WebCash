package com.siriusif.service.model.impl;

import org.springframework.stereotype.Repository;
import com.siriusif.model.User;
import com.siriusif.service.HibernateDaoImpl;
import com.siriusif.service.model.UserDao;

@Repository("userDao")
public class UserDaoImpl extends HibernateDaoImpl<User, Long> implements UserDao {

	@Override
	public boolean login(String username, String password) {
		// TODO SB : Replace HQL queries to JPQL queries
		return currentSession().createQuery("SELECT u FROM User u WHERE u.login= :username AND u.password = :password")
			.setParameter("username", username)
			.setParameter("password", password).list().size()==1;
	}

}
