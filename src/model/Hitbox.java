package model;

public class Hitbox {
	private int Xleft, Xright, Yup, Ydown;
	public Hitbox(int X, int Y, int width,int height) {
		Xleft = X- width/2;
		Xright = X+width/2;
		Yup= Y - height/2;
		Ydown=Y+height/2;
	}
	private Boolean isIn(int X, int Y) {
		if (X <= Xright && X >= Xleft)
			if (Y <= Ydown && Y >= Yup )
				return true;
		return false;
	}
	
	
	public Boolean isCollision(Hitbox hitbox2) {
		if (hitbox2.isIn(Xleft,Yup))
			return true;
		if (hitbox2.isIn(Xleft,Ydown))
			return true;
		if (hitbox2.isIn(Xright, Yup))
			return true;
		if (hitbox2.isIn(Xright, Ydown))
			return true;
		if (this.isIn(hitbox2.getXleft(),hitbox2.getYup()))
			return true;
		if (this.isIn(hitbox2.getXleft(),hitbox2.getYdown()))
			return true;
		if (this.isIn(hitbox2.getXright(), hitbox2.getYup()))
			return true;
		if (this.isIn(hitbox2.getXright(), hitbox2.getYdown()))
			return true;
		return false;
		
	}
	
	
	public void updateHitbox(int X, int Y, int width,int height) {
		Xleft =X-width/2;
		Xright =X+width/2;
		Yup= Y-height/2;
		Ydown=Y+height/2;
	}
	
	public void updateHitbox(int X, int Y) {
		updateHitbox(X,Y,Xright-Xleft, Ydown-Yup);
	}
	
	public int getXleft() {
		return Xleft;
	}
	
	public int getXright() {
		return Xright;
	}
	public int getYup() {
		return Yup;
	}
	public int getYdown() {
		return Ydown;
	}
}
