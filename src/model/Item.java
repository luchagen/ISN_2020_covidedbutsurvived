package model;

import engine.Cmd;

public class Item {
	protected int X,Y, Xmid,Ymid;
	
	protected String skin;
	protected String type;
	protected Cmd state;
	
	public Item(int[] spawn) {
		
			X=spawn[0]*Labyrinthe.Tile_length;
			Y=spawn[1]*Labyrinthe.Tile_length;
			Xmid=spawn[0];
			Ymid=spawn[1];
			state = Cmd.IDLE;
			skin="img/Items/items.txt";
			type="weapon";
			
		}
		
	}
	
	
