package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;

import util.ImageLoader;

public class FlowerShop extends AllObjects {
	// Background

	//private BufferedImage img;
	//private Image img;
	
	private int layer;
	private int state;
	private Color backgroundColor;

	// ---------------------
	public FlowerShop(float x, float y, int state, int layer) {
		super(x, y);
		this.worldState = state;
		this.layer = layer;
		loadBackground(state, layer);
		backgroundColor=new Color (0,0,0);
	}

	// ---------------------
	@Override
	public void draw(Graphics2D g) {
		g.setBackground(backgroundColor);
		g.setColor(backgroundColor);
		g.fillRect(0,0,1920,1080);
		g.drawImage(activeImage, (int) pos.x, (int) pos.y, null);
		
		
	}

	public void update(int worldState) {
		loadBackground(worldState, layer);
	}

	// ---------------------
	@Override
	protected void stateScript(int state) {
		

	}

	// ---------------------
	protected void loadBackground(int state, int layer) {
		if (state == 0) {
			activeImage = new ImageIcon("assets/image/title_base.gif").getImage();
		}
		if(state==MORNING) {
			activeImage = new ImageIcon("assets/image/main_base.png").getImage();
			backgroundColor=new Color(171,211,245);
		}
		if(state==AFTERNOON) {
			backgroundColor=new Color(78,205,222);
		}
		if (state==EVENING) {
			backgroundColor=new Color(245,116,100);
		}
		if (state==END) {
			activeImage= new ImageIcon("assets/image/ending.gif").getImage();
			
		}
	}

	public void setState(int state) {
		this.state = state;
	}
	public void setBackgroundColor(Color c) {
		backgroundColor=c;
	}
}
