package model;

import engine.Cmd;

public abstract class Tile {
	protected int nature;
	protected boolean canWalkOn;
	protected boolean canFinishGame;
	protected boolean nextlevel;
	
	//informations destinees au spritegroup
	protected String skin;
	protected String type;
	protected Cmd state;
	
	
	protected String trapType;
	protected int damage;
	protected String trapSkin;
	
	String findSource(String in_source) { 	//Permet d assimiler une case a une photo
											// Le fichier source level_i.txt comportait une serie de codes xxx attribuee a chacune des cases
											// Cette methode decode le code xxx en un repertoire menant un fichier image au format png
											// exemple: findSource("123") -> "1/2/3.png" 
											//DEPRECATED : cette methode n'est plus utilisee en raison du spritesystem qui le remplace
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



	
	
	
