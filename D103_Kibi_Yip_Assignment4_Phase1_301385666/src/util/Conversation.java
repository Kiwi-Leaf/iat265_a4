package util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import ddf.minim.*;

import objects.AllObjects;

public class Conversation extends AllObjects {
	protected ArrayList<BufferedImage> avon, felicia, iris;
	protected Color avonColor, feliciaColor, irisColor, tutorialColor, activeColor;
	protected int startTimer;
	protected int conversationTimer;
	protected int conversationLength;
	protected int currentConversation;
	protected boolean midStop;
	protected Rectangle2D.Double textBox;
	protected String speaker, speechContent;
	protected Font textFont, titleFont;
	protected boolean imageOnRight = true;

	public Conversation(float x, float y, int worldState) {
		super(x, y);
		this.worldState = worldState;
		startTimer = 90;
		conversationTimer = 120;
		avon = new ArrayList<BufferedImage>();
		avon.add(ImageLoader.loadImage("assets/image/avon_avatar_1.png"));
		avon.add(ImageLoader.loadImage("assets/image/avon_avatar_2.png"));
		avonColor = new Color(111, 140, 245, 225);

		felicia = new ArrayList<BufferedImage>();
		felicia.add(ImageLoader.loadImage("assets/image/felicia_avatar_1.png"));
		felicia.add(ImageLoader.loadImage("assets/image/felicia_avatar_2.png"));
		feliciaColor = new Color(245, 159, 135, 225);

		iris = new ArrayList<BufferedImage>();
		iris.add(ImageLoader.loadImage("assets/image/mc_avatar_1.png"));
		iris.add(ImageLoader.loadImage("assets/image/mc_avatar_2.png"));
		iris.add(ImageLoader.loadImage("assets/image/mc_avatar_3.png"));
		irisColor = new Color(168, 245, 86, 225);

		tutorialColor = new Color(196, 177, 135, 225);
		textBox = new Rectangle2D.Double(0, 40, 1920 - 100, 270 - 50 - 40);
		activeColor = irisColor;
		speaker = "Testing";
		speechContent = "placeholder";
		titleFont = new Font("Arial Rounded MT Bold", Font.PLAIN, 50);
		textFont = new Font("Arial Rounded MT Bold", Font.PLAIN, 35);
		activeImage = iris.get(0);
		currentConversation = 1;
		loadScript();
	}

	public void update(int worldState) {
		stateScript(worldState);
		if (startTimer > -2) {
			startTimer--;
		} else {
			conversationTimer--;
		}
		if (conversationTimer < 0 && currentConversation == conversationLength) {
			taskCompleted = true;
		}
//		if (state == MORNING) {
//
//		} else if (state == AFTERNOON) {
//
//		}

	}

	@Override
	public void draw(Graphics2D g) {
		AffineTransform origin = g.getTransform();
		g.translate(pos.x, pos.y);
		g.scale(scale, scale);

		if (startTimer < 0) {
			if (activeImage != null) {
				if (imageOnRight) {
					// for iris
					g.drawImage(activeImage, 1920 - 80 - activeImage.getWidth(null), -activeImage.getHeight(null) / 2,
							null);
				} else {
					// for customers
					g.drawImage(activeImage, 0, -activeImage.getHeight(null) / 2, null);
				}
			}
//			System.out.println("is drawing");
			g.setColor(activeColor);
			g.fill(textBox);
			g.setColor(Color.black);
			g.setFont(titleFont);
			g.drawString(speaker, 50, 100);
			g.setFont(textFont);
			if (worldState == MORNING && (currentConversation == 2)) {
				drawString(g, speechContent, 50, 90);
			} else {
				drawString(g, speechContent, 50, 120);
			}
		}
		g.setTransform(origin);
	}

	private void loadScript() {
		if (worldState == MORNING) {
			conversationLength = 3;
		} else if (worldState == AFTERNOON) {
			conversationLength = 5;
		} else if (worldState == EVENING) {
			conversationLength = 9;
		}
	}

	public int getCurrentConversation() {
		return currentConversation;
	}

	@Override
	protected void stateScript(int worldState) {
		if (worldState == MORNING) {
			if (currentConversation == 1) {
				imageOnRight = true;
				activeColor = irisColor;
				activeImage = iris.get(0);
				speaker = "Iris";
				speechContent = "Alright, let's see¡K what do I need to do¡K";
				autoNextConversation();
			} else if (currentConversation == 2) {
				imageOnRight = true;
				activeColor = tutorialColor;
				activeImage = null;
				speaker = "";
				speechContent = "Use 'A' or 'D' to move around. Click on item to get it. Press Space to drop item.";
				autoNextConversation();
			} else if (currentConversation == 3) {
				imageOnRight = true;
				activeColor = irisColor;
				activeImage = iris.get(0);
				speaker = "Iris";
				speechContent = "OK, I need to get the can and water the flowers, and open the store.";
			}
		} else if (worldState == AFTERNOON) {
			if (currentConversation == 1) {
				imageOnRight = false;
				activeColor = feliciaColor;
				activeImage = felicia.get(0);
				speaker = "Felicia";
				speechContent = "Uh¡K Hello¡KUm¡K May I please get some flowers for my friends, it would be best if it is blue¡K";
				if (midStop) {
					currentConversation++;
					conversationTimer = 120;
					midStop = false;
				}
			} else if (currentConversation == 2) {
				imageOnRight = true;
				activeColor = irisColor;
				activeImage = iris.get(2);
				speaker = "Iris";
				speechContent = "Here you go! I hope your friend likes them!";
				autoNextConversation();
			} else if (currentConversation == 3) {
				imageOnRight = false;
				activeColor = feliciaColor;
				activeImage = felicia.get(1);
				speaker = "Felicia";
				speechContent = "They are so beautiful! Thanks for picking those for my friend.";
				autoNextConversation();
			} else if (currentConversation == 4) {
				imageOnRight = false;
				activeColor = feliciaColor;
				activeImage = felicia.get(1);
				speaker = "Felicia";
				speechContent = "They are having a hard time lately. I hope this can cheer them up a bit. Thanks again.";
				autoNextConversation();
			} else if (currentConversation == 5) {
				imageOnRight = true;
				activeColor = irisColor;
				activeImage = iris.get(1);
				speaker = "Iris";
				speechContent = "¡K I hope the flowers can help her friend¡K";
				autoNextConversation();
			}
		} else if (worldState == EVENING) {
			if (currentConversation == 1) {
				imageOnRight = true;
				activeColor = irisColor;
				activeImage = iris.get(0);
				speaker = "Iris";
				speechContent = "Hello there. What are you looking for today?";
				autoNextConversation();
			} else if (currentConversation == 2) {
				imageOnRight = false;
				activeColor = avonColor;
				activeImage = avon.get(0);
				speaker = "Avon";
				speechContent = "I need something for our anniversary. Do you have any suggestion?";
				autoNextConversation();
			} else if (currentConversation == 3) {
				imageOnRight = true;
				activeColor = irisColor;
				activeImage = iris.get(0);
				speaker = "Iris";
				speechContent = "Of course! We have beautiful roses today, would you like them?";
				autoNextConversation();
			} else if (currentConversation == 4) {
				imageOnRight = false;
				activeColor = avonColor;
				activeImage = avon.get(0);
				speaker = "Avon";
				speechContent = "Sure. That sounds good.";
				if (midStop) {
					currentConversation++;
					conversationTimer = 120;
					midStop = false;
				}
			} else if (currentConversation == 5) {
				imageOnRight = true;
				activeColor = irisColor;
				activeImage = iris.get(0);
				speaker = "Iris";
				speechContent = "Here you go! ";
				autoNextConversation();
			} else if (currentConversation == 6) {
				imageOnRight = false;
				activeColor = avonColor;
				activeImage = avon.get(0);
				speaker = "Avon";
				speechContent = "¡K";
				autoNextConversation();
			} else if (currentConversation == 7) {
				imageOnRight = false;
				activeColor = avonColor;
				activeImage = avon.get(1);
				speaker = "Avon";
				speechContent = "Thanks.";
				autoNextConversation();
			} else if (currentConversation == 8) {
				imageOnRight = true;
				activeColor = irisColor;
				activeImage = iris.get(0);
				speaker = "Iris";
				speechContent = "Phew¡K Alright, let's close the store and end the day.";
				if (midStop) {
					currentConversation++;
					conversationTimer = 120;
					midStop = false;
				}
			}
			else if(currentConversation==9) {
				imageOnRight = true;
				activeColor = irisColor;
				activeImage = iris.get(2);
				speaker = "Iris";
				speechContent = "Thanks for helping.";
				autoNextConversation();
			}
		}
	}

	private void drawString(Graphics g, String text, int x, int y) {
		for (String line : text.split("\n"))
			g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}

	public void setMidStop(boolean x) {
		midStop = x;
	}

	private void autoNextConversation() {
		if (conversationTimer < 0) {
			currentConversation++;
			conversationTimer = 120;
		}
	}

}
