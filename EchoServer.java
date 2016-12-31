/*
 *	Mark Hirons
 *	CS352 Internet Technology Asst1
 */

import java.net.Socket;
import java.net.ServerSocket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EchoServer {

	/*
	 *	Creates an echoserver that takes a string from a client
	 *  and sends back a reversed version of the string.
	 */

    public static void main(String[] args) throws Exception {

    	if (args.length != 1){
    		System.out.println("Correct usage: \n" + "EchoServer <portnumber>");
    		System.exit(1);
    	}

    	int portNumber = Integer.parseInt(args[0]);

        // create socket
        ServerSocket serverSocket = null;
        try{
        	serverSocket = new ServerSocket(portNumber);
        } catch (Exception e){
        	e.printStackTrace();
        }
       	
        // repeatedly wait for connections, and process
        while (true) {

        	try{
        		// a "blocking" call which waits until a connection is requested
	        	Socket clientSocket = serverSocket.accept();
	            // open up IO streams
	        	PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
	        	BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	            // waits for data and reads it in until connection dies
	           	String inputLine, outputLine;
	           	while ((inputLine = in.readLine()) != null){
	           		outputLine = reverseString(inputLine);
	           		out.println(outputLine);
	           		if (inputLine.equals("#") || (inputLine.equals("$"))){
	           			break;
	           		}
	           	}
	            // close IO streams, then socket
	            in.close();
	           	out.close();
	            clientSocket.close();
	            serverSocket.close();
	            break;
        	} catch (Exception e){
        		e.printStackTrace();
        	}
            
        }
    }

    /*
     * Reverses a string.
     * @param string_to_reverse : string to be reversed
     * @return reversedString : the string that has been reversed
     */
    private static String reverseString(String string_to_reverse){
    	String reversedString = "";
    	int length = string_to_reverse.length();
    	for (int i = 0; i < length; i++){
    		reversedString = reversedString + string_to_reverse.charAt(length - (i + 1));
    	}
    	return reversedString;
    }
}