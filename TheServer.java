import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TheServer class intend to handle
 * multiple client calls at a single predefined
 * port # 19999.
 * This simple class demonstrate how multiple clients interact
 * with a single server.
 * @author dushantha
 *
 */

public class TheServer implements Runnable {
	
	 protected final static int port = 19999;
	  static Socket connection	;
	 private static ExecutorService executor = null;
	 
	 /**
	  * TheServer constructor
	  * @param poolsise int
	  * @param connection Socket
	  */
	 public TheServer(int poolsise,Socket 
			 connection){
		 executor =Executors.newFixedThreadPool(poolsise);
		 TheServer.connection = connection;
	 }
	 
	 public static void main(String[] args) {
		 
		 try {
			 
			 /* the server socket class has been used to set up a new server.
			  * The server socket can be created to listen to a particular port
			  * thus accept and handle incoming sockets.
			  */
			 ServerSocket socket1 = new ServerSocket(port);
			 System.out.println("---> TheServer Initialized");	
			 
			 while(true){
				 
				 
				 /*
				  * Accept the incoming connection to this socket,
				  * till then the program floor is suspended,i.e. the method
				  * is blocked till the connection is established.
				  * It's achieve via the accept() of ServerSocket class.
				  */
				 Socket connection = socket1.accept();
				 Runnable runnable = new TheServer(5,connection);
				 executor.execute(runnable);
			 }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 		 
	 }

	@Override
	public void run() {

		/*Instantiate the scanner instance that's bound to the input stream 
         * object which reads the data sent in by the client.  
         */
        Scanner sc = null;
		try {
			sc = new Scanner(connection.getInputStream());
			System.out.println("--- Reading from the client :  ");
	        
	        int number = sc.nextInt(); // the accepted number is doubled.
	        int temp = number*2;
	        
	        PrintStream p = new PrintStream(connection.getOutputStream());
	        
	        p.println(temp);
	      //need to wait 10 seconds to pretend that we're processing something
	        try {
	             Thread.sleep(10000);
	        }  catch (InterruptedException e){	        	
	        	System.out.println("MultipleClientServer.Class: run() :"+ e.getMessage());	        	
	        }
	        
	     // releasing resources.
	        sc.close();			
			
		} catch (IOException e) {			
			System.out.println("MultipleClientServer.Class: run() :"+ e.getMessage());	
		} 
		
	}
	 
	 
	
	
	
	
	

}
