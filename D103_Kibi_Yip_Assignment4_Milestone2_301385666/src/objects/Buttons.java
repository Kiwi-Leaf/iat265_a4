package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import chararcters.Player;
import util.ImageLoader;
import util.MouseInteraction;

public class Buttons extends AllObjects implements MouseInteraction {
	/*
	 Object List Title==> 1.door 
	 Morning,Afternoon, Evening==> 1.sign 2.Rose pot3. flax pot 4.Watering Can 
	 End==> 1. EndCard
	 */
//	private BufferedImage button;
	private BufferedImage button=null;
	private BufferedImage button2 = null;
	private int itemNumber;
	private boolean isMouseHover=false;
//	private Water water;
	
	
	
	

	public Buttons(float x, float y, int state, int itemNumber) {
		// TODO Auto-generated constructor stub
		super(x, y);
		worldState = state;
		this.itemNumber = itemNumber;
//		water=null;
		loadButtons();
		if (itemNumber==WATERING_CAN) {
			taskCompleted=true;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		AffineTransform origin = g.getTransform();
		g.translate(pos.x, pos.y);
		g.scale(scale,scale);
		
//		if(button2!=null&&isMouseHover) {
//		
//		}
//		else {
//		
//		}
		g.drawImage(activeImage, -activeImage.getWidth(null) / 2, -activeImage.getHeight(null) / 2, null);
		g.setTransform(origin);
//		
//		if(worldState==MORNING&&water!=null) {
//			water.draw(g);
//			
//		}

	}
	public void update(int worldState) {
		stateScript(worldState);
	
		if(button2!=null&&taskCompleted) {
			activeImage=button2;
		}
		if(button2!=null&&isMouseHover) {
			activeImage=button2;
		}
		if(this.playerItem==this.targetItem&& !taskCompleted) {
			taskCompleted=true;
			System.out.println("Item number:"+itemNumber);
			
		}
		
//		System.out.println("=========================");
//		System.out.println("Item number:"+itemNumber);
//		System.out.println("targetItem:"+targetItem);
//		System.out.println("player item:"+playerItem);
	}

	protected void stateScript(int state) {
		if(worldState==MORNING) {
			if(itemNumber==ROSE) {
				targetItem=WATERING_CAN;
			}
			else if(itemNumber==FLAX) {
				targetItem=WATERING_CAN;
			}
			else {
				targetItem=0;
			}
		}
		
	}

	private void loadButtons() {
		if (worldState == 0 && itemNumber == 1) {
			activeImage = ImageLoader.loadImage("assets/image/door_closed.png");
			button2 = ImageLoader.loadImage("assets/image/door_open.png");
		}
		else if (worldState==MORNING) {
			if (itemNumber==SIGN) {
				//activeImage = ImageLoader.loadImage("assets/image/sign_closed.png");
				button = ImageLoader.loadImage("assets/image/sign_closed.png");
				button2 = ImageLoader.loadImage("assets/image/sign_open.png");
				activeImage=button;
			}
			else if(itemNumber==ROSE) {
				activeImage = ImageLoader.loadImage("assets/image/rose_pot.png");
//				water=new Water(pos.x,pos.y);
			}
			else if(itemNumber==FLAX) {
				activeImage = ImageLoader.loadImage("assets/image/flax_pot.png");
//				water=new Water(pos.x,pos.y);
			}
			else if(itemNumber==WATERING_CAN) {
				activeImage = ImageLoader.loadImage("assets/image/watering_can.png");
			}
		}
		else if(worldState==END) {
			activeImage=ImageLoader.loadImage("assets/image/ending_sign.png");
		}
	}

	public boolean mouseHover(int x, int y) {

		return (Math.abs(pos.x - x) < activeImage.getWidth(null) / 2
					&& Math.abs(pos.y - y) < activeImage.getHeight(null) / 2);
	}
	
	public void setIsMouseHover(boolean x) {
		isMouseHover=x;
	}

	@Override
	public boolean isClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if (Math.abs(pos.x - e.getX()) < activeImage.getWidth(null) / 2
				&& Math.abs(pos.y - e.getY()) < activeImage.getHeight(null) / 2) {
//			if(worldState==MORNING &&water!=null) {
//				water.setIsShowing(true);
//			}
			return true;
		}
		else {
			return false;
		}
	}
	
	public void setScale(float x) {
		scale+=x;
	}
	public void setOriginalScale() {
		scale=1;
	}
	public int getItemNumber() {
		return itemNumber;
	}

	@Override
	public void setPlayerItem(int x) {
		playerItem=x;
	}
	
	public void setActiveImage() {
		activeImage=button2;
	}
	public void resetActiveImage() {
		activeImage=button;
	}

	@Override
	public boolean getTaskCompleted() {
		// TODO Auto-generated method stub
		return taskCompleted;
	}

	@Override
	public boolean playerIsCloseToItem(Player p) {
		if (Math.abs(this.pos.x-p.getPos().x)<this.activeImage.getWidth(null)/2+p.getWidth()/2 ){
			return true;
		}
		else {
			return false;
		}
		
	}
//	public Water getWater() {
//		return water;
//	}
	
}
