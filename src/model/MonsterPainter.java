package model;

import java.awt.image.BufferedImage;

import engine.GamePainter;

public class MonsterPainter implements GamePainter {
	Monster[] monstres;
	public MonsterPainter(Monster[] in_monstres) {
		monstres=in_monstres;
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
