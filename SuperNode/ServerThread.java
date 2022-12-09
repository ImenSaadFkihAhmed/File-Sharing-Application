

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerThread extends Thread{
	private BufferedReader br;
	private PrintWriter pw;
	private Socket connectToClient;
	private method serverFunctions;
	
	public ServerThread(Socket soc,method serverFunctions)throws IOException{
		super();
		connectToClient = soc;
		this.serverFunctions = serverFunctions;
		br = getReader(connectToClient);
		pw = getWriter(connectToClient);
		start();
	}
	
	public PrintWriter getWriter(Socket socket)throws IOException{
		OutputStream socketOut = socket.getOutputStream();
		return new PrintWriter(socketOut, true);
		
	}
	
	public BufferedReader getReader(Socket socket)throws IOException{
		InputStream socketIn = socket.getInputStream();
		return new BufferedReader(new InputStreamReader(socketIn));
		
	}
	
	/*
	 * Deal with messages sent from client
	 * @see java.lang.Thread#run()
	 */
	public void run(){
		try{
			
			String msg = null;
			
			while((msg = br.readLine())!=null){
				StringTokenizer st = new StringTokenizer(msg);
				System.out.println(msg);
				String command = st.nextToken();
				String ID = st.nextToken();
				String fileName = st.nextToken();
				
				if("register".equals(command)){
					serverFunctions.registery(ID, fileName);
					
				}else if("unregister".endsWith(command)){
					serverFunctions.unregistery(ID, fileName);
					
				}else if("search".equals(command)){
					ArrayList<String> peerList = new ArrayList<String>();
					
					//find the peer list that have that file
					peerList = serverFunctions.search(fileName,ID);
							
					if(peerList.size() != 0){
						for(int i =0; i<peerList.size();i++){
							pw.println(peerList.get(i));
						}
					}
					
					pw.println("bye");
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
