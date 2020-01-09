package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	static int port = 23;

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		WaitingRoom waitingRoom = new WaitingRoom();
		ServerSocket serverSocket = new ServerSocket(port);
		System.out.println("Server launched, listening on the port number " + port);
		boolean endServer = false;
		while (!endServer) {
			Socket clientSocket = serverSocket.accept();
			System.out.println("Connection established with client: " + clientSocket.getInetAddress().getHostAddress());
			boolean endSession = false;
			ObjectOutputStream objectOutput = new ObjectOutputStream((clientSocket).getOutputStream());
			ObjectInputStream objectInput = new ObjectInputStream(clientSocket.getInputStream());
			while (!endServer && !endSession) {
				String request = (String) objectInput.readObject();
				switch (request.substring(0, 3)) {
				case "NCK":
					waitingRoom.addPlayer((String) request.substring(3));
					break;
				case "WTR":
					objectOutput.writeObject(waitingRoom);
					objectOutput.reset();
					break;
				}
			}

		}
	}

}
