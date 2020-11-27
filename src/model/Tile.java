package model;

public abstract class Tile {
	public int nature;
	boolean canWalkOn;
	boolean canFinishGame;
	public String skin;
	
	String findSource(String in_source) {
		String source = "img/";
		String[] liste_repertoire = in_source.split("");
		for(int i=0;i<liste_repertoire.length;i++) {
			source+=liste_repertoire[i]+"/";
		}
		source=source.substring(0, source.length() - 1);
		source+=".png";
		return source;
	}
}
