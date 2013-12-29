package finace.http.getdata;

import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GetDataMethod {
	
	private ExecutorService executorServicePool;
	
	
	public GetDataMethod(int threadNum){
		executorServicePool = Executors.newFixedThreadPool(threadNum);
	}
	
	public GetDataMethod(){
		executorServicePool = Executors.newFixedThreadPool(4);
	}
	
	
	public void startGetData(){
		
		for (int i=0;i<6;i++){
			Thread getThread = new Thread(new GetDataRunnable("2013-09-30"),"thread get data "+i);
			System.out.println("thread get data "+i);
			executorServicePool.execute(getThread);
		}
		
		executorServicePool.shutdown();
		
	}

	public ExecutorService getExecutorService() {
		return executorServicePool;
	}

	public void setExecutorService(ExecutorService executorService) {
		this.executorServicePool = executorService;
	}
	
	 
	
	public static void main(String[] arg) throws ClassNotFoundException, SQLException{
		GetDataMethod get = new GetDataMethod(6);
		get.startGetData();
		
		
	}
	
	

}
