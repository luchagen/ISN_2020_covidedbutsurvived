package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import Labyrinthe.Labyrinthe;
import Labyrinthe.Tile;
import engine.Cmd;
import engine.Game;
import items.DistanceWeapon;
import items.Heal;
import items.Inventory;
import items.Shield;
/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 *         Version avec personnage qui peut se deplacer. A completer dans les
 *         versions suivantes.
 * 
 */
public class PacmanGame implements Game {

	/**
	 * constructeur avec fichier source pour le help
	 * 
	 */
	protected static final int game_speed=(int) (Labyrinthe.Tile_length/4);
	private Pacman heros;
	private Labyrinthe donjon;
	public ArrayList<Monster> monstres;
	private ArrayList<Bullet> bullets;
	private ArrayList<Item> items;
	
	
	private int cooldownleftonbullet=0;
	private int playerhitcooldown=0;
	
	private Inventory inventory;
	int gamecounter=0;
	private int elapsedTime;
	private boolean isTimeElapsed;
	private boolean isLastLevel;
	
	public PacmanGame(Pacman in_heros,Labyrinthe in_donjon,ArrayList<Monster> in_monstres) {
		heros=in_heros;
		donjon=in_donjon;
		monstres=in_monstres;
	}
	
	public PacmanGame(String source, Pacman in_heros, Labyrinthe in_donjon, ArrayList<Monster> in_monstres, ArrayList<Item> in_items) { 
		BufferedReader helpReader;
		try {
			helpReader = new BufferedReader(new FileReader(source));
			String ligne;
			while ((ligne = helpReader.readLine()) != null) {
				System.out.println(ligne);
			}
			helpReader.close();
		} catch (IOException e) {
			System.out.println("Help not available");
		}
		heros=in_heros;
		donjon=in_donjon;
		monstres=in_monstres;
		inventory=heros.getInventory();
		isTimeElapsed=false;
		bullets=new ArrayList<Bullet>();
		items=in_items;
	}
	
	private void coolDown() {
		if (cooldownleftonbullet!=0)
			cooldownleftonbullet-=1;
		if (playerhitcooldown!=0)
			playerhitcooldown-=1;
	}
	/**
	 * faire evoluer le jeu suite a une commande
	 * 
	 * @param commande
	 */
	@Override
	public void evolve(Cmd commande) {
		coolDown();
		//System.out.println("Execute" + commande);
		int Xw = heros.personnagehitbox.getXleft()+game_speed ;
		int Xe = heros.personnagehitbox.getXright()-game_speed ;
		int Yn = heros.personnagehitbox.getYup()+game_speed ;
		int Ys = heros.personnagehitbox.getYdown()-game_speed ;
		int tilelength= Labyrinthe.Tile_length;
		
		switch (commande) {
		
		case RIGHT:
			if (((Xe+game_speed)<(Labyrinthe.nb_largeur*tilelength))&& (donjon.cases[(Xe+game_speed)/tilelength][Yn/tilelength].isCanWalkOn()) && (donjon.cases[(Xe+game_speed)/tilelength][Ys/tilelength].isCanWalkOn()) )
				heros.moveRIGHT();
			break;
		case LEFT:
			if(((Xw-game_speed)>0)&& (donjon.cases[(Xw-game_speed)/tilelength][Yn/tilelength].isCanWalkOn()) && (donjon.cases[(Xw-game_speed)/tilelength][Ys/tilelength].isCanWalkOn()) )
				heros.moveLEFT();
			break;
		case UP:
			if(((Yn-game_speed)>0)&& (donjon.cases[Xw/tilelength][(Yn-game_speed)/tilelength].isCanWalkOn()) && (donjon.cases[Xe/tilelength][(Yn-game_speed)/tilelength].isCanWalkOn()))
				heros.moveUP();
			break;
		case DOWN:
			if(((Ys+2*game_speed)<(Labyrinthe.nb_hauteur*tilelength))&& (donjon.cases[Xw/tilelength][(Ys+game_speed)/tilelength].isCanWalkOn()) && (donjon.cases[Xe/tilelength][(Ys+game_speed)/tilelength].isCanWalkOn()))
				heros.moveDOWN();
			break;
		case STERILIZE:
			this.pacmanKick();
			break;
		case SHOUT:
			if (cooldownleftonbullet==0 && heros.haveweapon==true) {
				bullets.add(new Bullet(heros));
				cooldownleftonbullet=10;
			}
			break;
		case HEAL:
			heros.useHeal();
			break;
		case SHIELD:
			heros.useShield();
			break;
		default:
			break;
		}
}
	
	public void evolveBullets() {
		if(heros.haveweapon==true) {
			for(Bullet bullet:bullets ) {
				switch (bullet.direction) {
				case 'R':
					if((bullet.Xtile+1<Labyrinthe.nb_largeur) && (donjon.cases[bullet.Xtile+1][bullet.Ytile].isCanWalkOn()))
						bullet.evolveBullet();
					else
						bullet.isTerminal=true;;
					break;
				case 'L':
					if((bullet.Xtile-1>=0) && (donjon.cases[bullet.Xtile-1][bullet.Ytile].isCanWalkOn()))
						bullet.evolveBullet();
					else
						bullet.isTerminal=true;;
					break;
				case 'U':
					if((bullet.Ytile-1>=0) && (donjon.cases[bullet.Xtile][bullet.Ytile-1].isCanWalkOn()))
						bullet.evolveBullet();
					else
						bullet.isTerminal=true;;
					break;
				case 'D':
					if((bullet.Ytile+1<Labyrinthe.nb_hauteur) && (donjon.cases[bullet.Xtile][bullet.Ytile+1].isCanWalkOn()))
						bullet.evolveBullet();
					else
						bullet.isTerminal=true;;
					break;
			}
			}

		int i=0;
		while(i<bullets.size()) {
			if(bullets.get(i).isTerminal) {
				bullets.remove(i);
			}
			else i++;
		}
	}}
	
	public void pacmanKick() {
		for(Monster monster:monstres) {
			if((monster.X/Labyrinthe.Tile_length==heros.X/Labyrinthe.Tile_length)&&(monster.Y/Labyrinthe.Tile_length==heros.Y/Labyrinthe.Tile_length)) { 
				monster.loseHP();
				if(monster.getHP()==0) {
					monstres.remove(monster);
				}
				break;
			}
		}
	}
	
	public void bulletsKillMonsters() {
		for (Bullet bullet:bullets) {
			for (Monster monster:monstres) {
				if(monster.personnagehitbox.isCollision(bullet.bullethitbox)) { 
					monster.loseHP();
					bullet.isTerminal=true;
					if(monster.getHP()==0) {
						monstres.remove(monster);
						Random rnd= new Random();
						float randomizedchance=rnd.nextFloat();
						System.out.println(randomizedchance);
						if (randomizedchance<0.5) {
						int[] information= new int[3];
							if (monster.getType().contentEquals("covid")) {
								information[0]=monster.getX()/Labyrinthe.Tile_length;
								information[1]=monster.getY()/Labyrinthe.Tile_length;
								information[2]=701;		
								Item droppeditem=new Item(information);
								items.add(droppeditem);
							}
							else if (monster.getType().contentEquals("pq")) {
								information[0]=monster.getX()/Labyrinthe.Tile_length;
								information[1]=monster.getY()/Labyrinthe.Tile_length;
								information[2]=702;		
								Item droppeditem=new Item(information);
								items.add(droppeditem);
							}
						}
					}
					break;
				}
			}
		}
		int i=0;
		while(i<bullets.size()) {
			if(bullets.get(i).isTerminal) {
				bullets.remove(i);
			}
			else i++;
		}
	}
	
	@Override
	public void evolveMonsters() {
		ArrayList<Monster> monstresaajouter = new ArrayList<Monster>();
		ArrayList<Monster> monstresatuer = new ArrayList<Monster>();
		for(Monster monster:monstres) {
			Boolean canmoveright=false;
			Boolean canmoveleft=false;
			Boolean canmoveup=false;
			Boolean canmovedown=false;
			int Xw = monster.personnagehitbox.getXleft()+game_speed ;
			int Xe = monster.personnagehitbox.getXright()-game_speed ;
			int Yn = monster.personnagehitbox.getYup()+game_speed ;
			int Ys = monster.personnagehitbox.getYdown()-game_speed ;
			int tilelength= Labyrinthe.Tile_length;
			if (((Xe+game_speed)<(Labyrinthe.nb_largeur*tilelength))&& (donjon.cases[(Xe+game_speed)/tilelength][Yn/tilelength].isCanWalkOn()) && (donjon.cases[(Xe+game_speed)/tilelength][Ys/tilelength].isCanWalkOn()) )
					if(monster.type.equals("covid"))canmoveright=true; //le pacman peut bouger si il ny a pas dobstacle
					else if(monster.type.equals("pq"))canmoveright=true;
					else if(monster.type.equals("police"))if(monster.Y/tilelength==heros.Y/tilelength && monster.X<heros.X)canmoveright=true; //la police bouge si elle voit le heros
			if(((Xw-game_speed)>0)&& (donjon.cases[(Xw-game_speed)/tilelength][Yn/tilelength].isCanWalkOn()) && (donjon.cases[(Xw-game_speed)/tilelength][Ys/tilelength].isCanWalkOn()) )
					if(monster.type.equals("covid"))canmoveleft=true;
					else if(monster.type.equals("pq"))canmoveleft=true;
					else if(monster.type.equals("police"))if(monster.Y/tilelength==heros.Y/tilelength && monster.X>heros.X)canmoveleft=true;
			if(((Yn-game_speed)>0)&& (donjon.cases[Xw/tilelength][(Yn-game_speed)/tilelength].isCanWalkOn()) && (donjon.cases[Xe/tilelength][(Yn-game_speed)/tilelength].isCanWalkOn()))
					if(monster.type.equals("covid"))canmoveup=true;
					else if(monster.type.equals("police"))if(monster.X/tilelength==heros.X/tilelength && monster.Y>heros.Y)canmoveup=true;
			if(((Ys+2*game_speed)<(Labyrinthe.nb_hauteur*tilelength))&& (donjon.cases[Xw/tilelength][(Ys+game_speed)/tilelength].isCanWalkOn()) && (donjon.cases[Xe/tilelength][(Ys+game_speed)/tilelength].isCanWalkOn()))
					if(monster.type.equals("covid"))canmovedown=true;
					else if(monster.type.equals("police"))if(monster.X/tilelength==heros.X/tilelength && monster.Y<heros.Y)canmovedown=true;
			monster.monsterMove(canmoveleft,canmoveright,canmoveup,canmovedown);
		}

	}
	
	/*
	 * Applying damages
	 */
	@Override
	public void isBeingTouchedByAMonster(){
		if (playerhitcooldown==0)
			for(Monster monster:monstres) {
				if((monster.personnagehitbox.isCollision(heros.personnagehitbox))) { //Changer ca en mettant une methode qui detecte si les persos partage la meme hitbox
					heros.loseHP();
					System.out.println("AIE J'AI PRIS UN COUP!");	
					playerhitcooldown=20;
			}
		}
	}
	
	
	@Override
	public void applyTrapDamage() {
		Tile currentTile=donjon.cases[heros.X/Labyrinthe.Tile_length][heros.Y/Labyrinthe.Tile_length];
		
		if((currentTile.getNature()==6) && (currentTile.getState()!=Cmd.ACTIVATED)) {
			currentTile.setSkin(currentTile.findSource(currentTile.getTrapSkin()));
			currentTile.setState(Cmd.ACTIVATED);
			heros.takeDamage(currentTile.getDamage());
			System.out.print("Oups! It's a "+currentTile.getTrapType()+" trap!\n");
			System.out.print("HP remained: "+heros.getHP()+"\n");
		}
	}
	
	/**
	 *verifier si on peut passer au prochain niveau
	 */
	public boolean nextlevel() {
		return donjon.cases[heros.X/Labyrinthe.Tile_length][heros.Y/Labyrinthe.Tile_length].isNextlevel();
	}

	/**
	 * verifier si le jeu est fini
	 */
	@Override
	public boolean isFinished() {
		return donjon.cases[heros.X/Labyrinthe.Tile_length][heros.Y/Labyrinthe.Tile_length].isCanFinishGame();		
	}
	
	public void getweapon() {
		if(!this.inventory.isFull()) {
			boolean isItemAdded=false;
			for(int i=0;i<items.size();i++) {
				if((heros.X/Labyrinthe.Tile_length==items.get(i).Xmid)&&(heros.Y/Labyrinthe.Tile_length==items.get(i).Ymid)&&items.get(i).state!=Cmd.ACTIVATED){
					switch(items.get(i).type) {
						case "weapon":
							isItemAdded=this.inventory.addItem(new DistanceWeapon());
							heros.haveweapon=true;
							break;
						case "potion":
							isItemAdded=this.inventory.addItem(new Heal());
							break;
						case "shield":
							isItemAdded=this.inventory.addItem(new Shield());
							break;
					}
					if(isItemAdded)
						System.out.println("OH NOOOOOONNNNNN");
						items.get(i).state=Cmd.ACTIVATED;
				}
			}
		}
	}

	@Override
	public boolean isTimeOver(long elapsedtime) {
		return (elapsedtime > donjon.getTime_Limit());
	}
	
	@Override
	public boolean isKilled() {
		return (heros.getHP()<=0);
	}
	
	@Override
	public boolean isGameOver(long elapsedtime) {
		return (this.isFinished() || this.isTimeOver(elapsedtime) || this.isKilled());
	}
	
	/*
	 * getters & setters
	 */

	public void setNewLevel(Pacman in_heros, Labyrinthe in_donjon, ArrayList<Monster> in_monstres) {
		this.heros=in_heros;
		this.donjon=in_donjon;
		this.monstres=in_monstres;
	}

	public void setElapsedTime(int elapsedTime) {
		this.elapsedTime=elapsedTime;
	}
	public int getElapsedTime() {
		return this.elapsedTime;
	}

	public static int getGameSpeed() {
		return game_speed;
	}

	public Pacman getHeros() {
		return heros;
	}
	public ArrayList<Item> getItems() {
		return items;
	}
	
	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

	public Labyrinthe getDonjon() {
		return donjon;
	}

	public ArrayList<Monster> getMonsters() {
		return monstres;
	}
	public int getGamecounter() {
		return gamecounter;
	}
	public boolean isTimeElapsed() {
		return this.isTimeElapsed;
	}
	public void setIsTimeElapsed(boolean value) {
		this.isTimeElapsed=value;
	}
	public boolean getIsLastLevel() {
		return this.isLastLevel;
	}
	public void setIsLastLevel(boolean value) {
		this.isLastLevel=value;
	}
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory newInventory) {
		this.inventory=newInventory;
	}
	public Boolean getIscooldownweapon() {
		if (cooldownleftonbullet!=0)
			return true;
		return false;
	}
	public Boolean getIscooldownplayerhit() {
		if (playerhitcooldown!=0)
			return true;
		return false;
	}
	}
	
