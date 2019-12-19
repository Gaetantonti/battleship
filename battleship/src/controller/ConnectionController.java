package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConnectionController implements Initializable{
	
	@FXML private Button connectionButton;
	@FXML private TextField nickname;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	@FXML
	public void pressConnectionButton() throws Exception {               
	    try {
	    	Stage stageToClose = (Stage) connectionButton.getScene().getWindow();
	    	stageToClose.close();
	    	Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/WaitingRoomView.fxml"));
	        Stage stage = new Stage();
	        stage.setScene(new Scene(root));  
	        stage.show();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}

}
