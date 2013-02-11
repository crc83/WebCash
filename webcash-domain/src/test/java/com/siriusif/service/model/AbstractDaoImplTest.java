package com.siriusif.service.model;

import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;


@ContextConfiguration(locations = {"/webcash-persistence-beans.xml"})
@ActiveProfiles("test")
public abstract class AbstractDaoImplTest  extends AbstractJUnit4SpringContextTests{

}
