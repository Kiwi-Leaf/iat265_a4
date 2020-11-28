package chararcters;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

import util.MouseInteraction;

public class Customer extends Characters implements MouseInteraction {
//	private boolean isSatisfied=false;

	private Image content;
	private int wantedItem;

	public Customer(float x, float y, int worldState) {
		// TODO Auto-generated constructor stub
		super(x, y);
		this.worldState = worldState;
		if (worldState == AFTERNOON) {
			activeImage = new ImageIcon("assets/image/felicia_1.gif").getImage();
			content = new ImageIcon("assets/image/felicia_2.gif").getImage();
			wantedItem = FLAX;
		} else if (worldState == EVENING) {
			activeImage = new ImageIcon("assets/image/avon_1.gif").getImage();
			content = new ImageIcon("assets/image/avon_2.gif").getImage();
			wantedItem = ROSE;
		}

	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		AffineTransform origin = g.getTransform();
		g.translate(pos.x, pos.y);
		g.scale(scale, scale);
		g.drawImage(activeImage, -activeImage.getWidth(null) / 2, -activeImage.getHeight(null) / 2, null);

		g.setTransform(origin);
	}

	@Override
	public boolean isClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		return (Math.abs(pos.x - e.getX()) < activeImage.getWidth(null) / 2
				&& Math.abs(pos.y - e.getY()) < activeImage.getHeight(null) / 2);
	}

	
	

	@Override
	public String dialogue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPlayerItem(int x) {
		// TODO Auto-generated method stub
		playerItem = x;
	}

	@Override
	public boolean getTaskCompleted() {
		// TODO Auto-generated method stub
		return false;
	}

	public void update(int worldState) {
		if(taskCompleted) {
			activeImage=content;
		}
	}

	public int getWantedItem() {
		return wantedItem;
	}
	public boolean playerIsCloseToItem(Player p) {
		if (Math.abs(this.pos.x-p.getPos().x)<this.activeImage.getWidth(null)/2+p.getWidth()/2 ){
			return true;
		}
		else {
			return false;
		}
		
	}

}
