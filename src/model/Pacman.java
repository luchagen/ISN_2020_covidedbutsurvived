package model;

import items.Inventory;

public class Pacman  extends Personnage{
	
	
	public boolean haveweapon;
	Inventory inventory;
	
	public Pacman(int[] spawn) {
		super(spawn);
		this.length=19;
		this.height=28;
	}
	
	public Pacman(int[] spawn, Inventory in_inventory) {
		super(spawn);
		this.length= 19;
		this.height=28;
		// implementation du spritesystem : on remplace la mention directe a un sprite par l'utilisation d un fichier qui indique quels sprites utiliser dans quelles situations
		this.skin= "img/hero.txt";
		this.HP=5;
		this.shield=2;
		this.lastMove='R';
		inventory=in_inventory;
		
		// TODO Auto-generated constructor stub
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
