package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class KeyboardPanel extends JPanel implements KeyListener {
	private List<Key> keys;
	
	public KeyboardPanel(int width, int height) {
		this.setPreferredSize(new Dimension(width, height));
		
		this.keys = new ArrayList<Key>();
		//coloumn 1
		this.keys.add(new Key(10, 10, '1'));
		this.keys.add(new Key(100, 10, '2'));
		this.keys.add(new Key(190, 10, '3'));
		this.keys.add(new Key(280, 10, '4'));
		this.keys.add(new Key(370, 10, '5'));
		this.keys.add(new Key(460, 10, '6'));
		this.keys.add(new Key(550, 10, '7'));
		this.keys.add(new Key(640, 10, '8'));
		this.keys.add(new Key(730, 10, '9'));
		this.keys.add(new Key(820, 10, '0'));
		
		//coloumn 2
		this.keys.add(new Key(10, 100, 'q'));
		this.keys.add(new Key(100, 100, 'w'));
		this.keys.add(new Key(190, 100, 'e'));
		this.keys.add(new Key(280, 100, 'r'));
		this.keys.add(new Key(370, 100, 't'));
		this.keys.add(new Key(460, 100, 'y'));
		this.keys.add(new Key(550, 100, 'u'));
		this.keys.add(new Key(640, 100, 'i'));
		this.keys.add(new Key(730, 100, 'o'));
		this.keys.add(new Key(820, 100, 'p'));
		
		//coloumn 3
		this.keys.add(new Key(50, 190, 'a'));
		this.keys.add(new Key(140, 190, 's'));
		this.keys.add(new Key(230, 190, 'd'));
		this.keys.add(new Key(320, 190, 'f'));
		this.keys.add(new Key(410, 190, 'g'));
		this.keys.add(new Key(500, 190, 'h'));
		this.keys.add(new Key(590, 190, 'j'));
		this.keys.add(new Key(680, 190, 'k'));
		this.keys.add(new Key(770, 190, 'l'));
		
		//coloumn 4
		this.keys.add(new Key(80, 280, 'z'));
		this.keys.add(new Key(170, 280, 'x'));
		this.keys.add(new Key(260, 280, 'c'));
		this.keys.add(new Key(350, 280, 'v'));
		this.keys.add(new Key(440, 280, 'b'));
		this.keys.add(new Key(530, 280, 'n'));
		this.keys.add(new Key(620, 280, 'm'));

		
		this.addKeyListener(this);
		this.setFocusable(true);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(Key key : this.keys) {
			key.render(g);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		char key = e.getKeyChar();
		for (Key k : this.keys) {
			if(k.isSymbolMatch(key)) {
				k.setPressed();
				repaint();
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		char key = e.getKeyChar();
		for (Key k : this.keys) {
			if(k.isSymbolMatch(key)) {
				k.setReleased();
				repaint();
			}
		}
	}
}
