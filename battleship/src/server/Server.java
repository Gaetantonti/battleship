package server;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server {
	
	static int port = 23;
	static String waitingRoom = "Louis";
	ArrayList<String> connectedClients;
	
	public static void main(String[] args){
		ServerSocket socket;
		try {
			socket = new ServerSocket(port);
			System.out.println("Server launched, listening on the port number " + port);
			while (true) {
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
