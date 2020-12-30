package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ItemPainter {
	Item Item;
	int etat;
	
	public  ItemPainter(Item in_Item)
	 {Item=in_Item;
	
}
	
	public void draw(Graphics2D crayon_item, int HEIGHT_INTERFACE,int etat) {
		Image img;
		try {
			img = ImageIO.read(new File("img/Items/"+etat+".png"));
			crayon_item.drawImage(img, Item.X,Item.Y+HEIGHT_INTERFACE, Item.X+Labyrinthe.Tile_length , Item.Y+Labyrinthe.Tile_length+HEIGHT_INTERFACE, 0, 0, img.getWidth(null), img.getWidth(null), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
}}