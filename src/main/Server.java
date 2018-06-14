package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

	private ServerSocket serversocket;
	private Socket client;
	
	private BufferedReader in;
	private PrintWriter out;

	
	public Server(int port) {
		init(port);
		new Thread(this).start();
	}
	
	
	//initializes a new server listening for a connection
	private void init(int port) {
		try {
			System.out.println("Server waiting for connections...");
			serversocket = new ServerSocket(port);
			client = serversocket.accept();
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream(), true);
			System.out.println("Client " + client.getInetAddress().getHostAddress() + " joined the server");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//sends a message to client
	public void sendMessage(String msg) {
		out.println(msg);
	}
	
	
	//reading constantly client input stream
	@Override
	public void run() {
		try {
			String msgIn;
			while((msgIn = in.readLine()) != null) {
				System.out.println("Client: " + msgIn);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
