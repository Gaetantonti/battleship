package server;

import java.net.InetSocketAddress;
import java.net.Socket;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Client extends Application{

	private static final String hostname = "localhost";
	private static final int PORT = 23;
	Socket socket;
	
	public Client() {
	}

	public void connect() {
		try {
			socket = new Socket();
			socket.connect(new InetSocketAddress(hostname, PORT), 200);
			System.out.println("Successful connection");
		} catch (Exception e) {
			System.err.println(e);
			System.out.println("Failed connection");
		}
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/ConnectionView.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("view/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
