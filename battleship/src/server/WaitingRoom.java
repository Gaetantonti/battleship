package server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WaitingRoom implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<String> playerList;
	private ArrayList<ArrayList<String>> invitations = new ArrayList<ArrayList<String>>();

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
	
	public void addInvitation(String player1, String player2) {
		ArrayList<String> list = new ArrayList<String>();
		list.add(player1);
		list.add(player2);
		invitations.add(list);
	}
	
	public void removeInvitation(String player1) {
		for(int i = 0; i < invitations.size(); i++) {
			if(invitations.get(i).get(0).equals(player1)) {
				invitations.remove(i);
			}
		}
	}
	
	public ArrayList<ArrayList<String>> getInvitations(){
		return invitations;
	}
	
}
