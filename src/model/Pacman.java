package model;

public class Pacman  extends Personnage{
	public Pacman(int[] spawn) {
		super(spawn);
		// implementation du spritesystem : on remplace la mention directe a un sprite par l'utilisation d un fichier qui indique quels sprites utiliser dans quelles situations
		this.skin= "img/hero.txt";
		this.shield=2;
	}
		

	// mise a jour de la position des points qui definissent la hitbox du pacman, a mettre  a jour apres chaque deplacement 
	public void updateHitbox() {
		int Tile_length = Labyrinthe.Tile_length;
		int a = (int) (0.355*Tile_length);
		int b = (int) (0.625*Tile_length);
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
	}
	
	public void moveUP() {
			Y=Y-PacmanGame.game_speed;
			updateHitbox();
			System.out.println("("+X+","+Y+")");
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
	}
	//le pacman entre en collision avec un mur  bord en allant vers la droite, il  rebondit  (bump)
	public void collisionRIGHT() {
		X=X-1;
		updateHitbox();
		System.out.println("*BUMP*");
	}
}
