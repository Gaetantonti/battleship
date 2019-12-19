package server;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	
	static int port = 23;
	
	public static void main(String[] args){
		ServerSocket serverSocket;
		ArrayList<String> connectedClients = new ArrayList<String>();
		connectedClients.add("Louis");
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Server launched, listening on the port number " + port);
			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Connection established with client: " + clientSocket.getInetAddress().getHostAddress());
				 try 
		            {
		                ObjectOutputStream objectOutput = new ObjectOutputStream(clientSocket.getOutputStream());
		                objectOutput.writeObject(connectedClients);               
		            } 
		            catch (IOException e) 
		            {
		                e.printStackTrace();
		            } 
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
