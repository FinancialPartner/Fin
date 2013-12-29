package finace.http.getdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;

public class DataConfig {
	
	private static String zcfzGetUrlPref;
	private static String zcfzGetUrlAfter;
	private static String profitGetUrlPref;
	private static String profitGetUrlAfter;
	private static String cashGetUrlPref;
	private static String cashGetUrlAfter;
	
	private static String dburl;
	private static String dbdriver;
	private static String dbuser;
	private static String dbpassword;
	
	private static LinkedBlockingQueue<String> codeQueue;
	
	
	static {
		
		
		try{
			
			Properties prop = new Properties();
	
			InputStream in =new FileInputStream("config/config.properties");
			prop.load(in);
			
			setZcfzGetUrlPref(prop.getProperty("zcfzb.url.pref"));
			setZcfzGetUrlAfter(prop.getProperty("zcfzb.url.after"));
			
			setProfitGetUrlPref(prop.getProperty("prof.url.pref"));
			setProfitGetUrlAfter(prop.getProperty("prof.url.after"));
			
			setCashGetUrlPref(prop.getProperty("cash.url.pref"));
			setCashGetUrlAfter(prop.getProperty("cash.url.after"));
			
			setDburl(prop.getProperty("db.url"));
			setDbdriver(prop.getProperty("db.driver"));
			setDbuser(prop.getProperty("db.username"));
			setDbpassword(prop.getProperty("db.password"));
			
			in.close();
			
			codeQueue = new LinkedBlockingQueue<String>();
			BufferedReader bf = new BufferedReader(new FileReader("config/code.txt"));

			String codetmp = bf.readLine();
			
			while(codetmp!=null){
				
				codeQueue.add(codetmp);
				
				codetmp = bf.readLine();
				
			}

			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
	}


	public static String getZcfzGetUrlPref() {
		return zcfzGetUrlPref;
	}


	public static void setZcfzGetUrlPref(String zcfzGetUrlPref) {
		DataConfig.zcfzGetUrlPref = zcfzGetUrlPref;
	}


	public static String getZcfzGetUrlAfter() {
		return zcfzGetUrlAfter;
	}


	public static void setZcfzGetUrlAfter(String zcfzGetUrlAfter) {
		DataConfig.zcfzGetUrlAfter = zcfzGetUrlAfter;
	}


	public static String getProfitGetUrlPref() {
		return profitGetUrlPref;
	}


	public static void setProfitGetUrlPref(String profitGetUrlPref) {
		DataConfig.profitGetUrlPref = profitGetUrlPref;
	}


	public static String getProfitGetUrlAfter() {
		return profitGetUrlAfter;
	}


	public static void setProfitGetUrlAfter(String profitGetUrlAfter) {
		DataConfig.profitGetUrlAfter = profitGetUrlAfter;
	}


	public static String getCashGetUrlPref() {
		return cashGetUrlPref;
	}


	public static void setCashGetUrlPref(String cashGetUrlPref) {
		DataConfig.cashGetUrlPref = cashGetUrlPref;
	}


	public static String getCashGetUrlAfter() {
		return cashGetUrlAfter;
	}


	public static void setCashGetUrlAfter(String cashGetUrlAfter) {
		DataConfig.cashGetUrlAfter = cashGetUrlAfter;
	}


	public static LinkedBlockingQueue<String> getCodeQueue() {
		return codeQueue;
	}


	public static void setCodeQueue(LinkedBlockingQueue<String> codeQueue) {
		DataConfig.codeQueue = codeQueue;
	}


	public static String getDburl() {
		return dburl;
	}


	public static void setDburl(String dburl) {
		DataConfig.dburl = dburl;
	}


	public static String getDbdriver() {
		return dbdriver;
	}


	public static void setDbdriver(String dbdriver) {
		DataConfig.dbdriver = dbdriver;
	}


	public static String getDbuser() {
		return dbuser;
	}


	public static void setDbuser(String dbuser) {
		DataConfig.dbuser = dbuser;
	}


	public static String getDbpassword() {
		return dbpassword;
	}


	public static void setDbpassword(String dbpassword) {
		DataConfig.dbpassword = dbpassword;
	}


	
	
	

}
