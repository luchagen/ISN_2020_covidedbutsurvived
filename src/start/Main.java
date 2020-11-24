package start;

import model.PacmanPainter;
import engine.GameEngineGraphical;
import model.PacmanController;
import model.PacmanGame;
import model.Labyrinthe;
import model.Pacman;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

		// creation du jeu particulier et de son afficheur
		Labyrinthe donjon = new Labyrinthe("fichier.txt");
		//Pacman heros = new Pacman(donjon);
		
		//Monstre[] monstres = new Monstre(donjon)[4];
		//PacmanGame game = new PacmanGame("helpFilePacman.txt",heros,monstres);
		//PacmanPainter painter = new PacmanPainter(game);
		PacmanController controller = new PacmanController();

		// classe qui lance le moteur de jeu generique
		//GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);
		//engine.run();
	}

}
