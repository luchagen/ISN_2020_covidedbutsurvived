package start;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class RootPanel extends JPanel {
	public void paintComponent(Graphics crayon) {
	    try {
			Image img = ImageIO.read(new File("img/userInterface/wood_bg.png"));
			int a = img.getWidth(null);
			int b = img.getWidth(null);
			crayon.drawImage(img, 0, 0, 320, 320, 0, 0, 320, 320, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
