package items;

public class Item {
	protected int ItemTypeId;
	protected boolean isAWeapon;
	protected boolean isAHeal;
	protected boolean isAShield;
	protected String source;
	
	public int getItemTypeId() {
		return ItemTypeId;
	}
	public boolean isAWeapon() {
		return isAWeapon;
	}
	public boolean isAHeal() {
		return isAHeal;
	}
	public boolean isAShield() {
		return isAShield;
	}
	public String getSource() {
		return source;
	}
}


