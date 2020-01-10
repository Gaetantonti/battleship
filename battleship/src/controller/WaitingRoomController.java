package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import server.Client;
import server.WaitingRoom;

public class WaitingRoomController implements Initializable {

	private Client client;
	@FXML
	private ListView<String> connectedClients;
	@FXML
	private Text numberOfClients;
	@FXML
	private Text myNickname;

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
		myNickname.setText("You are connected as '" + client.getNickname() + "'");
		refreshWaitingRoom();
		// checkInvitations();
	}

	public void initWaitingRoom() throws IOException, ClassNotFoundException {
		client.sendMessage("WTR");
		numberOfClients
				.setText("Players online : " + String.valueOf(client.getWaitingRoom().getPlayerList().size() - 1));
		for (String s : client.getWaitingRoom().getPlayerList()) {
			if (!s.equals(client.getNickname())) {
				connectedClients.getItems().add(s);
			}
		}
	}

	private void refreshWaitingRoom() {
		EventHandler<ActionEvent> e = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					List<String> listBefore = client.getWaitingRoom().getPlayerList();
					client.sendMessage("WTR");
					if (!client.getWaitingRoom().getPlayerList().equals(listBefore)) {
						numberOfClients.setText("Players online : "
								+ String.valueOf(client.getWaitingRoom().getPlayerList().size() - 1));
						connectedClients.getItems().clear();
						for (String s : client.getWaitingRoom().getPlayerList()) {
							if (!s.equals(client.getNickname())) {
								connectedClients.getItems().add(s);
							}
						}
					}

				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}

				if (!client.getWaitingRoom().getInvitations().isEmpty()) {
					String nickname = null;
					for (int i = 0; i < client.getWaitingRoom().getInvitations().size(); i++) {
						if (client.getWaitingRoom().getInvitations().get(i).get(1).equals(client.getNickname())) {
							nickname = client.getWaitingRoom().getInvitations().get(i).get(0);
							System.out.println(nickname);
						}
					}
					if(!nickname.equals(null)) {
						FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/InvitationView.fxml"));
						Stage stage = new Stage();
				        try {
							stage.setScene(new Scene(loader.load()));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
				        stage.show();
					}
				}

			}
		};
		KeyFrame k = new KeyFrame(Duration.seconds(1), e);
		Timeline refreshWaitingRoom = new Timeline(k);
		refreshWaitingRoom.setCycleCount(Timeline.INDEFINITE);
		refreshWaitingRoom.play();
	}

	@FXML
	private void sendInvitation() throws ClassNotFoundException, IOException {
		String player2 = (String) connectedClients.getSelectionModel().getSelectedItem();
		client.sendMessage("INV" + client.getNickname() + "*" + player2);
	}

}
