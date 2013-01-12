package com.siriusif.service.model;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "/persistence-beans.xml", "/test-persistence-beans.xml" })
public abstract class AbstractDaoImplTest  extends AbstractJUnit4SpringContextTests{

}
