package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {

	private BufferedReader in;
	private PrintWriter out;
	
	public Client(String hostname, int hostport) {
		init(hostname, hostport);
		new Thread(this).start();
	}
	
	
	//initializes a new client-server connection
	private void init(String hostname, int hostport) {
		try {
			Socket server = new Socket(hostname, hostport);
            out = new PrintWriter(server.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(server.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//sends a message to server host
	public void sendMessage(String msg)  {
		out.println(msg);
	}
	
	
	//reading constantly server input stream
	@Override
	public void run() {
	    try {
            String msgIn;
            while((msgIn = in.readLine()) != null) {
                System.out.println("Server: " + msgIn);
            }
        } catch(IOException e) {
	        e.printStackTrace();
        }

	}
}
