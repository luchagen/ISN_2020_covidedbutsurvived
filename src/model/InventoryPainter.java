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
		this.inventory = inventory;
	}

	public void draw(Graphics2D crayon) {
		int origin_x = MainPainter.PLAYABLE_ZONE_WIDTH + MainPainter.dec;
		int origin_y = MainPainter.TOP_INTERFACE_HEIGHT + MainPainter.dec;
		int item_size = 32;
		int dec2 = (int) ((MainPainter.inventory_tile_size - item_size) / 2);
		int img_item_size;
		int coord_tab_x;
		int coord_tab_y;
		Image img_item;
		for (int i = 0; i < inventory.getMaxNbItem(); i++) {
			if (inventory.getItem(i).getItemTypeId() != 0) {
				try {
					coord_tab_y = (int) (i / MainPainter.inventory_column);
					coord_tab_x = (int) (i % MainPainter.inventory_column);
					img_item = ImageIO.read(new File(inventory.getItem(i).getSource()));
					img_item_size = img_item.getHeight(null);
					crayon.drawImage(img_item, origin_x + MainPainter.inventory_tile_size * coord_tab_x + dec2,
							origin_y + MainPainter.inventory_tile_size * coord_tab_y + dec2,
							origin_x + MainPainter.inventory_tile_size * coord_tab_x + img_item_size + dec2,
							origin_y + MainPainter.inventory_tile_size * coord_tab_y + img_item_size + dec2, 0, 0,
							img_item_size, img_item_size, null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}
}
