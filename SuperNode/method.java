

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class method {

	private HashMap<Integer,Info_File> registryHashList = new HashMap<Integer,Info_File>(); 
	private ArrayList<Info_File> registryList = new ArrayList<Info_File>(); 
	private Integer hashIndex = 0;
//	FileWriter writer = null;
	/*
	 *  Register file on server side
	 */
	public void registery(String peerID, String fileName){
		// TODO Auto-generated method stub
		registerThread register = new registerThread(peerID, fileName);
		Thread thread = new Thread(register);
		thread.start();
		thread = null;
	}

	/*
	 *  registerThread
	 *  Used to implement multiusers to register files at the same time
	 */
	class registerThread implements Runnable{
		private String peerID;
		private String fileName;
		
		public registerThread(String peerID, String fileName){
			this.peerID = peerID;
			this.fileName = fileName;
		}
		@Override
		public void run() {
			long startRegister = System.nanoTime();
			// TODO Auto-generated method stub
			if(registryList.size()==0){
				
				try{
					
					FileWriter writer = new FileWriter("./serverLog.txt",true);
					// Add register file to the registery list
					registryHashList.put(0,new Info_File(peerID,fileName));hashIndex++;
		            registryList.add(new Info_File(peerID,fileName));
					System.out.println("File:"+fileName+" from "+"Client:"+peerID+" is registried!");
					
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String time = df.format(new Date());
					writer.write(time + "\t\tFile "+fileName + " is registered on the index server!\r\n");
					writer.close();		

				}catch(Exception e)
				{
					e.printStackTrace();
				}			
				
			}
			else{
				try{
					if(fileNotExist(peerID,fileName)){	
						FileWriter writer = new FileWriter("./serverLog.txt",true);
						registryHashList.put(hashIndex,new Info_File(peerID,fileName));hashIndex++;
						registryList.add(new Info_File(peerID,fileName));
						System.out.println("File:"+fileName+" from "+"Client:"+peerID+" is registried!");
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String time = df.format(new Date());
						writer.write(time + "\t\tFile "+fileName + " is registered on the index server!\r\n");
						writer.close();
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}	
			long endRegister = System.nanoTime();
			long timeRegister = endRegister - startRegister;
			timeRegister = timeRegister/1000000;
			try {
				FileWriter writer = new FileWriter("./serverPerformance.txt",true);
				writer.flush();
				writer.write("register time" +"-"+ Info_Peer.local.ID +"-"+ timeRegister + "\n");
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private boolean fileNotExist(String peerID, String fileName) {
		// TODO Auto-generated method stub
		for(int i=0;i<registryList.size();i++){
			if(registryList.get(i).getName().equals(fileName)&&
					registryList.get(i).getID().equals(peerID)){
				return false;
			}
		}
		return true;
		
	}

	/*
	 *  Unregister file on server side
	 */
	public void unregistery(String peerID, String fileName){
		// TODO Auto-generated method stub
		unregisteryThread unregister = new unregisteryThread(peerID, fileName);
		Thread thread = new Thread(unregister);
		thread.start();
		thread = null;
		
	}

	/*
	 *  unregisterThread
	 *  Used to implement multiusers to unregister files at the same time
	 */
	class unregisteryThread implements Runnable{
		private String peerID;
		private String fileName;
		
		public unregisteryThread(String peerID, String fileName){
			this.peerID = peerID;
			this.fileName = fileName;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i=0;i<registryList.size();i++){
				try{
					
					if(registryList.get(i).getName().equals(fileName)&&
							registryList.get(i).getID().equals(peerID)){
						FileWriter writer = new FileWriter("./serverLog.txt",true);
						registryList.remove(i);
						System.out.println("File:"+fileName+" from "+"Client:"+peerID+" is removed!");
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String time = df.format(new Date());
						writer.write(time + "\t\tFile "+fileName + " is unregistered on the index server!\r\n");
						writer.close();
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	/*
	 *  Search file on server side
	 */
//	public ArrayList<String> search(String fileName) {
//		//search file ID: IP + port
//		ArrayList<String> peerList = new ArrayList<String>();
//
//		for(int i=0;i<registryList.size();i++){
//			if(registryList.get(i).getName().equals(fileName)){
//				peerList.add(registryList.get(i).getID());
//				
//			}
//		}
//		return peerList;
//	}
	
	public ArrayList<String> search(String fileName,String ID) {
		//search file ID: IP + port
		ArrayList<String> peerList = new ArrayList<String>();
		
		ExecutorService execPool = Executors.newCachedThreadPool();
		Callable<ArrayList<String>> call = new searchThread(fileName,ID);
		Future<ArrayList<String>> result = execPool.submit(call);
		
		try{
			if(result.get().size() != 0)
				peerList = (ArrayList<String>) result.get().clone();
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(ExecutionException e){
			e.printStackTrace();
		}finally{
			execPool.shutdown();
		}
		
		return peerList;
	}
	
	class searchThread implements Callable<ArrayList<String>>{
		private ArrayList<String> peerList = new ArrayList<String>();
		private String fileName;
		
		public searchThread(String fileName,String ID){
			this.fileName = fileName;
		}
		@Override
		public ArrayList<String> call() throws Exception {
			long startSearch = System.nanoTime();
			// TODO Auto-generated method stub
			for(int i=0;i<registryList.size();i++){
				if(registryList.get(i).getName().equals(fileName)){
					peerList.add(registryList.get(i).getID());
					
				}
			}
			long endSearch = System.nanoTime();
			long timeSearch = endSearch - startSearch;
			timeSearch = timeSearch/1000000;
			try {
				FileWriter writer = new FileWriter("./serverPerformance.txt",true);
				writer.flush();
				writer.write("register time" +"-"+ Info_Peer.local.ID +"-"+ timeSearch + "\n");
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return peerList;
		}
		
	}

}




