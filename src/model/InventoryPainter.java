package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import items.Inventory;

public class InventoryPainter {
	private Inventory inventory;
	public InventoryPainter(Inventory inventory) {
		this.inventory=inventory;
	}
	public void draw(Graphics2D crayon) {
		int origin_x=MainPainter.PLAYABLE_ZONE_WIDTH+MainPainter.dec;
		int origin_y=MainPainter.TOP_INTERFACE_HEIGHT+MainPainter.dec;
		int item_size=32;
		int dec2=(int)((MainPainter.inventory_tile_size-item_size)/2);
		int img_item_size;
		Image img_item;
		for(int i=0;i<inventory.getMaxNbItem();i++) {
			if(inventory.getItem(i).getItemTypeId()!=0) {
				try {
					img_item=ImageIO.read(new File(inventory.getItem(i).getSource()));
					img_item_size=img_item.getHeight(null);
					crayon.drawImage(img_item, origin_x+dec2, origin_y+dec2, origin_x+img_item_size, origin_y+img_item_size, 0, 0, img_item_size,img_item_size, null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
}
