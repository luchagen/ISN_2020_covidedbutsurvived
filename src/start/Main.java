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
		int j=0;
		int nbLevels=2;
		boolean repeat;
		
		// creation du jeu particulier et de son afficheur
		do {
			String source = "levels/";
			source+=j;
			source+=".txt";
			Labyrinthe donjon = new Labyrinthe(source);
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
			if (game.nextlevel()==true)
				repeat=true;
			else
				repeat=false;
			j++;
		}while(repeat && j<nbLevels);
		
		System.out.println(" Félicitation vous avez gagné :JEU TERMINE !");


		}
		

		}


	


