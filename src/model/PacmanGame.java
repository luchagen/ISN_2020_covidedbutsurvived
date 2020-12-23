package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


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
	private Monster[] monstres;
	int gamecounter=0;
	private int elapsedTime;
	
	public PacmanGame(String source, Pacman in_heros, Labyrinthe in_donjon, Monster[] in_monstres) { 
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
		case SHOUT:
			break;
		case IDLE:
			break;
		}
}
	
	@Override
	public void evolveMonsters() {
		for(int i=0;i<monstres.length;i++) {
			Boolean canmoveright=false;
			Boolean canmoveleft=false;
			Boolean canmoveup=false;
			Boolean canmovedown=false;
			if(monstres[i].Xet<Labyrinthe.nb_largeur) 
				if((donjon.cases[monstres[i].Xet][monstres[i].Yn].canWalkOn) && (donjon.cases[monstres[i].Xet][monstres[i].Ys].canWalkOn)) 
					canmoveright=true;
			if((monstres[i].Xwtwest)>0) 
				if((donjon.cases[monstres[i].Xwt][monstres[i].Yn].canWalkOn) && (donjon.cases[monstres[i].Xwt][monstres[i].Ys].canWalkOn) )
					canmoveleft=true;
			if((monstres[i].Yntnorth)>0) 
				if((donjon.cases[monstres[i].Xw][monstres[i].Ynt].canWalkOn) && (donjon.cases[monstres[i].Xe][monstres[i].Ynt].canWalkOn))
					canmoveup=true;
			if((monstres[i].Yst)<Labyrinthe.nb_hauteur)
				if((donjon.cases[monstres[i].Xw][monstres[i].Yst].canWalkOn) && (donjon.cases[monstres[i].Xe][monstres[i].Yst].canWalkOn))
					canmovedown=true;
			monstres[i].monsterMove(canmoveleft,canmoveright,canmoveup,canmovedown);
	        	
			}	
	}
	
	/*
	 * Applying damages
	 */
	@Override
	public void isBeingTouchedByAMonster() {
		for(int i=0;i<monstres.length;i++) {
			if((monstres[i].Xmid==heros.Xmid)&&(monstres[i].Ymid==heros.Ymid)) { //Changer ça en mettant une methode qui detecte si les persos partage la meme hitbox
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

	public void setNewLevel(Pacman in_heros, Labyrinthe in_donjon, Monster[] in_monstres) {
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

	public Labyrinthe getDonjon() {
		return donjon;
	}

	public Monster[] getMonstres() {
		return monstres;
	}

	public int getGamecounter() {
		return gamecounter;
	}
	
	}
