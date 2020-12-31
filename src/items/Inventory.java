package items;


public class Inventory {
	protected Item[] inventory;
	protected int maxNbItem; 
	
	public Inventory(int maxNbItem) {
		this.maxNbItem=maxNbItem;
		inventory=new Item[maxNbItem];
		for(int i=0;i<maxNbItem;i++) {
			inventory[i]=new DefaultItem();
		}
	}
	
	public void addItem(Item item) {
		
		boolean isItemAdded=false;
		Item comp = new DefaultItem();
		int i =0;
		while(i<maxNbItem && isItemAdded==false) {
			if(inventory[i].getItemTypeId()==0) {
				inventory[i]=item;
				
				isItemAdded=true;
			}
			i++;
		}
		if(isItemAdded==false) {
			System.out.println("Inventaire plein !");
		}
	}
	
	public void delItem(int ItemNb) {
		if(ItemNb<maxNbItem) {
			inventory[ItemNb]= new DefaultItem();
		}
	}
	public Item[] getItems() {
		return inventory;
	}
	public Item getItem(int index) {
		if(index<this.maxNbItem)
			return inventory[index];
		return new DefaultItem();
	}
	public int getMaxNbItem() {
		return maxNbItem;
	}
	
}
