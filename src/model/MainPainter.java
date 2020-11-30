package model;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import engine.GamePainter;

public class MainPainter implements GamePainter{

	/**
	 * la taille des cases
	 */
	protected static final int WIDTH = 320;
	protected static final int HEIGHT = 320;

	/**
	 * appelle constructeur parent
	 * 
	 * @param game
	 *            le jeutest a afficher
	 */
	PacmanPainter herosPainter;
	LabyrinthePainter donjonPainter;
	MonsterPainter monsterPainter;
	
	public MainPainter(Pacman in_heros, Labyrinthe in_donjon, Monster[] in_monsters) {
		herosPainter= new PacmanPainter(in_heros);
		donjonPainter = new LabyrinthePainter(in_donjon, WIDTH, HEIGHT);
		monsterPainter= new MonsterPainter(in_monsters);
	}

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {
		Graphics2D crayon_pac = (Graphics2D) im.getGraphics();
		Graphics2D crayon_lab = (Graphics2D) im.getGraphics();
		Graphics2D crayon_evl = (Graphics2D) im.getGraphics();
		donjonPainter.draw(crayon_lab);
		herosPainter.draw(crayon_pac);
		monsterPainter.draw(crayon_evl);
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
