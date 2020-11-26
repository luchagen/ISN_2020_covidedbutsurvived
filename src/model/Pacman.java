package model;

public class Pacman {
	public int X,Y,X_case,Y_case;
	
	public Pacman(int X_0,int Y_0) {
		X=X_0;
		Y=Y_0;
		}
	public Pacman(int[] spawn) {
		X=spawn[0]*Labyrinthe.Tile_length;
		Y=spawn[1]*Labyrinthe.Tile_length;
		X_case=spawn[0];
		Y_case=spawn[1];
	}
	
	public void moveUP() {
		if(Y>0) {
			Y=Y-PacmanGame.game_speed;
			Y_case=Y/Labyrinthe.Tile_length;
			System.out.println("("+X+","+Y+")");
		}
	}
	public void moveDOWN() {
		if(Y<PacmanPainter.HEIGHT-Labyrinthe.Tile_length) {
			Y=Y+PacmanGame.game_speed;
			Y_case=Y/Labyrinthe.Tile_length;
			System.out.println("("+X+","+Y+")");
		}
		
	}
	public void moveLEFT() {
		if(X>0) {
			X=X-PacmanGame.game_speed;
			X_case=X/Labyrinthe.Tile_length;
			System.out.println("("+X+","+Y+")");
			
		}
		
	}
	public void moveRIGHT() {
		if(X<PacmanPainter.WIDTH-Labyrinthe.Tile_length) {
			X=X+PacmanGame.game_speed;
			X_case=X/Labyrinthe.Tile_length;
			System.out.println("("+X+","+Y+")");
		}
		
	}
	
}
