package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

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
	Pacman heros;
	Labyrinthe donjon;
	Monster[] monstres;
	
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
		case IDLE:
			break;
		}
		//evolve monsters
		for(int i=0;i<monstres.length;i++) {
			Random r = new Random();
	        int n = r.nextInt(4);
	        int l=Labyrinthe.Tile_length;
			switch (n) {
				case 0:
					if((monstres[i].X<MainPainter.WIDTH-l) && (donjon.cases[monstres[i].X/l+1][monstres[i].Y/l].canWalkOn))
						monstres[i].X+=l;
					break;
				case 2:
					if((monstres[i].X>=l) && (donjon.cases[monstres[i].X/l-1][monstres[i].Y/l].canWalkOn))
						monstres[i].X-=l;
					break;
				case 1:
					if((monstres[i].Y>=l) && (donjon.cases[monstres[i].X/l][monstres[i].Y/l-1].canWalkOn))
						monstres[i].Y-=l;
					break;
					
				case 3:
					if((monstres[i].Y<MainPainter.WIDTH-l)&& (donjon.cases[monstres[i].X/l][monstres[i].Y/l+1].canWalkOn))
						monstres[i].Y+=l;
					break;
				}
				}	
	}

	/**
	 * verifier si le jeu est fini
	 */
	@Override
	public boolean isFinished() {
		
		{return donjon.cases[((Labyrinthe.Tile_length/2)+ heros.X)/Labyrinthe.Tile_length][((Labyrinthe.Tile_length/2) +heros.Y)/Labyrinthe.Tile_length].canFinishGame;}
		
	}
	/**
	 *verifier si on peut passer au prochain niveau
	 */
	public boolean nextlevel() {
		
		{return donjon.cases[((Labyrinthe.Tile_length/2)+ heros.X)/Labyrinthe.Tile_length][((Labyrinthe.Tile_length/2) +heros.Y)/Labyrinthe.Tile_length].nextlevel;}
		
	}
	/**
	 * verifier si le jeu est fini
	 */
	@Override
	public boolean isGameOver(long elapsedtime) {
		if (elapsedtime < donjon.Time_Limit)
			return false;
		else
			return true;
	}
	
	public void isBeingTouchedByAMonster() {
		int l=Labyrinthe.Tile_length;
		for(int i=0;i<monstres.length;i++) {
			if((monstres[i].X/l==heros.X/l)&&(monstres[i].Y/l==heros.Y/l)) { //Changer ça en mettant une méthode qui détecte si les persos partage la même hitbox
				heros.loseHP();
				System.out.println("AIE J'AI PRIS UN COUP!");
			}
		}
	}
	public boolean isKilled() {
		if(heros.getHP()==0)
			return true;
		else
			return false;
		
	}
	}
