package model;

import java.awt.image.BufferedImage;

import engine.GamePainter;

public class LabyrinthePainter implements GamePainter {
	Labyrinthe donjon;
	public LabyrinthePainter(Labyrinthe in_donjon) {
		donjon=in_donjon;
	}
	@Override
	public void draw(BufferedImage image) {
		// TODO Auto-generated method stub
		
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
