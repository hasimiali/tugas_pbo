package dynamicball;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import dynamicball.Ball;

public class BallPanel extends JPanel implements KeyListener{
	private static final int REFRESH_RATE=144;
	
	private ArrayList<Ball> balls;
	
	private BallArea box;
	private int areaWidth;
	private int areaHeight;
	
	private char[] symbol= {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
					  'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 
					  'y', 'z','A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
					  'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 
					  'Y', 'Z','1', '2', '3', '4', '5', '6', '7', '8', '9', '0'
	};
	
	public BallPanel(int width, int height) {
		this.areaWidth=width;
		this.areaHeight=height;
		this.setPreferredSize(new Dimension(areaWidth, areaHeight));
		
		this.balls = new ArrayList<Ball>();
		box = new BallArea(0, 0, width, height, Color.BLACK, Color.WHITE);
		
		
		//untuk mendapatkan ukuran area bg jika frame diresize ketika program berjalan
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Component c = (Component)e.getSource();
				Dimension dim = c.getSize();
				areaWidth = dim.width;
				areaHeight = dim.height;
				box.set(0, 0, areaWidth, areaHeight);
			}
		});
		
		this.addKeyListener(this);
		this.setFocusable(true);
		startThread();
	}
	
	//fungsi thread
	public void startThread() {
		Thread gameThread = new Thread() {
			public void run() {
				while(true) {
					for(Ball ball : balls) {
						ball.collide(box);
						
						for(Ball ball2 : balls) {
							if(ball!=ball2) {
								ball.collide(ball2);
							}
						}
					}

					repaint();
					try {
						Thread.sleep(1000/REFRESH_RATE);
					}catch(InterruptedException ex) {}
				}
			}
		};
		gameThread.start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		box.draw(g);
		for(Ball ball : this.balls) {
			ball.draw(g);
		}
	}
	
	public void addNewBall(KeyEvent e) {
		Random rand = new Random();
		int radius = 40;
		int x = rand.nextInt(this.areaWidth-radius*2-20)+radius+10;
		int y = rand.nextInt(this.areaHeight-radius*2-20)+radius+10;
		int speed = 4;
		int angleInDegree = rand.nextInt(360);
		
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		
		for(int i=0; i<36; i++) {
			if(e.getKeyChar()==symbol[i]) {
				Ball ball = new Ball(x, y, radius, speed, angleInDegree, new Color(r, g, b), e.getKeyChar());
				this.balls.add(ball);
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		addNewBall(e);
		repaint();
	}

	
}
