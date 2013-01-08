package com.siriusif.service.model;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.siriusif.model.User;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration(locations = "/persistence-beans.xml")
public class UserDaoImplTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private UserDao userDao;

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
}