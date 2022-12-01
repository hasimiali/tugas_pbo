package main;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import java.lang.Math;

public class BallPanel extends JPanel implements KeyListener {
	private static final int REFRESH_RATE = 120;
	ArrayList<Ball> balls = new ArrayList<>();
	private BallArea box;
	private int areaWidth;
	private int areaHeight;
	
	private char[] symbol= {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
			  'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 
			  'y', 'z','A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
			  'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 
			  'Y', 'Z','1', '2', '3', '4', '5', '6', '7', '8', '9', '0'
	};
	
	
	
	public BallPanel(final int width, final int height) {
		this.addKeyListener(this);
		this.setFocusable(true);
		
		this.areaWidth = width; this.areaHeight = height;
		this.setPreferredSize(new Dimension(areaWidth, areaHeight));
		Random rand = new Random();
		int radius = 50;
		int x = rand.nextInt(width - radius * 2 - 20) + radius + 10;
		int y = rand.nextInt(height - radius * 2 - 20) + radius + 10;
		int speed = 5;
		int angleInDegree = rand.nextInt(360);


		box = new BallArea(0, 0, width, height, Color.BLACK, Color.WHITE);
		//untuk mendapatkan ukuran area latar belakang jika frame diresize
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Component c = (Component)e.getSource();
				Dimension dim = c.getSize();
				areaWidth = dim.width;
				areaHeight = dim.height;
				box.set(0, 0, width, height);
			}
		});
		startThread();
	}


	public void ballCollission(ArrayList<Ball> balls) {
		for(Ball b1 : balls)
		    for(Ball b2 : balls)
		        if(Math.sqrt(Math.pow((b2.x-b1.x), 2)+Math.pow((b2.y-b1.y), 2)) <= 100){
		            //do whatever (such as bouncing off) when the balls collide
		        	System.out.println(Math.sqrt(Math.pow((b2.x-b1.x), 2)+Math.pow((b2.y-b1.y), 2)));
		            b1.speedX = -(b1.speedX);
		            b1.speedY = -(b1.speedY);
		            b2.speedX = -(b2.speedX);
		            b2.speedY = -(b2.speedY);           
		        }
	}
	public void startThread() {
		Thread gameThread = new Thread() {
			public void run() {
				while (true) {
					for(Ball i : balls) {
						i.collide(box);
						for(Ball j : balls) {
							if(i!=j) {
								i.collide(j);
							}
						}
					}
					repaint();
					try {
					Thread.sleep(1000 / REFRESH_RATE);
					} catch (InterruptedException ex) {}
				}
			}
		};
		gameThread.start();
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		box.draw(g);
		for(Ball i : balls) {
			i.draw(g);
		}
		
	}
	
	public void addNewBall(KeyEvent e) {
		System.out.println("bola " + e.getKeyChar() + " masuk");
		Random rand = new Random();
		int radius = 40;
		int x = rand.nextInt(this.areaWidth-radius*2-20)+radius+10;
		int y = rand.nextInt(this.areaHeight-radius*2-20)+radius+10;
		int speed = 4;
		int angleInDegree = rand.nextInt(360);
		
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();

		for(char c : this.symbol) {
			if(e.getKeyChar()==c) {
				System.out.println("bola " + e.getKeyChar() + " masuk");
				Ball ball = new Ball(x, y, radius, speed, angleInDegree, new Color(r, g, b), e.getKeyChar());
				this.balls.add(ball);
			}
		}
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		addNewBall(e);
		repaint();
	}
}