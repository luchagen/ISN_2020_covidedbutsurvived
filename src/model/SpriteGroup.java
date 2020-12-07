package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SpriteGroup {
	public ArrayList<String> spritelist = new ArrayList<String>();
	public int nbsprites;
	public SpriteGroup(String spritefile) {
		BufferedReader SpriteReader;
		try {
			SpriteReader = new BufferedReader( new FileReader(spritefile));
			String ligne;
			ligne = SpriteReader.readLine();
			while (ligne!=null) {
				String currentligne= new String(ligne);
				spritelist.add(currentligne);
				ligne = SpriteReader.readLine();
			}
			nbsprites=spritelist.size();
				
		} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	
	public String currentSpriteGet(int animationstage) {
		return spritelist.get(animationstage%nbsprites);
	}
		
}
