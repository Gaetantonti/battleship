package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import server.Client;

public class WaitingRoomController implements Initializable {

	private Client client;
	@FXML
	private ListView<String> connectedClients;

	public WaitingRoomController(Client client) {
		this.client = client;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		connectedClients.getItems().clear();
		for (String s : client.getWaitingRoom().getPlayerList()) {
			connectedClients.getItems().add(s);
		}
	}
	
	public void refresh() throws IOException, ClassNotFoundException {
		client.sendMessage("NCKJoueur2");
		client.sendMessage("WTR");
		connectedClients.getItems().clear();
		for (String s : client.getWaitingRoom().getPlayerList()) {
			connectedClients.getItems().add(s);
		}
	}

}
