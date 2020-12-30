package model;

public class Item {
	protected int X,Y, Xmid,Ymid;
	 protected int  etat=0;
	
	public Item(int[] spawn) {
		
			X=spawn[0]*Labyrinthe.Tile_length;
			Y=spawn[1]*Labyrinthe.Tile_length;
			Xmid=spawn[0];
			Ymid=spawn[1];
			
		}
		
	}
	
	
