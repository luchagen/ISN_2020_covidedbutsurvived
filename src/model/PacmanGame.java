package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import engine.Cmd;
import engine.Game;
import java.util.concurrent.TimeUnit;
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
	protected static final int game_speed=10;
	Pacman heros;
	Labyrinthe donjon;
	//Monster[] monstres;
	
	public PacmanGame(String source, Pacman in_heros, Labyrinthe in_donjon) { //Ajouter monstre plus tard
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
		//monstres=in_monstres;
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
		//for(int i=0;i<monstres.length;i++) {
			//monstres[i].randMove();
	
	}

	/**
	 * verifier si le jeu est fini
	 */
	@Override
	public boolean isFinished() {
		
		{return donjon.cases[((Labyrinthe.Tile_length/2)+ heros.X)/Labyrinthe.Tile_length][((Labyrinthe.Tile_length/2) +heros.Y)/Labyrinthe.Tile_length].canFinishGame;}
		
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
}
