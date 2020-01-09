package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class OneClientServer extends Thread{

	private ObjectOutputStream objectOutput; 
	private ObjectInputStream objectInput;
	private Socket clientSocket; 
	private WaitingRoom waitingRoom;
      
    public OneClientServer(Socket clientSocket, WaitingRoom waitingRoom){ 
        this.clientSocket = clientSocket; 
        this.waitingRoom = waitingRoom;
    } 
    
    @Override
    public void run(){
    	try {
			objectOutput = new ObjectOutputStream((clientSocket).getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			objectInput = new ObjectInputStream(clientSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean endSession = false;
    	while (!endSession) {
			String request = null;
			try {
				request = (String) objectInput.readObject();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			switch (request.substring(0, 3)) {
			case "NCK":
				waitingRoom.addPlayer((String) request.substring(3));
				break;
			case "WTR":
				try {
					objectOutput.writeObject(waitingRoom);
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					objectOutput.reset();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "DEL":
				waitingRoom.removePlayer((String) request.substring(3));
				endSession = true;
				break;
			}
		}
    }
}