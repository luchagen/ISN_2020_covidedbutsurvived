package model;

import java.util.Random;

import engine.Cmd;



public class Monster extends Personnage {
	private int longueurdaction=5;
	private int actionencours=0;
	
	
	public Monster(int[] spawn,int in_type) {
		super(spawn);
		this.skin= "img/monster.txt";
		this.State=Cmd.IDLE;
		switch(in_type)  {
		case 1:
			this.type="covid";
			this.HP=1;
			break;
		case 2:
			this.type="police";
			this.HP=100;
			break;
		default:
			this.type="covid";
			this.HP=1;
			break;
		}
		
		// TODO Auto-generated constructor stub
	}
	
	public void monsterMove(Boolean canmoveleft , Boolean canmoveright, Boolean canmoveup, Boolean canmovedown) {
		if (this.type.equals("covid")) {
			if (longueurdaction!=0) {
				longueurdaction -=1;
				switch (actionencours) {
				case 0:
					if(canmoveup) {
						moveUP();
					}
					else 
						longueurdaction=0;
					break;
				case 2:
					if(canmovedown) {
						moveDOWN();
					}
					else 
						longueurdaction=0;
					break;
				case 1:
					if(canmoveleft) {
						moveLEFT();
					}
					else 
						longueurdaction=0;
					break;
    			
				case 3:
					if(canmoveright) {
						moveRIGHT();
					}
					else 
						longueurdaction=0;
					break;
				case 4:
					break;
				}
			}
		
		
			else {
				Random r = new Random();
				int n = r.nextInt(5);
				actionencours=n;
				switch (n) {
				case 0:
					if(canmoveup) {
						moveUP();
					}
					break;
				case 2:
					if(canmovedown) {
						moveDOWN();
					}
					break;
				case 1:
					if(canmoveleft) {
						moveLEFT();
					}
					break;
			
				case 3:
					if(canmoveright) {
						moveRIGHT();
					}
    			
					break;
				case 4:
    				break;
        		
				}
				Random r1 = new Random();
				longueurdaction = r1.nextInt(10);
			}
		}	
		else if(this.type.equals("police")) {
			if(canmoveup) {
				moveUP();
			}
			else if(canmovedown) {
				moveDOWN();
			}
			else if(canmoveleft) {
				moveLEFT();
			}
			else if(canmoveright) {
				moveRIGHT();
			}
			else this.State=Cmd.IDLE;
		}	
	}
	

}
