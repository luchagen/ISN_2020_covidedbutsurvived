package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Labyrinthe {
	protected static int nb_largeur; // Le nombre de case en largeur du labyrinthe
	protected static int nb_hauteur; // Le nombre de case en hauteur du labyrinthe
	protected  int nb_case;			// Le produit des deux attributs pr�c�dents
	protected static int Tile_length; //La taille en pixel d'une case du labyrinthe
									//et donc que chacune des cases est un carr�
	protected int level;			// Le niveau attribu� au labyrinthe
	protected int Time_Limit;       // Le temps limite donn� au joueur pour gagner
	protected int rowRead;			// D�finit la ligne lue par chacune des m�thodes de cette classe
	protected final int lastRowLengthParameters=5; // D�finit la derni�re ligne que o� se trouve les param�tres de taille
	
	public Tile[][] cases;			// Chacune des cases est repr�sent�e par une instance de Tile, elles sont index�es par deux entiers (i,j)
									//repr�sentant les lignes et colonnes du labyrinthe 
	public int[]spawn=new int[2];		// Coordonn�es (i,j) repr�sentant la case o� le joueur doit apparaitre
	public ArrayList<int[]> spawnMonsters=new ArrayList<int[]>(); //Comme pr�c�demment sauf qu'il s'agit des monstres et il peut y en avoir plusieurs
	
	
	public Labyrinthe(String source) {
		rowRead=1;
		BufferedReader LabReader;
		try {
			LabReader = new BufferedReader(new FileReader(source));
			this.setLengthParameters(LabReader); // Lit les param�tres de taille du labyrinthe et d�finit les attributs correspondants
			cases = new Tile[nb_largeur][nb_hauteur]; // On peut alors d�finit plus pr�cis�ment l'attribut cases
			this.setCases(LabReader);  //Par la suite, on d�chiffre le code fournit pour g�n�rer le labyrinthe 
									  //cela revient � d�finir quel type de Tile repr�sente la case (i,j)
		//On l�ve des exceptions au cas o� (obligatoire pour compiler)
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Time_Limit=0; // La partie n'est pas lanc�e
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void setLengthParameters(BufferedReader LabReader) throws IOException { 
		//Initialisation des param�tres li�s aux dimensions du labyrinthe
		// Cette m�thode lit � partir de la toute premi�re ligne et s'arr�te � la lastRowLengthParameters
		String ligne = LabReader.readLine();
		String[] liste;
		while(rowRead<lastRowLengthParameters) {
		// Tant que nous n'avons pas d�pass� la lastRowLengthParameters, on initialise les attributs de cette classe
		// � partir des �l�ments fournis apr�s le signe "=" dans le fichier source
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
		// Ces derniers attributs sont calcul�s en dernier afin d'�viter qu'ils ne valent 0
		nb_case=nb_largeur*nb_hauteur;
		Tile_length =MainPainter.WIDTH/nb_largeur;
		LabReader.readLine();
	}
	
	private void setCases(BufferedReader LabReader) { //Cr�er le labyrinthe en initiant les cases, le spawn ainsi que le spawnMonsters
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
