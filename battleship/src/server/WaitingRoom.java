package server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WaitingRoom implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<String> playerList;

	public WaitingRoom() {
        this.playerList = new ArrayList<String>();
    }
	
	public List<String> getPlayerList(){
		return playerList;
	}
	
	public boolean nicknameExists(String nickname) {
		boolean b = false;
		if (playerList.contains(nickname)){
			b = true;
		}
		return b;
	}
	
	public void addPlayer(String nickname) {
		playerList.add(nickname);
	}
	
	public void removePlayer(String nickname) {
		playerList.remove(nickname);
	}
}
