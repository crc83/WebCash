package com.siriusif.service.model;

import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@PropertySource("classpath*:test_db.properties")
@ContextConfiguration(locations = { "/webcash-persistance-beans.xml"})
public abstract class AbstractDaoImplTest  extends AbstractJUnit4SpringContextTests{

}
