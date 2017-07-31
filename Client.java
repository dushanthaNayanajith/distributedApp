//package ClientPackeage;



import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * this class is a simple socket programming
 * class which acts as a client program.
 * @author dushantha
 *
 */
public class Client {

	public static void main(String[] args) {
		
		int number;
		/* define the host */
		String host ="localhost"; //127.0.0.1
		
		/* define the port number */
		int port =19999;
		
		System.out.println("socket has initialized");
		
		try{
			
			/* Obtain an address object of the server */
			InetAddress address = InetAddress.getByName(host);
			
			/* establish the socket connection with the server using 
			 * the InetAddress object  localhost/127.0.0.1 and port number 
			 * which is selected to establish the communication channel.
			 */			
			Socket connection = new Socket(address, port);			
			Scanner scaner = new Scanner(System.in);
			
			
			/** Read in the user in put from the terminal */
			System.out.println("Enter any number");			 
			number = scaner.nextInt();                         
			
			/*
			 * get the output stream to the server i.e. bound to the  Print stream
			 * thus any thing the print stream writes is exposed to the server
			 * via the connections output stream 
			 */			
			PrintStream printstrm = new PrintStream(connection.getOutputStream());			
			printstrm.println(number);
			
			/*
			 * Instantiate the Scanner instance for reading
			 * the incoming socket streams (from the server side).
			 * Whenever the server sends a processed response the client catches
			 * it and do what is necessary according to the business logic.
			 */			
			Scanner scanner1 = new Scanner(connection.getInputStream());
			
			String tempNumber = scanner1.next()	;//Read the socket's next content.
			
			connection.close();  // Close the connection once the input is received from the server
			
			System.out.println(tempNumber + "-- time :"+ new java.util.Date().toString());
			
			/*
			 * closing the opened scanner resources -- should go in the finally block 
			 * for better programming practices.
			 */
			scaner.close();
			scanner1.close();
			
			
		}catch(IOException ioExp){
			
			System.out.println("IOException : "+ ioExp);
		} catch (Exception exp){
			System.out.println("Exception :" +exp);
		}
		
		
	}

}
