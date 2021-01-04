package Labyrinthe;

import engine.Cmd;

public abstract class Tile {
	protected int nature;
	protected boolean canWalkOn;
	protected boolean canFinishGame;
	protected boolean nextlevel;
	protected boolean isAnimated;

	// informations destinees au spritegroup
	protected String skin;
	protected String type;
	protected Cmd state;

	protected String trapType;
	protected int damage;
	protected String trapSkin;

	public String findSource(String in_source) { // Permet d assimiler une case a une photo
											// Le fichier source level_i.txt comportait une serie de codes xxx attribuee
											// a chacune des cases
											// Cette methode decode le code xxx en un repertoire menant un fichier image
											// au format png
											// exemple: findSource("123") -> "1/2/3.png"
											// DEPRECATED : cette methode n'est plus utilisee en raison du spritesystem
											// qui le remplace
		String source = "img/";
		String[] liste_repertoire = in_source.split("");
		for (int i = 0; i < liste_repertoire.length; i++) {
			source += liste_repertoire[i] + "/";
		}
		source = source.substring(0, source.length() - 1);
		source += ".png";
		return source;
	}

	public int getNature() {
		return nature;
	}

	public void setNature(int nature) {
		this.nature = nature;
	}

	public boolean isCanWalkOn() {
		return canWalkOn;
	}

	public void setCanWalkOn(boolean canWalkOn) {
		this.canWalkOn = canWalkOn;
	}

	public boolean isCanFinishGame() {
		return canFinishGame;
	}

	public void setCanFinishGame(boolean canFinishGame) {
		this.canFinishGame = canFinishGame;
	}

	public boolean isNextlevel() {
		return nextlevel;
	}

	public void setNextlevel(boolean nextlevel) {
		this.nextlevel = nextlevel;
	}

	public boolean isAnimated() {
		return isAnimated;
	}

	public void setAnimated(boolean isAnimated) {
		this.isAnimated = isAnimated;
	}

	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Cmd getState() {
		return state;
	}

	public void setState(Cmd state) {
		this.state = state;
	}

	public String getTrapType() {
		return trapType;
	}

	public void setTrapType(String trapType) {
		this.trapType = trapType;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public String getTrapSkin() {
		return trapSkin;
	}

	public void setTrapSkin(String trapSkin) {
		this.trapSkin = trapSkin;
	}
}
