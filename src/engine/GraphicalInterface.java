package engine;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 *         interface graphique avec son controller et son afficheur
 *
 */
public class GraphicalInterface {

	/**
	 * le Panel pour l afficheur
	 */
	private DrawingPanel panel;
	private JFrame f;

	/**
	 * la construction de l interface graphique: JFrame avec panel pour le game
	 * 
	 * @param gamePainter    l'afficheur a utiliser dans le moteur
	 * @param gameController l'afficheur a utiliser dans le moteur
	 * @throws IOException
	 * 
	 */
	public GraphicalInterface(GamePainter gamePainter, GameController gameController) throws IOException {
		f = new JFrame();
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("CovidedButSurvived");
		f.setResizable(false);
		Image icon = ImageIO.read(new File("img/icon.png"));
		f.setIconImage(icon);
		// attacher le panel contenant l afficheur du game
		this.panel = new DrawingPanel(gamePainter);
		f.setContentPane(this.panel);

		// attacher controller au panel du game
		this.panel.addKeyListener(gameController);

		f.pack();
		f.setVisible(true);
		f.getContentPane().setFocusable(true);
		f.getContentPane().requestFocus();
	}

	/**
	 * mise a jour du dessin
	 */
	public void paint() {
		this.panel.drawGame();
	}

	public void dispose() {
		f.dispose();
	}

}
