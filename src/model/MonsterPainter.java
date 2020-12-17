package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import engine.Cmd;

public class MonsterPainter {
	Monster[] monstres;
	ArrayList<SpriteGroup> monstersprites = new ArrayList<SpriteGroup>();
	
	public MonsterPainter(Monster[] in_monstres) {
		monstres=in_monstres;
		for(int i=0; i<monstres.length; i++) {
			SpriteGroup sprite = new SpriteGroup(monstres[i].skin);
			monstersprites.add(sprite);
		}
	}
	
public void draw(Graphics2D crayon_pac, int animationstage) {
	Cmd commandeencours = Cmd.IDLE ;
	Image img;
	for(int i=0; i<monstres.length; i++) {
		try {
			img = ImageIO.read(new File(monstersprites.get(i).currentSpriteGet(animationstage,commandeencours)));
			crayon_pac.drawImage(img, monstres[i].X, monstres[i].Y, monstres[i].X+Labyrinthe.Tile_length , monstres[i].Y+Labyrinthe.Tile_length, 0, 0, img.getWidth(null), img.getWidth(null), null);
	} catch (IOException e) {
		// TODO Auto-generated catch block
	e.printStackTrace();
	}
	}
}
}
