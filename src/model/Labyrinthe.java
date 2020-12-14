package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Labyrinthe {
	protected static int nb_largeur; // Le nombre de case en largeur du labyrinthe
	protected static int nb_hauteur; // Le nombre de case en hauteur du labyrinthe
	protected  int nb_case;			// Le produit des deux attributs précédents
	protected static int Tile_length; //La taille en pixel d'une case du labyrinthe
									//et donc que chacune des cases est un carré
	protected int level;			// Le niveau attribué au labyrinthe
	protected int Time_Limit;       // Le temps limite donné au joueur pour gagner
	protected int rowRead;			// Définit la ligne lue par chacune des méthodes de cette classe
	protected final int lastRowLengthParameters=5; // Définit la dernière ligne que où se trouve les paramètres de taille
	
	public Tile[][] cases;			// Chacune des cases est représentée par une instance de Tile, elles sont indexées par deux entiers (i,j)
									//représentant les lignes et colonnes du labyrinthe 
	public int[]spawn=new int[2];		// Coordonnées (i,j) représentant la case où le joueur doit apparaitre
	public ArrayList<int[]> spawnMonsters=new ArrayList<int[]>(); //Comme précédemment sauf qu'il s'agit des monstres et il peut y en avoir plusieurs
	
	
	public Labyrinthe(String source) {
		rowRead=1;
		BufferedReader LabReader;
		try {
			LabReader = new BufferedReader(new FileReader(source));
			this.setLengthParameters(LabReader); // Lit les parametres de taille du labyrinthe et definit les attributs correspondants
			cases = new Tile[nb_largeur][nb_hauteur]; // On peut alors definit plus precisément l'attribut cases
			this.setCases(LabReader);  //Par la suite, on déchiffre le code fournit pour generer le labyrinthe 
									  //cela revient à definir quel type de Tile represente la case (i,j)
		//On lève des exceptions au cas où (obligatoire pour compiler)
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Time_Limit=0; // La partie n'est pas lancee
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void setLengthParameters(BufferedReader LabReader) throws IOException { 
		//Initialisation des parametres lies aux dimensions du labyrinthe
		// Cette methode lit a partir de la toute premiere ligne et s'arrete à la lastRowLengthParameters
		String ligne = LabReader.readLine();
		String[] liste;
		while(rowRead<lastRowLengthParameters) {
		// Tant que nous n'avons pas depasse la lastRowLengthParameters, on initialise les attributs de cette classe
		// à partir des elements fournis apres le signe "=" dans le fichier source
			rowRead++;
			ligne = LabReader.readLine();
			liste = ligne.split("=");
			if(rowRead==2)
				this.setLevel(Integer.parseInt(liste[1]));
			else if(rowRead==3)
				this.setNb_largeur(Integer.parseInt(liste[1]));
			else if(rowRead==4)
				this.setNb_hauteur(Integer.parseInt(liste[1]));
			else if(rowRead==5)
				this.setTime_Limit(Integer.parseInt(liste[1]));
			
		}
		// Ces derniers attributs sont calcules en dernier afin d'eviter qu'ils ne valent 0
		nb_case=nb_largeur*nb_hauteur;
		Tile_length =MainPainter.WIDTH/nb_largeur;
		LabReader.readLine();
	}
	
	private void setCases(BufferedReader LabReader) { //Creer le labyrinthe en initiant les cases, le spawn ainsi que le spawnMonsters
		int nat = 0;
		String liste_param[];
		String param[];
		try {
			String ligne;
			for(int i=0;i<nb_hauteur;i++) {
				ligne = LabReader.readLine();
				liste_param = ligne.split("#");
				for(int j=0;j<liste_param.length;j++) {
					param=liste_param[j].split("");
					nat=Integer.parseInt(param[0]);
					switch(nat) {
					case 0:
						this.cases[j][i]=new Wall(liste_param[j]); 
						break;
					case 1:
						this.cases[j][i]=new Floor(liste_param[j]);
						break;
					case 2:
						this.cases[j][i]=new Door(liste_param[j]);
						break;
					case 3:
						this.cases[j][i]=new Spawn(liste_param[j]);
						break;
					case 4:
						this.cases[j][i]=new MonsterSpawner(liste_param[j]);
						break;
					case 5:
						this.cases[j][i]=new Chest(liste_param[j]);
						break;
					default:
						cases[j][i]=new Wall(liste_param[j]);
						break;
				}
				if(nat==3) {
					int[] spawn = {j,i};
					this.setSpawn(spawn);
				}
				else if(nat==4){
					int[] tab= {j,i};
					spawnMonsters.add(tab);
				}
				}
			}
			LabReader.close();
		} catch (IOException e) {
			System.out.println("Fichier source inexistant");
	}
	}
	// Getters et Setters
	private void setSpawn(int[] spawn) {
		this.spawn = spawn;
	}
	public int getNb_largeur() {
		return nb_largeur;
	}
	public int getNb_hauteur() {
		return nb_hauteur;
	}
	public int getNb_case() {
		return nb_case;
	}
	public int getTime_Limit() {
		return Time_Limit;
	}
	public int getTile_length() {
		return Tile_length;
	}
	private void setNb_largeur(int in_nb_largeur) {
		nb_largeur=in_nb_largeur;
	}
	private void setNb_hauteur(int in_nb_hauteur) {
		nb_hauteur=in_nb_hauteur;
	}
	private void setLevel(int in_level) {
		level=in_level;
	}
	private void setTime_Limit(int in_setTime_Limit) {
		Time_Limit=in_setTime_Limit;
	}
}
