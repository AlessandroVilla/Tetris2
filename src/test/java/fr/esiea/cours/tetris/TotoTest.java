package fr.esiea.cours.tetris;

import static org.junit.Assert.*;
import org.junit.*;
public class TotoTest {

	@Test
	public void shouldReturnNonNull(){
		Toto t = new Toto();
		
		assertNotNull(t.titi().length()>12);
	}
}
