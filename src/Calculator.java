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
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Calculator implements ActionListener, MouseListener {

	public static Object a;
	public static String userIn = "";
	public static Calculator calculator;
	private Timer timer = new Timer(20, this);
	private static Renderer renderer = new Renderer();
	private static int x1;
	private static int x2;
	private static int y1;
	private static int y2;
	private static BufferedImage calcButtons;
	private static BufferedImage exit;
	private static double answer;

	public static enum STATE {
		main, graph
	}

	public static STATE state = STATE.main;

	public Calculator() {
		JFrame frame = new JFrame();
		frame.addMouseListener((MouseListener) this);
		frame.add(renderer);
		frame.setSize(900, 900);
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setTitle("Graphing Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		timer.start();
	}

	public static void main(String[] args) {
		setImages();
		calculator = new Calculator();
	}

	public static void setImages() {
		try {
			calcButtons = ImageIO.read((Calculator.class.getResource("calcButtons.png")));
			exit = ImageIO.read((Calculator.class.getResource("exit.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void repaint(Graphics g) {
		if (state == STATE.main) {
			g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
			g.drawImage(calcButtons, 0, 200, 800, 650, null);
			g.drawString(userIn, 10, 30);
			String te = "" + answer;
			g.drawString(te, 10, 170);
		}

		if (state == STATE.graph) {
			g.drawImage(exit, 750, 10, 80, 40, null);
			g.drawString("The x and y scale of this graph is 1.", 10, 20);
			g.drawString("f(x) = " + userIn, 10, 40);
			g.setColor(Color.red);
			g.drawString("Your function is in red.", 10, 60);
			g.setColor(Color.black);
			g.drawLine(0, 450, 1000, 450);
			g.drawLine(450, 0, 450, 1000);
			for (int x = 10; x <= 900; x++) {
				int y = 445;
				g.drawLine(x, y, x, y + 10);
				x += 19;
			}
			for (int y = 10; y <= 900; y++) {
				int x = 445;
				g.drawLine(x, y, x + 10, y);
				y += 19;
			}
			g.setColor(Color.RED);
			for (double i = -40; i <= 40; i++) {
				String userIn2 = userIn;
				double temp = i + .01;
				String tem = userIn;
				String sub = "" + i;
				userIn2 = userIn2.replaceAll("x", "(" + sub + ")");
				userIn2 = userIn2.replaceAll("E", "*" + 10 + "^");
				Double d = evaluate(userIn2);
				if (d.isNaN()) {
					g.setColor(Color.BLACK);
				}
				String sub2 = "" + temp;
				tem = tem.replaceAll("x", "(" + sub2 + ")");
				tem = tem.replaceAll("E", "*" + 10 + "^");
				x1 = (int) (i * 20) + 450;
				x2 = (int) (temp * 20) + 450;
				y1 = (int) (-1 * Graph(evaluate(userIn2))) + 450;
				y2 = (int) (-1 * Graph(evaluate(tem))) + 450;
				g.drawLine(x1, y1, x2, y2);
				g.setColor(Color.RED);
				i -= .99;
			}
		}
	}

	public static double evaluate(final String str) {
		return new Object() {
			int pos = -1, ch;

			void nextChar() {
				ch = (++pos < str.length()) ? str.charAt(pos) : -1;
			}

			boolean eat(int charToEat) {
				while (ch == ' ')
					nextChar();
				if (ch == charToEat) {
					nextChar();
					return true;
				}
				return false;
			}

			double parse() {
				nextChar();
				double x = parseExpression();
				if (pos < str.length())
					throw new RuntimeException("Unexpected: " + (char) ch);
				return x;
			}

			double parseExpression() {
				double x = parseTerm();
				for (;;) {
					if (eat('+'))
						x += parseTerm(); // addition
					else if (eat('-'))
						x -= parseTerm(); // subtraction
					else
						return x;
				}
			}

			double parseTerm() {
				double x = parseFactor();
				for (;;) {
					if (eat('*'))
						x *= parseFactor(); // multiplication
					else if (eat('/'))
						x /= parseFactor(); // division
					else
						return x;
				}
			}

			double parseFactor() {
				if (eat('+'))
					return parseFactor(); // unary plus
				if (eat('-'))
					return -parseFactor(); // unary minus

				double x;
				int startPos = this.pos;
				if (eat('(')) { // parentheses
					x = parseExpression();
					eat(')');
				} else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
					while ((ch >= '0' && ch <= '9') || ch == '.')
						nextChar();
					x = Double.parseDouble(str.substring(startPos, this.pos));
				} else if (ch >= 'a' && ch <= 'z') { // functions
					while (ch >= 'a' && ch <= 'z')
						nextChar();
					String func = str.substring(startPos, this.pos);
					x = parseFactor();
					if (func.equals("sqrt"))
						x = Math.sqrt(x);
					else if (func.equals("sin"))
						x = Math.sin((x));
					else if (func.equals("cos"))
						x = Math.cos((x));
					else if (func.equals("tan"))
						x = Math.tan((x));
					else
						throw new RuntimeException("Unknown function: " + func);
				} else {
					throw new RuntimeException("Unexpected: " + (char) ch);
				}

				if (eat('^'))
					x = Math.pow(x, parseFactor()); // exponentiation

				return x;
			}
		}.parse();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		//System.out.println(mx + " " + my);
		if (state == STATE.main & mx < 598 & mx > 422 & my < 845 & my > 766) {
			userIn = userIn + "0";
		}
		if (state == STATE.main & mx < 502 & mx > 422 & my < 741 & my > 663) {
			userIn = userIn + "1";
		}
		if (state == STATE.main & mx < 602 & mx > 518 & my < 741 & my > 663) {
			userIn = userIn + "2";
		}
		if (state == STATE.main & mx < 695 & mx > 614 & my < 845 & my > 766) {
			userIn = userIn + ".";
		}
		if (state == STATE.main & mx < 695 & mx > 614 & my < 741 & my > 663) {
			userIn = userIn + "3";
		}
		if (state == STATE.main & mx < 502 & mx > 422 & my < 637 & my > 563) {
			userIn = userIn + "4";
		}
		if (state == STATE.main & mx < 602 & mx > 518 & my < 637 & my > 563) {
			userIn = userIn + "5";
		}
		if (state == STATE.main & mx < 695 & mx > 614 & my < 637 & my > 563) {
			userIn = userIn + "6";
		}
		if (state == STATE.main & mx < 791 & mx > 711 & my < 637 & my > 563) {
			userIn = userIn + "+";
		}
		if (state == STATE.main & mx < 791 & mx > 711 & my < 845 & my > 663) {
			if (userIn.indexOf("x") > -1) {
				state = STATE.graph;
			} else
				answer = evaluate(userIn);
		}
		if (state == STATE.main & mx < 502 & mx > 422 & my < 534 & my > 459) {
			userIn = userIn + "7";
		}
		if (state == STATE.main & mx < 602 & mx > 518 & my < 534 & my > 459) {
			userIn = userIn + "8";
		}
		if (state == STATE.main & mx < 695 & mx > 614 & my < 534 & my > 459) {
			userIn = userIn + "9";
		}
		if (state == STATE.main & mx < 791 & mx > 711 & my < 534 & my > 459) {
			userIn = userIn + "-";
		}
		if (state == STATE.main & mx < 502 & mx > 422 & my < 434 & my > 354) {
			userIn = "";
			answer = 0;
		}
		if (state == STATE.main & mx < 791 & mx > 711 & my < 434 & my > 354) {
			userIn = userIn + "*";
		}
		if (state == STATE.main & mx < 695 & mx > 614 & my < 434 & my > 354) {
			userIn = userIn + "/";
		}
		if (state == STATE.main & mx < 203 & mx > 121 & my < 329 & my > 254) {
			userIn = userIn + "(";
		}
		if (state == STATE.main & mx < 300 & mx > 200 & my < 329 & my > 254) {
			userIn = userIn + ")";
		}
		if (state == STATE.main & mx < 396 & mx > 317 & my < 434 & my > 354) {
			userIn = userIn + "^";
		}
		if (state == STATE.main & mx < 204 & mx > 124 & my < 434 & my > 354) {
			userIn = userIn + "x";
		}
		if (state == STATE.main & mx < 107 & mx > 28 & my < 637 & my > 563) {
			userIn = userIn + "sin";
		}
		if (state == STATE.main & mx < 203 & mx > 124 & my < 637 & my > 563) {
			userIn = userIn + "cos";
		}
		if (state == STATE.main & mx < 301 & mx > 221 & my < 637 & my > 563) {
			userIn = userIn + "tan";
		}
		if (state == STATE.graph & mx < 832 & mx > 760 & my < 76 & my > 43) {
			state = STATE.main;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		renderer.repaint();
	}

	public static double Graph(double y) {
		double a1 = y - (int) y;
		if (a1 >= 0 && a1 < .025) {
			a1 = 0;
		}
		if (a1 >= .025 && a1 < .075) {
			a1 = .05;
		}
		if (a1 >= .075 && a1 < .125) {
			a1 = .1;
		}
		if (a1 >= .125 && a1 < .175) {
			a1 = .15;
		}
		if (a1 >= .175 && a1 < .225) {
			a1 = .2;
		}
		if (a1 >= .225 && a1 < .275) {
			a1 = .25;
		}
		if (a1 >= .275 && a1 < .325) {
			a1 = .3;
		}
		if (a1 >= .325 && a1 < .375) {
			a1 = .35;
		}
		if (a1 >= .375 && a1 < .425) {
			a1 = .4;
		}
		if (a1 >= .425 && a1 < .475) {
			a1 = .45;
		}
		if (a1 >= .475 && a1 < .525) {
			a1 = .5;
		}
		if (a1 >= .525 && a1 < .575) {
			a1 = .55;
		}
		if (a1 >= .575 && a1 < .625) {
			a1 = .6;
		}
		if (a1 >= .625 && a1 < .675) {
			a1 = .65;
		}
		if (a1 >= .675 && a1 < .725) {
			a1 = .7;
		}
		if (a1 >= .725 && a1 < .775) {
			a1 = .75;
		}
		if (a1 >= .775 && a1 < .825) {
			a1 = .8;
		}
		if (a1 >= .825 && a1 < .875) {
			a1 = .85;
		}
		if (a1 >= .875 && a1 < .925) {
			a1 = .9;
		}
		if (a1 >= .925 && a1 < .975) {
			a1 = .95;
		}
		if (a1 >= .975 && a1 <= 1) {
			a1 = 1;
		}
		double a2 = (a1 + (int) y) * 20;
		return a2;
	}
}
