package main;
import java.awt.*;
import java.util.Random;

public class Ball {
	float x, y;
	float speedX, speedY;
	float radius;
	private Color color;
	public Ball(float x, float y, float radius, float speed, float
	angleInDegree,
	Color color) {
		this.x = x;
		this.y = y;
		this.speedX = speed;
//				(float)(speed *
//		Math.cos(Math.toRadians(angleInDegree)));
		this.speedY = speed;
//		(float)(-speed *
//		(float)Math.sin(Math.toRadians(angleInDegree)));
		this.radius = radius;
		this.color = color;
	}
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval((int)(x - radius), (int)(y - radius), (int)(2 *
		radius), (int)(2 * radius));
	}
	public void collide(BallArea box) {
		float ballMinX = box.minX + radius;
		float ballMinY = box.minY + radius;
		float ballMaxX = box.maxX - radius;
		float ballMaxY = box.maxY - radius;
		x += speedX;
		y += speedY;
		//random color
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		//grafitasi
		speedY+=0.08;
		//friction
		if(speedX>0) speedX-=0.005;
		else if(speedX<0) speedX+=0.005;
		
		if (x < ballMinX) {
			speedX = -(speedX);
			x = ballMinX;
			color = new Color(r, g, b);
		} else if (x > ballMaxX) {
			speedX = -(speedX);
			x = ballMaxX;
			color = new Color(r, g, b);
		}
		if (y < ballMinY) {
			speedY = (float) -(speedY);
			y = ballMinY;
			color = new Color(r, g, b);
		} else if (y > ballMaxY) {
			speedY = -(speedY-1);
			y = ballMaxY;
			color = new Color(r, g, b);
		}
	}
}

