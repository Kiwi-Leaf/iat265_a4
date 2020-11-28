package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import util.ImageLoader;
import util.Util;

public class FlowerShop extends AllObjects {
	// Background

	// private BufferedImage img;
	// private Image img;

	private int layer;
	private int state;
	private Color backgroundColor;
	private Color flowerColor = new Color(204, 208, 248);
//	private Flower flower;
	private ArrayList<Flower> flower = new ArrayList<Flower>();
	private boolean showFlower=false;

	// ---------------------
	public FlowerShop(float x, float y, int state, int layer) {
		super(x, y);
		this.worldState = state;
		this.layer = layer;
		loadBackground(state, layer);
		backgroundColor = new Color(0, 0, 0);
//		for (int i = 0; i < 6; i++) {
//			for(int j=0;j<6;j++){
//			int r = Util.random(150,256);
//			int b = Util.random(150,256);
//			int g = Util.random(150,256);
//
//			Color tempColor = new Color(r, g, b, 255);
//			flower.add(new Flower(192+192*(i+j/2), 450+Util.random(5)*20, flowerColor,0, Util.random(70,80)));
//			}
//		}
		flower.add(new Flower(787, 177, flowerColor,0, Util.random(70,80)));
	}

	// ---------------------
	@Override
	public void draw(Graphics2D g) {
		g.setBackground(backgroundColor);
		g.setColor(backgroundColor);
		g.fillRect(0, 0, 1920, 1080);
		
		g.drawImage(activeImage, (int) pos.x, (int) pos.y, null);
		if(showFlower) {
			for(Flower f:flower) {	
				f.draw(g);
		}
		}
		
	}

	public void update(int worldState) {
		loadBackground(worldState, layer);
		
	}

	// ---------------------


	// ---------------------
	protected void loadBackground(int state, int layer) {
		if (state == 0) {
			activeImage = new ImageIcon("assets/image/title_base.gif").getImage();
		}
		if (state == MORNING) {
			activeImage = new ImageIcon("assets/image/main_base.png").getImage();
			backgroundColor = new Color(171, 211, 245);
			showFlower=true;
		}
		if (state == AFTERNOON) {
			backgroundColor = new Color(78, 205, 222);
			showFlower=true;
		}
		if (state == EVENING) {
			backgroundColor = new Color(245, 116, 100);
			showFlower=true;
		}
		if (state == END) {
			showFlower=false;
			activeImage = new ImageIcon("assets/image/ending.gif").getImage();

		}
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setBackgroundColor(Color c) {
		backgroundColor = c;
	}
}
