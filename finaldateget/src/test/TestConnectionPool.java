package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import finace.http.db.MyDBConnectionPool;

public class TestConnectionPool {

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
	public void test() throws ClassNotFoundException, SQLException {
		MyDBConnectionPool connPool = new MyDBConnectionPool(1000,2000);
		connPool.initPool();

		Connection conn = connPool.getConnection();
		System.out.println(connPool.getCurrentConn()+" "+connPool.getAviliConn());
		connPool.returnConnection(conn);
		System.out.println(connPool.getCurrentConn()+" "+connPool.getAviliConn());
		connPool.closeAllConnection();
		System.out.println(connPool.getCurrentConn()+" "+connPool.getAviliConn());
	}

}
