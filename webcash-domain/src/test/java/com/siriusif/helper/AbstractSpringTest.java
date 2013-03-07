package com.siriusif.helper;

import static org.junit.Assert.assertEquals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;



@ContextConfiguration(locations = {"/webcash-persistence-beans.xml"})
@ActiveProfiles("test")
public abstract class AbstractSpringTest  extends AbstractJUnit4SpringContextTests{
	
}