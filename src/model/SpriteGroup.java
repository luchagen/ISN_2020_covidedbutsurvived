package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import engine.Cmd;

public class SpriteGroup {
	private Hashtable<String,ArrayList<String>> spritelist = new Hashtable<String,ArrayList<String>>();
	public SpriteGroup(String spritefile) {
		BufferedReader SpriteReader;
		try {
			String currentgroup = "NONE";
			SpriteReader = new BufferedReader( new FileReader(spritefile));
			String ligne;
			String grouptitle;
			ligne = SpriteReader.readLine();
			while (ligne!=null) {
					String currentligne= new String(ligne);
					if (currentligne.charAt(0) == '%') {
						currentgroup = currentligne.replace("%","" );
						grouptitle = new String(currentgroup);
						ArrayList<String> group = new ArrayList<String>();
						spritelist.put(grouptitle, group );
						ligne = SpriteReader.readLine();
					}
					else {
						spritelist.get(currentgroup).add(currentligne);
						ligne = SpriteReader.readLine();
					}
						
						
			}
				
		} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	
	public String currentSpriteGet(int animationstage, Cmd commandeencours) {
		return spritelist.get(commandeencours.toString()).get(animationstage%spritelist.get(commandeencours.toString()).size());
	}
		
}
