package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

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
		crayon_pac.setColor(Color.blue);
		crayon_pac.fillOval(heros.X,heros.Y,40,40);
	}



}
