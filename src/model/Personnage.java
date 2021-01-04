package model;

import Labyrinthe.Labyrinthe;
import engine.Cmd;

public class Personnage {
	protected char lastMove;
	protected boolean canMove;
	
	protected int length,height;
	protected Hitbox personnagehitbox;
	
	protected int X,Y;
	
	
	//pour l affichage, la difference masque/sans masque  sera a affecter dans type pour le pacman
	protected String skin;
	protected String type;
	protected Cmd State;
	
	protected int HP, shield;

	
	public Personnage(int X_0,int Y_0) { //Constructeur inutile ?
		X=X_0;
		Y=Y_0;
		int length = Labyrinthe.Tile_length;
		int height = Labyrinthe.Tile_length;
		personnagehitbox = new Hitbox(X, Y , length, height);
		HP=3;
		canMove=true;
		}
	
	public Personnage(int[] spawn) {
		X=spawn[0]*Labyrinthe.Tile_length+Labyrinthe.Tile_length/2;
		Y=spawn[1]*Labyrinthe.Tile_length+Labyrinthe.Tile_length/2;
		personnagehitbox = new Hitbox(X, Y , length, height);
		HP=5;
		canMove=true;
		lastMove='N';
	}
	

	// mise a jour de la position des points qui definissent la hitbox du pacman, a mettre a jour apres chaque deplacement 
			
	public void moveUP() {
		if (canMove) {
			Y=Y-PacmanGame.game_speed;
			this.State=Cmd.UP;
			personnagehitbox.updateHitbox(X, Y,length,height);
			this.lastMove='U';
		}
	}
	
	public void moveDOWN() {
		if (canMove) {
			Y=Y+PacmanGame.game_speed;
			personnagehitbox.updateHitbox(X, Y,length,height);
			this.State=Cmd.DOWN;
			this.lastMove='D';
		}
	}
	
	public void moveLEFT() {
		if (canMove) {
			X=X-PacmanGame.game_speed;
			personnagehitbox.updateHitbox(X, Y,length,height);
			this.State=Cmd.LEFT;
			this.lastMove='L';
		}
	}
	
	public void moveRIGHT() {
		if (canMove) {
			X=X+PacmanGame.game_speed;
			personnagehitbox.updateHitbox(X, Y,length,height);
			this.State=Cmd.RIGHT;
			this.lastMove='R';
		}
	}
	public void loseHP() {
		if(shield>0)
			shield-=1;
		else
			HP-=1;
	}
	public void takeDamage(int damage) {
		HP-=damage;
	}
	public int getHP() {
		return HP;
	}
	public int getShield() {
		if(this.shield!=0)
			this.type="maskfull";
		else
			this.type="maskless";
		return shield;
	}
	public void setHP(int value) {
		this.HP=value;
	}
	public void setShield(int value) {
		this.shield=value;
		if(this.shield!=0)
			this.type="maskfull";
		else
			this.type="maskless";
	}
	
	public void setXright(int Xright) {
		X=Xright-length/2;
		this.personnagehitbox.updateHitbox(X, Y,length,height);
	}
	public void setXleft(int Xleft) {
		X=Xleft+length/2;
		this.personnagehitbox.updateHitbox(X, Y,length,height);
	}
	public void setYup(int Yup) {
		Y=Yup+height/2;
		this.personnagehitbox.updateHitbox(X,Y,length,height);
	}
	public void setYdown(int Ydown) {
		Y=Ydown-height/2;
		this.personnagehitbox.updateHitbox(X,Y,length,height);
	}
	
	public void setHitbox(int Xcase,int Ycase) {
		X=Xcase*Labyrinthe.Tile_length+Labyrinthe.Tile_length/2;
		Y=Ycase*Labyrinthe.Tile_length+Labyrinthe.Tile_length/2;
		this.personnagehitbox.updateHitbox(X, Y,length, height);
	}

	public int getX() {
		return X;
	}
	public int getY() {
		return Y;
	}
	
	public void setcoordonées(int in_x,int in_y) {
		X=in_x;
		Y=in_y;
	}
	public String getType() {
		return this.type;
	}
	public String getSkin() {
		return skin;
	}
	public Cmd getState() {
		return State;
	}

}
