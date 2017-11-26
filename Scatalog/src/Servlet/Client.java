package Servlet;

import java.awt.print.Printable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Client
 */
@WebServlet("/Client")
public class Client extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private Socket socket; 
	
    public void init() throws ServletException {
    		try {
    			Socket s = new Socket("localhost", 6789);
    			socket = s; 
    			oos = new ObjectOutputStream(s.getOutputStream()); 
    			ois = new ObjectInputStream(s.getInputStream()); 
    		} catch(IOException ioe){
    			ioe.printStackTrace();
    		}
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 	 HttpSession session = request.getSession();
    		 if(session.getAttribute("socket") == null)
    			 session.setAttribute("socket", socket);
    		 readMessage(); 
    		 
    }
    
    private void readMessage() {
    		try {
    			ois.readObject(); 
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    }

}
