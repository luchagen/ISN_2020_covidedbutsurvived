package model;



import java.awt.Color;
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
	/**
	 * Parametres de taille donnant le pourcentage de la zone de jeu
	 * dans la fenetre, indifferent quelque soit la taille du labyrinthe
	 * (et donc du nombre de cases) 
	 */
	protected static final double PLAYABLE_ZONE_WIDTH_OCCUPATION_PERCENTAGE=0.7;
	protected static final double PLAYABLE_ZONE_HEIGHT_OCCUPATION_PERCENTAGE=0.7;
	/*
	 * Parametres a definir avec la taille du labyrinthe, sachant qu'une case doit faire 32x32 pixels !
	 */
	protected static int WIDTH;
	protected static int HEIGHT;
	protected static int PLAYABLE_ZONE_WIDTH;
	protected static int PLAYABLE_ZONE_HEIGHT;
	/*
	 * Parametres donnant la taille des differentes interfacess (PV+Temps restant/inventaire/boite de dialogue)
	 */
	
	protected static int TOP_INTERFACE_HEIGHT;
	protected static int TOP_INTERFACE_WIDTH;
	protected static int BOTTOM_INTERFACE_HEIGHT;
	protected static int RIGHT_INTERFACE_WIDTH;


	public int animationstage;
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
		this.defineSizeParameters(donjon);
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
		donjonPainter.draw(crayon_lab, TOP_INTERFACE_HEIGHT);
		herosPainter.draw(crayon_pac , animationStage(),controller,TOP_INTERFACE_HEIGHT);
		monsterPainter.draw(crayon_evl,animationStage(), TOP_INTERFACE_HEIGHT);
		
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
		int Time_Limit=this.game.getDonjon().getTime_Limit();
		double sizeHeartParameter=0.6;
		double diffSizeHeartParameter=(1-sizeHeartParameter)/2;
		double sizeRectHeartParameter=(1+sizeHeartParameter)/2;
		int maximumLifePoints=5;
		Color fond = new Color(153,102,0);
		crayon.setColor(fond);
		crayon.fillRect(0, 0, TOP_INTERFACE_WIDTH, TOP_INTERFACE_HEIGHT);
		Color fond2 = new Color( 246, 158, 32 );
		crayon.setColor(fond2);
		crayon.fillRoundRect((int)((1-sizeRectHeartParameter)*TOP_INTERFACE_HEIGHT/2), (int)((1-sizeRectHeartParameter)*TOP_INTERFACE_HEIGHT/2), (int)(diffSizeHeartParameter+sizeHeartParameter*maximumLifePoints+1)*TOP_INTERFACE_HEIGHT+(int)(1-sizeRectHeartParameter)*TOP_INTERFACE_HEIGHT, (int)(sizeRectHeartParameter*TOP_INTERFACE_HEIGHT), 30,30);
		
		try {
			for(int i=0;i<HP;i++) {
				img_heart = ImageIO.read(new File("img/userInterface/heart.png"));
				crayon.drawImage(img_heart, (int)((diffSizeHeartParameter+sizeHeartParameter*i)*TOP_INTERFACE_HEIGHT), (int)(diffSizeHeartParameter*TOP_INTERFACE_HEIGHT), (int)((diffSizeHeartParameter+sizeHeartParameter*(i+1))*TOP_INTERFACE_HEIGHT) , (int)((1-diffSizeHeartParameter)*TOP_INTERFACE_HEIGHT), 0, 0, img_heart.getWidth(null), img_heart.getWidth(null), null);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		crayon.setColor(Color.black);
	    crayon.setFont(new Font("Serif", Font.PLAIN, 40));  // Here
	    crayon.drawString(String.valueOf(Time_Limit-elapsedTime), TOP_INTERFACE_WIDTH-60 , TOP_INTERFACE_HEIGHT-10);
	    Color inventoryBG = new Color( 146, 114, 68 );
	    crayon.setColor(inventoryBG);
	    crayon.fillRect(TOP_INTERFACE_WIDTH, 0, RIGHT_INTERFACE_WIDTH, HEIGHT-BOTTOM_INTERFACE_HEIGHT);
	    Color chatBG = new Color( 230, 203, 163 );
	    crayon.setColor(chatBG);
	    crayon.fillRect(0,HEIGHT-BOTTOM_INTERFACE_HEIGHT, WIDTH, BOTTOM_INTERFACE_HEIGHT);
	}
	private void defineSizeParameters(Labyrinthe donjon) {
		this.PLAYABLE_ZONE_WIDTH=donjon.getNb_largeur()*32;
		this.PLAYABLE_ZONE_HEIGHT=donjon.getNb_hauteur()*32;
		System.out.println(PLAYABLE_ZONE_HEIGHT);
		this.WIDTH=(int)(this.PLAYABLE_ZONE_WIDTH/PLAYABLE_ZONE_WIDTH_OCCUPATION_PERCENTAGE);

		this.HEIGHT=(int)(this.PLAYABLE_ZONE_HEIGHT/PLAYABLE_ZONE_WIDTH_OCCUPATION_PERCENTAGE);
		System.out.println(HEIGHT);
		this.TOP_INTERFACE_HEIGHT=45;
		this.BOTTOM_INTERFACE_HEIGHT=this.HEIGHT-this.PLAYABLE_ZONE_HEIGHT-this.TOP_INTERFACE_HEIGHT;
		this.RIGHT_INTERFACE_WIDTH=this.WIDTH-this.PLAYABLE_ZONE_WIDTH;
		this.TOP_INTERFACE_WIDTH=this.PLAYABLE_ZONE_WIDTH;
		
	}
}
