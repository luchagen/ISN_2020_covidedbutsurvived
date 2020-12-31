package items;

public class Heal extends Item {
	public Heal() {
		this.ItemTypeId=1;
		this.isAHeal=true;
		this.isAShield=false;
		this.isAWeapon=false;
		this.source="img/items/vaccine.png";
	}
}
