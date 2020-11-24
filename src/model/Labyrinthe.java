package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Labyrinthe {
	int nb_largeur=10,nb_hauteur=10;
	int nb_case=nb_largeur*nb_hauteur;
	int level;
	Tile[][] cases = new Tile[nb_largeur][nb_hauteur];
	int[]spawn=new int[2];
	ArrayList<int[]> spawnMonsters=new ArrayList<int[]>();
	public Labyrinthe(String source) {
		int nat = 0;
		BufferedReader helpReader;
		try {
			helpReader = new BufferedReader(new FileReader(source));
			String ligne;
			int i = 0,j=0;
			while (i<nb_largeur) {
				while(j<nb_hauteur)
				{
					ligne = helpReader.readLine();
					if(ligne==null)
						cases[i][j].nature=0;
					else
						nat=Integer.parseInt(ligne);
						cases[i][j].nature=nat;
						if(nat==3) {
							spawn[0]=i;
							spawn[1]=j;
						}
						else if(nat==4){
							int[] tab= {i,j};
							spawnMonsters.add(tab);
						}
					j++;
				}
				i++;
			}
			helpReader.close();
		} catch (IOException e) {
			System.out.println("Help not available");
	}

}
}
