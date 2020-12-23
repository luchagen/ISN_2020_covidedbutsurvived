package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import engine.Cmd;

public class MonsterPainter {
	ArrayList<Monster> monstres;
	ArrayList<SpriteGroup> monstersprites = new ArrayList<SpriteGroup>();
	
	public MonsterPainter(ArrayList<Monster> in_monstres) {
		monstres=in_monstres;
		for(Monster monster:monstres) {
			SpriteGroup sprite = new SpriteGroup(monster.skin);
			monstersprites.add(sprite);
		}
	}
	
public void draw(Graphics2D crayon_pac, int animationstage, int HEIGHT_INTERFACE) {
	Cmd commandeencours = Cmd.IDLE ;
	Image img;
	for(int i=0; i<monstres.size(); i++) {
		try {
			img = ImageIO.read(new File(monstersprites.get(i).currentSpriteGet(animationstage,commandeencours)));
			crayon_pac.drawImage(img, monstres.get(i).X, monstres.get(i).Y+HEIGHT_INTERFACE, monstres.get(i).X+Labyrinthe.Tile_length , monstres.get(i).Y+Labyrinthe.Tile_length+HEIGHT_INTERFACE, 0, 0, img.getWidth(null), img.getWidth(null), null);
	} catch (IOException e) {
		// TODO Auto-generated catch block
	e.printStackTrace();
	}
	}
}
}
