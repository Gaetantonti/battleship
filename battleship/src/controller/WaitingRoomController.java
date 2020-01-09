package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.util.Duration;
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
		try {
			initWaitingRoom();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		refreshWaitingRoom();
	}
	
	public void initWaitingRoom() throws IOException, ClassNotFoundException {
		client.sendMessage("WTR");
		connectedClients.getItems().clear();
		for (String s : client.getWaitingRoom().getPlayerList()) {
			connectedClients.getItems().add(s);
		}
	}
	
	private void refreshWaitingRoom() {
		EventHandler<ActionEvent> e = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					initWaitingRoom();
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
			}
		};
		new KeyFrame(Duration.seconds(1), e);
		KeyFrame k = new KeyFrame(Duration.seconds(1), e);
		Timeline refreshWaitingRoom = new Timeline(k);
		refreshWaitingRoom.setCycleCount(Timeline.INDEFINITE);
		refreshWaitingRoom.play();
	}

}
