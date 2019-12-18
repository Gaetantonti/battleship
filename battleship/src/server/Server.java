package server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
	
	static int port = 23;
	
	public static void main(String[] args) throws IOException {
		ServerSocket socket = new ServerSocket(port);
		WaitingRoom waitingRoom = new WaitingRoom();
		System.out.println("Server launched, listening on the port number " + port);
		while (true) {
			
		}
	}

}
