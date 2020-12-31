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
	ArrayList<Item> items;
	ArrayList<SpriteGroup> itemsprites = new ArrayList<SpriteGroup>();
	
	
	public MonsterPainter(ArrayList<Monster> in_monstres, ArrayList<Item> in_items) {
		monstres=in_monstres;
		items=in_items;
		for(Monster monster:monstres) {
			SpriteGroup sprite = new SpriteGroup(monster.skin);
			monstersprites.add(sprite);
		}
		for(Item item:items) {
			SpriteGroup sprite = new SpriteGroup(item.skin);
			itemsprites.add(sprite);
		}
	}
	
public void draw(Graphics2D crayon_pac, int animationstage, int HEIGHT_INTERFACE) {
	Cmd commandeencours = Cmd.IDLE ;
	Image img;
	for(int i=0; i<monstres.size(); i++) {
		try {
			img = ImageIO.read(new File(monstersprites.get(i).currentSpriteGet(animationstage,monstres.get(i).State,monstres.get(i).type)));
			crayon_pac.drawImage(img, monstres.get(i).X, monstres.get(i).Y+HEIGHT_INTERFACE, monstres.get(i).X+Labyrinthe.Tile_length , monstres.get(i).Y+Labyrinthe.Tile_length+HEIGHT_INTERFACE, 0, 0, img.getWidth(null), img.getWidth(null), null);
	} catch (IOException e) {
		// TODO Auto-generated catch block
	e.printStackTrace();
	}
	}
	for(int i=0; i<items.size(); i++) {
		try {
			img = ImageIO.read(new File(itemsprites.get(i).currentSpriteGet(animationstage,items.get(i).state,items.get(i).type)));
			crayon_pac.drawImage(img, items.get(i).X, items.get(i).Y+HEIGHT_INTERFACE, items.get(i).X+Labyrinthe.Tile_length , items.get(i).Y+Labyrinthe.Tile_length+HEIGHT_INTERFACE, 0, 0, img.getWidth(null), img.getWidth(null), null);
	} catch (IOException e) {
		// TODO Auto-generated catch block
	e.printStackTrace();
	}
	}
}
}
