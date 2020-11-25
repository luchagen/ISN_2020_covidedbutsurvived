package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Labyrinthe {
	int nb_largeur=10,nb_hauteur=10;
	int nb_case=nb_largeur*nb_hauteur;
	int level;
	public Tile[][] cases = new Tile[nb_largeur][nb_hauteur];
	public int[]spawn=new int[2];
	public ArrayList<int[]> spawnMonsters=new ArrayList<int[]>();
	public Labyrinthe(String source) {
		int nat = 0;
		BufferedReader helpReader;
		try {
			helpReader = new BufferedReader(new FileReader(source));
			String ligne;
			for(int i=0;i<nb_largeur;i++) {
				for(int j=0;j<nb_hauteur;j++) {
					System.out.println(i+"   "+j);
					ligne = helpReader.readLine();
					if(ligne==null)
						cases[i][j]=new Wall();
					else {
						nat=Integer.parseInt(ligne);
						switch(nat) {
							case 0:
								cases[i][j]=new Wall();
								break;
							case 1:
								cases[i][j]=new Floor();
								break;
							case 2:
								cases[i][j]=new Door();
								break;
							case 3:
								cases[i][j]=new Spawn();
								break;
							case 4:
								cases[i][j]=new MonsterSpawner();
								break;
							default:
								cases[i][j]=new Wall();
								break;
						}
						if(nat==3) {
							spawn[0]=i;
							spawn[1]=j;
						}
						else if(nat==4){
							int[] tab= {i,j};
							spawnMonsters.add(tab);
						}
					}
				}
			}
			helpReader.close();
		} catch (IOException e) {
			System.out.println("Fichier inexistant");
	}

}
}
