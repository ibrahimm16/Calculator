import javax.swing.Timer;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MainCalculator implements ActionListener, MouseListener {
	private Timer timer = new Timer(20, this);
	private Renderer renderer = new Renderer();
	public static MainCalculator calculator;
	public static STATE state = STATE.main;
	private static BufferedImage wallpaper;
	private static BufferedImage console;
	private static BufferedImage one;
	private static BufferedImage two;
	private static BufferedImage three;
	private static BufferedImage four;
	private static BufferedImage five;
	private static BufferedImage six;
	private static BufferedImage seven;
	private static BufferedImage eight;
	private static BufferedImage nine;
	private static BufferedImage zero;
	private static BufferedImage dec;
	private static BufferedImage neg;
	private static BufferedImage graph;
	private static BufferedImage plus;
	private static BufferedImage minus;
	private static BufferedImage mul;
	private static BufferedImage div;
	private static BufferedImage lPar;
	private static BufferedImage rPar;
	private static BufferedImage programs;

	private static enum STATE {
		main
	}

	public MainCalculator() {
		JFrame frame = new JFrame();
		frame.addMouseListener((MouseListener) this);
		frame.add(renderer);
		frame.setSize(500, 700);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle("Graphing Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		timer.start();
	}

	public static void main(String[] args) {
		setTextures();
		calculator = new MainCalculator();
	}

	public static void setTextures() {
		try {
			console = ImageIO.read(MainCalculator.class.getResource("console.png"));
			one = ImageIO.read(MainCalculator.class.getResource("1.png"));
			two = ImageIO.read(MainCalculator.class.getResource("2.png"));
			three = ImageIO.read(MainCalculator.class.getResource("3.png"));
			four = ImageIO.read(MainCalculator.class.getResource("4.png"));
			five = ImageIO.read(MainCalculator.class.getResource("5.png"));
			six = ImageIO.read(MainCalculator.class.getResource("6.png"));
			seven = ImageIO.read(MainCalculator.class.getResource("7.png"));
			eight = ImageIO.read(MainCalculator.class.getResource("8.png"));
			nine = ImageIO.read(MainCalculator.class.getResource("9.png"));
			wallpaper = ImageIO.read(MainCalculator.class.getResource("calcwall.jpg"));
			zero = ImageIO.read(MainCalculator.class.getResource("0.png"));
			dec = ImageIO.read(MainCalculator.class.getResource("dot.png"));
			neg = ImageIO.read(MainCalculator.class.getResource("-.png"));
			graph = ImageIO.read(MainCalculator.class.getResource("graph.png"));
			plus = ImageIO.read(MainCalculator.class.getResource("+.png"));
			minus = ImageIO.read(MainCalculator.class.getResource("-2.png"));
			lPar = ImageIO.read(MainCalculator.class.getResource("(.png"));
			rPar = ImageIO.read(MainCalculator.class.getResource(").png"));
			mul = ImageIO.read(MainCalculator.class.getResource("x.png"));
			div = ImageIO.read(MainCalculator.class.getResource("÷.png"));
			programs = ImageIO.read(MainCalculator.class.getResource("programs.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void repaint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, 500, 700);
		g.drawImage(wallpaper, 0, 0, null);
		g.drawImage(console, 17, 20, null);
		
		if (state == STATE.main) {
			g.drawImage(zero, 10, 560, null);
			g.drawImage(dec, 120, 560, null);
			g.drawImage(neg, 230, 560, null);
			g.drawImage(one, 10, 450, null);
			g.drawImage(two, 120, 450, null);
			g.drawImage(three, 230, 450, null);
			g.drawImage(four, 10, 340, null);
			g.drawImage(five, 120, 340, null);
			g.drawImage(six, 230, 340, null);
			g.drawImage(seven, 10, 230, null);
			g.drawImage(eight, 120, 230, null);
			g.drawImage(nine, 230, 230, null);
			g.drawImage(graph, 340, 608, null);
			g.drawImage(plus, 335, 548, null);
			g.drawImage(minus, 390, 548, null);
			g.drawImage(rPar, 445, 548, null);
			g.drawImage(mul, 335, 493, null);
			g.drawImage(div, 390, 493, null);
			g.drawImage(lPar, 445, 493, null);
			g.drawImage(programs, 343, 230, null);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getX() < 100) {
			System.exit(0);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}
