package finace.http.getdata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.DefaultHttpClient;

public class GetDataRunnable implements Runnable {
	
	private String date;
	private boolean isGetAllDate = false;

	public GetDataRunnable(String _date){
		
		date = _date;
	}
	
	public GetDataRunnable(){
		
		isGetAllDate = true;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(DataConfig.getCodeQueue().size()>0){
			
			String codeString = DataConfig.getCodeQueue().remove();
			HttpClient httpClient = new DefaultHttpClient();
			

			try {
				HttpGet httpget = new HttpGet(DataConfig.getZcfzGetUrlPref()+codeString+DataConfig.getZcfzGetUrlAfter());
				HttpResponse response = httpClient.execute(httpget);
				System.out.println(codeString);
				HttpEntity entity = response.getEntity();
				String content="";
	            if (response.getStatusLine().getStatusCode()==200&&entity != null) {
					InputStream instream = entity.getContent();
					BufferedReader bfr = new BufferedReader(new InputStreamReader(instream,"GBK"));
					
					if(isGetAllDate){
						
						String line;
						
						while((line=bfr.readLine())!=null){
							
							System.out.println(line);    
							content+=line;
							
							
							
							
						}
						
					}else{
						
						String line=bfr.readLine();
						int index=1;
						String[] dateString;
						String[] lineArray;
						if(line!=null){
							
							if(line.contains(date)){
								
								lineArray = line.split(",");
								dateString = new String[120];
								for(int i=0;i<lineArray.length;i++){
									if(lineArray[i].trim().equals(date)){
										index = i;
										break;
									}
										
								}
								
								dateString[0]=date;
								
								int i=1;
								while((line=bfr.readLine())!=null&&!"".equals(line.trim())){
									  
									lineArray = line.split(",");
									
									dateString[i] = lineArray[index];
									
									System.out.println(i+" "+dateString[i]);
									
									i++;
								}
								
								
							}
							
							
							
						}
						
						
						
					}
					
	                                    
	                  
	                                    
				}else{
					System.out.println("content is null");
				}
				
				
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				DataConfig.getCodeQueue().add(codeString);
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				DataConfig.getCodeQueue().add(codeString);
				e.printStackTrace();
			}
		}
		

	}

}
