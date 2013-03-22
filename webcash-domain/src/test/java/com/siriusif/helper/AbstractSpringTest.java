package com.siriusif.helper;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;



@ContextConfiguration(locations = {"/webcash-persistence-beans.xml"})
@ActiveProfiles("test")
public abstract class AbstractSpringTest  extends AbstractJUnit4SpringContextTests{
	
}