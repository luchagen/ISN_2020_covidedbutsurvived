package start;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class HelpPanel extends JPanel {
	public void paintComponent(Graphics crayon) {
		this.setBackground(Color.BLUE);
		try {
			Image img_bg = ImageIO.read(new File("img/userInterface/controle_param.png"));
			int width = img_bg.getWidth(null);
			int height = img_bg.getHeight(null);
			crayon.drawImage(img_bg, 0, 0, width, height, 0, 0, width, height, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
