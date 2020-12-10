package model;

public abstract class Tile {
	public int nature;
	boolean canWalkOn;
	boolean canFinishGame;
	boolean nextlevel;
	public String skin;
	
	String findSource(String in_source) { 	//Permet d'assimiler une case à une photo
											// Le fichier source level_i.txt comportait une série de codes xxx attribuée à chacune des cases
											// Cette méthode décode le code xxx en un répertoire menant un fichier image au format png
											// exemple: findSource("123") -> "1/2/3.png"
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



	
	
	
