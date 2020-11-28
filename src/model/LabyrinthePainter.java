package model;


import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//Classe inutile pour le moment
public class LabyrinthePainter {
	Labyrinthe donjon;
	int largeur_rect, hauteur_rect;
	public LabyrinthePainter(Labyrinthe in_donjon, int WIDTH, int HEIGHT) {
		donjon=in_donjon;
		int nb_largeur=donjon.getNb_largeur();
		int nb_hauteur=donjon.getNb_hauteur();
		largeur_rect= WIDTH/nb_largeur;
		hauteur_rect= HEIGHT/nb_hauteur;
	}
	public void draw(Graphics2D crayon_lab) {
		int nb_largeur=donjon.getNb_largeur();
		int nb_hauteur=donjon.getNb_hauteur();
		for(int i=0;i<nb_largeur;i++) {
			for (int j=0;j<nb_hauteur;j++) {
				Image img;
				try {
					img = ImageIO.read(new File(donjon.cases[i][j].skin));
					crayon_lab.drawImage(img, largeur_rect*i, hauteur_rect*j, largeur_rect*(i+1) , hauteur_rect*(j+1), 0, 0, img.getWidth(null), img.getWidth(null), null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
