package start;


import java.io.IOException;

import engine.Game;
import engine.GameEngineGraphical;
import engine.GraphicalInterface;
import items.Heal;
import items.Inventory;
import items.Item;
import model.PacmanController;
import model.PacmanGame;
import model.Labyrinthe;
import model.MainPainter;
import model.Monster;
import model.Pacman;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {
		Root fenetre = new Root();
		int j=0;
		int maxInventoryNb=18;
		int nbLevels=2;
		int initialHP=5;
		int initialShield=2;
		Inventory initialInventory = new Inventory(maxInventoryNb);	int currentHP=initialHP;
		int currentShield=initialShield;
		Inventory currentInventory = initialInventory;
		boolean repeat;
		Labyrinthe donjon;
		Pacman heros;
		Monster[] monstres;
		Game game;
		MainPainter painter;
		GameEngineGraphical engine;
		GraphicalInterface gui;
		Inventory inventory;
		// creation du jeu particulier et de son afficheur
		do {
			String source = "levels/";
			source+=j;
			source+=".txt";
			
			donjon = new Labyrinthe(source);
			heros = new Pacman(donjon.spawn);
			monstres = new Monster[donjon.spawnMonsters.size()];
			
			for(int i=0;i<donjon.spawnMonsters.size();i++) {
				monstres[i]=new Monster(donjon.spawnMonsters.get(i));
			}
				
			game = new PacmanGame("helpFilePacman.txt",heros,donjon,monstres,currentInventory);
			heros.setHP(currentHP);
			heros.setShield(currentShield);
			game.setInventory(currentInventory);
			if(j==nbLevels-1)
				game.setIsLastLevel(true);
			else
				game.setIsLastLevel(false);
			PacmanController controller = new PacmanController();
			painter = new MainPainter(controller,game);
			// creation de l'interface graphique
			engine = new GameEngineGraphical(game, painter, controller);
			
			engine.run();
			if (game.nextlevel()==true) {
				currentHP=heros.getHP();
				currentShield=heros.getShield();
				currentInventory=game.getInventory();
				repeat=true;
				if(j!=nbLevels-1)
					engine.disposeGUI();
			}
			
			else
				repeat=false;
			j++;
		}while(repeat && j<nbLevels);
		


		}
		

		}


	


