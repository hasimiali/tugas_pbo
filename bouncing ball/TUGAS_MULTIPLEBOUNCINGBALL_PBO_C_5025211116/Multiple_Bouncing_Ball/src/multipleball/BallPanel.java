package multipleball;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Random;

import javax.swing.JPanel;

public class BallPanel extends JPanel{
	private static final int REFRESH_RATE=144;
	
	private Ball[] balls = new Ball[7];
	private Color[] randomColor = new Color[7];
	
	private BallArea box;
	private int areaWidth;
	private int areaHeight;
	
	public BallPanel(int width, int height) {
		this.areaWidth=width;
		this.areaHeight=height;
		this.setPreferredSize(new Dimension(areaWidth, areaHeight));
		
		for(int i=0; i<7; i++) {
			Random rand = new Random();
			int radius = 30;
			int x = rand.nextInt(width-radius*2-20)+radius+10;
			int y = rand.nextInt(height-radius*2-20)+radius+10;
			int speed = 5;
			int angleInDegree = rand.nextInt(360);
			
			float r = rand.nextFloat();
			float g = rand.nextFloat();
			float b = rand.nextFloat();
			randomColor[i]= new Color(r, g, b);
			
			balls[i] = new Ball(x, y, radius, speed, angleInDegree, randomColor[i]);
		}
		
		
		//ball = new Ball(x, y, radius, speed, angleInDegree, Color.YELLOW);
		box = new BallArea(0, 0, width, height, Color.BLACK, Color.WHITE);
		
		
		//untuk mendapatkan ukuran area bg jika fram diresize ketika program berjalan
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
		for(int i=0; i<7; i++) {
			balls[i].draw(g);
		}
	}
}
