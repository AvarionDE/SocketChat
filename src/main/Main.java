package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String args[]) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		
		while(!input.equalsIgnoreCase("s")  && !input.equalsIgnoreCase("c")) {
			System.out.println("Choose the following:");
			System.out.println("Type 's' to create a server");
			System.out.println("Type 'c' to connect to a server");
			input = reader.readLine();
		}
		
	
		
		switch(input) {
		case "s": createServer(reader); break;
		case "c": joinServer(reader); break;
		}
		
		reader.close();
	}
	
	
	
	private static void createServer(BufferedReader reader) throws IOException {
		int port;
	
		System.out.println("Type in a port");
		port = Integer.parseInt(reader.readLine());
		
		Server server = new Server(port);
		String message = "";
		
		while((message = reader.readLine()) != null) {
		    server.sendMessage(message);
		}
	}



	public static void joinServer(BufferedReader reader) throws IOException {
		int port;
		String hostname;
		
		System.out.println("Type in the host-ip:");
		hostname = reader.readLine();
		System.out.println("Type in a port:");
		port = Integer.parseInt(reader.readLine());
		
		Client client = new Client(hostname, port);
		String message = "";
		
		while((message = reader.readLine()) != null) {
		    client.sendMessage(message);
        }
	}
}

