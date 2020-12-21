package start;

import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

public class RootPanel extends JPanel {
	public void paintComponent(Graphics crayon) {
	    JButton StartButton = new JButton("Start");
	    this.add(StartButton);
	}
}
