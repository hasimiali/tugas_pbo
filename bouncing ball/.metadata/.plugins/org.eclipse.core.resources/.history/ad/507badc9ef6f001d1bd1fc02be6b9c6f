package multipleball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import multipleball.BallArea;

public class Ball {
	float x, y;
	float speedX, speedY;
	float radius;
	private Color color;
	
	public Ball(float x, float y, float radius, float speed, float angleInDegree, Color color) {
		this.x=x;
		this.y=y;
		this.radius=radius;
		this.speedX= (float)(speed*Math.cos(Math.toRadians(angleInDegree)));
		this.speedY= (float)(-speed*(float)Math.sin(Math.toRadians(angleInDegree)));
		this.color=color;
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval((int)(x-radius), (int)(y-radius), (int)(2*radius), (int)(2*radius));
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
//			this.color = new Color(r, g, b);
		}else if(x>ballMaxX) {
			speedX= -speedX;
			x=ballMaxX;
//			this.color = new Color(r, g, b);
		}
		
		if(y<ballMinY) {
			speedY= -speedY;
			y=ballMinY;
//			this.color = new Color(r, g, b);
		}else if(y>ballMaxY) {
			speedY= -speedY;
			y=ballMaxY;
//			this.color = new Color(r, g, b);
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
		    this.speedX = -this.speedX;
		    this.speedY = -this.speedY;
		    ball.speedX = -ball.speedX;
		    ball.speedY = -ball.speedY;
		    }
	}
}
