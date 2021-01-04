package start;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HelpFrame extends JFrame {
	public HelpFrame() throws IOException {
		this.setTitle("CovidedButSurvived - Guide d'utilisation");
		this.setSize(400, 550);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Image icon = ImageIO.read(new File("img/icon.png"));
		this.setIconImage(icon);
		this.setVisible(true);
		JPanel panneau = new HelpPanel();
		this.getContentPane().add(panneau);
	}

}
