package items;

public class Shield extends Item{
	boolean isAWeapon=false;
	boolean isAPotion=false;
	boolean isAShield=true;
	int ItemTypeId=2;
	int durability;
	
	public Shield() {
		durability=2;
	}
}
