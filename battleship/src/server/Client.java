package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

	private static final String hostname = "127.0.0.1";
	private static final int PORT = 23;
	private Socket socket;
	private WaitingRoom waitingRoom;
	private String nickname;
	ObjectOutputStream objectOutput;
	ObjectInputStream objectInput;

	public Client(String nickname) {
		this.nickname = nickname;
	}

	public boolean connect() throws IOException, ClassNotFoundException {
		socket = new Socket();
		socket.connect(new InetSocketAddress(hostname, PORT), 200);
		objectOutput = new ObjectOutputStream(socket.getOutputStream());
		objectInput = new ObjectInputStream(socket.getInputStream());
		sendMessage("NCK" + nickname);
		Object object = objectInput.readObject();
		boolean response = (boolean) object;
		return response;
	}

	public WaitingRoom getWaitingRoom() {
		return waitingRoom;
	}
	
	public void setWaitingRoom(WaitingRoom waitingRoom) {
		this.waitingRoom = waitingRoom;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void sendMessage(String message) throws IOException, ClassNotFoundException {
		objectOutput.writeObject(message);
		if(message.equals("WTR")) {
			Object object = objectInput.readObject();
			WaitingRoom wr = (WaitingRoom) object;
			this.setWaitingRoom(wr);
		}
	}

}
