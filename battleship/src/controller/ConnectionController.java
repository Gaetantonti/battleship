package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import server.Client;

public class ConnectionController implements Initializable{
	
	@FXML private TextField nickname;
	@FXML private Text textChooseNickname;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	@FXML
	public void pressConnectionButton() throws Exception {               
	    try {
	    	Client client = new Client(nickname.getText());
			boolean canConnect = client.connect();
			if(canConnect) {
				System.out.println("Successful connection");
				Stage stageToClose = (Stage) nickname.getScene().getWindow();
		    	stageToClose.close();
		    	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/WaitingRoomView.fxml"));
		    	WaitingRoomController wrc = new WaitingRoomController(client);
		    	loader.setController(wrc);
		        Stage stage = new Stage();
		        stage.setScene(new Scene(loader.load()));
		        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		            public void handle(WindowEvent we) {
		                try {
							client.sendMessage("DEL" + client.getNickname());
						} catch (ClassNotFoundException | IOException e) {
							e.printStackTrace();
						}
		            }
		        });   
		        stage.show();
			}else{
				textChooseNickname.setText("'" + nickname.getText() + "'" + " IS ALREADY USED");
				nickname.clear();
			}
	    	
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}

}
