package painters;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Labyrinthe.Labyrinthe;
import model.Bullet;

public class BulletPainter {

	ArrayList<Bullet> bullets;

	public BulletPainter(ArrayList<Bullet> in_bullet) {
		bullets = in_bullet;
	}

	public void draw(Graphics2D crayon_bull, int HEIGHT_INTERFACE) {
		Image img;
		for (Bullet bullet : bullets) {
			try {
				img = ImageIO.read(new File(bullet.getSkin()));
				crayon_bull.drawImage(img, bullet.getX() - Labyrinthe.Tile_length / 2,
						bullet.getY() - Labyrinthe.Tile_length / 2 + HEIGHT_INTERFACE, bullet.getX() + Labyrinthe.Tile_length / 2,
						bullet.getY() + Labyrinthe.Tile_length + HEIGHT_INTERFACE, 0, 0, img.getWidth(null),
						img.getWidth(null), null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
