package engine;

import java.util.ArrayList;

import model.Labyrinthe;
import model.Monster;
import model.Pacman;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 *         un jeu qui peut evoluer (avant de se terminer) sur un plateau width x
 *         height
 */
public interface Game {

	/**
	 * methode qui contient l'evolution du jeu en fonction de la commande
	 * 
	 * @param userCmd
	 *            commande utilisateur
	 */
	public void evolve(Cmd userCmd);
	public void evolveMonsters();

	/**
	 * @return true si et seulement si le jeu est fini
	 */
	public boolean isFinished();
	
	public boolean isTimeOver(long elapsedtime);
	
	public boolean isGameOver(long elapsedtime);
	
	public boolean isKilled();
	
	public boolean nextlevel();
	
	public void isBeingTouchedByAMonster();
	
	public void applyTrapDamage();
	
	public void setElapsedTime(int elapsedTime);
	
	public int getElapsedTime();

	public Pacman getHeros();

	public Labyrinthe getDonjon();

	public ArrayList<Monster> getMonsters();

	public int getGamecounter();
	
	public void setNewLevel(Pacman in_heros, Labyrinthe in_donjon, ArrayList<Monster> in_monstres);

}
