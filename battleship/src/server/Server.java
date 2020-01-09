package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	static int port = 23;

	public static void main(String[] args) throws IOException {
		WaitingRoom waitingRoom = new WaitingRoom();
		ServerSocket serverSocket = new ServerSocket(port);
		System.out.println("Server launched, listening on the port number " + port);
		while (true) {
			Socket clientSocket = serverSocket.accept();
			System.out.println("Connection established with client: " + clientSocket.getInetAddress().getHostAddress());
			OneClientServer ocs = new OneClientServer(clientSocket, waitingRoom);
			ocs.start();
		}
	}

}
