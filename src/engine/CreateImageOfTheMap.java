package engine;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Labyrinthe.Labyrinthe;
import model.SpriteGroup;
import painters.LabyrinthePainter;
import painters.MainPainter;
 
public class CreateImageOfTheMap {
	Labyrinthe donjon;
	ArrayList<SpriteGroup> sprites = new ArrayList<SpriteGroup>();
	int[][] spritegroupindex;
	int largeur_rect, hauteur_rect;
	int width, height;
	int origin_x, origin_y;
	
	
	public CreateImageOfTheMap(Labyrinthe in_donjon, int WIDTH, int HEIGHT) {
		donjon = in_donjon;
		int nb_largeur = donjon.getNb_largeur();
		int nb_hauteur = donjon.getNb_hauteur();
		spritegroupindex = new int[nb_hauteur][nb_largeur];
		largeur_rect = 32;
		hauteur_rect = 32;
		for (int i = 0; i < nb_hauteur; i++) {
			for (int j = 0; j < nb_largeur; j++) {
				sprites.add(new SpriteGroup(donjon.cases[i][j].getSkin()));

			}
		}
		this.width=WIDTH;
		this.height=HEIGHT;
		this.origin_x=0;
		this.origin_y=MainPainter.TOP_INTERFACE_HEIGHT;
	}
 
    public BufferedImage createNew() throws IOException {
 
 
        // Constructs a BufferedImage of one of the predefined image types.
        BufferedImage bufferedImage = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
 
        // Create a graphics which can be used to draw into the buffered image
        Graphics2D g2d = bufferedImage.createGraphics();
        
        int nb_largeur = donjon.getNb_largeur();
		int nb_hauteur = donjon.getNb_hauteur();
		for(int i=0;i<Labyrinthe.nb_largeur;i++) {
			for (int j=0;j<Labyrinthe.nb_hauteur;j++) {
				Image img;
				try {
					img = ImageIO.read(new File(donjon.cases[i][j].findSource(donjon.cases[i][j].getType())));
					
					g2d.drawImage(img, origin_x+largeur_rect*i, origin_y+hauteur_rect*j, origin_x+largeur_rect*(i+1) , origin_y+hauteur_rect*(j+1), 0, 0, img.getWidth(null), img.getWidth(null), null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
        g2d.dispose();
        return bufferedImage;
 
    }
 
}