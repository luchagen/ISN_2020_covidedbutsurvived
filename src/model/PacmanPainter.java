package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.GamePainter;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 * 
 */
public class PacmanPainter {

	/**
	 * la taille des cases
	 */
	protected static final int WIDTH = 400;
	protected static final int HEIGHT = 400;

	/**
	 * appelle constructeur parent
	 * 
	 * @param game
	 *            le jeutest a afficher
	 */
	Pacman heros;
	public PacmanPainter(Pacman in_heros) {
		heros=in_heros;
		
	}

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	public void draw(Graphics2D crayon_pac) {
		Image img;
		try {
			img = ImageIO.read(new File(heros.skin));
			crayon_pac.drawImage(img, heros.X, heros.Y, heros.X+40 , heros.Y+40, 0, 0, img.getWidth(null), img.getWidth(null), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}



}
}
