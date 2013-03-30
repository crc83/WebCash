package com.siriusif.service.model;

import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.siriusif.helper.AbstractSpringTest;
import com.siriusif.helper.Helper;
import com.siriusif.model.User;
import com.siriusif.model.Workshift;

import static org.junit.Assert.*;

public class UserDaoImplTest extends AbstractSpringTest {

    @Autowired
    private UserDao userDao;
    
    private User[] users;

    @Before
    public void setUp() throws JsonSyntaxException, JsonIOException, UnsupportedEncodingException {
    	users = Helper.fromJson("/users.json", User[].class);
    	userDao.clearAll();
    	for (User user : users){
    		userDao.add(user);
    	}
    }
    
    @Test
    public void testAdd() {
        int size = userDao.list().size();
        
        User user = new User();
        user.setLogin("admin");
        user.setPassword("admin");
        user.setName("Administrator");
        userDao.add(user);

        // list should have one more employee now
        assertTrue (size < userDao.list().size());
    }
    
    @Test
    public void testCorrectLogin() {
    	assertTrue(userDao.login("some_user", "some_password"));
    }
    
    @Test
    public void testIncorrectLogin() {
    	assertFalse(userDao.login("some_user", "wrong_password"));
    	assertFalse(userDao.login("wrong_user", "some_password"));
    	assertFalse(userDao.login("wrong_user", "wrong_password"));
    }
}