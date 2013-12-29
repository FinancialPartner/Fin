package finace.http.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import finace.http.getdata.DataConfig;

public class MyDBConnectionPool {
	
	private Map<java.sql.Connection, Boolean> connectionPool = null;
	private int minConn;
	private int maxConn;
	//当前可用线程
	private int aviliConn=0;
	//当前总线程数
	private int currentConn=0;

	public MyDBConnectionPool(int _minConn,int _maxConn){
		minConn = _minConn;
		maxConn = _maxConn;
	}
	
	public void initPool(){
		String dburl = DataConfig.getDburl();
		String dbuser = DataConfig.getDbuser();
		String dbpassword = DataConfig.getDbpassword();
		connectionPool = new HashMap<Connection,Boolean>();
		try {
			Class.forName(DataConfig.getDbdriver());
			
			System.out.println(dburl + dbuser + dbpassword);
			for(int i=0;i<minConn;i++){
				
				Connection conn = DriverManager.getConnection(dburl,dbuser,dbpassword);
				connectionPool.put(conn, true);
				addCurrentConn();
				addAviliConn();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		
		if(aviliConn==0&&currentConn<maxConn){
			
			Class.forName(DataConfig.getDbdriver());
			java.sql.Connection con = DriverManager.getConnection(DataConfig.getDburl());
			connectionPool.put(con, false);
			addCurrentConn();
			return con;
		
		}else if(aviliConn>0){
			
			for(Entry<Connection ,Boolean>entry:connectionPool.entrySet()){
				synchronized(entry){
					if(entry.getValue()){
						entry.setValue(false);
						delAviliConn();
						return entry.getKey();
					}
				}
			}
		}

		
		return null;
		
	}
	
	public void returnConnection(Connection conn){
		for(Entry<Connection ,Boolean>entry:connectionPool.entrySet()){
			synchronized(entry){
				if(entry.getKey().equals(conn)){
					addAviliConn();
					entry.setValue(true);
				}
			}
		}
	}
	
	public boolean isConnectionIdle(){
		
		return currentConn==aviliConn?true:false;
		
	}
	
	
	public boolean closeAllConnection(){
		
		if(isConnectionIdle()){
			for(Entry<Connection ,Boolean> entry:connectionPool.entrySet()){
				synchronized(entry){
					try {
						if(entry.getKey()!=null){
							entry.getKey().close();
							delCurrentConn();
							delAviliConn();
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			return true;
		}
		
		return false;
		
	}
	
	
	
	public synchronized void addCurrentConn(){
		++currentConn;
	}
	
	public synchronized void delCurrentConn(){
		--currentConn;
	}
	
	public synchronized void addAviliConn(){
		++aviliConn;
	}
	
	public synchronized void delAviliConn(){
		--aviliConn;
	}

	public Map<java.sql.Connection, Boolean> getConnectionPool() {
		return connectionPool;
	}

	public void setConnectionPool(Map<java.sql.Connection, Boolean> connectionPool) {
		this.connectionPool = connectionPool;
	}

	public int getMinConn() {
		return minConn;
	}

	public void setMinConn(int minConn) {
		this.minConn = minConn;
	}

	public int getMaxConn() {
		return maxConn;
	}

	public void setMaxConn(int maxConn) {
		this.maxConn = maxConn;
	}

	public int getAviliConn() {
		return aviliConn;
	}

	public void setAviliConn(int aviliConn) {
		this.aviliConn = aviliConn;
	}

	public int getCurrentConn() {
		return currentConn;
	}

	public void setCurrentConn(int currentConn) {
		this.currentConn = currentConn;
	}
	
	
	

}
