package painters;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Labyrinthe.Labyrinthe;
import engine.Cmd;
import model.Item;
import model.Monster;
import model.SpriteGroup;

public class MonsterPainter {
	ArrayList<Monster> monstres;
	ArrayList<SpriteGroup> monstersprites = new ArrayList<SpriteGroup>();
	ArrayList<Item> items;
	ArrayList<SpriteGroup> itemsprites = new ArrayList<SpriteGroup>();
	
	
	public MonsterPainter(ArrayList<Monster> in_monstres, ArrayList<Item> in_items) {
		monstres=in_monstres;
		items=in_items;
		for(Monster monster:monstres) {
			SpriteGroup sprite = new SpriteGroup(monster.getSkin());
			monstersprites.add(sprite);
		}
		for(Item item:items) {
			SpriteGroup sprite = new SpriteGroup(item.getSkin());
			itemsprites.add(sprite);
		}
	}
	
	public void actualizepainter() {
		if (monstres.size()!=monstersprites.size()) {
			monstersprites = new ArrayList<SpriteGroup>();
			for(Monster monster:monstres) {
				SpriteGroup sprite = new SpriteGroup(monster.getSkin());
				monstersprites.add(sprite);
			}
		}
		if (items.size()!=itemsprites.size()) {
			for(Item item:items) {
				if(items.indexOf(item)>=itemsprites.size()) {
					SpriteGroup sprite = new SpriteGroup(item.getSkin());
					itemsprites.add(sprite);
				}
			}
		}
			
	}
	
public void draw(Graphics2D crayon_pac, int animationstage, int HEIGHT_INTERFACE) {
	Image img;
	for(int i=0; i<monstres.size(); i++) {
		try {
			img = ImageIO.read(new File(monstersprites.get(i).currentSpriteGet(animationstage,monstres.get(i).getState(),monstres.get(i).getType())));
			crayon_pac.drawImage(img, monstres.get(i).getX()-Labyrinthe.Tile_length/2, monstres.get(i).getY()-Labyrinthe.Tile_length/2+HEIGHT_INTERFACE, monstres.get(i).getX()+Labyrinthe.Tile_length/2 , monstres.get(i).getY()+Labyrinthe.Tile_length/2+HEIGHT_INTERFACE, 0, 0, img.getWidth(null), img.getWidth(null), null);
	} catch (IOException e) {
		// TODO Auto-generated catch block
	e.printStackTrace();
	}
	}
	for(int i=0; i<items.size(); i++) {
		try {
			img = ImageIO.read(new File(itemsprites.get(i).currentSpriteGet(animationstage,items.get(i).getState(),items.get(i).getType())));
			crayon_pac.drawImage(img, items.get(i).getX(), items.get(i).getY()+HEIGHT_INTERFACE, items.get(i).getX()+Labyrinthe.Tile_length , items.get(i).getY()+Labyrinthe.Tile_length+HEIGHT_INTERFACE, 0, 0, img.getWidth(null), img.getWidth(null), null);
	} catch (IOException e) {
		// TODO Auto-generated catch block
	e.printStackTrace();
	}
	}
}
}
