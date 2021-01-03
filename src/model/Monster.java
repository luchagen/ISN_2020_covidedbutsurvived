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
			this.length= 32;
			this.height=32;
			this.HP=1;
			break;
		case 2:
			this.type="police";
			this.HP=100;
			this.length= 19;
			this.height=28;
			break;
		case 3:
			this.type="pq";
			this.HP=1;
			this.length= 20;
			this.height=20;
			this.lastMove='L';
			break;
		default:
			this.type="covid";
			this.HP=1;
			this.length= 34;
			this.height=34;
			break;
		}
		
		// TODO Auto-generated constructor stub
	}
	public void monsterMove(Boolean canmoveleft , Boolean canmoveright, Boolean canmoveup, Boolean canmovedown) {
		personnagehitbox.updateHitbox(X, Y,length,height);
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
		else if(this.type.contentEquals("pq")) {
			if(this.lastMove=='L' && canmoveleft==true)
				moveLEFT();
			else if	(this.lastMove=='R' && canmoveright==true)
				moveRIGHT();
			else if (this.lastMove=='L' && canmoveleft==false)
				this.lastMove='R';
			else if	(this.lastMove=='R' && canmoveright==false)
				this.lastMove='L';
		}
	personnagehitbox.updateHitbox(X, Y,length,height);
	}

	public void setState(Cmd state) {
		this.State=state;
	}
}
