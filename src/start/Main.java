package start;

import model.PacmanPainter;
import engine.GameEngineGraphical;
import model.PacmanController;
import model.PacmanGame;
import model.Labyrinthe;
import model.Monster;
import model.Pacman;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

		// creation du jeu particulier et de son afficheur
		
		Labyrinthe donjon = new Labyrinthe("fichier.txt");
		int nbmonstres= donjon.getNbrMonsters();
		Pacman heros = new Pacman(donjon.getSpawn());
		Monster[] monstres = new Monster[nbmonstres];
		for (int i=0 ;i<nbmonstres ; i++ )
			monstres[i]=new Monster(donjon.getMonsterspawns(i)[0],donjon.getMonsterspawns(i)[1]);
		PacmanGame game = new PacmanGame("helpFilePacman.txt",heros,monstres);
		PacmanPainter painter = new PacmanPainter(game);
		PacmanController controller = new PacmanController();

		//classe qui lance le moteur de jeu generique
		GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);
		engine.run();
	}

}
