package chararcters;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import objects.Buttons;
import objects.Flower;
import util.ImageLoader;

public class Player extends Characters {

//	private Image activeImage;
	private boolean walking = false;
	private boolean facingRight = false;
	private int itemInHand = 0;
	private final int EMPTY = 0;
	private Image standing, walk, walkRose, walkWater, walkFlax;
	private int speed = 10;
	private BufferedImage roseBouquet, flaxBouquet, wateringCan;
//	private Color flowerColor = new Color(204, 208, 248);
//	private Flower flower;

	// -----------------------------------
	public Player(float x, float y) {
		// TODO Auto-generated constructor stub
		super(x, y);
		standing = new ImageIcon("assets/image/mc_front.gif").getImage();
		walk = new ImageIcon("assets/image/mc_side_idle.gif").getImage();
		walkRose = new ImageIcon("assets/image/mc_side_rose.gif").getImage();
		walkWater = new ImageIcon("assets/image/mc_side_watering_can.gif").getImage();
		walkFlax = new ImageIcon("assets/image/mc_side_flax.gif").getImage();

//		flower = new Flower(0, 0, flowerColor, 40);

		roseBouquet = ImageLoader.loadImage("assets/image/rose_bouquet.png");
		flaxBouquet = ImageLoader.loadImage("assets/image/flax_bouquet.png");
		wateringCan = ImageLoader.loadImage("assets/image/watering_can.png");

		activeImage = standing;
	}

	// -----------------------------------
	public void update() {
		setActiveImage();
		move();
	}

	// -----------------------------------
	protected void setActiveImage() {
		if (walking == false) {
			activeImage = standing;
		} else {
			if (itemInHand == ROSE) {
				activeImage = walkRose;
			} else if (itemInHand == FLAX) {
				activeImage = walkFlax;
			} else if (itemInHand == WATERING_CAN) {
				activeImage = walkWater;
			} else
				activeImage = walk;
		}
	}

	// ---------------------------------
	protected void move() {
		if (walking) {
			if (facingRight && pos.x < 1920 - activeImage.getWidth(null) / 2) {
				pos.x += speed;
			} else if (!facingRight && pos.x > activeImage.getWidth(null) / 2) {
				pos.x -= speed;
			}
		}
	}


	

	// -----------------------------------
	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		AffineTransform origin = g.getTransform();

		g.translate(pos.x, pos.y);

		g.scale(scale, scale);
		if (facingRight) {
			g.scale(-1, 1);
		}
		g.drawImage(activeImage, -activeImage.getWidth(null) / 2, -activeImage.getHeight(null) / 2, null);

//		if(!(walking&&facingRight)) {
//		AffineTransform flowerPosition=g.getTransform();
//		g.translate(50,-130);
//		flower.draw(g);
//		g.setTransform(flowerPosition);
//		}
		
		
		AffineTransform tempTrans = g.getTransform();

		if (!walking) {
			if (itemInHand == ROSE) {
				g.translate(-100, 70);
				g.rotate(-19.5);
				g.drawImage(roseBouquet, -roseBouquet.getWidth() / 2, -roseBouquet.getHeight() / 2, null);
			} else if (itemInHand == FLAX) {
				g.translate(-120, 70);
				g.rotate(-19);
				g.drawImage(flaxBouquet, -flaxBouquet.getWidth() / 2, -flaxBouquet.getHeight() / 2, null);
			} else if (itemInHand == WATERING_CAN) {
				g.translate(-160, 160);
				g.rotate(-19);
				g.drawImage(wateringCan, -wateringCan.getWidth() / 2, -wateringCan.getHeight() / 2, null);
			}
		}

		g.setTransform(tempTrans);

		g.setTransform(origin);
	}

	@Override
	public String dialogue() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setWalking(boolean x) {
		walking = x;
	}

	public void setFacingRight(boolean x) {
		facingRight = x;
	}

	public void setItem(int x) {
		itemInHand = x;
	}

	public int getItemInHand() {
		return itemInHand;
	}

	public boolean isCloseToButton(Buttons b) {
		boolean dis;
		dis = (Math.abs(this.pos.x - b.getPos().x) < this.activeImage.getWidth(null) / 2 + b.getWidth() / 2);
		return dis;
	}
}
