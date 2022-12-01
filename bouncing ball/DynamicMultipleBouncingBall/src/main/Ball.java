package main;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Ball extends Rectangle{
	private static final long serialVersionUID = 1L;
	float x, y;
	float speedX, speedY;
	float radius;
	private Color color;
	char huruf;
	
	public Ball(float x, float y, float radius, float speed, float
	angleInDegree,
	Color color, char huruf) {
		this.x = x;
		this.y = y;
		this.speedX = speed;

		this.speedY = speed;

		this.radius = radius;
		this.color = color;
		this.huruf = huruf;
	}
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval((int)(x - radius), (int)(y - radius), (int)(2 *
		radius), (int)(2 * radius));
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
		g.drawString(String.valueOf(this.huruf), (int)x, (int)y);
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
		
		if (x < ballMinX) {
			speedX = -(speedX);
			x = ballMinX;
//			color = new Color(r, g, b);
		} else if (x > ballMaxX) {
			speedX = -(speedX);
			x = ballMaxX;
//			color = new Color(r, g, b);
		}
		if (y < ballMinY) {
			speedY = (float) -(speedY);
			y = ballMinY;
//			color = new Color(r, g, b);
		} else if (y > ballMaxY) {
			speedY = -(speedY);
			y = ballMaxY;
//			color = new Color(r, g, b);
		}
	}
	public void collide(Ball ball) {
		double distance = Math.sqrt(Math.pow(x - ball.x, 2) + Math.pow(y - ball.y, 2));
		
		if (distance <= radius + ball.radius){
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

