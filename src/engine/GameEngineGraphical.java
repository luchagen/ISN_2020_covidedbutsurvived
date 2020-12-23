package engine;

import java.io.IOException;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * moteur de game generique.
 * On lui passe un game et un afficheur et il permet d'executer un game.
 */
public class GameEngineGraphical {

	/**
	 * le game a executer
	 */
	private Game game;

	/**
	 * l'afficheur a utiliser pour le rendu
	 */
	private GamePainter gamePainter;

	/**
	 * le controlleur a utiliser pour recuperer les commandes
	 */
	private GameController gameController;

	/**
	 * l'interface graphique
	 */
	private GraphicalInterface gui;

	/**
	 * construit un moteur
	 * 
	 * @param game
	 *            game a lancer
	 * @param gamePainter
	 *            afficheur a utiliser
	 * @param gameController
	 *            controlleur a utiliser
	 *            
	 */
	public GameEngineGraphical(Game game, GamePainter gamePainter, GameController gameController) {
		// creation du game
		this.game = game;
		this.gamePainter = gamePainter;
		this.gameController = gameController;
	}

	/**
	 * permet de lancer le game
	 * @throws IOException 
	 */
	public void run() throws InterruptedException, IOException {
		long startTime = System.nanoTime();;
		 long endtime=System.nanoTime();
		 long elapsedtime=(endtime-startTime)/1000000;
	
		
		 this.gui = new GraphicalInterface(this.gamePainter,this.gameController);
		
		// boucle de game


		while (!this.game.isGameOver(elapsedtime) && !this.game.nextlevel()) {

			// demande controle utilisateur
			Cmd c = this.gameController.getCommand();
			// fait evoluer le game
			this.game.evolve(c);
			this.game.applyTrapDamage();
			this.game.evolveMonsters();
			this.game.isBeingTouchedByAMonster();
			// affiche le game
			this.gui.paint();
			endtime=System.nanoTime();
			elapsedtime=(endtime-startTime)/1000000000;
			this.game.setElapsedTime((int)elapsedtime); 
			System.out.print("elapsed time= "+elapsedtime+"sec ");
			// met en attente
			Thread.sleep(20);
		}
		
		if (this.game.isFinished()) 
			System.out.print("Congrats :You won!!");
		else if(this.game.isTimeOver(elapsedtime))
			System.out.println("Le temps limite a ete atteint !");
		else if(this.game.nextlevel()) 
			System.out.println("Level Completed !");
		else
			System.out.println("Game over :HP reached 0!!");
}
	
	public void disposeGUI() {
		this.gui.dispose();
	}
	}
