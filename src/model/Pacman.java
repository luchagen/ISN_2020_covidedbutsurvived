package model;

public class Pacman {
	public int X,Y;
	public Pacman(int X_0,int Y_0) {
		X=X_0;
		Y=Y_0;
		}
	public Pacman(int[] spawn) {
		
	}
	
	public void moveUP() {
		if(Y>0) {
			Y--;
		}
	}
	public void moveDOWN() {
		if(Y<100) {
			Y++;
		}
		
	}
	public void moveLEFT() {
		if(X>0) {
			X--;
		}
		
	}
	public void moveRIGHT() {
		if(X<100) {
			X++;
			System.out.println("("+X+","+Y+")");
		}
		
	}
	
}
