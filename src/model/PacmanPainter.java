package model;


import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 * 
 */
public class PacmanPainter {

		/**
	 * appelle constructeur parent
	 * 
	 * @param game
	 *            le jeutest a afficher
	 */
	Pacman heros;
	SpriteGroup herosprite;
	public PacmanPainter(Pacman in_heros) {
		heros=in_heros;
		herosprite = new SpriteGroup(heros.skin);
		
	}

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	public void draw(Graphics2D crayon_pac,int animationstage, PacmanController controller, int HEIGHT_INTERFACE) {
		Image img;
		try {
			img = ImageIO.read(new File(herosprite.currentSpriteGet(animationstage, controller.getCommand())));
			crayon_pac.drawImage(img, heros.X-Labyrinthe.Tile_length/2, heros.Y-Labyrinthe.Tile_length/2+HEIGHT_INTERFACE, heros.X+Labyrinthe.Tile_length/2 , heros.Y+Labyrinthe.Tile_length/2+HEIGHT_INTERFACE, 0, 0, img.getWidth(null), img.getWidth(null), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
}
}
