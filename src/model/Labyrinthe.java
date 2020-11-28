package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Labyrinthe {
	protected static int nb_largeur;
	protected static int nb_hauteur;
	protected  int nb_case;
	protected static int Tile_length;
	protected int level;
	protected int Time_Limit;
	protected int rowRead;
	protected final int lastRowLengthParameters=5;
	
	public Tile[][] cases;
	public int[]spawn=new int[2];
	public ArrayList<int[]> spawnMonsters=new ArrayList<int[]>();
	
	
	public Labyrinthe(String source) {
		rowRead=1;
		BufferedReader LabReader;
		try {
			LabReader = new BufferedReader(new FileReader(source));
			this.setLengthParameters(LabReader);
			cases = new Tile[nb_largeur][nb_hauteur];
			this.setCases(LabReader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Time_Limit=0; // La partie n'est pas lancée
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void setLengthParameters(BufferedReader LabReader) throws IOException { //Initialisation des paramètres liés aux dimensions du labyrinthe
		String ligne = LabReader.readLine();
		String[] liste;
		while(rowRead<lastRowLengthParameters) {
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
		nb_case=nb_largeur*nb_hauteur;
		Tile_length =PacmanPainter.WIDTH/nb_largeur;
		LabReader.readLine();
	}
	
	private void setCases(BufferedReader LabReader) { //Créer le labyrinthe en initiant les cases, le spawn ainsi que le spawnMonsters
		int nat = 0;
		String liste_param[];
		String param[];
		try {
			String ligne;
			for(int i=0;i<nb_hauteur;i++) {
				ligne = LabReader.readLine();
				System.out.println(ligne);
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
