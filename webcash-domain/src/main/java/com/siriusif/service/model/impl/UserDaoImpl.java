package com.siriusif.service.model.impl;

import org.springframework.stereotype.Repository;
import com.siriusif.model.User;
import com.siriusif.service.HibernateDaoImpl;
import com.siriusif.service.model.UserDao;

@Repository("userDao")
public class UserDaoImpl extends HibernateDaoImpl<User, Long> implements UserDao {

}
