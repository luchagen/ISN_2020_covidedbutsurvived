package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import engine.Cmd;

public class SpriteGroup {
	private Hashtable<String,ArrayList<String>[]> spritelist = new Hashtable<String,ArrayList<String>[]>();
	private int animationcounter;
	private Cmd commandeencours;
	private int stagepointer;
	private int animationpointer;
	private boolean currentanimationfinished;
	private String sprite = "img/error.png";
	public SpriteGroup(String spritefile) {
		BufferedReader SpriteReader;
		animationcounter=40;
		commandeencours=null;
		try {
			String currentgroup = "NONE";
			int currentstage= 0;
			SpriteReader = new BufferedReader( new FileReader(spritefile));
			String ligne;
			String grouptitle;
			ligne = SpriteReader.readLine();
			while (ligne!=null) {
					String currentligne= new String(ligne);
					if (currentligne.charAt(0) == '%') {
						@SuppressWarnings("unchecked")
						ArrayList<String>[] group = (ArrayList<String>[])new ArrayList[2];
						currentgroup = currentligne.replace("%","" );
						grouptitle = new String(currentgroup);
						ArrayList<String> startgroup = new ArrayList<String>(); //liste des sprites du debut de lanimation
						ArrayList<String> loopgroup = new ArrayList<String>();// liste des sprites du loop de lanimation
						
						group[0] = startgroup;
						group[1] = loopgroup;
						
						spritelist.put(grouptitle, group);
						ligne = SpriteReader.readLine();
					}
					else if (currentligne.charAt(0) == '#'){
						if (currentligne.contains("start")) {
							currentstage=0;
						}
						else
							currentstage=1;
						ligne = SpriteReader.readLine();
					}
					else {
						spritelist.get(currentgroup)[currentstage].add(currentligne);
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
	
	private String idlespriteGet(int in_animationcounter, String parametre) {
		return spritelist.get("IDLE"+parametre)[1].get(in_animationcounter%spritelist.get("IDLE"+parametre)[1].size());
	}
	public String currentSpriteGet(int in_animationcounter, Cmd in_commandeencours, String parametre) {
		//System.out.println(in_commandeencours.toString()+parametre);
		try {
		if (animationcounter!=in_animationcounter) {
			animationcounter=in_animationcounter;
			if (commandeencours!=in_commandeencours) {
				commandeencours=in_commandeencours;
				currentanimationfinished=false;
				stagepointer=0;
				animationpointer = 0;
				if (animationpointer<spritelist.get(in_commandeencours.toString()+parametre)[stagepointer].size())
					sprite = spritelist.get(in_commandeencours.toString()+parametre)[stagepointer].get(animationpointer);
				else {
					stagepointer=1;
					animationpointer = 0;
					sprite = spritelist.get(in_commandeencours.toString()+parametre)[stagepointer].get(animationpointer);
				}
				}		
			else {
				if (stagepointer==0) {
					if (animationpointer+1<spritelist.get(in_commandeencours.toString()+parametre)[stagepointer].size()) {
						
						animationpointer+=1;
						sprite = spritelist.get(in_commandeencours.toString()+parametre)[stagepointer].get(animationpointer);
					}
					else {
						stagepointer=1;
						animationpointer = 0;
						if (animationpointer<spritelist.get(in_commandeencours.toString()+parametre)[stagepointer].size()) {
							sprite = spritelist.get(in_commandeencours.toString()+parametre)[stagepointer].get(animationpointer);
						}
						else {
							animationpointer = 0;
							sprite = spritelist.get("IDLE"+parametre)[1].get(0);
							currentanimationfinished=true;
							
						}
					}
				}
				else {
					
					if (animationpointer+1<spritelist.get(in_commandeencours.toString()+parametre)[stagepointer].size()) {
						animationpointer+=1;
						sprite = spritelist.get(in_commandeencours.toString()+parametre)[stagepointer].get(animationpointer);
					}
					else {
						if (currentanimationfinished==true) {
							sprite = idlespriteGet(in_animationcounter, parametre);
						}
						else {
							animationpointer = 0;
							if (animationpointer<spritelist.get(in_commandeencours.toString()+parametre)[stagepointer].size()) {
								sprite = spritelist.get(in_commandeencours.toString()+parametre)[stagepointer].get(animationpointer);
							}
							else {
								animationpointer = 0;
								sprite = spritelist.get("IDLE"+parametre)[1].get(0);
								currentanimationfinished=true;
								
							}
						}
					}
				}
			}
		}
		return sprite;
		}catch (NullPointerException e) {
			e.printStackTrace();
			return "img/error.png";
		}
	}
	
	public String currentSpriteGet(int animationstage, Cmd commandeencours) {
		return currentSpriteGet(animationstage,commandeencours, "");
	}
		
}
