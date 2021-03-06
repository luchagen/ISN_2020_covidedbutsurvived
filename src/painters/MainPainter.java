package painters;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Labyrinthe.Labyrinthe;
import engine.Game;
import engine.GamePainter;
import model.Bullet;
import model.Item;
import model.Monster;
import model.Pacman;
import model.PacmanController;

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
	public static int WIDTH;
	public static int HEIGHT;
	public static int PLAYABLE_ZONE_WIDTH;
	public static int PLAYABLE_ZONE_HEIGHT;
	/*
	 * Parametres donnant la taille des differentes interfacess (PV+Temps restant/inventaire/boite de dialogue)
	 */
	
	public static int TOP_INTERFACE_HEIGHT;
	public static int TOP_INTERFACE_WIDTH;
	public static int BOTTOM_INTERFACE_HEIGHT;
	public static int RIGHT_INTERFACE_WIDTH;

	public static int inventory_column; //Nombre de colonne
	public static int inventory_row;   //Nombre de ligne
	public static int dec; //Decalage pixel de l'inventaire sur les bords
	public static int inventory_tile_size; //taille en pixel d'une case de l'inventaire
	
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
	BulletPainter bulletpainter;
	InventoryPainter inventoryPainter;
	PacmanController controller;
	Pacman heros;
	ArrayList<Bullet> bullets;
	ArrayList<Monster> monsters;
	ArrayList<Item> item;
	Game game;
	
	
	public MainPainter(PacmanController in_controller, Game in_game) {
		game=in_game;
		heros=game.getHeros();
		item=game.getItems();
		Labyrinthe donjon=game.getDonjon();
		this.defineSizeParameters(donjon);
		monsters=game.getMonsters();
		bullets=game.getBullets();
		herosPainter= new PacmanPainter(heros);
		donjonPainter = new LabyrinthePainter(donjon, PLAYABLE_ZONE_WIDTH, PLAYABLE_ZONE_HEIGHT);
		monsterPainter= new MonsterPainter(monsters,item);
		inventoryPainter = new InventoryPainter(game.getInventory());
		bulletpainter=new BulletPainter(bullets);
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
		Graphics2D crayon_bul = (Graphics2D) im.getGraphics();
		
		this.drawUserInterface(crayon_int);
		donjonPainter.draw(crayon_lab, TOP_INTERFACE_HEIGHT,  animationStage());
		herosPainter.draw(crayon_pac , animationStage(),controller, TOP_INTERFACE_HEIGHT);
		monsterPainter.actualizepainter();
		monsterPainter.draw(crayon_evl,animationStage(), TOP_INTERFACE_HEIGHT);
		bulletpainter.draw(crayon_bul, TOP_INTERFACE_HEIGHT);
		inventoryPainter.draw(crayon_evl);
		if(this.game.isKilled()) {
			try {
				this.notificationMessage(crayon_int,"img/userInterface/deathMessage.png");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if(this.game.isGameOver(this.game.getElapsedTime())) {
			try {
				this.notificationMessage(crayon_int,"img/userInterface/defeatMessage.png");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(this.game.nextlevel() && this.game.getIsLastLevel()) {
			try {
				this.notificationMessage(crayon_int,"img/userInterface/victoryMessage2.png");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
		Image img_inventory_icon;
		int HP=this.heros.getHP();
		int shield = this.heros.getShield();
		int elapsedTime=this.game.getElapsedTime();
		int Time_Limit=this.game.getDonjon().getTime_Limit();
		double sizeHeartParameter=0.6; // Valeur a fixer
		double diffSizeHeartParameter=(1-sizeHeartParameter)/2;
		double sizeRectHeartParameter=(1+sizeHeartParameter)/2;
		int maximumLifePoints=5;
		int origin_mask_x;
		int origin_mask_y;
		Color fond = new Color(153,102,0);
		crayon.setColor(fond);
		crayon.fillRect(0, 0, TOP_INTERFACE_WIDTH, TOP_INTERFACE_HEIGHT);
		Color fond2 = new Color( 246, 158, 32 );
		crayon.setColor(fond2);
		crayon.fillRoundRect((int)((1-sizeRectHeartParameter)*TOP_INTERFACE_HEIGHT/2), (int)((1-sizeRectHeartParameter)*TOP_INTERFACE_HEIGHT/2), (int)(diffSizeHeartParameter+sizeHeartParameter*maximumLifePoints+2)*TOP_INTERFACE_HEIGHT+(int)(1-sizeRectHeartParameter)*TOP_INTERFACE_HEIGHT, (int)(sizeRectHeartParameter*TOP_INTERFACE_HEIGHT), 30,30);
		
		try {
			for(int i=0;i<HP+shield;i++) {
				if(i<HP)
					if ((animationstage+1)/2==animationstage/2)
						if (game.getIscooldownplayerhit())
							img_heart = ImageIO.read(new File("img/userInterface/heartinvis.png")); // les coeurs clignotent quand on vient de se faire taper
						else	
							img_heart = ImageIO.read(new File("img/userInterface/heart.png"));
					else
						img_heart = ImageIO.read(new File("img/userInterface/heart.png"));
				else 
					img_heart = ImageIO.read(new File("img/userInterface/heart2.png"));
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
	    
	    int inventory_origin_x=this.PLAYABLE_ZONE_WIDTH+this.dec;
	    int inventory_origin_y=this.TOP_INTERFACE_HEIGHT+this.dec;
	    crayon.setColor(Color.yellow);
	    for(int i=0;i<this.inventory_column;i++) {
	    	for(int j=0;j<this.inventory_row;j++) {
	    		crayon.drawRect(inventory_origin_x+i*this.inventory_tile_size, inventory_origin_y+j*this.inventory_tile_size, this.inventory_tile_size, this.inventory_tile_size);
	    	}
	    }
	    int inventory_icon_size=32;
	    int inventory_icon_origin_x=inventory_origin_x;
	    int inventory_icon_origin_y=(int)((TOP_INTERFACE_HEIGHT-inventory_icon_size)/2);
	    try {
			img_inventory_icon = ImageIO.read(new File("img/userInterface/inventory_icon.png"));
			crayon.drawImage(img_inventory_icon, inventory_icon_origin_x, inventory_icon_origin_y, inventory_icon_origin_x+inventory_icon_size , inventory_icon_origin_y+inventory_icon_size, 0, 0, img_inventory_icon.getWidth(null), img_inventory_icon.getHeight(null), null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void defineSizeParameters(Labyrinthe donjon) {
		this.PLAYABLE_ZONE_WIDTH=donjon.getNb_largeur()*32;
		this.PLAYABLE_ZONE_HEIGHT=donjon.getNb_hauteur()*32;
		this.dec=10;
		this.inventory_tile_size=40;
			
		this.inventory_column=3;
		this.inventory_row=6;
		
		this.HEIGHT=(int)(this.PLAYABLE_ZONE_HEIGHT/PLAYABLE_ZONE_WIDTH_OCCUPATION_PERCENTAGE);
		System.out.println(HEIGHT);
		this.TOP_INTERFACE_HEIGHT=45; //Valeur a fixer
		this.BOTTOM_INTERFACE_HEIGHT=100;
		this.RIGHT_INTERFACE_WIDTH=inventory_column*inventory_tile_size+2*dec;
		this.WIDTH=PLAYABLE_ZONE_WIDTH+RIGHT_INTERFACE_WIDTH;
		this.HEIGHT=PLAYABLE_ZONE_HEIGHT+TOP_INTERFACE_HEIGHT+BOTTOM_INTERFACE_HEIGHT;
		this.TOP_INTERFACE_WIDTH=this.PLAYABLE_ZONE_WIDTH;
		
	}
	private void notificationMessage(Graphics2D crayon, String source) throws IOException {
		Image img_deathMessage = ImageIO.read(new File(source));
		double percentageOfPlayableZoneWidth=1;
		int imgWidth = (int)(percentageOfPlayableZoneWidth*PLAYABLE_ZONE_WIDTH);
		int imgHeight=(int)(imgWidth*img_deathMessage.getHeight(null)/img_deathMessage.getWidth(null));
		int x0=(int)((PLAYABLE_ZONE_WIDTH-imgWidth)/2);
		int y0=(int)((PLAYABLE_ZONE_HEIGHT-imgHeight)/2);
		int x1=x0+imgWidth;
		int y1=y0+imgHeight;
		Color chatBG = new Color( 230, 203, 163 );
	    crayon.setColor(chatBG);
		crayon.fillRect(x0, y0, x1-x0, y1-y0);
		crayon.drawImage(img_deathMessage, x0,y0, x1, y1, 0, 0, img_deathMessage.getWidth(null), img_deathMessage.getHeight(null), null);
	}
}
