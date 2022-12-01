package dynamicball;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import dynamicball.BallArea;

public class Ball {
	float x, y;
	float speedX, speedY;
	float radius;
	private Color color;
	private static final int RELEASED=1;
	private static final int PRESSED=2;
	private int state;
	private char symbol;
	
	public Ball(float x, float y, float radius, float speed, float angleInDegree, Color color) {
		this.x=x;
		this.y=y;
		this.radius=radius;
		this.speedX= (float)(speed*Math.cos(Math.toRadians(angleInDegree)));
		this.speedY= (float)(-speed*(float)Math.sin(Math.toRadians(angleInDegree)));
		this.color=color;
		this.state=RELEASED;
	}
	public Ball(float x, float y, float radius, float speed, float angleInDegree, Color color
			    , char symbol) {
		this.x=x;
		this.y=y;
		this.radius=radius;
		this.speedX= (float)(speed*Math.cos(Math.toRadians(angleInDegree)));
		this.speedY= (float)(-speed*(float)Math.sin(Math.toRadians(angleInDegree)));
		this.color=color;
		this.state=RELEASED;
		this.symbol=symbol;
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval((int)(x-radius), (int)(y-radius), (int)(2*radius), (int)(2*radius));
		int stringPosX=(int)(x-8);
		int stringPosY=(int)(y+8);
		g.setColor(Color.BLACK);
		g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 30)); 
		g.drawString(String.valueOf(this.symbol), stringPosX, stringPosY);
	}
	
	public void collide(BallArea box) {
		float ballMinX=box.minX+radius;
		float ballMinY=box.minY+radius;
		float ballMaxX=box.maxX-radius;
		float ballMaxY=box.maxY-radius;
		
		x+=speedX;
		y+=speedY;
		
		
		if(x<ballMinX) {
			speedX= -speedX;
			x=ballMinX;
		}else if(x>ballMaxX) {
			speedX= -speedX;
			x=ballMaxX;
		}
		
		if(y<ballMinY) {
			speedY= -speedY;
			y=ballMinY;
		}else if(y>ballMaxY) {
			speedY= -speedY;
			y=ballMaxY;
		}
	}
	public void collide(Ball ball) {
		double distance = Math.sqrt(Math.pow(x - ball.x, 2) + Math.pow(y - ball.y, 2));
		
		if (distance <= radius + ball.radius){ //if distance between two balls has them touching/overlapping, change direction
		    // undo last velocity update; now the balls aren't overlapping anymore
		    x = x - speedX ;
		    y = y - speedY ;
		    ball.x = ball.x - ball.speedX ;
		    ball.y = ball.y - ball.speedY ;
		    // reverse all velocity components
		    this.speedX = -this.speedX;
		    this.speedY = -this.speedY;
		    ball.speedX = -ball.speedX;
		    ball.speedY = -ball.speedY;
		    }
	}
	public boolean isSymbolMatch(char symbol) {
		if(this.symbol==symbol) {
			return true;
		}return false;
	}
	
	public void setPressed() {
		this.state=PRESSED;
	}
	public void setReleased() {
		this.state=RELEASED;
	}
}