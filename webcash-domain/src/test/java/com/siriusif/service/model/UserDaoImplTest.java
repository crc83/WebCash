package com.siriusif.service.model;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.siriusif.helper.AbstractSpringTest;
import com.siriusif.model.User;
import static org.junit.Assert.*;

public class UserDaoImplTest extends AbstractSpringTest {

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