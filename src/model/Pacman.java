package model;

import items.Inventory;

public class Pacman  extends Personnage{
	protected char lastMove;
	
	public boolean haveweapon;
	Inventory inventory;
	public Pacman(int[] spawn, Inventory in_inventory) {
		super(spawn);
		// implementation du spritesystem : on remplace la mention directe a un sprite par l'utilisation d un fichier qui indique quels sprites utiliser dans quelles situations
		this.skin= "img/hero.txt";
		this.HP=5;
		this.shield=2;
		this.lastMove='R';
		inventory=in_inventory;
		
		// TODO Auto-generated constructor stub
	}
		
	public void moveUP() {
			Y=Y-PacmanGame.game_speed;
			updateHitbox();
			System.out.println("("+X+","+Y+")");
			this.lastMove='U';
	}
	//le pacman entre en collision avec un mur/ bord en allant vers le haut, il  rebondit  (bump)
	// note: cette mesure est obligatoire (et typique d un jeu video de ce genre) 
	// pour ne pas etre incapable de longer un mur quand on s en rapproche trop
	public void collisionUP() {
			Y=Y+1;
			updateHitbox();
			System.out.println("*BUMP*");
	}
	
	public void moveDOWN() {
			Y=Y+PacmanGame.game_speed;
			updateHitbox();
			System.out.println("("+X+","+Y+")");
			this.lastMove='D';
		}
	//le pacman entre en collision avec un mur/ bord en allant vers le bas, il rebondit  (bump)
	public void collisionDOWN() {
		Y=Y-1;
		updateHitbox();
		System.out.println("*BUMP*");
	}
	
	public void moveLEFT() {
			X=X-PacmanGame.game_speed;
			updateHitbox();
			System.out.println("("+X+","+Y+")");
			this.lastMove='L';
	}
	//le pacman entre en collision avec un mur  bord en allant vers la gauche, il  rebondit  (bump)
	public void collisionLEFT() {
		X=X+1;
		updateHitbox();
		System.out.println("*BUMP*");
	}
	
	public void moveRIGHT() {
			X=X+PacmanGame.game_speed;
			updateHitbox();
			System.out.println("("+X+","+Y+")");
			this.lastMove='R';
	}
	//le pacman entre en collision avec un mur  bord en allant vers la droite, il  rebondit  (bump)
	public void collisionRIGHT() {
		X=X-1;
		updateHitbox();
		System.out.println("*BUMP*");
	}
	public Inventory getInventory() {
		return inventory;
	}
	public void useHeal() {
		int index = inventory.checkItem(1);
		if(index !=-1) {
			if(HP<5) {
				inventory.delItem(index);
				HP+=1;
			}
			else
				System.out.println("Vie deja pleine !");
		}
		else
			System.out.println("Aucun vaccin present dans l'inventaire !");
	}
	public void useShield() {
		int index = inventory.checkItem(2);
		if(index !=-1) {
			if(shield<2) {
				inventory.delItem(index);
				shield+=1;
			}
			else
				System.out.println("Bouclier deja plein !");
		}
		else
			System.out.println("Aucun vaccin present dans l'inventaire !");
	}
}
