package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
		for (String s : client.getConnectedClients()) {
			connectedClients.getItems().add(s);
		}

	}

}
