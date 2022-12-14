package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class MousePanel extends JPanel implements MouseListener, MouseMotionListener {
	private int areaWidth;
	private int areaHeight;
	private String text1, text2;
	
	public MousePanel(int width, int height) {
		this.areaWidth = width;
		this.areaHeight = height;
		this.setPreferredSize(new Dimension(areaWidth, areaHeight));
		this.text1 = "Lakukan sesuatu menggunakan mouse...";
		this.text2 = "Position";
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setFocusable(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString(this.text1, 0, this.areaHeight / 2);
		g.drawString(this.text2, 0, 15);
	}
	
	public void mouseClicked(MouseEvent e) {
		this.text1 = "Tombol mouse diklik pada posisi X: " + e.getX() + " Y: " + e.getY();
		repaint();
	}
	public void mousePressed(MouseEvent e) {
		this.text1 = "Tombol mouse ditekan pada posisi X: " + e.getX() + " Y: " + e.getY();
		repaint();
	}
	public void mouseReleased(MouseEvent e) {
		this.text1 = "Tombol mouse dilepas pada posisi X: " + e.getX() + " Y: " + e.getY();
		repaint();
	}
	public void mouseEntered(MouseEvent e) {
		this.text1 = "Mouse memasuki area MousePanel";
		repaint();
	}
	public void mouseExited(MouseEvent e) {
		this.text1 = "Mouse meninggalkan area MousePanel";
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.text2 = "Position X= " + e.getX() + "Y= " + e.getY() + " dragged";
		repaint();
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.text2 = "Position X= " + e.getX() + "Y= " + e.getY() + " undragged";
		repaint();
		
	}

	
}
