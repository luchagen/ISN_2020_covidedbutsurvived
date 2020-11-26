package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.GamePainter;
//Classe inutile pour le moment
public class LabyrinthePainter {
	Labyrinthe donjon;
	int largeur_rect, hauteur_rect;
	public LabyrinthePainter(Labyrinthe in_donjon, int WIDTH, int HEIGHT) {
		donjon=in_donjon;
		largeur_rect= WIDTH/donjon.nb_largeur;
		hauteur_rect= HEIGHT/donjon.nb_hauteur;
	}
	public void draw(Graphics2D crayon_lab) {
		for(int i=0;i<donjon.nb_largeur;i++) {
			for (int j=0;j<donjon.nb_hauteur;j++) {
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
