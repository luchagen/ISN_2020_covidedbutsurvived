package model;



import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.Game;
import engine.GamePainter;

public class MainPainter implements GamePainter{
	public int animationstage;
	/**
	 * la taille des cases
	 */
	
	protected static final int PLAYABLE_ZONE_WIDTH=320;
	protected static final int PLAYABLE_ZONE_HEIGHT=320;
	protected static final int HEIGHT_INTERFACE=32;
	public static final int WIDTH_INTERFACE=PLAYABLE_ZONE_WIDTH;
	protected static final int HEIGHT = HEIGHT_INTERFACE+PLAYABLE_ZONE_HEIGHT;
	protected static final int WIDTH = PLAYABLE_ZONE_WIDTH;

	/**
	 * appelle constructeur parent
	 * 
	 * @param game
	 *            le jeutest a afficher
	 */
	PacmanPainter herosPainter;
	LabyrinthePainter donjonPainter;
	MonsterPainter monsterPainter;
	PacmanController controller;
	Pacman heros;
	Monster[] monsters;
	Game game;
	
	
	public MainPainter(PacmanController in_controller, Game in_game) {
		game=in_game;
		heros=game.getHeros();
		Labyrinthe donjon=game.getDonjon();
		monsters=game.getMonstres();
		herosPainter= new PacmanPainter(heros);
		donjonPainter = new LabyrinthePainter(donjon, PLAYABLE_ZONE_WIDTH, PLAYABLE_ZONE_HEIGHT);
		monsterPainter= new MonsterPainter(monsters);
		controller = in_controller;
		animationstage=0;
	}

	/** 
	 * on calcule l'etape a laquelle les animations doivent etre
	 */
	public int animationStage() {

		if (animationstage==64)
				animationstage=0;
		else 
			animationstage += 1;
		System.out.print(animationstage);
		return animationstage/2;
			
			
		
	}
	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {
		Graphics2D crayon_int = (Graphics2D) im.getGraphics();
		Graphics2D crayon_pac = (Graphics2D) im.getGraphics();
		Graphics2D crayon_lab = (Graphics2D) im.getGraphics();
		Graphics2D crayon_evl = (Graphics2D) im.getGraphics();
		this.drawUserInterface(crayon_int);
		donjonPainter.draw(crayon_lab, HEIGHT_INTERFACE);
		herosPainter.draw(crayon_pac , animationStage(),controller, HEIGHT_INTERFACE);
		monsterPainter.draw(crayon_evl,animationStage(), HEIGHT_INTERFACE);
		
	}

	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}
	private void drawUserInterface(Graphics2D crayon) {
		Image img_heart;
		Image img_bg;
		int HP=this.heros.getHP();
		int elapsedTime=this.game.getElapsedTime();
		try {
			img_bg = ImageIO.read(new File("img/userInterface/bg.png"));
			int bg_size=img_bg.getWidth(null);
			for(int j=0;j<WIDTH_INTERFACE/bg_size;j++) {
				crayon.drawImage(img_bg, 32*j, 0, 32*(j+1) , 32, 0, 0, img_bg.getWidth(null), img_bg.getWidth(null), null);
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		try {
			for(int i=0;i<HP;i++) {
				img_heart = ImageIO.read(new File("img/userInterface/heart.png"));
				crayon.drawImage(img_heart, 32*i, 0, 32*(i+1) , 32, 0, 0, img_heart.getWidth(null), img_heart.getWidth(null), null);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    crayon.setFont(new Font("TimesRoman", Font.PLAIN, 40));  // Here
	    crayon.drawString(String.valueOf(elapsedTime), WIDTH_INTERFACE-50, 30);
	}
}
