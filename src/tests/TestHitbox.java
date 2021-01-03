package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Hitbox;

class TestHitbox {
	private Hitbox hitbox1;
	private Hitbox[] hitboxes = new Hitbox[100];
	private Hitbox hitbox3;
	
	Random rnd = new Random(2543543);
	@BeforeEach
	void setUp() {
		
	}
	
	@Test
	void testIsCollision() throws Exception{
		hitbox1 = new Hitbox(0,0,10,10);
		for (int i=0; i<100; i++) {
			Integer n = (int) rnd.nextInt(20);
			Integer m = (int) rnd.nextInt(20);
			hitboxes[i]= new Hitbox(n-10,m-10,10,10);
		}
		
		Boolean test=true;
		for (int i=0; i<100; i++) { 
			if(hitbox1.isCollision(hitboxes[i])==false)
				test=false;}
		assertTrue(test);
	}
	
	@Test
	void testIsNotCollision() throws Exception{
		hitbox1 = new Hitbox(0,0,10,10);
		hitbox3	= new Hitbox(11,11,10,10);
		assertFalse(hitbox1.isCollision(hitbox3));
	}
}
