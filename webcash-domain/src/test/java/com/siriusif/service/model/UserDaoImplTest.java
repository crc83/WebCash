package com.siriusif.service.model;

import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.siriusif.helper.AbstractSpringTest;
import com.siriusif.helper.Helper;
import com.siriusif.model.User;

import static org.junit.Assert.*;

public class UserDaoImplTest extends AbstractSpringTest {

    @Autowired
    private UserDao userDao;
    
    private User[] users;

    /**
     * fill inmemory db with test data from json
     * @throws JsonSyntaxException
     * @throws JsonIOException
     * @throws UnsupportedEncodingException
     */
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
        user.setRole("ROLE_USER");
        userDao.add(user);

        // list should have one more employee now
        assertTrue (size < userDao.list().size());
    }
    
    /** 
     * when : I try to login with correct credentials
     * then : I receive "true"
     */
    @Test
    public void testCorrectLogin() {
    	assertTrue(userDao.login("some_user", "some_password"));
    }
    
    /**
     * when : I try to login with wrong login and/or password
     * then : I receive "false"
     */
    @Test
    public void testIncorrectLogin() {
    	assertFalse(userDao.login("some_user", "wrong_password"));
    	assertFalse(userDao.login("wrong_user", "some_password"));
    	assertFalse(userDao.login("wrong_user", "wrong_password"));
    }
    
    /**
     * when : I try to find user by login and it's exist
     * than : I should receive this user
     */
    @Test
    public void testFindByLoginFound() {
    	assertNotNull(userDao.findByLogin("admin"));
    }

    /**
     * when : I try to find user by login and it's exist
     * than : I should receive null
     */
    @Test
    public void testFindByLoginNotFound() {
    	assertNull(userDao.findByLogin("unknown_user"));    	
    }
}