package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import engine.GamePainter;
//Classe inutile pour le moment
public class LabyrinthePainter implements GamePainter {
	Labyrinthe donjon;
	public LabyrinthePainter(Labyrinthe in_donjon) {
		donjon=in_donjon;
	}
	@Override
	public void draw(BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		crayon.setColor(Color.blue);
		crayon.drawOval(game.heros.X,game.heros.Y,10,10);
		
	}
	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

}
