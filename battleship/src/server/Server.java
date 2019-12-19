package server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	
	static int port = 23;
	static String waitingRoom = "Louis";
	ArrayList<String> connectedClients;
	
	public static void main(String[] args){
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Server launched, listening on the port number " + port);
			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Connection established with client: " + clientSocket.getInetAddress().getHostAddress());
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
