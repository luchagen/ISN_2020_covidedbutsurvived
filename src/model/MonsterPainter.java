package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.GamePainter;

public class MonsterPainter {
	Monster[] monstres;
	
	public MonsterPainter(Monster[] in_monstres) {
		monstres=in_monstres;
	}
	
public void draw(Graphics2D crayon_pac) {
	Image img;
	for(int i=0; i<monstres.length; i++) {
		try {
			img = ImageIO.read(new File(monstres[i].skin));
			crayon_pac.drawImage(img, monstres[i].X, monstres[i].Y, monstres[i].X+Labyrinthe.Tile_length , monstres[i].Y+Labyrinthe.Tile_length, 0, 0, img.getWidth(null), img.getWidth(null), null);
	} catch (IOException e) {
		// TODO Auto-generated catch block
	e.printStackTrace();
	}
	}
}
}
