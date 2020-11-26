package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

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
				switch(donjon.cases[i][j].nature) {
					case 0:
						crayon_lab.setColor(Color.black);
						break;
					case 1:
						crayon_lab.setColor(Color.green);
						break;
					case 2:
						crayon_lab.setColor(Color.orange);
						break;
					case 3:
						crayon_lab.setColor(Color.CYAN);
						break;
					case 4:
						crayon_lab.setColor(Color.orange);
						break;
					default:
						crayon_lab.setColor(Color.white);
						break;
					
				}
				crayon_lab.fillRect(largeur_rect*i, hauteur_rect*j, largeur_rect , hauteur_rect);
			}
		}
	}

}
