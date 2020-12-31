package start;


import java.io.IOException;
import java.util.ArrayList;

import engine.Game;
import engine.GameEngineGraphical;
import model.PacmanController;
import model.PacmanGame;
import model.Labyrinthe;
import model.MainPainter;
import model.Monster;
import model.Pacman;
import model.Item;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {
		int j=0;
		int nbLevels=4;
		boolean repeat;
		Labyrinthe donjon;
		Pacman heros;
		ArrayList<Monster> monstres;
		Game game;
		MainPainter painter;
		GameEngineGraphical engine;
		ArrayList<Item> items;
		//Bullet bullet;
		
		// creation du jeu particulier et de son afficheur
		do {
			String source = "levels/";
			source+=j;
			source+=".txt";
			donjon = new Labyrinthe(source);
			heros = new Pacman(donjon.spawn);
			items=new ArrayList<Item>();
			for(int i=0;i<donjon.spawnItems.size();i++) {
				items.add(new Item(donjon.spawnItems.get(i)));
			}
			monstres = new ArrayList<Monster>();
			for(int i=0;i<donjon.spawnMonsters.size();i++) {
				monstres.add(new Monster(donjon.spawnMonsters.get(i),donjon.typeMonsters.get(i)));
			}
			
			game = new PacmanGame("helpFilePacman.txt",heros,donjon,monstres, items);
			PacmanController controller = new PacmanController();
			
			painter = new MainPainter(controller,game);
			// creation de l'interface graphique
			engine = new GameEngineGraphical(game, painter, controller);
			
			engine.run();
			if (game.nextlevel()==true) {
				repeat=true;
				if(j!=nbLevels-1)
					engine.disposeGUI();
			}
			
			else
				repeat=false;
			j++;
		}while(repeat && j<nbLevels);
		
		if((j==nbLevels)&&repeat) {
			System.out.print("Felicitations vous avez gagne :JEU TERMINE !");
		}

		}
		

		}


	


