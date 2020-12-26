package model;

public class Personnage {
	protected int X,Y,Xwt,Xet,Ynt,Yst,Xw,Xe,Yn,Ys,Xwtwest,Yntnorth,Xmid,Ymid;
	protected String skin;
	protected int HP;

	
	public Personnage(int X_0,int Y_0) { //Constructeur inutile ?
		X=X_0;
		Y=Y_0;
		updateHitbox();
		}
	
	public Personnage(int[] spawn) {
		X=spawn[0]*Labyrinthe.Tile_length;
		Y=spawn[1]*Labyrinthe.Tile_length;
		updateHitbox();
	}
	

	// mise a jour de la position des points qui definissent la hitbox du pacman, a mettre a jour apres chaque deplacement 
		public void updateHitbox() {
			int Tile_length = Labyrinthe.Tile_length;
			int a = (int) (0.355*Tile_length);
			int b = (int) (0.625*Tile_length);
			int c = (int) (0.5*Tile_length);
			Xwtwest=(X+a-PacmanGame.game_speed);
			Xwt=Xwtwest/Tile_length;
			Xet=(X+b+PacmanGame.game_speed)/Tile_length;
			Yntnorth=(Y+a-PacmanGame.game_speed);
			Ynt=Yntnorth/Tile_length;
			Yst=(Y+b+PacmanGame.game_speed)/Tile_length;
			Xw=(X+a)/Tile_length;
			Xe=(X+b)/Tile_length;
			Yn=(Y+a)/Tile_length;
			Ys=(Y+b)/Tile_length;
			Xmid=(X+c)/Tile_length;
			Ymid=(Y+c)/Tile_length;
		}
		
	public void moveUP() {
			Y=Y-PacmanGame.game_speed;
			updateHitbox();
	}
	
	public void collisionUP() {
			Y=Y+1;
			updateHitbox();
	}
	
	public void moveDOWN() {
			Y=Y+PacmanGame.game_speed;
			updateHitbox();
	}
	
	public void collisionDOWN() {
		Y=Y-1;
		updateHitbox();
	}
	
	public void moveLEFT() {
			X=X-PacmanGame.game_speed;
			updateHitbox();
	}
	
	public void collisionLEFT() {
		X=X+1;
		updateHitbox();
	}
	
	public void moveRIGHT() {
			X=X+PacmanGame.game_speed;
			updateHitbox();
	}
	
	public void collisionRIGHT() {
		X=X-1;
		updateHitbox();
	}
	public void loseHP() {
		HP-=1;
	}
	public void takeDamage(int damage) {
		HP-=damage;
	}
	public int getHP() {
		return HP;
	}
}
