package mian;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import ddf.minim.*;

import chararcters.Customer;
import chararcters.Player;
import objects.AllObjects;
import objects.Buttons;
import objects.Flower;
import objects.FlowerShop;
import objects.Water;
import util.Conversation;
import util.MinimHelper;

public class FloristPanel extends JPanel implements ActionListener {
	
	private final Dimension screenSize = new Dimension(1920, 1080);
	private Timer timer;
	private boolean stateChanged = true;
	private FlowerShop background;
	private ArrayList<AllObjects> things = new ArrayList<AllObjects>();
	private Buttons door;
	private Buttons rosePot, flaxPot, wateringCan, sign, endingSign;
	private float enlargeScale = 0.2f;
	private Player iris;
	private Customer avon, felicia;
	private int taskCount;
	private int taskCompletedCount = 0;
	private Conversation conversation;
	private Minim minim = new Minim(new MinimHelper());
	private AudioPlayer titleBGM, mainBGM, endBGM, wateringCanSound, flowerBouquetSound, wateringSound,doorOpenSound,doorBellSound,woodSignSound;

	private JFrame frame;
	private Water water, water2;


	// ========Story_States=============
	private int state;
	private final int TITLE = 0;
	private final int MORNING = 1;
	private final int AFTERNOON = 2;
	private final int EVENING = 3;
	private final int END = 4;

	public FloristPanel(JFrame frame) {
		timer = new Timer(33, this);
		timer.start();
		state = TITLE;
		background = new FlowerShop(0, 0, state, 1);
		door = new Buttons(1571, 540, 0, 1);
		sign = new Buttons(268, 425, 1, 1);
		rosePot = new Buttons(1078, 650, 1, 2);
		flaxPot = new Buttons(1368, 650, 1, 3);
		wateringCan = new Buttons(1694, 492, 1, 4);

		endingSign = new Buttons(1150, 480, END, 1);
		taskCompletedCount = 0;

		things.add(sign);
		things.add(rosePot);
		things.add(flaxPot);
		things.add(wateringCan);

		conversation = new Conversation(50, 1080 - 300, MORNING);

		taskCount = 3;
		iris = new Player(1500, 525);
		felicia = new Customer(400, 535, AFTERNOON);
		avon = new Customer(400, 535, EVENING);

		water = new Water(1078, 650);
		water2 = new Water(1368, 650);


		titleBGM = minim.loadFile("sharing_an_umbrella.mp3");
		mainBGM = minim.loadFile("forgiven_fate.mp3");
		endBGM = minim.loadFile("checkmate.mp3");
		wateringCanSound = minim.loadFile("watering_can.mp3");
		wateringSound = minim.loadFile("water.mp3");
		flowerBouquetSound = minim.loadFile("flower.mp3");
		doorOpenSound=minim.loadFile("door_open.mp3");
		doorBellSound=minim.loadFile("door_with_bell.mp3");
		woodSignSound=minim.loadFile("woodSign.mp3");


		setPreferredSize(screenSize);
		addMouseListener(new MyMouseAdapter());
		//addMouseMotionListener(new MyMouseMotionAdapter());
		addKeyListener(new MyKeyAdapter());
		setFocusable(true);
		titleBGM.loop();
		this.frame = frame;
		
		System.out.println("This is the title screen.");
		System.out.println("You will be using mouse interaction and \"a\" & \"d\" key to interact with elements.");
		System.out.println("Here, you should be able to click on the door to go to the net stage.");
		System.out.println("================================================");
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		background.draw(g2);
		if (state == TITLE) {
			door.draw(g2);
		} else if (state > TITLE && state < END) {

			for (AllObjects t : things) {
				if (t != null) {
					t.draw(g2);
				}
			}
			if (state == MORNING) {
				water.draw(g2);
				water2.draw(g2);
			}

			iris.draw(g2);
			conversation.draw(g2);
		} else if (state == END) {
			endingSign.draw(g2);
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		changeState();
		if (state == TITLE) {

		} else if (state > TITLE && state < END) {

			background.update(state);
			for (AllObjects t : things) {
				if (t != null) {
					t.update(state);
				}
			}
			iris.update();
			if (state == MORNING) {
				if (taskCount == taskCompletedCount) {
					stateChanged = false;
				}
			} else if (state == AFTERNOON) {
				if (conversation.getCurrentConversation() == 5) {
					for (int i = 0; i < things.size(); i++) {
						if (things.get(i) instanceof Customer) {
							things.remove(i);
							doorBellSound.play(0);
						}
					}
				}
				if (conversation.getTaskCompleted()) {
					stateChanged = false;
				}
			} else if (state == EVENING) {
				if (conversation.getCurrentConversation() >=9) {
					background.setBackgroundColor(new Color(74, 54, 235));
					for (int i = 0; i < things.size(); i++) {
						if (things.get(i) instanceof Customer) {
							things.remove(i);
							doorBellSound.play(0);
						}
					}
					if (conversation.getCurrentConversation() >= 10) {
						sign.resetActiveImage();
					}
				}

				if (conversation.getTaskCompleted()) {
					stateChanged = false;
				}
			}

			conversation.update(state);
		} else if (state == END) {

			background.update(state);
		}

		repaint();
	}

	private class MyMouseAdapter extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (state == TITLE) {
				if (door.isClicked(e)) {
					stateChanged = false;
				}
				titleBGM.pause();
				titleBGM.rewind();
				mainBGM.loop();
				
			}
		}

		public void mousePressed(MouseEvent e) {
			if (state == TITLE) {
				if (door.isClicked(e)) {
					door.setActiveImage();
					doorOpenSound.play(0);
				}
			} else if (state > TITLE && state < END) {
				for (AllObjects t : things) {
					if (t instanceof Buttons) {
						Buttons buttons = (Buttons) t;
						if (buttons.isClicked(e) && buttons.playerIsCloseToItem(iris)) {
							buttons.setScale(enlargeScale);
							if (state == MORNING) {
								if (iris.getItemInHand() == 4) {
									if (!buttons.getTaskCompleted()) {
										taskCompletedCount += 1;
										
										if (buttons.getItemNumber() == 2) {
											water.setIsShowing(true);
											wateringSound.play(0);
										} else if (buttons.getItemNumber() == 3) {
											water2.setIsShowing(true);
											wateringSound.play(0);
										}
									}
									buttons.setPlayerItem(iris.getItemInHand());
								} else if (buttons.getItemNumber() == 1) {
//									iris.setItem(buttons.getItemNumber());
									if (!buttons.getTaskCompleted()) {
										taskCompletedCount += 1;
										woodSignSound.play(0);
									}
									buttons.setTaskCompleted(true);

								} else {
									if (buttons.getItemNumber() == 4) {
										wateringCanSound.play(0);
									} else if (buttons.getItemNumber() == 2 || buttons.getItemNumber() == 3) {
										flowerBouquetSound.play(0);
									}
									iris.setItem(buttons.getItemNumber());

								}
							} else if (state == AFTERNOON) {
								if (buttons.getItemNumber() != 1) {
									if(buttons.getItemNumber()==4) {
										wateringCanSound.play(0);
									}
									else {
										flowerBouquetSound.play(0);
									}
									iris.setItem(buttons.getItemNumber());
								}
							} else if (state == EVENING) {
								if (buttons.getItemNumber() != 1) {
									if(buttons.getItemNumber()==4) {
										wateringCanSound.play(0);
									}
									else {
										flowerBouquetSound.play(0);
									}
									iris.setItem(buttons.getItemNumber());
								} else if (buttons.getItemNumber() == 1) {
									if (conversation.getCurrentConversation() == 9) {
										sign.resetActiveImage();
										woodSignSound.play(0);
										conversation.setMidStop(true);
									}
								}
							}
						}
					} else if (t instanceof Customer) {
						Customer customer = (Customer) t;
						if (customer.isClicked(e) && customer.playerIsCloseToItem(iris)) {
							if (iris.getItemInHand() == customer.getWantedItem()) {
								iris.setItem(0);
								customer.setTaskCompleted(true);
								flowerBouquetSound.play(0);
								conversation.setMidStop(true);
							}
						}
					}
				}

			} else if (state == END) {
				if (endingSign.isClicked(e)) {
					endingSign.setScale(enlargeScale);
					frame.dispose();
					endBGM.close();
					titleBGM.close();
					mainBGM.close();
					frame = new FloristApp("A Day at the Florist's");

				}
			}
		}

		public void mouseReleased(MouseEvent e) {
			if (state > TITLE && state < END) {
				for (AllObjects t : things) {
					if (t instanceof Buttons) {
						Buttons buttons = (Buttons) t;
						buttons.setOriginalScale();
					}
				}
			} else if (state == END) {
				endingSign.setOriginalScale();
			}
		}
	}


	private void changeState() {
		if (!stateChanged) {
			if (state == TITLE) {
				state = 1;
				background = new FlowerShop(0, 0, state, 1);
				taskCount = 3;
				stateChanged = true;
				
				System.out.println("This is the morning stage.");
				System.out.println("Each Conversation dialogues will change automatically after 3 seconds");
				System.out.println("However, if the dialogue is related to task, it will stay until the task is completed");
				System.out.println("Interaction with items only applies when character is close to the object, i.e. the image of item and character need to overlap.");
				System.out.println("Here you should be able to water the plants with watering can and change the sign to open.");
				System.out.println("=========================================================");

			} else if (state == MORNING) {
				state = 2;
				taskCount = 1;
				conversation = new Conversation(50, 1080 - 300, AFTERNOON);
				stateChanged = true;
				for (AllObjects t : things) {
					if (t instanceof Buttons && ((Buttons) t).getItemNumber() == 1) {
						Buttons sign = (Buttons) t;
						sign.setActiveImage();
					}
				}
				things.add(felicia);
				doorBellSound.play(0);
				System.out.println("For this stage and the next, you can pick up flowers to give to customers.");
				System.out.println("You can only pick up flowers if you are not holding the watering can. Press space to drop items if needed.");
				System.out.println("This customer will request for the flowers on the right.");
				System.out.println("====================================");
				
			} else if (state == AFTERNOON) {
				state = 3;
				taskCount = 2;
				conversation = new Conversation(50, 1080 - 300, EVENING);
				things.add(avon);
				doorBellSound.play(0);
				stateChanged = true;
				System.out.println("This customer will request the flowers on the right.");
				System.out.println("After the customer leaves, there will be dialogues prompting you to interact with the sign on the door.");
				System.out.println("Click on the sign on that stage will get you to the ending screen.");
				System.out.println("============================");
			} else if (state == EVENING) {
				mainBGM.pause();
				mainBGM.rewind();
				endBGM.loop();
				state = 4;
				taskCount = 0;
				stateChanged = true;
				System.out.println("This is the ending screen.");
				System.out.println("Click on the card to restart app.");
				System.out.println("=================");
			}
		}
	}

	private class MyKeyAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
				iris.setFacingRight(true);
				iris.setWalking(true);
			}
			if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
				iris.setFacingRight(false);
				iris.setWalking(true);
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				iris.setItem(0);
			}

		}

		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT
					|| e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
				iris.setWalking(false);
			}
		}
	}


}
