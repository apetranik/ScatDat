package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
	private Vector<ServerThread> serverThreads; 
	public Server(int port) {
		try{
			System.out.println("Trying to bind to port " + port);
			ServerSocket ss = new ServerSocket(port); 
			System.out.println("Bound to port" + port);
			serverThreads = new Vector<ServerThread>(); 
			while(true) {
				Socket s = ss.accept();
				System.out.println("Accepted connection from " + s.getInetAddress());
				ServerThread st = new ServerThread(s, this); 
				serverThreads.add(st); 
			}
		}catch(IOException ioe) {
			System.out.println("ioe in Server constructor: " + ioe.getMessage());
		}
	}
	
	public static void main(String args[]) {
		new Server(6789); 
	}
	
	//public void broadcast(String message, ServerThread st) {
		public void broadcast(ServerThread st) {
				//System.out.println(message);
				for(ServerThread thread : serverThreads) {
					if(thread != st) {
						//thread.sendMessage(message);
						thread.sendMessage(); 
					}
				}
		}
}
