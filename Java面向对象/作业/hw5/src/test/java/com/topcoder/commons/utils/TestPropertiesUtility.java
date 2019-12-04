package com.topcoder.commons.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * this is a test class for class PropertiesUtility
 * @author mao jinyong
 */
public class TestPropertiesUtility {

	private Properties properties = new Properties();
	PropertiesUtility pu = new PropertiesUtility();
	private String key="class";
	private boolean required = true;
	@Before
	public void before() {
		properties.setProperty("String", "String");
		properties.setProperty("Integer", "1024");
		properties.setProperty("Double", "12.345");
		properties.setProperty("Long", "10241024");
		properties.setProperty("Float", "9.9");
		properties.setProperty("Date", "20191016");
		properties.setProperty("Class", "java.lang.String");
	}

	/**
	 *
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testGetStringProperty() throws Exception {
		String key1 = "String";
		String key2 = "hello"; // not found
		Class<Exception> exceptionClass = Exception.class;

		String actual1 = PropertiesUtility.getStringProperty(properties, key1, true, exceptionClass);
		String actual2 = PropertiesUtility.getStringProperty(properties, key2, false, exceptionClass);

		Assert.assertEquals(properties.getProperty(key1), actual1);
		Assert.assertEquals(properties.getProperty(key2), actual2);//null, null
		PropertiesUtility.getStringProperty(properties, key2, true, exceptionClass);
	}
	@Test(expected = Exception.class)
	public void testgetStringProperty1() throws Exception {
		Properties properties = new Properties();
		properties.setProperty("class1","123");
		String delimiter = "/s/s/s./s/s/s";
		PropertiesUtility.getStringsProperty(properties,key,delimiter,required,Exception.class);
	}
	/**
	 * test the function getStringProperty() with delimiter
	 * case 2: property = null and required = false
	 * @throws Exception
	 */
	@Test
	public void testgetStringProperty2() throws Exception {
		Properties properties = new Properties();
		properties.setProperty("class1","123");
		String delimiter = "/s/s/s./s/s/s";
		String value[] = PropertiesUtility.getStringsProperty(properties,key, delimiter, !required, Exception.class);
		assertEquals(null,value);
	}
	/**
	 * test the function getStringProperty() with delimiter
	 * case 2: property exists
	 * @throws Exception
	 */
	@Test
	public void testgetStringsProperty3() throws Exception {
		Properties properties = new Properties();
		properties.setProperty("class","1.2.3");
		String delimiter = "/d./d./d";
		String value[] = PropertiesUtility.getStringsProperty(properties,key,delimiter,required,Exception.class);
		assertEquals("1.2.3",value[0]);
	}

	/**
	 *
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testGetIntegerProperty() throws Exception {
		String key1 = "Integer";
		String key2 = "hello";//not found
		String key3 = "String";
		Class<Exception> exceptionClass = Exception.class;

		Integer actual = PropertiesUtility.getIntegerProperty(properties, key1, true, exceptionClass);
		Assert.assertEquals(Integer.valueOf(properties.getProperty(key1)), actual);
		Integer actua2 = PropertiesUtility.getIntegerProperty(properties, key2, false, exceptionClass);
		Assert.assertEquals(Integer.valueOf(properties.getProperty(key2)), actua2);
		Throwable t = null;
		try{
			PropertiesUtility.getIntegerProperty(properties, key2, false, exceptionClass);
		}catch (Exception ex){
			t = ex;
		}
		assertNotNull(t);

		Throwable t2 = null;
		try{
			PropertiesUtility.getIntegerProperty(properties, key3, true, exceptionClass);
		}catch (NumberFormatException  ex){
			t2 = ex;
		}
		assertNotNull(t2);

		Throwable t3 = null;
		try{
			PropertiesUtility.getIntegerProperty(properties, key2, true, exceptionClass);
		}catch (Exception ex){
			t3= ex;
		}
		assertNotNull(t3);
	}

	/**
	 *
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testGetLongProperty() throws Exception {
		String key1 = "Long";
		String key2 = "hello";//not found
		String key3 = "String";
		Class<Exception> exceptionClass = Exception.class;
		PropertiesUtility.getLongProperty(properties, key1, true, exceptionClass);
		PropertiesUtility.getLongProperty(properties, key2, false, exceptionClass);
		PropertiesUtility.getLongProperty(properties, key2, true, exceptionClass);

		PropertiesUtility.getLongProperty(properties, key3, true, exceptionClass);
	}

	/**
	 *
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testGetDoubleProperty() throws Exception {
		String key1 = "Double";
		String key2 = "hello";
		String key3 = "String";
		Class<Exception> exceptionClass = Exception.class;
		PropertiesUtility.getDoubleProperty(properties, key1, true, exceptionClass);
		PropertiesUtility.getDoubleProperty(properties, key2, false, exceptionClass);
		PropertiesUtility.getDoubleProperty(properties, key2, true, exceptionClass);

		PropertiesUtility.getDoubleProperty(properties, key3, true, exceptionClass);
	}

	/**
	 *
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testGetDateProperty() throws Exception {
		String key1 = "Date";
		String key2 = "hello";
		String key3 = "String";
		Class<Exception> exceptionClass = Exception.class;
		PropertiesUtility.getDateProperty(properties, key1, "yyyyMMdd", true, exceptionClass);
		PropertiesUtility.getDateProperty(properties, key2, "yyyyMMdd", false, exceptionClass);
		PropertiesUtility.getDateProperty(properties, key2, "yyyyMMdd", true, exceptionClass);

		PropertiesUtility.getDateProperty(properties, key3, "yyyyMMdd", true, exceptionClass);
	}

	/**
	 *
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testGetClassProperty() throws Exception {
		String key1 = "Class";
		String key2 = "hello";
		String key3 = "Integer";
		Class<Exception> exceptionClass = Exception.class;
		PropertiesUtility.getClassProperty(properties, key1, true, exceptionClass);
		PropertiesUtility.getClassProperty(properties, key2, false, exceptionClass);
		PropertiesUtility.getClassProperty(properties, key2, true, exceptionClass);
		PropertiesUtility.getClassProperty(properties, key3, true, exceptionClass);
	}

	/**
	 *
	 */
	@Test
	public void testGetSubConfiguration(){
		Properties actual = PropertiesUtility.getSubConfiguration(properties,"config");

		String configName = "config";
		String prefix = configName + ".";
		Properties result = new Properties();

		for (Map.Entry<Object, Object> entry : properties.entrySet()){
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			if(key.startsWith(prefix)){
				String newKey = key.substring(prefix.length());
				result.put(newKey, value);
			}
		}

		assertEquals(result,actual);
	}
}
