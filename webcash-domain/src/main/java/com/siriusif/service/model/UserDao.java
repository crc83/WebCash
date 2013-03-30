package com.siriusif.service.model;

import com.siriusif.model.User;
import com.siriusif.service.GenericDao;

public interface UserDao extends GenericDao<User, Long>{

	boolean login(String username, String password);

}
