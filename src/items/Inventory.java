package items;

import java.util.ArrayList;

public class Inventory {
	protected Item[] inventory;
	protected int maxNbItem; 
	
	public Inventory(int maxNbItem) {
		this.maxNbItem=maxNbItem;
		inventory=new Item[maxNbItem];
	}
	public void addItem(Item item) {
		boolean isItemAdded=false;
		Item comp = NullItem();
		for(int i=0;i<maxNbItem;i++) {
			if(inventory[i]= && isItemAdded==false) {
				inventory[i]=item;
				isItemAdded=true;
			}
		}
		if(isItemAdded==false) {
			System.out.println("Inventaire plein !");
		}
	}
	
	public void delItem(int ItemNb) {
		if(ItemNb<maxNbItem) {
			inventory[ItemNb]=null;
		}
	}
	
}
