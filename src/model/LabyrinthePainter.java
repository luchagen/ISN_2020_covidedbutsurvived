package model;


import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class LabyrinthePainter {
	Labyrinthe donjon;
	ArrayList<SpriteGroup> sprites = new ArrayList<SpriteGroup>(); // on dessine avec un seul spritegroup toutes les cases du labyrinthe 
	int largeur_rect, hauteur_rect;
	public LabyrinthePainter(Labyrinthe in_donjon, int WIDTH, int HEIGHT) {
		donjon=in_donjon;
		int nb_largeur=donjon.getNb_largeur();
		int nb_hauteur=donjon.getNb_hauteur();
		largeur_rect= WIDTH/nb_largeur;
		hauteur_rect= HEIGHT/nb_hauteur;
		for(int i=0;i<nb_hauteur;i++) {
			for(int j=0;j<nb_largeur;j++) {
				sprites.add(new SpriteGroup(donjon.cases[i][j].skin)); //si la case testee doit avoir un sprite dynamique, on lui attribue un spritegroup dedie

			}
		}
	}
	public void draw(Graphics2D crayon_lab, int HEIGHT_INTERFACE, int animationstage) {
		int nb_largeur=donjon.getNb_largeur();
		int nb_hauteur=donjon.getNb_hauteur();
		for(int i=0;i<nb_largeur;i++) {
			for (int j=0;j<nb_hauteur;j++) {
				Image img;
				try {
					
						img = ImageIO.read(new File(sprites.get(i*nb_hauteur +j).currentSpriteGet(animationstage, donjon.cases[i][j].state, donjon.cases[i][j].type)));
					crayon_lab.drawImage(img, largeur_rect*i, hauteur_rect*j+HEIGHT_INTERFACE, largeur_rect*(i+1) , hauteur_rect*(j+1)+HEIGHT_INTERFACE, 0, 0, img.getWidth(null), img.getWidth(null), null);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
