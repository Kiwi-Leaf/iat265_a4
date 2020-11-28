package util;

import java.awt.event.MouseEvent;

import chararcters.Player;

public interface MouseInteraction {

	public boolean isClicked(MouseEvent e);
	public void setPlayerItem(int x);
	public boolean getTaskCompleted();
	public boolean playerIsCloseToItem(Player p);
}
