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
	protected static final int game_speed=Labyrinthe.Tile_length/2;
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
			if((heros.X_case<Labyrinthe.nb_largeur-1) && (donjon.cases[heros.X_case+1][heros.Y_case].canWalkOn))
			heros.moveRIGHT();
			break;
		case LEFT:
			if((heros.X_case>0) && (donjon.cases[heros.X_case-1][heros.Y_case].canWalkOn))
			heros.moveLEFT();
			break;
		case UP:
			if((heros.Y_case>0) && (donjon.cases[heros.X_case][heros.Y_case-1].canWalkOn))
			heros.moveUP();
			break;
		case DOWN:
			if((heros.Y_case<Labyrinthe.nb_hauteur-1) && (donjon.cases[heros.X_case][heros.Y_case+1].canWalkOn))
			heros.moveDOWN();
			break;
		case IDLE:
			break;
		}
		//for(int i=0;i<monstres.length;i++) {
			//monstres[i].randMove();
		//}
	}

	/**
	 * verifier si le jeu est fini
	 */
	@Override
	public boolean isFinished() {
		return donjon.cases[heros.X_case][heros.Y_case].canFinishGame;
	}
}
