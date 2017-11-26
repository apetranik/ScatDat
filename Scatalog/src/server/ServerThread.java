package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {
	private ObjectInputStream ois; 
	private ObjectOutputStream oos;
	Server server; 
	public ServerThread(Socket s, Server server) {
		try {
			this.server = server; 
			ois = new ObjectInputStream(s.getInputStream());
			oos = new ObjectOutputStream(s.getOutputStream()); 
			this.start(); 
		} catch(IOException ioe) {
			System.out.println("ioe in ServerThread constructor: " + ioe.getMessage());
		}
	}
	
	//public void sendMessage(String message) {
		public void sendMessage() {
			try {
				oos.writeObject("");
				oos.flush(); 
			} catch(IOException ioe) {
				System.out.println("ioe " +ioe.getMessage());
			}
			//pWriter.println(message);
			//pWriter.flush(); 
		}
		public void run() { 
			try {
				while(true) {
					//String line = bReader.readLine();
					ois.readObject(); 
					//cr.broadcast(line, this);
					server.broadcast(this);
					
				}
			}catch(IOException ioe) {
				System.out.println("ioe in ServerThread.run(): " + ioe.getMessage()); 
			}catch(ClassNotFoundException cnfe) {
				System.out.println("cnfe:" + cnfe.getMessage());
			}
		}
}

