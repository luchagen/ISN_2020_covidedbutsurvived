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
public class PacmanPainter implements GamePainter {

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
	PacmanGame game;
	Labyrinthe donjon;
	int largeur_rect;
	int hauteur_rect;
	public PacmanPainter(PacmanGame in_game, Labyrinthe in_donjon) {
		game=in_game;
		donjon=in_donjon;
		largeur_rect=WIDTH/donjon.nb_largeur;
		hauteur_rect=HEIGHT/donjon.nb_hauteur;
	}

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		Graphics2D crayon1 = (Graphics2D) im.getGraphics();
		crayon.setColor(Color.blue);
		for(int i=0;i<donjon.nb_largeur;i++) {
			for (int j=0;j<donjon.nb_hauteur;j++) {
				switch(donjon.cases[i][j].nature) {
					case 0:
						crayon1.setColor(Color.black);
						break;
					case 1:
						crayon1.setColor(Color.green);
						break;
					case 2:
						crayon1.setColor(Color.orange);
						break;
					case 3:
						crayon1.setColor(Color.CYAN);
						break;
					case 4:
						crayon1.setColor(Color.orange);
						break;
					default:
						crayon1.setColor(Color.white);
						break;
					
				}
				crayon1.fillRect(largeur_rect*i, hauteur_rect*j, largeur_rect , hauteur_rect);
			}
		}
		crayon.fillOval(game.heros.X,game.heros.Y,40,40);
	}

	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}

}
