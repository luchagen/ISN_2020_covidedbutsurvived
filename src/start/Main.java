package start;


import engine.GameEngineGraphical;
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

	public static void main(String[] args) throws InterruptedException {
		
		// creation du jeu particulier et de son afficheur
		Labyrinthe donjon = new Labyrinthe("levels/level_1.txt");
		Pacman heros = new Pacman(donjon.spawn);
		Monster[] monstres = new Monster[donjon.spawnMonsters.size()];
		for(int i=0;i<donjon.spawnMonsters.size();i++) {
			monstres[i]=new Monster(donjon.spawnMonsters.get(i));
		}
		
			
		PacmanGame game = new PacmanGame("helpFilePacman.txt",heros,donjon,monstres);
		
		PacmanController controller = new PacmanController();
		
		MainPainter painter = new MainPainter(heros,donjon,monstres,controller);

		// classe qui lance le moteur de jeu generique
		GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);
		
		engine.run();


		}
		

		}


	


