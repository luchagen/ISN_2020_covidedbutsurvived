


package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Labyrinthe.Labyrinthe;
import engine.Cmd;
import model.Monster;
import model.Pacman;
import model.PacmanGame;
import model.Personnage;

class TestPacmanGame {
	Labyrinthe donjon;
	Pacman heros;
	PacmanGame game;
	ArrayList<Monster> monstres;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
	}
	

	@BeforeEach
	void setUp() throws Exception {
		donjon=new Labyrinthe("levels/testfile.txt");
		heros=new Pacman(donjon.spawn);
		monstres = new ArrayList<Monster>();
		for(int i=0;i<donjon.spawnMonsters.size();i++) {
			monstres.add(new Monster(donjon.spawnMonsters.get(i),donjon.typeMonsters.get(i)));
		}
		game=new PacmanGame(heros,donjon,monstres);
		monstres = new ArrayList<Monster>();
		
		for(int i=0;i<donjon.spawnMonsters.size();i++) {
			monstres.add(new Monster(donjon.spawnMonsters.get(i),donjon.typeMonsters.get(i)));
		}
		
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	@Test
	void testEvolveRightCorrect() throws Exception{
		//placer le pacman dans une case ou il peut bouger a droite
		heros.setHitbox(0, 0);
		int xold=heros.getX();
		int yold=heros.getY();
		game.evolve(Cmd.RIGHT);
		assertTrue(heros.getX()==xold+PacmanGame.getGameSpeed());
		assertTrue(heros.getY()==yold);
	}
	
	@Test
	void testEvolveLeftCorrect() throws Exception{
		//placer le pacman dans une case ou il peut bouger a gauche
		heros.setHitbox(1, 0);
		int xold=heros.getX();
		int yold=heros.getY();
		game.evolve(Cmd.LEFT);
		assertTrue(heros.getX()==xold-PacmanGame.getGameSpeed());
		assertTrue(heros.getY()==yold);
	}
	
	@Test
	void testEvolveUpCorrect() throws Exception{
		//placer le pacman dans une case ou il peut bouger vers le haut
		heros.setHitbox(0, 1);
		int xold=heros.getX();
		int yold=heros.getY();
		game.evolve(Cmd.UP);
		assertTrue(heros.getX()==xold);
		assertTrue(heros.getY()==yold-PacmanGame.getGameSpeed());
	}
	
	@Test
	void testEvolveDownCorrect() throws Exception{
		//placer le pacman dans une case ou il peut bouger vers le bas
		heros.setHitbox(0, 0);
		int xold=heros.getX();
		int yold=heros.getY();
		game.evolve(Cmd.DOWN);
		assertTrue(heros.getX()==xold);
		assertTrue(heros.getY()==yold+PacmanGame.getGameSpeed());
	}
	
	@Test
	void testLabyrintheRightBoundary() throws Exception{
		//placer le pacman a l extremite droite
		heros.setXright(donjon.getNb_largeur()*donjon.getTile_length());
		int xold=heros.getX();
		int yold=heros.getY();
		game.evolve(Cmd.RIGHT);
		assertTrue(heros.getX()==xold);
		assertTrue(heros.getY()==yold);
	}
	
	@Test
	void testLabyrintheLeftBoundary() throws Exception{
		//placer le pacman a l extremite gauche
		heros.setXleft(0);
		int xold=heros.getX();
		int yold=heros.getY();
		game.evolve(Cmd.LEFT);
		assertTrue(heros.getX()==xold);
		assertTrue(heros.getY()==yold);
	}
	
	@Test
	void testLabyrintheUpBoundary() throws Exception{
		//placer le pacman a l extremite haut
		heros.setYup(0);
		int xold=heros.getX();
		int yold=heros.getY();
		game.evolve(Cmd.UP);
		assertTrue(heros.getX()==xold);
		assertTrue(heros.getY()==yold);
	}
	
	@Test
	void testLabyrintheDownBoundary() throws Exception{
		//placer le pacman a l extremite bas
		heros.setYdown(donjon.getNb_hauteur()*donjon.getTile_length());
		int xold=heros.getX();
		int yold=heros.getY();
		game.evolve(Cmd.DOWN);
		assertTrue(heros.getX()==xold);
		assertTrue(heros.getY()==yold);
	}

	@Test
	   void testMonsterDamage() throws Exception{
		//tester si l heros perd du hp en touchant le monstre
		int HPold=heros.getHP();
		heros.setHitbox(2, 2);
		game.monstres.get(0).setHitbox(2, 2);
		game.isBeingTouchedByAMonster();
		assertTrue(heros.getHP()!=HPold);
		
	}
	
		
}
