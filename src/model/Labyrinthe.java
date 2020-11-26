package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Labyrinthe {
	protected static final int nb_largeur=10;
	protected static final int nb_hauteur=10;
	protected static final int nb_case=nb_largeur*nb_hauteur;
	protected static final int Tile_length =PacmanPainter.WIDTH/nb_largeur;
	protected static final int level=1;
	
	public Tile[][] cases = new Tile[nb_largeur][nb_hauteur];
	public int[]spawn=new int[2];
	public ArrayList<int[]> spawnMonsters=new ArrayList<int[]>();
	
	
	public Labyrinthe(String source) {
		int nat = 0;
		BufferedReader helpReader;
		String liste_param[];
		String param[];
		int liste_param_int;
		try {
			helpReader = new BufferedReader(new FileReader(source));
			String ligne;
			for(int i=0;i<nb_hauteur;i++) {
				ligne = helpReader.readLine();
				liste_param = ligne.split("#");
				for(int j=0;j<liste_param.length;j++) {
					param=liste_param[j].split("");
					nat=Integer.parseInt(param[0]);
					switch(nat) {
					case 0:
						cases[j][i]=new Wall(liste_param[j]);
						break;
					case 1:
						cases[j][i]=new Floor(liste_param[j]);
						break;
					case 2:
						cases[j][i]=new Door(liste_param[j]);
						break;
					case 3:
						cases[j][i]=new Spawn(liste_param[j]);
						break;
					case 4:
						cases[j][i]=new MonsterSpawner(liste_param[j]);
						break;
					default:
						cases[j][i]=new Wall(liste_param[j]);
						break;
				}
				if(nat==3) {
					spawn[0]=j;
					spawn[1]=i;
				}
				else if(nat==4){
					int[] tab= {j,i};
					spawnMonsters.add(tab);
				}
				}
			}
			helpReader.close();
		} catch (IOException e) {
			System.out.println("Fichier inexistant");
	}

}
}
