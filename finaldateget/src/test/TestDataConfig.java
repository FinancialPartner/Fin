package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import finace.http.getdata.DataConfig;

public class TestDataConfig {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetZcfzGetUrlPref() {
		assertEquals("http://quotes.money.163.com/service/zcfzb_", DataConfig.getZcfzGetUrlPref());
	}


	@Test
	public void testGetZcfzGetUrlAfter() {
		assertEquals(".html", DataConfig.getZcfzGetUrlAfter());
	}


	@Test
	public void testGetProfitGetUrlPref() {
		assertEquals("http://quotes.money.163.com/service/lrb_", DataConfig.getProfitGetUrlPref());
	}


	@Test
	public void testGetProfitGetUrlAfter() {
		assertEquals(".html", DataConfig.getProfitGetUrlAfter());
	}


	@Test
	public void testGetCashGetUrlPref() {
		assertEquals("http://quotes.money.163.com/service/xjllb_", DataConfig.getCashGetUrlPref());
	}


	@Test
	public void testGetCashGetUrlAfter() {
		assertEquals(".html", DataConfig.getCashGetUrlAfter());
	}


	@Test
	public void testGetCodeQueue() {
		assertEquals(2477, DataConfig.getCodeQueue().size());
	}
	
	@Test
	public void testGetDburl(){
		assertEquals("jdbc:mysql://192.168.0.1:3306/test",DataConfig.getDburl());
	}
	
	@Test
	public void testGetDbdriver(){
		assertEquals("com.mysql.jdbc.Driver",DataConfig.getDbdriver());
	}
	
	@Test
	public void testGetDbuser(){
		assertEquals("root",DataConfig.getDbuser());
	}
	
	@Test
	public void testGetDbpassword(){
		assertEquals("123456",DataConfig.getDbpassword());
	}


}
