package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import server.Client;

public class ConnectionController implements Initializable{
	
	@FXML private TextField nickname;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	@FXML
	public void pressConnectionButton() throws Exception {               
	    try {
	    	Client client = new Client(nickname.getText());
			client.connect();
	    	Stage stageToClose = (Stage) nickname.getScene().getWindow();
	    	stageToClose.close();
	    	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/WaitingRoomView.fxml"));
	    	WaitingRoomController wrc = new WaitingRoomController(client);
	    	loader.setController(wrc);
	        Stage stage = new Stage();
	        Scene scene = new Scene(loader.load());
	        scene.getStylesheets().add(getClass().getClassLoader().getResource("view/application.css").toExternalForm());
	        stage.setScene(scene);
	        stage.show();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}

}
