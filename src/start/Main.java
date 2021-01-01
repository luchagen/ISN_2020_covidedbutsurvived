package start;


import java.io.IOException;
import java.util.ArrayList;
import engine.Game;
import engine.GameEngineGraphical;
import engine.GraphicalInterface;
import items.DistanceWeapon;
import items.Heal;
import items.Inventory;
import items.Shield;
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
		Root fenetre = new Root();
		int j=0;
		int nbLevels=5;
		int maxInventoryNb=18;
		int initialHP=5;
		int initialShield=2;
		Inventory initialInventory = new Inventory(maxInventoryNb);	int currentHP=initialHP;
		for(int l=0;l<9;l++) {
			initialInventory.addItem(new Heal());
		}
		for(int l=0;l<9;l++) {
			initialInventory.addItem(new DistanceWeapon());
		}
		int currentShield=initialShield;
		Inventory currentInventory = initialInventory;
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
			heros = new Pacman(donjon.spawn, currentInventory);
			items=new ArrayList<Item>();
			for(int i=0;i<donjon.spawnItems.size();i++) {
				items.add(new Item(donjon.spawnItems.get(i)));
			}
			heros = new Pacman(donjon.spawn, currentInventory);
			monstres = new ArrayList<Monster>();
			for(int i=0;i<donjon.spawnMonsters.size();i++) {
				monstres.add(new Monster(donjon.spawnMonsters.get(i),donjon.typeMonsters.get(i)));
			}
			game = new PacmanGame("helpFilePacman.txt",heros,donjon,monstres, items);
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
		if((j==nbLevels)&&repeat) {
			System.out.print("Felicitations vous avez gagne :JEU TERMINE !");
		}

		}
		

		}


	


