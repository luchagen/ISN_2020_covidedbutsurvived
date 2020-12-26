package model;

import java.util.Random;

public class Monster extends Personnage {
	private int longueurdaction=5;
	private int actionencours=0;
	
	public Monster(int[] spawn) {
		super(spawn);
		this.skin= "img/monster.txt";
		this.HP=1;
		// TODO Auto-generated constructor stub
	}
	
	public void monsterMove(Boolean canmoveleft , Boolean canmoveright, Boolean canmoveup, Boolean canmovedown) {
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
	

}
