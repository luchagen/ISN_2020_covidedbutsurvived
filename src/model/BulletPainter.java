package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BulletPainter {

//Bullet bullet;
//public BulletPainter(Bullet in_bullet) {
	//bullet=in_bullet;}
	
	
	
	public  void draw(Graphics2D crayon_bull, int HEIGHT_INTERFACE,Bullet bullet) {
		Image img;
		try {
			img = ImageIO.read(new File(bullet.skin));
			crayon_bull.drawImage(img, bullet.X, bullet.Y+HEIGHT_INTERFACE, bullet.X+Labyrinthe.Tile_length , bullet.Y+Labyrinthe.Tile_length+HEIGHT_INTERFACE, 0, 0, img.getWidth(null), img.getWidth(null), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
}
}
