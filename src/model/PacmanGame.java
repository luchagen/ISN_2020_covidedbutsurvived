package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import engine.Cmd;
import engine.Game;
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
	private ArrayList<Monster> monsters;
	private ArrayList<Bullet> bullets;
	int gamecounter=0;
	private int elapsedTime;
	
	
	public PacmanGame(String source, Pacman in_heros, Labyrinthe in_donjon, ArrayList<Monster> in_monstres) { 
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
		monsters=in_monstres;
		bullets=new ArrayList<Bullet>();
	}
	

	/**
	 * faire evoluer le jeu suite a une commande
	 * 
	 * @param commande
	 */
	@Override
	public void evolve(Cmd commande) {
		System.out.println("Execute "+commande);
		switch (commande) {
		case RIGHT:
			if((heros.Xet<Labyrinthe.nb_largeur) && (donjon.cases[heros.Xet][heros.Yn].canWalkOn) && (donjon.cases[heros.Xet][heros.Ys].canWalkOn) )
				heros.moveRIGHT();
			else
				heros.collisionRIGHT();
			break;
		case LEFT:
			if(((heros.Xwtwest)>0) && (donjon.cases[heros.Xwt][heros.Yn].canWalkOn) && (donjon.cases[heros.Xwt][heros.Ys].canWalkOn) )
				heros.moveLEFT();
			else
				heros.collisionLEFT();
			break;
		case UP:
			if(((heros.Yntnorth)>0) && (donjon.cases[heros.Xw][heros.Ynt].canWalkOn) && (donjon.cases[heros.Xe][heros.Ynt].canWalkOn))
				heros.moveUP();
			else
				heros.collisionUP();
			break;
		case DOWN:
			if(((heros.Yst)<Labyrinthe.nb_hauteur) && (donjon.cases[heros.Xw][heros.Yst].canWalkOn) && (donjon.cases[heros.Xe][heros.Yst].canWalkOn))
				heros.moveDOWN();
			else
				heros.collisionDOWN();
			break;
		case STERILIZE:
			this.pacmanKick();
			break;
		case SHOUT:
			bullets.add(new Bullet(heros));
			break;
		case IDLE:
			break;
		}
}
	
	public void evolveBullets() {
		for(Bullet bullet:bullets ) {
			switch (bullet.direction) {
			case 'R':
				if((bullet.Xtile+1<Labyrinthe.nb_largeur) && (donjon.cases[bullet.Xtile+1][bullet.Ytile].canWalkOn))
					bullet.evolveBullet();
				else
					bullet.isTerminal=true;;
				break;
			case 'L':
				if((bullet.Xtile-1>=0) && (donjon.cases[bullet.Xtile-1][bullet.Ytile].canWalkOn))
					bullet.evolveBullet();
				else
					bullet.isTerminal=true;;
				break;
			case 'U':
				if((bullet.Ytile-1>=0) && (donjon.cases[bullet.Xtile][bullet.Ytile-1].canWalkOn))
					bullet.evolveBullet();
				else
					bullet.isTerminal=true;;
				break;
			case 'D':
				if((bullet.Ytile+1<Labyrinthe.nb_hauteur) && (donjon.cases[bullet.Xtile][bullet.Ytile+1].canWalkOn))
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
	}
	
	public void pacmanKick() {
		for(Monster monster:monsters) {
			if((monster.Xmid==heros.Xmid)&&(monster.Ymid==heros.Ymid)) { 
				monster.loseHP();
				if(monster.getHP()==0) {
					monsters.remove(monster);
				}
				break;
			}
		}
	}
	
	public void bulletsKillMonsters() {
		for (Bullet bullet:bullets) {
			for (Monster monster:monsters) {
				if((monster.Xmid==bullet.Xtile)&&(monster.Ymid==bullet.Ytile)) { 
					monster.loseHP();
					bullet.isTerminal=true;
					if(monster.getHP()==0) {
						monsters.remove(monster);
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
		for(Monster monster:monsters) {
			Boolean canmoveright=false;
			Boolean canmoveleft=false;
			Boolean canmoveup=false;
			Boolean canmovedown=false;
			if(monster.Xet<Labyrinthe.nb_largeur) 
				if((donjon.cases[monster.Xet][monster.Yn].canWalkOn) && (donjon.cases[monster.Xet][monster.Ys].canWalkOn)) 
					canmoveright=true;
			if((monster.Xwtwest)>0) 
				if((donjon.cases[monster.Xwt][monster.Yn].canWalkOn) && (donjon.cases[monster.Xwt][monster.Ys].canWalkOn) )
					canmoveleft=true;
			if((monster.Yntnorth)>0) 
				if((donjon.cases[monster.Xw][monster.Ynt].canWalkOn) && (donjon.cases[monster.Xe][monster.Ynt].canWalkOn))
					canmoveup=true;
			if((monster.Yst)<Labyrinthe.nb_hauteur)
				if((donjon.cases[monster.Xw][monster.Yst].canWalkOn) && (donjon.cases[monster.Xe][monster.Yst].canWalkOn))
					canmovedown=true;
			monster.monsterMove(canmoveleft,canmoveright,canmoveup,canmovedown);
	        	
			}	
	}
	
	/*
	 * Applying damages
	 */
	@Override
	public void isBeingTouchedByAMonster() {
		for(Monster monster:monsters) {
			if((monster.Xmid==heros.Xmid)&&(monster.Ymid==heros.Ymid)) { //Changer ca en mettant une methode qui detecte si les persos partage la meme hitbox
				heros.loseHP();
				System.out.println("AIE J'AI PRIS UN COUP!");
			}
		}
	}
	
	@Override
	public void applyTrapDamage() {
		Tile currentTile=donjon.cases[heros.Xmid][heros.Ymid];
		
		if((currentTile.nature==6) && (!currentTile.isTrapOpen)) {
			currentTile.skin=currentTile.findSource(currentTile.trapSkin);
			currentTile.isTrapOpen=true;
			heros.takeDamage(currentTile.damage);
			System.out.print("Oups! It's a "+currentTile.trapType+" trap!\n");
			System.out.print("HP remained: "+heros.getHP()+"\n");
		}
	}
	
	/**
	 *verifier si on peut passer au prochain niveau
	 */
	public boolean nextlevel() {
		return donjon.cases[heros.Xmid][heros.Ymid].nextlevel;
	}

	/**
	 * verifier si le jeu est fini
	 */
	@Override
	public boolean isFinished() {
		return donjon.cases[heros.Xmid][heros.Ymid].canFinishGame;		
	}

	@Override
	public boolean isTimeOver(long elapsedtime) {
		return (elapsedtime > donjon.Time_Limit);
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
		this.monsters=in_monstres;
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
	
	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

	public Labyrinthe getDonjon() {
		return donjon;
	}

	public ArrayList<Monster> getMonsters() {
		return monsters;
	}

	public int getGamecounter() {
		return gamecounter;
	}
	
	}
