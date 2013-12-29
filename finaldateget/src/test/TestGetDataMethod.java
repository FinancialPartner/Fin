/**
 * 
 */
package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import finace.http.getdata.GetDataMethod;

/**
 * @author hadoop
 *
 */
public class TestGetDataMethod {

	private GetDataMethod getdMethod;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		getdMethod = new GetDataMethod(6);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link finace.http.getdata.GetDataMethod#startGetData()}.
	 */
	@Test
	public void testStartGetData() {
		getdMethod.startGetData();
	}

}
