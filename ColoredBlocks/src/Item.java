
// Project created by Ibrahim Manfoud

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Item implements ActionListener, KeyListener, MouseListener, Serializable {
	private static final long serialVersionUID = -12345679L;
	private Color c;
	public static Item item;
	private Color Player;
	private Renderer renderer;
	private Rectangle player;
	private ArrayList<Rectangle> whiteBox;
	private ArrayList<Rectangle> redBox;
	private ArrayList<Rectangle> blueBox;
	private ArrayList<Rectangle> greenBox;
	private ArrayList<Rectangle> orangeBox;
	private ArrayList<Rectangle> orangeLongBox;
	private ArrayList<Rectangle> redLongBox;
	private ArrayList<Rectangle> greenLongBox;
	private ArrayList<Rectangle> blueLongBox;
	private static int speed;
	private static int score = 0;
	private static double colorPlayer;
	private static double boxLocation;
	private static double XorY;
	private static double orangeLocation;
	private static int width = 600;
	private static int height = 600;
	private static int classicEasyScore = 0;
	private static int classicMediumScore = 0;
	private static int classicHardScore = 0;
	private static int classicInsaneScore = 0;
	private static int fourWaysEasyScore = 0;
	private static int fourWaysMediumScore = 0;
	private static int fourWaysHardScore = 0;
	private static int fourWaysInsaneScore = 0;
	private static int storyScore = 0;
	private static String saveState = "1";
	private Random rand;
	private static BufferedImage howtoplay;
	private static BufferedImage gamemodes;
	private static BufferedImage settings;
	private static BufferedImage color;
	private static BufferedImage blocks;
	private static BufferedImage classicGame;
	private static BufferedImage borderSetting;
	private static BufferedImage On;
	private static BufferedImage Off;
	private static BufferedImage exit;
	private static BufferedImage scoreMessage;
	private static BufferedImage Hscore;
	private static BufferedImage playAgain;
	private static BufferedImage easy;
	private static BufferedImage medium;
	private static BufferedImage hard;
	private static BufferedImage insane;
	private static BufferedImage gameStartImage;
	private static BufferedImage controls;
	private static BufferedImage wasd;
	private static BufferedImage arrows;
	private static BufferedImage alldir;
	private static BufferedImage storymode;
	private static boolean border = true;
	private static boolean control = true;
	private static double r1 = .1;
	private static double r2 = .4;

	public static enum STATE {
		mainMenu, settings, gameModes, classicChoices, classicEasy, classicMedium, classicHard, classicEasy2, classicMedium2, classicHard2, classicStarterMedium, classicStarterEasy, classicStarterHard, fourWaysChoices, fourWaysEasy, fourWaysMedium, fourWaysHard, fourWaysEasy2, fourWaysMedium2, fourWaysHard2, fourWaysStarterEasy, fourWaysStarterMedium, fourWaysStarterHard, storyGame, storyGame2, storyGameStarter, fourWaysStarterInsane, classicStarterInsane, fourWaysInsane, fourWaysInsane2, classicInsane, classicInsane2, boxGameState, boxGameState2
	};

	public static STATE state = STATE.mainMenu;

	public Item() {
		rand = new Random();
		whiteBox = new ArrayList<Rectangle>();
		redBox = new ArrayList<Rectangle>();
		blueBox = new ArrayList<Rectangle>();
		greenBox = new ArrayList<Rectangle>();
		orangeBox = new ArrayList<Rectangle>();
		orangeLongBox = new ArrayList<Rectangle>();
		greenLongBox = new ArrayList<Rectangle>();
		blueLongBox = new ArrayList<Rectangle>();
		redLongBox = new ArrayList<Rectangle>();
		JFrame frame = new JFrame();
		renderer = new Renderer();
		Timer timer = new Timer(20, this);
		frame.addKeyListener((KeyListener) this);
		frame.addMouseListener((MouseListener) this);
		frame.add(renderer);
		frame.setTitle("Color Blocks");
		frame.setSize(600, 600);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		player = new Rectangle(200, 200, 20, 20);
		timer.start();
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		setImages();
		item = new Item();
	}

	public void actionPerformed(ActionEvent e) {
		classic();
		fourWays();
		boxGame();
		renderer.repaint();
	}

	public static void writeToFile(Item i) throws IOException {
		OutputStream file = new FileOutputStream("ColorBlocksSave.bin");
		OutputStream buffer = new BufferedOutputStream(file);
		ObjectOutput output = new ObjectOutputStream(buffer);
		output.writeObject(i);
	}

	public static void readFile() throws IOException, ClassNotFoundException {
		InputStream file = new FileInputStream("ColorBlocksSave.bin");
		InputStream buffer = new BufferedInputStream(file);
		ObjectInput input = new ObjectInputStream(buffer);
		item = (Item) input.readObject();
	}

	public static void setImages() {
		try {
			howtoplay = ImageIO.read((Item.class.getResource("howtoplay.png")));
			gamemodes = ImageIO.read((Item.class.getResource("gamemodes.png")));
			color = ImageIO.read((Item.class.getResource("color.png")));
			blocks = ImageIO.read((Item.class.getResource("blocks.png")));
			classicGame = ImageIO.read((Item.class.getResource("classic.png")));
			settings = ImageIO.read((Item.class.getResource("Settings.png")));
			borderSetting = ImageIO.read((Item.class.getResource("border.png")));
			On = ImageIO.read((Item.class.getResource("On.png")));
			Off = ImageIO.read((Item.class.getResource("Off.png")));
			exit = ImageIO.read((Item.class.getResource("exit.png")));
			scoreMessage = ImageIO.read((Item.class.getResource("scoreText.png")));
			Hscore = ImageIO.read((Item.class.getResource("highscore.png")));
			playAgain = ImageIO.read((Item.class.getResource("playAgain.png")));
			easy = ImageIO.read((Item.class.getResource("easy.png")));
			medium = ImageIO.read((Item.class.getResource("medium.png")));
			hard = ImageIO.read((Item.class.getResource("hard.png")));
			gameStartImage = ImageIO.read((Item.class.getResource("startGame.png")));
			controls = ImageIO.read((Item.class.getResource("controls.png")));
			arrows = ImageIO.read((Item.class.getResource("arrows.png")));
			wasd = ImageIO.read((Item.class.getResource("wasd.png")));
			alldir = ImageIO.read((Item.class.getResource("alldir.png")));
			storymode = ImageIO.read((Item.class.getResource("storymode.png")));
			insane = ImageIO.read((Item.class.getResource("insane.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// the repaint method uses game states and variables to determine what
	// should be displayed on the jframe
	public void repaint(Graphics g) {
		if (state == STATE.boxGameState) {
			g.setColor(Color.black);
			g.fillRect(0, 0, 600, 600);
			if (colorPlayer <= .25) {
				c = Color.orange;
			}
			else if (colorPlayer <= .5 && colorPlayer > .25) {
				c = Color.red;
			}
			else if (colorPlayer <= .75 && colorPlayer > .5) {
				c = Color.blue;
			}
			else {
				c = Color.green;
			}
			g.setColor(c);
			g.fillRect(player.x, player.y, player.width, player.height);
			for (Rectangle red : redLongBox) {
				paintRedColumn(g, red);
			}
			for (Rectangle blue : blueLongBox) {
				paintBlueColumn(g, blue);
			}
			for (Rectangle green : greenLongBox) {
				paintgreenColumn(g, green);
			}
			for (Rectangle orange : orangeLongBox) {
				paintOrangeColumn(g, orange);
			}
			if (border) {
				g.setColor(Color.RED);
				g.fillRect(0, 0, 10, 600);
				g.setColor(Color.green);
				g.fillRect(0, 561, 600, 10);
				g.setColor(Color.ORANGE);
				g.fillRect(584, 0, 10, 600);
				g.setColor(Color.blue);
				g.fillRect(0, 0, 600, 10);
				g.setColor(Color.red);
				g.fillRect(0, 0, 10, 10);
			}
		}

		if (state == STATE.classicMedium || state == STATE.classicEasy || state == STATE.classicHard
				|| state == STATE.fourWaysMedium || state == STATE.fourWaysEasy || state == STATE.fourWaysHard
				|| state == STATE.classicInsane || state == STATE.fourWaysInsane || state == STATE.storyGame) {
			g.setColor(Color.black);
			g.fillRect(0, 0, 600, 600);
			if (colorPlayer <= .2) {
				c = Color.white;
			}
			if (colorPlayer <= 1 && colorPlayer > .8) {
				c = Color.red;
			}
			if (colorPlayer <= .4 && colorPlayer > .2) {
				c = Color.blue;
			}
			if (colorPlayer <= .6 && colorPlayer > .4) {
				c = Color.green;
			}
			if (colorPlayer <= .8 && colorPlayer > .6) {
				c = Color.orange;
			}
			if (border) {
				g.setColor(Color.RED);
				g.fillRect(0, 0, 10, 600);
				g.setColor(Color.green);
				g.fillRect(0, 561, 600, 10);
				g.setColor(Color.ORANGE);
				g.fillRect(584, 0, 10, 600);
				g.setColor(Color.blue);
				g.fillRect(0, 0, 600, 10);
				g.setColor(Color.red);
				g.fillRect(0, 0, 10, 10);
			}
			g.setColor(c);
			g.fillRect(player.x, player.y, player.width, player.height);
			Player = g.getColor();
			for (Rectangle white : whiteBox) {
				paintWhiteColumn(g, white);
			}
			for (Rectangle red : redBox) {
				paintRedColumn(g, red);
			}
			for (Rectangle blue : blueBox) {
				paintBlueColumn(g, blue);
			}
			for (Rectangle green : greenBox) {
				paintgreenColumn(g, green);
			}
			for (Rectangle orange : orangeBox) {
				paintOrangeColumn(g, orange);
			}
			g.setColor(Color.white);
			g.drawString("Score: " + score, 15, 25);
			if (state == STATE.fourWaysEasy) {
				g.drawString("High Score: " + fourWaysEasyScore, 485, 25);
			}
			if (state == STATE.fourWaysMedium) {
				g.drawString("High Score: " + fourWaysMediumScore, 485, 25);
			}
			if (state == STATE.fourWaysHard) {
				g.drawString("High Score: " + fourWaysHardScore, 485, 25);
			}
			if (state == STATE.fourWaysInsane) {
				g.drawString("High Score: " + fourWaysInsaneScore, 485, 25);
			}
			if (state == STATE.classicEasy) {
				g.drawString("High Score: " + classicEasyScore, 485, 25);
			}
			if (state == STATE.classicMedium) {
				g.drawString("High Score: " + classicMediumScore, 485, 25);
			}
			if (state == STATE.classicHard) {
				g.drawString("High Score: " + classicHardScore, 485, 25);
			}
			if (state == STATE.classicInsane) {
				g.drawString("High Score: " + classicInsaneScore, 485, 25);
			}
			if (state == STATE.storyGame) {
				g.drawString("High Score: " + storyScore, 485, 25);
			}
		}
		if (state == STATE.mainMenu) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 600, 600);
			g.drawImage(howtoplay, 213, 330, 175, 43, null);
			g.drawImage(gamemodes, 192, 456, 216, 44, null);
			g.drawImage(settings, 227, 390, 147, 49, null);
			g.drawImage(color, 145, 100, null);
			g.drawImage(blocks, 112, 200, null);
			if (border) {
				g.setColor(Color.RED);
				g.fillRect(0, 0, 10, 600);
				g.setColor(Color.green);
				g.fillRect(0, 561, 600, 10);
				g.setColor(Color.ORANGE);
				g.fillRect(584, 0, 10, 600);
				g.setColor(Color.blue);
				g.fillRect(0, 0, 600, 10);
				g.setColor(Color.red);
				g.fillRect(0, 0, 10, 10);
			}
		}
		if (state == STATE.gameModes) {
			g.setColor(Color.black);
			g.fillRect(0, 0, 600, 600);
			g.drawImage(classicGame, 176, 170, null);

			if (border) {
				g.setColor(Color.RED);
				g.fillRect(0, 0, 10, 600);
				g.setColor(Color.green);
				g.fillRect(0, 561, 600, 10);
				g.setColor(Color.ORANGE);
				g.fillRect(584, 0, 10, 600);
				g.setColor(Color.blue);
				g.fillRect(0, 0, 600, 10);
				g.setColor(Color.red);
				g.fillRect(0, 0, 10, 10);
			}
			g.drawImage(exit, 20, 20, 78, 44, null);
			g.drawImage(alldir, 76, 290, null);
			g.drawImage(storymode, 105, 50, null);
		}
		if (state == STATE.settings) {
			g.setColor(Color.black);
			g.fillRect(0, 0, 600, 600);
			if (border) {
				g.setColor(Color.RED);
				g.fillRect(0, 0, 10, 600);
				g.setColor(Color.green);
				g.fillRect(0, 561, 600, 10);
				g.setColor(Color.ORANGE);
				g.fillRect(584, 0, 10, 600);
				g.setColor(Color.blue);
				g.fillRect(0, 0, 600, 10);
				g.setColor(Color.red);
				g.fillRect(0, 0, 10, 10);
				g.setColor(Color.white);
				g.fillRect(148, 153, 129, 98);
			} else {
				g.setColor(Color.white);
				g.fillRect(301, 153, 151, 98);
			}
			if (control) {
				g.setColor(Color.white);
				g.fillRect(286, 395, 269, 98);
			} else {
				g.setColor(Color.white);
				g.fillRect(45, 395, 223, 98);
			}
			g.drawImage(borderSetting, 173, 50, null);
			g.drawImage(On, 153, 158, null);
			g.drawImage(Off, 306, 158, null);
			g.drawImage(exit, 20, 20, 78, 44, null);
			g.drawImage(controls, 135, 280, null);
			g.drawImage(wasd, 50, 400, null);
			g.drawImage(arrows, 291, 400, null);
		}
		if (state == STATE.classicMedium2 || state == STATE.classicEasy2 || state == STATE.classicHard2
				|| state == STATE.fourWaysMedium2 || state == STATE.fourWaysEasy2 || state == STATE.fourWaysHard2
				|| state == STATE.storyGame2 || state == STATE.classicInsane2 || state == STATE.fourWaysInsane2) {
			g.setColor(Color.black);
			g.fillRect(0, 0, 600, 600);
			if (border) {
				g.setColor(Color.RED);
				g.fillRect(0, 0, 10, 600);
				g.setColor(Color.green);
				g.fillRect(0, 561, 600, 10);
				g.setColor(Color.ORANGE);
				g.fillRect(584, 0, 10, 600);
				g.setColor(Color.blue);
				g.fillRect(0, 0, 600, 10);
				g.setColor(Color.red);
				g.fillRect(0, 0, 10, 10);
			}
			g.drawImage(exit, 20, 20, 78, 44, null);
			g.drawImage(scoreMessage, 40, 200, null);
			g.drawImage(Hscore, 35, 300, null);
			if (r1 < .2) {
				g.setColor(Color.red);
			}
			if (r1 < .4 && r1 >= .2) {
				g.setColor(Color.orange);
			}
			if (r1 < .6 && r1 >= .4) {
				g.setColor(Color.blue);
			}
			if (r1 < .8 && r1 >= .6) {
				g.setColor(Color.green);
			}
			if (r1 < 1 && r1 >= .8) {
				g.setColor(Color.orange);
			}
			Font font = new Font("times", 10, 50);
			g.setFont(font);
			g.drawString("" + score, 472, 250);
			if (r2 < .2) {
				g.setColor(Color.red);
			}
			if (r2 < .4 && r2 >= .2) {
				g.setColor(Color.white);
			}
			if (r2 < .6 && r2 >= .4) {
				g.setColor(Color.blue);
			}
			if (r2 < .8 && r2 >= .6) {
				g.setColor(Color.green);
			}
			if (r2 < 1 && r2 >= .8) {
				g.setColor(Color.orange);
			}
			if (state == STATE.fourWaysEasy2) {
				g.drawString("" + fourWaysEasyScore, 478, 350);
			}
			if (state == STATE.fourWaysMedium2) {
				g.drawString("" + fourWaysMediumScore, 478, 350);
			}
			if (state == STATE.fourWaysHard2) {
				g.drawString("" + fourWaysHardScore, 478, 350);
			}
			if (state == STATE.fourWaysInsane2) {
				g.drawString("" + fourWaysInsaneScore, 478, 350);
			}
			if (state == STATE.classicEasy2) {
				g.drawString("" + classicEasyScore, 478, 350);
			}
			if (state == STATE.classicMedium2) {
				g.drawString("" + classicMediumScore, 478, 350);
			}
			if (state == STATE.classicHard2) {
				g.drawString("" + classicHardScore, 478, 350);
			}
			if (state == STATE.classicInsane2) {
				g.drawString("" + classicInsaneScore, 478, 350);
			}
			if (state == STATE.storyGame2) {
				g.drawString("" + storyScore, 478, 350);
			}
			g.drawImage(playAgain, 34, 420, null);
		}
		if (state == STATE.classicChoices || state == STATE.fourWaysChoices) {
			g.setColor(Color.black);
			g.fillRect(0, 0, 600, 600);
			if (border) {
				g.setColor(Color.RED);
				g.fillRect(0, 0, 10, 600);
				g.setColor(Color.green);
				g.fillRect(0, 561, 600, 10);
				g.setColor(Color.ORANGE);
				g.fillRect(584, 0, 10, 600);
				g.setColor(Color.blue);
				g.fillRect(0, 0, 600, 10);
				g.setColor(Color.red);
				g.fillRect(0, 0, 10, 10);
			}
			g.drawImage(easy, 208, 30, null);
			g.drawImage(medium, 160, 165, null);
			g.drawImage(hard, 204, 300, null);
			g.drawImage(insane, 181, 435, null);
			g.drawImage(exit, 20, 20, 78, 44, null);
		}
		if (state == STATE.classicStarterEasy || state == STATE.classicStarterMedium
				|| state == STATE.classicStarterHard || state == STATE.fourWaysStarterHard
				|| state == STATE.fourWaysStarterEasy || state == STATE.fourWaysStarterMedium
				|| state == STATE.storyGameStarter || state == STATE.classicStarterInsane
				|| state == STATE.fourWaysStarterInsane) {
			g.setColor(Color.black);
			g.fillRect(0, 0, 600, 600);
			if (border) {
				g.setColor(Color.RED);
				g.fillRect(0, 0, 10, 600);
				g.setColor(Color.green);
				g.fillRect(0, 561, 600, 10);
				g.setColor(Color.ORANGE);
				g.fillRect(584, 0, 10, 600);
				g.setColor(Color.blue);
				g.fillRect(0, 0, 600, 10);
				g.setColor(Color.red);
				g.fillRect(0, 0, 10, 10);
			}
			g.drawImage(gameStartImage, 52, 227, null);
		}
	}

	// the keyPressed method uses keycodes received from user input to determine
	// if a certain action should be done.
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_0) {
			state = STATE.boxGameState;
			speed = 3;
			addLongOrangeColumn();
			addLongBlueColumn();
			addLongRedColumn();
			addLongGreenColumn();
			player.x = 270;
			player.y = 270;
		}
		if (state == STATE.fourWaysEasy || state == STATE.fourWaysMedium || state == STATE.fourWaysHard
				|| state == STATE.classicEasy || state == STATE.classicMedium || state == STATE.classicHard
				|| state == STATE.classicInsane || state == STATE.fourWaysInsane || state == STATE.storyGame
				|| state == STATE.boxGameState) {
			if (control) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					if (player.x >= 30) {
						player.x -= 30;
					}
					renderer.repaint();
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if (player.x <= 560) {
						player.x += 30;
					}
					renderer.repaint();
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					if (player.y <= 530) {
						player.y += 30;
					}
					renderer.repaint();
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					if (player.y >= 30) {
						player.y -= 30;
					}
					renderer.repaint();
				}
			}
			if (!control) {
				if (e.getKeyCode() == KeyEvent.VK_A) {
					if (player.x >= 30) {
						player.x -= 30;
					}
					renderer.repaint();
				}
				if (e.getKeyCode() == KeyEvent.VK_D) {
					if (player.x <= 560) {
						player.x += 30;
					}
					renderer.repaint();
				}
				if (e.getKeyCode() == KeyEvent.VK_S) {
					if (player.y <= 530) {
						player.y += 30;
					}
					renderer.repaint();
				}
				if (e.getKeyCode() == KeyEvent.VK_W) {
					if (player.y >= 30) {
						player.y -= 30;
					}
					renderer.repaint();
				}
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_F) {
			if (state == STATE.storyGameStarter || state == STATE.storyGame2) {
				r1 = Math.random();
				r2 = Math.random();
				state = STATE.storyGame;
				score = 0;
				speed = 5;
				if (whiteBox.size() == 1) {
					whiteBox.remove(0);
					redBox.remove(0);
					blueBox.remove(0);
					orangeBox.remove(0);
					greenBox.remove(0);
				}
				addWhiteColumn();
				addRedColumn();
				addBlueColumn();
				addgreenColumn();
				addOrangeColumn();
				player.x = 300;
				player.y = 300;
			}
			if (state == STATE.fourWaysStarterEasy || state == STATE.fourWaysEasy2) {
				speed = 5;
				r1 = Math.random();
				r2 = Math.random();
				state = STATE.fourWaysEasy;
				score = 0;
				if (whiteBox.size() == 1) {
					whiteBox.remove(0);
					redBox.remove(0);
					blueBox.remove(0);
					orangeBox.remove(0);
					greenBox.remove(0);
				}
				addWhiteColumn();
				addRedColumn();
				addBlueColumn();
				addgreenColumn();
				addOrangeColumn();
				player.x = 300;
				player.y = 300;
			}
			if (state == STATE.fourWaysStarterMedium || state == STATE.fourWaysMedium2) {
				speed = 6;
				r1 = Math.random();
				r2 = Math.random();
				state = STATE.fourWaysMedium;
				score = 0;
				if (whiteBox.size() == 1) {
					whiteBox.remove(0);
					redBox.remove(0);
					blueBox.remove(0);
					orangeBox.remove(0);
					greenBox.remove(0);
				}
				addWhiteColumn();
				addRedColumn();
				addBlueColumn();
				addgreenColumn();
				addOrangeColumn();
				player.x = 300;
				player.y = 300;
			}
			if (state == STATE.fourWaysStarterHard || state == STATE.fourWaysHard2) {
				speed = 7;
				r1 = Math.random();
				r2 = Math.random();
				state = STATE.fourWaysHard;
				score = 0;
				if (whiteBox.size() == 1) {
					whiteBox.remove(0);
					redBox.remove(0);
					blueBox.remove(0);
					orangeBox.remove(0);
					greenBox.remove(0);
				}
				addWhiteColumn();
				addRedColumn();
				addBlueColumn();
				addgreenColumn();
				addOrangeColumn();
				player.x = 300;
				player.y = 300;
			}
			if (state == STATE.fourWaysStarterInsane || state == STATE.fourWaysInsane2) {
				speed = 8;
				r1 = Math.random();
				r2 = Math.random();
				state = STATE.fourWaysInsane;
				score = 0;
				if (whiteBox.size() == 1) {
					whiteBox.remove(0);
					redBox.remove(0);
					blueBox.remove(0);
					orangeBox.remove(0);
					greenBox.remove(0);
				}
				addWhiteColumn();
				addRedColumn();
				addBlueColumn();
				addgreenColumn();
				addOrangeColumn();
				player.x = 300;
				player.y = 300;
			}
			if (state == STATE.classicStarterEasy || state == STATE.classicEasy2) {
				speed = 5;
				r1 = Math.random();
				r2 = Math.random();
				state = STATE.classicEasy;
				score = 0;
				if (whiteBox.size() == 1) {
					whiteBox.remove(0);
					redBox.remove(0);
					blueBox.remove(0);
					orangeBox.remove(0);
					greenBox.remove(0);
				}
				addWhiteColumn();
				addRedColumn();
				addBlueColumn();
				addgreenColumn();
				addOrangeColumn();
				player.x = 300;
				player.y = 300;
			}
			if (state == STATE.classicStarterMedium || state == STATE.classicMedium2) {
				speed = 6;
				r1 = Math.random();
				r2 = Math.random();
				state = STATE.classicMedium;
				score = 0;
				if (whiteBox.size() == 1) {
					whiteBox.remove(0);
					redBox.remove(0);
					blueBox.remove(0);
					orangeBox.remove(0);
					greenBox.remove(0);
				}
				addWhiteColumn();
				addRedColumn();
				addBlueColumn();
				addgreenColumn();
				addOrangeColumn();
				player.x = 300;
				player.y = 300;
			}
			if (state == STATE.classicStarterHard || state == STATE.classicHard2) {
				speed = 7;
				r1 = Math.random();
				r2 = Math.random();
				state = STATE.classicHard;
				score = 0;
				if (whiteBox.size() == 1) {
					whiteBox.remove(0);
					redBox.remove(0);
					blueBox.remove(0);
					orangeBox.remove(0);
					greenBox.remove(0);
				}
				addWhiteColumn();
				addRedColumn();
				addBlueColumn();
				addgreenColumn();
				addOrangeColumn();
				player.x = 300;
				player.y = 300;
			}
			if (state == STATE.classicStarterInsane || state == STATE.classicInsane2) {
				speed = 8;
				r1 = Math.random();
				r2 = Math.random();
				state = STATE.classicInsane;
				score = 0;
				if (whiteBox.size() == 1) {
					whiteBox.remove(0);
					redBox.remove(0);
					blueBox.remove(0);
					orangeBox.remove(0);
					greenBox.remove(0);
				}
				addWhiteColumn();
				addRedColumn();
				addBlueColumn();
				addgreenColumn();
				addOrangeColumn();
				player.x = 300;
				player.y = 300;
			}
		}
	}

	// The mousePressed method gets the location of where on screen the mouse
	// was pressed, and if it is in the appropriate location of a button during
	// the appropriate game state, an action will be performed.
	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		System.out.println(mx + " " + my);
		if (mx <= 495 && mx >= 112 && my <= 172 && my >= 76) {
			if (state == STATE.gameModes) {
				state = STATE.storyGameStarter;
			}
		}
		if (mx <= 393 && mx >= 212 && my <= 153 && my >= 56) {
			if (state == STATE.classicChoices) {
				state = STATE.classicStarterEasy;
			}
			if (state == STATE.fourWaysChoices) {
				state = STATE.fourWaysStarterEasy;
			}
		}
		if (mx <= 441 && mx >= 164 && my <= 271 && my >= 192) {
			if (state == STATE.classicChoices) {
				state = STATE.classicStarterMedium;
			}
			if (state == STATE.fourWaysChoices) {
				state = STATE.fourWaysStarterMedium;
			}
		}
		if (mx <= 396 && mx >= 208 && my <= 412 && my >= 325) {
			if (state == STATE.classicChoices) {
				state = STATE.classicStarterHard;
			}
			if (state == STATE.fourWaysChoices) {
				state = STATE.fourWaysStarterHard;
			}
		}
		if (mx <= 419 && mx >= 184 && my <= 546 && my >= 461) {
			if (state == STATE.classicChoices) {
				state = STATE.classicStarterInsane;
			}
			if (state == STATE.fourWaysChoices) {
				state = STATE.fourWaysStarterInsane;
			}
		}
		if (mx <= 408 && mx >= 192 && my <= 524 && my >= 482) {
			if (state == STATE.mainMenu) {
				state = STATE.gameModes;
			}
		}
		if (mx <= 375 && mx >= 231 && my <= 463 && my >= 417) {
			if (state == STATE.mainMenu) {
				state = STATE.settings;
			}
		}
		if (mx <= 448 && mx >= 311 && my <= 269 && my >= 184) {
			if (state == STATE.settings) {
				border = false;
			}
		}
		if (mx <= 273 && mx >= 157 && my <= 269 && my >= 184) {
			if (state == STATE.settings) {
				border = true;
			}
		}
		if (mx <= 263 && mx >= 55 && my <= 511 && my >= 427) {
			if (state == STATE.settings) {
				control = false;
			}
		}
		if (mx <= 550 && mx >= 295 && my <= 511 && my >= 427) {
			if (state == STATE.settings) {
				control = true;
			}
		}
		if (mx <= 100 && mx >= 25 && my <= 88 && my >= 47) {
			if (state == STATE.settings || state == STATE.gameModes || state == STATE.classicEasy2
					|| state == STATE.classicMedium2 || state == STATE.classicHard2 || state == STATE.fourWaysEasy2
					|| state == STATE.fourWaysMedium2 || state == STATE.fourWaysHard2 || state == STATE.classicInsane2
					|| state == STATE.storyGame2 || state == STATE.fourWaysInsane2) {
				state = STATE.mainMenu;
			}
		}
		if (mx <= 422 && mx >= 180 && my <= 281 && my >= 197) {
			if (state == STATE.gameModes) {
				state = STATE.classicChoices;
			}
		}
		if (mx <= 100 && mx >= 25 && my <= 88 && my >= 47) {
			if (state == STATE.classicChoices || state == STATE.fourWaysChoices) {
				state = STATE.gameModes;
			}
		}
		if (mx <= 524 && mx >= 80 && my <= 402 && my >= 316) {
			if (state == STATE.gameModes) {
				state = STATE.fourWaysChoices;
			}
		}
	}

	// The following five methods draw the 5 colored rectangles in their
	// appropriate locations.
	public void paintWhiteColumn(Graphics g, Rectangle white) {
		g.setColor(Color.white);
		g.fillRect(white.x, white.y, white.width, white.height);
	}

	public void paintOrangeColumn(Graphics g, Rectangle orange) {
		g.setColor(Color.orange);
		g.fillRect(orange.x, orange.y, orange.width, orange.height);
	}

	public void paintRedColumn(Graphics g, Rectangle red) {
		g.setColor(Color.red);
		g.fillRect(red.x, red.y, red.width, red.height);
	}

	public void paintBlueColumn(Graphics g, Rectangle blue) {
		g.setColor(Color.blue);
		g.fillRect(blue.x, blue.y, blue.width, blue.height);
	}

	public void paintgreenColumn(Graphics g, Rectangle green) {
		g.setColor(Color.green);
		g.fillRect(green.x, green.y, green.width, green.height);
	}

	public void paintLongOrangeColumn(Graphics g, Rectangle orange) {
		g.setColor(Color.orange);
		g.fillRect(orange.x, orange.y, orange.width, orange.height);
	}

	public void paintLongBlueColumn(Graphics g, Rectangle blue) {
		g.setColor(Color.blue);
		g.fillRect(blue.x, blue.y, blue.width, blue.height);
	}

	public void paintLongRedColumn(Graphics g, Rectangle red) {
		g.setColor(Color.red);
		g.fillRect(red.x, red.y, red.width, red.height);
	}

	public void paintLongGreenColumn(Graphics g, Rectangle green) {
		g.setColor(Color.green);
		g.fillRect(green.x, green.y, green.width, green.height);
	}

	public void addLongOrangeColumn() {
		colorPlayer = Math.random();
		orangeLocation = Math.random();
		if (orangeLocation < .25) {
			orangeLongBox.add(new Rectangle(20, 560, 560, 20));
		} else if (orangeLocation >= .25 && orangeLocation < .5) {
			orangeLongBox.add(new Rectangle(0, 0, 560, 20));
		} else if (orangeLocation >= .5 && orangeLocation < .75) {
			orangeLongBox.add(new Rectangle(0, 20, 20, 560));
		} else {
			orangeLongBox.add(new Rectangle(560, 0, 20, 560));
		}
	}

	public void addLongBlueColumn() {
		if (orangeLocation < .25) {
			blueLongBox.add(new Rectangle(560, 0, 20, 560));
		} else if (orangeLocation >= .25 && orangeLocation < .5) {
			blueLongBox.add(new Rectangle(20, 560, 560, 20));
		} else if (orangeLocation >= .5 && orangeLocation < .75) {
			blueLongBox.add(new Rectangle(0, 0, 560, 20));
		} else {
			blueLongBox.add(new Rectangle(0, 20, 20, 560));
		}
	}

	public void addLongRedColumn() {
		if (orangeLocation < .25) {
			redLongBox.add(new Rectangle(0, 0, 20, 580));
		} else if (orangeLocation >= .25 && orangeLocation < .5) {
			redLongBox.add(new Rectangle(560, 0, 20, 560));
		} else if (orangeLocation >= .5 && orangeLocation < .75) {
			redLongBox.add(new Rectangle(20, 560, 560, 20));
		} else {
			redLongBox.add(new Rectangle(0, 0, 560, 20));
		}
	}

	public void addLongGreenColumn() {
		if (orangeLocation < .25) {
			greenLongBox.add(new Rectangle(0, 0, 560, 20));
		} else if (orangeLocation >= .25 && orangeLocation < .5) {
			greenLongBox.add(new Rectangle(0, 0, 20, 580));
		} else if (orangeLocation >= .5 && orangeLocation < .75) {
			greenLongBox.add(new Rectangle(560, 0, 20, 560));
		} else {
			greenLongBox.add(new Rectangle(20, 560, 560, 20));
		}
	}

	public void boxGame() {
		if (orangeLocation < .25) {
			for (int i = 0; i < orangeLongBox.size(); i++) {
				Rectangle rect = orangeLongBox.get(i);
				Rectangle rectb = blueLongBox.get(i);
				Rectangle rectr = redLongBox.get(i);
				Rectangle rectg = greenLongBox.get(i);
				rect.y -= speed;
				rectb.x -= speed;
				rectr.x += speed;
				rectg.y += speed;

				if (Player == Color.orange && player.y - rect.y <= 20 && player.y - rect.y > -20) {
					if (!(player.x <= rect.x + rect.width) || !(player.x >= rect.x)) {

						System.out.println(1);
					}
				}

				orangeLongBox.get(i).width -= 6;
				blueLongBox.get(i).height -= 6;
				redLongBox.get(i).height -= 6;
				greenLongBox.get(i).width -= 6;
				orangeLongBox.get(i).x += 3;
				blueLongBox.get(i).y += 3;
				redLongBox.get(i).y += 3;
				greenLongBox.get(i).x += 3;

				if (orangeLongBox.get(i).y <= 280) {
					orangeLongBox.remove(i);
					blueLongBox.remove(i);
					redLongBox.remove(i);
					greenLongBox.remove(i);
					addLongOrangeColumn();
					addLongBlueColumn();
					addLongRedColumn();
					addLongGreenColumn();
				}
			}
		} else if (orangeLocation >= .25 && orangeLocation < .5) {
			for (int i = 0; i < orangeLongBox.size(); i++) {
				Rectangle rect = orangeLongBox.get(i);
				Rectangle rectb = blueLongBox.get(i);
				Rectangle rectr = redLongBox.get(i);
				Rectangle rectg = greenLongBox.get(i);
				rect.y += speed;
				rectb.y -= speed;
				rectr.x -= speed;
				rectg.x += speed;

				if (Player == Color.orange && player.y - rect.y <= 20 && player.y - rect.y > -20) {
					if (!(player.x <= rect.x + rect.width) || !(player.x >= rect.x)) {

						System.out.println(1);
					}
				}

				orangeLongBox.get(i).width -= 6;
				blueLongBox.get(i).width -= 6;
				redLongBox.get(i).height -= 6;
				greenLongBox.get(i).height -= 6;
				orangeLongBox.get(i).x += 3;
				blueLongBox.get(i).x += 3;
				redLongBox.get(i).y += 3;
				greenLongBox.get(i).y += 3;

				if (blueLongBox.get(i).y <= 280) {
					orangeLongBox.remove(i);
					blueLongBox.remove(i);
					redLongBox.remove(i);
					greenLongBox.remove(i);
					addLongOrangeColumn();
					addLongBlueColumn();
					addLongRedColumn();
					addLongGreenColumn();
				}
			}
		} else if (orangeLocation >= .5 && orangeLocation < .75) {
			for (int i = 0; i < orangeLongBox.size(); i++) {
				Rectangle rect = orangeLongBox.get(i);
				Rectangle rectb = blueLongBox.get(i);
				Rectangle rectr = redLongBox.get(i);
				Rectangle rectg = greenLongBox.get(i);
				rect.x += speed;
				rectb.y += speed;
				rectr.y -= speed;
				rectg.x -= speed;

				if (Player == Color.orange && player.y - rect.y <= 20 && player.y - rect.y > -20) {
					if (!(player.x <= rect.x + rect.width) || !(player.x >= rect.x)) {

						System.out.println(1);
					}
				}

				orangeLongBox.get(i).height -= 6;
				blueLongBox.get(i).width -= 6;
				redLongBox.get(i).width -= 6;
				greenLongBox.get(i).height -= 6;
				orangeLongBox.get(i).y += 3;
				blueLongBox.get(i).x += 3;
				redLongBox.get(i).x += 3;
				greenLongBox.get(i).y += 3;

				if (redLongBox.get(i).y <= 280) {
					orangeLongBox.remove(i);
					blueLongBox.remove(i);
					redLongBox.remove(i);
					greenLongBox.remove(i);
					addLongOrangeColumn();
					addLongBlueColumn();
					addLongRedColumn();
					addLongGreenColumn();
				}
			}
		} else {
			for (int i = 0; i < orangeLongBox.size(); i++) {
				Rectangle rect = orangeLongBox.get(i);
				Rectangle rectb = blueLongBox.get(i);
				Rectangle rectr = redLongBox.get(i);
				Rectangle rectg = greenLongBox.get(i);
				rect.x -= speed;
				rectb.x += speed;
				rectr.y += speed;
				rectg.y -= speed;

				if (Player == Color.orange && player.y - rect.y <= 20 && player.y - rect.y > -20) {
					if (!(player.x <= rect.x + rect.width) || !(player.x >= rect.x)) {

						System.out.println(1);
					}
				}

				orangeLongBox.get(i).height -= 6;
				blueLongBox.get(i).height -= 6;
				redLongBox.get(i).width -= 6;
				greenLongBox.get(i).width -= 6;
				orangeLongBox.get(i).y += 3;
				blueLongBox.get(i).y += 3;
				redLongBox.get(i).x += 3;
				greenLongBox.get(i).x += 3;

				if (greenLongBox.get(i).y <= 280) {
					orangeLongBox.remove(i);
					blueLongBox.remove(i);
					redLongBox.remove(i);
					greenLongBox.remove(i);
					addLongOrangeColumn();
					addLongBlueColumn();
					addLongRedColumn();
					addLongGreenColumn();
				}
			}
		}
	}

	// Creates a new orange box in a location that is based off of randomly
	// generated numbers.
	public void addOrangeColumn() {
		if (state == STATE.classicEasy || state == STATE.classicMedium || state == STATE.classicHard
				|| state == STATE.classicInsane || state == STATE.storyGame) {
			if (XorY < .5) {
				if (boxLocation <= .2) {
					orangeBox.add(new Rectangle(600, 120, 20, 120));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					orangeBox.add(new Rectangle(600, 240, 20, 120));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					orangeBox.add(new Rectangle(600, 360, 20, 120));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					orangeBox.add(new Rectangle(600, 480, 20, 120));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					orangeBox.add(new Rectangle(600, 0, 20, 120));
				}
			}
			if (XorY >= .5) {
				if (boxLocation <= .2) {
					orangeBox.add(new Rectangle(120, 600, 120, 20));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					orangeBox.add(new Rectangle(240, 600, 120, 20));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					orangeBox.add(new Rectangle(360, 600, 120, 20));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					orangeBox.add(new Rectangle(480, 600, 120, 20));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					orangeBox.add(new Rectangle(0, 600, 120, 20));
				}
			}
		}
		if (state == STATE.fourWaysEasy || state == STATE.fourWaysMedium || state == STATE.fourWaysHard
				|| state == STATE.fourWaysInsane) {
			if (XorY < .25) {
				if (boxLocation <= .2) {
					orangeBox.add(new Rectangle(600, 120, 20, 120));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					orangeBox.add(new Rectangle(600, 240, 20, 120));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					orangeBox.add(new Rectangle(600, 360, 20, 120));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					orangeBox.add(new Rectangle(600, 480, 20, 120));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					orangeBox.add(new Rectangle(600, 0, 20, 120));
				}
			}
			if (XorY >= .25 && XorY < .5) {
				if (boxLocation <= .2) {
					orangeBox.add(new Rectangle(120, 600, 120, 20));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					orangeBox.add(new Rectangle(240, 600, 120, 20));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					orangeBox.add(new Rectangle(360, 600, 120, 20));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					orangeBox.add(new Rectangle(480, 600, 120, 20));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					orangeBox.add(new Rectangle(0, 600, 120, 20));
				}
			}
			if (XorY >= .5 && XorY < .75) {
				if (boxLocation <= .2) {
					orangeBox.add(new Rectangle(120, -30, 120, 20));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					orangeBox.add(new Rectangle(240, -30, 120, 20));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					orangeBox.add(new Rectangle(360, -30, 120, 20));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					orangeBox.add(new Rectangle(480, -30, 120, 20));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					orangeBox.add(new Rectangle(0, -30, 120, 20));
				}
			}
			if (XorY >= .75 && XorY < 1) {
				if (boxLocation <= .2) {
					orangeBox.add(new Rectangle(-30, 120, 20, 120));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					orangeBox.add(new Rectangle(-30, 240, 20, 120));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					orangeBox.add(new Rectangle(-30, 360, 20, 120));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					orangeBox.add(new Rectangle(-30, 480, 20, 120));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					orangeBox.add(new Rectangle(-30, 0, 20, 120));
				}
			}
		}
	}

	// Creates a new green box in a location that is based off of randomly
	// generated numbers.
	public void addgreenColumn() {
		if (state == STATE.classicEasy || state == STATE.classicMedium || state == STATE.classicHard
				|| state == STATE.classicInsane || state == STATE.storyGame) {
			colorPlayer = rand.nextDouble();
			if (XorY < .5) {
				if (boxLocation <= .2) {
					greenBox.add(new Rectangle(600, 240, 20, 120));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					greenBox.add(new Rectangle(600, 360, 20, 120));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					greenBox.add(new Rectangle(600, 480, 20, 120));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					greenBox.add(new Rectangle(600, 0, 20, 120));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					greenBox.add(new Rectangle(600, 120, 20, 120));
				}
			}
			if (XorY >= .5) {
				if (boxLocation <= .2) {
					greenBox.add(new Rectangle(240, 600, 120, 20));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					greenBox.add(new Rectangle(360, 600, 120, 20));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					greenBox.add(new Rectangle(480, 600, 120, 20));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					greenBox.add(new Rectangle(0, 600, 120, 20));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					greenBox.add(new Rectangle(120, 600, 120, 20));
				}
			}
		}
		if (state == STATE.fourWaysEasy || state == STATE.fourWaysMedium || state == STATE.fourWaysHard
				|| state == STATE.fourWaysInsane) {
			colorPlayer = rand.nextDouble();
			if (XorY < .25) {
				if (boxLocation <= .2) {
					greenBox.add(new Rectangle(600, 240, 20, 120));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					greenBox.add(new Rectangle(600, 360, 20, 120));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					greenBox.add(new Rectangle(600, 480, 20, 120));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					greenBox.add(new Rectangle(600, 0, 20, 120));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					greenBox.add(new Rectangle(600, 120, 20, 120));
				}
			}
			if (XorY >= .25 && XorY < .5) {
				if (boxLocation <= .2) {
					greenBox.add(new Rectangle(240, 600, 120, 20));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					greenBox.add(new Rectangle(360, 600, 120, 20));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					greenBox.add(new Rectangle(480, 600, 120, 20));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					greenBox.add(new Rectangle(0, 600, 120, 20));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					greenBox.add(new Rectangle(120, 600, 120, 20));
				}
			}
			if (XorY >= .5 && XorY < .75) {
				if (boxLocation <= .2) {
					greenBox.add(new Rectangle(240, -30, 120, 20));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					greenBox.add(new Rectangle(360, -30, 120, 20));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					greenBox.add(new Rectangle(480, -30, 120, 20));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					greenBox.add(new Rectangle(0, -30, 120, 20));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					greenBox.add(new Rectangle(120, -30, 120, 20));
				}
			}
			if (XorY >= .75 && XorY < 1) {
				if (boxLocation <= .2) {
					greenBox.add(new Rectangle(-30, 240, 20, 120));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					greenBox.add(new Rectangle(-30, 360, 20, 120));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					greenBox.add(new Rectangle(-30, 480, 20, 120));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					greenBox.add(new Rectangle(-30, 0, 20, 120));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					greenBox.add(new Rectangle(-30, 120, 20, 120));
				}
			}
		}
	}

	// Creates a new blue box in a location that is based off of randomly
	// generated numbers.
	public void addBlueColumn() {
		if (state == STATE.classicEasy || state == STATE.classicMedium || state == STATE.classicHard
				|| state == STATE.classicInsane || state == STATE.storyGame) {
			if (XorY < .5) {
				if (boxLocation <= .2) {
					blueBox.add(new Rectangle(600, 360, 20, 120));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					blueBox.add(new Rectangle(600, 480, 20, 120));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					blueBox.add(new Rectangle(600, 0, 20, 120));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					blueBox.add(new Rectangle(600, 120, 20, 120));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					blueBox.add(new Rectangle(600, 240, 20, 120));
				}
			}
			if (XorY >= .5) {
				if (boxLocation <= .2) {
					blueBox.add(new Rectangle(360, 600, 120, 20));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					blueBox.add(new Rectangle(480, 600, 120, 20));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					blueBox.add(new Rectangle(0, 600, 120, 20));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					blueBox.add(new Rectangle(120, 600, 120, 20));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					blueBox.add(new Rectangle(240, 600, 120, 20));
				}
			}
		}
		if (state == STATE.fourWaysEasy || state == STATE.fourWaysMedium || state == STATE.fourWaysHard
				|| state == STATE.fourWaysInsane) {
			if (XorY < .25) {
				if (boxLocation <= .2) {
					blueBox.add(new Rectangle(600, 360, 20, 120));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					blueBox.add(new Rectangle(600, 480, 20, 120));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					blueBox.add(new Rectangle(600, 0, 20, 120));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					blueBox.add(new Rectangle(600, 120, 20, 120));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					blueBox.add(new Rectangle(600, 240, 20, 120));
				}
			}
			if (XorY >= .25 && XorY < .5) {
				if (boxLocation <= .2) {
					blueBox.add(new Rectangle(360, 600, 120, 20));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					blueBox.add(new Rectangle(480, 600, 120, 20));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					blueBox.add(new Rectangle(0, 600, 120, 20));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					blueBox.add(new Rectangle(120, 600, 120, 20));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					blueBox.add(new Rectangle(240, 600, 120, 20));
				}
			}
			if (XorY >= .5 && XorY < .75) {
				if (boxLocation <= .2) {
					blueBox.add(new Rectangle(360, -30, 120, 20));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					blueBox.add(new Rectangle(480, -30, 120, 20));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					blueBox.add(new Rectangle(0, -30, 120, 20));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					blueBox.add(new Rectangle(120, -30, 120, 20));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					blueBox.add(new Rectangle(240, -30, 120, 20));
				}
			}
			if (XorY >= .75 && XorY < 1) {
				if (boxLocation <= .2) {
					blueBox.add(new Rectangle(-30, 360, 20, 120));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					blueBox.add(new Rectangle(-30, 480, 20, 120));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					blueBox.add(new Rectangle(-30, 0, 20, 120));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					blueBox.add(new Rectangle(-30, 120, 20, 120));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					blueBox.add(new Rectangle(-30, 240, 20, 120));
				}
			}
		}
	}

	// Creates a new red box in a location that is based off of randomly
	// generated numbers.
	public void addRedColumn() {
		if (state == STATE.classicEasy || state == STATE.classicMedium || state == STATE.classicHard
				|| state == STATE.classicInsane || state == STATE.storyGame) {
			if (XorY < .5) {
				if (boxLocation <= .2) {
					redBox.add(new Rectangle(600, 480, 20, 120));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					redBox.add(new Rectangle(600, 0, 20, 120));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					redBox.add(new Rectangle(600, 120, 20, 120));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					redBox.add(new Rectangle(600, 240, 20, 120));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					redBox.add(new Rectangle(600, 360, 20, 120));
				}
			}
			if (XorY >= .5) {
				if (boxLocation <= .2) {
					redBox.add(new Rectangle(480, 600, 120, 20));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					redBox.add(new Rectangle(0, 600, 120, 20));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					redBox.add(new Rectangle(120, 600, 120, 20));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					redBox.add(new Rectangle(240, 600, 120, 20));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					redBox.add(new Rectangle(360, 600, 120, 20));
				}
			}
		}
		if (state == STATE.fourWaysEasy || state == STATE.fourWaysMedium || state == STATE.fourWaysHard
				|| state == STATE.fourWaysInsane) {
			if (XorY < .25) {
				if (boxLocation <= .2) {
					redBox.add(new Rectangle(600, 480, 20, 120));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					redBox.add(new Rectangle(600, 0, 20, 120));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					redBox.add(new Rectangle(600, 120, 20, 120));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					redBox.add(new Rectangle(600, 240, 20, 120));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					redBox.add(new Rectangle(600, 360, 20, 120));
				}
			}
			if (XorY >= .25 && XorY < .5) {
				if (boxLocation <= .2) {
					redBox.add(new Rectangle(480, 600, 120, 20));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					redBox.add(new Rectangle(0, 600, 120, 20));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					redBox.add(new Rectangle(120, 600, 120, 20));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					redBox.add(new Rectangle(240, 600, 120, 20));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					redBox.add(new Rectangle(360, 600, 120, 20));
				}
			}
			if (XorY >= .5 && XorY < .75) {
				if (boxLocation <= .2) {
					redBox.add(new Rectangle(480, -30, 120, 20));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					redBox.add(new Rectangle(0, -30, 120, 20));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					redBox.add(new Rectangle(120, -30, 120, 20));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					redBox.add(new Rectangle(240, -30, 120, 20));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					redBox.add(new Rectangle(360, -30, 120, 20));
				}
			}
			if (XorY >= .75 && XorY < 1) {
				if (boxLocation <= .2) {
					redBox.add(new Rectangle(-30, 480, 20, 120));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					redBox.add(new Rectangle(-30, 0, 20, 120));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					redBox.add(new Rectangle(-30, 120, 20, 120));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					redBox.add(new Rectangle(-30, 240, 20, 120));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					redBox.add(new Rectangle(-30, 360, 20, 120));
				}
			}
		}
	}

	// Creates a new white box in a location that is based off of randomly
	// generated numbers.
	public void addWhiteColumn() {
		XorY = rand.nextDouble();
		boxLocation = rand.nextDouble();
		if (state == STATE.classicEasy || state == STATE.classicMedium || state == STATE.classicHard
				|| state == STATE.classicInsane || state == STATE.storyGame) {
			if (XorY < .5) {
				if (boxLocation <= .2) {
					whiteBox.add(new Rectangle(600, 0, 20, 120));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					whiteBox.add(new Rectangle(600, 120, 20, 120));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					whiteBox.add(new Rectangle(600, 240, 20, 120));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					whiteBox.add(new Rectangle(600, 360, 20, 120));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					whiteBox.add(new Rectangle(600, 480, 20, 120));
				}
			}
			if (XorY >= .5) {
				if (boxLocation <= .2) {
					whiteBox.add(new Rectangle(0, 600, 120, 20));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					whiteBox.add(new Rectangle(120, 600, 120, 20));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					whiteBox.add(new Rectangle(240, 600, 120, 20));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					whiteBox.add(new Rectangle(360, 600, 120, 20));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					whiteBox.add(new Rectangle(480, 600, 120, 20));
				}
			}
		}
		if (state == STATE.fourWaysEasy || state == STATE.fourWaysMedium || state == STATE.fourWaysHard
				|| state == STATE.fourWaysInsane) {
			if (XorY < .25) {
				if (boxLocation <= .2) {
					whiteBox.add(new Rectangle(600, 0, 20, 120));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					whiteBox.add(new Rectangle(600, 120, 20, 120));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					whiteBox.add(new Rectangle(600, 240, 20, 120));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					whiteBox.add(new Rectangle(600, 360, 20, 120));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					whiteBox.add(new Rectangle(600, 480, 20, 120));
				}
			}
			if (XorY >= .25 && XorY < .5) {
				if (boxLocation <= .2) {
					whiteBox.add(new Rectangle(0, 600, 120, 20));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					whiteBox.add(new Rectangle(120, 600, 120, 20));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					whiteBox.add(new Rectangle(240, 600, 120, 20));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					whiteBox.add(new Rectangle(360, 600, 120, 20));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					whiteBox.add(new Rectangle(480, 600, 120, 20));
				}
			}
			if (XorY >= .5 && XorY < .75) {
				if (boxLocation <= .2) {
					whiteBox.add(new Rectangle(0, -30, 120, 20));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					whiteBox.add(new Rectangle(120, -30, 120, 20));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					whiteBox.add(new Rectangle(240, -30, 120, 20));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					whiteBox.add(new Rectangle(360, -30, 120, 20));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					whiteBox.add(new Rectangle(480, -30, 120, 20));
				}
			}
			if (XorY >= .75 && XorY < 1) {
				if (boxLocation <= .2) {
					whiteBox.add(new Rectangle(-30, 0, 20, 120));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					whiteBox.add(new Rectangle(-30, 120, 20, 120));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					whiteBox.add(new Rectangle(-30, 240, 20, 120));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					whiteBox.add(new Rectangle(-30, 360, 20, 120));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					whiteBox.add(new Rectangle(-30, 480, 20, 120));
				}
			}
		}
	}

	// code for the classic and story gamemodes that checks whether the player
	// is in a location that will cause the gamemode to end, moves the location
	// of the colored boxes, compares the current score to the highscore and
	// changes the highscore if need be, removes the row of colored boxes once
	// it reaches the end of the screen, and creates a new row.
	public void classic() {
		if (state == STATE.storyGame) {
			if (score == 0) {
				speed = 5;
			} else if (score == 8) {
				speed = 6;
			} else if (score == 16) {
				speed = 7;
			} else if (score == 24) {
				speed = 8;
			} else if (score == 32) {
				speed = 9;
			} else if (score == 40) {
				speed = 10;
			} else if (score == 48) {
				speed = 11;
			} else if (score == 56) {
				speed = 12;
			} else if (score == 64) {
				speed = 13;
			} else if (score == 72) {
				speed = 14;
			}
		}
		if (state == STATE.classicMedium || state == STATE.classicEasy || state == STATE.classicHard
				|| state == STATE.storyGame || state == STATE.classicInsane) {
			if (XorY < .5) {
				for (int i = 0; i < whiteBox.size(); i++) {
					Rectangle rect = whiteBox.get(i);
					Rectangle rectb = blueBox.get(i);
					Rectangle rectr = redBox.get(i);
					Rectangle recto = orangeBox.get(i);
					Rectangle rectp = greenBox.get(i);
					rect.x -= speed;
					rectb.x -= speed;
					rectr.x -= speed;
					recto.x -= speed;
					rectp.x -= speed;
					if (Player == Color.white && player.x - rect.x <= 20 && player.x - rect.x > -20) {
						if (!(player.y - rect.y <= 101) || !(player.y - rect.y >= 0)) {
							if (state == STATE.classicEasy) {
								state = STATE.classicEasy2;
							}
							if (state == STATE.classicMedium) {
								state = STATE.classicMedium2;
							}
							if (state == STATE.classicHard) {
								state = STATE.classicHard2;
							}
							if (state == STATE.classicInsane) {
								state = STATE.classicInsane2;
							}
							if (state == STATE.storyGame) {
								state = STATE.storyGame2;
							}
						}
					}
					if (Player == Color.red && player.x - rectr.x <= 20 && player.x - rectr.x > -20) {
						if (!(player.y - rectr.y <= 101) || !(player.y - rectr.y >= 0)) {
							if (state == STATE.classicEasy) {
								state = STATE.classicEasy2;
							}
							if (state == STATE.classicMedium) {
								state = STATE.classicMedium2;
							}
							if (state == STATE.classicHard) {
								state = STATE.classicHard2;
							}
							if (state == STATE.classicInsane) {
								state = STATE.classicInsane2;
							}
							if (state == STATE.storyGame) {
								state = STATE.storyGame2;
							}
						}
					}
					if (Player == Color.blue && player.x - rectb.x <= 20 && player.x - rectb.x > -20) {
						if (!(player.y - rectb.y <= 101) || !(player.y - rectb.y >= 0)) {
							if (state == STATE.classicEasy) {
								state = STATE.classicEasy2;
							}
							if (state == STATE.classicMedium) {
								state = STATE.classicMedium2;
							}
							if (state == STATE.classicHard) {
								state = STATE.classicHard2;
							}
							if (state == STATE.classicInsane) {
								state = STATE.classicInsane2;
							}
							if (state == STATE.storyGame) {
								state = STATE.storyGame2;
							}
						}
					}
					if (Player == Color.orange && player.x - recto.x <= 20 && player.x - recto.x > -20) {
						if (!(player.y - recto.y <= 101) || !(player.y - recto.y >= 0)) {
							if (state == STATE.classicEasy) {
								state = STATE.classicEasy2;
							}
							if (state == STATE.classicMedium) {
								state = STATE.classicMedium2;
							}
							if (state == STATE.classicHard) {
								state = STATE.classicHard2;
							}
							if (state == STATE.classicInsane) {
								state = STATE.classicInsane2;
							}
							if (state == STATE.storyGame) {
								state = STATE.storyGame2;
							}
						}
					}
					if (Player == Color.green && player.x - rectp.x <= 20 && player.x - rectp.x > -20) {
						if (!(player.y - rectp.y <= 101) || !(player.y - rectp.y >= 0)) {
							if (state == STATE.classicEasy) {
								state = STATE.classicEasy2;
							}
							if (state == STATE.classicMedium) {
								state = STATE.classicMedium2;
							}
							if (state == STATE.classicHard) {
								state = STATE.classicHard2;
							}
							if (state == STATE.classicInsane) {
								state = STATE.classicInsane2;
							}
							if (state == STATE.storyGame) {
								state = STATE.storyGame2;
							}
						}
					}
					if (rect.x <= 0 && (state == STATE.classicMedium || state == STATE.classicEasy
							|| state == STATE.classicHard || state == STATE.storyGame)) {
						if (whiteBox.size() >= 1) {
							whiteBox.remove(i);
							redBox.remove(i);
							orangeBox.remove(i);
							blueBox.remove(i);
							greenBox.remove(i);
						}
						addWhiteColumn();
						addBlueColumn();
						addRedColumn();
						addOrangeColumn();
						addgreenColumn();
						score++;
						if (state == STATE.classicEasy) {
							if (score > classicEasyScore) {
								classicEasyScore = score;
							}
						}
						if (state == STATE.classicMedium) {
							if (score > classicMediumScore) {
								classicMediumScore = score;
							}
						}
						if (state == STATE.classicHard) {
							if (score > classicHardScore) {
								classicHardScore = score;
							}
						}
						if (state == STATE.classicInsane) {
							if (score > classicInsaneScore) {
								classicInsaneScore = score;
							}
						}
						if (state == STATE.storyGame) {
							if (score > storyScore) {
								storyScore = score;
							}
						}
					}
				}
			}
			if (XorY >= .5) {
				for (int x = 0; x < whiteBox.size(); x++) {
					Rectangle rect = whiteBox.get(x);
					Rectangle rectb = blueBox.get(x);
					Rectangle rectr = redBox.get(x);
					Rectangle recto = orangeBox.get(x);
					Rectangle rectp = greenBox.get(x);
					rect.y -= speed;
					rectb.y -= speed;
					rectr.y -= speed;
					recto.y -= speed;
					rectp.y -= speed;
					if (Player == Color.white && rect.y - player.y <= 20 && rect.y - player.y >= -20) {
						if (!(player.x - rect.x <= 101) || !(player.x - rect.x >= 0)) {
							if (state == STATE.classicEasy) {
								state = STATE.classicEasy2;
							}
							if (state == STATE.classicMedium) {
								state = STATE.classicMedium2;
							}
							if (state == STATE.classicHard) {
								state = STATE.classicHard2;
							}
							if (state == STATE.classicInsane) {
								state = STATE.classicInsane2;
							}
							if (state == STATE.storyGame) {
								state = STATE.storyGame2;
							}
						}
					}
					if (Player == Color.blue && rectb.y - player.y <= 20 && rectb.y - player.y >= -20) {
						if (!(player.x - rectb.x <= 101) || !(player.x - rectb.x >= 0)) {
							if (state == STATE.classicEasy) {
								state = STATE.classicEasy2;
							}
							if (state == STATE.classicMedium) {
								state = STATE.classicMedium2;
							}
							if (state == STATE.classicHard) {
								state = STATE.classicHard2;
							}
							if (state == STATE.classicInsane) {
								state = STATE.classicInsane2;
							}
							if (state == STATE.storyGame) {
								state = STATE.storyGame2;
							}
						}
					}
					if (Player == Color.red && rectr.y - player.y <= 20 && rectr.y - player.y >= -20) {
						if (!(player.x - rectr.x <= 101) || !(player.x - rectr.x >= 0)) {
							if (state == STATE.classicEasy) {
								state = STATE.classicEasy2;
							}
							if (state == STATE.classicMedium) {
								state = STATE.classicMedium2;
							}
							if (state == STATE.classicHard) {
								state = STATE.classicHard2;
							}
							if (state == STATE.classicInsane) {
								state = STATE.classicInsane2;
							}
							if (state == STATE.storyGame) {
								state = STATE.storyGame2;
							}
						}
					}
					if (Player == Color.green && rectp.y - player.y <= 20 && rectp.y - player.y >= -20) {
						if (!(player.x - rectp.x <= 101) || !(player.x - rectp.x >= 0)) {
							if (state == STATE.classicEasy) {
								state = STATE.classicEasy2;
							}
							if (state == STATE.classicMedium) {
								state = STATE.classicMedium2;
							}
							if (state == STATE.classicHard) {
								state = STATE.classicHard2;
							}
							if (state == STATE.classicInsane) {
								state = STATE.classicInsane2;
							}
							if (state == STATE.storyGame) {
								state = STATE.storyGame2;
							}
						}
					}
					if (Player == Color.orange && recto.y - player.y <= 20 && recto.y - player.y >= -20) {
						if (!(player.x - recto.x <= 101) || !(player.x - recto.x >= 0)) {
							if (state == STATE.classicEasy) {
								state = STATE.classicEasy2;
							}
							if (state == STATE.classicMedium) {
								state = STATE.classicMedium2;
							}
							if (state == STATE.classicHard) {
								state = STATE.classicHard2;
							}
							if (state == STATE.classicInsane) {
								state = STATE.classicInsane2;
							}
							if (state == STATE.storyGame) {
								state = STATE.storyGame2;
							}
						}
					}
					if (rect.y <= 0 && (state == STATE.classicMedium || state == STATE.classicEasy
							|| state == STATE.classicHard || state == STATE.storyGame)) {
						if (whiteBox.size() >= 1) {
							whiteBox.remove(x);
							redBox.remove(x);
							orangeBox.remove(x);
							blueBox.remove(x);
							greenBox.remove(x);
						}
						addWhiteColumn();
						addBlueColumn();
						addRedColumn();
						addOrangeColumn();
						addgreenColumn();
						score++;
						if (state == STATE.classicEasy) {
							if (score > classicEasyScore) {
								classicEasyScore = score;
							}
						}
						if (state == STATE.classicMedium) {
							if (score > classicMediumScore) {
								classicMediumScore = score;
							}
						}
						if (state == STATE.classicHard) {
							if (score > classicHardScore) {
								classicHardScore = score;
							}
						}
						if (state == STATE.classicInsane) {
							if (score > classicInsaneScore) {
								classicInsaneScore = score;
							}
						}
						if (state == STATE.storyGame) {
							if (score > storyScore) {
								storyScore = score;
							}
						}
					}
				}
			}
		}
	}

	// Code does the same as in the classic method, but this code is written for
	// the all directions gamemode instead.
	public void fourWays() {
		if (state == STATE.fourWaysMedium || state == STATE.fourWaysEasy || state == STATE.fourWaysHard
				|| state == STATE.fourWaysInsane) {
			if (XorY < .25) {
				for (int i = 0; i < whiteBox.size(); i++) {
					Rectangle rect = whiteBox.get(i);
					Rectangle rectb = blueBox.get(i);
					Rectangle rectr = redBox.get(i);
					Rectangle recto = orangeBox.get(i);
					Rectangle rectp = greenBox.get(i);
					rect.x -= speed;
					rectb.x -= speed;
					rectr.x -= speed;
					recto.x -= speed;
					rectp.x -= speed;
					if (Player == Color.white && player.x - rect.x <= 20 && player.x - rect.x > -20) {
						if (!(player.y - rect.y <= 101) || !(player.y - rect.y >= 0)) {
							if (state == STATE.fourWaysEasy) {
								state = STATE.fourWaysEasy2;
							}
							if (state == STATE.fourWaysMedium) {
								state = STATE.fourWaysMedium2;
							}
							if (state == STATE.fourWaysHard) {
								state = STATE.fourWaysHard2;
							}
							if (state == STATE.fourWaysInsane) {
								state = STATE.fourWaysInsane2;
							}
						}
					}
					if (Player == Color.red && player.x - rectr.x <= 20 && player.x - rectr.x > -20) {
						if (!(player.y - rectr.y <= 101) || !(player.y - rectr.y >= 0)) {
							if (state == STATE.fourWaysEasy) {
								state = STATE.fourWaysEasy2;
							}
							if (state == STATE.fourWaysMedium) {
								state = STATE.fourWaysMedium2;
							}
							if (state == STATE.fourWaysHard) {
								state = STATE.fourWaysHard2;
							}
							if (state == STATE.fourWaysInsane) {
								state = STATE.fourWaysInsane2;
							}
						}
					}
					if (Player == Color.blue && player.x - rectb.x <= 20 && player.x - rectb.x > -20) {
						if (!(player.y - rectb.y <= 101) || !(player.y - rectb.y >= 0)) {
							if (state == STATE.fourWaysEasy) {
								state = STATE.fourWaysEasy2;
							}
							if (state == STATE.fourWaysMedium) {
								state = STATE.fourWaysMedium2;
							}
							if (state == STATE.fourWaysHard) {
								state = STATE.fourWaysHard2;
							}
							if (state == STATE.fourWaysInsane) {
								state = STATE.fourWaysInsane2;
							}
						}
					}
					if (Player == Color.orange && player.x - recto.x <= 20 && player.x - recto.x > -20) {
						if (!(player.y - recto.y <= 101) || !(player.y - recto.y >= 0)) {
							if (state == STATE.fourWaysEasy) {
								state = STATE.fourWaysEasy2;
							}
							if (state == STATE.fourWaysMedium) {
								state = STATE.fourWaysMedium2;
							}
							if (state == STATE.fourWaysHard) {
								state = STATE.fourWaysHard2;
							}
							if (state == STATE.fourWaysInsane) {
								state = STATE.fourWaysInsane2;
							}
						}
					}
					if (Player == Color.green && player.x - rectp.x <= 20 && player.x - rectp.x > -20) {
						if (!(player.y - rectp.y <= 101) || !(player.y - rectp.y >= 0)) {
							if (state == STATE.fourWaysEasy) {
								state = STATE.fourWaysEasy2;
							}
							if (state == STATE.fourWaysMedium) {
								state = STATE.fourWaysMedium2;
							}
							if (state == STATE.fourWaysHard) {
								state = STATE.fourWaysHard2;
							}
							if (state == STATE.fourWaysInsane) {
								state = STATE.fourWaysInsane2;
							}
						}
					}
					if (rect.x <= 0 && (state == STATE.fourWaysMedium || state == STATE.fourWaysEasy
							|| state == STATE.fourWaysHard || state == STATE.fourWaysInsane)) {
						if (whiteBox.size() >= 1) {
							whiteBox.remove(i);
							redBox.remove(i);
							orangeBox.remove(i);
							blueBox.remove(i);
							greenBox.remove(i);
						}
						addWhiteColumn();
						addBlueColumn();
						addRedColumn();
						addOrangeColumn();
						addgreenColumn();
						score++;
						if (state == STATE.fourWaysEasy) {
							if (score > fourWaysEasyScore) {
								fourWaysEasyScore = score;
							}
						}
						if (state == STATE.fourWaysMedium) {
							if (score > fourWaysMediumScore) {
								fourWaysMediumScore = score;
							}
						}
						if (state == STATE.fourWaysHard) {
							if (score > fourWaysHardScore) {
								fourWaysHardScore = score;
							}
						}
						if (state == STATE.fourWaysInsane) {
							if (score > fourWaysInsaneScore) {
								fourWaysInsaneScore = score;
							}
						}
					}
				}
			}
			if (XorY >= .25 && XorY < .5) {
				for (int x = 0; x < whiteBox.size(); x++) {
					Rectangle rect = whiteBox.get(x);
					Rectangle rectb = blueBox.get(x);
					Rectangle rectr = redBox.get(x);
					Rectangle recto = orangeBox.get(x);
					Rectangle rectp = greenBox.get(x);
					rect.y -= speed;
					rectb.y -= speed;
					rectr.y -= speed;
					recto.y -= speed;
					rectp.y -= speed;
					if (Player == Color.white && rect.y - player.y <= 20 && rect.y - player.y >= -20) {
						if (!(player.x - rect.x <= 101) || !(player.x - rect.x >= 0)) {
							if (state == STATE.fourWaysEasy) {
								state = STATE.fourWaysEasy2;
							}
							if (state == STATE.fourWaysMedium) {
								state = STATE.fourWaysMedium2;
							}
							if (state == STATE.fourWaysHard) {
								state = STATE.fourWaysHard2;
							}
							if (state == STATE.fourWaysInsane) {
								state = STATE.fourWaysInsane2;
							}
						}
					}
					if (Player == Color.blue && rectb.y - player.y <= 20 && rectb.y - player.y >= -20) {
						if (!(player.x - rectb.x <= 101) || !(player.x - rectb.x >= 0)) {
							if (state == STATE.fourWaysEasy) {
								state = STATE.fourWaysEasy2;
							}
							if (state == STATE.fourWaysMedium) {
								state = STATE.fourWaysMedium2;
							}
							if (state == STATE.fourWaysHard) {
								state = STATE.fourWaysHard2;
							}
							if (state == STATE.fourWaysInsane) {
								state = STATE.fourWaysInsane2;
							}
						}
					}
					if (Player == Color.red && rectr.y - player.y <= 20 && rectr.y - player.y >= -20) {
						if (!(player.x - rectr.x <= 101) || !(player.x - rectr.x >= 0)) {
							if (state == STATE.fourWaysEasy) {
								state = STATE.fourWaysEasy2;
							}
							if (state == STATE.fourWaysMedium) {
								state = STATE.fourWaysMedium2;
							}
							if (state == STATE.fourWaysHard) {
								state = STATE.fourWaysHard2;
							}
							if (state == STATE.fourWaysInsane) {
								state = STATE.fourWaysInsane2;
							}
						}
					}
					if (Player == Color.green && rectp.y - player.y <= 20 && rectp.y - player.y >= -20) {
						if (!(player.x - rectp.x <= 101) || !(player.x - rectp.x >= 0)) {
							if (state == STATE.fourWaysEasy) {
								state = STATE.fourWaysEasy2;
							}
							if (state == STATE.fourWaysMedium) {
								state = STATE.fourWaysMedium2;
							}
							if (state == STATE.fourWaysHard) {
								state = STATE.fourWaysHard2;
							}
							if (state == STATE.fourWaysInsane) {
								state = STATE.fourWaysInsane2;
							}
						}
					}
					if (Player == Color.orange && recto.y - player.y <= 20 && recto.y - player.y >= -20) {
						if (!(player.x - recto.x <= 101) || !(player.x - recto.x >= 0)) {
							if (state == STATE.fourWaysEasy) {
								state = STATE.fourWaysEasy2;
							}
							if (state == STATE.fourWaysMedium) {
								state = STATE.fourWaysMedium2;
							}
							if (state == STATE.fourWaysHard) {
								state = STATE.fourWaysHard2;
							}
							if (state == STATE.fourWaysInsane) {
								state = STATE.fourWaysInsane2;
							}
						}
					}
					if (rect.y <= 0 && (state == STATE.fourWaysMedium || state == STATE.fourWaysEasy
							|| state == STATE.fourWaysHard || state == STATE.fourWaysInsane)) {
						if (whiteBox.size() >= 1) {
							whiteBox.remove(x);
							redBox.remove(x);
							orangeBox.remove(x);
							blueBox.remove(x);
							greenBox.remove(x);
						}
						addWhiteColumn();
						addBlueColumn();
						addRedColumn();
						addOrangeColumn();
						addgreenColumn();
						score++;
						if (state == STATE.fourWaysEasy) {
							if (score > fourWaysEasyScore) {
								fourWaysEasyScore = score;
							}
						}
						if (state == STATE.fourWaysMedium) {
							if (score > fourWaysMediumScore) {
								fourWaysMediumScore = score;
							}
						}
						if (state == STATE.fourWaysHard) {
							if (score > fourWaysHardScore) {
								fourWaysHardScore = score;
							}
						}
						if (state == STATE.fourWaysInsane) {
							if (score > fourWaysInsaneScore) {
								fourWaysInsaneScore = score;
							}
						}
					}
				}
			}
			if (XorY >= .5 && XorY < .75) {
				for (int x = 0; x < whiteBox.size(); x++) {
					Rectangle rect = whiteBox.get(x);
					Rectangle rectb = blueBox.get(x);
					Rectangle rectr = redBox.get(x);
					Rectangle recto = orangeBox.get(x);
					Rectangle rectp = greenBox.get(x);
					rect.y += speed;
					rectb.y += speed;
					rectr.y += speed;
					recto.y += speed;
					rectp.y += speed;
					if (Player == Color.white && rect.y - player.y <= 20 && rect.y - player.y >= -20) {
						if (!(player.x - rect.x <= 101) || !(player.x - rect.x >= 0)) {
							if (state == STATE.fourWaysEasy) {
								state = STATE.fourWaysEasy2;
							}
							if (state == STATE.fourWaysMedium) {
								state = STATE.fourWaysMedium2;
							}
							if (state == STATE.fourWaysHard) {
								state = STATE.fourWaysHard2;
							}
							if (state == STATE.fourWaysInsane) {
								state = STATE.fourWaysInsane2;
							}
						}
					}
					if (Player == Color.blue && rectb.y - player.y <= 20 && rectb.y - player.y >= -20) {
						if (!(player.x - rectb.x <= 101) || !(player.x - rectb.x >= 0)) {
							if (state == STATE.fourWaysEasy) {
								state = STATE.fourWaysEasy2;
							}
							if (state == STATE.fourWaysMedium) {
								state = STATE.fourWaysMedium2;
							}
							if (state == STATE.fourWaysHard) {
								state = STATE.fourWaysHard2;
							}
							if (state == STATE.fourWaysInsane) {
								state = STATE.fourWaysInsane2;
							}
						}
					}
					if (Player == Color.red && rectr.y - player.y <= 20 && rectr.y - player.y >= -20) {
						if (!(player.x - rectr.x <= 101) || !(player.x - rectr.x >= 0)) {
							if (state == STATE.fourWaysEasy) {
								state = STATE.fourWaysEasy2;
							}
							if (state == STATE.fourWaysMedium) {
								state = STATE.fourWaysMedium2;
							}
							if (state == STATE.fourWaysHard) {
								state = STATE.fourWaysHard2;
							}
							if (state == STATE.fourWaysInsane) {
								state = STATE.fourWaysInsane2;
							}
						}
					}
					if (Player == Color.green && rectp.y - player.y <= 20 && rectp.y - player.y >= -20) {
						if (!(player.x - rectp.x <= 101) || !(player.x - rectp.x >= 0)) {
							if (state == STATE.fourWaysEasy) {
								state = STATE.fourWaysEasy2;
							}
							if (state == STATE.fourWaysMedium) {
								state = STATE.fourWaysMedium2;
							}
							if (state == STATE.fourWaysHard) {
								state = STATE.fourWaysHard2;
							}
							if (state == STATE.fourWaysInsane) {
								state = STATE.fourWaysInsane2;
							}
						}
					}
					if (Player == Color.orange && recto.y - player.y <= 20 && recto.y - player.y >= -20) {
						if (!(player.x - recto.x <= 101) || !(player.x - recto.x >= 0)) {
							if (state == STATE.fourWaysEasy) {
								state = STATE.fourWaysEasy2;
							}
							if (state == STATE.fourWaysMedium) {
								state = STATE.fourWaysMedium2;
							}
							if (state == STATE.fourWaysHard) {
								state = STATE.fourWaysHard2;
							}
							if (state == STATE.fourWaysInsane) {
								state = STATE.fourWaysInsane2;
							}
						}
					}
					if (rect.y >= 600 && (state == STATE.fourWaysMedium || state == STATE.fourWaysEasy
							|| state == STATE.fourWaysHard || state == STATE.fourWaysInsane)) {
						if (whiteBox.size() >= 1) {
							whiteBox.remove(x);
							redBox.remove(x);
							orangeBox.remove(x);
							blueBox.remove(x);
							greenBox.remove(x);
						}
						addWhiteColumn();
						addBlueColumn();
						addRedColumn();
						addOrangeColumn();
						addgreenColumn();
						score++;
						if (state == STATE.fourWaysEasy) {
							if (score > fourWaysEasyScore) {
								fourWaysEasyScore = score;
							}
						}
						if (state == STATE.fourWaysMedium) {
							if (score > fourWaysMediumScore) {
								fourWaysMediumScore = score;
							}
						}
						if (state == STATE.fourWaysHard) {
							if (score > fourWaysHardScore) {
								fourWaysHardScore = score;
							}
						}
						if (state == STATE.fourWaysInsane) {
							if (score > fourWaysInsaneScore) {
								fourWaysInsaneScore = score;
							}
						}
					}
				}
			}
			if (XorY >= .75) {
				for (int i = 0; i < whiteBox.size(); i++) {
					Rectangle rect = whiteBox.get(i);
					Rectangle rectb = blueBox.get(i);
					Rectangle rectr = redBox.get(i);
					Rectangle recto = orangeBox.get(i);
					Rectangle rectp = greenBox.get(i);
					rect.x += speed;
					rectb.x += speed;
					rectr.x += speed;
					recto.x += speed;
					rectp.x += speed;
					if (Player == Color.white && player.x - rect.x <= 20 && player.x - rect.x > -20) {
						if (!(player.y - rect.y <= 101) || !(player.y - rect.y >= 0)) {
							if (state == STATE.fourWaysEasy) {
								state = STATE.fourWaysEasy2;
							}
							if (state == STATE.fourWaysMedium) {
								state = STATE.fourWaysMedium2;
							}
							if (state == STATE.fourWaysHard) {
								state = STATE.fourWaysHard2;
							}
							if (state == STATE.fourWaysInsane) {
								state = STATE.fourWaysInsane2;
							}
						}
					}
					if (Player == Color.red && player.x - rectr.x <= 20 && player.x - rectr.x > -20) {
						if (!(player.y - rectr.y <= 101) || !(player.y - rectr.y >= 0)) {
							if (state == STATE.fourWaysEasy) {
								state = STATE.fourWaysEasy2;
							}
							if (state == STATE.fourWaysMedium) {
								state = STATE.fourWaysMedium2;
							}
							if (state == STATE.fourWaysHard) {
								state = STATE.fourWaysHard2;
							}
							if (state == STATE.fourWaysInsane) {
								state = STATE.fourWaysInsane2;
							}
						}
					}
					if (Player == Color.blue && player.x - rectb.x <= 20 && player.x - rectb.x > -20) {
						if (!(player.y - rectb.y <= 101) || !(player.y - rectb.y >= 0)) {
							if (state == STATE.fourWaysEasy) {
								state = STATE.fourWaysEasy2;
							}
							if (state == STATE.fourWaysMedium) {
								state = STATE.fourWaysMedium2;
							}
							if (state == STATE.fourWaysHard) {
								state = STATE.fourWaysHard2;
							}
							if (state == STATE.fourWaysInsane) {
								state = STATE.fourWaysInsane2;
							}
						}
					}
					if (Player == Color.orange && player.x - recto.x <= 20 && player.x - recto.x > -20) {
						if (!(player.y - recto.y <= 101) || !(player.y - recto.y >= 0)) {
							if (state == STATE.fourWaysEasy) {
								state = STATE.fourWaysEasy2;
							}
							if (state == STATE.fourWaysMedium) {
								state = STATE.fourWaysMedium2;
							}
							if (state == STATE.fourWaysHard) {
								state = STATE.fourWaysHard2;
							}
							if (state == STATE.fourWaysInsane) {
								state = STATE.fourWaysInsane2;
							}
						}
					}
					if (Player == Color.green && player.x - rectp.x <= 20 && player.x - rectp.x > -20) {
						if (!(player.y - rectp.y <= 101) || !(player.y - rectp.y >= 0)) {
							if (state == STATE.fourWaysEasy) {
								state = STATE.fourWaysEasy2;
							}
							if (state == STATE.fourWaysMedium) {
								state = STATE.fourWaysMedium2;
							}
							if (state == STATE.fourWaysHard) {
								state = STATE.fourWaysHard2;
							}
							if (state == STATE.fourWaysInsane) {
								state = STATE.fourWaysInsane2;
							}
						}
					}
					if (rect.x >= 600 && (state == STATE.fourWaysMedium || state == STATE.fourWaysEasy
							|| state == STATE.fourWaysHard || state == STATE.fourWaysInsane)) {
						if (whiteBox.size() >= 1) {
							whiteBox.remove(i);
							redBox.remove(i);
							orangeBox.remove(i);
							blueBox.remove(i);
							greenBox.remove(i);
						}
						addWhiteColumn();
						addBlueColumn();
						addRedColumn();
						addOrangeColumn();
						addgreenColumn();
						score++;
						if (state == STATE.fourWaysEasy) {
							if (score > fourWaysEasyScore) {
								fourWaysEasyScore = score;
							}
						}
						if (state == STATE.fourWaysMedium) {
							if (score > fourWaysMediumScore) {
								fourWaysMediumScore = score;
							}
						}
						if (state == STATE.fourWaysHard) {
							if (score > fourWaysHardScore) {
								fourWaysHardScore = score;
							}
						}
						if (state == STATE.fourWaysInsane) {
							if (score > fourWaysInsaneScore) {
								fourWaysInsaneScore = score;
							}
						}
					}
				}

			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}