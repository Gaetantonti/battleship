package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;



public class Client{

	private static final String hostname = "localhost";
	private static final int PORT = 23;
	private Socket socket;
	private ArrayList<String> connectedClients;
	private String nickname;
	
	public Client(String nickname) {
		this.nickname = nickname;
	}

	@SuppressWarnings("unchecked")
	public void connect() {
		try {
			socket = new Socket();
			socket.connect(new InetSocketAddress(hostname, PORT), 200);
			System.out.println("Successful connection");
            try {
                ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
                try {
                    Object object = objectInput.readObject();
                    connectedClients =  (ArrayList<String>) object;
                    System.out.println(connectedClients);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }           
		} catch (Exception e) {
			System.err.println(e);
			System.out.println("Failed connection");
		}
	}
	
	public List<String> getConnectedClients(){
		return connectedClients;
	}
	


}
