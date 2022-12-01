package dynamicball;

import javax.swing.JFrame;

import dynamicball.BallPanel;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new JFrame("Dynamic Bouncing Ball");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(new BallPanel(900, 720));
				frame.pack();
				frame.setVisible(true);
			}
		});
	}

}
