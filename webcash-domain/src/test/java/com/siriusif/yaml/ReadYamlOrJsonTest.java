package com.siriusif.yaml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

import junit.framework.TestCase;

import org.junit.Test;

import com.esotericsoftware.yamlbeans.YamlReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.siriusif.model.Order;

public class ReadYamlOrJsonTest extends TestCase{
	@Test
	public void testReadYaml() throws IOException{
		BufferedReader bufferedReader = getCPFileReader("/order.yml");
		YamlReader reader2 = new YamlReader(bufferedReader);
		Order order = reader2.read(Order.class);
		assertEquals("administrator", order.getAuthor());
		assertEquals(0,order.getSum().compareTo(new BigDecimal(13.51)));
	}

	private BufferedReader getCPFileReader(String fileName)
			throws UnsupportedEncodingException {
		InputStream in = this.getClass().getResourceAsStream(fileName);
		Reader reader = new InputStreamReader(in, "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(reader);
		return bufferedReader;
	}

	@Test
	public void testReadJson() throws IOException{		
		Order order = new Gson().fromJson(getCPFileReader("/order.json"), Order.class);
		assertEquals("administrator", order.getAuthor());
		assertEquals(BigDecimal.valueOf(13.51),order.getSum());
	}
	
}
