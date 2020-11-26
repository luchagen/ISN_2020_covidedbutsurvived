package start;


import engine.GameEngineGraphical;
import model.PacmanController;
import model.PacmanGame;
import model.Labyrinthe;
import model.MainPainter;

import model.Pacman;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		// creation du jeu particulier et de son afficheur
		Labyrinthe donjon = new Labyrinthe("donjon.txt");
		Pacman heros = new Pacman(donjon.spawn);
		//Monster[] monstres = new Monster[donjon.spawnMonsters.size()];
		
		//PacmanGame game = new PacmanGame("helpFilePacman.txt",heros,monstres);
		PacmanGame game = new PacmanGame("helpFilePacman.txt",heros,donjon);
		MainPainter painter = new MainPainter(heros,donjon);
		PacmanController controller = new PacmanController();

		// classe qui lance le moteur de jeu generique
		GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);
		
		engine.run();
<<<<<<< HEAD
		}
		
=======
		System.out.println();
>>>>>>> a2a8b684bd37f7dae5d978541f4171d2bb57c734
	}


